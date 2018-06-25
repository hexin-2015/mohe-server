package com.game.bean;

import java.util.ArrayList;

public class DishResultBean extends AiResultBean {
	protected Integer result_num;
	
	protected ArrayList<DishBean> result = new ArrayList<DishBean>();
	
	public ArrayList<DishBean> getResult() {
		return result;
	}
	
	public void setResult(ArrayList<DishBean> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "ImageResultBean [result=" + result.toString() + ", log_id=" + log_id + "]";
	}

}
