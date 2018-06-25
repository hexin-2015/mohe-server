package com.game.common;

import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;

import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.game.common.defined.DetectType;
import com.game.common.defined.KindType;
import com.game.common.module.ImageModule;
import com.game.entity.DetectLog;
import com.game.entity.DetectPlantLog;
import com.game.entity.KindEntity;
import com.game.entity.show.ShowKindInfo;
import com.game.service.KindService;

public class KindFactory {
	
	private KindType type;
	
	private DetectLog detectLog;
	
	private KindService service;
	/**
	 * 存储查询结果
	 */
	private KindEntity kindInfo;
	
	//是否已查询数据库
	private Boolean hadQuery = false;
	
	public KindFactory(KindType type,KindService service) {
		this.type = type;
		this.service = service;
	}
	
	public void setData(DetectLog data) {
		this.detectLog = data;
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
		entity.setKindType(type);
		if(kindEntity == null){
			entity.setImage_min_score(entity.getDecectScore());
			entity.setImage_num(1);
			entity.setImage_ids(Integer.toString(detectLog.getId()));
			//id直接赋值到entity中
			service.insert(entity);
			kindInfo = entity;
			return;
		}
		//如果最低分小于当前分值，加入图片库
		kindInfo = kindEntity;
		if(kindEntity.getImage_min_score() < entity.getDecectScore()){
			
		}
	}
	
	private boolean isNeedCreateKind(){
		if(type.getIndex()>4){
			return false;
		}
		return true;
	}
	
	private void queryKindInfo(){
		
		KindEntity entity = getKindEntityFromDetectLog();
		entity.setKindType(type);
		KindEntity kindEntity = service.get(entity);
		if(kindEntity != null){
			kindEntity.setKindType(type);
		}
		kindInfo = kindEntity;
		hadQuery = true;
	}
	
	public KindEntity getKindEntity() {
		if(!hadQuery){
			queryKindInfo();
		}
		return kindInfo;
	}
	
	public void setData(Integer kindid) {
		if(kindid != null){
			KindEntity entity = new KindEntity();
			entity.setKindType(type);
			entity.setId(kindid);
			kindInfo=service.get(entity);
			hadQuery = true;
		}
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
			
		};
		
		
		return showKindInfo;
		
	}
	
	public void updateComment(Integer comment_id,Integer comment_score ){
		KindEntity kindEntity = getKindEntity();
		kindEntity.setComment_id(String.valueOf(comment_id));
		kindEntity.setComment_score(comment_score);
		kindEntity.setKindType(type);
		service.update(kindEntity);
	}
	
	
	
	
	
	

}
