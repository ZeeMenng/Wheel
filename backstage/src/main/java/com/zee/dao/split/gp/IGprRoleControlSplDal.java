package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GprRoleControl;

/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<GprRoleControl>，可手动更改。角色拥有的控件权限。
 */

public interface IGprRoleControlSplDal extends IBaseSplDal {

	public int add(GprRoleControl gprRoleControl);

	public int addList(ArrayList<GprRoleControl> gprRoleControlList);

	public int delete(String id);

	public int deleteByRoleId(String roleId);

	public int deleteByControlId(String controlId);

	public int deleteByIdList(ArrayList<String> idList);

	public int deleteByRoleIdList(ArrayList<String> roleIdList);

	public int deleteByControlIdList(ArrayList<String> controlIdList);

	public int update(GprRoleControl gprRoleControl);

	public GprRoleControl getModel(String id);

	public List<GprRoleControl> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}
