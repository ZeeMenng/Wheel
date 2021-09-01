package com.zee.dao.split.gp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zee.dao.split.base.IBaseSplDal;
import com.zee.ent.extend.gp.GprResource;



/**
 * @author Zee
 * @createDate 2017/05/22 14:01:41
 * @updateDate 2020/7/4 16:32:20
 * @description 扩展自实体类IBaseUntDal<GprResource>，可手动更改。附件关联表。只要存有附件字段的表，都会通过此表于gp_resource表关联。
 */

public interface IGprResourceSplDal extends IBaseSplDal {

	public int add(GprResource gprResource);

	public int addList(ArrayList<GprResource> gprResourceList);

	public int delete(String id);

	public int deleteByIdList(ArrayList<String> idList);

	public int deleteByBusinessId(String businessId);
	
	public int deleteByBusinessIdList(ArrayList<String> businessIdList);
	
	public int update(GprResource gprResource);

	public GprResource getModel(String id);

	public List<GprResource> getList(Map<String, Object> map);
	
	public List<Map<String, Object>> getListBySQL(String sql);

}





