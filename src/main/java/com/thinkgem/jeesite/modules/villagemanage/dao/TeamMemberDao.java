/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.villagemanage.dao;


import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sqtpy.entity.Sqtpy;
import com.thinkgem.jeesite.modules.villagemanage.entity.TeamMember;

/**
 * 團隊成員接口
 * @author 刘钢
 * @version 2017-08-22
 */
@MyBatisDao
public interface TeamMemberDao extends CrudDao<TeamMember> {
	
	/**
	 * 根据teamId 查询队员信息
	 * @param entity
	 * @return
	 */
	public List<TeamMember> findTeamMember (String teamId);
	
	public List<TeamMember> findDscxyByTeam(TeamMember teamMember);
	
	public List<TeamMember> findYscxyByTeam(TeamMember teamMember);
	
	public List<TeamMember> findDscxyByMember(TeamMember teamMember);
	
	public List<TeamMember> findYscxyByMember(TeamMember teamMember);
	
	public List<TeamMember> vilfwxyDshlistByTeam(TeamMember teamMember);
	//2019-04-18贫困村团协议待审核查询
	public List<TeamMember> selVilfwxyDshlistByTeam(TeamMember teamMember);
	public List<TeamMember> vilfwxyDshlistByTeamByApp(TeamMember teamMember);
	
	public List<TeamMember> vilfwxyDshlistByMember(TeamMember teamMember);
	
	public List<TeamMember> vilfwxyYshlistByTeam(TeamMember teamMember);
	
	public List<TeamMember> vilfwxyYshlistByMember(TeamMember teamMember);
	public List<TeamMember> vilfwxyYshlistByMemberByApp(TeamMember teamMember);
	
	public TeamMember getTeamMember(TeamMember teamMember);
	public TeamMember getMemberInfo(TeamMember teamMember);
	public int updateTeamprotocol(TeamMember teamMember); 
	public int updateMemberprotocol(TeamMember teamMember); 
	public int changeMember(TeamMember teamMember);
	public int changeTeam(TeamMember teamMember);
	
	public List<TeamMember> getTeam(TeamMember teamMember);
	
	public TeamMember getVillageByTeamId(TeamMember teamMember);
	//查询贫困村团队协议列表
	public List<TeamMember> villagelistByTeam(TeamMember teamMember);
	
	//查询贫困村团队协议列表
	public List<TeamMember> villagelistByMember(TeamMember teamMember);
}
