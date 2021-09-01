package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GpConfig;
import com.zee.ent.generate.gp.GpConfigGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/19 11:57:32
 * @description 实体类GpConfigParameter，方法参数，自动生成。配置项信息。
 */

public class GpConfigParameter extends BaseParameter {

	@ApiModel(value = "GpConfigAddList", description = "批量添加GpConfig所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GpConfig> entityList = new ArrayList<GpConfig>();

		public ArrayList<GpConfig> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GpConfig> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GpConfigDeleteByIdList", description = "批量删除GpConfig所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GpConfigUpdateList", description = "批量修改GpConfig所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GpConfig entity = new GpConfig();

		public GpConfig getEntity() {
			return entity;
		}

		public void setEntity(GpConfig entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GpConfigGetList", description = "模糊查询GpConfig所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GpConfigGetListEntityRelated", description = "模糊查询GpConfig所需的参数，实体类相关。")
		public static class EntityRelated extends GpConfigGenEnt{
        
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







