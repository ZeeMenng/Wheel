package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GprConfigUser;
import com.zee.ent.generate.gp.GprConfigUserGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/19 11:24:07
 * @description 实体类GprConfigUserParameter，方法参数，自动生成。用户配置信息。
 */

public class GprConfigUserParameter extends BaseParameter {

	@ApiModel(value = "GprConfigUserAddList", description = "批量添加GprConfigUser所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GprConfigUser> entityList = new ArrayList<GprConfigUser>();

		public ArrayList<GprConfigUser> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GprConfigUser> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GprConfigUserDeleteByIdList", description = "批量删除GprConfigUser所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GprConfigUserUpdateList", description = "批量修改GprConfigUser所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GprConfigUser entity = new GprConfigUser();

		public GprConfigUser getEntity() {
			return entity;
		}

		public void setEntity(GprConfigUser entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GprConfigUserGetList", description = "模糊查询GprConfigUser所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GprConfigUserGetListEntityRelated", description = "模糊查询GprConfigUser所需的参数，实体类相关。")
		public static class EntityRelated extends GprConfigUserGenEnt{
        
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







