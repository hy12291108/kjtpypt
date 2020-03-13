/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.threearea.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.threearea.entity.ThreeAreaBaseData;
/**
 * 三区人才参数维护接口
 * @author 刘钢
 * @version 2017-09-28
 */
@MyBatisDao
public interface ThreeAreaBaseDataDao extends CrudDao<ThreeAreaBaseData> {
	/*
	 * 刘钢
	 * 三区人才参数获取
	 * 20170929
	 */
	public ThreeAreaBaseData getParameter(String currentTime);
	/*
	 * 刘钢
	 * 三区人才审核参数获取
	 * 20171006
	 */
	public ThreeAreaBaseData getShParameter(String currentTime);
	/*
	 * 刘钢
	 * 三区人才申请参数获取
	 * 20171006
	 */
	public List<ThreeAreaBaseData> getSqParameter(String currentTime);
	/*
	 * 刘钢
	 * 三区人才获取通过审核的参数列表
	 * 20171006
	 */
	public ThreeAreaBaseData getPassParameter(String currentTime);
	
}
