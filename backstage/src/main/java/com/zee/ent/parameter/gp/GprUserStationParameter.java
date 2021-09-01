package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GprUserStation;
import com.zee.ent.generate.gp.GprUserStationGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类GprUserStationParameter，方法参数，自动生成。用户所属岗位。
 */

public class GprUserStationParameter extends BaseParameter {

	@ApiModel(value = "GprUserStationAddList", description = "批量添加GprUserStation所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GprUserStation> entityList = new ArrayList<GprUserStation>();

		public ArrayList<GprUserStation> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GprUserStation> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GprUserStationDeleteByIdList", description = "批量删除GprUserStation所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GprUserStationUpdateList", description = "批量修改GprUserStation所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GprUserStation entity = new GprUserStation();

		public GprUserStation getEntity() {
			return entity;
		}

		public void setEntity(GprUserStation entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GprUserStationGetList", description = "模糊查询GprUserStation所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GprUserStationGetListEntityRelated", description = "模糊查询GprUserStation所需的参数，实体类相关。")
		public static class EntityRelated extends GprUserStationGenEnt{
        
		}
	}

}







