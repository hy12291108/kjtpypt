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
import com.thinkgem.jeesite.modules.sqtpy.dao.SqtpyDao;
import com.thinkgem.jeesite.modules.sqtpy.dao.TpyShDao;
import com.thinkgem.jeesite.modules.sqtpy.entity.Sqtpy;
import com.thinkgem.jeesite.modules.sqtpy.service.SqtpyService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;

import net.sf.json.JSONObject;

/**
 * APP 需求审核
 *
 * 赵凯浩
 * 2017年10月31日 下午2:41:32
 */
@Service
@Transactional(readOnly = true)
public class DemandCheckService extends BaseService{
	
	@Autowired
	private SqtpyDao sqtpyDao;
	@Autowired
	private TpyShDao tpyShDao;
	@Autowired
	private SqtpyService sqtpyService;
	@Autowired
	private SystemService systemService;
	
	
	/**
	 * 审核
	 * @param jsonObj
	 * @return
	 */
	@Transactional(readOnly = false)
	public AppResult demcheck(@RequestBody JSONObject jsonObj){
		AppResult appResult = new AppResult();
		
		try {
			// 1、【前台】选择的特派员,需求申请对象
			User tpy = (User) JSONObject.toBean(jsonObj.getJSONObject("tpy"), User.class);
			Sqtpy sqtpy = (Sqtpy) JSONObject.toBean(jsonObj.getJSONObject("data"), Sqtpy.class);
			// 2、缓存前台传来的checkFlag，避免被第二步的user覆盖
			String state = sqtpy.getState();
			String zpryj = sqtpy.getZpryj();
			String zpr = jsonObj.getString("zpr");
			// 3、通过id从数据库查询完整User对象
			sqtpy = sqtpyDao.get(sqtpy.getId());
			// 4、更换特派员
			chooseTpy(sqtpy, tpy);
			// 5、审核参数
			sqtpy.setState(state);
			sqtpy.setZpryj(zpryj);
			sqtpy.setZpr(zpr);
			sqtpy.setZpTime(UtilDate.getSimple(new Date()));
			// 6、修改需求单位申请特派员对象
			boolean flag = sqtpyDao.updateTpySqInfo1(sqtpy)>0?true:false;
			// 组装返回数据
			if(flag){				
				appResult.setMsg("2".equals(sqtpy.getState())?"审核通过":"已拒绝");
			}else{
				appResult.setMsg("已拒绝");
				appResult.setSuccess(false);
			}
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	// 更换其他特派员
	private void chooseTpy(Sqtpy sqtpy, User user) throws Exception {
		if(user.getId()==null || user.getId().equals("")){
			return;
		}
		
		sqtpy.setTpyid(user.getId());
		sqtpy.setTpyname(user.getName());
		sqtpy.setZc(user.getTpyTitle());
		sqtpy.setZy(user.getTpyMajor());
		sqtpy.setTechspecial(user.getTpySpecial());
		sqtpy.setCompany(user.getTpyCompany());
		sqtpy.setMobile(user.getMobile());
		sqtpy.setOffice(user.getOffice());
	}

	/**
	 * 查询所有记录
	 * @param jsonObj
	 * @return
	 */
	public AppResult findDemandCheckAll(@RequestBody JSONObject jsonObj) {
		AppResult appResult = new AppResult();
		AppPage appPage = new AppPage(jsonObj);
		
		try {
			// 1、【前台】分页页码
			int pageNo = appPage.getPageNo();
			// 2、【前台】需求单位申请特派员对象
			Sqtpy sqtpy = (Sqtpy) JSONObject.toBean(appPage.getData(), Sqtpy.class);
//			// 3、将当前用户伪装成已登录用户，并赋予所需的功能权限
//			User user = appRoleService.getCurrentUser(new User(jsonObj.getString("id")));
//			// 4、生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
//			sqtpy.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
			// 5、所在机构
			User userOffice = systemService.getUser(jsonObj.getString("id"));
			sqtpy.setOffice(userOffice.getOffice());
			// 6、组装分页对象
			Page<Sqtpy> page = new Page<Sqtpy>(pageNo, AppConfigure.pageSize);
			sqtpy.setPage(page);
			// 7、获取需求单位申请特派员分页数据集
			page.setList(tpyShDao.findListbyApp(sqtpy));
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
	 * 通过id查询需求申请记录
	 * @param id
	 * @param officeId
	 * @return
	 */
	public AppResult findById(String id, String officeId) {
		AppResult appResult = new AppResult();
		
		try {
			// 1、通过id从数据库查询完整User对象
			Sqtpy sqtpy = tpyShDao.get(id);
			// 2、申请的特派员
			User user = systemService.getUser(sqtpy.getTpyid());
			// 3、当前市县该专业下的所有特派员列表
			String major = DictUtils.getDictValue(sqtpy.getZy(), "tpy_major", sqtpy.getZy());
			List<User> tpyList = sqtpyService.TpyListbyMajor(officeId, major);
			// 组装返回数据
			appResult.setObj(sqtpy);
//			appResult.setSubObj(user.getOffice() == null ? null : user.getOffice().getId());
			appResult.setThirdObj(tpyList);
			appResult.setFourObj(user);
		} catch (Exception e) {
			e.printStackTrace();
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}

}
