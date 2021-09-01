package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GprRoleDomain;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2020/8/11 11:39:55
 * @description 扩展自实体类IBaseUntDal<GprRoleDomain>，可手动更改。角色拥有的功能模块权限。
 */

public interface IGprRoleDomainSplDal extends IBaseSplDal {

	public int add(GprRoleDomain gprRoleDomain);

	public int addList(ArrayList<GprRoleDomain> gprRoleDomainList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);
	
	public int deleteByRoleId(String roleId);

	public int deleteByRoleIdList(ArrayList<String> roleIdList);

	public int update(GprRoleDomain gprRoleDomain);

	public GprRoleDomain getModel(String id);

	public List<GprRoleDomain> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





