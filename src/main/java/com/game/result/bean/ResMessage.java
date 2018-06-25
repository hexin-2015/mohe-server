package com.game.result.bean;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ResMessage {
	
	private String status;
	private Integer code;
	@JsonInclude(Include.NON_NULL)
	private String message;
	@JsonInclude(Include.NON_NULL)
	private Object data;
	@JsonInclude(Include.NON_NULL)
	private Object ext;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	public Object getExt() {
		return ext;
	}
	public void setExt(Object ext) {
		this.ext = ext;
	}
	
	
	@Override
	public String toString() {
		return "ResMessage [status=" + status + ", code=" + code + ", message=" + message + ", data=" + data + ", ext="
				+ ext + "]";
	}
	public String toJsonString() {
		return JSON.toJSONString(this);
	}

}
