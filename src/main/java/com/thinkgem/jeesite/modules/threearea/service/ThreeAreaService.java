/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.threearea.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.dao.TpyInfoDao;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.TpyInfo;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.threearea.dao.ThreeAreaBaseDataDao;
import com.thinkgem.jeesite.modules.threearea.dao.ThreeAreaDao;
import com.thinkgem.jeesite.modules.threearea.entity.ThreeArea;
import com.thinkgem.jeesite.modules.threearea.entity.ThreeAreaBaseData;

/**
 * 三区人才Service
 * @author 刘钢
 * @version 2017-08-17
 */
@Service
@Transactional(readOnly = true)
public class ThreeAreaService extends  BaseService {
	@Autowired
	private OfficeDao officeDao;
	@Autowired
	private ThreeAreaDao threeAreaDao;
	@Autowired
	private ThreeAreaBaseDataDao threeAreaBaseDataDao;
	@Autowired
	private TpyInfoDao tpyInfoDao;
	@Autowired
	private UserDao userDao;
	@Transactional(readOnly = true)
	public TpyInfo get(String id){
		TpyInfo tpyInfo = tpyInfoDao.get(id);
		return tpyInfo;
	}
	public ThreeArea getThreeArea(String id){
		ThreeArea threeArea = threeAreaDao.get(id);
		return threeArea;
	}
	/*
	 * 特派员申请三区人才
	 * 20170920
	 * 刘钢
	 */
	@Transactional(readOnly=false)
	public Map<String,String> addSqThreeArea(ThreeArea threeArea){
		Map<String, String> map = new HashMap<String,String>();
		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			User user = UserUtils.getUser();	
			//判断是否为省级特派员
			if(user.getOffice().getId().equals("08bae2518f1646dfa9e0b6cedf904b54")){
				map.put("false", "省级特派员需下派后才能申请成为三区人才");
				return map;
			}
			//判断本年度是否已申请过三区人才
			ThreeArea threeArea1 = new ThreeArea();
			threeArea1.setYear(threeArea.getYear());
			threeArea1.setTpyId(user.getId());
			List<ThreeArea> threeArea2 = threeAreaDao.getSqResult(threeArea1);
			if(threeArea2.size()>0){
				map.put("false", threeArea.getYear()+"度已申请过三区人才");
				return map;
			}
			threeArea.setZoneId(user.getOffice().getId());
			threeArea.setZoneName(user.getOffice().getName());
			threeArea.setZoneParentId(user.getOffice().getParentId());
			threeArea.setXpId(user.getTpyXpFlag());
			//根据tpyXpFlag 查出parentid 放入实体类中threeArea 作为标识
			Office office = new Office();
			office.setId(user.getTpyXpFlag());
			if(officeDao.get(office)!=null){
			threeArea.setXpParentId(officeDao.get(office).getParentId());
			}else{
				map.put("false", "申请失败");
				return map;
			}
			threeArea.setTpyId(user.getId());
			threeArea.preInsert();
			threeArea.setStatus("1");
			threeArea.setSqTime(sf.format(new Date()));
			//threeArea.setPersonalInfo(personalInfo)
			threeAreaDao.insert(threeArea);
			map.put("true", "申请成功");
			return map;
		} catch (Exception e) {
			map.put("false", "申请失败");
			return map;
		}
	}
	/* @author 刘钢
	 * 三区人才审核列表获取
	 * 20170920
	 */
	public List<ThreeArea> getThreeArea1(){
		//审核时间未在开始时间 结束时间之间
		User user = UserUtils.getUser();
		List<ThreeArea> lists = null;
		ThreeArea threeArea = new ThreeArea();
		ThreeAreaBaseData threeAreaBaseData = threeAreaBaseDataDao.getShParameter(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		if(threeAreaBaseData!=null){	
		//省获取三区列表
		if(user.getOffice().getId().equals("08bae2518f1646dfa9e0b6cedf904b54")){
			threeArea.setStatus("2");
			threeArea.setYear(threeAreaBaseData.getYear());
			lists =threeAreaDao.getThreeAreaP(threeArea);
		}else{
			//市获取列表
			lists =threeAreaDao.getThreeAreaC(user.getOffice().getId(),threeAreaBaseData.getYear());
		}
		 return lists;
		}else{
			//省获取三区列表
			if(user.getOffice().getId().equals("08bae2518f1646dfa9e0b6cedf904b54")){
				threeArea.setStatus("2");
				lists =threeAreaDao.getThreeAreaP(threeArea);
			}else{
				//市获取列表
				lists =threeAreaDao.getThreeAreaC(user.getOffice().getId(),"");
			}
			return lists;
		}
	}
	/* @author 刘钢
	 * 三区人才审核结果更新
	 * 20170920
	 */
	@Transactional(readOnly=false)
	public boolean updateStatus(ThreeArea threeArea){
		
		try {
			threeAreaDao.update(threeArea);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	
	}
	/*
	 * @author 刘钢
	 * 查找审核通过的人才
	 * 20170920
	 * 
	 */
	public List<ThreeArea> getPassTalent(){
		//按区域及管辖范围查询特派员
		User user = new User();
		user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
		user.setCheckFlag("2");
		List<User> userList = userDao.findListForThreeArea(user);
		//查询审核通过的，如果年度可以查出，按年度，否则，查询出所有年度
		ThreeAreaBaseData threeAreaBaseData = threeAreaBaseDataDao.getPassParameter(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		//三区人才
		List<ThreeArea> threeAreas = new ArrayList<ThreeArea>();
		List<ThreeArea> threeArea = null;
		if(threeAreaBaseData!=null){
		ThreeArea threeArea1 = new ThreeArea();
		threeArea1.setStatus("4");
		threeArea1.setYear(threeAreaBaseData.getYear());
		 threeArea = threeAreaDao.getPassTalent(threeArea1);
		 for(int i=0;i<userList.size();i++){
			 for(int j=0;j<threeArea.size();j++){
				 if(userList.get(i).getId().equals(threeArea.get(j).getTpyId())){
					 threeAreas.add(threeArea.get(j));
				 }
			 }
		 }
		return threeAreas;}else{
			ThreeArea threeArea1 = new ThreeArea();
			threeArea1.setStatus("4");
			threeArea = threeAreaDao.getPassTalent(threeArea1);
			 for(int i=0;i<userList.size();i++){
				 for(int j=0;j<threeArea.size();j++){
					 if(userList.get(i).getId().equals(threeArea.get(j).getTpyId())){
						 threeAreas.add(threeArea.get(j));
					 }
				 }
			 }
			return threeAreas;
		}
	}
	
	/*
	 * @author 刘钢
	 * 查找审核通过的人才，获取下派人员列表
	 * 20170920
	 * 
	 */
	public List<ThreeArea> getXpList(){
		//查询审核通过的，如果年度可以查出，按年度，否则，查询出所有年度
		ThreeAreaBaseData threeAreaBaseData = threeAreaBaseDataDao.getPassParameter(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		List<ThreeArea> threeArea = null;
		if(threeAreaBaseData!=null){
		ThreeArea threeArea1 = new ThreeArea();
		threeArea1.setStatus("4");
		threeArea1.setYear(threeAreaBaseData.getYear());
		 threeArea = threeAreaDao.getXpList(threeArea1);
		return threeArea;}else{
			ThreeArea threeArea1 = new ThreeArea();
			threeArea1.setStatus("4");
			threeArea = threeAreaDao.getXpList(threeArea1);
			return threeArea;
		}
	}
	
	/*
	 * @author 刘钢
	 * 查找审核未通过的人才
	 * 20170929
	 * @updateDate 20171011
	 */
	public List<ThreeArea> getFailTalent(){
		//按区域及管辖范围查询特派员
		User user = new User();
		user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
		user.setCheckFlag("2");
		List<User> userList = userDao.findListForThreeArea(user);
		ThreeAreaBaseData threeAreaBaseData = threeAreaBaseDataDao.getPassParameter(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		//三区人才
		List<ThreeArea> threeAreas = new ArrayList<ThreeArea>();
		List<ThreeArea> threeArea = null;
		if(threeAreaBaseData!=null){
		ThreeArea threeArea1 = new ThreeArea();
		threeArea1.setYear(threeAreaBaseData.getYear());
		threeArea = threeAreaDao.getFailTalent(threeArea1);
		for(int i=0;i<userList.size();i++){
			 for(int j=0;j<threeArea.size();j++){
				 if(userList.get(i).getId().equals(threeArea.get(j).getTpyId())){
					 threeAreas.add(threeArea.get(j));
				 }
			 }
		 }
		return threeAreas;}else{
			ThreeArea threeArea1 = new ThreeArea();
			threeArea = threeAreaDao.getFailTalent(threeArea1);
			for(int i=0;i<userList.size();i++){
				 for(int j=0;j<threeArea.size();j++){
					 if(userList.get(i).getId().equals(threeArea.get(j).getTpyId())){
						 threeAreas.add(threeArea.get(j));
					 }
				 }
			 }
			return threeAreas;
		}
	}
	
	
	/*
	 * @author 刘钢
	 * 三区人才历史记录
	 *  20170927
	 */
	public List<ThreeArea> findResult(){
		ThreeArea threeArea = new ThreeArea();
		threeArea.setTpyId(UserUtils.getUser().getId());
		return threeAreaDao.getShResult(threeArea);
	}
	/*
	 * 刘钢
	 * 三区人才参数维护
	 * 20170928
	 * 
	 */
	@Transactional(readOnly=false)
	public boolean addBaseData(ThreeAreaBaseData threeAreaBaseData){
		try {
			threeAreaBaseData.preInsert();
			threeAreaBaseDataDao.insert(threeAreaBaseData);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	/*
	 * 刘钢
	 * 三区人才获取申请时间段内的参数
	 * 20170929
	 */
	public ThreeAreaBaseData getParameter(){
		//获取申请时间段内的参数
		List<ThreeAreaBaseData> threeAreaBaseData1 = threeAreaBaseDataDao.getSqParameter(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		ThreeAreaBaseData threeAreaBaseData = new ThreeAreaBaseData();
		if(threeAreaBaseData1!=null){
			for(int i=0;i<threeAreaBaseData1.size();i++){
				threeAreaBaseData=threeAreaBaseData1.get(0);
			}
		if(threeAreaBaseData.getYearStartTime()!=null&&threeAreaBaseData.getYearEndTime()!=null&&
				threeAreaBaseData.getStartTime()!=null&&threeAreaBaseData.getEndTime()!=null){
		threeAreaBaseData.setYearStartTime(threeAreaBaseData.getYearStartTime().substring(0, 10));
		threeAreaBaseData.setYearEndTime(threeAreaBaseData.getYearEndTime().substring(0, 10));
		threeAreaBaseData.setStartTime(threeAreaBaseData.getStartTime().substring(0, 10));
		threeAreaBaseData.setEndTime(threeAreaBaseData.getEndTime().substring(0, 10));
		}
		return threeAreaBaseData;}
		else{
			return null;
		}
	}
	
	/*
	 * 刘钢
	 * 三区人才获取下派时间段内的参数
	 * 20170929
	 */
	public ThreeAreaBaseData getXpParameter(){
		//获取申请时间段内的参数
		ThreeAreaBaseData threeAreaBaseData = threeAreaBaseDataDao.getParameter(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		System.out.println("threeAreaBaseData="+threeAreaBaseData);
		if(threeAreaBaseData!=null){
			if(threeAreaBaseData.getYearStartTime()!=null&&threeAreaBaseData.getYearEndTime()!=null&&
					threeAreaBaseData.getStartTime()!=null&&threeAreaBaseData.getEndTime()!=null){
					threeAreaBaseData.setYearStartTime(threeAreaBaseData.getYearStartTime().substring(0, 10));
					threeAreaBaseData.setYearEndTime(threeAreaBaseData.getYearEndTime().substring(0, 10));
					threeAreaBaseData.setStartTime(threeAreaBaseData.getStartTime().substring(0, 10));
					threeAreaBaseData.setEndTime(threeAreaBaseData.getEndTime().substring(0, 10));
			}
		return threeAreaBaseData;
		}else{
			return null;
		}
	}
	
	
	
	/*
	 * LG
	 * 三区参数修改
	 * 20170929
	 */
	@Transactional(readOnly=false)
	public boolean updateBaseData(ThreeAreaBaseData threeAreaBaseData){
		try {
			threeAreaBaseData.preUpdate();
			threeAreaBaseDataDao.update(threeAreaBaseData);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	/* @author 刘钢
	 * 三区人才下派信息更新
	 * 20170929
	 */
	@Transactional(readOnly=false)
	public boolean updateXpInfo(ThreeArea threeArea){
		String xpTime = DateUtils.getDate("yyyy-MM-dd");
		threeArea.setXpTime(xpTime);
		Office office1 = null;
		List<Office> office = officeDao.getOffice(threeArea.getXpZone());
		if(office!=null){
			for(int i=0;i<office.size();i++){
				office1 = office.get(0);
			}
			threeArea.setXpZone(office1.getId());
			threeArea.setXpZoneName(office1.getName());
			threeAreaDao.updateXpInfo(threeArea);
			return true;
		}else{
			return false;
		}
	}
	/* @author gz
	 * 三区人才信息修改
	 * 20181127
	 */
	@Transactional(readOnly=false)
	public boolean tpyThreeAreaupdate(ThreeArea threeArea){

		try {
			threeAreaDao.tpyThreeAreaupdate(threeArea);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	/* @author gz
	 * 三区人才审核列表获取
	 * 20181127带分页及查询功能的方法
	 */
	public Page<ThreeArea> getThreeArea2(Page<ThreeArea> page,ThreeArea threeArea){
		//审核时间未在开始时间 结束时间之间
		User user = UserUtils.getUser();
	//	ThreeArea threeArea = new ThreeArea();
		ThreeAreaBaseData threeAreaBaseData = threeAreaBaseDataDao.getShParameter(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		if(threeAreaBaseData!=null){	
		//省获取三区列表
		//	System.out.println("9999999999999999="+user.getOffice().getId());
		if(user.getOffice().getId().equals("08bae2518f1646dfa9e0b6cedf904b54")){
			threeArea.setStatus("2");
			threeArea.setYear(threeAreaBaseData.getYear());
			// 设置分页参数
			threeArea.setPage(page);
			page.setList(threeAreaDao.getThreeAreaP(threeArea));
		}else{
			//市获取列表
			threeArea.setPage(page);
			
			threeArea.setXpId(user.getOffice().getId());
			threeArea.setYear(threeAreaBaseData.getYear());
			threeArea.setName(threeArea.getName());
			page.setList(threeAreaDao.getThreeAreaC1(threeArea));
		}
		 return page;
		}else{
			//省获取三区列表
			if(user.getOffice().getId().equals("08bae2518f1646dfa9e0b6cedf904b54")){
				threeArea.setStatus("2");
				threeArea.setPage(page);
				page.setList(threeAreaDao.getThreeAreaP(threeArea));
			}else{
				//市获取列表
				threeArea.setPage(page);
				threeArea.setXpId(user.getOffice().getId());
				threeArea.setYear("");
				threeArea.setName(threeArea.getName());
				page.setList(threeAreaDao.getThreeAreaC1(threeArea));
			}
			return page;
		}
	}
	/*
	 * @author gz
	 * 查找审核通过的人才
	 * 20181127带分页及查询功能的方法
	 * 
	 */
	public Page<ThreeArea> getPassTalent2(Page<ThreeArea> page,ThreeArea threeArea){
		//按区域及管辖范围查询特派员
		User user = UserUtils.getUser();
		user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
		user.setCheckFlag("2");
		List<User> userList = userDao.findListForThreeArea(user);
		//查询审核通过的，如果年度可以查出，按年度，否则，查询出所有年度
		ThreeAreaBaseData threeAreaBaseData = threeAreaBaseDataDao.getPassParameter(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		//三区人才

		if(threeAreaBaseData!=null){
		//	System.out.println("3333333333333333333="+user.getOffice().getId());
			//省获取三区列表
			if(user.getOffice().getId().equals("08bae2518f1646dfa9e0b6cedf904b54")){
				threeArea.setStatus("4");
				threeArea.setYear(threeAreaBaseData.getYear());
				threeArea.setPage(page);
				page.setList(threeAreaDao.getPassTalent(threeArea));
			}else{
				//市获取列表
				threeArea.setStatus("4");
				threeArea.setYear(threeAreaBaseData.getYear());
				threeArea.setPage(page);
				threeArea.setXpId("");
				threeArea.setName(threeArea.getName());
				page.setList(threeAreaDao.getPassTalent1(threeArea));
			}
		

		return page;
		}else{
			//省获取三区列表
			if(user.getOffice().getId().equals("08bae2518f1646dfa9e0b6cedf904b54")){
				threeArea.setStatus("4");
				threeArea.setPage(page);
				page.setList(threeAreaDao.getPassTalent(threeArea));
			}else{
				//市获取列表
			//	System.out.println("4444444444444444442="+user.getOffice().getId());
				threeArea.setStatus("4");
				threeArea.setPage(page);
				threeArea.setXpId(user.getOffice().getId());
				threeArea.setName(threeArea.getName());
				page.setList(threeAreaDao.getPassTalent1(threeArea));
			}
			
			return page;
		}
	}
	/*
	 * @author gz
	 * 查找审核未通过的人才
	 * 20181127带分页及查询功能的方法
	 */
	public Page<ThreeArea> getFailTalent2(Page<ThreeArea> page,ThreeArea threeArea){
		//按区域及管辖范围查询特派员
		User user = UserUtils.getUser();
		user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
		user.setCheckFlag("2");
		List<User> userList = userDao.findListForThreeArea(user);
		ThreeAreaBaseData threeAreaBaseData = threeAreaBaseDataDao.getPassParameter(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		//三区人才
		if(threeAreaBaseData!=null){
		//	System.out.println("4444444444444444442=");
		//省获取三区列表
			if(user.getOffice().getId().equals("08bae2518f1646dfa9e0b6cedf904b54")){
				threeArea.setYear(threeAreaBaseData.getYear());
				threeArea.setPage(page);
				page.setList(threeAreaDao.getFailTalent(threeArea));
	
			}else{
				//市获取列表
				
				threeArea.setYear(threeAreaBaseData.getYear());
				threeArea.setPage(page);
				threeArea.setXpId(user.getOffice().getId());
				threeArea.setName(threeArea.getName());
				page.setList(threeAreaDao.getFailTalent1(threeArea));
			}
		return page;
		}else{
		//	System.out.println("3333333333333=");
			//省获取三区列表
			if(user.getOffice().getId().equals("08bae2518f1646dfa9e0b6cedf904b54")){
				threeArea.setPage(page);
				page.setList(threeAreaDao.getFailTalent(threeArea));
			}else{
				//市获取列表
				threeArea.setPage(page);
				threeArea.setXpId(user.getOffice().getId());
				threeArea.setName(threeArea.getName());
				page.setList(threeAreaDao.getFailTalent1(threeArea));
			}
			
			return page;
		}
	}	
 }
