package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GpMessage;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/5/7 15:00:36
 * @description 扩展自实体类IBaseUntDal<GpMessage>，可手动更改。消息表。如果消息类型是公告，在用户读取消息的时候向消息队列表（gpr_message_user）插入数据；如果消息类型是私信和提醒，则新建消息后立即向消息列表（gpr_message_use）中插入数据。
 */

public interface IGpMessageSplDal extends IBaseSplDal {

	public int add(GpMessage gpMessage);

	public int addList(ArrayList<GpMessage> gpMessageList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(GpMessage gpMessage);

	public GpMessage getModel(String id);

	public List<GpMessage> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





