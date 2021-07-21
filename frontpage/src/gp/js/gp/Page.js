/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description 系统页面。 相关页面的js方法。
 */

$(document).ready(function() {

	// 初始化应用领域下拉框
	var selectParam = {
		selectId : "selectTypeDomain",
		textField : "name",
		valueField : "id"
	};

	var ajaxParam = {
		url : RU_GPDOMAIN_GETLISTBYJSONDATA + "?jsonData={}"
	}
	initDropDownList(selectParam, ajaxParam);

	// 初始化是否为公共页面下拉框
	var selectParamAdmin = {
		selectId : "selectIsPublicCode",
		textField : "text",
		valueField : "code"
	};
	var ajaxParamAdmin = {
		url : RU_GPDICTIONARY_GETLISTBYTYPEID + DI_BOOLEAN
	}
	initDropDownList(selectParamAdmin, ajaxParamAdmin);

});

function updatePageConstant() {

	var pagePathConfig = getUserConfigByCode("pagePath");
	var jsPathConfig = getUserConfigByCode("jsPath");
	pagePathConfig.configValue = $("#textFrontPagePath").val();
	jsPathConfig.configValue = $("#textJsConstantsPath").val()

	updateUserConfig(pagePathConfig);
	updateUserConfig(jsPathConfig);

	var ajaxParamter = {
		"url" : "/extend/swagger/gp/gpPage/updatePageConstants",
		"data" : "jsonData=" + JSON.stringify({
			"jsConstantsPath" : $("#textJsConstantsPath").val(),
			"frontPagePath" : $("#textFrontPagePath").val()
		}),
		"type" : "GET",
		"async" : true,
		"beforeSend" : function() {
			layer.closeAll();
			layer.load(0, {
				shade : false
			});
		},
		"success" : function(resultData) {
			layer.closeAll();
			layer.msg('更新成功……', {
				time : 500,
			});
			$("#queryBuilderForm").submit();
		},

	};

	universalAjax(ajaxParamter);
}
