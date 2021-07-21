/** *******************************************树形菜单相关方法******************************************* */
// 树形菜单是否实施变化
var IS_IMMEDIATE = true;
// 拖拽数形菜单时拖拽节点的兄弟节点
var treeNodesDragBrotherArray = new Array();

// 初始化详情页中的zTree插件
function initDetailTree(treeParam) {

	// 组织机构树形结构begin
	var setting = {
		check : {
			enable : false
		},
		view : {
			showIcon : false,
			showLine : true,
			selectedMulti : false
		},

		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "fartherId"
			}
		}
	};

	var treeNodes = [];
	if (treeParam.initNodes)
		treeNodes = treeParam.initNodes;
	var ajaxParamter = {
		"url" : treeParam.url,
		"type" : "GET",
		"async" : true,
		"success" : function(resultData) {
			treeNodes = treeNodes.concat(resultData.data);

			// 将一级功能模块的fartherId都变为应用领域的ID，级别变为0，否则功能模块无法依附应用领域
			$.each(treeNodes, function(index, value) {
				if (value.fartherId == null) {
					value.level = 0;
					value.fartherId = value.domainId;
					treeNodes[index] = value;
				}
			});
			var orgnaizationTree = $.fn.zTree.init($("#" + treeParam.container), setting, treeNodes);

			$.each(treeNodes, function(index, value) {

				if (treeParam.expandNodeLevel == null || value.level < treeParam.expandNodeLevel) {
					var node = orgnaizationTree.getNodeByParam("id", value.id);
					orgnaizationTree.expandNode(node, true);// 展开指定节点
				}
			});
		}
	};

	universalAjax(ajaxParamter);
}

var className = "dark";
var newCount = 1;
function addHoverDom(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj(treeId);
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0)
		return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.tId + "' title='add node' onfocus='this.blur();'></span>";
	sObj.after(addStr);
	var btn = $("#addBtn_" + treeNode.tId);
	if (btn)
		btn.bind("click", function() {

			if (treeNode.level > 4) {
				layer.msg('树形菜单不能超过5级……', {
					time : 1500
				});
				return false;
			}

			var newNode = {
				id : (100 + newCount),
				fartherId : treeNode.id,
				name : "new node" + (newCount++)
			};
			zTree.addNodes(treeNode, newNode);
			var treeNodes = new Array();
			treeNodes.push(zTree.getNodeByParam("name", newNode.name, treeNode));
			updateModulesData(treeId, treeNodes, 'ADD');
			return false;
		});

};
function removeHoverDom(treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
	$("#addBtn_" + treeNode.tId).unbind().remove();
};
function showRemoveBtn(treeId, treeNode) {
	return treeNode.level != 0;
}
function showRenameBtn(treeId, treeNode) {
	return treeNode.level != 0;
}
function beforeDrag(treeId, treeNodes) {
	for (var i = 0, l = treeNodes.length; i < l; i++) {
		var pid = treeNodes[i].fartherId;
		var level = treeNodes[i].level;
		if ((pid == "root" || pid == null || pid == "null") & level == 0) {
			layer.msg('根节点不能移动……', {
				time : 1000
			});
			return false;
		}
	}
	return true;
}
function beforeDrop(treeId, treeNodes, targetNode, moveType) {

	if (targetNode.level == 0) {
		layer.msg('根节点为应用领域，不能移动到根节点……', {
			time : 1000
		});
		return false;
	}
	return true;
};

function onDrop(event, treeId, treeNodes, targetNode, moveType) {

	updateModulesData(treeId, treeNodes, 'DROP', targetNode, moveType);

};

function onDrag(event, treeId, treeNodes) {
	updateModulesData(treeId, treeNodes, 'DRAG');

};

