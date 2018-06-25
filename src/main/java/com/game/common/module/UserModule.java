package com.game.common.module;

import java.sql.Savepoint;
import java.util.HashMap;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.game.entity.UserEntity;
import com.game.service.UserService;


public class UserModule {
	
	private static UserService service;
	
	private String openid;
	
	private static HashMap<String, UserEntity> users = new HashMap<String, UserEntity>(1000);
	
	private UserEntity userEntity;
	
	public UserModule(String openid,UserService userService){
		if(UserModule.service == null){
			UserModule.service = userService;
		}
		this.openid = openid;
		
	}
	
	public UserEntity getUserEntity() {
		
		if(userEntity == null){
			UserEntity userEntity = users.get(openid);
			if(userEntity == null){
				UserEntity user = service.getUser(openid);
				users.put(openid, user);
				this.userEntity = user;
			}else{
				this.userEntity = userEntity;
			}	
		}
		return userEntity;
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
