/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.kjtpypt.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.kjtpypt.entity.SysAttachment;
import com.thinkgem.jeesite.modules.villagemanage.entity.TeamMember;

/**
 * 附件表DAO接口
 * @author Grace
 * @version 2017-08-03
 */
@MyBatisDao
public interface SysAttachmentDao extends CrudDao<SysAttachment> {
	public List<SysAttachment> findAttachByRecordId(SysAttachment sysAttachment);
	//2019-04-18加带附件类别的方法
	public List<SysAttachment> findTunAttachByRecordId(SysAttachment sysAttachment);

	public void deleteByFileName(String filename);
	//2019-04-18审核不通过del_flag=1
	public void changeTeamAttachment(String atttable,String remarks);
	
	
}