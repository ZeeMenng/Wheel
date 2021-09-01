package com.zee.bll.generate.split.gp;

import org.springframework.beans.factory.annotation.Autowired;

import com.zee.bll.generate.split.base.BaseSplBll;
import com.zee.dao.split.gp.IGpEarlyWarningSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018-8-17 16:57:52
 * @description 预警阀值表 业务逻辑处理类，扩展自BaseSplBll<GpEarlyWarning>，自动生成。
 */
public class GpEarlyWarningGenSplBll extends BaseSplBll {

	@Autowired
	protected IGpEarlyWarningSplDal gpEarlyWarningSplDal;

}





