package com.game.common.aop;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.game.common.defined.CommentType;
import com.game.result.Message;
import com.game.result.consts.ReasonCode;

@Component  
@Aspect  
public class DefaultAop {
	
	@Pointcut("execution(public * com.game.controller.CommentController.*(..))")
	private void commentController(){}
	
	@Before(value="commentController()")
	public void beforeMethod(JoinPoint joinPoint){
		
		
	}
	
	@Around(value="commentController()")
	public Object around(ProceedingJoinPoint pjp) throws  Throwable{
		Object[] args = pjp.getArgs();
		if(args.length>1){
			HttpServletRequest request = (HttpServletRequest)args[0];
			String imgType = request.getParameter("imgType");
			if(imgType==null || !CommentType.contains(imgType)){
				String jsonString = Message.FailMessage(ReasonCode.ParamPreCheck, "imgType error !").toJsonString();
				//return 错误
				return jsonString;
			}
		}
		//执行主程序
		Object proceed = pjp.proceed();
		return proceed;
	}
	
	@After(value="commentController()")
	public void afterMethod(JoinPoint joinPoint){
		
	}
	
}
