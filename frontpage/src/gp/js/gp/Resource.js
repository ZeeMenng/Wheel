/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/7/5 20:49:43
 * @description  文件信息。 相关页面的js方法。
 */

$(document).ready(function () {


    //初始化列表页主体部分，包括查询条件表单及数据表格等。
    var pageParam = {
        formId: "queryBuilderForm",
        tableId: "contentTable",
        editPage: {
            title: "批量修改表单",
            url: RP_GPRESOURCE_EDIT
        },
        detailPage: {
            url: RP_GPRESOURCE_DETAIL
        },
        addPage: {
            url: RP_GPRESOURCE_ADD
        },
        deleteInterface: {
            url: RU_GPRESOURCE_DELETE
        },
        deleteListInterface: {
            url: RU_GPRESOURCE_DELETELIST
        }

    };
    var ajaxParam = {
        url: RU_GPRESOURCE_GETLISTBYJSONDATA,
        type: "GET",
        submitData: {
            "entityRelated": {},
            "orderList": [{
                "columnName": "addTime",
                "isASC": false
            }],
            "page": {
                "pageIndex": DEFAULT_PAGE_INDEX,
                "pageSize": DEFAULT_PAGE_SIZE
            }
        },
        columnInfo: [
            {
                "columnName": "originalName",
                "columnText": "资源名称",
                "style": "text-align:left",
                "linkFunction": function (event) {
                    var href = RP_GPRESOURCE_DETAIL + "?" + RECORD_ID + "=" + event.id;
                    return href;
                },
            },
            {
                "columnName": "extension",
                "columnText": "资源类型",
                "style": "text-align:left",
            },
            {
                "columnName": "path",
                "columnText": "资源路径",
                "width": "300px",
                "style": "text-align:left",
            },
            {
                "columnName": "addTime",
                "columnText": "添加时间",
                "style": "text-align:left",
            }

        ]
    };

    var operationParam = [{
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
                pageParam.deleteInterface.url = RU_GPRESOURCE_DELETE;
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
            return true;
        }
    }];
    initQueryForm(pageParam, ajaxParam, operationParam);

});