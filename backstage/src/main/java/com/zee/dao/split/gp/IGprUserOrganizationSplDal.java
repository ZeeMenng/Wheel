package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GprUserOrganization;

/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<GprUserOrganization>，可手动更改。用户所属组织机构。
 */

public interface IGprUserOrganizationSplDal extends IBaseSplDal {

	public int add(GprUserOrganization gprUserOrganization);

	public int addList(ArrayList<GprUserOrganization> gprUserOrganizationList);

	public int delete(String id);

	public int deleteByUserId(String userId);

	public int deleteByOrganizationId(String organizationId);
	
	public int deleteByIdList(ArrayList<String> idList);

	public int deleteByUserIdList(ArrayList<String> userIdList);
	
	public int deleteByOrganizationIdList(ArrayList<String> organizationIdList);

	public int update(GprUserOrganization gprUserOrganization);

	public GprUserOrganization getModel(String id);

	public List<GprUserOrganization> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}
