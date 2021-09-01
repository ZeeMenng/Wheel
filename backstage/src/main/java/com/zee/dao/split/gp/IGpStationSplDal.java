package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GpStation;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<GpStation>，可手动更改。岗位。
 */

public interface IGpStationSplDal extends IBaseSplDal {

	public int add(GpStation gpStation);

	public int addList(ArrayList<GpStation> gpStationList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(GpStation gpStation);

	public GpStation getModel(String id);

	public List<GpStation> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





