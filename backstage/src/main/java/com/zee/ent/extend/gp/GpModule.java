package com.zee.ent.extend.gp;

import com.zee.ent.generate.gp.GpModuleGenEnt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2017/12/15 23:42:00
 * @description 扩展自实体类GpModuleGenEnt，可手动更改。功能模块。
 */

@ApiModel(value = "GpModule", description = "功能模块。")
public class GpModule extends GpModuleGenEnt {

	@ApiModelProperty(value = "应用领域名称", hidden = false, required = false)
	private String domainName;

	@ApiModelProperty(value = "级联方式。0全部级联 1仅级联增 2仅级联删  3仅级联改 4不做级联", hidden = false, required = false)
	private Byte cascadeTypeCode;

	@ApiModelProperty(value = "菜单图标", hidden = false, required = false)
	private String iconPaths;

	@ApiModelProperty(value = "菜单图标", hidden = false, required = false)
	private String iconIds;

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
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

	public Byte getCascadeTypeCode() {
		return cascadeTypeCode;
	}

	public void setCascadeTypeCode(Byte cascadeTypeCode) {
		this.cascadeTypeCode = cascadeTypeCode;
	}

}
