package com.thinkgem.jeesite.modules.dailywork.web.tpy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.dailywork.entity.tpy.TpyAnnualreport;
import com.thinkgem.jeesite.modules.dailywork.entity.tpy.TpyDailyrecord;
import com.thinkgem.jeesite.modules.dailywork.service.tpy.TpyAnnualreportService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/dailywork/tpy/tpyAnnualreportInquiry")
public class TpyAnnualreportInquiryController extends BaseService {
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
		Page<TpyAnnualreport> page = tpyAnnualreportService.findListWithinPower(new Page<TpyAnnualreport>(request, response), tpyAnnualreport);
		model.addAttribute("page", page);		
		return "modules/dailywork/tpy/tpyAnnualreportListInquiry";
	}
	@RequestMapping(value = "view")
	public String view(TpyAnnualreport tpyAnnualreport, Model model) {
		tpyAnnualreport.setHtml("inquiry");
		if(tpyAnnualreport.getRepStatus().equals("pass")){
			tpyAnnualreport.setRepStatus("通过");
		}else if(tpyAnnualreport.getRepStatus().equals("return")){
			tpyAnnualreport.setRepStatus("退回");
		}else{
			tpyAnnualreport.setRepStatus("审核中");
		}
		tpyAnnualreport.setRepTpyuser(UserUtils.get(tpyAnnualreport.getRepTpyid()));
		model.addAttribute("tpyAnnualreport", tpyAnnualreport);
		return "modules/dailywork/tpy/tpyAnnualreportFormView";
	}

}
