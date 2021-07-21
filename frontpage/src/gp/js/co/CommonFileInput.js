/** *******************************************文件上传相关方法******************************************* */
// 多文件上传控件 接受后台数据后 初始化
function initAddFileInput() {
	
	// 初始化前先销毁上一个初始化，否则点击左侧功能模块，控制无法显示图片。同时取消绑定on事件，否则上传时会重复执行on事件。
	$("#fileIcons").fileinput('clear');
	$("#fileIcons").fileinput('reset');
	$("#fileIcons").fileinput('refresh');
	$("#fileIcons").fileinput('destroy');
	// 初始化上传控件的样式
	var $Control = $("#fileIcons").fileinput({
		language : 'zh',
		theme : 'fa',
		showRemove : false,
		showZoom : false,
		showDrag : false,
		showUpload : false,
		showClose:false,
		//是否显示标题,就是那个文本框
		showCaption : true,
		//显示上传图片的大小信息
		showPreview:true,
		//是否显示拖拽区域，默认不写为true，但是会占用很大区域
		dropZoneEnabled : false,
		ajaxSettings : {
			headers : {
				'Authorization' : "Bearer " + JSON.parse(getCookies({
					item : "token"
				})).accessToken
			}
		},
		uploadUrl : INTERFACE_SERVER + "/extend/swagger/gp/gpResource/saveUploadFile",
		uploadAsync : true,
		// 传入功能模块和页面路径
		uploadExtraData : function(previewId, index) {
			// 获取焦点菜单的主键
			var moduleId = $("ul.sub-menu .nav-item.start.active").attr("id");
			if (moduleId == null)
				moduleId = "";
			var beginIndex = HOME_PATH.length;
			var endIndex = window.location.href.indexOf("?");
			if (endIndex == -1)
				endIndex = window.location.href.length;

			var pageUrl = window.location.href.substring(beginIndex, endIndex);
			var data = {
				moduleId : moduleId,
				pageUrl : pageUrl
			};
			return data;
		},
		browseClass : "btn btn-primary",
		fileType : "image",
		previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
		overwriteInitial : false,
		initialPreviewAsData : true

	});
	  
	initFileInput($Control, "hiddenIconIds", "hiddenIconPaths");

}

function initEditFileInput(IconIdArray, IconPathArray) {
	isDropZoneEnabled=true;
	if((IconPathArray.length==1&&IconPathArray[0]=="")){
		IconIdArray=null;
		IconPathArray=[];
		isDropZoneEnabled=false;
	}
	var initialPreviewConfigArray = [];
	if (IconIdArray != null && IconPathArray != null) {
		for (var i = 0; i < IconIdArray.length; i++) {
			initialPreviewConfigArray[i] = {
				url : INTERFACE_SERVER + RU_GPRESOURCE_GETMODELBYPATH + IconIdArray[i]
			};
		}
	}
	// 初始化前先销毁上一个初始化，否则点击左侧功能模块，控制无法显示图片。同时取消绑定on事件，否则上传时会重复执行on事件。
	$("#fileIcons").fileinput('clear');
	$("#fileIcons").fileinput('reset');
	$("#fileIcons").fileinput('refresh');
	$("#fileIcons").fileinput('destroy');
	var $Control = $("#fileIcons").fileinput({
		language : 'zh',
		theme : 'fa',
		showRemove : false,
		showZoom : false,
		showDrag : false,
		showUpload : false,
		showCaption : false,
		showPreview:true,
		showClose:false,
		//是否显示标题,就是那个文本框
		showCaption : true,
		//是否显示拖拽区域，默认不写为true，但是会占用很大区域
		dropZoneEnabled : isDropZoneEnabled,
		ajaxSettings : {
			headers : {
				'Authorization' : "Bearer " + JSON.parse(localStorage.getItem("token")).accessToken
			}
		},
		uploadUrl : INTERFACE_SERVER + "/extend/swagger/gp/gpResource/saveUploadFile",
		uploadAsync : true,
		// 传入功能模块和页面路径
		uploadExtraData : function(previewId, index) {
			// 获取焦点菜单的主键
			var moduleId = $("ul.sub-menu .nav-item.start.active").attr("id");
			if (moduleId == null)
				moduleId = "";
			var beginIndex = HOME_PATH.length;
			var endIndex = window.location.href.indexOf("?");
			if (endIndex == -1)
				endIndex = window.location.href.length;

			var pageUrl = window.location.href.substring(beginIndex, endIndex);
			var data = {
				moduleId : moduleId,
				pageUrl : pageUrl
			};
			return data;
		},
		browseClass : "btn btn-primary",
		fileType : "image",
		previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
		overwriteInitial : false,
		initialPreviewAsData : true,
		initialPreview : IconPathArray,
		initialPreviewConfig : initialPreviewConfigArray
	});
	initFileInput($Control, "hiddenIconIds", "hiddenIconPaths");
}

