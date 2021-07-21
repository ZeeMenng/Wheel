/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description  操作日志。 相关页面的js方法。
 */

$(document).ready(function() {



    //初始化下拉框--操作类型
    var selectParam = {
        selectId : "isoperTypeCode",
        textField : "text",
        valueField : "code"
    };
    var ajaxParam = {
        url : RU_GPDICTIONARY_GETLISTBYTYPEID + DI_OPER_TYPE
    }
    initDropDownList(selectParam, ajaxParam);
    //初始化下拉框--是否成功
    var selectParam = {
        selectId : "isisSuccessCode",
        textField : "text",
        valueField : "code"
    };
    var ajaxParam = {
        url : RU_GPDICTIONARY_GETLISTBYTYPEID + DI_BOOLEAN
    }
    initDropDownList(selectParam, ajaxParam);
	
	//初始化列表页主体部分，包括查询条件表单及数据表格等。
	var pageParam = {
		formId : "queryBuilderForm",
		tableId : "contentTable",
		editPage : {
			title : "批量修改表单",
			url : RP_GPOPERLOG_EDIT
		},
		detailPage : {
			url : RP_GPOPERLOG_DETAIL
		},
		addPage : {
			url : RP_GPOPERLOG_ADD
		},
		deleteInterface : {
			url : RU_GPOPERLOG_DELETE
		},
		deleteListInterface : {
			url : RU_GPOPERLOG_DELETELIST
		},
		exportExcelInterface:{
			url: RU_GPOPERLOG_EXPORTEXCEL
		}

	};
	var ajaxParam = {
		url : RU_GPOPERLOG_GETLISTBYJSONDATA,
		type : "GET",
		submitData : {
			"entityRelated" : {

			},
			"orderList" : [ {
				"columnName" : "addTime",
				"isASC" : false
			} ],
			"page" : {
				"pageIndex" : DEFAULT_PAGE_INDEX,
				"pageSize" : DEFAULT_PAGE_SIZE
			}
		},
		columnInfo : [ 
			{
                "columnName" : "domainName",
                "columnText" : "应用领域",
                "style" : "text-align:left",
            },
			 {
			"columnName" : "tableName",
			"columnText" : "操作表名",
			"style" : "text-align:left",
			"linkFunction" : function(event) {
				var href = RP_GPOPERLOG_DETAIL + "?" + RECORD_ID + "=" + event.id;
				return href;
			},
			},
            {
                "columnName" : "operTypeText",
                "columnText" : "操作类型",
                "style" : "text-align:left",
            },
			 {
			"columnName" : "totalCount",
			"columnText" : "记录总数",
			"style" : "text-align:center",
			},
            {
                "columnName" : "isSuccessCode",
                "columnText" : "是否成功",
                "style" : "text-align:center",
            },
			 {
			"columnName" : "addTime",
			"columnText" : "操作时间",
			"style" : "text-align:center",
			}
        
       ]
	};

	var operationParam = [ {
		"operationText" : "修改",
		"buttonClass" : "yellow",
		"iconClass" : "fa fa-pencil-square-o",
		"clickFunction" : function(event) {
			window.location.href = pageParam.editPage.url + "?" + RECORD_ID + "=" + event.data.id;
		},
		"visibleFunction" : function(recordData) {
			return false;
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
				pageParam.deleteInterface.url = RU_GPOPERLOG_DELETE;
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