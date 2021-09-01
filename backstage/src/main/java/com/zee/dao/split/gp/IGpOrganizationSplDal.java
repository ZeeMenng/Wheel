package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GpOrganization;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<GpOrganization>，可手动更改。组织机构。
 */

public interface IGpOrganizationSplDal extends IBaseSplDal {

	public int add(GpOrganization gpOrganization);

	public int addList(ArrayList<GpOrganization> gpOrganizationList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(GpOrganization gpOrganization);

	public GpOrganization getModel(String id);

	public List<GpOrganization> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





