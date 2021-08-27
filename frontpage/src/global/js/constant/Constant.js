/**
 * @author Zee
 * @createDate 2018/02/02 05:11:00
 * @updateDate 2018/02/02 05:11:00
 * @description  后台首页的js方法。
 */

$(document).ready(function() {


});

//nginx静态资源服务器
var NGINX_SERVER_IMG = "http://192.168.200.193:8081/res/img/";
// 服务器反向代理解决跨域
// var INTERFACE_SERVER="http://192.168.200.193:8081/mango/" ;
// 演示服务器

// 线上使用resource/conf/js/serverConfig.js，实现热部署
// var INTERFACE_SERVER="http://192.168.200.193:8081/mango" ;
var INTERFACE_SERVER = "http://localhost:60011/mango";

// var INTERFACE_SERVER="http://172.16.100.108:60011/mango" ;
var HOME_PATH = 'http://localhost';
// var INTERFACE_SERVER="http://127.0.0.1:60011/mango" ;
var RESULT_CODE_TOKEN_EXPIRED = 950000;

var BASE_PATH = "/";
var GP_INDEX = "/pc/ss/gp/html/in/Index.html";
var GP_LOGIN = "/pc/ss/gp/html/lo/Login.html";// 登陆页面

// 门户网站暂时无法引用到本常量JS文件，修正后，将上面用到链接的部分改成引入此常量。
// 门户网站（WebPortals)地址
var URL_WP = "http://192.168.200.193:8081/pc/ss/pi/front/dist/html/index/index.html";
// 数据采集（DataAcquisition）
var URL_DA = "";
// 监测预警Monitoring and Forecasting
var URL_MF = "";
// 溯源系统Security Traceability System
var URL_ST = "http://192.168.200.193:60080/cos/sy/index.html";

// 列表页 默认分页大小
var DEFAULT_PAGE_SIZE = 10;
// 列表页默认起始页
var DEFAULT_PAGE_INDEX = 1;

var DOMAIN_ID_WP = '032769fd7e376c04fb13c66419a72598';
var DOMAIN_ID_GP = '09335bd69ab9826df8b69589a2568055';

var DCODE_BOOLEAN_T = 0;
var DCODE_BOOLEAN_F = 1;