function beforeEditName(treeId, treeNode) {

	className = (className === "dark" ? "" : "dark");
	var treeNodes = new Array();
	treeNodes.push(treeNode);
	updateModulesData(treeId, treeNodes, 'UPDATE');
	var zTree = $.fn.zTree.getZTreeObj("ulModuleTree");
	zTree.selectNode(treeNode);
	zTree.editName(treeNode);
	return false;
}
function beforeRemove(treeId, treeNode) {
	className = (className === "dark" ? "" : "dark");
	var zTree = $.fn.zTree.getZTreeObj(treeId);
	zTree.selectNode(treeNode);
	layer.confirm('您确定要删除节点  ' + treeNode.name + ' 吗？', {
		btn : [ '确定', '取消' ]
	}, function(index) {
		// 手动处理删除逻辑
		layer.close(index);
		zTree.removeNode(treeNode);
		onRemove(null, treeId, treeNode);
	});
	// 不再自动去发onRemove事件
	return false;
}
function onRemove(e, treeId, treeNode) {
	var treeNodes = new Array();
	treeNodes.push(treeNode);
	updateModulesData(treeId, treeNodes, 'DELETE');
}
function beforeRename(treeId, treeNode, newName, isCancel) {
	className = (className === "dark" ? "" : "dark");
	if (newName.length == 0) {
		setTimeout(function() {
			var zTree = $.fn.zTree.getZTreeObj("ulModuleTree");
			zTree.cancelEditName();
			layer.alert("节点名称不能为空。", {
				icon : 6
			});
		}, 0);
		return false;
	}

	var nodeNameLength = newName.length;
	var blen = 0;
	for (i = 0; i < nodeNameLength; i++) {
		if ((newName.charCodeAt(i) & 0xff00) != 0) {
			blen++;
		}
		blen++;
	}

	if (blen > 100) {
		layer.msg('节点名称不能超过50个汉字……', {
			time : 1500
		});
		return false;
	}

	return true;
}
function onRename(e, treeId, treeNode, isCancel) {
	var treeNodes = new Array();
	treeNodes.push(treeNode);
	updateModulesData(treeId, treeNodes, 'UPDATE');
}
function beforeClick(treeId, treeNode, clickFlag) {

	if (treeNode.level == 0) {
		layer.msg('根节点为应用领域，不能修改……', {
			time : 1000
		});
		return false;
	}
	return true;
}
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

/**
 * @author Zee
 * @createDate 2021年4月16日 下午2:17:41
 * @updateDate 2021年4月16日 下午2:17:41
 * @description  为了防止看不到悬到节点浮的增删改按钮，折叠时宽度减少
 */
function onCollapse(event, treeId, treeNode) {
	$("#" + treeNode.tId).width(function(n, c) {
		return c - 21;
	});
};

/**
 * @author Zee
 * @createDate 2021年4月16日 下午2:18:13
 * @updateDate 2021年4月16日 下午2:18:13
 * @description 为了防止看不到悬到节点浮的增删改按钮，展开时宽度增加
 */
function onExpand(event, treeId, treeNode) {
	$("#" + treeNode.tId).width(function(n, c) {
		return c + 21;
	});
};

