/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dailywork.web.village;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.dailywork.entity.tpy.TpyProtocol;
import com.thinkgem.jeesite.modules.dailywork.entity.village.VilProtocol;
import com.thinkgem.jeesite.modules.dailywork.service.village.VilProtocolService;
import com.thinkgem.jeesite.modules.sqtpy.entity.Sqtpy;
import com.thinkgem.jeesite.modules.sqtpy.service.SqtpyService;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.villagemanage.entity.TeamMember;
import com.thinkgem.jeesite.modules.villagemanage.service.VillageService;

/**
 * 贫困村服务协议Controller
 * @author Grace
 * @version 2017-08-02
 */
@Controller
@RequestMapping(value = "${adminPath}/dailywork/village/vilProtocol")
public class VilProtocolController extends BaseController {

	@Autowired
	private VilProtocolService vilProtocolService;
	
	@Autowired
	private VillageService villageService;
	
	@Autowired
	private SqtpyService SqtpyService;
	
	@Value("${TPYuploadPath}")
	private String uploadePath;
	
	@ModelAttribute
	public VilProtocol get(@RequestParam(required=false) String id) {
		VilProtocol entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = vilProtocolService.get(id);
		}
		if (entity == null){
			entity = new VilProtocol();
		}
		return entity;
	}
	
	@RequestMapping(value = {""})
	public String list(Sqtpy sqtpy, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Sqtpy> page = SqtpyService.findDscxy(new Page<Sqtpy>(request, response),sqtpy); 
		model.addAttribute("tpye","village");
		model.addAttribute("page", page);
		return "modules/dailywork/village/vilProtocolInPairs";
	}
	
	@RequestMapping(value = {"fwdxlist"})
	public String fwdxlist(Sqtpy sqtpy, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Sqtpy> page = SqtpyService.findDscxy(new Page<Sqtpy>(request, response),sqtpy); 
		model.addAttribute("tpye","village");
		model.addAttribute("page", page);
		return "modules/dailywork/village/vilProtocolInPairs";
	}
	
	@RequestMapping(value = {"vilteamlist"})
	public String vilteamlist(TeamMember teamMember, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TeamMember> page = villageService.findDscxyByTeam(new Page<TeamMember>(request, response),teamMember); 
		model.addAttribute("tpye","village");
		model.addAttribute("page", page);
		return "modules/dailywork/village/vilProtocolInPairsByVilTeam";
	}
	
	@RequestMapping(value = {"vilmemberlist"})
	public String vilmemberlist(TeamMember teamMember, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TeamMember> page = villageService.findDscxyByMember(new Page<TeamMember>(request, response),teamMember); 
		model.addAttribute("tpye","village");
		model.addAttribute("page", page);
		return "modules/dailywork/village/vilProtocolInPairsByVilMember";
	}
	
	@RequestMapping(value = {"yscvilteamlist"})
	public String yscvilteamlist(TeamMember teamMember, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TeamMember> page = villageService.findYscxyByTeam(new Page<TeamMember>(request, response),teamMember); 
		model.addAttribute("page", page);
		return "modules/dailywork/village/vilProtocolListByVilTeam";
	}
	
	@RequestMapping(value = {"yscvilmemberlist"})
	public String yscvilmemberlist(TeamMember teamMember, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TeamMember> page = villageService.findYscxyByMember(new Page<TeamMember>(request, response),teamMember); 
		model.addAttribute("page", page);
		return "modules/dailywork/village/vilProtocolListByVilMember";
	}
	
	@RequestMapping(value = {"xqdwlist"})
	public String xqdwlist(Sqtpy sqtpy, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Sqtpy> page = SqtpyService.findDscxybyxqdw(new Page<Sqtpy>(request, response),sqtpy); 
		model.addAttribute("tpye","villagebyxqdw");
		model.addAttribute("page", page);
		return "modules/dailywork/village/vilProtocolInPairsByxqdw";
	}
	
	@RequestMapping(value = {"list"})
	public String list1(Sqtpy sqtpy, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Sqtpy> page = SqtpyService.findYscxy(new Page<Sqtpy>(request, response),sqtpy); 
		model.addAttribute("tpye","village");
		model.addAttribute("page", page);
		return "modules/dailywork/village/vilProtocolList";
	}
	
	@RequestMapping(value = {"xqdwlist2"})
	public String xqdwlist2(Sqtpy sqtpy, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Sqtpy> page = SqtpyService.findYscxybyxqdw(new Page<Sqtpy>(request, response),sqtpy); 
		model.addAttribute("tpye","villagebyxqdw");
		model.addAttribute("page", page);
		return "modules/dailywork/village/vilProtocolListByxqdw";
	}
	
	@RequiresPermissions("dailywork:village:vilProtocol:view")
	@RequestMapping(value = {"list2"})
	public String list2(VilProtocol vilProtocol, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("tpye","village");
		Page<VilProtocol> page = vilProtocolService.findPage(new Page<VilProtocol>(request, response), vilProtocol); 
		model.addAttribute("page", page);
		return "modules/dailywork/village/vilProtocolList";
	}

	
	@RequestMapping(value = "form")
	public String form(VilProtocol vilProtocol, Model model,Sqtpy sqtpy) {
		String vilProtocolId=sqtpy.getFwxy();
		vilProtocol.setSysAttachmentList(vilProtocolService.findAttatchments(vilProtocolId));
		List<Sqtpy> sqtpylist = SqtpyService.findList(sqtpy);
		sqtpy=sqtpylist.get(0);
		vilProtocol.setVilName(sqtpy.getXqdwname());
		vilProtocol.setVilId(sqtpy.getXqdwid());
		vilProtocol.setVilTpyname(sqtpy.getTpyname());
		vilProtocol.setVilTpyid(sqtpy.getTpyid());
		vilProtocol.setDdId(sqtpy.getId());
		//联系人，指派人
		vilProtocol.setVilContact(sqtpy.getZpr());
		//村需求，需求单位申请原因
		vilProtocol.setVilNeeds(sqtpy.getXqdwsqReason());
		vilProtocol.setHelpRelationid(sqtpy.getId());
		vilProtocol.setVilContactphone(sqtpy.getXqdwphone());
		vilProtocol.setVilTpyphone(sqtpy.getMobile());
		vilProtocol.setVilContact(sqtpy.getCorpcorName());
		vilProtocol.setFwxyzpr(sqtpy.getFwxyzpr());
		vilProtocol.setFwxyzpTime(sqtpy.getFwxyzpTime());
		vilProtocol.setFwxyopinion(sqtpy.getFwxyopinion());
		if(sqtpy.getFwxystateflag().equals("1") )
		{
			vilProtocol.setFwxystateflag("审核通过");
		}else if(sqtpy.getFwxystateflag().equals("2"))
		{
			vilProtocol.setFwxystateflag("审核未通过");
		}else
		{
			vilProtocol.setFwxystateflag("审核中");
		}		
		model.addAttribute("vilProtocol", vilProtocol);
		return "modules/dailywork/village/vilProtocolInfo";
	}
	@RequestMapping(value = "xqdwform")
	public String xqdwform(VilProtocol vilProtocol, Model model,Sqtpy sqtpy) {
		String vilProtocolId=sqtpy.getFwxyorg();
		vilProtocol.setSysAttachmentList(vilProtocolService.findAttatchments(vilProtocolId));
		List<Sqtpy> sqtpylist = SqtpyService.findList(sqtpy);
		sqtpy=sqtpylist.get(0);
		vilProtocol.setVilName(sqtpy.getXqdwname());
		vilProtocol.setVilId(sqtpy.getXqdwid());
		vilProtocol.setVilTpyname(sqtpy.getTpyname());
		vilProtocol.setVilTpyid(sqtpy.getTpyid());
		vilProtocol.setDdId(sqtpy.getId());
		//联系人，指派人
		vilProtocol.setVilContact(sqtpy.getZpr());
		//村需求，需求单位申请原因
		vilProtocol.setVilNeeds(sqtpy.getXqdwsqReason());
		vilProtocol.setHelpRelationid(sqtpy.getId());
		model.addAttribute("vilProtocol", vilProtocol);
		return "modules/dailywork/village/vilProtocolInfoByxqdw";
	}
	
	@RequestMapping(value = "fwxyshform")
	public String fwxyshform(VilProtocol vilProtocol, Model model,Sqtpy sqtpy) {
		String vilProtocolId=sqtpy.getFwxy();
		//String vilProtocolId2 =sqtpy.getFwxyorg();	
		vilProtocol.setSysAttachmentList(vilProtocolService.findAttatchments(vilProtocolId));
		//vilProtocol.setSysAttachmentList2(vilProtocolService.findAttatchments(vilProtocolId2));
		List<Sqtpy> sqtpylist = SqtpyService.findList(sqtpy);
		sqtpy=sqtpylist.get(0);
		vilProtocol.setVilName(sqtpy.getXqdwname());
		vilProtocol.setVilId(sqtpy.getXqdwid());
		vilProtocol.setVilTpyname(sqtpy.getTpyname());
		vilProtocol.setVilTpyid(sqtpy.getTpyid());
		vilProtocol.setDdId(sqtpy.getId());
		//联系人，指派人
		vilProtocol.setVilContact(sqtpy.getZpr());
		//村需求，需求单位申请原因
		vilProtocol.setVilNeeds(sqtpy.getXqdwsqReason());
		vilProtocol.setHelpRelationid(sqtpy.getId());
		vilProtocol.setVilContactphone(sqtpy.getXqdwphone());
		vilProtocol.setVilTpyphone(sqtpy.getMobile());
		vilProtocol.setVilContact(sqtpy.getCorpcorName());
		vilProtocol.setFwxyzpr(UserUtils.getUser().getName());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		vilProtocol.setFwxyzpTime(sdf.format(new Date()));
		model.addAttribute("vilProtocol", vilProtocol);
		return "modules/dailywork/village/FwxyShInfo";
	}
	
	@RequestMapping(value = "MemberShfrom")
	public String MemberShfrom(TeamMember teamMember, Model model) {	
		String remarks="personalxieyi";
		teamMember = villageService.getMemberInfo(teamMember);
	//	teamMember.setSysAttachmentList(vilProtocolService.findAttatchments(teamMember.getMemberprotocol()));
		teamMember.setSysAttachmentList(vilProtocolService.findTunAttatchments(teamMember.getId(),remarks));
		teamMember.setCheckPerson1(UserUtils.getUser().getName());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		teamMember.setCheckTime1(sdf.format(new Date()));
		model.addAttribute("teamMember", teamMember);
		return "modules/dailywork/village/vilfwxyshInfoByMember";
	}
	
	@RequestMapping(value = "TeamShfrom")
	public String TeamShfrom(TeamMember teamMember, Model model) {	
		teamMember = villageService.getMemberInfo(teamMember);
		teamMember.setSysAttachmentList(vilProtocolService.findAttatchments(teamMember.getTeamprotocol()));
		teamMember.setCheckPerson(UserUtils.getUser().getName());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		teamMember.setCheckTime(sdf.format(new Date()));
		model.addAttribute("teamMember", teamMember);
		return "modules/dailywork/village/vilfwxyshInfoByTeam";
	}
	//2019-04-18修改查询团协议附件
	@RequestMapping(value = "SelectTeamShfrom")
	public String SelectTeamShfrom(TeamMember teamMember, Model model) {	
		String remarks="tunxieyi";
		teamMember = villageService.getMemberInfo(teamMember);
		teamMember.setSysAttachmentList(vilProtocolService.findTunAttatchments(teamMember.getId(),remarks));
		teamMember.setCheckPerson(UserUtils.getUser().getName());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		teamMember.setCheckTime(sdf.format(new Date()));
		model.addAttribute("teamMember", teamMember);
		return "modules/dailywork/village/vilfwxyshInfoByTeam";
	}
	@RequestMapping(value = "MemberShInfo")
	public String MemberShInfo(TeamMember teamMember, Model model) {	
		//2019-04-23修改
		//新加参数
		String remarks="personalxieyi";
		teamMember = villageService.getMemberInfo(teamMember);
		//原方法
		//teamMember.setSysAttachmentList(vilProtocolService.findAttatchments(teamMember.getMemberprotocol()));
		//新方法
		teamMember.setSysAttachmentList(vilProtocolService.findTunAttatchments(teamMember.getId(),remarks));
		if(teamMember.getMemberprotocolFlag().equals("1") )
		{
			teamMember.setMemberprotocolFlag("审核中");
		}else if(teamMember.getMemberprotocolFlag().equals("2"))
		{
			teamMember.setMemberprotocolFlag("已通过");
		}else if(teamMember.getMemberprotocolFlag().equals("3"))
		{
			teamMember.setMemberprotocolFlag("未通过");
		}else{
			teamMember.setMemberprotocolFlag("未提交");
		}		
		model.addAttribute("teamMember", teamMember);
		return "modules/dailywork/village/vilfwxyYshInfoByMember";
	}
	
	@RequestMapping(value = "TeamShInfo")
	public String TeamShInfo(TeamMember teamMember, Model model) {	
		teamMember = villageService.getMemberInfo(teamMember);
		teamMember.setSysAttachmentList(vilProtocolService.findAttatchments(teamMember.getTeamprotocol()));		
		if(teamMember.getTeamprotocolFlag().equals("1") )
		{
			teamMember.setTeamprotocolFlag("审核中");
		}else if(teamMember.getTeamprotocolFlag().equals("2"))
		{
			teamMember.setTeamprotocolFlag("已通过");
		}else if(teamMember.getTeamprotocolFlag().equals("3"))
		{
			teamMember.setTeamprotocolFlag("未通过");
		}else{
			teamMember.setTeamprotocolFlag("未提交");
		}	
		model.addAttribute("teamMember", teamMember);
		return "modules/dailywork/village/vilfwxyYshInfoByTeam";
	}
	
	//2019-04-18管理员团协议详细信息查看
	@RequestMapping(value = "ToviewTeamShInfo")
	public String ToviewTeamShInfo(TeamMember teamMember, Model model) {	
		String remarks="tunxieyi";
		teamMember = villageService.getMemberInfo(teamMember);
	//	teamMember.setSysAttachmentList(vilProtocolService.findAttatchments(teamMember.getTeamprotocol()));	
		teamMember.setSysAttachmentList(vilProtocolService.findTunAttatchments(teamMember.getId(),remarks));
		if(teamMember.getTeamprotocolFlag().equals("1") )
		{
			teamMember.setTeamprotocolFlag("审核中");
		}else if(teamMember.getTeamprotocolFlag().equals("2"))
		{
			teamMember.setTeamprotocolFlag("已通过");
		}else if(teamMember.getTeamprotocolFlag().equals("3"))
		{
			teamMember.setTeamprotocolFlag("未通过");
		}else{
			teamMember.setTeamprotocolFlag("未提交");
		}	
		model.addAttribute("teamMember", teamMember);
		return "modules/dailywork/village/vilfwxyYshInfoByTeam";
	}
	@RequestMapping(value = "fwxyyshform")
	public String fwxyyshform(VilProtocol vilProtocol, Model model,Sqtpy sqtpy) {
		String vilProtocolId=sqtpy.getFwxy();
		//String vilProtocolId2 =sqtpy.getFwxyorg();	
		vilProtocol.setSysAttachmentList(vilProtocolService.findAttatchments(vilProtocolId));
		//vilProtocol.setSysAttachmentList2(vilProtocolService.findAttatchments(vilProtocolId2));
		List<Sqtpy> sqtpylist = SqtpyService.findList(sqtpy);
		sqtpy=sqtpylist.get(0);
		vilProtocol.setVilName(sqtpy.getXqdwname());
		vilProtocol.setVilId(sqtpy.getXqdwid());
		vilProtocol.setVilTpyname(sqtpy.getTpyname());
		vilProtocol.setVilContactphone(sqtpy.getXqdwphone());
		vilProtocol.setVilTpyphone(sqtpy.getMobile());
		vilProtocol.setVilContact(sqtpy.getCorpcorName());
		if(sqtpy.getFwxystateflag().equals("1") )
		{
			vilProtocol.setFwxystateflag("已通过");
		}else if(sqtpy.getFwxystateflag().equals("2"))
		{
			vilProtocol.setFwxystateflag("未通过");
		}else
		{
			vilProtocol.setFwxystateflag("审核中");
		}		
		vilProtocol.setFwxyopinion(sqtpy.getFwxyopinion());
		//vilProtocol.setFwxyopinion1(sqtpy.getFwxyopinion1());
		vilProtocol.setVilTpyid(sqtpy.getTpyid());
		vilProtocol.setDdId(sqtpy.getId());
		//联系人，指派人
		vilProtocol.setVilContact(sqtpy.getZpr());
		//村需求，需求单位申请原因
		vilProtocol.setVilNeeds(sqtpy.getXqdwsqReason());
		vilProtocol.setHelpRelationid(sqtpy.getId());
		vilProtocol.setVilContactphone(sqtpy.getXqdwphone());
		vilProtocol.setVilTpyphone(sqtpy.getMobile());
		vilProtocol.setVilContact(sqtpy.getCorpcorName());
		vilProtocol.setFwxyzpr(sqtpy.getFwxyzpr());
		vilProtocol.setFwxyzpTime(sqtpy.getFwxyzpTime());
		model.addAttribute("vilProtocol", vilProtocol);
		return "modules/dailywork/village/FwxyYshInfo";
	}
	
	@RequestMapping(value = "fwxyRshform")
	public String fwxyRshform(VilProtocol vilProtocol, Model model,Sqtpy sqtpy) {
		String vilProtocolId=sqtpy.getFwxy();
		String vilProtocolId2 =sqtpy.getFwxyorg();	
		vilProtocol.setSysAttachmentList(vilProtocolService.findAttatchments(vilProtocolId));
		vilProtocol.setSysAttachmentList2(vilProtocolService.findAttatchments(vilProtocolId2));
		List<Sqtpy> sqtpylist = SqtpyService.findList(sqtpy);
		sqtpy=sqtpylist.get(0);
		vilProtocol.setVilName(sqtpy.getXqdwname());
		vilProtocol.setVilId(sqtpy.getXqdwid());
		vilProtocol.setVilTpyname(sqtpy.getTpyname());
		if(sqtpy.getFwxystateflag().equals("3")){
			vilProtocol.setFwxystateflag("0");
		}
		else
		{
			vilProtocol.setFwxystateflag(sqtpy.getFwxystateflag());
		}
		if(sqtpy.getFwxystateflag1().equals("3")){
			vilProtocol.setFwxystateflag1("0");
		}
		else
		{
			vilProtocol.setFwxystateflag1(sqtpy.getFwxystateflag1());
		}
		vilProtocol.setFwxyopinion(sqtpy.getFwxyopinion());
		vilProtocol.setFwxyopinion1(sqtpy.getFwxyopinion1());
		vilProtocol.setVilTpyid(sqtpy.getTpyid());
		vilProtocol.setDdId(sqtpy.getId());
		//联系人，指派人
		vilProtocol.setVilContact(sqtpy.getZpr());
		//村需求，需求单位申请原因
		vilProtocol.setVilNeeds(sqtpy.getXqdwsqReason());
		vilProtocol.setHelpRelationid(sqtpy.getId());
		vilProtocol.setVilContactphone(sqtpy.getXqdwphone());
		vilProtocol.setVilTpyphone(sqtpy.getMobile());
		vilProtocol.setVilContact(sqtpy.getCorpcorName());
		vilProtocol.setFwxyzpr(UserUtils.getUser().getName());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		vilProtocol.setFwxyzpTime(sdf.format(new Date()));
		model.addAttribute("vilProtocol", vilProtocol);
		return "modules/dailywork/village/FwxyRshInfo";
	}
