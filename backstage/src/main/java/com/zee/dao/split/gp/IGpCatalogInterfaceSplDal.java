package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GpCatalogInterface;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2020/10/21 21:21:11
 * @description 扩展自实体类IBaseUntDal<GpCatalogInterface>，可手动更改。接口分类字典管理存放接口分类信息，支持树形分级分类，主要但不限于业务上的分类方式，支持同时对接口进行多种分类。
 */

public interface IGpCatalogInterfaceSplDal extends IBaseSplDal {

	public int add(GpCatalogInterface gpCatalogInterface);

	public int addList(ArrayList<GpCatalogInterface> gpCatalogInterfaceList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(GpCatalogInterface gpCatalogInterface);

	public GpCatalogInterface getModel(String id);

	public List<GpCatalogInterface> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





