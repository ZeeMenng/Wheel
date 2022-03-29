package com.zee.dao.unity.gp;

import java.util.List;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GprDomainMessage;
import com.zee.ent.extend.gp.GpDomain;
import com.zee.ent.extend.gp.GpMessage;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2022/3/29 18:24:21
 * @description 扩展自实体类IBaseUntDal<GprDomainMessage>，可手动更改。应用领域的站内信。
 */

public interface IGprDomainMessageUntDal extends IBaseUntDal<GprDomainMessage> {

 
	public int deleteByDomainId(String  domainId);
    
    public int deleteByDomainIdList(List<String> domainIdList);

	public List<GprDomainMessage> getListByDomainId(String  domainId);
 
	public int deleteByMessageId(String  messageId);
    
    public int deleteByMessageIdList(List<String> messageIdList);

	public List<GprDomainMessage> getListByMessageId(String  messageId);
        
	public int deleteByCompositeIdList(List<GprDomainMessage> gprDomainMessageList);   
   

   
}






