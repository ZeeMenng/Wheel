/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/7/11 17:38:33
 * @description  企业推介产品表 相关页面的js方法。
 */

$(document).ready(function() {

	
	//初始化列表页主体部分，包括查询条件表单及数据表格等。
	var pageParam = {
		formId : "queryBuilderForm",
		tableId : "contentTable",
		editPage : {
			title : "批量修改表单",
			url : RP_PIPRODUCTRECOMMEND_EDIT
		},
		detailPage : {
			url : RP_PIPRODUCTRECOMMEND_DETAIL
		},
		addPage : {
			url : RP_PIPRODUCTRECOMMEND_ADD
		},
		deleteInterface : {
			url : RU_PIPRODUCTRECOMMEND_DELETE
		},
		deleteListInterface : {
			url : RU_PIPRODUCTRECOMMEND_DELETELIST
		}

	};
	var ajaxParam = {
		url : RU_PIPRODUCTRECOMMEND_GETLISTBYJSONDATA,
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
			"columnName" : "name",
			"columnText" : "名称",
			"style" : "text-align:left",
			"linkFunction" : function(event) {
				var href = RP_PIPRODUCTRECOMMEND_DETAIL + "?" + RECORD_ID + "=" + event.id;
				return href;
			},
			}, 
			 /*{
			"columnName" : "brand",
			"columnText" : "产品品牌",
			"style" : "text-align:left",
			}, */
			 {
			"columnName" : "weight",
			"columnText" : "重量(千克)",
			"style" : "text-align:left",
			}, 
			 {
			"columnName" : "sellingPrice",
			"columnText" : "售价(元)",
			"style" : "text-align:left",
			}, 
			 {
			"columnName" : "perPriceUnit",
			"columnText" : "单价(元/千克)",
			"style" : "text-align:left",
			},
            {
                "columnName" : "addTime",
                "columnText" : "添加时间",
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
				pageParam.deleteInterface.url = RU_PIPRODUCTRECOMMEND_DELETE;
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

function initAddFileInput() {
	//初始化上传控件的样式
	var $Control = $("#fileResourceId").fileinput({
		language: 'zh',
		theme: 'fa',
		showRemove: false,
		showZoom: false,
		showDrag: false,
		showUpload: false,
		showCaption: false,
		ajaxSettings: {
			headers: {
				'Authorization': "Bearer " + JSON.parse(localStorage.getItem("token")).accessToken
			}
		},
		uploadUrl: INTERFACE_SERVER + "/extend/swagger/gp/gpResource/saveUploadFile",
		uploadAsync: true,
		browseClass: "btn btn-primary btn-lg",
		fileType: "image",
		previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
		overwriteInitial: false,
		initialPreviewAsData: true

	});
	initFileInput($Control);
}


function initEditFileInput(resourceIdArray, resourcePathArray) {
	var initialPreviewConfigArray = [];
	for (var i = 0; i < resourceIdArray.length; i++) {
		initialPreviewConfigArray[i] = {url: INTERFACE_SERVER + RU_GPRESOURCE_GETMODELBYPATH + resourceIdArray[i]};
	}

	var $Control = $("#fileResourceId").fileinput({
		language: 'zh',
		theme: 'fa',
		showRemove: false,
		showZoom: false,
		showDrag: false,
		showUpload: false,
		showCaption: false,
		ajaxSettings: {
			headers: {
				'Authorization': "Bearer " + JSON.parse(localStorage.getItem("token")).accessToken
			}
		},
		uploadUrl: INTERFACE_SERVER + "/extend/swagger/gp/gpResource/saveUploadFile",
		uploadAsync: true,
		browseClass: "btn btn-primary btn-lg",
		fileType: "image",
		previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
		overwriteInitial: false,
		initialPreviewAsData: true,
		initialPreview: resourcePathArray,
		initialPreviewConfig: initialPreviewConfigArray
	});
	initFileInput($Control);
}

function initFileInput(Control) {
	$(Control).on('filepreupload', function (event, data, previewId, index) {
		for (var i = 0; i < data.files.length; i++) {
			var file = data.files[i];
			if (file.name.length > 100) {
				layer.alert("文件名不能超过100个字符！" + data.result.resultMessage, {
					icon: 6
				});
				return false;
			}
			if (file.name.indexOf('|') != -1) {
				layer.alert("文件名中不能包含字符“|”" + data.result.resultMessage, {
					icon: 6
				});
				return false;
			}
		}
	}).on('filebatchselected', function (event, data, id, index) {
		$(this).fileinput("upload");
	}).on("fileuploaded", function (event, data, previewId, index) {

		//清除元素
		$("#fileTitleImage-error").remove();
		// 图片格式校验
		var gpResource = data.response.data;
		if (!data.response.isSuccess) {
			layer.alert("上传标题图片出错！" + data.response.resultMessage, {
				icon: 6
			});
			return false;
		}

		$("#" + previewId).attr("resourceId", gpResource[0].id);
		$("#" + previewId).attr("resourcePath", gpResource[0].path);

		var resourceIdList = $("#hiddenResourceId").val();
		if (resourceIdList.endsWith("|"))
			resourceIdList += gpResource[0].id + "|";
		else
			resourceIdList += "|" + gpResource[0].id + "|";
		var resourcePathList = $("#hiddenResourcePaths").val();
		if (resourcePathList.endsWith("|"))
			resourcePathList += gpResource[0].path + "|";
		else
			resourcePathList += "|" + gpResource[0].path + "|";

		$("#hiddenResourceId").val(resourceIdList);
		$("#hiddenResourcePaths").val(resourcePathList);
		return true;
	}).on('filebatchuploadcomplete', function (event, files, extra) {
		var resourceIdList = $("#hiddenResourceId").val();
		var resourcePathList = $("#hiddenResourcePaths").val();
		if (resourceIdList.endsWith("|"))
			resourceIdList = resourceIdList.substr(0, resourceIdList.length - 1);
		if (resourceIdList.startsWith("|"))
			resourceIdList = resourceIdList.substr(1, resourceIdList.length);
		if (resourcePathList.startsWith("|"))
			resourcePathList = resourcePathList.substr(1, resourcePathList.length);
		if (resourcePathList.endsWith("|"))
			resourcePathList = resourcePathList.substr(0, resourcePathList.length - 1);

		$("#hiddenResourceId").val(resourceIdList);
		$("#hiddenResourcePaths").val(resourcePathList);
	}).on("filesuccessremove", function (event, id, index) {

		var resourceIdList = $("#hiddenResourceId").val();
		var resourceId = $("#" + id).attr("resourceId");
		var resourceIdShu = $("#" + id).attr("resourceId") + "|";
		if (resourceIdList.indexOf(resourceIdShu) != -1)
			resourceIdList = resourceIdList.replace(resourceIdShu, "");
		if (resourceIdList.indexOf(resourceId) != -1)
			resourceIdList = resourceIdList.replace(resourceId, "");

		$("#hiddenResourceId").val(resourceIdList);

		var resourcePathList = $("#hiddenResourcePaths").val();
		var resourcePath = $("#" + id).attr("resourcePath");
		var resourcePathShu = $("#" + id).attr("resourcePath") + "|";
		if (resourcePathList.indexOf(resourcePathShu) != -1)
			resourcePathList = resourcePathList.replace(resourcePathShu, "");
		if (resourcePathList.indexOf(resourcePath) != -1)
			resourcePathList = resourcePathList.replace(resourcePath, "");

		$("#hiddenResourcePaths").val(resourcePathList);
	}).on('fileremoved', function (event, id, index) {
		console.log("fileremoved=======================fileremoved");
		console.log(event);
		console.log(id);
		console.log(index);
	}).on('filedeleted', function (event, key, jqXHR, data) {
		var result = jqXHR.responseJSON;
		if (!result.isSuccess) {
			layer.alert("删除图片出错！" + result.resultMessage, {
				icon: 6
			});
			return false;
		}
		var resourceId = result.data.id;
		var resourceIdShu = resourceId + "|";
		var resourceIdList = $("#hiddenResourceId").val();
		if (resourceIdList.indexOf(resourceIdShu) != -1)
			resourceIdList = resourceIdList.replace(resourceIdShu, "");
		if (resourceIdList.indexOf(resourceId) != -1)
			resourceIdList = resourceIdList.replace(resourceId, "");
		$("#hiddenResourceId").val(resourceIdList);

		var resourcePath = result.data.path;
		var resourcePathList = $("#hiddenResourcePaths").val();
		var resourcePathShu = resourcePath + "|";
		if (resourcePathList.indexOf(resourcePathShu) != -1)
			resourcePathList = resourcePathList.replace(resourcePathShu, "");
		if (resourcePathList.indexOf(resourcePath) != -1)
			resourcePathList = resourcePathList.replace(resourcePath, "");
		$("#hiddenResourcePaths").val(resourcePathList);

	});

}