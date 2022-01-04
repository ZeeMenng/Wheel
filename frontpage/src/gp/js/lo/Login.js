/**
 * @author Zee
 * @createDate 2018/02/02 05:11:00
 * @updateDate 2018/02/02 05:11:00
 * @description 后台登录页的js方法。
 */

$(document).ready(function () {
	// 如果已经登录，跳转到首页页
	if (getCookies({ item: "token" })) {
		var token = JSON.parse(getCookies({ item: "token" }));
		$("#userName").text(token.userName);
		if (new Date(token.adeadTime) >= new Date()) {
			location.href = '../in/Index.html';
			return true;
		}
	}

	$('#clickmewow').click(function () {
		$('#radio1003').attr('checked', 'checked');
	});
	if (Cookies("remember") == "true") {
		$("#remember").attr("checked", true);
		$("#username").val(Cookies("userName"));
		$("#password").val(Cookies("passWord"));
	}
	$("#tnc").change(function () {
		$('#registpolicy').css('display', 'none');
	});
	$(document).keydown(function (event) {// 给输入框绑定按键事件
		e = event ? event : (window.event ? window.event : null);
		if (e.keyCode == "13") {// 判断如果按下的是回车键则执行下面的代码
			$("#login").trigger("click");
		}
	});
	$("#login").click(function () {
		var userName = $("#username").val();
		var password = $("#password").val();
		// 判断记住密码选项是否勾选，如勾选，记录cookie，如未勾选，删除cookie
		if ($('#remember').prop('checked')) {
			Cookies("remember", "true", {
				expires: 7
			}); // 存储一个带7天期限的 cookie
			Cookies("userName", userName, {
				expires: 7
			}); // 存储一个带7天期限的 cookie
			Cookies("passWord", password, {
				expires: 7
			}); // 存储一个带7天期限的 cookie
		} else {
			Cookies("remember", "false", {
				expires: -1
			}); // 删除 cookie
			Cookies("userName", '', {
				expires: -1
			});
			Cookies("passWord", '', {
				expires: -1
			});
		}

		if (userName == '') {
			$("#hide").show().find("span").html('请输入用户名')
		}
		if (password == '') {
			$("#hide").show().find("span").html('请输入密码')
		}
		if (password == '' && userName == '') {
			$("#hide").show().find("span").html('输入用户名和密码.')
		}
		if (userName && password) {
			// 查询内容列表
			var submitData = {
				"client_id": DOMAIN_ID_GP,
				"grant_type": "password",
				"username": userName,
				"password": password

			}
			$("#hide").hide();
			getData(submitData)

		}

	})

	// 忘记密码-填写邮箱-点击提交
	$("#codeBtn").click(function () {
		var toMailAddress = $("#email").val();
		if (toMailAddress == '') {
			$("#hide1").show().find("span").html('请输入您的邮箱,用于获取验证码！')
		}
		if (toMailAddress) {
			// 查询内容列表
			var submitData = {
				"entityRelated": {
					"toMailAddress": toMailAddress
				}
			}
			$("#hide1").hide();
			var submitDataString = JSON.stringify(submitData);
			getResetData(submitDataString)

		}

	});

	// 忘记密码-填写邮箱-验证码校验
	$("#submit-btn").click(function () {
		var hiddenCode = $("#hiddenCode").val(), code = $("#code").val(), reusername = $("#hiddenUserName").val(), email = $("#email").val();
		if (email == null || email == "") {
			// 验证码错误
			$("#hide1").show().find("span").html('请填写您的邮箱以找回密码！')
		} else if (code != hiddenCode || code == "" || code == null) {
			// 验证码错误
			$("#hide1").show().find("span").html('请输入正确的验证码！')
		} else if (code == hiddenCode) {
			// 验证码验证成功，显示用户名及填写新密码模块，带出用户名
			$("#hidden-btn").css("display", "none")
			$("#hidden-form").show()
			$("#reusername").attr("placeholder", reusername)
		}

	});

	// 忘记密码-填写邮箱-点击提交-修改密码-点击提交
	$("#update-btn").click(function () {
		var updateid = $("#hiddenId").val(), code = $("#code").val(), reusername = $("#hiddenUserName").val(), newpassword = $("#newpassword").val(), rpnewpassword = $("#rpnewpassword").val();
		if (newpassword == '') {
			$("#hide2").show().find("span").html('请输入新密码')
		}
		if (rpnewpassword == '') {
			$("#hide2").show().find("span").html('请再次输入新密码')
		}
		if (newpassword != rpnewpassword) {
			$("#hide2").show().find("span").html('两次输入的密码不一致')
		}
		if (updateid && newpassword) {
			// 查询内容列表
			var submitData = {
				"entityRelated": {
					"code": code,
					"id": updateid,
					"userName": reusername,
					"newPassWord": newpassword,
					"rpnewpassword": rpnewpassword
				}
			}
			$("#hide2").hide();
			var submitDataString = JSON.stringify(submitData);
			getUpdateData(submitDataString)

		}
	});

	// 用户名和邮箱的排重校验begin
	var isUserNameMatchCondition = true;
	var isEmailMatchCondition = true;
	// A、当输入框失去焦点时，如果输入框的内容不为空，则进行校验
	// B、当输入框获得焦点时，隐藏提示内容

	$("#textUserName").blur(function () {
		var userName = $('#textUserName').val();
		var url = INTERFACE_SERVER + "/extend/swagger/gp/gpUser/userNameVerify?userName=" + userName;
		var type = "GET";
		var contentType = "application/x-www-form-urlencoded";
		var success = function (resultData) {
			if (!resultData["isSuccess"]) {
				alert(resultData["resultMessage"]);
				return false;
			}
			if (resultData.data.length >= 1) {
				$("#errorBox_userNamePromp").show();
				isUserNameMatchCondition = false;
			} else {
				$("#errorBox_userNamePromp").hide();
				isUserNameMatchCondition = true;
			}
		};
		if (userName != "" && userName != null && userName != undefined) {
			$.ajax({
				type: type,
				url: url,
				contentType: contentType,
				success: success
			});
		} else {
			$("#errorBox_userNamePromp").hide();
		}
	});
	$("#textUserName").on('focus', function () {
		$("#errorBox_userNamePromp").hide();
	});
	$("#textEmail").blur(function () {
		// alert("邮箱失去焦点");
		var email = $('#textEmail').val();
		var url = INTERFACE_SERVER + "/extend/swagger/gp/gpUser/emailVerify?email=" + email;
		var type = "GET";
		var contentType = "application/x-www-form-urlencoded";
		var success = function (resultData) {
			if (!resultData["isSuccess"]) {
				alert(resultData["resultMessage"]);
				return false;
			}
			if (resultData.data.length >= 1) {
				$("#errorBox_emailPromp").show();
				isEmailMatchCondition = false;
			} else {
				$("#errorBox_emailPromp").hide();
				isEmailMatchCondition = true;
			}
		};
		if (email != "" && email != null && email != undefined) {
			$.ajax({
				type: type,
				url: url,
				contentType: contentType,
				success: success
			});
		} else {
			$("#errorBox_emailPromp").hide();
		}
	});
	$("#textEmail").on('focus', function () {
		$("#errorBox_emailPromp").hide();
	});

	// 用户名和邮箱的排重校验end

	handleLogin();
	handleForgetPassword();
	handleRegister();

});

