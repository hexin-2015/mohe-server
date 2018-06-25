package com.game.bean;

public class ScoreBean {
	
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
	public String toString() {
		return "ScoreBean [name=" + name + ", score=" + score + "]";
	}
	
}
