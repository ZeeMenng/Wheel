/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description  字典类型。 相关页面的js方法。
 */
var FormRepeater = function () {
    return {
        init: function () {
            $(".mt-repeater").repeater({
                show: function () {
                    $(this).slideDown(),
                        $(".date-picker").datepicker({
                            rtl: App.isRTL(),
                            orientation: "left",
                            autoclose: !0
                        })
                },
                hide: function (e) {
                    confirm("Are you sure you want to delete this element?") && $(this).slideUp(e)
                },
                ready: function (e) { }
            })
        }
    }
}();


function getRepeaterVal() {

    var dictionaryListForm = $('.mt-repeater').repeaterVal().dictionaryListRepeater;
    var dictionaryList = new Array();
    for (var i = 0; i < dictionaryListForm.length; i++) {
        var dictionary = {};
        dictionary.text = dictionaryListForm[i].textDictionaryText;
        dictionary.code = dictionaryListForm[i].textDictionaryCode;
        dictionary.remark = dictionaryListForm[i].textDictionaryRemark;
        dictionaryList.push(dictionary);
    }
    repeaterVal = { dictionaryList: dictionaryList };
    return repeaterVal;
}