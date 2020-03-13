package com.thinkgem.jeesite.modules.sqtpy.dao;

import java.util.List;


import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sqtpy.entity.Sqtpy;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

@MyBatisDao
public interface TpyUserDao extends CrudDao<User> {

	public List<String> getmajorlist(String officeid);
	
	public User getTpybyId(String tpyid);
	
	public List<User> getTpyListbyMajor(String officeid,String major);
	
	public List<User> getXqdwList(User user);
}
