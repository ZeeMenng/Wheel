var txtActive;

// 验证插件中加入手机号校验功能
jQuery.validator.addMethod("phone", function (value, element) {
	var length = value.length;
	var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\d{8})$/;
	return this.optional(element) || (length == 11 && mobile.test(value));
}, "请填写正确的手机号码");

$(document).ready(function () {
	if (!validateLogin()) {
		return false;
	}
	var userConfig = getUserConfigByCode("pageSize");
	if (userConfig)
		DEFAULT_PAGE_SIZE = userConfig.configValue;

	// 是否折叠菜单
	if (getUserConfigByCode("tableLayout") != null) {
		var isFoldConfig = (getUserConfigByCode("isFold").configValue === 'true');
		if (isFoldConfig) {
			$("body").addClass("page-sidebar-closed");
			$("#linkMenuUl").addClass("page-sidebar-menu-closed");
			$(".search-wrap").hide();
		}
	}

	if (window.location.pathname.indexOf("/lo/Login.html") != -1) {
		return true;
	}
	$("#batchEditButton").prop("class", "hidden");

	// 注销按钮
	$("#aLogout").click(function () {
		var ajaxParamter = {
			"url": "/oauth/logout",
			"data": {},
			"type": "GET",
			"async": true,
			"success": function (resultData) {
				if (!resultData["isSuccess"]) {
					layer.alert("退出操作出错！" + resultData["resultMessage"], {
						icon: 6
					});
					return false;
				}

				var cookieData = {
					item: "token"
				};
				removeCookies(cookieData);

				location.href = '../lo/Login.html';
			}
		};

		universalAjax(ajaxParamter);

	});

	if (jQuery().datepicker) {
		$('.date-picker').datepicker({
			language: 'zh-CN',
			todayBtn: "linked",
			autoclose: true,
			rtl: App.isRTL(),
			format: "yyyy-mm-dd",
			fontAwesome: true,
			orientation: "left",
			todayHighlight: true,
		});

		initMessage();
	}
	if (jQuery().datetimepicker) {
		$(".form-datetime").datetimepicker({
			language: 'zh-CN',
			format: "yyyy-mm-dd hh:ii P"

		});
	}
	$(".form_datetime").datetimepicker({
		language: 'zh-CN',
		todayBtn: 1,
		autoclose: true,
		isRTL: App.isRTL(),
		format: "yyyy-mm-dd hh:ii",
		fontAwesome: true,
		todayHighlight: true,
		pickerPosition: (App.isRTL() ? "bottom-right" : "bottom-left")
	});

	initNavbar();
	layui.use(['layer', 'form'], function () {
		var layer = layui.layer, form = layui.form;
	});

	initLinkMenu();

});

/** ***************************初始化化左侧菜单、头部消息******************************************* */
function initNavbar() {
	var href = window.location.href;

	var beginIndex = href.lastIndexOf('/') + 1;
	var endIndex = href.indexOf('.html');
	href = href.substring(beginIndex, endIndex);
	var sL = href.length;
	var lastUpperCodeIndex = 0;
	for (var i = 0; i < sL; i++) {
		if (href.charAt(i) === href.charAt(i).toUpperCase()) {
			lastUpperCodeIndex = i;
		}
	}
	href = href.substring(0, lastUpperCodeIndex);
	$("#navbarListA").attr("href", href + "List.html");

	$("#navbarIndexA").click(function () {
		window.location = GP_INDEX;
		return false;
	});

	$("#navbarListA").click(function () {

		window.location = href + "List.html";
		return false;
	});

}

