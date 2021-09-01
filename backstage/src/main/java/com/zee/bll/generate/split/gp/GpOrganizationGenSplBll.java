package com.zee.bll.generate.split.gp;

import org.springframework.beans.factory.annotation.Autowired;

import com.zee.bll.generate.split.base.BaseSplBll;
import com.zee.dao.split.gp.IGpOrganizationSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018/5/24 0:40:54
 * @description 组织机构。 业务逻辑处理类，扩展自BaseSplBll<GpOrganization>，自动生成。
 */
public class GpOrganizationGenSplBll extends BaseSplBll {

	@Autowired
	protected IGpOrganizationSplDal gpOrganizationSplDal;

}





