package com.game.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.game.common.util.HttpRequestUtil;
import com.game.entity.UserEntity;


public class WeiXinHttpRequest {
	
	private static final String weixinHost = "https://api.weixin.qq.com/sns/jscode2session"; 
	private static final String appId = "wxb05e4d1a962febd9"; 
	private static final String secret = "068b01c00b6f829f2081ac67438cc78c"; 
	
	final static Logger log = LoggerFactory.getLogger(WeiXinHttpRequest.class);
	public static UserEntity getOpenId(String code){
		
		StringBuilder urlPath = new StringBuilder(weixinHost); // 微信提供的API，这里最好也放在配置文件
		urlPath.append(String.format("?appid=%s", appId));
		urlPath.append(String.format("&secret=%s", secret));
		urlPath.append(String.format("&js_code=%s", code));
		urlPath.append(String.format("&grant_type=%s", "authorization_code")); // 固定值
		String res = HttpRequestUtil.request(urlPath.toString(), HttpRequestUtil.REQUEST_MENTHOD_GET, null);
		JSONObject parse = JSONObject.parseObject(res);
		String error = parse.getString("errcode");
		if(error!=null){
			log.warn("requst:"+urlPath+",result:"+res);
			return null;
		}
		String session_key = parse.getString("session_key");
		String openid = parse.getString("openid");
		UserEntity userEntity = new UserEntity();
		userEntity.setSessionKey(session_key);
		userEntity.setOpenid(openid);
		return userEntity;
	}
}
