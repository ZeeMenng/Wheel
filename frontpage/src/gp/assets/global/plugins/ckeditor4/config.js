/**
 * @version 4.6.1
 * @license Copyright (c) 2003-2016, CKSource - Frederico Knabben. All rights
 *          reserved. For licensing, see LICENSE.md or
 *          http://ckeditor.com/license
 */


CKEDITOR.editorConfig = function( config ) {
	config.image_previewText = ' '; // 图片信息面板预览区内容的文字内容，默认显示CKEditor自带的内容
	config.removeDialogTabs = 'image:advanced;image:Link'; // 移除图片上传页面的'高级','链接'页签

	config.filebrowserImageUploadUrl = INTERFACE_SERVER + RU_UTLUPLOADUTIL_CKIMGUPLOAD; //上传图片
	config.filebrowserFlashUploadUrl = INTERFACE_SERVER + RU_UTLUPLOADUTIL_CKVIDEOUPLOAD;//上传视频
};
