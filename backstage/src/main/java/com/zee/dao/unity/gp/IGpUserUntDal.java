package com.zee.dao.unity.gp;

import java.util.List;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GpUser;
import com.zee.ent.extend.gp.GpLoginLog;
import com.zee.ent.extend.gp.GpMessage;
import com.zee.ent.extend.gp.GpOperLogLogin;
import com.zee.ent.extend.gp.GpToken;
import com.zee.ent.extend.gp.GprDomainUser;
import com.zee.ent.extend.gp.GprMessageUser;
import com.zee.ent.extend.gp.GprUserOrganization;
import com.zee.ent.extend.gp.GprUserRole;
import com.zee.ent.extend.gp.GprUserStation;
import com.zee.ent.extend.gp.GprConfigUser;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/2/2 18:48:29
 * @description 扩展自实体类IBaseUntDal<GpUser>，可手动更改。系统用户。
 */

public interface IGpUserUntDal extends IBaseUntDal<GpUser> {

   

	public int deleteByEmail(String  email );

	public GpUser getModelByEmail(String  email );

	public int isUniqueEmail(String  email);
  

	public int deleteByPhone(String  phone );

	public GpUser getModelByPhone(String  phone );

	public int isUniquePhone(String  phone);
  

	public int deleteByQq(String  qq );

	public GpUser getModelByQq(String  qq );

	public int isUniqueQq(String  qq);
  

	public int deleteByUserName(String  userName );

	public GpUser getModelByUserName(String  userName );

	public int isUniqueUserName(String  userName);
  

   
}






