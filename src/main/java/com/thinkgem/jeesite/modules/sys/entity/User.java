/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.supcan.annotation.treelist.cols.SupCol;
import com.thinkgem.jeesite.common.utils.Collections3;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.common.utils.excel.fieldtype.RoleListType;
import com.thinkgem.jeesite.modules.sqtpy.entity.Sqtpy;

/**
 * 用户Entity
 * @author ThinkGem
 * @version 2013-12-05
 */
public class User extends DataEntity<User> {

	private static final long serialVersionUID = 1L;
	private Office company;	// 归属公司
	private Office office;	// 归属部门
	private Sqtpy sqtpy; //帮扶关系类
	private String officeName; //归属部门名称
	private String loginName;// 登录名
	private String password;// 密码
	private String no;		// 工号
	@NotNull @NotEmpty
	private String name;	// 特派员姓名/需求单位名称
	@Email
	private String email;	// 邮箱
	private String phone;	// 电话
	private String mobile;	// 特派员手机号码/法人联系号码
	private String userType;// 用户类型 
	private String loginIp;	// 最后登陆IP
	private Date loginDate;	// 最后登陆日期
	private String loginFlag;	// 是否允许登陆
	private String photo;	// 头像

	private String oldLoginName;// 原登录名
	private String newPassword;	// 新密码
	
	private String oldLoginIp;	// 上次登陆IP
	private Date oldLoginDate;	// 上次登陆日期
	
	private Role role;	// 根据角色查询用户条件
	//特派员参数
	private String sex;//性别
	private String tpyIdcard;//身份证号
	private String tpyBirthDate;//出生日期
	private String tpyTitle;//职称
	private String tpyQulification;//学历
	private String tpyCompany;//工作单位名称
	private String tpyDept;//工作部门
	private String tpyPosition;//工作职位	
	private String tpyBeginWorkDate;//开始工作时间
	private String tpyMajor;//专业
	private String tpySpecial;//专业特长
	private String tpyPostCode;//邮编
	private String tpyAddress;//特派员地址
	private String tpyLocation;//籍贯
	@NumberFormat
	private String bankAccount;//银行账户
	private String bankName;//户名
	private String bankOpen;//开户行
	private String starTime; //服务开始时间
	private String endTime;//服务结束时间
	private String tpyReason; //需求单位的申请理由
	//服务对象注册信息和部分法人（特派员）信息
	private String corpType;//单位类型
	private String corpOrgCode;//组织机构代码
	private String corpZczb;//注册资本
	private String corpEstDate;//单位成立日期
	private String corpLegRepName;//法定代表
	private String corpCorName;//联系人姓名
	private String corpCorPhone;//联系人电话 20170817

	private String corpExIncome;//去年收入
	private String corpInvest;//已投资
	private String corpNumWorker;//用工人数
	private String corpMajor;//主营业务[或法人科技优势与服务内容]
	private String corpScale;//现有规模[或法人营业范围]
	private String corpNeeds;//技术需求
	private String corpDwyyzzhfrdmfyj;//单位营业执照或法人
	//标识
	private String checkFlag;//审核标识  0 未审核 1 审核未通过 2 审核通过	
	private String checkPerson;//审核人
	private String checkTime;//审核时间	
	private String checkAdvice;//审核意见	
	private String personFlag;//0 表示特派员（自然人 ）1 表示需求单位 2法人 3表示管理员 4 临时用户
	private int tpyNum;//特派员工作单位的特派员数量
	private String tpyXpFlag;//特派员下派标识
	private String xpTime;//下派时间
	private String tjTableImage;//推荐表图片
	private String threeAreaFlag;//是否三区人才标识[1为三区人才],临时标记
	private String zjFlag;//专家标识   0 否 1是
	private String xpNd;//下派年度本年一下派，不是本年未下派
	private String tpyXpFlagName;//下派區域名稱
	
	public String getTpyXpFlagName() {
		return tpyXpFlagName;
	}

