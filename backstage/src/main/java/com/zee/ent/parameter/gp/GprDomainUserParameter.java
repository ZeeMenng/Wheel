package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GprDomainUser;
import com.zee.ent.generate.gp.GprDomainUserGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类GprDomainUserParameter，方法参数，自动生成。应用领域拥有的用户。
 */

public class GprDomainUserParameter extends BaseParameter {

	@ApiModel(value = "GprDomainUserAddList", description = "批量添加GprDomainUser所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GprDomainUser> entityList = new ArrayList<GprDomainUser>();

		public ArrayList<GprDomainUser> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GprDomainUser> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GprDomainUserDeleteByIdList", description = "批量删除GprDomainUser所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GprDomainUserUpdateList", description = "批量修改GprDomainUser所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GprDomainUser entity = new GprDomainUser();

		public GprDomainUser getEntity() {
			return entity;
		}

		public void setEntity(GprDomainUser entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GprDomainUserGetList", description = "模糊查询GprDomainUser所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GprDomainUserGetListEntityRelated", description = "模糊查询GprDomainUser所需的参数，实体类相关。")
		public static class EntityRelated extends GprDomainUserGenEnt{
        
		}
	}

}