// 初始化左侧菜单
function initLinkMenu() {
	var userInfo = JSON.parse(getCookies({
		item: "token"
	}));
	var userName = userInfo.userName;
	var ajaxParamter = {
		"url": RU_GPMODULE_GETLINKMENU + "?userName=" + userName,
		"type": "GET",
		"data": "jsonData=" + encodeURIComponent(JSON.stringify({
			"domainId": DOMAIN_ID_GP
		})),
		"async": true,
		"success": function (result) {
			total = result.totalCount;
			var data = result.data;
			$.each(data, function (i, n) {

				if (n["level"] == "1") {
					var menu = $("#firstLevelMenuLi").clone();
					menu.attr("id", n["id"]);
					menu.attr("fartherId", n["fartherId"]);
					menu.find("h3").html(n["name"]);
					$("#linkMenuUl").append(menu);
				}

			});

			$.each(data, function (i, n) {
				if (n["level"] == "2") {
					{
						var secondLevelMenu = $("#secondeLevelMenuLi").clone();
						secondLevelMenu.attr("id", n["id"]);
						secondLevelMenu.attr("fartherId", n["fartherId"]);

						if (n["pageUrl"] != null)
							secondLevelMenu.find("a.nav-toggle").attr("href", n["pageUrl"]);
						else
							secondLevelMenu.find("a.nav-toggle").attr("href", "javascript:;");

						secondLevelMenu.find("span.title").html(n["name"]);
						secondLevelMenu.find("a i").addClass(n["iconClass"]);
						// 如果有自定义的菜单图标，则显示
						if (n["iconResource"] != null && n["iconResource"] != '')
							thirdLevelMenu.find("img").show().attr("src", n["iconResource"]);
						var $firstLevelMenu = $("#linkMenuUl li[id='" + n["fartherId"] + "']");
						var $lastSecondLevelMenu = $("#linkMenuUl li[fartherId='" + n["fartherId"] + "']:last");

						if ($lastSecondLevelMenu.length != 0)
							$lastSecondLevelMenu.after(secondLevelMenu);
						else if ($firstLevelMenu.length != 0)
							$firstLevelMenu.after(secondLevelMenu);
						// 说明没有一级菜单，则二级菜单为一级菜单
						else
							$("#linkMenuUl").append(secondLevelMenu);
					}
				}
			});

			$.each(data, function (i, n) {
				if (n["level"] == "3") {
					{
						var thirdLevelMenu = $("#thirdLevelMenuLi").clone();
						thirdLevelMenu.attr("id", n["id"]);
						if (n["pageUrl"] != null) {
							thirdLevelMenu.find("a").attr("href", BASE_PATH + n["pageUrl"]);
						} else
							thirdLevelMenu.find("a").attr("href", "javascript:;");

						thirdLevelMenu.find("span.title").html(n["name"]);

						thirdLevelMenu.find("span.badge").html(n["XiaoXiShuLiang"]);
						thirdLevelMenu.find("a i").addClass(n["iconClass"]);
						// 如果有自定义的菜单图标，则显示
						if (n["iconResource"] != null && n["iconResource"] != '')
							thirdLevelMenu.find("img").show().attr("src", n["iconResource"]);
						$("#linkMenuUl li[id='" + n["fartherId"] + "'] ul").append(thirdLevelMenu);
						// 菜单加焦点
						var pageUrl = n["pageUrl"] == null ? "" : n["pageUrl"];

						var lastUrlindex = pageUrl.lastIndexOf("\/");
						pageUrl = pageUrl.substring(lastUrlindex + 1, pageUrl.length);

						var link = window.location.href;
						if ($("#navbarListA").attr("href") != null)
							link = $("#navbarListA").attr("href");

						if (link.indexOf(pageUrl) >= 0) {
							thirdLevelMenu.parent().parent().addClass("active open");
							thirdLevelMenu.addClass("active");
						}

						/** * 左侧导航菜单隐藏事件 */
						$("#linkMenuUl .sidebar-toggler").unbind("click");
						$("#linkMenuUl .sidebar-toggler").click(function () {
							if ($("#linkMenuUl").hasClass("page-sidebar-menu-closed")) {
								var userConfig = getUserConfigByCode("isFold");
								userConfig.configValue = false;
								updateUserConfig(userConfig);
							} else {
								var userConfig = getUserConfigByCode("isFold");
								userConfig.configValue = true;
								updateUserConfig(userConfig);
							}
						})

					}
				}
			});

			// 清除模板
			$("#firstLevelMenuLi").remove();
			$("#secondeLevelMenuLi").remove();
			$("li[id='thirdLevelMenuLi']").remove();
			// 获取当前页面的左侧激活菜单ID
			var activeMenuId = $(".nav-item.start.active").attr("id");
			// 是否命中
			var isHist = false;
			// 左侧搜索匹配激活状态
			$("body").on("keyup mouseup", ".searchInput", function (e) {
				var keyword = $(this).val();
				var txts = $('.badge').prev('.title');

				txts.each(function (i, v) {
					var menuText = $(v).text();
					var liParent = $(v).parent().parent().parent().parent();
					var liParent2 = $(v).parent().parent();

					if (liParent2.hasClass("active")) {
						liParent.find(".sub-menu").css("display", "none");
						liParent.find(".arrow").removeClass("open");
						liParent.removeClass("active open");
					}
					liParent2.removeClass("active");
					if (keyword != '')
						if (menuText.indexOf(keyword) > -1) {
							isHist = true;
							liParent.addClass("active open");
							liParent.find(".sub-menu").css("display", "block");
							liParent.find(".arrow").addClass("open");
							liParent2.addClass("active");
						}

				});
				// 没有命中或者输入框为空的话恢复之前的状态
				if (!isHist || keyword == '') {
					$("#" + activeMenuId).addClass("active");
					$("#" + activeMenuId).parent().css("display", "block");
					$("#" + activeMenuId).parent().parent().addClass("active open");
				}

			});

		}
	};

	universalAjax(ajaxParamter);

}