	public void setTpyXpFlagName(String tpyXpFlagName) {
		this.tpyXpFlagName = tpyXpFlagName;
	}

	public String getXpNd() {
		return xpNd;
	}

	public void setXpNd(String xpNd) {
		this.xpNd = xpNd;
	}
	public String getZjFlag() {
		return zjFlag;
	}

	public void setZjFlag(String zjFlag) {
		this.zjFlag = zjFlag;
	}

	public String getThreeAreaFlag() {
		return threeAreaFlag;
	}

	public void setThreeAreaFlag(String threeAreaFlag) {
		this.threeAreaFlag = threeAreaFlag;
	}

	public String getTjTableImage() {
		return tjTableImage;
	}

	public void setTjTableImage(String tjTableImage) {
		this.tjTableImage = tjTableImage;
	}

	public String getXpTime() {
		return xpTime;
	}

	public void setXpTime(String xpTime) {
		this.xpTime = xpTime;
	}
	private String xpStartTime;//下派开始时间
	public String getXpStartTime() {
		return xpStartTime;
	}

	public void setXpStartTime(String xpStartTime) {
		this.xpStartTime = xpStartTime;
	}

	public String getXpEndTime() {
		return xpEndTime;
	}

	public void setXpEndTime(String xpEndTime) {
		this.xpEndTime = xpEndTime;
	}
	private String xpEndTime;//下派结束时间
	public String getTpyXpFlag() {
		return tpyXpFlag;
	}

	public void setTpyXpFlag(String tpyXpFlag) {
		this.tpyXpFlag = tpyXpFlag;
	}

	public String getCheckPerson() {
		return checkPerson;
	}

	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public String getCheckAdvice() {
		return checkAdvice;
	}

	public void setCheckAdvice(String checkAdvice) {
		this.checkAdvice = checkAdvice;
	}

	
	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public int getTpyNum() {
		return tpyNum;
	}

	public void setTpyNum(int tpyNum) {
		this.tpyNum = tpyNum;
	}

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getPersonFlag() {
		return personFlag;
	}

	public void setPersonFlag(String personFlag) {
		this.personFlag = personFlag;
	}
	

	public String getSex() {
		return sex;
	}
	public Sqtpy getSqtpy() {
		return sqtpy;
	}

