package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;
import com.zee.ent.extend.gp.GpDomain;
import com.zee.ent.extend.gp.GprRoleInterface;
import com.zee.ent.extend.gp.GprCatalogInterface;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/2/2 18:48:48
 * @description 实体类GpInterfaceGenEnt，自动生成。系统接口。
 */

public class GpInterfaceGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="记录创建时间。",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="应用领域。外键，引用应用领域表（domain）的主键。",hidden=false,required=true)
    private String domainId;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="是否为公共接口。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值1。",allowableValues="0,1",hidden=false,required=false)
    private Byte isPublicCode;
    @ApiModelProperty(value="接口名称。",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="备注字段。",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="接口编号。",hidden=false,required=true)
    private String serialNo;
    @ApiModelProperty(value="操作主表。",hidden=false,required=false)
    private String tableName;
    @ApiModelProperty(value="接口调用方式。对应数据字典表（dictionary）中的编码字段（code）。目前两种类型：1GET，2POST。",allowableValues="0,1",hidden=false,required=false)
    private Byte typeCode;
    @ApiModelProperty(value="记录最后一次修改时间。",hidden=false,required=false)
    private Date updateTime;
    @ApiModelProperty(value="访问路径。",hidden=false,required=true)
    private String url;

   //本表做为子表时，父表实体对象
    private  GpDomain gpDomain;

    //本表做为父表时，子表数据列表
    private ArrayList<GprRoleInterface> gprRoleInterfaceList;   
    private ArrayList<GprCatalogInterface> gprCatalogInterfaceList;   

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
	 * get方法。是否为公共接口。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值1。
	 */
	public Byte getIsPublicCode() {
		return this.isPublicCode;
	}

	/**
	 * set方法。是否为公共接口。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值1。
	 */
	public void setIsPublicCode(Byte isPublicCode) {
		this.isPublicCode = isPublicCode;
	}
    
	/**
	 * get方法。接口名称。
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。接口名称。
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
	 * get方法。操作主表。
	 */
	public String getTableName() {
		return this.tableName;
	}

	/**
	 * set方法。操作主表。
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
    
	/**
	 * get方法。接口调用方式。对应数据字典表（dictionary）中的编码字段（code）。目前两种类型：1GET，2POST。
	 */
	public Byte getTypeCode() {
		return this.typeCode;
	}

	/**
	 * set方法。接口调用方式。对应数据字典表（dictionary）中的编码字段（code）。目前两种类型：1GET，2POST。
	 */
	public void setTypeCode(Byte typeCode) {
		this.typeCode = typeCode;
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
	 * get方法。访问路径。
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * set方法。访问路径。
	 */
	public void setUrl(String url) {
		this.url = url;
	}
    



	/**
	 * get方法。本表做为父表时，子表实体对象。角色拥有的接口权限。
	 */
	public ArrayList<GprRoleInterface> getGprRoleInterfaceList() {
		return this.gprRoleInterfaceList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。角色拥有的接口权限。
	 */
	public void setGprRoleInterfaceList(ArrayList<GprRoleInterface> gprRoleInterfaceList) {
		this.gprRoleInterfaceList = gprRoleInterfaceList;
	}

	/**
	 * get方法。本表做为父表时，子表实体对象。后台接口所属分类。
	 */
	public ArrayList<GprCatalogInterface> getGprCatalogInterfaceList() {
		return this.gprCatalogInterfaceList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。后台接口所属分类。
	 */
	public void setGprCatalogInterfaceList(ArrayList<GprCatalogInterface> gprCatalogInterfaceList) {
		this.gprCatalogInterfaceList = gprCatalogInterfaceList;
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







