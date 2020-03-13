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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.dailywork.entity.tpy.TpyDailyrecord;
import com.thinkgem.jeesite.modules.dailywork.service.tpy.TpyDailyrecordService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/dailywork/tpy/tpyDailyrecordInquiry")
public class TpyDailyrecordInquiryController extends BaseController{
	@Autowired
	private TpyDailyrecordService tpyDailyrecordService;
	
	@ModelAttribute
	public TpyDailyrecord get(@RequestParam(required=false) String id) {
		TpyDailyrecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tpyDailyrecordService.get(id);
		}
		if (entity == null){
			entity = new TpyDailyrecord();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(TpyDailyrecord tpyDailyrecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TpyDailyrecord> page = tpyDailyrecordService.findListWithinPower(new Page<TpyDailyrecord>(request, response),tpyDailyrecord);
		model.addAttribute("page", page);
		return "modules/dailywork/tpy/tpyDailyrecordListInquiry";
	}
	
	@RequestMapping(value = "view")
	public String view(TpyDailyrecord tpyDailyrecord, Model model) {
		tpyDailyrecord.setHtml("inquiry");
		if(tpyDailyrecord.getRecTpyuser().getPersonFlag().equals("0")){
			tpyDailyrecord.setTpytype("自然人特派员");
		}else if(tpyDailyrecord.getRecTpyuser().getPersonFlag().equals("2")){
			tpyDailyrecord.setTpytype("法人特派员");
		}else{
			tpyDailyrecord.setTpytype("其他特派员");
		}
		if(tpyDailyrecord.getRecStatus().equals("pass")){
			tpyDailyrecord.setRecStatus("通过");
		}else if(tpyDailyrecord.getRecStatus().equals("return")){
			tpyDailyrecord.setRecStatus("退回");
		}else{
			tpyDailyrecord.setRecStatus("审核中");
		}
		model.addAttribute("tpyDailyrecord", tpyDailyrecord);
		return "modules/dailywork/tpy/tpyDailyrecordView";
	}
}
