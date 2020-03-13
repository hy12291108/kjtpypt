/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;


import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.TpyInfo;

/**
 * 特派员基本信息
 * @author 刘钢
 * @version 2017-08-14
 */
@MyBatisDao
public interface TpyInfoDao extends CrudDao<TpyInfo> {
	public TpyInfo get(String id);
	public TpyInfo getInfo(String id);
	public int updateBaseInfo(TpyInfo tpyInfo);
}
