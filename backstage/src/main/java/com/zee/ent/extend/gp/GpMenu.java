package com.zee.ent.extend.gp;

import com.zee.ent.generate.gp.GpMenuGenEnt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2017/12/15 23:42:00
 * @description 扩展自实体类GpMenuGenEnt，可手动更改。链接菜单。
 */

@ApiModel(value = "GpMenu", description = "链接菜单。")
public class GpMenu extends GpMenuGenEnt {
	@ApiModelProperty(value="功能模块名称。",hidden=false,required=false)
    private String moduleName;
	@ApiModelProperty(value="应用领域",hidden=false,required=false)
    private String domainName;
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

}