/**
 * 添加服务对象协议信息
 * @param orgid
 * @param tpyid
 * @param model
 * @return
 */
	@RequestMapping(value = "add")
	public String add(@RequestParam String recid,@RequestParam String type,@RequestParam String vilid ,@RequestParam String tpyid,Model model) {
		VilProtocol vilProtocol=new VilProtocol();
		vilProtocol.setVilCreateRole(type);
		Sqtpy sqtpy=new Sqtpy();
		sqtpy.setId(recid);
		List<Sqtpy> sqtpylist = SqtpyService.findList(sqtpy);
		sqtpy=sqtpylist.get(0);
		vilProtocol.setVilName(sqtpy.getXqdwname());
		vilProtocol.setVilId(sqtpy.getXqdwid());
		vilProtocol.setVilTpyname(sqtpy.getTpyname());
		vilProtocol.setVilTpyid(sqtpy.getTpyid());
		vilProtocol.setDdId(recid);
		//联系人，指派人
		vilProtocol.setVilContact(sqtpy.getZpr());
		//vilProtocol.set(sqtpy.getZpr());
		//村需求，需求单位申请原因
		vilProtocol.setVilNeeds(sqtpy.getXqdwsqReason());
		vilProtocol.setHelpRelationid(recid);
		vilProtocol.setFwxystateflag(sqtpy.getFwxystateflag());
		vilProtocol.setFwxyopinion(sqtpy.getFwxyopinion());
		vilProtocol.setVilContactphone(sqtpy.getXqdwphone());
		vilProtocol.setVilTpyphone(sqtpy.getMobile());
		vilProtocol.setVilContact(sqtpy.getCorpcorName());
		model.addAttribute("vilProtocol", vilProtocol);
		return "modules/dailywork/village/vilProtocolForm";
	}
	
	/**
	 * 添加服务对象协议信息
	 * @param orgid
	 * @param tpyid
	 * @param model
	 * @return
	 */
		@RequestMapping(value = "addvil")
		public String addvilByteam(@RequestParam String userId,@RequestParam String teamId,@RequestParam String villageId ,@RequestParam String memberType1 ,Model model) {		
			String memberType = "";
			if(memberType1.equals("1")){
				memberType= "团长";
				TeamMember teamMember = villageService.getTeamMember(teamId, userId, villageId, memberType);
				model.addAttribute("teamMember", teamMember);
				return "modules/dailywork/village/vilProtocolFormbyvilteam";
			}else{
				TeamMember teamMember = villageService.getTeamMember(teamId, userId, villageId, memberType);
				model.addAttribute("teamMember", teamMember);
				return "modules/dailywork/village/vilProtocolFormbyvilmember";	
			}
			
		}
		
		
	
	@RequestMapping(value = "addxqdw")
	public String addxqdw(@RequestParam String recid,@RequestParam String type,@RequestParam String vilid ,@RequestParam String tpyid,Model model) {
		VilProtocol vilProtocol=new VilProtocol();
		vilProtocol.setVilCreateRole(type);
		Sqtpy sqtpy=new Sqtpy();
		sqtpy.setId(recid);
		List<Sqtpy> sqtpylist = SqtpyService.findList(sqtpy);
		sqtpy=sqtpylist.get(0);
		vilProtocol.setVilName(sqtpy.getXqdwname());
		vilProtocol.setVilId(sqtpy.getXqdwid());
		vilProtocol.setVilTpyname(sqtpy.getTpyname());
		vilProtocol.setVilTpyid(sqtpy.getTpyid());
		vilProtocol.setDdId(recid);
		//联系人，指派人
		vilProtocol.setVilContact(sqtpy.getZpr());
		//村需求，需求单位申请原因
		vilProtocol.setVilNeeds(sqtpy.getXqdwsqReason());
		vilProtocol.setHelpRelationid(recid);
		vilProtocol.setFwxystateflag1(sqtpy.getFwxystateflag1());
		vilProtocol.setFwxyopinion1(sqtpy.getFwxyopinion1());
		/*String vilProtocolId=vilProtocol.getId();
		vilProtocol.setSysAttachmentList(vilProtocolService.findAttatchments(vilProtocolId));*/
		model.addAttribute("vilProtocol", vilProtocol);
		return "modules/dailywork/village/vilProtocolFormByxqdw";
	}
	
	/**
	 * 保存服务对象服务协议及附件
	 * @param vilProtocol
	 * @param model
	 * @param redirectAttributes
	 * @param files
	 * @return
	 */
	@RequestMapping(value = "save")
	public String save(VilProtocol vilProtocol, Model model, RedirectAttributes redirectAttributes,@RequestParam("file") CommonsMultipartFile[] files) {
		if (!beanValidator(model, vilProtocol)){
			Sqtpy sqtpy = new Sqtpy();
			return form(vilProtocol, model,sqtpy);
		}
		if(files.length!=0){
			 vilProtocol.setVilAttach("withAttach");
		}
		//删除附件
//		System.out.println(vilProtocol.getCheckbox());
		if(vilProtocol.getCheckbox()!=null){
			System.out.println(vilProtocol.getCheckbox());
			for(String checkBox:vilProtocol.getCheckbox()){
				vilProtocolService.deleteSingelePic(checkBox);
			}
		}
		//1.保存主表
		vilProtocolService.save(vilProtocol);
		//2.保存附件
		 if(files!=null&&files.length>0){  
	            //循环获取file数组中得文件  
			 for(CommonsMultipartFile file:files){
				 //文件，关联表，关联表记录主键
				 if(file.getSize()!=0){
				 this.vilProtocolService.upload(file,"villageProtocl",vilProtocol.getId());
				 }
			 }
		 }
		//3.更新帮扶关系表
		 Sqtpy sqtpy=new Sqtpy();
		 sqtpy.setId(vilProtocol.getHelpRelationid());
		 sqtpy.setFwxyorg(vilProtocol.getId());
		addMessage(redirectAttributes, "保存贫困村服务协议成功");
		return "redirect:"+Global.getAdminPath()+"/dailywork/village/vilProtocol/?repage";
	}
	//保存贫困村团协议协议
	@RequestMapping(value = "savevilprotocol")
	public String savevilprotocol(TeamMember teamMember, Model model, RedirectAttributes redirectAttributes,@RequestParam("file") CommonsMultipartFile[] files) {
		System.out.println(files.length);
		if(files.length!=0){
			teamMember.setVilAttach("withAttach");
		}
		//2.保存附件
		//科技特派团协议附件
		 if(files!=null&&files.length>0){  
	            //循环获取file数组中得文件  
			 for(CommonsMultipartFile file:files){
				 //文件，关联表，关联表记录主键
				 if(file.getSize()!=0){
				 this.vilProtocolService.uploadTeamprotocol(file,"teamProtocl",teamMember);
				 }
			 }
		 }
		addMessage(redirectAttributes, "保存团队服务协议成功");
		return "redirect:"+Global.getAdminPath()+"/dailywork/village/vilProtocol/vilteamlist?repage";
	}
	//保存贫困村团协议协议2019-04-18修改
	@RequestMapping(value = "savevilTprotocol")
	public String savevilTprotocol(TeamMember teamMember, Model model, RedirectAttributes redirectAttributes,@RequestParam("file") CommonsMultipartFile[] files) {
		System.out.println(files.length);
		if(files.length!=0){
			teamMember.setVilAttach("withAttach");
		}
		String viltype="tunxieyi";
		//2.保存附件
		//科技特派团协议附件
		 if(files!=null&&files.length>0){  
	            //循环获取file数组中得文件  
			 for(CommonsMultipartFile file:files){
				 //文件，关联表，关联表记录主键
				 if(file.getSize()!=0){
				 this.vilProtocolService.saveTeamprotocol(file,"id",teamMember,viltype);
				 }
			 }
		 }
		addMessage(redirectAttributes, "保存团队服务协议成功");
		return "redirect:"+Global.getAdminPath()+"/dailywork/village/vilProtocol/vilteamlist?repage";
	}
	@RequestMapping(value = "savevilprotocolBymember")
	public String savevilprotocolBymember(TeamMember teamMember, Model model, RedirectAttributes redirectAttributes,@RequestParam("file") CommonsMultipartFile[] files) {
		if(files.length!=0){
			teamMember.setVilAttach("withAttach");
		}
		//2.保存附件
		 //科技特派团员附件
		 if(files!=null&&files.length>0){  
	            //循环获取file数组中得文件  
			 for(CommonsMultipartFile file:files){
				 //文件，关联表，关联表记录主键
				 if(file.getSize()!=0){
				 this.vilProtocolService.uploadMember(file,"memberprotocol",teamMember);
				 }
			 }
		 }
		addMessage(redirectAttributes, "保存个人服务协议成功");
		return "redirect:"+Global.getAdminPath()+"/dailywork/village/vilProtocol/vilmemberlist?repage";
	}
	//2019-04-23保存团个人服务协议
	@RequestMapping(value = "savevilprotocolBypersonal")
	public String savevilprotocolBypersonal(TeamMember teamMember, Model model, RedirectAttributes redirectAttributes,@RequestParam("file") CommonsMultipartFile[] files) {
		if(files.length!=0){
			teamMember.setVilAttach("withAttach");
		}
		String viltype="personalxieyi";
		//2.保存附件
		 //科技特派团员附件
		 if(files!=null&&files.length>0){  
	            //循环获取file数组中得文件  
			 for(CommonsMultipartFile file:files){
				 //文件，关联表，关联表记录主键
				 if(file.getSize()!=0){
				 this.vilProtocolService.saveTeamprotocolperson(file, "id", teamMember, viltype);
				 }
			 }
		 }
		addMessage(redirectAttributes, "保存个人服务协议成功");
		return "redirect:"+Global.getAdminPath()+"/dailywork/village/vilProtocol/vilmemberlist?repage";
	}
	
	@RequestMapping(value = "saveshvilprotocolBymember")
	public String saveshvilprotocolBymember(TeamMember teamMember, Model model, RedirectAttributes redirectAttributes) {
		villageService.changeMember(teamMember);
		addMessage(redirectAttributes, "保存贫困村服务协议成功");
		return "redirect:"+Global.getAdminPath()+"/dailywork/village/vilProtocol/vilFwxyByMember?repage";
	}
	
	@RequestMapping(value = "saveshvilprotocolByteam")
	public String saveshvilprotocolByteam(TeamMember teamMember, Model model, RedirectAttributes redirectAttributes) {

		villageService.changeTeam(teamMember);
		addMessage(redirectAttributes, "保存贫困村服务协议成功");
		return "redirect:"+Global.getAdminPath()+"/dailywork/village/vilProtocol/vilFwxyByTeam?repage";
	}
	//2019-04-18审核贫困村团队协议
	@RequestMapping(value = "shenhevilprotocolByteam")
	public String shenhevilprotocolByteam(TeamMember teamMember, Model model, RedirectAttributes redirectAttributes) {
		//villageService.changeTeam(teamMember);
		villageService.updateTeam(teamMember);
		addMessage(redirectAttributes, "审核成功");
		return "redirect:"+Global.getAdminPath()+"/dailywork/village/vilProtocol/vilFwxyByTeam?repage";
	}
	@RequestMapping(value = "vilinfobyTeam")
	public String vilinfobyTeam(TeamMember teamMember, Model model) {
		teamMember = villageService.getMemberInfo(teamMember);
		teamMember.setSysAttachmentList(vilProtocolService.findAttatchments(teamMember.getTeamprotocol()));
		if(teamMember.getTeamprotocolFlag().equals("1") )
		{
			teamMember.setTeamprotocolFlag("审核中");
		}else if(teamMember.getTeamprotocolFlag().equals("2"))
		{
			teamMember.setTeamprotocolFlag("已通过");
		}else if(teamMember.getTeamprotocolFlag().equals("3"))
		{
			teamMember.setTeamprotocolFlag("未通过");
		}else{
			teamMember.setTeamprotocolFlag("未提交");
		}				
		model.addAttribute("teamMember", teamMember);
		return "modules/dailywork/village/vilProtocolInfobyvilteam";
	}
	//2019-04-18本人查看自己的团协议详细信息
	@RequestMapping(value = "ToviewvilinfobyTeam")
	public String ToviewvilinfobyTeam(TeamMember teamMember, Model model) {
		String remarks="tunxieyi";
		teamMember = villageService.getMemberInfo(teamMember);
		//teamMember.setSysAttachmentList(vilProtocolService.findAttatchments(teamMember.getTeamprotocol()));
		teamMember.setSysAttachmentList(vilProtocolService.findTunAttatchments(teamMember.getId(), remarks));
		if(teamMember.getTeamprotocolFlag().equals("1") )
		{
			teamMember.setTeamprotocolFlag("审核中");
		}else if(teamMember.getTeamprotocolFlag().equals("2"))
		{
			teamMember.setTeamprotocolFlag("已通过");
		}else if(teamMember.getTeamprotocolFlag().equals("3"))
		{
			teamMember.setTeamprotocolFlag("未通过");
		}else{
			teamMember.setTeamprotocolFlag("未提交");
		}				
		model.addAttribute("teamMember", teamMember);
		return "modules/dailywork/village/vilProtocolInfobyvilteam";
	}
	
	@RequestMapping(value = "vilinfobyMember")
	public String vilinfobyMember(TeamMember teamMember, Model model) {
		teamMember = villageService.getMemberInfo(teamMember);
		teamMember.setSysAttachmentList(vilProtocolService.findAttatchments(teamMember.getMemberprotocol()));
		if(teamMember.getMemberprotocolFlag().equals("1") )
		{
			teamMember.setMemberprotocolFlag("审核中");
		}else if(teamMember.getMemberprotocolFlag().equals("2"))
		{
			teamMember.setMemberprotocolFlag("已通过");
		}else if(teamMember.getMemberprotocolFlag().equals("3"))
		{
			teamMember.setMemberprotocolFlag("未通过");
		}else{
			teamMember.setMemberprotocolFlag("未提交");
		}		
		model.addAttribute("teamMember", teamMember);
		return "modules/dailywork/village/vilProtocolInfobyvilmember";
	}
