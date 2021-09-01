package com.zee.ent.parameter.gp;

import java.util.ArrayList;
import java.util.Date;

import com.zee.ent.extend.gp.GpValueLocation;
import com.zee.ent.generate.gp.GpValueLocationGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/2/3 14:28:06
 * @description 实体类GpValueLocationParameter，方法参数，自动生成。调用存储过程查询某个值在本数据库中的位置，记录相关信息到本表中。
 */

public class GpValueLocationParameter extends BaseParameter {

	@ApiModel(value = "GpValueLocationAddList", description = "批量添加GpValueLocation所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GpValueLocation> entityList = new ArrayList<GpValueLocation>();

		public ArrayList<GpValueLocation> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GpValueLocation> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GpValueLocationDeleteByIdList", description = "批量删除GpValueLocation所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GpValueLocationUpdateList", description = "批量修改GpValueLocation所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GpValueLocation entity = new GpValueLocation();

		public GpValueLocation getEntity() {
			return entity;
		}

		public void setEntity(GpValueLocation entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GpValueLocationGetList", description = "模糊查询GpValueLocation所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GpValueLocationGetListEntityRelated", description = "模糊查询GpValueLocation所需的参数，实体类相关。")
		public static class EntityRelated extends GpValueLocationGenEnt {

			@ApiModelProperty(value = "添加时间查询起止时间。", required = false)
			private Date beginAddTime;

			@ApiModelProperty(value = "添加时间查询结束时间。", required = false)
			private Date endAddTime;

			public Date getBeginAddTime() {
				return this.beginAddTime;
			}

			public void setBeginAddTime(Date beginAddTime) {
				this.beginAddTime = beginAddTime;
			}

			public Date getEndAddTime() {
				return this.endAddTime;
			}

			public void setEndAddTime(Date endAddTime) {
				this.endAddTime = endAddTime;
			}

		}
	}

}
