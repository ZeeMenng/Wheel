package com.zee.ent.extend.gp;

import com.zee.ent.generate.gp.GprCatalogInterfaceGenEnt;

import io.swagger.annotations.ApiModel;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2020/10/21 21:21:11
 * @description 扩展自实体类GprCatalogInterfaceGenEnt，可手动更改。后台接口所属分类。
 */

@ApiModel(value = "GprCatalogInterface", description = "后台接口所属分类。")
public class GprCatalogInterface extends GprCatalogInterfaceGenEnt {
	private Byte categoryCode;

	public Byte getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(Byte categoryCode) {
		this.categoryCode = categoryCode;
	}

}
