/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/7/11 17:38:33
 * @description 专家表 相关页面的js方法。
 */

$(document).ready(function() {

	// 初始化是否推荐下拉框
	var show = false;
	var selectParam1 = {
		selectId : "selectIsRecommend",
		textField : "text",
		valueField : "code"
	};
	var ajaxParam1 = {
		url : RU_GPDICTIONARY_GETLISTBYTYPEID + DI_BOOLEAN
	}
	initDropDownList(selectParam1, ajaxParam1);
	// 初始化是否结婚下拉框
	var show = false;
	var selectParam2 = {
		selectId : "selectIsMarriageCode",
		textField : "text",
		valueField : "code"
	};
	var ajaxParam2 = {
		url : RU_GPDICTIONARY_GETLISTBYTYPEID + DI_BOOLEAN
	}
	initDropDownList(selectParam2, ajaxParam2);
	// 初始化男女下拉框
	var selectParam3 = {
		selectId : "selectGenderCode",
		textField : "text",
		valueField : "code"
	};
	var ajaxParam3 = {
		url : RU_GPDICTIONARY_GETLISTBYTYPEID + DI_GENDER
	}
	initDropDownList(selectParam3, ajaxParam3);

	// 初始化列表页主体部分，包括查询条件表单及数据表格等。
	var pageParam = {
		formId : "queryBuilderForm",
		tableId : "contentTable",
		editPage : {
			title : "批量修改表单",
			url : RP_PIEXPERT_EDIT
		},
		detailPage : {
			url : RP_PIEXPERT_DETAIL
		},
		addPage : {
			url : RP_PIEXPERT_ADD
		},
		deleteInterface : {
			url : RU_PIEXPERT_DELETE
		},
		deleteListInterface : {
			url : RU_PIEXPERT_DELETELIST
		}

	};
	var ajaxParam = {
		url : RU_PIEXPERT_GETLISTBYJSONDATA,
		type : "GET",
		submitData : {
			"entityRelated" : {

			},
			"orderList" : [ {
				"columnName" : "isRecommend",
				"isASC" : false
			} ],
			"page" : {
				"pageIndex" : DEFAULT_PAGE_INDEX,
				"pageSize" : DEFAULT_PAGE_SIZE
			}
		},
		columnInfo : [

		{
			"columnName" : "userName",
			"columnText" : "专家姓名",
			"style" : "text-align:left",
			"linkFunction" : function(event) {
				var href = RP_PIEXPERT_DETAIL + "?" + RECORD_ID + "=" + event.id;
				return href;
			},
		}, {
			"columnName" : "genderValue",
			"columnText" : "性别",
			"style" : "text-align:left",
		}, {
			"columnName" : "birthTime",
			"columnText" : "出生日期",
			"style" : "text-align:left",
		}, {
			"columnName" : "phone",
			"columnText" : "联系方式",
			"style" : "text-align:left",
		}, {
			"columnName" : "isRecommend",
			"columnText" : "是否推荐",
			"style" : "text-align:left",
		}, ]
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
				pageParam.deleteInterface.url = RU_PIEXPERT_DELETE;
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