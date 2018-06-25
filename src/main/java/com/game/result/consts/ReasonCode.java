package com.game.result.consts;

public class ReasonCode {
	
	/**
	 * 成功
	 */
	public static final Integer Success = 200;
	
	/**
	 * token错误-未授权
	 */
	public static final Integer Unauthorized = 401 ;
	
	/**
	 * 信息不存在
	 */
	public static final Integer NotFound = 404 ;
	
	/**
	 * 信息已存在-冲突
	 */
	public static final Integer Conflict = 409 ;
	
	/**
	 * 必填参数填写不完整,或值类型不匹配
	 */
	public static final Integer ParamPreCheck = 412;
	
	/**
	 * 参数值错误
	 */
	public static final Integer ParamValue = 417;
	
	/**
	 * 其他错误
	 */
	public static final Integer Other = 418;
		
	
	
}
