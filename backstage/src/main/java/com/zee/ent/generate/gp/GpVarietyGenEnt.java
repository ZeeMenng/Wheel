package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/2/2 18:48:53
 * @description 实体类GpVarietyGenEnt，自动生成。品种表
 */

public class GpVarietyGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="创建时间",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="创建者",hidden=false,required=false)
    private String addUserId;
    @ApiModelProperty(value="时间周期,单位为天",hidden=false,required=false)
    private Integer cycleTime;
    @ApiModelProperty(value="描述",hidden=false,required=false)
    private String describes;
    @ApiModelProperty(value="id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="品种名称",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="父级id",hidden=false,required=false)
    private String parentId;
    @ApiModelProperty(value="排序字段",hidden=false,required=false)
    private Integer priority;
    @ApiModelProperty(value="关系id/唯一code",hidden=false,required=false)
    private String relationId;
    @ApiModelProperty(value="备注字段",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="对应附件表id",hidden=false,required=false)
    private String resourceId;
    @ApiModelProperty(value="对应附件表url",hidden=false,required=false)
    private String resourceUrl;
    @ApiModelProperty(value="对应字典表状态code",allowableValues="0,1",hidden=false,required=false)
    private Byte stutasCode;
    @ApiModelProperty(value="对应字典表状态text",hidden=false,required=false)
    private String stutasText;
    @ApiModelProperty(value="最后一次修改时间",hidden=false,required=false)
    private Date updateTime;

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
	 * get方法。创建者
	 */
	public String getAddUserId() {
		return this.addUserId;
	}

	/**
	 * set方法。创建者
	 */
	public void setAddUserId(String addUserId) {
		this.addUserId = addUserId;
	}
    
	/**
	 * get方法。时间周期,单位为天
	 */
	public Integer getCycleTime() {
		return this.cycleTime;
	}

	/**
	 * set方法。时间周期,单位为天
	 */
	public void setCycleTime(Integer cycleTime) {
		this.cycleTime = cycleTime;
	}
    
	/**
	 * get方法。描述
	 */
	public String getDescribes() {
		return this.describes;
	}

	/**
	 * set方法。描述
	 */
	public void setDescribes(String describes) {
		this.describes = describes;
	}
    
	/**
	 * get方法。id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set方法。id
	 */
	public void setId(String id) {
		this.id = id;
	}
    
	/**
	 * get方法。品种名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。品种名称
	 */
	public void setName(String name) {
		this.name = name;
	}
    
	/**
	 * get方法。父级id
	 */
	public String getParentId() {
		return this.parentId;
	}

	/**
	 * set方法。父级id
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
    
	/**
	 * get方法。排序字段
	 */
	public Integer getPriority() {
		return this.priority;
	}

	/**
	 * set方法。排序字段
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
    
	/**
	 * get方法。关系id/唯一code
	 */
	public String getRelationId() {
		return this.relationId;
	}

	/**
	 * set方法。关系id/唯一code
	 */
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
    
	/**
	 * get方法。备注字段
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set方法。备注字段
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
	/**
	 * get方法。对应附件表id
	 */
	public String getResourceId() {
		return this.resourceId;
	}

	/**
	 * set方法。对应附件表id
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
    
	/**
	 * get方法。对应附件表url
	 */
	public String getResourceUrl() {
		return this.resourceUrl;
	}

	/**
	 * set方法。对应附件表url
	 */
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
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
    










}







