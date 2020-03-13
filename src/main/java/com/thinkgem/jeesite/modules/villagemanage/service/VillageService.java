/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.villagemanage.service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.kjtpypt.dao.SysAttachmentDao;
import com.thinkgem.jeesite.kjtpypt.entity.SysAttachment;
import com.thinkgem.jeesite.modules.dailywork.entity.village.VilProtocol;
import com.thinkgem.jeesite.modules.sqtpy.entity.Sqtpy;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.threearea.dao.ThreeAreaBaseDataDao;
import com.thinkgem.jeesite.modules.threearea.dao.ThreeAreaDao;
import com.thinkgem.jeesite.modules.threearea.entity.ThreeArea;
import com.thinkgem.jeesite.modules.threearea.entity.ThreeAreaBaseData;
import com.thinkgem.jeesite.modules.villagemanage.dao.ServiceTeamDao;
import com.thinkgem.jeesite.modules.villagemanage.dao.TeamMemberDao;
import com.thinkgem.jeesite.modules.villagemanage.dao.VillageDao;
import com.thinkgem.jeesite.modules.villagemanage.entity.ServiceTeam;
import com.thinkgem.jeesite.modules.villagemanage.entity.TeamMember;
import com.thinkgem.jeesite.modules.villagemanage.entity.Village;

/**
 * 贫困村Service
 * @author 刘钢
 * @version 2017-08-17
 */
@Service
@Transactional(readOnly = true)
public class VillageService extends  CrudService<VillageDao, Village> {
	@Autowired
	private VillageDao villageDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private SystemService systemService;
	@Autowired
	private ServiceTeamDao serviceTeamDao;
	@Autowired
	private TeamMemberDao teamMemberDao;
	@Autowired
	private ThreeAreaDao threeAreaDao;
	@Autowired
	private ThreeAreaBaseDataDao  threeAreaBaseDataDao;
	@Autowired
	private SysAttachmentDao sysAttachmentDao;
	/*
	 * @author 刘钢
	 * 20170818
	 */
	@Transactional(readOnly = false)
	public void saveVillage(Village village){
		String zoneId = UserUtils.getUser().getOffice().getId();
		village.preInsert();
		village.setZoneId(zoneId);
		village.setDelFlag("0");
		villageDao.insert(village);
	}	
	/*
	 * @author 刘钢
	 * 20170818
	 */
	public Page<Village> findVillage(Page<Village> page, Village village) {
		// 设置分页参数
		village.setPage(page);
		// 执行分页查询
		List<Village> village1 = villageDao.findList(village);
		page.setList(villageDao.findList(village));
		return page;
	}
	
	/*
	 * @author 刘钢
	 * 20170818
	 */
	public Page<Village> findVillagelist(Page<Village> page, Village village) {
		village.getSqlMap().put("dsf", dataScopeFilter(UserUtils.getUser().getCurrentUser(), "o", "a"));
		// 设置分页参数
		village.setPage(page);
		// 执行分页查询
		//List<Village> village1 = villageDao.findVillagelist(village);
		page.setList(villageDao.findVillagelist(village));
		return page;
	}
	
