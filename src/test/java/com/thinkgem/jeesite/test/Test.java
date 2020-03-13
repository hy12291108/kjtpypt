package com.thinkgem.jeesite.test;

import com.thinkgem.jeesite.modules.sys.entity.TpyInfo;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

public class Test {
	static SystemService systemService = new SystemService();
	public static void main(String[] args) {
		
		TpyInfo user = systemService.getTpyInfo("0ff6986078f942fe8168a4a2e7eec33d");
		System.out.println(user.getId());
	}
}
