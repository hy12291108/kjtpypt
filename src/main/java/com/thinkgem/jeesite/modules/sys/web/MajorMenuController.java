/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.MajorMenu;
import com.thinkgem.jeesite.modules.sys.entity.Menu;
import com.thinkgem.jeesite.modules.sys.service.MajorMenuService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 菜单Controller
 * @author ThinkGem
 * @version 2013-3-23
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/majorMenu")
public class MajorMenuController extends BaseController {

	@Autowired
	private MajorMenuService majorMenuService;
	
	@ModelAttribute("majorMenu")
	public MajorMenu get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return majorMenuService.getMajorMenu(id);
		}else{
			return new MajorMenu();
		}
	}

	@RequiresPermissions("sys:menu:view")
	@RequestMapping(value = {"list", ""})
	public String list(Model model) {
		List<MajorMenu> majorList = majorMenuService.findAllMajorMenu();
        model.addAttribute("majorList",majorList);
		return "modules/sys/majorMenuList";
	}
	
	@RequestMapping(value = {"registerList", ""})
	@ResponseBody
	public Map<String,Object> registerList() {
		Map<String,Object> map = new HashMap<String,Object>();
		List<MajorMenu> majorList = majorMenuService.findAllMajorMenu();
		map.put("majorList", majorList);
		return map;
	}
	
	
	
	
	@RequiresPermissions("sys:menu:view")
	@RequestMapping(value = {"selectSecond", ""})
	public String listSecond(Model model,MajorMenu majorMenu) {
		List<MajorMenu> majorList = majorMenuService.findMajorMenuSecond(majorMenu);
        model.addAttribute("majorList",majorList);
		return "modules/sys/majorMenuSecondList";
	}
	
	@ResponseBody
	@RequestMapping(value = {"registerSecondList", ""})
	public Map<String,Object> registerSecondList(MajorMenu majorMenu) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<MajorMenu> majorList = majorMenuService.findMajorMenuSecond(majorMenu);
		map.put("majorList", majorList);
		return map;
	}
	
	
	
	
	
	
	@RequestMapping(value = {"add1", ""})
	public String add1() {
		return "modules/sys/majorMenuAdd";
	}

	@RequestMapping(value = {"add2", ""})
	public String add2(MajorMenu majorMenu,Model model) {
		model.addAttribute("majorMenuName",majorMenu.getName());
		model.addAttribute("majorMenuId",majorMenu.getId());
		return "modules/sys/majorMenuSecond";
	}
	
	
	@RequiresPermissions("sys:menu:edit")
	@RequestMapping(value = "save")
	public String save(MajorMenu menu, Model model, RedirectAttributes redirectAttributes) {
		if(!UserUtils.getUser().isAdmin()){
			addMessage(redirectAttributes, "越权操作，只有超级管理员才能添加或修改数据！");
			return "redirect:" + adminPath + "/sys/role/?repage";
		}
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/menu/majorMenuList";
		}
		majorMenuService.saveMajorMenu(menu);
		addMessage(redirectAttributes, "保存菜单'" + menu.getName() + "'成功");
		return "redirect:" + adminPath + "/sys/majorMenu/list";
	}
	
	@RequiresPermissions("sys:menu:edit")
	@RequestMapping(value = "delete")
	public String delete(MajorMenu menu, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/menu/";
		}
		majorMenuService.deleteMenu(menu);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/sys/majorMenu/list";
	}



	
	
	
}
