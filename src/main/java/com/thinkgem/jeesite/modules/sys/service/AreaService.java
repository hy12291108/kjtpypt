/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.modules.sys.dao.AreaDao;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 区域Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends TreeService<AreaDao, Area> {

	public List<Area> findAll(){
		boolean flag=true;
		return UserUtils.getAreaList(flag);
	}

	public List<Area> findList(){
		boolean flag=false;
		return UserUtils.getAreaList(flag);
	}
	
	/*贫困县获取*/
	public List<Area> findPoorAreaList(){
		boolean flag=false;
		List<Area> poorArea = UserUtils.getAreaList(flag);
		if(poorArea!=null){
			for(int i=0;i<poorArea.size();i++){
				if(poorArea.get(i).getType().equals("4")&&poorArea.get(i).getFlag().equals("0")){
					poorArea.remove(i);
				}
			}
			
		}
		return UserUtils.getAreaList(flag);
	}
	
	@Transactional(readOnly = false)
	public void save(Area area) {
		super.save(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(Area area) {
		super.delete(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
}
