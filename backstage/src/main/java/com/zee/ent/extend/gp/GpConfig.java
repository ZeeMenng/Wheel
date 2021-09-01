package com.zee.ent.extend.gp;

import com.zee.ent.generate.gp.GpConfigGenEnt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2021/1/19 11:57:31
 * @description 扩展自实体类GpConfigGenEnt，可手动更改。配置项信息。
 */

@ApiModel(value = "GpConfig", description = "配置项信息。")
public class GpConfig extends GpConfigGenEnt {

	@ApiModelProperty(value = "主键。业务。外键，引用应用领域表（domain）、页面（page）或控件表（control）等的主键。", hidden = false, required = true)
	private String businessId;

	@ApiModelProperty(value = "配置项值。", hidden = false, required = false)
	private String configValue;

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

}
