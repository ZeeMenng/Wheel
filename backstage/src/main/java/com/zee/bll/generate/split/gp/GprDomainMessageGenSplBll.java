package com.zee.bll.generate.split.gp;

import org.springframework.beans.factory.annotation.Autowired;

import com.zee.bll.generate.split.base.BaseSplBll;
import com.zee.dao.split.gp.IGprDomainMessageSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018-9-17 10:14:52
 * @description 应用领域的站内信。 业务逻辑处理类，扩展自BaseSplBll<GprDomainMessage>，自动生成。
 */
public class GprDomainMessageGenSplBll extends BaseSplBll {

	@Autowired
	protected IGprDomainMessageSplDal gprDomainMessageSplDal;

}





