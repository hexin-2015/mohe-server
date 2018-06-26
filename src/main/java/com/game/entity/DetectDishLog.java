package com.game.entity;

import java.util.ArrayList;

import com.game.bean.AiResultBean;
import com.game.bean.DishBean;
import com.game.bean.DishResultBean;
import com.game.bean.ImageResultBean;
import com.game.bean.ScoreBean;

/**
 * @author admin
 *
 */
public class DetectDishLog extends DetectLog {
	private String name;
	private float score;
	private int calorie;

	public int getCalorie() {
		return calorie;
	}

	public void setCalorie(int calorie) {
		this.calorie = calorie;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	@Override
	public void setData(AiResultBean data) {
		
		DishResultBean resData = (DishResultBean)data;
		ArrayList<DishBean> result = resData.getResult();
		if(result==null ||result.isEmpty()){
			return;
		}
		setScore(result.get(0).getProbability());
		setName(result.get(0).getName().trim());
		setCalorie(result.get(0).getCalorie());
		
	}
	
	@Override
	public KindEntity changeToKindEntity(){
		KindEntity entity = super.changeToKindEntity();
		entity.setName(name);
		entity.setDecectScore(Math.round(score*10000f));
		return entity;
	}
	
	@Override
	public String toString() {
		return "DetectPlantLog [id=" + id + ", openid=" + openid + ", name=" + name + ", scope=" + score + ", String="
				+ path + ", time=" + time +"]";
	}

}
