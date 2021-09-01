package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GpResource;

/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/5/7 15:00:36
 * @description 扩展自实体类IBaseUntDal<GpResource>，可手动更改。通用文件信息存储表。
 */

public interface IGpResourceSplDal extends IBaseSplDal {

	public int add(GpResource gpResource);

	public int addList(ArrayList<GpResource> gpResourceList);

	public int delete(String id);

	public int deleteByDomainId(String domainId);

	public int deleteByIdList(ArrayList<String> idList);

	public int deleteByDomainIdList(ArrayList<String> idList);

	public int update(GpResource gpResource);

	public GpResource getModel(String id);

	public List<GpResource> getList(Map<String, Object> map);

	public List<GpResource> getListByBusinessId(String businessId);

	public List<Map<String, Object>> getListBySQL(String sql);

}
