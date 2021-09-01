package com.zee.bll.generate.split.gp;

import org.springframework.beans.factory.annotation.Autowired;

import com.zee.bll.generate.split.base.BaseSplBll;
import com.zee.dao.split.gp.IGpDomainSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018/5/24 0:40:53
 * @description 应用领域。 业务逻辑处理类，扩展自BaseSplBll<GpDomain>，自动生成。
 */
public class GpDomainGenSplBll extends BaseSplBll {

	@Autowired
	protected IGpDomainSplDal gpDomainSplDal;

}





