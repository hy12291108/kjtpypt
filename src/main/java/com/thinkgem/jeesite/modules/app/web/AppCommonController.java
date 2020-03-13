package com.thinkgem.jeesite.modules.app.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.thinkgem.jeesite.modules.app.bean.AppResult;
import com.thinkgem.jeesite.modules.app.service.AppCommonService;

/**
 * APP 公用
 *
 * 赵凯浩
 * 2017年10月12日 下午3:27:51
 */
@Controller
@RequestMapping(value = "/app/common")
public class AppCommonController {

	@Autowired
	private AppCommonService appCommonService; 
	
	
	/**
	 * 上传文件(图片)
	 * @param file
	 * @param status
	 * @param httpSession
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "upload")
	@ResponseBody
	public AppResult upload(@RequestParam("file") CommonsMultipartFile file, String status, HttpSession httpSession) throws Exception {
        return appCommonService.upload(file, status, httpSession);
	}
	
	/**
	 * 上传图片(视频)
	 * @param file
	 * @param status
	 * @param httpSession
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "uploadVideo")
	@ResponseBody
	public AppResult uploadVideo(@RequestParam("file") CommonsMultipartFile file, String status, HttpSession httpSession) throws Exception {
        return appCommonService.uploadVideo(file, status, httpSession);
	}
	
}
