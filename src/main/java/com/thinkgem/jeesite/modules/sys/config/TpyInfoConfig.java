package com.thinkgem.jeesite.modules.sys.config;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 特派员相关数据配置类
 */
@Component
public class TpyInfoConfig implements InitializingBean {

    /**
     * 特派员推荐表模板路径以及EXCEL文件输出路径
     */
    @Value("${sourceFilePath}")
    private String sourceFilePath;
    @Value("${targetFilePath}")
    private String targetFilePath;
    @Value("${imageUrl}")
    private String photoUrl;
    //特派人申报推荐表模板路径
    public static String SOURCE_FILE_PATH;
    //特派人申报推荐表Excel生成文件路径
    public static String TARGET_FILE_PATH;
    //头像路径
    public static String PHOTO_URL;
    @Override
    public void afterPropertiesSet() throws Exception {
        SOURCE_FILE_PATH=sourceFilePath;
        TARGET_FILE_PATH=targetFilePath;
        PHOTO_URL=photoUrl;
    }


    /**
     * 特派员注册默认归属公司(所属机构)
     */
    public final static String COMPANY="08bae2518f1646dfa9e0b6cedf904b54";

    /**
     * 登录状态
     */
    public final static String LOGIN_FLAG_YES="1";  //可登录状态
    public final static String LOGIN_FLAG_NO="0";   //不可登录状态

    /**
     * 审核标识
     */
    public final static String CHECK_FLAG_NOT_EXAMINE="0";  //未审核
    public final static String CHECK_FLAG_NO_PASS="1";   //审核未通过
    public final static String CHECK_FLAG_PASS="2";   //审核通过
    public final static String CHECK_FLAG_NO_UPLOADED="3";   //注册后未上传推荐表

    /**
     * 特派员身份类型
     */
    //自然人
	public final static String PERSON_FLAG_NATURE="0";
    //服务对象
    public final static String PERSON_FLAG_SERVER="1";
    //法人
	public final static String PERSON_FLAG_CORPORATION="2";
    //普通用户
	public final static String PERSON_FLAG_TEMPORARY="4";
    //反向
	public final static String PERSON_FLAG_REVERSE="5";
    //企业
	public final static String PERSON_FLAG_ENTERPRISE="6";


    /**
     * 特派员相关角色分类
     */
    //新注册特派员(默认角色)
    public final static String ROLE_NEW_REGISTER_TPY="3bb6453c699d49508b15529670ad9e9b";
    //市科技管理员
	public final static String ROLE_CITY_SCIENCE_ADMIN="a6fa28e8fe4c4e4eb5a541c9b14c6123";
    //县科技管理员
	public final static String ROLE_COUNTY_SCIENCE_ADMIN="2e4e7026dab34efd98caf7cbc6e9020d";
    //省科技局管理员
	public final static String ROLE_PROVINCE_SCIENCE_ADMIN="6c097685ad9343d0a3b9b73becd9434f";
    //市科技局特派员
	public final static String ROLE_CITY_SCIENCE_TPY="030c92ade0f0452eacc4d395f3d29961";
    //县科技局特派员
	public final static String ROLE_COUNTY_SCIENCE_TPY="7daf4e3fb95449cfa9d5b6ff53b7e28c";
    //省科技局特派员
    public final static String ROLE_PROVINCE_SCIENCE_TPY="f19d286a9e7c4b7a887823a1d522e504";
    //反向特派员
	public final static String ROLE_FX_TPY="4d54294fbd694873b19f752cda308f2d";  //TODO 待修改



}