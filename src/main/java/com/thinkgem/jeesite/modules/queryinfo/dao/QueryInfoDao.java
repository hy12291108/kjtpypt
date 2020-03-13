package com.thinkgem.jeesite.modules.queryinfo.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.queryinfo.entity.QueryInfoCount;
import com.thinkgem.jeesite.modules.sys.entity.User;
/**
 * 申请特派员DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface QueryInfoDao extends CrudDao<User> {
	/**
	 * 查询特派员用户信息列表
	 * @param user 
	 * @return
	 */
	public List<User> QueryTpyInfoList(User user);
	
	/**
	 * APP特派员查询审核列表获取
	 * @param user
	 * @return
	 */
	public List<User> QueryTpyInfoListApp(User user);
	/**
	 *  查询需求单位信息列表
	 * @return
	 */
	public List<User> QueryXqdwInfoList(User user);
	
	/**
	 * APP服务对象查询审核列表获取
	 * @param user
	 * @return
	 */
	public List<User> QueryXqdwInfoListApp(User user);
	/**
	 *  查询特派员工作单位的特派员数量
	 * @return
	 */
	public List<User> getTpyNum(User user);
		
} 
