package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GprUserStation;

/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<GprUserStation>，可手动更改。用户所属岗位。
 */

public interface IGprUserStationSplDal extends IBaseSplDal {

	public int add(GprUserStation gprUserStation);

	public int addList(ArrayList<GprUserStation> gprUserStationList);

	public int delete(String id);

	public int deleteByUserId(String userId);

	public int deleteByStationId(String stationId);

	public int deleteByIdList(ArrayList<String> idList);

	public int deleteByUserIdList(ArrayList<String> userIdList);

	public int deleteByStationIdList(ArrayList<String> stationIdList);

	public int update(GprUserStation gprUserStation);

	public GprUserStation getModel(String id);

	public List<GprUserStation> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}
