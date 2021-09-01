package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GprRoleInterface;

/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<GprRoleInterface>，可手动更改。角色拥有的接口权限。
 */

public interface IGprRoleInterfaceSplDal extends IBaseSplDal {

	public int add(GprRoleInterface gprRoleInterface);

	public int addList(ArrayList<GprRoleInterface> gprRoleInterfaceList);

	public int delete(String id);

	public int deleteByRoleId(String roleId);

	public int deleteByInterfaceId(String interfaceId);

	public int deleteByIdList(ArrayList<String> idList);

	public int deleteByRoleIdList(ArrayList<String> roleIdList);

	public int deleteByInterfaceIdList(ArrayList<String> interfaceIdList);

	public int deleteByCompositeIdList(ArrayList<GprRoleInterface> gprRoleInterfaceList);
	
	public int update(GprRoleInterface gprRoleInterface);

	public GprRoleInterface getModel(String id);

	public List<GprRoleInterface> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}
