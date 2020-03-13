/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.eightMile.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.eightMile.entity.KjtpyVideoInfo;

/**
 * 视频文件DAO接口
 * @author 武鹏飞
 * @version 2019-11-29
 */
@MyBatisDao
public interface KjtpyVideoInfoDao extends CrudDao<KjtpyVideoInfo> {

	List<KjtpyVideoInfo> getListByArticleId(String articleId);
}