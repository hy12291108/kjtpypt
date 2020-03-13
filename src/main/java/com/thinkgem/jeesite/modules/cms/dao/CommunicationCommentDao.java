/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cms.entity.CommunicationComment;

/**
 * 评论DAO接口
 * @author LG
 * @version 20171101
 */
@MyBatisDao
public interface CommunicationCommentDao extends CrudDao<CommunicationComment> {
	/*
	 * 所有发布的评论
	 * 20171102
	 * @author LG
	 */
	public List<CommunicationComment> findAllList(String id);
	/*
	 * 评论审核列表
	 * 20171102
	 */
	public List<CommunicationComment> findCheckList(CommunicationComment communicationComment);
	/*
	 * 更新审核结果
	 * 20171102
	 */
	public int updateCheck(String id);
	
	
	/*
	 * 删除更新
	 */
	public int updateStatus(String id);
}
