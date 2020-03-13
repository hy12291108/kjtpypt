package com.thinkgem.jeesite.modules.dailywork.web.tpy;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.modules.dailywork.entity.tpy.TpyAnnualreport;
import com.thinkgem.jeesite.modules.dailywork.service.tpy.TpyAnnualreportService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/dailywork/tpy/tpyAnnualreportVerify")
public class TpyAnnualreportVerifyController extends BaseController{
	@Autowired
	private TpyAnnualreportService tpyAnnualreportService;
	
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
	
	@RequestMapping(value = {"list", ""})
	public String list(TpyAnnualreport tpyAnnualreport, HttpServletRequest request, HttpServletResponse response, Model model) {
//		tpyAnnualreport.setRepTpyid(UserUtils.getUser().toString());
		tpyAnnualreport.setRepTpyLocation(UserUtils.getOfficeList().get(0).toString());
		tpyAnnualreport.setRepStatus("inProcess");
		Page<TpyAnnualreport> page = tpyAnnualreportService.findPage(new Page<TpyAnnualreport>(request, response), tpyAnnualreport); 
		for(int i=0;i<page.getList().size();i++){
			if(page.getList().get(i).getRepStatus().equals("pass")){
				page.getList().get(i).setHtml("hidden='hidden'");
			}
		}
		model.addAttribute("page", page);
		return "modules/dailywork/tpy/tpyAnnualreportListVerify";
	}
	
	@RequestMapping(value = {"YshTpyAnnualreportlist"})
	public String YshTpyAnnualreportlist(TpyAnnualreport tpyAnnualreport, HttpServletRequest request, HttpServletResponse response, Model model) {
//		tpyAnnualreport.setRepTpyid(UserUtils.getUser().toString());
		tpyAnnualreport.setRepStatus("pass");
		tpyAnnualreport.setRepTpyLocation(UserUtils.getOfficeList().get(0).toString());
		Page<TpyAnnualreport> page = tpyAnnualreportService.findPage(new Page<TpyAnnualreport>(request, response), tpyAnnualreport); 
		for(int i=0;i<page.getList().size();i++){
			if(page.getList().get(i).getRepStatus().equals("pass")){
				page.getList().get(i).setHtml("hidden='hidden'");
			}
		}
		model.addAttribute("page", page);
		return "modules/dailywork/tpy/tpyAnnualreportListVerifyYsh";
	}
	
	@RequestMapping(value = "form")
	public String form(TpyAnnualreport tpyAnnualreport, Model model) {
		tpyAnnualreport.setHtml("verify");
		tpyAnnualreport.setRepApproperson(UserUtils.getUser().getName());
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");;
		String Time = sdf1.format(new Date());
		tpyAnnualreport.setRepApprotime(Time);
		if(tpyAnnualreport.getRepStatus().equals("pass")){
			tpyAnnualreport.setRepStatus("通过");
		}else if(tpyAnnualreport.getRepStatus().equals("return")){
			tpyAnnualreport.setRepStatus("退回");
		}else{
			tpyAnnualreport.setRepStatus("审核中");
		}
		model.addAttribute("tpyAnnualreport", tpyAnnualreport);
		return "modules/dailywork/tpy/tpyAnnualreportFormVerify";
	}
	
	@RequestMapping(value = "save")
	public String save(TpyAnnualreport tpyAnnualreport, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tpyAnnualreport)){
			return form(tpyAnnualreport, model);
		}
		tpyAnnualreport.setRepAppropersonid(UserUtils.getUser().getId());	
		tpyAnnualreportService.updateTpyAnnualreport(tpyAnnualreport);
		addMessage(redirectAttributes, "审核特派员年度考核成功");
		return "redirect:"+Global.getAdminPath()+"/dailywork/tpy/tpyAnnualreportVerify/?repage";
	}
	
	@RequestMapping(value = "view")
	public String view(TpyAnnualreport tpyAnnualreport, Model model) {
		tpyAnnualreport.setHtml("verifyView");
		User u1=UserUtils.get(tpyAnnualreport.getRepTpyid());
		User u2=UserUtils.get(tpyAnnualreport.getRepAppropersonid());
		if(tpyAnnualreport.getRepStatus().equals("pass")){
			tpyAnnualreport.setRepStatus("通过");
		}else if(tpyAnnualreport.getRepStatus().equals("return")){
			tpyAnnualreport.setRepStatus("退回");
		}else{
			tpyAnnualreport.setRepStatus("审核中");
		}
		tpyAnnualreport.setRepTpyuser(UserUtils.get(tpyAnnualreport.getRepTpyid()));
//		tpyAnnualreport.setRepApproUser(UserUtils.get(tpyAnnualreport.getRepAppropersonid()));
		model.addAttribute("tpyAnnualreport", tpyAnnualreport);
		return "modules/dailywork/tpy/tpyAnnualreportFormView";
	}
}
