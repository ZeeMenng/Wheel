package com.zee.utl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;


/**
 * 计算工具类
 * @author lxy
 * @createDate 2018年5月23日 下午9:45:23
 * @updateDate 2018年5月23日 下午9:45:23
 * @description 
 */
public class MathUtil {
	
	/**
	 * 求a/b 的百分比
	 * @param a
	 * @param b
	 * @param format   如："0.00%"保留两位
	 * @return
	 */
	public static String getPercent(BigDecimal a, BigDecimal b, String format) {
		BigDecimal divide = a.divide(b, 10, BigDecimal.ROUND_HALF_UP);
		DecimalFormat df = new DecimalFormat(format);
		return df.format(divide);
	}
	
	/**
	 * 格式化BigDecimal
	 * 保留两位小数
	 * @param value
	 * @return
	 */
	public static BigDecimal decimalFormat(BigDecimal value) {
		return decimalFormat(value, "0.00");
	}
	
	/**
	 * 格式化BigDecimal
	 * 自定义格式
	 * @param value
	 * @param format
	 * @return
	 */
	public static BigDecimal decimalFormat(BigDecimal value, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return new BigDecimal(df.format(value));
	}
	
	/**
	 * 获取list中的最大值数值
	 * @param list
	 * @return
	 */
	public static Integer getMax(List<Integer> list) {
		int max = 0;
		for (Integer num : list) {
			if(num > max){
				max = num;
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		BigDecimal a = new BigDecimal("0.23232443");   
		BigDecimal b = new BigDecimal("0.333444444");
		System.out.println(getPercent(a,b, "0.00000%"));
		System.out.println(decimalFormat(a.multiply(b)));
	}
	
	
	
	/**
	 * 将两个long值的商转换成百分比形式，并设置保留小数点后几位
	 * @param divisor	除数
	 * @param dividend	被除数
	 * @return
	 */
	public static String getPercentByLong(long divisor,long dividend,int digit) {
		NumberFormat numberFormat = NumberFormat.getInstance();     
		//设置精确到小数点后digit位     
		numberFormat.setMaximumFractionDigits(digit);     
		String result = numberFormat.format((float)divisor/(float)dividend*100);  
		return result+"%";
	}
	
}
