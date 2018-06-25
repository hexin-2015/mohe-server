package com.game.common;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

import com.game.common.util.MemcachedUtil;

public class WeiXinSession {
	//默认过期时间600s
	private static Date expireTime = new Date(3600*48*1000);
		
	private String sessionID;
	
	private HashMap<String, Object> data = new HashMap<String, Object>();
	
	public WeiXinSession(String sessionID) {
		this.sessionID = sessionID;
	}
	
	
	public static String createSessionID(String sessionKey,String openid){
		//sessionKey&&openid&&时间戳
		
		StringBuilder sb = new StringBuilder(128) ;
		sb.append(sessionKey);
		sb.append(openid);
		long currentTimeMillis = System.currentTimeMillis();
		int sort =  0xFFF & (int)currentTimeMillis;
		sb.append(sort);
		//sha1
		String md5Hex = DigestUtils.md5Hex(sb.toString());
		return md5Hex;	
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" } )
	public void set(String key,Serializable value){
		
		Object res = MemcachedUtil.get(sessionID);
		if(res != null ){
			data = (HashMap<String, Object>)res;	
		}
		data.put(key, value);
		MemcachedUtil.set(sessionID, data,expireTime);
	}
	
	@SuppressWarnings("unchecked")
	public Serializable get(String key){
		Object res = MemcachedUtil.get(sessionID);
		if(res == null){
			return null;
		}
		data = (HashMap<String, Object>)res;
		return (Serializable)data.get(key);
	}
	
	public boolean haveSession(){
		Object res = MemcachedUtil.get(sessionID);
		if(res == null){
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> getAll(){
		Object res = MemcachedUtil.get(sessionID);
		if(res == null){
			return null;
		}
		return (HashMap<String, Object>)res;
	}
	
	private static String openid(String sessionid){
		
		if(sessionid == null || sessionid.isEmpty()){
			return null;
		}
		WeiXinSession weiXinSession = new WeiXinSession(sessionid);
		Serializable data = weiXinSession.get("openid");
		return (String)data;
	}
	
	public static String getOpenid(HttpServletRequest request){
		String sessionID = request.getParameter("sessionID");
		return openid(sessionID);
		
	}
	
	public String getOpenid(){
		return openid(sessionID);
	}
		 
	
	
	

}
