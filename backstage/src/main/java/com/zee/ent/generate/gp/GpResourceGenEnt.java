package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;
import com.zee.ent.extend.gp.GprResource;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/2/2 18:48:51
 * @description 实体类GpResourceGenEnt，自动生成。文件信息。
 */

public class GpResourceGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="新增时间",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="应用领域。外键，引用应用领域表（domain）的主键。",hidden=false,required=true)
    private String domainId;
    @ApiModelProperty(value="资源类型，后缀名，统一小写。",hidden=false,required=true)
    private String extension;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="所属功能模块。外键，引用应用功能模块表（module）的主键。",hidden=false,required=false)
    private String moduleId;
    @ApiModelProperty(value="资源名称",hidden=false,required=true)
    private String newName;
    @ApiModelProperty(value="资源原始名称",hidden=false,required=true)
    private String originalName;
    @ApiModelProperty(value="所属页面。外键，引用应用页面表（page）的主键。",hidden=false,required=false)
    private String pageId;
    @ApiModelProperty(value="资源路径",hidden=false,required=true)
    private String path;
    @ApiModelProperty(value="排列顺序",hidden=false,required=false)
    private Integer priority;
    @ApiModelProperty(value="资源大小，单位字节。",hidden=false,required=true)
    private long size;
    @ApiModelProperty(value="文件创建者。外键，引用应用系统用户表（user）的主键。",hidden=false,required=false)
    private String userId;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表
    private ArrayList<GprResource> gprResourceList;   

    //父子表均为自身时


	/**
	 * get方法。新增时间
	 */
	public Date getAddTime() {
		return this.addTime;
	}

	/**
	 * set方法。新增时间
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
	 * get方法。资源类型，后缀名，统一小写。
	 */
	public String getExtension() {
		return this.extension;
	}

	/**
	 * set方法。资源类型，后缀名，统一小写。
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}
    
	/**
	 * get方法。主键
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set方法。主键
	 */
	public void setId(String id) {
		this.id = id;
	}
    
	/**
	 * get方法。所属功能模块。外键，引用应用功能模块表（module）的主键。
	 */
	public String getModuleId() {
		return this.moduleId;
	}

	/**
	 * set方法。所属功能模块。外键，引用应用功能模块表（module）的主键。
	 */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
    
	/**
	 * get方法。资源名称
	 */
	public String getNewName() {
		return this.newName;
	}

	/**
	 * set方法。资源名称
	 */
	public void setNewName(String newName) {
		this.newName = newName;
	}
    
	/**
	 * get方法。资源原始名称
	 */
	public String getOriginalName() {
		return this.originalName;
	}

	/**
	 * set方法。资源原始名称
	 */
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
    
	/**
	 * get方法。所属页面。外键，引用应用页面表（page）的主键。
	 */
	public String getPageId() {
		return this.pageId;
	}

	/**
	 * set方法。所属页面。外键，引用应用页面表（page）的主键。
	 */
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
    
	/**
	 * get方法。资源路径
	 */
	public String getPath() {
		return this.path;
	}

	/**
	 * set方法。资源路径
	 */
	public void setPath(String path) {
		this.path = path;
	}
    
	/**
	 * get方法。排列顺序
	 */
	public Integer getPriority() {
		return this.priority;
	}

	/**
	 * set方法。排列顺序
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
    
	/**
	 * get方法。资源大小，单位字节。
	 */
	public long getSize() {
		return this.size;
	}

	/**
	 * set方法。资源大小，单位字节。
	 */
	public void setSize(long size) {
		this.size = size;
	}
    
	/**
	 * get方法。文件创建者。外键，引用应用系统用户表（user）的主键。
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * set方法。文件创建者。外键，引用应用系统用户表（user）的主键。
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    



	/**
	 * get方法。本表做为父表时，子表实体对象。附件关联表。只要存有附件字段的表，都会通过此表于gp_resource表关联。
	 */
	public ArrayList<GprResource> getGprResourceList() {
		return this.gprResourceList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。附件关联表。只要存有附件字段的表，都会通过此表于gp_resource表关联。
	 */
	public void setGprResourceList(ArrayList<GprResource> gprResourceList) {
		this.gprResourceList = gprResourceList;
	}








}







