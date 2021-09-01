package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GpRegionCountry;
import com.zee.ent.generate.gp.GpRegionCountryGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类GpRegionCountryParameter，方法参数，自动生成。地区信息。
 */

public class GpRegionCountryParameter extends BaseParameter {

	@ApiModel(value = "GpRegionCountryAddList", description = "批量添加GpRegionCountry所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GpRegionCountry> entityList = new ArrayList<GpRegionCountry>();

		public ArrayList<GpRegionCountry> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GpRegionCountry> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GpRegionCountryDeleteByIdList", description = "批量删除GpRegionCountry所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GpRegionCountryUpdateList", description = "批量修改GpRegionCountry所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GpRegionCountry entity = new GpRegionCountry();

		public GpRegionCountry getEntity() {
			return entity;
		}

		public void setEntity(GpRegionCountry entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GpRegionCountryGetList", description = "模糊查询GpRegionCountry所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GpRegionCountryGetListEntityRelated", description = "模糊查询GpRegionCountry所需的参数，实体类相关。")
		public static class EntityRelated extends GpRegionCountryGenEnt{
        
		}
	}

}







