/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.gwdj.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.gwdj.entity.JjbgGwdj;
import com.thinkgem.jeesite.modules.gwdj.dao.JjbgGwdjDao;

/**
 * 公文登记Service
 * @author 张云天
 * @version 2017-06-23
 */
@Service
@Transactional(readOnly = true)
public class JjbgGwdjService extends CrudService<JjbgGwdjDao, JjbgGwdj> {

	public JjbgGwdj get(String id) {
		return super.get(id);
	}
	
	public List<JjbgGwdj> findList(JjbgGwdj jjbgGwdj) {
		return super.findList(jjbgGwdj);
	}
	
	public Page<JjbgGwdj> findPage(Page<JjbgGwdj> page, JjbgGwdj jjbgGwdj) {
		return super.findPage(page, jjbgGwdj);
	}
	
	@Transactional(readOnly = false)
	public void save(JjbgGwdj jjbgGwdj) {
		super.save(jjbgGwdj);
	}
	
	@Transactional(readOnly = false)
	public void delete(JjbgGwdj jjbgGwdj) {
		super.delete(jjbgGwdj);
	}
	
}