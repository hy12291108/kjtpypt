/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.eightMile.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.eightMile.entity.KjtpyVideoInfo;
import com.thinkgem.jeesite.modules.eightMile.service.KjtpyVideoInfoService;

/**
 * 视频文件Controller
 * @author 武鹏飞
 * @version 2019-11-29
 */
@Controller
@RequestMapping(value = "${adminPath}/eightmile/kjtpyVideoInfo")
public class KjtpyVideoInfoController extends BaseController {

	@Autowired
	private KjtpyVideoInfoService kjtpyVideoInfoService;
	
	@ModelAttribute
	public KjtpyVideoInfo get(@RequestParam(required=false) String id) {
		KjtpyVideoInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = kjtpyVideoInfoService.get(id);
		}
		if (entity == null){
			entity = new KjtpyVideoInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("eightmile:kjtpyVideoInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(KjtpyVideoInfo kjtpyVideoInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<KjtpyVideoInfo> page = kjtpyVideoInfoService.findPage(new Page<KjtpyVideoInfo>(request, response), kjtpyVideoInfo); 
		model.addAttribute("page", page);
		return "modules/eightmile/kjtpyVideoInfoList";
	}

	@RequiresPermissions("eightmile:kjtpyVideoInfo:view")
	@RequestMapping(value = "form")
	public String form(KjtpyVideoInfo kjtpyVideoInfo, Model model) {
		model.addAttribute("kjtpyVideoInfo", kjtpyVideoInfo);
		return "modules/eightmile/kjtpyVideoInfoForm";
	}

	@RequiresPermissions("eightmile:kjtpyVideoInfo:edit")
	@RequestMapping(value = "save")
	public String save(KjtpyVideoInfo kjtpyVideoInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, kjtpyVideoInfo)){
			return form(kjtpyVideoInfo, model);
		}
		kjtpyVideoInfoService.save(kjtpyVideoInfo);
		addMessage(redirectAttributes, "保存视频文件成功");
		return "redirect:"+Global.getAdminPath()+"/eightmile/kjtpyVideoInfo/?repage";
	}
	
	@RequiresPermissions("eightmile:kjtpyVideoInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(KjtpyVideoInfo kjtpyVideoInfo, RedirectAttributes redirectAttributes) {
		kjtpyVideoInfoService.delete(kjtpyVideoInfo);
		addMessage(redirectAttributes, "删除视频文件成功");
		return "redirect:"+Global.getAdminPath()+"/eightmile/kjtpyVideoInfo/?repage";
	}

}