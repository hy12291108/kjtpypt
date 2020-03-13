/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.gwdj.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 公文登记Entity
 * @author 张云天
 * @version 2017-06-23
 */
public class JjbgGwdj extends DataEntity<JjbgGwdj> {
	
	private static final long serialVersionUID = 1L;
	private String isDept;		// 拟稿部门
	private String drafter;		// 拟稿人
	private String fwzh;		// 发文字号
	private String title;		// 题目
	private String zsUnit;		// 主送单位
	private String csUnit;		// 抄送单位
	
	public JjbgGwdj() {
		super();
	}

	public JjbgGwdj(String id){
		super(id);
	}

	@Length(min=0, max=255, message="拟稿部门长度必须介于 0 和 255 之间")
	public String getIsDept() {
		return isDept;
	}

	public void setIsDept(String isDept) {
		this.isDept = isDept;
	}
	
	@Length(min=0, max=255, message="拟稿人长度必须介于 0 和 255 之间")
	public String getDrafter() {
		return drafter;
	}

	public void setDrafter(String drafter) {
		this.drafter = drafter;
	}
	
	@Length(min=0, max=255, message="发文字号长度必须介于 0 和 255 之间")
	public String getFwzh() {
		return fwzh;
	}

	public void setFwzh(String fwzh) {
		this.fwzh = fwzh;
	}
	
	@Length(min=0, max=255, message="题目长度必须介于 0 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=255, message="主送单位长度必须介于 0 和 255 之间")
	public String getZsUnit() {
		return zsUnit;
	}

	public void setZsUnit(String zsUnit) {
		this.zsUnit = zsUnit;
	}
	
	@Length(min=0, max=255, message="抄送单位长度必须介于 0 和 255 之间")
	public String getCsUnit() {
		return csUnit;
	}

	public void setCsUnit(String csUnit) {
		this.csUnit = csUnit;
	}
	
}