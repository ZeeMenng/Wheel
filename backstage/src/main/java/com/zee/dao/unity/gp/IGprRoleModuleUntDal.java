package com.zee.dao.unity.gp;

import java.util.List;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GprRoleModule;
import com.zee.ent.extend.gp.GpModule;
import com.zee.ent.extend.gp.GpRole;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/2/2 18:48:21
 * @description 扩展自实体类IBaseUntDal<GprRoleModule>，可手动更改。角色拥有的功能模块权限。
 */

public interface IGprRoleModuleUntDal extends IBaseUntDal<GprRoleModule> {

 
	public int deleteByModuleId(String  moduleId);
    
    public int deleteByModuleIdList(List<String> moduleIdList);

	public List<GprRoleModule> getListByModuleId(String  moduleId);
 
	public int deleteByRoleId(String  roleId);
    
    public int deleteByRoleIdList(List<String> roleIdList);

	public List<GprRoleModule> getListByRoleId(String  roleId);
        
	public int deleteByCompositeIdList(List<GprRoleModule> gprRoleModuleList);   
   

   
}






