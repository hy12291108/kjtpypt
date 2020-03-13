package com.thinkgem.jeesite.modules.villagemanage.entity;

import java.util.ArrayList;
import java.util.List;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.kjtpypt.entity.SysAttachment;
import com.thinkgem.jeesite.modules.sys.entity.Office;

public class TeamMember extends DataEntity<TeamMember> {
	private static final long serialVersionUID = 1L;
	private String teamId;//团队id
	private String name;//姓名
	private String tpyCompany;//单位
	private String tpyTitle;//职称
	private String tpyMajor;//专业
	private String mobile;//手机
	private String email;//邮箱
	private String memberType;//成员类型 0 团员 1 团长
	private String userId;//特派员ID
	private String teamName;// 团队名称
	private String teamArea;// 所属区域
	private String serviceCyfx;// 服务方向
	private String startTime;//开始时间
	private String endTime;//结束时间
	private String villageId;//贫困村id
	private String villageName;//村名
	private String secretaryName;//村书记
	private String secretaryPhone;//村书记电话
	private String deputy;//联系人
	private String deputyPhone;//联系人电话
	private int houseNumber;//户数
	private int population;//人口
	private int poorNumber;//贫困户数
	private int poorPopulation;//贫困人口
	private String estateInfo;//产业现状
	private String scienceNeed;//科技需求
	private String zoneId;//所属区域id
	private String teamprotocol;//团队协议
	private String memberprotocol;//团员协议
	private String teamprotocolFlag;//团队协议状态0:未上传，1已上传，2，审核成功，3审核失败
	private String memberprotocolFlag;//团员协议状态0:未上传，1已上传，2，审核成功，3审核失败
	private String vilAttach;		// 附件
	private List<SysAttachment> sysAttachmentList=new ArrayList<SysAttachment>();//
	private List<SysAttachment> sysAttachmentList2=new ArrayList<SysAttachment>();//	
	private String checkPerson;//团队审核人
	private String checkTime;//团队审核时间
	private String checkOpinion;//团队审核意见
	private String checkPerson1;//团员审核人
	private String checkTime1;//团员审核时间
	private String checkOpinion1;//团员审核意见
	private Office office;
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public String getCheckPerson1() {
		return checkPerson1;
	}

	public void setCheckPerson1(String checkPerson1) {
		this.checkPerson1 = checkPerson1;
	}

	public String getCheckTime1() {
		return checkTime1;
	}

	public void setCheckTime1(String checkTime1) {
		this.checkTime1 = checkTime1;
	}

	public String getCheckOpinion1() {
		return checkOpinion1;
	}

	public void setCheckOpinion1(String checkOpinion1) {
		this.checkOpinion1 = checkOpinion1;
	}

	public String getCheckPerson() {
		return checkPerson;
	}

	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public String getCheckOpinion() {
		return checkOpinion;
	}

	public void setCheckOpinion(String checkOpinion) {
		this.checkOpinion = checkOpinion;
	}

	public String getTeamprotocolFlag() {
		return teamprotocolFlag;
	}

	public void setTeamprotocolFlag(String teamprotocolFlag) {
		this.teamprotocolFlag = teamprotocolFlag;
	}

	public String getMemberprotocolFlag() {
		return memberprotocolFlag;
	}

	public void setMemberprotocolFlag(String memberprotocolFlag) {
		this.memberprotocolFlag = memberprotocolFlag;
	}

	public String getVilAttach() {
		return vilAttach;
	}

	public void setVilAttach(String vilAttach) {
		this.vilAttach = vilAttach;
	}

	public List<SysAttachment> getSysAttachmentList() {
		return sysAttachmentList;
	}

	public void setSysAttachmentList(List<SysAttachment> sysAttachmentList) {
		this.sysAttachmentList = sysAttachmentList;
	}

	public List<SysAttachment> getSysAttachmentList2() {
		return sysAttachmentList2;
	}

	public void setSysAttachmentList2(List<SysAttachment> sysAttachmentList2) {
		this.sysAttachmentList2 = sysAttachmentList2;
	}

	public String getTeamprotocol() {
		return teamprotocol;
	}

	public void setTeamprotocol(String teamprotocol) {
		this.teamprotocol = teamprotocol;
	}
	public String getMemberprotocol() {
		return memberprotocol;
	}

	public void setMemberprotocol(String memberprotocol) {
		this.memberprotocol = memberprotocol;
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

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getSecretaryName() {
		return secretaryName;
	}

	public void setSecretaryName(String secretaryName) {
		this.secretaryName = secretaryName;
	}

	public String getSecretaryPhone() {
		return secretaryPhone;
	}

	public void setSecretaryPhone(String secretaryPhone) {
		this.secretaryPhone = secretaryPhone;
	}

	public String getDeputy() {
		return deputy;
	}

	public void setDeputy(String deputy) {
		this.deputy = deputy;
	}

	public String getDeputyPhone() {
		return deputyPhone;
	}

	public void setDeputyPhone(String deputyPhone) {
		this.deputyPhone = deputyPhone;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public int getPoorNumber() {
		return poorNumber;
	}

	public void setPoorNumber(int poorNumber) {
		this.poorNumber = poorNumber;
	}

	public int getPoorPopulation() {
		return poorPopulation;
	}

	public void setPoorPopulation(int poorPopulation) {
		this.poorPopulation = poorPopulation;
	}

	public String getEstateInfo() {
		return estateInfo;
	}

	public void setEstateInfo(String estateInfo) {
		this.estateInfo = estateInfo;
	}

	public String getScienceNeed() {
		return scienceNeed;
	}

	public void setScienceNeed(String scienceNeed) {
		this.scienceNeed = scienceNeed;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTpyCompany() {
		return tpyCompany;
	}

	public void setTpyCompany(String tpyCompany) {
		this.tpyCompany = tpyCompany;
	}

	public String getTpyTitle() {
		return tpyTitle;
	}

	public void setTpyTitle(String tpyTitle) {
		this.tpyTitle = tpyTitle;
	}

	public String getTpyMajor() {
		return tpyMajor;
	}

	public void setTpyMajor(String tpyMajor) {
		this.tpyMajor = tpyMajor;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	
}
