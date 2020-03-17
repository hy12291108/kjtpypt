package com.thinkgem.jeesite.common.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import com.thinkgem.jeesite.modules.eightMile.entity.EightLogin;
import com.thinkgem.jeesite.modules.eightMile.entity.KjtpyVideoInfo;

import net.sf.json.JSONObject;

public class VideoUtils {

	public static final String URLSTR = "http://800li.net:8085/admin.php?c=exapi&a="; //
	public static final String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML,like Gecko) Chrome/29.0.1 547.66 Safari/537.36";
	public static final String DEF_CHARSE= "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 3000;
	public static final int DEF_READ_TIMEOUT = 3000;
	public static final String USER = "B9C508B5E011A64A5B073669820C6924 "; //八百里超级管理员账号
	public static final String PASSWORD = "123456";
	public static final String INLINE_AUTH = "1";
	private static EightLogin eightLogin;
	private static KjtpyVideoInfo kjtpyVideoInfo;
	
	/*
	 * 发送登录请求,返回登录状态
	 * */
	private static EightLogin createUpload() throws IOException {
		//设置参数
		Map<String,String> param = new HashMap<String,String>();
		param.put("user",USER);
		param.put("password",PASSWORD);
		param.put("inline_auth", INLINE_AUTH);
		
		//设置连接地址
		String urlStr = null;
		urlStr = URLSTR + "createUpload";
		//获取接口返回值
		String rs = HttpRequestUtils.openConn(param,urlStr,"POST");
		JSONObject objJson = JSONObject.fromObject(rs);
		String code = objJson.getString("code");
		String msg = objJson.getString("msg");
		String streamName = objJson.getString("stream_name");
		eightLogin = new EightLogin(code,msg,streamName);
		return eightLogin;
	}
	
	/*
	 * 发送请求,返回点播信息
	 * */
	public static KjtpyVideoInfo vodInfo(String streamName) throws IOException {
		//设置参数
		Map<String,String> param = new HashMap<String,String>();
		param.put("user",USER);
		param.put("password",PASSWORD);
		param.put("inline_auth", INLINE_AUTH);
		param.put("stream_name", streamName);
		
		//设置连接地址
		String urlStr = null;
		urlStr = URLSTR + "vodInfo";
		//获取接口返回值
		String rs = HttpRequestUtils.openConn(param,urlStr,"POST");
		JSONObject objJson = JSONObject.fromObject(rs);
		System.out.println(objJson);
		
		String html_code = objJson.getString("html_code");
		String p2ps_html_code = objJson.getString("p2ps_html_code");
		String rtmp_html_code = objJson.getString("rtmp_html_code");
		String hls_html_code = objJson.getString("hls_html_code");
		String p2ps_swf_url = objJson.getString("p2ps_swf_url");
		String rtmp_swf_url = objJson.getString("rtmp_swf_url");
		String hls_url = objJson.getString("hls_url");
		String virtual_path = objJson.getString("virtual_path");
		String local_path = objJson.getString("local_path");
		String stream_name = objJson.getString("stream_name");
		String title = objJson.getString("title");
		String msgthumbnail = objJson.getString("thumbnail");
		String play_password = objJson.getString("play_password");
//		String publish_password = objJson.getString("publish_password");
		String max_concurrents = objJson.getString("max_concurrents");
		String tag = objJson.getString("tag");
		String disabled = objJson.getString("disabled");
		String available = objJson.getString("available");
		String creation_time = objJson.getString("creation_time");
		String width = objJson.getString("width");
		String height = objJson.getString("height");
		String duration = objJson.getString("duration");
		String framerate = objJson.getString("framerate");
		
		String bitrate = objJson.getString("bitrate");
		String standard = objJson.getString("standard");
		String description = objJson.getString("description");
		String category = objJson.getString("category");
		String cid = objJson.getString("cid");
		String cms_id = objJson.getString("cms_id");
		kjtpyVideoInfo = new KjtpyVideoInfo(html_code,p2ps_html_code,rtmp_html_code,hls_html_code,p2ps_swf_url,rtmp_swf_url,hls_url,virtual_path,
				local_path,stream_name,title,msgthumbnail,play_password,max_concurrents,tag,disabled,
				available,creation_time,width,height,duration,framerate,bitrate,standard,description,category,cid,cms_id);
		
		return kjtpyVideoInfo;
	}
	
	
	
	
	//展示给外部调用
	public static EightLogin getEightLogin() throws IOException{
//		if(eightLogin != null && EightLogin.canUser(eightLogin)){
//			return eightLogin;
//		}else{
			return createUpload();
//		}
	}

	public static String getVideoUploadLaunchURLAction(String streamName,String videoTitle) throws IOException {
		//设置参数
		Map<String,String> param = new HashMap<String,String>();
		param.put("user",USER);
		param.put("password",PASSWORD);
		param.put("inline_auth", INLINE_AUTH);
		param.put("stream_name", streamName);
		param.put("title", videoTitle);
		
		//设置连接地址
		String urlStr = null;
		urlStr = URLSTR + "getVideoUploadLaunchURL";
		String rs = HttpRequestUtils.openConn(param,urlStr,"POST");
		JSONObject objJson = JSONObject.fromObject(rs);
		return objJson.getString("url");
	}
	
	/**
	 * 上传文件
	 * @throws IOException 
	 * */
	
//	public static boolean uploadFile(CommonsMultipartFile file) throws IOException {
//		//设置参数
//		Map<String,String> param = new HashMap<String,String>();
//		param.put("cms_id","");
//		param.put("stream_name","武鹏飞的测试数据	");
//		param.put("user",USER);
//		param.put("password",PASSWORD);
//	
//		String urlStr = null;
//		urlStr = URLSTR + "login" ;
//		
//		return false;
//	}
	
	
	
}
