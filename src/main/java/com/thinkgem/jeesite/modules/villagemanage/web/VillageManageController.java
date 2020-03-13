/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.villagemanage.web;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.villagemanage.entity.ServiceTeam;
import com.thinkgem.jeesite.modules.villagemanage.entity.TeamMember;
import com.thinkgem.jeesite.modules.villagemanage.entity.Village;
import com.thinkgem.jeesite.modules.villagemanage.service.VillageService;

/**
 * 贫困村Controller
 * @author 刘钢
 * @version 2017-8-17
 */
@Controller
@RequestMapping(value = "/a/VillageManage")
public class VillageManageController extends BaseController {
	@Autowired
	private VillageService villageService;	
	@Autowired
	private SystemService systemService;
	/*
	 * 根据ID,获取贫困村信息
	 * @author 刘钢
	 * 20170818
	 */
	@ModelAttribute
	public Village get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return villageService.getVillage(id);
		}else{
			return new Village();
		}
	}
	
	/*
	 * 添加信息
	 * @author 刘钢
	 * 20170817
	 */
	@RequestMapping("addInfo")
	public String addInfo(){
		return "modules/villageManage/addInfo";
	}
	/* 保存信息
	 * @author 刘钢
	 * 20170818
	 */
	@RequestMapping("saveInfo")
	public String saveInfo(Village village){
		villageService.saveVillage(village);
		return "redirect:" + adminPath + "/VillageManage/list";
	}
	/*
	 * 查询贫困村信息分页显示
	 * @author 刘钢
	 * 20170818
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = {"list", ""})
	public String list(Village village, HttpServletRequest request, HttpServletResponse response, Model model) {
		village.setZoneId(UserUtils.getUser().getOffice().getId());
		Page<Village> page = villageService.findVillage(new Page<Village>(request, response),village);
		model.addAttribute("page", page);
		return "modules/villageManage/villageList";
	}
	/*
	 * 贫困村详细信息
	 * @author 刘钢
	 * 20170821
	 */
	@RequestMapping("villageInfo")
	public String villageInfo(Village village,Model model){
		model.addAttribute("village", village);
		return "modules/villageManage/villageInfo";
	}
	
	/*
	 * 修改贫困村信息
	 * @author 刘钢
	 * 20170821
	 */
	@RequestMapping("updateVillageInfo")
	public String update(Village village,RedirectAttributes redirectAttributes){
		villageService.updateVillageInfo(village);
		addMessage(redirectAttributes, "修改成功");
		return "redirect:" + adminPath + "/VillageManage/list";
	}
	
	/*
	 * 删除贫困村
	 * @author 刘钢
	 * 20170821
	 */
	@RequestMapping("delete")
	public String delete(Village village,RedirectAttributes redirectAttributes){
		villageService.deleteVillageInfo(village);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:" + adminPath + "/VillageManage/list";
	}
	/*
	 * 贫困村申请组建特派团页面
	 * @author 刘钢
	 * 20170823
	 */
	@RequestMapping("villageSq")
	public String villageSq(Village village, HttpServletRequest request, HttpServletResponse response, Model model){
		village.setZoneId(UserUtils.getUser().getOffice().getId());
		Page<Village> page = villageService.findVillage(new Page<Village>(request, response),village);
		model.addAttribute("page", page);
		return "modules/villageManage/villageSq";
	}
	/*
	 * 贫困村特派团组建页面
	 * @author 刘钢
	 * 20170823
	 
	@RequestMapping("villageServiceTeam")
	public String villageServiceTeam(User user,Village village,Model model,HttpServletRequest request, HttpServletResponse response){
		Page<User> page = villageService.findUser(new Page<User>(request, response),user);
		model.addAttribute("page", page);
		model.addAttribute("village", village);
		return  "modules/villageManage/villageServiceTeam";
	}*/
	@RequestMapping("villageServiceTeam")
	public String villageServiceTeam(User user,Village village,Model model,HttpServletRequest request, HttpServletResponse response){
		List<User> page = villageService.findUserList(user);
		model.addAttribute("page", page);
		model.addAttribute("village", village);
		return  "modules/villageManage/villageServiceTeam1";
	}
	
	
	/*
	 * 贫困村特派团查询特派员备选列表
	 * @author 刘钢
	 * 20170922
	 */
	@ResponseBody
	@RequestMapping("findTpyList")
	public Map<String,Object> findTpyList(User user){
		Map<String,Object> map = new HashMap<String, Object>();
		List<User> page = villageService.findUserList(user);
		map.put("page", page);
		return  map;
	}
	
	
	
	
	
	/*
	 * 选择组队成员实现后台逻辑处理
	 * @author 刘钢
	 * 20170824
	 */
	@RequestMapping("insertBatch")
	public String insertBatch(ServiceTeam serviceTeam,HttpServletRequest request){
		//String ids[] = request.getParameterValues("userId");
		String ids[] = request.getParameterValues("memberType");
		String villageId = request.getParameter("Id");
	//	String zoneId = request.getParameter("zoneId");
	//	System.out.println("111111111111=zoneId="+zoneId);
		String typeId = request.getParameter("memberType");
		villageService.insertBatch(serviceTeam,ids,villageId,typeId);
		return "redirect:"+ adminPath + "/VillageManage/teamMemberInfo?teamId="+serviceTeam.getId();
	}
	/*
	 * 信息列表（贫困村信息、团队信息、队员信息）
	 * @author（刘钢）
	 * 20170828
	 */
	@RequestMapping("serviceInformation")
	public String serviceInformation(Village village,Model model){
		Village villageInfo =  villageService.getVillageInfo(village.getId());
		if(villageInfo.getServiceTeam()==null||villageInfo.getServiceTeam().isEmpty()){
			model.addAttribute("msg", "无服务团队信息，查无数据！");
		}
		model.addAttribute("village",villageInfo);
		return "modules/villageManage/serviceInfo";
	}
	/*
	 * 成员信息查询
	 * @author（刘钢）
	 * 20170828
	 */
	@RequestMapping("teamMemberInfo")
	public String teamMemberInfo(@RequestParam("teamId") String teamId,Model model){
		List<TeamMember> teamMember = villageService.findTeamMember(teamId);
		if(teamMember==null){
			model.addAttribute("message", "查无数据!");
		}else{
			model.addAttribute("teamMember", teamMember);
			model.addAttribute("teamId",teamId);
		}
		return "modules/villageManage/teamMemberInfo";
	}
	/*
	 * ajax 请求获取全部特派员 
	 * @author 刘刚
	 * 20170829
	 */
	@RequestMapping("ajax")
	@ResponseBody
	public Map<String,Object> findUserList(User user,HttpServletRequest request){
		String teamId= request.getParameter("teamId");
		List<User> users = villageService.findUserList(teamId,user);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user",users);
		return map;
	}
	/*
	 * ajax 请求根据要求获取特派员 
	 * @author 刘刚
	 * 20170829
	 */
	@RequestMapping("findUserOrderList")
	@ResponseBody
	public Map<String,Object> findUserOrderList(User user,HttpServletRequest request, HttpServletResponse response){
		String name = request.getParameter("name");
		String tpyMajor = request.getParameter("tpyMajor");
		String teamId = request.getParameter("teamId");
		System.out.println(name);
		System.out.println(tpyMajor);
		user.setName(name);
		user.setTpyMajor(tpyMajor);
		List<User> users = villageService.findUserList(teamId,user);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user",users);
		return map;
	}
	/*
	 * 团队成员增加
	 * @author 刘刚
	 * 20170830
	 */
	@RequestMapping("teamMemberAdd")
	public String teamMemberAdd(HttpServletRequest request,RedirectAttributes redirectAttributes){
		String userIds[] = request.getParameterValues("userId");
		System.out.println(userIds.length);
		String teamId = request.getParameter("teamId");
		System.out.println(teamId);
		String msg = villageService.teamMemberAdd(teamId,userIds);
		addMessage(redirectAttributes,msg);
		return  "redirect:"+ adminPath + "/VillageManage/teamMemberInfo?teamId="+teamId;
	}
	/* 
	 * 团队成员删除
	 * @author 刘刚
	 * 20170830
	 */
	@RequestMapping("teamMemberDel")
	public String teamMemberDel(HttpServletRequest request,RedirectAttributes redirectAttributes){
		String teamId = request.getParameter("teamId");
		String id = request.getParameter("id");
		String msg = villageService.teamMemberDel(id);
		addMessage(redirectAttributes,msg);
		return  "redirect:"+ adminPath + "/VillageManage/teamMemberInfo?teamId="+teamId;
	}
	/*
	 * 删除团队
	 * @author 刘刚
	 * 20170831
	 */
	@RequestMapping("deleteTeam")
	public String teamDel(HttpServletRequest request,RedirectAttributes redirectAttributes){
		String teamId = request.getParameter("teamId");
		String id = request.getParameter("id");
		String msg = villageService.teamDel(teamId);
		addMessage(redirectAttributes,msg);
		return  "redirect:"+ adminPath + "/VillageManage/serviceInformation?id="+id;
	}
}
