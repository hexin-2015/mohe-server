package com.game.service;

import java.util.List;

import com.game.entity.UserEntity;
import com.game.entity.UserLikeListEntity;

public interface UserService {
	
	public UserEntity getUser(String openid);
	
	public List<UserEntity> getUser(List<String> openids);
	
	public void insert(UserEntity user);
	
	public void update(UserEntity user);
	
	public UserLikeListEntity getUserLikeList(UserLikeListEntity entity);
	
	public boolean insertUserLikeList(UserLikeListEntity entity);
	
	public boolean updateUserLikeList(UserLikeListEntity entity);
}
