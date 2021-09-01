package com.zee.bll.generate.split.gp;

import org.springframework.beans.factory.annotation.Autowired;

import com.zee.bll.generate.split.base.BaseSplBll;
import com.zee.dao.split.gp.IGpRegionSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018/5/24 0:40:54
 * @description 地区信息。 业务逻辑处理类，扩展自BaseSplBll<GpRegion>，自动生成。
 */
public class GpRegionGenSplBll extends BaseSplBll {

	@Autowired
	protected IGpRegionSplDal gpRegionSplDal;

}