function getData(ajaxParam) {
	$.ajax({
		"type": "POST",
		"url": INTERFACE_SERVER + "/oauth/token",
		"data": ajaxParam,
		"dataType": "json",
		"contentType": "application/x-www-form-urlencoded",
		"async": true,
		"success": function (res) {
			if (res.isSuccess) {
				var infoData = JSON.stringify(res.data);
				var cookieData = {
					item: "token",
					data: infoData,
					path: '/'
				};
				setCookies(cookieData);
				setUserConfigCookie();
				window.location.href = '../in/Index.html';
				window.event.returnValue = false;
			} else {
				$("#hide").show().find("span").html(res.resultMessage)
			}
		}
	});
}

function getResetData(ajaxParam) {
	$.ajax({
		type: "GET",
		url: INTERFACE_SERVER + "/extend/swagger/gp/gpUser/sendResetMailByJsonData",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: "jsonData=" + ajaxParam,
		async: false,
		success: function (res) {
			if (res.isSuccess) {
				var infoData = JSON.stringify(res.data);

				var cookieData = {
					item: "token",
					data: infoData,
					path: '/',
					expires: date
				};
				setCookies(cookieData);
				$("#hiddenCode").val(res.data[0].code);
				$('#hiddenId').val(res.data[0].id);
				$('#hiddenUserName').val(res.data[0].userName);
				$("#hide1").show().find("span").html(res.resultMessage)
				// window.location.href = '../lo/login.html';
				// window.event.returnValue=true;
			} else {
				$("#hide1").show().find("span").html(res.resultMessage)
			}
		}
	})
}