/**
 * @author Zee
 * @createDate 2021年4月2日 下午2:21:21
 * @updateDate 2021年4月2日 下午2:21:21
 * @description 初始化提醒消息
 */
function initMessage() {
	if (!getCookies({
		item: "token"
	}))
		return false;

	var token = JSON.parse(getCookies({
		item: "token"
	}));
	var userInfo = token.gpUser;
	$("#userName").text(userInfo.userName);

	var ajaxParameter = {
		"url": "/extend/swagger/gp/gprMessageUser/getSysListByJsonData",
		"data": "jsonData=" + JSON.stringify({
			"entityRelated": {
				"userName": token.userName,
				"userId": token.userId
			}
		}),
		"dataType": "json",
		"type": "GET",
		"async": true,
		"success": function (res) {
			if (!validateLogin(res.resultCode))
				return false;
			if (res.totalCount == 0) {
				$("#header_notification_bar .dropdown-menu").hide();
				$("#header_notification_bar .badge").hide();
				return false;
			}

			var html = '';
			$("#header_notification_bar").find("span").text(res.totalCount);
			$("#systematic").text(res.totalCount);
			/*
			 * $("#systematic-time").text(res.data[0].addTime)
			 * $("#systematic-detiles").text(res.data[0].content)
			 */
			for (var i = 0; i < res.data.length; i++) {
				html += '<li>' + '<a href="/pc/ss/gp/html/gp/MessageList.html">' + '<span class="time" id="systematic-time">' + res.data[i].addTime + '</span>' + '<span class="details" style="width: 160px;display: inline-block;">' + '<span class="label label-sm label-icon label-warning">' + '<i class="fa fa-bell-o"></i>' + res.data[i].content + '</span>'
				'</span>' + '</a>' + '</li>'
			}
			$("#systematic-detiles").html(html)
		}
	};
	universalAjax(ajaxParameter);

	ajaxParameter = {
		"url": "/extend/swagger/gp/gprMessageUser/getUserListByJsonData",
		"dataType": "json",
		"type": "GET",
		"async": false,
		"data": "jsonData=" + JSON.stringify({
			"entityRelated": {
				"userName": token.userName,
				"userId": token.userId
			}
		}),
		success: function (res) {
			if (res.totalCount == 0) {
				$("#header_inbox_bar .dropdown-menu").hide();
				$("#header_inbox_bar .badge").hide();
			}
			var html = '';
			$("#header_inbox_bar").find("span").text(res.totalCount);
			$("#userInfo").text(res.totalCount);
			for (var i = 0; i < res.data.length; i++) {
				html += '<li>' + '<a href="/pc/ss/gp/html/gp/MessageList.html">' + '<span class="photo">' + '<img src="../../assets/layouts/layout3/img/avatar2.jpg" class="img-circle" alt="">' + '</span>' + '<span class="subject">' + '<span class="from"> ' + res.data[i].userName + '</span>' + '<span class="time">' + res.data[i].addTime + '</span>' + '</span>' + '<span class="message"> ' + res.data[i].content + '</span>' + '</a>' + '</li>'
			}
			$("#userInfo-detiles").html(html)
		}
	};
	universalAjax(ajaxParameter);

}
/** ***************************初始化化左侧菜单、头部消息******************************************* */

