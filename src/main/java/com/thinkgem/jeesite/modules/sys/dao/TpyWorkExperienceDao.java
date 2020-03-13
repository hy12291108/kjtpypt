/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;


import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.TpyWorkExperience;

/**
 * 特派员工作经历
 * @author 刘钢
 * @version 2017-08-14
 */
@MyBatisDao
public interface TpyWorkExperienceDao extends CrudDao<TpyWorkExperience> {
	
	/*
	 * 根据特派员信息表的特派员id查找信息
	 * @author 刘钢
	 * 20170911
	 */
	public List<TpyWorkExperience> findWork(String id);
	
	
}
