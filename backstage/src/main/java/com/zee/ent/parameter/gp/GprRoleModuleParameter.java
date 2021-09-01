package com.zee.ent.parameter.gp;

import java.util.ArrayList;

import com.zee.ent.extend.gp.GprRoleModule;
import com.zee.ent.generate.gp.GprRoleModuleGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类GprRoleModuleParameter，方法参数，自动生成。角色拥有的功能模块权限。
 */

public class GprRoleModuleParameter extends BaseParameter {

	@ApiModel(value = "GprRoleModuleAddList", description = "批量添加GprRoleModule所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GprRoleModule> entityList = new ArrayList<GprRoleModule>();

		public ArrayList<GprRoleModule> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GprRoleModule> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GprRoleModuleDeleteByIdList", description = "批量删除GprRoleModule所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GprRoleModuleDeleteByCompositeIdList", description = "批量删除GprRoleModule所复合主键参数")
	public static class DeleteByCompositeIdList {

		@ApiModelProperty(value = "要删除的记录列表 ", required = false)
		private ArrayList<GprRoleModule> entityList = new ArrayList<GprRoleModule>();

		public ArrayList<GprRoleModule> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GprRoleModule> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GprRoleModuleUpdateList", description = "批量修改GprRoleModule所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GprRoleModule entity = new GprRoleModule();

		public GprRoleModule getEntity() {
			return entity;
		}

		public void setEntity(GprRoleModule entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GprRoleModuleGetList", description = "模糊查询GprRoleModule所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GprRoleModuleGetListEntityRelated", description = "模糊查询GprRoleModule所需的参数，实体类相关。")
		public static class EntityRelated extends GprRoleModuleGenEnt {

		}
	}

}
