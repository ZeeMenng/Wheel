package com.zee.bll.generate.split.gp;

import org.springframework.beans.factory.annotation.Autowired;

import com.zee.bll.generate.split.base.BaseSplBll;
import com.zee.dao.split.gp.IGprResourceSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2020/7/4 16:32:20
 * @description 附件关联表。只要存有附件字段的表，都会通过此表于gp_resource表关联。 业务逻辑处理类，扩展自BaseSplBll<GprResource>，自动生成。
 */
public class GprResourceGenSplBll extends BaseSplBll {

	@Autowired
	protected IGprResourceSplDal gprResourceSplDal;

}





