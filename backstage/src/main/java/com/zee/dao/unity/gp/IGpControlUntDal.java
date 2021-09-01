package com.zee.dao.unity.gp;

import java.util.List;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GpControl;
import com.zee.ent.extend.gp.GpDomain;
import com.zee.ent.extend.gp.GpPage;
import com.zee.ent.extend.gp.GprRoleControl;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/2/2 18:48:24
 * @description 扩展自实体类IBaseUntDal<GpControl>，可手动更改。系统控件。
 */

public interface IGpControlUntDal extends IBaseUntDal<GpControl> {

 
	public int deleteByDomainId(String  domainId);
    
    public int deleteByDomainIdList(List<String> domainIdList);

	public List<GpControl> getListByDomainId(String  domainId);
 
	public int deleteByPageId(String  pageId);
    
    public int deleteByPageIdList(List<String> pageIdList);

	public List<GpControl> getListByPageId(String  pageId);
        
	public int deleteByCompositeIdList(List<GpControl> gpControlList);   
   

   
}






