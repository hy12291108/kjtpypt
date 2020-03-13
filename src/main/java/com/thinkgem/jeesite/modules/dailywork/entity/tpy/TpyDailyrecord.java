/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dailywork.entity.tpy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.kjtpypt.entity.SysAttachment;
import com.thinkgem.jeesite.modules.sqtpy.entity.Sqtpy;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.villagemanage.entity.TeamMember;

/**
 * 特派员工作日志Entity
 * @author Grace
 * @version 2017-08-10
 */
public class TpyDailyrecord extends DataEntity<TpyDailyrecord> {
	
	private static final long serialVersionUID = 1L;
	private String recTpyid;		// 特派员id
	private User recTpyuser;		// 特派员user
	private String tpytype;        //特派员类型
	private String recTpyLocation;		// 特派员所属区域
//	private List<String> recTpyLocationList;		// 特派员所属区域
	private String recHelpobjid;		// 帮扶对象id
	private String recHelpobjtype;		// 帮扶对象类型
	/*private String recStime;		// 帮扶开始时间
	private String recEtime;		// 帮扶结束时间
*/	private String recHelpstatus;		// 帮扶状态
	private String recStatus;		// 审批状态  
	private String recApproperson;		// 审批人
	private String recAppropersonid;		// 审批人id
	private User recApproUser; //审批人user
	private String recApprotime;		// 审批时间
	private String recApproopinion;		// 审批意见
	
