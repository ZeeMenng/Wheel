package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GpPage;

/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<GpPage>，可手动更改。系统页面。
 */

public interface IGpPageSplDal extends IBaseSplDal {

	public int add(GpPage gpPage);

	public int addList(ArrayList<GpPage> gpPageList);

	public int delete(String id);

	public int deleteByDomainId(String domainId);

	public int deleteByIdList(ArrayList<String> idList);

	public int deleteByDomainIdList(ArrayList<String> idList);

	public int update(GpPage gpPage);

	public GpPage getModel(String id);

	public GpPage getModelByPageUrl(String domainId, String pageUrl);

	public List<GpPage> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}
