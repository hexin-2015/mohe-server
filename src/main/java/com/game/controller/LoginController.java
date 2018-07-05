package com.game.controller;


import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.common.GoldFactory;
import com.game.common.WeiXinHttpRequest;
import com.game.common.WeiXinSession;
import com.game.common.defined.GoldReason;
import com.game.common.defined.GoldSectionDefined;
import com.game.common.util.MemcachedUtil;
import com.game.common.util.StringUtil;
import com.game.entity.UserEntity;
import com.game.result.Message;
import com.game.result.bean.ResMessage;
import com.game.service.UserService;



@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired UserService userService;
	//@Autowired MemCachedClient memcachedClient;
	
	@RequestMapping(value="")
	@ResponseBody
	public String undefind(HttpServletRequest request,HttpServletResponse response){
		
		
		WeiXinSession weiXinSession = new WeiXinSession("1");
		weiXinSession.set("openid", "okTwE5g70F-arEzYon-0SPyZlD9o");
		weiXinSession.set("sessionKey","xxxx");
		String serializable = (String)weiXinSession.get("openid");
		System.err.println(serializable);
		return "hello";
	}
	
	
	@RequestMapping(value="/index")
	@ResponseBody
	public String show(HttpServletRequest request,HttpServletResponse response){
		
		ResMessage successMessage = Message.SuccessMessage("success!");
		return successMessage.toJsonString();
	}
	
	@RequestMapping(value="/weixin",produces="application/json;charset=utf-8")
	@ResponseBody
	public String weixin(HttpServletRequest request,HttpServletResponse response){

		String code = request.getParameter("code");
		if(code==null){
			ResMessage msg = Message.FailMessage(500, "code cant be empty!");
			return msg.toJsonString();
		}
		 UserEntity openId = WeiXinHttpRequest.getOpenId(code);
		//获取失败
		if(openId==null){
			ResMessage msg = Message.FailMessage(500, "获取openId失败");
			return msg.toJsonString();
		}
		UserEntity user = userService.getUser(openId.getOpenid());
		//新增
		if(user==null){
			user = new UserEntity();
			user.setOpenid(openId.getOpenid());
			userService.insert(user);
		}
		String sessionID = WeiXinSession.createSessionID( openId.getSessionKey(),openId.getOpenid());
		//存储session值
		WeiXinSession weiXinSession = new WeiXinSession(sessionID);
		weiXinSession.set("openid", openId.getOpenid());
		weiXinSession.set("sessionKey", openId.getSessionKey());
		
		HashMap<String,String> resMap = new HashMap<String,String>();
		resMap.put("sessionID", sessionID);
		ResMessage successMessage = Message.SuccessMessage(resMap);
		return successMessage.toJsonString();
	}
	
	@RequestMapping(value="/uploadUserInfo",produces="application/json;charset=utf-8")
	@ResponseBody
	public String uploadUserInfo(HttpServletRequest request,HttpServletResponse response,UserEntity user){
		String openid = WeiXinSession.getOpenid(request);
		//String openid = "okTwE5g70F-arEzYon-0SPyZlD9o";
		if(openid == null){
			ResMessage msg = Message.FailMessage(500, "sessionID 错误!");
			return msg.toJsonString();
		}
		user.setOpenid(openid);
		userService.update(user);
		ResMessage successMessage = Message.SuccessMessage("success!");
		//发送金币
		GoldFactory.doGetRandomGold(openid, GoldReason.UPDATE_USERINFO, GoldSectionDefined.SECTION_5_15, successMessage);
		return successMessage.toJsonString();
		
	}
	
	
	
}
