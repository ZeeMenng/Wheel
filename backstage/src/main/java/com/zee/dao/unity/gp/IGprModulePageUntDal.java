package com.zee.dao.unity.gp;

import java.util.List;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GprModulePage;
import com.zee.ent.extend.gp.GpModule;
import com.zee.ent.extend.gp.GpPage;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/2/2 18:48:20
 * @description 扩展自实体类IBaseUntDal<GprModulePage>，可手动更改。功能模块所包含的页面。
 */

public interface IGprModulePageUntDal extends IBaseUntDal<GprModulePage> {

 
	public int deleteByModuleId(String  moduleId);
    
    public int deleteByModuleIdList(List<String> moduleIdList);

	public List<GprModulePage> getListByModuleId(String  moduleId);
 
	public int deleteByPageId(String  pageId);
    
    public int deleteByPageIdList(List<String> pageIdList);

	public List<GprModulePage> getListByPageId(String  pageId);
        
	public int deleteByCompositeIdList(List<GprModulePage> gprModulePageList);   
   

   
}






