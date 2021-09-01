package com.zee.dao.unity.gp;

import java.util.List;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GprResource;
import com.zee.ent.extend.gp.GpResource;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/2/2 18:48:20
 * @description 扩展自实体类IBaseUntDal<GprResource>，可手动更改。附件关联表。只要存有附件字段的表，都会通过此表于gp_resource表关联。
 */

public interface IGprResourceUntDal extends IBaseUntDal<GprResource> {

 
	public int deleteByResourceId(String  resourceId);
    
    public int deleteByResourceIdList(List<String> resourceIdList);

	public List<GprResource> getListByResourceId(String  resourceId);
   

   
}






