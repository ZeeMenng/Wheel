/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description  国家信息。 相关页面的js方法。
 */

$(document).ready(function () {

	//初始化下拉框
	var selectParam = {
		selectId: "selectIsIndependentCode",
		textField: "text",
		valueField: "code"
	};
	var ajaxParam = {
		url: RU_GPDICTIONARY_GETLISTBYTYPEID + DI_BOOLEAN
	}
	initDropDownList(selectParam, ajaxParam);

	//初始化下拉框
	var selectParam = {
		selectId: "selectIsDisplayCode",
		textField: "text",
		valueField: "code"
	};
	var ajaxParam = {
		url: RU_GPDICTIONARY_GETLISTBYTYPEID + DI_BOOLEAN
	}
	initDropDownList(selectParam, ajaxParam);


});