package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;
import com.zee.ent.extend.gp.GpStation;
import com.zee.ent.extend.gp.GprUserOrganization;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/2/2 18:48:50
 * @description 实体类GpOrganizationGenEnt，自动生成。组织机构。
 */

public class GpOrganizationGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="记录创建时间。",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="地址。",hidden=false,required=false)
    private String address;
    @ApiModelProperty(value="邮箱。",hidden=false,required=false)
    private String email;
    @ApiModelProperty(value="父级组织机构。外键，引用自身组织机构表（module）的主键。",hidden=false,required=false)
    private String fartherId;
    @ApiModelProperty(value="传真。",hidden=false,required=false)
    private String fax;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="模块级别。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义两种级别：1第一级，2第二级。",allowableValues="0,1",hidden=false,required=false)
    private Byte levelCode;
    @ApiModelProperty(value="模块级别。文本，对应数据字典表（dictionary）中的文本字段（text）。目前先定义两种级别：1第一级，2第二级。",hidden=false,required=false)
    private String levelText;
    @ApiModelProperty(value="名称。",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="电话号码。",hidden=false,required=false)
    private String phone;
    @ApiModelProperty(value="邮编。",hidden=false,required=false)
    private String postcode;
    @ApiModelProperty(value="排序字段。",hidden=false,required=false)
    private Integer priority;
    @ApiModelProperty(value="备注字段。",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="机构职能。",hidden=false,required=false)
    private String responsibility;
    @ApiModelProperty(value="编号。",hidden=false,required=false)
    private String serialNo;
    @ApiModelProperty(value="简称。",hidden=false,required=false)
    private String shortName;
    @ApiModelProperty(value="类型。对应数据字典表（dictionary）中的编码字段（code）。目前七种类型：1县政府、2省市双管单位、3镇政府、4合作社、5生产企业、6加工企业、7销售企业,8物流企业。",allowableValues="0,1",hidden=false,required=false)
    private Byte typeCode;
    @ApiModelProperty(value="类型。对应数据字典表（dictionary）中的文本字段（text）。目前七种类型：1县政府、2省市双管单位、3镇政府、4合作社、5生产企业、6加工企业、7销售企业。",hidden=false,required=false)
    private String typeText;
    @ApiModelProperty(value="记录最后一次修改时间。",hidden=false,required=false)
    private Date updateTime;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表
    private ArrayList<GpStation> gpStationList;   
    private ArrayList<GprUserOrganization> gprUserOrganizationList;   

    //父子表均为自身时


	/**
	 * get方法。记录创建时间。
	 */
	public Date getAddTime() {
		return this.addTime;
	}

	/**
	 * set方法。记录创建时间。
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
    
	/**
	 * get方法。地址。
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * set方法。地址。
	 */
	public void setAddress(String address) {
		this.address = address;
	}
    
	/**
	 * get方法。邮箱。
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * set方法。邮箱。
	 */
	public void setEmail(String email) {
		this.email = email;
	}
    
	/**
	 * get方法。父级组织机构。外键，引用自身组织机构表（module）的主键。
	 */
	public String getFartherId() {
		return this.fartherId;
	}

	/**
	 * set方法。父级组织机构。外键，引用自身组织机构表（module）的主键。
	 */
	public void setFartherId(String fartherId) {
		this.fartherId = fartherId;
	}
    
	/**
	 * get方法。传真。
	 */
	public String getFax() {
		return this.fax;
	}

	/**
	 * set方法。传真。
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
    
	/**
	 * get方法。主键。
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set方法。主键。
	 */
	public void setId(String id) {
		this.id = id;
	}
    
	/**
	 * get方法。模块级别。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义两种级别：1第一级，2第二级。
	 */
	public Byte getLevelCode() {
		return this.levelCode;
	}

	/**
	 * set方法。模块级别。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义两种级别：1第一级，2第二级。
	 */
	public void setLevelCode(Byte levelCode) {
		this.levelCode = levelCode;
	}
    
	/**
	 * get方法。模块级别。文本，对应数据字典表（dictionary）中的文本字段（text）。目前先定义两种级别：1第一级，2第二级。
	 */
	public String getLevelText() {
		return this.levelText;
	}

	/**
	 * set方法。模块级别。文本，对应数据字典表（dictionary）中的文本字段（text）。目前先定义两种级别：1第一级，2第二级。
	 */
	public void setLevelText(String levelText) {
		this.levelText = levelText;
	}
    
	/**
	 * get方法。名称。
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。名称。
	 */
	public void setName(String name) {
		this.name = name;
	}
    
	/**
	 * get方法。电话号码。
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * set方法。电话号码。
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
    
	/**
	 * get方法。邮编。
	 */
	public String getPostcode() {
		return this.postcode;
	}

	/**
	 * set方法。邮编。
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
    
	/**
	 * get方法。排序字段。
	 */
	public Integer getPriority() {
		return this.priority;
	}

	/**
	 * set方法。排序字段。
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
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
    
	/**
	 * get方法。机构职能。
	 */
	public String getResponsibility() {
		return this.responsibility;
	}

	/**
	 * set方法。机构职能。
	 */
	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}
    
	/**
	 * get方法。编号。
	 */
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * set方法。编号。
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
    
	/**
	 * get方法。简称。
	 */
	public String getShortName() {
		return this.shortName;
	}

	/**
	 * set方法。简称。
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
    
	/**
	 * get方法。类型。对应数据字典表（dictionary）中的编码字段（code）。目前七种类型：1县政府、2省市双管单位、3镇政府、4合作社、5生产企业、6加工企业、7销售企业,8物流企业。
	 */
	public Byte getTypeCode() {
		return this.typeCode;
	}

	/**
	 * set方法。类型。对应数据字典表（dictionary）中的编码字段（code）。目前七种类型：1县政府、2省市双管单位、3镇政府、4合作社、5生产企业、6加工企业、7销售企业,8物流企业。
	 */
	public void setTypeCode(Byte typeCode) {
		this.typeCode = typeCode;
	}
    
	/**
	 * get方法。类型。对应数据字典表（dictionary）中的文本字段（text）。目前七种类型：1县政府、2省市双管单位、3镇政府、4合作社、5生产企业、6加工企业、7销售企业。
	 */
	public String getTypeText() {
		return this.typeText;
	}

	/**
	 * set方法。类型。对应数据字典表（dictionary）中的文本字段（text）。目前七种类型：1县政府、2省市双管单位、3镇政府、4合作社、5生产企业、6加工企业、7销售企业。
	 */
	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}
    
	/**
	 * get方法。记录最后一次修改时间。
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * set方法。记录最后一次修改时间。
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    



	/**
	 * get方法。本表做为父表时，子表实体对象。岗位。
	 */
	public ArrayList<GpStation> getGpStationList() {
		return this.gpStationList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。岗位。
	 */
	public void setGpStationList(ArrayList<GpStation> gpStationList) {
		this.gpStationList = gpStationList;
	}

	/**
	 * get方法。本表做为父表时，子表实体对象。用户所属组织机构。
	 */
	public ArrayList<GprUserOrganization> getGprUserOrganizationList() {
		return this.gprUserOrganizationList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。用户所属组织机构。
	 */
	public void setGprUserOrganizationList(ArrayList<GprUserOrganization> gprUserOrganizationList) {
		this.gprUserOrganizationList = gprUserOrganizationList;
	}








}







