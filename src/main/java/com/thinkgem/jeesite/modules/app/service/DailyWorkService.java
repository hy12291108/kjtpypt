package com.thinkgem.jeesite.modules.app.service;

import java.text.SimpleDateFormat;
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
import com.thinkgem.jeesite.modules.dailywork.dao.tpy.TpyDailyrecordDao;
import com.thinkgem.jeesite.modules.dailywork.entity.tpy.TpyDailyrecord;
import com.thinkgem.jeesite.modules.dailywork.service.tpy.TpyDailyrecordService;
import com.thinkgem.jeesite.modules.sqtpy.dao.SqtpyDao;
import com.thinkgem.jeesite.modules.sqtpy.entity.Sqtpy;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.villagemanage.entity.TeamMember;
import com.thinkgem.jeesite.modules.villagemanage.service.VillageService;

import net.sf.json.JSONObject;

/**
 * APP 工作日志填报
 *
 * 赵凯浩
 * 2017年8月29日 上午10:35:47
 */
@Service
@Transactional(readOnly = true)
public class DailyWorkService extends BaseService{

	@Autowired
	private SqtpyDao sqtpyDao;
	@Autowired
	private VillageService villageService;
	@Autowired
	private TpyDailyrecordDao tpyDailyrecordDao;
	@Autowired
	private SystemService systemService;
	@Autowired
	private TpyDailyrecordService tpyDailyrecordService;
	
	
	/**
	 * 通过特派员id查询服务对象
	 * @param tpyid
	 * @return
	 */
	public AppResult findServiceObjByTpyid(String tpyid){
		AppResult appResult = new AppResult();

		try {
			// 1、获取服务对象列表
			List<Sqtpy> sqtpyList = sqtpyDao.findAllByTpyidApp(tpyid); 
			appResult.setObj(sqtpyList);
			// 2、抓取列表第一个服务对象
			if(sqtpyList.size() > 0){
				appResult.setFourObj(sqtpyList.get(0));
			}
			// 3、获取提供服务的特派团列表
			TeamMember teamMember = new TeamMember();
			teamMember.setUserId(tpyid);
			List<TeamMember> teamList = villageService.getTeam(teamMember); 
			appResult.setSubObj(teamList);
			// 4、抓取列表第一个特派团
			if(teamList.size() > 0){
				appResult.setThirdObj(teamList.get(0));
			}else{
				appResult.setThirdObj("");
			}
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.getMessage());
		}
		
