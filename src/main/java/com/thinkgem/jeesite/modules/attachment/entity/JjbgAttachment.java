/**
 */
package com.thinkgem.jeesite.modules.attachment.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 附件表Entity
 * @author zl
 * @version 2017-08-27
 */
public class JjbgAttachment extends DataEntity<JjbgAttachment> {
	
	private static final long serialVersionUID = 1L;
	private String ahtYwlx;		//业务类型
	private String ahtYwlxName;		//业务类型名称
	private String ahtOperId;		//业务编号
	private String ahtName;		//存放名称
	private String ahtFilename;		//显示名称
	private String ahtsize;		//文件大小
	private String ahttype;		//文件类型
	private String ahtdate;		//上传时间
	private String ahtpath;		//存放地址
	

	public JjbgAttachment() {
		super();
	}

	public JjbgAttachment(String id){
		super(id);
	}
	
	public String getAhtYwlx() {
		return ahtYwlx;
	}
	public void setAhtYwlx(String ahtYwlx) {
		this.ahtYwlx = ahtYwlx;
	}
	public String getAhtYwlxName() {
		return ahtYwlxName;
	}
	public void setAhtYwlxName(String ahtYwlxName) {
		this.ahtYwlxName = ahtYwlxName;
	}
	public String getAhtOperId() {
		return ahtOperId;
	}
	public void setAhtOperId(String ahtOperId) {
		this.ahtOperId = ahtOperId;
	}
	public String getAhtName() {
		return ahtName;
	}
	public void setAhtName(String ahtName) {
		this.ahtName = ahtName;
	}
	public String getAhtFilename() {
		return ahtFilename;
	}
	public void setAhtFilename(String ahtFilename) {
		this.ahtFilename = ahtFilename;
	}

	public String getAhtsize() {
		return ahtsize;
	}

	public void setAhtsize(String ahtsize) {
		this.ahtsize = ahtsize;
	}

	public String getAhttype() {
		return ahttype;
	}

	public void setAhttype(String ahttype) {
		this.ahttype = ahttype;
	}

	public String getAhtdate() {
		return ahtdate;
	}

	public void setAhtdate(String ahtdate) {
		this.ahtdate = ahtdate;
	}

	public String getAhtpath() {
		return ahtpath;
	}

	public void setAhtpath(String ahtpath) {
		this.ahtpath = ahtpath;
	}
	
}