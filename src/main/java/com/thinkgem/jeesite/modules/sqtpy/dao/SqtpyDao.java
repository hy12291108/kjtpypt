package com.thinkgem.jeesite.modules.sqtpy.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sqtpy.entity.Sqtpy;
import com.thinkgem.jeesite.modules.sys.entity.User;
/**
 * 申请特派员DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface SqtpyDao extends CrudDao<Sqtpy> {
	
	
	/**
	 * 查看需求单位申请特派员列表
	 * author 曹雷
	 * 2017-08-01
	 */
	public List<Sqtpy> selectsqtpylist(Sqtpy sqtpy);
	public List<Sqtpy> selectsqtpylistYsh(Sqtpy sqtpy);
	public List<Sqtpy> selectsqtpylist1(String xqdwname);
	
	/**
	 * 通过特派员id查询 服务对象（需求单位），用于特派员日志填报（APP）
	 * @param tpyid
	 * @return
	 */
	public List<Sqtpy> findAllByTpyidApp(String tpyid);
	
	/**
	 * 更新申请特派员信息
	 * @param sqtpy
	 * @return
	 */
	public int updateTpySqInfo(Sqtpy sqtpy);
	/**
	 * 更新申请特派员信息
	 * @param sqtpy
	 * @return
	 */
	public int updateTpySqInfo1(Sqtpy sqtpy);
	public int updateTpySqInfo2(Sqtpy sqtpy);
	public int updateTpySqInfo3(Sqtpy sqtpy);
	public int changeTpy(Sqtpy sqtpy);
	/**
	 * 删除申请特派员信息
	 * @param user
	 * @return
	 */
	public int deleteTpySqInfo(String id);
	/**
	 * 更新服务协议(特派员)
	 * @param sqtpy
	 */
	public void updatexqdwsqtpyFwxyForAttch(Sqtpy sqtpy);
	/**
	 * 更新服务协议（需求单位）
	 * @param sqtpy
	 */
	public void updatexqdwsqtpyFwxyForAttchbyxqdw(Sqtpy sqtpy);
	
	public List<Sqtpy> selectbfgx(String tpyid,String xqdwid,String nowDate);
	
}
