/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.modules.sys.dao.AreaDao;
import com.thinkgem.jeesite.modules.sys.dao.MajorMenuDao;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.entity.MajorMenu;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.villagemanage.dao.VillageDao;
import com.thinkgem.jeesite.modules.villagemanage.entity.Village;

/**
 * 专业service
 * @author LG
 * @version 20171014
 */
@Service
@Transactional(readOnly = true)
public class MajorMenuService extends  CrudService<MajorMenuDao, MajorMenu>  {
	@Autowired MajorMenuDao majorMenuDao;
	public MajorMenu getMajorMenu(String id){
		return majorMenuDao.get(id);
	}
	public List<MajorMenu> findAllMajorMenu(){
		//List<MajorMenu> list = majorMenuDao.findAllMajorMenu();
		return majorMenuDao.findAllMajorMenu();
	}
	public List<MajorMenu> findMajorMenu(){
		//List<MajorMenu> list = majorMenuDao.findAllMajorMenu();
		return majorMenuDao.findMajorMenu();
	}
	
	
	
	public List<MajorMenu> findMajorMenuSecond(MajorMenu majorMenu){
		//List<MajorMenu> list = majorMenuDao.findMajorMenuSecond();
		return  majorMenuDao.findMajorMenuSecond(majorMenu);
	}
	
	@Transactional(readOnly = false)
	public boolean saveMajorMenu(MajorMenu majorMenu){
		majorMenu.preInsert();
		majorMenuDao.insert(majorMenu);
		return true;
	}
	@Transactional(readOnly = false)
	public boolean deleteMenu(MajorMenu majorMenu){
		majorMenu.preUpdate();
		majorMenuDao.delete(majorMenu);
		return true;
	}
}
