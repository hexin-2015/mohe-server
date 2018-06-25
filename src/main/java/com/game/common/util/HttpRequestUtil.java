﻿package com.game.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestUtil {
	
	public static final String REQUEST_MENTHOD_POST = "POST";  
	public static final String REQUEST_MENTHOD_GET = "GET";
	
	
	//private static final Logger log = Logger.getLogger(HttpRequestUtil.class);
	 /**  
     * 发起http请求并获取结果  
     *   
     * @param requestUrl 请求地址  
     * @param requestMethod 请求方式（GET、POST）  
     * @param outputStr 提交的数据  
     * @return String  
     */    
    public static String request(String requestUrl, String requestMethod, String outputStr) {    
        StringBuffer buffer = new StringBuffer();  
        InputStream inputStream=null;  
        try {  
            URL url = new URL(requestUrl);  
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();    
            httpUrlConn.setDoOutput(true);    
            httpUrlConn.setDoInput(true);    
            httpUrlConn.setUseCaches(false);  
            // 设置请求方式（GET/POST）    
            httpUrlConn.setRequestMethod(requestMethod);    
            if ("POST".equalsIgnoreCase(requestMethod))    
                httpUrlConn.connect();    
    
            // 当有数据需要提交时    
            if (null != outputStr) {    
                OutputStream outputStream = httpUrlConn.getOutputStream();    
                // 注意编码格式，防止中文乱码    
                outputStream.write(outputStr.getBytes("UTF-8"));    
                outputStream.close();    
            }  
            //将返回的输入流转换成字符串    
            inputStream = httpUrlConn.getInputStream();    
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");    
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);    
    
            String str = null;    
            while ((str = bufferedReader.readLine()) != null) {    
                buffer.append(str);    
            }    
            bufferedReader.close();    
            inputStreamReader.close();    
            // 释放资源    
            inputStream.close();    
            inputStream = null;    
            httpUrlConn.disconnect();    
           
        } catch (ConnectException ce) {    
              ce.printStackTrace();  
              System.out.println("Weixin server connection timed out");  
        } catch (Exception e) {    
               e.printStackTrace();  
               System.out.println("http request error:{}");  
        }finally{  
            try {  
                if(inputStream!=null){  
                    inputStream.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }   
        return buffer.toString();    
    }    

	public static void main(String[] args){
		
	}
}

