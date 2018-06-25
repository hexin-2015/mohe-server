package com.game.common.defined;

/**
 * 随机获得率配置
 * @author admin
 *
 */
public class GoldGetRate {
	
	//随机获得,1000以上需要通过概率获得
	//RANDOM("random",10000);
	private static final float Random = 0.05f;
	
	public static float getRate(GoldReason reason){
		switch (reason) {
		case RANDOM:
			return Random;
		default:
			break;
		}
		return 1;
	}
	
	
}
