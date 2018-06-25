package com.game.dao;

import java.util.List;

import com.game.entity.DetectFaceLog;
import com.game.entity.DetectLog;
import com.game.entity.UserEntity;


public interface DetectLogDao {

	public int insertDetectPlantLog(DetectLog log);
	
	public int insertDetectAnimalLog(DetectLog log);
	
	public int insertDetectCarLog(DetectLog log);
	
	public int insertDetectDishLog(DetectLog log);
	
	public int insertDetectFaceLog(DetectLog log);

	public void updateFaceLogBeRank(Integer logid);
	
	public List<String> getImagePlant(List<Integer> ids);
	
	public List<String> getImageAnimal(List<Integer> ids);
	
	public List<String> getImageCar(List<Integer> ids);
	
	public List<String> getImageDish(List<Integer> ids);

	public DetectFaceLog getFaceLog(int id);
	
	
}
