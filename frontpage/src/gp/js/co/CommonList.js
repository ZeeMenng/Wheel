var selectRows = new Array();
var tableLayout;
Array.prototype.prepend = function(needle) {
	var a = this.slice(0);
	// 使用unshift方法向a开头添加item
	a.unshift(needle);
	return a;
}

$(document).ready(function() {
	// 列表页表格样式，默认超出为省略号，不出现横向滚动条
	
	if (getUserConfigByCode("tableLayout") != null) {
		var tableLayout =getUserConfigByCode("tableLayout").configValue;
		if (tableLayout) {
			if(tableLayout==="fixed")
				$(".table-scrollable").removeClass("table-scrollable");
			else
				$("#contentTable").css("table-layout", tableLayout)
		}
	}
	
	// 增加刷新后全选有否判断，非全选时父checkbox不选中
	var checked_count = 0;
	var num = 0;
	num = $("#contentTable input[name='childCheckbox']").length;
	$("#contentTable input[name='childCheckbox']").each(function(i, n) {
		var recordId = $(this).closest("tr").attr("id");
		if ($(this).get(0).checked == true) {
			checked_count++;
		}
	});
	if (num > checked_count) {
		$("#contentTable input[name='headerCheckbox']").attr("checked", false);
	} else if (num == checked_count) {

		$("#contentTable input[name='headerCheckbox']").prop('checked', true);
	}
	initPageSizeSelect();
	initCheckbox();
});


/**
 * Zee 初始化表单按钮事件，并执行一次查询操作
 * 
 * @param pageParam
 *            页面标签参数
 * @param ajaxParam
 *            ajax参数
 * @param operationParam
 *            动作参数
 */
var DEFAULT_ORDER_LIST;
function initQueryForm(pageParam, ajaxParam, operationParam) {
	DEFAULT_ORDER_LIST=ajaxParam.submitData.orderList;
    $("#" + pageParam.formId).unbind("submit");
    $("#" + pageParam.formId).submit(function (e) {
        e.preventDefault();
        // 执行查询动作要重新初始化pageIndex和pageSize
        ajaxParam.submitData.pageIndex = DEFAULT_PAGE_INDEX;
        ajaxParam.submitData.pageSize = DEFAULT_PAGE_SIZE;
        return executeQuery(pageParam, ajaxParam, operationParam);
    });
    $("#resetButton").click(function (e) {
        e.preventDefault();
        $("#" + pageParam.formId)[0].reset();
        $("input[type='hidden']").val("");
        $(".selectpicker").selectpicker({
            noneSelectedText: '请选择，可多选……',
            width: ''
        });
        $(".selectpicker").selectpicker("refresh");
        selectRows = new Array();
        // 执行查询动作要重新初始化pageIndex和pageSize
        ajaxParam.submitData.pageIndex = DEFAULT_PAGE_INDEX;
        ajaxParam.submitData.pageSize = DEFAULT_PAGE_SIZE;
        ajaxParam.submitData.orderList=DEFAULT_ORDER_LIST;
        return executeQuery(pageParam, ajaxParam, operationParam);
    });
    $("#" + pageParam.formId).submit();
}

$("#submitButton").click(function (e) {
    initQueryForm(pageParam, ajaxParam, operationParam);
});

/**
 * Zee 执行表单查询动作
 * 
 * @param pageParam
 *            页面标签参数
 * @param ajaxParam
 *            ajax参数
 * @param operationParam
 *            动作参数
 */
function executeQuery(pageParam, ajaxParam, operationParam) {
    var formData = $("#" + pageParam.formId).serializeArray();
    // 将查询条件和其它请求参数组装
    if (ajaxParam.submitData != null)
        $.each(formData, function (i, n) {
            ajaxParam.submitData.entityRelated[formData[i].name] = formData[i].value;
        });
    initNewGrid(pageParam, ajaxParam, operationParam);
}

/**
 * Zee 初始化数据表格
 * 
 * @param pageParam
 *            页面标签参数
 * @param ajaxParam
 *            ajax参数
 * @param operationParam
 *            动作参数
 */
