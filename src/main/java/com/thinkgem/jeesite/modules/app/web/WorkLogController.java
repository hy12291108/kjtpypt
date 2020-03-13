package com.thinkgem.jeesite.modules.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.modules.app.bean.AppResult;
import com.thinkgem.jeesite.modules.app.service.WorkLogService;

import net.sf.json.JSONObject;

/**
 * APP 日志查询
 *
 * 赵凯浩
 * 2017年8月29日 下午3:46:05
 */
@Controller
@RequestMapping(value = "/app/worklog")
public class WorkLogController {
	
	@Autowired
	private WorkLogService workLogService;
	
	
	/**
	 * 日志审批
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"check"})
	@ResponseBody
	public AppResult check(@RequestBody JSONObject jsonObj){
		return workLogService.check(jsonObj);
	}
	
	/**
	 * 查询当前管理员下的特派员的日志
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"findAll"})
	@ResponseBody
	public AppResult findAll(@RequestBody JSONObject jsonObj){
		return workLogService.findAll(jsonObj);
	}
	
	/**
	 * 通过日志id查询日志对象
	 * @param id
	 * @return
	 */
	@RequestMapping(value = {"findById"})
	@ResponseBody
	public AppResult findById(String id){
		return workLogService.findById(id);
	}
	
}
