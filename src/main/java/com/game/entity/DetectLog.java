package com.game.entity;

import com.game.bean.AiResultBean;

public abstract class DetectLog {
	
	protected int id;
	protected String openid;
	protected String path;
	protected int time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	public abstract void setData(AiResultBean data);
	
	public KindEntity changeToKindEntity(){
		KindEntity entity = new KindEntity();
		return entity;
	}
	

}
