var webpack = require("webpack");
const glob = require('glob');
const fs = require('fs');
const TerserPlugin = require("terser-webpack-plugin");
const HtmlWebpackPlugin = require('html-webpack-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const CssMinimizerPlugin = require('css-minimizer-webpack-plugin');
const MiniCssExtractPlugin = require("mini-css-extract-plugin");

const path = require('path');
const outputPath = path.resolve(__dirname, "./dist");

const SpeedMeasurePlugin = require("speed-measure-webpack-plugin");

//多页面应用打包
let htmlEntry = function () {
    let entry = {};
    glob.sync("./src/gp/html/**/*.html").map(e => {
        const name = e.split('/')[5];
        if (name)
            entry[name] = e;
    });
    return entry;
}();


let HtmlWebpack = [];
//清理dist
HtmlWebpack.push(new CleanWebpackPlugin());

HtmlWebpack.push(new MiniCssExtractPlugin({

}));
//查看各loader的花费时间
HtmlWebpack.push(new SpeedMeasurePlugin());
Object.entries(htmlEntry).forEach(e => {
    HtmlWebpack.push(new HtmlWebpackPlugin({
        filename: `${e[1]}`.replace('/src/', '/'),
        template: e[1],
    }));
});
let jsEntry = function () {
    let entry = {};
    glob.sync('./src/main.js').map(e => {
        const name = e.replace('/src/', '/');
        if (name)
            entry[name] = e;
    });
    return entry;
}();
module.exports = {
    entry: jsEntry,
    output: {
        filename: 'main.js',
        path: outputPath
    },

    devtool: 'eval-source-map',
    module: {
        rules: [
            {
                test: /\.html?$/,
                use: [
                    {
                        loader: 'html-loader',
                    },
                    {
                        loader: 'webpack-ssi-include-loader',
                        options: {
                            localPath: path.join(__dirname, '/src/gp/html/cl/')
                        },
                    },
                ],
            },
            {
                test: /\.css/i,
                use: [
                    {
                        loader: MiniCssExtractPlugin.loader,
                    }, {
                        loader: 'css-loader',
                    }
                ]
            },
            {
                test: /\.(jpe?g|png|gif|ico)/i,
                type: 'asset/resource',
                generator: {
                    filename: 'img/[name]-[hash].[ext]'
                }
            },
            {
                test: /\.(woff2?|woff?|eot|ttf|otf|svg)/i,
                type: 'asset/resource',
                generator: {
                    filename: 'fonts/[name][ext]'
                }
            },
            {
                test: /\.txt/,
                type: 'asset/source'
            },

        ],
    },
    plugins: HtmlWebpack,
    mode: 'development'
};

