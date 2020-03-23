/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.common.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import com.ckfinder.connector.ConnectorServlet;

/**
 * CKFinderConnectorServlet
 * @author ThinkGem
 * @version 2014-06-25
 */
public class CKFinderConnectorServlet extends ConnectorServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		prepareGetResponse(request, response, false);
		super.doGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		prepareGetResponse(request, response, true);
		super.doPost(request, response);
	}
//原始路径方法
	private void prepareGetResponse(final HttpServletRequest request,
			final HttpServletResponse response, final boolean post) throws ServletException {
		Principal principal = (Principal) UserUtils.getPrincipal();
		if (principal == null){
			return;
		}
		String command = request.getParameter("command");
		String type = request.getParameter("type");

		if ("Init".equals(command)){// 初始化时，如果startupPath文件夹不存在，则自动创建startupPath文件夹
			String startupPath = request.getParameter("startupPath");// 当前文件夹可指定为模块名
			if (startupPath!=null){
				String[] ss = startupPath.split(":");
				if (ss.length==2){
					String realPath = Global.getUserfilesBaseDir() + Global.USERFILES_BASE_URL
							+ principal + "/" + ss[0] + ss[1];
					FileUtils.createDirectory(FileUtils.path(realPath));
				}
			}
		}else if ("QuickUpload".equals(command) && type!=null){// 快捷上传，自动创建当前文件夹，并上传到该路径
			String currentFolder = request.getParameter("currentFolder");// 当前文件夹可指定为模块名
			String realPath = Global.getUserfilesBaseDir() + Global.USERFILES_BASE_URL
					+ principal + "/" + type + (currentFolder != null ? currentFolder : "");
			FileUtils.createDirectory(FileUtils.path(realPath));
		}

	}

	//新路径配置方法2018-03-21
//	private void prepareGetResponse(final HttpServletRequest request,
//			final HttpServletResponse response, final boolean post) throws ServletException {
//		Principal principal = (Principal) UserUtils.getPrincipal();
//		if (principal == null){
//			return;
//		}
//		String command = request.getParameter("command");
//		String type = request.getParameter("type");
//		// 初始化时，如果startupPath文件夹不存在，则自动创建startupPath文件夹
//		if ("Init".equals(command)){
//			String startupPath = request.getParameter("startupPath");// 当前文件夹可指定为模块名
//			if (startupPath!=null){
//				String[] ss = startupPath.split(":");
//				if (ss.length==2){
//					String realPath="";
//					System.out.println("根路径Global.getUserfilesBaseDir()="+Global.getUserfilesBaseDir()+"========2233=========");
//					//拼接新的根路径 正式服务器Tomact：apache-tomcat-8.0.35
//					//String newgenpath="D:"+"\\"+"apache-tomcat-7.0.78"+"\\"+"webapps"+"\\"+"kjtpypt"+"\\"+"/";
//					String newgenpath="D:"+"\\"+"apache-tomcat-8.0.35"+"\\"+"webapps"+"\\"+"kjtpypt"+"\\"+"/";
//					System.out.println("newgenpath="+newgenpath+"00000000000000000000000000000");
//					//原来的根路径
//					String ylgenpath = Global.getUserfilesBaseDir().replace("\\", "");
//					System.out.println(ylgenpath+"00000000000000000000000000000");
//					System.out.println("Global.USERFILES_BASE_URL="+Global.USERFILES_BASE_URL+"00000000000000000000000000000");
//
//					//if(ylgenpath.equals("D:apache-tomcat-7.0.78webappskjtpypt/")){
//
//					if(ylgenpath.equals("D:apache-tomcat-8.0.35webappskjtpypt/")){
//						System.out.println("1111111111111111111111111111");
//						//原来的根路径带项目名称：kjtpypt
//					//	realPath = Global.getUserfilesBaseDir() + Global.USERFILES_BASE_URL
//					//	+ principal + "/" + ss[0] + ss[1];
//						realPath = newgenpath + Global.USERFILES_BASE_URL
//						+ principal + "/" + ss[0] + ss[1];
//
//					}else{
//						System.out.println("222222222222222222222");
//						//原来的根路径不带项目名称：kjtpypt时采用新拼接的根路径：newgenpath
//						//kjtpypt/userfiles/
//					    String newgenpathurl="userfiles/";
//					//	realPath = newgenpath + Global.USERFILES_BASE_URL
//					//	+ principal + "/" + ss[0] + ss[1];
//						realPath = newgenpath + newgenpathurl
//						+ principal + "/" + ss[0] + ss[1];
//					}
//					System.out.println("--------------------"+realPath+"-----------------------------------------------");
//					FileUtils.createDirectory(FileUtils.path(realPath));
//				}
//			}
//		}
//		// 快捷上传，自动创建当前文件夹，并上传到该路径
//		else if ("QuickUpload".equals(command) && type!=null){
//			String currentFolder = request.getParameter("currentFolder");// 当前文件夹可指定为模块名
//			String realPath = Global.getUserfilesBaseDir() + Global.USERFILES_BASE_URL
//					+ principal + "/" + type + (currentFolder != null ? currentFolder : "");
//			System.out.println("66666666666666666666"+realPath+"66666666666666666666666666");
//			FileUtils.createDirectory(FileUtils.path(realPath));
//		}
//
//	}
}
