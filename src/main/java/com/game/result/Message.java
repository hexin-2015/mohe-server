package com.game.result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.result.bean.ResMessage;
import com.game.result.consts.ReasonCode;

public class Message {
	
	protected static final String SUCCESS = "success";
	protected static final String FAIL = "fail";
	
	protected static Logger log = LoggerFactory.getLogger(Message.class);
	/**
	 * 返回成功信息
	 * @param data
	 * @return
	 */
	public static ResMessage SuccessMessage(Object data){
		
		ResMessage res = new ResMessage();
		res.setCode(ReasonCode.Success);
		res.setStatus(SUCCESS);
		res.setData(data);	
		return res;
	}
	
	/**
	 * 返回失败信息
	 * @param data
	 * @return
	 */	
	public static ResMessage FailMessage(Integer code,String message) {
		ResMessage res = new ResMessage();
		res.setCode(code);
		res.setStatus(FAIL);
		res.setMessage(message);
		return res;
	}
	
	/**
	 * 返回成功信息
	 * @param data
	 * @return
	 */
	public static ResMessage SuccessMessage(){
		
		ResMessage res = new ResMessage();
		res.setCode(ReasonCode.Success);
		res.setStatus(SUCCESS);
		return res;
	}
	
	
	
}