function initZTreeEditForm(pageParam, ajaxParam) {
	var resultAjaxData;
	var formEdit = $('#' + pageParam.formId);
	var errorMessage = $('.alert-danger', formEdit);
	var successMessage = $('.alert-success', formEdit);
	var selectRowsCookie = getCookies({
		item : "selectRows"
	});
	var recordId = ajaxParam.recordId;

	// 添加重置按钮事件，重置的动作类似于重新加载
	$("#buttonReset").unbind('click');
	$("#buttonReset").click(function() {
		var zTree = $.fn.zTree.getZTreeObj(pageParam.treeId);
		var selectNodes = zTree.getSelectedNodes();
		if (selectNodes != null && selectNodes.length != 0)
			$('#' + selectNodes[0].tId + '_a').trigger('click');

		layer.msg('表单已重置……', {
			time : 1000
		});
	});

	formEdit.on("submit", function() {
		for ( var e in CKEDITOR.instances)
			CKEDITOR.instances[e].updateElement();
	});

	formEdit.validate({
		errorClass : 'help-block',
		rules : pageParam.validateRules,
		messages : pageParam.validateMessages,
		ignore : '',
		errorPlacement : function(e, r) {
			r.attr("data-error-container") ? e.appendTo(r.attr("data-error-container")) : e.insertAfter(r)
		},
		highlight : function(element) {
			$(element).closest('.element-group').addClass('has-error');
		},

		unhighlight : function(element) {
			$(element).closest('.element-group').removeClass('has-error');
		},
		success : function(label) {
			label.closest('.element-group').removeClass('has-error');
		},

		submitHandler : function(form) {

			var formData = formEdit.serializeArray();
			// 将查询条件和其它请求参数组装
			if (ajaxParam.submitData != null)
				$.each(formData, function(i, n) {
					var propertyName = getPropertyName(formData[i].name);
					ajaxParam.submitData[propertyName] = formData[i].value;
				});

			if (ajaxParam.type == null)
				ajaxParam.type = "POST";
			if (ajaxParam.contentType == null)
				ajaxParam.contentType = "application/json;charset=utf-8";
			if (ajaxParam.contentType === "application/json;charset=utf-8")
				ajaxParam.submitData = JSON.stringify(ajaxParam.submitData);
			// 提交富文本数据，如果包含特殊符号"&"，到后台的数据会被截断，所以用encodeURIComponent。
			if (ajaxParam.contentType === "application/x-www-form-urlencoded")
				ajaxParam.submitData = "jsonData=" + encodeURIComponent(JSON.stringify(ajaxParam.submitData));
			if (ajaxParam.dataType == null)
				ajaxParam.dataType = "JSON";
			if (ajaxParam.async == null)
				ajaxParam.async = true;
			if (ajaxParam.success == null)
				ajaxParam.success = function(resultData) {
					if (!resultData["isSuccess"]) {
						alert(resultData["resultMessage"]);
						return false;
					}

					// 更新当前节点，注意这里如果直接获取ajaxParam.id做为getNodeByParam参数是只能获取第一次点击时的值
					var zTree = $.fn.zTree.getZTreeObj(pageParam.treeId);
					var node = zTree.getNodeByParam("id", $("#hiddenId").val());
					node.name = $("#textName").val();
					zTree.updateNode(node);

					layer.msg('记录修改成功……', {
						time : 1000
					});

					// 修改成功后要清空submitData函数，否则再次修改会出错
					ajaxParam.submitData = {};
				};
			if (ajaxParam.error == null)
				ajaxParam.error = ajaxErrorFunction;

			var ajaxParamter = {
				"url" : ajaxParam.url,
				"data" : ajaxParam.submitData,
				"dataType" : ajaxParam.dataType,
				"contentType" : ajaxParam.contentType,
				"type" : ajaxParam.type,
				"async" : ajaxParam.async,
				"success" : ajaxParam.success,
				"error" : ajaxParam.error
			};
			universalAjax(ajaxParamter);
		}
	});

	$("#buttonBack").click(function() {
		history.back();
		return false;
	});

	// 初始化页面标签
	var ajaxParamter = {
		"url" : ajaxParam.getModelUrl + recordId,
		"type" : "GET",
		"async" : true,
		"success" : function(resultData) {
			resultAjaxData = resultData;
			if (!resultData["isSuccess"]) {
				alert(resultData["resultMessage"]);
				return false;
			}
			var ajaxData = resultData.data;

			if (ajaxData.imgPath != null) {
				$("#imgPath").attr("src", ajaxData.imgPath);
				$("#new").hide();
				$("#exists").show();
				$("#move").show();
			}
			var form = document.forms[pageParam.formId];
			// 遍历指定form表单所有元素
			for (var i = 0; i < form.length; i++) {
				var fieldName = form[i].name;
				var array = fieldName.split("");
				var prefix = null;
				for (var n = 0; n < array.length; n++) {
					if (array[n].toLocaleString().charCodeAt(0) >= 65 && array[n].toLocaleString().charCodeAt(0) <= 90)// 第一个大写字母
					{
						prefix = fieldName.substr(0, n);
						break;
					}
				}

				var tagLength = prefix == null ? 0 : prefix.length;
				var prop = fieldName.substr(tagLength);
				prop = prop.substr(0, 1).toLowerCase() + prop.substr(1);

				var value = ajaxData[prop];

				switch (prefix) {
				case "hidden":
					$("[name='" + fieldName + "']").val(value);
					break;
				case "text":
					$("[name='" + fieldName + "']").val(value);
					break;
				case "select":
					$("select[name='" + fieldName + "']").val(value);
					break;
				case "radio":
					if (value != null)
						$("[name='" + fieldName + "'][value='" + value + "']").get(0).checked = true;
					break;
				case "textarea":
					$("textarea[name='" + fieldName + "']").val(value);
					break;
				case "checkbox":
					$("[name='" + fieldName + "'][value='" + value + "']").get(0).checked = true;
					break;
				default:
					break;
				}
			}
		}
	};
	if (ajaxParam.getModelAsync != null)
		ajaxParamter.async = ajaxParam.getModelAsync;
	universalAjax(ajaxParamter);

	if (!ajaxParam.getModelAsync)
		return resultAjaxData;
	return null;

}

