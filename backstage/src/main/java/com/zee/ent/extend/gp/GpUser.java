package com.zee.ent.extend.gp;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zee.ent.generate.gp.GpUserGenEnt;
import com.zee.set.annotation.DictionaryConvertAnnotation;
import com.zee.set.serializer.JacksonDictionarySerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2017/12/15 23:42:00
 * @description 扩展自实体类GpUserGenEnt，可手动更改。系统用户。
 */

@ApiModel(value = "GpUser", description = "系统用户。")
public class GpUser extends GpUserGenEnt {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "应用领域", hidden = false, required = false)
	private GpDomain currentDomain;

	@ApiModelProperty(value = "性别", hidden = false, required = false)
	@DictionaryConvertAnnotation(typeId = "2275e63ff0101d36c6e93eb37ec44f31", codeField = "genderCode")
	@JsonSerialize(using = JacksonDictionarySerializer.class, nullsUsing = JacksonDictionarySerializer.class)
	private String genderValue;

	@ApiModelProperty(value = "是否管理员", hidden = false, required = true)
	@DictionaryConvertAnnotation(typeId = "dc1f9015660bcbcee7f1dfc1a5dea1ea", codeField = "isAdminCode")
	@JsonSerialize(using = JacksonDictionarySerializer.class, nullsUsing = JacksonDictionarySerializer.class)
	private String isAdminValue;

	@ApiModelProperty(value = "是否禁用", hidden = false, required = true)
	@DictionaryConvertAnnotation(typeId = "dc1f9015660bcbcee7f1dfc1a5dea1ea", codeField = "isDisabledCode")
	@JsonSerialize(using = JacksonDictionarySerializer.class, nullsUsing = JacksonDictionarySerializer.class)
	private String isDisabledValue;

	@ApiModelProperty(value = "是否已婚", hidden = false, required = false)
	@DictionaryConvertAnnotation(typeId = "dc1f9015660bcbcee7f1dfc1a5dea1ea", codeField = "isMarriageCode")
	@JsonSerialize(using = JacksonDictionarySerializer.class, nullsUsing = JacksonDictionarySerializer.class)
	private String isMarriageValue;

	@ApiModelProperty(value = "应用领域ids", hidden = false, required = false)
	private String domainIds;

	@ApiModelProperty(value = "应用领域Names", hidden = false, required = false)
	private String domainNames;

	@ApiModelProperty(value = "组织机构ids", hidden = false, required = false)
	private String orgIds;

	@ApiModelProperty(value = "角色ids", hidden = false, required = false)
	private String roleIds;

	@ApiModelProperty(value = "角色名称", hidden = false, required = false)
	private String roleNames;

	@ApiModelProperty(value = "头像路径", hidden = false, required = false)
	private String iconPaths;

	@ApiModelProperty(value = "头像路径", hidden = false, required = false)
	private String iconIds;

	// 覆盖父类属性，以适应@RequestBody方式获取前端Form表单传入参数
	@ApiModelProperty(value = "出生日期。", hidden = false, required = false)
	@JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date birthTime;

	/*
	 * //此处统一除理密码加密解密 BCryptPasswordEncoder encoder =new
	 * BCryptPasswordEncoder();
	 * 
	 * @Override public String getPassword() { return
	 * encoder.encode(super.getPassword().trim()); }
	 * 
	 * @Override public void setPassword(String password) {
	 * super.setPassword(encoder.encode(password)); }
	 * 
	 */

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	public GpDomain getCurrentDomain() {
		return currentDomain;
	}

	public void setCurrentDomain(GpDomain currentDomain) {
		this.currentDomain = currentDomain;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public String getDomainIds() {
		return domainIds;
	}

	public void setDomainIds(String domainIds) {
		this.domainIds = domainIds;
	}

	public String getDomainNames() {
		return domainNames;
	}

	public void setDomainNames(String domainNames) {
		this.domainNames = domainNames;
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

	public Date getBirthTime() {
		return birthTime;
	}

	public void setBirthTime(Date birthTime) {
		this.birthTime = birthTime;
	}

	public String getGenderValue() {
		return genderValue;
	}

	public void setGenderValue(String genderValue) {
		this.genderValue = genderValue;
	}

	public String getIsAdminValue() {
		return isAdminValue;
	}

	public void setIsAdminValue(String isAdminValue) {
		this.isAdminValue = isAdminValue;
	}

	public String getIsDisabledValue() {
		return isDisabledValue;
	}

	public void setIsDisabledValue(String isDisabledValue) {
		this.isDisabledValue = isDisabledValue;
	}

	public String getIsMarriageValue() {
		return isMarriageValue;
	}

	public void setIsMarriageValue(String isMarriageValue) {
		this.isMarriageValue = isMarriageValue;
	}

}
