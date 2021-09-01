package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GpResource;
import com.zee.ent.generate.gp.GpResourceGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/5/7 15:00:36
 * @description 实体类GpResourceParameter，方法参数，自动生成。通用文件信息存储表。
 */

public class GpResourceParameter extends BaseParameter {

	@ApiModel(value = "GpResourceAddList", description = "批量添加GpResource所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GpResource> entityList = new ArrayList<GpResource>();

		public ArrayList<GpResource> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GpResource> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GpResourceDeleteByIdList", description = "批量删除GpResource所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GpResourceUpdateList", description = "批量修改GpResource所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GpResource entity = new GpResource();

		public GpResource getEntity() {
			return entity;
		}

		public void setEntity(GpResource entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GpResourceGetList", description = "模糊查询GpResource所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GpResourceGetListEntityRelated", description = "模糊查询GpResource所需的参数，实体类相关。")
		public static class EntityRelated extends GpResourceGenEnt{
        
        @ApiModelProperty(value="新增时间查询起止时间。",required=false)
		private Date beginAddTime;

        @ApiModelProperty(value="新增时间查询结束时间。",required=false)
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







