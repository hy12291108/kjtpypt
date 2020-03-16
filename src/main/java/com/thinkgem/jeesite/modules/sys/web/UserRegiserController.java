/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.MajorMenu;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.MajorMenuService;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 用户注册Controller
 * @author 刘钢
 * @version 2017-8-7
 */
@Controller
@RequestMapping(value = "/a/UserRegister")
public class UserRegiserController extends BaseController {

	@Autowired
	private SystemService systemService;
	@Autowired
	private OfficeService officeService;
	@Autowired
	private MajorMenuService majorMenuService;
	@ModelAttribute
	public User get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return systemService.getUser(id);
		}else{
			return new User();
		}
	}
	
	@ModelAttribute("majorMenu")
	public MajorMenu getMajorMenu(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return majorMenuService.getMajorMenu(id);
		}else{
			return new MajorMenu();
		}
	}
	/*
	 * 专业controller
	 */
	@RequestMapping(value = {"registerList", ""})
	@ResponseBody
	public Map<String,Object> registerList() {
		Map<String,Object> map = new HashMap<String,Object>();
		List<MajorMenu> majorList = majorMenuService.findAllMajorMenu();
		map.put("majorList", majorList);
		return map;
	}
	/*
	 * 专业controller
	 */
	@ResponseBody
	@RequestMapping(value = {"registerSecondList", ""})
	public Map<String,Object> registerSecondList(MajorMenu majorMenu) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<MajorMenu> majorList = majorMenuService.findMajorMenuSecond(majorMenu);
		map.put("majorList", majorList);
		return map;
	}
	
	/*
	 * 临时用户注册页面
	 * 20171031
	 */
	@RequestMapping(value = {"temporary", ""})
	public String temporaryRegister(){
		return "register/temporaryUserRegister";
	}
	/*
	 * 临时用户注册保存
	 * 20171031
	 */
	@RequestMapping(value = {"tempSave", ""})
	public String tempSave(User user){
		user.setCompany(new Office("08bae2518f1646dfa9e0b6cedf904b54"));
		user.setOffice(new Office("08bae2518f1646dfa9e0b6cedf904b54") );
		// 如果新密码为空，则不更换密码
		if (StringUtils.isNotBlank(user.getNewPassword())) {
		user.setPassword(SystemService.entryptPassword(user.getNewPassword()));
		}
		systemService.tempUserSave(user);
		return "redirect:"+ "/f"  ;
	}
	/*
	 * 临时用户登录页面
	 * 20171031
	 */
	@RequestMapping(value = {"temporaryLoginIndex", ""})
	public String temporaryLoginIndex(){
		return "register/temporaryUserLogin";
	}
	
	
	
	/*
	 * 临时用户登录页面
	 * 20171031
	 */
	@RequestMapping(value = {"temporaryLogin", ""})
	public String temporaryLogin(HttpServletRequest request, Model model,HttpSession session){
		String name  = request.getParameter("username");
		String password = request.getParameter("password");
		User user = UserUtils.getByLoginName(name);
		boolean flag=false;
		if(user!=null){
			flag =systemService.temporaryLogin(user,password);
		}
		if(flag){
			session.setAttribute("user",user);
			return "redirect:"+ "/f" ;
		}else{
			return "redirect:"+ "/a/UserRegister/temporaryLoginIndex" ;
		}
		
		
	}
	
	/*
	 * 临时用户退出登录
	 * 20171031
	 */
	@RequestMapping(value = {"logOff", ""})
	public String temporarylogOff(HttpSession session){
		session.removeAttribute("user");
		return "redirect:"+ "/f" ;
	}
	
	
	
	
	
	
	/*
	 * 注册信息修改
	 * 20171026
	 * lg
	 */
	@RequestMapping("registerUpdate")
	public String registerUpdate(Model model){
				User user = UserUtils.getUser();
				//自然人
				if(user.getPersonFlag().equals("0")){
					//user.getOffice().setId("08bae2518f1646dfa9e0b6cedf904b54");
					//user.setOffice(new Office(request.getParameter("office.id")));
				//	user.setTpyXpFlag();
					if(user.getTpyBeginWorkDate()!=null){
					user.setTpyBeginWorkDate(user.getTpyBeginWorkDate().substring(0, 10));
					}
					model.addAttribute("user", user);
					return "register/tpyRegisterUpdate";
				//法人	
				}else if(user.getPersonFlag().equals("2")){
					if(user.getTpyBeginWorkDate()!=null){
						user.setTpyBeginWorkDate(user.getTpyBeginWorkDate().substring(0, 10));
						}
					model.addAttribute("user", user);
					return "register/tpyCorpRegisterUpdate";
				//服务对象
				}else if(user.getPersonFlag().equals("1")){
					model.addAttribute("user", user);
					return "register/xdRegisterUpdate";
				}
				return null;
			}
	
	/*
	 * 注册审核结果查看
	 * 20171025
	 */
	@RequestMapping("registerResult")
	public String registerResult(Model model){
		User user = UserUtils.getUser();
		List<String> list = new ArrayList<String>();
		String imagePath[] = null;
		System.out.println("2222222222222222222222222");
		System.out.println(user.getTjTableImage());
		if(user.getTjTableImage()!=null){
			if(user.getTjTableImage()!=""){
				
				imagePath = user.getTjTableImage().split("\\|");
				for(int i=0;i<imagePath.length;i++){
					String path=imagePath[i].substring(12);
					list.add(imagePath[i].substring(12));
				}	
			}
			
		}
//		if(user.getTjTableImage()!=null&&!(user.getTjTableImage().equals(""))){
//			imagePath = user.getTjTableImage().split("\\|");
//			for(int i=0;i<imagePath.length;i++){
//				String path=imagePath[i].substring(12);
//				System.out.println(path+"111111111111111111111111");
//				list.add(imagePath[i].substring(12));
//			}
//		}
		System.out.println(user.getPersonFlag());
		//自然人
		if(user.getPersonFlag().equals("0")){
			model.addAttribute("imagePathList",list);		
			if(user.getTpyBeginWorkDate()!=null){
				user.setTpyBeginWorkDate(user.getTpyBeginWorkDate().substring(0, 10));
				}else{
					user.setTpyBeginWorkDate("");
				}
			if(user.getCheckTime()!=null){
			user.setCheckTime(user.getCheckTime().substring(0, 10));
			}
			model.addAttribute("user", user);
			return "modules/check/tpyShResult";
		//法人	
		}else if(user.getPersonFlag().equals("2")){
			model.addAttribute("imagePathList",list);
			if(user.getTpyBeginWorkDate()!=null){
			user.setTpyBeginWorkDate(user.getTpyBeginWorkDate().substring(0, 10));
			}
			if(user.getCheckTime()!=null){
			user.setCheckTime(user.getCheckTime().substring(0, 10));}
			model.addAttribute("user", user);
			return "modules/check/tpyCorpInfoShResult";
		//服务对象
		}else if(user.getPersonFlag().equals("1")){
			model.addAttribute("imagePathList",list);
			if(user.getCheckTime()!=null){
				user.setCheckTime(user.getCheckTime().substring(0, 10));}
			model.addAttribute("user", user);
			return "modules/check/xdInfoShResult";	
		}
		return null;
	}
	
	
	
	
	
	/*
	 * 特派员自然人注册页面
	 * @author 刘钢
	 * 20170727
	 */
	@RequestMapping(value = "form")
	public String form(User user, Model model) {
		if (user.getCompany()==null || user.getCompany().getId()==null){
			user.setCompany(new Office("08bae2518f1646dfa9e0b6cedf904b54"));
		}
		if (user.getOffice()==null || user.getOffice().getId()==null){
			user.setOffice(new Office(""));
		}
		model.addAttribute("user", user);
		return "register/tpyZrrRegister";
	}
	/*
	 * 特派员法人注册页面
	 * @author 刘钢
	 * 20170727
	 */
	@RequestMapping(value = "tpyCorp")
	public String tpyCorp(User user, Model model) {
		if (user.getCompany()==null || user.getCompany().getId()==null){
			user.setCompany(new Office("08bae2518f1646dfa9e0b6cedf904b54"));
		}
		if (user.getOffice()==null || user.getOffice().getId()==null){
			user.setOffice(new Office(""));
		}
		model.addAttribute("user", user);
		return "register/tpyFrRegister";
	}
	
	/*
	 * 需求单位注册
	 * @author 刘钢
	 * 20170731
	 */
	@RequestMapping(value = "xdregister")
	public String xdForm(User user, Model model) {
		if (user.getCompany()==null || user.getCompany().getId()==null){
			user.setCompany(new Office("08bae2518f1646dfa9e0b6cedf904b54"));
		}
		if (user.getOffice()==null || user.getOffice().getId()==null){
			user.setOffice(new Office(""));
		}
		model.addAttribute("user", user);
		return "register/xdRegister";
	}
	@Override
	protected void addMessage(Model model, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
			System.out.println(sb.toString());
		}
		model.addAttribute("message", sb.toString());
	}
	
	
	/* 特派员注册
	 * @author 刘钢
	 * 20170727
	 */
	@RequestMapping(value = "save1")
	public String save1(User user, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		// 修正引用赋值问题，不知道为何，Company和Office引用的一个实例地址，修改了一个，另外一个跟着修改。
		user.setCompany(new Office(request.getParameter("company.id")));
		user.setOffice(new Office(request.getParameter("office.id")));
		// 如果新密码为空，则不更换密码
		if (StringUtils.isNotBlank(user.getNewPassword())) {
			user.setPassword(SystemService.entryptPassword(user.getNewPassword()));
		}
		if (!beanValidator(model, user)){
			return form(user, model);
		}
		if (!"true".equals(checkLoginName(user.getOldLoginName(), user.getLoginName()))){
			addMessage(model, "保存用户'" + user.getLoginName() + "'失败，登录名已存在");
			return form(user, model);
		}
		// 角色数据验证，根据角色设置省份、部门
		List<Role> roleList = Lists.newArrayList();
		List<String> roleIdList = user.getRoleIdList();
		for (Role r : systemService.findTpyRole()){
			if (roleIdList.contains(r.getId())){
				roleList.add(r);
			}
		}
		user.setRoleList(roleList);
		// 保存用户信息
		String flag = systemService.saveUser1(user);
		if(flag.equals("0")){
			// 清除当前用户缓存
			if (user.getLoginName().equals(UserUtils.getUser().getLoginName())){
				UserUtils.clearCache();
			}
			addMessage(redirectAttributes, "保存用户'" + user.getName() + "'成功");
			return "redirect:"+ adminPath  +"/login";
		}else{
			// 清除当前用户缓存
			if (user.getLoginName().equals(UserUtils.getUser().getLoginName())){
				UserUtils.clearCache();
				}
			addMessage(redirectAttributes, "修改'" + user.getName() + "'成功");
			//return "redirect:"+ adminPath  +"/UserRegister/registerResult/";
			return "redirect:"+ "/a/UserRegister/registerResult/" ;
			
		}
	}
	/* 需求单位注册
	 * @author 刘钢
	 * 20170727
	 */
	@RequestMapping(value = "xdsave")
	public String xdSave(User user, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		// 修正引用赋值问题，不知道为何，Company和Office引用的一个实例地址，修改了一个，另外一个跟着修改。
		user.setCompany(new Office(request.getParameter("company.id")));
		user.setOffice(new Office(request.getParameter("office.id")));
		// 如果新密码为空，则不更换密码
		if (StringUtils.isNotBlank(user.getNewPassword())) {
			user.setPassword(SystemService.entryptPassword(user.getNewPassword()));
		}
		if (!beanValidator(model, user)){
			return form(user, model);
		}
		if (!"true".equals(checkLoginName(user.getOldLoginName(), user.getLoginName()))){
			addMessage(model, "保存用户'" + user.getLoginName() + "'失败，登录名已存在");
			return xdForm(user, model);
		}
		// 角色数据验证，根据角色设置省份、部门
		List<Role> roleList = Lists.newArrayList();
		List<String> roleIdList = user.getRoleIdList();
		for (Role r : systemService.findXdRole()){
			if (roleIdList.contains(r.getId())){
				roleList.add(r);
			}
		}
		user.setRoleList(roleList);
		// 保存用户信息
		String flag = systemService.saveUser1(user);
		if(flag.equals("0")){
			// 清除当前用户缓存
			if (user.getLoginName().equals(UserUtils.getUser().getLoginName())){
				UserUtils.clearCache();
			}
			addMessage(redirectAttributes, "保存用户'" + user.getName() + "'成功");
			return "redirect:"+ adminPath  +"/login";
		}else{
			// 清除当前用户缓存
			if (user.getLoginName().equals(UserUtils.getUser().getLoginName())){
				UserUtils.clearCache();
				}
			addMessage(redirectAttributes, "修改'" + user.getName() + "'成功");
			//return "redirect:"+ adminPath  +"/UserRegister/registerResult";
			return "redirect:"+ "/a/UserRegister/registerResult/" ;
		}
		
	}
	
	
	/**
	 * 验证登录名是否有效
	 * @param oldLoginName
	 * @param loginName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkLoginName")
	public String checkLoginName(String oldLoginName, String loginName) {
		if (loginName !=null && loginName.equals(oldLoginName)) {
			return "true";
		} else if (loginName !=null && systemService.getUserByLoginName(loginName) == null) {
			return "true";
		}
		return "false";
	}
	
	
	
	/**
	 * 获取机构JSON数据。
	 * @param extId 排除的ID
	 * @param type	类型（1：公司；2：部门/小组/其它：3：用户）
	 * @param grade 显示级别
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, @RequestParam(required=false) String type,
			@RequestParam(required=false) Long grade, @RequestParam(required=false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Office> list = officeService.findOfficeList();
		for (int i=0; i<list.size(); i++){
			Office e = list.get(i);
			if ((StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1))
					&& (type == null || (type != null && (type.equals("1") ? type.equals(e.getType()) : true)))
					&& (grade == null || (grade != null && Integer.parseInt(e.getGrade()) <= grade.intValue()))
					&& Global.YES.equals(e.getUseable())){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("pIds", e.getParentIds());
				map.put("name", e.getName());
				if (type != null && "3".equals(type)){
					map.put("isParent", true);
				}
				mapList.add(map);
			}
		}
		return mapList;
	}
	/*
	 * 201708
	 * ajax获取工作单位列表
	 * 刘钢
	 */
	@RequestMapping(value = "corpList")
	@ResponseBody
	private Map<String,Object> corpList(@RequestParam(required=true) @RequestBody String officeId){
		System.out.println(officeId);
		Map<String,Object> map = new HashMap<String, Object>();
		List<String> corpList = systemService.corpList(officeId);
		if(corpList!=null&&!(corpList.isEmpty())){
			map.put("corpList", corpList);
		}
		map.put("corpList", corpList);
		return map;
	}
	/*
	 * 下载文件
	 * @author lg
	 * 20171011
	 */
	@RequestMapping("downLoad")
	public void downLoad(HttpServletRequest request,HttpServletResponse response){
		
		File file = new File("D://DownloadTemplate//自然人科技特派员推荐表.doc");
		FileUtils.downFile(file,request,response);
	}	
	/*
	 * 下载文件
	 * @author lg
	 * 20171015
	 */
	@RequestMapping("downLoadCorp")
	public void downLoadCorp(HttpServletRequest request,HttpServletResponse response){
		File file = new File("D://DownloadTemplate//法人科技特派员推荐表.doc");
		FileUtils.downFile(file,request,response);
	}	
	
	/*
	 * 上传图片
	 * @author lg
	 * 20171011
	 */
	@RequestMapping("uploadImage")
	@ResponseBody
	public Map<String,Object> uploadImage( @RequestParam("file") CommonsMultipartFile files[],MultipartFile file,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		 List<String> list = new ArrayList<String>();  
		    // 获得项目的路径  
		    ServletContext sc = request.getSession().getServletContext();  
		    //获取路径
		    String rootPath =  sc.getRealPath("/");
		    //String  paths = rootPath.substring(0, 3);
		    String date = sf.format(new Date());
		    String  paths = rootPath.substring(0, 3)+"uploadImg"+new SimpleDateFormat("/yyyy/MM/dd").format(new Date())+"/"; 
		    System.out.println(paths);
		    // 上传位置  
		    //String path = sc.getRealPath("/img") + "/"; // 设定文件保存的目录  
		    File f = new File(paths);  
		    if (!f.exists())  
		        f.mkdirs();  
		    for (int i = 0; i < files.length; i++) {  
		        // 获得原始文件名  
		        String fileName = files[i].getOriginalFilename();  
		        String fileTail = fileName.substring(fileName.lastIndexOf("."));
		        System.out.println("原始文件名:" + fileName);  
		        // 新文件名  
		        String newFileName = date+UUID.randomUUID()+fileTail;  
		        if (!files[i].isEmpty()) {  
		            try {  
		                FileOutputStream fos = new FileOutputStream(paths 
		                        + newFileName);  
		                InputStream in = files[i].getInputStream();  
		                int b = 0;  
		                byte[] buffer = new byte[1024]; 
		                while ((b = in.read(buffer)) != -1) {  
		                    fos.write(buffer,0,b);  
		                }  
		                fos.close();  
		                in.close();  
		            } catch (Exception e) {  
		                e.printStackTrace();  
		            }  
		        }  
		        System.out.println("上传图片到:" + paths + newFileName);  
		        list.add(paths + newFileName);  
		    }  
		    map.put("fileList",list); 
		return map;
	}
	
	
	/*
	 * 上传图片
	 * @author LG
	 * 20171107
	 */
	@RequestMapping("uploadZjzxImage")
	@ResponseBody
	public Map<String,Object> uploadZjzxImage( @RequestParam("file") CommonsMultipartFile files[],MultipartFile file,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		 List<String> list = new ArrayList<String>();  
		    // 获得项目的路径  
		    ServletContext sc = request.getSession().getServletContext();  
		    //获取路径
		    String rootPath =  sc.getRealPath("/");
		    //String  paths = rootPath.substring(0, 3);
		    String date = sf.format(new Date());
		    String  paths = rootPath.substring(0, 3)+"uploadImg/zjzx"+new SimpleDateFormat("/yyyy/MM/dd").format(new Date())+"/"; 
		    System.out.println(paths);
		    // 上传位置  
		    //String path = sc.getRealPath("/img") + "/"; // 设定文件保存的目录  
		    File f = new File(paths);  
		    if (!f.exists())  
		        f.mkdirs();  
		    for (int i = 0; i < files.length; i++) {  
		        // 获得原始文件名  
		        String fileName = files[i].getOriginalFilename();  
		        String fileTail = fileName.substring(fileName.lastIndexOf("."));
		        System.out.println("原始文件名:" + fileName);  
		        // 新文件名  
		        String newFileName = date+UUID.randomUUID()+fileTail;  
		        if (!files[i].isEmpty()) {  
		            try {  
		                FileOutputStream fos = new FileOutputStream(paths 
		                        + newFileName);  
		                InputStream in = files[i].getInputStream();  
		                int b = 0;  
		                byte[] buffer = new byte[1024]; 
		                while ((b = in.read(buffer)) != -1) {  
		                    fos.write(buffer,0,b);  
		                }  
		                fos.close();  
		                in.close();  
		            } catch (Exception e) {  
		                e.printStackTrace();  
		            }  
		        }  
		        System.out.println("上传图片到:" + paths + newFileName);  
		        list.add(paths + newFileName);  
		    }  
		    map.put("fileList", list); 
		return map;
	}
	
	@RequestMapping("uploadVideo")
	@ResponseBody
	public Map<String,Object> uploadVideo( @RequestParam("file") CommonsMultipartFile files[],HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		 List<String> list = new ArrayList<String>();  
		    // 获得项目的路径  
		    ServletContext sc = request.getSession().getServletContext();  
		    //获取路径
		    String rootPath =  sc.getRealPath("/");
		    //String  paths = rootPath.substring(0, 3);
		    String date = sf.format(new Date());
		    String  paths = rootPath.substring(0, 3)+"uploadVideo"+new SimpleDateFormat("/yyyy/MM/dd").format(new Date())+"/"; 
		    System.out.println(paths);
		    // 上传位置  
		    //String path = sc.getRealPath("/img") + "/"; // 设定文件保存的目录  
		    File f = new File(paths);  
		    if (!f.exists())  
		        f.mkdirs();  
		    for (int i = 0; i < files.length; i++) {  
		        // 获得原始文件名  
		        String fileName = files[i].getOriginalFilename();  
		        String fileTail = fileName.substring(fileName.lastIndexOf("."));
		        System.out.println("原始文件名:" + fileName);  
		        // 新文件名  
		        String newFileName = date+UUID.randomUUID()+fileTail;  
		        if (!files[i].isEmpty()) {  
		            try {  
		                FileOutputStream fos = new FileOutputStream(paths 
		                        + newFileName);  
		                InputStream in = files[i].getInputStream();  
		                int b = 0;  
		                byte[] buffer = new byte[1024]; 
		                while ((b = in.read(buffer)) != -1) {  
		                    fos.write(buffer,0,b);  
		                }  
		                fos.close();  
		                in.close();  
		            } catch (Exception e) {  
		                e.printStackTrace();  
		            }  
		        }  
		        System.out.println("上传视频到:" + paths + newFileName);  
		        list.add(paths + newFileName);  
		    }  
		    map.put("fileList", list); 
		return map;
	}
	
	@RequestMapping("testVideo")
	public String testVideo(){
		return "/modules/test/index";
	}
	
	
}
