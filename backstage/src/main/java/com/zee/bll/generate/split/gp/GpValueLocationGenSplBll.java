package com.zee.bll.generate.split.gp;

import org.springframework.beans.factory.annotation.Autowired;

import com.zee.bll.generate.split.base.BaseSplBll;
import com.zee.dao.split.gp.IGpValueLocationSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2020/8/11 12:00:42
 * @description 调用存储过程查询某个值在本数据库中的位置，记录相关信息到本表中。 业务逻辑处理类，扩展自BaseSplBll<GpValueLocation>，自动生成。
 */
public class GpValueLocationGenSplBll extends BaseSplBll {

	@Autowired
	protected IGpValueLocationSplDal gpValueLocationSplDal;

}





