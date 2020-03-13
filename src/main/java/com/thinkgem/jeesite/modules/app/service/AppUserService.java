package com.thinkgem.jeesite.modules.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.app.bean.AppConfigure;
import com.thinkgem.jeesite.modules.app.bean.AppPage;
import com.thinkgem.jeesite.modules.app.bean.AppResult;
import com.thinkgem.jeesite.modules.app.bean.UtilDate;
import com.thinkgem.jeesite.modules.queryinfo.dao.QueryInfoDao;
import com.thinkgem.jeesite.modules.sqtpy.dao.TpyUserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import net.sf.json.JSONObject;

/**
 * APP 特派员/需求单位管理
 *
 * 赵凯浩
 * 2017年8月24日 下午1:05:27
 */
@Service
@Transactional(readOnly = true)
public class AppUserService extends BaseService{

	@Autowired
	private QueryInfoDao queryinfoDao;
	@Autowired
	private SystemService systemService;
	@Autowired
	private AppRoleService appRoleService;
	@Autowired
	private TpyUserDao tpyUserDao;
	
	/**
	 * 查询当前管理员下的所有特派员列表
	 * @param user
	 * @return
	 */
	public AppResult findTpyAll(@RequestBody JSONObject jsonObj) {
		AppResult appResult = new AppResult();
		AppPage appPage = new AppPage(jsonObj);

		try {
			// 1、【前台】分页页码
			int pageNo = appPage.getPageNo();
			// 2、【前台】User对象
			User user = (User) JSONObject.toBean(appPage.getData(), User.class);
			// 3、将当前用户伪装成已登录用户，并赋予所需的功能权限
			user = appRoleService.getCurrentUser(user);
			// 4、组装分页对象
			Page<User> page = new Page<User>(pageNo, AppConfigure.pageSize);
			page.setOrderBy("check_flag");
			user.setPage(page);
			// 5、所在机构
			User userOffice = systemService.getUser(user.getId());
			user.setOffice(userOffice.getOffice());
			// 6、特派员列表
			List<User> userList = queryinfoDao.QueryTpyInfoListApp(user);
			page.setList(userList);
			// 组装返回数据
			appResult.setObj(page);
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	
	/**
	 * 查询当前管理员下的所有需求单位列表
	 * @param user
	 * @return
	 */
	public AppResult findXqdwAll(@RequestBody JSONObject jsonObj) {
		AppResult appResult = new AppResult();
		AppPage appPage = new AppPage(jsonObj);
		
		try {
			// 1、【前台】分页页码
			int pageNo = appPage.getPageNo();
			// 2、【前台】User对象
			User user = (User) JSONObject.toBean(appPage.getData(), User.class);
			// 3、将当前用户伪装成已登录用户，并赋予所需的功能权限
			user = appRoleService.getCurrentUser(user);
			// 4、组装分页对象
			Page<User> page = new Page<User>(pageNo, AppConfigure.pageSize);
			page.setOrderBy("check_flag");
			user.setPage(page);
			// 5、所在机构
			User userOffice = systemService.getUser(user.getId());
			user.setOffice(userOffice.getOffice());
			// 6、需求单位列表
			List<User> userList = queryinfoDao.QueryXqdwInfoListApp(user);
			page.setList(userList);
			// 组装返回数据
			appResult.setObj(page);
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}

		return appResult;
	}
	
	/**
	 * 审核
	 * @param jsonUser
	 * @return
	 */
	@Transactional(readOnly = false)
	public AppResult checkUser(@RequestBody JSONObject jsonUser){
		AppResult appResult = new AppResult();
		
		try {
			// 1.1、【前台】当前用户id
			String id = jsonUser.getString("id");
			// 1.2、【前台】被审核对象（自然人、法人、需求单位）
			User user = (User) JSONObject.toBean(jsonUser.getJSONObject("data"), User.class);
			// 2、缓存前台传来的审核信息，避免被第3步的user覆盖
			String checkFlag = user.getCheckFlag(); // 状态
			String checkAdvice = user.getCheckAdvice(); // 意见
			String checkPerson = jsonUser.getString("checkPerson"); // 审核人
			// 3、通过id从数据库查询完整User对象
			user = systemService.getUser(user.getId());
			// 4、设置审核参数
			user.setCheckFlag(checkFlag);
			user.setCheckAdvice(checkAdvice);
			user.setCheckPerson(checkPerson);
			user.setCheckTime(UtilDate.getSimple(new Date()));
			// 5、审核
			if("1".equals(user.getPersonFlag())){
				// 5.1、需求单位
				systemService.updateXdShResultById(id, user);
			}else{
				// 5.2、特派员（自然人、法人）				
				systemService.updateTpyShResultById(id, user);			
			}
			// 6、清除缓存中的User（没有这一步时会出错）
			UserUtils.clearCache(user);
			// 组装返回数据
			appResult.setMsg("2".equals(user.getCheckFlag())?"已通过":"已拒绝");
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	
	/**
	 * 根据特派员ID查询指定特派员/需求单位
	 * @param user
	 * @return
	 */
	public AppResult findUserById(String userId) {
		AppResult appResult = new AppResult();

		try {
			// 1、通过id从数据库查询完整User对象
			//User user = systemService.getUser(userId);
			User user = tpyUserDao.getTpybyId(userId);
			// 2、获取推荐表图片原始路径字符串
			String[] tjTableImage = {};
			if(user.getTjTableImage()!=null){
				tjTableImage = user.getTjTableImage().split("\\|");
			}
			//3、拼装成可访问的推荐表图片路径
			String img = "";
			for (String string : tjTableImage) {
				img += AppConfigure.tjImage + string.substring(12) + ",";
			}
			// 4、单位类型(代号转名称)
			String corpType = user.getCorpType();
			if(corpType!=null){
				user.setCorpType(DictUtils.getDictLabel(user.getCorpType(), "corp_type", user.getCorpType()));
			}
			// 返回结果对象
			appResult.setObj(user);
			appResult.setSubObj(user.getOffice());
			appResult.setThirdObj(img.substring(0, img.length()-1));
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}

}
