package com.game.service;

import java.util.List;

import org.apache.catalina.User;

import com.game.entity.LogGold;
import com.game.entity.UserEntity;
import com.game.entity.UserLikeListEntity;

public interface GoldService {
	
	int addGold(String openid,int gold);
	
	int insertLog(LogGold log);
	
}
