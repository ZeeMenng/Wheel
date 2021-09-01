package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;
import com.zee.ent.extend.gp.GpDictionary;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/2/2 18:48:47
 * @description 实体类GpDictionaryTypeGenEnt，自动生成。字典类型。
 */

public class GpDictionaryTypeGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="常量名称",hidden=false,required=false)
    private String constantName;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="名称",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="备注",hidden=false,required=false)
    private String remark;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表
    private ArrayList<GpDictionary> gpDictionaryList;   

    //父子表均为自身时


	/**
	 * get方法。常量名称
	 */
	public String getConstantName() {
		return this.constantName;
	}

	/**
	 * set方法。常量名称
	 */
	public void setConstantName(String constantName) {
		this.constantName = constantName;
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
	 * get方法。名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。名称
	 */
	public void setName(String name) {
		this.name = name;
	}
    
	/**
	 * get方法。备注
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set方法。备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
    



	/**
	 * get方法。本表做为父表时，子表实体对象。字典信息。
	 */
	public ArrayList<GpDictionary> getGpDictionaryList() {
		return this.gpDictionaryList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。字典信息。
	 */
	public void setGpDictionaryList(ArrayList<GpDictionary> gpDictionaryList) {
		this.gpDictionaryList = gpDictionaryList;
	}








}







