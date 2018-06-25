package com.game.common.cache;


import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.scripting.xmltags.VarDeclSqlNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.game.common.defined.HostDefined;
import com.game.common.module.ImageModule;
import com.game.common.util.MemcachedUtil;
import com.game.common.util.NumberUtil;
import com.game.entity.RankData;
import com.game.entity.UserEntity;
import com.game.service.RankService;
import com.game.service.UserService;

@Component(value="rankCache")
public class RankCache {
	
	private static final int RankNum = 100;
	//默认过期时间3600s
	private static Date expireTime = new Date(3600*1000);
	
	private static final boolean isSetCache = false;
	
	private List<RankData> rankMaxBeauty = new ArrayList<RankData>(RankNum);
	
	private List<RankData> rankMinBeauty = new ArrayList<RankData>(RankNum);
	
	private List<RankData> rankMaxGold = new ArrayList<RankData>(RankNum);
	
	
	@Autowired RankService rankService;
	
	@Autowired UserService userService ;
	
	
	public List<RankData> getMaxBeauty(){
		
		RankData rankData = new RankData();
		rankData.setLimitNum(RankNum);
		rankMaxBeauty = new ArrayList<RankData>(RankNum);
		if(rankMaxBeauty.isEmpty()){
			
			List<RankData> maxBeauty = rankService.getMaxBeauty(rankData);
			putUserInfo(maxBeauty);
			rankMaxBeauty = maxBeauty;
			//vip插入
			RankData vipRank = getVipRank();
			putVipInRank(vipRank,rankMaxBeauty);
			
		}else{
			
			System.err.println("使用静态");
		}
		return rankMaxBeauty;
	}
	
	private void putVipInRank(RankData vipData,List<RankData> rankDatas){
		ArrayList<RankData> rank = (ArrayList<RankData>)rankDatas;
		if(rankDatas.size()<RankNum){
			
			rank.add(0,vipData);
		}else {
			rank.remove(rank.size()-1);
			rank.add(0,vipData);
		}

		
	}
	
	/**
	 * 获取vip信息
	 */
	private RankData getVipRank(){
		//List<String> openids = new ArrayList<String>();
		//openids.add("okTwE5hneAwnJyzp5o7zrjLgsB-A");
		//Mimo
		UserEntity user = userService.getUser("okTwE5hneAwnJyzp5o7zrjLgsB-A");
		RankData rankData = new RankData();
		rankData.setAvatarUrl(user.getAvatarUrl());
		rankData.setBeauty(99.99f);
		rankData.setImgPath("http://mohe.ipnewgame.com/image/20180606/f8d2eedb-1929-48bb-a5c5-f80771bb4d87.jpg");
		rankData.setNickName("MIMO");
		rankData.setOrder(0);
		
		return rankData;
		
	}
	
	
	private static String createImgHttpPath(String localPath){
		return ImageModule.getShowUrl(localPath);
	}
	
	/**
	 * 补充userInfo信息 
	 * @param rankDatas
	 */
	private void putUserInfo(List<RankData> rankDatas){
		
		List<UserEntity> users = new ArrayList<UserEntity>();
		if(rankDatas.size()>0){
			List<String> openids = new ArrayList<String>();
			for (RankData row : rankDatas) {
				openids.add(row.getOpenid());
			}
			users = userService.getUser(openids);
		}
		HashMap<String, UserEntity> hashUser = new HashMap<String,UserEntity>(users.size());
		for (UserEntity userEntity : users) {
			hashUser.put(userEntity.getOpenid(), userEntity);
		}
		int i = 1;
		for (RankData row : rankDatas) {
			UserEntity user = hashUser.get(row.getOpenid());
			if(user != null){
				row.setAvatarUrl(user.getAvatarUrl());
				row.setNickName(user.getNickName());
			}
			//填充游客信息
			putVisitorInfo(row);
			
			row.setOpenid(null);
			String decimalNumber = NumberUtil.getDecimalNumber(row.getBeauty(), 2);
			row.setBeauty(Float.parseFloat(decimalNumber));
			
			String httpPath = createImgHttpPath(row.getImgPath());
			row.setImgPath(httpPath);
			row.setOrder(i);
			i++;
		}
	}
	
	/**
	 * 填充游客信息
	 * @param row 
	 */
	
	private void putVisitorInfo(RankData row) {
		
		String defaultVisitorImg = "http://mohe.ipnewgame.com/image/image/youke.jpg";
		if(row.getAvatarUrl() == null || row.getAvatarUrl().isEmpty()){
			row.setAvatarUrl(defaultVisitorImg);
			row.setNickName("游客");
		}
		
	}
	
	/**
	 * 填充游客和orderid
	 * @param rows
	 */
	private void putVisitorInfoAndOrderId(List<RankData> rows) {
		int i = 1;
		for (RankData rankData : rows) {
			putVisitorInfo(rankData);
			rankData.setOrder(i);
			i += 1;
		}
		
	}
	
	
	/**
	 * 颜值低谷榜
	 * @return
	 */
	public List<RankData> getMixBeauty(){
		RankData rankData = new RankData();
		rankData.setLimitNum(RankNum);

		//防止缓存
		rankMinBeauty = new ArrayList<RankData>(RankNum);
		if(rankMinBeauty.isEmpty()){
			
			List<RankData> minBeauty = rankService.getMinBeauty(rankData);
			putUserInfo(minBeauty);
			
			rankMinBeauty = minBeauty;
	
		}else{
			
			System.err.println("使用静态");
		}
		
		return rankMinBeauty;
	}
	
	/**
	 * 财富榜
	 * @return
	 */
	public List<RankData> getMaxGold() {
		RankData rankData = new RankData();
		rankData.setLimitNum(RankNum);
		//防止缓存
		if(!isSetCache){
			rankMaxGold = new ArrayList<RankData>(RankNum);
		}
		
		if(rankMaxGold.isEmpty()){
			
			List<RankData> res = rankService.getMaxGold(rankData);
			putVisitorInfoAndOrderId(res);
			
			rankMaxGold = res;
	
		}else{
			
			System.err.println("使用静态");
		}
		
		return rankMaxGold;
	}
	
	
	

}
