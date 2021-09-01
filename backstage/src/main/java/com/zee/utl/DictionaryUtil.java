package com.zee.utl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.zee.bll.extend.unity.gp.GpDictionaryUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.set.symbolic.CustomSymbolic;

/**
 * 字典值转换
 * @author lxy
 * @createDate 2018年3月7日 下午3:11:40
 * @updateDate 2018年3月7日 下午3:11:40
 * @description 
 */
@Component
public class DictionaryUtil {
	
	private static Map<String, String> dictMap;
	
	@Autowired
	@Qualifier("gpDictionaryUntBll")
	protected GpDictionaryUntBll gpDictionaryUntBll;
	
	public <T>T dictTransform(T t){
		initDictMap();
		try {
			Class<? extends Object> clazz = t.getClass();
			List<Field> list = new ArrayList<Field>();
			Field[] fields = clazz.getDeclaredFields();
			list.addAll(Arrays.asList(fields));
			Field[] superFields = clazz.getSuperclass().getDeclaredFields();
			list.addAll(Arrays.asList(superFields));
			String value;
			Field fieldValue;
			for (Field field : list) {
				field.setAccessible(true);
				String fieldStr = field.get(t)!=null?field.get(t).toString():"";
				switch(field.getName()){
				case "genderCode":
					value = dictMap.get(CustomSymbolic.DI_GENDER + "_" + fieldStr);
					fieldValue = clazz.getDeclaredField("genderValue");
					fieldValue.setAccessible(true);
					fieldValue.set(t, value);
					continue;
				case "isMarriageCode":
					value = dictMap.get(CustomSymbolic.DI_BOOLEAN + "_" + fieldStr);
					fieldValue = clazz.getDeclaredField("isMarriageValue");
					fieldValue.setAccessible(true);
					fieldValue.set(t, value);
					continue;
				case "isAdminCode":
					value = dictMap.get(CustomSymbolic.DI_BOOLEAN + "_" + fieldStr);
					fieldValue = clazz.getDeclaredField("isAdminValue");
					fieldValue.setAccessible(true);
					fieldValue.set(t, value);
					continue;
				case "isDisabledCode":
					value = dictMap.get(CustomSymbolic.DI_BOOLEAN + "_" + fieldStr);
					fieldValue = clazz.getDeclaredField("isDisabledValue");
					fieldValue.setAccessible(true);
					fieldValue.set(t, value);
					continue;
				case "isPublicCode":
					value = dictMap.get(CustomSymbolic.DI_BOOLEAN + "_" + fieldStr);
					fieldValue = clazz.getDeclaredField("isPublicValue");
					fieldValue.setAccessible(true);
					fieldValue.set(t, value);
					continue;
				case "isDisplayCode":
					value = dictMap.get(CustomSymbolic.DI_BOOLEAN + "_" + fieldStr);
					fieldValue = clazz.getDeclaredField("isDisplayValue");
					fieldValue.setAccessible(true);
					fieldValue.set(t, value);
					continue;
				case "isSuccessCode":
					value = dictMap.get(CustomSymbolic.DI_BOOLEAN + "_" + fieldStr);
					fieldValue = clazz.getDeclaredField("isSuccessValue");
					fieldValue.setAccessible(true);
					fieldValue.set(t, value);
					continue;
				}
			}
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private void initDictMap(){
		if(dictMap == null){
			dictMap = new HashMap<String, String>();
			Map<String, Object> map = new HashMap<String, Object>();
			StringBuffer selectBuffer = new StringBuffer();
			selectBuffer.append("			SELECT                                                    ");
			selectBuffer.append("				CONCAT(B.id, '_', A.CODE) dictKey,                    ");
			selectBuffer.append("				A.text dictValue                                      ");
			selectBuffer.append("			FROM                                                      ");
			selectBuffer.append("				gp_dictionary A                                       ");
			selectBuffer.append("			INNER JOIN gp_dictionary_type B ON A.type_id = B.id       ");
			map.put("Sql", selectBuffer.toString());
			ResultModel resultModel = gpDictionaryUntBll.getListBySQL(map);
			List<Map<String, Object>> modelList = (List<Map<String, Object>>) resultModel.getData();
			for (Map<String, Object> map2 : modelList) {
				dictMap.put(map2.get("dictKey").toString(), map2.get("dictValue").toString());
			}
		}
	}
	
}
