package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GpMenu;

/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<GpMenu>，可手动更改。链接菜单。
 */

public interface IGpMenuSplDal extends IBaseSplDal {

	public int add(GpMenu gpMenu);

	public int addList(ArrayList<GpMenu> gpMenuList);

	public int delete(String id);

	public int deleteByDomainId(String domainId);
	
	public int deleteByIdList(ArrayList<String> idList);

	public int deleteByDomainIdList(ArrayList<String> domainIdList);

	public int update(GpMenu gpMenu);

	public GpMenu getModel(String id);

	public List<GpMenu> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}