/** ***************************获取/修改用户/应用相关配置******************************************* */

/**
 * @author Zee
 * @createDate 2021年1月22日 下午4:22:16
 * @updateDate 2021年1月22日 下午4:22:16
 * @description 获取并设置Cookie当前用户在当前应用领域下的配置
 */
function setUserConfigCookie() {

	var ajaxParameter = {
		"url": RU_GPRCONFIGUSER_GETCURRENTUSERCONFIG,
		"type": "GET",
		"async": false,
		"success": function (result) {
			var infoData = JSON.stringify(result.data);
			var cookieData = {
				item: "userConfig",
				data: infoData,
				path: '/'
			};
			setCookies(cookieData);
		}
	};
	universalAjax(ajaxParameter);

}

/**
 * @author Zee
 * @createDate 2021年1月22日 下午2:50:39
 * @updateDate 2021年1月22日 下午2:50:39
 * @description 根据Key获取用户配置
 */
function getUserConfigByCode(code) {
	var userConfigListCookie = getCookies({
		item: "userConfig"
	});
	var userConfig = null;
	if (userConfigListCookie) {
		var userConfigList = JSON.parse(userConfigListCookie);

		$.each(userConfigList, function (i, n) {
			if (n.code == code) {
				userConfig = n;
				return false;
			}
		});
	}
	return userConfig;
}

/**
 * @author Zee
 * @createDate 2021年1月22日 下午3:13:09
 * @updateDate 2021年1月22日 下午3:13:09
 * @description 更新用户配置
 */
function updateUserConfig(userConfig) {
	var ajaxParamter = {
		"url": RU_GPRCONFIGUSER_ADDORUPDATE,
		"data": JSON.stringify({
			configId: userConfig.configId,
			configValue: userConfig.configValue
		}),
		"success": function (resultData) {
			// 后台更新成功后，重新初始化本地Cookie
			setUserConfigCookie();
		}
	};

	universalAjax(ajaxParamter);
}
/**
 * ***************************获取/修改 用户/应用
 * 相关配置*******************************************
 */

/** ***************************本地存储相关方法（Cookie和localStorage）******************************************* */
/**
 * @author Zee
 * @createDate 2021年3月25日 下午3:27:14
 * @updateDate 2021年3月25日 下午3:27:14
 * @description 存储Cookie信息，如果支持localStorage优先使用
 */
function setCookies(cookieData) {
	if (window.localStorage) {
		localStorage.removeItem(cookieData.item);
		localStorage.setItem(cookieData.item, cookieData.data);
	} else {
		Cookies.remove(cookieData.item);

		if (cookieData.date == null) {
			var date = new Date();
			date.setTime("Fri, 31 Dec 9999 23:59:59 GMT");
			cookieData.date = date;
		}
		Cookies.set(cookieData.item, cookieData.data, {
			path: cookieData.path,
			expires: cookieData.date
		});
	}
}

/**
 * @author Zee
 * @createDate 2021年3月25日 下午3:36:24
 * @updateDate 2021年3月25日 下午3:36:24
 * @description 移除Cookie信息 如果支持localStorage优先使用
 */
function removeCookies(cookieData) {

	if (window.localStorage)
		localStorage.removeItem(cookieData.item);
	else
		Cookies.remove(cookieData.item);
}

/**
 * @author Zee
 * @createDate 2021年3月25日 下午3:36:09
 * @updateDate 2021年3月25日 下午3:36:09
 * @description 获取Cookie信息 如果支持localStorage优先使用
 */
function getCookies(cookieData) {
	if (window.localStorage)
		return localStorage.getItem(cookieData.item);
	else
		return Cookies.get(cookieData.item);
}
/** ***************************本地存储相关方法（Cookie和localStorage）******************************************* */

/** ***************************校验相关方法******************************************* */
function promptElementRules(pageParam) {
	var validateResult = true;
	var dr = pageParam.promptElementRules;
	if (dr != "" && dr != null && dr != undefined) {
		var len = dr.length;
		for (var i = 0; i < len; i++) {
			if (!($("#" + dr[i].elementId).is(":hidden"))) {
				validateResult = false;
			}
		}
	}
	return validateResult;
}

