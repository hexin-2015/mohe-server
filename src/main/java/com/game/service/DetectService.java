package com.game.service;

import com.game.entity.DetectFaceLog;
import com.game.entity.DetectLog;

public interface DetectService {
	
	int insert(DetectLog log);
	
	DetectFaceLog getFaceLog(int id);
	
	void updateFaceLogBeRank(int logid);
	
}
