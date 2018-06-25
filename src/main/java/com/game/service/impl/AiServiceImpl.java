package com.game.service.impl;

import java.util.HashMap;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.game.bean.AiResultBean;
import com.game.bean.DishResultBean;
import com.game.bean.FaceResultBean;
import com.game.bean.ImageResultBean;
import com.game.common.BaiduAi;
import com.game.common.util.ImageUtil;
import com.game.service.AiService;

@Service
class AiServiceImpl implements AiService{
	
	Logger logger = LoggerFactory.getLogger(AiServiceImpl.class);
	
	private BaiduAi api = BaiduAi.getInstance();
	
	public static AiResultBean explainResult(JSONObject res,Class<? extends AiResultBean> clazz){
		AiResultBean parseObject = com.alibaba.fastjson.JSONObject.parseObject(res.toString(), clazz);
		return parseObject;
	}
	

	@Override
	public AiResultBean plantDetect(String img) {
		JSONObject plantDetect = api.getAipImageClient().plantDetect(img,null);
		AiResultBean explainResult = explainResult(plantDetect,ImageResultBean.class);
		return explainResult;
	}
	

	@Override
	public AiResultBean animalDetect(String img) {
		
		JSONObject detect = api.getAipImageClient().animalDetect(img,null);
		AiResultBean explainResult = explainResult(detect,ImageResultBean.class);
		return explainResult;
	}


	@Override
	public AiResultBean carDetect(String img) {

		JSONObject detect = api.getAipImageClient().carDetect(img,null);
		AiResultBean explainResult = explainResult(detect,ImageResultBean.class);
		return explainResult;
	}


	@Override
	public AiResultBean dishDetect(String img) {
		
		JSONObject detect = api.getAipImageClient().dishDetect(img,null);
		AiResultBean explainResult = explainResult(detect,DishResultBean.class);
		return explainResult;
	}


	@Override
	public AiResultBean faceDetect(String img) {
		
		String imageBase64 = ImageUtil.getImageBase64Str(img);
		JSONObject detect = api.getAipFaceClient().detect(imageBase64,"BASE64",null);
		AiResultBean explainResult = explainResult(detect,FaceResultBean.class);
		return explainResult;
	}


	@Override
	public AiResultBean faceDetect(String img, String face_field) {
		String imageBase64 = ImageUtil.getImageBase64Str(img);
		HashMap<String, String> option = new HashMap<String,String>();
		option.put("face_field", face_field);
		JSONObject detect = api.getAipFaceClient().detect(imageBase64,"BASE64",option);
		AiResultBean explainResult = explainResult(detect,FaceResultBean.class);
		//System.err.println(detect);
		return explainResult;
	}
	
	
	
	

}
