package com.thinkgem.jeesite.modules.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.kjtpypt.entity.SysAttachment;
import com.thinkgem.jeesite.modules.app.bean.AppConfigure;
import com.thinkgem.jeesite.modules.app.bean.AppPage;
import com.thinkgem.jeesite.modules.app.bean.AppResult;
import com.thinkgem.jeesite.modules.app.bean.UtilDate;
import com.thinkgem.jeesite.modules.dailywork.service.village.VilProtocolService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.villagemanage.dao.TeamMemberDao;
import com.thinkgem.jeesite.modules.villagemanage.entity.TeamMember;

import net.sf.json.JSONObject;

/**
 * APP 贫困村服务协议
 *
 * 赵凯浩
 * 2017年10月13日 下午3:18:23
 */
@Service
@Transactional(readOnly = true)
public class AppVillageService extends BaseService{
	
	@Autowired
	private TeamMemberDao teamMemberDao;
	@Autowired
	private SystemService systemService;
	@Autowired
	private VilProtocolService vilProtocolService;
	
	
	/**
	 * 查询所有团队协议
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
//			User user_ = appRoleService.getCurrentUser(new User(id));
			// 3、生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
//			teamMember.getSqlMap().put("dsf", dataScopeFilter(user_.getCurrentUser(), "o", "a"));
			// 4、设置分页
			Page<TeamMember> page = new Page<TeamMember>(pageNo, AppConfigure.pageSize);
			teamMember.setPage(page);
			// 5、所在机构
			User userOffice = systemService.getUser(id);
			teamMember.setZoneId(userOffice.getOffice().getId());
			// 6、拉取数据库数据
			page.setList(teamMemberDao.vilfwxyDshlistByTeamByApp(teamMember));
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
	 * 查询所有团员协议
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
//			// 2、将当前用户伪装成已登录用户，并赋予所需的功能权限
//			User user_ = appRoleService.getCurrentUser(new User(id));
//			// 3、生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
//			teamMember.getSqlMap().put("dsf", dataScopeFilter(user_.getCurrentUser(), "o", "a"));
			// 4、设置分页
			Page<TeamMember> page = new Page<TeamMember>(pageNo, AppConfigure.pageSize);
			teamMember.setPage(page);
			// 5、所在机构
			User userOffice = systemService.getUser(id);
			teamMember.setZoneId(userOffice.getOffice().getId());
			// 6、拉取数据数据
			page.setList(teamMemberDao.vilfwxyYshlistByMemberByApp(teamMember));
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
	 * 通过id查询对象
	 * @param jsonObj
	 * @return
	 */
	public AppResult getMemberById(String id, String titleName){
		AppResult appResult = new AppResult();
		
		try {
			// 1、创建协议对象
			TeamMember teamMember = new TeamMember();
			teamMember.setId(id);
			// 2、通过协议id从数据库拉取完整协议对象
			teamMember = teamMemberDao.getMemberInfo(teamMember);
			// 3、得到协议中的指定特派员
			User user = systemService.getUser(teamMember.getUserId());
			// 4、来去数据库协议附件列表
			List<SysAttachment> list = null;
			if("团队协议".equals(titleName)){
				list = vilProtocolService.findAttatchments(teamMember.getTeamprotocol());
			}else{
				list = vilProtocolService.findAttatchments(teamMember.getMemberprotocol());
			}
			// 组装返回数据
			appResult.setObj(teamMember);
			appResult.setSubObj(user.getOffice().getId());
			appResult.setThirdObj(list);
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	
	/**
	 * 审核
	 * @param jsonObj
	 * @return
	 */
	@Transactional(readOnly = false)
	public AppResult checkVillage(JSONObject jsonObj){
		AppResult appResult = new AppResult();
		
		try {
			// 1.1、【前台】当前用户id
			String id = jsonObj.getString("id");
			// 1.2、【前台】协议类型（服务对像、团队协议、个人协议）
			String titleName = jsonObj.getString("titleName");
			// 1.3、【前台】协议对象
			TeamMember teamMember = (TeamMember) JSONObject.toBean(jsonObj.getJSONObject("data"), TeamMember.class);
			// 2、当前用户
			User user = systemService.getUser(id);
			// 3、缓存审核结果
			String checkOpinion = "";
			String teamprotocolFlag = "";
			String checkOpinion1 = "";
			String memberprotocolFlag = "";
			if("团队协议".equals(titleName)){
				checkOpinion = teamMember.getCheckOpinion();
				teamprotocolFlag = teamMember.getTeamprotocolFlag();
			}else{
				checkOpinion1 = teamMember.getCheckOpinion1();
				memberprotocolFlag = teamMember.getMemberprotocolFlag();
			}
			// 4、获得完成对象
			teamMember = teamMemberDao.getMemberInfo(teamMember);
			// 5、协议附件
			teamMember.setSysAttachmentList(vilProtocolService.findAttatchments(teamMember.getTeamprotocol()));
			// 6、审核参数
			if("团队协议".equals(titleName)){
				teamMember.setCheckOpinion(checkOpinion);
				teamMember.setTeamprotocolFlag(teamprotocolFlag);
				teamMember.setCheckPerson(user.getName());
				teamMember.setCheckTime(UtilDate.getSimple(new Date()));
				teamMemberDao.changeTeam(teamMember);
			}else{
				teamMember.setCheckOpinion1(checkOpinion1);
				teamMember.setMemberprotocolFlag(memberprotocolFlag);
				teamMember.setCheckPerson1(user.getName());
				teamMember.setCheckTime1(UtilDate.getSimple(new Date()));
				teamMemberDao.changeMember(teamMember);
			}
			// 7、保存
			teamMemberDao.changeMember(teamMember);
			// 组装返回数据
			if("2".equals(teamprotocolFlag) || "2".equals(memberprotocolFlag)){				
				appResult.setMsg("已通过");
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
	
}
