package com.game.result.bean;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ResGold {
	private Integer getGold ;

	public Integer getGetGold() {
		return getGold;
	}

	public void setGetGold(Integer getGold) {
		this.getGold = getGold;
	}

	@Override
	public String toString() {
		return "ResGold [getGold=" + getGold + "]";
	}
	
	

}
