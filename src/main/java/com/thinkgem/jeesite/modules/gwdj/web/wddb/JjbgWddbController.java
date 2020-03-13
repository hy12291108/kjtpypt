/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.gwdj.web.wddb;

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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.gwdj.entity.JjbgGwdj;
import com.thinkgem.jeesite.modules.gwdj.service.JjbgGwdjService;

/**
 * 待办发文Controller
 * @author ｚｙｔ
 * @version 2017-06-26
 */
@Controller
@RequestMapping(value = "${adminPath}/dbfw/jjbgWddb")
public class JjbgWddbController extends BaseController {

	@Autowired
	private JjbgGwdjService jjbgGwdjService;
	
	@ModelAttribute
	public JjbgGwdj get(@RequestParam(required=false) String id) {
		JjbgGwdj entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = jjbgGwdjService.get(id);
		}
		if (entity == null){
			entity = new JjbgGwdj();
		}
		return entity;
	}
	
	@RequiresPermissions("dbfw:jjbgWddb:view")
	@RequestMapping(value = {"list", ""})
	public String list(JjbgGwdj jjbgGwdj, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<JjbgGwdj> page = jjbgGwdjService.findPage(new Page<JjbgGwdj>(request, response), jjbgGwdj); 
		model.addAttribute("page", page);
		return "modules/dbfw/jjbgWddbList";
	}

	@RequiresPermissions("dbfw:jjbgWddb:view")
	@RequestMapping(value = "form")
	public String form(JjbgGwdj jjbgGwdj, Model model) {
		model.addAttribute("jjbgGwdj", jjbgGwdj);
		return "modules/dbfw/jjbgWddbForm";
	}

	@RequiresPermissions("dbfw:jjbgWddb:edit")
	@RequestMapping(value = "save")
	public String save(JjbgGwdj jjbgGwdj, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, jjbgGwdj)){
			return form(jjbgGwdj, model);
		}
		jjbgGwdjService.save(jjbgGwdj);
		addMessage(redirectAttributes, "保存待办发文成功");
		return "redirect:"+Global.getAdminPath()+"/dbfw/jjbgWddb/?repage";
	}
	
	@RequiresPermissions("dbfw:jjbgWddb:edit")
	@RequestMapping(value = "delete")
	public String delete(JjbgGwdj jjbgGwdj, RedirectAttributes redirectAttributes) {
		jjbgGwdjService.delete(jjbgGwdj);
		addMessage(redirectAttributes, "删除待办发文成功");
		return "redirect:"+Global.getAdminPath()+"/dbfw/jjbgWddb/?repage";
	}

}