package com.zee.dao.unity.gp;

import java.util.List;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GpDictionary;
import com.zee.ent.extend.gp.GpDictionaryType;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/2/2 18:48:24
 * @description 扩展自实体类IBaseUntDal<GpDictionary>，可手动更改。字典信息。
 */

public interface IGpDictionaryUntDal extends IBaseUntDal<GpDictionary> {

 
	public int deleteByTypeId(String  typeId);
    
    public int deleteByTypeIdList(List<String> typeIdList);

	public List<GpDictionary> getListByTypeId(String  typeId);
   

   
}






