package com.game.service;

import java.util.List;

import com.game.entity.CommentEntity;
import com.game.entity.DetectLog;

public interface CommentService {
	
	boolean insert(CommentEntity comment);
	
	boolean update(CommentEntity comment);
	
	CommentEntity get(CommentEntity comment);
	
	List<CommentEntity> getList(CommentEntity comment);
	
}
