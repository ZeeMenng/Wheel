/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description  字典信息。 相关页面的js方法。
 */



function initDictionType(async) {
	// 初始化字典类型下拉框
	var selectParam = {
		selectId: "selectTypeId",
		textField: "name",
		valueField: "id"
	};

	var typeJsonData = {
		"orderList": [{
			"columnName": "name",
			"isASC": true
		}]
	};
	var ajaxParam = {
		async: async,
		url: RU_GPDICTIONARYTYPE_GETLISTBYJSONDATA + "?jsonData=" + JSON.stringify(typeJsonData)
	};
	initDropDownList(selectParam, ajaxParam);
	$("#" + selectParam.selectId).select2({
		placeholder: '请选择，可搜索……',
		width: '100%'
	});
}