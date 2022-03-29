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
	

	@ApiModelProperty(value = "消息接收者ID列表。", hidden = false, required = false)
	private String receiverUserIds;

	@ApiModelProperty(value = "消息接收者名称列表。", hidden = false, required = false)
	private String receiverUserNames;

	@ApiModelProperty(value = "消息接收应用ID列表。", hidden = false, required = false)
	private String receiverDomainIds;

	@ApiModelProperty(value = "消息接收应用名称列表。", hidden = false, required = false)
	private String receiverDomainNames;

	@ApiModelProperty(value = "是否已读标志。", hidden = false, required = false)
	private String isReadCode;


	public String getReceiverUserIds() {
		return receiverUserIds;
	}

	public void setReceiverUserIds(String receiverUserIds) {
		this.receiverUserIds = receiverUserIds;
	}

	public String getReceiverUserNames() {
		return receiverUserNames;
	}

	public void setReceiverUserNames(String receiverUserNames) {
		this.receiverUserNames = receiverUserNames;
	}

	public String getReceiverDomainIds() {
		return receiverDomainIds;
	}

	public void setReceiverDomainIds(String receiverDomainIds) {
		this.receiverDomainIds = receiverDomainIds;
	}

	public String getReceiverDomainNames() {
		return receiverDomainNames;
	}

	public void setReceiverDomainNames(String receiverDomainNames) {
		this.receiverDomainNames = receiverDomainNames;
	}

	public String getIsReadCode() {
		return isReadCode;
	}

	public void setIsReadCode(String isReadCode) {
		this.isReadCode = isReadCode;
	}
}







