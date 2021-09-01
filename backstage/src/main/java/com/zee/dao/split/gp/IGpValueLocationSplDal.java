package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GpValueLocation;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2020/8/11 12:00:42
 * @description 扩展自实体类IBaseUntDal<GpValueLocation>，可手动更改。调用存储过程查询某个值在本数据库中的位置，记录相关信息到本表中。
 */

public interface IGpValueLocationSplDal extends IBaseSplDal {

	public int add(GpValueLocation gpValueLocation);

	public int addList(ArrayList<GpValueLocation> gpValueLocationList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(GpValueLocation gpValueLocation);

	public GpValueLocation getModel(String id);

	public List<GpValueLocation> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





