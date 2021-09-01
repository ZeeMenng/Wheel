package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.zee.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2020/8/11 11:43:45
 * @description 实体类GprUserIconGenEnt，自动生成。用户头像。
 */

public class GprUserIconGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="是否为默认头像。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。",hidden=false,required=false)
    private Byte isDefault;
    @ApiModelProperty(value="用户头像。外键，引用资源（resource）表的主键",hidden=false,required=false)
    private String resourceId;
    @ApiModelProperty(value="系统用户。外键，引用系统用户（user）表的主键",hidden=false,required=false)
    private String userId;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

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
	 * get方法。是否为默认头像。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。
	 */
	public Byte getIsDefault() {
		return this.isDefault;
	}

	/**
	 * set方法。是否为默认头像。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。
	 */
	public void setIsDefault(Byte isDefault) {
		this.isDefault = isDefault;
	}
    
	/**
	 * get方法。用户头像。外键，引用资源（resource）表的主键
	 */
	public String getResourceId() {
		return this.resourceId;
	}

	/**
	 * set方法。用户头像。外键，引用资源（resource）表的主键
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
    
	/**
	 * get方法。系统用户。外键，引用系统用户（user）表的主键
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * set方法。系统用户。外键，引用系统用户（user）表的主键
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    

    //一对多关系中，多端数据列表

}







