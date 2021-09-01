package com.zee.ent.extend.gp;

import com.zee.ent.generate.gp.GpMessageGenEnt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2017/12/15 23:42:00
 * @description 扩展自实体类GpMessageGenEnt，可手动更改。站内信。
 */

@ApiModel(value = "GpMessage", description = "站内信。")
public class GpMessage extends GpMessageGenEnt {
	
	@ApiModelProperty(value = "消息类型。", hidden = false, required = false)
	private String messageType;
	
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	
	@ApiModelProperty(value = "是否已读标志。", hidden = false, required = false)
	private String isReadCode;
	
	public String getIsReadCode() {
		return isReadCode;
	}
	public void setIsReadCode(String isReadCode) {
		this.isReadCode = isReadCode;
	}
	
	
	@ApiModelProperty(value = "消息接收者id及name。", hidden = false, required = false)
	private String receiverObjs;
	
	public String getReceiverObjs() {
		return receiverObjs;
	}
	public void setReceiverObjs(String receiverObjs) {
		this.receiverObjs = receiverObjs;
	}
	
	@ApiModelProperty(value = "消息接收者ID。", hidden = false, required = false)
	private String receiverIds;
	
	public String getReceiverIds() {
		return receiverIds;
	}
	public void setReceiverIds(String receiverIds) {
		this.receiverIds = receiverIds;
	}
	
	
	@ApiModelProperty(value = "消息接收者名称。", hidden = false, required = false)
	private String receiverNames;
	
	public String getReceiverNames() {
		return receiverNames;
	}
	public void setReceiverNames(String receiverNames) {
		this.receiverNames = receiverNames;
	}
	
}







