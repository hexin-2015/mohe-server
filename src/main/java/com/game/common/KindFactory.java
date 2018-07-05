package com.game.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.game.common.defined.KindType;
import com.game.common.module.ImageModule;
import com.game.common.module.KindModule;
import com.game.entity.DetectLog;
import com.game.entity.KindEntity;
import com.game.entity.show.ShowKindInfo;
import com.game.service.KindService;
import com.mysql.fabric.xmlrpc.base.Array;

public class KindFactory {
	
	private KindType type;
	
	private DetectLog detectLog;
	
	private KindService service;
	
	//是否已查询数据库
	private Boolean isGetDataById = false;
	
	//detectChangeLog
	private KindEntity detectKindEntity ;

	private int kindid;

	private String kindName;
	
	private KindModule kindModule;
	
	private KindFactory(KindType type,KindService service){
		this.type = type;
		this.service = service;
	}
	
	public static KindFactory newInstance(KindType type,KindService service) {
		return new KindFactory(type, service);
	}
	
	public void setData(DetectLog data) {
		this.detectLog = data;
		this.isGetDataById = false;
		KindEntity kindEntity = getKindEntityFromDetectLog();
		kindName = kindEntity.getName();
		this.kindModule = newKindModule();
	}
	
	public void setData(int kindid){
		this.kindid = kindid;
		this.isGetDataById = true;
		this.kindModule = newKindModule();
	}
	
	private KindModule newKindModule(){
		if(isGetDataById){
			return new KindModule(type,kindid);
		}
		return new KindModule(type, kindName);
	}
	
	
	private KindEntity getKindEntityFromDetectLog() {
		if(detectLog==null){
			throw new RuntimeException("先设置data！");
		}
		KindEntity changeToKindEntity = detectLog.changeToKindEntity();
		return changeToKindEntity;
	}
	
	public void save(){
		//检测是否需要生成品类
		if(!isNeedCreateKind()){
			return;
		}
		KindEntity kindEntity = getKindEntity();
		KindEntity entity = getKindEntityFromDetectLog();
		detectKindEntity = entity;
		entity.setKindType(type);
		if(kindEntity == null){
			entity.setImage_min_score(entity.getDecectScore());
			entity.setImage_num(1);
			entity.setImage_scores(String.valueOf(entity.getDecectScore()));
			entity.setImage_ids(Integer.toString(detectLog.getId()));
			//id直接赋值到entity中
			service.insert(entity);
			return;
		}
		//如果最低分小于当前分值，加入图片库
		int imageNum = kindEntity.getImage_num();
		if(kindEntity.getImage_min_score() < entity.getDecectScore() || imageNum < KindModule.IMG_NUM){
			updateKindImg();
			//更新缓存
			updateLocalCache();
		}
		
	}
		
	public KindEntity getKindEntity() {
		if(kindModule == null){
			throw new RuntimeException("先调用setData!");
		}
		KindEntity kindEntity = kindModule.getKindEntity();
		if(kindEntity == null){
			return null;
		}
		kindEntity.setKindType(type);
		return kindEntity;
	}
	
	public void updateLocalCache(){
		if(kindModule == null){
			throw new RuntimeException("先调用setData!");
		}
		kindModule.clearLocalCache();
	}
	
	
	public ShowKindInfo getShowKindInfo(){
		KindEntity kindEntity = getKindEntity();
		//使用left Join 查找外部信息
		ShowKindInfo showKindInfo = service.getShowKindInfo(kindEntity);
		if(showKindInfo.getImages()!=null){
			
			ArrayList<String> arr = new ArrayList<String>();
			for (String path : showKindInfo.getImages()) {
				String showUrl = ImageModule.getShowUrl(path);
				arr.add(showUrl);
			}
			showKindInfo.setImages(arr);
		}
		
		return showKindInfo;
		
	}
	
	public void updateComment(Integer comment_id,Integer comment_score ){
		KindEntity kindEntity = getKindEntity();
		kindEntity.setComment_id(String.valueOf(comment_id));
		kindEntity.setComment_score(comment_score);
		kindEntity.setKindType(type);
		service.update(kindEntity);
		updateLocalCache();
	}
	
	private boolean isNeedCreateKind(){
		if(type.getIndex()>4){
			return false;
		}
		return true;
	}
	/**
	 * 根据识别结果更新kind中图片
	 */
	private void updateKindImg(){
		if(detectKindEntity==null){
			return;
		}
		
		//获取最低识别分数
		KindEntity kindEntity = getKindEntity();
		String image_ids = kindEntity.getImage_ids();
		int minScore = kindEntity.getImage_min_score();
		String image_scores = kindEntity.getImage_scores();

		if(kindEntity.getImage_num() < KindModule.IMG_NUM){
			minScore = minScore > detectKindEntity.getDecectScore() ? detectKindEntity.getDecectScore() : minScore;
			String newImageIds = image_ids + "," + detectLog.getId();
			String newImageScores = image_scores + "," + detectKindEntity.getDecectScore();
			kindEntity.setImage_min_score(minScore);
			kindEntity.setImage_ids(newImageIds);
			kindEntity.setImage_scores(newImageScores);
			kindEntity.setImage_num(kindEntity.getImage_num()+1);
		} else {
			//替换图片中的最低分数项
			replaceMinScoreImage(detectKindEntity,kindEntity);
		}
		service.update(kindEntity);
	
	}
	/**
	 * 
	 * @param src 识别的结果
	 * @param dest 数据库中记录
	 */
	private void replaceMinScoreImage(KindEntity src,KindEntity dest) {
		String image_scores = dest.getImage_scores();
		String[] split = image_scores.split(",");
		int minIndex = getScoreArrayMinElementIndex(split);
		split[minIndex] = String.valueOf(src.getDecectScore());
		//修改scores
		dest.setImage_scores(String.join(",", split));
		//修改IDs
		String image_ids = dest.getImage_ids();
		String[] split2 = image_ids.split(",");
		split2[minIndex] = String.valueOf(detectLog.getId());
		dest.setImage_ids(String.join(",", split2));
		//最小分数
		minIndex = getScoreArrayMinElementIndex(split);
		dest.setImage_min_score(Integer.parseInt(split[minIndex]));
		
	}
	
	private int getScoreArrayMinElementIndex(String[] scoreArr){
		int minIndex = 0;
		int minValue = Integer.MAX_VALUE;
		for (int i = 0 ; i < scoreArr.length ; i++) {
			int parseInt = Integer.parseInt(scoreArr[i]);
			if(parseInt < minValue){
				minValue = parseInt;
				minIndex = i;
			}
		}
		return minIndex;
	}
	
	
	
	
	

}
