/**
 * @license Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights
 *          reserved. For licensing, see LICENSE.md or
 *          http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function(config) {
	// config.extraPlugins = 'clipboard';
	// config.extraPlugins = 'uploadimage';
	// config.uploadUrl = INTERFACE_SERVER + RU_GPRESOURCE_CKEDITORFILEUPLOAD
	
	config.extraPlugins = 'html5video,widget,widgetselection,clipboard,lineutils';
	config.allowedContent = true;
	config.filebrowserUploadUrl = INTERFACE_SERVER + RU_GPRESOURCE_CKEDITORFILEUPLOAD;

	config.image_previewText = ' '; // 图片信息面板预览区内容的文字内容，默认显示CKEditor自带的内容
	config.removeDialogTabs = 'image:advanced;image:Link'; // 移除图片上传页面的'高级','链接'页签

	config.filebrowserImageUploadUrl = INTERFACE_SERVER + RU_GPRESOURCE_CKEDITORFILEUPLOAD; // 上传图片
	// config.filebrowserFlashUploadUrl = INTERFACE_SERVER +
	// RU_GPRESOURCE_CKEDITORVIDEOUPLOAD;//上传视频
	// config.selectMultiple = true;
};

for ( var i in CKEDITOR.instances) {
	CKEDITOR.instances[i].on('fileUploadRequest', function(evt) {
		var xhr = evt.data.fileLoader.xhr;
		var dataStr = localStorage.getItem("token");
		xhr.setRequestHeader("Authorization", "Bearer " + JSON.parse(dataStr).accessToken);

		// xhr.send(this.file);
		// Prevented the default behavior.
		// evt.stop();
	});

	CKEDITOR.instances[i].on('fileUploadResponse', function(evt) {
		// Prevent the default response handler.
		evt.stop();
		var data = evt.data, xhr = data.fileLoader.xhr, response = xhr.responseText.split('|');
		if (response[1]) {
			// An error occurred during upload.
			data.message = response[1];
			evt.cancel();
		} else {
			var resultModel = JSON.parse(response[0]);
			data.url = resultModel.data[0].path;
		}
	});

	// CKEDITOR.instances[i].on('change', function () {
	// var a = e.editor.document;
	// var b = a.find("img");
	// var count = b.count();
	// for (var i = 0; i < count; i++) {
	// var src = b.getItem(i).$.src;//获取img的src
	// if (src.substring(0, 4) == 'data') {
	// var ajaxParamter = {
	// "url": "/extend/swagger/gp/gpResource/ckeditorFileUpload",
	// "data": {},
	// "contentType": "multipart/form-data",
	// "type": "POST",
	// "async": "false",
	// "success": function (json) {
	// var data = eval("(" + json + ")");
	// if (data.success) {
	// b.getItem(i).$.src = data.url;
	// }
	// }
	//
	// };
	// universalAjax(ajaxParamter);
	// }
	// }
	// });
}
