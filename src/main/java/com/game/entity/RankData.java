package com.game.entity;

public class RankData {
	private Integer order;
	
	private String openid;
	
	private String nickName;
	
	private String avatarUrl;
	
	private String imgPath;
	
	private float beauty;
	
	private float gold;
	
	private int time;
	

	/**
	 * 查询开始时间
	 */
	private String startTime;
	
	/**
	 * 查询结束时间
	 */
	private String endTime;
	
	/**
	 * 限制条数
	 */
	private Integer limitNum;
	

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public float getBeauty() {
		return beauty;
	}

	public void setBeauty(float beauty) {
		this.beauty = beauty;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}

	public float getGold() {
		return gold;
	}

	public void setGold(float gold) {
		this.gold = gold;
	}

	@Override
	public String toString() {
		return "RankData [order=" + order + ", openid=" + openid + ", nickName=" + nickName + ", avatarUrl=" + avatarUrl
				+ ", imgPath=" + imgPath + ", beauty=" + beauty + ", gold=" + gold + ", time=" + time + ", startTime="
				+ startTime + ", endTime=" + endTime + ", limitNum=" + limitNum + "]";
	}
	
	

	
	
	
}
