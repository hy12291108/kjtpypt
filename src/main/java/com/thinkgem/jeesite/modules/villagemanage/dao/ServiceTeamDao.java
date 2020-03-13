/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.villagemanage.dao;


import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.villagemanage.entity.ServiceTeam;
import com.thinkgem.jeesite.modules.villagemanage.entity.Village;

/**
 * 服務團隊接口
 * @author 刘钢
 * @version 2017-08-22
 */
@MyBatisDao
public interface ServiceTeamDao extends CrudDao<ServiceTeam> {
	
	
	/**
	 * 查找特派团列表
	 * @param village
	 * @return
	 */
	public List<ServiceTeam> getServiceTeamList(ServiceTeam serviceTeam);
}
