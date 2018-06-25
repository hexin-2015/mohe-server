package com.game.common.module;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.game.common.GoldFactory;
import com.game.common.defined.GoldGetRate;
import com.game.common.defined.GoldReason;
import com.game.common.util.TimeUtil;
import com.game.entity.LogGold;
import com.game.entity.UserEntity;
import com.game.entity.UserLikeListEntity;
import com.game.service.GoldService;
import com.game.service.UserService;

public class GoldModule {
	private String openid;
	private UserEntity userEntity;
	//private 
	
	
	public GoldModule(String openid) {
		this.openid = openid;
		if(userEntity==null){
			setUserEntity();
		}

	}
	
	//判断该用户是否可以获得这些金币
	public int getGold(GoldReason reason,int gold){
		//是否达到每日限制
		if(isMoreThanMax(reason)){
			return 0;
		}
		//是否需要概率控制
		if(isNeedRateDecide(reason)){
			//是否通过概率
			if(!isSuccessGet(reason)){
				return 0;
			}
		}
		
		//概率通过后处理
		
		return gold;
	}
	
	
	public boolean isMoreThanMax(GoldReason reason) {
		return false;
	}
	
	public boolean isNeedRateDecide(GoldReason reason) {
		if(reason.getIndex()>1000){
			return true;
		}
		return false;
	}
	
	public void addGold(int gold,GoldReason reason) {
		GoldService service = GoldFactory.getService(GoldService.class);
		//添加用户剩余金币
		service.addGold(openid, gold);
		//添加日志
		LogGold logGold = getLogGold(reason, gold);
		service.insertLog(logGold);
		//设置缓存
		userEntity.setGold(userEntity.getGold()+gold);
		//更新每日获取数据
		
		updateUserLimitBean();
	}
	
	private LogGold getLogGold(GoldReason reason,int gold){
		LogGold logGold = new LogGold();
		logGold.setOpenid(openid);
		logGold.setChange(gold);
		logGold.setOldNum(userEntity.getGold());
		logGold.setNewNum(userEntity.getGold()+gold);
		logGold.setReason(reason.getIndex());
		logGold.setTime(TimeUtil.getNow());
		return logGold;
	}
	
	private void updateUserLimitBean(){
		
	}
	
	private void setUserEntity(){
		UserService service = GoldFactory.getService(UserService.class);
		UserModule userModule = new UserModule(openid, service);
		userEntity = userModule.getUserEntity();
		
	}
	
	
	private boolean isSuccessGet(GoldReason reason) {
		boolean res = false;
		//获取概率
		float rate = GoldGetRate.getRate(reason);
		switch (reason) {
		case RANDOM:
			res = GoldFactory.isGoal(rate);
			break;

		default:
			break;
		}
		
		
		return res;
	}
	
	

}
