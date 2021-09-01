package com.zee.bll.extend.unity.gp;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zee.bll.generate.unity.gp.GpDictionaryGenUntBll;
import com.zee.ent.custom.ResultModel;

/**
 * @author Zee
 * @createDate 2017/05/22 14:43:59
 * @updateDate 2017/12/15 23:41:48
 * @description 字典信息。 业务逻辑处理类，扩展自GpDictionaryGenUntBll ，可手动更改。
 */
@Service("gpDictionaryUntBll")
public class GpDictionaryUntBll extends GpDictionaryGenUntBll {

	/* (non-Javadoc)
	 * @see com.zee.bll.generate.unity.gp.GpDictionaryGenUntBll#getListByTypeId(java.lang.String, boolean)
	 * 
	 * 注意一个方法A调同一个类里的另一个有缓存注解的方法B，这样是不走缓存的。
	 */
	@Cacheable(cacheNames = { "dictionaryList" }, key = "#typeId")
	@Override
	public ResultModel getListByTypeId(String typeId, boolean isLog) {
		return super.getListByTypeId(typeId, isLog);
	}

	@CacheEvict(cacheNames = { "dictionaryList" }, key = "#typeId")
	@Override
	public ResultModel deleteByTypeId(String typeId, boolean isLog) {
		return super.deleteByTypeId(typeId, isLog);
	}

}
