package com.zee.dao.unity.gp;

import java.util.List;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GpOperLog;
import com.zee.ent.extend.gp.GpDomain;
import com.zee.ent.extend.gp.GpOperLogLogin;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/2/2 18:48:26
 * @description 扩展自实体类IBaseUntDal<GpOperLog>，可手动更改。操作日志。
 */

public interface IGpOperLogUntDal extends IBaseUntDal<GpOperLog> {

 
	public int deleteByDomainId(String  domainId);
    
    public int deleteByDomainIdList(List<String> domainIdList);

	public List<GpOperLog> getListByDomainId(String  domainId);
   

   
}






