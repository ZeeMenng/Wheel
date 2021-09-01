package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;
import com.zee.ent.extend.gp.GprConfigDomain;
import com.zee.ent.extend.gp.GprConfigUser;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/2/2 18:48:46
 * @description 实体类GpConfigGenEnt，自动生成。配置项信息。
 */

public class GpConfigGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="记录创建时间。",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="键值。",hidden=false,required=false)
    private String code;
    @ApiModelProperty(value="默认值。",hidden=false,required=false)
    private String defaultValue;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="名称。",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="排列顺序。",hidden=false,required=false)
    private Integer priority;
    @ApiModelProperty(value="备注。",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="编号。",hidden=false,required=false)
    private String serialNo;
    @ApiModelProperty(value="记录最后一次修改时间。",hidden=false,required=false)
    private Date updateTime;
    @ApiModelProperty(value="值类型。",hidden=false,required=false)
    private String valueType;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表
    private ArrayList<GprConfigDomain> gprConfigDomainList;   
    private ArrayList<GprConfigUser> gprConfigUserList;   

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
	 * get方法。键值。
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * set方法。键值。
	 */
	public void setCode(String code) {
		this.code = code;
	}
    
	/**
	 * get方法。默认值。
	 */
	public String getDefaultValue() {
		return this.defaultValue;
	}

	/**
	 * set方法。默认值。
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
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
	 * get方法。排列顺序。
	 */
	public Integer getPriority() {
		return this.priority;
	}

	/**
	 * set方法。排列顺序。
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
    
	/**
	 * get方法。备注。
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set方法。备注。
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
	 * get方法。值类型。
	 */
	public String getValueType() {
		return this.valueType;
	}

	/**
	 * set方法。值类型。
	 */
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
    



	/**
	 * get方法。本表做为父表时，子表实体对象。应用领域配置信息。
	 */
	public ArrayList<GprConfigDomain> getGprConfigDomainList() {
		return this.gprConfigDomainList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。应用领域配置信息。
	 */
	public void setGprConfigDomainList(ArrayList<GprConfigDomain> gprConfigDomainList) {
		this.gprConfigDomainList = gprConfigDomainList;
	}

	/**
	 * get方法。本表做为父表时，子表实体对象。用户配置信息。
	 */
	public ArrayList<GprConfigUser> getGprConfigUserList() {
		return this.gprConfigUserList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。用户配置信息。
	 */
	public void setGprConfigUserList(ArrayList<GprConfigUser> gprConfigUserList) {
		this.gprConfigUserList = gprConfigUserList;
	}








}







