/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dailywork.entity.tpy;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.kjtpypt.entity.SysAttachment;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 特派员服务协议Entity
 * @author Grace
 * @version 2017-08-04
 */
public class TpyProtocol extends DataEntity<TpyProtocol> {
	
	private static final long serialVersionUID = 1L;
	private String proTpyid;		// 特派员id
	private String proType;		// 协议类型
	private String proAttach;		// 协议
	private String proStatus;		// 审批状态
	private String proApproperson;		// 审批人
	private String proApprotime;		// 审批时间
	private String proApproopinion;		// 审批意见
	private List<SysAttachment> sysAttachmentList=new ArrayList<SysAttachment>();//
	private List<String> checkbox;      //CheckBox
	private User user;            //用户
	private String html;//
	private String html2;//
	

	
	public TpyProtocol() {
		super();
	}

	public TpyProtocol(String id){
		super(id);
	}

	@Length(min=0, max=64, message="特派员id长度必须介于 0 和 64 之间")
	public String getProTpyid() {
		return proTpyid;
	}

	public void setProTpyid(String proTpyid) {
		this.proTpyid = proTpyid;
	}
	
	@Length(min=0, max=64, message="协议类型长度必须介于 0 和 64 之间")
	public String getProType() {
		return proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}
	
	@Length(min=0, max=64, message="协议长度必须介于 0 和 64 之间")
	public String getProAttach() {
		return proAttach;
	}

	public void setProAttach(String proAttach) {
		this.proAttach = proAttach;
	}
	
	@Length(min=0, max=64, message="审批状态长度必须介于 0 和 64 之间")
	public String getProStatus() {
		return proStatus;
	}

	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
	}
	
	@Length(min=0, max=64, message="审批人长度必须介于 0 和 64 之间")
	public String getProApproperson() {
		return proApproperson;
	}

	public void setProApproperson(String proApproperson) {
		this.proApproperson = proApproperson;
	}
	
	@Length(min=0, max=64, message="审批时间长度必须介于 0 和 64 之间")
	public String getProApprotime() {
		return proApprotime;
	}

	public void setProApprotime(String proApprotime) {
		this.proApprotime = proApprotime;
	}
	
	@Length(min=0, max=255, message="审批意见长度必须介于 0 和 255 之间")
	public String getProApproopinion() {
		return proApproopinion;
	}

	public void setProApproopinion(String proApproopinion) {
		this.proApproopinion = proApproopinion;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getHtml2() {
		return html2;
	}

	public void setHtml2(String html2) {
		this.html2 = html2;
	}

	
}