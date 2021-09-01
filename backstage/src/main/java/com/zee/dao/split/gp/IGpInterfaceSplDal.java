package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GpInterface;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<GpInterface>，可手动更改。系统接口。
 */

public interface IGpInterfaceSplDal extends IBaseSplDal {

	public int add(GpInterface gpInterface);

	public int addList(ArrayList<GpInterface> gpInterfaceList);

	public int addListWithDffOrAdd(ArrayList<GpInterface> gpInterfaceList);
	
	public int delete(String id);
	
	public int deleteByDomainId(String domainId);

	public int deleteByIdList(ArrayList<String> idList);

	public int deleteByDomainIdList(ArrayList<String> idList);
	
	public int update(GpInterface gpInterface);

	public GpInterface getModel(String id);
	
	public GpInterface getModelByUrl(String url);

	public List<GpInterface> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

	public int isPermitted(String userId,String url);
	
	
}