function initNewGrid(pageParam, ajaxParam, operationParam) {
    $("#pageSizeSelect").val(ajaxParam.submitData.pageSize);
    $("#" + pageParam.tableId).empty();
    // 初始化表头部分
    var header = "<thead><tr>";
    header += "<th class='table-checkbox'  style='width:50px;'>";
    header += "<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>";
    header += "<input name='headerCheckbox' type='checkbox' class='group-checkable' />";
    header += "<span></span></label></th><th style='width:30px;'>序号</th>";

    for (var i = 0; i < ajaxParam.columnInfo.length; i++) {
        if (typeof (ajaxParam.columnInfo[i].width) != "undefined"&&(tableLayout==="fixed"))
            header += "<th class='sorting' sortBy='' columnName='" + ajaxParam.columnInfo[i].columnName + "' width='" + ajaxParam.columnInfo[i].width + "'><span>" + ajaxParam.columnInfo[i].columnText + "</span></th>";
        else
            header += "<th class='sorting' sortBy='' columnName='" + ajaxParam.columnInfo[i].columnName + "'><span>" + ajaxParam.columnInfo[i].columnText + "</span></th>";
    }


    if (operationParam != null)
        operationParam.length == 0 ? header += "</tr></thead>" : header += "<th style='min-width:105px;'>操作</th></tr></thead>";

    if(ajaxParam.submitData.orderList!=null&&ajaxParam.submitData.orderList.length!=0)
    	for (var i = 0; i < ajaxParam.submitData.orderList.length; i++) {
    		var sortby;
    		var thClass;
    		if(ajaxParam.submitData.orderList[i].isASC){
				thClass="sorting_asc";
    			 sortby="asc";
    		}
    			else{
    				thClass="sorting_desc";
    				sortby="desc";
    			}
    		header=$(header);
    		header.find("th[columnName='"+ajaxParam.submitData.orderList[i].columnName+"']").attr("class",thClass);
    		header.find("th[columnName='"+ajaxParam.submitData.orderList[i].columnName+"']" ).attr("sortby",sortby);
    	}
    
    $("#" + pageParam.tableId).append(header);

    var orderListArray = new Array();
    $("#" + pageParam.tableId + " th").unbind("click");
    // 如果没有操作按钮，所有列均可有点击排序，如果有操作按钮最后一列不能排序
    var $sortTh= $("#" + pageParam.tableId + " th:gt(1):not(:last)");
   if(operationParam!=null&&operationParam.length==0)
	   $sortTh= $("#" + pageParam.tableId + " th:gt(1)");
   $sortTh.click(function () {
        var orderColumn = {
            "isASC": null,
            "columnName": $(this).attr("columnName")
        };

        if ($(this).attr("class") == "sorting") {
            $(this).attr("class", "sorting_asc");
            $(this).attr("sortBy", "asc")
            orderColumn.isASC = true;
        } else if ($(this).attr("class") == "sorting_asc") {
            $(this).attr("class", "sorting_desc");
            $(this).attr("sortBy", "desc")
            orderColumn.isASC = false;
        } else {
            $(this).attr("class", "sorting");
            $(this).attr("sortBy", null);
        }

        if (containsColumn(orderListArray, orderColumn) >= 0)
            orderListArray.splice(containsColumn(orderListArray, orderColumn), 1);
        if (orderColumn.isASC != null)
            orderListArray = orderListArray.prepend(orderColumn);

        $("#" + pageParam.tableId + " th").each(function () {
            if ($(this).attr("sortBy") == null || $(this).attr("sortBy") == '')
                return true;

        });

        ajaxParam.submitData.orderList = orderListArray;
        ajaxParam.submitData.pageIndex = DEFAULT_PAGE_INDEX;
        ajaxParam.submitData.pageSize = DEFAULT_PAGE_SIZE;
        if ($("#pageSizeSelect").val()) {
            ajaxParam.submitData.pageSize = $("#pageSizeSelect").val();
        }

        pageClick(pageParam, ajaxParam, operationParam);
    });

    pageClick(pageParam, ajaxParam, operationParam);
    bindPageButton(pageParam, ajaxParam, operationParam);
    initTopRightButton(pageParam, ajaxParam, operationParam);
    
 // 只有单元格超长时才显示title信息
    $('#contentTable thead tr th span').each(function (i,n) {
    	var tdWidth=window.getComputedStyle(n).width;
    	var tdParentWidth=window.getComputedStyle(n.parentElement).width;
    	  if (parseFloat(tdWidth) === parseFloat(tdParentWidth)) {
    		  $(n).attr('title', n.textContent);
    	  }
    	});
    
}


