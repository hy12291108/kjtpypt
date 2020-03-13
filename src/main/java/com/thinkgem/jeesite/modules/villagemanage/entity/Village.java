package com.thinkgem.jeesite.modules.villagemanage.entity;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;

public class Village extends DataEntity<Village> {
	private static final long serialVersionUID = 1L;
	private Office office;
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
	List<ServiceTeam> serviceTeam;//特派员团队
	
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	public List<ServiceTeam> getServiceTeam() {
		return serviceTeam;
	}
	public void setServiceTeam(List<ServiceTeam> serviceTeam) {
		this.serviceTeam = serviceTeam;
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
	
}
