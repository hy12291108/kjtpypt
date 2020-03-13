/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;


/**
 * 特派员工作经历信息表
 * @author 刘钢
 * @version 2017-08-10
 */
public class TpyWorkExperience extends DataEntity<TpyWorkExperience> {

	private static final long serialVersionUID = 1L;
	private String tpyCorpName;//单位名称
	private String tpyWorkDate;//入职时间
	private String tpyLeaveDate;//离职时间
	private String tpyWork;//工作岗位
	private String tpyInfoId;//特派员编号
	
	public String getTpyInfoId() {
		return tpyInfoId;
	}
	public void setTpyInfoId(String tpyInfoId) {
		this.tpyInfoId = tpyInfoId;
	}
	public String getTpyCorpName() {
		return tpyCorpName;
	}
	public void setTpyCorpName(String tpyCorpName) {
		this.tpyCorpName = tpyCorpName;
	}
	public String getTpyWorkDate() {
		return tpyWorkDate;
	}
	public void setTpyWorkDate(String tpyWorkDate) {
		this.tpyWorkDate = tpyWorkDate;
	}
	public String getTpyLeaveDate() {
		return tpyLeaveDate;
	}
	public void setTpyLeaveDate(String tpyLeaveDate) {
		this.tpyLeaveDate = tpyLeaveDate;
	}
	public String getTpyWork() {
		return tpyWork;
	}
	public void setTpyWork(String tpyWork) {
		this.tpyWork = tpyWork;
	}
}