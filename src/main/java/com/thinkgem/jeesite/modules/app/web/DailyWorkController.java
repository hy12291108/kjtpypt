package com.thinkgem.jeesite.modules.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.modules.app.bean.AppResult;
import com.thinkgem.jeesite.modules.app.service.DailyWorkService;

import net.sf.json.JSONObject;

/**
 * APP 工作日志填报
 *
 * 赵凯浩
 * 2017年8月29日 上午9:35:57
 */
@Controller
@RequestMapping(value = "/app/dailywork")
public class DailyWorkController {
	
	@Autowired
	private DailyWorkService dailyWorkService;
	
	
	/**
	 * 通过特派员id查询服务对象列表
	 * @param tpyid
	 * @return
	 */
	@RequestMapping(value = {"findServiceObjByTpyid"})
	@ResponseBody
	public AppResult findServiceObjByTpyid(String tpyid){
		return dailyWorkService.findServiceObjByTpyid(tpyid);
	}
	
	/**
	 * 新增日志填报
	 * 贫困村
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"save"})
	@ResponseBody
	public AppResult save(@RequestBody JSONObject jsonObj){
		return dailyWorkService.save(jsonObj);
	}
	
	/**
	 * 新增日志填报
	 * 服务对象
	 * @param id
	 * @return
	 */
	@RequestMapping(value = {"submit"})
	@ResponseBody
	public AppResult submit(String id){
		return dailyWorkService.submit(id);
	}
	
	/**
	 * 保存日志填报（修改）
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"update"})
	@ResponseBody
	public AppResult update(@RequestBody JSONObject jsonObj){
		return dailyWorkService.update(jsonObj);
	}
	
	/**
	 * 通过特派员id查询指定特派员的日志填报记录（列表）
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"findAllByRecTpyId"})
	@ResponseBody
	public AppResult findAllByRecTpyId(@RequestBody JSONObject jsonObj){
		return dailyWorkService.findAllByRecTpyId(jsonObj);
	}
	
	/**
	 * 通过日志id查询日志对象（详情）
	 * @param id
	 * @return
	 */
	@RequestMapping(value = {"findById"})
	@ResponseBody
	public AppResult findById(String id){
		return dailyWorkService.findById(id);
	}

}
