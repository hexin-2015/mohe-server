package com.game.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.common.defined.CommentType;
import com.game.common.util.StringUtil;
import com.game.dao.CommentDao;
import com.game.dao.DetectLogDao;
import com.game.dao.KindDao;
import com.game.dao.UserDao;
import com.game.entity.CommentEntity;
import com.game.entity.DetectLog;
import com.game.entity.KindEntity;
import com.game.entity.show.ShowKindInfo;
import com.game.service.KindService;
import com.mysql.fabric.xmlrpc.base.Array;


@Service
public class KindServiceImpl implements KindService{

	@Autowired KindDao dao;
	@Autowired DetectLogDao detectDao;
	@Autowired UserDao userDao;
	@Autowired CommentDao commentDao;
	
	
	@Override
	public KindEntity get(KindEntity log) {
		
		String typeName = log.getKindType().getName();
		String fixMethodName = StringUtil.toUpperFristChar(typeName);
		String methodName = "get"+fixMethodName;
		KindEntity res = null;
		try {
			Method method = dao.getClass().getMethod(methodName, KindEntity.class);
			res = (KindEntity)method.invoke(dao, log);
		} catch (NoSuchMethodException e) {		
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	



	@Override
	public int insert(KindEntity log) {
		String typeName = log.getKindType().getName();
		String fixMethodName = StringUtil.toUpperFristChar(typeName);
		String methodName = "insert"+fixMethodName;
		log.setName(log.getName().trim());
		int resid = 0;
		try {
			Method method = dao.getClass().getMethod(methodName, KindEntity.class);
			resid = (int)method.invoke(dao, log);
		} catch (NoSuchMethodException e) {		
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}	
		return resid;	
	}




	@Override
	public ShowKindInfo getShowKindInfo(KindEntity entity) {
		ShowKindInfo res = new ShowKindInfo();
		res.setKind_id(entity.getId());
		
		//评论消息
		if(entity.getComment_id()!=null && !entity.getComment_id().isEmpty()){
			CommentEntity userInfoPlant = getComment(entity);
			userInfoPlant.setOpenid(null);
			res.setKindInfo(userInfoPlant);
		}
		
		//图片列表
		String[] split = entity.getImage_ids().split(",");
		if(split.length > 0){
			List<String> images = getImages(entity);
			res.setImages(images);	
		}
		
		return res;
		
		
	}
	
	private CommentEntity getComment(KindEntity entity){
		
		String typeName = entity.getKindType().getName();
		String fixMethodName = StringUtil.toUpperFristChar(typeName);
		String methodName = "getUserInfo"+fixMethodName;
		int commentid = Integer.parseInt(entity.getComment_id());
		CommentEntity res = null;
		try {
			Method method = commentDao.getClass().getMethod(methodName, Integer.class);
			res = (CommentEntity)method.invoke(commentDao, commentid);
		} catch (NoSuchMethodException e) {		
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	private List<String> getImages(KindEntity entity){
		String[] split = entity.getImage_ids().split(",");
		ArrayList<Integer> ints = new ArrayList<Integer>();
		for (String str : split) {
			ints.add(Integer.parseInt(str));
		}
		//获取方法名
		String typeName = entity.getKindType().getName();
		String fixMethodName = StringUtil.toUpperFristChar(typeName);
		String methodName = "getImage"+fixMethodName;
		List<String> res = null;
		try {
			Method method = detectDao.getClass().getMethod(methodName, List.class);
			res = (List<String>)method.invoke(detectDao, ints);
		} catch (NoSuchMethodException e) {		
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return res;
	}




	@Override
	public void update(KindEntity log) {
		
		String typeName = log.getKindType().getName();
		String fixMethodName = StringUtil.toUpperFristChar(typeName);
		String methodName = "update"+fixMethodName;
		int resid = 0;
		try {
			Method method = dao.getClass().getMethod(methodName, KindEntity.class);
			resid = (int)method.invoke(dao, log);
		} catch (NoSuchMethodException e) {		
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}	
		
	}
	
	

}
