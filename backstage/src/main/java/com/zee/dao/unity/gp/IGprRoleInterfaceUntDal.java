package com.zee.dao.unity.gp;

import java.util.List;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GprRoleInterface;
import com.zee.ent.extend.gp.GpInterface;
import com.zee.ent.extend.gp.GpRole;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/2/2 18:48:21
 * @description 扩展自实体类IBaseUntDal<GprRoleInterface>，可手动更改。角色拥有的接口权限。
 */

public interface IGprRoleInterfaceUntDal extends IBaseUntDal<GprRoleInterface> {

 
	public int deleteByInterfaceId(String  interfaceId);
    
    public int deleteByInterfaceIdList(List<String> interfaceIdList);

	public List<GprRoleInterface> getListByInterfaceId(String  interfaceId);
 
	public int deleteByRoleId(String  roleId);
    
    public int deleteByRoleIdList(List<String> roleIdList);

	public List<GprRoleInterface> getListByRoleId(String  roleId);
        
	public int deleteByCompositeIdList(List<GprRoleInterface> gprRoleInterfaceList);   
   

   
}






