package com.zee.ent.extend.gp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zee.ent.generate.gp.GpInterfaceGenEnt;
import com.zee.set.annotation.DictionaryConvertAnnotation;
import com.zee.set.serializer.JacksonDictionarySerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2017/12/15 23:42:00
 * @description 扩展自实体类GpInterfaceGenEnt，可手动更改。系统接口。
 */

@ApiModel(value = "GpInterface", description = "系统接口。")
public class GpInterface extends GpInterfaceGenEnt {
  
	@ApiModelProperty(value="应用领域",hidden=false,required=false)
    private String domainName;
	
	@ApiModelProperty(value="是否为公共接口",hidden=false,required=false)
	@JsonSerialize(using = JacksonDictionarySerializer.class)
	@DictionaryConvertAnnotation(typeId = "dc1f9015660bcbcee7f1dfc1a5dea1ea")
    private String isPublicValue;
	
    @ApiModelProperty(value="接口调用方式。对应数据字典表（dictionary）中的编码字段（code）。目前两种类型：1GET，2POST。",allowableValues="0,1",hidden=false,required=false)
	@JsonSerialize(using = JacksonDictionarySerializer.class)
	@DictionaryConvertAnnotation(typeId = "d9e634b861944c5f8c63544d16786dad")
    private String typeValue;

	

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getIsPublicValue() {
		return super.getIsPublicCode().toString();
	}

	public void setIsPublicValue(String isPublicValue) {
		this.isPublicValue = isPublicValue;
	}
	
	public String getTypeValue() {
		return super.getTypeCode().toString();
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}
}







