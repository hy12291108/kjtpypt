/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dailywork.service.tpy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dailywork.entity.tpy.TpyAnnualreport;
import com.thinkgem.jeesite.modules.dailywork.entity.tpy.TpyDailyrecord;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.dailywork.dao.tpy.TpyAnnualreportDao;

/**
 * 特派员年度考核Service
 * @author Grace
 * @version 2017-08-10
 */
@Service
@Transactional(readOnly = true)
public class TpyAnnualreportService extends CrudService<TpyAnnualreportDao, TpyAnnualreport> {

	@Autowired
	TpyAnnualreportDao tpyAnnualreportDao;
	
	public TpyAnnualreport get(String id) {
		return super.get(id);
	}
	
	public List<TpyAnnualreport> findList(TpyAnnualreport tpyAnnualreport) {
		return super.findList(tpyAnnualreport);
	}
	public List<TpyAnnualreport> getTpyAnnualreport(TpyAnnualreport tpyAnnualreport) {
		List<TpyAnnualreport> list = tpyAnnualreportDao.getTpyAnnualreport(tpyAnnualreport);
		return list;
	}
	public TpyAnnualreport getAnnualreportInfo(String id) {
		TpyAnnualreport tpyAnnualreport = new TpyAnnualreport();
		tpyAnnualreport = tpyAnnualreportDao.getAnnualreportInfo(id);
		return tpyAnnualreport;
	}
	
	public Page<TpyAnnualreport> findPage(Page<TpyAnnualreport> page, TpyAnnualreport tpyAnnualreport) {
		return super.findPage(page, tpyAnnualreport);
	}
	
	@Transactional(readOnly = false)
	public void save(TpyAnnualreport tpyAnnualreport) {
		super.save(tpyAnnualreport);
	}
	
	@Transactional(readOnly = false)
	public void change(TpyAnnualreport tpyAnnualreport) {
		tpyAnnualreportDao.change(tpyAnnualreport);
	}
	
	@Transactional(readOnly = false)
	public void UpdateInfo(TpyAnnualreport tpyAnnualreport) {
		tpyAnnualreportDao.UpdateInfo(tpyAnnualreport);
	}
	
	@Transactional(readOnly = false)
	public void updateTpyAnnualreport(TpyAnnualreport tpyAnnualreport) {
		tpyAnnualreportDao.updateTpyAnnualreport(tpyAnnualreport);
	}
	
	@Transactional(readOnly = false)
	public void delete(TpyAnnualreport tpyAnnualreport) {
		super.delete(tpyAnnualreport);
	}
	
	@Transactional(readOnly = false)
	public Page<TpyAnnualreport> findListWithinPower(Page<TpyAnnualreport> page,TpyAnnualreport tpyAnnualreport) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		tpyAnnualreport.getSqlMap().put("dsf", dataScopeFilter(UserUtils.getUser().getCurrentUser(), "o", "a"));
		// 设置分页参数
		tpyAnnualreport.setPage(page);
		// 执行分页查询
		page.setList(tpyAnnualreportDao.findListWithinPower(tpyAnnualreport));
		return page;
	}

	public TpyAnnualreport getTpyReportByYear(String nowYear,String tpyid) {
		TpyAnnualreport t = new TpyAnnualreport();
		t = tpyAnnualreportDao.getTpyReportByYear(nowYear,tpyid);
		return t;
	}

	
}