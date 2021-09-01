package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GprDomainMessage;
import com.zee.ent.generate.gp.GprDomainMessageGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-9-17 10:14:52
 * @description 实体类GprDomainMessageParameter，方法参数，自动生成。应用领域的站内信。
 */

public class GprDomainMessageParameter extends BaseParameter {

	@ApiModel(value = "GprDomainMessageAddList", description = "批量添加GprDomainMessage所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GprDomainMessage> entityList = new ArrayList<GprDomainMessage>();

		public ArrayList<GprDomainMessage> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GprDomainMessage> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GprDomainMessageDeleteByIdList", description = "批量删除GprDomainMessage所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GprDomainMessageUpdateList", description = "批量修改GprDomainMessage所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GprDomainMessage entity = new GprDomainMessage();

		public GprDomainMessage getEntity() {
			return entity;
		}

		public void setEntity(GprDomainMessage entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GprDomainMessageGetList", description = "模糊查询GprDomainMessage所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GprDomainMessageGetListEntityRelated", description = "模糊查询GprDomainMessage所需的参数，实体类相关。")
		public static class EntityRelated extends GprDomainMessageGenEnt{
        
		}
	}

}







