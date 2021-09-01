package com.zee.ent.parameter.gp;

import java.util.ArrayList;
import java.util.Date;

import com.zee.ent.extend.gp.GpVariety;
import com.zee.ent.generate.gp.GpVarietyGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/2/3 14:28:06
 * @description 实体类GpVarietyParameter，方法参数，自动生成。品种表
 */

public class GpVarietyParameter extends BaseParameter {

	@ApiModel(value = "GpVarietyAddList", description = "批量添加GpVariety所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GpVariety> entityList = new ArrayList<GpVariety>();

		public ArrayList<GpVariety> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GpVariety> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GpVarietyDeleteByIdList", description = "批量删除GpVariety所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GpVarietyUpdateList", description = "批量修改GpVariety所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GpVariety entity = new GpVariety();

		public GpVariety getEntity() {
			return entity;
		}

		public void setEntity(GpVariety entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GpVarietyGetList", description = "模糊查询GpVariety所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GpVarietyGetListEntityRelated", description = "模糊查询GpVariety所需的参数，实体类相关。")
		public static class EntityRelated extends GpVarietyGenEnt{
        
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