/**
 * Zee 分页代码
 * 
 * @param pageParam
 *            页面标签参数
 * @param ajaxParam
 *            ajax参数
 * @param operationParam
 *            动作参数
 */
function pageClick(pageParam, ajaxParam, operationParam) {
    var pageSize = ajaxParam.submitData.pageSize;
    var pageIndex = ajaxParam.submitData.pageIndex;
    $("#pageSizeSelect").val(pageSize);
    $("#pageIndexHidden").val(pageIndex);

    var total = 0;
    var pageCount = 0;

    ajaxParam.submitData.page = {
        "pageIndex": pageIndex,
        "pageSize": pageSize
    };

    var submitDataString = JSON.stringify(ajaxParam.submitData);

    // 此处应该是同步请求才精确，否则无法保证pageCountText始终有正确的值，这个稍后改
    var ajaxParamter = {
        "url": ajaxParam.url,
        "type": ajaxParam.type,
        "data": "jsonData=" + submitDataString,
        "async": true,
        "success": function (message) {
            total = message.totalCount;

            
            var data = message.data;
            pageIndex = parseInt(pageIndex);

            $("#" + pageParam.tableId + " tr").not($("#" + pageParam.tableId + " tr")[0]).remove();

            if (total == null || total == 0) {
                $("#contentTable input[name='headerCheckbox']").get(0).checked = false;
                noResult(pageParam,ajaxParam);
                return;
            } else if (data.length == 0) {
                if (pageIndex > 1) {
                    ajaxParam.submitData.pageIndex--;
                    pageClick(pageParam, ajaxParam, operationParam);
                    return;
                } else {
                    noResult(pageParam,ajaxParam);
                    return;
                }
            }
            $("#" + pageParam.tableId).append("<tbody");
            $.each(data, function (a, n) {

                var row = "<tr> class='odd gradeX'";
                row += "<td><label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>";
                row += "<input name='childCheckbox' type='checkbox' class='checkboxes' value='1' />";
                row += "<span></span></label></td>";
                row += "<td>";

                row += ((pageIndex - 1) * pageSize + a + 1);
                row += "</td>";
                var col = "";
                var trId = ""// 单一记录主键

                for (var i = 0; i < ajaxParam.columnInfo.length; i++) {
                    col = ajaxParam.columnInfo[i].columnName;

                    row += "<td";
                    ajaxParam.columnInfo[i].class == null ? row += "" : row += " class='" + ajaxParam.columnInfo[i].class + "'";
                    ajaxParam.columnInfo[i].style == null ? row += "" : row += " style='" + ajaxParam.columnInfo[i].style + "'";
                    
                    row += "><span>";
                    if (n[col] == null) {
                        row += "</span></td>";
                        continue;
                    }
                    if (ajaxParam.columnInfo[i].linkFunction != null)
                        row += "<a style='color:#337ab7;' href='" + ajaxParam.columnInfo[i].linkFunction(n) + "'>";
                    if (ajaxParam.columnInfo[i].bgcolorFunction != null)
                        row += "<span class='" + ajaxParam.columnInfo[i].bgcolorFunction(n) + "'>";

                    row += n[col];
                    if (ajaxParam.columnInfo[i].linkFunction != null)
                        row += "</a>";
                    if (ajaxParam.columnInfo[i].bgcolorFunction != null)
                        row += "</span>";
                    row += "</td>";
                }

                // 第一次遍历，插入操作按钮
                if (operationParam != null && operationParam.length != 0) {
                    row += "<td><span>&nbsp;&nbsp;"
                    $.each(operationParam, function (b, m) {
                        // 如果visibleFunction方法参数返回的是false，则不显示操作按钮
                        if (m.visibleFunction != null)
                            if (!m.visibleFunction(n))
                                return true;
                        row += "<button class='btn default btn-xs ";
                        if (m.buttonClass != undefined)
                            row += m.buttonClass;

                        row += "' id='" + m.operationText + "'>";

                        row += "<i class=' ";
                        if (m.iconClass != undefined)
                            row += m.iconClass;
                        row += "'></i>";

                        row += m.operationText;
                        row += "</button>&nbsp;&nbsp;";

                    });
                    row += "</span></td></tr>";

                    row = $(row);
                    // 兼容之前的获取记录主键写法
                    n.recordId = n["id"];
                    // 第二次遍历，给操作按钮添加相应事件，把整条记录的所有参数返回
                    $.each(operationParam, function (b, m) {
                        row.find("#" + m.operationText).unbind("click");
                        row.find("#" + m.operationText).click(n, m.clickFunction);
                    });

                }
                row = $(row);
                row.attr("id", n["id"]);
                // 行头的checkbox是否选中
                if ($.inArray(n["id"], selectRows) == -1)
                    row.find("input[type='checkbox']").get(0).checked = false;
                else
                    row.find("input[type='checkbox']").get(0).checked = true;

                $("#" + pageParam.tableId).append(row);
            });
            $("#" + pageParam.tableId).append("</tbody>");
            if (pageSize == 0)
                pageSize = total;
            if (total % pageSize == 0)
                pageCount = total / pageSize;
            else
                pageCount = parseInt(total / pageSize) + 1;
            if ($("#gotoPageText").val() != pageIndex) {
                $("#gotoPageText").val(null);
            }
            $("#pageCountHidden").val(pageCount);
            $("#totalCountSpan").text(total);
            $("#pageIndexSpan").text(pageIndex + "/" + pageCount);
            // 判断列头的复选框是否被选择,如果当前页所有的列都被选择,则复选框处于选中状态
            var isSelectAll = true;
            if ($("#contentTable input[name='childCheckbox']").length == 0)// 如果一条数据都没有，也不能选中
                isSelectAll = false;
            else
                $("#contentTable input[name='childCheckbox']").each(function (i, n) {
                    var recordId = $(n).closest("tr").attr("id");
                    if ($.inArray(recordId, selectRows) == -1) {
                        isSelectAll = false;
                        return false;
                    }
                    return true;
                });
            if (isSelectAll)
                $("#contentTable input[name='headerCheckbox']").get(0).checked = true;
            else
                $("#contentTable input[name='headerCheckbox']").get(0).checked = false;

// 只有单元格超长时才显示title信息
            $('#contentTable tbody tr td span').each(function (i,n) {
            	var tdWidth=window.getComputedStyle(n).width;
            	var tdParentWidth=window.getComputedStyle(n.parentElement).width;
            	  if (parseFloat(tdWidth) === parseFloat(tdParentWidth)) {
            		  $(n).attr('title', n.textContent);
            	  }
            	});
            
            return false;
        },

    };

    universalAjax(ajaxParamter);

}

