package com.zee.bll.generate.split.gp;

import org.springframework.beans.factory.annotation.Autowired;

import com.zee.bll.generate.split.base.BaseSplBll;
import com.zee.dao.split.gp.IGpCatalogInterfaceSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2020/10/21 21:21:11
 * @description 接口分类字典管理存放接口分类信息，支持树形分级分类，主要但不限于业务上的分类方式，支持同时对接口进行多种分类。 业务逻辑处理类，扩展自BaseSplBll<GpCatalogInterface>，自动生成。
 */
public class GpCatalogInterfaceGenSplBll extends BaseSplBll {

	@Autowired
	protected IGpCatalogInterfaceSplDal gpCatalogInterfaceSplDal;

}





