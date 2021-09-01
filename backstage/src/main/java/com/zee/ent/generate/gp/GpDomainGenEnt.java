package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;
import com.zee.ent.extend.gp.GpControl;
import com.zee.ent.extend.gp.GpInterface;
import com.zee.ent.extend.gp.GpLoginLog;
import com.zee.ent.extend.gp.GpModule;
import com.zee.ent.extend.gp.GpOperLog;
import com.zee.ent.extend.gp.GpPage;
import com.zee.ent.extend.gp.GpToken;
import com.zee.ent.extend.gp.GprDomainMessage;
import com.zee.ent.extend.gp.GprDomainUser;
import com.zee.ent.extend.gp.GprRoleDomain;
import com.zee.ent.extend.gp.GprConfigDomain;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/2/2 18:48:47
 * @description 实体类GpDomainGenEnt，自动生成。应用领域。
 */

public class GpDomainGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="记录创建时间。",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="领域域名。",hidden=false,required=false)
    private String com;
    @ApiModelProperty(value="领域图标。",hidden=false,required=false)
    private String iconResource;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="领域名称。",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="备注字段。",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="领域编号。",hidden=false,required=false)
    private String serialNo;
    @ApiModelProperty(value="记录最后一次修改时间。",hidden=false,required=false)
    private Date updateTime;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表
    private ArrayList<GpControl> gpControlList;   
    private ArrayList<GpInterface> gpInterfaceList;   
    private ArrayList<GpLoginLog> gpLoginLogList;   
    private ArrayList<GpModule> gpModuleList;   
    private ArrayList<GpOperLog> gpOperLogList;   
    private ArrayList<GpPage> gpPageList;   
    private ArrayList<GpToken> gpTokenList;   
    private ArrayList<GprDomainMessage> gprDomainMessageList;   
    private ArrayList<GprDomainUser> gprDomainUserList;   
    private ArrayList<GprRoleDomain> gprRoleDomainList;   
    private ArrayList<GprConfigDomain> gprConfigDomainList;   

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
	 * get方法。领域域名。
	 */
	public String getCom() {
		return this.com;
	}

	/**
	 * set方法。领域域名。
	 */
	public void setCom(String com) {
		this.com = com;
	}
    
	/**
	 * get方法。领域图标。
	 */
	public String getIconResource() {
		return this.iconResource;
	}

	/**
	 * set方法。领域图标。
	 */
	public void setIconResource(String iconResource) {
		this.iconResource = iconResource;
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
	 * get方法。领域名称。
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。领域名称。
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
	 * get方法。领域编号。
	 */
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * set方法。领域编号。
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
	 * get方法。本表做为父表时，子表实体对象。系统控件。
	 */
	public ArrayList<GpControl> getGpControlList() {
		return this.gpControlList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。系统控件。
	 */
	public void setGpControlList(ArrayList<GpControl> gpControlList) {
		this.gpControlList = gpControlList;
	}

	/**
	 * get方法。本表做为父表时，子表实体对象。系统接口。
	 */
	public ArrayList<GpInterface> getGpInterfaceList() {
		return this.gpInterfaceList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。系统接口。
	 */
	public void setGpInterfaceList(ArrayList<GpInterface> gpInterfaceList) {
		this.gpInterfaceList = gpInterfaceList;
	}

	/**
	 * get方法。本表做为父表时，子表实体对象。登录日志。
	 */
	public ArrayList<GpLoginLog> getGpLoginLogList() {
		return this.gpLoginLogList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。登录日志。
	 */
	public void setGpLoginLogList(ArrayList<GpLoginLog> gpLoginLogList) {
		this.gpLoginLogList = gpLoginLogList;
	}

	/**
	 * get方法。本表做为父表时，子表实体对象。功能模块。
	 */
	public ArrayList<GpModule> getGpModuleList() {
		return this.gpModuleList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。功能模块。
	 */
	public void setGpModuleList(ArrayList<GpModule> gpModuleList) {
		this.gpModuleList = gpModuleList;
	}

	/**
	 * get方法。本表做为父表时，子表实体对象。操作日志。
	 */
	public ArrayList<GpOperLog> getGpOperLogList() {
		return this.gpOperLogList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。操作日志。
	 */
	public void setGpOperLogList(ArrayList<GpOperLog> gpOperLogList) {
		this.gpOperLogList = gpOperLogList;
	}

	/**
	 * get方法。本表做为父表时，子表实体对象。系统页面。
	 */
	public ArrayList<GpPage> getGpPageList() {
		return this.gpPageList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。系统页面。
	 */
	public void setGpPageList(ArrayList<GpPage> gpPageList) {
		this.gpPageList = gpPageList;
	}

	/**
	 * get方法。本表做为父表时，子表实体对象。token信息。
	 */
	public ArrayList<GpToken> getGpTokenList() {
		return this.gpTokenList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。token信息。
	 */
	public void setGpTokenList(ArrayList<GpToken> gpTokenList) {
		this.gpTokenList = gpTokenList;
	}

	/**
	 * get方法。本表做为父表时，子表实体对象。应用领域的站内信。
	 */
	public ArrayList<GprDomainMessage> getGprDomainMessageList() {
		return this.gprDomainMessageList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。应用领域的站内信。
	 */
	public void setGprDomainMessageList(ArrayList<GprDomainMessage> gprDomainMessageList) {
		this.gprDomainMessageList = gprDomainMessageList;
	}

	/**
	 * get方法。本表做为父表时，子表实体对象。应用领域拥有的用户。
	 */
	public ArrayList<GprDomainUser> getGprDomainUserList() {
		return this.gprDomainUserList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。应用领域拥有的用户。
	 */
	public void setGprDomainUserList(ArrayList<GprDomainUser> gprDomainUserList) {
		this.gprDomainUserList = gprDomainUserList;
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








}







