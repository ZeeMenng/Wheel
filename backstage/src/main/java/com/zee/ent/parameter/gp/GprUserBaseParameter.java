package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GprUserBase;
import com.zee.ent.generate.gp.GprUserBaseGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-21 10:22:39
 * @description 实体类GprUserBaseParameter，方法参数，自动生成。用户归属的基地。
 */

public class GprUserBaseParameter extends BaseParameter {

	@ApiModel(value = "GprUserBaseAddList", description = "批量添加GprUserBase所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GprUserBase> entityList = new ArrayList<GprUserBase>();

		public ArrayList<GprUserBase> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GprUserBase> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GprUserBaseDeleteByIdList", description = "批量删除GprUserBase所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GprUserBaseUpdateList", description = "批量修改GprUserBase所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GprUserBase entity = new GprUserBase();

		public GprUserBase getEntity() {
			return entity;
		}

		public void setEntity(GprUserBase entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GprUserBaseGetList", description = "模糊查询GprUserBase所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GprUserBaseGetListEntityRelated", description = "模糊查询GprUserBase所需的参数，实体类相关。")
		public static class EntityRelated extends GprUserBaseGenEnt{
        
		}
	}

}







