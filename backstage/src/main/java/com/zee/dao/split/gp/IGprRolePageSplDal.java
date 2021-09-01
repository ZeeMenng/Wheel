package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GprRolePage;

/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<GprRolePage>，可手动更改。角色拥有的页面权限。
 */

public interface IGprRolePageSplDal extends IBaseSplDal {

	public int add(GprRolePage gprRolePage);

	public int addList(ArrayList<GprRolePage> gprRolePageList);

	public int delete(String id);

	public int deleteByRoleId(String roleid);

	public int deleteByPageId(String pageId);

	public int deleteByIdList(ArrayList<String> idList);

	public int deleteByRoleIdList(ArrayList<String> roleIdList);

	public int deleteByPageIdList(ArrayList<String> pageIdList);

	public int update(GprRolePage gprRolePage);

	public GprRolePage getModel(String id);

	public List<GprRolePage> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}
