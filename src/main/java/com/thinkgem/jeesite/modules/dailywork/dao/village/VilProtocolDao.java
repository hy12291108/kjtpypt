/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dailywork.dao.village;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dailywork.entity.village.VilProtocol;
import com.thinkgem.jeesite.modules.sqtpy.entity.Sqtpy;

/**
 * 贫困村服务协议DAO接口
 * @author Grace
 * @version 2017-08-02
 */
@MyBatisDao
public interface VilProtocolDao extends CrudDao<VilProtocol> {
	
	/**
	 * 修改服务协议审核状态
	 * @param sqtpy
	 * @return
	 */
	public int savefwxyopinion(VilProtocol vilProtocol);
	public int savefwxy2(Sqtpy sqtpy);
	
	
}