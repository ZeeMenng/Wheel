package com.zee.utl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zee.set.enumer.DiAreaUnitEnum;
import com.zee.set.enumer.DiPerPriceUnitEnum;
import com.zee.set.enumer.DiWeightUnit;
import com.zee.set.enumer.DiYieldUnitEnum;

/**
 * @author lxy
 * @createDate 2018年6月25日 下午4:35:00
 * @updateDate 2018年6月25日 下午4:35:00
 * @description 单位转换工具
 */
public class UnitUtil {
	
	private static Logger log = LoggerFactory.getLogger(UnitUtil.class);
	
	public static BigDecimal switchUnit(BigDecimal value, Byte code, String type){
		BigDecimal switchValue = new BigDecimal("0");
		switch (type) {
		case "DI_PER_PRICE_UNIT":
		    switchValue = diPerPriceUnit(value, code);
		    break;
		case "DI_WEIGHT_UNIT":
		    switchValue = diWeightUnit(value, code);
		    break;
		case "DI_AREA_UNIT":
		    switchValue = DiAreaUnit(value, code);
		    break;
		case "DI_YIELD_UNIT":
		    switchValue = DiYieldUnit(value, code);
		    break;
		}
		
		return switchValue;
	}
	
	private static BigDecimal diWeightUnit(BigDecimal value, Byte code) {
		
		if(DiWeightUnit.DUN.getCode() == code){//吨 to 千克
			return value.multiply(new BigDecimal("1000"));
		}else if(DiWeightUnit.WANDUN.getCode() == code){//万吨 to 千克
			return value.multiply(new BigDecimal("10000000"));
		}else{
			return value;
		}
	}

	private static BigDecimal diPerPriceUnit(BigDecimal value, Byte code){
		
		if(DiPerPriceUnitEnum.WANYUAN.getCode() == code){//万元/公斤 to 元/公斤
			return value.multiply(new BigDecimal("10000"));
		}else if(DiPerPriceUnitEnum.YIYUAN.getCode() == code){//亿元/公斤 to 元/公斤
			return value.multiply(new BigDecimal("100000000"));
		}else if(DiPerPriceUnitEnum.MEIYUAN.getCode() == code){//美元/公斤 to 元/公斤（汇率按6.52）
			return value.multiply(new BigDecimal("6.52"));
		}else if(DiPerPriceUnitEnum.WANMEIYUAN.getCode() == code){//万美元/公斤 to 元/公斤（汇率按6.52）
			return value.multiply(new BigDecimal("65200"));
		}else if(DiPerPriceUnitEnum.BAIWANMEIYUAN.getCode() == code){//百万美元/公斤 to 元/公斤（汇率按6.52）
			return value.multiply(new BigDecimal("6520000"));
		}else if(DiPerPriceUnitEnum.YUANJIN.getCode() == code){
			return value.multiply(new BigDecimal("2"));//元/斤 to 元/公斤
		}
		else{
			return value;
		}
	}
	
	private static BigDecimal DiAreaUnit(BigDecimal value, Byte code) {
		
		if(DiAreaUnitEnum.GONGQING.getCode() == code){//公顷 to 平方米
			return value.multiply(new BigDecimal("10000"));
		}else if(DiAreaUnitEnum.MU.getCode()==code){//亩 to 平方米
			return value.multiply(new BigDecimal("666.666"));
		}else if(DiAreaUnitEnum.WANMU.getCode() == code){//万亩 to 平方米
			return value.multiply(new BigDecimal("6666666.666"));
		}else{
			return value;
		}
		
	}
	
	private static BigDecimal DiYieldUnit(BigDecimal value, Byte code) {
			
		if(DiYieldUnitEnum.KE.getCode() == code){//克/亩 to 千克/亩
			return value.multiply(new BigDecimal("0.001"));
		}else{
			return value;
		}
	}
	
	
}
