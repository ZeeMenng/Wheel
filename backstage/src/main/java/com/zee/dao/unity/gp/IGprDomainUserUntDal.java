package com.zee.dao.unity.gp;

import java.util.List;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GprDomainUser;
import com.zee.ent.extend.gp.GpDomain;
import com.zee.ent.extend.gp.GpUser;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/2/2 18:48:20
 * @description 扩展自实体类IBaseUntDal<GprDomainUser>，可手动更改。应用领域拥有的用户。
 */

public interface IGprDomainUserUntDal extends IBaseUntDal<GprDomainUser> {

 
	public int deleteByDomainId(String  domainId);
    
    public int deleteByDomainIdList(List<String> domainIdList);

	public List<GprDomainUser> getListByDomainId(String  domainId);
 
	public int deleteByUserId(String  userId);
    
    public int deleteByUserIdList(List<String> userIdList);

	public List<GprDomainUser> getListByUserId(String  userId);
        
	public int deleteByCompositeIdList(List<GprDomainUser> gprDomainUserList);   
   

   
}






