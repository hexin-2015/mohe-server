package com.game.dao;

import java.util.List;

import com.game.entity.RankData;

public interface RankDao {
	
	
	List<RankData> getMaxBeauty(RankData rankData);
	
	List<RankData> getMinBeauty(RankData rankData);

	List<RankData> getMaxGold(RankData rankData);
	

}
