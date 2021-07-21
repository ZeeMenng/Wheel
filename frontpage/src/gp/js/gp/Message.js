/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description  站内信。 相关页面的js方法。
 */

$(document).ready(function() {
	function GetQueryString(name) {
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if(r!=null)return  unescape(r[2]); return null;
	}
	var infoText = GetQueryString("messageType")*1+1
	if(GetQueryString("messageType")){
		$("#selectMessageType option").eq(infoText).attr("selected",true)
	}

	
	//初始化列表页主体部分，包括查询条件表单及数据表格等。
	var pageParam = {
		formId : "queryBuilderForm",
		tableId : "contentTable",
		editPage : {
			title : "批量修改表单",
			url : RP_GPMESSAGE_EDIT
		},
		detailPage : {
			url : RP_GPMESSAGE_DETAIL
		},
		addPage : {
			url : RP_GPMESSAGE_ADD
		},
		deleteInterface : {
			url : RU_GPMESSAGE_DELETE
		},
		deleteListInterface : {
			url : RU_GPMESSAGE_DELETELIST
		}

	};

	if(getStorage("userInfo")){
		//$("#userName").text(getStorage("userInfo")[0].id)
		console.log(getStorage("userInfo")[0].id)
		var userInfo = getStorage("userInfo")[0];
	}

	var ajaxParam = {
		url : RU_GPMESSAGE_GETLISTBYJSONDATA,
		type : "GET",
		submitData : {
			"entityRelated" : {
//				"messageType":"系统消息",
//				"id": userInfo.id
			},
			"orderList" : [ {
				"columnName" : "add_time",
				"isASC" : false
			} ],
			"page" : {
				"pageIndex" : DEFAULT_PAGE_INDEX,
				"pageSize" : DEFAULT_PAGE_SIZE
			}
		},
		columnInfo : [
			{
				"columnName" : "title",
				"columnText" : "消息标题",
				"style" : "text-align:left",
				"linkFunction" : function(event) {
					var href = RP_GPMESSAGE_DETAIL + "?" + RECORD_ID + "=" + event.id;
					return href;
				},
			},
//			{
//				"columnName" : "userName",
//				"columnText" : "消息创建者",
//				"style" : "text-align:left",
//			},
			{
				"columnName" : "typeText",
				"columnText" : "消息类型",
				"style" : "text-align:left",
			},
			 {
			"columnName" : "content",
			"columnText" : "消息内容",
			"style" : "text-align:left",
			},
			{
			"columnName" : "addTime",
			"columnText" : "记录时间",
			"style" : "text-align:left",
		    },
//			{
//				"columnName" : "isReadCode",
//				"columnText" : "是否已读",
//				"style" : "text-align:left",
//			},
//			 {
//			"columnName" : "remark",
//			"columnText" : "备注字段",
//			"style" : "text-align:left",
//			}, 
        
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
			if (recordData.status == "1")
				return false;
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
				pageParam.deleteInterface.url = RU_GPMESSAGE_DELETE;
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