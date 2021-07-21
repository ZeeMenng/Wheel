/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018-8-17 16:57:52
 * @description  预警阀值表 相关页面的js方法。
 */

$(document).ready(function() {
	
	//初始化下拉框
	
	//生育期阶段
	var selectParamAdmin = {
        selectId: "selectGrowthId",
        textField: "text",
        valueField: "code"
    };
    var ajaxParamAdmin = {
  	  url: RU_GPDICTIONARY_GETLISTBYTYPEID + DI_MANGO_GROWTH_TYPE //"/28bfd9b417764edda2ea3902c29f5e2b"
    };
    initDropDownList(selectParamAdmin, ajaxParamAdmin);

    //预警指标
    selectParamAdmin = {
        selectId: "selectWarningType",
        textField: "warningName",
        valueField: "warningType"
    };
    ajaxParamAdmin = {
        url: RU_GPEARLYWARNING_GETWARNINGTYPE //extend/swagger/gp/gpEarlyWarning/getWarningType
    };
    initDropDownList(selectParamAdmin, ajaxParamAdmin);
    
    //状态
    selectParamAdmin = {
        selectId: "selectStutasCode",
        textField: "text",
        valueField: "code"
    };
    ajaxParamAdmin = {
        url: RU_GPDICTIONARY_GETLISTBYTYPEID + DI_QUOTA_DATA_STATUS_TYPE //"/f7b936b246dc0ead6ea88dc7b0d24501"
    };
    initDropDownList(selectParamAdmin, ajaxParamAdmin);
    
    
    //根据下拉框选择 赋值input text
    $('#selectGrowthId').change(function(){
        var growthName = $(this).children('option:selected').text();
        $('#textGrowthName').val(growthName);
        
        //查询用
        $('#growthId').val($('#selectGrowthId').val());
    });
    $('#selectWarningType').change(function(){
        var warningName = $(this).children('option:selected').text();
        $('#textWarningName').val(warningName);
        
       //查询用
       $('#warningType').val($('#selectWarningType').val());
    });
    
    $('#selectStutasCode').change(function(){
        var stutasText = $(this).children('option:selected').text();
        $('#textStutasText').val(stutasText);
        
       //查询用
        $('#stutasCode').val($('#selectStutasCode').val());
    });

	
	//初始化列表页主体部分，包括查询条件表单及数据表格等。
	var pageParam = {
		formId : "queryBuilderForm",
		tableId : "contentTable",
		editPage : {
			title : "批量修改表单",
			url : RP_GPEARLYWARNING_EDIT
		},
		detailPage : {
			url : RP_GPEARLYWARNING_DETAIL
		},
		addPage : {
			url : RP_GPEARLYWARNING_ADD
		},
		deleteInterface : {
			url : RU_GPEARLYWARNING_DELETE
		},
		deleteListInterface : {
			url : RU_GPEARLYWARNING_DELETELIST
		}

	};
	var ajaxParam = {
		url : RU_GPEARLYWARNING_GETLISTBYJSONDATA,
		type : "GET",
		submitData : {
			"entityRelated" : {

			},
			"orderList" : [ {
				"columnName" : "A.growth_id",//add_time
				"isASC" : true
			} ],
			"page" : {
				"pageIndex" : DEFAULT_PAGE_INDEX,
				"pageSize" : DEFAULT_PAGE_SIZE
			}
		},
		columnInfo : [ 
        
			 {
			"columnName" : "growthName",
			"columnText" : "生育期阶段",
			"style" : "text-align:left",
			"linkFunction" : function(event) {
				var href = RP_GPEARLYWARNING_DETAIL + "?" + RECORD_ID + "=" + event.id;
				return href;
			},
			}, 
			 {
			"columnName" : "warningName",
			"columnText" : "预警指标",
			"style" : "text-align:left",
			}, 
			 {
			"columnName" : "maxVal",
			"columnText" : "最大阀值",
			"style" : "text-align:left",
			}, 
			 {
			"columnName" : "minVal",
			"columnText" : "最小阀值",
			"style" : "text-align:left",
			}, 
			 {
			"columnName" : "stutasText",
			"columnText" : "状态",
			"style" : "text-align:left",
			}
        
       ]
	};

	var operationParam = [ {
		"operationText" : "修改",
		"buttonClass" : "yellow",
		"iconClass" : "fa fa-pencil-square-o",
		"clickFunction" : function(event) {
//			var stutasCode = event.data.stutasCode;
//			if(stutasCode == 2){
//				layer.alert("该预警阀值已启用，无法修改！");
//				return;
//			}
			window.location.href = pageParam.editPage.url + "?" + RECORD_ID + "=" + event.data.id;
		}
	}, 
	{
		"operationText" : "删除",
		"buttonClass" : "red",
		"iconClass" : "fa fa-trash-o",
		"clickFunction" : function(event) {
			var stutasCode = event.data.stutasCode;
			if(stutasCode == 2){
				layer.alert("该预警阀值已启用，无法删除！");
				return;
			}
			
			layer.confirm('您确定要删除当前记录？', {
				btn : [ '确定', '取消' ]
			}, function() {
				layer.closeAll('dialog');
				ajaxParam.submitData.page.pageSize = $("#pageSizeText").val();
				ajaxParam.submitData.page.pageIndex = $("#pageIndexHidden").val();
				pageParam.deleteInterface.url = RU_GPEARLYWARNING_DELETE;
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