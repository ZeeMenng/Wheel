package com.zee.ent.parameter.gp;

import java.util.ArrayList;

import com.zee.ent.extend.gp.GprResource;
import com.zee.ent.generate.gp.GprResourceGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2020/7/4 16:32:20
 * @description 实体类GprResourceParameter，方法参数，自动生成。附件关联表。只要存有附件字段的表，都会通过此表于gp_resource表关联。
 */

public class GprResourceParameter extends BaseParameter {

	@ApiModel(value = "GprResourceAddList", description = "批量添加GprResource所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GprResource> entityList = new ArrayList<GprResource>();

		public ArrayList<GprResource> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GprResource> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GprResourceDeleteByIdList", description = "批量删除GprResource所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GprResourceUpdateList", description = "批量修改GprResource所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GprResource entity = new GprResource();

		public GprResource getEntity() {
			return entity;
		}

		public void setEntity(GprResource entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GprResourceGetList", description = "模糊查询GprResource所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GprResourceGetListEntityRelated", description = "模糊查询GprResource所需的参数，实体类相关。")
		public static class EntityRelated extends GprResourceGenEnt {

		}
	}

}
