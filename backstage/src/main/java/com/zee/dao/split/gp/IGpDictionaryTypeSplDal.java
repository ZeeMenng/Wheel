package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GpDictionaryType;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<GpDictionaryType>，可手动更改。字典类型。
 */

public interface IGpDictionaryTypeSplDal extends IBaseSplDal {

	public int add(GpDictionaryType gpDictionaryType);

	public int addList(ArrayList<GpDictionaryType> gpDictionaryTypeList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(GpDictionaryType gpDictionaryType);

	public GpDictionaryType getModel(String id);

	public List<GpDictionaryType> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





