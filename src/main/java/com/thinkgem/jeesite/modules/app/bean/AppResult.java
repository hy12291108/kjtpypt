package com.thinkgem.jeesite.modules.app.bean;


/**
 * APP 数据返回对象
 *
 * 赵凯浩
 * 2017年8月21日 上午8:32:35
 */
public class AppResult {
	
	private boolean success = true; // （必须）返回 true或false
	private String msg;             // 返回的文本消息内容
	private Object obj;             // 返回Object(Map、list等数据)对象
	private Object subObj;          // 补充参数（上面三个不够用时）
	private Object thirdObj;        // 补充参数（上面三个不够用时）
	private Object fourObj;         // 补充参数（上面三个不够用时）
	
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Object getObj() {
		return obj;
	}
	
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	public Object getSubObj() {
		return subObj;
	}
	
	public void setSubObj(Object subObj) {
		this.subObj = subObj;
	}

	public Object getThirdObj() {
		return thirdObj;
	}

	public void setThirdObj(Object thirdObj) {
		this.thirdObj = thirdObj;
	}

	public Object getFourObj() {
		return fourObj;
	}

	public void setFourObj(Object fourObj) {
		this.fourObj = fourObj;
	}
	
}
