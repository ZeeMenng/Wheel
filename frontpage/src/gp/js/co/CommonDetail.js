$(document).ready(function() {
	// 隐藏详情页中的ID标签（如果有的话）
	$("#labelId").parent().parent().css("display", "none");
});

/**
 * @author Zee
 * @createDate 2021年4月2日 下午2:04:46
 * @updateDate 2021年4月2日 下午2:04:46
 * @description 初始化通用详情页面
 */
function initDetailPage(pageParam, ajaxParamter) {
	$("#buttonBack").click(function() {
		history.back();
		return false;
	});
	var id = request("id");
	var resultAjaxData;
	if (id == null) {
		layer.alert("未能获取到主键信息……", {
			icon : 6
		});
		return;
	}
	if (ajaxParamter.url == null) {
		layer.alert("请求的链接地址不能为空……", {
			icon : 6
		});
		return;
	}
	ajaxParamter.url = ajaxParamter.url + id;
	if (ajaxParamter.type == null)
		ajaxParamter.type = "GET";
	if (ajaxParamter.dataType == null)
		ajaxParamter.dataType = "JSON";
	if (ajaxParamter.async == null)
		ajaxParamter.async = true;
	if (ajaxParamter.success == null)
		ajaxParamter.success = function(resultData) {
			if (!resultData["isSuccess"]) {
				layer.alert(resultData["resultMessage"], {
					icon : 6
				});
				return;
			}

			$("#" + pageParam.formId + " label").each(function(i, n) {
				// 遍历指定form表单中的所有label标签
				var fieldId = $(n).attr("id");
				var prefix = fieldId.substr(5);
				prefix = prefix.substr(0, 1).toLowerCase() + prefix.substr(1);
				$(n).html(resultData.data[prefix]);
			});
			$("#" + pageParam.formId + " img").each(function(i, n) {
				// 遍历指定form表单中的所有图像标签
				var fieldId = $(n).attr("id");
				var prefix = fieldId.substr(3);
				prefix = prefix.substr(0, 1).toLowerCase() + prefix.substr(1);
				if (resultData.data[prefix] != null)
					// $(n).attr("src", siteName + resultData.data[prefix]);
					$(n).attr("src", resultData.data[prefix]);
			});

			resultAjaxData = resultData;

		};
	if (ajaxParamter.error == null)
		ajaxParamter.error = ajaxErrorFunction;

	universalAjax(ajaxParamter);
	// 只有同步请求才能将获取的值返回
	if (!ajaxParamter.async)
		return resultAjaxData;
	return null;
}

function printPage() {
	bdhtml = window.document.body.innerHTML;// 获取当前页的html代码
	sprnstr = "<!--startprint-->";// 设置打印开始区域
	eprnstr = "<!--endprint-->";// 设置打印结束区域
	prnhtml = bdhtml.substring(bdhtml.indexOf(sprnstr) + 18); // 从开始代码向后取html

	prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));// 从结束代码向前取html
	window.document.body.innerHTML = prnhtml;
	window.print();
	window.document.body.innerHTML = bdhtml;
}


//跳转到修改页面
function gotoEditPage(editPage) {
	if (editPage == null) {
		var index = window.location.href.indexOf("Detail.html");
		if (index == -1)
			layer.msg('未找到修改页面……', {
				time : 1500
			});
		else
			window.location = window.location.href.replaceAll("Detail.html", "Edit.html");
	} else
		window.location = editPage;

}