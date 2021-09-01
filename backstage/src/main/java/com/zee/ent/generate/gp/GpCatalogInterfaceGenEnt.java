package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;
import com.zee.ent.extend.gp.GpCatalogInterface;
import com.zee.ent.extend.gp.GpCatalogInterface;
import com.zee.ent.extend.gp.GprCatalogInterface;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/2/2 18:48:46
 * @description 实体类GpCatalogInterfaceGenEnt，自动生成。接口分类字典。存放接口分类信息，支持树形分级分类，主要但不限于业务上的分类方式，支持同时对接口进行多种分类。
 */

public class GpCatalogInterfaceGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="分类方式。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义两种分类方式：1按业务分类，2按请求方式分类。",allowableValues="0,1",hidden=false,required=false)
    private Byte categoryCode;
    @ApiModelProperty(value="分类方式。文本，对应数据字典表（dictionary）中的文本字段（text）。目前先定义两种分类方式：1按业务分类，2按请求方式分类。",hidden=false,required=false)
    private String categoryText;
    @ApiModelProperty(value="父级模块。外键，引用自身接口分类字典表（gp_catalog_interface）的主键。",hidden=false,required=false)
    private String fartherId;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="类别层级。类别在层级关系中所属等级，从1开始。",hidden=false,required=false)
    private Byte level;
    @ApiModelProperty(value="类别名称。",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="排序字段。",hidden=false,required=false)
    private Integer priority;
    @ApiModelProperty(value="备注。",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="类别编号。",hidden=false,required=false)
    private String serialNo;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表
    private ArrayList<GprCatalogInterface> gprCatalogInterfaceList;   

    //父子表均为自身时
    private  GpCatalogInterface gpCatalogInterface;
    private ArrayList<GpCatalogInterface> gpCatalogInterfaceList;   


	/**
	 * get方法。分类方式。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义两种分类方式：1按业务分类，2按请求方式分类。
	 */
	public Byte getCategoryCode() {
		return this.categoryCode;
	}

	/**
	 * set方法。分类方式。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义两种分类方式：1按业务分类，2按请求方式分类。
	 */
	public void setCategoryCode(Byte categoryCode) {
		this.categoryCode = categoryCode;
	}
    
	/**
	 * get方法。分类方式。文本，对应数据字典表（dictionary）中的文本字段（text）。目前先定义两种分类方式：1按业务分类，2按请求方式分类。
	 */
	public String getCategoryText() {
		return this.categoryText;
	}

	/**
	 * set方法。分类方式。文本，对应数据字典表（dictionary）中的文本字段（text）。目前先定义两种分类方式：1按业务分类，2按请求方式分类。
	 */
	public void setCategoryText(String categoryText) {
		this.categoryText = categoryText;
	}
    
	/**
	 * get方法。父级模块。外键，引用自身接口分类字典表（gp_catalog_interface）的主键。
	 */
	public String getFartherId() {
		return this.fartherId;
	}

	/**
	 * set方法。父级模块。外键，引用自身接口分类字典表（gp_catalog_interface）的主键。
	 */
	public void setFartherId(String fartherId) {
		this.fartherId = fartherId;
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
	 * get方法。类别层级。类别在层级关系中所属等级，从1开始。
	 */
	public Byte getLevel() {
		return this.level;
	}

	/**
	 * set方法。类别层级。类别在层级关系中所属等级，从1开始。
	 */
	public void setLevel(Byte level) {
		this.level = level;
	}
    
	/**
	 * get方法。类别名称。
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。类别名称。
	 */
	public void setName(String name) {
		this.name = name;
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
	 * get方法。备注。
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set方法。备注。
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
	/**
	 * get方法。类别编号。
	 */
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * set方法。类别编号。
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
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
	 * get方法。父子表均为自身时，父表实体对象。接口分类字典。存放接口分类信息，支持树形分级分类，主要但不限于业务上的分类方式，支持同时对接口进行多种分类。
	 */
	public GpCatalogInterface getGpCatalogInterface() {
		return this.gpCatalogInterface;
	}

	/**
	 * set方法。父子表均为自身时，父表实体对象。接口分类字典。存放接口分类信息，支持树形分级分类，主要但不限于业务上的分类方式，支持同时对接口进行多种分类。
	 */
	public void setGpCatalogInterface(GpCatalogInterface gpCatalogInterface) {
		this.gpCatalogInterface = gpCatalogInterface;
	}


	/**
	 * get方法。父子表均为自身时，子表实体对象。接口分类字典。存放接口分类信息，支持树形分级分类，主要但不限于业务上的分类方式，支持同时对接口进行多种分类。
	 */
	public ArrayList<GpCatalogInterface> getGpCatalogInterfaceList() {
		return this.gpCatalogInterfaceList;
	}

	/**
	 * set方法。父子表均为自身时，子表实体对象。接口分类字典。存放接口分类信息，支持树形分级分类，主要但不限于业务上的分类方式，支持同时对接口进行多种分类。
	 */
	public void setGpCatalogInterfaceList(ArrayList<GpCatalogInterface> gpCatalogInterfaceList) {
		this.gpCatalogInterfaceList = gpCatalogInterfaceList;
	}


}







