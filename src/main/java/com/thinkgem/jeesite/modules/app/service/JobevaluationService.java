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
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

import net.sf.json.JSONObject;

/**
 * APP 工作评价
 *
 * 赵凯浩
 * 2017年10月31日 下午2:42:02
 */
@Service
@Transactional(readOnly = true)
public class JobevaluationService extends BaseService {

	@Autowired
	private AppRoleService appRoleService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private TpyDailyrecordDao tpyDailyrecordDao;
	
	
	/**
	 * 工作日志评价
	 * @param jsonObj
	 * @return
	 */
	@Transactional(readOnly = false)
	public AppResult evaluate(JSONObject jsonObj){
		AppResult appResult = new AppResult();
		
		try {
			// 1、【前台】特派员日志对象
			TpyDailyrecord tpyDailyrecord = (TpyDailyrecord) JSONObject.toBean(jsonObj, TpyDailyrecord.class);
			// 2、缓存评价人、评价意见
			String fwdxpjr = tpyDailyrecord.getFwdxpjr(); // 评价人的姓名
			String fwdxpjYj = tpyDailyrecord.getFwdxpjYj(); // 评价内容
			// 3、获取并修改数据库中的日志对象
			TpyDailyrecord dailyrecord = tpyDailyrecordDao.get(tpyDailyrecord.getId());
			// 3.1、评价状态
			dailyrecord.setFwdxpjState("1");
			// 3.2、评价内容
			dailyrecord.setFwdxpjYj(fwdxpjYj);
			// 3.3、评价人、评价时间
			dailyrecord.setFwdxpjr(fwdxpjr);
			dailyrecord.setFwdxpjTime(UtilDate.getSimple(new Date()));
			// 4、修改日志对象
			tpyDailyrecordDao.savepj(dailyrecord);
			// 组装返回数据
			appResult.setMsg("评价成功");
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
			// 1、【前台】分页页码
			int pageNo = appPage.getPageNo();
			// 2、【前台】特派员日志对象
			TpyDailyrecord tpyDailyrecord = (TpyDailyrecord) JSONObject.toBean(appPage.getData(), TpyDailyrecord.class);
			tpyDailyrecord.setRecStatus("pass");
			// 3、将当前用户伪装成已登录用户，并赋予所需的功能权限
			String id = jsonObj.getString("id");
			User user = appRoleService.getCurrentUser(new User(id));
			// 4、生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
			tpyDailyrecord.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
			// 5、组装分页对象
			Page<TpyDailyrecord> page = new Page<TpyDailyrecord>(pageNo, AppConfigure.pageSize);
			page.setOrderBy("recStatus ASC, recWrittenTime DESC");
			tpyDailyrecord.setPage(page);
			// 6、机构过滤
			tpyDailyrecord.setOffice(systemService.getUser(id).getOffice());
			// 组装返回数据
			page.setList(tpyDailyrecordDao.xqdwckPage(tpyDailyrecord));
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
			TpyDailyrecord dr = tpyDailyrecordDao.get(id);
			// 2、获取图片
			String[] tjTableImage = {};
			if(dr.getDrTableImage()!=null){
				tjTableImage = dr.getDrTableImage().split("\\|");
			}
			String img = "";
			for (String string : tjTableImage) {
				if(string.length() > 12){
					img += AppConfigure.tjImage + string.substring(12) + ",";					
				}
			}
			// 返回数据
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
