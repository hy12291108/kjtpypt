/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.sys.dao.DictDao;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.entity.MajorMenu;
import com.thinkgem.jeesite.modules.sys.service.MajorMenuService;

/**
 * 用户注册获取数据工具类
 * @author ThinkGem
 * @version 2013-5-29
 */
public class ForRegister {
	
	private static MajorMenuService majorMenuService = SpringContextHolder.getBean(MajorMenuService.class);

	
	/**
	 * 获取专业列表
	 * */
	public static List<MajorMenu> getMajorMenuList(){
		List<MajorMenu> majorList = majorMenuService.findAllMajorMenu();
		return majorList;
	}
	
}
