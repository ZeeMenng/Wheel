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
	

	@ApiModelProperty(value = "是否已读标志。", hidden = false, required = false)
	private String isReadCode;

	public String getIsReadCode() {
		return isReadCode;
	}

	public void setIsReadCode(String isReadCode) {
		this.isReadCode = isReadCode;
	}
}







