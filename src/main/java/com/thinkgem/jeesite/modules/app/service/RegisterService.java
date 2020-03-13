package com.thinkgem.jeesite.modules.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.app.bean.AppConfigure;
import com.thinkgem.jeesite.modules.app.bean.AppResult;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.MajorMenuService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import net.sf.json.JSONObject;

/**
 * APP 注册
 *
 * 赵凯浩
 * 2017年8月22日 上午9:10:40
 */
@Service
@Transactional(readOnly = true)
public class RegisterService extends BaseService {
	
	@Autowired
	private SystemService systemService;
	@Autowired
	private MajorMenuService majorMenuService;
	
	
	/**
	 * 注册（自然人、法人、需求单位）
	 * @param jsonObj
	 * @return
	 */
	@Transactional(readOnly = false)
	public AppResult register(JSONObject jsonObj){
		AppResult appResult = new AppResult();
		
		try {			
			// 1、【前台】User对象
			User user = (User) JSONObject.toBean(jsonObj.getJSONObject("user"), User.class);
			// 2、【前台】工作单位(office)
			Office office = (Office) JSONObject.toBean(jsonObj.getJSONObject("office"), Office.class);	
			// 3、设置默认属性
			user.setCompany(new Office("08bae2518f1646dfa9e0b6cedf904b54")); // 主管单位（company:陕西省科技厅）
			user.setOffice(office); // 设置工作单位
			user.setLoginFlag("1");
			user.setCheckFlag("0");
			// 4添加时密码不为null，修改时密码为null
			if(user.getNewPassword()!=null){
				// 4.1、加密密码
				user.setPassword(SystemService.entryptPassword(user.getNewPassword()));
				// 4.2、登录名已存在
				if (!"true".equals(checkLoginName(user.getOldLoginName(), user.getLoginName()))){
					appResult.setMsg("保存'" + user.getLoginName() + "'失败，登录名已存在");
					appResult.setSuccess(false);
					return appResult;
				}			
			}
			// 5、角色数据验证，根据角色设置省份、部门
			List<Role> roleList = Lists.newArrayList();
			// 5.1、默认需求单位角色
			/**--------------------区分需求单位和特派员----------------------------------*/
			List<Role> roleList_ = systemService.findXdRole();
			String roleIdList = "abf520ca23614c0da7dfd4057485f6ed";
			// 5.2、是否是特派员角色
			if(!"1".equals(user.getPersonFlag())){
				roleList_ = systemService.findTpyRole();
				roleIdList = "3bb6453c699d49508b15529670ad9e9b";
				user.setTpyXpFlag(office.getId());
			}
			/**--------------------区分需求单位和特派员----------------------------------*/
			// 5.3、角色获取
			for (Role r : roleList_){
				if (roleIdList.equals(r.getId())){roleList.add(r);}
			}
			// 5.4、设置角色列表
			user.setRoleList(roleList);
			if(user.getId()!=null){
				user.setUpdateBy(user);
			}
			// 6、保存用户信息
			systemService.saveUser1(user);
			// 7、清除当前用户缓存
			if (user.getLoginName().equals(UserUtils.getUser().getLoginName())){
				UserUtils.clearCache();
			}
			appResult.setMsg("提交成功,请等待管理员审核...");
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	
	/**
	 * 验证登录名是否有效
	 * @param oldLoginName
	 * @param loginName
	 * @return
	 */
	public String checkLoginName(String oldLoginName, String loginName) {
		if (loginName !=null && loginName.equals(oldLoginName)) {
			return "true";
		} else if (loginName !=null && systemService.getUserByLoginName(loginName) == null) {
			return "true";
		}
		return "false";
	}
	
	/**
	 * 获取职称、性别、学历，以及专业类别和专业
	 * @param type
	 * @return
	 */
	public AppResult getDictList(){
		AppResult appResult = new AppResult();
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {			
			// 1、获取性别列表
			map.put("sex", DictUtils.getDictList("sex"));
			// 2、获取职称列表
			map.put("tpy_title", DictUtils.getDictList("tpy_title"));
			// 3、获取学历列表
			map.put("tpy_qulification", DictUtils.getDictList("tpy_qulification"));
			// 4、获取单位类型列表
			map.put("corp_type", DictUtils.getDictList("corp_type"));
			// 5、获取专业类型列表
			map.put("majorTypeList", majorMenuService.findAllMajorMenu());
			// 6、获取所有专业列表
			map.put("majorList", majorMenuService.findMajorMenu());
			// 返回处理结果
			appResult.setObj(map);
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	
	/**
	 * 游客注册
	 * 原始方法改写
	 * @param jsonObj
	 * @return
	 */
	@Transactional(readOnly = false)
	public AppResult visitor(JSONObject jsonObj){
		AppResult appResult = new AppResult();
		
		try {
			User user = (User) JSONObject.toBean(jsonObj, User.class);
			user.setPersonFlag("4");
			user.setCompany(new Office("08bae2518f1646dfa9e0b6cedf904b54"));
			user.setOffice(new Office("08bae2518f1646dfa9e0b6cedf904b54") );
			user.setPassword(SystemService.entryptPassword(user.getNewPassword()));
			// 4.2、登录名已存在
			if (!"true".equals(checkLoginName(user.getOldLoginName(), user.getLoginName()))){
				appResult.setMsg("保存'" + user.getLoginName() + "'失败，登录名已存在");
				appResult.setSuccess(false);
				return appResult;
			}
			systemService.tempUserSave(user);
			appResult.setMsg("注册成功");
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}

}