// 多文件上传控制 刚进入新增页面 初始化
function initFileInput(fileControl, hiddenResourceIdsControl, hiddenResourcePathsControl) {
	// 初始化前先销毁上一个初始化，否则点击左侧功能模块，控制无法显示图片。同时取消绑定on事件，否则上传时会重复执行on事件。
	$(fileControl).unbind('filepreupload');
	$(fileControl).unbind('filebatchselected');
	$(fileControl).unbind('fileuploaded');
	$(fileControl).unbind('filebatchuploadcomplete');
	$(fileControl).unbind('filesuccessremove');
	$(fileControl).unbind('fileremoved');
	$(fileControl).unbind('filedeleted');
	
	$(fileControl).on('filepreupload', function(event, data, previewId, index) {
		for (var i = 0; i < data.files.length; i++) {
			var file = data.files[i];
			if (file.name.length > 100) {
				layer.alert("文件名不能超过100个字符！" + data.result.resultMessage, {
					icon : 6
				});
				return false;
			}
			if (file.name.indexOf(',') != -1) {
				layer.alert("文件名中不能包含字符“,”" + data.result.resultMessage, {
					icon : 6
				});
				return false;
			}
		}
	}).on('filebatchselected', function(event, data, id, index) {
		$(this).fileinput("upload");
	}).on("fileuploaded", function(event, data, previewId, index) {

		// 清除元素
		$("#fileTitleImage-error").remove();
		// 图片格式校验
		var gpResource = data.response.data;
		if (!data.response.isSuccess) {
			layer.alert("上传标题图片出错！" + data.response.resultMessage, {
				icon : 6
			});
			return false;
		}

		$("#" + previewId).attr("resourceId", gpResource[0].id);
		$("#" + previewId).attr("resourcePath", gpResource[0].path);

		var resourceIdList = $("#" + hiddenResourceIdsControl).val();
		if (resourceIdList.endsWith(","))
			resourceIdList += gpResource[0].id + ",";
		else
			resourceIdList += "," + gpResource[0].id + ",";
		var resourcePathList = $("#" + hiddenResourcePathsControl).val();
		if (resourcePathList.endsWith(","))
			resourcePathList += gpResource[0].path + ",";
		else
			resourcePathList += "," + gpResource[0].path + ",";

		$("#" + hiddenResourceIdsControl).val(resourceIdList);
		$("#" + hiddenResourcePathsControl).val(resourcePathList);
		return true;
	}).on('filebatchuploadcomplete', function(event, files, extra) {
		var resourceIdList = $("#" + hiddenResourceIdsControl).val();
		var resourcePathList = $("#" + hiddenResourcePathsControl).val();
		if (resourceIdList.endsWith(","))
			resourceIdList = resourceIdList.substr(0, resourceIdList.length - 1);
		if (resourceIdList.startsWith(","))
			resourceIdList = resourceIdList.substr(1, resourceIdList.length);
		if (resourcePathList.startsWith(","))
			resourcePathList = resourcePathList.substr(1, resourcePathList.length);
		if (resourcePathList.endsWith(","))
			resourcePathList = resourcePathList.substr(0, resourcePathList.length - 1);

		$("#" + hiddenResourceIdsControl).val(resourceIdList);
		$("#" + hiddenResourcePathsControl).val(resourcePathList);
	}).on("filesuccessremove", function(event, id, index) {

		var resourceIdList = $("#" + hiddenResourceIdsControl).val();
		var resourceId = $("#" + id).attr("resourceId");
		var resourceIdShu = $("#" + id).attr("resourceId") + ",";
		if (resourceIdList.indexOf(resourceIdShu) != -1)
			resourceIdList = resourceIdList.replace(resourceIdShu, "");
		if (resourceIdList.indexOf(resourceId) != -1)
			resourceIdList = resourceIdList.replace(resourceId, "");

		$("#" + hiddenResourceIdsControl).val(resourceIdList);

		var resourcePathList = $("#" + hiddenResourcePathsControl).val();
		var resourcePath = $("#" + id).attr("resourcePath");
		var resourcePathShu = $("#" + id).attr("resourcePath") + ",";
		if (resourcePathList.indexOf(resourcePathShu) != -1)
			resourcePathList = resourcePathList.replace(resourcePathShu, "");
		if (resourcePathList.indexOf(resourcePath) != -1)
			resourcePathList = resourcePathList.replace(resourcePath, "");

		$("#" + hiddenResourcePathsControl).val(resourcePathList);
	}).on('fileremoved', function(event, id, index) {

	}).on('filedeleted', function(event, key, jqXHR, data) {
		var result = jqXHR.responseJSON;
		if (!result.isSuccess) {
			layer.alert("删除图片出错！" + result.resultMessage, {
				icon : 6
			});
			return false;
		}
		var resourceId = result.data.id;
		var resourceIdShu = resourceId + ",";
		var resourceIdList = $("#" + hiddenResourceIdsControl).val();
		if (resourceIdList.indexOf(resourceIdShu) != -1)
			resourceIdList = resourceIdList.replace(resourceIdShu, "");
		if (resourceIdList.indexOf(resourceId) != -1)
			resourceIdList = resourceIdList.replace(resourceId, "");
		$("#" + hiddenResourceIdsControl).val(resourceIdList);

		var resourcePath = result.data.path;
		var resourcePathList = $("#" + hiddenResourcePathsControl).val();
		var resourcePathShu = resourcePath + ",";
		if (resourcePathList.indexOf(resourcePathShu) != -1)
			resourcePathList = resourcePathList.replace(resourcePathShu, "");
		if (resourcePathList.indexOf(resourcePath) != -1)
			resourcePathList = resourcePathList.replace(resourcePath, "");
		$("#" + hiddenResourcePathsControl).val(resourcePathList);

	});

}

/** *******************************************文件上传相关方法******************************************* */

