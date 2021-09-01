package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GpEarlyWarning;
import com.zee.ent.generate.gp.GpEarlyWarningGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-8-17 16:57:52
 * @description 实体类GpEarlyWarningParameter，方法参数，自动生成。预警阀值表
 */

public class GpEarlyWarningParameter extends BaseParameter {

	@ApiModel(value = "GpEarlyWarningAddList", description = "批量添加GpEarlyWarning所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GpEarlyWarning> entityList = new ArrayList<GpEarlyWarning>();

		public ArrayList<GpEarlyWarning> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GpEarlyWarning> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GpEarlyWarningDeleteByIdList", description = "批量删除GpEarlyWarning所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GpEarlyWarningUpdateList", description = "批量修改GpEarlyWarning所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GpEarlyWarning entity = new GpEarlyWarning();

		public GpEarlyWarning getEntity() {
			return entity;
		}

		public void setEntity(GpEarlyWarning entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GpEarlyWarningGetList", description = "模糊查询GpEarlyWarning所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GpEarlyWarningGetListEntityRelated", description = "模糊查询GpEarlyWarning所需的参数，实体类相关。")
		public static class EntityRelated extends GpEarlyWarningGenEnt{
        
        @ApiModelProperty(value="创建时间查询起止时间。",required=false)
		private Date beginAddTime;

        @ApiModelProperty(value="创建时间查询结束时间。",required=false)
		private Date endAddTime;

        @ApiModelProperty(value="最后一次修改时间查询起止时间。",required=false)
		private Date beginUpdateTime;

        @ApiModelProperty(value="最后一次修改时间查询结束时间。",required=false)
		private Date endUpdateTime;

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
        
		public Date getBeginUpdateTime() {
			return this.beginUpdateTime;
		}
        
		public void setBeginUpdateTime(Date beginUpdateTime) {
			this.beginUpdateTime = beginUpdateTime;
		}
        
        public Date getEndUpdateTime() {
			return this.endUpdateTime;
		}
        
		public void setEndUpdateTime(Date endUpdateTime) {
			this.endUpdateTime = endUpdateTime;
		}
        
		}
	}

}







