package com.thinkgem.jeesite.modules.app.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.app.bean.AppConfigure;
import com.thinkgem.jeesite.modules.app.bean.AppResult;

/**
 * APP 公用
 *
 * 赵凯浩
 * 2017年10月27日 下午5:12:59
 */
@Service
@Transactional(readOnly = true)
public class AppCommonService extends BaseService{

	/**
	 * 从配置文件中读取文件存放路径
	 */
	@Value("${TPYuploadPath}")
	private String uploadPath;
	
	
	/**
	 * 上传文件(图片)
	 * 注册、日志填报、专家咨询
	 * @param file
	 * @param status 
	 * @param httpSession
	 * @return
	 * @throws Exception
	 */
	public AppResult upload(@RequestParam("file") CommonsMultipartFile file, String status, HttpSession httpSession) throws Exception {
		AppResult appResult = new AppResult();

		try {			
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
			// 1、存放路径
			String path = getPath(httpSession, sf, status);
			// 2、旧文件名
			String oldName=file.getOriginalFilename();	
		    // 3、新文件名  
	        String newName = sf.format(new Date()) + UUID.randomUUID() + oldName.substring(oldName.lastIndexOf("."));
	        // 4、上传文件
			File localFile = new File(path + newName);
			if(!localFile.exists()){  
				localFile.mkdirs();  
			} 
			file.transferTo(localFile);
			appResult.setObj(path + newName);
			appResult.setSubObj(newName); // 新文件名
			appResult.setThirdObj(oldName); // 旧文件名
		} catch (Exception e) {
			appResult.setMsg("上传失败");
			appResult.setSuccess(false);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
        
        return appResult;
	}
	// 获取文件存放路径
	private String getPath(HttpSession httpSession, SimpleDateFormat sf, String status) throws Exception{
		
		// 1、获得项目的路径  
	    String rootPath = httpSession.getServletContext().getRealPath("/");
	    // 2、文件存放位置目录
	    String position = "";
	    if("drImage".equals(status)){
	    	// 2.1、日志填报（日志图片）
	    	position = AppConfigure.drImage_position;
	    }else if("tjImage".equals(status)){
	    	// 2.2、注册（自然人、法人）推荐表图片
	    	position = AppConfigure.tjImage_position;
	    }else if("zxImage".equals(status)){
	    	// 2.3、专家咨询（缩略图）
	    	position = AppConfigure.zjzxImage_position;
	    }
	    // 3、完整的文件存放路径
	    String path = rootPath.substring(0, 3) + position + new SimpleDateFormat("/yyyy/MM/dd").format(new Date())+"/";
	 
	    return path;
	}
	
	/**
	 * 上传文件（视频）
	 * 专家咨询
	 * @param file
	 * @param status
	 * @param httpSession
	 * @return
	 * @throws Exception
	 */
	public AppResult uploadVideo(@RequestParam("file") CommonsMultipartFile file, String status, HttpSession httpSession) throws Exception {
		AppResult appResult = new AppResult();
		
		try {			
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
			// 1、存放路径
			String path = uploadPath;
			// 2、旧文件名
			String oldName=file.getOriginalFilename();	
		    // 3、新文件名  
	        String newName = sf.format(new Date()) + UUID.randomUUID() + oldName.substring(oldName.lastIndexOf("."));
	        // 4、判断存放文件的文件夹是否存在，不存在则创建
			File localFile = new File(path + newName);
			if(!localFile.exists()){ 
				// mkdirs：可以创建多级文件夹
				localFile.mkdirs();  
			}
			// 5、上传文件
			file.transferTo(localFile);
			// 组装返回数据
			appResult.setMsg("上传成功");
			appResult.setObj(path + newName);
			appResult.setSubObj(newName); // 新文件名
			appResult.setThirdObj(oldName); // 旧文件名
		} catch (Exception e) {
			appResult.setMsg("上传失败");
			appResult.setSuccess(false);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
        
        return appResult;
	}
}
