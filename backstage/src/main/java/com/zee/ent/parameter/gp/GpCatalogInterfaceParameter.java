package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GpCatalogInterface;
import com.zee.ent.generate.gp.GpCatalogInterfaceGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2020/10/21 21:21:11
 * @description 实体类GpCatalogInterfaceParameter，方法参数，自动生成。接口分类字典管理存放接口分类信息，支持树形分级分类，主要但不限于业务上的分类方式，支持同时对接口进行多种分类。
 */

public class GpCatalogInterfaceParameter extends BaseParameter {

	@ApiModel(value = "GpCatalogInterfaceAddList", description = "批量添加GpCatalogInterface所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GpCatalogInterface> entityList = new ArrayList<GpCatalogInterface>();

		public ArrayList<GpCatalogInterface> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GpCatalogInterface> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GpCatalogInterfaceDeleteByIdList", description = "批量删除GpCatalogInterface所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GpCatalogInterfaceUpdateList", description = "批量修改GpCatalogInterface所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GpCatalogInterface entity = new GpCatalogInterface();

		public GpCatalogInterface getEntity() {
			return entity;
		}

		public void setEntity(GpCatalogInterface entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GpCatalogInterfaceGetList", description = "模糊查询GpCatalogInterface所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GpCatalogInterfaceGetListEntityRelated", description = "模糊查询GpCatalogInterface所需的参数，实体类相关。")
		public static class EntityRelated extends GpCatalogInterfaceGenEnt{
        
		}
	}

}







