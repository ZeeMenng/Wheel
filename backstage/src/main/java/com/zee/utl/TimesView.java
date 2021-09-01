package com.zee.utl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.zee.ent.custom.ResultModel;
import com.zee.set.symbolic.CustomSymbolic;

public class TimesView {
	
	/**
	 * 获取时间封装
	 * lxy
	 * @param pmap //视图名，年year，月month，日date，小时hour（默认为年）
	 * @return
	 */
	public static List<String> getTimesView(Map<String, String> pmap){
		
		String viewName = "year";//视图名，年year，月month，日date，小时hour（默认为年）
		if(StringUtils.isNotBlank(pmap.get("viewName"))){
			viewName = pmap.get("viewName");
		}
		Integer pastNum = 0;//往前推
		if(pmap.get("pastNum") != null){
			pastNum = Integer.valueOf(pmap.get("pastNum"));
		}
		Boolean hasCurrent = true;//是否包含现在（默认包含现在）
		if(pmap.get("hasCurrent") != null){
			hasCurrent = Boolean.valueOf(pmap.get("hasCurrent"));
		}
		Integer afterNum = 0;//往后推
		if(pmap.get("afterNum") != null){
			afterNum = Integer.valueOf(pmap.get("afterNum"));
		}
		Boolean isASC = true;//排序（默认正序）
		if(pmap.get("isASC") != null){
			isASC = Boolean.valueOf(pmap.get("isASC"));
		}
		
		//配置CalendarType
		int CalendarType = 1;//默认为年
		String CalendarFormat = "yyyy";
		switch (viewName) {
		case "year":
			CalendarType = Calendar.YEAR;
			CalendarFormat = "yyyy";
			break;
		case "month":
			CalendarType = Calendar.MONTH;
			CalendarFormat = "yyyy-MM";
			break;
		case "date":
			CalendarType = Calendar.DATE;
			CalendarFormat = "yyyy-MM-dd";
			break;
		case "hour":
			CalendarType = Calendar.HOUR_OF_DAY;
			CalendarFormat = "yyyy-MM-dd HH";
			break;
		}
		
		List<String> timesList = new ArrayList<String>();
		for (int i = pastNum; i > 0; i--) {//过去多少
			Calendar calendar = Calendar.getInstance();
			if(Calendar.MONTH == CalendarType){//如要需要月份，设置为每月第一天，避免一个月有31天
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
			}
			calendar.set(CalendarType, calendar.get(CalendarType) - i);
			SimpleDateFormat df = new SimpleDateFormat(CalendarFormat);
			timesList.add(df.format(calendar.getTime()));
		}
		if(hasCurrent){//是否包含现在
			Calendar calendar = Calendar.getInstance();
			calendar.set(CalendarType, calendar.get(CalendarType));
			SimpleDateFormat df = new SimpleDateFormat(CalendarFormat);
			timesList.add(df.format(calendar.getTime()));
		}
		for (int i = 1; i <= afterNum; i++) {//将来多少
			Calendar calendar = Calendar.getInstance();
			if(Calendar.MONTH == CalendarType){//如要需要月份，设置为每月第一天，避免一个月有31天
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
			}
			calendar.set(CalendarType, calendar.get(CalendarType) + i);
			SimpleDateFormat df = new SimpleDateFormat(CalendarFormat);
			timesList.add(df.format(calendar.getTime()));
		}
		
		if(!isASC){
			Collections.reverse(timesList);
		}
		
		return timesList;
	}
	
	/**
	 * 获取时间对应值
	 * @param pmap
	 * @return
	 */
	public static ResultModel getTimesData(Map<String, String> pmap, List<Map<String, Object>> modelList, Set<String> keySet) {

		List<String> timesList = getTimesView(pmap);

		List<String> dateTimeList = new ArrayList<String>();
		List<Map<String, Object>> removeMap = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map2 : modelList) {
			String dateTime = map2.get("dateTime").toString();
			if (timesList.contains(dateTime)) {
				dateTimeList.add(dateTime);
			} else {
				removeMap.add(map2);
			}
		}
		for (Map<String, Object> map2 : removeMap) {
			modelList.remove(map2);
		}
		timesList.removeAll(dateTimeList);
		
		BigDecimal num = new BigDecimal("0.000000");
		for (String times : timesList) {
			Map<String, Object> map2 = new HashMap<String, Object>();
			for (String key : keySet) {
				if ("dateTime".equals(key)) {
					map2.put(key, times);
				} else if ("id".equals(key) || key.startsWith("region")) {
					map2.put(key, null);
				} else {
					map2.put(key, num);
				}
			}
			modelList.add(map2);
		}
		
		//排序
		Collections.sort(modelList, new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				int ret = 0;
				if(o1.get("dateTime") != null && o2.get("dateTime") != null){
					ret = o1.get("dateTime").toString().compareTo(o2.get("dateTime").toString());
				}
				return ret;
			}
		});		
		
		ResultModel result = new ResultModel();
		result.setData(modelList);
		result.setTotalCount(modelList.size());
		result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return result;
	}
	
	public static void main(String[] args) {
		Map<String, String> timeMap = new HashMap<String, String>();
		timeMap.put("viewName", "hour");//视图名，年year，月month，日date，小时hour（默认为年）
		timeMap.put("hasCurrent", "true");
		timeMap.put("pastNum", "23");
		timeMap.put("afterNum", "1");
		List<String> timesList = TimesView.getTimesView(timeMap);
		for (String string : timesList) {
			System.out.println(string);
		}
		
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
//		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println(df.format(calendar.getTime()));
	}
	
}
