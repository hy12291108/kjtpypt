/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.service;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.thinkgem.jeesite.common.utils.excel.ExcelUtils;
import com.thinkgem.jeesite.modules.sys.dao.*;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.wrap.UserToMap;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.security.Digests;
import com.thinkgem.jeesite.common.security.shiro.session.SessionDAO;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.service.ServiceException;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.Servlets;
import com.thinkgem.jeesite.modules.sys.entity.Menu;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.TpyInfo;
import com.thinkgem.jeesite.modules.sys.entity.TpyWorkExperience;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.security.SystemAuthorizingRealm;
import com.thinkgem.jeesite.modules.sys.utils.LogUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 系统管理，安全相关实体的管理类,包括用户、角色、菜单、注册管理.
 *
 * @author ThinkGem
 * @version 2013-12-05
 */
@Service
@Transactional(readOnly = true)
public class SystemService extends BaseService implements InitializingBean {

    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;

    //特派人申报推荐表模板路径
    @Value("${sourceFilePath}")
    private String sourceFilePath;
    //特派人申报推荐表Excel生成文件路径
    @Value("${targetFilePath}")
    private String targetFilePath;
    @Autowired
    private UserToMap userToMap;

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private SessionDAO sessionDao;
    @Autowired
    private SystemAuthorizingRealm systemRealm;

    @Autowired
    private TpyInfoDao tpyInfoDao;
    @Autowired
    private OfficeService officeService;
    @Autowired
    private TpyWorkExperienceDao tpyWorkExperienceDao;

    public SessionDAO getSessionDao() {
        return sessionDao;
    }

    @Autowired
    private IdentityService identityService;


    @Transactional(readOnly = false)
    public int updateZjFlag(User user) {
        System.out.println(userDao.updateZjFlag(user));
        return userDao.updateZjFlag(user);
    }