	private String recHelpContent;     //帮扶工作内容
	@NotNull(message = "请选择帮扶开始时间")
	private String recStartTime;     //帮扶开始时间
	@NotNull(message = "请选择帮扶开始时间")
	private String recEndTime;      //帮扶结束时间
	private String recWrittenTime; //填写时间
	private String recWriter;   //填写人
	private String recHelpObj;  //帮扶对象
	private List<Sqtpy>  recHelpObjList;  //帮扶对象List
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
	private String fwdxpjr;//服务对象评价人
	private String fwdxpjTime;//服务对象评价时间
	private String fwdxpjYj;//服务对象评价意见
	private String fwdxpjState;//服务对象评价状态
	// APP 通过归属部门查询时用
	private Office office;
	private String drTableImage;//日志表图片
	private String Year;//年度
	private String IsThreeArea;//年度是否三区人才
	private String teamId;//特派团id
	private String teamName;//特派团名称
	private String teamMemberType;//特派团成员类型
	private List<TeamMember> teamMemberList;
	private String dailyRecordType;//团日志类型;	
	public String getDailyRecordType() {
		return dailyRecordType;
	}
	public void setDailyRecordType(String dailyRecordType) {
		this.dailyRecordType = dailyRecordType;
	}
	public List<TeamMember> getTeamMemberList() {
		return teamMemberList;
	}
	public void setTeamMemberList(List<TeamMember> teamMemberList) {
		this.teamMemberList = teamMemberList;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public String getTeamMemberType() {
		return teamMemberType;
	}
	public void setTeamMemberType(String teamMemberType) {
		this.teamMemberType = teamMemberType;
	}
	public String getYear() {
		return Year;
	}
	public void setYear(String year) {
		Year = year;
	}
	public String getIsThreeArea() {
		return IsThreeArea;
	}
	public void setIsThreeArea(String isThreeArea) {
		IsThreeArea = isThreeArea;
	}
	public String getDrTableImage() {
		return drTableImage;
	}
	public void setDrTableImage(String drTableImage) {
		this.drTableImage = drTableImage;
	}
	public String getFwdxpjState() {
		return fwdxpjState;
	}
	public void setFwdxpjState(String fwdxpjState) {
		this.fwdxpjState = fwdxpjState;
	}
	public String getFwdxpjr() {
		return fwdxpjr;
	}
	public void setFwdxpjr(String fwdxpjr) {
		this.fwdxpjr = fwdxpjr;
	}
	public String getFwdxpjTime() {
		return fwdxpjTime;
	}
	public void setFwdxpjTime(String fwdxpjTime) {
		this.fwdxpjTime = fwdxpjTime;
	}
	public String getFwdxpjYj() {
		return fwdxpjYj;
	}
	public void setFwdxpjYj(String fwdxpjYj) {
		this.fwdxpjYj = fwdxpjYj;
	}
	public String getTpytype() {
		return tpytype;
	}
	public void setTpytype(String tpytype) {
		this.tpytype = tpytype;
	}
	public int getKjfwts() {
		return kjfwts;
	}
	public void setKjfwts(int kjfwts) {
		this.kjfwts = kjfwts;
	}
	public double getJtjjzc() {
		return jtjjzc;
	}

	public void setJtjjzc(double jtjjzc) {
		this.jtjjzc = jtjjzc;
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

	public TpyDailyrecord() {
		super();
	}

	public TpyDailyrecord(String id){
		super(id);
	}

	@Length(min=0, max=64, message="特派员id长度必须介于 0 和 64 之间")
	public String getRecTpyid() {
		return recTpyid;
	}

	public void setRecTpyid(String recTpyid) {
		this.recTpyid = recTpyid;
	}
	
	@Length(min=0, max=64, message="帮扶对象id长度必须介于 0 和 64 之间")
	public String getRecHelpobjid() {
		return recHelpobjid;
	}

	public void setRecHelpobjid(String recHelpobjid) {
		this.recHelpobjid = recHelpobjid;
	}
	
	@Length(min=0, max=64, message="帮扶对象类型长度必须介于 0 和 64 之间")
	public String getRecHelpobjtype() {
		return recHelpobjtype;
	}

	public void setRecHelpobjtype(String recHelpobjtype) {
		this.recHelpobjtype = recHelpobjtype;
	}
/*	
	@Length(min=0, max=64, message="帮扶开始时间长度必须介于 0 和 64 之间")
	public String getRecStime() {
		return recStime;
	}

	public void setRecStime(String recStime) {
		this.recStime = recStime;
	}
	
	@Length(min=0, max=64, message="帮扶结束时间长度必须介于 0 和 64 之间")
	public String getRecEtime() {
		return recEtime;
	}

	public void setRecEtime(String recEtime) {
		this.recEtime = recEtime;
	}*/
	
	@Length(min=0, max=64, message="帮扶状态长度必须介于 0 和 64 之间")
	public String getRecHelpstatus() {
		return recHelpstatus;
	}

	public void setRecHelpstatus(String recHelpstatus) {
		this.recHelpstatus = recHelpstatus;
	}
	
	@Length(min=0, max=64, message="审批状态长度必须介于 0 和 64 之间")
	public String getRecStatus() {
		return recStatus;
	}

	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
	}
	
	@Length(min=0, max=64, message="审批人长度必须介于 0 和 64 之间")
	public String getRecApproperson() {
		return recApproperson;
	}

	public void setRecApproperson(String recApproperson) {
		this.recApproperson = recApproperson;
	}
	public String getRecApprotime() {
		return recApprotime;
	}
	public void setRecApprotime(String recApprotime) {
		this.recApprotime = recApprotime;
	}
	@Length(min=0, max=255, message="审批意见长度必须介于 0 和 255 之间")
	public String getRecApproopinion() {
		return recApproopinion;
	}

	public void setRecApproopinion(String recApproopinion) {
		this.recApproopinion = recApproopinion;
	}

	public String getRecHelpContent() {
		return recHelpContent;
	}

	public void setRecHelpContent(String recHelpContent) {
		this.recHelpContent = recHelpContent;
	}

	public String getRecStartTime() {
		return recStartTime;
	}
	public void setRecStartTime(String recStartTime) {
		this.recStartTime = recStartTime;
	}
	public String getRecEndTime() {
		return recEndTime;
	}
	public void setRecEndTime(String recEndTime) {
		this.recEndTime = recEndTime;
	}
	public String getRecWrittenTime() {
		return recWrittenTime;
	}
	public void setRecWrittenTime(String recWrittenTime) {
		this.recWrittenTime = recWrittenTime;
	}
	public String getRecWriter() {
		return recWriter;
	}

	public void setRecWriter(String rec_writer) {
		this.recWriter = rec_writer;
	}

	public String getRecHelpObj() {
		return recHelpObj;
	}

	public void setRecHelpObj(String rec_helpObj) {
		this.recHelpObj = rec_helpObj;
	}

	

	public List<Sqtpy> getRecHelpObjList() {
		return recHelpObjList;
	}
	public void setRecHelpObjList(List<Sqtpy> recHelpObjList) {
		this.recHelpObjList = recHelpObjList;
	}
	public String getRecTpyLocation() {
		return recTpyLocation;
	}

	public void setRecTpyLocation(String recTpyLocation) {
		this.recTpyLocation = recTpyLocation;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getRecAppropersonid() {
		return recAppropersonid;
	}

	public void setRecAppropersonid(String recAppropersonid) {
		this.recAppropersonid = recAppropersonid;
	}

	public User getRecTpyuser() {
		return recTpyuser;
	}

	public void setRecTpyuser(User recTpyuser) {
		this.recTpyuser = recTpyuser;
	}

	public User getRecApproUser() {
		return recApproUser;
	}

	public void setRecApproUser(User recApproUser) {
		this.recApproUser = recApproUser;
	}
	
	public Office getOffice() {
		return office;
	}
	
	public void setOffice(Office office) {
		this.office = office;
	}

}