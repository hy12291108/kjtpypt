/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dailywork.dao.tpy;

import java.util.Date;
import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dailywork.entity.tpy.TpyAnnualreport;
import com.thinkgem.jeesite.modules.dailywork.entity.tpy.TpyDailyrecord;
import com.thinkgem.jeesite.modules.sqtpy.entity.Sqtpy;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.threearea.entity.ThreeArea;
import com.thinkgem.jeesite.modules.villagemanage.entity.TeamMember;

/**
 * 特派员工作日志DAO接口
 * @author Grace
 * @version 2017-08-10
 */
@MyBatisDao
public interface TpyDailyrecordDao extends CrudDao<TpyDailyrecord> {

	List<TpyDailyrecord> findListWithinPower(TpyDailyrecord tpyDailyrecord);
	public int change(TpyDailyrecord tpyDailyrecord);
	public int savepj(TpyDailyrecord tpyDailyrecord);
	public int changetpyDailyrecord(TpyDailyrecord tpyDailyrecord);
	public String getThreeAreaYear(Date date);
	public List<TpyDailyrecord> CheckIsThreeAreaByYear(TpyDailyrecord tpyDailyrecord);
	List<Sqtpy> getFwdxList(String tpyid);
	List<Sqtpy> getVilList(String tpyid);
	
	List<TpyDailyrecord> findAllByApp(TpyDailyrecord tpyDailyrecord);
	
	List<TpyDailyrecord> findAllByRecTpyId(TpyDailyrecord tpyDailyrecord);
	
	List<TpyDailyrecord> findAllByRecTpyIdApp(TpyDailyrecord tpyDailyrecord);
	
	TpyDailyrecord getVerifyInfo(String id);
	
	List<TpyDailyrecord> xqdwckPage(TpyDailyrecord tpyDailyrecord);
	public String getTeamName(String TeamId);
	
	public String getVillageName(String villageId);
	
	public String getFwdxName(String fwdxId);
	//获得团队所在区域
	public String getTeamarea(String TeamId);
	//查询本年度是否是三区人才
	public List<ThreeArea> CheckIsThreeAreaByYear(String year,String tpyid);
	
}