package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GpDomain;
import com.zee.ent.generate.gp.GpDomainGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类GpDomainParameter，方法参数，自动生成。应用领域。
 */

public class GpDomainParameter extends BaseParameter {

	@ApiModel(value = "GpDomainAddList", description = "批量添加GpDomain所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GpDomain> entityList = new ArrayList<GpDomain>();

		public ArrayList<GpDomain> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GpDomain> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GpDomainDeleteByIdList", description = "批量删除GpDomain所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GpDomainUpdateList", description = "批量修改GpDomain所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GpDomain entity = new GpDomain();

		public GpDomain getEntity() {
			return entity;
		}

		public void setEntity(GpDomain entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GpDomainGetList", description = "模糊查询GpDomain所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GpDomainGetListEntityRelated", description = "模糊查询GpDomain所需的参数，实体类相关。")
		public static class EntityRelated extends GpDomainGenEnt{
        
        @ApiModelProperty(value="记录创建时间。查询起止时间。",required=false)
		private Date beginAddTime;

        @ApiModelProperty(value="记录创建时间。查询结束时间。",required=false)
		private Date endAddTime;

        @ApiModelProperty(value="记录最后一次修改时间。查询起止时间。",required=false)
		private Date beginUpdateTime;

        @ApiModelProperty(value="记录最后一次修改时间。查询结束时间。",required=false)
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