    /*
     * @author lg
     * 特派员下派
     * 20170919
     *
     */
    @Transactional(readOnly = false)
    public boolean updateXp(String id, String areaId, String time, String startTime, String endTime) {
        if (areaId.equals("") || areaId == null) {
            return false;
        }
        try {
            Office office = officeService.findOffice(areaId);
            User user = userDao.get(id);
            user.setTpyXpFlag(office.getId());
            user.setXpTime(time);
            user.setXpStartTime(startTime);
            user.setXpEndTime(endTime);
            user.setXpNd(time.substring(0, 4));
            user.setTpyXpFlagName(office.getName());
            userDao.updateXp(user);
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    //-- User Service --//
    /*
     * 更新特派员工作经历（单条）
     * @author刘钢
     * 20170911
     */
    @Transactional(readOnly = false)
    public int updateExperience(TpyWorkExperience experience) {
        experience.preUpdate();
        int i = tpyWorkExperienceDao.update(experience);
        System.out.println(i);
        return i;
    }


    /*
     * 增加特派员工作经历（单条）
     * @author刘钢
     * 20170911
     */
    @Transactional(readOnly = false)
    public int addExperience(TpyWorkExperience experience) {
        experience.preInsert();
        int i = tpyWorkExperienceDao.insert(experience);
        System.out.println(i);
        return i;
    }


    /*
     * 查找特派员工作经历
     * @author刘钢
     * 20170911
     */
    public TpyWorkExperience getWorkExperience(String id) {
        TpyWorkExperience tpyWorkExperience = tpyWorkExperienceDao.get(id);
        if (tpyWorkExperience != null) {
            tpyWorkExperience.setTpyWorkDate(tpyWorkExperience.getTpyWorkDate().substring(0, 10));
            tpyWorkExperience.setTpyLeaveDate(tpyWorkExperience.getTpyLeaveDate().substring(0, 10));
        }
        return tpyWorkExperience;
    }


    /*
     * 查找特派员工作经历
     * @author刘钢
     * 20170911
     *
     */
    public List<TpyWorkExperience> findWork(String id) {
        List<TpyWorkExperience> experience = tpyWorkExperienceDao.findWork(id);
        if (experience != null) {
            for (int i = 0; i < experience.size(); i++) {
                experience.get(i).setTpyWorkDate(experience.get(i).getTpyWorkDate().substring(0, 10));
                experience.get(i).setTpyLeaveDate(experience.get(i).getTpyLeaveDate().substring(0, 10));
            }
        }
        return experience;
    }

    /*
     * 删除特派员工作经历（单条）
     * @author刘钢
     * 20170911
     */
    @Transactional(readOnly = false)
    public int deleteExperience(TpyWorkExperience experience) {
        int i = tpyWorkExperienceDao.delete(experience);
        System.out.println(i);
        return i;
    }

    /*
     * 特派员注册获取角色列表
     * @author刘钢
     * 20170727
     */
    public List<Role> findTpyRole() {
        List<Role> role = UserUtils.getTpyRoleList();
        List<Role> role1 = new ArrayList<Role>();
        for (int i = 0; i < role.size(); i++) {
            if (role.get(i).getId().equals("3bb6453c699d49508b15529670ad9e9b")) {
                role1.add(role.get(i));
            }
        }
        return role1;
    }

    /*
     * 需求单位注册获取角色列表
     * @author刘钢
     * 20170801
     */
    public List<Role> findXdRole() {
        List<Role> role = UserUtils.getTpyRoleList();
        List<Role> role1 = new ArrayList<Role>();
        for (int i = 0; i < role.size(); i++) {
            if (role.get(i).getId().equals("abf520ca23614c0da7dfd4057485f6ed")) {
                role1.add(role.get(i));
            }
        }
        return role1;
    }


    /**
     * 反向特派员注册获取角色列表
     */
    public List<Role> findFxRole() {
        List<Role> role = UserUtils.getTpyRoleList();
        List<Role> role1 = new ArrayList<Role>();
        for (int i = 0; i < role.size(); i++) {
            if (role.get(i).getId().equals("4d54294fbd694873b19f752cda308f2d")) { //TODO 待修改
                role1.add(role.get(i));
            }
        }
        return role1;
    }

    /*临时用户登录
     *@author 刘钢
     *20170727
     */
    @Transactional(readOnly = false)
    public boolean temporaryLogin(User user, String password) {
        //明文 密文
        boolean flag = SystemService.validatePassword(password, user.getPassword());
        System.out.println(flag);
        return flag;
    }

    /*临时用户注册
     *@author 刘钢
     *20171101
     */
    @Transactional(readOnly = false)
    public String tempUserSave(User user) {
        user.preInsert();
        if(userDao.insertTempUser(user)==1){//注册成功
            // 清除用户缓存
            UserUtils.clearCache(user);
            return "1";
        }
        return "0";
    }


    /*用户注册
     *@author 刘钢
     *20170727
     */
    @Transactional(readOnly = false)
    public String saveUser1(User user) {

        /**
         * 注册或者更新用户时生成特派员推荐表Excel
         * 成功将Excel文件目录赋值给user
         */
        user = this.userChangExcel(user);

        String flag;
        if (StringUtils.isBlank(user.getId())) {
            flag = "0";
            user.preInsert();
            //设置下派标识
            user.setTpyXpFlag(user.getOffice().getId());
            userDao.insert(user);
        } else {
            flag = "1";
            // 清除原用户机构用户缓存
            User oldUser = userDao.get(user.getId());
            if (oldUser.getOffice() != null && oldUser.getOffice().getId() != null) {
                CacheUtils.remove(UserUtils.USER_CACHE, UserUtils.USER_CACHE_LIST_BY_OFFICE_ID_ + oldUser.getOffice().getId());
            }
            // 更新用户数据
            user.preUpdate();
            if (user.getPersonFlag().equals("0")) {
                userDao.updatetpy(user);  //自然人特派员更新
            } else if (user.getPersonFlag().equals("2")) {
                userDao.updatetpyCorp(user); //法人特派员更新
            } else if (user.getPersonFlag().equals("1")) {
                userDao.updateServiceObject(user);//需求单位特派员更新
            } else if (user.getPersonFlag().equals("5")) {  //TODO 添加反派特派员更新（目前没写）
                userDao.updatetpy(user);//反向特派员更新
            } else if (user.getPersonFlag().equals("6")) { //TODO  添加企业用户更新（目前没写）
                userDao.updatetpy(user);//企业更新
            }
        }
        if (StringUtils.isNotBlank(user.getId())) {
            // 更新用户与角色关联
            userDao.deleteUserRole(user);
            if (user.getRoleList() != null && user.getRoleList().size() > 0) {
                userDao.insertUserRole(user);
            } else {
                throw new ServiceException(user.getLoginName() + "没有设置角色！");
            }
            // 清除用户缓存
            UserUtils.clearCache(user);
        }
        return flag;
    }

    /**
     * 注册用户时生成特派员推荐表Excel
     *
     * @param user
     * @return
     */
    private User userChangExcel(User user) {
        //生成的Excel文件名
        String exportExcelName = UUID.randomUUID() + ".xls";
        //文档数据
        Map<String, String> data = null;
        //模板路径
        String source = null;
        //生成路径
        String target = null;
        if (user.getPersonFlag().equals("0")) {//自然人特派员生成Excel
            source = String.format(sourceFilePath, "自然人特派员推荐表模板.xls");
            target = String.format(targetFilePath, "自然人特派员推荐表", new SimpleDateFormat("yyyy/MM/dd").format(new Date()), exportExcelName);
            data = userToMap.getMapByNatureTpy(user);
        } else if (user.getPersonFlag().equals("2")) {//法人特派员生成Excel
            source = String.format(sourceFilePath, "法人特派员推荐表模板.xls");
            target = String.format(targetFilePath, "法人特派员推荐表", new SimpleDateFormat("yyyy/MM/dd").format(new Date()), exportExcelName);
            data = userToMap.getMapByCorporationTpy(user);
        } else if (user.getPersonFlag().equals("5")) { //反向特派员生成Excel
            source = String.format(sourceFilePath, "反向特派员推荐表模板.xls");
            target = String.format(targetFilePath, "反向特派员推荐表", new SimpleDateFormat("yyyy/MM/dd").format(new Date()), exportExcelName);
            data = userToMap.getMapByReverseTpy(user);
        }
        //生成EXCEL,成功返回true，失败返回false
        if (data != null ? ExcelUtils.replaceModel(data, source, target) : false) {
            user.setTpyExcelUrl(target);//将Excel文件目录赋值给user
        }
        return user;
    }

    /*
     * 更新特派员审核结果
     * @author 刘钢
     * 20170803
     */
    @Transactional(readOnly = false)
    public void updateTpyShResultById(String id, User user) {
        Role role = new Role();
        user.preUpdate();
        userDao.updateShResultById(user);
        if (StringUtils.isNotBlank(user.getId()) && user.getCheckFlag().equals("2")) {
            // 更新用户与角色关联
            userDao.deleteUserRole(user);
            //User currentUser = UserUtils.getUser();
            String roleId = userDao.findRoleList(id);
            System.out.println(roleId);
            if (roleId != null) {
                if (roleId.equals("a6fa28e8fe4c4e4eb5a541c9b14c6123")) { //市科技局管理员
                    role.setId("030c92ade0f0452eacc4d395f3d29961");
                } else if (roleId.equals("2e4e7026dab34efd98caf7cbc6e9020d")) { //县科技局管理员
                    role.setId("7daf4e3fb95449cfa9d5b6ff53b7e28c");
                }else if (roleId.equals("236ed64260ef445cb6eaa27944df678b")){ //反向特派员管理员  //TODO 待修改
                    role.setId("4d54294fbd694873b19f752cda308f2d");//反向特派员 //TODO 待修改
                }else {  //省管理员
                    role.setId("f19d286a9e7c4b7a887823a1d522e504");
                }
                user.setRole(role);
                List<Role> roleList = new ArrayList<Role>();
                roleList.add(role);
                user.setRoleList(roleList);
                userDao.insertUserRole(user);
                // 清除用户缓存
                UserUtils.clearCache(user);
            }
        }
        CacheUtils.remove(user.getLoginName());
    }

    /*
     * 更新需求单位审核结果
     * @author 刘钢
     * 20170804
     */
    @Transactional(readOnly = false)
    public void updateXdShResultById(String id, User user) {
        Role role = new Role();
        user.preUpdate();
        userDao.updateShResultById(user);
        if (StringUtils.isNotBlank(user.getId()) && user.getCheckFlag().equals("2")) {
            // 更新用户与角色关联
            userDao.deleteUserRole(user);
            //User currentUser = UserUtils.getUser();
            String roleId = userDao.findRoleList(id);
            if (roleId != null) {
                //判断是否为市管理员
                if (roleId.equals("a6fa28e8fe4c4e4eb5a541c9b14c6123")) {
                    //設置需求單位（省、市、縣）角色
                    role.setId("e98ce06c8b154a1f86d7157b2516bd03");
                    //判断是否为县管理员
                } else if (roleId.equals("2e4e7026dab34efd98caf7cbc6e9020d")) {
                    role.setId("3861d5290ef249558419d928d1f2657b");
                } else {
                    role.setId("de3b6c4b0ae4492c8772da19376b586c");
                }
                user.setRole(role);
                List<Role> roleList = new ArrayList<Role>();
                roleList.add(role);
                user.setRoleList(roleList);
                userDao.insertUserRole(user);
                // 清除用户缓存
                UserUtils.clearCache(user);
            }
        }
        CacheUtils.remove(user.getLoginName());
    }

    /*
     * 根据officeId获取单位列表
     */
    public List<String> corpList(String officeId) {
        List<String> corpList = userDao.getCorpList(officeId);
        if (corpList != null && !(corpList.isEmpty())) {
            return corpList;
        }
        corpList.add("查无数据");
        return corpList;
    }


    /*
     * 特派员基本信息 保存/修改
     * @author 刘钢 20170908
     */
    @Transactional(readOnly = false)
    public void tjTableSaveOrUpDate(User user, TpyInfo tpyInfo, TpyWorkExperience tpyWorkExperience) {
        if (StringUtils.isBlank(tpyInfo.getId())) {
            tpyInfo.setTpyId(user.getId());
            tpyInfo.preInsert();
            tpyInfoDao.insert(tpyInfo);
            System.out.println(tpyWorkExperience.getTpyCorpName());
            //特派员经历
            if (!user.getPersonFlag().equals("2")) {
                if (!(tpyWorkExperience.getTpyCorpName().equals(""))) {
                    String[] tpyCorpName = tpyWorkExperience.getTpyCorpName().split(",");
                    String[] tpyWork = tpyWorkExperience.getTpyWork().split(",");
                    String[] tpyWorkDate = tpyWorkExperience.getTpyWorkDate().split(",");
                    String[] tpyLeaveDate = tpyWorkExperience.getTpyLeaveDate().split(",");
                    for (int i = 0; i < tpyCorpName.length; i++) {
                        tpyWorkExperience.preInsert();
                        tpyWorkExperience.setTpyCorpName(tpyCorpName[i]);
                        tpyWorkExperience.setTpyWork(tpyWork[i]);
                        tpyWorkExperience.setTpyWorkDate(tpyWorkDate[i]);
                        tpyWorkExperience.setTpyLeaveDate(tpyLeaveDate[i]);
                        tpyWorkExperience.setTpyInfoId(tpyInfo.getId());
                        tpyWorkExperienceDao.insert(tpyWorkExperience);
                    }
                }
            }
        } else {
            //更新基本信息
            tpyInfo.preUpdate();
            tpyInfoDao.updateBaseInfo(tpyInfo);
        }
    }

    /**
     * 获取用户
     *
     * @param id
     * @return
     */
    public User getUser(String id) {
        User user = UserUtils.get(id);
        if (user != null) {
            if (user.getTpyBeginWorkDate() != null) {
                if (user.getTpyBeginWorkDate() != "") {
                    user.setTpyBeginWorkDate(user.getTpyBeginWorkDate().substring(0, 10));
                }

            }
        }
        return user;
    }

    /*
     * 获取特派员基本信息 根据tpyInfo的id
     * @author 刘钢
     * 20170907
     */
    public TpyInfo getTpyInfo(String id) {
        return tpyInfoDao.getInfo(id);
    }

    /*
     * 获取特派员基本信息 根据user的id
     * @author 刘钢
     * 20170907
     */
    public TpyInfo getTpyInfos(String id) {
        return tpyInfoDao.get(id);
    }

    /**
     * 根据登录名获取用户
     *
     * @param loginName
     * @return
     */
    public User getUserByLoginName(String loginName) {
        return UserUtils.getByLoginName(loginName);
    }

    /*
     * 管理员查询特派员信息
     * 刘钢
     * 20170904
     */
    public Page<User> findListByAdmin(Page<User> page, User user) {
        // 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
        //user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
        user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
        user.setCheckFlag("2");
        user.setPage(page);
        page.setList(userDao.findListByAdmin(user));
        return page;
    }


    /*
     * 分页查询备选专家的特派员
     * 刘钢
     * 20170904
     */
    public Page<User> findZjSelectList(Page<User> page, User user) {
        user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
        user.setCheckFlag("2");
        // 设置分页参数
        user.setPage(page);
        // 执行分页查询
        page.setList(userDao.findZjSelectList(user));
        return page;
    }


    /*
     * 分页查询特派员信息
     * 刘钢
     * 20170904
     */
    public Page<User> findUser(Page<User> page, User user) {
        // 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
        //user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
		/*boolean flag = UserUtils.getUser().isAdmin();
		if(flag){
			user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
			user.setPage(page);
			page.setList(userDao.findListByAdmin(user));
			return page;
		}else{*/
        //查询某科技局注册的特派员
        Office office = new Office();
        office.setId(UserUtils.getUser().getOffice().getId());
        user.setOffice(office);
        // 设置分页参数
        user.setPage(page);
        // 执行分页查询
        page.setList(userDao.findList(user));
        return page;//}
    }

    /*
     * 分页查询未下派特派员信息
     * 刘钢
     * 20170904
     */
    public Page<User> findXpTpyUser(Page<User> page, User user) {
        // 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
        //user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
        //DateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //查询某科技局注册的特派员
        Office office = new Office();
        office.setId(UserUtils.getUser().getOffice().getId());
        user.setOffice(office);
        // 设置分页参数
        user.setPage(page);
        //判断是否下派
        List<User> xpTpy = userDao.findList(user);
        if (xpTpy != null) {
            for (int i = 0; i < xpTpy.size(); i++) {
                if (xpTpy.get(i).getXpEndTime() != null) {
                    int j = user.getXpTime().compareTo(xpTpy.get(i).getXpEndTime().substring(0, 10));
                    if (j == 0 || j < 0) {
                        xpTpy.remove(i);
                        Long pageCount = page.getCount() - 1l;
                        page.setCount(pageCount);
                    }
                }
            }
        }
        // 设置分页参数
        user.setPage(page);
        // 执行分页查询
        page.setList(xpTpy);
        return page;
    }

    /*
     * 分页查询已下派特派员信息
     * 刘钢
     * 20170926
     */
    public Page<User> findXpUser(Page<User> page, User user) {
        // 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
        //user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
        //查询某科技局注册的特派员
        SimpleDateFormat sfnd = new SimpleDateFormat("yyyy");
        String xpNd = sfnd.format(new Date());
        System.out.println("xpNd=" + xpNd);
        Office office = new Office();
        office.setId(UserUtils.getUser().getOffice().getId());
        user.setOffice(office);
        // 设置分页参数
        user.setPage(page);
        user.setXpNd(xpNd);
        // 执行分页查询
        page.setList(userDao.findXpList(user));
        return page;
    }


    /*
     * 分页查询需求单位信息
     * 刘钢
     * 20170906
     */
    public Page<User> findXdList(Page<User> page, User user) {
        // 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
        //user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
        //查询某科技局注册的需求单位
        Office office = new Office();
        office.setId(UserUtils.getUser().getOffice().getId());
        user.setOffice(office);
        // 设置分页参数
        user.setPage(page);
        // 执行分页查询
        page.setList(userDao.findXdList(user));
        return page;
    }


    /*
     * 分页查询审核通过特派员信息
     * 刘钢
     * 20170904
     */
    public Page<User> findPassOrNo(Page<User> page, User user) {
        // 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
        //user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
        //查询某科技局注册的特派员
        Office office = new Office();
        office.setId(UserUtils.getUser().getOffice().getId());
        user.setOffice(office);
        // 设置分页参数
        user.setPage(page);
        // 执行分页查询
        page.setList(userDao.findList(user));
        return page;
    }


    /*
     * 分页查询审核通过需求单位信息
     * 刘钢
     * 20170906
     */
    public Page<User> findXdPass(Page<User> page, User user) {
        // 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
        //user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
        //查询某科技局注册的需求单位
        Office office = new Office();
        office.setId(UserUtils.getUser().getOffice().getId());
        user.setOffice(office);
        // 设置分页参数
        user.setPage(page);
        // 执行分页查询
        page.setList(userDao.findXdList(user));
        return page;
    }


    /**
     * 无分页查询人员列表
     *
     * @param user
     * @return
     */
    public List<User> findUser(User user) {
        // 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）
        user.getSqlMap().put("dsf", dataScopeFilter(user.getCurrentUser(), "o", "a"));
        List<User> list = userDao.findList(user);
        return list;
    }

    /**
     * 通过部门ID获取用户列表，仅返回用户id和name（树查询用户时用）
     *
     * @param
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<User> findUserByOfficeId(String officeId) {
        List<User> list = (List<User>) CacheUtils.get(UserUtils.USER_CACHE, UserUtils.USER_CACHE_LIST_BY_OFFICE_ID_ + officeId);
        if (list == null) {
            User user = new User();
            user.setOffice(new Office(officeId));
            list = userDao.findUserByOfficeId(user);
            CacheUtils.put(UserUtils.USER_CACHE, UserUtils.USER_CACHE_LIST_BY_OFFICE_ID_ + officeId, list);
        }
        return list;
    }

    @Transactional(readOnly = false)
    public void saveUser(User user) {
        if (StringUtils.isBlank(user.getId())) {
            user.preInsert();
            userDao.insert(user);
        } else {
            // 清除原用户机构用户缓存
            User oldUser = userDao.get(user.getId());
            if (oldUser.getOffice() != null && oldUser.getOffice().getId() != null) {
                CacheUtils.remove(UserUtils.USER_CACHE, UserUtils.USER_CACHE_LIST_BY_OFFICE_ID_ + oldUser.getOffice().getId());
            }
            // 更新用户数据
            user.preUpdate();
            userDao.update(user);
        }
        if (StringUtils.isNotBlank(user.getId())) {
            // 更新用户与角色关联
            userDao.deleteUserRole(user);
            if (user.getRoleList() != null && user.getRoleList().size() > 0) {
                userDao.insertUserRole(user);
            } else {
                throw new ServiceException(user.getLoginName() + "没有设置角色！");
            }
            // 将当前用户同步到Activiti
            saveActivitiUser(user);
            // 清除用户缓存
            UserUtils.clearCache(user);
            // 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
        }
    }

    @Transactional(readOnly = false)
    public void updateUserInfo(User user) {
        user.preUpdate();
        userDao.updateUserInfo(user);
        // 清除用户缓存
        UserUtils.clearCache(user);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
    }

    @Transactional(readOnly = false)
    public void deleteUser(User user) {
        userDao.delete(user);
        // 同步到Activiti
        deleteActivitiUser(user);
        // 清除用户缓存
        UserUtils.clearCache(user);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
    }

    @Transactional(readOnly = false)
    public void updatePasswordById(String id, String loginName, String newPassword) {
        User user = new User(id);
        user.setPassword(entryptPassword(newPassword));
        userDao.updatePasswordById(user);
        // 清除用户缓存
        user.setLoginName(loginName);
        UserUtils.clearCache(user);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
    }

    @Transactional(readOnly = false)
    public void updateUserLoginInfo(User user) {
        // 保存上次登录信息
        user.setOldLoginIp(user.getLoginIp());
        user.setOldLoginDate(user.getLoginDate());
        // 更新本次登录信息
        user.setLoginIp(StringUtils.getRemoteAddr(Servlets.getRequest()));
        user.setLoginDate(new Date());
        userDao.updateLoginInfo(user);
    }

    /**
     * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
     */
    public static String entryptPassword(String plainPassword) {
        String plain = Encodes.unescapeHtml(plainPassword);
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
        return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
    }

    /**
     * 验证密码
     *
     * @param plainPassword 明文密码
     * @param password      密文密码
     * @return 验证成功返回true
     */
    public static boolean validatePassword(String plainPassword, String password) {
        String plain = Encodes.unescapeHtml(plainPassword);
        byte[] salt = Encodes.decodeHex(password.substring(0, 16));
        byte[] hashPassword = Digests.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
        return password.equals(Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
    }

    /**
     * 获得活动会话
     *
     * @return
     */
    public Collection<Session> getActiveSessions() {
        return sessionDao.getActiveSessions(false);
    }

    //-- Role Service --//

    public Role getRole(String id) {
        return roleDao.get(id);
    }

    public List<Role> findRolesByUserId(String userId) {
        return roleDao.findRolesByUserId(userId);
    }

    public Role getRoleByName(String name) {
        Role r = new Role();
        r.setName(name);
        return roleDao.getByName(r);
    }

    public Role getRoleByEnname(String enname) {
        Role r = new Role();
        r.setEnname(enname);
        return roleDao.getByEnname(r);
    }

    public List<Role> findRole(Role role) {
        return roleDao.findList(role);
    }

    public List<Role> findAllRole() {
        return UserUtils.getRoleList();
    }

    @Transactional(readOnly = false)
    public void saveRole(Role role) {
        if (StringUtils.isBlank(role.getId())) {
            role.preInsert();
            roleDao.insert(role);
            // 同步到Activiti
            saveActivitiGroup(role);
        } else {
            role.preUpdate();
            roleDao.update(role);
        }
        // 更新角色与菜单关联
        roleDao.deleteRoleMenu(role);
        if (role.getMenuList().size() > 0) {
            roleDao.insertRoleMenu(role);
        }
        // 更新角色与部门关联
        roleDao.deleteRoleOffice(role);
        if (role.getOfficeList().size() > 0) {
            roleDao.insertRoleOffice(role);
        }
        // 同步到Activiti
        saveActivitiGroup(role);
        // 清除用户角色缓存
        UserUtils.removeCache(UserUtils.CACHE_ROLE_LIST);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
    }

    @Transactional(readOnly = false)
    public void deleteRole(Role role) {
        roleDao.delete(role);
        // 同步到Activiti
        deleteActivitiGroup(role);
        // 清除用户角色缓存
        UserUtils.removeCache(UserUtils.CACHE_ROLE_LIST);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
    }

    @Transactional(readOnly = false)
    public Boolean outUserInRole(Role role, User user) {
        List<Role> roles = user.getRoleList();
        for (Role e : roles) {
            if (e.getId().equals(role.getId())) {
                roles.remove(e);
                saveUser(user);
                return true;
            }
        }
        return false;
    }

    @Transactional(readOnly = false)
    public User assignUserToRole(Role role, User user) {
        if (user == null) {
            return null;
        }
        List<String> roleIds = user.getRoleIdList();
        if (roleIds.contains(role.getId())) {
            return null;
        }
        user.getRoleList().add(role);
        saveUser(user);
        return user;
    }

    //-- Menu Service --//

    public Menu getMenu(String id) {
        return menuDao.get(id);
    }

    public List<Menu> findAllMenu() {
        return UserUtils.getMenuList();
    }

    @Transactional(readOnly = false)
    public void saveMenu(Menu menu) {

        // 获取父节点实体
        menu.setParent(this.getMenu(menu.getParent().getId()));

        // 获取修改前的parentIds，用于更新子节点的parentIds
        String oldParentIds = menu.getParentIds();

        // 设置新的父节点串
        menu.setParentIds(menu.getParent().getParentIds() + menu.getParent().getId() + ",");

        // 保存或更新实体
        if (StringUtils.isBlank(menu.getId())) {
            menu.preInsert();
            menuDao.insert(menu);
        } else {
            menu.preUpdate();
            menuDao.update(menu);
        }

        // 更新子节点 parentIds
        Menu m = new Menu();
        m.setParentIds("%," + menu.getId() + ",%");
        List<Menu> list = menuDao.findByParentIdsLike(m);
        for (Menu e : list) {
            e.setParentIds(e.getParentIds().replace(oldParentIds, menu.getParentIds()));
            menuDao.updateParentIds(e);
        }
        // 清除用户菜单缓存
        UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
        // 清除日志相关缓存
        CacheUtils.remove(LogUtils.CACHE_MENU_NAME_PATH_MAP);
    }

    @Transactional(readOnly = false)
    public void updateMenuSort(Menu menu) {
        menuDao.updateSort(menu);
        // 清除用户菜单缓存
        UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
        // 清除日志相关缓存
        CacheUtils.remove(LogUtils.CACHE_MENU_NAME_PATH_MAP);
    }

    @Transactional(readOnly = false)
    public void deleteMenu(Menu menu) {
        menuDao.delete(menu);
        // 清除用户菜单缓存
        UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
//		// 清除权限缓存
//		systemRealm.clearAllCachedAuthorizationInfo();
        // 清除日志相关缓存
        CacheUtils.remove(LogUtils.CACHE_MENU_NAME_PATH_MAP);
    }

    /**
     * 获取Key加载信息
     */
    public static boolean printKeyLoadMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n======================================================================\r\n");
        sb.append("\r\n    欢迎使用 " + Global.getConfig("productName") + "  - Powered By 协同数码股份有限公司\r\n");
        sb.append("\r\n======================================================================\r\n");
        System.out.println(sb.toString());
        return true;
    }

    ///////////////// Synchronized to the Activiti //////////////////

    // 已废弃，同步见：ActGroupEntityServiceFactory.java、ActUserEntityServiceFactory.java

    /**
     * 是需要同步Activiti数据，如果从未同步过，则同步数据。
     */
    private static boolean isSynActivitiIndetity = true;

    public void afterPropertiesSet() throws Exception {
        if (!Global.isSynActivitiIndetity()) {
            return;
        }
        if (isSynActivitiIndetity) {
            isSynActivitiIndetity = false;
            // 同步角色数据
            List<Group> groupList = identityService.createGroupQuery().list();
            if (groupList.size() == 0) {
                Iterator<Role> roles = roleDao.findAllList(new Role()).iterator();
                while (roles.hasNext()) {
                    Role role = roles.next();
                    saveActivitiGroup(role);
                }
            }
            // 同步用户数据
            List<org.activiti.engine.identity.User> userList = identityService.createUserQuery().list();
            if (userList.size() == 0) {
                Iterator<User> users = userDao.findAllList(new User()).iterator();
                while (users.hasNext()) {
                    saveActivitiUser(users.next());
                }
            }
        }
    }

    private void saveActivitiGroup(Role role) {
        if (!Global.isSynActivitiIndetity()) {
            return;
        }
        String groupId = role.getEnname();

        // 如果修改了英文名，则删除原Activiti角色
        if (StringUtils.isNotBlank(role.getOldEnname()) && !role.getOldEnname().equals(role.getEnname())) {
            identityService.deleteGroup(role.getOldEnname());
        }

        Group group = identityService.createGroupQuery().groupId(groupId).singleResult();
        if (group == null) {
            group = identityService.newGroup(groupId);
        }
        group.setName(role.getName());
        group.setType(role.getRoleType());
        identityService.saveGroup(group);

        // 删除用户与用户组关系
        List<org.activiti.engine.identity.User> activitiUserList = identityService.createUserQuery().memberOfGroup(groupId).list();
        for (org.activiti.engine.identity.User activitiUser : activitiUserList) {
            identityService.deleteMembership(activitiUser.getId(), groupId);
        }

        // 创建用户与用户组关系
        List<User> userList = findUser(new User(new Role(role.getId())));
        for (User e : userList) {
            String userId = e.getLoginName();//ObjectUtils.toString(user.getId());
            // 如果该用户不存在，则创建一个
            org.activiti.engine.identity.User activitiUser = identityService.createUserQuery().userId(userId).singleResult();
            if (activitiUser == null) {
                activitiUser = identityService.newUser(userId);
                activitiUser.setFirstName(e.getName());
                activitiUser.setLastName(StringUtils.EMPTY);
                activitiUser.setEmail(e.getEmail());
                activitiUser.setPassword(StringUtils.EMPTY);
                identityService.saveUser(activitiUser);
            }
            identityService.createMembership(userId, groupId);
        }
    }

    public void deleteActivitiGroup(Role role) {
        if (!Global.isSynActivitiIndetity()) {
            return;
        }
        if (role != null) {
            String groupId = role.getEnname();
            identityService.deleteGroup(groupId);
        }
    }

    private void saveActivitiUser(User user) {
        if (!Global.isSynActivitiIndetity()) {
            return;
        }
        String userId = user.getLoginName();//ObjectUtils.toString(user.getId());
        org.activiti.engine.identity.User activitiUser = identityService.createUserQuery().userId(userId).singleResult();
        if (activitiUser == null) {
            activitiUser = identityService.newUser(userId);
        }
        activitiUser.setFirstName(user.getName());
        activitiUser.setLastName(StringUtils.EMPTY);
        activitiUser.setEmail(user.getEmail());
        activitiUser.setPassword(StringUtils.EMPTY);
        identityService.saveUser(activitiUser);

        // 删除用户与用户组关系
        List<Group> activitiGroups = identityService.createGroupQuery().groupMember(userId).list();
        for (Group group : activitiGroups) {
            identityService.deleteMembership(userId, group.getId());
        }
        // 创建用户与用户组关系
        for (Role role : user.getRoleList()) {
            String groupId = role.getEnname();
            // 如果该用户组不存在，则创建一个
            Group group = identityService.createGroupQuery().groupId(groupId).singleResult();
            if (group == null) {
                group = identityService.newGroup(groupId);
                group.setName(role.getName());
                group.setType(role.getRoleType());
                identityService.saveGroup(group);
            }
            identityService.createMembership(userId, role.getEnname());
        }
    }

    private void deleteActivitiUser(User user) {
        if (!Global.isSynActivitiIndetity()) {
            return;
        }
        if (user != null) {
            String userId = user.getLoginName();//ObjectUtils.toString(user.getId());
            identityService.deleteUser(userId);
        }
    }

    ///////////////// Synchronized to the Activiti end //////////////////

}
