package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;
import com.zee.ent.extend.gp.GpOrganization;
import com.zee.ent.extend.gp.GprUserStation;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/2/2 18:48:52
 * @description 实体类GpStationGenEnt，自动生成。岗位。
 */

public class GpStationGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="记录创建时间。",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="名称。",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="所属组织机构。外键，引用系统组织机构表（organization）的主键。",hidden=false,required=false)
    private String organizationId;
    @ApiModelProperty(value="备注字段。",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="机构职能。",hidden=false,required=false)
    private String responsibility;
    @ApiModelProperty(value="编号。",hidden=false,required=false)
    private String serialNo;
    @ApiModelProperty(value="记录最后一次修改时间。",hidden=false,required=false)
    private Date updateTime;

   //本表做为子表时，父表实体对象
    private  GpOrganization gpOrganization;

    //本表做为父表时，子表数据列表
    private ArrayList<GprUserStation> gprUserStationList;   

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
	 * get方法。所属组织机构。外键，引用系统组织机构表（organization）的主键。
	 */
	public String getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * set方法。所属组织机构。外键，引用系统组织机构表（organization）的主键。
	 */
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
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
	 * get方法。本表做为父表时，子表实体对象。用户所属岗位。
	 */
	public ArrayList<GprUserStation> getGprUserStationList() {
		return this.gprUserStationList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。用户所属岗位。
	 */
	public void setGprUserStationList(ArrayList<GprUserStation> gprUserStationList) {
		this.gprUserStationList = gprUserStationList;
	}




	/**
	 * get方法。本表做为子表时，父表实体对象。组织机构。
	 */
	public GpOrganization getGpOrganization() {
		return this.gpOrganization;
	}

	/**
	 * set方法。本表做为子表时，父表实体对象。组织机构。
	 */
	public void setGpOrganization(GpOrganization gpOrganization) {
		this.gpOrganization = gpOrganization;
	}





}







