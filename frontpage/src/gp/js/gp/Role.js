/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description 系统角色。 相关页面的js方法。
 */
// Role返回信息
var initResult;
$(document).ready(function() {

});

function initRoleInfo() {
	var pageParam = {
		formId : "formEdit",
		validateRules : {
			textName : {
				required : true
			}
		}
	};
	var ajaxParam = {
		getModelAsync : false,
		url : RU_GPROLE_UPDATE,
		updateListUrl : RU_GPROLE_UPDATELIST,
		getModelUrl : RU_GPROLE_GETMODELBYPATH,
		submitData : {},
		success : function(resultData) {
			layer.msg(resultData.resultMessage, {
				time : 1000
			});
			
		}
	};
	initResult = initEditPage(pageParam, ajaxParam);
}

function initDomainModuleTree() {

	var jsonData = {
		"entityRelated" : {

		},
		"orderList" : [ {
			"columnName" : "name",
			"isASC" : true
		} ]
	};
	// 树形结构begin
	var setting = {
		check : {
			enable : true
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "fartherId"
			}
		},

		callback : {
			onCheck : function(event, treeId, treeNode) {

				// 手动变化节点状态
				var zTree = $.fn.zTree.getZTreeObj(treeId);
				zTree.checkNode(treeNode, treeNode.checked, true, false);

				// 获取状态发生变化的节点
				var selectedNodes = zTree.getChangeCheckedNodes();

				var gprRoleDomainList = new Array();
				var gprRoleModuleList = new Array();
				$.each(selectedNodes, function(i, v) {
					if (v.isDomain) {
						var gprRoleDomain = {};
						gprRoleDomain.roleId = request("ID");
						gprRoleDomain.domainId = v.id;
						gprRoleDomain.isEnableCode = 0;
						gprRoleDomainList.push(gprRoleDomain);
					} else {

						var gprRoleModule = {};
						gprRoleModule.roleId = request("ID");
						gprRoleModule.moduleId = v.id;
						gprRoleModule.isEnableCode = 0;
						gprRoleModuleList.push(gprRoleModule);
					}

				});

				var submitData = {
					gprRoleDomainEntityList : gprRoleDomainList,
					gprRoleModuleEntityList : gprRoleModuleList
				}

				var ajaxParamter = {
					"url" : RU_GPRROLEMODULE_ADDDOMAINMODULEAUTHORITY,
					"data" : JSON.stringify(submitData),
					"success" : function(resultData) {
						if (resultData.isSuccess) {
							// 节点状态（后台）更新成功后要刷新初始化整个树形结构
							zTree.refresh();
							layer.msg('应用功能权限修改成功……', {
								time : 1500
							});
						}

					}
				};

				if (!treeNode.checked)
					ajaxParamter.url = RU_GPRROLEMODULE_DELETEDOMAINMODULEAUTHORITY;

				universalAjax(ajaxParamter);

			}
		}
	};
	var ajaxParamter = {
		"url" : RU_GPDOMAIN_GETLISTBYJSONDATA + "?jsonData=" + JSON.stringify(jsonData),
		"async" : false,
		"type" : "GET",
		"success" : function(resultData) {
			var zNodes = [];
			if (resultData.data != null && resultData.data.length != 0) {
				zNodes = resultData.data;

				// 更改应用领域图标展示
				for (i = 0; i < zNodes.length; i++) {
					//zNodes[i].icon = "/pc/global/plugins/zTree_v3/css/zTreeStyle/img/diy/5.png";
					//zNodes[i].iconSkin = "diy01";
					zNodes[i].isDomain = true;
				}
			}
			$.fn.zTree.init($("#ulDomainModuleTree"), setting, zNodes);

			$(".ztree .level0 a").attr("style", "cursor:default")

			var moduleTree = $.fn.zTree.getZTreeObj("ulDomainModuleTree");

			var nodeList = moduleTree.getNodes();
			$.each(nodeList, function(index, value) {
				var ajaxParamter = {
					"url" : RU_GPMODULE_GETLISTBYDOMAINID + value.id,
					"async" : false,
					"type" : "GET",
					"success" : function(resultData) {
						if (!resultData.isSuccess)
							return true;
						// 更改功能模块图标展示
						/*for (i = 0; i < resultData.data.length; i++) {
							resultData.data[i].icon = "/pc/global/plugins/zTree_v3/css/zTreeStyle/img/diy/3.png";
							resultData.data[i].iconSkin = "diy02";
						}*/
						moduleTree.addNodes(value, -1, resultData.data);
						
					}
				};
				universalAjax(ajaxParamter);
			});
			fuzzySearch("ulDomainModuleTree", '#textDomainModuleTreeSearch', null, false); // 初始化模糊搜索方法
		}
	};
	universalAjax(ajaxParamter);

	// 初始化树形菜单中的复选
	var moduleTree = $.fn.zTree.getZTreeObj("ulDomainModuleTree");

	var domainOrModuleIds = initResult.data.moduleIds + "," + initResult.data.domainIds;
	var domainOrModuleIdArray = domainOrModuleIds.split(",");
	for (idx in domainOrModuleIdArray) {
		var node = moduleTree.getNodeByParam("id", domainOrModuleIdArray[idx]);
		if (node) {
			moduleTree.checkNode(node, true, false, false);
			moduleTree.refresh();
		}
	}

}

