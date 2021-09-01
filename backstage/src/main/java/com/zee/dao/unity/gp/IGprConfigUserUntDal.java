package com.zee.dao.unity.gp;

import java.util.List;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GprConfigUser;
import com.zee.ent.extend.gp.GpUser;
import com.zee.ent.extend.gp.GpConfig;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/2/2 18:48:19
 * @description 扩展自实体类IBaseUntDal<GprConfigUser>，可手动更改。用户配置信息。
 */

public interface IGprConfigUserUntDal extends IBaseUntDal<GprConfigUser> {

 
	public int deleteByUserId(String  userId);
    
    public int deleteByUserIdList(List<String> userIdList);

	public List<GprConfigUser> getListByUserId(String  userId);
 
	public int deleteByConfigId(String  configId);
    
    public int deleteByConfigIdList(List<String> configIdList);

	public List<GprConfigUser> getListByConfigId(String  configId);
        
	public int deleteByCompositeIdList(List<GprConfigUser> gprConfigUserList);   
   

   
}






