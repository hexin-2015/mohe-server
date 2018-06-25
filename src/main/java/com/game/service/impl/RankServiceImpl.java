package com.game.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.dao.RankDao;
import com.game.dao.UserDao;
import com.game.entity.RankData;
import com.game.entity.UserEntity;
import com.game.service.RankService;
import com.game.service.UserService;

@Service
public class RankServiceImpl implements RankService{
	
	@Autowired RankDao dao;

	@Override
	public List<RankData> getMaxBeauty(RankData rankData) {
		
		return dao.getMaxBeauty(rankData);
	}

	@Override
	public List<RankData> getMinBeauty(RankData rankData) {
		
		return dao.getMinBeauty(rankData);
	}

	@Override
	public List<RankData> getMaxGold(RankData rankData) {

		return dao.getMaxGold(rankData);
	}




}
