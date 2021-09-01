package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GpRegion;
import com.zee.ent.generate.gp.GpRegionGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类GpRegionParameter，方法参数，自动生成。地区信息。
 */

public class GpRegionParameter extends BaseParameter {

	@ApiModel(value = "GpRegionAddList", description = "批量添加GpRegion所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GpRegion> entityList = new ArrayList<GpRegion>();

		public ArrayList<GpRegion> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GpRegion> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GpRegionDeleteByIdList", description = "批量删除GpRegion所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GpRegionUpdateList", description = "批量修改GpRegion所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GpRegion entity = new GpRegion();

		public GpRegion getEntity() {
			return entity;
		}

		public void setEntity(GpRegion entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GpRegionGetList", description = "模糊查询GpRegion所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GpRegionGetListEntityRelated", description = "模糊查询GpRegion所需的参数，实体类相关。")
		public static class EntityRelated extends GpRegionGenEnt{
        
		}
	}

}







