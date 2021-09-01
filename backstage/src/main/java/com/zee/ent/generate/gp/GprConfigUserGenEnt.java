package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;
import com.zee.ent.extend.gp.GpUser;
import com.zee.ent.extend.gp.GpConfig;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/2/2 18:48:42
 * @description 实体类GprConfigUserGenEnt，自动生成。用户配置信息。
 */

public class GprConfigUserGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="记录创建时间。",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="配置项。外键，引用配置项表（config）的主键。",hidden=false,required=false)
    private String configId;
    @ApiModelProperty(value="配置项值。",hidden=false,required=false)
    private String configValue;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="记录最后一次修改时间。",hidden=false,required=false)
    private Date updateTime;
    @ApiModelProperty(value="用户。外键，引用用户表（user）的主键。",hidden=false,required=false)
    private String userId;

   //本表做为子表时，父表实体对象
    private  GpUser gpUser;
    private  GpConfig gpConfig;

    //本表做为父表时，子表数据列表

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
	 * get方法。配置项。外键，引用配置项表（config）的主键。
	 */
	public String getConfigId() {
		return this.configId;
	}

	/**
	 * set方法。配置项。外键，引用配置项表（config）的主键。
	 */
	public void setConfigId(String configId) {
		this.configId = configId;
	}
    
	/**
	 * get方法。配置项值。
	 */
	public String getConfigValue() {
		return this.configValue;
	}

	/**
	 * set方法。配置项值。
	 */
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
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
	 * get方法。用户。外键，引用用户表（user）的主键。
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * set方法。用户。外键，引用用户表（user）的主键。
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    






	/**
	 * get方法。本表做为子表时，父表实体对象。系统用户。
	 */
	public GpUser getGpUser() {
		return this.gpUser;
	}

	/**
	 * set方法。本表做为子表时，父表实体对象。系统用户。
	 */
	public void setGpUser(GpUser gpUser) {
		this.gpUser = gpUser;
	}

	/**
	 * get方法。本表做为子表时，父表实体对象。配置项信息。
	 */
	public GpConfig getGpConfig() {
		return this.gpConfig;
	}

	/**
	 * set方法。本表做为子表时，父表实体对象。配置项信息。
	 */
	public void setGpConfig(GpConfig gpConfig) {
		this.gpConfig = gpConfig;
	}





}