	/*
	 * 查询通过审核的自然人或法人及下派人员和三区人才
	 * @author 刘钢
	 * 20170823 
	 
	public Page<User> findUser(Page<User> page, User user) {
		user.setCheckFlag("2");
		Office office = new Office();
		office.setId(UserUtils.getUser().getOffice().getId());
		user.setOffice(office);
		// 设置分页参数
		user.setPage(page);
		User user2 = new User();
		// 执行分页查询
		//1.查询下派到贫困村和贫困村所在县的特派员
		List<User> user1= villageDao.findUserList(user);
		if(page.getPageNo()==1){
		//2.查询下派到贫困村的三区人才
		  //2.1用当前时间查询所在年度
		ThreeAreaBaseData threeAreaBaseData = threeAreaBaseDataDao.getParameter(DateUtils.getDate("yyyy-MM-dd HH-MM-SS"));
			//2.1.1判断根据当前时间是否查询到三区维护参数
		if(threeAreaBaseData!=null){
		ThreeArea threeArea = new ThreeArea();
		threeArea.setStatus("4");
		threeArea.setXpZone(user.getOffice().getId());
		threeArea.setYear(threeAreaBaseData.getYear());
		if(user.getTpyMajor()!=null){
			threeArea.setTpyMajor(user.getTpyMajor());
		}
		if(user.getName()!=null){
			threeArea.setName(user.getName());
		}
			//2.2查询下派区域的三区人才
		List<ThreeArea> threeAreas =  threeAreaDao.findXpZonePerson(threeArea);
		if(threeAreas!=null){
		for(int i=0;i<threeAreas.size();i++){
			user2 = userDao.get(threeAreas.get(i).getTpyId());
			if(user1!=null){
			//2.3判断查询出来的三区人才和user表里县里面的特派员是否重复，重复则删除
			for(int j=0;j<user1.size();j++){
				if(threeAreas.get(i).getTpyId().equals(user1.get(j).getId())){
					user1.remove(j);
					page.setCount(page.getCount()-1l);
				}
			}}
			user1.add(user2);
			page.setCount(page.getCount()+1l);
		}	
		}}}
		page.setList(user1);
		return page;
	}
	*/
	