function containsColumn(array, item) {
    for (var i in array)
        if (array[i].columnName == item.columnName)
            return i

    return -1;
}



/**
 * Zee 初始化分页按钮事件
 * 
 * @param pageParam
 *            页面标签参数
 * @param ajaxParam
 *            ajax参数
 * @param operationParam
 *            动作参数
 */
function bindPageButton(pageParam, ajaxParam, operationParam) {

    $("#firstA").unbind("click");
    $("#prviousA").unbind("click");
    $("#nextA").unbind("click");
    $("#lastA").unbind("click");
    $("#gotoPageA").unbind("click");

    $("#gotoPageText").unbind("change");
    $("#pageSizeSelect").unbind("change");

    var pageSize = ajaxParam.submitData.pageSize;
    var pageIndex = ajaxParam.submitData.pageIndex;

    // 第一页
    $("#firstA").click(function () {
        if (ajaxParam.submitData.pageIndex != 1) {
            ajaxParam.submitData.pageIndex = 1;
            pageClick(pageParam, ajaxParam, operationParam);
        }
    });

    // 上一页
    $("#prviousA").click(function () {
        if (ajaxParam.submitData.pageIndex != 1) {
            ajaxParam.submitData.pageIndex--;
            pageClick(pageParam, ajaxParam, operationParam);
        }
    });

    // 最后一页
    $("#lastA").click(function () {
        var pageCount = $("#pageCountHidden").val();
        if (ajaxParam.submitData.pageIndex != pageCount) {
            ajaxParam.submitData.pageIndex = pageCount;
            pageClick(pageParam, ajaxParam, operationParam);
        }
    });

    // 下一页
    $("#nextA").click(function () {
        var pageCount = $("#pageCountHidden").val();
        if (ajaxParam.submitData.pageIndex != pageCount) {
            ajaxParam.submitData.pageIndex++;
            pageClick(pageParam, ajaxParam, operationParam);
        }
    });

    $("#pageSizeSelect").change(function () {

        ajaxParam.submitData.pageSize = $("#pageSizeSelect").val();
        ajaxParam.submitData.pageIndex = DEFAULT_PAGE_INDEX;

        var userConfig = getUserConfigByCode("pageSize");
        userConfig.configValue = ajaxParam.submitData.pageSize
        updateUserConfig(userConfig);

        pageClick(pageParam, ajaxParam, operationParam);
    });

    // 跳转到
    $("#gotoPageText").keyup(function () {
        // if (event.keyCode == 13)
        gotoPage(pageParam, ajaxParam, operationParam);
    });
    $("#gotoPageButton").click(function () {
        gotoPage(pageParam, ajaxParam, operationParam);
    });

    function gotoPage(pageParam, ajaxParam, operationParam) {
        var pageCount = $("#pageCountHidden").val();
        var gotoPage = $("#gotoPageText").val();

        var r = /^[0-9]*[1-9][0-9]*$/

        if (!r.test(gotoPage)) {
            return false;
        }
        if (eval(gotoPage) < 1 || eval(gotoPage) > pageCount) {
            return false;
        }

        var intGotoPage = parseInt(gotoPage);
        ajaxParam.submitData.pageIndex = intGotoPage;
        pageClick(pageParam, ajaxParam, operationParam);
        return false;
    }
}

