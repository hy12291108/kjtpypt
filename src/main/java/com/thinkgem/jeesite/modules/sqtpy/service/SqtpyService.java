package com.thinkgem.jeesite.modules.sqtpy.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.dailywork.entity.tpy.TpyDailyrecord;
import com.thinkgem.jeesite.modules.sqtpy.dao.SqtpyDao;
import com.thinkgem.jeesite.modules.sqtpy.dao.TpyShDao;
import com.thinkgem.jeesite.modules.sqtpy.dao.TpyUserDao;
import com.thinkgem.jeesite.modules.sqtpy.entity.Sqtpy;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import freemarker.template.utility.DateUtil;

@Service
@Transactional(readOnly = true)
public class SqtpyService extends CrudService<SqtpyDao, Sqtpy> {
	@Autowired
	private SqtpyDao sqtpyDao;
	@Autowired
	private TpyUserDao tpyuserDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private TpyShDao tpyshDao;
	/**
	 * 分页查询特派员列表
	 * @param user
	 * @return
	 */
	public Page<User> findTpy(Page<User> page, User user) {
		user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
		// 设置分页参数
		user.setPage(page);
		
		// 执行分页查询
		page.setList(tpyuserDao.findList(user));
		return page;
	}
	

	/**
	 * 分页查询特派员列表
	 * @param user
	 * @return
	 */
	public Page<User> findXqdw(Page<User> page, User user) {
//		user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
//		// 设置分页参数
		user.setPage(page);
//		
		// 执行分页查询
		page.setList(tpyuserDao.getXqdwList(user));
		return page;
	}
	
	/**
	 * 保存需求单位保存特派员信息
	 * @author caolei
	 * @date 2017 08-07
	 */
	@Transactional(readOnly = false)
	public void saveXqdwSqTpy(Sqtpy sqtpy){
			sqtpy.preInsert();
			sqtpyDao.insert(sqtpy);
	}
	
	public Page<Sqtpy> xqdwsqList(Page<Sqtpy> page, Sqtpy sqtpy){	
		// 设置分页参数
		sqtpy.setPage(page);
		// 执行分页查询
		page.setList(sqtpyDao.selectsqtpylist(sqtpy));
		return page;
	}
	public Page<Sqtpy> xqdwsqListYsh(Page<Sqtpy> page, Sqtpy sqtpy){	
		// 设置分页参数
		sqtpy.setPage(page);
		// 执行分页查询
		page.setList(sqtpyDao.selectsqtpylistYsh(sqtpy));
		return page;
	}
	public Page<Sqtpy> xqdwsqListysh(Page<Sqtpy> page, Sqtpy sqtpy,String xqdwname){
		// 设置分页参数
		sqtpy.setPage(page);
		// 执行分页查询
		page.setList(sqtpyDao.selectsqtpylist1(xqdwname));
		return page;
	}
	/**
	 * 获得特派员信息
	 */
	public User getTpy(User user){	
		return tpyuserDao.get(user);
	}
	/**
	 * 获得审核信息
	 */
	public Sqtpy getShTpy(Sqtpy sqtpy){
		sqtpy = tpyshDao.get(sqtpy);
		if(sqtpy.getIsmajor().equals("1")){
			
		}else{
			User user = userDao.get(sqtpy.getTpyid());
			user.setCorpType(DictUtils.getDictLabel(user.getCorpType(), "corp_type", user.getCorpType()));
			sqtpy.setTpyinfo(user);
		}	
		if(sqtpy.getState().equals("1")){
			sqtpy.setState("审核中");
			System.out.println(sqtpy.getState()+"88888888888888888888888");
		}
		else if(sqtpy.getState().equals("2"))
		{
			sqtpy.setState("审核已通过，进行中");
		}
		else if(sqtpy.getState().equals("3"))
		{
			sqtpy.setState("审核未通过");
		}
		else if(sqtpy.getState().equals("4"))
		{
			sqtpy.setState("已结束");
		}
		sqtpy.setZpr(UserUtils.getUser().getName());	
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		sqtpy.setZpTime(sdf.format(new Date()));
		System.out.println("******************"+sqtpy.getTechspecial()+sqtpy.getZpTime());
		return sqtpy;
	}
	public Sqtpy getCkTpy(Sqtpy sqtpy){
		sqtpy = tpyshDao.get(sqtpy);
		sqtpy.setTpyinfo(userDao.get(sqtpy.getTpyid()));
		if(sqtpy.getState().equals("1")){
			sqtpy.setState("审核中");
		}
		else if(sqtpy.getState().equals("2"))
		{
			sqtpy.setState("已通过");
		}
		else if(sqtpy.getState().equals("3"))
		{
			sqtpy.setState("未通过");
		}
		else if(sqtpy.getState().equals("4"))
		{
			sqtpy.setState("已结束");
		}
		return sqtpy;
	}
	public Sqtpy getTpyInfo(Sqtpy sqtpy){
		sqtpy = tpyshDao.get(sqtpy);
		sqtpy.setTpyinfo(userDao.get(sqtpy.getTpyid()));
		return sqtpy;
	}
	/**
	 * 获得当前Office下的 专业列表
	 * @param officeid
	 * @return
	 */
	public List<String> majorlist(String officeid){
		List<String> list = tpyuserDao.getmajorlist(officeid);
		return list;	
	}
	/**
	 * 获得当前区域某专业的特派员
	 * @param officeid
	 * @return
	 */
	public List<User> TpyListbyMajor(String officeid,String major){
		List<User> list = tpyuserDao.getTpyListbyMajor(officeid, major);
		return list;	
	}
	
