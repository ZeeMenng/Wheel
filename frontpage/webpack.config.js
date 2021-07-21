/**
 * Created by Molay on 16/7/30.
 */
var webpack = require("webpack");
const path = require('path');
const glob = require('glob');
const fs = require('fs');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const outputPath = path.resolve(__dirname, "./dist");
// //优化打包时间
// const threadLoader = require('thread-loader');
// const jsWorkerPool = {
//     // options

//     // 产生的 worker 的数量，默认是 (cpu 核心数 - 1)
//     // 当 require('os').cpus() 是 undefined 时，则为 1
//     workers: 20,

//     // 闲置时定时删除 worker 进程
//     // 默认为 500ms
//     // 可以设置为无穷大， 这样在监视模式(--watch)下可以保持 worker 持续存在
//     poolTimeout: 2000
// };

// const cssWorkerPool = {
//     // 一个 worker 进程中并行执行工作的数量
//     // 默认为 20
//     workerParallelJobs: 20,
//     poolTimeout: 2000
// };

// threadLoader.warmup(jsWorkerPool, ['babel-loader', 'html-loader', 'webpack-ssi-include-loader', 'file-loader']);
// threadLoader.warmup(cssWorkerPool, ['css-loader', 'style-loader']);

const var_interface = require('dotenv').config({ path: './src/global/js/constant/Interface.js' })
const var_Constant = require('dotenv').config({ path: './src/global/js/constant/Constant.js' })
const var_page = require('dotenv').config({ path: './src/global/js/constant/Page.js' })
const var_dictionary = require('dotenv').config({ path: './src/global/js/constant/Dictionary.js' })

if (var_interface.error) {
    throw var_interface.error
}


//多页面应用打包
let htmlEntry = function () {
    let entry = {};

    //./src/**/*.html
    glob.sync('./src/gp/html/lo/Login.html').map(e => {
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
        template: e[1]

    }));

});

let jsEntry = function () {
    let entry = {};
    //./src/**/*.js
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
    if (fs.existsSync(outputPath)) {    // 查询路径是否存在，返回Boolean值
        files = fs.readdirSync(outputPath); // 方法将返回一个包含“指定目录下所有文件名称”的数组对象。
        files.forEach((file) => {
            var fileName = file.split('.')[0];    // 获取文件名
            var curPath = outputPath + "/" + file;  // 文件的绝对路径
            if (entry[fileName]) {
                fs.unlinkSync(curPath);   // 删除文件操作
            }
        });

    }
}

removeFile(jsEntry, outputPath);
/**************************因为有hash为后缀，我们的js打包不会替换，这样会出现堆积js的现象。在打包js前，将目标文件夹里重复的js文件处理掉*********************************/
module.exports = {

    entry: jsEntry,
    output: {
        filename: "[name].[chunkhash].js", // 打包后的文件
        chunkFilename: "[name].js",
        path: outputPath, //所有输出文件的目标路径;path是webpack所有文件的输出的路径，必须是绝对路径，比如：output输出的js,url-loader解析的图片，HtmlWebpackPlugin生成的html文件，都会存放在以path为基础的目录下

    },
    // 为了确保断点信息能被VSCode正确读取，浏览器需要知道原始代码和打包代码的映射关系，这就是SourceMap要干的事情。
    // 若构建速度较慢，可改用 cheap-module-source-map 或 cheap-module-eval-source-map 模式，去掉列映射
    // 不建议使用 source-map，除非你不赶时间，也不在乎源码安全问题
    devtool: 'eval-source-map',

    //文件加载器 loader
    //loader 让 webpack 能够去处理那些非 JavaScript 文件（webpack 自身只理解 JavaScript）
    module: {
        rules: [
            {
                test: /\.html?$/,
                use: [
                    {
                        loader: 'html-loader', // Used to output as html
                    },
                    {
                        loader: 'webpack-ssi-include-loader',
                        options: {
                            localPath: path.join(__dirname, '/src/gp/html/cl/')
                            //location: 'https://your.website.com/', // http url where the file can be dl
                        },
                    },
                ],
            },
            // {
            //     test: /\.js$/,
            //     use: {
            //         loader: 'babel-loader'
            //     }
            // },
            {
                test: /\.(css|sass|scss)$/,
                use: ['style-loader', 'css-loader']
            },

            {
                test: /\.xml$/,
                use: [{
                    loader: 'xml-loader'
                }]
            },
            {
                test: /\.(png|svg|jpg|gif|mp4|ico)$/,
                use: [{
                    loader: 'file-loader'
                }]
            }
        ]

    },
    // // 本地服务器配置，当使用VSCode调试时屏蔽这块的配置
    // devServer: {
    //     contentBase: './dist',  //默认本地服务器所在的根目录
    //     historyApiFallback: false,   //是否跳转到index.html
    //     inline: true,   //源文件改变时刷新页面
    //     port: 8084  //端口号，默认8080
    // },

    plugins: HtmlWebpack,
    //mode: 'production'
    mode: 'development'
};









