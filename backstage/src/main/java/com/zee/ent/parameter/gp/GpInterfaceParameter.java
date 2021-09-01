package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GpInterface;
import com.zee.ent.generate.gp.GpInterfaceGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类GpInterfaceParameter，方法参数，自动生成。系统接口。
 */

public class GpInterfaceParameter extends BaseParameter {

	@ApiModel(value = "GpInterfaceAddList", description = "批量添加GpInterface所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GpInterface> entityList = new ArrayList<GpInterface>();

		public ArrayList<GpInterface> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GpInterface> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GpInterfaceDeleteByIdList", description = "批量删除GpInterface所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GpInterfaceUpdateList", description = "批量修改GpInterface所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GpInterface entity = new GpInterface();

		public GpInterface getEntity() {
			return entity;
		}

		public void setEntity(GpInterface entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GpInterfaceGetList", description = "模糊查询GpInterface所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GpInterfaceGetListEntityRelated", description = "模糊查询GpInterface所需的参数，实体类相关。")
		public static class EntityRelated extends GpInterfaceGenEnt{
        
        @ApiModelProperty(value="记录创建时间。查询起止时间。",required=false)
		private Date beginAddTime;

        @ApiModelProperty(value="记录创建时间。查询结束时间。",required=false)
		private Date endAddTime;

		public Date getBeginAddTime() {
			return this.beginAddTime;
		}
        
		public void setBeginAddTime(Date beginAddTime) {
			this.beginAddTime = beginAddTime;
		}
        
        public Date getEndAddTime() {
			return this.endAddTime;
		}
        
		public void setEndAddTime(Date endAddTime) {
			this.endAddTime = endAddTime;
		}
        
		}
	}

}







