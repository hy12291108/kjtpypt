package com.thinkgem.jeesite.modules.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.app.bean.AppConfigure;
import com.thinkgem.jeesite.modules.app.bean.AppPage;
import com.thinkgem.jeesite.modules.app.bean.AppResult;
import com.thinkgem.jeesite.modules.dailywork.dao.tpy.TpyAnnualreportDao;
import com.thinkgem.jeesite.modules.dailywork.dao.tpy.TpyDailyrecordDao;
import com.thinkgem.jeesite.modules.dailywork.entity.tpy.TpyAnnualreport;
import com.thinkgem.jeesite.modules.dailywork.entity.tpy.TpyDailyrecord;
import com.thinkgem.jeesite.modules.queryinfo.dao.QueryInfoCountDao;
import com.thinkgem.jeesite.modules.queryinfo.entity.QueryInfoCount;
import com.thinkgem.jeesite.modules.queryinfo.service.QueryInfoService;
import com.thinkgem.jeesite.modules.sqtpy.dao.TpyShDao;
import com.thinkgem.jeesite.modules.sqtpy.entity.Sqtpy;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.villagemanage.dao.ServiceTeamDao;
import com.thinkgem.jeesite.modules.villagemanage.dao.TeamMemberDao;
import com.thinkgem.jeesite.modules.villagemanage.dao.VillageDao;
import com.thinkgem.jeesite.modules.villagemanage.entity.ServiceTeam;
import com.thinkgem.jeesite.modules.villagemanage.entity.TeamMember;
import com.thinkgem.jeesite.modules.villagemanage.entity.Village;

import net.sf.json.JSONObject;

/**
 * APP 统计查询
 *
 * 赵凯浩
 * 2017年8月30日 下午12:44:28
 */
@Service
@Transactional(readOnly = true)
public class StatisticalService extends BaseService{
	
	@Autowired
	private TpyShDao tpyshDao;
	@Autowired
	private VillageDao villageDao;
	@Autowired
	private TeamMemberDao teamMemberDao;
	@Autowired
	private ServiceTeamDao serviceTeamDao;
	@Autowired
	private QueryInfoCountDao queryInfoCountDao;
	@Autowired
	private TpyDailyrecordDao tpyDailyrecordDao;
	@Autowired
	private TpyAnnualreportDao tpyAnnualreportDao;
	
	@Autowired
	private SystemService systemService;
	@Autowired
	private AppRoleService appRoleService;
	@Autowired
	private QueryInfoService queryinfoservice;
		
	
	/**1、科技特派员名单、服务对象名单***************************************************/
	/**
	 * 查询当前管理员下的所有已通过的特派员列表
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"findTpyAll"})
	@ResponseBody
	public AppResult findTpyAll(@RequestBody JSONObject jsonObj) {
		AppResult appResult = new AppResult();
		AppPage appPage = new AppPage(jsonObj);
		
		try {
			// 1.1、【前台】分页页码
			int pageNo = appPage.getPageNo();
			// 1.2、【前台】User对象
			User user = (User) JSONObject.toBean(appPage.getData(), User.class);
			// 2、将当前用户伪装成已登录用户，并赋予所需的功能权限
			user = appRoleService.getCurrentUser(user);
			// 3、组装分页对象
			Page<User> page = new Page<User>(pageNo, AppConfigure.pageSize);
			page.setOrderBy("name");
			user.setCheckFlag("2"); // 2：审核通过
			// 4、获取特派员列表
			Page<User> userPage = queryinfoservice.findTpyInfoList(page, user);
			// 组装返回数据
			appResult.setObj(userPage);
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	
	/**
	 * 查询当前管理员下的所有已通过的服务对象列表
	 * @param jsonObj
	 * @return
	 */
	public AppResult findXqdwAll(@RequestBody JSONObject jsonObj) {
		AppResult appResult = new AppResult();
		AppPage appPage = new AppPage(jsonObj);
		
		try {
			// 1.1、【前台】分页页码
			int pageNo = appPage.getPageNo();
			// 1.2、【前台】User对象
			User user = (User) JSONObject.toBean(appPage.getData(), User.class);
			// 2、将当前用户伪装成已登录用户，并赋予所需的功能权限
			user = appRoleService.getCurrentUser(user);
			// 3、组装分页对象
			Page<User> page = new Page<User>(pageNo, AppConfigure.pageSize);
			page.setOrderBy("name");
			user.setCheckFlag("2"); // 2：审核通过
			// 4、获取特派员列表
			Page<User> userPage = queryinfoservice.findXqdwInfoList(page, user);
			// 组装返回数据
			appResult.setObj(userPage);
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}

		return appResult;
	}
	
