$(document).ready(function() {

	// 初始化是否管理员下拉框
	var selectParamAdmin = {
		selectId : "selectIsAdminCode",
		textField : "text",
		valueField : "code"
	};
	var ajaxParamBoolean = {
		url : RU_GPDICTIONARY_GETLISTBYTYPEID + DI_BOOLEAN
	}
	initDropDownList(selectParamAdmin, ajaxParamBoolean);

	// 初始化是否禁用下拉框
	var selectParam1 = {
		selectId : "selectIsDisabledCode",
		textField : "text",
		valueField : "code"
	};

	initDropDownList(selectParam1, ajaxParamBoolean);

	// 初始化是否结婚下拉框
	var show = false;
	var selectParam2 = {
		selectId : "selectIsMarriageCode",
		textField : "text",
		valueField : "code"
	};
	initDropDownList(selectParam2, ajaxParamBoolean);

	// 初始化男女下拉框
	var selectParam3 = {
		selectId : "selectGenderCode",
		textField : "text",
		valueField : "code"
	};
	var ajaxParam3 = {
		url : RU_GPDICTIONARY_GETLISTBYTYPEID + DI_GENDER
	}
	initDropDownList(selectParam3, ajaxParam3);

	// 初始化应用领域下拉框
	var selectParam = {
		selectId : "selectDomainIds",
		textField : "name",
		valueField : "id"
	};
	var domainJsonData = {
		"entityRelated" : {

		},
		"orderList" : [ {
			"columnName" : "name",
			"isASC" : true
		} ]
	};
	var ajaxParam = {
		url : RU_GPDOMAIN_GETLISTBYJSONDATA + "?jsonData=" + JSON.stringify(domainJsonData)
	}
	initDropDownList(selectParam, ajaxParam);
	$('#selectDomainIds').on('changed.bs.select', function(e, clickedIndex, newValue, oldValue) {
		var selectDomainIdsVal = $(e.currentTarget).val();
		$("#hiddenDomainIds").val(selectDomainIdsVal);
	});
	$("#selectDomainIds").selectpicker({
		noneSelectedText : '请选择，可多选……',
		width : '100%'
	});
	$("#selectDomainIds").selectpicker('refresh');

	// 初始化选择角色下拉框
	var selectParam = {
		selectId : "selectRoleIds",
		textField : "name",
		valueField : "id"
	};

	var roleJsonData = {
		"entityRelated" : {

		},
		"orderList" : [ {
			"columnName" : "name",
			"isASC" : true
		} ]
	};
	var ajaxParam = {
		url : RU_GPROLE_GETLISTBYJSONDATA + "?jsonData=" + JSON.stringify(roleJsonData)
	};
	initDropDownList(selectParam, ajaxParam);
	$('#selectRoleIds').on('changed.bs.select', function(e, clickedIndex, newValue, oldValue) {
		var selectDomainIdsVal = $(e.currentTarget).val();
		$("#hiddenRoleIds").val(selectDomainIdsVal);
	});
	$("#selectRoleIds").selectpicker({
		noneSelectedText : '请选择，可多选……',
		width : '100%'
	});
	$("#selectRoleIds").selectpicker('refresh');

});

function initTreeNodes(treeId, async) {

	var jsonData = {
		"entityRelated" : {

		},
		"orderList" : [ {
			"columnName" : "name",
			"isASC" : true
		} ]
	};

	// 组织机构树形结构begin
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
			onCheck : onCheck
		}
	};

	var ajaxParamter = {
		"url" : RU_GPORGANIZATION_GETLISTBYJSONDATA + "?jsonData=" + JSON.stringify(jsonData),
		"async" : async,
		"type" : "GET",
		"success" : function(res) {
			var treeNodes = res.data;
			var orgnaizationTree = $.fn.zTree.init($("#" + treeId), setting, treeNodes);
			fuzzySearch(treeId, '#ztree_search', null, true); // 初始化模糊搜索方法
			var node = orgnaizationTree.getNodes()[0];
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
		}
	};
	universalAjax(ajaxParamter);
}

function onCheck(event, treeId, treeNode) {
	var treeObj = $.fn.zTree.getZTreeObj(treeId);
	var nodes = treeObj.getCheckedNodes(true);
	var arr = [];
	for (var i = 0; i < nodes.length; i++) {
		arr.push(nodes[i].id)
	}
	$("#hiddenOrgIds").val(arr)
}

function resetPassword() {

	layer.confirm('您确定要重置此用户的密码吗？重置后会立即生效。', {
		btn : [ '确定', '取消' ]
	}, function() {
		layer.closeAll('dialog');
		var ajaxParamter = {
			"url" : RU_GPUSER_RESETPASSWORD + $("#hiddenId").val(),
			"type" : "GET",
			"async" : true,
			"success" : function(resultData) {
				if (!resultData["isSuccess"]) {
					layer.alert("重置密码出错，请联系系统管理人员！" + resultData["resultMessage"], {
						icon : 6
					});
					return false;
				}
				layer.alert("密码重置成功！", {
					icon : 6
				});

			}
		};

		universalAjax(ajaxParamter);

		return false;
	});

}

function disableUser() {
	layer.confirm('您确定要注销此用户吗？注销后会立即生效，此用户将无法登录系统。', {
		btn : [ '确定', '取消' ]
	}, function() {
		layer.closeAll('dialog');
		var ajaxParamter = {
			"url" : RU_GPUSER_DISABLEUSER + $("#hiddenId").val(),
			"type" : "GET",
			"async" : true,
			"success" : function(resultData) {
				if (!resultData["isSuccess"]) {
					layer.alert("注销用户出错，请联系系统管理人员！" + resultData["resultMessage"], {
						icon : 6
					});
					return false;
				}
				layer.alert("注销用户重置成功！", {
					icon : 6
				});

			}
		};

		universalAjax(ajaxParamter);

		return false;
	});

}
