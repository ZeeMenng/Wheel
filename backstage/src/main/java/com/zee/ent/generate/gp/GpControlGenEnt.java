package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;
import com.zee.ent.extend.gp.GpDomain;
import com.zee.ent.extend.gp.GpPage;
import com.zee.ent.extend.gp.GprRoleControl;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/3/29 11:34:23
 * @description 实体类GpControlGenEnt，自动生成。系统控件。
 */

public class GpControlGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="记录创建时间。",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="控件编码。",hidden=false,required=false)
    private String code;
    @ApiModelProperty(value="应用领域。外键，引用应用领域表（domain）的主键。",hidden=false,required=false)
    private String domainId;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="控件名称。",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="所属页面。外键，引用系统页面表（page）的主键（id）。",hidden=false,required=false)
    private String pageId;
    @ApiModelProperty(value="所属页面。路径，和系统页面表（page）的路径（url）对应。",hidden=false,required=false)
    private String pageUrl;
    @ApiModelProperty(value="备注字段。",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="控件编号。",hidden=false,required=false)
    private String serialNo;
    @ApiModelProperty(value="记录最后一次修改时间。",hidden=false,required=false)
    private Date updateTime;

   //本表做为子表时，父表实体对象
    private  GpDomain gpDomain;
    private  GpPage gpPage;

    //本表做为父表时，子表数据列表
    private ArrayList<GprRoleControl> gprRoleControlList;   

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
	 * get方法。控件编码。
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * set方法。控件编码。
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * get方法。控件名称。
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。控件名称。
	 */
	public void setName(String name) {
		this.name = name;
	}
    
	/**
	 * get方法。所属页面。外键，引用系统页面表（page）的主键（id）。
	 */
	public String getPageId() {
		return this.pageId;
	}

	/**
	 * set方法。所属页面。外键，引用系统页面表（page）的主键（id）。
	 */
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
    
	/**
	 * get方法。所属页面。路径，和系统页面表（page）的路径（url）对应。
	 */
	public String getPageUrl() {
		return this.pageUrl;
	}

	/**
	 * set方法。所属页面。路径，和系统页面表（page）的路径（url）对应。
	 */
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
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
	 * get方法。控件编号。
	 */
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * set方法。控件编号。
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
	 * get方法。本表做为子表时，父表实体对象。系统页面。
	 */
	public GpPage getGpPage() {
		return this.gpPage;
	}

	/**
	 * set方法。本表做为子表时，父表实体对象。系统页面。
	 */
	public void setGpPage(GpPage gpPage) {
		this.gpPage = gpPage;
	}





}







