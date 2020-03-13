/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.kjtpypt.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 附件表Entity
 * @author Grace
 * @version 2017-08-03
 */
public class SysAttachment extends DataEntity<SysAttachment> {
	
	private static final long serialVersionUID = 1L;
	private String attRecordid;		// 关联表记录主键
	private String attTable;		// 关联表
	private String attOriginname;		// 附件原名称
	private String attName;		// 附件名称
	private String attFolder;		// 附件存储文件夹
	
	
	public SysAttachment() {
		super();
	}

	public SysAttachment(String id){
		super(id);
	}

	@Length(min=0, max=64, message="关联表记录主键长度必须介于 0 和 64 之间")
	public String getAttRecordid() {
		return attRecordid;
	}

	public void setAttRecordid(String attRecordid) {
		this.attRecordid = attRecordid;
	}
	
	@Length(min=0, max=64, message="关联表长度必须介于 0 和 64 之间")
	public String getAttTable() {
		return attTable;
	}

	public void setAttTable(String attTable) {
		this.attTable = attTable;
	}
	
	@Length(min=0, max=64, message="附件原名称长度必须介于 0 和 64 之间")
	public String getAttOriginname() {
		return attOriginname;
	}

	public void setAttOriginname(String attOriginname) {
		this.attOriginname = attOriginname;
	}
	
	@Length(min=0, max=64, message="附件名称长度必须介于 0 和 64 之间")
	public String getAttName() {
		return attName;
	}

	public void setAttName(String attName) {
		this.attName = attName;
	}
	
	@Length(min=0, max=64, message="附件存储文件夹长度必须介于 0 和 64 之间")
	public String getAttFolder() {
		return attFolder;
	}

	public void setAttFolder(String attFolder) {
		this.attFolder = attFolder;
	}
	
}