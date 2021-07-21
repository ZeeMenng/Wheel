/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description 系统接口。 相关页面的js方法。
 */

$(document).ready(function() {

});

function initSelect(async) {

	// 初始化应用领域下拉框
	var selectParam1 = {
		selectId : "selectDomainId",
		textField : "name",
		valueField : "id"
	};
	var ajaxParam1 = {
		url : RU_GPDOMAIN_GETLISTBYJSONDATA + "?jsonData={}",
		async : async
	}
	initDropDownList(selectParam1, ajaxParam1);

	// 初始化是否为公共接口下拉框
	var selectParam2 = {
		selectId : "selectIsPublicCode",
		textField : "text",
		valueField : "code"
	};
	var ajaxParam2 = {
		url : RU_GPDICTIONARY_GETLISTBYTYPEID + DI_BOOLEAN,
		async : async
	}
	initDropDownList(selectParam2, ajaxParam2);

	// 初始化接口调用方法
	var selectParam3 = {
		selectId : "selectTypeCode",
		textField : "text",
		valueField : "code"
	};
	var ajaxParam3 = {
		url : RU_GPDICTIONARY_GETLISTBYTYPEID + DI_REQUEST_METHOD,
		async : async
	}
	initDropDownList(selectParam3, ajaxParam3);

}

