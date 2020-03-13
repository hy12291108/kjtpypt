package com.thinkgem.jeesite.modules.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.modules.app.bean.AppResult;
import com.thinkgem.jeesite.modules.app.service.AppVillageService;

import net.sf.json.JSONObject;

/**
 * APP 贫困村服务协议
 *
 * 赵凯浩
 * 2017年10月13日 下午3:39:41
 */
@Controller
@RequestMapping(value = "/app/village")
public class AppVillageController {

	@Autowired
	private AppVillageService appVillageService;
	
	/**
	 * 查询所有团队协议
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"findAllByTeam"})
	@ResponseBody
	public AppResult findAllByTeam(@RequestBody JSONObject jsonObj){
		return appVillageService.findAllByTeam(jsonObj);
	}
	
	/**
	 * 查询所有团队协议
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"findAllByMember"})
	@ResponseBody
	public AppResult findAllByMember(@RequestBody JSONObject jsonObj){
		return appVillageService.findAllByMember(jsonObj);
	}
	
	/**
	 * 通过id查询对象
	 * @param id
	 * @return
	 */
	@RequestMapping(value = {"getMemberById"})
	@ResponseBody
	public AppResult getMemberById(String id, String titleName){
		return appVillageService.getMemberById(id, titleName);
	}
	
	/**
	 * 审核
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"checkVillage"})
	@ResponseBody
	public AppResult checkVillage(@RequestBody JSONObject jsonObj){
		return appVillageService.checkVillage(jsonObj);
	}
	
}
