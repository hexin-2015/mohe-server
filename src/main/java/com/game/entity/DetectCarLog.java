package com.game.entity;

import java.util.ArrayList;

import com.game.bean.AiResultBean;
import com.game.bean.ImageResultBean;
import com.game.bean.ScoreBean;

/**
 * @author admin
 *
 */
public class DetectCarLog extends DetectLog {
	private String name;
	private float score;

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
		
		ImageResultBean resData = (ImageResultBean)data;
		ArrayList<ScoreBean> result = resData.getResult();
		if(result==null ||result.isEmpty()){
			return;
		}
		setScore(result.get(0).getScore());
		setName(result.get(0).getName().trim());
		
	}
	
	@Override
	public String toString() {
		return "DetectPlantLog [id=" + id + ", openid=" + openid + ", name=" + name + ", scope=" + score + ", String="
				+ path + ", time=" + time +"]";
	}

}
