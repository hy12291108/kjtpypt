/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dailywork.service.tpy;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.kjtpypt.dao.SysAttachmentDao;
import com.thinkgem.jeesite.kjtpypt.entity.SysAttachment;
import com.thinkgem.jeesite.modules.dailywork.dao.tpy.TpyProtocolDao;
import com.thinkgem.jeesite.modules.dailywork.entity.tpy.TpyProtocol;

/**
 * 日常工作-特派员-服务协议书Service
 * @author GraceQu
 * @version 2017-07-28
 */
@Service
@Transactional(readOnly = true)
public class TpyProtocolService extends CrudService<TpyProtocolDao, TpyProtocol> {
	/* Properties pps = new Properties();
	 pps.load(new FileInputStream("src/main/resources/fileUploadDowload.properties"));*/
	@Value("${TPYuploadPath}")
	private String uploadPath;
	
	@Value("${TPYtemplatePath}")
	private String templatePath;
	
	@Autowired
	TpyProtocolDao tpyProtocolDao;
	
	@Autowired
	SysAttachmentDao sysAttachmentDao;
	
	@Transactional(readOnly = false)
	public Boolean upload(CommonsMultipartFile file,String tpyProtocolId) {
		//写入磁盘
		String originFileName=file.getOriginalFilename();
		String prefix="."+originFileName.substring(originFileName.lastIndexOf(".")+1);
		String newFileName= UUID.randomUUID()+prefix;
	    File localFile = new File(this.uploadPath + newFileName);
	    if(!localFile.exists()){  
	    	localFile.mkdirs();  
        } 
        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            System.out.println("写入文件失败");
            e.printStackTrace();
            return false;
        }
       
        int rssult;
		//记录到协议表
       /* TpyProtocol tpyProtocol=new TpyProtocol();
        tpyProtocol.setProTpyid(UserUtils.getUser().toString());
        tpyProtocol.preInsert();
        rssult=tpyProtocolDao.insert(tpyProtocol);*/
        //记录到附件表
        //附件表存储信息
        SysAttachment sysAttachment=new SysAttachment();
        if(prefix.equals(".png")||prefix.equals(".jpg")){
        	sysAttachment.setAttFolder("pic");
        }else{
        	sysAttachment.setAttFolder("doc");
        }
        sysAttachment.setAttTable("tpyProtocol");
        sysAttachment.setAttRecordid(tpyProtocolId);
        sysAttachment.setAttOriginname(originFileName);
        sysAttachment.setAttName(newFileName);
//        //生成主键uuid
        sysAttachment.preInsert();
        sysAttachmentDao.insert(sysAttachment);
        return true;   
	}
/*
	public Data get(String id) {
		return super.get(id);
	}
	
	public List<Data> findList(Data data) {
		return super.findList(data);
	}
	
	public Page<Data> findPage(Page<Data> page, Data data) {
		return super.findPage(page, data);
	}
	
	@Transactional(readOnly = false)
	public void save(Data data) {
		super.save(data);
	}
	
	@Transactional(readOnly = false)
	public void delete(Data data) {
		super.delete(data);
	}*/

	public void downLoad(HttpServletResponse res, HttpServletRequest req, String name) throws IOException {
		   OutputStream os = res.getOutputStream();
	        try {
	            //编码，否则下载文件中文名乱码，并解决空格变+问题  其中%20是空格在UTF-8下的编码
	            res.reset();
	            res.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(name, "UTF-8").replace("+", "%20"));
	            res.setContentType("application/octet-stream; charset=utf-8");
	            os.write(FileUtils.readFileToByteArray(getDictionaryFile(req,name)));
	            os.flush();
	        }
	        catch(Exception e){
	            e.printStackTrace();
	        }
	        finally {
	            if (os != null) {
	            os.close(); 
	            }
	        }
	}
	  private File getDictionaryFile(HttpServletRequest req,String name) {
//	        File file =new File(req.getSession().getServletContext().getRealPath("/WEB-INF/document/down.xlsx"));
		  File file=new File(templatePath+name);
			return file;
	 }

	public List<SysAttachment> findAttatchments(String tpyProtocolId) {
		SysAttachment sysAttachment=new SysAttachment();
		sysAttachment.setAttRecordid(tpyProtocolId);
		return sysAttachmentDao.findAttachByRecordId(sysAttachment);
	}
}