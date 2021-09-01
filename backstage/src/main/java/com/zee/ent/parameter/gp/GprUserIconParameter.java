package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GprUserIcon;
import com.zee.ent.generate.gp.GprUserIconGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/5/7 15:00:36
 * @description 实体类GprUserIconParameter，方法参数，自动生成。用户头像表
 */

public class GprUserIconParameter extends BaseParameter {

	@ApiModel(value = "GprUserIconAddList", description = "批量添加GprUserIcon所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GprUserIcon> entityList = new ArrayList<GprUserIcon>();

		public ArrayList<GprUserIcon> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GprUserIcon> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GprUserIconDeleteByIdList", description = "批量删除GprUserIcon所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GprUserIconUpdateList", description = "批量修改GprUserIcon所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GprUserIcon entity = new GprUserIcon();

		public GprUserIcon getEntity() {
			return entity;
		}

		public void setEntity(GprUserIcon entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GprUserIconGetList", description = "模糊查询GprUserIcon所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GprUserIconGetListEntityRelated", description = "模糊查询GprUserIcon所需的参数，实体类相关。")
		public static class EntityRelated extends GprUserIconGenEnt{
        
		}
	}

}







