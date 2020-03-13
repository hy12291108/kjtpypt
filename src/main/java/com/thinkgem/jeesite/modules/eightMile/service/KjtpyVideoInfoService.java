/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.eightMile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.eightMile.entity.KjtpyVideoInfo;
import com.thinkgem.jeesite.modules.eightMile.dao.KjtpyVideoInfoDao;

/**
 * 视频文件Service
 * @author 武鹏飞
 * @version 2019-11-29
 */
@Service
@Transactional(readOnly = true)
public class KjtpyVideoInfoService extends CrudService<KjtpyVideoInfoDao, KjtpyVideoInfo> {

	@Autowired
	private KjtpyVideoInfoDao dao;
	
	public KjtpyVideoInfo get(String id) {
		return super.get(id);
	}
	
	public List<KjtpyVideoInfo> findList(KjtpyVideoInfo kjtpyVideoInfo) {
		return super.findList(kjtpyVideoInfo);
	}
	
	public Page<KjtpyVideoInfo> findPage(Page<KjtpyVideoInfo> page, KjtpyVideoInfo kjtpyVideoInfo) {
		return super.findPage(page, kjtpyVideoInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(KjtpyVideoInfo kjtpyVideoInfo) {
		super.save(kjtpyVideoInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(KjtpyVideoInfo kjtpyVideoInfo) {
		super.delete(kjtpyVideoInfo);
	}

	public List<KjtpyVideoInfo> getListByArticleId(String ArticleId) {
		// TODO Auto-generated method stub
		return dao.getListByArticleId(ArticleId);
	}
	
	
	
	
}