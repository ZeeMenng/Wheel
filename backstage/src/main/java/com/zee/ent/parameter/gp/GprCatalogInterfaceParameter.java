package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GprCatalogInterface;
import com.zee.ent.generate.gp.GprCatalogInterfaceGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2020/10/21 21:21:11
 * @description 实体类GprCatalogInterfaceParameter，方法参数，自动生成。后台接口所属分类。
 */

public class GprCatalogInterfaceParameter extends BaseParameter {

	@ApiModel(value = "GprCatalogInterfaceAddList", description = "批量添加GprCatalogInterface所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GprCatalogInterface> entityList = new ArrayList<GprCatalogInterface>();

		public ArrayList<GprCatalogInterface> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GprCatalogInterface> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GprCatalogInterfaceDeleteByIdList", description = "批量删除GprCatalogInterface所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GprCatalogInterfaceUpdateList", description = "批量修改GprCatalogInterface所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GprCatalogInterface entity = new GprCatalogInterface();

		public GprCatalogInterface getEntity() {
			return entity;
		}

		public void setEntity(GprCatalogInterface entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GprCatalogInterfaceGetList", description = "模糊查询GprCatalogInterface所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GprCatalogInterfaceGetListEntityRelated", description = "模糊查询GprCatalogInterface所需的参数，实体类相关。")
		public static class EntityRelated extends GprCatalogInterfaceGenEnt{
        
		}
	}

}







