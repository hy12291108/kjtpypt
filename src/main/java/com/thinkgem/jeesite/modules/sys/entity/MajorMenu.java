/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 专业菜单Entity
 * @author LG
 * @version 20171014
 */
public class MajorMenu extends DataEntity<MajorMenu> {

	private static final long serialVersionUID = 1L;
	private String name;//菜单名称
	private String menuFlag;//菜单标识是否一级菜单 0一级 1 二级
	private String menuParentId;//菜单父标识
	private String menuParentName;//菜单父标识名称
	public String getMenuParentName() {
		return menuParentName;
	}
	public void setMenuParentName(String menuParentName) {
		this.menuParentName = menuParentName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMenuFlag() {
		return menuFlag;
	}
	public void setMenuFlag(String menuFlag) {
		this.menuFlag = menuFlag;
	}
	public String getMenuParentId() {
		return menuParentId;
	}
	public void setMenuParentId(String menuParentId) {
		this.menuParentId = menuParentId;
	}
}