function getPropertyName(fieldName) {

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
	return prop;
}

function lengthVerificationRules(pageParam) {
	var validateResult = true;
	var dr = pageParam.lengthVerificationRules;
	if (dr != "" && dr != null && dr != undefined) {
		var len = dr.length;
		for (var i = 0; i < len; i++) {
			$("#errorBox_" + dr[i].promptId).hide();
			var len1 = $("#" + dr[i].elementId).val().length;
			var len2 = parseInt(dr[i].lengthRestrict);
			if ($("#" + dr[i].elementId).val().length >= parseInt(dr[i].lengthRestrict)) {
				validateResult = false;
				$("#errorBox_" + dr[i].promptId).show();
			}
		}
	}
	return validateResult;
}

function ajaxRules(pageParam) {
	var passCheck = true;
	var rs = pageParam.ajaxRules;
	if (rs != "" && rs != null && rs != undefined) {
		for (var i = 0, len = rs.length; i < len; i++) {
			var rst = $("#errorBox_" + rs[i].eid).attr("passCheck");
			if (rst == "false") {
				passCheck = false;
				break;
			}
		}
	}
	return passCheck;

}

function dynamicRules(pageParam) {

	var validateResult = true;
	var dr = pageParam.dynamicRules;
	if (dr != "" && dr != null && dr != undefined) {

		var len = dr.length;
		for (var i = 0; i < len; i++) {

			$("#errorBox_" + dr[i].slave).hide();
			if ($("#" + dr[i].master).val() == dr[i].value) {
				if ($("#" + dr[i].slave).val() == "") {
					$("#errorBox_" + dr[i].slave).show();
					validateResult = false;
				}
			}
		}
		if (!$("#errorBox_ImgFormVerification").is(":hidden")) {
			$("#errorBox_hiddenImgPath").hide();
		}
	}
	return validateResult;
}
/** ***************************校验相关方法******************************************* */

/** ***************************通用初始化组件方法（下拉框、单选框、自动填充）******************************************* */
/**
 * Zee 初始化下拉框
 * 
 * @param selectParam
 *            下拉框参数
 * @param ajaxParam
 *            ajax参数
 */
function initDropDownList(selectParam, ajaxParam) {

	var type = ajaxParam.type;
	var submitData = ajaxParam.submitData;

	if (type == null)
		type = "GET";
	if (type.toUpperCase() == "POST" && type != null)
		submitData = JSON.stringify(submitData);

	var ajaxParamter = {
		"url": ajaxParam.url,
		"data": submitData,
		"type": type,
		"async": false,
		"success": function (message) {
			if (!message["isSuccess"]) {
				layer.alert(resultData["resultMessage"], {
					icon: 6
				});
				return false;
			}
			$.each(message.data, function (i, n) {
				$("#" + selectParam.selectId).append("<option value='" + n[selectParam.valueField] + "'>" + n[selectParam.textField] + "</option>");
			});

			return false;
		},

	};

	universalAjax(ajaxParamter);

}

/**
 * Zee 初始化单选按钮组
 * 
 * @param selectParam
 *            下拉框参数
 * @param ajaxParam
 *            ajax参数
 */
function initRadioList(radioParam, ajaxParam) {

	var type = ajaxParam.type;
	var submitData = ajaxParam.submitData;

	if (type == null)
		type = "GET";
	if (type.toUpperCase() == "POST" && type != null)
		submitData = JSON.stringify(submitData);

	var ajaxParamter = {
		"url": ajaxParam.url,
		"data": submitData,
		"type": type,
		"async": false,
		"success": function (message) {
			if (!message["isSuccess"]) {
				layer.alert(resultData["resultMessage"], {
					icon: 6
				});
				return false;
			}
			$.each(message.data, function (i, n) {
				var html = "<label class='mt-radio'>";
				html += "<input type='radio' name='";
				html += radioParam.name;
				html += "' value='";
				html += n[radioParam.valueField];
				html += "'>";
				html += n[radioParam.textField];
				html += "<span></span>";
				html += "</label>";

				$("#" + radioParam.containerId).append(html);
			});

			return false;
		},

	};

	universalAjax(ajaxParamter);

}

