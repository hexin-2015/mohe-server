package com.game.bean;

public class DishBean {
	
	private String name;
	
	private float probability;
	
	private boolean has_calorie;
	
	private Integer calorie;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getProbability() {
		return probability;
	}

	public void setProbability(float probability) {
		this.probability = probability;
	}

	public boolean isHas_calorie() {
		return has_calorie;
	}

	public void setHas_calorie(boolean has_calorie) {
		this.has_calorie = has_calorie;
	}

	public Integer getCalorie() {
		return calorie;
	}

	public void setCalorie(Integer calorie) {
		this.calorie = calorie;
	}

	@Override
	public String toString() {
		return "DishBean [name=" + name + ", probability=" + probability + ", has_calorie=" + has_calorie + ", calorie="
				+ calorie + "]";
	}
	
	
	
}
