package com.game.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.dao.GoldDao;
import com.game.dao.UserDao;
import com.game.entity.LogGold;
import com.game.entity.UserEntity;
import com.game.entity.UserLikeListEntity;
import com.game.service.GoldService;
import com.game.service.UserService;

@Service
public class GoldServiceImpl implements GoldService{
	
	@Autowired GoldDao dao;

	@Override
	public int addGold(String openid, int gold) {
		return dao.addGold(openid,gold);
	}

	@Override
	public int insertLog(LogGold log) {
		return dao.insertLog(log);
	}

	

}
