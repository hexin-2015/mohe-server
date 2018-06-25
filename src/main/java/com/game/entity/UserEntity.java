package com.game.entity;

import java.io.Serializable;

public class UserEntity implements Serializable {
	
	/**
	 * 序列ID
	 */
	private static final long serialVersionUID = 1L;
	
	private String sessionKey;
	private String openid;
	private Integer gender;
	private String nickName;
	private String avatarUrl;
	private Integer gold;
	private float beauty;
	
	public Integer getGold() {
		return gold;
	}
	public void setGold(Integer gold) {
		this.gold = gold;
	}
	public float getBeauty() {
		return beauty;
	}
	public void setBeauty(float beauty) {
		this.beauty = beauty;
	}
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
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
	@Override
	public String toString() {
		return "UserEntity [sessionKey=" + sessionKey + ", openid=" + openid + ", gender=" + gender + ", nickName="
				+ nickName + ", avatarUrl=" + avatarUrl + ", gold=" + gold + ", beauty=" + beauty + "]";
	}
	
}
