package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GprDomainMessage;

/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018-9-17 10:14:52
 * @description 扩展自实体类IBaseUntDal<GprDomainMessage>，可手动更改。应用领域的站内信。
 */

public interface IGprDomainMessageSplDal extends IBaseSplDal {

	public int add(GprDomainMessage gprDomainMessage);

	public int addList(ArrayList<GprDomainMessage> gprDomainMessageList);

	public int delete(String id);

	public int deleteByMessageId(String messageId);

	public int deleteByDomainId(String domainId);
	
	public int deleteByIdList(ArrayList<String> idList);

	public int deleteByMessageIdList(ArrayList<String> messageIdList);

	public int deleteByDomainIdList(ArrayList<String> domainIdList);

	public int update(GprDomainMessage gprDomainMessage);

	public GprDomainMessage getModel(String id);

	public List<GprDomainMessage> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}
