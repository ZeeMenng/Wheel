package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;
import com.zee.ent.extend.gp.GpDomain;
import com.zee.ent.extend.gp.GpMessage;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2022/3/29 18:24:23
 * @description 实体类GprDomainMessageGenEnt，自动生成。应用领域的站内信。
 */

public class GprDomainMessageGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="记录创建时间。即消息下发时间。",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="接收消息的应用领域。外键，引用应用领域表（domain）的主键。",hidden=false,required=false)
    private String domainId;
    @ApiModelProperty(value="接收消息的应用领域。名称，和应用领域表（domain）的名称（name）字段对应。",hidden=false,required=false)
    private String domainName;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="领域消息。外键，引用站内信（message）的主键。",hidden=false,required=false)
    private String messageId;

   //本表做为子表时，父表实体对象
    private  GpDomain gpDomain;
    private  GpMessage gpMessage;

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。记录创建时间。即消息下发时间。
	 */
	public Date getAddTime() {
		return this.addTime;
	}

	/**
	 * set方法。记录创建时间。即消息下发时间。
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
    
	/**
	 * get方法。接收消息的应用领域。外键，引用应用领域表（domain）的主键。
	 */
	public String getDomainId() {
		return this.domainId;
	}

	/**
	 * set方法。接收消息的应用领域。外键，引用应用领域表（domain）的主键。
	 */
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
    
	/**
	 * get方法。接收消息的应用领域。名称，和应用领域表（domain）的名称（name）字段对应。
	 */
	public String getDomainName() {
		return this.domainName;
	}

	/**
	 * set方法。接收消息的应用领域。名称，和应用领域表（domain）的名称（name）字段对应。
	 */
	public void setDomainName(String domainName) {
		this.domainName = domainName;
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
	 * get方法。领域消息。外键，引用站内信（message）的主键。
	 */
	public String getMessageId() {
		return this.messageId;
	}

	/**
	 * set方法。领域消息。外键，引用站内信（message）的主键。
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
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
	 * get方法。本表做为子表时，父表实体对象。系统消息。
	 */
	public GpMessage getGpMessage() {
		return this.gpMessage;
	}

	/**
	 * set方法。本表做为子表时，父表实体对象。系统消息。
	 */
	public void setGpMessage(GpMessage gpMessage) {
		this.gpMessage = gpMessage;
	}





}







