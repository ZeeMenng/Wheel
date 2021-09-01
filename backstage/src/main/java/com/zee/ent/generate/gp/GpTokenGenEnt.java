package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;
import com.zee.ent.extend.gp.GpOperLogLogin;
import com.zee.ent.extend.gp.GpDomain;
import com.zee.ent.extend.gp.GpLoginLog;
import com.zee.ent.extend.gp.GpUser;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/2/2 18:48:52
 * @description 实体类GpTokenGenEnt，自动生成。token信息。
 */

public class GpTokenGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="access_token过期时间。",hidden=false,required=false)
    private Date aDeadTime;
    @ApiModelProperty(value="access_token。唯一字段。",hidden=false,required=false)
    private String accessToken;
    @ApiModelProperty(value="记录创建时间。",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="应用领域。外键，引用应用领域表（domain）的主键。",hidden=false,required=false)
    private String domainId;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="refresh_token过期时间。",hidden=false,required=false)
    private Date rDeadTime;
    @ApiModelProperty(value="refresh_token。唯一字段。",hidden=false,required=false)
    private String refreshToken;
    @ApiModelProperty(value="备注字段。",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="动态密钥。",hidden=false,required=false)
    private String secret;
    @ApiModelProperty(value="记录最后一次修改时间。",hidden=false,required=false)
    private Date updateTime;
    @ApiModelProperty(value="所属用户。外键，引用系统用户表（user）的主键。",hidden=false,required=false)
    private String userId;
    @ApiModelProperty(value="所属用户 。登录名称，和系统用户表（user）的登录名称（user_name）对应。",hidden=false,required=false)
    private String userName;

   //本表做为子表时，父表实体对象
    private  GpDomain gpDomain;
    private  GpLoginLog gpLoginLog;
    private  GpUser gpUser;

    //本表做为父表时，子表数据列表
    private ArrayList<GpOperLogLogin> gpOperLogLoginList;   

    //父子表均为自身时


	/**
	 * get方法。access_token过期时间。
	 */
	public Date getADeadTime() {
		return this.aDeadTime;
	}

	/**
	 * set方法。access_token过期时间。
	 */
	public void setADeadTime(Date aDeadTime) {
		this.aDeadTime = aDeadTime;
	}
    
	/**
	 * get方法。access_token。唯一字段。
	 */
	public String getAccessToken() {
		return this.accessToken;
	}

	/**
	 * set方法。access_token。唯一字段。
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
    
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
	 * get方法。refresh_token过期时间。
	 */
	public Date getRDeadTime() {
		return this.rDeadTime;
	}

	/**
	 * set方法。refresh_token过期时间。
	 */
	public void setRDeadTime(Date rDeadTime) {
		this.rDeadTime = rDeadTime;
	}
    
	/**
	 * get方法。refresh_token。唯一字段。
	 */
	public String getRefreshToken() {
		return this.refreshToken;
	}

	/**
	 * set方法。refresh_token。唯一字段。
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
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
	 * get方法。动态密钥。
	 */
	public String getSecret() {
		return this.secret;
	}

	/**
	 * set方法。动态密钥。
	 */
	public void setSecret(String secret) {
		this.secret = secret;
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
	 * get方法。所属用户。外键，引用系统用户表（user）的主键。
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * set方法。所属用户。外键，引用系统用户表（user）的主键。
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    
	/**
	 * get方法。所属用户 。登录名称，和系统用户表（user）的登录名称（user_name）对应。
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * set方法。所属用户 。登录名称，和系统用户表（user）的登录名称（user_name）对应。
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
	 * get方法。本表做为子表时，父表实体对象。登录日志。
	 */
	public GpLoginLog getGpLoginLog() {
		return this.gpLoginLog;
	}

	/**
	 * set方法。本表做为子表时，父表实体对象。登录日志。
	 */
	public void setGpLoginLog(GpLoginLog gpLoginLog) {
		this.gpLoginLog = gpLoginLog;
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







