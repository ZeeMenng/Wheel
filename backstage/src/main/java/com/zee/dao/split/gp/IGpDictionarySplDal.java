package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GpDictionary;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<GpDictionary>，可手动更改。字典信息。
 */

public interface IGpDictionarySplDal extends IBaseSplDal {

	public int add(GpDictionary gpDictionary);

	public int addList(ArrayList<GpDictionary> gpDictionaryList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(GpDictionary gpDictionary);

	public GpDictionary getModel(String id);

	public List<GpDictionary> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





