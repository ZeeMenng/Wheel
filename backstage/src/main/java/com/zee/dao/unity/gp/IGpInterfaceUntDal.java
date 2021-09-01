package com.zee.dao.unity.gp;

import java.util.List;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GpInterface;
import com.zee.ent.extend.gp.GpDomain;
import com.zee.ent.extend.gp.GprRoleInterface;
import com.zee.ent.extend.gp.GprCatalogInterface;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/2/2 18:48:25
 * @description 扩展自实体类IBaseUntDal<GpInterface>，可手动更改。系统接口。
 */

public interface IGpInterfaceUntDal extends IBaseUntDal<GpInterface> {

 
	public int deleteByDomainId(String  domainId);
    
    public int deleteByDomainIdList(List<String> domainIdList);

	public List<GpInterface> getListByDomainId(String  domainId);
   

	public int deleteByUrl(String  url );

	public GpInterface getModelByUrl(String  url );

	public int isUniqueUrl(String  url);
  

   
}






