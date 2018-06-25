package com.game.common.module;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.game.entity.UserLikeListEntity;

public class LikeModule {
	
	
	public static List<Integer> getLikeList(UserLikeListEntity entity){
		List<Integer> reList = new ArrayList<Integer>();
		
		if(entity.getComment_ids() == null){
			return reList;
		}
		
		String[] split = entity.getComment_ids().split(",");
		for (String s : split) {
			reList.add(Integer.parseInt(s));
		}
		return reList;
	}
	
	public static Set<Integer> getLikeSet(UserLikeListEntity entity){
		Set<Integer> res = new HashSet<Integer>();
		
		if(entity == null || entity.getComment_ids() == null){
			return res;
		}
		
		String[] split = entity.getComment_ids().split(",");
		for (String s : split) {
			res.add(Integer.parseInt(s));
		}
		return res;
	}

}
