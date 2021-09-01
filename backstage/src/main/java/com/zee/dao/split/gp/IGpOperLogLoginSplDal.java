package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GpOperLogLogin;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<GpOperLogLogin>，可手动更改。登录用户操作日志。
 */

public interface IGpOperLogLoginSplDal extends IBaseSplDal {

	public int add(GpOperLogLogin gpOperLogLogin);

	public int addList(ArrayList<GpOperLogLogin> gpOperLogLoginList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(GpOperLogLogin gpOperLogLogin);

	public GpOperLogLogin getModel(String id);

	public List<GpOperLogLogin> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





