package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;
import com.zee.ent.extend.gp.GpUser;
import com.zee.ent.extend.gp.GprDomainMessage;
import com.zee.ent.extend.gp.GprMessageUser;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2022/3/29 18:24:23
 * @description 实体类GpMessageGenEnt，自动生成。系统消息。
 */

public class GpMessageGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="记录创建时间。",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="消息内容。",hidden=false,required=false)
    private String content;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="接收消息的应用ID列表。英文逗号分隔。",hidden=false,required=false)
    private String receiverDomainIds;
    @ApiModelProperty(value="接收消息的应用名称列表。英文逗号分隔。",hidden=false,required=false)
    private String receiverDomainNames;
    @ApiModelProperty(value="接收消息的用户ID列表。英文逗号分隔。",hidden=false,required=false)
    private String receiverUserIds;
    @ApiModelProperty(value="接收消息的用户名称列表。英文逗号分隔。",hidden=false,required=false)
    private String receiverUserNames;
    @ApiModelProperty(value="备注字段。",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="消息标题",hidden=false,required=false)
    private String title;
    @ApiModelProperty(value="消息类型。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义4种类型：0其它，1公告，2提醒，3私信。如果消息类型是公告，在用户读取消息的时候向消息队列表（gpr_message_user）插入数据；如果消息类型是私信和提醒，则新建消息后立即向消息列表（gpr_message_use）中插入数据。",allowableValues="0,1",hidden=false,required=false)
    private Byte typeCode;
    @ApiModelProperty(value="消息类型。文本，对应数据字典表（dictionary）中的文本字段（text）。目前先定义4种类型：0其它，1公告，2提醒，3私信。如果消息类型是公告，在用户读取消息的时候向消息队列表（gpr_message_user）插入数据；如果消息类型是私信和提醒，则新建消息后立即向消息列表（gpr_message_use）中插入数据。",hidden=false,required=false)
    private String typeText;
    @ApiModelProperty(value="消息创建者。外键，引用系统用户表（user）的主键。",hidden=false,required=false)
    private String userId;
    @ApiModelProperty(value="消息创建者。登录名称，和系统用户表（user）的登录名称（user_name）对应。",hidden=false,required=false)
    private String userName;

   //本表做为子表时，父表实体对象
    private  GpUser gpUser;

    //本表做为父表时，子表数据列表
    private ArrayList<GprDomainMessage> gprDomainMessageList;   
    private ArrayList<GprMessageUser> gprMessageUserList;   

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
	 * get方法。消息内容。
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * set方法。消息内容。
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * get方法。接收消息的应用ID列表。英文逗号分隔。
	 */
	public String getReceiverDomainIds() {
		return this.receiverDomainIds;
	}

	/**
	 * set方法。接收消息的应用ID列表。英文逗号分隔。
	 */
	public void setReceiverDomainIds(String receiverDomainIds) {
		this.receiverDomainIds = receiverDomainIds;
	}
    
	/**
	 * get方法。接收消息的应用名称列表。英文逗号分隔。
	 */
	public String getReceiverDomainNames() {
		return this.receiverDomainNames;
	}

	/**
	 * set方法。接收消息的应用名称列表。英文逗号分隔。
	 */
	public void setReceiverDomainNames(String receiverDomainNames) {
		this.receiverDomainNames = receiverDomainNames;
	}
    
	/**
	 * get方法。接收消息的用户ID列表。英文逗号分隔。
	 */
	public String getReceiverUserIds() {
		return this.receiverUserIds;
	}

	/**
	 * set方法。接收消息的用户ID列表。英文逗号分隔。
	 */
	public void setReceiverUserIds(String receiverUserIds) {
		this.receiverUserIds = receiverUserIds;
	}
    
	/**
	 * get方法。接收消息的用户名称列表。英文逗号分隔。
	 */
	public String getReceiverUserNames() {
		return this.receiverUserNames;
	}

	/**
	 * set方法。接收消息的用户名称列表。英文逗号分隔。
	 */
	public void setReceiverUserNames(String receiverUserNames) {
		this.receiverUserNames = receiverUserNames;
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
	 * get方法。消息标题
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * set方法。消息标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
    
	/**
	 * get方法。消息类型。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义4种类型：0其它，1公告，2提醒，3私信。如果消息类型是公告，在用户读取消息的时候向消息队列表（gpr_message_user）插入数据；如果消息类型是私信和提醒，则新建消息后立即向消息列表（gpr_message_use）中插入数据。
	 */
	public Byte getTypeCode() {
		return this.typeCode;
	}

	/**
	 * set方法。消息类型。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义4种类型：0其它，1公告，2提醒，3私信。如果消息类型是公告，在用户读取消息的时候向消息队列表（gpr_message_user）插入数据；如果消息类型是私信和提醒，则新建消息后立即向消息列表（gpr_message_use）中插入数据。
	 */
	public void setTypeCode(Byte typeCode) {
		this.typeCode = typeCode;
	}
    
	/**
	 * get方法。消息类型。文本，对应数据字典表（dictionary）中的文本字段（text）。目前先定义4种类型：0其它，1公告，2提醒，3私信。如果消息类型是公告，在用户读取消息的时候向消息队列表（gpr_message_user）插入数据；如果消息类型是私信和提醒，则新建消息后立即向消息列表（gpr_message_use）中插入数据。
	 */
	public String getTypeText() {
		return this.typeText;
	}

	/**
	 * set方法。消息类型。文本，对应数据字典表（dictionary）中的文本字段（text）。目前先定义4种类型：0其它，1公告，2提醒，3私信。如果消息类型是公告，在用户读取消息的时候向消息队列表（gpr_message_user）插入数据；如果消息类型是私信和提醒，则新建消息后立即向消息列表（gpr_message_use）中插入数据。
	 */
	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}
    
	/**
	 * get方法。消息创建者。外键，引用系统用户表（user）的主键。
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * set方法。消息创建者。外键，引用系统用户表（user）的主键。
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    
	/**
	 * get方法。消息创建者。登录名称，和系统用户表（user）的登录名称（user_name）对应。
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * set方法。消息创建者。登录名称，和系统用户表（user）的登录名称（user_name）对应。
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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







