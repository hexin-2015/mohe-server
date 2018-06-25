package com.game.common;
import java.util.HashMap;
import com.game.common.defined.GoldReason;
import com.game.common.defined.GoldSectionDefined;
import com.game.common.module.GoldModule;
import com.game.common.util.NumberUtil;
import com.game.common.util.SpringContextUtil;
import com.game.result.bean.ResGold;
import com.game.result.bean.ResMessage;

public class GoldFactory {
	
	private static GoldFactory factory = new GoldFactory();
	
	private static HashMap<String,GoldModule> goldModules = new HashMap<String,GoldModule>();
	
	private GoldModule goldModule = null;
	
	private GoldFactory(){}
	
	public static void doGetRandomGold(String openid,GoldReason reason,GoldSectionDefined.Section section,ResMessage message){
		if(openid == null ){
			return ;
		}
		GoldFactory instance = getInstance(openid);
		int gold = instance.getRandomGold(reason,section.min,section.max);
		instance.saveGold(gold,reason);
		instance.bindMessage(message,gold);
	}
	
	public static void doGetRandomGold(String openid,GoldReason reason,GoldSectionDefined.Section section){
		if(openid == null ){
			return ;
		}
		GoldFactory instance = getInstance(openid);
		int gold = instance.getRandomGold(reason,section.min,section.max);
		instance.saveGold(gold,reason);
	}
	
	
	public static void doGetGold(String openid,GoldReason reason,int goldNum,ResMessage message){
		if(openid == null){
			return ;
		}
		GoldFactory instance = getInstance(openid);
		int gold = instance.getGold(reason,goldNum);
		instance.saveGold(gold,reason);
		instance.bindMessage(message,gold);
		
	}
	
	public static void doGetGold(String openid,GoldReason reason,int goldNum){
		if(openid == null){
			return ;
		}
		GoldFactory instance = getInstance(openid);
		int gold = instance.getGold(reason,goldNum);
		instance.saveGold(gold,reason);
	}
	
	private static GoldFactory getInstance(String openid){
		GoldModule module = goldModules.get(openid);
		if(module==null){
			factory.goldModule = new GoldModule(openid);
		}else{
			factory.goldModule = module;
		}
		
		return factory;
	}
	
	
	private int getRandomGold(GoldReason reason,int minNum,int maxNum){
		if(minNum > maxNum){
			return 0;
		}
		int gold = getRandomGold(minNum, maxNum);
		gold = goldModule.getGold(reason,gold);
		
		return gold;
	}
	
	private int getGold(GoldReason reason,int goldNum){
		if(goldNum > 0){
			return goldModule.getGold(reason,goldNum);
		}
		return 0;
	}
	
	private int getRandomGold(int min,int max){
		return NumberUtil.random(min, max);
	}
	//是否中奖
	public static boolean isGoal(float rate){
		int random = NumberUtil.random(10000);
		if(random < Math.round(10000*rate)){
			return true;
		}
		return false;
	} 
	
	private void bindMessage(ResMessage message,int gold){
		if( gold > 0 ){
			ResGold resGold = new ResGold();
			resGold.setGetGold(gold);
			message.setExt(resGold);
		}
		
	}
	
	public static <T> T getService(Class<T> clazz ) {
		T service = SpringContextUtil.getBean(clazz);
		return service;
	}
	
	
	private void saveGold(int gold,GoldReason reason){
		if(gold <= 0){
			return ;
		}
		goldModule.addGold(gold,reason);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
