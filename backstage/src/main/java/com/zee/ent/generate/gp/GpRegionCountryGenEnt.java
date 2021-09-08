package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/9/8 9:50:23
 * @description 实体类GpRegionCountryGenEnt，自动生成。地区信息。
 */

public class GpRegionCountryGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="二位代码",hidden=false,required=false)
    private String alpha2;
    @ApiModelProperty(value="三位代码",hidden=false,required=false)
    private String alpha3;
    @ApiModelProperty(value="国家面积。单位平方公里（ SquareKilometer sq . km ）。",hidden=false,required=false)
    private Integer area;
    @ApiModelProperty(value="中文名称。",hidden=false,required=false)
    private String chineseName;
    @ApiModelProperty(value="英文名称。",hidden=false,required=false)
    private String englishName;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="是否显示。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。",allowableValues="0,1",hidden=false,required=false)
    private Byte isDisplayCode;
    @ApiModelProperty(value="是否独立主权。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。",allowableValues="0,1",hidden=false,required=false)
    private Byte isIndependentCode;
    @ApiModelProperty(value="ISO 3166-2国际标准代码。",hidden=false,required=false)
    private String iso;
    @ApiModelProperty(value="地区纬度。",hidden=false,required=false)
    private String latitude;
    @ApiModelProperty(value="地区经度。",hidden=false,required=false)
    private String longitude;
    @ApiModelProperty(value="ISO 3166-1三位数字代码。",hidden=false,required=true)
    private String numericKey;
    @ApiModelProperty(value="备注字段。",hidden=false,required=false)
    private String remark;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。二位代码
	 */
	public String getAlpha2() {
		return this.alpha2;
	}

	/**
	 * set方法。二位代码
	 */
	public void setAlpha2(String alpha2) {
		this.alpha2 = alpha2;
	}
    
	/**
	 * get方法。三位代码
	 */
	public String getAlpha3() {
		return this.alpha3;
	}

	/**
	 * set方法。三位代码
	 */
	public void setAlpha3(String alpha3) {
		this.alpha3 = alpha3;
	}
    
	/**
	 * get方法。国家面积。单位平方公里（ SquareKilometer sq . km ）。
	 */
	public Integer getArea() {
		return this.area;
	}

	/**
	 * set方法。国家面积。单位平方公里（ SquareKilometer sq . km ）。
	 */
	public void setArea(Integer area) {
		this.area = area;
	}
    
	/**
	 * get方法。中文名称。
	 */
	public String getChineseName() {
		return this.chineseName;
	}

	/**
	 * set方法。中文名称。
	 */
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
    
	/**
	 * get方法。英文名称。
	 */
	public String getEnglishName() {
		return this.englishName;
	}

	/**
	 * set方法。英文名称。
	 */
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
    
	/**
	 * get方法。主键
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set方法。主键
	 */
	public void setId(String id) {
		this.id = id;
	}
    
	/**
	 * get方法。是否显示。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。
	 */
	public Byte getIsDisplayCode() {
		return this.isDisplayCode;
	}

	/**
	 * set方法。是否显示。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。
	 */
	public void setIsDisplayCode(Byte isDisplayCode) {
		this.isDisplayCode = isDisplayCode;
	}
    
	/**
	 * get方法。是否独立主权。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。
	 */
	public Byte getIsIndependentCode() {
		return this.isIndependentCode;
	}

	/**
	 * set方法。是否独立主权。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。
	 */
	public void setIsIndependentCode(Byte isIndependentCode) {
		this.isIndependentCode = isIndependentCode;
	}
    
	/**
	 * get方法。ISO 3166-2国际标准代码。
	 */
	public String getIso() {
		return this.iso;
	}

	/**
	 * set方法。ISO 3166-2国际标准代码。
	 */
	public void setIso(String iso) {
		this.iso = iso;
	}
    
	/**
	 * get方法。地区纬度。
	 */
	public String getLatitude() {
		return this.latitude;
	}

	/**
	 * set方法。地区纬度。
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
    
	/**
	 * get方法。地区经度。
	 */
	public String getLongitude() {
		return this.longitude;
	}

	/**
	 * set方法。地区经度。
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
    
	/**
	 * get方法。ISO 3166-1三位数字代码。
	 */
	public String getNumericKey() {
		return this.numericKey;
	}

	/**
	 * set方法。ISO 3166-1三位数字代码。
	 */
	public void setNumericKey(String numericKey) {
		this.numericKey = numericKey;
	}
    
	/**
	 * get方法。备注字段。
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set方法。备注字段。
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
    










}







