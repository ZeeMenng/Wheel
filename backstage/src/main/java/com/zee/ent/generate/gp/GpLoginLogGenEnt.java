package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;
import com.zee.ent.extend.gp.GpDomain;
import com.zee.ent.extend.gp.GpUser;
import com.zee.ent.extend.gp.GpOperLogLogin;
import com.zee.ent.extend.gp.GpToken;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/2/2 18:48:48
 * @description 实体类GpLoginLogGenEnt，自动生成。登录日志。
 */

public class GpLoginLogGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="浏览器版本。",hidden=false,required=false)
    private String browser;
    @ApiModelProperty(value="应用领域。外键，引用应用领域表（domain）的主键。",hidden=false,required=false)
    private String domainId;
    @ApiModelProperty(value="登录时长。单位秒。",hidden=false,required=false)
    private Integer duration;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="IP地址。",hidden=false,required=false)
    private String ip;
    @ApiModelProperty(value="IP归属地。",hidden=false,required=false)
    private String ipAddress;
    @ApiModelProperty(value="登录是否成功。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。",allowableValues="0,1",hidden=false,required=false)
    private Byte isSuccessCode;
    @ApiModelProperty(value="登录时间。",hidden=false,required=false)
    private Date loginTime;
    @ApiModelProperty(value="退出时间。",hidden=false,required=false)
    private Date logoutTime;
    @ApiModelProperty(value="退出方式。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，四种类型：0其它，1Token过期，2主动退出，3被动退出。",allowableValues="0,1",hidden=false,required=false)
    private Byte logoutTypeCode;
    @ApiModelProperty(value="操作系统。",hidden=false,required=false)
    private String os;
    @ApiModelProperty(value="备注字段。",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="屏幕分辨率。",hidden=false,required=false)
    private String resolution;
    @ApiModelProperty(value="系统用户。外键，引用系统用户表（user）的主键。",hidden=false,required=false)
    private String userId;
    @ApiModelProperty(value="登录用户名。",hidden=false,required=false)
    private String userName;

   //本表做为子表时，父表实体对象
    private  GpDomain gpDomain;
    private  GpUser gpUser;

    //本表做为父表时，子表数据列表
    private ArrayList<GpOperLogLogin> gpOperLogLoginList;   
    private ArrayList<GpToken> gpTokenList;   

    //父子表均为自身时


	/**
	 * get方法。浏览器版本。
	 */
	public String getBrowser() {
		return this.browser;
	}

	/**
	 * set方法。浏览器版本。
	 */
	public void setBrowser(String browser) {
		this.browser = browser;
	}
    
	/**
	 * get方法。应用领域。外键，引用应用领域表（domain）的主键。
	 */
	public String getDomainId() {
		return this.domainId;
	}

	/**
	 * set方法。应用领域。外键，引用应用领域表（domain）的主键。
	 */
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
    
	/**
	 * get方法。登录时长。单位秒。
	 */
	public Integer getDuration() {
		return this.duration;
	}

	/**
	 * set方法。登录时长。单位秒。
	 */
	public void setDuration(Integer duration) {
		this.duration = duration;
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
	 * get方法。IP地址。
	 */
	public String getIp() {
		return this.ip;
	}

	/**
	 * set方法。IP地址。
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
    
	/**
	 * get方法。IP归属地。
	 */
	public String getIpAddress() {
		return this.ipAddress;
	}

	/**
	 * set方法。IP归属地。
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
    
	/**
	 * get方法。登录是否成功。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。
	 */
	public Byte getIsSuccessCode() {
		return this.isSuccessCode;
	}

	/**
	 * set方法。登录是否成功。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。
	 */
	public void setIsSuccessCode(Byte isSuccessCode) {
		this.isSuccessCode = isSuccessCode;
	}
    
	/**
	 * get方法。登录时间。
	 */
	public Date getLoginTime() {
		return this.loginTime;
	}

	/**
	 * set方法。登录时间。
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
    
	/**
	 * get方法。退出时间。
	 */
	public Date getLogoutTime() {
		return this.logoutTime;
	}

	/**
	 * set方法。退出时间。
	 */
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}
    
	/**
	 * get方法。退出方式。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，四种类型：0其它，1Token过期，2主动退出，3被动退出。
	 */
	public Byte getLogoutTypeCode() {
		return this.logoutTypeCode;
	}

	/**
	 * set方法。退出方式。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，四种类型：0其它，1Token过期，2主动退出，3被动退出。
	 */
	public void setLogoutTypeCode(Byte logoutTypeCode) {
		this.logoutTypeCode = logoutTypeCode;
	}
    
	/**
	 * get方法。操作系统。
	 */
	public String getOs() {
		return this.os;
	}

	/**
	 * set方法。操作系统。
	 */
	public void setOs(String os) {
		this.os = os;
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
	 * get方法。屏幕分辨率。
	 */
	public String getResolution() {
		return this.resolution;
	}

	/**
	 * set方法。屏幕分辨率。
	 */
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
    
	/**
	 * get方法。系统用户。外键，引用系统用户表（user）的主键。
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * set方法。系统用户。外键，引用系统用户表（user）的主键。
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    
	/**
	 * get方法。登录用户名。
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * set方法。登录用户名。
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	 * get方法。本表做为子表时，父表实体对象。应用领域。
	 */
	public GpDomain getGpDomain() {
		return this.gpDomain;
	}

	/**
	 * set方法。本表做为子表时，父表实体对象。应用领域。
	 */
	public void setGpDomain(GpDomain gpDomain) {
		this.gpDomain = gpDomain;
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





}







