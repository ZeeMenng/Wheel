/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description 地区信息。 相关页面的js方法。
 */


function initUlRegionTree(regionCountryISO) {

	var jsonData = {
		"entityRelated": {
			countryIso: regionCountryISO
		},
		"orderList": [{
			"columnName": "code",
			"isASC": true
		}]
	};
	// 树形结构begin
	var setting = {
		//实时添加或修改节点时需要带入的参数
		ajaxData: {
			countryIso: regionCountryISO
		},
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
			addUrl: RU_GPREGION_ADD,
			deleteListUrl: RU_GPREGION_DELETELIST,
			updateListUrl: RU_GPREGION_UPDATELISTWITHDFF,
			updateUrl: RU_GPREGION_UPDATE,
			getModelUrl: RU_GPREGION_GETMODELBYPATH
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

	$.fn.zTree.init($("#ulRegionTree"), setting, zNodes);

	var ajaxParamter = {
		"url": RU_GPREGION_GETLISTBYJSONDATA + "?jsonData=" + JSON.stringify(jsonData),
		"async": true,
		"type": "GET",
		"success": function (resultData) {
			if (resultData.totalCount == 0)
				return false
			var moduleTree = $.fn.zTree.getZTreeObj("ulRegionTree");
			zNodes = resultData.data;

			$.fn.zTree.init($("#ulRegionTree"), setting, zNodes);

			$(".ztree .level0 a").attr("style", "cursor:default")

			// 树形菜单加上 F2快捷键
			$("#ulRegionTree").on("keydown", "li", function (event) {
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

			fuzzySearch("ulRegionTree", '#textRegionTreeSearch', null, true); // 初始化模糊搜索方法
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
			textCode: {
				required: true
			},
			textName: {
				required: true
			}, textLongitude: {
				number: true
			},
			textLatitude: {
				number: true
			},
			textArea: {
				number: true
			}
		}
	};
	var ajaxParam = {
		recordId: treeNode.id,
		getModelAsync: false,
		url: zTree.setting.url.updateUrl,
		getModelUrl: zTree.setting.url.getModelUrl,
		submitData: {

		}
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
