package com.game.dao;

import java.util.List;

import com.game.entity.CommentEntity;

public interface CommentDao {
	
	public int insertPlant(CommentEntity comment);
		
	public int updatePlant(CommentEntity comment);
	
	public CommentEntity getPlant(CommentEntity comment);
	
	public List<CommentEntity> getListPlant(CommentEntity comment);
	
	public CommentEntity getUserInfoPlant(Integer id);
	
	public int insertAnimal(CommentEntity comment);
	public int updateAnimal(CommentEntity comment);	
	public CommentEntity getAnimal(CommentEntity comment);
	public List<CommentEntity> getListAnimal(CommentEntity comment);
	public CommentEntity getUserInfoAnimal(Integer id);
	
	public int insertCar(CommentEntity comment);
	public int updateCar(CommentEntity comment);	
	public CommentEntity getCar(CommentEntity comment);
	public List<CommentEntity> getListCar(CommentEntity comment);
	public CommentEntity getUserInfoCar(Integer id);
	
	public int insertDish(CommentEntity comment);
	public int updateDish(CommentEntity comment);	
	public CommentEntity getDish(CommentEntity comment);
	public List<CommentEntity> getListDish(CommentEntity comment);
	public CommentEntity getUserInfoDish(Integer id);

}
