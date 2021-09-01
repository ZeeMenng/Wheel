package com.zee.utl;

import java.util.HashMap;

import com.zee.set.symbolic.CustomSymbolic;

/**
 * @author Zee
 * @createDate 2017年4月18日 下午4:18:40
 * @updateDate 2017年4月18日 下午4:18:40
 * @description 这是个用于根据字典编码获取字典文本的类。
 *              关于字典编码和文档常量，目前三种存储方式，一种是直接用常量，一种是用枚举，另外一种就是使用当前类中的示例。
 */
public class DictionaryItem {

	private static DictionaryItem dictionaryItem;

	private HashMap<Byte, String> booleanD = new HashMap<Byte, String>();
	

	private DictionaryItem() {

		booleanD.put(CustomSymbolic.DCODE_BOOLEAN_T, "是");
		booleanD.put(CustomSymbolic.DCODE_BOOLEAN_F, "否");

	}

	public static DictionaryItem instance() {
		if (dictionaryItem == null) {
			dictionaryItem = new DictionaryItem();
		}
		return dictionaryItem;
	}

	public HashMap<Byte, String> getOperType() {
		return booleanD;
	}

	public void setOperType(HashMap<Byte, String> operType) {
		this.booleanD = operType;
	}

}
