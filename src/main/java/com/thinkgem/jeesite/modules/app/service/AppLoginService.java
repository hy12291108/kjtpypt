package com.thinkgem.jeesite.modules.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.app.bean.AppConfigure;
import com.thinkgem.jeesite.modules.app.bean.AppResult;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

import net.sf.json.JSONObject;

/**
 * APP 登录
 *
 * 赵凯浩
 * 2017年9月11日 上午11:26:19
 */
@Service
@Transactional(readOnly = true)
public class AppLoginService extends BaseService{

	@Autowired
	private UserDao userDao;

	
	/**
	 * 用户登录
	 * @param jsonObj
	 * @return
	 */
	public AppResult login(JSONObject jsonObj){
		AppResult appResult = new AppResult();
		
		try {
			// 1、【前台】User对象（主要包含loginName和password属性）
			User userJson = (User) JSONObject.toBean(jsonObj, User.class);
			// 2、通过loginName在数据库查询用户独享；
			User user = userDao.getByLoginName(userJson);
			// 3、判断账号密码是否正确并组装返回数据
			if(user != null && SystemService.validatePassword(userJson.getPassword(), user.getPassword())){
				appResult.setMsg("登录成功");
				appResult.setObj(user);
				appResult.setSubObj(user.getOffice());
			}else{
				appResult.setSuccess(false);
				appResult.setMsg("账号或密码错误");
			}
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
	
		return appResult;
	}
	
}
