package com.thinkgem.jeesite.modules.dailywork.web.tpy;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.thinkgem.jeesite.modules.dailywork.entity.tpy.TpyDailyrecord;
import com.thinkgem.jeesite.modules.dailywork.service.tpy.TpyDailyrecordService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/dailywork/tpy/tpyDailyrecordVerify")
public class TpyDailyrecordVerifyController  extends BaseController {
	
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
		for(Office o:UserUtils.getOfficeList()){
			System.out.println(o.toString());
		}
		System.out.println(tpyDailyrecord.getRecWriter());
		tpyDailyrecord.setRecTpyLocation(UserUtils.getOfficeList().get(0).toString());
		tpyDailyrecord.setRecStatus("no");
		Page<TpyDailyrecord> page = tpyDailyrecordService.findPage(new Page<TpyDailyrecord>(request, response), tpyDailyrecord);
		for(int i=0;i<page.getList().size();i++){
			if(!page.getList().get(i).getRecStatus().equals("inProcess")){
				page.getList().get(i).setHtml("hidden='hidden'");
			}
		}
		model.addAttribute("page", page);
		return "modules/dailywork/tpy/tpyDailyrecordListVerify";
	}

	@RequestMapping(value = {"YshList"})
	public String Yshlist(TpyDailyrecord tpyDailyrecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		for(Office o:UserUtils.getOfficeList()){
			System.out.println(o.toString());
		}
		tpyDailyrecord.setRecTpyLocation(UserUtils.getOfficeList().get(0).toString());
		tpyDailyrecord.setRecStatus("pass");
		Page<TpyDailyrecord> page = tpyDailyrecordService.findPage(new Page<TpyDailyrecord>(request, response), tpyDailyrecord);
		for(int i=0;i<page.getList().size();i++){
			if(!page.getList().get(i).getRecStatus().equals("inProcess")){
				page.getList().get(i).setHtml("hidden='hidden'");
			}
		}
		model.addAttribute("page", page);
		return "modules/dailywork/tpy/tpyDailyrecordListVerifyYsh";
	}

	
	@RequestMapping(value = "form")
	public String form(TpyDailyrecord tpyDailyrecord, Model model) {
		String id = tpyDailyrecord.getId();
		TpyDailyrecord tpyDailyrecord1 =new TpyDailyrecord();
		tpyDailyrecord1 = tpyDailyrecordService.getVerifyInfo(id);
		if(tpyDailyrecord1.getRecTpyuser().getPersonFlag().equals("0")){
			tpyDailyrecord1.setTpytype("自然人特派员");
		}else if(tpyDailyrecord1.getRecTpyuser().getPersonFlag().equals("2")){
			tpyDailyrecord1.setTpytype("法人特派员");
		}else{
			tpyDailyrecord1.setTpytype("其他特派员");
		}
		tpyDailyrecord1.setRecApproperson(UserUtils.getUser().getName());
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
		String Time = sdf1.format(new Date());
		tpyDailyrecord1.setRecApprotime(Time);
		String imagePath[] = null;
		List list = new ArrayList();
		if(tpyDailyrecord1.getDrTableImage()!=null&&!(tpyDailyrecord1.getDrTableImage().equals(""))){
			imagePath = tpyDailyrecord1.getDrTableImage().split("\\|");
			for(int i=0;i<imagePath.length;i++){
				String path=imagePath[i].substring(12);
				System.out.println(path);
				list.add(imagePath[i].substring(12));
			}
		}
		model.addAttribute("tpyDailyrecord", tpyDailyrecord1);
		model.addAttribute("imagePathList",list);		
		String recHelpobjtype = tpyDailyrecord1.getRecHelpobjtype();
		if(recHelpobjtype.equals("需求单位")){
			return "modules/dailywork/tpy/tpyDailyrecordFormVerify";
		}else{
			return "modules/dailywork/tpy/tpyDailyrecordFormVerifyByVillage";
		}	
	}
	
	@RequestMapping(value = "view")
	public String view(TpyDailyrecord tpyDailyrecord, Model model) {
		tpyDailyrecord.setHtml("verify");
		String id = tpyDailyrecord.getId();
		TpyDailyrecord tpyDailyrecord1 =new TpyDailyrecord();
		tpyDailyrecord1 = tpyDailyrecordService.getVerifyInfo(id);
		if(tpyDailyrecord1.getRecTpyuser().getPersonFlag().equals("0")){
			tpyDailyrecord.setTpytype("自然人特派员");
		}else if(tpyDailyrecord1.getRecTpyuser().getPersonFlag().equals("2")){
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
		String imagePath[] = null;
		List list = new ArrayList();
		if(tpyDailyrecord1.getDrTableImage()!=null&&!(tpyDailyrecord1.getDrTableImage().equals(""))){
			imagePath = tpyDailyrecord1.getDrTableImage().split("\\|");
			for(int i=0;i<imagePath.length;i++){
				String path=imagePath[i].substring(12);
				System.out.println(path);
				list.add(imagePath[i].substring(12));
			}
		}
		model.addAttribute("imagePathList",list);
		model.addAttribute("tpyDailyrecord", tpyDailyrecord);
		if(tpyDailyrecord.getRecHelpobjtype().equals("需求单位")){
			return "modules/dailywork/tpy/tpyDailyrecordView";
		}else{
			return "modules/dailywork/tpy/tpyDailyrecordViewByVillage";
		}
		
	}
	
	@RequestMapping(value = "save")
	public String save(TpyDailyrecord tpyDailyrecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tpyDailyrecord)){
			return form(tpyDailyrecord, model);
		}
		tpyDailyrecord.setRecApproperson(UserUtils.getUser().getName());
		tpyDailyrecordService.save(tpyDailyrecord);
		addMessage(redirectAttributes, "审批特派员工作日志成功");
		return "redirect:"+Global.getAdminPath()+"/dailywork/tpy/tpyDailyrecordVerify/?repage";
	}
}
