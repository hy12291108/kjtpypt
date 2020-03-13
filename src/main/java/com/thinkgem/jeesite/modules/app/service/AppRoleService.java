package com.thinkgem.jeesite.modules.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

/**
 * APP 用于解决在APP端登录时，user不是当前登录用户的问题
 * 特派员/需求单位
 * 赵凯浩
 * 2017年8月24日 下午1:03:36
 */
@Service
@Transactional(readOnly = true)
public class AppRoleService extends BaseService{

	@Autowired
	private SystemService systemService;
	
	
	/**
	 * 将一个user对象伪装成一个登录用户，并给他赋予相应的功能权限
	 * @param user
	 * @return
	 */
	@SuppressWarnings("static-access")
	public User getCurrentUser(User user) throws Exception{
		
		// 1、从数据库获取当前用户的完整User对象
		User user_ = systemService.getUser(user.getId());
		// 2、用户权限验证
		if("thinkgem".equals(user_.getLoginName()) || "1".equals(user_.getId())){
			// 2.1、超级管理员
			user_.isAdmin(user_.getId());
		}else{
			// 之前表没有关联关系时获取权限方法
			// 2.2、一般管理员
//			List<Role> roleList = roleDao.findRolesByUserId(user.getId()); // user.getId()
//			user.setRoleList(roleList);
		}
		// 3、设置当前用户（模拟的登录用户）
		user.setCurrentUser(user_);
		
		return user;
	}
	
}
