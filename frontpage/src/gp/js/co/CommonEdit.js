/**
 * @author Zee
 * @createDate 2021年4月2日 下午2:04:24
 * @updateDate 2021年4月2日 下午2:04:24
 * @description 初始化通用编辑页面
 */
function initEditPage(pageParam, ajaxParam) {
	var resultAjaxData;
	var formEdit = $('#' + pageParam.formId);
	var errorMessage = $('.alert-danger', formEdit);
	var successMessage = $('.alert-success', formEdit);
	var selectRowsCookie = getCookies({
		item: "selectRows"
	});
	var id = request(RECORD_ID);
	var isUpdateList = false;
	if (selectRowsCookie != null && id == null)
		isUpdateList = true;

	if (isUpdateList) {
		pageParam.validateRules = {};
	}

	formEdit.on("submit", function () {
		for (var e in CKEDITOR.instances)
			CKEDITOR.instances[e].updateElement();
		dynamicRules(pageParam);
		lengthVerificationRules(pageParam);
		promptElementRules(pageParam);
	});

	formEdit.validate({
		errorClass: 'help-block',
		rules: pageParam.validateRules,
		messages: pageParam.validateMessages,
		ignore: '',
		errorPlacement: function (e, r) {

			r.attr("data-error-container") ? e.appendTo(r.attr("data-error-container")) : e.insertAfter(r)
		},
		highlight: function (element) {
			$(element).closest('.element-group').addClass('has-error');
		},

		unhighlight: function (element) {
			$(element).closest('.element-group').removeClass('has-error');
		},
		success: function (label) {
			label.closest('.element-group').removeClass('has-error');
		},

		submitHandler: function (form) {

			if (!dynamicRules(pageParam)) {
				return;
			}
			if (!ajaxRules(pageParam)) {
				return;
			}
			if (!lengthVerificationRules(pageParam)) {
				return;
			}
			if (!promptElementRules(pageParam)) {
				return;
			}
			successMessage.show();
			errorMessage.hide();

			var formData = formEdit.serializeArray();
			// 将查询条件和其它请求参数组装
			if (ajaxParam.submitData != null)
				if (typeof ajaxParam.submitData == "string")// 处理重复提交时反复转换的问题
					ajaxParam.submitData = JSON.parse(ajaxParam.submitData);

			//处理Repeater数据
			var dataRepeaterListName;
			if (pageParam.dataRepeaterList != null)
				dataRepeaterListName = pageParam.dataRepeaterList.name;

			$.each(formData, function (i, n) {
				//处理Repeater数据
				if (dataRepeaterListName != null && formData[i].name.indexOf(dataRepeaterListName) > -1)
					return true;

				var propertyName = getPropertyName(formData[i].name);
				ajaxParam.submitData[propertyName] = formData[i].value;
			});
			//处理Repeater数据
			if (pageParam.dataRepeaterList != null)
				if ($('.repeater').repeaterVal() != null && $('.repeater').repeaterVal()[dataRepeaterListName] != null) {
					var repeaterListForm = $('.repeater').repeaterVal()[dataRepeaterListName];
					var repeaterList = new Array();
					for (var i = 0; i < repeaterListForm.length; i++) {
						var repeater = {};

						//遍历Repeater对象属性
						$.each(repeaterListForm[i], function (k, w) {
							k = getPropertyName(k);
							var startIndex = k.indexOf(dataRepeaterListName) + dataRepeaterListName.length;
							var repeaterPropertyName = k.substr(startIndex);
							//转换属性名称，首字母小写
							repeaterPropertyName = repeaterPropertyName.substr(0, 1).toLowerCase() + repeaterPropertyName.substr(1);
							repeater[repeaterPropertyName] = w;
						});
						repeaterList.push(repeater);
					}
					ajaxParam.submitData[dataRepeaterListName] = repeaterList;
				}


			if (ajaxParam.type == null)
				ajaxParam.type = "POST";
			if (ajaxParam.contentType == null)
				ajaxParam.contentType = "application/json;charset=utf-8";

			if (isUpdateList) {
				ajaxParam.submitData = {
					"entity": ajaxParam.submitData,
					"idList": JSON.parse(selectRowsCookie)
				};
				ajaxParam.url = ajaxParam.updateListUrl;
			}
			if (ajaxParam.contentType === "application/json;charset=utf-8")
				if (typeof ajaxParam.submitData == "object")// 处理重复提交时反复转换的问题
					ajaxParam.submitData = JSON.stringify(ajaxParam.submitData);
			// 提交富文本数据，如果包含特殊符号"&"，到后台的数据会被截断，所以用encodeURIComponent。
			if (ajaxParam.contentType === "application/x-www-form-urlencoded")
				if (typeof ajaxParam.submitData == "object")// 处理重复提交时反复转换的问题
					ajaxParam.submitData = "jsonData=" + encodeURIComponent(JSON.stringify(ajaxParam.submitData));
			if (ajaxParam.dataType == null)
				ajaxParam.dataType = "JSON";
			if (ajaxParam.async == null)
				ajaxParam.async = true;
			if (ajaxParam.success == null)
				ajaxParam.success = function (resultData) {
					if (!resultData["isSuccess"]) {
						alert(resultData["resultMessage"]);
						return false;
					}

					layer.msg('记录修改成功，即将跳回列表页……', {
						time: 1000
					});

					if (isUpdateList) {
						setTimeout("closeLayer();", 1100);
						var cookieData = {
							item: "selectRows"
						};
						removeCookies(cookieData);
						parent.location.reload(); // 父页面刷新
					} else {
						setTimeout("$('#navbarListA').click();", 1100);
					}
				};
			if (ajaxParam.error == null)
				ajaxParam.error = ajaxErrorFunction;

			var ajaxParamter = {
				"url": ajaxParam.url,
				"data": ajaxParam.submitData,
				"dataType": ajaxParam.dataType,
				"contentType": ajaxParam.contentType,
				"type": ajaxParam.type,
				"async": ajaxParam.async,
				"success": ajaxParam.success,
				"error": ajaxParam.error
			};
			universalAjax(ajaxParamter);
		}
	});

	$("#buttonBack").click(function () {
		history.back();
		return false;
	});

	if (isUpdateList) {
		$("span.required").remove();
		$(".page-sidebar-wrapper").remove();
		$(".page-header").remove();
		$(".page-bar").remove();
		$(".page-footer").remove();
		$("#buttonBack").click(function () {

			closeLayer();

			return false;
		});
		return null;
	}

	// 初始化页面标签
	var ajaxParamter = {
		"url": ajaxParam.getModelUrl + id,
		"type": "GET",
		"async": true,
		"success": function (resultData) {
			resultAjaxData = resultData;
			if (!resultData["isSuccess"]) {
				alert(resultData["resultMessage"]);
				return false;
			}
			var ajaxData = resultData.data;


			//处理Repeater数据
			var $repeater = null;
			var dataRepeaterListName;
			var repeaterClass = {};

			if (pageParam.dataRepeaterList != null) {
				$repeater = FormRepeater.init(pageParam);
				dataRepeaterListName = pageParam.dataRepeaterList.name;
			}


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

				//定义Repeater对象中的属性
				if ($repeater != null && fieldName.indexOf((dataRepeaterListName.substr(0, 1).toUpperCase() + dataRepeaterListName.substr(1))) > -1) {
					var startIndex = fieldName.lastIndexOf("[") + 1;
					var endIndex = fieldName.lastIndexOf("]");
					repeaterClass[fieldName.substring(startIndex, endIndex)] = "";

					continue;
				}

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
			//处理Repeater数据
			if ($repeater != null) {
				var dataRepeaterList = ajaxData[dataRepeaterListName];
				var repeaterList = new Array();
				$.each(dataRepeaterList, function (i, v) {
					var repeaterObj = {};
					jQuery.each(repeaterClass, function (j, w) {
						var startIndex = j.indexOf((dataRepeaterListName.substr(0, 1).toUpperCase() + dataRepeaterListName.substr(1))) + dataRepeaterListName.length;
						var propertyName = j.substr(startIndex).substr(0, 1).toLowerCase() + j.substr(startIndex).substr(1);
						repeaterObj[j] = v[propertyName];
					});
					repeaterList.push(repeaterObj)
				});
				$repeater.setList(repeaterList);
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
