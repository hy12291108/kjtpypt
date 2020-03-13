/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cms.dao.CommentDao;
import com.thinkgem.jeesite.modules.cms.dao.ComunicationDao;
import com.thinkgem.jeesite.modules.cms.entity.Comment;
import com.thinkgem.jeesite.modules.cms.entity.Communication;

/**
 * 发布Service
 * @author LG
 * @version 20171101
 */
@Service
@Transactional(readOnly = true)
public class CommunicationService extends CrudService<ComunicationDao, Communication> {
	@Autowired
	private ComunicationDao comunicationDao;
	@Transactional(readOnly = false)
	public String insert(Communication communication){
		communication.preInsert();
		comunicationDao.insert(communication);
		return null;
	}
	
	
	
	public Page<Communication> findPage(Page<Communication> page, Communication communication) {
		return super.findPage(page,communication);
	}
	
	public List<Communication> checkList(Communication communication){
		List<Communication> communicationList = comunicationDao.findAllList(communication);
		return communicationList;
	}
	@Transactional(readOnly = false)
	public int updateCheck(String[] commentId){
		for(int i=0;i<commentId.length;i++){
			comunicationDao.updateCheck(commentId[i]);
		}
		return 0;
	}
	@Transactional(readOnly = false)
	public int updateStatus(String[] commentId){
		for(int i=0;i<commentId.length;i++){
			comunicationDao.updateStatus(commentId[i]);
		}
		return 0;
	}
	 
	/*
	public Page<Comment> findPage(Page<Comment> page, Comment comment) {
//		DetachedCriteria dc = commentDao.createDetachedCriteria();
//		if (StringUtils.isNotBlank(comment.getContentId())){
//			dc.add(Restrictions.eq("contentId", comment.getContentId()));
//		}
//		if (StringUtils.isNotEmpty(comment.getTitle())){
//			dc.add(Restrictions.like("title", "%"+comment.getTitle()+"%"));
//		}
//		dc.add(Restrictions.eq(Comment.FIELD_DEL_FLAG, comment.getDelFlag()));
//		dc.addOrder(Order.desc("id"));
//		return commentDao.find(page, dc);
		comment.getSqlMap().put("dsf", dataScopeFilter(comment.getCurrentUser(), "o", "u"));
		
		return super.findPage(page, comment);
	}
	
	@Transactional(readOnly = false)
	public void delete(Comment entity, Boolean isRe) {
		super.delete(entity);
	}*/
}
