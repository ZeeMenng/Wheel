/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description  组织机构。 相关页面的js方法。
 */

$(document).ready(function () {

	//初始化机构类型下拉框
	var selectParamAdmin = {
		selectId: "selectTypeCode",
		textField: "text",
		valueField: "code"
	};
	var ajaxParamAdmin = {
		url: RU_GPDICTIONARY_GETLISTBYTYPEID + DI_ORG_TYPE
	}
	initDropDownList(selectParamAdmin, ajaxParamAdmin);

	//初始化组织机构级别下拉框
	var selectParamAdmin = {
		selectId: "selectLevelCode",
		textField: "text",
		valueField: "code"
	};
	var ajaxParamAdmin = {
		url: RU_GPDICTIONARY_GETLISTBYTYPEID + DI_ORGANIZATION
	}
	initDropDownList(selectParamAdmin, ajaxParamAdmin);


});


function initUlOrganizationTree() {

	var jsonData = {
		"entityRelated": {
		},
		"orderList": [{
			"columnName": "priority",
			"isASC": true
		}]
	};
	// 树形结构begin
	var setting = {
		check: {
			enable: false
		},
		view: {
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			selectedMulti: true,
			dblClickExpand: false
		},
		edit: {
			enable: true,
			editNameSelectAll: true
		},
		url: {
			addUrl: RU_GPORGANIZATION_ADD,
			deleteListUrl: RU_GPORGANIZATION_DELETELIST,
			updateListUrl: RU_GPORGANIZATION_UPDATELISTWITHDFF,
			updateUrl: RU_GPORGANIZATION_UPDATE,
			getModelUrl: RU_GPORGANIZATION_GETMODELBYPATH
		},

		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "fartherId"
			}
		},
		callback: {
			beforeRemove: beforeRemove,
			beforeRename: beforeRename,
			onDrop: onDrop,
			onDrag: onDrag,
			onRemove: onRemove,
			onRename: onRename,
			onClick: onClick

		}
	};

	var zNodes = [{
		id: null,
		pId: 0,
		name: "根节点",
		open: true,
		categoryCode: $("input[name='catalogTypeCodeRadio']:checked").val(),
		categoryText: $("input[name='catalogTypeCodeRadio']:checked").text()
	}];

	$.fn.zTree.init($("#ulOrganizationTree"), setting, zNodes);

	var ajaxParamter = {
		"url": RU_GPORGANIZATION_GETLISTBYJSONDATA + "?jsonData=" + JSON.stringify(jsonData),
		"async": true,
		"type": "GET",
		"success": function (resultData) {
			if (resultData.totalCount == 0)
				return false
			var moduleTree = $.fn.zTree.getZTreeObj("ulOrganizationTree");
			zNodes = resultData.data;

			$.fn.zTree.init($("#ulOrganizationTree"), setting, zNodes);

			$(".ztree .level0 a").attr("style", "cursor:default")

			// 树形菜单加上 F2快捷键
			$("#ulOrganizationTree").on("keydown", "li", function (event) {
				if (event.keyCode == 113) {
					var node = moduleTree.getNodeByTId($(this).attr("id"));
					moduleTree.editName(node);
				}
			});
			// 展开节点
			$.each(zNodes, function (index, value) {
				if (value.level < 2) {
					var node = moduleTree.getNodeByParam("id", value.id);
					moduleTree.expandNode(node, true);// 展开指定节点
				}
			});

			fuzzySearch("ulOrganizationTree", '#textCatalogCategoryTreeSearch', null, true); // 初始化模糊搜索方法
		}
	};
	universalAjax(ajaxParamter);
}

function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj(treeId);
	var pageParam = {
		treeId: treeId,
		formId: "formEdit",
		validateRules: {
			textDomainId: {
				required: true
			},
			textName: {
				required: true
			},
			selectLevelCode: {
				required: true
			},
			textPriority: {
				digits: true
			}
		}
	};
	var ajaxParam = {
		recordId: treeNode.id,
		getModelAsync: false,
		url: zTree.setting.url.updateUrl,
		getModelUrl: zTree.setting.url.getModelUrl,
		submitData: {}
	};

	var initResult = initZTreeEditForm(pageParam, ajaxParam);
	if (!initResult.isSuccess) {
		layer.alert("查询信息错误" + initResult.resultMessage, {
			icon: 6
		});
		return;
	}
	if (initResult.data.iconIds != null) {
		initEditFileInput(initResult.data.iconIds.split(","), initResult.data.iconPaths.split(","));
	}
}