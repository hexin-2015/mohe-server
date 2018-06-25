package com.game.entity;

import com.game.common.defined.KindType;

public class KindEntity {
	
	private Integer id;
	private String name;
	private Integer image_min_score;
	private Integer image_num;
	private String image_ids;
	private String comment_id;
	private Integer comment_score;
	private KindType kindType;
	private Integer decectScore;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getImage_min_score() {
		return image_min_score;
	}
	public void setImage_min_score(Integer image_min_score) {
		this.image_min_score = image_min_score;
	}
	public Integer getImage_num() {
		return image_num;
	}
	public void setImage_num(Integer image_num) {
		this.image_num = image_num;
	}
	public String getImage_ids() {
		return image_ids;
	}
	public void setImage_ids(String image_ids) {
		this.image_ids = image_ids;
	}
	public String getComment_id() {
		return comment_id;
	}
	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}
	public Integer getComment_score() {
		return comment_score;
	}
	public void setComment_score(Integer comment_score) {
		this.comment_score = comment_score;
	}
	
	public Integer getDecectScore() {
		return decectScore;
	}
	public void setDecectScore(Integer decectScore) {
		this.decectScore = decectScore;
	}

	public KindType getKindType() {
		return kindType;
	}
	public void setKindType(KindType kindType) {
		this.kindType = kindType;
	}
	@Override
	public String toString() {
		return "KindEntity [id=" + id + ", name=" + name + ", image_min_score=" + image_min_score + ", image_num="
				+ image_num + ", image_ids=" + image_ids + ", comment_id=" + comment_id + ", comment_score="
				+ comment_score + ", kindType=" + kindType + ", decectScore=" + decectScore + "]";
	}
	

}
