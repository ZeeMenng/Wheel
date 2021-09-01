package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GpRole;

/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<GpRole>，可手动更改。系统角色。
 */

public interface IGpRoleSplDal extends IBaseSplDal {

	public int add(GpRole gpRole);

	public int addList(ArrayList<GpRole> gpRoleList);

	public int delete(String id);

	public int deleteByDomainId(String domainId);

	public int deleteByIdList(ArrayList<String> idList);

	public int deleteByDomainIdList(ArrayList<String> domainIdList);

	public int update(GpRole gpRole);

	public GpRole getModel(String id);

	public List<GpRole> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}
