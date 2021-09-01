package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;
import com.zee.ent.extend.gp.GpLoginLog;
import com.zee.ent.extend.gp.GpMessage;
import com.zee.ent.extend.gp.GpOperLogLogin;
import com.zee.ent.extend.gp.GpToken;
import com.zee.ent.extend.gp.GprDomainUser;
import com.zee.ent.extend.gp.GprMessageUser;
import com.zee.ent.extend.gp.GprUserOrganization;
import com.zee.ent.extend.gp.GprUserRole;
import com.zee.ent.extend.gp.GprUserStation;
import com.zee.ent.extend.gp.GprConfigUser;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/2/2 18:48:52
 * @description 实体类GpUserGenEnt，自动生成。系统用户。
 */

public class GpUserGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="记录创建时间。",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="年龄。",hidden=false,required=false)
    private Byte age;
    @ApiModelProperty(value="出生日期。",hidden=false,required=false)
    private Date birthTime;
    @ApiModelProperty(value="邮箱。",hidden=false,required=false)
    private String email;
    @ApiModelProperty(value="性别。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0男，1女。",allowableValues="0,1",hidden=false,required=false)
    private Byte genderCode;
    @ApiModelProperty(value="头像图片存放路径。",hidden=false,required=false)
    private String icon;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="是否管理员。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。",allowableValues="0,1",hidden=false,required=true)
    private Byte isAdminCode;
    @ApiModelProperty(value="是否禁用。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。",allowableValues="0,1",hidden=false,required=true)
    private Byte isDisabledCode;
    @ApiModelProperty(value="是否已婚。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。",allowableValues="0,1",hidden=false,required=false)
    private Byte isMarriageCode;
    @ApiModelProperty(value="最后登录IP。",hidden=false,required=false)
    private String lastLoginIp;
    @ApiModelProperty(value="最后登录时间。",hidden=false,required=false)
    private Date lastLoginTime;
    @ApiModelProperty(value="登录次数。",hidden=false,required=false)
    private Integer loginCount;
    @ApiModelProperty(value="登录密码。",hidden=false,required=false)
    private String password;
    @ApiModelProperty(value="电话号码。",hidden=false,required=false)
    private String phone;
    @ApiModelProperty(value="QQ号",hidden=false,required=false)
    private String qq;
    @ApiModelProperty(value="真实姓名。",hidden=false,required=false)
    private String realName;
    @ApiModelProperty(value="注册IP。",hidden=false,required=false)
    private String registerIp;
    @ApiModelProperty(value="备注字段。",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="记录最后一次修改时间。",hidden=false,required=false)
    private Date updateTime;
    @ApiModelProperty(value="登录账号。",hidden=false,required=true)
    private String userName;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表
    private ArrayList<GpLoginLog> gpLoginLogList;   
    private ArrayList<GpMessage> gpMessageList;   
    private ArrayList<GpOperLogLogin> gpOperLogLoginList;   
    private ArrayList<GpToken> gpTokenList;   
    private ArrayList<GprDomainUser> gprDomainUserList;   
    private ArrayList<GprMessageUser> gprMessageUserList;   
    private ArrayList<GprUserOrganization> gprUserOrganizationList;   
    private ArrayList<GprUserRole> gprUserRoleList;   
    private ArrayList<GprUserStation> gprUserStationList;   
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
	 * get方法。年龄。
	 */
	public Byte getAge() {
		return this.age;
	}

	/**
	 * set方法。年龄。
	 */
	public void setAge(Byte age) {
		this.age = age;
	}
    
	/**
	 * get方法。出生日期。
	 */
	public Date getBirthTime() {
		return this.birthTime;
	}

	/**
	 * set方法。出生日期。
	 */
	public void setBirthTime(Date birthTime) {
		this.birthTime = birthTime;
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
	 * get方法。性别。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0男，1女。
	 */
	public Byte getGenderCode() {
		return this.genderCode;
	}

	/**
	 * set方法。性别。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0男，1女。
	 */
	public void setGenderCode(Byte genderCode) {
		this.genderCode = genderCode;
	}
    
	/**
	 * get方法。头像图片存放路径。
	 */
	public String getIcon() {
		return this.icon;
	}

	/**
	 * set方法。头像图片存放路径。
	 */
	public void setIcon(String icon) {
		this.icon = icon;
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
	 * get方法。是否管理员。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。
	 */
	public Byte getIsAdminCode() {
		return this.isAdminCode;
	}

	/**
	 * set方法。是否管理员。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。
	 */
	public void setIsAdminCode(Byte isAdminCode) {
		this.isAdminCode = isAdminCode;
	}
    
	/**
	 * get方法。是否禁用。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。
	 */
	public Byte getIsDisabledCode() {
		return this.isDisabledCode;
	}

	/**
	 * set方法。是否禁用。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。
	 */
	public void setIsDisabledCode(Byte isDisabledCode) {
		this.isDisabledCode = isDisabledCode;
	}
    
	/**
	 * get方法。是否已婚。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。
	 */
	public Byte getIsMarriageCode() {
		return this.isMarriageCode;
	}

	/**
	 * set方法。是否已婚。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。
	 */
	public void setIsMarriageCode(Byte isMarriageCode) {
		this.isMarriageCode = isMarriageCode;
	}
    
	/**
	 * get方法。最后登录IP。
	 */
	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	/**
	 * set方法。最后登录IP。
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
    
	/**
	 * get方法。最后登录时间。
	 */
	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	/**
	 * set方法。最后登录时间。
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
    
	/**
	 * get方法。登录次数。
	 */
	public Integer getLoginCount() {
		return this.loginCount;
	}

	/**
	 * set方法。登录次数。
	 */
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
    
	/**
	 * get方法。登录密码。
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * set方法。登录密码。
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * get方法。QQ号
	 */
	public String getQq() {
		return this.qq;
	}

	/**
	 * set方法。QQ号
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}
    
	/**
	 * get方法。真实姓名。
	 */
	public String getRealName() {
		return this.realName;
	}

	/**
	 * set方法。真实姓名。
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
    
	/**
	 * get方法。注册IP。
	 */
	public String getRegisterIp() {
		return this.registerIp;
	}

	/**
	 * set方法。注册IP。
	 */
	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
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
	 * get方法。登录账号。
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * set方法。登录账号。
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * get方法。本表做为父表时，子表实体对象。系统消息。
	 */
	public ArrayList<GpMessage> getGpMessageList() {
		return this.gpMessageList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。系统消息。
	 */
	public void setGpMessageList(ArrayList<GpMessage> gpMessageList) {
		this.gpMessageList = gpMessageList;
	}

	/**
	 * get方法。本表做为父表时，子表实体对象。登录用户操作日志。
	 */
	public ArrayList<GpOperLogLogin> getGpOperLogLoginList() {
		return this.gpOperLogLoginList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。登录用户操作日志。
	 */
	public void setGpOperLogLoginList(ArrayList<GpOperLogLogin> gpOperLogLoginList) {
		this.gpOperLogLoginList = gpOperLogLoginList;
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
	 * get方法。本表做为父表时，子表实体对象。消息队列。
	 */
	public ArrayList<GprMessageUser> getGprMessageUserList() {
		return this.gprMessageUserList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。消息队列。
	 */
	public void setGprMessageUserList(ArrayList<GprMessageUser> gprMessageUserList) {
		this.gprMessageUserList = gprMessageUserList;
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