function initUlInterfaceCatalogTree() {

	var jsonData = {
		"entityRelated" : {
			categoryCode : 1
		},
		"orderList" : [ {
			"columnName" : "priority",
			"isASC" : true
		} ]
	};
	// 树形结构begin
	var setting = {
		check : {
			enable : true
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
			onDrag : onDrag,
			onDrop : onDrop,
			onRemove : onRemove,
			onRename : onRename,
			onCollapse : onCollapse,
			onExpand : onExpand,
			onCheck : function onCheck(event, treeId, treeNode) {
				var treeObj = $.fn.zTree.getZTreeObj(treeId);
				var nodes = treeObj.getCheckedNodes(true);
				var interfaceCatalogIds = [];
				for (var i = 0; i < nodes.length; i++) {
					interfaceCatalogIds.push(nodes[i].id)
				}
				$("#hiddenInterfaceCatalogIds").val(interfaceCatalogIds);
				$("#submitButton").click();
			}

		}
	};

	var zNodes = [ {
		id : null,
		pId : 0,
		name : "根节点",
		open : true
	} ];

	$.fn.zTree.init($("#ulInterfaceCatalogTree"), setting, zNodes);

	var ajaxParamter = {
		"url" : RU_GPCATALOGINTERFACE_GETLISTBYJSONDATA + "?jsonData=" + JSON.stringify(jsonData),
		"async" : true,
		"type" : "GET",
		"success" : function(resultData) {
			if (resultData.totalCount == 0)
				return false
			var moduleTree = $.fn.zTree.getZTreeObj("ulInterfaceCatalogTree");
			zNodes = resultData.data;
			/*
			 * for (i = 0; i < zNodes.length; i++) { zNodes[i].icon =
			 * "/pc/global/plugins/zTree_v3/css/zTreeStyle/img/diy/3.png";
			 * zNodes[i].iconSkin = "diy02"; }
			 */
			$.fn.zTree.init($("#ulInterfaceCatalogTree"), setting, zNodes);

			$(".ztree .level0 a").attr("style", "cursor:default")

			// 树形菜单加上 F2快捷键
			$("#ulInterfaceCatalogTree").on("keydown", "li", function(event) {
				if (event.keyCode == 113) {
					var node = moduleTree.getNodeByTId($(this).attr("id"));
					if (node.isHover && !node.isDomain)
						moduleTree.editName(node);
					else if (node.isHover && node.level != 0)
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

			fuzzySearch("ulInterfaceCatalogTree", '#textInterfaceCatalogTreeSearch', null, true); // 初始化模糊搜索方法
		}
	};
	universalAjax(ajaxParamter);
}

function initUlEditInterfaceCatalogTree() {

	var jsonData = {
		"entityRelated" : {
			categoryCode : 1
		},
		"orderList" : [ {
			"columnName" : "priority",
			"isASC" : true
		} ]
	};
	// 树形结构begin
	var setting = {
		check : {
			enable : true
		},
		view : {
			selectedMulti : false,
			dblClickExpand : false
		},
		edit : {
			enable : false,
			editNameSelectAll : false
		},
		url : {
			addUrl : RU_GPCATALOGINTERFACE_ADD,
			deleteListUrl : RU_GPCATALOGINTERFACE_DELETELIST,
			updateListUrl : RU_GPCATALOGINTERFACE_UPDATELISTWITHDFF,
			updateUrl : RU_GPCATALOGINTERFACE_UPDATE,
			getModelUrl : RU_GPCATALOGINTERFACE_GETMODEL
		},

		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "fartherId"
			}
		},
		callback : {
			beforeCheck : function(treeId, treeNode) {

				if (treeNode.children && treeNode.children.length > 0) {
					layer.msg('请选择叶子节点……', {
						time : 1500
					});
					return false;

				}

			},
			onCheck : function(event, treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj(treeId);

				// 单选
				if (treeNode.checked) {
					zTree.checkAllNodes(false);
					zTree.checkNode(treeNode, true, true, false);
				}

				var gprCatalogInterfaceList = [];
				$.each(selectRows, function(a, b) {
					var gprCatalogInterface = {};
					gprCatalogInterface.catalogId = treeNode.id;
					gprCatalogInterface.interfaceId = b;
					gprCatalogInterface.categoryCode = 1;
					gprCatalogInterfaceList.push(gprCatalogInterface);
				});

				var submitData = {
					entityList : gprCatalogInterfaceList
				}

				var ajaxParamter = {
					"url" : RU_GPRCATALOGINTERFACE_ADDLIST,
					"data" : JSON.stringify(submitData),
					"success" : function(resultData) {
						selectRows = new Array();
						$("#queryBuilderForm").submit();
						layer.msg('修改分类成功……', {
							time : 1500
						});
					}
				};
				universalAjax(ajaxParamter);

			}
		}
	};

	var zNodes = [ {
		id : null,
		pId : 0,
		name : "根节点",
		open : true
	} ];

	$.fn.zTree.init($("#ulEditInterfaceCatalogTree"), setting, zNodes);

	var ajaxParamter = {
		"url" : RU_GPCATALOGINTERFACE_GETLISTBYJSONDATA + "?jsonData=" + JSON.stringify(jsonData),
		"async" : true,
		"type" : "GET",
		"success" : function(resultData) {
			if (resultData.totalCount == 0)
				return false
			var moduleTree = $.fn.zTree.getZTreeObj("ulEditInterfaceCatalogTree");
			zNodes = resultData.data;

			/*
			 * for (i = 0; i < zNodes.length; i++) { zNodes[i].icon =
			 * "/pc/global/plugins/zTree_v3/css/zTreeStyle/img/diy/3.png";
			 * zNodes[i].iconSkin = "diy02"; }
			 */
			$.fn.zTree.init($("#ulEditInterfaceCatalogTree"), setting, zNodes);

			$(".ztree .level0 a").attr("style", "cursor:default")

			// 树形菜单加上 F2快捷键
			$("#ulEditInterfaceCatalogTree").on("keydown", "li", function(event) {
				if (event.keyCode == 113) {
					var node = moduleTree.getNodeByTId($(this).attr("id"));
					if (node.isHover && !node.isDomain)
						moduleTree.editName(node);
					else if (node.isHover && node.level != 0)
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

			fuzzySearch("ulEditInterfaceCatalogTree", '#textEditInterfaceCatalogTreeSearch', null, true); // 初始化模糊搜索方法
		}
	};
	universalAjax(ajaxParamter);
}

function initInterfaceTable(diplayOpeationButton) {
	// 初始化列表页主体部分，包括查询条件表单及数据表格等。
	var pageParam = {
		formId : "queryBuilderForm",
		tableId : "contentTable",
		editPage : {
			title : "批量修改表单",
			url : RP_GPINTERFACE_EDIT
		},
		detailPage : {
			url : RP_GPINTERFACE_DETAIL
		},
		addPage : {
			url : RP_GPINTERFACE_ADD
		},
		deleteInterface : {
			url : RU_GPINTERFACE_DELETE
		},
		deleteListInterface : {
			url : RU_GPINTERFACE_DELETELIST
		},
		exportExcelInterface : {
			url : RU_GPINTERFACE_EXPORTEXCEL
		}

	};
	var ajaxParam = {
		url : RU_GPINTERFACE_GETLISTBYJSONDATA,
		type : "GET",
		submitData : {
			"entityRelated" : {},
			"orderList" : [ {
				"columnName" : "A.update_time",
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
			"columnText" : "接口名称",
			"style" : "text-align:left",
			"width" : "60px",
			"linkFunction" : function(event) {
				var href = RP_GPINTERFACE_DETAIL + "?" + RECORD_ID + "=" + event.id;
				return href;
			},
		}, {
			"columnName" : "url",
			"columnText" : "访问路径",
			"width" : "250px",
			"style" : "text-align:left"

		}, {
			"columnName" : "isPublicCode",
			"columnText" : "公共",
			"width" : "50px",
			"style" : "text-align:center"

		}, {
			"columnName" : "updateTime",
			"columnText" : "更新时间",
			"style" : "text-align:left"

		} ]
	};
	var operationParam = [];
	if (diplayOpeationButton)
		operationParam = [ {
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
					pageParam.deleteInterface.url = RU_GPROLE_DELETE;
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

	$("#updateInterfaceConstantsButton").click(function() {
		// 获取配置的路径
		var interfaceSymbolicJsConfig = getUserConfigByCode("interfaceSymbolicJs");
		if (interfaceSymbolicJsConfig)
			interfaceSymbolicJs = interfaceSymbolicJsConfig.configValue;
		layer.open({
			area : [ '800px', '230px' ],
			type : 1,
			closeBtn : true,
			shift : 7,
			shadeClose : true,
			content : "<div style='width:750px;text-align: center' class='form-inline'>" + "<br/><div  style='margin: 10px' class='form-group'><span style='font-weight:bold'>JS    常量路径： </span><input size='60pt'  id='textJsConstantsPath' class='form-control' type='text' name='textJsConstantsPath' value='" + interfaceSymbolicJs + "'/></div>" + "<br/><div style='margin: 20px,0px,20px' class='form-group'><button style='margin-top:5%;' type='button' class='form-control btn btn-block  btn-lg red-mint' onclick='updatePageConstant()'>提交</button></div></div>"
		});
		return;

	});
}

/**
 * @author Zee
 * @createDate 2020年9月17日 下午6:05:12
 * @updateDate 2020年9月17日 下午6:05:12
 * @description layer.load 参数: icon:0,1,2 loading风格 shade:false 是否有遮罩，true表示有遮罩
 *              time : 2*1000 设定最长等待时间,设置时间之后，loading会在时间到之后自动关闭
 * 
 * 
 */
function updatePageConstant() {

	var interfaceSymbolicJs = getUserConfigByCode("interfaceSymbolicJs");
	interfaceSymbolicJs.configValue = $("#textJsConstantsPath").val();
	updateUserConfig(interfaceSymbolicJs);

	var ajaxParamter = {
		"url" : "/extend/swagger/gp/gpInterface/updateInterfaceConstants",
		"data" : "jsonData=" + JSON.stringify({
			"jsConstantsPath" : $("#textJsConstantsPath").val()
		}),
		"type" : "GET",
		"async" : true,
		"beforeSend" : function() {
			layer.closeAll();
			layer.load(0, {
				shade : false
			});
		},
		"success" : function(resultData) {
			layer.closeAll();
			layer.msg('更新成功……', {
				time : 500,
			});
			$("#queryBuilderForm").submit();
		},

	};

	universalAjax(ajaxParamter);
}
