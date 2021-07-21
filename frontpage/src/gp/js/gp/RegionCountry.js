/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description  国家信息。 相关页面的js方法。
 */

$(document).ready(function() {

	//初始化下拉框
	var selectParam = {
		selectId : "selectIsDisplayCode",
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
			url : RP_GPREGIONCOUNTRY_EDIT
		},
		detailPage : {
			url : RP_GPREGIONCOUNTRY_DETAIL
		},
		addPage : {
			url : RP_GPREGIONCOUNTRY_ADD
		},
		deleteInterface : {
			url : RU_GPREGIONCOUNTRY_DELETE
		},
		deleteListInterface : {
			url : RU_GPREGIONCOUNTRY_DELETELIST
		},
		exportExcelInterface:{
			url:RU_GPREGIONCOUNTRY_EXPORTEXCEL
		}

	};
	var ajaxParam = {
		url : RU_GPREGIONCOUNTRY_GETLISTBYJSONDATA,
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
			"columnName" : "chineseName",
			"columnText" : "中文名称",
			"style" : "text-align:center",
			"linkFunction" : function(event) {
				var href = RP_GPREGIONCOUNTRY_DETAIL + "?" + RECORD_ID + "=" + event.id;
				return href;
			},
			}, 
			 {
			"columnName" : "englishName",
			"columnText" : "英文名称",
			"style" : "text-align:center",
			},
            {
                "columnName" : "isoCode",
                "columnText" : "地区编码",
                "style" : "text-align:center",
            },
			 /*{
			"columnName" : "longitude",
			"columnText" : "地区经度",
			"style" : "text-align:center",
			}, 
			 {
			"columnName" : "latitude",
			"columnText" : "地区纬度",
			"style" : "text-align:center",
			},*/
			 {
			"columnName" : "area",
			"columnText" : "国家面积",
			"style" : "text-align:left",
			},
			 {
			"columnName" : "isDisplayCode",
			"columnText" : "是否显示",
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
				pageParam.deleteInterface.url = RU_GPREGIONCOUNTRY_DELETE;
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
			return false;
		}
	} ];
	initQueryForm(pageParam, ajaxParam, operationParam);

});