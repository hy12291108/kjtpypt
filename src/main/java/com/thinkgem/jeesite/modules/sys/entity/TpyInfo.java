/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.DataEntity;


/**
 * 特派员信息表
 * @author 刘钢
 * @version 2017-08-10
 */
public class TpyInfo extends DataEntity<TpyInfo> {

	private static final long serialVersionUID = 1L;
	private String tpyNation;//民族
	private String tpyPoliticalStatus;//政治面貌
	private String tpyHealthCondition;//健康状况
	private String tpyMaritalStatus;//婚姻状况
	private String tpyGraduateSchool;//毕业学校
	private String tpyIsDeploy;//是否市际间调配
	private String tpySource;//从何市调配
	private String tpyServiceSite;//拟分配的服务地点
	private String tpyServiceContent;//拟开展的服务内容
	private String tpyRewardStatus;//奖惩情况
	private String tpyId;//特派员标识
	private String tpySW;//从事科技服务与创业情况
	private String tpySD;//法人科技特派员技术力量与优势
	public String getTpySD() {
		return tpySD;
	}
	public void setTpySD(String tpySD) {
		this.tpySD = tpySD;
	}
	public String getTpySW() {
		return tpySW;
	}
	public void setTpySW(String tpySW) {
		this.tpySW = tpySW;
	}
	public String getTpyId() {
		return tpyId;
	}
	public void setTpyId(String tpyId) {
		this.tpyId = tpyId;
	}
	public String getTpyNation() {
		return tpyNation;
	}
	public void setTpyNation(String tpyNation) {
		this.tpyNation = tpyNation;
	}
	public String getTpyPoliticalStatus() {
		return tpyPoliticalStatus;
	}
	public void setTpyPoliticalStatus(String tpyPoliticalStatus) {
		this.tpyPoliticalStatus = tpyPoliticalStatus;
	}
	public String getTpyHealthCondition() {
		return tpyHealthCondition;
	}
	public void setTpyHealthCondition(String tpyHealthCondition) {
		this.tpyHealthCondition = tpyHealthCondition;
	}
	public String getTpyMaritalStatus() {
		return tpyMaritalStatus;
	}
	public void setTpyMaritalStatus(String tpyMaritalStatus) {
		this.tpyMaritalStatus = tpyMaritalStatus;
	}
	public String getTpyGraduateSchool() {
		return tpyGraduateSchool;
	}
	public void setTpyGraduateSchool(String tpyGraduateSchool) {
		this.tpyGraduateSchool = tpyGraduateSchool;
	}
	public String getTpyIsDeploy() {
		return tpyIsDeploy;
	}
	public void setTpyIsDeploy(String tpyIsDeploy) {
		this.tpyIsDeploy = tpyIsDeploy;
	}
	public String getTpySource() {
		return tpySource;
	}
	public void setTpySource(String tpySource) {
		this.tpySource = tpySource;
	}
	public String getTpyServiceSite() {
		return tpyServiceSite;
	}
	public void setTpyServiceSite(String tpyServiceSite) {
		this.tpyServiceSite = tpyServiceSite;
	}
	public String getTpyServiceContent() {
		return tpyServiceContent;
	}
	public void setTpyServiceContent(String tpyServiceContent) {
		this.tpyServiceContent = tpyServiceContent;
	}
	public String getTpyRewardStatus() {
		return tpyRewardStatus;
	}
	public void setTpyRewardStatus(String tpyRewardStatus) {
		this.tpyRewardStatus = tpyRewardStatus;
	}
	private List<TpyWorkExperience> tpyWorkExperience;
	public List<TpyWorkExperience> getTpyWorkExperience() {
		return tpyWorkExperience;
	}
	public void setTpyWorkExperience(List<TpyWorkExperience> tpyWorkExperience) {
		this.tpyWorkExperience = tpyWorkExperience;
	}

}