// 初始化AutoComplete输入框
function initAutoComplete(itemParam, ajaxParam) {
	var autoCompleteCache = {};
	// 失去输入焦点后，如果显示值为空，则同时清空隐藏值
	$("#" + itemParam.textFieldInputId).unbind("blur");
	$("#" + itemParam.textFieldInputId).bind("blur", function () {
		if ($("#" + itemParam.textFieldInputId).val() == "") {
			$("#" + itemParam.textFieldInputId).val("");
			$("#" + itemParam.valueFieldInputId).val("");
		} else {
			// 如果是已经存在缓存中的关键字，也就是输入到文本框中的文字是不能提交的，只能选择后才能提交
			if (($("#" + itemParam.textFieldInputId).val() in autoCompleteCache) || $("#" + itemParam.textFieldInputId).val().toString().length == 1) {
				$("#" + itemParam.textFieldInputId).val("");
				$("#" + itemParam.valueFieldInputId).val("");
			}
		}
	});

	$("#" + itemParam.textFieldInputId).autocomplete({
		minLength: 2,
		autoFocus: true,
		source: function (request, response) {
			var term = request.term;
			if (term in autoCompleteCache) {
				response($.map(autoCompleteCache[term], function (item) {
					return {
						value: item[itemParam.textField],
						label: item[itemParam.textField],
						submitValue: item[itemParam.valueField]
					}
				}));
				return;
			}
			// 将关键字赋予模糊查询的键
			ajaxParam.jsonData.entityRelated.autoCompleteKey = request.term;

			var ajaxParamter = {
				"url": ajaxParam.url + "?jsonData=" + encodeURIComponent(JSON.stringify(ajaxParam.jsonData)),
				"async": true,
				"type": "GET",
				"success": function (resultData) {
					autoCompleteCache[term] = resultData.data;
					response($.map(resultData.data, function (item) {
						return {
							value: item[itemParam.textField],
							label: item[itemParam.textField],
							submitValue: item[itemParam.valueField]
						}
					}));
				}
			};
			universalAjax(ajaxParamter);

		},
		select: function (e, ui) {
			$("#" + itemParam.valueFieldInputId).val(ui.item.submitValue);
		}
	});
}
/** ***************************通用初始化组件方法（下拉框、单选框、自动填充）******************************************* */

/** ***************************通用Ajax处理方法******************************************* */

/**
 * Zee 通用ajax处理方法
 * 
 * @param ajaxParamter
 */
function universalAjax(ajaxParameter) {

	if (ajaxParameter.url == null) {
		layer.alert("请求链接不能为空！", {
			icon: 6
		});
		return;
	}
	if (ajaxParameter.crossDomain == null)
		ajaxParameter.crossDomain = false;
	if (ajaxParameter.type == null)
		ajaxParameter.type = "POST";
	if (ajaxParameter.contentType == null)
		ajaxParameter.contentType = "application/json;charset=utf-8";
	if (ajaxParameter.dataType == null)
		ajaxParameter.dataType = "JSON";
	if (ajaxParameter.async == null)
		ajaxParameter.async = true;
	if (ajaxParameter.success == null)
		ajaxParameter.success = ajaxSuccessFunction;
	if (ajaxParameter.error == null)
		ajaxParameter.error = ajaxErrorFunction;
	if (ajaxParameter.headers == null) {
		var dataStr = getCookies({
			item: "token"
		});
		ajaxParameter.headers = {
			"Authorization": "Bearer " + JSON.parse(dataStr).accessToken,
			"ClientId": JSON.parse(dataStr).clientId
		};
	}

	var url = ajaxParameter.url;
	var data = ajaxParameter.data;

	url = INTERFACE_SERVER + url;

	$.ajax({
		url: url,
		type: ajaxParameter.type,
		contentType: ajaxParameter.contentType,
		headers: ajaxParameter.headers,
		data: data,
		dataType: ajaxParameter.dataType,
		async: ajaxParameter.async,
		error: ajaxParameter.error,
		beforeSend: ajaxParameter.beforeSend,
		success: ajaxParameter.success
	});
}

