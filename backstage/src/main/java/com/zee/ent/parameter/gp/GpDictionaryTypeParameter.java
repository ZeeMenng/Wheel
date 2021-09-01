package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GpDictionaryType;
import com.zee.ent.generate.gp.GpDictionaryTypeGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类GpDictionaryTypeParameter，方法参数，自动生成。字典类型。
 */

public class GpDictionaryTypeParameter extends BaseParameter {

	@ApiModel(value = "GpDictionaryTypeAddList", description = "批量添加GpDictionaryType所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GpDictionaryType> entityList = new ArrayList<GpDictionaryType>();

		public ArrayList<GpDictionaryType> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GpDictionaryType> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GpDictionaryTypeDeleteByIdList", description = "批量删除GpDictionaryType所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GpDictionaryTypeUpdateList", description = "批量修改GpDictionaryType所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GpDictionaryType entity = new GpDictionaryType();

		public GpDictionaryType getEntity() {
			return entity;
		}

		public void setEntity(GpDictionaryType entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GpDictionaryTypeGetList", description = "模糊查询GpDictionaryType所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GpDictionaryTypeGetListEntityRelated", description = "模糊查询GpDictionaryType所需的参数，实体类相关。")
		public static class EntityRelated extends GpDictionaryTypeGenEnt{
        
		}
	}

}







