package com.zee.ent.extend.gp;

import com.zee.ent.generate.gp.GpOrganizationGenEnt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2017/12/15 23:42:00
 * @description 扩展自实体类GpOrganizationGenEnt，可手动更改。组织机构。
 */

@ApiModel(value = "GpOrganization", description = "组织机构。")
public class GpOrganization extends GpOrganizationGenEnt {
  
	@ApiModelProperty(value = "顶级组织机构名称。", hidden = false, required = false)
	private String organizationTop;
	@ApiModelProperty(value = "父级组织机构名称。", hidden = false, required = false)
	private String organizationFar;
	@ApiModelProperty(value="名称。",hidden=false,required=false)
    private String fartherName;
	
	public String getOrganizationTop() {
		return organizationTop;
	}
	public void setOrganizationTop(String organizationTop) {
		this.organizationTop = organizationTop;
	}
	public String getOrganizationFar() {
		return organizationFar;
	}
	public void setOrganizationFar(String organizationFar) {
		this.organizationFar = organizationFar;
	}
	public String getFartherName() {
		return fartherName;
	}
	public void setFartherName(String fartherName) {
		this.fartherName = fartherName;
	}
	
	@ApiModelProperty(value = "所属组织机构名称列表code", hidden = false, required = false)
	private String code;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@ApiModelProperty(value = "所属组织机构名称列表text", hidden = false, required = false)
	private String text;

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}







