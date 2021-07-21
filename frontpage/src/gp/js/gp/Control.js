/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description 系统组件。 相关页面的js方法。
 */

$(document).ready(function() {

	// 静态链接自动填充
	var itemParam = {
		"textField" : "url",
		"valueField" : "id",
		"textFieldInputId" : "textPageUrl",
		"valueFieldInputId" : "textPageId"
	};
	var ajaxParam = {
		"url" : RU_GPPAGE_GETLISTBYJSONDATA,
		"jsonData" : {
			entityRelated : {
				autoCompletekey : "",
			},
			orderList : [ {
				"columnName" : "url",
				"isASC" : true
			} ],
			"page" : {
				"pageIndex" : 1,
				"pageSize" : 10
			}
		}
	}
	initAutoComplete(itemParam, ajaxParam);

	
});