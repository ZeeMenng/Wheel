package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;
import com.zee.ent.extend.gp.GprRoleControl;
import com.zee.ent.extend.gp.GprRoleDomain;
import com.zee.ent.extend.gp.GprRoleInterface;
import com.zee.ent.extend.gp.GprRoleModule;
import com.zee.ent.extend.gp.GprRolePage;
import com.zee.ent.extend.gp.GprUserRole;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/2/2 18:48:51
 * @description 实体类GpRoleGenEnt，自动生成。系统角色。
 */

public class GpRoleGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="记录创建时间。",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="角色名称。",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="备注字段。",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="记录最后一次修改时间。",hidden=false,required=false)
    private Date updateTime;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表
    private ArrayList<GprRoleControl> gprRoleControlList;   
    private ArrayList<GprRoleDomain> gprRoleDomainList;   
    private ArrayList<GprRoleInterface> gprRoleInterfaceList;   
    private ArrayList<GprRoleModule> gprRoleModuleList;   
    private ArrayList<GprRolePage> gprRolePageList;   
    private ArrayList<GprUserRole> gprUserRoleList;   

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
	 * get方法。角色名称。
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。角色名称。
	 */
	public void setName(String name) {
		this.name = name;
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
	 * get方法。本表做为父表时，子表实体对象。角色拥有的控件权限。
	 */
	public ArrayList<GprRoleControl> getGprRoleControlList() {
		return this.gprRoleControlList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。角色拥有的控件权限。
	 */
	public void setGprRoleControlList(ArrayList<GprRoleControl> gprRoleControlList) {
		this.gprRoleControlList = gprRoleControlList;
	}

	/**
	 * get方法。本表做为父表时，子表实体对象。角色拥有的功能模块权限。
	 */
	public ArrayList<GprRoleDomain> getGprRoleDomainList() {
		return this.gprRoleDomainList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。角色拥有的功能模块权限。
	 */
	public void setGprRoleDomainList(ArrayList<GprRoleDomain> gprRoleDomainList) {
		this.gprRoleDomainList = gprRoleDomainList;
	}

	/**
	 * get方法。本表做为父表时，子表实体对象。角色拥有的接口权限。
	 */
	public ArrayList<GprRoleInterface> getGprRoleInterfaceList() {
		return this.gprRoleInterfaceList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。角色拥有的接口权限。
	 */
	public void setGprRoleInterfaceList(ArrayList<GprRoleInterface> gprRoleInterfaceList) {
		this.gprRoleInterfaceList = gprRoleInterfaceList;
	}

	/**
	 * get方法。本表做为父表时，子表实体对象。角色拥有的功能模块权限。
	 */
	public ArrayList<GprRoleModule> getGprRoleModuleList() {
		return this.gprRoleModuleList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。角色拥有的功能模块权限。
	 */
	public void setGprRoleModuleList(ArrayList<GprRoleModule> gprRoleModuleList) {
		this.gprRoleModuleList = gprRoleModuleList;
	}

	/**
	 * get方法。本表做为父表时，子表实体对象。角色拥有的页面权限。
	 */
	public ArrayList<GprRolePage> getGprRolePageList() {
		return this.gprRolePageList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。角色拥有的页面权限。
	 */
	public void setGprRolePageList(ArrayList<GprRolePage> gprRolePageList) {
		this.gprRolePageList = gprRolePageList;
	}

	/**
	 * get方法。本表做为父表时，子表实体对象。用户拥有的角色。
	 */
	public ArrayList<GprUserRole> getGprUserRoleList() {
		return this.gprUserRoleList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。用户拥有的角色。
	 */
	public void setGprUserRoleList(ArrayList<GprUserRole> gprUserRoleList) {
		this.gprUserRoleList = gprUserRoleList;
	}








}







