
package com.thinkgem.jeesite.modules.app.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;

/**
 * APP 时间格式化工具
 *
 * 赵凯浩
 * 2017年9月11日 上午11:23:49
 */
public class UtilDate {
	
    /** 年月日时分秒(无下划线) yyyyMMddHHmmss */
    public static final String dtLong                  = "yyyyMMddHHmmss";
    
    /** 完整时间 yyyy-MM-dd HH:mm:ss */
    public static final String simple                  = "yyyy-MM-dd HH:mm:ss";
    
    /** 年月日(无下划线) yyyyMMdd */
    public static final String dtShort                 = "yyyyMMdd";
    
    /** 年月日(有下划线) yyyyMMdd */
    public static final String dt_Short                = "yyyy-MM-dd";
	
    
    /**
     * 返回时间(精确到毫秒)
     * 格式：yyyyMMddHHmmss
     * @param date
     * @return
     */
	public  static String getDtLong(Date date){
		DateFormat df=new SimpleDateFormat(dtLong);
		return df.format(date);
	}
	
	/**
	 * 获取日期(精确到毫秒)
	 * 格式：yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public  static String getSimple(Date date){
		DateFormat df=new SimpleDateFormat(simple);
		return df.format(date);
	}
	
	/**
	 * 获取年月日(精确到天)
	 * 格式：yyyyMMdd
	 * @param date
	 * @return
	 */
	public static String getDtShort(Date date){
		DateFormat df=new SimpleDateFormat(dtShort);
		return df.format(date);
	}
	
	/**
	 * 获取年月日(精确到天)
	 * 格式：yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String getDt_Short(Date date){
		DateFormat df=new SimpleDateFormat(dt_Short);
		return df.format(date);
	}
	
	/**
	 * 产生随机的三位数
	 * @return
	 */
	public static String getThreeRandom(){
		Random rad=new Random();
		return rad.nextInt(1000)+"";
	}
	
	/**
	 * 时间加一天
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static String getTomorrow(String dateStr) throws ParseException{
		// 字符串转为时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date today = sdf.parse(dateStr);
		Format f = new SimpleDateFormat("yyyy-MM-dd");
 
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
 
        Date tomorrow = c.getTime();
        
        return f.format(tomorrow);
	}
	
}
