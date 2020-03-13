package com.thinkgem.jeesite.modules.app.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.app.bean.AppConfigure;
import com.thinkgem.jeesite.modules.app.bean.AppPage;
import com.thinkgem.jeesite.modules.app.bean.AppResult;
import com.thinkgem.jeesite.modules.app.bean.UtilDate;
import com.thinkgem.jeesite.modules.dailywork.dao.tpy.TpyDailyrecordDao;
import com.thinkgem.jeesite.modules.dailywork.entity.tpy.TpyDailyrecord;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

import net.sf.json.JSONObject;

/**
 * APP 工作日志查询审核
 *
 * 赵凯浩
 * 2017年8月29日 下午3:56:10
 */
@Service
@Transactional(readOnly = true)
public class WorkLogService extends BaseService{

	@Autowired
	private UserDao userDao;
	@Autowired
	private SystemService systemService;
	@Autowired
	private TpyDailyrecordDao tpyDailyrecordDao;
	
	
	/**
	 * 日志审核
	 * @param jsonObj
	 * @return
	 */
	@Transactional(readOnly = false)
	public AppResult check(JSONObject jsonObj){
		AppResult appResult = new AppResult();
		
		try {
			// 1、【前台】特派员日志对象
			TpyDailyrecord tpyDailyrecord = (TpyDailyrecord) JSONObject.toBean(jsonObj, TpyDailyrecord.class);
			// 2、缓存审核批信息
			String recStatus = tpyDailyrecord.getRecStatus(); // 审批状态
			String recApproopinion = tpyDailyrecord.getRecApproopinion(); // 意见
			String recAppropersonid = tpyDailyrecord.getRecAppropersonid(); // 审核人
			// 3、得到当前用户
			User user = userDao.get(recAppropersonid);
			// 4、获取并修改数据库中的日志对象
			TpyDailyrecord dailyrecord = tpyDailyrecordDao.get(tpyDailyrecord.getId());
			// 4.1、审批状态
			dailyrecord.setRecStatus(recStatus);
			// 4.2、审批人姓名、id
			dailyrecord.setRecApproperson(user.getName());
			dailyrecord.setRecAppropersonid(user.getId());
			// 4.3、审批意见
			dailyrecord.setRecApproopinion(recApproopinion);
			// 4.4、审批时间
			dailyrecord.setRecApprotime(UtilDate.getSimple(new Date()));
			// 5、保存
			tpyDailyrecordDao.update(dailyrecord);
			// 组装返回数据
			if("pass".equals(recStatus)){
				appResult.setMsg("已通过");				
			}else{
				appResult.setMsg("已拒绝");
			}
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	
	/**
	 * 查询当前管理员下的特派员的日志
	 * @param jsonObj
	 * @return
	 */
	public AppResult findAll(JSONObject jsonObj){
		AppResult appResult = new AppResult();
		AppPage appPage = new AppPage(jsonObj);
		
		try {
			// 1.1、【前台】分页页码
			int pageNo = appPage.getPageNo();
			// 1.2、【前台】特派员日志对象
			TpyDailyrecord tpyDailyrecord = (TpyDailyrecord) JSONObject.toBean(appPage.getData(), TpyDailyrecord.class);
			// 2、组装分页对象
			Page<TpyDailyrecord> page = new Page<TpyDailyrecord>(pageNo, AppConfigure.pageSize);
			page.setOrderBy("recStatus ASC, recWrittenTime DESC");
			tpyDailyrecord.setPage(page);
			// 3、所在机构
			User userOffice = systemService.getUser(jsonObj.getString("id"));
			tpyDailyrecord.setOffice(userOffice.getOffice());
			// 4、获取日志列表
			page.setList(tpyDailyrecordDao.findAllByApp(tpyDailyrecord));
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
	 * 通过日志id查询日志对象
	 * @param id
	 * @return
	 */
	public AppResult findById(String id){
		AppResult appResult = new AppResult();
		
		try {
			// 1、获取日志对象
			TpyDailyrecord dr = tpyDailyrecordDao.get(id);
			// 2、获取原始图片路径字符串
			String[] tjTableImage = {};
			if(dr.getDrTableImage()!=null){
				tjTableImage = dr.getDrTableImage().split("\\|");
			}
			// 3、拼接成可访问的图片路径
			String img = "";
			for (String string : tjTableImage) {
				if(string.length() > 12){
					img += AppConfigure.drImage + string.substring(12) + ",";
				}
			}
			// 组装返回数据
			appResult.setObj(dr);
			appResult.setThirdObj(img.substring(0, img.length()-1));
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
	
		return appResult;
	}
	
}
