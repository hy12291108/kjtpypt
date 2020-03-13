package com.thinkgem.jeesite.modules.queryinfo.web;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
import com.thinkgem.jeesite.modules.dailywork.entity.tpy.TpyAnnualreport;
import com.thinkgem.jeesite.modules.queryinfo.entity.QueryInfoCount;
import com.thinkgem.jeesite.modules.queryinfo.entity.Zjhf;
import com.thinkgem.jeesite.modules.queryinfo.service.QueryInfoService;
import com.thinkgem.jeesite.modules.sqtpy.dao.SqtpyDao;
import com.thinkgem.jeesite.modules.sqtpy.entity.Sqtpy;
import com.thinkgem.jeesite.modules.sqtpy.service.SqtpyService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.villagemanage.entity.ServiceTeam;
import com.thinkgem.jeesite.modules.villagemanage.entity.TeamMember;
import com.thinkgem.jeesite.modules.villagemanage.entity.Village;
import com.thinkgem.jeesite.modules.villagemanage.service.VillageService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author caolei
 * 特派员
 */
@Controller
@RequestMapping(value = "${adminPath}/queryinfo/info")
public class QueryInfoController extends BaseController {
	@Autowired
	private QueryInfoService queryinfoservice;
	@Autowired
	private SqtpyService sqtpyService;
	@Autowired
	private VillageService villageService;

	
	@ModelAttribute
	public Sqtpy get() {
	      return new Sqtpy();
	}
	
	@RequestMapping(value = {"queryzrrtpyList", ""})
	public String queryzrrtpyList(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		user.setPersonFlag("0");
		Page<User> page = queryinfoservice.findTpyInfoList(new Page<User>(request, response), user);
        model.addAttribute("page", page);
		return "modules/queryinfo/CxZrrTpyList";
	}
	
	@RequestMapping(value = {"queryfrtpyList"})
	public String queryfrtpyList(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		user.setPersonFlag("2");
		Page<User> page = queryinfoservice.findTpyInfoList(new Page<User>(request, response), user);
        model.addAttribute("page", page);
		return "modules/queryinfo/CxFrTpyList";
	}
	@RequestMapping(value = {"tpyviewtj"})
	public String tpyviewtj(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "modules/queryinfo/TpyViewTj";
	}
	//统计图（从日志里获得数据）
	@RequestMapping(value = {"tpytjview"})
	public String tpytjview(QueryInfoCount queryInfoCount, HttpServletRequest request, HttpServletResponse response, Model model) {
		queryInfoCount.setUser(UserUtils.getUser());
		String bfsx = "jtjjzc";
		if(queryInfoCount != null){
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy");
			String year = sdf1.format(new Date());
			queryInfoCount.setYear(year+"年");			
			queryInfoCount.setBfsx(bfsx);
		}	
		QueryInfoCount queryInfoCount1 = new QueryInfoCount();
		queryInfoCount1 = queryinfoservice.getDyBfsxNum(queryInfoCount);
		List<String> YearList = queryinfoservice.getDrYear();
		model.addAttribute("YearList",YearList);
		return "modules/queryinfo/TpyViewTj2";
	}
	
