package com.zee.dao.unity.gp;

import java.util.List;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GprUserRole;
import com.zee.ent.extend.gp.GpRole;
import com.zee.ent.extend.gp.GpUser;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/2/2 18:48:23
 * @description 扩展自实体类IBaseUntDal<GprUserRole>，可手动更改。用户拥有的角色。
 */

public interface IGprUserRoleUntDal extends IBaseUntDal<GprUserRole> {

 
	public int deleteByRoleId(String  roleId);
    
    public int deleteByRoleIdList(List<String> roleIdList);

	public List<GprUserRole> getListByRoleId(String  roleId);
 
	public int deleteByUserId(String  userId);
    
    public int deleteByUserIdList(List<String> userIdList);

	public List<GprUserRole> getListByUserId(String  userId);
        
	public int deleteByCompositeIdList(List<GprUserRole> gprUserRoleList);   
   

   
}






