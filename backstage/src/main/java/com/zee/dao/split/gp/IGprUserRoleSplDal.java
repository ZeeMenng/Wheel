package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GprUserRole;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<GprUserRole>，可手动更改。用户拥有的角色。
 */

public interface IGprUserRoleSplDal extends IBaseSplDal {

	public int add(GprUserRole gprUserRole);

	public int addList(ArrayList<GprUserRole> gprUserRoleList);

	public int delete(String id);

	public int deleteByUserId(String userId);
	
	public int deleteByRoleId(String roleId);

	public int deleteByIdList(ArrayList<String> idList);

	public int deleteByUserIdList(ArrayList<String> userIdList);
	
	public int deleteByRoleIdList(ArrayList<String> roleIdList);
	
	public int update(GprUserRole gprUserRole);

	public GprUserRole getModel(String id);

	public List<GprUserRole> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





