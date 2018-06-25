package com.game.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class SpringContextUtil implements ApplicationContextAware{
	
	 // Spring应用上下文环境  
    private static ApplicationContext applicationContext;  

    /** 
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境 
     *  
     * @param applicationContext 
     */  
    public void setApplicationContext(ApplicationContext applicationContext) {  
        SpringContextUtil.applicationContext = applicationContext;  
    }  
  
    /** 
     * @return ApplicationContext 
     */  
    public static ApplicationContext getApplicationContext() {  
    	checkApplicationContext();
        return applicationContext;  
    }  
  
    /** 
     * 获取对象 
     * 这里重写了bean方法，起主要作用 
     * @param name 
     * @return Object 一个以所给名字注册的bean的实例 
     * @throws BeansException 
     */  
    public static Object getBean(String name) throws BeansException {  
    	checkApplicationContext();
        return applicationContext.getBean(name);  
    }  
    
    /**
     * 返回类
     * @param clazz
     * @return
     */
    public static <T> T getBean(Class<T> clazz){
		checkApplicationContext();
		return  applicationContext.getBean(clazz);
    }
    
    private static void checkApplicationContext() {  
    	if (applicationContext == null) {  
    		throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextUtil");  
    	}  
	}  
}  

