package com.game.dao;

import com.game.entity.KindEntity;
import com.game.entity.show.ShowKindInfo;

public interface KindDao {
	
	public KindEntity getPlant(KindEntity log);
	
	public int insertPlant(KindEntity log);
	
	public int updatePlant(KindEntity log);
	
	public KindEntity getAnimal(KindEntity entity);
	
	public int insertAnimal(KindEntity entity);
	
	public int updateAnimal(KindEntity entity);
	
	public KindEntity getCar(KindEntity entity);
	
	public int insertCar(KindEntity entity);
	
	public int updateCar(KindEntity entity);
	
	public KindEntity getDish(KindEntity entity);
	
	public int insertDish(KindEntity entity);
	
	public int updateDish(KindEntity entity);
	

}