function initTopRightButton(pageParam, ajaxParam, operationParam) {
    // 处理表格右上角的通用按键单击事件
    $("#addButton").unbind("click");
    $("#addButton").click(function () {
        window.location.href = pageParam.addPage.url;
    });
    $("#batchDeleteButton").unbind("click");
    $("#batchDeleteButton").click(function () {
        if (selectRows.length == 0) {
            layer.alert('请选择要删除的记录！', {
                icon: 6
            });
            return false;
        }

        layer.confirm('您确定要删除这' + selectRows.length + '条记录？', {
            btn: ['确定', '取消']
        }, function () {
            layer.closeAll('dialog');
            ajaxParam.submitData.page.pageSize = $("#pageSizeText").val();
            ajaxParam.submitData.page.pageIndex = $("#pageIndexHidden").val();
            pageParam.deleteListInterface.type = "POST";
            pageParam.deleteListInterface.submitData = {
                idList: selectRows
            };

            deleteRecordList(pageParam, ajaxParam, operationParam);

            return false;
        });

    });
    $("#batchEditButton").unbind("click");
    $("#batchEditButton").click(function () {
        if (selectRows.length == 0) {
            layer.alert('请选择要修改的记录！', {
                icon: 6
            });
            return false;
        }

        var date = new Date();
        date.setTime(date.getTime() + (1 * 24 * 60 * 60 * 1000));
        
      
    	var cookieData={
    			item:"selectRows",
    			data: selectRows,
    			path:'/'
    	};
    	
    	setCookies(cookieData);
       
        pageParam.editPage.selectRows = selectRows;

        popUpPage(pageParam.editPage);
    });
    $("#batchExportButton").unbind("click");
    $("#batchExportButton").click(function () {

        var excelJsonData = {
            "columnInfo": ajaxParam.columnInfo,
            "selectRows": selectRows
        };

        $.extend(excelJsonData, ajaxParam.submitData);
        if (selectRows.length == 0)
            layer.msg('未选择要导出的记录，默认导出当前页……', {
                time: 1500
            });
        // 如果有选择删除分页信息，默认导出最大分页值，由后台常量控制SQLQUERY_KEYWORDS_PAGESIZE_MAX
        else
            delete excelJsonData.page;

        location.href = INTERFACE_SERVER + pageParam.exportExcelInterface.url + "?jsonData=" + JSON.stringify(excelJsonData);
    });

}

