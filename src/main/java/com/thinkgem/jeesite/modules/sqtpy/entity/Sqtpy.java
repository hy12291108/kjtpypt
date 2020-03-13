package com.thinkgem.jeesite.modules.sqtpy.entity;




import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
/**
 * 
 * @author caolei
 * Sqtpy需求单位申请特派员Entity
 */
public class Sqtpy extends DataEntity<Sqtpy> {
	private Office company1;// 归属公司
	private Office office;	// 归属部门
	private static final long serialVersionUID = 1L;//编号
	private String xqdwid;//需求单位编号
	private String xqdwname;//需求单位名称
	private String tpyid;//特派员编号
	private String tpyname;//特派员姓名
	private String zc;//职称
	private String zy;//专业
	private String mobile;//手机
	private String techspecial;//专业特长
	private String company;//所在单位
	private String starTime;//服务开始时间
	private String endTime;//服务结束时间
	private String state;//审批状态
	private String zpr;//指派人
	private String zpryj;//指派人意见
	private String fwxy;//服务协议
	private String fwxyorg;//机构服务协议
	private String xqdwsqReason;//需求单位的申请理由
	private String ismajor;//是否按专业查询
	private String corpcorName;//需求单位联系人
	private String zpTime;//指派时间
	private String selectName;//列表名字
	private String xqdwphone;//需求单位电话
	private String fwxystateflag;//特派员协议是否审核：0，未审核；1，审核通过；2，审核未通过
	private String fwxyopinion;//服务协议意见(特派员)
	private String fwxystateflag1;//特派员协议是否审核：0，未审核；1，审核通过；2，审核未通过
	private String fwxyopinion1;//服务协议意见（需求单位）
	private String fwxyzpr;//服务协议指派人
	private String fwxyzpTime;//服务协议指派时间
	private String personFlag;//特派员类型（0：自然人特派员，2：法人特派员） 
	private User tpyinfo;
	
	public User getTpyinfo() {
		return tpyinfo;
	}
	public void setTpyinfo(User tpyinfo) {
		this.tpyinfo = tpyinfo;
	}
	public String getPersonFlag() {
		return personFlag;
	}
	public void setPersonFlag(String personFlag) {
		this.personFlag = personFlag;
	}
	public String getFwxyzpr() {
		return fwxyzpr;
	}
	public void setFwxyzpr(String fwxyzpr) {
		this.fwxyzpr = fwxyzpr;
	}
	public String getFwxyzpTime() {
		return fwxyzpTime;
	}
	public void setFwxyzpTime(String fwxyzpTime) {
		this.fwxyzpTime = fwxyzpTime;
	}
	public String getFwxystateflag1() {
		return fwxystateflag1;
	}
	public void setFwxystateflag1(String fwxystateflag1) {
		this.fwxystateflag1 = fwxystateflag1;
	}
	public String getFwxyopinion1() {
		return fwxyopinion1;
	}
	public void setFwxyopinion1(String fwxyopinion1) {
		this.fwxyopinion1 = fwxyopinion1;
	}
	public String getFwxyopinion() {
		return fwxyopinion;
	}
	public void setFwxyopinion(String fwxyopinion) {
		this.fwxyopinion = fwxyopinion;
	}
	public String getFwxystateflag() {
		return fwxystateflag;
	}
	public void setFwxystateflag(String fwxystateflag) {
		this.fwxystateflag = fwxystateflag;
	}
	public String getXqdwphone() {
		return xqdwphone;
	}
	public void setXqdwphone(String xqdwphone) {
		this.xqdwphone = xqdwphone;
	}
	public String getSelectName() {
		return selectName;
	}
	public void setSelectName(String selectName) {
		this.selectName = selectName;
	}
	public String getZpTime() {
		return zpTime;
	}
	public void setZpTime(String zpTime) {
		this.zpTime = zpTime;
	}
	public String getCorpcorName() {
		return corpcorName;
	}
	public void setCorpcorName(String corpcorName) {
		this.corpcorName = corpcorName;
	}
	public String getIsmajor() {
		return ismajor;
	}
	public void setIsmajor(String ismajor) {
		this.ismajor = ismajor;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getXqdwid() {
		return xqdwid;
	}
	public void setXqdwid(String xqdwid) {
		this.xqdwid = xqdwid;
	}
	public String getXqdwname() {
		return xqdwname;
	}
	public void setXqdwname(String xqdwname) {
		this.xqdwname = xqdwname;
	}
	public String getTpyid() {
		return tpyid;
	}
	public void setTpyid(String tpyid) {
		this.tpyid = tpyid;
	}
	public String getTpyname() {
		return tpyname;
	}
	public void setTpyname(String tpyname) {
		this.tpyname = tpyname;
	}
	public String getZc() {
		return zc;
	}
	public void setZc(String zc) {
		this.zc = zc;
	}
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy = zy;
	}

	public String getTechspecial() {
		return techspecial;
	}
	public void setTechspecial(String techspecial) {
		this.techspecial = techspecial;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getStarTime() {
		return starTime;
	}
	public void setStarTime(String starTime) {
		this.starTime = starTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZpr() {
		return zpr;
	}
	public void setZpr(String zpr) {
		this.zpr = zpr;
	}
	public String getZpryj() {
		return zpryj;
	}
	public void setZpryj(String zpryj) {
		this.zpryj = zpryj;
	}
	public String getFwxy() {
		return fwxy;
	}
	public void setFwxy(String fwxy) {
		this.fwxy = fwxy;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getXqdwsqReason() {
		return xqdwsqReason;
	}
	public void setXqdwsqReason(String xqdwsqReason) {
		this.xqdwsqReason = xqdwsqReason;
	}

	public Office getCompany1() {
		return company1;
	}

	public void setCompany1(Office company1) {
		this.company1 = company1;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	public Sqtpy() {
		super();
	}
	public String getFwxyorg() {
		return fwxyorg;
	}
	public void setFwxyorg(String fwxyorg) {
		this.fwxyorg = fwxyorg;
	}

	
	
	
}
