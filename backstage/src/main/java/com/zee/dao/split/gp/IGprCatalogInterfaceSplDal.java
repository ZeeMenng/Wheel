package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GpModule;
import com.zee.ent.extend.gp.GprCatalogInterface;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2020/10/21 21:21:11
 * @description 扩展自实体类IBaseUntDal<GprCatalogInterface>，可手动更改。后台接口所属分类。
 */

public interface IGprCatalogInterfaceSplDal extends IBaseSplDal {

	public int add(GprCatalogInterface gprCatalogInterface);

	public int addList(ArrayList<GprCatalogInterface> gprCatalogInterfaceList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);
	
	public int deleteInvalidRecord(ArrayList<GprCatalogInterface> gprCatalogInterfaceList);

	public int update(GprCatalogInterface gprCatalogInterface);

	public GprCatalogInterface getModel(String id);

	public List<GprCatalogInterface> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





