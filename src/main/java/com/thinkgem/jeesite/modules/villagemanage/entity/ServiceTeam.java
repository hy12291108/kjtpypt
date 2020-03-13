package com.thinkgem.jeesite.modules.villagemanage.entity;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;

public class ServiceTeam extends DataEntity<ServiceTeam> {
	private static final long serialVersionUID = 1L;
	private String teamName;// 团队名称
	private String teamArea;// 所属区域
	private String serviceCyfx;// 服务方向
	private String startTime;//开始时间
	private String endTime;//结束时间
	private String villageId;//贫困村id
	private List<TeamMember> teamMember;//团队成员
	private Office office;//所属区域
	private Village village;//贫困村
	
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	public Village getVillage() {
		return village;
	}
	public void setVillage(Village village) {
		this.village = village;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getTeamArea() {
		return teamArea;
	}
	public void setTeamArea(String teamArea) {
		this.teamArea = teamArea;
	}
	public String getServiceCyfx() {
		return serviceCyfx;
	}
	public void setServiceCyfx(String serviceCyfx) {
		this.serviceCyfx = serviceCyfx;
	}

	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	public List<TeamMember> getTeamMember() {
		return teamMember;
	}
	public void setTeamMember(List<TeamMember> teamMember) {
		this.teamMember = teamMember;
	}
}
