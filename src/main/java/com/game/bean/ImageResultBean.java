package com.game.bean;

import java.util.ArrayList;

public class ImageResultBean extends AiResultBean {
	
	protected ArrayList<ScoreBean> result = new ArrayList<ScoreBean>();
	
	public ArrayList<ScoreBean> getResult() {
		return result;
	}
	
	public void setResult(ArrayList<ScoreBean> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "ImageResultBean [result=" + result.toString() + ", log_id=" + log_id + "]";
	}

}
