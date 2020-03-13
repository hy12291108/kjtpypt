/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dailywork.web.tpy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.dailywork.entity.tpy.TpyDailyrecord;
import com.thinkgem.jeesite.modules.dailywork.service.tpy.TpyDailyrecordService;
import com.thinkgem.jeesite.modules.sqtpy.entity.Sqtpy;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.threearea.entity.ThreeArea;
import com.thinkgem.jeesite.modules.villagemanage.entity.TeamMember;
import com.thinkgem.jeesite.modules.villagemanage.service.VillageService;

/**
 * 特派员工作日志Controller
 * @author Grace
 * @version 2017-08-10
 */
@Controller
@RequestMapping(value = "${adminPath}/dailywork/tpy/tpyDailyrecord")
public class TpyDailyrecordController extends BaseController {

	@Autowired
	private TpyDailyrecordService tpyDailyrecordService;
	
	@Autowired
	private VillageService villageService;
	
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
		if(UserUtils.getUser().getPersonFlag().equals("1")){
			tpyDailyrecord.setRecHelpobjid(UserUtils.getUser().getId());;
		}else{
			tpyDailyrecord.setRecTpyid(UserUtils.getUser().getId());
		}
		tpyDailyrecord.setRecStatus("no");
		Page<TpyDailyrecord> page = tpyDailyrecordService.findPage(new Page<TpyDailyrecord>(request, response), tpyDailyrecord); 
		for(int i=0;i<page.getList().size();i++){
			if(!page.getList().get(i).getRecStatus().equals("return")){
				page.getList().get(i).setHtml("hidden='hidden'");
			}
		}
		model.addAttribute("page", page);
//		System.out.println(UserUtils.getRoleList());
		return "modules/dailywork/tpy/tpyDailyrecordList";
	}
	@RequestMapping(value = {"yshlist"})
	public String yshlist(TpyDailyrecord tpyDailyrecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(UserUtils.getUser().getPersonFlag().equals("1")){
			tpyDailyrecord.setRecHelpobjid(UserUtils.getUser().getId());;
		}else{
			tpyDailyrecord.setRecTpyid(UserUtils.getUser().getId());
		}
		tpyDailyrecord.setRecStatus("pass");
		Page<TpyDailyrecord> page = tpyDailyrecordService.findPage(new Page<TpyDailyrecord>(request, response), tpyDailyrecord); 
		for(int i=0;i<page.getList().size();i++){
			if(!page.getList().get(i).getRecStatus().equals("return")){
				page.getList().get(i).setHtml("hidden='hidden'");
			}
		}
		model.addAttribute("page", page);
		return "modules/dailywork/tpy/tpyDailyrecordYshList";
	}
	

	
	@RequestMapping(value = {"dpjlist"})
	public String dpjlist(TpyDailyrecord tpyDailyrecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(UserUtils.getUser().getPersonFlag().equals("1")){
			tpyDailyrecord.setRecHelpobjid(UserUtils.getUser().getId());;
		}else{
			tpyDailyrecord.setRecTpyid(UserUtils.getUser().getId());
		}
		tpyDailyrecord.setRecStatus("pass");
		tpyDailyrecord.setFwdxpjState("0");
		Page<TpyDailyrecord> page = tpyDailyrecordService.xqdwckPage(new Page<TpyDailyrecord>(request, response), tpyDailyrecord); 
		for(int i=0;i<page.getList().size();i++){
			if(!page.getList().get(i).getRecStatus().equals("return")){
				page.getList().get(i).setHtml("hidden='hidden'");
			}
		}
		model.addAttribute("page", page);
//		System.out.println(UserUtils.getRoleList());
		return "modules/dailywork/tpy/tpyDailyrecordCkList";
	}
	

	@RequestMapping(value = {"ypjlist"})
	public String ypjlist(TpyDailyrecord tpyDailyrecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(UserUtils.getUser().getPersonFlag().equals("1")){
			tpyDailyrecord.setRecHelpobjid(UserUtils.getUser().getId());;
		}else{
			tpyDailyrecord.setRecTpyid(UserUtils.getUser().getId());
		}
		tpyDailyrecord.setRecStatus("pass");
		tpyDailyrecord.setFwdxpjState("1");
		Page<TpyDailyrecord> page = tpyDailyrecordService.xqdwckPage(new Page<TpyDailyrecord>(request, response), tpyDailyrecord); 
		for(int i=0;i<page.getList().size();i++){
			if(!page.getList().get(i).getRecStatus().equals("return")){
				page.getList().get(i).setHtml("hidden='hidden'");
			}
		}
		model.addAttribute("page", page);
		return "modules/dailywork/tpy/tpyDailyrecordypjList";
	}
	
	@RequestMapping(value = "form")
	public String form( TpyDailyrecord tpyDailyrecord, Model model) {
		
		String tpyid = UserUtils.getUser().getId();
		tpyDailyrecord.setRecWriter(UserUtils.getUser().getName());
		if(UserUtils.getUser().getPersonFlag().equals("0"))	{
			tpyDailyrecord.setTpytype("自然人特派员");
		}else if(UserUtils.getUser().getPersonFlag().equals("2"))	{
			tpyDailyrecord.setTpytype("法人特派员");
		}else{
			tpyDailyrecord.setTpytype("未知特派员");
		}
		tpyDailyrecord.setRecHelpobjtype("需求单位");
		List<Sqtpy> list=tpyDailyrecordService.getFwdxList(tpyid);
		tpyDailyrecord.setRecHelpObjList(list);	
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy");
		String Year = sdf1.format(new Date())+"年";
		System.out.println("1111111111===="+Year);
		tpyDailyrecord.setYear(Year);
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		tpyDailyrecord.setRecWrittenTime(sdf.format(date));
		tpyDailyrecord.setRecTpyid(tpyid);
		List<TpyDailyrecord> ThreeArealist=tpyDailyrecordService.CheckIsThreeAreaByYear(tpyDailyrecord);
		
	//	List<ThreeArea> ThreeArealist=tpyDailyrecordService.CheckIsThreeAreaByYear(Year, tpyid);
		if(ThreeArealist.size()>0){
			tpyDailyrecord.setIsThreeArea("是");
		}else{
			tpyDailyrecord.setIsThreeArea("否");
		}
		tpyDailyrecord.setDailyRecordType("个人日志(服务对象)");
		model.addAttribute("tpyDailyrecord", tpyDailyrecord);
		return "modules/dailywork/tpy/testAuditForm";
		//return "modules/dailywork/tpy/index";
	}
	
	@RequestMapping(value = "vilform")
	public String vilform( TpyDailyrecord tpyDailyrecord, Model model) {
		
		String tpyid = UserUtils.getUser().getId();
		tpyDailyrecord.setRecWriter(UserUtils.getUser().getName());
		if(UserUtils.getUser().getPersonFlag().equals("0"))	{
			tpyDailyrecord.setTpytype("自然人特派员");
		}else if(UserUtils.getUser().getPersonFlag().equals("2"))	{
			tpyDailyrecord.setTpytype("法人特派员");
		}else{
			tpyDailyrecord.setTpytype("未知特派员");
		}	
		tpyDailyrecord.setRecHelpobjtype("贫困村");
		List<Sqtpy> list=tpyDailyrecordService.getVilList(tpyid);
		tpyDailyrecord.setRecHelpObjList(list);				
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy");
		String Year = sdf1.format(new Date())+"年";
		tpyDailyrecord.setYear(Year);
		//获得特派团
		TeamMember teamMember = new TeamMember();
		teamMember.setUserId(UserUtils.getUser().getId());
		List<TeamMember> list2 = villageService.getTeam(teamMember);	
		teamMember.setUserId(UserUtils.getUser().getId());
		tpyDailyrecord.setTeamMemberList(list2);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		tpyDailyrecord.setRecWrittenTime(sdf.format(new Date()));
		model.addAttribute("tpyDailyrecord", tpyDailyrecord);
		return "modules/dailywork/tpy/testAuditForm1";
		//return "modules/dailywork/tpy/index";
	}
	
	@RequestMapping(value = "update")
	public String update( TpyDailyrecord tpyDailyrecord, Model model) {
		
		String id = tpyDailyrecord.getId();		
		TpyDailyrecord tpyDailyrecord1 =new TpyDailyrecord();
		tpyDailyrecord1 = tpyDailyrecordService.getVerifyInfo(id);
		String tpyid = UserUtils.getUser().getId();
		if(UserUtils.getUser().getPersonFlag().equals("0"))	{
			tpyDailyrecord1.setTpytype("自然人特派员");
		}else if(UserUtils.getUser().getPersonFlag().equals("2"))	{
			tpyDailyrecord1.setTpytype("法人特派员");
		}else{
			tpyDailyrecord1.setTpytype("未知特派员");
		}		
		List<Sqtpy> list=tpyDailyrecordService.getFwdxList(tpyid);
		tpyDailyrecord1.setRecHelpObjList(list);		
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		//tpyDailyrecord1.setRecWrittenTime(sdf.format(new Date()));
		
		if(tpyDailyrecord1.getRecHelpobjtype().equals("需求单位")){
			model.addAttribute("tpyDailyrecord", tpyDailyrecord1);
			return "modules/dailywork/tpy/tpyDailyrecordUpdate";
		}else{
			TeamMember teamMember = new TeamMember();
			teamMember.setUserId(UserUtils.getUser().getId());
			List<TeamMember> list2 = villageService.getTeam(teamMember);	
			teamMember.setUserId(UserUtils.getUser().getId());
			tpyDailyrecord1.setTeamMemberList(list2);
			model.addAttribute("tpyDailyrecord", tpyDailyrecord1);
			return "modules/dailywork/tpy/tpyDailyrecordUpdateByVillage";
		}
	}
	
	@RequestMapping(value = "change")
	public String change( TpyDailyrecord tpyDailyrecord, RedirectAttributes redirectAttributes) {
		tpyDailyrecord.setRecStatus("inProcess");
		tpyDailyrecordService.change(tpyDailyrecord);
		addMessage(redirectAttributes, "提交成功");
		return "redirect:"+Global.getAdminPath()+"/dailywork/tpy/tpyDailyrecord/?repage";
	}
	
	@RequestMapping(value = "changetpyDailyrecord")
	public String changetpyDailyrecord( TpyDailyrecord tpyDailyrecord, RedirectAttributes redirectAttributes) {
		if(tpyDailyrecord.getRecStatus().equals("no")){
			tpyDailyrecord.setRecStatus("no");
		}else{
			tpyDailyrecord.setRecStatus("inProcess");
		}
		 String teamName = tpyDailyrecordService.getTeamName(tpyDailyrecord.getTeamId());
	     String villageName = tpyDailyrecordService.getVillageName(tpyDailyrecord.getRecHelpobjid());
	     String recHelpObj= tpyDailyrecordService.getFwdxName(tpyDailyrecord.getRecHelpobjid());
	     if(tpyDailyrecord.getRecHelpobjtype().equals("需求单位")){
	    	 tpyDailyrecord.setRecHelpObj(recHelpObj);
	     }else{
	         tpyDailyrecord.setTeamName(teamName);
	         tpyDailyrecord.setRecHelpObj(villageName);
	     }  
			tpyDailyrecord.setRecTpyid(UserUtils.getUser().getId());
			tpyDailyrecord.setRecWriter(UserUtils.getUser().getName());
			tpyDailyrecord.setRecTpyLocation(UserUtils.getOfficeList().toString());
		tpyDailyrecordService.changetpyDailyrecord(tpyDailyrecord);
		addMessage(redirectAttributes, "提交成功");
		return "redirect:"+Global.getAdminPath()+"/dailywork/tpy/tpyDailyrecord/?repage";
	}
	@RequestMapping(value = "view")
	public String view(TpyDailyrecord tpyDailyrecord, Model model) {
		
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
		if(tpyDailyrecord.getRecHelpobjtype().equals("1")){
			tpyDailyrecord.setRecHelpobjtype("需求单位");
		}else{
			tpyDailyrecord.setRecHelpobjtype("贫困村");
		}
		String imagePath[] = null;
		List list = new ArrayList();
		if(tpyDailyrecord.getDrTableImage()!=null&&!(tpyDailyrecord.getDrTableImage().equals(""))){
			imagePath = tpyDailyrecord.getDrTableImage().split("\\|");
			for(int i=0;i<imagePath.length;i++){
				String path=imagePath[i].substring(12);
				System.out.println(path);
				list.add(imagePath[i].substring(12));
			}
		}
		model.addAttribute("tpyDailyrecord", tpyDailyrecord);
		model.addAttribute("imagePathList",list);
		String recHelpobjtype = tpyDailyrecord1.getRecHelpobjtype();
		if(recHelpobjtype.equals("贫困村")){
			return "modules/dailywork/tpy/tpyDailyrecordViewByVillage";
		}else if(recHelpobjtype.equals("需求单位")){
			return "modules/dailywork/tpy/tpyDailyrecordView";
		}else{
			return "modules/dailywork/tpy/tpyDailyrecordView";
		}	
	}
	
	@RequestMapping(value = "pj")
	public String pj(TpyDailyrecord tpyDailyrecord, Model model) {
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
		if(tpyDailyrecord.getRecHelpobjtype().equals("1")){
			tpyDailyrecord.setRecHelpobjtype("需求单位");
		}else{
			tpyDailyrecord.setRecHelpobjtype("贫困村");
		}
		tpyDailyrecord.setFwdxpjr(UserUtils.getUser().getName());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		tpyDailyrecord.setFwdxpjTime(sdf.format(new Date()));
		model.addAttribute("tpyDailyrecord", tpyDailyrecord);
		return "modules/dailywork/tpy/tpyDailyrecordPjView";
	}
	
	@RequestMapping(value = "pjck")
	public String pjck(TpyDailyrecord tpyDailyrecord, Model model) {
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
		if(tpyDailyrecord.getRecHelpobjtype().equals("1")){
			tpyDailyrecord.setRecHelpobjtype("需求单位");
		}else{
			tpyDailyrecord.setRecHelpobjtype("贫困村");
		}
		model.addAttribute("tpyDailyrecord", tpyDailyrecord);
		return "modules/dailywork/tpy/tpyDailyrecordPjCk";
	}

	@RequiresPermissions("dailywork:tpy:tpyDailyrecord:edit")
	@RequestMapping(value = "save")
	public String save( @Validated TpyDailyrecord tpyDailyrecord, Model model, RedirectAttributes redirectAttributes, BindingResult br) {
		if (br.hasErrors()){			
			return pj(tpyDailyrecord, model);
        }else{ 	
        String teamName = tpyDailyrecordService.getTeamName(tpyDailyrecord.getTeamId());
        String villageName = tpyDailyrecordService.getVillageName(tpyDailyrecord.getRecHelpobjid());
        String recHelpObj= tpyDailyrecordService.getFwdxName(tpyDailyrecord.getRecHelpobjid());
        	if(tpyDailyrecord.getRecHelpobjtype().equals("需求单位")){
        	 tpyDailyrecord.setRecHelpObj(recHelpObj);
        	 tpyDailyrecord.setRecTpyLocation(UserUtils.getOfficeList().toString());
	        }else{
	        	tpyDailyrecord.setTeamName(teamName);
	        	
	            //tpyDailyrecord.setRecHelpObj(villageName);
	        }  
        System.out.println("1111111="+tpyDailyrecord.getDrTableImage());
		tpyDailyrecord.setRecTpyid(UserUtils.getUser().getId());
		tpyDailyrecord.setRecWriter(UserUtils.getUser().getName());
		tpyDailyrecord.setRecStatus("no");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		tpyDailyrecord.setRecWrittenTime(sdf.format(date));
		//tpyDailyrecord.setRecTpyLocation(UserUtils.getOfficeList().toString());
		tpyDailyrecordService.save(tpyDailyrecord);
		addMessage(redirectAttributes, "保存特派员工作日志成功");
		return "redirect:"+Global.getAdminPath()+"/dailywork/tpy/tpyDailyrecord/?repage";
		}
	}
	
	@RequestMapping(value = "savepj")
	public String savepj( @Validated TpyDailyrecord tpyDailyrecord, Model model, RedirectAttributes redirectAttributes, BindingResult br) {
		if (br.hasErrors()){			
			return form(tpyDailyrecord, model);
        }else{
        System.out.println(tpyDailyrecord.getRecHelpObj()+"++++++++++++");
		tpyDailyrecord.setFwdxpjState("1");
		tpyDailyrecordService.savepj(tpyDailyrecord);
		addMessage(redirectAttributes, "评价成功");
		return "redirect:"+Global.getAdminPath()+"/dailywork/tpy/tpyDailyrecord/ypjlist?repage";}
	}
	
	@RequiresPermissions("dailywork:tpy:tpyDailyrecord:edit")
	@RequestMapping(value = "delete")
	public String delete(TpyDailyrecord tpyDailyrecord, RedirectAttributes redirectAttributes) {
		tpyDailyrecordService.delete(tpyDailyrecord);
		addMessage(redirectAttributes, "删除特派员工作日志成功");
		return "redirect:"+Global.getAdminPath()+"/dailywork/tpy/tpyDailyrecord/?repage";
	}
	
	/*
	 * 服务对象图片上传
	 * @author lg
	 * 20171011
	 */
	@RequestMapping("uploadFwdxImage")
	@ResponseBody
	public Map<String,Object> uploadFwdxImage( @RequestParam("file") CommonsMultipartFile files[],MultipartFile file,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		 List<String> list = new ArrayList<String>();  
		    // 获得项目的路径  
		    ServletContext sc = request.getSession().getServletContext();  
		    //获取路径
		    String rootPath =  sc.getRealPath("/");
		    System.out.println(sc);
		    //String  paths = rootPath.substring(0, 3);
		    String date = sf.format(new Date());
		    String  paths = rootPath.substring(0, 3)+"uploadImg/tpyDailyRecordImg/"+UserUtils.getUser().getId()+"/fwdx"+new SimpleDateFormat("/yyyy/MM/dd").format(new Date())+"/"; 
		  //  String  paths = "D:/uploadImg/tpyDailyRecordImg/"+UserUtils.getUser().getId()+"/fwdx"+new SimpleDateFormat("/yyyy/MM/dd").format(new Date())+"/"; 
		    System.out.println("服务对象日志上传："+paths);
		    // 上传位置  
		    //String path = sc.getRealPath("/img") + "/"; // 设定文件保存的目录  
		    File f = new File(paths);  
		    if (!f.exists())  
		        f.mkdirs();  
		    for (int i = 0; i < files.length; i++) {  
		        // 获得原始文件名  
		        String fileName = files[i].getOriginalFilename();  
		        String fileTail = fileName.substring(fileName.lastIndexOf("."));
		        System.out.println("原始文件名:" + fileName);  
		        // 新文件名  
		        String newFileName = date+UUID.randomUUID()+fileTail;  
		        if (!files[i].isEmpty()) {  
		            try {  
		                FileOutputStream fos = new FileOutputStream(paths 
		                        + newFileName);  
		                InputStream in = files[i].getInputStream();  
		                int b = 0;  
		                byte[] buffer = new byte[1024]; 
		                while ((b = in.read(buffer)) != -1) {  
		                    fos.write(buffer,0,b);  
		                }  
		                fos.close();  
		                in.close();  
		            } catch (Exception e) {  
		                e.printStackTrace();  
		            }  
		        }  
		        System.out.println("上传图片到:" + paths + newFileName);  
		        list.add(paths + newFileName);  
		    }  
		    map.put("fileList", list); 
		return map;
	}
	
	/*
	 * 上传服务协议图片//日志图片上传
	 * @author lg
	 * 20171011
	 */
	@RequestMapping("uploadVilImage")
	@ResponseBody
	public Map<String,Object> uploadVilImage( @RequestParam("file") CommonsMultipartFile files[],MultipartFile file,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		 List<String> list = new ArrayList<String>();  
		    // 获得项目的路径  
		    ServletContext sc = request.getSession().getServletContext();  
		    //获取路径
		    String rootPath =  sc.getRealPath("/");
		    //String  paths = rootPath.substring(0, 3);
		    String date = sf.format(new Date());
		    String  paths = rootPath.substring(0, 3)+"uploadImg/tpyDailyRecordImg/"+UserUtils.getUser().getId()+"/village"+new SimpleDateFormat("/yyyy/MM/dd").format(new Date())+"/"; 
		    System.out.println(paths);
		    // 上传位置  
		    //String path = sc.getRealPath("/img") + "/"; // 设定文件保存的目录  
		    File f = new File(paths);  
		    if (!f.exists())  
		        f.mkdirs();  
		    for (int i = 0; i < files.length; i++) {  
		        // 获得原始文件名  
		        String fileName = files[i].getOriginalFilename();  
		        String fileTail = fileName.substring(fileName.lastIndexOf("."));
		        System.out.println("原始文件名:" + fileName);  
		        // 新文件名  
		        String newFileName = date+UUID.randomUUID()+fileTail;  
		        if (!files[i].isEmpty()) {  
		            try {  
		                FileOutputStream fos = new FileOutputStream(paths 
		                        + newFileName);  
		                InputStream in = files[i].getInputStream();  
		                int b = 0;  
		                byte[] buffer = new byte[1024]; 
		                while ((b = in.read(buffer)) != -1) {  
		                    fos.write(buffer,0,b);  
		                }  
		                fos.close();  
		                in.close();  
		            } catch (Exception e) {  
		                e.printStackTrace();  
		            }  
		        }  
		        System.out.println("上传图片到:" + paths + newFileName);  
		        list.add(paths + newFileName);  
		    }  
		    map.put("fileList", list); 
		return map;
	}
	
	@RequestMapping(value = {"getVillage"})
	public  @ResponseBody TeamMember getVillage(@RequestParam(required=false) String teamId) {
		TeamMember teamMember = new TeamMember();
		teamMember.setUserId(UserUtils.getUser().getId());
		teamMember.setTeamId(teamId);	
		System.out.println("teamId====================="+teamId); 
		//通过teamId获得团队所在区域
		String Teamarea = tpyDailyrecordService.getTeamarea(teamId);
		System.out.println("Teamarea====================="+Teamarea);  
		teamMember.setTeamArea(Teamarea);
		TeamMember teamMember1 = villageService.getVillageByTeamId(teamMember);
		return teamMember1;
	}
	
	

}