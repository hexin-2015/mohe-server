package com.game.common;

import com.game.bean.AiResultBean;
import com.game.common.util.StringUtil;
import com.game.common.util.TimeUtil;
import com.game.entity.DetectLog;
import com.game.service.DetectService;

public class DetectLogFactory {
	
	private String imgType; 
	
	private AiResultBean data; 
	
	private String imgPath;
	
	private String openid;
	
	private DetectService service;
	
	private DetectLog log;
	
	
	/**
	 * 
	 * @param type 图片类型
	 * @param imgPath 存储path
	 * @param Openid openid
	 */
	public DetectLogFactory(String type,String imgPath,String openid) {
		imgType = type;
		this.imgPath = imgPath;
		this.openid = openid;
	}
	
	public void setService(DetectService service){
		this.service = service;
	}
	
	public void setData(AiResultBean data){
		this.data = data;
	}
	
	/**
	 * imgType:plant,animal,car,dish,face
	 * 根据type动态生成对应类实例：
	 * 例如：imgType:plant
	 * 生成实例类： com.game.entity.DetectPlantLog
	 * @return DetectLog
	 */
	private DetectLog makeEntity(){
		
		StringBuilder sb = new StringBuilder();
		sb.append("com.game.entity.Detect");
		sb.append(StringUtil.toUpperFristChar(imgType));
		sb.append("Log");
		String className = sb.toString();
		DetectLog log = null;
		try {
			Class<?> clazz = Class.forName(className);
			log = (DetectLog)clazz.newInstance();
		} catch (ClassNotFoundException e) {	
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return log;
	}
	
	/**
	 * 返回log数据
	 * 要在save之后才能获取到数据
	 * @return
	 */
	
	public DetectLog getDetectLog(){
		return log;
	}
	
	
	/**
	 * 保存
	 */
	public Integer save(){
		DetectLog entity = makeEntity();
		if(entity==null){
			return null;
		}
		entity.setOpenid(openid);
		entity.setPath(imgPath);
		entity.setTime(TimeUtil.getNow());
		entity.setData(data);

		int insertid = service.insert(entity);
		//设置log
		log = entity;
		
		return insertid;
	}
	
	
	

}
