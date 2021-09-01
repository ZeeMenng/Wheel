package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GpOperLog;
import com.zee.ent.generate.gp.GpOperLogGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类GpOperLogParameter，方法参数，自动生成。操作日志。
 */

public class GpOperLogParameter extends BaseParameter {

	@ApiModel(value = "GpOperLogAddList", description = "批量添加GpOperLog所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GpOperLog> entityList = new ArrayList<GpOperLog>();

		public ArrayList<GpOperLog> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GpOperLog> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GpOperLogDeleteByIdList", description = "批量删除GpOperLog所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GpOperLogUpdateList", description = "批量修改GpOperLog所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GpOperLog entity = new GpOperLog();

		public GpOperLog getEntity() {
			return entity;
		}

		public void setEntity(GpOperLog entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GpOperLogGetList", description = "模糊查询GpOperLog所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GpOperLogGetListEntityRelated", description = "模糊查询GpOperLog所需的参数，实体类相关。")
		public static class EntityRelated extends GpOperLogGenEnt{
        
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







