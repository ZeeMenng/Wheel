package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GprRoleControl;
import com.zee.ent.generate.gp.GprRoleControlGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类GprRoleControlParameter，方法参数，自动生成。角色拥有的控件权限。
 */

public class GprRoleControlParameter extends BaseParameter {

	@ApiModel(value = "GprRoleControlAddList", description = "批量添加GprRoleControl所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GprRoleControl> entityList = new ArrayList<GprRoleControl>();

		public ArrayList<GprRoleControl> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GprRoleControl> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GprRoleControlDeleteByIdList", description = "批量删除GprRoleControl所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GprRoleControlUpdateList", description = "批量修改GprRoleControl所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GprRoleControl entity = new GprRoleControl();

		public GprRoleControl getEntity() {
			return entity;
		}

		public void setEntity(GprRoleControl entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GprRoleControlGetList", description = "模糊查询GprRoleControl所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GprRoleControlGetListEntityRelated", description = "模糊查询GprRoleControl所需的参数，实体类相关。")
		public static class EntityRelated extends GprRoleControlGenEnt{
        
		}
	}

}







