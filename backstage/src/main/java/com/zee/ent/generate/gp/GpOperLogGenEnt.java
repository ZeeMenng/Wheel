package com.zee.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;
import com.zee.ent.extend.gp.GpDomain;
import com.zee.ent.extend.gp.GpOperLogLogin;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/2/2 18:48:49
 * @description 实体类GpOperLogGenEnt，自动生成。操作日志。
 */

public class GpOperLogGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="记录创建时间。",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="应用领域。外键，引用应用领域表（domain）的主键。",hidden=false,required=false)
    private String domainId;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="期望影响记录总数。期望写操作影响的的记录总数，不确定数量（比如关联删除、非主键删除）或读取操作时传入参数为0。",hidden=false,required=false)
    private Integer incomeCount;
    @ApiModelProperty(value="传入参数。记录调用BLL层方法时传入的参数值，对象的话序列化成JSON字符串保存。",hidden=false,required=false)
    private String incomeValue;
    @ApiModelProperty(value="操作是否成功。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。 ",allowableValues="0,1",hidden=false,required=false)
    private Byte isSuccessCode;
    @ApiModelProperty(value="操作记录主键。只记录单条记录操作时的主键，暂不考虑记录针对多条记录操作的主键列表。",hidden=false,required=false)
    private String objectId;
    @ApiModelProperty(value="操作类型。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义9种类型：0其它，1添加记录，2批量添加，3删除记录，4批量删除，5修改记录，6单条查询，7模糊查询，8自定义查询。",allowableValues="0,1",hidden=false,required=false)
    private Byte operTypeCode;
    @ApiModelProperty(value="操作类型。文本，对应数据字典表（dictionary）中的文本字段（text）。目前先定义9种类型：0其它，1添加记录，2批量添加，3删除记录，4批量删除，5修改记录，6单条查询，7模糊查询，8自定义查询。",hidden=false,required=false)
    private String operTypeText;
    @ApiModelProperty(value="备注字段。",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="操作结果编码。操作结果和一套编码表对应，暂不进行具体设计，只是在程序根据现有代码设计给出了一部分编码规则。",allowableValues="0,1",hidden=false,required=false)
    private Integer resultCode;
    @ApiModelProperty(value="提示信息。BLL层方法执行后返回给调用者的提示信息。",hidden=false,required=false)
    private String resultMessage;
    @ApiModelProperty(value="返回值。记录调用BLL层方法时返回的参数值，对象的话序列化成JSON字符串保存。",hidden=false,required=false)
    private String returnValue;
    @ApiModelProperty(value="操作表名。只记录核心表名，暂不考虑记录操作动作涉及的所有表名列表。",hidden=false,required=false)
    private String tableName;
    @ApiModelProperty(value="记录总数。写入操作的记录总数或读取到符合条件的总数。",hidden=false,required=false)
    private long totalCount;

   //本表做为子表时，父表实体对象
    private  GpDomain gpDomain;

    //本表做为父表时，子表数据列表
    private ArrayList<GpOperLogLogin> gpOperLogLoginList;   

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
	 * get方法。期望影响记录总数。期望写操作影响的的记录总数，不确定数量（比如关联删除、非主键删除）或读取操作时传入参数为0。
	 */
	public Integer getIncomeCount() {
		return this.incomeCount;
	}

	/**
	 * set方法。期望影响记录总数。期望写操作影响的的记录总数，不确定数量（比如关联删除、非主键删除）或读取操作时传入参数为0。
	 */
	public void setIncomeCount(Integer incomeCount) {
		this.incomeCount = incomeCount;
	}
    
	/**
	 * get方法。传入参数。记录调用BLL层方法时传入的参数值，对象的话序列化成JSON字符串保存。
	 */
	public String getIncomeValue() {
		return this.incomeValue;
	}

	/**
	 * set方法。传入参数。记录调用BLL层方法时传入的参数值，对象的话序列化成JSON字符串保存。
	 */
	public void setIncomeValue(String incomeValue) {
		this.incomeValue = incomeValue;
	}
    
	/**
	 * get方法。操作是否成功。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。 
	 */
	public Byte getIsSuccessCode() {
		return this.isSuccessCode;
	}

	/**
	 * set方法。操作是否成功。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。 
	 */
	public void setIsSuccessCode(Byte isSuccessCode) {
		this.isSuccessCode = isSuccessCode;
	}
    
	/**
	 * get方法。操作记录主键。只记录单条记录操作时的主键，暂不考虑记录针对多条记录操作的主键列表。
	 */
	public String getObjectId() {
		return this.objectId;
	}

	/**
	 * set方法。操作记录主键。只记录单条记录操作时的主键，暂不考虑记录针对多条记录操作的主键列表。
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
    
	/**
	 * get方法。操作类型。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义9种类型：0其它，1添加记录，2批量添加，3删除记录，4批量删除，5修改记录，6单条查询，7模糊查询，8自定义查询。
	 */
	public Byte getOperTypeCode() {
		return this.operTypeCode;
	}

	/**
	 * set方法。操作类型。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义9种类型：0其它，1添加记录，2批量添加，3删除记录，4批量删除，5修改记录，6单条查询，7模糊查询，8自定义查询。
	 */
	public void setOperTypeCode(Byte operTypeCode) {
		this.operTypeCode = operTypeCode;
	}
    
	/**
	 * get方法。操作类型。文本，对应数据字典表（dictionary）中的文本字段（text）。目前先定义9种类型：0其它，1添加记录，2批量添加，3删除记录，4批量删除，5修改记录，6单条查询，7模糊查询，8自定义查询。
	 */
	public String getOperTypeText() {
		return this.operTypeText;
	}

	/**
	 * set方法。操作类型。文本，对应数据字典表（dictionary）中的文本字段（text）。目前先定义9种类型：0其它，1添加记录，2批量添加，3删除记录，4批量删除，5修改记录，6单条查询，7模糊查询，8自定义查询。
	 */
	public void setOperTypeText(String operTypeText) {
		this.operTypeText = operTypeText;
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
	 * get方法。操作结果编码。操作结果和一套编码表对应，暂不进行具体设计，只是在程序根据现有代码设计给出了一部分编码规则。
	 */
	public Integer getResultCode() {
		return this.resultCode;
	}

	/**
	 * set方法。操作结果编码。操作结果和一套编码表对应，暂不进行具体设计，只是在程序根据现有代码设计给出了一部分编码规则。
	 */
	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}
    
	/**
	 * get方法。提示信息。BLL层方法执行后返回给调用者的提示信息。
	 */
	public String getResultMessage() {
		return this.resultMessage;
	}

	/**
	 * set方法。提示信息。BLL层方法执行后返回给调用者的提示信息。
	 */
	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
    
	/**
	 * get方法。返回值。记录调用BLL层方法时返回的参数值，对象的话序列化成JSON字符串保存。
	 */
	public String getReturnValue() {
		return this.returnValue;
	}

	/**
	 * set方法。返回值。记录调用BLL层方法时返回的参数值，对象的话序列化成JSON字符串保存。
	 */
	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}
    
	/**
	 * get方法。操作表名。只记录核心表名，暂不考虑记录操作动作涉及的所有表名列表。
	 */
	public String getTableName() {
		return this.tableName;
	}

	/**
	 * set方法。操作表名。只记录核心表名，暂不考虑记录操作动作涉及的所有表名列表。
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
    
	/**
	 * get方法。记录总数。写入操作的记录总数或读取到符合条件的总数。
	 */
	public long getTotalCount() {
		return this.totalCount;
	}

	/**
	 * set方法。记录总数。写入操作的记录总数或读取到符合条件的总数。
	 */
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
    



	/**
	 * get方法。本表做为父表时，子表实体对象。登录用户操作日志。
	 */
	public ArrayList<GpOperLogLogin> getGpOperLogLoginList() {
		return this.gpOperLogLoginList;
	}

	/**
	 * set方法。本表做为父表时，子表实体对象。登录用户操作日志。
	 */
	public void setGpOperLogLoginList(ArrayList<GpOperLogLogin> gpOperLogLoginList) {
		this.gpOperLogLoginList = gpOperLogLoginList;
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







