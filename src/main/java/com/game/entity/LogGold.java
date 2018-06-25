package com.game.entity;


public class LogGold {
	
	private String openid;
	
	private int reason;
	
	private int oldNum;
	
	private int newNum;
	
	private int change;
	
	private int time;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}

	public int getOldNum() {
		return oldNum;
	}

	public void setOldNum(int oldNum) {
		this.oldNum = oldNum;
	}

	public int getNewNum() {
		return newNum;
	}

	public void setNewNum(int newNum) {
		this.newNum = newNum;
	}

	public int getChange() {
		return change;
	}

	public void setChange(int change) {
		this.change = change;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "LogGold [openid=" + openid + ", reason=" + reason + ", oldNum=" + oldNum + ", newNum=" + newNum
				+ ", change=" + change + ", time=" + time + "]";
	}
	
	


}
