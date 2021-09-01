package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GprConfigDomain;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2021/1/20 10:44:14
 * @description 扩展自实体类IBaseUntDal<GprConfigDomain>，可手动更改。应用领域配置信息。
 */

public interface IGprConfigDomainSplDal extends IBaseSplDal {

	public int add(GprConfigDomain gprConfigDomain);

	public int addList(ArrayList<GprConfigDomain> gprConfigDomainList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int update(GprConfigDomain gprConfigDomain);

	public GprConfigDomain getModel(String id);

	public List<GprConfigDomain> getList(Map<String, Object> map);

	public List<Map<String, Object>> getListBySQL(String sql);

}





