/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.common.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 发送请求工具类
 * @author ThinkGem
 * @version 2013-5-29
 */
public class HttpRequestUtils {
	
	public static final String URLSTR = "http://domain:8085/admin.php?c=exapi&a="; //
	public static final String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML,like Gecko) Chrome/29.0.1 547.66 Safari/537.36";
	public static final String DEF_CHARSE= "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 3000;
	public static final int DEF_READ_TIMEOUT = 3000;
	public static final String USER = "B9C508B5E011A64A5B073669820C6924"; //八百里管理员账号
	public static final String PASSWORD = "123456";
	
	
	/**
	 * 发送请求，返回字符串
	 * @throws IOException 
	 * */
	public static String openConn(Map<String,String> map,String requestUrl,String method) throws IOException{
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String rs = null;
		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			conn = (HttpURLConnection)url.openConnection();
			
			if("POST".equals(method)){
				conn.setRequestMethod(method);
				conn.setDoOutput(true);
			}else{
				conn.setRequestMethod("GET");
			}
			
			conn.setRequestProperty("User-agent", userAgent);
			conn.setUseCaches(false);
			conn.setConnectTimeout(DEF_CONN_TIMEOUT);
			conn.setReadTimeout(DEF_READ_TIMEOUT);
			conn.setInstanceFollowRedirects(false);
			conn.connect();
		
			//设置参数
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			if(map != null){
				try {
					out.writeBytes(urlencode(map));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			InputStream is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is,DEF_CHARSE));
			String strReader = null;
			while((strReader = reader.readLine()) != null){
				sb.append(strReader);
			}
			rs = sb.toString();
			return rs;
		}catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(reader != null){
				reader.close();
			}
			if(conn != null){
				conn.disconnect();
			}
		}
		return rs;
	}
	
	//将map型转为参数型
	private static String urlencode(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		for(Map.Entry i:paramMap.entrySet()){
			try {
				sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"", "UTF-8")).append("&");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
}
