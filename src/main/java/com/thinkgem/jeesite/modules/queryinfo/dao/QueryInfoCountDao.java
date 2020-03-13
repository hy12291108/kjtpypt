package com.thinkgem.jeesite.modules.queryinfo.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.queryinfo.entity.QueryInfoCount;
import com.thinkgem.jeesite.modules.sys.entity.User;
/**
 * 申请特派员DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface QueryInfoCountDao extends CrudDao<QueryInfoCount> {	
	/**
	 * 根据年份查询特派员数量
	 * @param year
	 * @return
	 */
	public QueryInfoCount getQureyInfoCount(QueryInfoCount queryInfoCount);
	/**
	 * 获得培训农民数量
	 * @param queryInfoCount
	 * @return
	 */
	public QueryInfoCount getPxnmNum(QueryInfoCount queryInfoCount);
	/**
	 * 获得推广新技术数量
	 * @param queryInfoCount
	 * @return
	 */
	public QueryInfoCount getTgxjsNum(QueryInfoCount queryInfoCount);
	/**
	 * 获得引进推广新品种新产品（个）数量
	 * @param queryInfoCount
	 * @return
	 */
	public QueryInfoCount getYjtgxpzxcpNum(QueryInfoCount queryInfoCount);
	/**
	 * 获得解决技术难题(个）数量
	 * @param queryInfoCount
	 * @return
	 */
	public QueryInfoCount getJjjsntNum(QueryInfoCount queryInfoCount);
	/**
	 * 获得创办领办培育企业或合作社（个）数量
	 * @param queryInfoCount
	 * @return
	 */
	public QueryInfoCount getCbqyhzsNum(QueryInfoCount queryInfoCount);
	/**
	 * 获得带动就业人次数量
	 * @param queryInfoCount
	 * @return
	 */
	public QueryInfoCount getDdjyrcNum(QueryInfoCount queryInfoCount);
	/**
	 * 获得服务农村合作组织数量
	 * @param queryInfoCount
	 * @return
	 */
	public QueryInfoCount getFwnchzzzNum(QueryInfoCount queryInfoCount);
	/**
	 * 获得建立科技示范基地数量
	 * @param queryInfoCount
	 * @return
	 */
	public QueryInfoCount getJlkjsfjdNum(QueryInfoCount queryInfoCount);
	/**
	 * 获得培育科技示范户数量
	 * @param queryInfoCount
	 * @return
	 */
	public QueryInfoCount getPykjsfhNum(QueryInfoCount queryInfoCount);
	/**
	 * 获得举办培训班（场次）数量
	 * @param queryInfoCount
	 * @return
	 */
	public QueryInfoCount getJbpxbNum(QueryInfoCount queryInfoCount);
	/**
	 * 获得培训人员（人次）数量
	 * @param queryInfoCount
	 * @return
	 */
	public QueryInfoCount getPxryNum(QueryInfoCount queryInfoCount);
	/**
	 * 获得培训贫困群众（人次）数量
	 * @param queryInfoCount
	 * @return
	 */
	public QueryInfoCount getPxpkqzNum(QueryInfoCount queryInfoCount);
	/**
	 * 获得帮扶贫困村（个）数量
	 * @param queryInfoCount
	 * @return
	 */
	public QueryInfoCount getBfpkcNum(QueryInfoCount queryInfoCount);
	/**
	 * 获得贫困户（户）数量
	 * @param queryInfoCount
	 * @return
	 */
	public QueryInfoCount getPkhNum(QueryInfoCount queryInfoCount);
	/**
	 * 获得带动脱贫（户）数量
	 * @param queryInfoCount
	 * @return
	 */
	public QueryInfoCount getDdtpNum(QueryInfoCount queryInfoCount);
	/**
	 * 获得贫困人口（个）数量
	 * @param queryInfoCount
	 * @return
	 */
	public QueryInfoCount getPkrkNum(QueryInfoCount queryInfoCount);
	/**
	 * 获得科技服务天数（天）数量
	 * @param queryInfoCount
	 * @return
	 */
	public QueryInfoCount getKjfwtsNum(QueryInfoCount queryInfoCount);
	/**
	 * 获得集体经济增收（万元）数量
	 * @param queryInfoCount
	 * @return
	 */
	public QueryInfoCount getJtjjzcNum(QueryInfoCount queryInfoCount);
	/**
	 * 获得实现产值（万元）数量
	 * @param queryInfoCount
	 * @return
	 */
	public QueryInfoCount getSxczNum(QueryInfoCount queryInfoCount);
	/**
	 * 获得带动增收（万元）数量
	 * @param queryInfoCount
	 * @return
	 */
	public QueryInfoCount getDdzsNum(QueryInfoCount queryInfoCount);
	/**
	 * 获得创利税（万元）数量
	 * @param queryInfoCount
	 * @return
	 */
	public QueryInfoCount getClsNum(QueryInfoCount queryInfoCount);
	
	/**
	 * 获得日志年份
	 * @param 
	 * @return
	 */
	public List<String> getDrYear();
	/**
	 *  查询专家回复次数列表
	 * @return
	 */
	public List<QueryInfoCount> getzjhfch(QueryInfoCount queryInfoCount);
} 
