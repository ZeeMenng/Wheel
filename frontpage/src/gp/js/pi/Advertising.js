/**
 * @author Zee
 * @createDate 2018/01/16 01:48:00
 * @updateDate 2018/7/12 14:46:55
 * @description  CMS广告表 相关页面的js方法。
 */

$(document).ready(function () {


    //初始化列表页主体部分，包括查询条件表单及数据表格等。
    var pageParam = {
        formId: "queryBuilderForm",
        tableId: "contentTable",
        editPage: {
            title: "批量修改表单",
            url: RP_PIADVERTISING_EDIT
        },
        detailPage: {
            url: RP_PIADVERTISING_DETAIL
        },
        addPage: {
            url: RP_PIADVERTISING_ADD
        },
        deleteInterface: {
            url: RU_PIADVERTISING_DELETE
        },
        deleteListInterface: {
            url: RU_PIADVERTISING_DELETELIST
        }

    };
    var ajaxParam = {
        url: RU_PIADVERTISING_GETLISTBYJSONDATA,
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
                "columnName": "name",
                "columnText": "广告名称",
                "style": "text-align:left",
                "linkFunction": function (event) {
                    var href = RP_PIADVERTISING_DETAIL + "?" + RECORD_ID + "=" + event.id;
                    return href;
                }
            },
            {
                "columnName": "resourcePath",
                "columnText": "资源路径",
                "style": "text-align:left",
                "width": "300px"
            },
            {
                "columnName": "addTime",
                "columnText": "添加时间",
                "style": "text-align:left",
            },

            {
                "columnName": "updateTime",
                "columnText": "修改时间",
                "style": "text-align:left",
            },

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
                pageParam.deleteInterface.url = RU_PIADVERTISING_DELETE;
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


function initAddFileInput() {
    //初始化上传控件的样式
    var $Control = $("#fileResourceIds").fileinput({
        language: 'zh',
        theme: 'fa',
        showRemove: false,
        showZoom: false,
        showDrag: false,
        showUpload: false,
        showCaption: false,
        ajaxSettings: {
            headers: {
                'Authorization': "Bearer " + JSON.parse(localStorage.getItem("token")).accessToken
            }
        },
        uploadUrl: INTERFACE_SERVER + "/extend/swagger/gp/gpResource/saveUploadFile",
        uploadAsync: true,
        browseClass: "btn btn-primary btn-lg",
        fileType: "image",
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        overwriteInitial: false,
        initialPreviewAsData: true

    });
    initFileInput($Control);
}


function initEditFileInput(resourceIdArray, resourcePathArray) {
    var initialPreviewConfigArray = [];
    for (var i = 0; i < resourceIdArray.length; i++) {
        initialPreviewConfigArray[i] = {url: INTERFACE_SERVER + RU_GPRESOURCE_GETMODELBYPATH + resourceIdArray[i]};
    }

    var $Control = $("#fileResourceIds").fileinput({
        language: 'zh',
        theme: 'fa',
        showRemove: false,
        showZoom: false,
        showDrag: false,
        showUpload: false,
        showCaption: false,
        ajaxSettings: {
            headers: {
                'Authorization': "Bearer " + JSON.parse(localStorage.getItem("token")).accessToken
            }
        },
        uploadUrl: INTERFACE_SERVER + "/extend/swagger/gp/gpResource/saveUploadFile",
        uploadAsync: true,
        browseClass: "btn btn-primary btn-lg",
        fileType: "image",
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        overwriteInitial: false,
        initialPreviewAsData: true,
        initialPreview: resourcePathArray,
        initialPreviewConfig: initialPreviewConfigArray
    });
    initFileInput($Control);
}
