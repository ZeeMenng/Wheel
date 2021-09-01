package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GprModulePage;

/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<GprModulePage>，可手动更改。功能模块所包含的页面。
 */

public interface IGprModulePageSplDal extends IBaseSplDal {

	public int add(GprModulePage gprModulePage);

	public int addList(ArrayList<GprModulePage> gprModulePageList);

	public int delete(String id);

	public int deleteByModuleId(String moduleId);

	public int deleteByPageId(String pageId);

	public int deleteByIdList(ArrayList<String> idList);

	public int deleteByModuleIdList(ArrayList<String> moduleIddList);

	public int deleteByPageIdList(ArrayList<String> pageIdList);

	public int update(GprModulePage gprModulePage);

	public GprModulePage getModel(String id);

	public List<GprModulePage> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}
