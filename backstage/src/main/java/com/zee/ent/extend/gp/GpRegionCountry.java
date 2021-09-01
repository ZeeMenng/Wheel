package com.zee.ent.extend.gp;

import com.zee.ent.generate.gp.GpRegionCountryGenEnt;

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
	
	 @ApiModelProperty(value="是否显示，两种类型：是，否。",allowableValues="0,1",hidden=false,required=false)
	    private String isDisplayValue;

		public String getIsDisplayValue() {
			return this.isDisplayValue;
		}

		public void setIsDisplayValue(String isDisplayValue) {
			this.isDisplayValue = isDisplayValue;
		}
}







