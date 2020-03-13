package com.thinkgem.jeesite.modules.sqtpy.web;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sqtpy.dao.SqtpyDao;
import com.thinkgem.jeesite.modules.sqtpy.entity.Sqtpy;
import com.thinkgem.jeesite.modules.sqtpy.service.SqtpyService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import freemarker.template.utility.DateUtil;
/**
 * 
 * @author caolei
 * 特派员
 */
@Controller
@RequestMapping(value = "${adminPath}/sqtpy/tpy")
public class TpyController extends BaseController {
	@Autowired
	private SqtpyService sqtpyService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private OfficeService officeService;

	/**
	 * 申请科技特派员主页面
	 * @param user
	 * @param model
	 * @return
	 */
	@ModelAttribute
	public Sqtpy get() {
	      return new Sqtpy();
	}
	@ModelAttribute
	public User get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return systemService.getUser(id);
		}else{
			return new User();
		}
	}
	@RequestMapping(value = {"Demo"})
	public String Demo(User user, Model model) {
		return "modules/application/Demo";
	}
	
	@RequestMapping(value = {"majorinfo"})
	public String majorinfo(User user, Model model) {
		String officeid = UserUtils.getUser().getOffice().getId();		
		List<String> list = sqtpyService.majorlist(officeid);
		user.setPersonFlag("0");
        model.addAttribute("tpymajorlist", list);
        model.addAttribute("user", user);
		return "modules/application/TpyMajorInfo";
	}
	

	@RequestMapping(value = {"form"})
	public String form(User user, Model model) {
		user = sqtpyService.getTpy(user);
		user.setTpyMajor(DictUtils.getDictLabel(user.getTpyMajor(), "tpy_major", user.getTpyMajor()));
		user.setTpyQulification(DictUtils.getDictLabel(user.getTpyQulification(), "tpy_qulification", user.getTpyQulification()));
		user.setTpyTitle(DictUtils.getDictLabel(user.getTpyTitle(), "tpy_title", user.getTpyTitle()));
		model.addAttribute("user", user);
		return "modules/application/TpySqInfo";
	}
	@RequestMapping(value = {"frform"})
	public String frform(User user, Model model) {
		user = sqtpyService.getTpy(user);
		user.setCorpType(DictUtils.getDictLabel(user.getCorpType(), "corp_type", user.getCorpType()));
		model.addAttribute("user", user);
		return "modules/application/FrTpySqInfo";
	}
	
	@RequestMapping(value = {"change"})
	public String change(Sqtpy sqtpy, Model model) {
		if(sqtpy.getPersonFlag().equals("0")){
			sqtpy = sqtpyService.getTpyInfo(sqtpy);
			model.addAttribute("sqtpy", sqtpy);
			return "modules/application/XgTpySqInfo";
		}else{
			sqtpy = sqtpyService.getTpyInfo(sqtpy);
			User user = new User();
			user.setId(sqtpy.getTpyid());
			user = sqtpyService.getTpy(user);
			user.setCorpType(DictUtils.getDictLabel(user.getCorpType(), "corp_type", user.getCorpType()));
			sqtpy.setTpyinfo(user);
			
			model.addAttribute("sqtpy", sqtpy);
			return "modules/application/XgFrTpySqInfo";
		}
		
	}
	
	@RequestMapping(value = {"changeTpy"})
	public String changeZrrTpy(Sqtpy sqtpy,RedirectAttributes redirectAttributes) {
		if(sqtpy.getState().equals("0")){
			sqtpy.setState("0");
		}else if(sqtpy.getState().equals("3")){
			sqtpy.setState("1");
		}try {
			sqtpyService.changeTpy(sqtpy);
			 addMessage(redirectAttributes, "修改成功！");
		} catch (Exception e) {
			addMessage(redirectAttributes, "修改失败！");
		}	 
		 return "redirect:" + adminPath + "/sqtpy/tpy/Xqlist";
	}
	/**
	 * 获取已经申请或者正在派遣的特派员列表
	 * @param user
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value = {"list", ""})
	public String list(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		user.setOffice(UserUtils.getUser().getOffice());
		user.setPersonFlag("0");
		Page<User> page = sqtpyService.findTpy(new Page<User>(request, response),user);
        model.addAttribute("page", page);
		return "modules/application/TpySqList";
	}

	
	@RequestMapping(value = {"frlist"})
	public String frlist(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		user.setOffice(UserUtils.getUser().getOffice());
		user.setPersonFlag("2");
		Page<User> page = sqtpyService.findTpy(new Page<User>(request, response),user);
        model.addAttribute("page", page);
		return "modules/application/FrTpySqList";
	}
	
	@RequestMapping(value = {"Shlist"})
	public String Shlist(Sqtpy sqtpy, HttpServletRequest request, HttpServletResponse response, Model model) {
		sqtpy.setOffice(UserUtils.getUser().getOffice());
		sqtpy.setState("0");
		if(sqtpy.getIsmajor()!=null){
			if(sqtpy.getIsmajor().equals("2")){
				sqtpy.setIsmajor("");
			}
		}
		Page<Sqtpy> page = sqtpyService.findDyUser(new Page<Sqtpy>(request, response),sqtpy);
        model.addAttribute("page", page);
		return "modules/application/TpyShList";
	}
	@RequestMapping(value = {"yshlist", ""})
	public String yshlist(Sqtpy sqtpy, HttpServletRequest request, HttpServletResponse response, Model model) {
		sqtpy.setOffice(UserUtils.getUser().getOffice());
		sqtpy.setState("0");
		if(sqtpy.getIsmajor()!=null){
			if(sqtpy.getIsmajor().equals("2")){
				sqtpy.setIsmajor("");
			}
		}
		Page<Sqtpy> page = sqtpyService.findDyUser1(new Page<Sqtpy>(request, response),sqtpy);
        model.addAttribute("page", page);
		return "modules/application/TpyYshList";
	}
	
	@RequestMapping(value = "SaveSqtpy")
	public String SaveSqtpy(Sqtpy sqtpy,User user,HttpServletRequest request, Model model, RedirectAttributes redirectAttributes){
		sqtpy.setXqdwname(UserUtils.getUser().getName()) ;
		sqtpy.setXqdwid(UserUtils.getUser().getId());
		sqtpy.setTpyname(user.getName());
		sqtpy.setTpyid(user.getId());		
		sqtpy.setXqdwphone(UserUtils.getUser().getCorpCorPhone());
		if(user.getPersonFlag().equals("0")){
			sqtpy.setZc(user.getTpyTitle());
			sqtpy.setZy(user.getTpyMajor());
			sqtpy.setMobile(user.getMobile());
			sqtpy.setTechspecial(user.getTpySpecial());
			sqtpy.setCompany(user.getTpyCompany());
			sqtpy.setPersonFlag("0");
		}else
		{
			sqtpy.setMobile(UserUtils.getUser().getCorpCorPhone());
			sqtpy.setPersonFlag("2");
		}
		sqtpy.setStarTime(user.getStarTime());
		sqtpy.setEndTime(user.getEndTime());
		sqtpy.setXqdwsqReason(user.getTpyReason());
		sqtpy.setFwxy("null");
		sqtpy.setFwxyorg("null");
		sqtpy.setState("0");
		sqtpy.setIsmajor("0");
		sqtpyService.saveXqdwSqTpy(sqtpy);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + adminPath + "/sqtpy/tpy/Xqlist";		
	}
	
	@RequestMapping(value = "SaveSqtpybymajor")
	public String SaveSqtpybymajor(Sqtpy sqtpy,User user,HttpServletRequest request, Model model, RedirectAttributes redirectAttributes){
		sqtpy.setXqdwname(UserUtils.getUser().getName()) ;
		sqtpy.setXqdwid(UserUtils.getUser().getId());
		sqtpy.setZy(user.getTpyMajor());
		sqtpy.setStarTime(user.getStarTime());
		sqtpy.setEndTime(user.getEndTime());
		sqtpy.setXqdwphone(UserUtils.getUser().getCorpCorPhone());
		sqtpy.setXqdwsqReason(user.getTpyReason());
		sqtpy.setFwxy("null");
		sqtpy.setFwxyorg("null");
		sqtpy.setState("0");
		sqtpy.setIsmajor("1");
		sqtpy.setPersonFlag(user.getPersonFlag());
		sqtpyService.saveXqdwSqTpy(sqtpy);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + adminPath + "/sqtpy/tpy/Xqlist";	
	}
	
	@RequestMapping("Xqlist")
	public String Xqlist(Sqtpy sqtpy, HttpServletRequest request, HttpServletResponse response, Model model) {
		sqtpy.setXqdwid(UserUtils.getUser().getId());
		sqtpy.setState("0");
		if(sqtpy.getIsmajor()!=null){
			if(sqtpy.getIsmajor().equals("2")){
				sqtpy.setIsmajor("");
			}
		}
		Page<Sqtpy> page = sqtpyService.xqdwsqList(new Page<Sqtpy>(request, response), sqtpy);
        model.addAttribute("page", page);
		return "modules/application/TpyList";
	}
	
	@RequestMapping("YshXqlist")
	public String YshXqlist(Sqtpy sqtpy, HttpServletRequest request, HttpServletResponse response, Model model) {
		sqtpy.setXqdwid(UserUtils.getUser().getId());
		sqtpy.setState("2");
		if(sqtpy.getIsmajor()!=null){
			if(sqtpy.getIsmajor().equals("2")){
				sqtpy.setIsmajor("");
			}
		}
		Page<Sqtpy> page = sqtpyService.xqdwsqListYsh(new Page<Sqtpy>(request, response), sqtpy);
        model.addAttribute("page", page);
		return "modules/application/TpyListYsh";
	}
	
	@RequestMapping(value = {"updateTpyState"})
	public String updateTpyState(Sqtpy sqtpy) {
		sqtpyService.updateTpySqInfo(sqtpy);			
		return "redirect:" + adminPath + "/sqtpy/tpy/Xqlist";
	}
	
	@RequestMapping(value = {"deleteTpySqInfo"})
	public String deleteTpySqInfo(Sqtpy sqtpy) {
		sqtpyService.deleteTpySqInfo(sqtpy.getId());			
		return "redirect:" + adminPath + "/sqtpy/tpy/Xqlist";
	}
	
	@RequestMapping(value = {"TpySh"})
	public String TpySh(User user, Model model) {
		return "modules/application/TpySh";
	}
	
	@RequestMapping(value = {"selectShInfo"})
	public String selectShInfo(Sqtpy sqtpy, Model model) {
		sqtpy = sqtpyService.getShTpy(sqtpy);
		String officeid = UserUtils.getUser().getOffice().getId();
		String major = DictUtils.getDictValue(sqtpy.getZy(), "tpy_major", sqtpy.getZy());
		List<User> list = sqtpyService.TpyListbyMajor(officeid, major);
		System.out.println("ssss"+list.size());
		if(sqtpy.getPersonFlag().equals("0")){
			model.addAttribute("tpylistbymajor", list);
			model.addAttribute("sqtpy", sqtpy);		
			return "modules/application/ShTpyInfo";
		}else
		{
			model.addAttribute("sqtpy", sqtpy);		
			return "modules/application/ShFrTpyInfo";
		}        
	}
	
	@RequestMapping(value = {"selectShInfo1"})
	public String selectShInfo1(Sqtpy sqtpy, Model model) {	
		sqtpy = sqtpyService.getCkTpy(sqtpy);
		if(sqtpy.getPersonFlag().equals("0")){
			model.addAttribute("sqtpy", sqtpy);		
			return "modules/application/YshInfo";
		}else{
			model.addAttribute("sqtpy", sqtpy);		
			return "modules/application/FrYshInfo";
		}
		
	}
	
	@RequestMapping(value = {"saveshtpy"})
	public String saveshtpy(Sqtpy sqtpy,String action, RedirectAttributes redirectAttributes) {
		
		if(action.equals("审核通过"))
		{
			addMessage(redirectAttributes, "保存成功");
			sqtpyService.updateTpySqInfo1(sqtpy);
			return "redirect:" + adminPath + "/sqtpy/tpy/yshlist";
		}else if(action.equals("审核不通过"))
		{
			addMessage(redirectAttributes, "保存成功");
			sqtpyService.updateTpySqInfo2(sqtpy);
			return "redirect:" + adminPath + "/sqtpy/tpy/yshlist";
		}else
		{
			addMessage(redirectAttributes, "保存成功");
			return "redirect:" + adminPath + "/sqtpy/tpy/yshlist";
		}							
	}
	@RequestMapping(value = {"saveshtpyqbtg"})
	public String saveshtpyqbtg(Sqtpy sqtpy) {
					
		return "redirect:" + adminPath + "/sqtpy/tpy/Shlist";
	}
	@RequestMapping(value = {"choosetpy"})
	public  @ResponseBody Sqtpy choosetpy(@RequestParam(required=false) String tpyid) {
		Sqtpy sqtpy = sqtpyService.selectTpy1(tpyid);
		System.out.println("-------"+sqtpy.getZy()+"----"+sqtpy.getZc());
		sqtpy.setZy(DictUtils.getDictLabel(sqtpy.getZy(), "tpy_major", sqtpy.getZy()));
		sqtpy.setZc(DictUtils.getDictLabel(sqtpy.getZc(), "tpy_title", sqtpy.getZc()));
		return sqtpy;
	}
	
	/**
	 * 根据特派员服务区域id查询该区域下的需求单位
	 * @param user
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * 2018-07-31
	 * by gz
	 */
	
	@RequestMapping(value = {"Xqdwlist", ""})
	public String Xqdwlist(HttpServletRequest request, HttpServletResponse response, Model model) {
		Office office = new Office();
		office = officeService.get(UserUtils.getUser().getTpyXpFlag());
		User user = new User();
		user.setOffice(office);
		Page<User> page = sqtpyService.findXqdw(new Page<User>(request, response),user);
        model.addAttribute("page", page);
		return "modules/application/XqdwList";
	}
	
	/**
	 * 申请需求单位
	 * @param user
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * 2018-07-31
	 * by gz
	 */
	@RequestMapping(value = {"Sqxqdw"})
	public String Sqxqdw(User user, Model model) {
		user = sqtpyService.getXqdw(user);
		model.addAttribute("user", user);
		return "modules/application/TpySqXqdwInfo";
		
	}
	/**
	 * 保存特派员申请的需求单位信息
	 * @param user
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * 2018-07-31
	 * by gz
	 */
	@RequestMapping(value = "SaveTpySqXqdw")
	public String SaveTpySqXqdw(Sqtpy sqtpy,User user,HttpServletRequest request, Model model, RedirectAttributes redirectAttributes){
		sqtpy.setXqdwname(user.getName()) ;
		sqtpy.setXqdwid(user.getId());	
		sqtpy.setTpyname(UserUtils.getUser().getName());
		sqtpy.setTpyid(UserUtils.getUser().getId());
		if(user.getCorpCorPhone().length()>11){
			sqtpy.setXqdwphone(user.getCorpCorPhone().substring(0,11));
		}else{
			sqtpy.setXqdwphone(user.getCorpCorPhone());
		}
		
		if(UserUtils.getUser().getPersonFlag().equals("0")){
			sqtpy.setZc(UserUtils.getUser().getTpyTitle());
			sqtpy.setZy(UserUtils.getUser().getTpyMajor());
			sqtpy.setMobile(UserUtils.getUser().getMobile());
			sqtpy.setTechspecial(UserUtils.getUser().getTpySpecial());
			sqtpy.setCompany(UserUtils.getUser().getTpyCompany());
			sqtpy.setPersonFlag("0");
		}else
		{
			if(user.getCorpCorPhone().length()>11){
				sqtpy.setMobile(user.getCorpCorPhone().substring(0,11));
			}else{
				sqtpy.setMobile(user.getCorpCorPhone());
			}
			sqtpy.setPersonFlag("2");
		}
		sqtpy.setStarTime(user.getStarTime());
		sqtpy.setEndTime(user.getEndTime());
		sqtpy.setXqdwsqReason(user.getTpyReason());
		sqtpy.setFwxy("null");
		sqtpy.setFwxyorg("null");
		sqtpy.setState("1");
		sqtpy.setIsmajor("0");
		sqtpyService.saveXqdwSqTpy(sqtpy);
		addMessage(redirectAttributes, "保存成功");	
	    return "redirect:" + adminPath + "/sqtpy/tpy/Xqdwlist";
		
	}
	
	@RequestMapping(value = {"selectbfgx"})
	public  @ResponseBody List<Sqtpy> selectbfgx(@RequestParam(required=false) String xqdwid) {
		String tpyid = UserUtils.getUser().getId();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String nowDate = df.format(new Date());	
		System.out.println("nowDate="+nowDate);
		List<Sqtpy> list = sqtpyService.selectbfgx(tpyid,xqdwid,nowDate);
		return list;
	}
}
