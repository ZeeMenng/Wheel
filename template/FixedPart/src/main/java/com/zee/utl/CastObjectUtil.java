package com.zee.utl;

/**
 * @author Zee
 * @createDate 2021年2月2日 下午4:41:58
 * @updateDate 2021年2月2日 下午4:41:58
 * @description 消除强制转换Object对象时的警告
 */
public class CastObjectUtil {

	@SuppressWarnings("unchecked")
	public static <T> T cast(Object obj) {
		return (T) obj;
	}

	private CastObjectUtil() {
		throw new UnsupportedOperationException();
	}

}
