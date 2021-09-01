package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GprUserBase;

/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-6-21 10:22:39
 * @description 扩展自实体类IBaseUntDal<GprUserBase>，可手动更改。用户归属的基地。
 */

public interface IGprUserBaseSplDal extends IBaseSplDal {

	public int add(GprUserBase gprUserBase);

	public int addList(ArrayList<GprUserBase> gprUserBaseList);

	public int delete(String id);

	public int deleteByUserId(String userId);

	public int deleteByBaseId(String userId);

	public int deleteByIdList(ArrayList<String> idList);

	public int deleteByUserIdList(ArrayList<String> userIdList);

	public int deleteByBaseIdList(ArrayList<String> userIdList);

	public int update(GprUserBase gprUserBase);

	public GprUserBase getModel(String id);

	public List<GprUserBase> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}