function getUpdateData(ajaxParam) {
	$.ajax({
		type: "GET",
		url: INTERFACE_SERVER + "/extend/swagger/gp/gpUser/updatePassWord",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: "jsonData=" + ajaxParam,
		async: false,
		success: function (res) {
			if (res.isSuccess) {
				var infoData = JSON.stringify(res.data);

				var cookieData = {
					item: "token",
					data: infoData,
					path: '/',
					expires: date
				};
				$("#hide2").show().find("span").html(res.resultMessage)
			} else {
				$("#hide2").show().find("span").html(res.resultMessage)
			}
		}
	});
}

function handleLogin() {
	$('.login-form').validate({
		errorElement: 'span', // default input error message container
		errorClass: 'help-block', // default input error message class
		focusInvalid: false, // do not focus the last invalid input
		rules: {
			username: {
				required: true
			},
			password: {
				required: true
			},
			remember: {
				required: false
			}
		},

		messages: {
			username: {
				required: "Username is required."
			},
			password: {
				required: "Password is required."
			}
		},

		invalidHandler: function (event, validator) { // display error alert
			// on form submit
			$('.alert-danger', $('.login-form')).show();
		},

		highlight: function (element) { // hightlight error inputs
			$(element).closest('.form-group').addClass('has-error'); // set
			// error
			// class
			// to
			// the
			// control
			// group
		},

		success: function (label) {
			label.closest('.form-group').removeClass('has-error');
			label.remove();
		},

		errorPlacement: function (error, element) {
			error.insertAfter(element.closest('.input-icon'));
		},

		submitHandler: function (form) {
			form.submit();
		}
	});

	$('.login-form input').keypress(function (e) {
		if (e.which == 13) {
			if ($('.login-form').validate().form()) {
				$('.login-form').submit();
			}
			return false;
		}
	});
}

