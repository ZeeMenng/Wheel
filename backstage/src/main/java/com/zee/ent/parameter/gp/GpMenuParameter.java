package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GpMenu;
import com.zee.ent.generate.gp.GpMenuGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类GpMenuParameter，方法参数，自动生成。链接菜单。
 */

public class GpMenuParameter extends BaseParameter {

	@ApiModel(value = "GpMenuAddList", description = "批量添加GpMenu所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GpMenu> entityList = new ArrayList<GpMenu>();

		public ArrayList<GpMenu> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GpMenu> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GpMenuDeleteByIdList", description = "批量删除GpMenu所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GpMenuUpdateList", description = "批量修改GpMenu所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GpMenu entity = new GpMenu();

		public GpMenu getEntity() {
			return entity;
		}

		public void setEntity(GpMenu entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GpMenuGetList", description = "模糊查询GpMenu所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GpMenuGetListEntityRelated", description = "模糊查询GpMenu所需的参数，实体类相关。")
		public static class EntityRelated extends GpMenuGenEnt{
        
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







