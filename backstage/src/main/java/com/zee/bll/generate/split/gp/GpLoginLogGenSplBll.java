package com.zee.bll.generate.split.gp;

import org.springframework.beans.factory.annotation.Autowired;

import com.zee.bll.generate.split.base.BaseSplBll;
import com.zee.dao.split.gp.IGpLoginLogSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018/6/19 13:56:06
 * @description 登录日志。 业务逻辑处理类，扩展自BaseSplBll<GpLoginLog>，自动生成。
 */
public class GpLoginLogGenSplBll extends BaseSplBll {

	@Autowired
	protected IGpLoginLogSplDal gpLoginLogSplDal;

}





