package com.zee.ent.extend.gp;

import com.zee.ent.generate.gp.GpDomainGenEnt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2017/12/15 23:42:00
 * @description 扩展自实体类GpDomainGenEnt，可手动更改。应用领域。
 */

@ApiModel(value = "GpDomain", description = "应用领域。")
public class GpDomain extends GpDomainGenEnt {
	private String modules;

	@ApiModelProperty(value = "菜单图标", hidden = false, required = false)
	private String iconPaths;

	@ApiModelProperty(value = "菜单图标", hidden = false, required = false)
	private String iconIds;

	public String getModules() {
		return modules;
	}

	public void setModules(String modules) {
		this.modules = modules;
	}

	public String getIconPaths() {
		return iconPaths;
	}

	public void setIconPaths(String iconPaths) {
		this.iconPaths = iconPaths;
	}

	public String getIconIds() {
		return iconIds;
	}

	public void setIconIds(String iconIds) {
		this.iconIds = iconIds;
	}

}
