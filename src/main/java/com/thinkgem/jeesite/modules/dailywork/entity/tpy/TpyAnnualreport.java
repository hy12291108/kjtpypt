/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dailywork.entity.tpy;

import java.util.Date;

import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 特派员年度考核Entity
 * @author Grace
 * @version 2017-08-10
 */
public class TpyAnnualreport extends DataEntity<TpyAnnualreport> {
	
	private static final long serialVersionUID = 1L;
	private String repTpyid;		// 特派员id
	private User repTpyuser;	//特派员user
	private String repTpyLocation;		// 特派员所属区域
	private String repTime;		// 年度
	private String repWritenTime;   //填写时间 
	private String repImptech;		// 引进新产品新技术
	private String repSpreadtech;		// 推广新技术
	private String repSolveproblem;		// 解决技术难题
	private String repTrainfarmer;		// 培训农民
	private String repFoundorg;		// 创办领办培育企业或合作社
	private String repJobopt;		// 带动就业人数
	private String repOutputvalue;		// 实现产值
	private String repTax;		// 创利税
	private String repYearsummary;		// 年度工作总结
	private String repLevel;           //考核等级
	private String repStatus;		// 审批状态
	private String repApproperson;		// 审批人
	private String repAppropersonid;		// 审批人id
	private User repApproUser; //审批人user
	private String repApprotime;		// 审批时间
	private String repApproopinion;		// 审批意见
	private String html;
	private double jtjjzc;//集体经济增收（万元）
	private int kjfwts;//科技服务天数（天）
	private int yjtgxpzxcp;//引进推广新品种新产品（个）
	private int tgxjs;//推广新技术（个）
	private int jjjsnt;//解决技术难题(个）
	private int pxnm;//培训农民（人次）
	private int cbqyhzs;//创办领办培育企业或合作社（个）
	private int ddjyrc;//带动就业人次(个)
	private int fwnchzzz;//服务农村合作组织(个)
	private int jlkjsfjd;//建立科技示范基地
	private int pykjsfh;//培育科技示范户
	private int jbpxb;//举办培训班（场次）
	private int pxry;//培训人员（人次）
	private int pxpkqz;//培训贫困群众（人次）
	private double sxcz;//实现产值（万元）
	private double ddzs;//带动增收（万元）
	private double cls;//创利税（万元）
	private int bfpkc;//帮扶贫困村（个）
	private int pkh;//贫困户（户）
	private int ddtp;//带动脱贫（户）
	private int pkrk;//贫困人口（个）
	private Office office;
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public String getRepWritenTime() {
		return repWritenTime;
	}

	public void setRepWritenTime(String repWritenTime) {
		this.repWritenTime = repWritenTime;
	}

	public double getJtjjzc() {
		return jtjjzc;
	}

	public void setJtjjzc(double jtjjzc) {
		this.jtjjzc = jtjjzc;
	}

	public int getKjfwts() {
		return kjfwts;
	}

	public void setKjfwts(int kjfwts) {
		this.kjfwts = kjfwts;
	}

	public int getYjtgxpzxcp() {
		return yjtgxpzxcp;
	}

	public void setYjtgxpzxcp(int yjtgxpzxcp) {
		this.yjtgxpzxcp = yjtgxpzxcp;
	}

	public int getTgxjs() {
		return tgxjs;
	}

	public void setTgxjs(int tgxjs) {
		this.tgxjs = tgxjs;
	}

	public int getJjjsnt() {
		return jjjsnt;
	}

	public void setJjjsnt(int jjjsnt) {
		this.jjjsnt = jjjsnt;
	}

	public int getPxnm() {
		return pxnm;
	}

	public void setPxnm(int pxnm) {
		this.pxnm = pxnm;
	}

	public int getCbqyhzs() {
		return cbqyhzs;
	}

	public void setCbqyhzs(int cbqyhzs) {
		this.cbqyhzs = cbqyhzs;
	}

	public int getDdjyrc() {
		return ddjyrc;
	}

	public void setDdjyrc(int ddjyrc) {
		this.ddjyrc = ddjyrc;
	}

	public int getFwnchzzz() {
		return fwnchzzz;
	}

	public void setFwnchzzz(int fwnchzzz) {
		this.fwnchzzz = fwnchzzz;
	}

	public int getJlkjsfjd() {
		return jlkjsfjd;
	}

	public void setJlkjsfjd(int jlkjsfjd) {
		this.jlkjsfjd = jlkjsfjd;
	}

	public int getPykjsfh() {
		return pykjsfh;
	}

	public void setPykjsfh(int pykjsfh) {
		this.pykjsfh = pykjsfh;
	}

	public int getJbpxb() {
		return jbpxb;
	}

	public void setJbpxb(int jbpxb) {
		this.jbpxb = jbpxb;
	}

	public int getPxry() {
		return pxry;
	}

	public void setPxry(int pxry) {
		this.pxry = pxry;
	}

	public int getPxpkqz() {
		return pxpkqz;
	}

	public void setPxpkqz(int pxpkqz) {
		this.pxpkqz = pxpkqz;
	}

