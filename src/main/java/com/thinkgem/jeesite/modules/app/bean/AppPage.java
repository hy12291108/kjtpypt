package com.thinkgem.jeesite.modules.app.bean;

import net.sf.json.JSONObject;

/**
 * APP 分页
 *
 * 赵凯浩
 * 2017年9月18日 下午5:47:40
 */
public class AppPage {

	private int pageNo; // 当前页
	private JSONObject data; // 当前数据包
	
	public AppPage(JSONObject jsonObj) {
		try {			
			this.pageNo = jsonObj.getInt("pageNo");
		} catch (Exception e) {
			this.pageNo = 1;
		}
		try {			
			this.data = jsonObj.getJSONObject("data");
		} catch (Exception e) {
			this.data = JSONObject.fromObject("{}");
		}
	}

	public int getPageNo() {
		return pageNo;
	}

	public JSONObject getData() {
		return data;
	}

}