function handleForgetPassword() {
	$('.forget-form').validate({
		errorElement: 'span', // default input error message container
		errorClass: 'help-block', // default input error message class
		focusInvalid: false, // do not focus the last invalid input
		ignore: "",
		rules: {
			email: {
				required: true,
				email: true
			}
		},

		messages: {
			email: {
				required: "Email is required."
			}
		},

		invalidHandler: function (event, validator) { // display error alert
			// on form submit

		},

		highlight: function (element) { // hightlight error inputs
			$(element).closest('.form-group').addClass('has-error'); // set
			// error
			// class
			// to
			// the
			// control
			// group
		},

		success: function (label) {
			label.closest('.form-group').removeClass('has-error');
			label.remove();
		},

		errorPlacement: function (error, element) {
			error.insertAfter(element.closest('.input-icon'));
		},

		submitHandler: function (form) {
			form.submit();
		}
	});

	$('.forget-form input').keypress(function (e) {
		if (e.which == 13) {
			if ($('.forget-form').validate().form()) {
				$('.forget-form').submit();
			}
			return false;
		}
	});

	jQuery('#forget-password').click(function () {
		jQuery('.login-form').hide();
		jQuery('.forget-form').show();
	});

	// 忘记密码-提交-返回
	jQuery('#back-btn').click(function () {
		jQuery("#hide1").hide();
		jQuery("#hide2").hide();
		document.getElementById("resetPassword").reset();
		document.getElementById("forgetPassword").reset();
		jQuery("#hidden-form").hide();
		jQuery('#hidden-btn').show();
		jQuery('.login-form').show();
		jQuery('.forget-form').hide();
	});

	// 忘记密码-返回
	jQuery("#back-btn1").click(function () {
		jQuery("#hide1").hide();
		jQuery("#hide2").hide();
		document.getElementById("forgetPassword").reset();
		document.getElementById("resetPassword").reset();
		jQuery("#forgetPassword").css("display", "none")
		jQuery(".login-form").show()
	});

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

function handleRegister() {

	function format(state) {
		if (!state.id) {
			return state.text;
		}
		var $state = $('<span><img src="../assets/global/img/flags/' + state.element.value.toLowerCase() + '.png" class="img-flag" /> ' + state.text + '</span>');

		return $state;
	}

	if (jQuery().select2 && $('#country_list').size() > 0) {
		$("#country_list").select2({
			placeholder: '<i class="fa fa-map-marker"></i>&nbsp;Select a Country',
			templateResult: format,
			templateSelection: format,
			width: 'auto',
			escapeMarkup: function (m) {
				return m;
			}
		});

		$('#country_list').change(function () {
			$('.register-form').validate().element($(this)); // revalidate
			// the chosen
			// dropdown
			// value and
			// show error or
			// success
			// message for
			// the input
		});
	}

	$('.register-form').validate({
		errorElement: 'span', // default input error message container
		errorClass: 'help-block', // default input error message class
		focusInvalid: false, // do not focus the last invalid input
		ignore: "",
		rules: {

			fullname: {
				required: true
			},
			email: {
				required: true,
				email: true
			},
			address: {
				required: true
			},
			city: {
				required: true
			},
			country: {
				required: true
			},

			username: {
				required: true
			},
			password: {
				required: true
			},
			rpassword: {
				equalTo: "#register_password"
			},

			tnc: {
				required: true
			}
		},

		messages: { // custom messages for radio buttons and checkboxes
			tnc: {
				required: "请勾选注册须知完成注册，谢谢！"
			}
		},

		invalidHandler: function (event, validator) { // display error alert
			// on form submit

		},

		highlight: function (element) { // hightlight error inputs
			$(element).closest('.form-group').addClass('has-error'); // set
			// error
			// class
			// to
			// the
			// control
			// group
		},

		success: function (label) {
			label.closest('.form-group').removeClass('has-error');
			label.remove();
		},

		errorPlacement: function (error, element) {
			if (element.attr("name") == "tnc") { // insert checkbox errors
				// after the container
				error.insertAfter($('#register_tnc_error'));
			} else if (element.closest('.input-icon').size() === 1) {
				error.insertAfter(element.closest('.input-icon'));
			} else {
				error.insertAfter(element);
			}
		},

		submitHandler: function (form) {
			/* form.submit(); */
			var formRegister = $('#formRegister');
			var url = INTERFACE_SERVER + "/extend/swagger/gp/gpUser/register";
			var type = "POST";
			var contentType = "application/x-www-form-urlencoded";
			var dataType = "JSON";
			var ajaxParam = {
				submitData: {}
			};
			var formData = formRegister.serializeArray();
			if (ajaxParam.submitData != null)
				$.each(formData, function (i, n) {
					var propertyName = getPropertyName(formData[i].name)
					ajaxParam.submitData[propertyName] = formData[i].value;
				});
			var submitData = "jsonData=" + encodeURIComponent(JSON.stringify(ajaxParam.submitData));
			var success = function (resultData) {
				if (!resultData["isSuccess"]) {
					alert(resultData["resultMessage"]);
					return false;
				}

				layer.msg('注册成功，谢谢！', {
					time: 10000
				});

				setTimeout("$('#navbarListA').click();", 5000);

				if (resultData.isSuccess) {
					window.location.href = '../lo/Login.html';
					window.event.returnValue = false;
				}
			};
			if (isUserNameMatchCondition == true && isEmailMatchCondition == true) {
				$.ajax({
					type: type,
					url: url,
					contentType: contentType,
					data: submitData,
					dataType: dataType,
					success: success
				});
			} else {
				return;
			}

		}
	});

	$('.register-form input').keypress(function (e) {
		if (e.which == 13) {
			if ($('.register-form').validate().form()) {
				$('.register-form').submit();
			}
			return false;
		}
	});

	// 注册须知
	jQuery('#registpolicy-btn').click(function () {

		jQuery('#registpolicy').css('display', 'block');
		jQuery('.login-form').hide();
	});

	jQuery('#register-btn').click(function () {
		jQuery('.login-form').hide();
		jQuery('.register-form').show();
	});

	// 用户注册，返回按钮
	jQuery('#register-back-btn').click(function () {
		$("#tnc-error").hide();
		$("#register_password-error").hide();
		$("#username-error").hide();
		$("#email-error").hide();
		$("#fullname-error").hide();
		$("#rpassword-error").hide();
		document.getElementById("formRegister").reset();
		jQuery('.login-form').show();
		jQuery('.register-form').hide();
		jQuery('#registpolicy').css('display', 'none');
	});
}