package com.thinkgem.jeesite.modules.sys.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TpyInfoConfig  {


    //自然人
	public final static String NATURE_PERSON_FLAG="0";
    //法人
	public final static String CORPORATION_PERSON_FLAG="2";
    //普通用户
	public final static String TEMPORARY_PERSON_FLAG="4";
    //反向
	public final static String REVERSE_PERSON_FLAG="5";
    //企业
	public final static String ENTERPRISE_PERSON_FLAG="6";
    //市科技管理员
	public final static String CITY_SCIENCE_ADMIN="a6fa28e8fe4c4e4eb5a541c9b14c6123";
    //县科技管理员
	public final static String COUNTY_SCIENCE_ADMIN="2e4e7026dab34efd98caf7cbc6e9020d";
    //省管理员
	public final static String PROVINCE_SCIENCE_ADMIN="6c097685ad9343d0a3b9b73becd9434f";
    //市科技局特派员
	public final static String CITY_SCIENCE_TPY="030c92ade0f0452eacc4d395f3d29961";
    //县科技局特派员
	public final static String COUNTY_SCIENCE_TPY="7daf4e3fb95449cfa9d5b6ff53b7e28c";
    //省特派员
    public final static String PROVINCE_SCIENCE_TPY="f19d286a9e7c4b7a887823a1d522e504";
    //反向特派员
	public final static String FX_TPY="4d54294fbd694873b19f752cda308f2d";

}