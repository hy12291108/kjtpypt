/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dailywork.dao.tpy;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.dailywork.entity.tpy.TpyAnnualreport;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 特派员年度考核DAO接口
 * @author Grace
 * @version 2017-08-10
 */
@MyBatisDao
public interface TpyAnnualreportDao extends CrudDao<TpyAnnualreport> {

	List<TpyAnnualreport> findListWithinPower(TpyAnnualreport tpyAnnualreport);
	List<TpyAnnualreport> getTpyAnnualreport(TpyAnnualreport tpyAnnualreport);
	public TpyAnnualreport getTpyReportByYear(String nowYear,String tpyid); 
	
	public TpyAnnualreport getAnnualreportInfo(String id);
	public int change(TpyAnnualreport tpyAnnualreport);
	public int updateTpyAnnualreport(TpyAnnualreport tpyAnnualreport);
	public int UpdateInfo(TpyAnnualreport tpyAnnualreport);
}