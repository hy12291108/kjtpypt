package com.thinkgem.jeesite.modules.app.bean;

/**
 * APP 基本配置参数
 *
 * 赵凯浩
 * 2017年9月13日 下午4:57:09
 */
public class AppConfigure {
	
	// 系统异常
	public final static String MSG_Exception = "系统错误，请联系管理员";
	
	// DEBUG日志异常提示信息，显示APP标识
	public final static String APP_ERR = "==========APP==========";
	
	// 分页每页数据大小
	public final static int pageSize = 10;
	
	/** 图片存放路径********************************************/
	// 注册（自然人、法人）推荐表图片
	public final static String tjImage = "/tjImage";
	public final static String tjImage_position = "uploadImg"; // 例：E:\\uploadImg
	
	// 专家咨询图片（缩略图）
	public final static String zjzxImage = "/zjzxImage";
	public final static String zjzxImage_position = "uploadImg\\zjzx";
	
	// 日志填报图片
	public final static String drImage = "/tjImage";
	public final static String drImage_position = "uploadImg\\tpyDailyRecordImg";
	/** 图片存放路径********************************************/
	
}
