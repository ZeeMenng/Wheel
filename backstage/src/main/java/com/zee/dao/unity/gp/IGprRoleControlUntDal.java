package com.zee.dao.unity.gp;

import java.util.List;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GprRoleControl;
import com.zee.ent.extend.gp.GpControl;
import com.zee.ent.extend.gp.GpRole;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/2/2 18:48:21
 * @description 扩展自实体类IBaseUntDal<GprRoleControl>，可手动更改。角色拥有的控件权限。
 */

public interface IGprRoleControlUntDal extends IBaseUntDal<GprRoleControl> {

 
	public int deleteByControlId(String  controlId);
    
    public int deleteByControlIdList(List<String> controlIdList);

	public List<GprRoleControl> getListByControlId(String  controlId);
 
	public int deleteByRoleId(String  roleId);
    
    public int deleteByRoleIdList(List<String> roleIdList);

	public List<GprRoleControl> getListByRoleId(String  roleId);
        
	public int deleteByCompositeIdList(List<GprRoleControl> gprRoleControlList);   
   

   
}






