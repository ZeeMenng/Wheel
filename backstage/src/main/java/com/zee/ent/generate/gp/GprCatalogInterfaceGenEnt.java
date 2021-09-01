package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;
import com.zee.ent.extend.gp.GpCatalogInterface;
import com.zee.ent.extend.gp.GpInterface;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/2/2 18:48:41
 * @description 实体类GprCatalogInterfaceGenEnt，自动生成。后台接口所属分类。
 */

public class GprCatalogInterfaceGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="接口分类。外键，引用接口分类字典表（catalog_interface）的主键。",hidden=false,required=false)
    private String catalogId;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="系统接口。外键，引用系统接口表（interface）的主键。",hidden=false,required=false)
    private String interfaceId;

   //本表做为子表时，父表实体对象
    private  GpCatalogInterface gpCatalogInterface;
    private  GpInterface gpInterface;

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。接口分类。外键，引用接口分类字典表（catalog_interface）的主键。
	 */
	public String getCatalogId() {
		return this.catalogId;
	}

	/**
	 * set方法。接口分类。外键，引用接口分类字典表（catalog_interface）的主键。
	 */
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
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
	 * get方法。系统接口。外键，引用系统接口表（interface）的主键。
	 */
	public String getInterfaceId() {
		return this.interfaceId;
	}

	/**
	 * set方法。系统接口。外键，引用系统接口表（interface）的主键。
	 */
	public void setInterfaceId(String interfaceId) {
		this.interfaceId = interfaceId;
	}
    






	/**
	 * get方法。本表做为子表时，父表实体对象。接口分类字典。存放接口分类信息，支持树形分级分类，主要但不限于业务上的分类方式，支持同时对接口进行多种分类。
	 */
	public GpCatalogInterface getGpCatalogInterface() {
		return this.gpCatalogInterface;
	}

	/**
	 * set方法。本表做为子表时，父表实体对象。接口分类字典。存放接口分类信息，支持树形分级分类，主要但不限于业务上的分类方式，支持同时对接口进行多种分类。
	 */
	public void setGpCatalogInterface(GpCatalogInterface gpCatalogInterface) {
		this.gpCatalogInterface = gpCatalogInterface;
	}

	/**
	 * get方法。本表做为子表时，父表实体对象。系统接口。
	 */
	public GpInterface getGpInterface() {
		return this.gpInterface;
	}

	/**
	 * set方法。本表做为子表时，父表实体对象。系统接口。
	 */
	public void setGpInterface(GpInterface gpInterface) {
		this.gpInterface = gpInterface;
	}





}







