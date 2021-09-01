package com.zee.utl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.zee.set.symbolic.CustomSymbolic;

import net.sf.json.JSONObject;

/**
 * @author ZeeMenng 2016/10/8 根据传入的Map获取PageHelper插件所需要要的pageNum和pageSize参数
 *
 */
public class PageHelperUtil {

	public static int getPageNum(Map<String, Object> map) {
		int pageIndex = CustomSymbolic.SQLQUERY_PAGEINDEX_DEFAULTVALUE;
		if (map.get("Page") != null) {
			JSONObject pageObject = (JSONObject) map.get("Page");
			if (pageObject.containsKey("pageIndex") && StringUtils.isNotBlank(pageObject.getString("pageIndex")))
				pageIndex = pageObject.getInt("pageIndex");
		}
		return pageIndex;
	}

	public static int getPageSize(Map<String, Object> map) {
		int pageSize = CustomSymbolic.SQLQUERY_KEYWORDS_PAGESIZE_MAX;
		if (map.get("Page") != null) {
			JSONObject pageObject = (JSONObject) map.get("Page");
			if (pageObject.containsKey("pageSize") && StringUtils.isNotBlank(pageObject.getString("pageSize")))
				pageSize = pageObject.getInt("pageSize");
		}
		return pageSize;
	}

}
