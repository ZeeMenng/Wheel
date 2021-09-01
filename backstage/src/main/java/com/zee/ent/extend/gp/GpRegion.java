package com.zee.ent.extend.gp;

import com.zee.ent.generate.gp.GpRegionGenEnt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2017/12/15 23:42:00
 * @description 扩展自实体类GpRegionGenEnt，可手动更改。地区信息。
 */

@ApiModel(value = "GpRegion", description = "地区信息。")
public class GpRegion extends GpRegionGenEnt {
  
	@ApiModelProperty(value="是否显示，两种类型：是，否。")
    private String isDisplayValue;

	public String getIsDisplayValue() {
		return this.isDisplayValue;
	}

	public void setIsDisplayValue(String isDisplayValue) {
		this.isDisplayValue = isDisplayValue;
	}
	
	@ApiModelProperty(value="父级地区名称。")
    private String fartherName;
	
	public String getFartherName() {
		return this.fartherName;
	}

	public void setFartherName(String fartherName) {
		this.fartherName = fartherName;
	}
	
	@ApiModelProperty(value="区域等级名称。")
    private String regionLevelName;
	
	public String getRegionLevelName() {
		return this.regionLevelName;
	}

	public void setRegionLevelName(String regionLevelName) {
		this.regionLevelName = regionLevelName;
	}
	
	@ApiModelProperty(value="地区类别名称。")
    private String categoryName;
	
	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}







