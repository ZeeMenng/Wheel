package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GprRoleModule;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<GprRoleModule>，可手动更改。角色拥有的功能模块权限。
 */

public interface IGprRoleModuleSplDal extends IBaseSplDal {

	public int add(GprRoleModule gprRoleModule);

	public int addList(ArrayList<GprRoleModule> gprRoleModuleList);

	public int delete(String id);
	
	public int deleteByRoleId(String roleId);
	
	public int deleteByModuleId(String moduleId);

	public int deleteByIdList(ArrayList<String> idList);
	
	public int deleteByRoleIdList(ArrayList<String> roleIdList);
	
	public int deleteByModuleIdList(ArrayList<String> moduleIdList);

	public int update(GprRoleModule gprRoleModule);

	public GprRoleModule getModel(String id);

	public List<GprRoleModule> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





