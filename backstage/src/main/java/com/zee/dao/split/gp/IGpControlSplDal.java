package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GpControl;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<GpControl>，可手动更改。系统控件。
 */

public interface IGpControlSplDal extends IBaseSplDal {

	public int add(GpControl gpControl);

	public int addList(ArrayList<GpControl> gpControlList);

	public int delete(String id);

	public int deleteByDomainId(String domainId);

	public int deleteByIdList(ArrayList<String> idList);

	public int deleteByDomainIdList(ArrayList<String> idList);

	public int update(GpControl gpControl);

	public GpControl getModel(String id);

	public List<GpControl> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