	public void setSqtpy(Sqtpy sqtpy) {
		this.sqtpy = sqtpy;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTpyIdcard() {
		return tpyIdcard;
	}

	public void setTpyIdcard(String tpyIdcard) {
		this.tpyIdcard = tpyIdcard;
	}

	public String getTpyBirthDate() {
		return tpyBirthDate;
	}

	public void setTpyBirthDate(String tpyBirthDate) {
		this.tpyBirthDate = tpyBirthDate;
	}

	public String getTpyTitle() {
		return tpyTitle;
	}

	public void setTpyTitle(String tpyTitle) {
		this.tpyTitle = tpyTitle;
	}

	public String getTpyQulification() {
		return tpyQulification;
	}

	public void setTpyQulification(String tpyQulification) {
		this.tpyQulification = tpyQulification;
	}

	public String getTpyCompany() {
		return tpyCompany;
	}

	public void setTpyCompany(String tpyCompany) {
		this.tpyCompany = tpyCompany;
	}

	public String getTpyDept() {
		return tpyDept;
	}

	public void setTpyDept(String tpyDept) {
		this.tpyDept = tpyDept;
	}

	public String getTpyPosition() {
		return tpyPosition;
	}

	public void setTpyPosition(String tpyPosition) {
		this.tpyPosition = tpyPosition;
	}
	public String getTpyBeginWorkDate() {
		return tpyBeginWorkDate;
	}

	public void setTpyBeginWorkDate(String tpyBeginWorkDate) {
		this.tpyBeginWorkDate = tpyBeginWorkDate;
	}

	public String getTpyMajor() {
		return tpyMajor;
	}

	public void setTpyMajor(String tpyMajor) {
		this.tpyMajor = tpyMajor;
	}

	public String getTpySpecial() {
		return tpySpecial;
	}

	public void setTpySpecial(String tpySpecial) {
		this.tpySpecial = tpySpecial;
	}

	public String getTpyPostCode() {
		return tpyPostCode;
	}

	public void setTpyPostCode(String tpyPostCode) {
		this.tpyPostCode = tpyPostCode;
	}

	public String getTpyAddress() {
		return tpyAddress;
	}

	public void setTpyAddress(String tpyAddress) {
		this.tpyAddress = tpyAddress;
	}

	public String getTpyLocation() {
		return tpyLocation;
	}

	public void setTpyLocation(String tpyLocation) {
		this.tpyLocation = tpyLocation;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankOpen() {
		return bankOpen;
	}

	public void setBankOpen(String bankOpen) {
		this.bankOpen = bankOpen;
	}

	public String getCorpType() {
		return corpType;
	}

	public void setCorpType(String corpType) {
		this.corpType = corpType;
	}

	public String getCorpOrgCode() {
		return corpOrgCode;
	}

	public void setCorpOrgCode(String corpOrgCode) {
		this.corpOrgCode = corpOrgCode;
	}

	public String getCorpZczb() {
		return corpZczb;
	}

	public void setCorpZczb(String corpZczb) {
		this.corpZczb = corpZczb;
	}

	public String getCorpEstDate() {
		return corpEstDate;
	}

	public void setCorpEstDate(String corpEstDate) {
		this.corpEstDate = corpEstDate;
	}

	public String getCorpLegRepName() {
		return corpLegRepName;
	}

	public void setCorpLegRepName(String corpLegRepName) {
		this.corpLegRepName = corpLegRepName;
	}

	public String getCorpCorName() {
		return corpCorName;
	}

	public void setCorpCorName(String corpCorName) {
		this.corpCorName = corpCorName;
	}
	public String getCorpCorPhone() {
		return corpCorPhone;
	}

	public void setCorpCorPhone(String corpCorPhone) {
		this.corpCorPhone = corpCorPhone;
	}

	public String getCorpExIncome() {
		return corpExIncome;
	}

	public void setCorpExIncome(String corpExIncome) {
		this.corpExIncome = corpExIncome;
	}

	public String getCorpInvest() {
		return corpInvest;
	}

	public void setCorpInvest(String corpInvest) {
		this.corpInvest = corpInvest;
	}

	public String getCorpNumWorker() {
		return corpNumWorker;
	}

	public void setCorpNumWorker(String corpNumWorker) {
		this.corpNumWorker = corpNumWorker;
	}

	public String getCorpMajor() {
		return corpMajor;
	}

	public void setCorpMajor(String corpMajor) {
		this.corpMajor = corpMajor;
	}

	public String getCorpScale() {
		return corpScale;
	}

	public void setCorpScale(String corpScale) {
		this.corpScale = corpScale;
	}

	public String getCorpNeeds() {
		return corpNeeds;
	}

	public void setCorpNeeds(String corpNeeds) {
		this.corpNeeds = corpNeeds;
	}

	public String getCorpDwyyzzhfrdmfyj() {
		return corpDwyyzzhfrdmfyj;
	}

	public void setCorpDwyyzzhfrdmfyj(String corpDwyyzzhfrdmfyj) {
		this.corpDwyyzzhfrdmfyj = corpDwyyzzhfrdmfyj;
	}

	

	
	
	
	
	
	
	


	
	


	


	public String getStarTime() {
		return starTime;
	}

	public void setStarTime(String starTime) {
		this.starTime = starTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTpyReason() {
		return tpyReason;
	}

	public void setTpyReason(String tpyReason) {
		this.tpyReason = tpyReason;
	}



















	private List<Role> roleList = Lists.newArrayList(); // 拥有角色列表

	public User() {
		super();
		this.loginFlag = Global.YES;
	}
	
	public User(String id){
		super(id);
	}

	public User(String id, String loginName){
		super(id);
		this.loginName = loginName;
	}

	public User(Role role){
		super();
		this.role = role;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}

	@SupCol(isUnique="true", isHide="true")
	//@ExcelField(title="ID", type=1, align=2, sort=1)
	public String getId() {
		return id;
	}

	@JsonIgnore
	@NotNull(message="归属公司不能为空")
	@ExcelField(title="领导机构", align=2, sort=20)
	public Office getCompany() {
		return company;
	}

	public void setCompany(Office company) {
		this.company = company;
	}
	
	@JsonIgnore
	@NotNull(message="归属部门不能为空")
	@ExcelField(title="归属部门", align=2, sort=25)
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	@Length(min=1, max=100, message="登录名长度必须介于 1 和 100 之间")
	@ExcelField(title="登录名", align=2, sort=30)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@JsonIgnore
	@Length(min=1, max=100, message="密码长度必须介于 1 和 100 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Length(min=1, max=100, message="姓名长度必须介于 1 和 100 之间")
	@ExcelField(title="姓名", align=2, sort=40)
	public String getName() {
		return name;
	}
	
	@Length(min=1, max=100, message="工号长度必须介于 1 和 100 之间")
	//@ExcelField(title="工号", align=2, sort=45)
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Email(message="邮箱格式不正确")
	@Length(min=0, max=200, message="邮箱长度必须介于 1 和 200 之间")
	@ExcelField(title="邮箱", align=1, sort=50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=200, message="电话长度必须介于 1 和 200 之间")
	@ExcelField(title="电话", align=2, sort=60)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Length(min=0, max=200, message="手机长度必须介于 1 和 200 之间")
	@ExcelField(title="手机", align=2, sort=70)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	//@ExcelField(title="备注", align=1, sort=900)
	public String getRemarks() {
		return remarks;
	}
	
	@Length(min=0, max=100, message="用户类型长度必须介于 1 和 100 之间")
	//@ExcelField(title="用户类型", align=2, sort=80, dictType="sys_user_type")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@ExcelField(title="创建时间", type=0, align=1, sort=90)
	public Date getCreateDate() {
		return createDate;
	}

	//@ExcelField(title="最后登录IP", type=1, align=1, sort=100)
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="最后登录日期", type=1, align=1, sort=110)
	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getOldLoginName() {
		return oldLoginName;
	}

	public void setOldLoginName(String oldLoginName) {
		this.oldLoginName = oldLoginName;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldLoginIp() {
		if (oldLoginIp == null){
			return loginIp;
		}
		return oldLoginIp;
	}

	public void setOldLoginIp(String oldLoginIp) {
		this.oldLoginIp = oldLoginIp;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOldLoginDate() {
		if (oldLoginDate == null){
			return loginDate;
		}
		return oldLoginDate;
	}

	public void setOldLoginDate(Date oldLoginDate) {
		this.oldLoginDate = oldLoginDate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@JsonIgnore
	//@ExcelField(title="拥有角色", align=1, sort=800, fieldType=RoleListType.class)
	public List<Role> getRoleList() {
		return roleList;
	}
	
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	@JsonIgnore
	public List<String> getRoleIdList() {
		List<String> roleIdList = Lists.newArrayList();
		for (Role role : roleList) {
			roleIdList.add(role.getId());
		}
		return roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		roleList = Lists.newArrayList();
		for (String roleId : roleIdList) {
			Role role = new Role();
			role.setId(roleId);
			roleList.add(role);
		}
	}
	
	/**
	 * 用户拥有的角色名称字符串, 多个角色名称用','分隔.
	 */
	public String getRoleNames() {
		return Collections3.extractToString(roleList, "name", ",");
	}
	
	public boolean isAdmin(){
		return isAdmin(this.id);
	}
	
	public static boolean isAdmin(String id){
		return id != null && "1".equals(id);
	}
	
	@Override
	public String toString() {
		return id;
	}

	public void tpyXpFlagName() {
		// TODO Auto-generated method stub
		
	}
}