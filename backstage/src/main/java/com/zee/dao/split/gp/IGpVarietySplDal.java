package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GpVariety;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-7-31 14:22:08
 * @description 扩展自实体类IBaseUntDal<GpVariety>，可手动更改。品种表
 */

public interface IGpVarietySplDal extends IBaseSplDal {

	public int add(GpVariety gpVariety);

	public int addList(ArrayList<GpVariety> gpVarietyList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(GpVariety gpVariety);

	public GpVariety getModel(String id);

	public List<GpVariety> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





