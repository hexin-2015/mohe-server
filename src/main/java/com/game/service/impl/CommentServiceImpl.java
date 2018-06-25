package com.game.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.game.common.defined.CommentType;
import com.game.common.util.StringUtil;
import com.game.dao.CommentDao;
import com.game.dao.DetectLogDao;
import com.game.dao.UserDao;
import com.game.entity.CommentEntity;
import com.game.entity.DetectAnimalLog;
import com.game.entity.DetectLog;
import com.game.entity.DetectPlantLog;
import com.game.entity.UserEntity;
import com.game.service.CommentService;
import com.game.service.DetectService;
import com.game.service.UserService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired 
	CommentDao dao;

	@Override
	public boolean insert(CommentEntity comment) {
		
		CommentType type = comment.getType();
		String typeName = type.getName();
		String methodName = "insert"+StringUtil.toUpperFristChar(typeName);
		int invokeRes = 0;
		try {
			Method method = dao.getClass().getMethod(methodName, CommentEntity.class);
			invokeRes = (int)method.invoke(dao, comment);
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
		
		return invokeRes == 0 ? false : true ;
		
		
	}
	
	
	@Override
	public boolean update(CommentEntity comment) {
		
		CommentType type = comment.getType();
		String typeName = type.getName();
		String methodName = "update"+StringUtil.toUpperFristChar(typeName);
		int res = 0;
		try {
			Method method = dao.getClass().getMethod(methodName, CommentEntity.class);
			res = (int)method.invoke(dao, comment);
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
		return res==0 ? false : true;
	}


	@Override
	public CommentEntity get(CommentEntity comment) {
		
		CommentType type = comment.getType();
		String typeName = type.getName();
		String methodName = "get"+StringUtil.toUpperFristChar(typeName);
		CommentEntity res = null;
		try {
			Method method = dao.getClass().getMethod(methodName, CommentEntity.class);
			res = (CommentEntity)method.invoke(dao, comment);
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
	@Override
	public List<CommentEntity> getList(CommentEntity comment) {
		
		CommentType type = comment.getType();
		String typeName = type.getName();
		String methodName = "getList"+StringUtil.toUpperFristChar(typeName);
		List<CommentEntity> res = null;
		try {
			Method method = dao.getClass().getMethod(methodName, CommentEntity.class);
			res = (List<CommentEntity>)method.invoke(dao, comment);
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
	
	

}
