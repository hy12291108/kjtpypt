/**
 * Copyright &copy; 2012-2013 <a href="httparamMap://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.service.ServiceException;
import com.thinkgem.jeesite.common.utils.excel.ExcelUtils;
import com.thinkgem.jeesite.modules.sys.config.TpyInfoConfig;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.wrap.UserToMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 个人、企业、特派员注册、申报（业务层）
 * @author 白子涵
 * @version 2020-03-21
 */
@Service
@Transactional(readOnly = true)
public class TpyRegisterService extends CrudService<UserDao, User> {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserToMap userToMap;
    @Autowired
    private SystemService systemService;



    /**
     * 生成特派员推荐表Excel,成功将URL赋值给User
     * @param user
     * @return
     */
    public User userChangExcel(User user) {
        //生成的Excel文件名
        String exportExcelName = UUID.randomUUID() + ".xls";
        //文档数据
        Map<String, String> data = null;
        //模板路径
        String source = null;
        //生成路径
        String target = null;
        if (user.getPersonFlag().equals(TpyInfoConfig.PERSON_FLAG_NATURE)) {//自然人特派员生成Excel
            source = String.format(TpyInfoConfig.SOURCE_FILE_PATH, "自然人特派员推荐表模板.xls");
            target = String.format(TpyInfoConfig.TARGET_FILE_PATH, "自然人特派员推荐表", new SimpleDateFormat("yyyy/MM/dd").format(new Date()), exportExcelName);
            data = userToMap.getMapByNatureTpy(user);
        } else if (user.getPersonFlag().equals(TpyInfoConfig.PERSON_FLAG_CORPORATION)) {//法人特派员生成Excel
            source = String.format(TpyInfoConfig.SOURCE_FILE_PATH, "法人特派员推荐表模板.xls");
            target = String.format(TpyInfoConfig.TARGET_FILE_PATH, "法人特派员推荐表", new SimpleDateFormat("yyyy/MM/dd").format(new Date()), exportExcelName);
            data = userToMap.getMapByCorporationTpy(user);
        } else if (user.getPersonFlag().equals(TpyInfoConfig.PERSON_FLAG_REVERSE)) { //反向特派员生成Excel
            source = String.format(TpyInfoConfig.SOURCE_FILE_PATH, "反向特派员推荐表模板.xls");
            target = String.format(TpyInfoConfig.TARGET_FILE_PATH, "反向特派员推荐表", new SimpleDateFormat("yyyy/MM/dd").format(new Date()), exportExcelName);
            data = userToMap.getMapByReverseTpy(user);
        }
        //生成EXCEL,成功返回true，失败返回false
        if (data != null ? ExcelUtils.replaceModel(data, source, target) : false) {
            user.setTpyExcelUrl(target);//将Excel文件目录赋值给user
        }
        return user;
    }

    public User getUser(String id) {
        return userDao.get(id);
    }





    /**
     * 自然人、法人、反向特派员注册
     * 第一次注册执行此方法
     * @param user
     * @return true:注册成功 false:注册失败
     */
    @Transactional(readOnly = false)
    public boolean tpyRegister(User user) {
        try {
            //1、初始化id、create_by、create_date、update_by、update_date
            user.preInsert();
            //2、设置默认属性
            user.setLoginFlag(TpyInfoConfig.LOGIN_FLAG_YES);   //可登录状态
            user.setCheckFlag(TpyInfoConfig.CHECK_FLAG_NO_UPLOADED); //推荐表未上传状态
            //3、密码加密
            user.setPassword(SystemService.entryptPassword(user.getPassword()));
            //4、设置默认新注册特派员角色
            String roleIdList = TpyInfoConfig.ROLE_NEW_REGISTER_TPY;
            //4.1、角色获取
            List<Role> roleList = Lists.newArrayList();
            List<Role> roleList_ = systemService.findTpyRole();
            for (Role r : roleList_){
                if (roleIdList.equals(r.getId())){roleList.add(r);}
            }
            //4.2设置角色列表
            user.setRoleList(roleList);
            //5、设置下派标识
            user.setTpyXpFlag(user.getOffice().getId());
            //6、保存用户信息
            int i = userDao.register(user);
            if(i==1){  //注册成功
                // 更新用户与角色关联
                if (user.getRoleList() != null && user.getRoleList().size() > 0) {
                    userDao.insertUserRole(user);
                } else {
                    throw new ServiceException(user.getLoginName() + "没有设置角色！");
                }
                // 清除用户缓存
                UserUtils.clearCache(user);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            //数据库插入数据异常、注册失败
            return false;
        }
        return false;
    }


    /**
     * 更新用户信息时，验证登录名是否有效
     * @param oldLoginName 更新前登录名
     * @param loginName 更新后登录名
     * @return true：有效  false:无效
     */
    public boolean checkLoginName(String oldLoginName, String loginName) {
        if (loginName != null && loginName.equals(oldLoginName) && UserUtils.getByLoginName(loginName) == null) {
            return true;
        }
        return false;
    }

}
