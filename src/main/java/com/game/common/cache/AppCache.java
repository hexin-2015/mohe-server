package com.game.common.cache;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

import com.game.common.util.MemcachedUtil;

public class AppCache {
	//默认过期时间3600s
	private static Date expireTime = new Date(3600*1000);
	
	
	
	

}
