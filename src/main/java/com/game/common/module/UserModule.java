package com.game.common.module;


import java.util.HashMap;
import com.game.common.util.TimeUtil;
import com.game.entity.UserEntity;
import com.game.service.UserService;


public class UserModule {
	
	private static UserService service;
	
	private String openid;
	
	private static HashMap<String, UserEntity> users = new HashMap<String, UserEntity>(1000);
	
	private UserEntity userEntity;
	
	private static int lastClearMapTime = 0;
	/**
	 * 过期时间3600秒
	 */
	private static final int usersExpireTime = 10;
	
	public UserModule(String openid,UserService userService){
		if(UserModule.service == null){
			UserModule.service = userService;
		}
		this.openid = openid;
		
	}
	
	/**
	 * 防止users保留太大没活跃的user信息，所以定时清理users
	 * 小于1000条自动跳过
	 */
	private static void clearUsersCache(){
		if(users.size()<1000){
			return;
		}
		int now = TimeUtil.getNow();
		int expireTime = lastClearMapTime + usersExpireTime;
		//过期
		if( now > expireTime ){
			users = new HashMap<String, UserEntity>(1000);
			lastClearMapTime = now;
		}
	}
	
	
	public UserEntity getUserEntity() {
		//定时清理User缓存
		clearUsersCache();
		
		UserEntity userEntity = users.get(openid);
		if(userEntity == null){
			UserEntity user = service.getUser(openid);
			users.put(openid, user);
			this.userEntity = user;
		}else{
			this.userEntity = userEntity;
		}

		return this.userEntity;
	}
	
	
	public void saveBeauty(float beauty){
		UserEntity entity = getUserEntity();
		if(entity.getBeauty() < beauty){
			UserEntity user = new UserEntity();
			user.setOpenid(openid);
			user.setBeauty(beauty);
			service.update(user);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
}
