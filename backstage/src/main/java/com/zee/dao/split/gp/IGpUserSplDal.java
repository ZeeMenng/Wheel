package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GpUser;

/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<GpUser>，可手动更改。系统用户。
 */

public interface IGpUserSplDal extends IBaseSplDal {

	public int add(GpUser gpUser);

	public int addList(ArrayList<GpUser> gpUserList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(GpUser gpUser);

	public GpUser getModel(String id);

	public GpUser getModelByUserName(String userName);

	public List<GpUser> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}
