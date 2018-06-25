package com.game.common.module;

import java.io.File;
import java.sql.Savepoint;
import java.util.HashMap;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.game.common.defined.HostDefined;
import com.game.entity.UserEntity;
import com.game.service.UserService;


public class ImageModule {
	
	public static String getShowUrl(String localPath){
		if(localPath == null || localPath.length()<8){
			return "";
		}
		
		String separator = String.valueOf(File.separatorChar);
		String imageHost = HostDefined.IMAGE_HOST;
		int lastIndexOf = localPath.lastIndexOf(separator);
		String filepath = localPath.substring(lastIndexOf-8);
		String reString = imageHost+separator+filepath;
		return reString;
	}
	
	
	
	
	
	
	
	
	
	
}
