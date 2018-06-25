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
	
	
	
	
	
	
}
