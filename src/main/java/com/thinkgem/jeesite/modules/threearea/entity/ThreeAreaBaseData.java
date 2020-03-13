/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.threearea.entity;


import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 三区人才实体类
 * @author liuGang
 * @version 2017-09-28
 */
public class ThreeAreaBaseData extends DataEntity<ThreeAreaBaseData> {
	private static final long serialVersionUID = 1L;
	private String year;
	private String startTime;
	private String endTime;
	private String yearStartTime;
	private String yearEndTime;
	public String getYear() {
		return year;
	}
	public String getYearStartTime() {
		return yearStartTime;
	}
	public void setYearStartTime(String yearStartTime) {
		this.yearStartTime = yearStartTime;
	}
	public String getYearEndTime() {
		return yearEndTime;
	}
	public void setYearEndTime(String yearEndTime) {
		this.yearEndTime = yearEndTime;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}