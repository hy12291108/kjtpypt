/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dailywork.service.tpy;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dailywork.entity.tpy.TpyAnnualreport;
import com.thinkgem.jeesite.modules.dailywork.entity.tpy.TpyDailyrecord;
import com.thinkgem.jeesite.modules.sqtpy.entity.Sqtpy;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.threearea.entity.ThreeArea;
import com.thinkgem.jeesite.modules.villagemanage.entity.TeamMember;
import com.thinkgem.jeesite.modules.dailywork.dao.tpy.TpyDailyrecordDao;

/**
 * 特派员工作日志Service
 * @author Grace
 * @version 2017-08-10
 */
@Service
@Transactional(readOnly = true)
public class TpyDailyrecordService extends CrudService<TpyDailyrecordDao, TpyDailyrecord> {

	@Autowired
	TpyDailyrecordDao tpyDailyrecordDao;
	
	public TpyDailyrecord get(String id) {
		return super.get(id);
	}
	
	public List<TpyDailyrecord> findList(TpyDailyrecord tpyDailyrecord) {
		return super.findList(tpyDailyrecord);
	}
	
	public TpyDailyrecord getVerifyInfo(String id) {
		TpyDailyrecord tpyDailyrecord1 = new TpyDailyrecord();
		tpyDailyrecord1 = tpyDailyrecordDao.getVerifyInfo(id);
		return tpyDailyrecord1;
	}
	
	public List<Sqtpy> getFwdxList(String tpyid) {
		List <Sqtpy> list = tpyDailyrecordDao.getFwdxList(tpyid);
		return list;
	}
	
	public List<Sqtpy> getVilList(String tpyid) {
		List <Sqtpy> list = tpyDailyrecordDao.getVilList(tpyid);
		return list;
	}
	
	public Page<TpyDailyrecord> findPage(Page<TpyDailyrecord> page, TpyDailyrecord tpyDailyrecord) {
		return super.findPage(page, tpyDailyrecord);
	}
	public Page<TpyDailyrecord> xqdwckPage(Page<TpyDailyrecord> page, TpyDailyrecord tpyDailyrecord) {
		//tpyDailyrecord.getSqlMap().put("dsf", dataScopeFilter(tpyDailyrecord.getCurrentUser(), "o", "a"));
		tpyDailyrecord.setOffice(UserUtils.getUser().getOffice());
		tpyDailyrecord.setPage(page);
		page.setList(tpyDailyrecordDao.xqdwckPage(tpyDailyrecord));
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(TpyDailyrecord tpyDailyrecord) {
		super.save(tpyDailyrecord);
	}
	
	@Transactional(readOnly = false)
	public void change(TpyDailyrecord tpyDailyrecord) {
		tpyDailyrecordDao.change(tpyDailyrecord);
	}
	@Transactional(readOnly = false)
	public void savepj(TpyDailyrecord tpyDailyrecord) {
		tpyDailyrecordDao.savepj(tpyDailyrecord);
	}
	
	@Transactional(readOnly = false)
	public void changetpyDailyrecord(TpyDailyrecord tpyDailyrecord) {
		tpyDailyrecordDao.changetpyDailyrecord(tpyDailyrecord);
	}
	@Transactional(readOnly = false)
	public void delete(TpyDailyrecord tpyDailyrecord) {
		super.delete(tpyDailyrecord);
	}
	
	@Transactional(readOnly = false)
	public Page<TpyDailyrecord> findListWithinPower(Page<TpyDailyrecord> page, TpyDailyrecord tpyDailyrecord) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		tpyDailyrecord.getSqlMap().put("dsf", dataScopeFilter(UserUtils.getUser().getCurrentUser(), "o", "a"));
		// 设置分页参数
		tpyDailyrecord.setPage(page);
		// 执行分页查询
		page.setList(tpyDailyrecordDao.findListWithinPower(tpyDailyrecord));
		return page;
	}
	
	public List<TpyDailyrecord> CheckIsThreeAreaByYear(TpyDailyrecord tpyDailyrecord) {
		List <TpyDailyrecord> list = tpyDailyrecordDao.CheckIsThreeAreaByYear(tpyDailyrecord);
		return list;
	}
	public List<ThreeArea> CheckIsThreeAreaByYear(String year,String tpyid) {
		List <ThreeArea> list = tpyDailyrecordDao.CheckIsThreeAreaByYear(year, tpyid);
		return list;
	}
	/**
	 * 获取特派团名称
	 * @param TeamId
	 * @return
	 */
	public String getTeamName(String TeamId){
		String TeamName = tpyDailyrecordDao.getTeamName(TeamId);
		return TeamName;
	}
	/**
	 * 获取贫困村名称
	 * @param TeamId
	 * @return
	 */
	public String getVillageName(String villageId){
		String villageName = tpyDailyrecordDao.getVillageName(villageId);
		return villageName;
	}
	
	/**
	 * 获取服务对象名称
	 * @param TeamId
	 * @return
	 */
	public String getFwdxName(String fwdxId){
		String villageName = tpyDailyrecordDao.getFwdxName(fwdxId);
		return villageName;
	}
	/**
	 * 获取特派团所在区域
	 * @param TeamId
	 * @return
	 */
	public String getTeamarea(String TeamId){
		String Teamarea = tpyDailyrecordDao.getTeamarea(TeamId);
		return Teamarea;
	}
	
}