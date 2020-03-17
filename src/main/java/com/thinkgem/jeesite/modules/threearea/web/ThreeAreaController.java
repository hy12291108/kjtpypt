/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.threearea.web;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
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
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.entity.TpyInfo;
import com.thinkgem.jeesite.modules.sys.entity.TpyWorkExperience;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.AreaService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.threearea.entity.ThreeArea;
import com.thinkgem.jeesite.modules.threearea.entity.ThreeAreaBaseData;
import com.thinkgem.jeesite.modules.threearea.service.ThreeAreaService;

/**
 * 三区人才Controller
 * @author 刘钢
 * @version 2017-9-04
 */
@Controller
@RequestMapping(value = "${adminPath}/threeSq")
public class ThreeAreaController extends BaseController {
	@Autowired
	private ThreeAreaService threeAreaService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private AreaService areaService;
	@ModelAttribute
	public ThreeArea get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return threeAreaService.getThreeArea(id);
		}else{
			return new ThreeArea();
		}
	}
	
	/*
	 * 三区人才申请页面
	 * 201709012
	 * 刘钢
	 */
	@RequestMapping("threeAreaSq")
	public String threeAreaSq(User user, Model model,ThreeArea threeArea){
		if (StringUtils.isNotBlank(threeArea.getId())){
			model.addAttribute("message", "已成功申请三区人才");
		}
		//获取当前用户
		user = UserUtils.getUser();
		if(user.getPersonFlag().equals("2")){
			model.addAttribute("message","法人不能申请三区人才");
			return "modules/threeArea/threeAreaSqNo";
		}
		String areaId = user.getOffice().getArea().getId();
		System.out.println(user.getOffice().getArea().getName());
		Area area = areaService.get(areaId);
		if(area.getFlag().equals("1")){
			model.addAttribute("message","贫困县不能申请三区人才");
			return "modules/threeArea/threeAreaSqNo";
		}
		System.out.println(area.getName());
		TpyInfo tpyInfo = threeAreaService.get(user.getId());
		List<TpyWorkExperience> experience = new ArrayList<TpyWorkExperience>();
		ThreeAreaBaseData threeAreaBaseData = threeAreaService.getParameter();
		if(threeAreaBaseData!=null){
		//判断基本信息是否完善
		if(tpyInfo!=null){
			String id = tpyInfo.getId();
			experience = systemService.findWork(id);
		}
		model.addAttribute("threeAreaBaseData", threeAreaBaseData);
		model.addAttribute("user",user);
		model.addAttribute("tpyInfo",tpyInfo);
		model.addAttribute("experience",experience);
		model.addAttribute("threeArea",threeArea);
		return "modules/threeArea/threeAreaSq";
	}else{
		//判断基本信息是否完善
				if(tpyInfo!=null){
					String id = tpyInfo.getId();
					experience = systemService.findWork(id);
				}
				model.addAttribute("message","申请时间已过！");
				model.addAttribute("user",user);
				model.addAttribute("tpyInfo",tpyInfo);
				model.addAttribute("experience",experience);
				model.addAttribute("threeArea",threeArea);
				return "modules/threeArea/threeAreaSq";
	}
		}
	/*
	 * 特派员申请三区人才
	 * 20170912
	 * 刘钢
	 */
	@RequestMapping("tpySqThreeArea")
	public String tpySqThreeArea(ThreeArea threeArea,RedirectAttributes redirectAttributes){
		Map<String,String> map = threeAreaService.addSqThreeArea(threeArea);
		 for (String key : map.keySet()) {
			 addMessage(redirectAttributes, map.get(key));
			  }
		 System.out.println("mapmapmap=================="+map);
		 //返回到本页
		//return "redirect:"+adminPath+"/threeSq/threeAreaSq";
		return "redirect:"+adminPath+"/threeSq/findResult";
	}
	/* @author 刘钢
	 * 省/市进行三区人才的审核
	 * 20170920
	 * @update20181127带分页及查询的方法
	 * gz
	 */
	@RequestMapping("threeAreaCheck")
	public String threeAreaCheck(ThreeArea threeArea, HttpServletRequest request, HttpServletResponse response, Model model){
		Page <ThreeArea> page = threeAreaService.getThreeArea2(new Page<ThreeArea>(request, response), threeArea);
		if(threeArea!=null){
		//	model.addAttribute("threeArea", threeArea);
			model.addAttribute("page", page);
			return "modules/threeArea/threeAreaCheck";}
		else{
			model.addAttribute("message", "三区人才申请未结束或审核时间已过");
			//model.addAttribute("threeArea", threeArea);
			model.addAttribute("page", page);
			return "modules/threeArea/threeAreaCheck";
		}
	}
	/* @author LG
	 * 具体信息审核
	 * 20170921
	 */
	@RequestMapping("threeAreaInfoCheck")
	public String threeAreaInfoCheck(ThreeArea threeArea, Model model){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		if(threeArea.getStatus().equals("1")){
			threeArea.setTpyBirthDate(threeArea.getTpyBirthDate().substring(0,10));
			threeArea.setCheckTimeC(sf.format(new Date()));
			threeArea.setCheckPersonC(UserUtils.getUser().getName());
			model.addAttribute("threeArea", threeArea);
			return "modules/threeArea/threeAreaInfoC";	
		}else{
			threeArea.setTpyBirthDate(threeArea.getTpyBirthDate().substring(0,10));
			threeArea.setCheckTimeP(sf.format(new Date()));
			threeArea.setCheckPersonP(UserUtils.getUser().getName());
			model.addAttribute("threeArea", threeArea);
			return "modules/threeArea/threeAreaInfoP";	
		}
	}
	
	
	
	/* @author 刘钢
	 * 审核结果更新
	 * 20170920
	 */
	@RequestMapping("updatestatus")
	public String updatestatus(ThreeArea threeArea,RedirectAttributes redirectAttributes){
		boolean flag = threeAreaService.updateStatus(threeArea);
		if(flag){
			addMessage(redirectAttributes, "审核成功");
			return "redirect:"+adminPath+"/threeSq/threeAreaCheck";
		}else{
			addMessage(redirectAttributes, "审核失败");
			return "redirect:"+adminPath+"/threeSq/threeAreaCheck";
		}
		
	}
	
	/* @author 刘钢
	 * 三区人才审核通过查询
	 * 20170920
	 * @update20181127带分页及查询的方法
	 * gz
	 */
	@RequestMapping("/threeAreaTalent")
	public String threeAreaTalent(ThreeArea threeArea, HttpServletRequest request, HttpServletResponse response, Model model){
		Page <ThreeArea> page = threeAreaService.getPassTalent2(new Page<ThreeArea>(request, response), threeArea);
//		if(threeArea!=null){
//		//	model.addAttribute("threeArea", threeArea);
//			model.addAttribute("page", page);
//			return "modules/threeArea/threeAreaCheck";}
//		else{
//			model.addAttribute("message", "三区人才申请未结束或审核时间已过");
//			//model.addAttribute("threeArea", threeArea);
//			model.addAttribute("page", page);
//			return "modules/threeArea/threeAreaCheck";
//		}
	//	List<ThreeArea> threeArea  = threeAreaService.getPassTalent();
		if(threeArea!=null){
			//model.addAttribute("threeArea", threeArea);
			model.addAttribute("page", page);
			return "modules/threeArea/threeAreaTalent";
		}else{
			model.addAttribute("message", "查无数据");
			//model.addAttribute("threeArea", threeArea);
			model.addAttribute("page", page);
			return "modules/threeArea/threeAreaTalent";
		}
		
	}
	/* @author 刘钢
	 * 三区人才审核未通过查询
	 * 20170920
	 * @update20181127带分页及查询的方法
	 * gz
	 */
	@RequestMapping("/getFailTalent")
	public String getFailTalent(ThreeArea threeArea, HttpServletRequest request, HttpServletResponse response, Model model){
		Page <ThreeArea> page = threeAreaService.getFailTalent2(new Page<ThreeArea>(request, response), threeArea);
//		if(threeArea!=null){
//		//	model.addAttribute("threeArea", threeArea);
//			model.addAttribute("page", page);
//			return "modules/threeArea/threeAreaCheck";}
//		else{
//			model.addAttribute("message", "三区人才申请未结束或审核时间已过");
//			//model.addAttribute("threeArea", threeArea);
//			model.addAttribute("page", page);
//			return "modules/threeArea/threeAreaCheck";
//		}
	//	List<ThreeArea> threeArea  = threeAreaService.getFailTalent();
		if(threeArea!=null){
			//model.addAttribute("threeArea", threeArea);
			model.addAttribute("page", page);
			return "modules/threeArea/threeAreaFail";
		}else{
			model.addAttribute("message", "查无数据");
			model.addAttribute("page", page);
			return "modules/threeArea/threeAreaFail";
		}
		
	}
	
	
	
	/*
	 * @author 刘钢
	 * 三区人才历史记录
	 *  20170927
	 */
	@RequestMapping("/findResult")
	public String findResult(Model model){
		List<ThreeArea> threeArea =threeAreaService.findResult();
		model.addAttribute("threeArea", threeArea);
		return "modules/threeArea/threeAreaResult";
	}
	/*
	 * @author 刘钢
	 * 三区人才审核结果具体信息查看
	 *  20170927
	 */
	@RequestMapping("/threeAreaResultInfo")
	public String threeAreaResultInfo(ThreeArea threeArea,Model model){
		if(threeArea!=null){
			threeArea.setTpyBirthDate(threeArea.getTpyBirthDate().substring(0, 10));
		if(threeArea.getCheckTimeC()!=null){
			threeArea.setCheckTimeC(threeArea.getCheckTimeC().substring(0, 10));
		}
		if(threeArea.getCheckTimeP()!=null){
			threeArea.setCheckTimeP(threeArea.getCheckTimeP().substring(0, 10));}
		}
		model.addAttribute("threeArea",threeArea);
		return "modules/threeArea/threeAreaResultInfo";
	}
	/*
	 * @author gz
	 * 查询要修改的三区人才信息
	 *  20181127
	 */
	@RequestMapping("/threeAreaResultInfoupdate")
	public String threeAreaResultInfoupdate(ThreeArea threeArea,Model model){
		if(threeArea!=null){
			threeArea.setTpyBirthDate(threeArea.getTpyBirthDate().substring(0, 10));
		if(threeArea.getCheckTimeC()!=null){
			threeArea.setCheckTimeC(threeArea.getCheckTimeC().substring(0, 10));
		}
		if(threeArea.getCheckTimeP()!=null){
			threeArea.setCheckTimeP(threeArea.getCheckTimeP().substring(0, 10));}
		}
		model.addAttribute("threeArea",threeArea);
		return "modules/threeArea/threeAreaResultInfoupdate";
	}
	/*
	 * @author gz
	 * 修改的三区人才信息
	 *  20181127
	 */
	@RequestMapping("tpyThreeAreaupdate")
	public String tpyThreeAreaupdate(ThreeArea threeArea,RedirectAttributes redirectAttributes) {
		boolean flag = threeAreaService.tpyThreeAreaupdate(threeArea);
		if(flag){
			addMessage(redirectAttributes, "修改成功");
			return "redirect:"+adminPath+"/threeSq/findResult";
		}else{
			addMessage(redirectAttributes, "修改失败");
			return "redirect:"+adminPath+"/threeSq/findResult";
		}
		
	

	}
	/*
	 * 三区参数维护
	 * 20171007
	 * LG
	 */
	@RequestMapping("baseInfoData")
	public String baseInfoData(Model model){
		ThreeAreaBaseData threeAreaBaseData = threeAreaService.getParameter();
		if(threeAreaBaseData != null){
			model.addAttribute("threeAreaBaseData", threeAreaBaseData);
			return "modules/threeArea/baseInfoData";
		}else{
			return "modules/threeArea/baseInfoData";
		}
	}
	/*
	 * 三区参数添加
	 * 20171007
	 * LG
	 */
	@RequestMapping("addBaseInfoData")
	public String addBaseInfoData(Model model){
	/*	ThreeAreaBaseData threeAreaBaseData = threeAreaService.getParameter();
		if(threeAreaBaseData != null){
			model.addAttribute("threeAreaBaseData", threeAreaBaseData);
			return "modules/threeArea/baseInfoData";
		}else{
			return "modules/threeArea/addbaseInfoData";
		}*/
		return "modules/threeArea/addbaseInfoData";
	}
	
	
	
	@RequestMapping("insertBaseData")
	public String insertBaseData(String id,ThreeAreaBaseData threeAreaBaseData,RedirectAttributes redirectAttributes){
		//存在bug 空指针 需判断
		//if(threeAreaBaseData.getId().equals("")||threeAreaBaseData.getId()==null){
		if(id.equals("")||id==null){
		if(threeAreaService.addBaseData(threeAreaBaseData)){
			 addMessage(redirectAttributes,"参数维护成功");
			 return "redirect:"+adminPath+"/threeSq/baseInfoData";
		}else{
			 addMessage(redirectAttributes,"参数维护失败");
			 return "redirect:"+adminPath+"/threeSq/baseInfoData";
		}}else{
			boolean flag = threeAreaService.updateBaseData(threeAreaBaseData);
			if(flag){
				addMessage(redirectAttributes,"参数维护成功");
				return "redirect:"+adminPath+"/threeSq/baseInfoData";
			}else{
				addMessage(redirectAttributes,"参数维护失败");
				 return "redirect:"+adminPath+"/threeSq/baseInfoData";
			}
		}
		
	}
	
	
	
	/* @author 刘钢
	 * 三区人才下派
	 * 20170929
	 */
	@RequestMapping("/threeAreaXp")
	public String threeAreaXp(Model model){
		//获取所有下派人员列表
		List<ThreeArea> threeArea  = threeAreaService.getPassTalent();
		//获取已下派人员列表
		//List<ThreeArea> threeArea  = threeAreaService.getXpList();
		ThreeAreaBaseData threeAreaBaseData = threeAreaService.getXpParameter();
		if(threeAreaBaseData!=null){
		if(threeArea.size()>0){
			model.addAttribute("threeArea", threeArea);
			model.addAttribute("year",threeAreaBaseData.getYear());
			model.addAttribute("xpPerson",UserUtils.getUser().getName());
			return "modules/threeArea/threeAreaXp";
		}else{
			model.addAttribute("message", "查无数据");
			model.addAttribute("threeArea", threeArea);
			return "modules/threeArea/threeAreaXp";
		}}else{
			model.addAttribute("message", "下派时间未到或下派时间已过");
			model.addAttribute("threeArea", threeArea);
			return "modules/threeArea/threeAreaXp";
		}
		
	}
	
	/* @author 刘钢
	 * 三区人才下派信息更新
	 * 20170929
	 */
	@RequestMapping("/updateXpInfo")
	public String updateXpInfo(HttpServletRequest request,Model model){
		String id = request.getParameter("id");
		String xpZone = request.getParameter("area.id");
		//String startTime = request.getParameter("xpStartTime");
		//String endTime = request.getParameter("xpEndTime");
		String xpPerson = request.getParameter("xpPerson");
		String xpYear = request.getParameter("xpYear");
		ThreeArea threeArea = new ThreeArea();
		threeArea.setId(id);
		threeArea.setXpZone(xpZone);
		//threeArea.setXpStartTime(startTime);
		//threeArea.setXpEndTime(endTime);
		threeArea.setXpPerson(xpPerson);
		threeArea.setXpYear(xpYear);
		threeAreaService.updateXpInfo(threeArea);
		return "redirect:"+adminPath+"/threeSq/threeAreaXp";
	}
	
	
	
}
