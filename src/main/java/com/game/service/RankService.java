package com.game.service;

import java.util.List;

import com.game.entity.RankData;
import com.game.entity.UserEntity;

public interface RankService {
	
	public List<RankData> getMaxBeauty(RankData rankData);
	
	public List<RankData> getMinBeauty(RankData rankData);
	
	public List<RankData> getMaxGold(RankData rankData);
	
}
