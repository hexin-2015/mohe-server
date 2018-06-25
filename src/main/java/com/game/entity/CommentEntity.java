package com.game.entity;

import com.game.common.defined.CommentType;

public class CommentEntity {
	private int id;
	private String openid;
	private CommentType type;
	private String kind_id;
	private String content;
	private int score;	
	private String nickName;
	private String avatarUrl;
	private int isMe;
	private int isLiked;
	private String createtime;
	

	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
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
	
	public CommentType getType() {
		return type;
	}
	public void setType(CommentType type) {
		this.type = type;
	}
	public String getKind_id() {
		return kind_id;
	}
	public void setKind_id(String kind_id) {
		this.kind_id = kind_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
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
	
	public int getIsMe() {
		return isMe;
	}
	public void setIsMe(int isMe) {
		this.isMe = isMe;
	}
	public int getIsLiked() {
		return isLiked;
	}
	public void setIsLiked(int isLiked) {
		this.isLiked = isLiked;
	}
	@Override
	public String toString() {
		return "CommentEntity [id=" + id + ", openid=" + openid + ", type=" + type + ", kind_id=" + kind_id
				+ ", content=" + content + ", score=" + score + ", nickName=" + nickName + ", avatarUrl=" + avatarUrl
				+ ", isMe=" + isMe + ", isLiked=" + isLiked + ", createtime=" + createtime + "]";
	}
	
	

}
