package com.thinkgem.jeesite.modules.queryinfo.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.queryinfo.dao.QueryInfoCountDao;
import com.thinkgem.jeesite.modules.queryinfo.dao.QueryInfoDao;
import com.thinkgem.jeesite.modules.queryinfo.entity.QueryInfoCount;
import com.thinkgem.jeesite.modules.sqtpy.dao.SqtpyDao;
import com.thinkgem.jeesite.modules.sqtpy.dao.TpyShDao;
import com.thinkgem.jeesite.modules.sqtpy.dao.TpyUserDao;
import com.thinkgem.jeesite.modules.sqtpy.entity.Sqtpy;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import freemarker.template.utility.DateUtil;

@Service
@Transactional(readOnly = true)
public class QueryInfoService extends CrudService<SqtpyDao, Sqtpy> {
	@Autowired
	private QueryInfoDao queryinfoDao;
	@Autowired
	private QueryInfoCountDao queryInfoCountDao;
	@Autowired
	private TpyShDao tpyshDao;
	public Page<User> findTpyInfoList(Page<User> page, User user) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
		// 设置分页参数
		user.setPage(page);
		// 执行分页查询
		page.setList(queryinfoDao.QueryTpyInfoList(user));
		return page;
	}
	
	public Page<User> findXqdwInfoList(Page<User> page, User user) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
		// 设置分页参数
		user.setPage(page);
		// 执行分页查询
		page.setList(queryinfoDao.QueryXqdwInfoList(user));
		return page;
	}
	
	/**
	 * 获得需求单位信息
	 */
	public User getUser(String id){
		User user = queryinfoDao.get(id);
		return user;
	}
	/**
	 * 获得需求服务信息
	 */
	public Sqtpy getXqInfo(String id){
		Sqtpy sqtpy = tpyshDao.get(id);
		if(sqtpy == null){
			System.out.println("kong");
		}else{
			System.out.println("yes");
		}
		return sqtpy;
	}
	/**
	 * 获得特派员工作单位的特派员数量
	 * @param page
	 * @param user
	 * @return
	 */
	public Page<User> GetTpyNum(Page<User> page, User user) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
		// 设置分页参数
		user.setPage(page);
		// 执行分页查询
		page.setList(queryinfoDao.getTpyNum(user));
		return page;
	}
	
	public QueryInfoCount getQureyInfoCount(String year){
		User user = UserUtils.getUser();
		QueryInfoCount queryInfoCount1 = new QueryInfoCount();
		queryInfoCount1.setYear(year);
		queryInfoCount1.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
		QueryInfoCount queryInfoCount = queryInfoCountDao.getQureyInfoCount(queryInfoCount1);
		return queryInfoCount;
	}
	//获得对应帮扶属性数量
	public QueryInfoCount getDyBfsxNum(QueryInfoCount queryInfoCount){
		if(queryInfoCount == null){
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy");
		    String year = sdf1.format(new Date());
			queryInfoCount.setYear(year+"年");			
			queryInfoCount.setBfsx("jtjjzc");
		}	
		queryInfoCount.getSqlMap().put("dsf", dataScopeFilter(queryInfoCount.getUser().getCurrentUser(), "o", "a"));
		//获得有效对象
		QueryInfoCount queryInfoCount1 = new QueryInfoCount();
		//获得无效对象
		QueryInfoCount queryInfoCount2 = new QueryInfoCount();
		queryInfoCount2.setJanuary(0);
		queryInfoCount2.setFebruary(0);
		queryInfoCount2.setMarch(0);
		queryInfoCount2.setApril(0);
		queryInfoCount2.setMay(0);
		queryInfoCount2.setJune(0);
		queryInfoCount2.setJuly(0);
		queryInfoCount2.setAugust(0);
		queryInfoCount2.setSeptember(0);
		queryInfoCount2.setOctober(0);
		queryInfoCount2.setNovember(0);
		queryInfoCount2.setDecember(0);
		queryInfoCount2.setTotal(0);
		queryInfoCount2.setJanuary1(0);
		queryInfoCount2.setFebruary1(0);
		queryInfoCount2.setMarch1(0);
		queryInfoCount2.setApril1(0);
		queryInfoCount2.setMay1(0);
		queryInfoCount2.setJune1(0);
		queryInfoCount2.setJuly1(0);
		queryInfoCount2.setAugust1(0);
		queryInfoCount2.setSeptember1(0);
		queryInfoCount2.setOctober1(0);
		queryInfoCount2.setNovember1(0);
		queryInfoCount2.setDecember1(0);
		queryInfoCount2.setTotal1(0);
		//培训农民
		if(queryInfoCount.getBfsx().equals("pxnm")){
			queryInfoCount1 = queryInfoCountDao.getPxnmNum(queryInfoCount);
			if(queryInfoCount1 != null){
				return queryInfoCount1;
			}else{
				return queryInfoCount2;
			}
			
		}
		//推广新技术
		else if(queryInfoCount.getBfsx().equals("tgxjs")){
			queryInfoCount1 = queryInfoCountDao.getTgxjsNum(queryInfoCount);
			if(queryInfoCount1 != null){
				return queryInfoCount1;
			}else{
				return queryInfoCount2;
			}
		}
		//引进推广新品种新产品（个）
		else if(queryInfoCount.getBfsx().equals("yjtgxpzxcp")){			
			queryInfoCount1 = queryInfoCountDao.getYjtgxpzxcpNum(queryInfoCount);
			if(queryInfoCount1 != null){
				return queryInfoCount1;
			}else{
				return queryInfoCount2;
			}
		}
		//解决技术难题(个）
		else if(queryInfoCount.getBfsx().equals("jjjsnt")){
			queryInfoCount1 = queryInfoCountDao.getJjjsntNum(queryInfoCount);
			if(queryInfoCount1 != null){
				return queryInfoCount1;
			}else{
				return queryInfoCount2;
			}
		}
		//创办领办培育企业或合作社（个）
		else if(queryInfoCount.getBfsx().equals("cbqyhzs")){
			queryInfoCount1 = queryInfoCountDao.getCbqyhzsNum(queryInfoCount);
			if(queryInfoCount1 != null){
				return queryInfoCount1;
			}else{
				return queryInfoCount2;
			}
		}
		//带动就业人次
		else if(queryInfoCount.getBfsx().equals("ddjyrc")){
			queryInfoCount1 = queryInfoCountDao.getDdjyrcNum(queryInfoCount);
			if(queryInfoCount1 != null){
				return queryInfoCount1;
			}else{
				return queryInfoCount2;
			}
		}
		//服务农村合作组织
		else if(queryInfoCount.getBfsx().equals("fwnchzzz")){
			queryInfoCount1 = queryInfoCountDao.getFwnchzzzNum(queryInfoCount);
			if(queryInfoCount1 != null){
				return queryInfoCount1;
			}else{
				return queryInfoCount2;
			}
		}
		//建立科技示范基地
		else if(queryInfoCount.getBfsx().equals("jlkjsfjd")){
			queryInfoCount1 = queryInfoCountDao.getJlkjsfjdNum(queryInfoCount);
			if(queryInfoCount1 != null){
				return queryInfoCount1;
			}else{
				return queryInfoCount2;
			}
		}
		//培育科技示范户
		else if(queryInfoCount.getBfsx().equals("pykjsfh")){
			queryInfoCount1 = queryInfoCountDao.getPykjsfhNum(queryInfoCount);
			if(queryInfoCount1 != null){
				return queryInfoCount1;
			}else{
				return queryInfoCount2;
			}
		}
		//举办培训班
		else if(queryInfoCount.getBfsx().equals("jbpxb")){
			queryInfoCount1 = queryInfoCountDao.getJbpxbNum(queryInfoCount);
			if(queryInfoCount1 != null){
				return queryInfoCount1;
			}else{
				return queryInfoCount2;
			}
		}
		//培训人员（人次）
		else if(queryInfoCount.getBfsx().equals("pxry")){
			queryInfoCount1 = queryInfoCountDao.getPxryNum(queryInfoCount);
			if(queryInfoCount1 != null){
				return queryInfoCount1;
			}else{
				return queryInfoCount2;
			}
		}
		//培训贫困群众（人次）
		else if(queryInfoCount.getBfsx().equals("pxpkqz")){
			queryInfoCount1 = queryInfoCountDao.getPxpkqzNum(queryInfoCount);
			if(queryInfoCount1 != null){
				return queryInfoCount1;
			}else{
				return queryInfoCount2;
			}
		}
		//帮扶贫困村（个）
		else if(queryInfoCount.getBfsx().equals("bfpkc")){
			queryInfoCount1 = queryInfoCountDao.getBfpkcNum(queryInfoCount);
			if(queryInfoCount1 != null){
				return queryInfoCount1;
			}else{
				return queryInfoCount2;
			}
		}
		//贫困户（户）
		else if(queryInfoCount.getBfsx().equals("pkh")){
			queryInfoCount1 = queryInfoCountDao.getPkhNum(queryInfoCount);
			if(queryInfoCount1 != null){
				return queryInfoCount1;
			}else{
				return queryInfoCount2;
			}
		}
		//带动脱贫（户）
		else if(queryInfoCount.getBfsx().equals("ddtp")){
			queryInfoCount.setBfsx("b.tgxjs");
			queryInfoCount1 = queryInfoCountDao.getDdtpNum(queryInfoCount);
			if(queryInfoCount1 != null){
				return queryInfoCount1;
			}else{
				return queryInfoCount2;
			}
		}
		//贫困人口（个）
		else if(queryInfoCount.getBfsx().equals("pkrk")){
			queryInfoCount.setBfsx("b.tgxjs");
			queryInfoCount1 = queryInfoCountDao.getPkrkNum(queryInfoCount);
			if(queryInfoCount1 != null){
				return queryInfoCount1;
			}else{
				return queryInfoCount2;
			}
		}
		//科技服务天数（天）
		else if(queryInfoCount.getBfsx().equals("kjfwts")){
			queryInfoCount1 = queryInfoCountDao.getKjfwtsNum(queryInfoCount);
			if(queryInfoCount1 != null){
				return queryInfoCount1;
			}else{
				return queryInfoCount2;
			}
		}
		//集体经济增收（万元）
		else if(queryInfoCount.getBfsx().equals("jtjjzc")){
			queryInfoCount1 = queryInfoCountDao.getJtjjzcNum(queryInfoCount);
			if(queryInfoCount1 != null){
				return queryInfoCount1;
			}else{
				return queryInfoCount2;
			}
		}
		//实现产值（万元）
		else if(queryInfoCount.getBfsx().equals("sxcz")){
			queryInfoCount1 = queryInfoCountDao.getSxczNum(queryInfoCount);
			if(queryInfoCount1 != null){
				return queryInfoCount1;
			}else{
				return queryInfoCount2;
			}
		}
		//带动增收（万元）
		else if(queryInfoCount.getBfsx().equals("ddzs")){
			queryInfoCount1 = queryInfoCountDao.getDdzsNum(queryInfoCount);
			if(queryInfoCount1 != null){
				return queryInfoCount1;
			}else{
				return queryInfoCount2;
			}
		}
		//创利税（万元）
		else if(queryInfoCount.getBfsx().equals("cls")){
			queryInfoCount1 = queryInfoCountDao.getClsNum(queryInfoCount);
			if(queryInfoCount1 != null){
				return queryInfoCount1;
			}else{
				return queryInfoCount2;
			}
		}
		//其他 
		else{
			return queryInfoCount2;
		}
	}
	//获得日志年份
	public List<String> getDrYear(){
		List<String> list = new ArrayList<String>();	
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy");
		String year = sdf1.format(new Date());
		list = queryInfoCountDao.getDrYear();
		if(list != null ){
			
		}else{
			list.add(year);
		}		
		return list;
	}
	
	/**
	 * 获得专家回复次数列表
	 * @param page
	 * @param user
	 * @return
	 */
	public Page<QueryInfoCount> getzjhfch(Page<QueryInfoCount> page, QueryInfoCount queryInfoCount) {
		// 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
		User user = UserUtils.getUser();
		queryInfoCount.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
		// 设置分页参数
		queryInfoCount.setPage(page);
		// 执行分页查询
		page.setList(queryInfoCountDao.getzjhfch(queryInfoCount));
		return page;
	}
	
}
