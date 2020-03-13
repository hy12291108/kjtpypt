//package com.thinkgem.jeesite.modules.eightMile.web;
//
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.lang.StringUtils;
////import org.poslink.core.orm.Page;
////import org.poslink.core.orm.PropertyFilter;
////import org.poslink.core.utils.encode.JsonBinder;
////import org.poslink.core.web.grid.EasyUiGrid;
////import org.poslink.core.web.grid.EasyUiGridModel;
////import org.poslink.extend.web.bean.EasyUiResult;
////import org.poslink.tools.http.HttpRequestProxy;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//
//import com.thinkgem.jeesite.modules.eightMile.entity.Media800liBean;
//import com.thinkgem.jeesite.modules.eightMile.util.AESUtil;
//
///**
// * 课程课件管理Action
// */
//@Controller
//@RequestMapping(value = "/course/courseunit")
//public class CourseUnitController{
//	
//	protected Logger logger = LoggerFactory.getLogger(getClass());
//	
//	@Autowired
//	private CourseService courseService;
//
//	private Url hrp = new HttpRequestProxy();
//	private static JsonBinder binder = JsonBinder.buildNonDefaultBinder();
//
//	// 流媒体
//	public static String IP = "http://127.0.0.1:8083"; // 定义服务器的播放的IP地址
//	public static String IPL = "http://127.0.0.1:8085"; // 流媒体服务器端IP地址
//	public static String MEDIA_KEY = "nJfCBj6ghJuyt78oejkniA=="; // 流媒体管理员密码	
//	private static String encoding = "UTF-8"; // 编码
//	private static String address = "http://127.0.0.1:8085/admin.php?c=exapi&a=";
//	private static String auth = "&inline_auth=1&user=88EB11476A49DDEA0C538A7290B77F6E &password="; 
//	
//	static {
//		try {
//			auth += AESUtil.decrypt("Tjd6jj8987jklitNbH3lWw==", MEDIA_KEY);
//		} catch (Exception e) {
//			System.out.println("流媒体密码解密失败");
//		}
//	}
//
//	/**
//	 * 上传完之后点击这个请求返回文件访问路径
//	 */
//	@RequestMapping(value="/upload",method=RequestMethod.POST)
//	@ResponseBody
//	public EasyUiResult upload(String streamName) throws Exception {
//		EasyUiResult result = new EasyUiResult();
//
//		String url3 = address + "vodInfo&stream_name=" + streamName + "&standard=HD" + auth;
//		String str3 = hrp.doRequest(url3, null, null, encoding);
//		Media800liBean bean = binder.fromJson(str3, Media800liBean.class);
//		if (bean.getCode().equals("ok")) {
//			result.setMsg(bean.getP2ps_swf_url().substring(22, bean.getP2ps_swf_url().length() - 16));
//		}
//		return result;
//	}
//
//	/**
//	 * 打开800li客户端-准备上传
//	 */
//	@RequestMapping(value="/href",method=RequestMethod.POST)
//	@ResponseBody
//	public EasyUiResult href() throws Exception{
//		EasyUiResult result = new EasyUiResult();
//		String url1 = address + "createUpload" + auth;
//		String str1 = hrp.doRequest(url1, null, null, encoding);
//		Media800liBean bean = binder.fromJson(str1, Media800liBean.class);
//		result.setObj(bean.getStream_name());
//		String url2 = address + "getVideoUploadLaunchURL&stream_name=" + bean.getStream_name() + "&standard=HD" + auth;
//		String str2 = hrp.doRequest(url2, null, null, encoding);
//		bean = binder.fromJson(str2, Media800liBean.class);
//		
//		result.setMsg(bean.getUrl());
//		return result;
//	}
//	
//	/**
//	 * 删除课件
//	 
//	@RequestMapping(value="/delete",method=RequestMethod.POST)
//	@ResponseBody
//	public EasyUiResult delete(String unitId){
//		EasyUiResult result = new EasyUiResult();
//		String msg = "删除课程课件成功！";
//		String unitName = null;
//		try {
//			CourseUnit courseUnit = courseService.getCourseUnit(unitId);
//			unitName = courseUnit.getUnitName();
//			String unitSmId = courseUnit.getUnitSmId();
//			String url = address + "removeVod&stream_name=" + unitSmId + auth;
//			URLConnection connection = new URL(url).openConnection();
//			HttpURLConnection httpConn = (HttpURLConnection) connection;
//			int code = httpConn.getResponseCode();
//			if (code == 200) {
//				courseService.deleteCourseUnit(unitId);
//				result.setSuccess(true);
//			} else {
//				result.setSuccess(false);
//			}
//		} catch (Exception ex) {
//			logger.error(ex.getMessage(), ex);
//			msg = "删除课程课件 " + unitName + " 失败！";
//			result.setSuccess(false);
//		}
//		logger.info(msg);
//		result.setMsg(msg);
//		return result;
//	}
//	*/
//	
//	
//}