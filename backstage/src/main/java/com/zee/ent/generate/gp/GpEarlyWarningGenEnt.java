package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/2/2 18:48:48
 * @description 实体类GpEarlyWarningGenEnt，自动生成。预警阀值表
 */

public class GpEarlyWarningGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="创建时间",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="生育期开始时间，如3-1",hidden=false,required=false)
    private String beginDate;
    @ApiModelProperty(value="生育期结束时间，如3-30",hidden=false,required=false)
    private String endDate;
    @ApiModelProperty(value="生育期id",hidden=false,required=false)
    private String growthId;
    @ApiModelProperty(value="生育期名称",hidden=false,required=false)
    private String growthName;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="最大阀值,默认阀值",hidden=false,required=false)
    private Double maxVal;
    @ApiModelProperty(value="最小阀值",hidden=false,required=false)
    private Double minVal;
    @ApiModelProperty(value="备注",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="对应字典表状态code",allowableValues="0,1",hidden=false,required=false)
    private Byte stutasCode;
    @ApiModelProperty(value="对应字典表状态text",hidden=false,required=false)
    private String stutasText;
    @ApiModelProperty(value="提醒信息",hidden=false,required=false)
    private String tipsMess;
    @ApiModelProperty(value="最后一次修改时间",hidden=false,required=false)
    private Date updateTime;
    @ApiModelProperty(value="预警指标名称",hidden=false,required=false)
    private String warningName;
    @ApiModelProperty(value="预警指标类型",hidden=false,required=false)
    private Integer warningType;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。创建时间
	 */
	public Date getAddTime() {
		return this.addTime;
	}

	/**
	 * set方法。创建时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
    
	/**
	 * get方法。生育期开始时间，如3-1
	 */
	public String getBeginDate() {
		return this.beginDate;
	}

	/**
	 * set方法。生育期开始时间，如3-1
	 */
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
    
	/**
	 * get方法。生育期结束时间，如3-30
	 */
	public String getEndDate() {
		return this.endDate;
	}

	/**
	 * set方法。生育期结束时间，如3-30
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
    
	/**
	 * get方法。生育期id
	 */
	public String getGrowthId() {
		return this.growthId;
	}

	/**
	 * set方法。生育期id
	 */
	public void setGrowthId(String growthId) {
		this.growthId = growthId;
	}
    
	/**
	 * get方法。生育期名称
	 */
	public String getGrowthName() {
		return this.growthName;
	}

	/**
	 * set方法。生育期名称
	 */
	public void setGrowthName(String growthName) {
		this.growthName = growthName;
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
	 * get方法。最大阀值,默认阀值
	 */
	public Double getMaxVal() {
		return this.maxVal;
	}

	/**
	 * set方法。最大阀值,默认阀值
	 */
	public void setMaxVal(Double maxVal) {
		this.maxVal = maxVal;
	}
    
	/**
	 * get方法。最小阀值
	 */
	public Double getMinVal() {
		return this.minVal;
	}

	/**
	 * set方法。最小阀值
	 */
	public void setMinVal(Double minVal) {
		this.minVal = minVal;
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
	 * get方法。对应字典表状态code
	 */
	public Byte getStutasCode() {
		return this.stutasCode;
	}

	/**
	 * set方法。对应字典表状态code
	 */
	public void setStutasCode(Byte stutasCode) {
		this.stutasCode = stutasCode;
	}
    
	/**
	 * get方法。对应字典表状态text
	 */
	public String getStutasText() {
		return this.stutasText;
	}

	/**
	 * set方法。对应字典表状态text
	 */
	public void setStutasText(String stutasText) {
		this.stutasText = stutasText;
	}
    
	/**
	 * get方法。提醒信息
	 */
	public String getTipsMess() {
		return this.tipsMess;
	}

	/**
	 * set方法。提醒信息
	 */
	public void setTipsMess(String tipsMess) {
		this.tipsMess = tipsMess;
	}
    
	/**
	 * get方法。最后一次修改时间
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * set方法。最后一次修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
	/**
	 * get方法。预警指标名称
	 */
	public String getWarningName() {
		return this.warningName;
	}

	/**
	 * set方法。预警指标名称
	 */
	public void setWarningName(String warningName) {
		this.warningName = warningName;
	}
    
	/**
	 * get方法。预警指标类型
	 */
	public Integer getWarningType() {
		return this.warningType;
	}

	/**
	 * set方法。预警指标类型
	 */
	public void setWarningType(Integer warningType) {
		this.warningType = warningType;
	}
    










}







