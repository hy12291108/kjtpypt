package com.thinkgem.jeesite.modules.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.kjtpypt.entity.SysAttachment;
import com.thinkgem.jeesite.modules.app.bean.AppConfigure;
import com.thinkgem.jeesite.modules.app.bean.AppPage;
import com.thinkgem.jeesite.modules.app.bean.AppResult;
import com.thinkgem.jeesite.modules.app.bean.UtilDate;
import com.thinkgem.jeesite.modules.dailywork.dao.village.VilProtocolDao;
import com.thinkgem.jeesite.modules.dailywork.entity.village.VilProtocol;
import com.thinkgem.jeesite.modules.dailywork.service.village.VilProtocolService;
import com.thinkgem.jeesite.modules.sqtpy.dao.SqtpyDao;
import com.thinkgem.jeesite.modules.sqtpy.dao.TpyShDao;
import com.thinkgem.jeesite.modules.sqtpy.entity.Sqtpy;
import com.thinkgem.jeesite.modules.sqtpy.service.SqtpyService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

import net.sf.json.JSONObject;

/**
 * APP 服务协议
 *
 * 赵凯浩
 * 2017年9月20日 上午9:43:59
 */
@Service
@Transactional(readOnly = true)
public class AgreementService extends BaseService{

	@Autowired
	private SqtpyDao sqtpyDao;
	@Autowired
	private SqtpyService sqtpyService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private TpyShDao tpyShDao;
	@Autowired
	private AppRoleService appRoleService;
	@Autowired
	private VilProtocolDao vilProtocolDao;
	@Autowired
	private VilProtocolService vilProtocolService;
	
	
	/**
	 * 查询所有协议
	 * 权限内的所有服务协议
	 * @param jsonObj
	 * @return
	 */
	public AppResult findAll(JSONObject jsonObj){
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
			// 5、所在机构
			User userOffice = systemService.getUser(user.getId());
			sqtpy.setOffice(userOffice.getOffice());
			List<Sqtpy> list = tpyShDao.findAllFwxyFromApp(sqtpy);
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
	 * 服务协议审核
	 * @param sqtpy
	 * @param state
	 * @return
	 */
	@Transactional(readOnly = false)
	public AppResult check(@RequestBody JSONObject jsonObj){
		AppResult appResult = new AppResult();
		
		try {
			// 1.1、【前台】当前用户id
			String id = jsonObj.getString("id");
			// 1.2、【前台】协议对象
			VilProtocol vilProtocol = (VilProtocol) JSONObject.toBean(jsonObj.getJSONObject("data"), VilProtocol.class);
			// 2、缓存前台传来的fwxystateflag，避免被第二步的user覆盖
			String fwxystateflag = vilProtocol.getFwxystateflag();
			String fwxyopinion = vilProtocol.getFwxyopinion();
			// 3、执行审核操作
			vilProtocol.setFwxystateflag(fwxystateflag);
			vilProtocol.setFwxyopinion(fwxyopinion);
			// 4、调用审核方法
			check(vilProtocol, id);
			// 组装返回数据
			if("1".equals(fwxystateflag)){				
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
	// 原始审核方法
	private boolean check(VilProtocol vilProtocol, String id) throws Exception {
		Sqtpy sqtpy = new Sqtpy();
		User user = systemService.getUser(id);
		
		sqtpy.setId(vilProtocol.getDdId());
		sqtpy.setFwxystateflag(vilProtocol.getFwxystateflag());
		sqtpy.setFwxyopinion(vilProtocol.getFwxyopinion());
		sqtpy.setFwxyzpr(user.getName());
		sqtpy.setFwxyzpTime(UtilDate.getSimple(new Date()));
		
		return vilProtocolDao.savefwxy2(sqtpy)>0?true:false;
	}
	
	/**
	 * 通过id查询服务协议记录
	 * @param id
	 * @return
	 */
	public AppResult findVilProtocolById(String id) {
		AppResult appResult = new AppResult();
		
		try {
			// 1、通过id从数据库查询完整User对象
			Sqtpy sqtpy = sqtpyDao.get(id);
			// 2、获取服务协议对象
			VilProtocol vilProtocol = sqtpy.getFwxystateflag().equals("3")
					?getVilProtocolByFwxystateflag_3(sqtpy):sqtpy.getFwxystateflag().equals("0")
					?getVilProtocolByFwxystateflag_0(sqtpy):getVilProtocolByFwxystateflagFromLook(sqtpy);
	
			// 3、获取特派员
			User user = systemService.getUser(sqtpy.getTpyid());
			List<SysAttachment> list = vilProtocolService.findAttatchments(sqtpy.getFwxy());
			// 组装返回数据
			appResult.setObj(vilProtocol);
			appResult.setSubObj(user.getOffice() == null ? null : user.getOffice().getId());
			appResult.setThirdObj(list);
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	// 原始方法 初次审核
	private VilProtocol getVilProtocolByFwxystateflag_0(Sqtpy sqtpy) throws Exception{
		
		VilProtocol vilProtocol = new VilProtocol();
		String vilProtocolId=sqtpy.getFwxy();
		vilProtocol.setSysAttachmentList(vilProtocolService.findAttatchments(vilProtocolId));
		List<Sqtpy> sqtpylist = sqtpyService.findList(sqtpy);
		sqtpy=sqtpylist.get(0);
		vilProtocol.setVilName(sqtpy.getXqdwname());
		vilProtocol.setVilId(sqtpy.getXqdwid());
		vilProtocol.setVilTpyname(sqtpy.getTpyname());
		vilProtocol.setVilTpyid(sqtpy.getTpyid());
		vilProtocol.setDdId(sqtpy.getId());
		vilProtocol.setFwxystateflag(sqtpy.getFwxystateflag());	
		//联系人，指派人
		vilProtocol.setVilContact(sqtpy.getZpr());
		//村需求，需求单位申请原因
		vilProtocol.setVilNeeds(sqtpy.getXqdwsqReason());
		vilProtocol.setHelpRelationid(sqtpy.getId());
		vilProtocol.setVilContactphone(sqtpy.getXqdwphone());
		vilProtocol.setVilTpyphone(sqtpy.getMobile());
		vilProtocol.setVilContact(sqtpy.getCorpcorName());
		vilProtocol.setFwxyzpr(sqtpy.getFwxyzpr());
		vilProtocol.setFwxyzpTime(sqtpy.getFwxyzpTime());
		
		return vilProtocol;
	}
	// 原始方法 重新审核
	private VilProtocol getVilProtocolByFwxystateflag_3(Sqtpy sqtpy) throws Exception {
		
		VilProtocol vilProtocol = new VilProtocol();
		
		String vilProtocolId=sqtpy.getFwxy();
		String vilProtocolId2 =sqtpy.getFwxyorg();	
		vilProtocol.setSysAttachmentList(vilProtocolService.findAttatchments(vilProtocolId));
		vilProtocol.setSysAttachmentList2(vilProtocolService.findAttatchments(vilProtocolId2));
		List<Sqtpy> sqtpylist = sqtpyService.findList(sqtpy);
		sqtpy=sqtpylist.get(0);
		vilProtocol.setVilName(sqtpy.getXqdwname());
		vilProtocol.setVilId(sqtpy.getXqdwid());
		vilProtocol.setVilTpyname(sqtpy.getTpyname());
		if(sqtpy.getFwxystateflag().equals("3")){
			vilProtocol.setFwxystateflag("0");
		}
		else
		{
			vilProtocol.setFwxystateflag(sqtpy.getFwxystateflag());
		}
		if(sqtpy.getFwxystateflag1().equals("3")){
			vilProtocol.setFwxystateflag1("0");
		}
		else
		{
			vilProtocol.setFwxystateflag1(sqtpy.getFwxystateflag1());
		}
		vilProtocol.setFwxyopinion(sqtpy.getFwxyopinion());
		vilProtocol.setFwxyopinion1(sqtpy.getFwxyopinion1());
		vilProtocol.setVilTpyid(sqtpy.getTpyid());
		vilProtocol.setDdId(sqtpy.getId());
		//联系人，指派人
		vilProtocol.setVilContact(sqtpy.getZpr());
		//村需求，需求单位申请原因
		vilProtocol.setVilNeeds(sqtpy.getXqdwsqReason());
		vilProtocol.setHelpRelationid(sqtpy.getId());
		vilProtocol.setVilContactphone(sqtpy.getXqdwphone());
		vilProtocol.setVilTpyphone(sqtpy.getMobile());
		vilProtocol.setVilContact(sqtpy.getCorpcorName());
		vilProtocol.setFwxyzpr(sqtpy.getFwxyzpr());
		vilProtocol.setFwxyzpTime(sqtpy.getFwxyzpTime());
		
		return vilProtocol;
	}
	// 原始方法 查看
	private VilProtocol getVilProtocolByFwxystateflagFromLook(Sqtpy sqtpy) throws Exception {
		
		VilProtocol vilProtocol = new VilProtocol();
		
		String vilProtocolId=sqtpy.getFwxy();
		vilProtocol.setSysAttachmentList(vilProtocolService.findAttatchments(vilProtocolId));
		List<Sqtpy> sqtpylist = sqtpyService.findList(sqtpy);
		sqtpy=sqtpylist.get(0);
		vilProtocol.setVilName(sqtpy.getXqdwname());
		vilProtocol.setVilId(sqtpy.getXqdwid());
		vilProtocol.setVilTpyname(sqtpy.getTpyname());
		vilProtocol.setVilContactphone(sqtpy.getXqdwphone());
		vilProtocol.setVilTpyphone(sqtpy.getMobile());
		vilProtocol.setVilContact(sqtpy.getCorpcorName());
		vilProtocol.setFwxystateflag(sqtpy.getFwxystateflag());	
		vilProtocol.setFwxyopinion(sqtpy.getFwxyopinion());
		//vilProtocol.setFwxyopinion1(sqtpy.getFwxyopinion1());
		vilProtocol.setVilTpyid(sqtpy.getTpyid());
		vilProtocol.setDdId(sqtpy.getId());
		//联系人，指派人
		vilProtocol.setVilContact(sqtpy.getZpr());
		//村需求，需求单位申请原因
		vilProtocol.setVilNeeds(sqtpy.getXqdwsqReason());
		vilProtocol.setHelpRelationid(sqtpy.getId());
		vilProtocol.setVilContactphone(sqtpy.getXqdwphone());
		vilProtocol.setVilTpyphone(sqtpy.getMobile());
		vilProtocol.setVilContact(sqtpy.getCorpcorName());
		vilProtocol.setFwxyzpr(sqtpy.getFwxyzpr());
		vilProtocol.setFwxyzpTime(sqtpy.getFwxyzpTime());
		
		return vilProtocol;
	}

}
