/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description CMS采集任务表 相关页面的js方法。
 */

$(document).ready(function() {

	// 初始化采集状态下拉框
	var selectParam1 = {
		selectId : "selectAcquisitionStatus",
		textField : "text",
		valueField : "code"
	};
	var ajaxParam1 = {
		url : RU_GPDICTIONARY_GETLISTBYTYPEID + DI_ACQUISITION
	}
	initDropDownList(selectParam1, ajaxParam1);
	// 初始化列表页主体部分，包括查询条件表单及数据表格等。
	var pageParam = {
		formId : "queryBuilderForm",
		tableId : "contentTable",
		editPage : {
			title : "批量修改表单",
			url : RP_PIACQUISITION_EDIT
		},
		detailPage : {
			url : RP_PIACQUISITION_DETAIL
		},
		addPage : {
			url : RP_PIACQUISITION_ADD
		},
		deleteInterface : {
			url : RU_PIACQUISITION_DELETE
		},
		deleteListInterface : {
			url : RU_PIACQUISITION_DELETELIST
		},
		exportExcelInterface : {
			url : RU_PIACQUISITION_EXPORTEXCEL
		}

	};
	var ajaxParam = {
		url : RU_PIACQUISITION_GETLISTBYJSONDATA,
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
			"columnName" : "acqName",
			"columnText" : "采集名称",
			"style" : "text-align:left",
			"linkFunction" : function(event) {
				var href = RP_PIACQUISITION_DETAIL + "?" + RECORD_ID + "=" + event.id;
				return href;
			},
		}, {
			"columnName" : "statusText",
			"columnText" : "当前状态",
			"style" : "text-align:left",
		}, {
			"columnName" : "startTime",
			"columnText" : "开始时间",
			"style" : "text-align:left",
		}, {
			"columnName" : "endTime",
			"columnText" : "结束时间",
			"style" : "text-align:left",
		}

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
				pageParam.deleteInterface.url = RU_PIACQUISITION_DELETE;
				pageParam.deleteInterface.type = "GET";
				pageParam.deleteInterface.submitData = {
					"id" : event.data.id,
				};
				deleteRecord(pageParam, ajaxParam, operationParam);
			});
		},
		"visibleFunction" : function(recordData) {
			if (recordData.status == "1")
				return true;
			return true;
		}
	} ];
	initQueryForm(pageParam, ajaxParam, operationParam);

});