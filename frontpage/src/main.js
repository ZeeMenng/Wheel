
var fontAwesomeCss = require("./gp/assets/global/plugins/font-awesome/css/font-awesome.min.css");
var simpleLineIconsCss = require("./gp/assets/global/plugins/simple-line-icons/simple-line-icons.min.css");
var bootstrapCss = require("./gp/assets/global/plugins/bootstrap/css/bootstrap.min.css");
var bootstrapSwitchCss = require("./gp/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css");
var jqueryUiCss = require("./global/plugins/jquery-ui-1.12.1/jquery-ui.min.css");

var componentsCss = require("./gp/assets/global/css/components.min.css");
var pluginsCss = require("./gp/assets/global/css/plugins.min.css");

var select2Css = require("./gp/assets/global/plugins/select2/css/select2.min.css");
var select2BootstrapCss = require("./gp/assets/global/plugins/select2/css/select2-bootstrap.min.css");
var loginCss = require("./gp/assets/pages/css/login.min.css");

var bootstrapWysihtml5Css = require("./gp/assets/global/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.css");
var bootstrapMarkdownCss = require("./gp/assets/global/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css");
var datatablesCss = require("./gp/assets/global/plugins/datatables/datatables.min.css");
var datatablesBootstrapCss = require("./gp/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css");
var bootstrapDatetimepickerCss = require("./gp/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css");
var bootstrapDatepickerCss = require("./gp/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker.css");

var layoutCss = require("./gp/assets/layouts/layout/css/layout.min.css");
var darkblueCss = require("./gp/assets/layouts/layout/css/themes/darkblue.min.css");
var customCss = require("./gp/assets/layouts/layout/css/custom.min.css");

var bootstrapSelectCss = require("./gp/assets/global/plugins/bootstrap-select/css/bootstrap-select.css");

var layuiCss = require("./global/plugins/layui/css/layui.css");
var uploadifyCss = require("./global/plugins/jquery-uploadify/uploadify.css");
var layerCss = require("./global/plugins/layui/css/modules/layer/default/layer.css");
var typeaheadCss = require("./global/plugins/typeahead/typeahead.css");
var fileinputCss = require("./gp/assets/global/plugins/bootstrap-fileinput-4.4.8/css/fileinput.css");
var jsonviewCss = require("./global/plugins/jquery-jsonview-1.2.3/jquery.jsonview.css");
var metroStyleCss = require("./global/plugins/zTree_v3/css/metroStyle/metroStyle.css");


var CommonCss = require("./gp/css/Common.css");

/*import jqueryJs from './gp/assets/global/plugins/jquery.min.js';
import jqueryUiJs from './global/plugins/jquery-ui-1.12.1/jquery-ui.min.js';
import bootstrapJs from './gp/assets/global/plugins/bootstrap/js/bootstrap.min.js';
import cookieJs from './gp/assets/global/plugins/js.cookie.min.js';
import slimscrollJs from './gp/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js';
import blockuiJs from './gp/assets/global/plugins/jquery.blockui.min.js';
import bootstrapSwitchJs from './gp/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js';

import datatableJs from './gp/assets/global/scripts/datatable.js';
import datatableMinJs from './gp/assets/global/plugins/datatables/datatables.min.js';
import datatablesBootstrapJs from './gp/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js';
import select2FullJs from './gp/assets/global/plugins/select2/js/select2.full.min.js';
import jqueryValidateJs from './gp/assets/global/plugins/jquery-validation/js/jquery.validate.js';
import additionalMethodJs from './gp/assets/global/plugins/jquery-validation/js/additional-methods.min.js';
import bootstrapDatepickerJs from './gp/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js';
import bootstrapDatepickerZhJs from './gp/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js';
import wysihtml5Js from './gp/assets/global/plugins/bootstrap-wysihtml5/wysihtml5-0.3.0.js';
import bootstrapWysihtml5Js from './gp/assets/global/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.js';
import ckeditorJs from './gp/assets/global/plugins/ckeditor/ckeditor.js';
import markdownJs from './gp/assets/global/plugins/bootstrap-markdown/lib/markdown.js';
import bootstrapMarkdownJs from './gp/assets/global/plugins/bootstrap-markdown/js/bootstrap-markdown.js';
import appJs from './gp/assets/global/scripts/app.min.js';
import tableDatatablesManagedJs from './gp/assets/pages/scripts/table-datatables-managed.min.js';
import componentsSelect2Js from './gp/assets/pages/scripts/components-select2.min.js';
import layoutJs from './gp/assets/layouts/layout/scripts/layout.min.js';
import demoJs from './gp/assets/layouts/layout/scripts/demo.min.js';
import quickSidebarJs from './gp/assets/layouts/global/scripts/quick-sidebar.min.js';
import quickNavJs from './gp/assets/layouts/global/scripts/quick-nav.min.js';
import bootstrapSelectJs from './gp/assets/global/plugins/bootstrap-select/js/bootstrap-select.js';
import componentsBootstrapSelectJs from './gp/assets/pages/scripts/components-bootstrap-select.min.js';
import ajaxhookJs from './global/plugins/Ajax-hook/dist/ajaxhook.min.js';
import uploadifyJs from './global/plugins/jquery-uploadify/jquery.uploadify.min.js';


import layuiJs from './global/plugins/layui/layui.js';
import layerJs from './global/plugins/layui/lay/modules/layer.js';
import handlebarsJs from './global/plugins/typeahead/handlebars.min.js';
import typeaheadJs from './global/plugins/typeahead/typeahead.bundle.min.js';
import fileinputJs from './gp/assets/global/plugins/bootstrap-fileinput-4.4.8/js/fileinput.js';
import fileinputZhJs from './gp/assets/global/plugins/bootstrap-fileinput-4.4.8/js/locales/zh.js';
import widgetJs from './global/plugins/jQuery-File-Upload/js/vendor/jquery.ui.widget.js';
import iframeTransportJs from './global/plugins/jQuery-File-Upload/js/jquery.iframe-transport.js';
import fileuploadJs from './global/plugins/jQuery-File-Upload/js/jquery.fileupload.js';
import fileuploadProcessJs from './global/plugins/jQuery-File-Upload/js/jquery.fileupload-process.js';
import fileuploadValidateJs from './global/plugins/jQuery-File-Upload/js/jquery.fileupload-validate.js';
import jsonviewJs from './global/plugins/jquery-jsonview-1.2.3/jquery.jsonview.js';
import loginJs from './gp/assets/pages/scripts/login.min.js';

import coConstantJs from './gp/js/co/Constant.js';
import constantJs from './global/js/constant/Constant.js';
import cictionaryJs from './global/js/constant/Dictionary.js';
import pageJs from './global/js/constant/Page.js';
import interfaceJs from './global/js/constant/Interface.js';
import commonJs from './gp/js/co/Common.js';*/


$(document).ready(function () {


});