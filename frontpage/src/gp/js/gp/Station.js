/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2021/9/27 21:36:54
 * @description  岗位。 相关页面的js方法。
 */

$(document).ready(function () {


	// var typeJsonData = {
	// 	"orderList": [{
	// 		"columnName": "name",
	// 		"isASC": true
	// 	}]
	// };
	// var ajaxParam = {
	// 	async: true,
	// 	url: RU_GPDICTIONARYTYPE_GETLISTBYJSONDATA + "?jsonData=" + JSON.stringify(typeJsonData)
	// };
	// initDropDownList(selectParam, ajaxParam);


	//初始化所属组织机构下拉框
	var selectParam = {
		selectId: "selectOrganizationId",
		textField: "name",
		valueField: "id"
	};
	$("#" + selectParam.selectId).select2({
		placeholder: '请选择，可搜索……',
		width: '100%',
		ajax: {
			url: function (params) {
				var jsonData = {
					"entityRelated": {
						name: params.term
					},
					"orderList": [
						{
							"columnName": "level",
							"isASC": true
						},
						{
							"columnName": "name",
							"isASC": false
						}],
					"page": {
						"pageIndex": DEFAULT_PAGE_INDEX,
						"pageSize": DEFAULT_PAGE_SIZE
					}
				};
				return INTERFACE_SERVER + RU_GPORGANIZATION_GETLISTBYJSONDATA + "?jsonData=" + JSON.stringify(jsonData);

			},

			dataType: 'json',
			async: false,
			processResults: function (data, params) {
				//返回的选项必须处理成以下格式
				var results = $.map(data.data, function (obj) {
					obj.text = obj.text || obj.name; // replace name with the property used for the text
					return obj;
				});

				return {
					results: results  //必须赋值给results并且必须返回一个obj
				};
			}
		},
	});





});
//组织机构下拉框被选中时触发
function selectChange() {
	var organizationNameArray = $('#selectOrganizationId').select2('data');
	if (organizationNameArray.length > 0)
		$('#hiddenOrganizationName').val(organizationNameArray[0].text);
}