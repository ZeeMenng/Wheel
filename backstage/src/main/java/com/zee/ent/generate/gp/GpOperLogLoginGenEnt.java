package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;
import com.zee.ent.extend.gp.GpLoginLog;
import com.zee.ent.extend.gp.GpOperLog;
import com.zee.ent.extend.gp.GpToken;
import com.zee.ent.extend.gp.GpUser;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/2/2 18:48:50
 * @description 实体类GpOperLogLoginGenEnt，自动生成。登录用户操作日志。
 */

public class GpOperLogLoginGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="所属登录。外键，引用登录日志表（login_log)的主键。",hidden=false,required=false)
    private String loginLogId;
    @ApiModelProperty(value="操作日志。外键，引用操作日志表（oper_log）表的主键。",hidden=false,required=false)
    private String operLogId;
    @ApiModelProperty(value="所属Token。外键，引用Token信息表（token）的主键。",hidden=false,required=false)
    private String tokenId;
    @ApiModelProperty(value="所属用户。外键，引用系统用户表（user）的主键。",hidden=false,required=false)
    private String userId;
    @ApiModelProperty(value="所属用户 。登录名称，和系统用户表（user）的登录名称（user_name）对应。",hidden=false,required=false)
    private String userName;

   //本表做为子表时，父表实体对象
    private  GpLoginLog gpLoginLog;
    private  GpOperLog gpOperLog;
    private  GpToken gpToken;
    private  GpUser gpUser;

    //本表做为父表时，子表数据列表

    //父子表均为自身时


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
	 * get方法。所属登录。外键，引用登录日志表（login_log)的主键。
	 */
	public String getLoginLogId() {
		return this.loginLogId;
	}

	/**
	 * set方法。所属登录。外键，引用登录日志表（login_log)的主键。
	 */
	public void setLoginLogId(String loginLogId) {
		this.loginLogId = loginLogId;
	}
    
	/**
	 * get方法。操作日志。外键，引用操作日志表（oper_log）表的主键。
	 */
	public String getOperLogId() {
		return this.operLogId;
	}

	/**
	 * set方法。操作日志。外键，引用操作日志表（oper_log）表的主键。
	 */
	public void setOperLogId(String operLogId) {
		this.operLogId = operLogId;
	}
    
	/**
	 * get方法。所属Token。外键，引用Token信息表（token）的主键。
	 */
	public String getTokenId() {
		return this.tokenId;
	}

	/**
	 * set方法。所属Token。外键，引用Token信息表（token）的主键。
	 */
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
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
	 * get方法。本表做为子表时，父表实体对象。操作日志。
	 */
	public GpOperLog getGpOperLog() {
		return this.gpOperLog;
	}

	/**
	 * set方法。本表做为子表时，父表实体对象。操作日志。
	 */
	public void setGpOperLog(GpOperLog gpOperLog) {
		this.gpOperLog = gpOperLog;
	}

	/**
	 * get方法。本表做为子表时，父表实体对象。token信息。
	 */
	public GpToken getGpToken() {
		return this.gpToken;
	}

	/**
	 * set方法。本表做为子表时，父表实体对象。token信息。
	 */
	public void setGpToken(GpToken gpToken) {
		this.gpToken = gpToken;
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







