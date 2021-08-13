var webpack = require("webpack");

const path = require('path');
const glob = require('glob');
const fs = require('fs');
const TerserPlugin = require("terser-webpack-plugin");
const HtmlWebpackPlugin = require('html-webpack-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin')
const CssMinimizerPlugin = require('css-minimizer-webpack-plugin');
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const outputPath = path.resolve(__dirname, "./dist");

const var_interface = require('dotenv').config({ path: './src/global/js/constant/Interface.js' })
const var_Constant = require('dotenv').config({ path: './src/global/js/constant/Constant.js' })
const var_page = require('dotenv').config({ path: './src/global/js/constant/Page.js' })
const var_dictionary = require('dotenv').config({ path: './src/global/js/constant/Dictionary.js' })

//多页面应用打包
let htmlEntry = function () {
    let entry = {};
    glob.sync('{./src/gp/html/gp/UserList.html,./src/gp/html/in/Index.html,./src/gp/html/lo/Login.html}').map(e => {
        const name = e.split('/')[5];
        if (name)
            entry[name] = e;
    });
    return entry;
}();

let HtmlWebpack = [];
Object.entries(htmlEntry).forEach(e => {
    HtmlWebpack.push(new HtmlWebpackPlugin({
        filename: `${e[1]}`.replace('/src/', '/'),
        template: e[1],
        minify: false,
    }));
});
HtmlWebpack.push(new MiniCssExtractPlugin({
}));

let jsEntry = function () {
    let entry = {};
    glob.sync('./src/gp/js/in/Index.js').map(e => {
        const name = e.replace('/src/', '/');
        if (name)
            entry[name] = e;
    });
    return entry;
}();

/**************************因为有hash为后缀，我们的js打包不会替换，这样会出现堆积js的现象。在打包js前，将目标文件夹里重复的js文件处理掉*********************************/
function removeFile(entry, outputPath) {
    var files = [];
    if (fs.existsSync(outputPath)) { // 查询路径是否存在，返回Boolean值
        files = fs.readdirSync(outputPath); // 方法将返回一个包含“指定目录下所有文件名称”的数组对象。
        files.forEach((file) => {
            var fileName = file.split('.')[0]; // 获取文件名
            var curPath = outputPath + "/" + file; // 文件的绝对路径
            if (entry[fileName]) {
                fs.unlinkSync(curPath); // 删除文件操作
            }
        });
    }
}
removeFile(jsEntry, outputPath);
/**************************因为有hash为后缀，我们的js打包不会替换，这样会出现堆积js的现象。在打包js前，将目标文件夹里重复的js文件处理掉*********************************/
module.exports = {
    entry: jsEntry,
    output: {
        filename: "[name].[chunkhash].js",
        chunkFilename: "[name].js",
        path: outputPath,

    },
    devtool: 'eval-source-map',
    module: {
        rules: [{
            test: /\.html?$/,
            use: [{
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
            test: /\.css$/i,
            use: [{
                loader: MiniCssExtractPlugin.loader, options: {

                },
            }, 'css-loader']
        },
        {
            test: /\.(jpe?g|png|svg|gif|ico)/i,
            type: 'asset/resource',
            generator: {
                filename: 'img/[name]-[hash].[ext]'
            }
        },
        {
            test: /\.(woff2?|woff?|eot|ttf|otf)/i,
            type: 'asset/resource',
            generator: {
                filename: 'font/[name]-[hash].[ext]'
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