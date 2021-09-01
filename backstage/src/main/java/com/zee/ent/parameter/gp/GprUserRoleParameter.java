package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GprUserRole;
import com.zee.ent.generate.gp.GprUserRoleGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类GprUserRoleParameter，方法参数，自动生成。用户拥有的角色。
 */

public class GprUserRoleParameter extends BaseParameter {

	@ApiModel(value = "GprUserRoleAddList", description = "批量添加GprUserRole所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GprUserRole> entityList = new ArrayList<GprUserRole>();

		public ArrayList<GprUserRole> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GprUserRole> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GprUserRoleDeleteByIdList", description = "批量删除GprUserRole所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GprUserRoleUpdateList", description = "批量修改GprUserRole所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GprUserRole entity = new GprUserRole();

		public GprUserRole getEntity() {
			return entity;
		}

		public void setEntity(GprUserRole entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GprUserRoleGetList", description = "模糊查询GprUserRole所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GprUserRoleGetListEntityRelated", description = "模糊查询GprUserRole所需的参数，实体类相关。")
		public static class EntityRelated extends GprUserRoleGenEnt{
        
		}
	}

}







