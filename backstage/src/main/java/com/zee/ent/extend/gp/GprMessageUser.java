package com.zee.ent.extend.gp;

import com.zee.ent.generate.gp.GprMessageUserGenEnt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2017/12/15 23:42:00
 * @description 扩展自实体类GprMessageUserGenEnt，可手动更改。消息接收者及消息读取状态。
 */

@ApiModel(value = "GprMessageUser", description = "消息接收者及消息读取状态。")
public class GprMessageUser extends GprMessageUserGenEnt {
	
	@ApiModelProperty(value="消息内容。",hidden=false,required=false)
    private String content;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@ApiModelProperty(value = "系统消息数量。", hidden = false, required = false)
	private String sysMessageNum;
	
	public String getSysMessageNum() {
		return sysMessageNum;
	}
	public void setSysMessageNum(String sysMessageNum) {
		this.sysMessageNum = sysMessageNum;
	}

	@ApiModelProperty(value = "个人消息数量。", hidden = false, required = false)
	private String userMessageNum;
	
	public String getUserMessageNum() {
		return userMessageNum;
	}
	public void setUserMessageNum(String userMessageNum) {
		this.userMessageNum = userMessageNum;
	}
	
}







