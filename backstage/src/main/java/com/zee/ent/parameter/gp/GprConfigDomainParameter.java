package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GprConfigDomain;
import com.zee.ent.generate.gp.GprConfigDomainGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/20 10:44:14
 * @description 实体类GprConfigDomainParameter，方法参数，自动生成。应用领域配置信息。
 */

public class GprConfigDomainParameter extends BaseParameter {

	@ApiModel(value = "GprConfigDomainAddList", description = "批量添加GprConfigDomain所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GprConfigDomain> entityList = new ArrayList<GprConfigDomain>();

		public ArrayList<GprConfigDomain> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GprConfigDomain> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GprConfigDomainDeleteByIdList", description = "批量删除GprConfigDomain所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GprConfigDomainUpdateList", description = "批量修改GprConfigDomain所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GprConfigDomain entity = new GprConfigDomain();

		public GprConfigDomain getEntity() {
			return entity;
		}

		public void setEntity(GprConfigDomain entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GprConfigDomainGetList", description = "模糊查询GprConfigDomain所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GprConfigDomainGetListEntityRelated", description = "模糊查询GprConfigDomain所需的参数，实体类相关。")
		public static class EntityRelated extends GprConfigDomainGenEnt{
        
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