	@RequestMapping(value = "tpytjview2")
	public @ResponseBody String[] tpytjview2(@RequestParam(required=false)String bfsx,@RequestParam(required=false)String year) {	
		QueryInfoCount queryInfoCount = new QueryInfoCount();
		queryInfoCount.setUser(UserUtils.getUser());
		queryInfoCount.setYear(year);
		queryInfoCount.setBfsx(bfsx);
		QueryInfoCount queryInfoCount1 = new QueryInfoCount();
		queryInfoCount1 = queryinfoservice.getDyBfsxNum(queryInfoCount);
		String[] array = new String[13];
		//为double类型的
		if(queryInfoCount.getBfsx().equals("jtjjzc")||queryInfoCount.getBfsx().equals("sxcz")||queryInfoCount.getBfsx().equals("ddzs")||queryInfoCount.getBfsx().equals("cls")){
			array[0] = Double.toString( queryInfoCount1.getJanuary1());
			array[1] = Double.toString( queryInfoCount1.getFebruary1());
			array[2] = Double.toString( queryInfoCount1.getMarch1());
			array[3] = Double.toString( queryInfoCount1.getApril1());
			array[4] = Double.toString( queryInfoCount1.getMay1());
			array[5] = Double.toString( queryInfoCount1.getJune1());
			array[6] = Double.toString( queryInfoCount1.getJuly1());
			array[7] = Double.toString( queryInfoCount1.getAugust1());
			array[8] = Double.toString( queryInfoCount1.getSeptember1());
			array[9] = Double.toString( queryInfoCount1.getOctober1());
			array[10] = Double.toString( queryInfoCount1.getNovember1());
			array[11] = Double.toString( queryInfoCount1.getDecember1());
			array[12] = Double.toString( queryInfoCount1.getTotal1());
		}
		//int型
		else{
			array[0] = Integer.toString( queryInfoCount1.getJanuary());
			array[1] = Integer.toString( queryInfoCount1.getFebruary());
			array[2] = Integer.toString( queryInfoCount1.getMarch());
			array[3] = Integer.toString( queryInfoCount1.getApril());
			array[4] = Integer.toString( queryInfoCount1.getMay());
			array[5] = Integer.toString( queryInfoCount1.getJune());
			array[6] = Integer.toString( queryInfoCount1.getJuly());
			array[7] = Integer.toString( queryInfoCount1.getAugust());
			array[8] = Integer.toString( queryInfoCount1.getSeptember());
			array[9] = Integer.toString( queryInfoCount1.getOctober());
			array[10] = Integer.toString( queryInfoCount1.getNovember());
			array[11] = Integer.toString( queryInfoCount1.getDecember());
			array[12] = Integer.toString( queryInfoCount1.getTotal());
		}
		//System.out.println(array.toString());
		//System.out.println("1月："+queryInfoCount1.getJanuary()+","+"2月："+queryInfoCount1.getFebruary()+","+"3月："+queryInfoCount1.getMarch()+","+"4月："+queryInfoCount1.getApril()+","+"5月："+queryInfoCount1.getMay()+","+"6月："+queryInfoCount1.getJune()+","+"7月："+queryInfoCount1.getJuly()+","+"8月："+queryInfoCount1.getAugust()+","+"9月："+queryInfoCount1.getSeptember()+","+"10月："+queryInfoCount1.getOctober()+","+"11月："+queryInfoCount1.getNovember()+","+"12月："+queryInfoCount1.getDecember()+".");
		//System.out.println("1月："+queryInfoCount1.getJanuary1()+","+"2月："+queryInfoCount1.getFebruary1()+","+"3月："+queryInfoCount1.getMarch1()+","+"4月："+queryInfoCount1.getApril1()+","+"5月："+queryInfoCount1.getMay1()+","+"6月："+queryInfoCount1.getJune1()+","+"7月："+queryInfoCount1.getJuly1()+","+"8月："+queryInfoCount1.getAugust1()+","+"9月："+queryInfoCount1.getSeptember1()+","+"10月："+queryInfoCount1.getOctober1()+","+"11月："+queryInfoCount1.getNovember1()+","+"12月："+queryInfoCount1.getDecember1()+"."); 
		return array;
	}
	@RequestMapping(value = "tpyviewtj1")
	public @ResponseBody Map<String, Integer[]> tpyviewtj1(@RequestParam(required=false)String Year) {
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy");
		String year = sdf1.format(new Date());
		String date = year+"-12-31";
		System.out.println("year2 " + date);
		QueryInfoCount queryInfoCount = queryinfoservice.getQureyInfoCount(date);	
		System.out.println("queryInfoCount.getFrTpyNum():"+queryInfoCount.getFrTpyNum());
		final Integer[] array1= new Integer[]{queryInfoCount.getZrrTpyNum(),queryInfoCount.getFrTpyNum()};
		HashMap<String, Integer[] > map = new HashMap<String, Integer[]>(){{  
			put("array1", array1);
		}};  
		
		return map;
	}
	@RequestMapping(value = "tpyviewtj2")
	public @ResponseBody Integer[] tpyviewtj2(@RequestParam(required=false)String year) {	
		System.out.println("year2 " + year);
		
		Integer[] array = new Integer[]{777,888}; 
		return array;
	}
	
