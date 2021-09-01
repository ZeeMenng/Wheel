package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GprRoleInterface;
import com.zee.ent.generate.gp.GprRoleInterfaceGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类GprRoleInterfaceParameter，方法参数，自动生成。角色拥有的接口权限。
 */

public class GprRoleInterfaceParameter extends BaseParameter {

	@ApiModel(value = "GprRoleInterfaceAddList", description = "批量添加GprRoleInterface所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GprRoleInterface> entityList = new ArrayList<GprRoleInterface>();

		public ArrayList<GprRoleInterface> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GprRoleInterface> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GprRoleInterfaceDeleteByIdList", description = "批量删除GprRoleInterface所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GprRoleInterfaceDeleteByCompositeIdList", description = "批量删除GprRoleInterface所复合主键参数")
	public static class DeleteByCompositeIdList {

		@ApiModelProperty(value = "要删除的记录列表 ", required = false)
		private ArrayList<GprRoleInterface> entityList = new ArrayList<GprRoleInterface>();

		public ArrayList<GprRoleInterface> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GprRoleInterface> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GprRoleInterfaceUpdateList", description = "批量修改GprRoleInterface所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GprRoleInterface entity = new GprRoleInterface();

		public GprRoleInterface getEntity() {
			return entity;
		}

		public void setEntity(GprRoleInterface entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GprRoleInterfaceGetList", description = "模糊查询GprRoleInterface所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GprRoleInterfaceGetListEntityRelated", description = "模糊查询GprRoleInterface所需的参数，实体类相关。")
		public static class EntityRelated extends GprRoleInterfaceGenEnt {

		}
	}

}
