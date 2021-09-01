package com.zee.dao.unity.gp;

import java.util.List;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GprCatalogInterface;
import com.zee.ent.extend.gp.GpCatalogInterface;
import com.zee.ent.extend.gp.GpInterface;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/2/2 18:48:19
 * @description 扩展自实体类IBaseUntDal<GprCatalogInterface>，可手动更改。后台接口所属分类。
 */

public interface IGprCatalogInterfaceUntDal extends IBaseUntDal<GprCatalogInterface> {

 
	public int deleteByCatalogId(String  catalogId);
    
    public int deleteByCatalogIdList(List<String> catalogIdList);

	public List<GprCatalogInterface> getListByCatalogId(String  catalogId);
 
	public int deleteByInterfaceId(String  interfaceId);
    
    public int deleteByInterfaceIdList(List<String> interfaceIdList);

	public List<GprCatalogInterface> getListByInterfaceId(String  interfaceId);
        
	public int deleteByCompositeIdList(List<GprCatalogInterface> gprCatalogInterfaceList);   
   

   
}






