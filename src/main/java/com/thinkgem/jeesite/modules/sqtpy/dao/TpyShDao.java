package com.thinkgem.jeesite.modules.sqtpy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sqtpy.entity.Sqtpy;
import com.thinkgem.jeesite.modules.sys.entity.User;
@MyBatisDao
public interface TpyShDao extends CrudDao<Sqtpy> {

	public List<Sqtpy> findShList(User user);
	public List<Sqtpy> findDyShList(Sqtpy sqtpy);
	public List<Sqtpy> findDyShList1(Sqtpy sqtpy);
	public List<Sqtpy> findShList1(Sqtpy sqtpy);	
	public List<Sqtpy> findDscxy(Sqtpy sqtpy);
	public List<Sqtpy> findYscxy(Sqtpy sqtpy);
	public List<Sqtpy> findDscxybyxqdw(Sqtpy sqtpy);
	public List<Sqtpy> findYscxybyxqdw(Sqtpy sqtpy);
	public List<Sqtpy> fwxyshlist(Sqtpy sqtpy);
	public List<Sqtpy> fwxyyshlist(Sqtpy sqtpy);
	// 查看审核未审核信息列表（app专用）
	public List<Sqtpy> findListbyApp(Sqtpy sqtpy);
	// 服务协议查询审核（APP）
	public List<Sqtpy> findAllFwxyFromApp(Sqtpy sqtpy);
	// 查询服务对象协议列表
	public List<Sqtpy> fwdxxylist(Sqtpy sqtpy);
	
}
