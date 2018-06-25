package com.game.common;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.context.support.StaticApplicationContext;

import com.game.bean.AiResultBean;

public class ExecutorDoAi {
	
	private static ConcurrentLinkedQueue<UploadFile> queue = new ConcurrentLinkedQueue<UploadFile>();
	
	private static ConcurrentHashMap<String, AiResultBean> resultMap = new ConcurrentHashMap<String, AiResultBean>(); 
	
	private static Lock lock= new ReentrantLock();
	
	public static void add(UploadFile item){
		queue.add(item);
	}
	
	public static AiResultBean getResult(String key) {
		lock.lock();
		while (true) {
			AiResultBean aiResultBean = resultMap.get(key);
			if(aiResultBean != null){
				lock.unlock();
				return aiResultBean;
			}
			
		}
		
	}
	
	
	
	

}
