package com.zee.dao.unity.gp;

import java.util.List;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GpToken;
import com.zee.ent.extend.gp.GpOperLogLogin;
import com.zee.ent.extend.gp.GpDomain;
import com.zee.ent.extend.gp.GpLoginLog;
import com.zee.ent.extend.gp.GpUser;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/2/2 18:48:28
 * @description 扩展自实体类IBaseUntDal<GpToken>，可手动更改。token信息。
 */

public interface IGpTokenUntDal extends IBaseUntDal<GpToken> {

 
	public int deleteByDomainId(String  domainId);
    
    public int deleteByDomainIdList(List<String> domainIdList);

	public List<GpToken> getListByDomainId(String  domainId);
 
	public int deleteByUserId(String  userId);
    
    public int deleteByUserIdList(List<String> userIdList);

	public List<GpToken> getListByUserId(String  userId);
        
	public int deleteByCompositeIdList(List<GpToken> gpTokenList);   
   

   
}






