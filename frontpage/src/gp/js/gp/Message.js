/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description  站内信。 相关页面的js方法。
 */

$(document).ready(function () {

	if (getCookies({
		item: "token"
	})) {
		var token = JSON.parse(getCookies({
			item: "token"
		}));
		if (token.gpUser != null)
			$("#textUserName").val(token.gpUser.userName);
		$("#textUserName").attr("readonly", "readonly");
		$("#hiddenUserId").val(token.gpUser.id);
	}


	initTypeSelect();
	initDomainSelect();
	initRecevierSelect();
});

//初始化消息类型下拉框
function initTypeSelect() {
	var selectParam = {
		selectId: "selectTypeCode",
		textField: "text",
		valueField: "code"
	};
	var ajaxParam = {
		url: RU_GPDICTIONARY_GETLISTBYTYPEID + DI_MESSAGE_TYPE
	}
	initDropDownList(selectParam, ajaxParam);
	$("#" + selectParam.selectId).select2({
		placeholder: '请选择，可搜索，可多选……',
		width: '100%'
	});
	$("#" + selectParam.selectId).on('changed.bs.select', function (e, clickedIndex, newValue, oldValue) {
		var selectTypeText = $(e.currentTarget).find("option:selected").text();
		$("#hiddenTypeText").val(selectTypeText);
	});
	//下面这句不能省略，否则会出现上面的事件无法绑定的问题
	$("#" + selectParam.selectId).selectpicker('refresh');
}

function initDomainSelect() {

	// 初始化选择应用领域下拉框
	var selectParam = {
		selectId: "selectReceiverDomainIds",
		textField: "name",
		valueField: "id"
	};

	var domainJsonData = {
		"entityRelated": {

		},
		//async:false,
		"orderList": [{
			"columnName": "name",
			"isASC": true
		}]
	};
	var ajaxParam = {
		url: RU_GPDOMAIN_GETLISTBYJSONDATA + "?jsonData=" + JSON.stringify(domainJsonData)
	};
	initDropDownList(selectParam, ajaxParam);

	$("#" + selectParam.selectId).select2({
		placeholder: '请选择，可搜索，可多选……',
		width: '100%'
	});

	//var newOption = new Option("选择所有", "0", false, false);
	//$("#" + selectParam.selectId).prepend(newOption).trigger('change');

	$("#" + selectParam.selectId).on('changed.bs.select', function (e, clickedIndex, newValue, oldValue) {

		var selectReceiverDomainIdsVal = $(e.currentTarget).val();
		$("#hiddenReceiverDomainIds").val(selectReceiverDomainIdsVal);

		var selectReceiverDomainNamesVal = "";
		var selected = $(e.currentTarget).select2("data");
		for (var i = 0; i <= selected.length - 1; i++) {
			selectReceiverDomainNamesVal += selected[i].text + ",";
		}
		selectReceiverDomainNamesVal.endsWith(",") ? selectReceiverDomainNamesVal = selectReceiverDomainNamesVal.substring(0, selectReceiverDomainNamesVal.length - 1) : selectReceiverDomainNamesVal;
		$("#hiddenReceiverDomainNames").val(selectReceiverDomainNamesVal);
	});
	$("#" + selectParam.selectId).selectpicker('refresh');

	$("#checkboxAllDomain").click(function () {
		if ($("#checkboxAllDomain").is(':checked')) {
			$("#" + selectParam.selectId + " > option").prop("selected", "selected");
			$("#" + selectParam.selectId).trigger('change');
		} else {
			$("#" + selectParam.selectId + " > option").removeAttr("selected");
			$("#" + selectParam.selectId).trigger("change");
		}
	});

}

function initRecevierSelect() {

	//初始化消息接收者
	var selectParam = {
		selectId: "selectReceiverUserIds",
		textField: "name",
		valueField: "id"
	};
	$("#" + selectParam.selectId).select2({
		placeholder: '请选择，可搜索，可多选……',
		width: '100%',
		ajax: {
			url: function (params) {
				var jsonData = {
					"entityRelated": {
						userName: params.term,
						//domainIds: $("#hiddenReceiverDomainIds").val()
					},
					"orderList": [
						{
							"columnName": "userName",
							"isASC": true
						},
						{
							"columnName": "userName",
							"isASC": false
						}],
					"page": {
						"pageIndex": DEFAULT_PAGE_INDEX,
						"pageSize": DEFAULT_PAGE_SIZE
					}
				};
				return INTERFACE_SERVER + RU_GPUSER_GETLISTBYJSONDATA + "?jsonData=" + JSON.stringify(jsonData);

			},

			dataType: 'json',
			async: false,
			processResults: function (data, params) {
				//返回的选项必须处理成以下格式
				var results = $.map(data.data, function (obj) {
					obj.text = obj.text || obj.userName; // replace name with the property used for the text
					return obj;
				});

				return {
					results: results  //必须赋值给results并且必须返回一个obj
				};
			}
		},
	});

	$("#" + selectParam.selectId).on('changed.bs.select', function (e, clickedIndex, newValue, oldValue) {
		var selectRecevierUserIdsVal = $(e.currentTarget).val();
		$("#hiddenReceiverUserIds").val(selectRecevierUserIdsVal);



		var selectReceiverUserNamesVal = "";
		var selected = $(e.currentTarget).select2("data");
		for (var i = 0; i <= selected.length - 1; i++) {
			selectReceiverUserNamesVal += selected[i].text + ",";
		}
		selectReceiverUserNamesVal.endsWith(",") ? selectReceiverUserNamesVal = selectReceiverUserNamesVal.substring(0, selectReceiverUserNamesVal.length - 1) : selectReceiverUserNamesVal;
		$("#hiddenReceiverUserNames").val(selectReceiverUserNamesVal);
	});

	$("#" + selectParam.selectId).selectpicker('refresh');

}