function getSelectedParentNodes(treeNode) {
	var parentNode = treeNode.getParentNode();
	var selectedParentNodes = new Array();
	if (parentNode != null) {
		selectedParentNodes.push(parentNode);
		return selectedParentNodes.concat(getSelectedParentNodes(parentNode));
	}
	return selectedParentNodes;
}

function initCheckedTreeNodes(resultData) {
	// 初始化树形菜单中的复选
	var moduleTree = $.fn.zTree.getZTreeObj("ulDomainModuleTree");

	var domainOrModuleIds = resultData.data.moduleIds + "," + resultData.data.domainIds;
	var domainOrModuleIdArray = domainOrModuleIds.split(",");
	for (idx in domainOrModuleIdArray) {
		var node = moduleTree.getNodeByParam("id", domainOrModuleIdArray[idx]);
		if (node) {
			moduleTree.checkNode(node, true, false, false);
			moduleTree.refresh();
		}
	}
}

function initCheckboxEvent() {

	var gprRoleInterfaceList = new Array();

	var ajaxParamter = {
		"async" : true,
		"type" : "POST",
		"success" : function(resultData) {
			if (resultData.isSuccess)
				layer.msg('接口权限修改成功……', {
					time : 1000
				});
		}
	};

	$("#contentTable").on("change", "input[name='childCheckbox']", function(event) {
		var recordId = $(this).closest("tr").attr("id");

		gprRoleInterfaceList = new Array();
		var gprRoleInterface = {};
		gprRoleInterface.roleId = request("ID");
		gprRoleInterface.interfaceId = recordId;
		gprRoleInterface.isEnableCode = 0;
		gprRoleInterfaceList.push(gprRoleInterface);

		ajaxParamter.data = JSON.stringify(gprRoleInterfaceList);
		if ($(this).get(0).checked) {
			ajaxParamter.url = RU_GPRROLEINTERFACE_ADDLIST
		} else {
			ajaxParamter.url = RU_GPRROLEINTERFACE_DELETEINTERFACEAUTHORITY
		}
		var submitData = {
			entityList : gprRoleInterfaceList
		}
		ajaxParamter.data = JSON.stringify(submitData);

		universalAjax(ajaxParamter);
	});

	$("#contentTable").on("change", "input[name='headerCheckbox']", function(event) {

		gprRoleInterfaceList = new Array();

		if ($(this).get(0).checked) {
			gprRoleInterfaceList = new Array();
			$("#contentTable input[name='childCheckbox']").each(function(i, n) {
				var recordId = $(this).closest("tr").attr("id");

				var gprRoleInterface = {};
				gprRoleInterface.roleId = request("ID");
				gprRoleInterface.interfaceId = recordId;
				gprRoleInterface.isEnableCode = 0;
				gprRoleInterfaceList.push(gprRoleInterface);
			});
			ajaxParamter.url = RU_GPRROLEINTERFACE_ADDLIST
		} else {
			gprRoleInterfaceList = new Array();
			$("#contentTable input[name='childCheckbox']").each(function(i, n) {
				var recordId = $(this).closest("tr").attr("id");

				var gprRoleInterface = {};
				gprRoleInterface.roleId = request("ID");
				gprRoleInterface.interfaceId = recordId;
				gprRoleInterface.isEnableCode = 0;
				gprRoleInterfaceList.push(gprRoleInterface);
			});
			ajaxParamter.url = RU_GPRROLEINTERFACE_DELETEINTERFACEAUTHORITY
		}
		var submitData = {
			entityList : gprRoleInterfaceList
		}
		ajaxParamter.data = JSON.stringify(submitData);

		universalAjax(ajaxParamter);
	});

}
