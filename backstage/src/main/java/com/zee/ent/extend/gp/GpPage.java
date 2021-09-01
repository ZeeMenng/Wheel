package com.zee.ent.extend.gp;

import com.zee.ent.generate.gp.GpPageGenEnt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2017/12/15 23:42:00
 * @description 扩展自实体类GpPageGenEnt，可手动更改。系统页面。
 */

@ApiModel(value = "GpPage", description = "系统页面。")
public class GpPage extends GpPageGenEnt {
	
	@ApiModelProperty(value="应用领域",hidden=false,required=false)
    private String domainName;
	
	@ApiModelProperty(value="是否为公共页面",hidden=false,required=false)
    private String isPublicValue;

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getIsPublicValue() {
		return isPublicValue;
	}

	public void setIsPublicValue(String isPublicValue) {
		this.isPublicValue = isPublicValue;
	}
	
}