/**
 * Zee ajax方法默认的失败回调函数
 * 
 * @param XMLHttpRequest
 * @param textStatus
 * @param errorThrown
 */
function ajaxErrorFunction(XMLHttpRequest, textStatus, errorThrown) {
	// 如果已经有提示框直接返回，不再重复提示
	if ($(".layui-layer.layui-layer-dialog").length != 0)
		return;
	layer.closeAll();
	var statusText = XMLHttpRequest.statusText;
	if (XMLHttpRequest.responseText != null && XMLHttpRequest.responseText != "") {

		var result = JSON.parse(XMLHttpRequest.responseText)
		// Token过期
		if (result.resultCode == RESULT_CODE_TOKEN_EXPIRED) {
			layer.msg(result.resultMessage, {
				time: 1500
			});
			removeCookies({
				item: "token"
			});
			location.href = '../lo/Login.html';
			return;
		}

		layer.alert(result.resultMessage, {
			icon: 6
		});

	} else if (textStatus == "error" && statusText.indexOf("NetworkError") > -1) {
		layer.alert("调用后台接口时出现错误！请检查网络连接……", {
			icon: 6
		});
		return false;
	} else {
		layer.alert("调用后台接口时出现错误！" + textStatus + " " + errorThrown, {
			icon: 6
		});
	}
}

// 利用Ajax-hook插件，拦截Ajax的error方法，如果出现错误（比如网络中断等）不再执行后续操作，但是两个异步Ajax之间无法互相拦截
/*
 * ah.proxy({ onError :(error, handler) => { console.log(error.error.type);
 * console.log(error.config.url); }, });
 */

/** ***************************通用Ajax处理方法******************************************* */

/**
 * Zee 接受页面传递的参数
 * 
 * @param name
 *            传递参数
 * @returns 参数值
 */
function request(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}

function closeLayer() {
	try {
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	} catch (e) {
		return undefined;
	}
}

function historyBack() {
	history.back();
	return false;
}

/**
 * @author Zee
 * @createDate 2021年4月2日 下午2:20:45
 * @updateDate 2021年4月2日 下午2:20:45
 * @description 登录验证，不成功则跳转到登录页
 */
function validateLogin(resultCode) {
	if (resultCode === RESULT_CODE_TOKEN_EXPIRED && window.location.pathname.indexOf("/lo/Login.html") == -1) {
		removeCookies({
			item: "token"
		});
		location.href = '../lo/Login.html';
		return fasle;
	}
	$("#goHome").click(function () {
		location.href = HOME_PATH + RP_ININDEX
	});

	if (getCookies({
		item: "token"
	})) {
		var token = JSON.parse(getCookies({
			item: "token"
		}));
		$("#userName").text(token.userName);
		if (token.gpUser != null && token.gpUser.icon != null && token.gpUser.icon != "")
			$("#userIcon").attr("src", token.gpUser.icon)
		if (new Date(token.adeadTime) >= new Date())
			return true;
	}
	if (window.location.pathname.indexOf("/lo/Login.html") == -1) {
		location.href = '../lo/Login.html';
	}

	return false;
}

function convertToColumnName(property) {
	for (i = 0; i < property.length; i++) {
		if (/[A-Z]/.test(property.charAt(i)))
			property = property.replace(property.charAt(i), '_' + property.charAt(i).toLowerCase());
	}
	return property;
}

function popUpPage(pageParam) {
	var width = "800px";
	var height = $(window).height() - 50 + 'px';
	var offsetTop = '20px';
	var offsetRight = "";

	if (typeof (pageParam.width) != "undefined")
		width = pageParam.width;
	if (typeof (pageParam.height) != "undefined") {
		height = pageParam.height;
		offsetTop = ($(window).height() - pageParam.height) / 2 + 'px';
	}
	if (typeof (pageParam.offsetTop) != "undefined")
		offsetTop = pageParam.offsetTop;
	if (typeof (pageParam.offsetRight) != "undefined")
		offsetRight = pageParam.offsetRight;

	layer.open({
		type: 2,
		title: pageParam.title,
		content: pageParam.url,
		area: [width, height],
		offset: [offsetTop, offsetRight]
	});
}
