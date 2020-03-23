/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 用户DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface UserDao extends CrudDao<User> {
	
	/* @author LG
	 * 指派专家
	 * 20171113
	 */
	public int updateZjFlag(User user);
	/* @author LG
	 * 查询备选专家的特派员列表
	 * 20171113
	 */
	public  List<User> findZjSelectList(User user);
	
	/*
	 * 临时用户注册
	 * 20171031
	 * 
	 */
	public int insertTempUser(User user);
	/*
	 * 修改特派員信息
	 * 20171026
	 * 
	 */
	public int updatetpy(User user);
	/*
	 * 修改特派員(法人)信息
	 * 20171026
	 * 
	 */
	public int updatetpyCorp(User user);
	
	/*
	 * 修改服務對象信息
	 * 20171026
	 * 
	 */
	public int updateServiceObject(User user);
	/*
	 * @author lg
	 * 特派员下派
	 * 20170919
	 * 
	 */
	public int updateXp(User user);
	
	/**
	 * 管理员查询所在区域数据
	 * @param User
	 * @return
	 * 20171010
	 * @author lg
	 */
	public  List<User> findListByAdmin(User user);

	
	
	/**
	 * 管理员查询所在区域数据
	 * @param User
	 * @return
	 * 20171106
	 * @author LG
	 */
	public  List<User> 	findListForThreeArea(User user);
	

	
	
	/**
	 * 查询需求单位数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 * 20170906
	 */
	public List<User> findXdList(User user);
	
	
	/**
	 * 根据登录名称查询用户
	 * @param loginName
	 * @return
	 */
	public User getByLoginName(User user);


	/**
	 * 通过OfficeId获取用户列表，仅返回用户id和name（树查询用户时用）
	 * @param user
	 * @return
	 */
	public List<User> findUserByOfficeId(User user);
	
	/**
	 * 查询全部用户数目
	 * @return
	 */
	public long findAllCount(User user);
	
	/**
	 * 更新用户密码
	 * @param user
	 * @return
	 */
	public int updatePasswordById(User user);
	
	/**
	 * 更新用户审核信息
	 * @param user
	 * @return
	 */
	public int updateShResultById(User user);
	
	/**
	 * 更新登录信息，如：登录IP、登录时间
	 * @param user
	 * @return
	 */
	public int updateLoginInfo(User user);

	/**
	 * 删除用户角色关联数据
	 * @param user
	 * @return
	 */
	public int deleteUserRole(User user);
	
	/**
	 * 插入用户角色关联数据
	 * @param user
	 * @return
	 */
	public int insertUserRole(User user);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int updateUserInfo(User user);
	/**
	 * 根据officeId，获取单位名称列表
	 * @author 刘钢
	 * 20170815
	 */
	public List<String> getCorpList(String officeId);
	/* 刘刚
	 * 20170906
	 * 根据 user_id 查询
	 */
	
	public String findRoleList(String id);
	
	/* 
	 * 未下派特派员查询
	 * 刘钢
	 * 20170926
	 */
	public List<User> findXpList(User user);

    /**
     * 上传推荐表、修改审核状态
     * @param user
     * @return
     */
    int uploadTjTable(User user);

    /**
     * 特派员注册
     * @param user
     * @return
     */
    int register(User user);


    /**
     * 特派员完善信息保存
     */
    int naturePerfectInfoSave(User user);
    int corpPerfectInfoSave(User user);
    int reversePerfectInfoSave(User user);

}
