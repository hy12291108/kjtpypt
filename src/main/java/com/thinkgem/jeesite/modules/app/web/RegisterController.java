package com.thinkgem.jeesite.modules.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.modules.app.bean.AppResult;
import com.thinkgem.jeesite.modules.app.service.RegisterService;

import net.sf.json.JSONObject;

/**
 * APP 注册
 *
 * 赵凯浩
 * 2017年8月22日 上午9:15:50
 */
@Controller
@RequestMapping(value = "/app/register")
public class RegisterController {
	
	@Autowired
	private RegisterService registerService;

	
	/**
	 * 注册（自然人、法人、需求单位）
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = "" ,method=RequestMethod.POST)
	@ResponseBody
	public AppResult register(@RequestBody JSONObject jsonObj) {
		return registerService.register(jsonObj);
	}
	
	/**
	 * 获取职称、性别、学历，以及专业类别和专业
	 * @return
	 */
	@RequestMapping(value = "getDictList")
	@ResponseBody
	public AppResult getDictList() {
		return registerService.getDictList();
	}
	
	/**
	 * 游客注册
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = "visitor" ,method=RequestMethod.POST)
	@ResponseBody
	public AppResult visitor(@RequestBody JSONObject jsonObj) {
		return registerService.visitor(jsonObj);
	}
	
}
