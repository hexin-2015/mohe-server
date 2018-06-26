package com.game.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.dao.UserDao;
import com.game.entity.UserAdviceEntity;
import com.game.entity.UserEntity;
import com.game.entity.UserLikeListEntity;
import com.game.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired UserDao dao;

	@Override
	public UserEntity getUser(String openid) {
		
		return dao.getUser(openid);
	}

	@Override
	public void insert(UserEntity user) {
		
		dao.insert(user);
	}

	@Override
	public void update(UserEntity user) {
		dao.update(user);
		
	}

	@Override
	public List<UserEntity> getUser(List<String> openids) {
		
		return dao.getUsers(openids);
	}

	@Override
	public UserLikeListEntity getUserLikeList(UserLikeListEntity entity) {
		
		return dao.getUserLikeList(entity);
	}

	@Override
	public boolean insertUserLikeList(UserLikeListEntity entity) {
		int res = dao.insertUserLikeList(entity);
		return res == 0 ? false : true;
	}

	@Override
	public boolean updateUserLikeList(UserLikeListEntity entity) {
		int res = dao.updateUserLikeList(entity);
		return res == 0 ? false : true;
	}

	@Override
	public boolean insertUserAdvice(UserAdviceEntity entity) {
		
		int res = dao.insertUserAdvice(entity);
		return res == 0 ? false : true;
	}

}
