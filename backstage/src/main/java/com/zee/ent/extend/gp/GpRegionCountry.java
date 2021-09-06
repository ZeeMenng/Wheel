package com.zee.ent.extend.gp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zee.ent.generate.gp.GpRegionCountryGenEnt;

import com.zee.set.annotation.DictionaryConvertAnnotation;
import com.zee.set.serializer.JacksonDictionarySerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2017/12/15 23:42:00
 * @description 扩展自实体类GpRegionCountryGenEnt，可手动更改。国家信息。
 */

@ApiModel(value = "GpRegionCountry", description = "国家信息。")
public class GpRegionCountry extends GpRegionCountryGenEnt {


    @ApiModelProperty(value = "是否显示", hidden = false, required = true)
    @DictionaryConvertAnnotation(typeId = "dc1f9015660bcbcee7f1dfc1a5dea1ea", codeField = "isDisplayCode")
    @JsonSerialize(using = JacksonDictionarySerializer.class, nullsUsing = JacksonDictionarySerializer.class)
    private String isDisplayValue;


    @ApiModelProperty(value = "是否独立主权", hidden = false, required = true)
    @DictionaryConvertAnnotation(typeId = "dc1f9015660bcbcee7f1dfc1a5dea1ea", codeField = "isIndependentCode")
    @JsonSerialize(using = JacksonDictionarySerializer.class, nullsUsing = JacksonDictionarySerializer.class)
    private String isIndependentValue;


    public String getIsDisplayValue() {
        return this.isDisplayValue;
    }

    public void setIsDisplayValue(String isDisplayValue) {

        this.isDisplayValue = isDisplayValue;
    }

    public String getIsIndependentValue() {
        return isIndependentValue;
    }

    public void setIsIndependentValue(String isIndependentValue) {
        this.isIndependentValue = isIndependentValue;
    }
}