		return appResult;
	}
	
	/**
	 * 新增日志填报
	 * 贫困村
	 * @param jsonObj
	 * @return
	 */
	@Transactional(readOnly = false)
	public AppResult save(@RequestBody JSONObject jsonObj){
		AppResult appResult = new AppResult();
		
		try {
			// 1、【前台】特派员工作日志对象
			TpyDailyrecord dailyRecord = (TpyDailyrecord) JSONObject.toBean(jsonObj, TpyDailyrecord.class);
			// 1.1、审批状态
			dailyRecord.setRecStatus("no");
			// 1.2、填写时间
			dailyRecord.setRecWrittenTime(UtilDate.getSimple(new Date()));
			// 当前特派员
			User user = systemService.getUser(dailyRecord.getRecTpyid());
			// 1.3、创建时间、创建人
			dailyRecord.setCreateDate(new Date());
			dailyRecord.setCreateBy(user);
			dailyRecord.setUpdateBy(user);
			/**特派员id, name, 归属部门（office）从前台缓存中读取*/
			// 1.4、特派员类型
			String tpyType = "0".equals(user.getPersonFlag())?
					"自然人特派员":"2".equals(user.getPersonFlag())?
					"法人特派员":"未知特派员";
			dailyRecord.setTpytype(tpyType);
			// 1.5、年度
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy");
			String Year = sdf1.format(new Date())+"年";
			dailyRecord.setYear(Year);
			// 1.6、是否三区人才
			List<TpyDailyrecord> ThreeArealist=tpyDailyrecordService.CheckIsThreeAreaByYear(dailyRecord);
			if(ThreeArealist.size()>0){
				dailyRecord.setIsThreeArea("是");
			}else{
				dailyRecord.setIsThreeArea("否");
			}
			// 1.7、开始和结果时间
			dailyRecord.setRecStartTime(dailyRecord.getRecStartTime().substring(0, 10));
			dailyRecord.setRecEndTime(dailyRecord.getRecEndTime().substring(0, 10));
			// 2、保存
			tpyDailyrecordService.save(dailyRecord);
			appResult.setMsg("日志填报成功");
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	
	/**
	 * 新增日志填报
	 * 服务对象
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	public AppResult submit(String id){
		AppResult appResult = new AppResult();
		
		try {
			TpyDailyrecord tpyDailyrecord = tpyDailyrecordService.get(id); 
			tpyDailyrecord.setRecStatus("inProcess");
			tpyDailyrecordService.change(tpyDailyrecord);
			appResult.setMsg("提交成功");
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.getMessage());
		}
		
		return appResult;
	}
	
	/**
	 * 保存日志填报（修改）
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false)
	public AppResult update(JSONObject jsonObj){
		AppResult appResult = new AppResult();
		
		try {
			// 1、【前台】特派员工作日志对象
			TpyDailyrecord dailyRecord = (TpyDailyrecord) JSONObject.toBean(jsonObj, TpyDailyrecord.class);
			// 1.1、审批状态
			if(!"no".equals(dailyRecord.getRecStatus())){
				dailyRecord.setRecStatus("inProcess");				
			}
			// 1.2、当前特派员
			User user = systemService.getUser(dailyRecord.getRecTpyid());
			// 1.3、修改人、修改时间
			dailyRecord.setUpdateBy(user);
			dailyRecord.setUpdateDate(new Date());
			// 2、保存
			tpyDailyrecordService.changetpyDailyrecord(dailyRecord);
			// 组装返回数据
			appResult.setMsg("日志修改成功");
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.getMessage());
		}
		
		return appResult;
	}
	
	/**
	 * 通过特派员id查询指定特派员的工作日志
	 * @param jsonObj
	 * @return
	 */
	public AppResult findAllByRecTpyId(JSONObject jsonObj){
		AppResult appResult = new AppResult();
		AppPage appPage = new AppPage(jsonObj);
		
		try {
			// 1、【前台】分页页码
			int pageNo = appPage.getPageNo();
			// 2、【前台】工作日志对象
			TpyDailyrecord dailyRecord = (TpyDailyrecord) JSONObject.toBean(appPage.getData(), TpyDailyrecord.class);
			// 3、组装分页对象
			Page<TpyDailyrecord> page = new Page<TpyDailyrecord>(pageNo, AppConfigure.pageSize);
			page.setOrderBy("recStatus");
			dailyRecord.setPage(page);
			// 4、查询指定特派员的日志列表
			page.setList(tpyDailyrecordDao.findAllByRecTpyIdApp(dailyRecord));
			// 返回带分页信息的数据集
			appResult.setObj(page);
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.getMessage());
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
			// 1、日志基础对象
			TpyDailyrecord dailyRecord = tpyDailyrecordDao.get(id);
			// 2、特派员类型
			User user = systemService.getUser(dailyRecord.getRecTpyid());
			String tpyType = "0".equals(user.getPersonFlag())?
					"自然人特派员":"2".equals(user.getPersonFlag())?
					"法人特派员":"未知特派员";
			dailyRecord.setTpytype(tpyType);
			// 3、获取图片
			String[] tjTableImage = {};
			if(dailyRecord.getDrTableImage()!=null){
				tjTableImage = dailyRecord.getDrTableImage().split("\\|");
			}
			String img = "";
			for (String string : tjTableImage) {
				if(string.length()>12){
					img += AppConfigure.drImage + string.substring(12) + ",";					
				}
			}
			// 返回数据
			appResult.setObj(dailyRecord);
			appResult.setThirdObj(img.substring(0, img.length()-1));
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.getMessage());
		}
	
		return appResult;
	}

}
