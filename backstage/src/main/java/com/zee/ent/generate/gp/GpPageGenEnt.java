package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;
import com.zee.ent.extend.gp.GpControl;
import com.zee.ent.extend.gp.GpDomain;
import com.zee.ent.extend.gp.GprModulePage;
import com.zee.ent.extend.gp.GprRolePage;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/2/2 18:48:50
 * @description 实体类GpPageGenEnt，自动生成。系统页面。
 */

public class GpPageGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="记录创建时间。",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="应用领域。外键，引用应用领域表（domain）的主键。",hidden=false,required=false)
    private String domainId;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="是否为公共页面。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值1。",allowableValues="0,1",hidden=false,required=false)
    private Byte isPublicCode;
    @ApiModelProperty(value="页面名称。",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="备注字段。",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="接口编号。",hidden=false,required=false)
    private String serialNo;
    @ApiModelProperty(value="记录最后一次修改时间。",hidden=false,required=false)
    private Date updateTime;
    @ApiModelProperty(value="存放路径。",hidden=false,required=false)
    private String url;

   //本表做为子表时，父表实体对象
    private  GpDomain gpDomain;

    //本表做为父表时，子表数据列表
    private ArrayList<GpControl> gpControlList;   
    private ArrayList<GprModulePage> gprModulePageList;   
    private ArrayList<GprRolePage> gprRolePageList;   

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
	 * get方法。是否为公共页面。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值1。
	 */
	public Byte getIsPublicCode() {
		return this.isPublicCode;
	}

	/**
	 * set方法。是否为公共页面。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值1。
	 */
	public void setIsPublicCode(Byte isPublicCode) {
		this.isPublicCode = isPublicCode;
	}
    
	/**
	 * get方法。页面名称。
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。页面名称。
	 */
	public void setName(String name) {
		this.name = name;
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
	 * get方法。接口编号。
	 */
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * set方法。接口编号。
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
	 * get方法。存放路径。
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * set方法。存放路径。
	 */
	public void setUrl(String url) {
		this.url = url;
	}
    



	/**
	 * get方法。本表做为父表时，子表实体对象。系统控件。
	 */
	public ArrayList<GpControl> getGpControlList() {
		return this.gpControlList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。系统控件。
	 */
	public void setGpControlList(ArrayList<GpControl> gpControlList) {
		this.gpControlList = gpControlList;
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
	 * get方法。本表做为父表时，子表实体对象。角色拥有的页面权限。
	 */
	public ArrayList<GprRolePage> getGprRolePageList() {
		return this.gprRolePageList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。角色拥有的页面权限。
	 */
	public void setGprRolePageList(ArrayList<GprRolePage> gprRolePageList) {
		this.gprRolePageList = gprRolePageList;
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





}







