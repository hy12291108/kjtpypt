/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.villagemanage.dao;


import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.villagemanage.entity.Village;

/**
 * 贫困村接口
 * @author 刘钢
 * @version 2017-08-17
 */
@MyBatisDao
public interface VillageDao extends CrudDao<Village> {
	
	public List<User>findUserList(User user);
	/**
	 * 查找贫困村列表
	 * @param village
	 * @return
	 */
	public List<Village> findVillagelist(Village village);
	
	
}
