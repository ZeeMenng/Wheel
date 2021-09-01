package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;
import com.zee.ent.extend.gp.GpControl;
import com.zee.ent.extend.gp.GpRole;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/2/2 18:48:43
 * @description 实体类GprRoleControlGenEnt，自动生成。角色拥有的控件权限。
 */

public class GprRoleControlGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="系统控件。外键，引用系统控件表（control）的主键。",hidden=false,required=false)
    private String controlId;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="是否有权。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。",allowableValues="0,1",hidden=false,required=false)
    private Byte isEnableCode;
    @ApiModelProperty(value="系统角色。外键，引用应用角色表（role）的主键。",hidden=false,required=false)
    private String roleId;

   //本表做为子表时，父表实体对象
    private  GpControl gpControl;
    private  GpRole gpRole;

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。系统控件。外键，引用系统控件表（control）的主键。
	 */
	public String getControlId() {
		return this.controlId;
	}

	/**
	 * set方法。系统控件。外键，引用系统控件表（control）的主键。
	 */
	public void setControlId(String controlId) {
		this.controlId = controlId;
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
	 * get方法。是否有权。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。
	 */
	public Byte getIsEnableCode() {
		return this.isEnableCode;
	}

	/**
	 * set方法。是否有权。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。
	 */
	public void setIsEnableCode(Byte isEnableCode) {
		this.isEnableCode = isEnableCode;
	}
    
	/**
	 * get方法。系统角色。外键，引用应用角色表（role）的主键。
	 */
	public String getRoleId() {
		return this.roleId;
	}

	/**
	 * set方法。系统角色。外键，引用应用角色表（role）的主键。
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
    






	/**
	 * get方法。本表做为子表时，父表实体对象。系统控件。
	 */
	public GpControl getGpControl() {
		return this.gpControl;
	}

	/**
	 * set方法。本表做为子表时，父表实体对象。系统控件。
	 */
	public void setGpControl(GpControl gpControl) {
		this.gpControl = gpControl;
	}

	/**
	 * get方法。本表做为子表时，父表实体对象。系统角色。
	 */
	public GpRole getGpRole() {
		return this.gpRole;
	}

	/**
	 * set方法。本表做为子表时，父表实体对象。系统角色。
	 */
	public void setGpRole(GpRole gpRole) {
		this.gpRole = gpRole;
	}





}







