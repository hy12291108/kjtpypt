/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cms.entity.Communication;

/**
 * 评论DAO接口
 * @author LG
 * @version 20171101
 */
@MyBatisDao
public interface ComunicationDao extends CrudDao<Communication> {
		
	/*
	 * 发布查询
	 * 20171102
	 */
	public List<Communication> findList();
	
	/*
	 * 查询未审核发布列表
	 */
	public List<Communication> findAllList(Communication communication);
	
	/*
	 * 审核结果更新
	 */
	
	public int updateCheck(String id);
	/*
	 * 删除更新
	 */
	public int updateStatus(String id);
	
}
