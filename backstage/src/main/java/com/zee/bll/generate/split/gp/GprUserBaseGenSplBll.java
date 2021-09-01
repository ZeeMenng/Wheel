package com.zee.bll.generate.split.gp;

import org.springframework.beans.factory.annotation.Autowired;

import com.zee.bll.generate.split.base.BaseSplBll;
import com.zee.dao.split.gp.IGprUserBaseSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018-6-21 10:22:39
 * @description 用户归属的基地。 业务逻辑处理类，扩展自BaseSplBll<GprUserBase>，自动生成。
 */
public class GprUserBaseGenSplBll extends BaseSplBll {

	@Autowired
	protected IGprUserBaseSplDal gprUserBaseSplDal;

}





