package com.game.entity.show;

import java.util.List;

import com.game.entity.CommentEntity;
import com.game.entity.DetectLog;

public class ShowKindInfo {
	private DetectLog detectLog;
	private int kind_id;
	private CommentEntity kindInfo;
	private List<String> images;
	
	
	public DetectLog getDetectLog() {
		return detectLog;
	}


	public void setDetectLog(DetectLog detectLog) {
		this.detectLog = detectLog;
	}


	public int getKind_id() {
		return kind_id;
	}


	public void setKind_id(int kind_id) {
		this.kind_id = kind_id;
	}


	public CommentEntity getKindInfo() {
		return kindInfo;
	}


	public void setKindInfo(CommentEntity kindInfo) {
		this.kindInfo = kindInfo;
	}



	public List<String> getImages() {
		return images;
	}


	public void setImages(List<String> images) {
		this.images = images;
	}


	@Override
	public String toString() {
		return "ShowKindInfo [detectLog=" + detectLog + ", kind_id=" + kind_id + ", kindInfo=" + kindInfo + ", images="
				+ images + "]";
	}
	
}
