/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/1/16 4:36:54
 * @description  链接菜单。 相关页面的js方法。
 */

$(document).ready(function () {

    //初始化应用领域下拉框
    var selectParam = {
        selectId: "typeDomainSelect",
        textField: "name",
        valueField: "id"
    };

    var ajaxParam = {
        url: RU_GPDOMAIN_GETLISTBYJSONDATA + "?jsonData={}"
    }
    initDropDownList(selectParam, ajaxParam);

    //初始化列表页主体部分，包括查询条件表单及数据表格等。
    var pageParam = {
        formId: "queryBuilderForm",
        tableId: "contentTable",
        editPage: {
            title: "批量修改表单",
            url: RP_GPMENU_EDIT
        },
        detailPage: {
            url: RP_GPMENU_DETAIL
        },
        addPage: {
            url: RP_GPMENU_ADD
        },
        deleteInterface: {
            url: RU_GPMENU_DELETE
        },
        deleteListInterface: {
            url: RU_GPMENU_DELETELIST
        },
        exportExcelInterface: {
            url: RU_GPMENU_EXPORTEXCEL
        }

    };

    //自动模糊查询begin
    var t = new Bloodhound({
        datumTokenizer: function(e) {
            return Bloodhound.tokenizers.whitespace(e.name)
        },
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        limit: 10,
        prefetch: {
            url: INTERFACE_SERVER + RU_GPPAGE_GETLISTBYJSONDATA + "?jsonData={page:{pageIndex:1,pageSize:1000}}",
            filter: function(e){
                return $.map(e.data, function(data) {
                    return {
                        name: data.url
                    }
                })
            }
        }
    });

    t.initialize();

    App.isRTL() && $(".typeahead").attr("dir", "rtl");
    $(".typeahead").typeahead(null, {
        name: "typeAhead",
        displayKey: "name",
        hint: !App.isRTL(),
        source: t.ttAdapter()
    });
    //自动模糊查询end

    var ajaxParam = {
        url: RU_GPMENU_GETLISTBYJSONDATA,
        type: "GET",
        submitData: {
            "entityRelated": {},
            "orderList": [{
                "columnName": "id",
                "isASC": true
            }],
            "page": {
                "pageIndex": DEFAULT_PAGE_INDEX,
                "pageSize": DEFAULT_PAGE_SIZE
            }
        },
        columnInfo: [

            {
                "columnName": "name",
                "columnText": "菜单名称",
                "style": "text-align:left",
                "linkFunction": function (event) {
                    var href = RP_GPMENU_DETAIL + "?" + RECORD_ID + "=" + event.id;
                    return href;
                },
            },
            {
                "columnName": "fartherName",
                "columnText": "上级菜单",
                "style": "text-align:left",
            },
            {
                "columnName": "pageUrl",
                "columnText": "链接页面",
                "style": "text-align:left",
                "width":"260px"
            },
            {
                "columnName": "priority",
                "columnText": "排序字段",
                "style": "text-align:left",
            }

        ]
    };

    var operationParam = [{
        "operationText": "修改",
        "buttonClass": "yellow",
        "iconClass": "fa fa-pencil-square-o",
        "clickFunction": function (event) {
            window.location.href = pageParam.editPage.url + "?" + RECORD_ID + "=" + event.data.id;
        }
    }, {
        "operationText": "删除",
        "buttonClass": "red",
        "iconClass": "fa fa-trash-o",
        "clickFunction": function (event) {
            layer.confirm('您确定要删除当前记录？', {
                btn: ['确定', '取消']
            }, function () {
                layer.closeAll('dialog');
                ajaxParam.submitData.page.pageSize = $("#pageSizeText").val();
                ajaxParam.submitData.page.pageIndex = $("#pageIndexHidden").val();
                pageParam.deleteInterface.url = RU_GPMENU_DELETE;
                pageParam.deleteInterface.type = "GET";
                pageParam.deleteInterface.submitData = {
                    "id": event.data.id,
                };
                deleteRecord(pageParam, ajaxParam, operationParam);
            });
        },
        "visibleFunction": function (recordData) {
            if (recordData.status == "1")
                return false;
            return false;
        }
    }];
    initQueryForm(pageParam, ajaxParam, operationParam);

});