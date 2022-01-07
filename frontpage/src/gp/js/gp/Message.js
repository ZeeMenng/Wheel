/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description  站内信。 相关页面的js方法。
 */

$(document).ready(function () {
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) return unescape(r[2]); return null;
	}
	var infoText = GetQueryString("messageType") * 1 + 1
	if (GetQueryString("messageType")) {
		$("#selectMessageType option").eq(infoText).attr("selected", true)
	}

	//初始化消息类型
	var selectParam = {
		selectId: "selectTypeCode",
		textField: "text",
		valueField: "code"
	};
	var ajaxParam = {
		url: RU_GPDICTIONARY_GETLISTBYTYPEID + DI_MESSAGE_TYPE
	}
	initDropDownList(selectParam, ajaxParam);

	// 初始化选择应用领域下拉框
	var selectParam = {
		selectId: "selectBusinessId",
		textField: "name",
		valueField: "id"
	};

	var domainJsonData = {
		"entityRelated": {

		},
		"orderList": [{
			"columnName": "name",
			"isASC": true
		}]
	};
	var ajaxParam = {
		url: RU_GPDOMAIN_GETLISTBYJSONDATA + "?jsonData=" + JSON.stringify(domainJsonData)
	};
	initDropDownList(selectParam, ajaxParam);
	$("#selectBusinessId").select2({
		placeholder: '请选择，可搜索……',
		width: '100%'
	});

	//初始化消息接收者
	var selectParam = {
		selectId: "selectReceiverIds",
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