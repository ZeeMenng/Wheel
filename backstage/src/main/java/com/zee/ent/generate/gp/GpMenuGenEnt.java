package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.zee.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2020/8/11 11:43:50
 * @description 实体类GpMenuGenEnt，自动生成。链接菜单。
 */

public class GpMenuGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="记录创建时间。",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="应用领域。外键，引用应用领域表（domain）的主键。",hidden=false,required=false)
    private String domainId;
    @ApiModelProperty(value="菜单图标样式。",hidden=false,required=false)
    private String iconClass;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="功能模块。外键，引用应用功能模块表（module）的主键。",hidden=false,required=false)
    private String moduleId;
    @ApiModelProperty(value="菜单名称。",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="链接页面。外键，引用系统页面表（page）的主键。",hidden=false,required=false)
    private String pageId;
    @ApiModelProperty(value="链接页面。路径，和系统页面表（page）的路径（url）对应。",hidden=false,required=false)
    private String pageUrl;
    @ApiModelProperty(value="排序字段。",hidden=false,required=false)
    private Integer priority;
    @ApiModelProperty(value="备注字段。",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="记录最后一次修改时间。",hidden=false,required=false)
    private Date updateTime;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

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
	 * get方法。菜单图标样式。
	 */
	public String getIconClass() {
		return this.iconClass;
	}

	/**
	 * set方法。菜单图标样式。
	 */
	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
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
	 * get方法。功能模块。外键，引用应用功能模块表（module）的主键。
	 */
	public String getModuleId() {
		return this.moduleId;
	}

	/**
	 * set方法。功能模块。外键，引用应用功能模块表（module）的主键。
	 */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
    
	/**
	 * get方法。菜单名称。
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。菜单名称。
	 */
	public void setName(String name) {
		this.name = name;
	}
    
	/**
	 * get方法。链接页面。外键，引用系统页面表（page）的主键。
	 */
	public String getPageId() {
		return this.pageId;
	}

	/**
	 * set方法。链接页面。外键，引用系统页面表（page）的主键。
	 */
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
    
	/**
	 * get方法。链接页面。路径，和系统页面表（page）的路径（url）对应。
	 */
	public String getPageUrl() {
		return this.pageUrl;
	}

	/**
	 * set方法。链接页面。路径，和系统页面表（page）的路径（url）对应。
	 */
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
    
	/**
	 * get方法。排序字段。
	 */
	public Integer getPriority() {
		return this.priority;
	}

	/**
	 * set方法。排序字段。
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
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
    

    //一对多关系中，多端数据列表

}







