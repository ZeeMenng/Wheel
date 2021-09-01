package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GpEarlyWarning;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-8-17 16:57:52
 * @description 扩展自实体类IBaseUntDal<GpEarlyWarning>，可手动更改。预警阀值表
 */

public interface IGpEarlyWarningSplDal extends IBaseSplDal {

	public int add(GpEarlyWarning gpEarlyWarning);

	public int addList(ArrayList<GpEarlyWarning> gpEarlyWarningList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(GpEarlyWarning gpEarlyWarning);

	public GpEarlyWarning getModel(String id);

	public List<GpEarlyWarning> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





