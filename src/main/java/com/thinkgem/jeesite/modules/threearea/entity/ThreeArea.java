/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.threearea.entity;


import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 三区人才实体类
 * @author liuGang
 * @version 2017-09-12
 */
public class ThreeArea extends DataEntity<ThreeArea> {
	private static final long serialVersionUID = 1L;
	private String zoneId;//区域部门id[区分不同区域]
	private String zoneName;//区域部门名称
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	private String zoneParentId;//区分所属市
	private String xpId;//下派id
	private String xpParentId;//下派父id
	private String tpyId;//特派员id
	private String name;    // 姓名
	private String email;	// 邮箱
	private String sex;     // 性别
	private String tpyBirthDate;//出生年月
	private String mobile;	// 特派员手机号码
	private String tpyQulification;//学历
	private String tpyMajor;//专业
	private String tpySpecial;//专业特长
	//市审核标识
	private String checkPersonC;//市审核人
	private String checkTimeC;//市审核时间
	private String checkAdviceC;//市审核人意见
	//省审核标识
	private String checkPersonP;//省审核人
	private String checkTimeP;//省审核时间
	private String checkAdviceP;//省审核意见
	private String year;//申请年度
	
	private String xpZone;//下派区域
	private String xpTime;//下派时间
	private String xpStartTime;//下派开始时间
	private String xpEndTime;//下派结束时间
	private String xpPerson;//下派人
	private String xpZoneName;//下派区域名称
	
	public String getXpZoneName() {
		return xpZoneName;
	}
	public void setXpZoneName(String xpZoneName) {
		this.xpZoneName = xpZoneName;
	}
	public String getXpZone() {
		return xpZone;
	}
	public void setXpZone(String xpZone) {
		this.xpZone = xpZone;
	}
	public String getXpTime() {
		return xpTime;
	}
	public void setXpTime(String xpTime) {
		this.xpTime = xpTime;
	}
	public String getXpStartTime() {
		return xpStartTime;
	}
	public void setXpStartTime(String xpStartTime) {
		this.xpStartTime = xpStartTime;
	}
	public String getXpEndTime() {
		return xpEndTime;
	}
	public void setXpEndTime(String xpEndTime) {
		this.xpEndTime = xpEndTime;
	}
	public String getXpPerson() {
		return xpPerson;
	}
	public void setXpPerson(String xpPerson) {
		this.xpPerson = xpPerson;
	}
	public String getXpYear() {
		return xpYear;
	}
	public void setXpYear(String xpYear) {
		this.xpYear = xpYear;
	}
	private String xpYear;//下派年度
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getCheckPersonC() {
		return checkPersonC;
	}
	public void setCheckPersonC(String checkPersonC) {
		this.checkPersonC = checkPersonC;
	}
	public String getCheckTimeC() {
		return checkTimeC;
	}
	public void setCheckTimeC(String checkTimeC) {
		this.checkTimeC = checkTimeC;
	}
	public String getCheckAdviceC() {
		return checkAdviceC;
	}
	public void setCheckAdviceC(String checkAdviceC) {
		this.checkAdviceC = checkAdviceC;
	}
	public String getCheckPersonP() {
		return checkPersonP;
	}
	public void setCheckPersonP(String checkPersonP) {
		this.checkPersonP = checkPersonP;
	}
	public String getCheckTimeP() {
		return checkTimeP;
	}
	public void setCheckTimeP(String checkTimeP) {
		this.checkTimeP = checkTimeP;
	}
	public String getCheckAdviceP() {
		return checkAdviceP;
	}
	public void setCheckAdviceP(String checkAdviceP) {
		this.checkAdviceP = checkAdviceP;
	}
	public String getTpySpecial() {
		return tpySpecial;
	}
	public void setTpySpecial(String tpySpecial) {
		this.tpySpecial = tpySpecial;
	}
	private String personalInfo;//个人情况
	private String tpyNation;//民族
	private String tpyPoliticalStatus;//政治面貌
	private String status;//审核状态
	private String sqTime;//申请成为三区人才的时间
	public String getXpId() {
		return xpId;
	}
	public void setXpId(String xpId) {
		this.xpId = xpId;
	}
	public String getXpParentId() {
		return xpParentId;
	}
	public void setXpParentId(String xpParentId) {
		this.xpParentId = xpParentId;
	}
	public String getTpyId() {
		return tpyId;
	}
	public void setTpyId(String tpyId) {
		this.tpyId = tpyId;
	}
	public String getZoneParentId() {
		return zoneParentId;
	}
	public void setZoneParentId(String zoneParentId) {
		this.zoneParentId = zoneParentId;
	}
	public String getSqTime() {
		return sqTime;
	}
	public void setSqTime(String sqTime) {
		this.sqTime = sqTime;
	}
	public String getZoneId() {
		return zoneId;
	}
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTpyQulification() {
		return tpyQulification;
	}
	public void setTpyQulification(String tpyQulification) {
		this.tpyQulification = tpyQulification;
	}
	public String getTpyMajor() {
		return tpyMajor;
	}
	public void setTpyMajor(String tpyMajor) {
		this.tpyMajor = tpyMajor;
	}
	public String getPersonalInfo() {
		return personalInfo;
	}
	public void setPersonalInfo(String personalInfo) {
		this.personalInfo = personalInfo;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTpyBirthDate() {
		return tpyBirthDate;
	}
	public void setTpyBirthDate(String tpyBirthDate) {
		this.tpyBirthDate = tpyBirthDate;
	}
}