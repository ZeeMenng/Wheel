package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GprUserOrganization;
import com.zee.ent.generate.gp.GprUserOrganizationGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类GprUserOrganizationParameter，方法参数，自动生成。用户所属组织机构。
 */

public class GprUserOrganizationParameter extends BaseParameter {

	@ApiModel(value = "GprUserOrganizationAddList", description = "批量添加GprUserOrganization所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GprUserOrganization> entityList = new ArrayList<GprUserOrganization>();

		public ArrayList<GprUserOrganization> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GprUserOrganization> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GprUserOrganizationDeleteByIdList", description = "批量删除GprUserOrganization所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GprUserOrganizationUpdateList", description = "批量修改GprUserOrganization所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GprUserOrganization entity = new GprUserOrganization();

		public GprUserOrganization getEntity() {
			return entity;
		}

		public void setEntity(GprUserOrganization entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GprUserOrganizationGetList", description = "模糊查询GprUserOrganization所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GprUserOrganizationGetListEntityRelated", description = "模糊查询GprUserOrganization所需的参数，实体类相关。")
		public static class EntityRelated extends GprUserOrganizationGenEnt{
        
		}
	}

}







