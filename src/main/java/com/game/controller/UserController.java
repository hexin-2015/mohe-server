package com.game.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.common.GoldFactory;
import com.game.common.WeiXinSession;
import com.game.common.defined.GoldReason;
import com.game.common.defined.GoldSectionDefined;
import com.game.common.module.UserModule;
import com.game.entity.UserAdviceEntity;
import com.game.entity.UserEntity;
import com.game.result.Message;
import com.game.result.bean.ResMessage;
import com.game.result.consts.ReasonCode;
import com.game.service.UserService;



@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired UserService userService;
	
	@RequestMapping(value="")
	@ResponseBody
	public String undefind(HttpServletRequest request,HttpServletResponse response){
		
		return "";
	}
	
	
	@RequestMapping(value="/info",produces="application/json;charset=utf-8")
	@ResponseBody
	public String info(HttpServletRequest request,HttpServletResponse response){
		
		String openid = WeiXinSession.getOpenid(request);
		if(openid==null){
			return Message.FailMessage(ReasonCode.ParamPreCheck, "sessionID 错误！").toJsonString();
		}
		UserEntity user = userService.getUser(openid);
		user.setOpenid(null);
		ResMessage successMessage = Message.SuccessMessage(user);
		
		//发送元宝
		GoldFactory.doGetRandomGold(openid, GoldReason.RANDOM, GoldSectionDefined.DEFAULT,successMessage);
		return successMessage.toJsonString();
	}
	
	@RequestMapping(value="/advice",produces="application/json;charset=utf-8")
	@ResponseBody
	public String advice(HttpServletRequest request,HttpServletResponse response){
		
		String openid = WeiXinSession.getOpenid(request);
		String content = request.getParameter("content");
		String type = request.getParameter("type");
		String contact= request.getParameter("contact");
		
		if(openid==null){
			return Message.FailMessage(ReasonCode.ParamPreCheck, "sessionID 错误！").toJsonString();
		}
		UserAdviceEntity entity = new UserAdviceEntity();
		entity.setOpenid(openid);
		entity.setContact(contact);
		entity.setType(type);
		entity.setContent(content);
		boolean res = userService.insertUserAdvice(entity);
		if(res == false){
			System.err.println("新增失败");
		}
		
		ResMessage successMessage = Message.SuccessMessage("success");
		return successMessage.toJsonString();
	}
	
	
	@RequestMapping(value="/gm",produces="application/json;charset=utf-8")
	@ResponseBody
	public String gm(HttpServletRequest request,HttpServletResponse response){
		
		String openid = request.getParameter("openid");
		String type = request.getParameter("type");
		String gmCode = request.getParameter("gm_code");
		String gold = request.getParameter("gold");
		
		if(!GoldReason.getByName(type).equals(GoldReason.GM_SEND)){
			return Message.FailMessage(ReasonCode.ParamPreCheck, "type 错误！").toJsonString();
		}
		
		if(!gmCode.equals("xxxxxx")){
			return Message.FailMessage(ReasonCode.ParamPreCheck, "gmCode 错误！").toJsonString();
		}
		
		if(openid == null || gold==null ||openid.isEmpty()||gold.isEmpty()){
			return Message.FailMessage(ReasonCode.ParamPreCheck, "参数错误！").toJsonString();
		}
		
		ResMessage resMessage = Message.SuccessMessage("success");
		GoldFactory.doGetGold(openid, GoldReason.GM_SEND, Integer.parseInt(gold),resMessage);
		
		return resMessage.toJsonString();
	} 
	
	
	
	
	
	
}
