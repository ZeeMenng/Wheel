package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GpToken;
import com.zee.ent.generate.gp.GpTokenGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类GpTokenParameter，方法参数，自动生成。token信息。
 */

public class GpTokenParameter extends BaseParameter {

	@ApiModel(value = "GpTokenAddList", description = "批量添加GpToken所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GpToken> entityList = new ArrayList<GpToken>();

		public ArrayList<GpToken> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GpToken> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GpTokenDeleteByIdList", description = "批量删除GpToken所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GpTokenUpdateList", description = "批量修改GpToken所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GpToken entity = new GpToken();

		public GpToken getEntity() {
			return entity;
		}

		public void setEntity(GpToken entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GpTokenGetList", description = "模糊查询GpToken所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GpTokenGetListEntityRelated", description = "模糊查询GpToken所需的参数，实体类相关。")
		public static class EntityRelated extends GpTokenGenEnt{
        
        @ApiModelProperty(value="access_token过期时间。查询起止时间。",required=false)
		private Date beginADeadTime;

        @ApiModelProperty(value="access_token过期时间。查询结束时间。",required=false)
		private Date endADeadTime;

        @ApiModelProperty(value="记录创建时间。查询起止时间。",required=false)
		private Date beginAddTime;

        @ApiModelProperty(value="记录创建时间。查询结束时间。",required=false)
		private Date endAddTime;

        @ApiModelProperty(value="refresh_token过期时间。查询起止时间。",required=false)
		private Date beginRDeadTime;

        @ApiModelProperty(value="refresh_token过期时间。查询结束时间。",required=false)
		private Date endRDeadTime;

        @ApiModelProperty(value="记录最后一次修改时间。查询起止时间。",required=false)
		private Date beginUpdateTime;

        @ApiModelProperty(value="记录最后一次修改时间。查询结束时间。",required=false)
		private Date endUpdateTime;

		public Date getBeginADeadTime() {
			return this.beginADeadTime;
		}
        
		public void setBeginADeadTime(Date beginADeadTime) {
			this.beginADeadTime = beginADeadTime;
		}
        
        public Date getEndADeadTime() {
			return this.endADeadTime;
		}
        
		public void setEndADeadTime(Date endADeadTime) {
			this.endADeadTime = endADeadTime;
		}
        
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
        
		public Date getBeginRDeadTime() {
			return this.beginRDeadTime;
		}
        
		public void setBeginRDeadTime(Date beginRDeadTime) {
			this.beginRDeadTime = beginRDeadTime;
		}
        
        public Date getEndRDeadTime() {
			return this.endRDeadTime;
		}
        
		public void setEndRDeadTime(Date endRDeadTime) {
			this.endRDeadTime = endRDeadTime;
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