	/**
	 * 根据特派员ID查询指定特派员/服务对象
	 * @param user
	 * @return
	 */
	public AppResult findUserById(String userId) {
		AppResult appResult = new AppResult();
		
		try {
			// 1、通过id从数据库查询完整User对象
			User user = systemService.getUser(userId);
			// 组装返回数据
			appResult.setObj(user);
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	/**1、科技特派员名单、需求单位名单***************************************************/
	
	
	/**2、特派员日志查询**********************************************************************/
	/**
	 * 特派员日志查询
	 * @param jsonObj
	 * @return
	 */
	public AppResult findTpyDailyrecordAll(JSONObject jsonObj){
		AppResult appResult = new AppResult();
		AppPage appPage = new AppPage(jsonObj);
		
		try {
			// 1.1、【前台】分页页码
			int pageNo = appPage.getPageNo();
			// 1.2、【前台】特派员日志对象
			TpyDailyrecord tpyDailyrecord = (TpyDailyrecord) JSONObject.toBean(appPage.getData(), TpyDailyrecord.class);
			// 2、将当前用户伪装成已登录用户，并赋予所需的功能权限
			User user = appRoleService.getCurrentUser(new User(jsonObj.getString("id")));
			// 3、生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
			tpyDailyrecord.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
			// 4、组装分页对象
			Page<TpyDailyrecord> page = new Page<TpyDailyrecord>(pageNo, AppConfigure.pageSize);
			page.setOrderBy("recStatus ASC, recWrittenTime DESC");
			tpyDailyrecord.setPage(page);
			// 5、获取日志列表
			page.setList(tpyDailyrecordDao.findListWithinPower(tpyDailyrecord));
			// 组装返回数据
			appResult.setObj(page);
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}

		return appResult;
	}
	/**2、特派员日志查询**********************************************************************/
	
	
	/**3、年度总结查询**********************************************************************/
	/**
	 * 年度总结查询
	 * @param jsonObj
	 * @return
	 */
	public AppResult findTpyAnnualreportAll(JSONObject jsonObj){
		AppResult appResult = new AppResult();
		AppPage appPage = new AppPage(jsonObj);
		
		try {
			// 1.1、【前台】分页页码
			int pageNo = appPage.getPageNo();
			// 1.2、【前台】特派员日志对象
			TpyAnnualreport tpyAnnualreport = (TpyAnnualreport) JSONObject.toBean(appPage.getData(), TpyAnnualreport.class);
			// 2、将当前用户伪装成已登录用户，并赋予所需的功能权限
			User user = appRoleService.getCurrentUser(new User(jsonObj.getString("id")));
			// 3、生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
			tpyAnnualreport.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
			// 4、组装分页对象
			Page<TpyAnnualreport> page = new Page<TpyAnnualreport>(pageNo, AppConfigure.pageSize);
			tpyAnnualreport.setPage(page);
			// 5、获取日志列表
			page.setList(tpyAnnualreportDao.findListWithinPower(tpyAnnualreport));
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
	 * 查看年度总结详情
	 * @param userId
	 * @return
	 */
	public AppResult findTpyAnnualreportById(String id) {
		AppResult appResult = new AppResult();
		
		try {
			TpyAnnualreport tpyAnnualreport = tpyAnnualreportDao.get(id);
			// 组装返回数据
			appResult.setObj(tpyAnnualreport);
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	/**3、年度总结查询**********************************************************************/
	
	
	/**4、帮扶情况查询***********************************************************************/
	/**
	 * 帮扶情况查询
	 * @param jsonObj
	 * @return
	 */
	public AppResult findSqtpyAll(JSONObject jsonObj){
		AppResult appResult = new AppResult();
		AppPage appPage = new AppPage(jsonObj);
		
		try {
			// 1.1、【前台】分页页码
			int pageNo = appPage.getPageNo();
			// 1.2、【前台】特派员日志对象
			Sqtpy sqtpy = (Sqtpy) JSONObject.toBean(appPage.getData(), Sqtpy.class);
			// 2、将当前用户伪装成已登录用户，并赋予所需的功能权限
			User user = appRoleService.getCurrentUser(new User(jsonObj.getString("id")));
			// 3、生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
			sqtpy.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
			// 4、组装分页对象
			Page<Sqtpy> page = new Page<Sqtpy>(pageNo, AppConfigure.pageSize);
			sqtpy.setPage(page);
			// 5、获取帮扶情况列表
			page.setList(tpyshDao.findShList1(sqtpy));
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
	 * 查看帮扶情况详情
	 * @param id
	 * @return
	 */
	public AppResult findSqtpyById(String id) {
		AppResult appResult = new AppResult();
		
		try {
			Sqtpy sqtpy = tpyshDao.get(id);
			// 组装返回数据
			appResult.setObj(sqtpy);
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	/**4、帮扶情况查询***********************************************************************/
	
	
	/**5、特派员统计图表**************************************************************/
	/**
	 * 特派员统计图表
	 * @param jsonObj
	 * @return
	 */
	public AppResult findTpytjviewAll(JSONObject jsonObj){
		AppResult appResult = new AppResult();
		AppPage appPage = new AppPage(jsonObj);
		
		try {
			// 1、【前台】特派员日志对象
			QueryInfoCount queryInfoCount = (QueryInfoCount) JSONObject.toBean(appPage.getData(), QueryInfoCount.class);
			String bfsx = getNmaeBySeries(queryInfoCount.getBfsx());
			// 转换帮扶属性名称为别称
			queryInfoCount.setBfsx(bfsx);
			// 2、将当前用户伪装成已登录用户，并赋予所需的功能权限
			User user = appRoleService.getCurrentUser(new User(jsonObj.getString("id")));
			// 3、当前用户
			queryInfoCount.setUser(user);
			// 4、查询数据
			queryInfoCount = queryinfoservice.getDyBfsxNum(queryInfoCount);
			// 组装返回数据
			appResult.setObj(getTpytjResult(bfsx, queryInfoCount));
			// 是否加载日志年份、帮扶属性
			if(!"true".equals(jsonObj.getString("flag"))){				
				appResult.setSubObj(queryinfoservice.getDrYear());
				appResult.setThirdObj(getBfsxList());
			}
		} catch (Exception e) {
			e.printStackTrace();
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}

		return appResult;
	}
	// 将统计结果转为数组
	private String[][] getTpytjResult(String bfsx, QueryInfoCount queryInfoCount) throws Exception{
		String[][] a = new String[2][13];
		
		// 1、名称
		a[0][0] = "01月";
		a[0][1] = "02月";
		a[0][2] = "03月";
		a[0][3] = "04月";
		a[0][4] = "05月";
		a[0][5] = "06月";
		a[0][6] = "07月";
		a[0][7] = "08月";
		a[0][8] = "09月";
		a[0][9] = "10月";
		a[0][10] = "11月";
		a[0][11] = "12月";
		a[0][12] = "总计";
		
		// 2.1数据-double型
		if("jtjjzc".equals(bfsx) || "sxcz".equals(bfsx) || "ddzs".equals(bfsx) || "cls".equals(bfsx)){
			a[1][0] = queryInfoCount.getJanuary1() + "";
			a[1][1] = queryInfoCount.getFebruary1() + "";
			a[1][2] = queryInfoCount.getMarch1() + "";
			a[1][3] = queryInfoCount.getApril1() + "";
			a[1][4] = queryInfoCount.getMay1() + "";
			a[1][5] = queryInfoCount.getJune1() + "";
			a[1][6] = queryInfoCount.getJuly1() + "";
			a[1][7] = queryInfoCount.getAugust1() + "";
			a[1][8] = queryInfoCount.getSeptember1() + "";
			a[1][9] = queryInfoCount.getOctober1() + "";
			a[1][10] = queryInfoCount.getNovember1() + "";
			a[1][11] = queryInfoCount.getDecember1() + "";
			a[1][12] = queryInfoCount.getTotal1() + "";
			
			return a;
		}
		
		// 2.2、数据-int型
		a[1][0] = queryInfoCount.getJanuary() + "";
		a[1][1] = queryInfoCount.getFebruary() + "";
		a[1][2] = queryInfoCount.getMarch() + "";
		a[1][3] = queryInfoCount.getApril() + "";
		a[1][4] = queryInfoCount.getMay() + "";
		a[1][5] = queryInfoCount.getJune() + "";
		a[1][6] = queryInfoCount.getJuly() + "";
		a[1][7] = queryInfoCount.getAugust() + "";
		a[1][8] = queryInfoCount.getSeptember() + "";
		a[1][9] = queryInfoCount.getOctober() + "";
		a[1][10] = queryInfoCount.getNovember() + "";
		a[1][11] = queryInfoCount.getDecember() + "";
		a[1][12] = queryInfoCount.getTotal() + "";

		return a;
	}
	// 帮扶属性
	private List<String> getBfsxList() throws Exception {
		List<String> list = new ArrayList<String>();
		
		list.add("集体经济增收 (单位:万元)");
		list.add("实现产值 (单位:万元)");
		list.add("带动增收 (单位:万元)");
		list.add("创利税 (单位:万元)");
		list.add("引进推广新品种新产品 (单位:个)");
		list.add("推广新技术 (单位:个)");
		list.add("解决技术难题 (单位:个)");
		list.add("培训农民 (单位:人次)");
		list.add("创办领办培育企业或合作社 (单位:个)");
		list.add("带动就业 (单位:人次)");
		list.add("服务农村合作组织 (单位:个)");
		list.add("建立科技示范基地 (单位:个)");
		list.add("培育科技示范户 (单位:户)");
		list.add("举办培训班 (单位:班次)");
		list.add("培训人员 (单位:人次)");
		list.add("培训贫困群众 (单位:人次)");
		list.add("帮扶贫困村 (单位:个)");
		list.add("贫困户 (单位:户)");
		list.add("带动脱贫 (单位:户)");
		list.add("贫困人口 (单位:个)");
		list.add("科技服务天数 (单位:天)");
		
		return list;
	}
	// 通过名称得到别名
	private String getNmaeBySeries(String bfsx) throws Exception {
		
		if("集体经济增收 (单位:万元)".equals(bfsx)){
			return "jtjjzc";
		}else 
		if("实现产值 (单位:万元)".equals(bfsx)){
			return "sxcz";
		}else 
		if("带动增收 (单位:万元)".equals(bfsx)){
			return "ddzs";
		}else 
		if("创利税 (单位:万元)".equals(bfsx)){
			return "cls";
		}else 
		if("引进推广新品种新产品 (单位:个)".equals(bfsx)){
			return "yjtgxpzxcp";
		}else 
		if("推广新技术 (单位:个)".equals(bfsx)){
			return "tgxjs";
		}else 
		if("解决技术难题 (单位:个)".equals(bfsx)){
			return "jjjsnt";
		}else 
		if("培训农民 (单位:人次)".equals(bfsx)){
			return "pxnm";
		}else 
		if("创办领办培育企业或合作社 (单位:个)".equals(bfsx)){
			return "cbqyhzs";
		}else 
		if("带动就业 (单位:人次)".equals(bfsx)){
			return "ddjyrc";
		}else 
		if("服务农村合作组织 (单位:个)".equals(bfsx)){
			return "fwnchzzz";
		}else 
		if("建立科技示范基地 (单位:个)".equals(bfsx)){
			return "jlkjsfjd";
		}else 
		if("培育科技示范户 (单位:户)".equals(bfsx)){
			return "pykjsfh";
		}else 
		if("举办培训班 (单位:班次)".equals(bfsx)){
			return "jbpxb";
		}else 
		if("培训人员 (单位:人次)".equals(bfsx)){
			return "pxry";
		}else 
		if("培训贫困群众 (单位:人次)".equals(bfsx)){
			return "pxpkqz";
		}else 
		if("帮扶贫困村 (单位:个)".equals(bfsx)){
			return "bfpkc";
		}else 
		if("贫困户 (单位:户)".equals(bfsx)){
			return "pkh";
		}else 
		if("带动脱贫 (单位:户)".equals(bfsx)){
			return "ddtp";
		}else 
		if("贫困人口 (单位:个)".equals(bfsx)){
			return "pkrk";
		}else 
		if("科技服务天数 (单位:天)".equals(bfsx)){
			return "kjfwts";
		}
		
		return "jtjjzc";
	}
	/**5、特派员统计图表**************************************************************/
	
	
	/**6、专家回复统计***********************************************************************/
	/**
	 * 专家回复统计
	 * @param jsonObj
	 * @return
	 */
	public AppResult findZjhftjAll(JSONObject jsonObj){
		AppResult appResult = new AppResult();
		AppPage appPage = new AppPage(jsonObj); 
	
		try {
			// 1.1、【前台】分页页码
			int pageNo = appPage.getPageNo();
			// 1.2、【前台】特派员日志对象
			QueryInfoCount queryInfoCount = (QueryInfoCount) JSONObject.toBean(appPage.getData(), QueryInfoCount.class);
			// 2、将当前用户伪装成已登录用户，并赋予所需的功能权限
			User user = appRoleService.getCurrentUser(new User(jsonObj.getString("id")));
			// 3、生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
			queryInfoCount.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
			// 4、组装分页对象
			Page<QueryInfoCount> page = new Page<QueryInfoCount>(pageNo, AppConfigure.pageSize);
			queryInfoCount.setPage(page);
			// 5、获取帮扶情况列表
			List<QueryInfoCount> list = queryInfoCountDao.getzjhfch(queryInfoCount);
			// 6、设置归属部门
			QueryInfoCount count = null;
			for (int i = 0, len = list.size(); i < len; i++) {
				count = list.get(i);
				count.setOffice(count.getUser().getOffice());
				list.set(i, count);
			}
			page.setList(list);
			// 组装返回数据
			appResult.setObj(page);
			
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	/**6、专家回复统计***********************************************************************/

	
	/**7、贫困村及特派团信息查询**********************************************************/
	/**
	 * 贫困村列表查询
	 * @param jsonObj
	 * @return
	 */
	public AppResult findVillageAll(JSONObject jsonObj){
		AppResult appResult = new AppResult();
		AppPage appPage = new AppPage(jsonObj);
		
		try {
			// 1.1、【前台】分页页码
			int pageNo = appPage.getPageNo();
			// 1.2、【前台】特派员日志对象
			Village village = (Village) JSONObject.toBean(appPage.getData(), Village.class);
			// 2、将当前用户伪装成已登录用户，并赋予所需的功能权限
			User user = appRoleService.getCurrentUser(new User(jsonObj.getString("id")));
			// 3、生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
			village.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
			// 4、组装分页对象
			Page<Village> page = new Page<Village>(pageNo, AppConfigure.pageSize);
			village.setPage(page);
			// 5、获取帮扶情况列表
			page.setList(villageDao.findVillagelist(village));
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
	 * 贫困村详情及特派团信息
	 * @param id
	 * @return
	 */
	public AppResult findVillageById(String id) {
		AppResult appResult = new AppResult();
		
		try {
			Village village = villageDao.get(id);
			if(village != null){
				List<ServiceTeam> serviceTeam = village.getServiceTeam();
				if(serviceTeam!=null){
					for(int i=0;i<serviceTeam.size();i++){
						if(serviceTeam.get(i).getDelFlag().equals("1")){
							serviceTeam.remove(i);
						}
					}
				}
			}
			// 组装返回数据
			appResult.setObj(village);
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	
	/**
	 * 特派团人员列表
	 * @param jsonObj
	 * @return
	 */
	public AppResult findServiceTeam(String id){
		AppResult appResult = new AppResult();
		
		try {
			List<TeamMember> teamMember = teamMemberDao.findTeamMember(id);
			// 组装返回数据
			appResult.setObj(teamMember);
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}

		return appResult;
	}
	/**7、贫困村及特派团信息查询**********************************************************/
	
	
	/**8、贫困村帮扶关系名单************************************************************/
	/**
	 * 贫困村帮扶关系名单
	 * @param jsonObj
	 * @return
	 */
	public AppResult findServiceTeamAll(JSONObject jsonObj){
		AppResult appResult = new AppResult();
		AppPage appPage = new AppPage(jsonObj);
		
		try {
			// 1.1、【前台】分页页码
			int pageNo = appPage.getPageNo();
			// 1.2、【前台】特派员日志对象
			ServiceTeam serviceTeam = (ServiceTeam) JSONObject.toBean(appPage.getData(), ServiceTeam.class);
			// 2、将当前用户伪装成已登录用户，并赋予所需的功能权限
			User user = appRoleService.getCurrentUser(new User(jsonObj.getString("id")));
			// 3、生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
			serviceTeam.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
			// 4、组装分页对象
			Page<ServiceTeam> page = new Page<ServiceTeam>(pageNo, AppConfigure.pageSize);
			serviceTeam.setPage(page);
			// 5、获取帮扶情况列表
			page.setList(serviceTeamDao.getServiceTeamList(serviceTeam));
			// 组装返回数据
			appResult.setObj(page);
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}

		return appResult;
	}
	/**8、贫困村帮扶关系名单************************************************************/
	
	
	/**9、服务协议信息查询******************************************************************/
	/**
	 * 服务对象协议查询
	 * @param jsonObj
	 * @return
	 */
	public AppResult findFuxyAll(JSONObject jsonObj){
		AppResult appResult = new AppResult();
		AppPage appPage = new AppPage(jsonObj);

		try {
			// 1.1、【前台】分页页码
			int pageNo = appPage.getPageNo();
			// 1.2、【前台】当前用户id
			String id = jsonObj.getString("id");
			// 1.2、【前台】工作日志对象
			Sqtpy sqtpy = (Sqtpy) JSONObject.toBean(appPage.getData(), Sqtpy.class);
			// 2、将当前用户伪装成已登录用户，并赋予所需的功能权限
			User user = appRoleService.getCurrentUser(new User(id));
			// 3、生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
			sqtpy.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
			// 4、查询分页数据
			Page<Sqtpy> page = new Page<Sqtpy>(pageNo, AppConfigure.pageSize);
			sqtpy.setPage(page);
			List<Sqtpy> list = tpyshDao.fwdxxylist(sqtpy);
			page.setList(list);
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
	 * （贫困村）团队协议
	 * @param jsonObj
	 * @return
	 */
	public AppResult findAllByTeam(JSONObject jsonObj){
		AppResult appResult = new AppResult();
		AppPage appPage = new AppPage(jsonObj);
		
		try {
			// 1.1、【前台】分页页码
			int pageNo = appPage.getPageNo();
			// 1.2、【前台】当前用户id
			String id = jsonObj.getString("id");
			// 1.3、【前台】当前从奥做对象
			TeamMember teamMember = (TeamMember) JSONObject.toBean(jsonObj.getJSONObject("data"), TeamMember.class);
			// 2、将当前用户伪装成已登录用户，并赋予所需的功能权限
			User user_ = appRoleService.getCurrentUser(new User(id));
			// 3、生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
			teamMember.getSqlMap().put("dsf", dataScopeFilter(user_.getCurrentUser(), "o", "a"));
			// 4、设置分页
			Page<TeamMember> page = new Page<TeamMember>(pageNo, AppConfigure.pageSize);
			teamMember.setPage(page);
			// 5、拉取数据库数据
			page.setList(teamMemberDao.villagelistByTeam(teamMember));
			// 返回数据
			appResult.setObj(page);
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	
	/**
	 * （贫困村）个人协议查询
	 * @param jsonObj
	 * @return
	 */
	public AppResult findAllByMember(JSONObject jsonObj){
		AppResult appResult = new AppResult();
		AppPage appPage = new AppPage(jsonObj);
		
		try {
			// 1.1、【前台】分页页码
			int pageNo = appPage.getPageNo();
			// 1.2、【前台】当前用户id
			String id = jsonObj.getString("id");
			// 1.3、【前台】当前操作对象
			TeamMember teamMember = (TeamMember) JSONObject.toBean(jsonObj.getJSONObject("data"), TeamMember.class);
			// 2、将当前用户伪装成已登录用户，并赋予所需的功能权限
			User user_ = appRoleService.getCurrentUser(new User(id));
			// 3、生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
			teamMember.getSqlMap().put("dsf", dataScopeFilter(user_.getCurrentUser(), "o", "a"));
			// 4、设置分页
			Page<TeamMember> page = new Page<TeamMember>(pageNo, AppConfigure.pageSize);
			teamMember.setPage(page);
			// 5、拉取数据数据
			page.setList(teamMemberDao.villagelistByMember(teamMember));
			// 组装返回数据
			appResult.setObj(page);
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	/**9、服务协议信息查询******************************************************************/
	
	
	/**10、派出单位派出科技特派员人数统计****************************************************/
	/**
	 * 派出单位派出科技特派员人数统计
	 * @param jsonObj
	 * @return
	 */
	public AppResult findSendTpyNum(JSONObject jsonObj){
		AppResult appResult = new AppResult();
		AppPage appPage = new AppPage(jsonObj); 
	
		try {
			// 1.1、【前台】分页页码
			int pageNo = appPage.getPageNo();
			// 1.2、【前台】User对象
			User user = (User) JSONObject.toBean(appPage.getData(), User.class);
			// 2、将当前用户伪装成已登录用户，并赋予所需的功能权限
			user = appRoleService.getCurrentUser(user);
			// 3、组装分页对象
			Page<User> page = new Page<User>(pageNo, AppConfigure.pageSize);
			// 4、获取特派员列表
			Page<User> userPage = queryinfoservice.GetTpyNum(page, user);
			// 组装返回数据
			appResult.setObj(userPage);
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	/**10、派出单位派出科技特派员人数统计****************************************************/
	
}
