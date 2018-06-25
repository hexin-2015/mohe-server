package com.game.common;

import com.baidu.aip.client.BaseClient;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.imageclassify.AipImageClassify;

public class BaiduAi {
	
	private AipFace aipFaceClient = null;
	private AipImageClassify aipImageClient = null;
	
	private static String appid = "";
	private static String apiKey = "";
	private static String secretKey = "";
	private static BaiduAi api = new BaiduAi();
	
	
	private static void setFaceAuth(){
		appid = "11299543";
		apiKey = "Yte74GGxMDXX954k6SMOF08D";
		secretKey = "ltGzCcVzPdVO4t4vZ6VGQ8tP0NelO4nF";
	}
	
	private BaiduAi(){
		  setFaceAuth();
		 
		  
	}
	
	public AipFace getAipFaceClient(){
		if(aipFaceClient == null){
			aipFaceClient = new AipFace(appid, apiKey , secretKey);
			setAttribute(aipFaceClient);
		}
		return aipFaceClient;
		  
	}
	
	
	public AipImageClassify getAipImageClient(){
		if(aipImageClient == null){
			aipImageClient = new AipImageClassify(appid, apiKey , secretKey);
			setAttribute(aipImageClient);
		}
		return aipImageClient;
	}
	
	private void setAttribute(BaseClient client) {
		 // 可选：设置网络连接参数
		 client.setConnectionTimeoutInMillis(2000);
		 client.setSocketTimeoutInMillis(60000);
	}
	
	
	public static BaiduAi getInstance(){
		return api;
	}
	

}
