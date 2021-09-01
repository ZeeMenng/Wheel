package com.zee.dao.unity.gp;

import java.util.List;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GpOperLogLogin;
import com.zee.ent.extend.gp.GpLoginLog;
import com.zee.ent.extend.gp.GpOperLog;
import com.zee.ent.extend.gp.GpToken;
import com.zee.ent.extend.gp.GpUser;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/2/2 18:48:27
 * @description 扩展自实体类IBaseUntDal<GpOperLogLogin>，可手动更改。登录用户操作日志。
 */

public interface IGpOperLogLoginUntDal extends IBaseUntDal<GpOperLogLogin> {

 
	public int deleteByLoginLogId(String  loginLogId);
    
    public int deleteByLoginLogIdList(List<String> loginLogIdList);

	public List<GpOperLogLogin> getListByLoginLogId(String  loginLogId);
 
	public int deleteByOperLogId(String  operLogId);
    
    public int deleteByOperLogIdList(List<String> operLogIdList);

	public List<GpOperLogLogin> getListByOperLogId(String  operLogId);
 
	public int deleteByTokenId(String  tokenId);
    
    public int deleteByTokenIdList(List<String> tokenIdList);

	public List<GpOperLogLogin> getListByTokenId(String  tokenId);
 
	public int deleteByUserId(String  userId);
    
    public int deleteByUserIdList(List<String> userIdList);

	public List<GpOperLogLogin> getListByUserId(String  userId);
        
	public int deleteByCompositeIdList(List<GpOperLogLogin> gpOperLogLoginList);   
   

   
}






