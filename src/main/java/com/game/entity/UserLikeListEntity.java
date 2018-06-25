package com.game.entity;


public class UserLikeListEntity {
	private String openid;
	private int comment_type;
	private int kind_id;
	private String comment_ids;
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public int getComment_type() {
		return comment_type;
	}
	public void setComment_type(int comment_type) {
		this.comment_type = comment_type;
	}
	public int getKind_id() {
		return kind_id;
	}
	public void setKind_id(int kind_id) {
		this.kind_id = kind_id;
	}
	public String getComment_ids() {
		return comment_ids;
	}
	public void setComment_ids(String comment_ids) {
		this.comment_ids = comment_ids;
	}
	
	public boolean haveLiked(String comment_id){
		String[] split = comment_ids.split(",");
		for (String string : split) {
			if(string.equals(comment_id)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "UserLikeListEntity [openid=" + openid + ", comment_type=" + comment_type + ", kind_id=" + kind_id
				+ ", comment_ids=" + comment_ids + "]";
	}
	
}
