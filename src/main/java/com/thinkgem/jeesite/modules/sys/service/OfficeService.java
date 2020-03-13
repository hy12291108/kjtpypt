/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.modules.sys.dao.OfficeDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 机构Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class OfficeService extends TreeService<OfficeDao, Office> {
	@Autowired
	private OfficeDao officeDao;
	/*
	 * @author lg
	 * 20170919
	 * 根据areaId查询Office信息
	 */
	public  Office findOffice(String areaId){
		List<Office> offices  = officeDao.getOffice(areaId);
		Office office = new Office();
		if(offices!=null){
			for(int i=0;i<offices.size();i++){
			office = offices.get(0);
			}
		}
		return office;
	}
	
	
	/*
	 * 特派员注册获取部门列表
	 * @author 刘钢
	 * 20170731
	 */
	public List<Office> findOfficeList(){
		 List<Office> officeList = new ArrayList<Office>();
		 officeList =  UserUtils.getOffice();
		 return officeList;
	}
	
	public List<Office> findAll(){
		return UserUtils.getOfficeList();
	}

	public List<Office> findList(Boolean isAll){
		if (isAll != null && isAll){
			return UserUtils.getOfficeAllList();
		}else{
			return UserUtils.getOfficeList();
		}
	}
	
	@Transactional(readOnly = true)
	public List<Office> findList(Office office){
		if(office != null){
			if(office.getParentIds()==""||office.getParentIds()==null){
				office.setParentIds("%");
			}else{
				office.setParentIds(office.getParentIds()+"%");
			}
			
			return dao.findByParentIdsLike(office);
		}
		return  new ArrayList<Office>();
	}
	
/*	@Transactional(readOnly = true)
	public List<Office> findList(Office office){
		if(office != null){
				office.setParentIds(office.getParentIds()+"%");
			return dao.findByParentIdsLike(office);
		}
		return  new ArrayList<Office>();
	}*/
	
	
	
	
	
	@Transactional(readOnly = false)
	public void save(Office office) {
		super.save(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(Office office) {
		super.delete(office);
		UserUtils.removeCache(UserUtils.CACHE_OFFICE_LIST);
	}
	
}
