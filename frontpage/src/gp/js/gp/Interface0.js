/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description 系统接口。 相关页面的js方法。
 */

$(document).ready(function() {

	// 初始化应用领域下拉框
	var selectParam1 = {
		selectId : "selectDomainId",
		textField : "name",
		valueField : "id"
	};
	var ajaxParam1 = {
		url : RU_GPDOMAIN_GETLISTBYJSONDATA + "?jsonData={}"
	}
	initDropDownList(selectParam1, ajaxParam1);

	// 初始化是否为公共接口下拉框
	var selectParam2 = {
		selectId : "selectIsPublicCode",
		textField : "text",
		valueField : "code"
	};
	var ajaxParam2 = {
		url : RU_GPDICTIONARY_GETLISTBYTYPEID + "/" + DI_BOOLEAN
	}
	initDropDownList(selectParam2, ajaxParam2);

	// 初始化接口调用方法
	var selectParam3 = {
		selectId : "selectTypeCode",
		textField : "text",
		valueField : "code"
	};
	var ajaxParam3 = {
		url : RU_GPDICTIONARY_GETLISTBYTYPEID + "/" + DI_REQUEST_METHOD
	}
	initDropDownList(selectParam3, ajaxParam3);

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
				"columnName" : "A.add_time",
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
			"linkFunction" : function(event) {
				var href = RP_GPINTERFACE_DETAIL + "?" + RECORD_ID + "=" + event.id;
				return href;
			},
		}, {
			"columnName" : "url",
			"columnText" : "访问路径",
			"style" : "text-align:left",
			"width" : '300px'
		}, {
			"columnName" : "isPublicCode",
			"columnText" : "公共",
			"style" : "text-align:center",
		}, {
			"columnName" : "typeCode",
			"columnText" : "请求方式",
			"style" : "text-align:left",
		}, {
		"columnName" : "addTime",
		"columnText" : "添加时间",
		"style" : "text-align:center",
		"width" : '120px'
	}]
	};

	var operationParam = [ {
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
				pageParam.deleteInterface.url = RU_GPINTERFACE_DELETE;
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

		layer.open({
			area : [ '800px', '230px' ],
			type : 1,
			closeBtn : true,
			shift : 7,
			shadeClose : true,
			content : "<div style='width:750px;text-align: center' class='form-inline'>" + "<br/><div  style='margin: 10px' class='form-group'><span style='font-weight:bold'>JS    常量路径： </span><input size='60pt'  id='textJsConstantsPath' class='form-control' type='text' name='textJsConstantsPath' value='D:\\JAVA\\GitHubTarget\\HuaPingMango\\FrontPage\\pc\\global\\js\\constant\\Interface.js'/></div>" + "<br/><div style='margin: 20px,0px,20px' class='form-group'><button style='margin-top:5%;' type='button' class='form-control btn btn-block  btn-lg red-mint' onclick='updatePageConstant()'>提交</button></div></div>"
		});
		return;

	});

});

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
			if (!resultData["isSuccess"]) {
				layer.alert(resultData["resultMessage"], {
					icon : 6
				});
				return false;
			}
			layer.closeAll();
			layer.msg('更新成功……', {
				time : 500,
			});
			$("#queryBuilderForm").submit();
		},

	};

	universalAjax(ajaxParamter);
}
