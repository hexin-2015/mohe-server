package com.game.common.util;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.ibatis.javassist.expr.NewArray;

import com.mysql.fabric.xmlrpc.base.Array;

public class NumberUtil {
	
	/**
	 * 截取小数点后数据
	 * @param number 待转换数字
	 * @param decimalNum 小数点位数
	 */
	public static <T extends Number> String getDecimalNumber (T number,int decimalNum) {
		if(decimalNum <= 0){
			Integer intValue = number.intValue();
			return intValue.toString();
		}
		char[] formatString = new char[decimalNum+1];
		Arrays.fill(formatString, '0');
		formatString[0] = '.';
		DecimalFormat decimalFormat=new DecimalFormat(new String(formatString));
		return  decimalFormat.format(number);
	} 
	
	
	public static int random(int min,int max){
		int nextInt = ThreadLocalRandom.current().nextInt(min, max);
		return nextInt;
	}
	
	public static int random(int max){
		int nextInt = ThreadLocalRandom.current().nextInt(max);
		return nextInt;
	}
}
