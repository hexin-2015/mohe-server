package com.game.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.alibaba.fastjson.JSONObject;
import com.game.bean.AiResultBean;
import com.game.common.DetectLogFactory;
import com.game.common.ExecutorDoAi;
import com.game.common.GoldFactory;
import com.game.common.KindFactory;
import com.game.common.UploadFile;
import com.game.common.WeiXinSession;
import com.game.common.defined.DetectType;
import com.game.common.defined.GoldReason;
import com.game.common.defined.KindType;
import com.game.common.module.UserModule;
import com.game.entity.DetectFaceLog;
import com.game.entity.DetectLog;
import com.game.entity.KindEntity;
import com.game.entity.show.ShowKindInfo;
import com.game.result.Message;
import com.game.result.bean.ResMessage;
import com.game.result.consts.ReasonCode;
import com.game.service.AiService;
import com.game.service.DetectService;
import com.game.service.KindService;
import com.game.service.UserService;


@Controller
@RequestMapping("/upload")
public class UploadController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private AiService service;
	@Autowired 
	private DetectService detectService;
	@Autowired 
	private KindService kindService;
	
	@RequestMapping(value="")
	@ResponseBody
	public String undefind(HttpServletRequest request,HttpServletResponse response){
		
		return "hello";
	}
	
	
	@RequestMapping(value="/index")
	@ResponseBody
	public String index(HttpServletRequest request,HttpServletResponse response){
		
		ResMessage successMessage = Message.SuccessMessage("success!");
		return successMessage.toJsonString();
	}
	
	
	@RequestMapping(value="/weixinsave",produces="application/json;charset=utf-8")
	@ResponseBody
	public String save(HttpServletRequest request,HttpServletResponse response){
		
		 boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		 Object res = "";
		 
		 if(isMultipart){
			 UploadFile uploadFile = new UploadFile(request,response);
			 boolean result = false;
			 try {
				 result = uploadFile.saveFile();
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(result == false){
				System.err.println(uploadFile.getError());
				return Message.FailMessage(ReasonCode.ParamValue,uploadFile.getError()).toJsonString();
			}
			 //判断session
			String sessionid = uploadFile.getParams().get("sessionID");
			if(sessionid == null || sessionid.isEmpty()){
				return Message.FailMessage(ReasonCode.ParamValue,"sessionid is empty").toJsonString();
			}
			WeiXinSession weiXinSession = new WeiXinSession(sessionid);
			String openid = weiXinSession.getOpenid();
			if(openid==null){
				return Message.FailMessage(ReasonCode.ParamValue,"openid is empty").toJsonString();
			}
			if(uploadFile.getFileNames().size()<1){
				return Message.FailMessage(ReasonCode.ParamValue,"上传文件为空").toJsonString();
			}
			//识别
			AiResultBean imgDetect = imgDetect(uploadFile) ;

			String imgPath = uploadFile.getFileNames().get(0);
			HashMap<String, String> params = uploadFile.getParams();
			//记录log
			DetectLogFactory detectLogFactory = new DetectLogFactory(params.get("imgType"),imgPath,openid);
			detectLogFactory.setService(detectService);
			detectLogFactory.setData(imgDetect);
			detectLogFactory.save();
			//识别存储结果
			DetectLog detectLog = detectLogFactory.getDetectLog();
			
			//生成类别
			ShowKindInfo showKindInfo = new ShowKindInfo();
			if(KindType.getByName(params.get("imgType")).getIndex() < 5){
				KindFactory kindFactory = KindFactory.newInstance(KindType.getByName(params.get("imgType")),kindService);
				kindFactory.setData(detectLog);
				kindFactory.save();
				showKindInfo = kindFactory.getShowKindInfo();
			}
			//返回
			detectLog.setOpenid(null);
			detectLog.setPath(null);
			showKindInfo.setDetectLog(detectLog);
			res = showKindInfo ;
		 }

		ResMessage successMessage = Message.SuccessMessage(res);
		return successMessage.toJsonString();
	}
	
	/**
	 * 识别图片		
	 * @param uploadFile
	 * @return
	 */
	private AiResultBean imgDetect(UploadFile uploadFile) {
		AiResultBean res = new AiResultBean();
		String imgPath = uploadFile.getFileNames().get(0);
		HashMap<String, String> params = uploadFile.getParams();
		String type = params.get("imgType");
		//放入队列
		//ExecutorDoAi.add(uploadFile);
		//等待结果
		
		//获得结果
		
		if(type == null){
			System.err.println("null");	
		}else if(type.equals("plant")){	
			res = service.plantDetect(imgPath);		
		}else if (type.equals("animal")) {
			res = service.animalDetect(imgPath);
		}else if (type.equals("car")) {
			res = service.carDetect(imgPath);
		}else if (type.equals("dish")) {
			res = service.dishDetect(imgPath);
		}else if (type.equals("face")) {
			res = service.faceDetect(imgPath,"age,beauty,gender");
		}

		return  res;
	}
	
	
	
	@RequestMapping(value="/doRankBeauty",produces="application/json;charset=utf-8")
	@ResponseBody
	public String doRankBeauty(HttpServletRequest request,HttpServletResponse response){
		
		String openid = WeiXinSession.getOpenid(request);
		String faceLogId = request.getParameter("faceLogId");
		if(faceLogId == null ){
			return Message.FailMessage(ReasonCode.ParamValue,"faceLogId 不能为空！").toJsonString();
		}
		int logid = Integer.parseInt(faceLogId);
		
		if(openid==null){
			return Message.FailMessage(ReasonCode.ParamValue,"Session 错误！").toJsonString();
		}
		//detectService
		DetectFaceLog faceLog = detectService.getFaceLog(logid);
		if(faceLog==null || !faceLog.getOpenid().equals(openid)){
			return Message.FailMessage(ReasonCode.ParamValue,"错误的logid！").toJsonString();
		}
		detectService.updateFaceLogBeRank(logid);
		//更新个人颜值	
		UserModule userModule = new UserModule(openid,userService);
		userModule.saveBeauty(faceLog.getBeauty());
		

		ResMessage successMessage = Message.SuccessMessage();
		//发送元宝
		GoldFactory.doGetGold(openid, GoldReason.DORANK, 2,successMessage);
		return successMessage.toJsonString();
	}
	
	
	
	@RequestMapping(value="/form")
	public String form(HttpServletRequest request,HttpServletResponse response){
		
		ResMessage successMessage = Message.SuccessMessage("success!");
		return "upload/form";
	}
	
	
	
}
