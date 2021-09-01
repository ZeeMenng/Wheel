package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GpOperLog;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<GpOperLog>，可手动更改。操作日志。
 */

public interface IGpOperLogSplDal extends IBaseSplDal {

	public int add(GpOperLog gpOperLog);

	public int addList(ArrayList<GpOperLog> gpOperLogList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(GpOperLog gpOperLog);

	public GpOperLog getModel(String id);

	public List<GpOperLog> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





