package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;
import com.zee.ent.extend.gp.GpModule;
import com.zee.ent.extend.gp.GpPage;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/2/2 18:48:43
 * @description 实体类GprModulePageGenEnt，自动生成。功能模块所包含的页面。
 */

public class GprModulePageGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="功能模块。外键，引用功能模块表（module）的主键。",hidden=false,required=false)
    private String moduleId;
    @ApiModelProperty(value="系统页面。外键，引用应用系统页面表（page）的主键。",hidden=false,required=false)
    private String pageId;

   //本表做为子表时，父表实体对象
    private  GpModule gpModule;
    private  GpPage gpPage;

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
	 * get方法。功能模块。外键，引用功能模块表（module）的主键。
	 */
	public String getModuleId() {
		return this.moduleId;
	}

	/**
	 * set方法。功能模块。外键，引用功能模块表（module）的主键。
	 */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
    
	/**
	 * get方法。系统页面。外键，引用应用系统页面表（page）的主键。
	 */
	public String getPageId() {
		return this.pageId;
	}

	/**
	 * set方法。系统页面。外键，引用应用系统页面表（page）的主键。
	 */
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
    






	/**
	 * get方法。本表做为子表时，父表实体对象。功能模块。
	 */
	public GpModule getGpModule() {
		return this.gpModule;
	}

	/**
	 * set方法。本表做为子表时，父表实体对象。功能模块。
	 */
	public void setGpModule(GpModule gpModule) {
		this.gpModule = gpModule;
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







