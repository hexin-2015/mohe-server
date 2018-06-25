package com.game.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.game.common.util.StringUtil;
import com.game.dao.DetectLogDao;
import com.game.dao.UserDao;
import com.game.entity.DetectAnimalLog;
import com.game.entity.DetectFaceLog;
import com.game.entity.DetectLog;
import com.game.entity.DetectPlantLog;
import com.game.entity.UserEntity;
import com.game.service.DetectService;
import com.game.service.UserService;

@Service
public class DetectPlantServiceImpl implements DetectService{
	
	@Autowired 
	DetectLogDao dao;

	@Override
	public int insert(DetectLog log) {
		
		String methodName = "insert"+log.getClass().getSimpleName();
		int resid = 0;
		try {
			Method method = dao.getClass().getMethod(methodName, DetectLog.class);
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
	public DetectFaceLog getFaceLog(int id) {
		
		return dao.getFaceLog(id);
	}

	@Override
	public void updateFaceLogBeRank(int logid) {
		
		dao.updateFaceLogBeRank(logid);
		
	}

	
	
	

}
