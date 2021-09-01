package com.zee.bll.generate.split.gp;

import org.springframework.beans.factory.annotation.Autowired;

import com.zee.bll.generate.split.base.BaseSplBll;
import com.zee.dao.split.gp.IGprRolePageSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018/5/24 0:40:53
 * @description 角色拥有的页面权限。 业务逻辑处理类，扩展自BaseSplBll<GprRolePage>，自动生成。
 */
public class GprRolePageGenSplBll extends BaseSplBll {

	@Autowired
	protected IGprRolePageSplDal gprRolePageSplDal;

}





