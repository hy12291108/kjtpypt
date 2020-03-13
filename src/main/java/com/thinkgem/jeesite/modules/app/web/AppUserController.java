package com.thinkgem.jeesite.modules.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.modules.app.bean.AppResult;
import com.thinkgem.jeesite.modules.app.service.AppUserService;

import net.sf.json.JSONObject;

/**
 * APP 特派员/需求单位管理
 *
 * 赵凯浩
 * 2017年8月24日 下午1:15:27
 */
@Controller
@RequestMapping(value = "/app/user")
public class AppUserController {
	
	@Autowired
	private AppUserService appUserService;

	
	/**
	 * 查询当前管理员下的所有特派员列表
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"findTpyAll"})
	@ResponseBody
	public AppResult findTpyAll(@RequestBody JSONObject jsonObj) {
		return appUserService.findTpyAll(jsonObj);
	}
	
	/**
	 * 查询当前管理员下的所有需求单位列表
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"findXqdwAll"})
	@ResponseBody
	public AppResult findXqdwAll(@RequestBody JSONObject jsonObj) {
		return appUserService.findXqdwAll(jsonObj);
	}

	/**
	 * 审核
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"checkUser"})
	@ResponseBody
	public AppResult checkUser(@RequestBody JSONObject jsonObj){
		return appUserService.checkUser(jsonObj);
	}
	
	/**
	 * 根据特派员ID查询指定特派员/需求单位
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = {"findUserById"})
	@ResponseBody
	public AppResult findUserById(String userId) {
		return appUserService.findUserById(userId);
	}
	
}
