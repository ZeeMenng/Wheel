/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018-7-31 14:57:17
 * @description 企业基本信息表 相关页面的js方法。
 */

$(document).ready(function() {

	// 初始化机构类型下拉框
	var selectParamAdmin = {
		selectId : "selectEnterpriseTypeCode",
		textField : "text",
		valueField : "code"
	};
	var ajaxParamAdmin = {
		url : RU_GPDICTIONARY_GETLISTBYTYPEID + DI_ENTERPRISE_TYPE
	}
	initDropDownList(selectParamAdmin, ajaxParamAdmin);

	// 根据下拉框选择 赋值input text
	$('#selectEnterpriseTypeCode').change(function() {
		var enterpriseTypeText = $(this).children('option:selected').text();
		$('#textEnterpriseTypeText').val(enterpriseTypeText);
	});

	// 初始化列表页主体部分，包括查询条件表单及数据表格等。
	var pageParam = {
		formId : "queryBuilderForm",
		tableId : "contentTable",
		editPage : {
			title : "批量修改表单",
			url : RP_DAENTERPRISEINFO_EDIT
		},
		detailPage : {
			url : RP_DAENTERPRISEINFO_DETAIL
		},
		addPage : {
			url : RP_DAENTERPRISEINFO_ADD
		},
		deleteInterface : {
			url : RU_DAENTERPRISEINFO_DELETE
		},
		deleteListInterface : {
			url : RU_DAENTERPRISEINFO_DELETELIST
		}

	};
	var ajaxParam = {
		url : RU_DAENTERPRISEINFO_GETLISTBYJSONDATA,
		type : "GET",
		submitData : {
			"entityRelated" : {

			},
			"orderList" : [ {
				"columnName" : "id",
				"isASC" : true
			} ],
			"page" : {
				"pageIndex" : DEFAULT_PAGE_INDEX,
				"pageSize" : DEFAULT_PAGE_SIZE
			}
		},
		columnInfo : [

		{
			"columnName" : "enterpriseName",
			"columnText" : "企业名称",
			"style" : "text-align:center",
			"linkFunction" : function(event) {
				var href = RP_DAENTERPRISEINFO_DETAIL + "?" + RECORD_ID + "=" + event.id;
				return href;
			},
		}, {
			"columnName" : "enterpriseCode",
			"columnText" : "企业编码",
			"style" : "text-align:center",
		}, {
			"columnName" : "corporationName",
			"columnText" : "企业法人/负责人",
			"style" : "text-align:center",
		}, {
			"columnName" : "corporationMobile",
			"columnText" : "联系电话",
			"style" : "text-align:center",
		}, {
			"columnName" : "enterpriseAddress",
			"columnText" : "企业地址",
			"style" : "text-align:center",
		}, {
			"columnName" : "enterpriseTypeText",
			"columnText" : "企业类型",
			"style" : "text-align:center",
		},

		]
	};

	var operationParam = [ {
		"operationText" : "修改",
		"buttonClass" : "yellow",
		"iconClass" : "fa fa-pencil-square-o",
		"clickFunction" : function(event) {
			window.location.href = pageParam.editPage.url + "?" + RECORD_ID + "=" + event.data.id;
		}
	}, {
		"operationText" : "删除",
		"buttonClass" : "red",
		"iconClass" : "fa fa-trash-o",
		"clickFunction" : function(event) {
			layer.confirm('您确定要删除当前记录？', {
				btn : [ '确定', '取消' ]
			}, function() {
				layer.closeAll('dialog');
				ajaxParam.submitData.page.pageSize = $("#pageSizeText").val();
				ajaxParam.submitData.page.pageIndex = $("#pageIndexHidden").val();
				pageParam.deleteInterface.url = RU_DAENTERPRISEINFO_DELETE;
				pageParam.deleteInterface.type = "GET";
				pageParam.deleteInterface.submitData = {
					"id" : event.data.id,
				};
				deleteRecord(pageParam, ajaxParam, operationParam);
			});
		},
		"visibleFunction" : function(recordData) {
			if (recordData.status == "1")
				return false;
			return true;
		}
	} ];
	initQueryForm(pageParam, ajaxParam, operationParam);

});