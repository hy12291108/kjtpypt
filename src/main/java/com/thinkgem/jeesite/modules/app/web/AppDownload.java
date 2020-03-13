package com.thinkgem.jeesite.modules.app.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.jeesite.modules.app.bean.AppConfigure;
import com.thinkgem.jeesite.modules.dailywork.service.village.VilProtocolService;

/**
 * APP 文件下载
 * 例子:http://localhost:8080/jjbg/app/download?filePath=fileuploadDir/fw\2017\10\24\5fccd8d0156e4865a432f5d9dae3e96a.docx&fileName=%E7%A7%91%E6%8A%80%E7%89%B9%E6%B4%BE%E5%91%98%E5%B9%B3%E5%8F%B0%E9%A1%B9%E7%9B%AE%E4%BD%BF%E7%94%A8%E8%AF%B4%E6%98%8E.docx
 * 赵凯浩
 * 2017年10月25日 上午8:39:00
 */
@Controller
@RequestMapping(value = "/app")
public class AppDownload {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private VilProtocolService vilProtocolService;
	
	
	/**
	 * 文件下载
	 * @param response
	 * @param request
	 * @param fileName
	 */
	@RequestMapping(value="download")
	public void download(HttpServletResponse response,HttpServletRequest request,String fileName){
		
		try {
			vilProtocolService.downLoad(response,request,fileName);			
		} catch (Exception e) {
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
	}

}
