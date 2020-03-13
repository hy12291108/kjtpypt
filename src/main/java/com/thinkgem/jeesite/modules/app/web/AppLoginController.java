package com.thinkgem.jeesite.modules.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.modules.app.bean.AppResult;
import com.thinkgem.jeesite.modules.app.service.AppLoginService;

import net.sf.json.JSONObject;

/**
 * APP 登录
 *
 * 赵凯浩
 * 2017年9月11日 下午2:21:01
 */
@Controller
@RequestMapping(value = "/app")
public class AppLoginController {
	
	@Autowired
	private AppLoginService loginService;
	
	
	/**
	 * 用户登录
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = "login")
	@ResponseBody
	public AppResult login(@RequestBody JSONObject jsonObj){
		return loginService.login(jsonObj);
	}

}
