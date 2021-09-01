package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GpRegionCountry;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<GpRegionCountry>，可手动更改。国家信息。
 */

public interface IGpRegionCountrySplDal extends IBaseSplDal {

	public int add(GpRegionCountry gpRegionCountry);

	public int addList(ArrayList<GpRegionCountry> gpRegionCountryList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(GpRegionCountry gpRegionCountry);

	public GpRegionCountry getModel(String id);

	public List<GpRegionCountry> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





