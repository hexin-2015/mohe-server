package com.game.dao;

import java.util.List;

import com.game.entity.UserEntity;
import com.game.entity.UserLikeListEntity;


public interface UserDao {

	public UserEntity getUser(String openid);
	
	public List<UserEntity> getUsers(List<String> openids);
	
	public void insert(UserEntity user);
	
	public void update(UserEntity user);
	
	public UserLikeListEntity getUserLikeList(UserLikeListEntity entity);
	
	public int insertUserLikeList(UserLikeListEntity entity);
	
	public int updateUserLikeList(UserLikeListEntity entity);
	
}
