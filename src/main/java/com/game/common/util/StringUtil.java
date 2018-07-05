package com.game.common.util;

import java.awt.datatransfer.StringSelection;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.druid.sql.visitor.functions.Char;

public class StringUtil {
	
	public static String toUpperFristChar(String string) {  
	    char[] charArray = string.toCharArray();  
	    charArray[0] -= 32;  
	    return String.valueOf(charArray);  
	} 
	
	
	public static boolean haveEmojiString(String string){
		
		return false;
	}
	
	
	public static String trimEmojiString(String string){
		
		String trim = string.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "");
		
		return trim;
	}
	
	public static String getTimeFormat(String date){
		
		if(date.contains(".")){
			int indexOf = date.indexOf(".");
			String reDate = date.substring(0,indexOf);
			return reDate;
		}

		return date;
	}
	
	
	
	
}
