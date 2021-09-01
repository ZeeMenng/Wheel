package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GprConfigUser;

/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2021/1/19 11:24:04
 * @description 扩展自实体类IBaseUntDal<GprConfigUser>，可手动更改。用户配置信息。
 */

public interface IGprConfigUserSplDal extends IBaseSplDal {

	public int add(GprConfigUser gprConfigUser);

	public int addList(ArrayList<GprConfigUser> gprConfigUserList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(GprConfigUser gprConfigUser);

	public int updateByCompositeId(GprConfigUser gprConfigUser);

	public GprConfigUser getModel(String id);

	public List<GprConfigUser> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}
