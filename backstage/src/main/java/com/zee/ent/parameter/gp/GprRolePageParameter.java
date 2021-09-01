package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GprRolePage;
import com.zee.ent.generate.gp.GprRolePageGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类GprRolePageParameter，方法参数，自动生成。角色拥有的页面权限。
 */

public class GprRolePageParameter extends BaseParameter {

	@ApiModel(value = "GprRolePageAddList", description = "批量添加GprRolePage所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GprRolePage> entityList = new ArrayList<GprRolePage>();

		public ArrayList<GprRolePage> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GprRolePage> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GprRolePageDeleteByIdList", description = "批量删除GprRolePage所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GprRolePageUpdateList", description = "批量修改GprRolePage所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GprRolePage entity = new GprRolePage();

		public GprRolePage getEntity() {
			return entity;
		}

		public void setEntity(GprRolePage entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GprRolePageGetList", description = "模糊查询GprRolePage所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GprRolePageGetListEntityRelated", description = "模糊查询GprRolePage所需的参数，实体类相关。")
		public static class EntityRelated extends GprRolePageGenEnt{
        
		}
	}

}







