package com.game.service;

import com.game.bean.AiResultBean;

public interface AiService {
	
	AiResultBean plantDetect(String img);
	
	AiResultBean animalDetect(String img);
	
	AiResultBean carDetect(String img);
	
	AiResultBean dishDetect(String img);
	
	AiResultBean faceDetect(String img);
	/**
	 * 
	 * @param img
	 * @param face_field 可选返回字段**age,beauty,expression,faceshape,gender,glasses,landmark,race,quality,facetype,parsing信息**
	 * @return
	 */
	AiResultBean faceDetect(String img,String face_field);

}
