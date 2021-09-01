package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GprModulePage;
import com.zee.ent.generate.gp.GprModulePageGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类GprModulePageParameter，方法参数，自动生成。功能模块所包含的页面。
 */

public class GprModulePageParameter extends BaseParameter {

	@ApiModel(value = "GprModulePageAddList", description = "批量添加GprModulePage所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GprModulePage> entityList = new ArrayList<GprModulePage>();

		public ArrayList<GprModulePage> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GprModulePage> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GprModulePageDeleteByIdList", description = "批量删除GprModulePage所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GprModulePageUpdateList", description = "批量修改GprModulePage所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GprModulePage entity = new GprModulePage();

		public GprModulePage getEntity() {
			return entity;
		}

		public void setEntity(GprModulePage entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GprModulePageGetList", description = "模糊查询GprModulePage所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GprModulePageGetListEntityRelated", description = "模糊查询GprModulePage所需的参数，实体类相关。")
		public static class EntityRelated extends GprModulePageGenEnt{
        
		}
	}

}