	@RequestMapping(value = "tpyviewtj3")
	public @ResponseBody Map<String, Integer[]> tpyviewtj3(@RequestParam(required=false)String year) {
		String date = year+"-12-31";
		System.out.println("year2 " + date);
		QueryInfoCount queryInfoCount = queryinfoservice.getQureyInfoCount(date);	
		System.out.println("queryInfoCount.getFrTpyNum():"+queryInfoCount.getFrTpyNum());
		final Integer[] array2= new Integer[]{queryInfoCount.getZrrTpyNum(),queryInfoCount.getFrTpyNum()};
//		HashMap<String,String[]> map1 = new HashMap<String, String[]>(){{
//			put("array",[{"aa":12},{"bb":10}]);
//		}};
		
		//String jsonStr = JSONArray.fromObject(queryInfoCount).toString();
		//System.out.println("jsonStr"+jsonStr);
		HashMap<String, Integer[] > map = new HashMap<String, Integer[]>(){{  
			put("array2", array2);
		}};  
		
		return map;
	}
	@RequestMapping(value = {"querytpyInfo"})
	public String querytpyInfo(User user, Model model) {
		user = queryinfoservice.getUser(user.getId());
		if(user.getPersonFlag().equals("0")){
			user.setTpyMajor(DictUtils.getDictLabel(user.getTpyMajor(), "tpy_major", user.getTpyMajor()));
			user.setSex(DictUtils.getDictLabel(user.getSex(), "sex", user.getSex()));
			model.addAttribute("user", user);
			return "modules/queryinfo/CxZrrTpyInfo";
		}else if(user.getPersonFlag().equals("2")){
			user.setCorpType(DictUtils.getDictLabel(user.getCorpType(), "corp_type", user.getCorpType()));
			model.addAttribute("user", user);
			return "modules/queryinfo/CxFrTpyInfo";
		}else{
			model.addAttribute("user", user);
			return "modules/queryinfo/CxTpyInfo";
		}
		
	}
	
	@RequestMapping(value = {"queryxqdwList", ""})
	public String queryxqdwList(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<User> page = queryinfoservice.findXqdwInfoList(new Page<User>(request, response), user);
        model.addAttribute("page", page);
		return "modules/queryinfo/CxXqdwList";
	}
	
	@RequestMapping(value = {"queryxqdwInfo"})
	public String queryxqdwInfo(User user, Model model) {
		user = queryinfoservice.getUser(user.getId());
		model.addAttribute("user", user);
		return "modules/queryinfo/CxXqdwInfo";
	}
	
	@RequestMapping(value = {"AllShlist", ""})
	public String AllShlist(User user, HttpServletRequest request, HttpServletResponse response, Model model,Sqtpy sqtpy) {
		Page<Sqtpy> page = sqtpyService.findUser(new Page<Sqtpy>(request, response),user,sqtpy);
		System.out.println();
        model.addAttribute("page", page);
		return "modules/application/TpyShList";
	}
	@RequestMapping(value = {"Allyshlist", ""})
	public String Allyshlist(HttpServletRequest request, HttpServletResponse response, Model model,Sqtpy sqtpy) {
		System.out.println("11111111111111111111111111111");
		Page<Sqtpy> page = sqtpyService.findUser1(new Page<Sqtpy>(request, response),sqtpy);
        model.addAttribute("page", page);
		return "modules/queryinfo/BFList";
	}
	
