/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description 功能模块。 相关页面的js方法。
 */

 function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj(treeId);
	var pageParam = {
		treeId : treeId,
		formId : "formEdit",
		validateRules : {
			textDomainId : {
				required : true
			},
			textName : {
				required : true
			},
			selectLevelCode : {
				required : true
			},
			textPriority : {
				digits : true
			}
		}
	};
	var ajaxParam = {
		recordId : treeNode.id,
		getModelAsync : false,
		url : zTree.setting.url.updateUrl,
		getModelUrl : zTree.setting.url.getModelUrl,
		submitData : {}
	};

	var initResult = initZTreeEditForm(pageParam, ajaxParam);
	if (!initResult.isSuccess) {
		layer.alert("查询信息错误" + initResult.resultMessage, {
			icon : 6
		});
		return;
	}
	if (initResult.data.iconIds != null) {
		initEditFileInput(initResult.data.iconIds.split(","), initResult.data.iconPaths.split(","));
	}
}