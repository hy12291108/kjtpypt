/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;


import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.MajorMenu;

/**
 * 菜单DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface MajorMenuDao extends CrudDao<MajorMenu> {
	public List<MajorMenu> findAllMajorMenu();
	public List<MajorMenu> findMajorMenuSecond(MajorMenu majorMenu);
	public List<MajorMenu> findMajorMenu();
	
}