	@RequestMapping(value = {"bfinfo", ""})
	public String bfinfo(HttpServletRequest request, HttpServletResponse response, Model model,Sqtpy sqtpy) {
		User xqdwuser = new User();
		User tpyuser = new User();
		Sqtpy sqtpy1 = new Sqtpy();
		sqtpy1 = queryinfoservice.getXqInfo(sqtpy.getId());
		xqdwuser = queryinfoservice.getUser(sqtpy1.getXqdwid());
		tpyuser = queryinfoservice.getUser(sqtpy1.getTpyid());
		model.addAttribute("sqtpy", sqtpy1);
		model.addAttribute("xqdwuser", xqdwuser);
		model.addAttribute("tpyuser", tpyuser);
		return "modules/queryinfo/BFInfo";
	}
	@RequestMapping(value = {"gettpynum", ""})
	public String gettpynum(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<User> page = queryinfoservice.GetTpyNum(new Page<User>(request, response),user);
        model.addAttribute("page", page);
		return "modules/queryinfo/tpynum";
	}
	//服务平台专家回复次数统计
	@RequestMapping(value = {"zjhftj", ""})
	public String zjhftj(QueryInfoCount queryInfoCount, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<QueryInfoCount> page = queryinfoservice.getzjhfch(new Page<QueryInfoCount>(request, response), queryInfoCount);
		model.addAttribute("page", page);
		return "modules/queryinfo/zjhftj";
	}
	/*
	 * 贫困村列表
	 * @author 刘钢
	 * 20170823
	 */
	@RequestMapping(value = {"villageList"," "})
	public String villageList(Village village, HttpServletRequest request, HttpServletResponse response, Model model){
		Page<Village> page = villageService.findVillagelist(new Page<Village>(request, response),village);
		model.addAttribute("page", page);
		return "modules/queryinfo/village/VillageList";
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
		return "modules/queryinfo/village/serviceInfo";
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
		return "modules/queryinfo/village/teamMemberInfo";
	}
	
	/*
	 * 查询特派团列表
	 * @author（刘钢）
	 * 20170828
	 */
	@RequestMapping(value = {"serviceTeamList"})
	public String serviceTeamList(ServiceTeam serviceTeam, HttpServletRequest request, HttpServletResponse response, Model model){
		Page<ServiceTeam> page = villageService.getServiceTeamList(new Page<ServiceTeam>(request, response),serviceTeam);
        model.addAttribute("page", page);
		return "modules/queryinfo/village/ServiceTeamList";
	}
	
	/*
	 * 查询服务对象协议列表 
	 */
	@RequestMapping(value = {"fwdxXyList"})
	public String fwdxXyList(Sqtpy sqtpy, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Sqtpy> page = sqtpyService.fwdxxylist(new Page<Sqtpy>(request, response),sqtpy); 
		model.addAttribute("page", page);
		return "modules/queryinfo/fwxy/FwdxXyList";
	}
	
	/*
	 * 查询贫困村团队协议列表 
	 */
	@RequestMapping(value = {"villagelistByTeam"})
	public String villagelistByTeam(TeamMember teamMember, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TeamMember> page = villageService.villagelistByTeam(new Page<TeamMember>(request, response),teamMember); 
		model.addAttribute("page", page);
		return "modules/queryinfo/fwxy/VillageListByTeam";
	}
	
	/*
	 * 查询贫困村个人协议列表 
	 */
	@RequestMapping(value = {"villagelistByMember"})
	public String villagelistByMember(TeamMember teamMember, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TeamMember> page = villageService.villagelistByMember(new Page<TeamMember>(request, response),teamMember); 
		model.addAttribute("page", page);
		return "modules/queryinfo/fwxy/VillageListByMember";
	}
}
