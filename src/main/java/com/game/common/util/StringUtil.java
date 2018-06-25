package com.game.common.util;

import java.util.Date;

public class StringUtil {
	
	public static String toUpperFristChar(String string) {  
	    char[] charArray = string.toCharArray();  
	    charArray[0] -= 32;  
	    return String.valueOf(charArray);  
	}  
}
