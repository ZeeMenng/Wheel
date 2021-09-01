package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/2/2 18:48:51
 * @description 实体类GpRegionGenEnt，自动生成。地区信息。
 */

public class GpRegionGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="区域面积大小，单位平方公里（ SquareKilometer sq . km ）。",hidden=false,required=false)
    private Integer area;
    @ApiModelProperty(value="地区类别字典，用于区分Region表中记录的类型。0是国家，1是直辖市，2是省，3是自治区，4是特别行政区，5是地级市，6是直辖市下的区，7是县级市（县级市不好区分，可以都暂时默认为7），8是县。",hidden=false,required=false)
    private Byte category;
    @ApiModelProperty(value="地区编码。",hidden=false,required=false)
    private String code;
    @ApiModelProperty(value="父级地区，引用父级行政机构的主键。",allowableValues="0,1",hidden=false,required=false)
    private String fartherCode;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="是否显示。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。",allowableValues="0,1",hidden=false,required=false)
    private Byte isDisplayCode;
    @ApiModelProperty(value="地区纬度。",hidden=false,required=false)
    private String latitude;
    @ApiModelProperty(value="地区经度。",hidden=false,required=false)
    private String longitude;
    @ApiModelProperty(value="地区名称。",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="区域等级。国家为第一级，直辖市、省、自治区、特别行政区为第二级，地级市、直辖市下的区为第三级，县级市、县为第四级、乡镇为第五级",hidden=false,required=false)
    private Byte regionLevel;
    @ApiModelProperty(value="备注字段。",hidden=false,required=false)
    private String remark;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。区域面积大小，单位平方公里（ SquareKilometer sq . km ）。
	 */
	public Integer getArea() {
		return this.area;
	}

	/**
	 * set方法。区域面积大小，单位平方公里（ SquareKilometer sq . km ）。
	 */
	public void setArea(Integer area) {
		this.area = area;
	}
    
	/**
	 * get方法。地区类别字典，用于区分Region表中记录的类型。0是国家，1是直辖市，2是省，3是自治区，4是特别行政区，5是地级市，6是直辖市下的区，7是县级市（县级市不好区分，可以都暂时默认为7），8是县。
	 */
	public Byte getCategory() {
		return this.category;
	}

	/**
	 * set方法。地区类别字典，用于区分Region表中记录的类型。0是国家，1是直辖市，2是省，3是自治区，4是特别行政区，5是地级市，6是直辖市下的区，7是县级市（县级市不好区分，可以都暂时默认为7），8是县。
	 */
	public void setCategory(Byte category) {
		this.category = category;
	}
    
	/**
	 * get方法。地区编码。
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * set方法。地区编码。
	 */
	public void setCode(String code) {
		this.code = code;
	}
    
	/**
	 * get方法。父级地区，引用父级行政机构的主键。
	 */
	public String getFartherCode() {
		return this.fartherCode;
	}

	/**
	 * set方法。父级地区，引用父级行政机构的主键。
	 */
	public void setFartherCode(String fartherCode) {
		this.fartherCode = fartherCode;
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
	 * get方法。地区名称。
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。地区名称。
	 */
	public void setName(String name) {
		this.name = name;
	}
    
	/**
	 * get方法。区域等级。国家为第一级，直辖市、省、自治区、特别行政区为第二级，地级市、直辖市下的区为第三级，县级市、县为第四级、乡镇为第五级
	 */
	public Byte getRegionLevel() {
		return this.regionLevel;
	}

	/**
	 * set方法。区域等级。国家为第一级，直辖市、省、自治区、特别行政区为第二级，地级市、直辖市下的区为第三级，县级市、县为第四级、乡镇为第五级
	 */
	public void setRegionLevel(Byte regionLevel) {
		this.regionLevel = regionLevel;
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







