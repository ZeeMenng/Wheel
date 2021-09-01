package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GpModule;
import com.zee.ent.generate.gp.GpModuleGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类GpModuleParameter，方法参数，自动生成。功能模块。
 */

public class GpModuleParameter extends BaseParameter {

	@ApiModel(value = "GpModuleAddList", description = "批量添加GpModule所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GpModule> entityList = new ArrayList<GpModule>();

		@ApiModelProperty(value = "级联方式。0全部级联 1仅级联增 2仅级联删  3仅级联改 4不做级联", hidden = false, required = false)
		private Byte cascadeTypeCode;

		public ArrayList<GpModule> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GpModule> entityList) {
			this.entityList = entityList;
		}

		public Byte getCascadeTypeCode() {
			return cascadeTypeCode;
		}

		public void setCascadeTypeCode(Byte cascadeTypeCode) {
			this.cascadeTypeCode = cascadeTypeCode;
		}

	}

	@ApiModel(value = "GpModuleDeleteByIdList", description = "批量删除GpModule所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

		@ApiModelProperty(value = "级联方式。0全部级联 1仅级联增 2仅级联删  3仅级联改 4不做级联", hidden = false, required = false)
		private Byte cascadeTypeCode;

		public Byte getCascadeTypeCode() {
			return cascadeTypeCode;
		}

		public void setCascadeTypeCode(Byte cascadeTypeCode) {
			this.cascadeTypeCode = cascadeTypeCode;
		}

	}

	@ApiModel(value = "GpModuleUpdateList", description = "批量修改GpModule所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GpModule entity = new GpModule();

		public GpModule getEntity() {
			return entity;
		}

		public void setEntity(GpModule entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GpModuleGetList", description = "模糊查询GpModule所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GpModuleGetListEntityRelated", description = "模糊查询GpModule所需的参数，实体类相关。")
		public static class EntityRelated extends GpModuleGenEnt {

			@ApiModelProperty(value = "记录创建时间。查询起止时间。", required = false)
			private Date beginAddTime;

			@ApiModelProperty(value = "记录创建时间。查询结束时间。", required = false)
			private Date endAddTime;

			@ApiModelProperty(value = "记录最后一次修改时间。查询起止时间。", required = false)
			private Date beginUpdateTime;

			@ApiModelProperty(value = "记录最后一次修改时间。查询结束时间。", required = false)
			private Date endUpdateTime;

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

			public Date getBeginUpdateTime() {
				return this.beginUpdateTime;
			}

			public void setBeginUpdateTime(Date beginUpdateTime) {
				this.beginUpdateTime = beginUpdateTime;
			}

			public Date getEndUpdateTime() {
				return this.endUpdateTime;
			}

			public void setEndUpdateTime(Date endUpdateTime) {
				this.endUpdateTime = endUpdateTime;
			}

		}
	}

}
