package com.game.controller;


import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.game.common.GoldFactory;
import com.game.common.KindFactory;
import com.game.common.WeiXinSession;
import com.game.common.defined.CommentType;
import com.game.common.defined.GoldReason;
import com.game.common.defined.KindType;
import com.game.common.module.LikeModule;
import com.game.entity.CommentEntity;
import com.game.entity.KindEntity;
import com.game.entity.UserLikeListEntity;
import com.game.result.Message;
import com.game.result.bean.ResMessage;
import com.game.result.consts.ReasonCode;
import com.game.service.CommentService;
import com.game.service.KindService;
import com.game.service.UserService;

/**
 * 评论系统
 * @author admin
 */

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired UserService userService;
	@Autowired CommentService service;
	@Autowired private KindService kindService;
	
	
	//添加评论
	@RequestMapping(value="/add",produces="application/json;charset=utf-8")
	@ResponseBody
	public String add(HttpServletRequest request,HttpServletResponse response){
		
		String openid = WeiXinSession.getOpenid(request);
		String imgType = request.getParameter("imgType");
		String kindid = request.getParameter("kindid");
		String content = request.getParameter("content");
		if(openid==null){
			return Message.FailMessage(ReasonCode.ParamPreCheck, "sessionID 错误！").toJsonString();
		}
		
		CommentEntity commentEntity = new CommentEntity();
		commentEntity.setContent(content);
		commentEntity.setOpenid(openid);
		commentEntity.setKind_id(kindid);
		commentEntity.setType(CommentType.getByName(imgType));
		boolean insert = service.insert(commentEntity);
		ResMessage resMessage = null;
		if(insert){
			resMessage = Message.SuccessMessage("success!");
		}else {
			 resMessage = Message.FailMessage(ReasonCode.Conflict,"fail!");
		}
		//发送元宝
		GoldFactory.doGetGold(openid, GoldReason.COMMENT, 2,resMessage);
		return resMessage.toJsonString();
	}
	
	
	@RequestMapping(value="/update",produces="application/json;charset=utf-8")
	@ResponseBody
	public String update(HttpServletRequest request,HttpServletResponse response){
		
		String openid = WeiXinSession.getOpenid(request);
		String imgType = request.getParameter("imgType");
		String id = request.getParameter("id");
		String content = request.getParameter("content");
		if(openid==null){
			return Message.FailMessage(ReasonCode.ParamPreCheck, "sessionID 错误！").toJsonString();
		}
		if(id==null || !StringUtils.isNumber(id) ){
			return Message.FailMessage(ReasonCode.ParamPreCheck, "id 错误！").toJsonString();
		}
		
		
		CommentEntity commentEntity = new CommentEntity();
		commentEntity.setContent(content);
		commentEntity.setOpenid(openid);
		commentEntity.setId(Integer.parseInt(id));
		commentEntity.setType(CommentType.getByName(imgType));
		boolean update = service.update(commentEntity);
		//结果返回
		ResMessage resMessage = null;
		if(update){
			resMessage = Message.SuccessMessage("success!");
		}else {
			 resMessage = Message.FailMessage(ReasonCode.Conflict,"fail!");
		}
		return resMessage.toJsonString();

	}
	
	
	@RequestMapping(value="/get",produces="application/json;charset=utf-8")
	@ResponseBody
	public String get(HttpServletRequest request,HttpServletResponse response){
		String openid = WeiXinSession.getOpenid(request);
		String imgType = request.getParameter("imgType");
		String kindid = request.getParameter("kindid");
		if(openid==null){
			return Message.FailMessage(ReasonCode.ParamPreCheck, "sessionID 错误！").toJsonString();
		}
		//获取所有评论
		CommentEntity commentEntity = new CommentEntity();
		commentEntity.setKind_id(kindid);
		commentEntity.setType(CommentType.getByName(imgType));
		List<CommentEntity> dbComment = service.getList(commentEntity);
		//获取所有点赞
		UserLikeListEntity entity = new UserLikeListEntity();
		entity.setOpenid(openid);
		entity.setComment_type(CommentType.getByName(imgType).getIndex());
		entity.setKind_id(Integer.parseInt(kindid));
		UserLikeListEntity userLikeList = userService.getUserLikeList(entity);
		Set<Integer> likeSet = LikeModule.getLikeSet(userLikeList);
		//过滤
		for (CommentEntity row : dbComment) {
			if(row.getOpenid()!=null && row.getOpenid().equals(openid)){
				row.setIsMe(1);
			}
			if(likeSet.contains(row.getId())){
				row.setIsLiked(1);
			}	
			row.setOpenid(null);
		}
		
		ResMessage resMessage = Message.SuccessMessage(dbComment);
		return resMessage.toJsonString();

	}
	
	@Transactional(value="iTransactionManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@RequestMapping(value="/like",produces="application/json;charset=utf-8")
	@ResponseBody
	public String like(HttpServletRequest request,HttpServletResponse response){
		
		String openid = WeiXinSession.getOpenid(request);
		String imgType = request.getParameter("imgType");
		String id = request.getParameter("id");
		String kindid = request.getParameter("kindid");
		if(openid==null){
			return Message.FailMessage(ReasonCode.ParamPreCheck, "sessionID 错误！").toJsonString();
		}
		if(id == null || kindid == null){
			return Message.FailMessage(ReasonCode.ParamPreCheck, "id 错误！").toJsonString();
		}

		//验证个人like表
		UserLikeListEntity entity = new UserLikeListEntity();
		entity.setOpenid(openid);
		entity.setComment_type(CommentType.getByName(imgType).getIndex());
		entity.setKind_id(Integer.parseInt(kindid));
		UserLikeListEntity userLikeList = userService.getUserLikeList(entity);
		//排查已存在
		if(userLikeList != null && userLikeList.haveLiked(id) ){
			return Message.FailMessage(ReasonCode.Conflict,"已点赞!").toJsonString();
		}
		//验证id是否存在
		CommentEntity commentEntity = new CommentEntity();
		commentEntity.setId(Integer.parseInt(id));
		commentEntity.setType(CommentType.getByName(imgType));
		//是否存在这个id
		CommentEntity dbComment = service.get(commentEntity);
		if(dbComment==null){
			return Message.FailMessage(ReasonCode.ParamValue,"ID不存在").toJsonString();
		}
		
		//事务操作
		//增加like表数据
		if(userLikeList == null){
			entity.setComment_ids(id);
			userService.insertUserLikeList(entity);
		}else {
			String ids = userLikeList.getComment_ids()+","+id;
			entity.setComment_ids(ids);
			userService.updateUserLikeList(entity);
		}
		
		//增加comment表score
		commentEntity.setScore(1);
		commentEntity.setOpenid(dbComment.getOpenid());
		boolean update = service.update(commentEntity);
		
		//判断是否成为最高点赞评论
		KindFactory kindFactory = new KindFactory(KindType.getByName(imgType), kindService);
		kindFactory.setData(Integer.parseInt(kindid));
		KindEntity kindEntity = kindFactory.getKindEntity();
		if(kindEntity.getComment_score() == null || kindEntity.getComment_score() < dbComment.getScore()+1){
			kindFactory.updateComment(dbComment.getId(),dbComment.getScore()+1);
		}

		//结果返回
		ResMessage resMessage = null;
		if(update){
			resMessage = Message.SuccessMessage("success!");
		}else {
			 resMessage = Message.FailMessage(ReasonCode.Conflict,"fail!");
		}
		//发送元宝
		GoldFactory.doGetGold(openid, GoldReason.LIKE, 1,resMessage);
		//被动增加
		GoldFactory.doGetGold(dbComment.getOpenid(), GoldReason.BELIKE, 1,resMessage);
		return resMessage.toJsonString();

	}
	
	
	
	
}
