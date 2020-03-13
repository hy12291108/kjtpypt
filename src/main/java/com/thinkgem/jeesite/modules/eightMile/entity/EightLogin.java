package com.thinkgem.jeesite.modules.eightMile.entity;


/*
 * 八百里登录实体类
 * */
public class EightLogin {
	//
	private String code;
	private String msg;
	private String streamName;
	private long canUse;

	public EightLogin(String code,String msg){
		this.code = code;
		this.msg = msg;
		this.canUse = System.currentTimeMillis() + 5*60*1000;
	}
	
	public EightLogin(String code, String msg, String streamName) {
		this.code = code;
		this.msg = msg;
		this.setStreamName(streamName);
		this.canUse = System.currentTimeMillis() + 5*60*1000;
	}

	//判断当前登录状态
	public static boolean canUser(EightLogin e){
		return System.currentTimeMillis() > e.getCanUse();
		
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}


	public long getCanUse() {
		return canUse;
	}


	public void setCanUser(long canUse) {
		this.canUse = canUse;
	}

	public String getStreamName() {
		return streamName;
	}

	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}
	
	
}