function updateModulesData(treeId, treeNodes, action, targetNode, moveType) {
	if (IS_IMMEDIATE) {
		immediateUpdate(treeId, treeNodes, action, targetNode, moveType);
		return;
	}

	var zTree = $.fn.zTree.getZTreeObj(treeId);
	var zTreeNodes = zTree.getNodes();
	var zTreeNodesJsonArray = zTree.transformToArray(zTreeNodes);
	// 修改数组长度为1，以达到删除其它节点只保留根目录节点的目的，因为根目录中已经用嵌套方式包含所有节点。
	zTreeNodesJsonArray.length = 1;
	var infoData = JSON.stringify(zTreeNodesJsonArray);
	$("#hiddenModules").val(infoData);
}

function immediateUpdate(treeId, treeNodes, action, targetNode, moveType) {
	var zTree = $.fn.zTree.getZTreeObj(treeId);
	var treeNodesArray = zTree.transformToArray(treeNodes);

	var ajaxParamter = {
		"async" : true,
		"type" : "POST",
		"success" : function(resultData) {
			// 添加成功更新当前系统ID
			if (action == "ADD") {
				treeNodes[0].id = resultData.objectId;
				zTree.updateNode(treeNodes[0])
			}

			// 更新成功右侧名称同步更改
			if (action == "UPDATE") {
				$("#textName").val(treeNodes[0].name);
			}

			layer.msg('数据已实时更新……', {
				time : 1500
			});
		}
	};
	var cascade = $("input[name='cascadeTypeCodeRadio']:checked").val();
	var rootNode = getCurrentRootNode(treeNodesArray[0]);
	if (action == "ADD") {

		var zTreeNodeJson = {
			id : null,
			cascadeTypeCode : cascade,
			name : treeNodesArray[0].name,
			fartherId : treeNodesArray[0].fartherId,
			level : (rootNode.isDomain ? treeNodesArray[0].level : treeNodesArray[0].level + 1),
			priority : treeNodesArray[0].getIndex(),
			categoryCode : rootNode.categoryCode,
			categoryText : rootNode.categoryText
		}

		// 如果根节点是应用领域
		if (rootNode.isDomain != null && rootNode.isDomain) {
			zTreeNodeJson.domainId = rootNode.id;
			zTreeNodeJson.domainName = rootNode.name;

			if (treeNodesArray[0].level == 1)
				zTreeNodeJson.fartherId = null;
		}

		ajaxParamter.data = JSON.stringify(zTreeNodeJson);
		ajaxParamter.url = zTree.setting.url.addUrl;
	}

	else if (action == "DELETE") {

		var idArray = new Array();

		$.each(treeNodesArray, function(i, v) {
			idArray.push(v.id)
		});
		var submitData = {
			idList : idArray
		};
		ajaxParamter.type = 'POST';
		ajaxParamter.data = JSON.stringify(submitData);
		ajaxParamter.url = zTree.setting.url.deleteListUrl;
	} else if (action == "UPDATE") {
		var zTreeNodeJsonArray = new Array();

		var zTreeNodeJson = {
			id : treeNodesArray[0].id,
			name : treeNodesArray[0].name,
			fartherId : treeNodesArray[0].fartherId,
			level : (rootNode.isDomain ? treeNodesArray[0].level : treeNodesArray[0].level + 1),
			priority : treeNodesArray[0].getIndex()
		}
		ajaxParamter.data = JSON.stringify(zTreeNodeJson);
		ajaxParamter.url = zTree.setting.url.updateUrl;

	} else if (action == "DRAG") {
		treeNodesDragBrotherArray = new Array();
		// 获取拖拽节点的兄弟节点，修改排序号
		function zTreeFilterPrev(node) {
			return (node.fartherId == treeNodesArray[0].fartherId && (node.getIndex() > treeNodesArray[0].getIndex()));
		}
		var treeNodesBrotherArray = zTree.getNodesByFilter(zTreeFilterPrev);
		$.each(treeNodesBrotherArray, function(i, v) {
			var zTreeNodeJson = {
				id : v.id,
				name : v.name,
				fartherId : v.fartherId,
				level : v.level + 1,
				priority : v.getIndex() - 1,
				categoryCode : v.categoryCode,
				categoryText : v.categoryText
			}

			// 如果为功能模块的拖拽，要特别处理
			if (rootNode.isDomain) {
				zTreeNodeJson.level = v.level;
				zTreeNodeJson.domainId = v.domainId;
				if (v.level == 1)
					zTreeNodeJson.fartherId = null;
			}

			treeNodesDragBrotherArray.push(zTreeNodeJson);
		});
		return true;
	} else if (action == "DROP") {
		var treeNodesBrotherArray = new Array();
		var zTreeNodeJsonArray = new Array();

		// 如果是拖拽动作，而且拖拽到根节点
		if (targetNode == null || treeNodes[0].level == 0) {
			var treeNodesBrotherArray = zTree.getNodesByParam("fartherId", null, null);
		}
		// 如果是拖拽动作，而且没有拖拽到根节点，有更简单的逻辑可以实现这个功能，就是取自己节点的父节点后再取所有子节点，但这样在数量大的时候可能会影响效率
		else {
			// 如果拖拽成为目标节点的子级节点
			if (moveType == "inner") {
				treeNodesBrotherArray = zTree.getNodesByParam("fartherId", targetNode.id, null);
			} else if (moveType == "prev" || moveType == "next") {
				function zTreeFilterPrev(node) {
					return (node.fartherId == targetNode.fartherId && (node.getIndex() >= (targetNode.getIndex() < 2 ? 0 : targetNode.getIndex() - 1)));
				}

				treeNodesBrotherArray = zTree.getNodesByFilter(zTreeFilterPrev);
			}
		}

		$.each(treeNodesBrotherArray, function(i, v) {
			var zTreeNodeJson = {
				id : v.id,
				name : v.name,
				fartherId : v.fartherId,
				level : v.level + 1,
				priority : v.getIndex(),
				categoryCode : v.categoryCode,
				categoryText : v.categoryText
			}

			// 如果为功能模块的拖拽，要特别处理
			if (rootNode.isDomain) {
				zTreeNodeJson.level = v.level;
				zTreeNodeJson.domainId = targetNode.domainId;
				if (v.level == 1)
					zTreeNodeJson.fartherId = null;
			}

			zTreeNodeJsonArray.push(zTreeNodeJson);
		});
		// 拖拽节点的子点级别可能发生变化
		var treeNodesChildArray = zTree.transformToArray(treeNodes[0].children);
		$.each(treeNodesChildArray, function(i, v) {
			var zTreeNodeJson = {
				id : v.id,
				name : v.name,
				fartherId : v.fartherId,
				level : v.level + 1,
				priority : v.getIndex(),
				categoryCode : v.categoryCode,
				categoryText : v.categoryText
			}
			// 如果为功能模块的拖拽，要特别处理
			if (rootNode.isDomain) {
				zTreeNodeJson.level = v.level;
				zTreeNodeJson.domainId = targetNode.domainId;
				if (v.level == 1)
					zTreeNodeJson.fartherId = null;
			}
			zTreeNodeJsonArray.push(zTreeNodeJson);
		});

		// 原有兄弟节点排序号也会发生变化，如果拖拽到不同层级中，也要修改
		if (treeNodesDragBrotherArray.length > 0 && targetNode.fartherId != treeNodesDragBrotherArray[0].fartherId)
			zTreeNodeJsonArray = zTreeNodeJsonArray.concat(treeNodesDragBrotherArray);
		var submitData = {
			entityList : zTreeNodeJsonArray
		}
		ajaxParamter.data = JSON.stringify(submitData);
		ajaxParamter.url = zTree.setting.url.updateListUrl;
	}

	universalAjax(ajaxParamter);

}


function selectAll() {
	var zTree = $.fn.zTree.getZTreeObj("ulModuleTree");
	zTree.setting.edit.editNameSelectAll = $("#selectAll").attr("checked");
}

// 获取当前节点的根节点(treeNode为当前节点)
function getCurrentRootNode(treeNode) {
	if (treeNode.getParentNode() != null) {
		var parentNode = treeNode.getParentNode();
		return getCurrentRootNode(parentNode);
	} else {
		return treeNode;
	}
}

/** *******************************************树形菜单相关方法******************************************* */

