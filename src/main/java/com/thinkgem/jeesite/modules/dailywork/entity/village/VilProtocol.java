/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dailywork.entity.village;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.kjtpypt.entity.SysAttachment;

/**
 * 贫困村服务协议Entity
 * @author Grace
 * @version 2017-08-02
 */
public class VilProtocol extends DataEntity<VilProtocol> {
	
	private static final long serialVersionUID = 1L;
	private String vilId;		// 村id
	private String vilName;		// 村名称
	private String vilLocation;		// 所属区县
	private String vilContact;		// 联系人
	private String vilTpyid;		// 特派员id
	private String vilTpyname;		// 特派员名称
	private String vilNeeds;		// 村需求
	private String vilAttach;		// 附件
	private List<SysAttachment> sysAttachmentList=new ArrayList<SysAttachment>();//
	private List<SysAttachment> sysAttachmentList2=new ArrayList<SysAttachment>();//	
	private List<String> checkbox;
	private String vilCreateRole;  //创建者角色
	private String html;
	private String helpRelationid; //帮扶关系id
	private String ddId; //订单id;
	private String stateflag;//特派员协议是否审核：0，未审核；1，审核通过；2，审核未通过，
	private String fwxyopinion;//服务协议审核意见(特派员)
	private String fwxyopinion1;//服务协议审核意见（需求单位）
	private String fwxystateflag;
	private String fwxystateflag1;
	private String vilContactphone;//服务对象联系电话
	private String vilTpyphone;//特派员联系电话
	private String fwxyzpr;//服务协议指派人
	private String fwxyzpTime;//服务协议指派时间
	
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
	public String getVilContactphone() {
		return vilContactphone;
	}
	public void setVilContactphone(String vilContactphone) {
		this.vilContactphone = vilContactphone;
	}
	public String getVilTpyphone() {
		return vilTpyphone;
	}
	public void setVilTpyphone(String vilTpyphone) {
		this.vilTpyphone = vilTpyphone;
	}
	public String getFwxyopinion1() {
		return fwxyopinion1;
	}
	public void setFwxyopinion1(String fwxyopinion1) {
		this.fwxyopinion1 = fwxyopinion1;
	}
	public String getFwxystateflag() {
		return fwxystateflag;
	}
	public void setFwxystateflag(String fwxystateflag) {
		this.fwxystateflag = fwxystateflag;
	}
	public String getFwxystateflag1() {
		return fwxystateflag1;
	}
	public void setFwxystateflag1(String fwxystateflag1) {
		this.fwxystateflag1 = fwxystateflag1;
	}
	public String getFwxyopinion() {
		return fwxyopinion;
	}
	public void setFwxyopinion(String fwxyopinion) {
		this.fwxyopinion = fwxyopinion;
	}
	public List<SysAttachment> getSysAttachmentList2() {
		return sysAttachmentList2;
	}
	public void setSysAttachmentList2(List<SysAttachment> sysAttachmentList2) {
		this.sysAttachmentList2 = sysAttachmentList2;
	}
	public String getStateflag() {
		return stateflag;
	}
	public void setStateflag(String stateflag) {
		this.stateflag = stateflag;
	}
	public String getDdId() {
		return ddId;
	}

	public void setDdId(String ddId) {
		this.ddId = ddId;
	}

	public VilProtocol() {
		super();
	}

	public VilProtocol(String id){
		super(id);
	}
	@NotEmpty(message = "村id不能为空")
	@Length(min=0, max=64, message="村id长度必须介于 0 和 64 之间")
	public String getVilId() {
		return vilId;
	}

	public void setVilId(String vilId) {
		this.vilId = vilId;
	}
	
	@Length(min=0, max=64, message="村名称长度必须介于 0 和 64 之间")
	public String getVilName() {
		return vilName;
	}

	public void setVilName(String vilName) {
		this.vilName = vilName;
	}
	
	@Length(min=0, max=64, message="所属区县长度必须介于 0 和 64 之间")
	public String getVilLocation() {
		return vilLocation;
	}

	public void setVilLocation(String vilLocation) {
		this.vilLocation = vilLocation;
	}
	
	@Length(min=0, max=64, message="联系人长度必须介于 0 和 64 之间")
	public String getVilContact() {
		return vilContact;
	}

	public void setVilContact(String vilContact) {
		this.vilContact = vilContact;
	}
	
	@Length(min=0, max=64, message="特派员id长度必须介于 0 和 64 之间")
	public String getVilTpyid() {
		return vilTpyid;
	}

	public void setVilTpyid(String vilTpyid) {
		this.vilTpyid = vilTpyid;
	}
	
	@Length(min=0, max=64, message="特派员名称长度必须介于 0 和 64 之间")
	public String getVilTpyname() {
		return vilTpyname;
	}

	public void setVilTpyname(String vilTpyname) {
		this.vilTpyname = vilTpyname;
	}
	
	@Length(min=0, max=64, message="村需求长度必须介于 0 和 64 之间")
	public String getVilNeeds() {
		return vilNeeds;
	}

	public void setVilNeeds(String vilNeeds) {
		this.vilNeeds = vilNeeds;
	}
	
	@Length(min=0, max=64, message="附件长度必须介于 0 和 64 之间")
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

	public List<String> getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(List<String> checkbox) {
		this.checkbox = checkbox;
	}

	public String getVilCreateRole() {
		return vilCreateRole;
	}

	public void setVilCreateRole(String vilCreateRole) {
		this.vilCreateRole = vilCreateRole;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getHelpRelationid() {
		return helpRelationid;
	}

	public void setHelpRelationid(String helpRelationid) {
		this.helpRelationid = helpRelationid;
	}


	
}