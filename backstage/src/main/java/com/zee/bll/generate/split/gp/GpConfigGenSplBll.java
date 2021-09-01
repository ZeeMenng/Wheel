package com.zee.bll.generate.split.gp;

import org.springframework.beans.factory.annotation.Autowired;

import com.zee.bll.generate.split.base.BaseSplBll;
import com.zee.dao.split.gp.IGpConfigSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2021/1/19 11:57:32
 * @description 配置项信息。 业务逻辑处理类，扩展自BaseSplBll<GpConfig>，自动生成。
 */
public class GpConfigGenSplBll extends BaseSplBll {

	@Autowired
	protected IGpConfigSplDal gpConfigSplDal;

}





