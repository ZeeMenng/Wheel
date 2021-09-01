package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;
import com.zee.ent.extend.gp.GpDomain;
import com.zee.ent.extend.gp.GpModule;
import com.zee.ent.extend.gp.GpModule;
import com.zee.ent.extend.gp.GprModulePage;
import com.zee.ent.extend.gp.GprRoleModule;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/2/2 18:48:49
 * @description 实体类GpModuleGenEnt，自动生成。功能模块。
 */

public class GpModuleGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="记录创建时间。",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="应用领域。外键，引用应用领域表（domain）的主键。",hidden=false,required=false)
    private String domainId;
    @ApiModelProperty(value="父级模块。外键，引用自身功能模块表（module）的主键。",hidden=false,required=false)
    private String fartherId;
    @ApiModelProperty(value="菜单图标。",hidden=false,required=false)
    private String iconResource;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="模块级别。模块在层级关系中所属等级，从1开始。",hidden=false,required=false)
    private Byte level;
    @ApiModelProperty(value="模块名称。",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="链接页面。外键，引用系统页面表（page）的主键。",hidden=false,required=false)
    private String pageId;
    @ApiModelProperty(value="链接页面。路径，和系统页面表（page）的路径（url）对应。",hidden=false,required=false)
    private String pageUrl;
    @ApiModelProperty(value="排序字段。",hidden=false,required=false)
    private Integer priority;
    @ApiModelProperty(value="备注字段。",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="模块编号。",hidden=false,required=false)
    private String serialNo;
    @ApiModelProperty(value="菜单样式。",hidden=false,required=false)
    private String style;
    @ApiModelProperty(value="记录最后一次修改时间。",hidden=false,required=false)
    private Date updateTime;

   //本表做为子表时，父表实体对象
    private  GpDomain gpDomain;

    //本表做为父表时，子表数据列表
    private ArrayList<GprModulePage> gprModulePageList;   
    private ArrayList<GprRoleModule> gprRoleModuleList;   

    //父子表均为自身时
    private  GpModule gpModule;
    private ArrayList<GpModule> gpModuleList;   


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
	 * get方法。父级模块。外键，引用自身功能模块表（module）的主键。
	 */
	public String getFartherId() {
		return this.fartherId;
	}

	/**
	 * set方法。父级模块。外键，引用自身功能模块表（module）的主键。
	 */
	public void setFartherId(String fartherId) {
		this.fartherId = fartherId;
	}
    
	/**
	 * get方法。菜单图标。
	 */
	public String getIconResource() {
		return this.iconResource;
	}

	/**
	 * set方法。菜单图标。
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
	 * get方法。模块级别。模块在层级关系中所属等级，从1开始。
	 */
	public Byte getLevel() {
		return this.level;
	}

	/**
	 * set方法。模块级别。模块在层级关系中所属等级，从1开始。
	 */
	public void setLevel(Byte level) {
		this.level = level;
	}
    
	/**
	 * get方法。模块名称。
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。模块名称。
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
	 * get方法。模块编号。
	 */
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * set方法。模块编号。
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
    
	/**
	 * get方法。菜单样式。
	 */
	public String getStyle() {
		return this.style;
	}

	/**
	 * set方法。菜单样式。
	 */
	public void setStyle(String style) {
		this.style = style;
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
	 * get方法。本表做为父表时，子表实体对象。功能模块所包含的页面。
	 */
	public ArrayList<GprModulePage> getGprModulePageList() {
		return this.gprModulePageList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。功能模块所包含的页面。
	 */
	public void setGprModulePageList(ArrayList<GprModulePage> gprModulePageList) {
		this.gprModulePageList = gprModulePageList;
	}

	/**
	 * get方法。本表做为父表时，子表实体对象。角色拥有的功能模块权限。
	 */
	public ArrayList<GprRoleModule> getGprRoleModuleList() {
		return this.gprRoleModuleList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。角色拥有的功能模块权限。
	 */
	public void setGprRoleModuleList(ArrayList<GprRoleModule> gprRoleModuleList) {
		this.gprRoleModuleList = gprRoleModuleList;
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
	 * get方法。父子表均为自身时，父表实体对象。功能模块。
	 */
	public GpModule getGpModule() {
		return this.gpModule;
	}

	/**
	 * set方法。父子表均为自身时，父表实体对象。功能模块。
	 */
	public void setGpModule(GpModule gpModule) {
		this.gpModule = gpModule;
	}


	/**
	 * get方法。父子表均为自身时，子表实体对象。功能模块。
	 */
	public ArrayList<GpModule> getGpModuleList() {
		return this.gpModuleList;
	}

	/**
	 * set方法。父子表均为自身时，子表实体对象。功能模块。
	 */
	public void setGpModuleList(ArrayList<GpModule> gpModuleList) {
		this.gpModuleList = gpModuleList;
	}


}







