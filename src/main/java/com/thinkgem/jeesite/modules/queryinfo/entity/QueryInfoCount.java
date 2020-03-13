/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.queryinfo.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.kjtpypt.entity.SysAttachment;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 贫困村服务协议Entity
 * @author Grace
 * @version 2017-08-02
 */
public class QueryInfoCount extends DataEntity<QueryInfoCount> {
	
	private static final long serialVersionUID = 1L;
	private String year;//年份
	private int zrrTpyNum;//自然人特派员数量
	private int frTpyNum;//法人特派员数量
	private int tpyNum;//特派员数量
	private Office office;//office类
	private int january; //1月 数量
	private int february; //2月 数量
	private int march; //3月 数量
	private int april; //4月 数量
	private int may; //5月 数量
	private int june; //6月 数量
	private int july; //7月 数量
	private int august; //8月 数量
	private int september; //9月 数量
	private int october; //10月 数量
	private int november; //11月 数量
	private int december; //12月 数量
	private int total;//全年总数量
	private double january1; //1月 数量(double型)
	private double february1; //2月 数量(double型)
	private double march1; //3月 数量(double型)
	private double april1; //4月 数量(double型)
	private double may1; //5月 数量(double型)
	private double june1; //6月 数量(double型)
	private double july1; //7月 数量(double型)
	private double august1; //8月 数量(double型)
	private double september1; //9月 数量(double型)
	private double october1; //10月 数量(double型)
	private double november1; //11月 数量(double型)
	private double december1; //12月 数量(double型)
	private double total1;//全年总数量(double型)
	private String bfsx; //帮扶属性
	private User user;//用户
	private int count;//统计数
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getJanuary1() {
		return january1;
	}
	public void setJanuary1(double january1) {
		this.january1 = january1;
	}
	public double getFebruary1() {
		return february1;
	}
	public void setFebruary1(double february1) {
		this.february1 = february1;
	}
	public double getMarch1() {
		return march1;
	}
	public void setMarch1(double march1) {
		this.march1 = march1;
	}
	public double getApril1() {
		return april1;
	}
	public void setApril1(double april1) {
		this.april1 = april1;
	}
	public double getMay1() {
		return may1;
	}
	public void setMay1(double may1) {
		this.may1 = may1;
	}
	public double getJune1() {
		return june1;
	}
	public void setJune1(double june1) {
		this.june1 = june1;
	}
	public double getJuly1() {
		return july1;
	}
	public void setJuly1(double july1) {
		this.july1 = july1;
	}
	public double getAugust1() {
		return august1;
	}
	public void setAugust1(double august1) {
		this.august1 = august1;
	}
	public double getSeptember1() {
		return september1;
	}
	public void setSeptember1(double september1) {
		this.september1 = september1;
	}
	public double getOctober1() {
		return october1;
	}
	public void setOctober1(double october1) {
		this.october1 = october1;
	}
	public double getNovember1() {
		return november1;
	}
	public void setNovember1(double november1) {
		this.november1 = november1;
	}
	public double getDecember1() {
		return december1;
	}
	public void setDecember1(double december1) {
		this.december1 = december1;
	}
	public double getTotal1() {
		return total1;
	}
	public void setTotal1(double total1) {
		this.total1 = total1;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getBfsx() {
		return bfsx;
	}
	public void setBfsx(String bfsx) {
		this.bfsx = bfsx;
	}
	public int getJanuary() {
		return january;
	}
	public void setJanuary(int january) {
		this.january = january;
	}
	public int getFebruary() {
		return february;
	}
	public void setFebruary(int february) {
		this.february = february;
	}
	public int getMarch() {
		return march;
	}
	public void setMarch(int march) {
		this.march = march;
	}
	public int getApril() {
		return april;
	}
	public void setApril(int april) {
		this.april = april;
	}
	public int getMay() {
		return may;
	}
	public void setMay(int may) {
		this.may = may;
	}
	public int getJune() {
		return june;
	}
	public void setJune(int june) {
		this.june = june;
	}
	public int getJuly() {
		return july;
	}
	public void setJuly(int july) {
		this.july = july;
	}
	public int getAugust() {
		return august;
	}
	public void setAugust(int august) {
		this.august = august;
	}
	public int getSeptember() {
		return september;
	}
	public void setSeptember(int september) {
		this.september = september;
	}
	public int getOctober() {
		return october;
	}
	public void setOctober(int october) {
		this.october = october;
	}
	public int getNovember() {
		return november;
	}
	public void setNovember(int november) {
		this.november = november;
	}
	public int getDecember() {
		return december;
	}
	public void setDecember(int december) {
		this.december = december;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	public int getZrrTpyNum() {
		return zrrTpyNum;
	}
	public void setZrrTpyNum(int zrrTpyNum) {
		this.zrrTpyNum = zrrTpyNum;
	}
	public int getFrTpyNum() {
		return frTpyNum;
	}
	public void setFrTpyNum(int frTpyNum) {
		this.frTpyNum = frTpyNum;
	}
	public int getTpyNum() {
		return tpyNum;
	}
	public void setTpyNum(int tpyNum) {
		this.tpyNum = tpyNum;
	}
	
	
}