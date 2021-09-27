/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2021/9/27 21:36:54
 * @description  岗位。 相关页面的js方法。
 */

$(document).ready(function () {
	//初始化所属组织机构下拉框
	var selectParam = {
		selectId: "selectOrganizationId",
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
		async: true,
		url: RU_GPDICTIONARYTYPE_GETLISTBYJSONDATA + "?jsonData=" + JSON.stringify(typeJsonData)
	};
	initDropDownList(selectParam, ajaxParam);
	$("#" + selectParam.selectId).select2({
		placeholder: '请选择，可搜索……',
		width: '100%'
	});
});