	/**
	 * 修改特派员审核状态
	 */
	@Transactional(readOnly = false)
	public void updateTpySqInfo(Sqtpy sqtpy){	
		sqtpyDao.updateTpySqInfo(sqtpy);
	}
	/**
	 * 修改特派员审核信息
	 */
	@Transactional(readOnly = false)
	public void updateTpySqInfo1(Sqtpy sqtpy){
		sqtpy.setState("2");
		if(sqtpy.getPersonFlag().equals("0")){
			sqtpyDao.updateTpySqInfo2(sqtpy);
		}else{
			sqtpyDao.updateTpySqInfo3(sqtpy);
		}
		
	}
	/**
	 * 修改特派员审核信息
	 */
	@Transactional(readOnly = false)
	public void updateTpySqInfo2(Sqtpy sqtpy){
		sqtpy.setState("3");
		sqtpyDao.updateTpySqInfo2(sqtpy);
	}
	
	@Transactional(readOnly = false)
	public void changeTpy(Sqtpy sqtpy){
		sqtpyDao.changeTpy(sqtpy);
	}
	
	
	/**
	 * 删除对特派员的申请
	 */
	@Transactional(readOnly = false)
	public void deleteTpySqInfo(String id){
		sqtpyDao.deleteTpySqInfo(id);
	}
	
