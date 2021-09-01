package com.zee.ent.parameter.gp;

import java.util.*;

import com.zee.ent.extend.gp.GpMessage;
import com.zee.ent.generate.gp.GpMessageGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/5/7 15:00:36
 * @description 实体类GpMessageParameter，方法参数，自动生成。消息表。如果消息类型是公告，在用户读取消息的时候向消息队列表（gpr_message_user）插入数据；如果消息类型是私信和提醒，则新建消息后立即向消息列表（gpr_message_use）中插入数据。
 */

public class GpMessageParameter extends BaseParameter {

	@ApiModel(value = "GpMessageAddList", description = "批量添加GpMessage所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GpMessage> entityList = new ArrayList<GpMessage>();

		public ArrayList<GpMessage> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GpMessage> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GpMessageDeleteByIdList", description = "批量删除GpMessage所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GpMessageUpdateList", description = "批量修改GpMessage所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GpMessage entity = new GpMessage();

		public GpMessage getEntity() {
			return entity;
		}

		public void setEntity(GpMessage entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GpMessageGetList", description = "模糊查询GpMessage所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GpMessageGetListEntityRelated", description = "模糊查询GpMessage所需的参数，实体类相关。")
		public static class EntityRelated extends GpMessageGenEnt{
        
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







