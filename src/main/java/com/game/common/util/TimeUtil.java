package com.game.common.util;

import java.util.Date;

public class TimeUtil {
	
	public static int getNow(){
		
		
		return (int)Math.floor(System.currentTimeMillis()/1000L);
	}
}
