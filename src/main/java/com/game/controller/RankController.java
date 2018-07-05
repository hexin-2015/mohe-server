package com.game.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.game.common.GoldFactory;
import com.game.common.KindFactory;
import com.game.common.WeiXinHttpRequest;
import com.game.common.WeiXinSession;
import com.game.common.cache.RankCache;
import com.game.common.defined.GoldReason;
import com.game.common.defined.GoldSectionDefined;
import com.game.common.defined.KindType;
import com.game.common.util.SpringContextUtil;
import com.game.common.util.StringUtil;
import com.game.entity.RankData;
import com.game.entity.show.ShowKindInfo;
import com.game.result.Message;
import com.game.result.bean.ResMessage;
import com.game.service.KindService;
import com.game.service.RankService;

@Controller
@RequestMapping("/rank")
public class RankController {
	
	@Autowired RankService service;
	
	@Autowired RankCache rank;
	
	@RequestMapping(value="/maxBeauty",produces="application/json;charset=utf-8")
	@ResponseBody
	public String maxBeauty(HttpServletRequest request,HttpServletResponse response){
	
		String openid = WeiXinSession.getOpenid(request);
		List<RankData> maxBeauty = rank.getMaxBeauty();
		ResMessage successMessage = Message.SuccessMessage(maxBeauty);
		//发送元宝
		GoldFactory.doGetRandomGold(openid, GoldReason.RANDOM, GoldSectionDefined.DEFAULT,successMessage);
		
		return successMessage.toJsonString();

	}
	
	
	
	@RequestMapping(value="/minBeauty",produces="application/json;charset=utf-8")
	@ResponseBody
	public String minBeauty(HttpServletRequest request,HttpServletResponse response){
		String openid = WeiXinSession.getOpenid(request);
		List<RankData> maxBeauty = rank.getMixBeauty();
		ResMessage successMessage = Message.SuccessMessage(maxBeauty);
		GoldFactory.doGetRandomGold(openid, GoldReason.RANDOM, GoldSectionDefined.DEFAULT,successMessage);
		return successMessage.toJsonString();

	}
	
	@RequestMapping(value="/maxGold",produces="application/json;charset=utf-8")
	@ResponseBody
	public String maxGold(HttpServletRequest request,HttpServletResponse response){
		String openid = WeiXinSession.getOpenid(request);
		List<RankData> res = rank.getMaxGold();
		
		ResMessage successMessage = Message.SuccessMessage(res);
		//发送元宝
		GoldFactory.doGetRandomGold(openid, GoldReason.RANDOM, GoldSectionDefined.DEFAULT,successMessage);
		return successMessage.toJsonString();

	}
	
	
	@RequestMapping(value="/test",produces="application/json;charset=utf-8")
	@ResponseBody
	public String test(HttpServletRequest request,HttpServletResponse response){
		String openid = WeiXinSession.getOpenid(request);
		//List<RankData> res = rank.getMaxGold();
		
		
		//发送元宝
		//GoldFactory.doGetRandomGold(openid, GoldReason.BEGIVE, GoldSectionDefined.DEFAULT,successMessage);
		//
		//GoldFactory.doGetRandomGold(openid, GoldReason.UPDATE_USERINFO, GoldSectionDefined.SECTION_5_15, successMessage);
		KindService bean = SpringContextUtil.getBean(KindService.class);
		KindFactory newInstance = KindFactory.newInstance(KindType.PLANT,bean);
		newInstance.setData(8);
		ShowKindInfo showKindInfo = newInstance.getShowKindInfo();
		
		ResMessage successMessage = Message.SuccessMessage(showKindInfo);
		String timeFormat = StringUtil.getTimeFormat("2017-11-11 11:11:11.0");
		System.err.println(timeFormat);
		
		return successMessage.toJsonString();

	}
	
	
	
	
}
