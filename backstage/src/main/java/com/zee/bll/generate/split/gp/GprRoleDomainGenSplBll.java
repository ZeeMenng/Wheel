package com.zee.bll.generate.split.gp;

import org.springframework.beans.factory.annotation.Autowired;

import com.zee.bll.generate.split.base.BaseSplBll;
import com.zee.dao.split.gp.IGprRoleDomainSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2020/8/11 11:39:56
 * @description 角色拥有的功能模块权限。 业务逻辑处理类，扩展自BaseSplBll<GprRoleDomain>，自动生成。
 */
public class GprRoleDomainGenSplBll extends BaseSplBll {

	@Autowired
	protected IGprRoleDomainSplDal gprRoleDomainSplDal;

}





