/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2020/10/21 21:21:11
 * @description 接口分类字典管理存放接口分类信息，支持树形分级分类，主要但不限于业务上的分类方式，支持同时对接口进行多种分类。
 *              相关页面的js方法。
 */

$(document).ready(function() {
});

function initUlCatalogCategoryTree(catalogCategoryCode) {

	var jsonData = {
		"entityRelated" : {
			categoryCode : catalogCategoryCode
		},
		"orderList" : [ {
			"columnName" : "priority",
			"isASC" : true
		} ]
	};
	// 树形结构begin
	var setting = {
		check : {
			enable : false
		},
		view : {
			addHoverDom : addHoverDom,
			removeHoverDom : removeHoverDom,
			selectedMulti : true,
			dblClickExpand : false
		},
		edit : {
			enable : true,
			editNameSelectAll : true
		},
		url : {
			addUrl : RU_GPCATALOGINTERFACE_ADD,
			deleteListUrl : RU_GPCATALOGINTERFACE_DELETELIST,
			updateListUrl : RU_GPCATALOGINTERFACE_UPDATELISTWITHDFF,
			updateUrl : RU_GPCATALOGINTERFACE_UPDATE,
			getModelUrl : RU_GPCATALOGINTERFACE_GETMODELBYPATH
		},

		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "fartherId"
			}
		},
		callback : {
			beforeRemove : beforeRemove,
			beforeRename : beforeRename,
			onDrop : onDrop,
			onDrag : onDrag,
			onRemove : onRemove,
			onRename : onRename,
			onClick : onClick

		}
	};

	var zNodes = [ {
		id : null,
		pId : 0,
		name : "根节点",
		open : true,
		categoryCode : $("input[name='catalogTypeCodeRadio']:checked").val(),
		categoryText : $("input[name='catalogTypeCodeRadio']:checked").text()
	} ];

	$.fn.zTree.init($("#ulCatalogCategoryTree"), setting, zNodes);

	var ajaxParamter = {
		"url" : RU_GPCATALOGINTERFACE_GETLISTBYJSONDATA + "?jsonData=" + JSON.stringify(jsonData),
		"async" : true,
		"type" : "GET",
		"success" : function(resultData) {
			if (resultData.totalCount == 0)
				return false
			var moduleTree = $.fn.zTree.getZTreeObj("ulCatalogCategoryTree");
			zNodes = resultData.data;
			/* 
			for (i = 0; i < zNodes.length; i++) {
				zNodes[i].icon = "/pc/global/plugins/zTree_v3/css/zTreeStyle/img/diy/3.png";
				zNodes[i].iconSkin = "diy02";
			}*/
			$.fn.zTree.init($("#ulCatalogCategoryTree"), setting, zNodes);

			$(".ztree .level0 a").attr("style", "cursor:default")

			// 树形菜单加上 F2快捷键
			$("#ulCatalogCategoryTree").on("keydown", "li", function(event) {
				if (event.keyCode == 113) {
					var node = moduleTree.getNodeByTId($(this).attr("id"));
					moduleTree.editName(node);
				}
			});
			// 展开节点
			$.each(zNodes, function(index, value) {
				if (value.level < 2) {
					var node = moduleTree.getNodeByParam("id", value.id);
					moduleTree.expandNode(node, true);// 展开指定节点
				}
			});

			fuzzySearch("ulCatalogCategoryTree", '#textInterfaceCatalogTreeSearch', null, true); // 初始化模糊搜索方法
		}
	};
	universalAjax(ajaxParamter);
}
