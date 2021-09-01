package com.zee.dao.unity.gp;

import java.util.List;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GprUserOrganization;
import com.zee.ent.extend.gp.GpOrganization;
import com.zee.ent.extend.gp.GpUser;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/2/2 18:48:23
 * @description 扩展自实体类IBaseUntDal<GprUserOrganization>，可手动更改。用户所属组织机构。
 */

public interface IGprUserOrganizationUntDal extends IBaseUntDal<GprUserOrganization> {

 
	public int deleteByOrganizationId(String  organizationId);
    
    public int deleteByOrganizationIdList(List<String> organizationIdList);

	public List<GprUserOrganization> getListByOrganizationId(String  organizationId);
 
	public int deleteByUserId(String  userId);
    
    public int deleteByUserIdList(List<String> userIdList);

	public List<GprUserOrganization> getListByUserId(String  userId);
        
	public int deleteByCompositeIdList(List<GprUserOrganization> gprUserOrganizationList);   
   

   
}






