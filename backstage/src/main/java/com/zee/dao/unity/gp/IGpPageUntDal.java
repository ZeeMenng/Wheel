package com.zee.dao.unity.gp;

import java.util.List;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GpPage;
import com.zee.ent.extend.gp.GpControl;
import com.zee.ent.extend.gp.GpDomain;
import com.zee.ent.extend.gp.GprModulePage;
import com.zee.ent.extend.gp.GprRolePage;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/2/2 18:48:27
 * @description 扩展自实体类IBaseUntDal<GpPage>，可手动更改。系统页面。
 */

public interface IGpPageUntDal extends IBaseUntDal<GpPage> {

 
	public int deleteByDomainId(String  domainId);
    
    public int deleteByDomainIdList(List<String> domainIdList);

	public List<GpPage> getListByDomainId(String  domainId);
   

   
}






