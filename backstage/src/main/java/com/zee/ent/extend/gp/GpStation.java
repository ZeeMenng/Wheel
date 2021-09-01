package com.zee.ent.extend.gp;

import com.zee.ent.generate.gp.GpStationGenEnt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2017/12/15 23:42:00
 * @description 扩展自实体类GpStationGenEnt，可手动更改。岗位。
 */

@ApiModel(value = "GpStation", description = "岗位。")
public class GpStation extends GpStationGenEnt {
  
	@ApiModelProperty(value = "所属组织机构名称", hidden = false, required = false)
	private String organizationName;

	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

}







