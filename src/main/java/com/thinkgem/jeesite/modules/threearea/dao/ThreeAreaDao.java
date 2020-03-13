/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.threearea.dao;
import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.threearea.entity.ThreeArea;
/**
 * 三区人才接口
 * @author 刘钢
 * @version 2017-08-17
 */
@MyBatisDao
public interface ThreeAreaDao extends CrudDao<ThreeArea> {
	/*
	 * @author LG
	 * 获取省的申请列表
	 * 20170920
	 */
	public List<ThreeArea> getThreeAreaP(ThreeArea threeArea);
	
	/*
	 * @author LG
	 * 获取市的申请列表
	 * 20170920
	 */
	public List<ThreeArea> getThreeAreaC(String id,String year);

	//public List<ThreeArea> getThreeAreaC(String id);
	/*
	 * @author LG
	 * 获取市的申请列表
	 * 20170920
	 */
	//public int updateStatus(String status,String id);
	/*
	 * @author LG
	 * 获取审核通过的人才
	 * 20170920
	 */
	public List<ThreeArea> getPassTalent(ThreeArea threeArea);
	
	/*
	 * @author LG
	 * 获取下派三区人才的列表
	 * 20171007
	 */
	public List<ThreeArea> getXpList(ThreeArea threeArea);
	/*
	 * @author LG
	 * 获取审核通过的人才
	 * 20170920
	 */
	public List<ThreeArea> getFailTalent(ThreeArea threeArea);
	
	/* @author LG
	 * 判断是否已申请三区人才
	 * 20170927
	 */
	public List<ThreeArea> getSqResult(ThreeArea threeArea);
	
	/* @author LG
	 * 三区人才历史记录
	 * 20170928
	 */
	public List<ThreeArea> getShResult(ThreeArea threeArea);
	
	/* @author 刘钢
	 * 三区人才下派信息更新
	 * 20170929
	 */
	public int updateXpInfo(ThreeArea threeArea);
	/*
	 * @author 刘钢
	 * 根据条件查询下派的三区人才
	 * 20171007
	 */
	public List<ThreeArea> findXpZonePerson(ThreeArea threeArea);
	/* @author gz
	 * 修改三区人才信息
	 * 20181127
	 */
	public int tpyThreeAreaupdate(ThreeArea threeArea);


	public List<ThreeArea> getThreeAreaC1(ThreeArea threeArea);
	/*
	 * @author LG
	 * 市级获取审核通过的人才
	 * 20181129
	 */
	public List<ThreeArea> getPassTalent1(ThreeArea threeArea);
	/*
	 * @author LG
	 * 获取审核未通过的人才
	 * 20181129
	 */
	public List<ThreeArea> getFailTalent1(ThreeArea threeArea);

}
