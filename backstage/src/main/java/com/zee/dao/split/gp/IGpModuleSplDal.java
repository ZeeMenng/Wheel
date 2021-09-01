package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GpModule;

/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2018/1/16 14:27:23
 * @description 扩展自实体类IBaseUntDal<GpModule>，可手动更改。功能模块。
 */

public interface IGpModuleSplDal extends IBaseSplDal {

	public int add(GpModule gpModule);

	public int addList(ArrayList<GpModule> gpModuleList);

	public int delete(String id);

	public int deleteByDomainId(String domainId);

	public int deleteByIdList(ArrayList<String> idList);

	public int deleteByDomainIdList(ArrayList<String> domainIdList);

	public int update(GpModule gpModule);

	public int deleteInvalidDomainModules(@Param("domainId") String domainId, @Param("moduleList") List<GpModule> validDomainModuleList);

	public int updateDomainModules(ArrayList<GpModule> gpModuleList);

	public GpModule getModel(String id);

	public List<GpModule> getList(Map<String, Object> map);

	public List<GpModule> getListByDomainId(String domainId);
	
	public List<GpModule> getListByRoleId(String roleId);

	public List<Map<String, Object>> getListBySQL(String sql);

}
