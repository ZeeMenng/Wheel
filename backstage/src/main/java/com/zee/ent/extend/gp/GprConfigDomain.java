package com.zee.ent.extend.gp;

import java.util.Date;

import com.zee.ent.generate.gp.GprConfigDomainGenEnt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2021/1/20 10:44:14
 * @description 扩展自实体类GprConfigDomainGenEnt，可手动更改。应用领域配置信息。
 */

@ApiModel(value = "GprConfigDomain", description = "应用领域配置信息。")
public class GprConfigDomain extends GprConfigDomainGenEnt {

	@ApiModelProperty(value = "键值。", hidden = false, required = false)
	private String code;
	@ApiModelProperty(value = "名称。", hidden = false, required = false)
	private String name;
	@ApiModelProperty(value = "备注。", hidden = false, required = false)
	private String remark;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
