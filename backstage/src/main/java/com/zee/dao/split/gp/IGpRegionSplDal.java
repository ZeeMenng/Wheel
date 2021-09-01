package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GpRegion;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<GpRegion>，可手动更改。地区信息。
 */

public interface IGpRegionSplDal extends IBaseSplDal {

	public int add(GpRegion gpRegion);

	public int addList(ArrayList<GpRegion> gpRegionList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(GpRegion gpRegion);

	public GpRegion getModel(String id);

	public List<GpRegion> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





