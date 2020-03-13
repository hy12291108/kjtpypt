package com.thinkgem.jeesite.modules.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.modules.app.bean.AppResult;
import com.thinkgem.jeesite.modules.app.service.JobevaluationService;

import net.sf.json.JSONObject;

/**
 * APP 工作评价
 *
 * 赵凯浩
 * 2017年10月27日 下午1:57:35
 */
@Controller
@RequestMapping(value = "/app/jobevaluation")
public class JobevaluationController {

	@Autowired
	private JobevaluationService jobevaluationService;
	
	
	/**
	 * 日志评价
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"evaluate"})
	@ResponseBody
	public AppResult evaluate(@RequestBody JSONObject jsonObj){
		return jobevaluationService.evaluate(jsonObj);
	}
	
	/**
	 * 查询当前管理员下的特派员的日志
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"findAll"})
	@ResponseBody
	public AppResult findAll(@RequestBody JSONObject jsonObj){
		return jobevaluationService.findAll(jsonObj);
	}
	
	/**
	 * 通过日志id查询日志对象
	 * @param id
	 * @return
	 */
	@RequestMapping(value = {"findById"})
	@ResponseBody
	public AppResult findById(String id){
		return jobevaluationService.findById(id);
	}
	
}
