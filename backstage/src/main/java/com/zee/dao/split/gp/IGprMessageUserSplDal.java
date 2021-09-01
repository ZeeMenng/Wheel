package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GprMessageUser;

/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<GprMessageUser>，可手动更改。消息接收者及消息读取状态。
 */

public interface IGprMessageUserSplDal extends IBaseSplDal {

	public int add(GprMessageUser gprMessageUser);

	public int addList(ArrayList<GprMessageUser> gprMessageUserList);

	public int delete(String id);

	public int deleteByMessageId(String messageId);

	public int deleteByUserId(String userId);

	public int deleteByIdList(ArrayList<String> idList);

	public int deleteByUserIdList(ArrayList<String> userIdList);

	public int deleteByMessageIdList(ArrayList<String> messageIdList);

	public int update(GprMessageUser gprMessageUser);

	public GprMessageUser getModel(String id);

	public List<GprMessageUser> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}