	public double getSxcz() {
		return sxcz;
	}

	public void setSxcz(double sxcz) {
		this.sxcz = sxcz;
	}

	public double getDdzs() {
		return ddzs;
	}

	public void setDdzs(double ddzs) {
		this.ddzs = ddzs;
	}

	public double getCls() {
		return cls;
	}

	public void setCls(double cls) {
		this.cls = cls;
	}

	public int getBfpkc() {
		return bfpkc;
	}

	public void setBfpkc(int bfpkc) {
		this.bfpkc = bfpkc;
	}

	public int getPkh() {
		return pkh;
	}

	public void setPkh(int pkh) {
		this.pkh = pkh;
	}

	public int getDdtp() {
		return ddtp;
	}

	public void setDdtp(int ddtp) {
		this.ddtp = ddtp;
	}

	public int getPkrk() {
		return pkrk;
	}

	public void setPkrk(int pkrk) {
		this.pkrk = pkrk;
	}

	public TpyAnnualreport() {
		super();
	}

	public TpyAnnualreport(String id){
		super(id);
	}

	@Length(min=0, max=64, message="特派员id长度必须介于 0 和 64 之间")
	public String getRepTpyid() {
		return repTpyid;
	}

	public void setRepTpyid(String repTpyid) {
		this.repTpyid = repTpyid;
	}
	
	public String getRepTime() {
		return repTime;
	}

	public void setRepTime(String repTime) {
		this.repTime = repTime;
	}
	
	@Max(value = 999999,message="引进新产品新技术：请输入数字")
	public String getRepImptech() {
		return repImptech;
	}

	public void setRepImptech(String repImptech) {
		this.repImptech = repImptech;
	}
	
	@Max(value = 999999,message="推广新技术：请输入数字")
	public String getRepSpreadtech() {
		return repSpreadtech;
	}

	public void setRepSpreadtech(String repSpreadtech) {
		this.repSpreadtech = repSpreadtech;
	}
	
	@Max(value = 999999,message="解决技术难题：请输入数字")
	public String getRepSolveproblem() {
		return repSolveproblem;
	}

	public void setRepSolveproblem(String repSolveproblem) {
		this.repSolveproblem = repSolveproblem;
	}
	
	@Max(value = 999999,message="培训农民：请输入数字")
	public String getRepTrainfarmer() {
		return repTrainfarmer;
	}

	public void setRepTrainfarmer(String repTrainfarmer) {
		this.repTrainfarmer = repTrainfarmer;
	}
	
	@Max(value = 999999,message="创办领办培育企业或合作社：请输入数字")
	public String getRepFoundorg() {
		return repFoundorg;
	}

	public void setRepFoundorg(String repFoundorg) {
		this.repFoundorg = repFoundorg;
	}
	
	@Max(value = 999999,message="带动就业人数：请输入数字")
	public String getRepJobopt() {
		return repJobopt;
	}

	public void setRepJobopt(String repJobopt) {
		this.repJobopt = repJobopt;
	}
	
	@Max(value = 999999,message="实现产值：请输入数字")
	public String getRepOutputvalue() {
		return repOutputvalue;
	}

	public void setRepOutputvalue(String repOutputvalue) {
		this.repOutputvalue = repOutputvalue;
	}
	
	@Max(value = 999999,message="创利税：请输入数字")
	public String getRepTax() {
		return repTax;
	}

	public void setRepTax(String repTax) {
		this.repTax = repTax;
	}
	
	public String getRepYearsummary() {
		return repYearsummary;
	}

	public void setRepYearsummary(String repYearsummary) {
		this.repYearsummary = repYearsummary;
	}

	public String getRepLevel() {
		return repLevel;
	}

	public void setRepLevel(String repLevel) {
		this.repLevel = repLevel;
	}

	public String getRepStatus() {
		return repStatus;
	}

	public void setRepStatus(String repStatus) {
		this.repStatus = repStatus;
	}

	public String getRepApproperson() {
		return repApproperson;
	}

	public void setRepApproperson(String repApproperson) {
		this.repApproperson = repApproperson;
	}

	public String getRepAppropersonid() {
		return repAppropersonid;
	}

	public void setRepAppropersonid(String repAppropersonid) {
		this.repAppropersonid = repAppropersonid;
	}


	public String getRepApprotime() {
		return repApprotime;
	}

	public void setRepApprotime(String repApprotime) {
		this.repApprotime = repApprotime;
	}

	public String getRepApproopinion() {
		return repApproopinion;
	}

	public void setRepApproopinion(String repApproopinion) {
		this.repApproopinion = repApproopinion;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getRepTpyLocation() {
		return repTpyLocation;
	}

	public void setRepTpyLocation(String repTpyLocation) {
		this.repTpyLocation = repTpyLocation;
	}

	public User getRepTpyuser() {
		return repTpyuser;
	}

	public void setRepTpyuser(User repTpyuser) {
		this.repTpyuser = repTpyuser;
	}

	public User getRepApproUser() {
		return repApproUser;
	}

	public void setRepApproUser(User repApproUser) {
		this.repApproUser = repApproUser;
	}
	
	
	
}