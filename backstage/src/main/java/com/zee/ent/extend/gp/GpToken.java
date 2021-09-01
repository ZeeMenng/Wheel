package com.zee.ent.extend.gp;

import com.zee.ent.generate.gp.GpTokenGenEnt;

import io.swagger.annotations.ApiModel;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2017/12/15 23:42:00
 * @description 扩展自实体类GpTokenGenEnt，可手动更改。token信息。
 */

@ApiModel(value = "GpToken", description = "token信息。")
public class GpToken extends GpTokenGenEnt {

	private GpUser gpUser;

	public GpUser getGpUser() {
		return gpUser;
	}

	public void setGpUser(GpUser gpUser) {
		this.gpUser = gpUser;
	}

}