	/*
	 * 查询通过审核的自然人或法人及下派人员和三区人才
	 * @author 刘钢
	 * 20171024
	 */
	public List<User> findUserList(User user) {
		user.setCheckFlag("2");
		Office office = new Office();
		office.setId(UserUtils.getUser().getOffice().getId());
		user.setOffice(office);
		// 设置分页参数
		User user2 = new User();
		//1.查询下派到贫困村和贫困村所在县的特派员
		List<User> user1= villageDao.findUserList(user);
		//2.查询下派到贫困村的三区人才
		  //2.1用当前时间查询所在年度
		ThreeAreaBaseData threeAreaBaseData = threeAreaBaseDataDao.getParameter(DateUtils.getDate("yyyy-MM-dd HH-MM-SS"));
			//2.1.1判断根据当前时间是否查询到三区维护参数
		if(threeAreaBaseData!=null){
		ThreeArea threeArea = new ThreeArea();
		threeArea.setStatus("4");
		threeArea.setXpZone(user.getOffice().getId());
		threeArea.setYear(threeAreaBaseData.getYear());
		if(user.getTpyMajor()!=null){
			threeArea.setTpyMajor(user.getTpyMajor());
		}
		if(user.getName()!=null){
			threeArea.setName(user.getName());
		}
			//2.2查询下派区域的三区人才
		List<ThreeArea> threeAreas =  threeAreaDao.findXpZonePerson(threeArea);
		if(threeAreas!=null){
		for(int i=0;i<threeAreas.size();i++){
			user2 = userDao.get(threeAreas.get(i).getTpyId());
			if(user1!=null){
			//2.3判断查询出来的三区人才和user表里县里面的特派员是否重复，重复则删除
			for(int j=0;j<user1.size();j++){
				if(threeAreas.get(i).getTpyId().equals(user1.get(j).getId())){
					user1.remove(j);
				}
			}}
			user2.setThreeAreaFlag("1");
			user1.add(user2);
		}	
		}}
		return user1;
	}
	
	
	
	
	
	
	/* 
	 * @author 刘钢
	 * 20170818
	 * 根据ID,获取贫困村信息
	 *
	 */
	public  Village getVillage(String id){
		return villageDao.get(id);
	}
	/* 
	 * @author 刘钢
	 * 20170831
	 * 根据ID,获取贫困村信息
	 *
	 */
	public  Village getVillageInfo(String id){
		Village village = new Village();
		try {
			village  =  villageDao.get(id);
			if(village!=null){
				List<ServiceTeam> serviceTeam = village.getServiceTeam();
				if(serviceTeam!=null){
				for(int i=0;i<serviceTeam.size();i++){
					if(serviceTeam.get(i).getDelFlag().equals("1")){
						serviceTeam.remove(i);
					}
				}}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 return village;
	}
	
	
	
	/*
	 * @author 刘钢
	 * 20170821
	 * 修改贫困村信息
	 */
	@Transactional(readOnly = false)
	public void updateVillageInfo(Village village){
		village.preUpdate();
		int a = villageDao.update(village);
		System.out.println(a);
	}
	
	
	/*
	 * @author 刘钢
	 * 20170821
	 * 删除贫困村信息
	 */
	@Transactional(readOnly = false)
	public void deleteVillageInfo(Village village){
		int a = villageDao.delete(village);
		System.out.println(a);
	}
	
	/*
	 * 插入团队信息，队员信息
	 * @author 刘钢
	 * 20170824
	 * typeId 团长对应的userId
	 */
	@Transactional(readOnly = false)
	public void insertBatch(ServiceTeam serviceTeam,String[]ids,String villageId,String typeId){
		//1.找到ID所属区域
		String areaName = UserUtils.getUser().getOffice().getArea().getName();
		System.out.println("areaNameareaNameareaNameareaNameareaName="+areaName);
		serviceTeam.setTeamArea(areaName);
		//2.生成id及插入贫困村ID(villageId)
		serviceTeam.setVillageId(villageId);
		serviceTeam.setDelFlag("0");
		serviceTeam.preInsert();
		int teamIds = serviceTeamDao.insert(serviceTeam);
		System.out.println(teamIds);
		String teamId = serviceTeam.getId();
		//3.插入团队成员
		 for (String id : ids) {
			 User user = systemService.getUser(id);
			 TeamMember teamMember =new TeamMember();
			 teamMember.setTeamId(teamId);
			 teamMember.setName(user.getName());
			 teamMember.setTpyCompany(user.getTpyCompany());
			 teamMember.setTpyTitle(user.getTpyTitle());
			 teamMember.setTpyMajor(user.getTpyMajor());
			 teamMember.setMobile(user.getMobile());
			 teamMember.setEmail(user.getEmail());
			 teamMember.setDelFlag("0");
			 teamMember.setUserId(id);
			 teamMember.setTeamprotocol("null");
			 teamMember.setMemberprotocol("null");
			 if(typeId.equals(id)){
			 teamMember.setMemberType("团长"); 
			 }else{
			 teamMember.setMemberType("团员");
			 }
			 teamMember.preInsert();
			 teamMemberDao.insert(teamMember);
		 }
	}
	
	/*
	 * 成员信息查询
	 * @author（刘钢）
	 * 20170828
	 */
	public List<TeamMember> findTeamMember(String teamId){
		 List<TeamMember> teamMember = teamMemberDao.findTeamMember(teamId);
		 return teamMember;
	}
	/*
	 * 查询特派员信息，用于模态框选择队员
	 * @author（刘钢）
	 * 20170829
	 */	
	public List<User> findUserList(String teamId,User user){
		user.setPersonFlag("0");
		user.setCheckFlag("2");
		Office office = new Office();
		office.setId(UserUtils.getUser().getOffice().getId());
		user.setOffice(office);
		User user2 = new User();
		//1.查询出来的用户列表
		List<User> users = villageDao.findUserList(user);
		//2.查询下派到贫困村的三区人才
		  //2.1用当前时间查询所在年度
		ThreeAreaBaseData threeAreaBaseData = threeAreaBaseDataDao.getParameter(DateUtils.getDate("yyyy-MM-dd HH-MM-SS"));
			//2.1.1判断根据当前时间是否查询到三区维护参数
		if(threeAreaBaseData!=null){
		ThreeArea threeArea = new ThreeArea();
		threeArea.setStatus("4");
		threeArea.setXpZone(user.getOffice().getId());
		threeArea.setYear(threeAreaBaseData.getYear());
		if(user.getTpyMajor()!=null){
			threeArea.setTpyMajor(user.getTpyMajor());
		}
		if(user.getName()!=null){
			threeArea.setName(user.getName());
		}
			//2.2查询下派区域的三区人才
		List<ThreeArea> threeAreas =  threeAreaDao.findXpZonePerson(threeArea);
		if(threeAreas!=null){
		for(int i=0;i<threeAreas.size();i++){
			user2 = userDao.get(threeAreas.get(i).getTpyId());
			if(users!=null){
			//2.3判断查询出来的三区人才和user表里县里面的特派员是否重复，重复则删除
			for(int j=0;j<users.size();j++){
				if(threeAreas.get(i).getTpyId().equals(users.get(j).getId())){
					users.remove(j);
				}
			}}
			users.add(user2);
		}	
		}}
		List<TeamMember> teamMember = teamMemberDao.findTeamMember(teamId);
		System.out.println("size1:"+teamMember.size());
		if(users!=null&&teamMember!=null){
			for(int i=0;i<teamMember.size();i++){
				for(int j=0;j<users.size();j++){
					System.out.println("size2:"+teamMember.size());
					System.out.println("size3:"+users.size());
					System.out.println("size"+j+":"+users.get(j).getId());
					if(users.get(j).getId().equals(teamMember.get(i).getUserId())){
						users.remove(j);
						break;
					}
				}
			}
		}
		return users;
	}
	/*
	 * 增加团队成员
	 * @author
	 * 刘钢
	 */
	@Transactional(readOnly = false)
	public String teamMemberAdd(String teamId,String userId[]){
		String msg = "";
		try {
			//插入团队成员
			 for (String id : userId) {
				 User user = systemService.getUser(id);
				 TeamMember teamMember =new TeamMember();
				 teamMember.setTeamId(teamId);
				 teamMember.setName(user.getName());
				 teamMember.setTpyCompany(user.getTpyCompany());
				 teamMember.setTpyTitle(user.getTpyTitle());
				 teamMember.setTpyMajor(user.getTpyMajor());
				 teamMember.setMobile(user.getMobile());
				 teamMember.setEmail(user.getEmail());
				 teamMember.setDelFlag("0");
				 teamMember.setUserId(id);
				 teamMember.setMemberType("团员");
				 teamMember.preInsert();
				 teamMemberDao.insert(teamMember);
			 }
			 msg = msg +"添加成功";
		} catch (Exception e) {
			 msg = msg+"添加失败"+e.getMessage();
		}
		return msg;
	}
	/* 
	 * 团队成员删除
	 * @author 刘刚
	 * 20170830
	 */
	@Transactional(readOnly = false)
	public String teamMemberDel(String id){
		TeamMember teamMember =new TeamMember();
		 teamMember.setId(id);
		 teamMember.setDelFlag("1");
		TeamMember teamMember1 = teamMemberDao.get(id);
		 String msg = "";
		 if(teamMember1.getMemberType().equals("团长")){
			 msg = msg+"团长不能删除！";
		 }else{
		 teamMemberDao.update(teamMember);
		 msg = msg+"删除成功！";
		 }
		 return msg;
	}
	/*
	 * 删除团队
	 * @author 刘刚
	 * 20170831
	 */
	@Transactional(readOnly = false)
	public String teamDel(String teamId){
		ServiceTeam team = new ServiceTeam();
		team.setId(teamId);
		team.setDelFlag("1");
		String msg = "";
		try {
			serviceTeamDao.update(team);
			msg = msg+"删除成功！";
		} catch (Exception e) {
			msg = msg +"删除失败："+e.getMessage();
		}
		return msg;
	}
	/**
	 * 待上传贫困村协议列表
	 * @param page
	 * @param sqtpy
	 * @return
	 */
	public Page<TeamMember> findDscxyByTeam(Page<TeamMember> page, TeamMember teamMember) {
		teamMember.setUserId(UserUtils.getUser().getId());
		// 设置分页参数
		teamMember.setPage(page);
		// 执行分页查询
		page.setList(teamMemberDao.findDscxyByTeam(teamMember));
		return page;
	}
	
	/**
	 * 已上传贫困村协议列表
	 * @param page
	 * @param sqtpy
	 * @return
	 */
	public Page<TeamMember> findYscxyByTeam(Page<TeamMember> page, TeamMember teamMember) {
		teamMember.setUserId(UserUtils.getUser().getId());
		// 设置分页参数
		teamMember.setPage(page);
		// 执行分页查询
		page.setList(teamMemberDao.findYscxyByTeam(teamMember));
		return page;
	}
	
	/**
	 * 待上传贫困村协议列表
	 * @param page
	 * @param sqtpy
	 * @return
	 */
	public Page<TeamMember> findDscxyByMember(Page<TeamMember> page, TeamMember teamMember) {
		teamMember.setUserId(UserUtils.getUser().getId());
		// 设置分页参数
		teamMember.setPage(page);
		// 执行分页查询
		page.setList(teamMemberDao.findDscxyByMember(teamMember));
		return page;
	}
	
	/**
	 * 已上传贫困村协议列表
	 * @param page
	 * @param sqtpy
	 * @return
	 */
	public Page<TeamMember> findYscxyByMember(Page<TeamMember> page, TeamMember teamMember) {
		teamMember.setUserId(UserUtils.getUser().getId());
		// 设置分页参数
		teamMember.setPage(page);
		// 执行分页查询
		page.setList(teamMemberDao.findYscxyByMember(teamMember));
		return page;
	}
	
	/* 
	 * @author 刘钢
	 * 20170818
	 * 根据ID,获取贫困村信息
	 *
	 */
	public  TeamMember getTeamMember(String teamId,String userId,String villageId,String memberType){
		TeamMember teamMember = new TeamMember();
		teamMember.setTeamId(teamId);
		teamMember.setUserId(userId);
		teamMember.setVillageId(villageId);
		teamMember.setMemberType(memberType);
		return teamMemberDao.getTeamMember(teamMember);
	}
	
	public  TeamMember getMemberInfo(TeamMember teamMember){
		return teamMemberDao.getMemberInfo(teamMember);
	}
	
	/**
	 * 待审核贫困村团协议列表
	 * @param page
	 * @param teamMember
	 * @return
	 */
	public Page<TeamMember> vilfwxyDshlistByTeam(Page<TeamMember> page, TeamMember teamMember) {
		teamMember.setZoneId(UserUtils.getUser().getOffice().getId());
		teamMember.setTeamprotocolFlag("1");
		// 设置分页参数
		teamMember.setPage(page);
		// 执行分页查询
		page.setList(teamMemberDao.vilfwxyDshlistByTeam(teamMember));
		return page;
	}
	/**
	 * 待审核贫困村团协议列表2019-04-18改
	 * @param page
	 * @param teamMember
	 * @return
	 */
//	public Page<TeamMember> selVilfwxyDshlistByTeam(Page<TeamMember> page, TeamMember teamMember) {
//		teamMember.setZoneId(UserUtils.getUser().getOffice().getId());
//		teamMember.setTeamprotocolFlag("1");
//		// 设置分页参数
//		teamMember.setPage(page);
//		// 执行分页查询
//		page.setList(teamMemberDao.vilfwxyDshlistByTeam(teamMember));
//		return page;
//	}
	
	/**
	 * 待审核贫困村团员协议列表
	 * @param page
	 * @param teamMember
	 * @return
	 */
	public Page<TeamMember> vilfwxyDshlistByMember(Page<TeamMember> page, TeamMember teamMember) {
		teamMember.setZoneId(UserUtils.getUser().getOffice().getId());
		teamMember.setMemberprotocolFlag("1");
		// 设置分页参数
		teamMember.setPage(page);
		// 执行分页查询
		page.setList(teamMemberDao.vilfwxyDshlistByMember(teamMember));
		return page;
	}
	/**
	 * 已审核贫困村团协议列表
	 * @param page
	 * @param teamMember
	 * @return
	 */
	public Page<TeamMember> vilfwxyYshlistByTeam(Page<TeamMember> page, TeamMember teamMember) {
		teamMember.setZoneId(UserUtils.getUser().getOffice().getId());
		// 设置分页参数
		teamMember.setPage(page);
		// 执行分页查询
		page.setList(teamMemberDao.vilfwxyYshlistByTeam(teamMember));
		return page;
	}
	
	/**
	 * 已审核贫困村团员协议列表
	 * @param page
	 * @param teamMember
	 * @return
	 */
	public Page<TeamMember> vilfwxyYshlistByMember(Page<TeamMember> page, TeamMember teamMember) {
		teamMember.setZoneId(UserUtils.getUser().getOffice().getId());
		// 设置分页参数
		teamMember.setPage(page);
		// 执行分页查询
		page.setList(teamMemberDao.vilfwxyYshlistByMember(teamMember));
		return page;
	}
	/**
	 * 
	 * @param village
	 */
	@Transactional(readOnly = false)
	public void changeMember(TeamMember teamMember){
		teamMemberDao.changeMember(teamMember);
		//2019-04-23修改团个人协议审核
		String atttable=teamMember.getId();
		String MemberprotocolFlag=teamMember.getMemberprotocolFlag();
	System.out.println("TeamprotocolFlag==============="+MemberprotocolFlag);
	System.out.println("atttable=============="+atttable);
		if(MemberprotocolFlag.equals("3")){
			String remarks="personalxieyi";
			sysAttachmentDao.changeTeamAttachment(atttable, remarks);	
		}
	}
	@Transactional(readOnly = false)
	public void changeTeam(TeamMember teamMember){
		teamMemberDao.changeTeam(teamMember);
	}
	//2019-04-18团服务协议审核
	@Transactional(readOnly = false)
	public void updateTeam(TeamMember teamMember){
		teamMemberDao.changeTeam(teamMember);
		String atttable=teamMember.getId();
		String TeamprotocolFlag=teamMember.getTeamprotocolFlag();
	System.out.println("TeamprotocolFlag==============="+TeamprotocolFlag);
	System.out.println("atttable=============="+atttable);
		if(TeamprotocolFlag.equals("3")){
			String remarks="tunxieyi";
			sysAttachmentDao.changeTeamAttachment(atttable, remarks);	
		}
		
	}
	public List<TeamMember> getTeam(TeamMember teamMember) {
		List <TeamMember> list = teamMemberDao.getTeam(teamMember);
		return list;
	}
	
	public TeamMember getVillageByTeamId(TeamMember teamMember) {
		TeamMember teamMember1 = teamMemberDao.getVillageByTeamId(teamMember);
		return teamMember1;
	}
	//获得特派团列表
	public Page<ServiceTeam> getServiceTeamList(Page<ServiceTeam> page,ServiceTeam serviceTeam){
		serviceTeam.getSqlMap().put("dsf", dataScopeFilter(UserUtils.getUser().getCurrentUser(), "o", "a"));
		serviceTeam.setPage(page);
		// 执行分页查询
		page.setList(serviceTeamDao.getServiceTeamList(serviceTeam));
		return page;
	}
	
	/**
	 * 查询贫困村团协议列表
	 * @param page
	 * @param teamMember
	 * @return
	 */
	public Page<TeamMember> villagelistByTeam(Page<TeamMember> page, TeamMember teamMember) {
		//teamMember.setZoneId(UserUtils.getUser().getOffice().getId());
		teamMember.getSqlMap().put("dsf", dataScopeFilter(UserUtils.getUser().getCurrentUser(), "o", "a"));
		// 设置分页参数
		teamMember.setPage(page);
		// 执行分页查询
		page.setList(teamMemberDao.villagelistByTeam(teamMember));
		return page;
	}
	
	/**
	 * 查询贫困村个人协议列表
	 * @param page
	 * @param teamMember
	 * @return
	 */
	public Page<TeamMember> villagelistByMember(Page<TeamMember> page, TeamMember teamMember) {
		//teamMember.setZoneId(UserUtils.getUser().getOffice().getId());
		teamMember.getSqlMap().put("dsf", dataScopeFilter(UserUtils.getUser().getCurrentUser(), "o", "a"));
		// 设置分页参数
		teamMember.setPage(page);
		// 执行分页查询
		page.setList(teamMemberDao.villagelistByMember(teamMember));
		return page;
	}
 }
