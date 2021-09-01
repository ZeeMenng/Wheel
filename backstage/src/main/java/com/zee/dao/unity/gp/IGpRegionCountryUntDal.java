package com.zee.dao.unity.gp;

import java.util.List;
import com.zee.dao.unity.base.IBaseUntDal;
import com.zee.ent.extend.gp.GpRegionCountry;




/**
 * @author Zee
 * @createDate 2017/05/22 14:01:29
 * @updateDate 2021/2/2 18:48:27
 * @description 扩展自实体类IBaseUntDal<GpRegionCountry>，可手动更改。地区信息。
 */

public interface IGpRegionCountryUntDal extends IBaseUntDal<GpRegionCountry> {

   

	public int deleteByIsoCode(String  isoCode );

	public GpRegionCountry getModelByIsoCode(String  isoCode );

	public int isUniqueIsoCode(String  isoCode);
  

   
}






