package com.thinkgem.jeesite.modules.attachment.web;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.batik.script.Window;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.thinkgem.jeesite.common.config.Global;

import sun.misc.BASE64Encoder;


/**
 * 下载文件
 * @version
 */
public class FileDownload {

	/**
	 * @param response 
	 * @param filePath		//文件完整路径(包括文件名和扩展名)
	 * @param fileName		//下载后看到的文件名
	 * @return  文件名
	 */
	public static void fileDownload(final HttpServletResponse response,HttpServletRequest request, String filePath, String fileName){  
		
		// filePath = URLEncoder.encode(filePath, "UTF-8");
		//System.out.print(filePath+"ddddddd");
		try{
			byte[] data = FileUtil.toByteArray2(filePath);  
		    fileName = URLEncoder.encode(fileName, "UTF-8");  
		    fileName = fileName.replaceAll("\\+", " ").replaceAll("%5B", "[").replaceAll("%5D","]").replaceAll("%40", "@")
		    		.replaceAll("%21", "!").replaceAll("%2C", ",").replaceAll("%27","'").replaceAll("%3D", "=")
		    		.replaceAll("%28", "(").replaceAll("%29", ")").replaceAll("%5E", "^").replaceAll("%7B", "{").replaceAll("%7D", "}"); 
		   
		    response.reset();  
		    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");  
		    response.addHeader("Content-Length", "" + data.length);  
		    response.setContentType("application/octet-stream;charset=UTF-8");  
		    OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());  
		    outputStream.write(data);  
		    outputStream.flush();  
		    outputStream.close();
		   // response.flushBuffer();
	    }
		catch(Exception e){
			try {
				request.getRequestDispatcher("/jjbg/a/zsgl/jjbgZsgl/fileError").forward(request,response);
			} catch (Exception e1) {
			} 
		}
	} 
	
	/**
	 * 直接在线打开查看
	 * @param response
	 * @param filePath
	 * @param fileName
	 * @throws Exception
	 */
public static void lookinline(final HttpServletResponse response, String filePath, String fileName) throws Exception{  
		
		byte[] data = FileUtil.toByteArray2(filePath);  
	    fileName = URLEncoder.encode(fileName, "UTF-8");  
	    response.reset();  
	    response.setHeader("Content-Disposition", "filename=\"" + fileName + "\"");  
	    response.addHeader("Content-Length", "" + data.length);  
	    response.setContentType("application/octet-stream;charset=UTF-8");  
	    OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());  
	    outputStream.write(data);  
	    outputStream.flush();  
	    outputStream.close();
	    response.flushBuffer();
	    
	} 

}
