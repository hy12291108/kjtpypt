package com.thinkgem.jeesite.modules.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.modules.app.bean.AppResult;
import com.thinkgem.jeesite.modules.app.service.AgreementService;

import net.sf.json.JSONObject;

/**
 * APP 服务协议
 *
 * 赵凯浩
 * 2017年9月20日 上午9:54:39
 */
@Controller
@RequestMapping(value = "/app/agreement")
public class AgreementController {
	
	@Autowired
	private AgreementService agreementService;
	
	
	/**
	 * 查询所有协议
	 * 权限内的所有服务协议
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"findAll"})
	@ResponseBody
	public AppResult findAll(@RequestBody JSONObject jsonObj){
		return agreementService.findAll(jsonObj);
	}
	
	/**
	 * 服务协议审核
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"check"})
	@ResponseBody
	public AppResult check(@RequestBody JSONObject jsonObj){
		return agreementService.check(jsonObj);
	}
	
	/**
	 *  通过id查询需求审核对象
	 * @param id
	 * @return
	 */
	@RequestMapping(value = {"findVilProtocolById"})
	@ResponseBody
	public AppResult findVilProtocolById(String id) {
		return agreementService.findVilProtocolById(id);
	}
	
}
