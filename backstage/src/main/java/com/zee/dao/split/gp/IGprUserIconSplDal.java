package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GprUserIcon;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/5/7 15:00:36
 * @description 扩展自实体类IBaseUntDal<GprUserIcon>，可手动更改。用户头像表
 */

public interface IGprUserIconSplDal extends IBaseSplDal {

	public int add(GprUserIcon gprUserIcon);

	public int addList(ArrayList<GprUserIcon> gprUserIconList);

	public int delete(String id);

	public int deleteByUserId(String userId);
	
	public int deleteByResourceId(String resourceId);

	public int deleteByIdList(ArrayList<String> idList);
	
	public int deleteByUserIdList(ArrayList<String> userIdList);
	
	public int deleteByResourceIdList(ArrayList<String> resourceIdList);

	public int update(GprUserIcon gprUserIcon);

	public GprUserIcon getModel(String id);

	public List<GprUserIcon> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





