package com.game.dao;

import org.apache.ibatis.annotations.Param;

import com.game.entity.LogGold;

public interface GoldDao {

	public int addGold(@Param("openid") String openid, @Param("num")Integer gold);

	public int insertLog(LogGold log);

}
