package com.zee.ent.parameter.base;

import java.io.Serializable;
import java.util.ArrayList;

import com.zee.ent.base.BaseEnt;
import com.zee.set.symbolic.CustomSymbolic;

import io.swagger.annotations.ApiModelProperty;

public class BaseParameter extends BaseEnt implements Serializable {
	private static final long serialVersionUID = 1L;

	public static class BaseParamAddList {

	}

	public static class BaseParamDeleteByIdList extends BaseParameter {

		@ApiModelProperty(value = "要删除记录的主键列表 ", required = false)
		private ArrayList<String> idList = new ArrayList<String>();

		public ArrayList<String> getIdList() {
			return idList;
		}

		public void setIdList(ArrayList<String> idList) {
			this.idList = idList;
		}

	}

	public static class BaseParamUpdateList {

		@ApiModelProperty(value = "要修改记录的主键列表 ", required = false)
		private ArrayList<String> idList = new ArrayList<String>();

		public ArrayList<String> getIdList() {
			return idList;
		}

		public void setIdList(ArrayList<String> idList) {
			this.idList = idList;
		}

	}

	public static class BaseParamGetList {

		@ApiModelProperty(value = "分页相关的查询条件 ", hidden = false, required = false)
		private Page page = new Page();
		@ApiModelProperty(value = "排序相关的查询条件 ", required = false)
		private ArrayList<Order> orderList = new ArrayList<Order>();

		public Page getPage() {
			return page;
		}

		public void setPage(Page page) {
			this.page = page;
		}

		public ArrayList<Order> getOrderList() {
			return orderList;
		}

		public void setOrderList(ArrayList<Order> orderList) {
			this.orderList = orderList;
		}

		public static class Page {

			@ApiModelProperty(value = "分页大小，默认为15条", allowableValues = "15", required = false)
			private Integer pageSize = CustomSymbolic.SQLQUERY_PAGESIZE_DEFAULTVALUE;
			@ApiModelProperty(value = "查询页码，默认为第1页", allowableValues = "1", required = false)
			private Integer pageIndex = CustomSymbolic.SQLQUERY_PAGEINDEX_DEFAULTVALUE;

			public Integer getPageSize() {
				return pageSize;
			}

			public void setPageSize(Integer pageSize) {
				this.pageSize = pageSize;
			}

			public Integer getPageIndex() {
				return pageIndex;
			}

			public void setPageIndex(Integer pageIndex) {
				this.pageIndex = pageIndex;
			}

		}

		public static class Order {

			@ApiModelProperty(value = "排序字段", allowableValues = "id", required = false)
			private String columnName;
			@ApiModelProperty(value = "是否正序排列", allowableValues = "false", required = false)
			private Boolean isASC;

			public String getColumnName() {
				return columnName;
			}

			public void setColumnName(String columnName) {
				this.columnName = columnName;
			}

			public Boolean getIsASC() {
				return isASC;
			}

			public void setIsASC(Boolean isASC) {
				this.isASC = isASC;
			}

		}

	}

}