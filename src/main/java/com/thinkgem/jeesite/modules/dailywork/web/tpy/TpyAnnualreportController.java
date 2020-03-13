/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dailywork.web.tpy;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.dailywork.entity.tpy.TpyAnnualreport;
import com.thinkgem.jeesite.modules.dailywork.service.tpy.TpyAnnualreportService;
import com.thinkgem.jeesite.modules.dailywork.service.tpy.TpyDailyrecordService;
import com.thinkgem.jeesite.modules.sqtpy.entity.Sqtpy;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 特派员年度考核Controller
 * @author Grace
 * @version 2017-08-10
 */
@Controller
@RequestMapping(value = "${adminPath}/dailywork/tpy/tpyAnnualreport")
public class TpyAnnualreportController extends BaseController {

	@Autowired
	private TpyAnnualreportService tpyAnnualreportService;
	@Autowired
	private TpyDailyrecordService tpyDailyrecordService;
	
	@ModelAttribute
	public TpyAnnualreport get(@RequestParam(required=false) String id) {
		TpyAnnualreport entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tpyAnnualreportService.get(id);
		}
		if (entity == null){
			entity = new TpyAnnualreport();
		}
		return entity;
	}
	
	@RequiresPermissions("dailywork:tpy:tpyAnnualreport:view")
	@RequestMapping(value = {"list", ""})
	public String list(TpyAnnualreport tpyAnnualreport, HttpServletRequest request, HttpServletResponse response, Model model) {
		tpyAnnualreport.setRepTpyid(UserUtils.getUser().toString());
		tpyAnnualreport.setRepStatus("inProcess");
		Page<TpyAnnualreport> page = tpyAnnualreportService.findPage(new Page<TpyAnnualreport>(request, response), tpyAnnualreport); 
//		for(int i=0;i<page.getList().size();i++){
//			if(page.getList().get(i).getRepStatus().equals("inProcess")){
//				page.getList().get(i).setHtml("hidden='hidden'");
//			}
//		}
		model.addAttribute("page", page);
		return "modules/dailywork/tpy/tpyAnnualreportList";
	}
	
	@RequestMapping(value = {"YshtpyAnnualreportList"})
	public String YshtpyAnnualreportList(TpyAnnualreport tpyAnnualreport, HttpServletRequest request, HttpServletResponse response, Model model) {
		tpyAnnualreport.setRepTpyid(UserUtils.getUser().toString());
		tpyAnnualreport.setRepStatus("pass");
		Page<TpyAnnualreport> page = tpyAnnualreportService.findPage(new Page<TpyAnnualreport>(request, response), tpyAnnualreport); 
//		for(int i=0;i<page.getList().size();i++){
//			if(page.getList().get(i).getRepStatus().equals("inProcess")){
//				page.getList().get(i).setHtml("hidden='hidden'");
//			}
//		}
		model.addAttribute("page", page);
		return "modules/dailywork/tpy/tpyAnnualreportListYsh";
	}
	@RequestMapping(value = "form")
	public String form(TpyAnnualreport tpyAnnualreport, Model model) {
		TpyAnnualreport tpyAnnualreport1 = new TpyAnnualreport();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
		String NowYear = sdf.format(new Date());
		String Time = sdf1.format(new Date());
		String tpyid= UserUtils.getUser().getId();
		tpyAnnualreport1 = tpyAnnualreportService.getTpyReportByYear(NowYear,tpyid);
		if(tpyAnnualreport1!=null){
		tpyAnnualreport1.setRepTpyuser(UserUtils.getUser());
		tpyAnnualreport1.setRepTime(NowYear);
		tpyAnnualreport1.setRepWritenTime(Time);
		model.addAttribute("tpyAnnualreport", tpyAnnualreport1);
		return "modules/dailywork/tpy/tpyAnnualreportForm";
		}else{
			tpyAnnualreport.setRepTpyuser(UserUtils.getUser());
			tpyAnnualreport.setRepTime(NowYear);
			tpyAnnualreport.setRepWritenTime(Time);
			model.addAttribute("tpyAnnualreport", tpyAnnualreport);
			return "modules/dailywork/tpy/tpyAnnualreportForm";
		}
	}
	
	@RequestMapping(value = "selectAnnualreport")
	public @ResponseBody TpyAnnualreport selectAnnualreport(@RequestParam(required=false)String repTime, @RequestParam(required=false)String repTpyuserid) {
		
		TpyAnnualreport tpyAnnualreport1 = tpyAnnualreportService.getTpyReportByYear(repTime,repTpyuserid);
		if(tpyAnnualreport1!=null){
			return tpyAnnualreport1;
		}else{
			TpyAnnualreport tpyAnnualreport = new TpyAnnualreport();
			return tpyAnnualreport;
		}
	}
	
	@RequestMapping(value = "update")
	public String update(TpyAnnualreport tpyAnnualreport, Model model) {	
		tpyAnnualreport=tpyAnnualreportService.getAnnualreportInfo(tpyAnnualreport.getId());
		if(tpyAnnualreport.getRepStatus().equals("return")){
			tpyAnnualreport.setRepStatus("退回");
		}else if(tpyAnnualreport.getRepStatus().equals("pass")){
			tpyAnnualreport.setRepStatus("通过");
		}else{
			tpyAnnualreport.setRepStatus("审核中");
		}
		model.addAttribute("tpyAnnualreport", tpyAnnualreport);
		return "modules/dailywork/tpy/tpyAnnualreportUpdate";
	}
	
	@RequestMapping(value = "updateInfo")
	public String updateInfo(TpyAnnualreport tpyAnnualreport, RedirectAttributes redirectAttributes) {
		if(tpyAnnualreport.getRepStatus().equals("no")){
			tpyAnnualreport.setRepStatus("no");
		}else{
			tpyAnnualreport.setRepStatus("inProcess");		
		}
		tpyAnnualreport.setRepTpyid(UserUtils.getUser().getId());
		tpyAnnualreportService.UpdateInfo(tpyAnnualreport);
		addMessage(redirectAttributes, "修改特派员年度考核成功");
		return "redirect:"+Global.getAdminPath()+"/dailywork/tpy/tpyAnnualreport/?repage";
	}
	
	@RequiresPermissions("dailywork:tpy:tpyAnnualreport:edit")
	@RequestMapping(value = "save")
	public String save(TpyAnnualreport tpyAnnualreport, Model model, RedirectAttributes redirectAttributes) {
		tpyAnnualreport.setRepTpyid(UserUtils.getUser().toString());
		List <TpyAnnualreport> list = tpyAnnualreportService.getTpyAnnualreport(tpyAnnualreport);
		if(list.size()>0){
			addMessage(redirectAttributes, tpyAnnualreport.getRepTime()+"年度总结信息已存在！");
			return "redirect:"+Global.getAdminPath()+"/dailywork/tpy/tpyAnnualreport/form?repage";
		}else{
		if (!beanValidator(model, tpyAnnualreport)){
			addMessage(redirectAttributes, "存入数据有误");
			return form(tpyAnnualreport, model);
		}	
		tpyAnnualreport.setRepStatus("no");
//		tpyAnnualreport.setRepTpyLocation(UserUtils.getAreaList().toString());
		tpyAnnualreport.setRepTpyLocation(UserUtils.getOfficeList().toString());
		tpyAnnualreportService.save(tpyAnnualreport);
		addMessage(redirectAttributes, "保存特派员年度考核成功");
		return "redirect:"+Global.getAdminPath()+"/dailywork/tpy/tpyAnnualreport/?repage";
		}
	}
	
	@RequestMapping(value = "change")
	public String change(TpyAnnualreport tpyAnnualreport, Model model, RedirectAttributes redirectAttributes) {
		tpyAnnualreport.setRepStatus("inProcess");
		tpyAnnualreportService.change(tpyAnnualreport);
		addMessage(redirectAttributes, "提交成功");
		return "redirect:"+Global.getAdminPath()+"/dailywork/tpy/tpyAnnualreport/?repage";	
	}
	
	
	@RequiresPermissions("dailywork:tpy:tpyAnnualreport:edit")
	@RequestMapping(value = "delete")
	public String delete(TpyAnnualreport tpyAnnualreport, RedirectAttributes redirectAttributes) {
		tpyAnnualreportService.delete(tpyAnnualreport);
		addMessage(redirectAttributes, "删除特派员年度考核成功");
		return "redirect:"+Global.getAdminPath()+"/dailywork/tpy/tpyAnnualreport/?repage";
	}
	
	@RequestMapping(value = "view")
	public String view(TpyAnnualreport tpyAnnualreport1, Model model) {
		TpyAnnualreport tpyAnnualreport = new TpyAnnualreport();
		tpyAnnualreport.setHtml("view");	
		tpyAnnualreport=tpyAnnualreportService.getAnnualreportInfo(tpyAnnualreport1.getId());
		if(tpyAnnualreport.getRepStatus().equals("pass")){
			tpyAnnualreport.setRepStatus("通过");
		}else if(tpyAnnualreport.getRepStatus().equals("return")){
			tpyAnnualreport.setRepStatus("退回");
		}else{
			tpyAnnualreport.setRepStatus("审核中");
		}
		model.addAttribute("tpyAnnualreport", tpyAnnualreport);
		return "modules/dailywork/tpy/tpyAnnualreportFormView";
	}

}