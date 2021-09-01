package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GpPage;
import com.zee.ent.generate.gp.GpPageGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类GpPageParameter，方法参数，自动生成。系统页面。
 */

public class GpPageParameter extends BaseParameter {

	@ApiModel(value = "GpPageAddList", description = "批量添加GpPage所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GpPage> entityList = new ArrayList<GpPage>();

		public ArrayList<GpPage> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GpPage> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GpPageDeleteByIdList", description = "批量删除GpPage所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GpPageUpdateList", description = "批量修改GpPage所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GpPage entity = new GpPage();

		public GpPage getEntity() {
			return entity;
		}

		public void setEntity(GpPage entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GpPageGetList", description = "模糊查询GpPage所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GpPageGetListEntityRelated", description = "模糊查询GpPage所需的参数，实体类相关。")
		public static class EntityRelated extends GpPageGenEnt{
        
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







