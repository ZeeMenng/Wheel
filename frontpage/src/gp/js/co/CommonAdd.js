
/**
 * @author Zee
 * @createDate 2021年4月2日 下午2:10:49
 * @updateDate 2021年4月2日 下午2:10:49
 * @description 初始化新增页面表单
 */
function initAddPage(pageParam, ajaxParam) {

    var formAdd = $('#' + pageParam.formId);
    var errorMessage = $('.alert-danger', formAdd);
    var successMessage = $('.alert-success', formAdd);
    var resultAjaxData;

    formAdd.on("submit", function () {
        for (var e in CKEDITOR.instances)
            CKEDITOR.instances[e].updateElement();
        dynamicRules(pageParam);
        lengthVerificationRules(pageParam);
        promptElementRules(pageParam);
    });

    formAdd.validate({
        errorClass: "help-block",
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
            var formData = formAdd.serializeArray();

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

                var propertyName = getPropertyName(formData[i].name)
                ajaxParam.submitData[propertyName] = formData[i].value;
            });

            //处理Repeater数据
            if (dataRepeaterListName != null)
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
            if (ajaxParam.contentType === "application/json;charset=utf-8")
                if (typeof ajaxParam.submitData == "object")// 处理重复提交时反复转换的问题
                    ajaxParam.submitData = JSON.stringify(ajaxParam.submitData);
            if (ajaxParam.contentType === "application/x-www-form-urlencoded")
                if (typeof ajaxParam.submitData == "object")// 处理重复提交时反复转换的问题
                    ajaxParam.submitData = "jsonData=" + encodeURIComponent(JSON.stringify(ajaxParam.submitData));
            if (ajaxParam.dataType == null)
                ajaxParam.dataType = "JSON";
            if (ajaxParam.async == null)
                ajaxParam.async = true;
            if (ajaxParam.success == null)
                ajaxParam.success = function (resultData) {
                    resultAjaxData = resultData;
                    if (!resultData["isSuccess"]) {
                        alert(resultData["resultMessage"]);
                        return false;
                    }

                    layer.msg('记录添加成功，即将跳回列表页……', {
                        time: 1000
                    });
                    setTimeout("$('#navbarListA').click();", 1100);

                };
            if (ajaxParam.error == null)
                ajaxParam.error = ajaxErrorFunction;

            var ajaxParamter = {
                "url": ajaxParam.url,
                "data": ajaxParam.submitData,
                "dataType": ajaxParam.dataType,
                "contentType": ajaxParam.contentType,
                "type": ajaxParam.type,
                "async": ajaxParam.sync,
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

    if (pageParam.dataRepeaterList != null)
        FormRepeater.init(pageParam);

    if (!ajaxParam.async)
        return resultAjaxData;
    return null;

}