function initCheckbox() {

	// 设置单条记录的checkbox单击事件
	$("#contentTable").on("click", "input[name='childCheckbox']", function(event) {
		var recordId = $(this).closest("tr").attr("id");
		// 选中记录，则将记录添加到数组中
		if ($(this).get(0).checked) {
			if ($.inArray(recordId, selectRows) == -1)
				selectRows.push(recordId);
		} else {// 取消选中，则删除数组中的记录
			if ($.inArray(recordId, selectRows) != -1)
				selectRows.splice($.inArray(recordId, selectRows), 1);
		}
	});

	$("#contentTable").on("click", "input[name='headerCheckbox']", function(event) {
		if ($(this).get(0).checked) {
			$("#contentTable input[name='childCheckbox']").each(function(i, n) {
				var recordId = $(this).closest("tr").attr("id");
				$(this).get(0).checked = true;
				if ($.inArray(recordId, selectRows) == -1) {
					selectRows.push(recordId);
				}
			});
		} else {
			$("#contentTable input[name='childCheckbox']").each(function(i, n) {
				var recordId = $(this).closest("tr").attr("id");
				$(this).get(0).checked = false;
				if ($.inArray(recordId, selectRows) != -1) {
					selectRows.splice($.inArray(recordId, selectRows), 1);
				}
			});

		}
	});
	// 增加非全选判断，非全选时父checkbox不选中
	$("#contentTable").on("click", "input[name='childCheckbox']", function(event) {
		/*
		 * if ($("#contentTable input[name='headerCheckbox']").is(':checked')) {
		 * $("#contentTable
		 * input[name='headerCheckbox']").attr("checked",false); }
		 */
		var checked_count = 0;
		var num = 0;
		num = $("#contentTable input[name='childCheckbox']").length;
		$("#contentTable input[name='childCheckbox']").each(function(i, n) {
			var recordId = $(this).closest("tr").attr("id");
			if ($(this).get(0).checked == true) {
				checked_count++;
			}
		});
		if (num > checked_count) {
			$("#contentTable input[name='headerCheckbox']").attr("checked", false);
		} else if (num == checked_count) {
			$("#contentTable input[name='headerCheckbox']").prop('checked', true);
		}
	});

}

/**
 * Zee 删除指定数据
 * 
 * @param ajaxParam
 *            ajax参数
 */
