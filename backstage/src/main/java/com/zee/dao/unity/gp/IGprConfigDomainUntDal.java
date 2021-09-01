package com.zee.dao.unity.gp;

import java.util.List;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GprConfigDomain;
import com.zee.ent.extend.gp.GpDomain;
import com.zee.ent.extend.gp.GpConfig;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/2/2 18:48:19
 * @description 扩展自实体类IBaseUntDal<GprConfigDomain>，可手动更改。应用领域配置信息。
 */

public interface IGprConfigDomainUntDal extends IBaseUntDal<GprConfigDomain> {

 
	public int deleteByDomainId(String  domainId);
    
    public int deleteByDomainIdList(List<String> domainIdList);

	public List<GprConfigDomain> getListByDomainId(String  domainId);
 
	public int deleteByConfigId(String  configId);
    
    public int deleteByConfigIdList(List<String> configIdList);

	public List<GprConfigDomain> getListByConfigId(String  configId);
        
	public int deleteByCompositeIdList(List<GprConfigDomain> gprConfigDomainList);   
   

   
}