	public Page<Sqtpy> findUser(Page<Sqtpy> page, User user,Sqtpy sqtpy) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
		// 设置分页参数
		sqtpy.setPage(page);
		// 执行分页查询
		page.setList(tpyshDao.findShList(user));
		return page;
	}
	public Page<Sqtpy> findDyUser(Page<Sqtpy> page,Sqtpy sqtpy) {
		
		// 设置分页参数
		sqtpy.setPage(page);
		// 执行分页查询
		page.setList(tpyshDao.findDyShList(sqtpy));
		return page;
	}
	public Page<Sqtpy> findDyUser1(Page<Sqtpy> page,Sqtpy sqtpy) {
		// 设置分页参数
		sqtpy.setPage(page);
		// 执行分页查询
		page.setList(tpyshDao.findDyShList1(sqtpy));
		return page;
	}
	public Page<Sqtpy> findUser1(Page<Sqtpy> page, Sqtpy sqtpy) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		sqtpy.getSqlMap().put("dsf", dataScopeFilter(UserUtils.getUser().getCurrentUser(), "o", "a"));
		// 设置分页参数
		sqtpy.setPage(page);
		// 执行分页查询
		page.setList(tpyshDao.findShList1(sqtpy));
		return page;
	}
	
	
	public Sqtpy selectTpy1(String tpyid){
		System.out.println(tpyid);
		User user = tpyuserDao.getTpybyId(tpyid);
		Sqtpy sqtpy = new Sqtpy();
		sqtpy.setTpyid(user.getId());
		sqtpy.setTpyname(user.getName());
		sqtpy.setZc(user.getTpyTitle());
		sqtpy.setZy(user.getTpyMajor());
		sqtpy.setTechspecial(user.getTpySpecial());
		sqtpy.setCompany(user.getTpyCompany());
		sqtpy.setMobile(user.getMobile());
		sqtpy.setOffice(user.getOffice());
		System.out.println(sqtpy.getOffice().getName());
		return sqtpy;
	}
	/**
	 * 待上传服务对象服务协议列表(特派员)
	 * @param page
	 * @param sqtpy
	 * @return
	 */
	public Page<Sqtpy> findDscxy(Page<Sqtpy> page, Sqtpy sqtpy) {
		sqtpy.setTpyid(UserUtils.getUser().getId());
		// 设置分页参数
		sqtpy.setPage(page);
		// 执行分页查询
		page.setList(tpyshDao.findDscxy(sqtpy));
		return page;
	}

	public Page<Sqtpy> fwxyshlist(Page<Sqtpy> page, Sqtpy sqtpy) {
		sqtpy.setTpyid(UserUtils.getUser().getId());
		sqtpy.setOffice(UserUtils.getUser().getOffice());
		// 设置分页参数
		sqtpy.setPage(page);
		// 执行分页查询
		page.setList(tpyshDao.fwxyshlist(sqtpy));
		return page;
	}
	public Page<Sqtpy> fwxyyshlist(Page<Sqtpy> page, Sqtpy sqtpy) {
		sqtpy.setTpyid(UserUtils.getUser().getId());
		sqtpy.setOffice(UserUtils.getUser().getOffice());
		// 设置分页参数
		sqtpy.setPage(page);
		// 执行分页查询
		page.setList(tpyshDao.fwxyyshlist(sqtpy));
		return page;
	}
	/**
	 * 待上传贫困村服务协议列表（需求单位）
	 * @param page
	 * @param sqtpy
	 * @return
	 */
	public Page<Sqtpy> findDscxybyxqdw(Page<Sqtpy> page, Sqtpy sqtpy) {
		sqtpy.setXqdwid(UserUtils.getUser().getId());
		// 设置分页参数
		sqtpy.setPage(page);
		// 执行分页查询
		page.setList(tpyshDao.findDscxybyxqdw(sqtpy));
		return page;
	}
	/**
	 * 待上传贫困村服务协议列表(特派员)
	 * @param page
	 * @param sqtpy
	 * @return
	 */
	public Page<Sqtpy> findYscxy(Page<Sqtpy> page, Sqtpy sqtpy) {
		sqtpy.setTpyid(UserUtils.getUser().getId());
		// 设置分页参数
		sqtpy.setPage(page);
		// 执行分页查询
		page.setList(tpyshDao.findYscxy(sqtpy));
		return page;
	}
	
	/**
	 * 待上传贫困村服务协议列表（需求单位）
	 * @param page
	 * @param sqtpy
	 * @return
	 */
	public Page<Sqtpy> findYscxybyxqdw(Page<Sqtpy> page, Sqtpy sqtpy) {
		sqtpy.setXqdwid(UserUtils.getUser().getId());
		// 设置分页参数
		sqtpy.setPage(page);
		// 执行分页查询
		page.setList(tpyshDao.findYscxybyxqdw(sqtpy));
		return page;
	}
	//
	public Page<Sqtpy> fwdxxylist(Page<Sqtpy> page, Sqtpy sqtpy) {
		sqtpy.setTpyid(UserUtils.getUser().getId());
		// 设置分页参数
		sqtpy.getSqlMap().put("dsf", dataScopeFilter(UserUtils.getUser().getCurrentUser(), "o", "a"));
		sqtpy.setPage(page);
		// 执行分页查询
		page.setList(tpyshDao.fwdxxylist(sqtpy));
		return page;
	}
	
	/**
	 * 获得需求单位信息
	 * 2018-07-31
	 * by gz
	 */
	public User getXqdw(User user){	
		return tpyuserDao.get(user);
	}
	
	/**
	 * 保存特派员申请需求单位的信息
	 * @author gz
	 * @date 2018-07-31
	 */
//	@Transactional(readOnly = false)
//	public void saveTpySqXqdw(Sqtpy user){
//		    user.preInsert();
//			sqtpyDao.insert(user);
//	}
	
	public List<Sqtpy> selectbfgx(String tpyid,String xqdwid,String nowDate){
		
		List<Sqtpy> list = sqtpyDao.selectbfgx(tpyid,xqdwid,nowDate);

		return list;
	}
}