function deleteRecord(pageParam, ajaxParam, operationParam) {

    var type = pageParam.deleteInterface.type;
    var submitData = pageParam.deleteInterface.submitData;
    var url = pageParam.deleteInterface.url;
    if (type == null)
        type = "GET";

    var ajaxParamter = {
        "url": url,
        "data": submitData,
        "type": type,
        "async": true,
        "success": function (resultData) {
            if (!resultData["isSuccess"]) {
                layer.alert(resultData["resultMessage"], {
                    icon: 6
                });
                return false;
            }

            // 一旦数据删除成功，就从数组中移除相应记录的ID
            if (pageParam.deleteInterface.submitData.id != null && $.inArray(pageParam.deleteInterface.submitData.id, selectRows) != -1)
                selectRows.splice($.inArray(pageParam.deleteInterface.submitData.id, selectRows), 1);
            if (pageParam.deleteInterface.submitData.idList != null) {
                var idList = pageParam.deleteInterface.submitData.idList;
                // 将数组中的的记录数单独存下来，如果一边遍历，一边删除，怎么也删除不干净的。专门写了处理这种情况的算法
                var arrayLength = selectRows.length;
                var preId;
                var i = 0;
                while (i < arrayLength) {
                    preId = idList[i];
                    if ($.inArray(preId, selectRows) != -1)
                        selectRows.splice($.inArray(preId, selectRows), 1);
                    arrayLength = selectRows.length;
                    // 说明数组中已经移除相应数据了
                    if (selectRows[i] != preId) {
                        if (i == 0)
                            continue;
                        else
                            i--;
                    } else {
                        i++;
                    }
                }

            }
            pageClick(pageParam, ajaxParam, operationParam);
        }
    };

    universalAjax(ajaxParamter);

}

function deleteRecordList(pageParam, ajaxParam, operationParam) {
    var type = pageParam.deleteListInterface.type;
    var submitData = pageParam.deleteListInterface.submitData;
    var url = pageParam.deleteListInterface.url;

    if (type.toUpperCase() == "POST" && submitData != null) {
        url = pageParam.deleteListInterface.url;
        submitData = JSON.stringify(submitData);
    }

    var ajaxParamter = {
        "url": url,
        "data": submitData,
        "type": type,
        "async": true,
        "success": function (resultData) {
            if (!resultData["isSuccess"]) {
                layer.alert(resultData["resultMessage"], {
                    icon: 6
                });
                return false;
            }

            // 一旦数据删除成功，就从数组中移除相应记录的ID
            if (pageParam.deleteListInterface.submitData.id != null && $.inArray(pageParam.deleteListInterface.submitData.id, selectRows) != -1)
                selectRows.splice($.inArray(pageParam.deleteListInterface.submitData.id, selectRows), 1);
            if (pageParam.deleteListInterface.submitData.idList != null) {
                var idList = pageParam.deleteListInterface.submitData.idList;
                // 将数组中的的记录数单独存下来，如果一边遍历，一边删除，怎么也删除不干净的。专门写了处理这种情况的算法
                var arrayLength = selectRows.length;
                var preId;
                var i = 0;
                while (i < arrayLength) {
                    preId = idList[i];
                    if ($.inArray(preId, selectRows) != -1)
                        selectRows.splice($.inArray(preId, selectRows), 1);
                    arrayLength = selectRows.length;
                    // 说明数组中已经移除相应数据了
                    if (selectRows[i] != preId) {
                        if (i == 0)
                            continue;
                        else
                            i--;
                    } else {
                        i++;
                    }
                }

            }
            pageClick(pageParam, ajaxParam, operationParam);
        }
    };

    universalAjax(ajaxParamter);

}


function initPageSizeSelect() {
	var selectData = [ {
		value : 5,
		text : 5
	}, {
		value : 10,
		text : 10
	}, {
		value : 15,
		text : 15
	}, {
		value : 30,
		text : 30
	}, {
		value : 50,
		text : 50
	},
	// {
	// value: 'All',
	// text: '所有'
	// }
	];

	$.each(selectData, function(i, n) {
		$("#pageSizeSelect").append("<option value='" + n["value"] + "'>" + n["text"] + "</option>");
	});

}


function noResult(pageParam,ajaxParam) {
	$("#totalCountSpan").text(0);
	$("#pageIndexSpan").text(0 + "/" + 0);
	

	var noResultRow="<tr class='odd'><td valign='top' colspan='"+(ajaxParam.columnInfo.length+3)+"' class='dataTables_empty'>没有合适的数据……</td></tr>";
	$("#"+pageParam.tableId).append(noResultRow);
    
	
}