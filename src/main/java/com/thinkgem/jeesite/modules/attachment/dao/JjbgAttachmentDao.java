/**
 */
package com.thinkgem.jeesite.modules.attachment.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.attachment.entity.JjbgAttachment;


/**
 * 附件DAO接口
 * @author zl
 * @version 2017-08-27
 */
@MyBatisDao
public interface JjbgAttachmentDao extends CrudDao<JjbgAttachment> {
	
	public List<JjbgAttachment> findattachByahtOperId(JjbgAttachment jjbgAttachment);

	public List<JjbgAttachment> findListBy(JjbgAttachment jjbgAttachment);
	
	public List<JjbgAttachment> findListByGW(String ahtOperId);
	
	public JjbgAttachment findObjectByPath(String path);
	
	public JjbgAttachment findObjectByAhtOperId(String operId);

	public List<JjbgAttachment> findListAtt(JjbgAttachment jjbgAttachment);

	public List<String> getFilePath(String attachmentId);
	
}