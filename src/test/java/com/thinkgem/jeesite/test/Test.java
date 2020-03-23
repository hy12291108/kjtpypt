package com.thinkgem.jeesite.test;

import com.thinkgem.jeesite.modules.sys.service.SystemService;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {
	
	
	
	
	static SystemService systemService = new SystemService();
	public static void main(String[] args) throws Exception {
		BufferedReader reader = null;
		HttpURLConnection conn = null;
		StringBuffer sb = new StringBuffer();
		URL url = new URL("http://800li.net:8085/admin.php?c=exapi&a=createUpload&inline_auth=1&user=B9C508B5E011A64A5B073669820C6924&password=123456");
		conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setInstanceFollowRedirects(false);
		conn.connect();
		//设置参数
		InputStream is = conn.getInputStream();
		reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		String strReader = null;
		while((strReader = reader.readLine()) != null){
			sb.append(strReader);
		}
		System.out.println("createUpload结果：" + sb);
		
		
		JSONObject o = JSONObject.fromObject(sb.toString());
		String st = o.getString("stream_name");
		System.out.println(st);
		url = new URL("http://800li.net:8085/admin.php?c=exapi&a=getVideoUploadLaunchURL&stream_name=" + st + "&inline_auth=1&user=B9C508B5E011A64A5B073669820C6924&password=123456");
		conn = (HttpURLConnection)url.openConnection();
		conn.connect();
		is = conn.getInputStream();
		reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		strReader = null;
		StringBuffer sb2 = new StringBuffer();
		while((strReader = reader.readLine()) != null){
			sb2.append(strReader);
		}
		System.out.println("getVideo结果：" +sb2);
		
		o = JSONObject.fromObject(sb2.toString());
		
		
		String st1 = o.getString("url");
		System.out.println("获取到的url：" + st1);
		
		
		
		url = new URL("http://800li.net:8085/admin.php?c=exapi&a=vodInfo&stream_name=" + st + "&inline_auth=1&user=B9C508B5E011A64A5B073669820C6924&password=123456");
		System.out.println(url);
		conn = (HttpURLConnection)url.openConnection();
		conn.connect();
		is = conn.getInputStream();
		reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		strReader = null;
		StringBuffer sb3 = new StringBuffer();
		while((strReader = reader.readLine()) != null){
			sb3.append(strReader);
		}
		System.out.println("获取到的video返回信息：" + sb3.toString());
		
		
		
		
		
	}
}
