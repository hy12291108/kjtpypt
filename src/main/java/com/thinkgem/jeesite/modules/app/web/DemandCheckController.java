package com.thinkgem.jeesite.modules.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.modules.app.bean.AppResult;
import com.thinkgem.jeesite.modules.app.service.DemandCheckService;

import net.sf.json.JSONObject;

/**
 * APP 需求审核
 *
 * 赵凯浩
 * 2017年8月25日 下午2:17:02
 */
@Controller
@RequestMapping(value = "/app/demandcheck")
public class DemandCheckController {
		
	@Autowired
	private DemandCheckService demandCheckService;
	
	
	/**
	 * 审核
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"demcheck"})
	@ResponseBody
	public AppResult demcheck(@RequestBody JSONObject jsonObj){
		return demandCheckService.demcheck(jsonObj);
	}
	
	/**
	 * 查询所有需求审核记录
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"findDemandCheckAll"})
	@ResponseBody
	public AppResult findDemandCheckAll(@RequestBody JSONObject jsonObj) {
		return demandCheckService.findDemandCheckAll(jsonObj);
	}
	
	/**
	 * 通过id查询需求审核对象
	 * @param id
	 * @param officeId
	 * @return
	 */
	@RequestMapping(value = {"findById"})
	@ResponseBody
	public AppResult findById(String id, String officeId) {
		return demandCheckService.findById(id, officeId);
	}
	
}