//2019-04-23个人查看自己的个人服务协议详细信息	
	@RequestMapping(value = "vilinfobyMemberpersion")
	public String vilinfobyMemberpersion(TeamMember teamMember, Model model) {
		String remarks="personalxieyi";
		teamMember = villageService.getMemberInfo(teamMember);
		//teamMember.setSysAttachmentList(vilProtocolService.findAttatchments(teamMember.getMemberprotocol()));
		teamMember.setSysAttachmentList(vilProtocolService.findTunAttatchments(teamMember.getId(), remarks));
		if(teamMember.getMemberprotocolFlag().equals("1") )
		{
			teamMember.setMemberprotocolFlag("审核中");
		}else if(teamMember.getMemberprotocolFlag().equals("2"))
		{
			teamMember.setMemberprotocolFlag("已通过");
		}else if(teamMember.getMemberprotocolFlag().equals("3"))
		{
			teamMember.setMemberprotocolFlag("未通过");
		}else{
			teamMember.setMemberprotocolFlag("未提交");
		}		
		model.addAttribute("teamMember", teamMember);
		return "modules/dailywork/village/vilProtocolInfobyvilmember";
	}
	@RequestMapping(value = "savexqdw")
	public String savexqdw(VilProtocol vilProtocol, Model model, RedirectAttributes redirectAttributes,@RequestParam("file") CommonsMultipartFile[] files) {
		if (!beanValidator(model, vilProtocol)){
			Sqtpy sqtpy = new Sqtpy();
			return xqdwform(vilProtocol, model,sqtpy);
		}
		if(files.length!=0){
			 vilProtocol.setVilAttach("withAttach");
		}
		//删除附件
//		System.out.println(vilProtocol.getCheckbox());
		if(vilProtocol.getCheckbox()!=null){
			System.out.println(vilProtocol.getCheckbox());
			for(String checkBox:vilProtocol.getCheckbox()){
				vilProtocolService.deleteSingelePic(checkBox);
			}
		}
		//TODO kjtpy 上传村服务协议 角色
		System.out.println(UserUtils.getRoleList());
		//1.保存主表
		vilProtocolService.savexqdw(vilProtocol);
		//2.保存附件
		 if(files!=null&&files.length>0){  
	            //循环获取file数组中得文件  
			 System.out.println("ttttttttttttttttttttt");
			 for(CommonsMultipartFile file:files){
				 //文件，关联表，关联表记录主键
				 if(file.getSize()!=0){
				 this.vilProtocolService.upload(file,"villageProtocl",vilProtocol.getId());
				 }
			 }
		 }
		//3.更新帮扶关系表
		 Sqtpy sqtpy=new Sqtpy();
		 sqtpy.setId(vilProtocol.getHelpRelationid());
		 sqtpy.setFwxyorg(vilProtocol.getId());
		addMessage(redirectAttributes, "保存贫困村服务协议成功");
		return "redirect:"+Global.getAdminPath()+"/dailywork/village/vilProtocol/xqdwlist";
	}
	@RequestMapping(value = "fwxysave")
	public String fwxysave(VilProtocol vilProtocol, Model model){
		
		vilProtocolService.fwxysave(vilProtocol);
		return "redirect:"+Global.getAdminPath()+"/dailywork/village/vilProtocol/fwxylist";		
	}
	/**
	 * 删除
	 * @param vilProtocol
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("dailywork:village:vilProtocol:edit")
	@RequestMapping(value = "delete")
	public String delete(VilProtocol vilProtocol, RedirectAttributes redirectAttributes) {
		vilProtocolService.delete(vilProtocol);
		addMessage(redirectAttributes, "删除贫困村服务协议成功");
		return "redirect:"+Global.getAdminPath()+"/dailywork/village/vilProtocol/?repage";
	}

	/**
	 * 点击预览图片/预览下载文档
	 * @param request
	 * @param response
	 * @param filename
	 * @throws Exception
	 */
	@RequestMapping(value = "showImage")
	public void showImage(HttpServletRequest request, HttpServletResponse response ,@RequestParam String filename ,@RequestParam String fileType) throws Exception {
		if(fileType.equals("pic")){
			response.setContentType("text/html; charset=UTF-8");
			response.setContentType("image/jpeg");
			String path=uploadePath+filename;
			FileInputStream fis = new FileInputStream(path);
			OutputStream os = response.getOutputStream();
			try {
				int count = 0;
				byte[] buffer = new byte[1024 * 1024];
				while ((count = fis.read(buffer)) != -1)
					os.write(buffer, 0, count);
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (os != null)
					os.close();
				if (fis != null)
					fis.close();
			}
		}else{
			this.vilProtocolService.downLoad(response,request,filename);
		}
		
	}
	
	@RequestMapping(value = "deleteImage")
	public void deleteImage(HttpServletRequest request, HttpServletResponse response ,@RequestParam String filename)  {
		vilProtocolService.deleteSingelePic(filename);
	}
	/**
	 * 特派员服务协议书报送
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("dailywork:tpy:view")
	@RequestMapping(value = {"tpyProtocol"})
	public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
//		Page<Data> page = dataService.findPage(new Page<Data>(request, response), data); 
//		model.addAttribute("page", page);
		return "modules/dailywork/agent/protocol";
	}

	@RequiresPermissions("dailywork:tpy:view")
	@RequestMapping(value = {"tpyProtocol2"})
	public String list2(TpyProtocol tpyProtocol,HttpServletRequest request, HttpServletResponse response, Model model) {
//		Page<Data> page = dataService.findPage(new Page<Data>(request, response), data); 
//		model.addAttribute("page", page);
//		return "modules/dailywork/agent/protocol";
		model.addAttribute("tpyProtocol", tpyProtocol);
		return "modules/dailywork/tpy/tpyProtocolAdd";
	}

	@RequestMapping(value = {"fwxylist"})
	public String fwxylist(Sqtpy sqtpy, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Sqtpy> page = SqtpyService.fwxyshlist(new Page<Sqtpy>(request, response),sqtpy); 
		model.addAttribute("page", page);
		return "modules/dailywork/village/FwxyShList";
	}
	
	@RequestMapping(value = {"vilFwxyByTeam"})
	public String vilFwxyByTeam(TeamMember teamMember, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TeamMember> page = villageService.vilfwxyDshlistByTeam(new Page<TeamMember>(request, response),teamMember);
		model.addAttribute("page", page);
		return "modules/dailywork/village/vilfwxyshListByTeam";
	}
	//2019-04-18贫困村团协议待审核数据查询
//	@RequestMapping(value = {"selectvilFwxyByTeam"})
//	public String selectvilFwxyByTeam(TeamMember teamMember, HttpServletRequest request, HttpServletResponse response, Model model) {
//		Page<TeamMember> page = villageService.selVilfwxyDshlistByTeam(new Page<TeamMember>(request, response),teamMember);
//		model.addAttribute("page", page);
//		return "modules/dailywork/village/vilfwxyshListByTeam";
//	}
	@RequestMapping(value = {"vilFwxyByMember"})
	public String vilFwxyByMember(TeamMember teamMember, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TeamMember> page = villageService.vilfwxyDshlistByMember(new Page<TeamMember>(request, response),teamMember); 
		model.addAttribute("page", page);
		return "modules/dailywork/village/vilfwxyshListByMember";
	}
	@RequestMapping(value = {"vilYshFwxyByTeam"})
	public String vilYshFwxyByTeam(TeamMember teamMember, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TeamMember> page = villageService.vilfwxyYshlistByTeam(new Page<TeamMember>(request, response),teamMember); 
		model.addAttribute("page", page);
		return "modules/dailywork/village/vilfwxyYshListByTeam";
	}
	
	@RequestMapping(value = {"vilYshFwxyByMember"})
	public String vilYshFwxyByMember(TeamMember teamMember, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TeamMember> page = villageService.vilfwxyYshlistByMember(new Page<TeamMember>(request, response),teamMember);
		model.addAttribute("page", page);
		return "modules/dailywork/village/vilfwxyYshListByMember";
	}
	
	@RequestMapping(value = {"fwxyyshlist"})
	public String fwxyyshlist(Sqtpy sqtpy, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Sqtpy> page = SqtpyService.fwxyyshlist(new Page<Sqtpy>(request, response),sqtpy); 
		model.addAttribute("page", page);
		return "modules/dailywork/village/FwxyYshList";
	}
	
}