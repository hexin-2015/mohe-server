package com.game.service;

import com.game.entity.KindEntity;
import com.game.entity.show.ShowKindInfo;

public interface KindService {
	
	public int insert(KindEntity log);
	
	public KindEntity get(KindEntity log);
	
	public void update(KindEntity log);
	
	public ShowKindInfo getShowKindInfo(KindEntity entity);
	
	
}
