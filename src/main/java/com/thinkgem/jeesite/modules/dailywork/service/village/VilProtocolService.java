/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dailywork.service.village;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.kjtpypt.dao.SysAttachmentDao;
import com.thinkgem.jeesite.kjtpypt.entity.SysAttachment;
import com.thinkgem.jeesite.modules.dailywork.dao.village.VilProtocolDao;
import com.thinkgem.jeesite.modules.dailywork.entity.village.VilProtocol;
import com.thinkgem.jeesite.modules.sqtpy.dao.SqtpyDao;
import com.thinkgem.jeesite.modules.sqtpy.entity.Sqtpy;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.villagemanage.dao.TeamMemberDao;
import com.thinkgem.jeesite.modules.villagemanage.entity.TeamMember;

/**
 * 贫困村服务协议Service
 * @author Grace
 * @version 2017-08-02
 */
@Service
@Transactional(readOnly = true)
public class VilProtocolService extends CrudService<VilProtocolDao, VilProtocol> {
	
	@Value("${TPYuploadPath}")
	private String uploadePath;

	@Autowired
	private SysAttachmentDao sysAttachmentDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SqtpyDao sqtpyDao;
	
	@Autowired
	private VilProtocolDao vilProtocolDao;
	
	@Autowired
	private TeamMemberDao teamMemberDao;
	
	public VilProtocol get(String id) {
		return super.get(id);
	}
	
	public List<VilProtocol> findList(VilProtocol vilProtocol) {
		return super.findList(vilProtocol);
	}
	
	public Page<VilProtocol> findPage(Page<VilProtocol> page, VilProtocol vilProtocol) {
		return super.findPage(page, vilProtocol);
	}
	

	@Transactional(readOnly = false)
	public void save(VilProtocol vilProtocol) {
			vilProtocol.preInsert();
			if(vilProtocol.getFwxystateflag().equals("2")){
				vilProtocol.setStateflag("3");			
			}
			vilProtocol.setStateflag("0");
			Sqtpy sqtpy = new Sqtpy();
			sqtpy.setId(vilProtocol.getDdId());
			sqtpy.setFwxy(vilProtocol.getId());
			if(vilProtocol.getFwxystateflag().equals("2")){
				sqtpy.setFwxystateflag("3");				
			}else
			{
				sqtpy.setFwxystateflag(vilProtocol.getFwxystateflag());	
			}
			System.out.println("1111111111111"+vilProtocol.getId());
			sqtpyDao.updatexqdwsqtpyFwxyForAttch(sqtpy);
			dao.insert(vilProtocol);
	}
	
	@Transactional(readOnly = false)
	public void fwxysave(VilProtocol vilProtocol) {
			System.out.println("1111111111111"+vilProtocol.getId()+"---------"+vilProtocol.getStateflag()+vilProtocol.getFwxyopinion());
			Sqtpy sqtpy = new Sqtpy();
			sqtpy.setId(vilProtocol.getDdId());
			sqtpy.setFwxystateflag(vilProtocol.getFwxystateflag());
			sqtpy.setFwxyopinion(vilProtocol.getFwxyopinion());
			sqtpy.setFwxyzpr(vilProtocol.getFwxyzpr());
			sqtpy.setFwxyzpTime(vilProtocol.getFwxyzpTime());
			vilProtocolDao.savefwxy2(sqtpy);
	}
	
	@Transactional(readOnly = false)
	public void savexqdw(VilProtocol vilProtocol) {
			vilProtocol.preInsert();
			if(vilProtocol.getFwxystateflag1().equals("2")){
				vilProtocol.setStateflag("3");			
			}
			vilProtocol.setStateflag("0");
			Sqtpy sqtpy = new Sqtpy();
			sqtpy.setId(vilProtocol.getDdId());
			sqtpy.setFwxyorg(vilProtocol.getId());
			if(vilProtocol.getFwxystateflag1().equals("2")){
				sqtpy.setFwxystateflag1("3");				
			}else
			{
				sqtpy.setFwxystateflag1(vilProtocol.getFwxystateflag1());	
			}
			sqtpyDao.updatexqdwsqtpyFwxyForAttchbyxqdw(sqtpy);
			dao.insert(vilProtocol);
	}
	
	@Transactional(readOnly = false)
	public void delete(VilProtocol vilProtocol) {
		super.delete(vilProtocol);
	}
	@Transactional(readOnly = false)
	public String upload(CommonsMultipartFile file,String attTable,String attRecordid) {
		//上传文件到服务器磁盘
		String originFileName=file.getOriginalFilename();
		String prefix="."+originFileName.substring(originFileName.lastIndexOf(".")+1);
		String newFileName= UUID.randomUUID()+prefix;
//	    File localFile = new File(this.uploadePath + file.getOriginalFilename());
	    File localFile = new File(this.uploadePath + newFileName);
	    if(!localFile.exists()){  
	    	localFile.mkdirs();  
        } 
        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            System.out.println("写入文件失败");
            e.printStackTrace();
            return "fail";
        }
        //附件表存储信息
        SysAttachment sysAttachment=new SysAttachment();
        sysAttachment.setAttTable(attTable);
        sysAttachment.setAttRecordid(attRecordid);
        sysAttachment.setAttOriginname(originFileName);
        sysAttachment.setAttName(newFileName);
        if(prefix.equals(".png")||prefix.equals(".jpg")){
        	sysAttachment.setAttFolder("pic");
        }else{
        	sysAttachment.setAttFolder("doc");
        }
        //生成主键uuid
        sysAttachment.preInsert();
        sysAttachmentDao.insert(sysAttachment);
        return "withAttachment";
	}
	
	@Transactional(readOnly = false)
	public String uploadTeam(CommonsMultipartFile file,String attTable,TeamMember teamMember) {
		//上传文件到服务器磁盘
		String originFileName=file.getOriginalFilename();
		System.out.println("写入文件="+originFileName);
		String prefix="."+originFileName.substring(originFileName.lastIndexOf(".")+1);
		String newFileName= UUID.randomUUID()+prefix;
//	    File localFile = new File(this.uploadePath + file.getOriginalFilename());
	    File localFile = new File(this.uploadePath + newFileName);
	    if(!localFile.exists()){  
	    	localFile.mkdirs();  
        } 
        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            System.out.println("写入文件失败");
            e.printStackTrace();
            return "fail";
        }
        //附件表存储信息
        SysAttachment sysAttachment=new SysAttachment();
        sysAttachment.preInsert();
        teamMember.setTeamprotocol(sysAttachment.getId());
        teamMember.setTeamprotocolFlag("1");     
        sysAttachment.setAttTable(attTable);
        sysAttachment.setAttRecordid(sysAttachment.getId());
        sysAttachment.setAttOriginname(originFileName);
        sysAttachment.setAttName(newFileName);
        if(prefix.equals(".png")||prefix.equals(".jpg")){
        	sysAttachment.setAttFolder("pic");
        }else{
        	sysAttachment.setAttFolder("doc");
        }
        //生成主键uuid
        teamMemberDao.updateTeamprotocol(teamMember);
        sysAttachmentDao.insert(sysAttachment);
        return "withAttachment";
	}
	@Transactional(readOnly = false)
	public String uploadTeamprotocol(CommonsMultipartFile file,String attTable,TeamMember teamMember) {
		//上传文件到服务器磁盘
		String originFileName=file.getOriginalFilename();
		System.out.println("写入文件="+originFileName);
		String prefix="."+originFileName.substring(originFileName.lastIndexOf(".")+1);
		String newFileName= UUID.randomUUID()+prefix;
//	    File localFile = new File(this.uploadePath + file.getOriginalFilename());
	    File localFile = new File(this.uploadePath + newFileName);
	    if(!localFile.exists()){  
	    	localFile.mkdirs();  
        } 
        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            System.out.println("写入文件失败");
            e.printStackTrace();
            return "fail";
        }
        //附件表存储信息
        SysAttachment sysAttachment=new SysAttachment();
        sysAttachment.preInsert();
        teamMember.setTeamprotocol(sysAttachment.getId());
        teamMember.setTeamprotocolFlag("1");     
        sysAttachment.setAttTable(attTable);
        sysAttachment.setAttRecordid(teamMember.getId());
        sysAttachment.setAttOriginname(originFileName);
        sysAttachment.setAttName(newFileName);
        if(prefix.equals(".png")||prefix.equals(".jpg")){
        	sysAttachment.setAttFolder("pic");
        }else{
        	sysAttachment.setAttFolder("doc");
        }
        //生成主键uuid
        teamMemberDao.updateTeamprotocol(teamMember);
        sysAttachmentDao.insert(sysAttachment);
        return "withAttachment";
	}
	
	//2019-04-18团协议上传方法加类型参数（remarks）
	@Transactional(readOnly = false)
	public String saveTeamprotocol(CommonsMultipartFile file,String attTable,TeamMember teamMember,String viltype) {
		//上传文件到服务器磁盘
		String originFileName=file.getOriginalFilename();
		System.out.println("写入文件="+originFileName);
		String prefix="."+originFileName.substring(originFileName.lastIndexOf(".")+1);
		String newFileName= UUID.randomUUID()+prefix;
//	    File localFile = new File(this.uploadePath + file.getOriginalFilename());
	    File localFile = new File(this.uploadePath + newFileName);
	    if(!localFile.exists()){  
	    	localFile.mkdirs();  
        } 
        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            System.out.println("写入文件失败");
            e.printStackTrace();
            return "fail";
        }
        //附件表存储信息
        SysAttachment sysAttachment=new SysAttachment();
        sysAttachment.preInsert();
        teamMember.setTeamprotocol(sysAttachment.getId());
        teamMember.setTeamprotocolFlag("1");     
        sysAttachment.setAttTable(attTable);
        sysAttachment.setAttRecordid(teamMember.getId());
        sysAttachment.setAttOriginname(originFileName);
        sysAttachment.setAttName(newFileName);
        sysAttachment.setRemarks(viltype);
        if(prefix.equals(".png")||prefix.equals(".jpg")){
        	sysAttachment.setAttFolder("pic");
        }else{
        	sysAttachment.setAttFolder("doc");
        }
        //生成主键uuid
        teamMemberDao.updateTeamprotocol(teamMember);
        sysAttachmentDao.insert(sysAttachment);
        return "withAttachment";
	}
	
	@Transactional(readOnly = false)
	public String uploadMember(CommonsMultipartFile file,String attTable,TeamMember teamMember) {
		//上传文件到服务器磁盘
		String originFileName=file.getOriginalFilename();
		String prefix="."+originFileName.substring(originFileName.lastIndexOf(".")+1);
		String newFileName= UUID.randomUUID()+prefix;
//	    File localFile = new File(this.uploadePath + file.getOriginalFilename());
	    File localFile = new File(this.uploadePath + newFileName);
	    if(!localFile.exists()){  
	    	localFile.mkdirs();  
        } 
        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            System.out.println("写入文件失败");
            e.printStackTrace();
            return "fail";
        }
        //附件表存储信息
        SysAttachment sysAttachment=new SysAttachment();
        sysAttachment.preInsert();
        teamMember.setMemberprotocol(sysAttachment.getId());
        teamMember.setMemberprotocolFlag("1");
        sysAttachment.setAttTable(attTable);
        sysAttachment.setAttRecordid(sysAttachment.getId());
        sysAttachment.setAttOriginname(originFileName);
        sysAttachment.setAttName(newFileName);
        if(prefix.equals(".png")||prefix.equals(".jpg")){
        	sysAttachment.setAttFolder("pic");
        }else{
        	sysAttachment.setAttFolder("doc");
        }
        //生成主键uuid
        teamMemberDao.updateMemberprotocol(teamMember);
        sysAttachmentDao.insert(sysAttachment);
        return "withAttachment";
	}
	//2019-04-23团个人协议上传
	@Transactional(readOnly = false)
	public String saveTeamprotocolperson(CommonsMultipartFile file,String attTable,TeamMember teamMember,String viltype) {
		//上传文件到服务器磁盘
		String originFileName=file.getOriginalFilename();
		String prefix="."+originFileName.substring(originFileName.lastIndexOf(".")+1);
		String newFileName= UUID.randomUUID()+prefix;
//	    File localFile = new File(this.uploadePath + file.getOriginalFilename());
	    File localFile = new File(this.uploadePath + newFileName);
	    if(!localFile.exists()){  
	    	localFile.mkdirs();  
        } 
        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            System.out.println("写入文件失败");
            e.printStackTrace();
            return "fail";
        }
        //附件表存储信息
        SysAttachment sysAttachment=new SysAttachment();
        sysAttachment.preInsert();
        teamMember.setMemberprotocol(sysAttachment.getId());
        teamMember.setMemberprotocolFlag("1");
        sysAttachment.setAttTable(attTable);
        sysAttachment.setAttRecordid(teamMember.getId());
        sysAttachment.setAttOriginname(originFileName);
        sysAttachment.setAttName(newFileName);
        sysAttachment.setRemarks(viltype);
        if(prefix.equals(".png")||prefix.equals(".jpg")){
        	sysAttachment.setAttFolder("pic");
        }else{
        	sysAttachment.setAttFolder("doc");
        }
        //生成主键uuid
        teamMemberDao.updateMemberprotocol(teamMember);
        sysAttachmentDao.insert(sysAttachment);
        return "withAttachment";
	}
	@Transactional(readOnly = false)
	public List<SysAttachment> findAttatchments(String vilProtocolId) {
		SysAttachment sysAttachment=new SysAttachment();
		sysAttachment.setAttRecordid(vilProtocolId);
	//	System.out.println("===================="+sysAttachment.setAttRecordid(vilProtocolId));
//		sysAttachmentDao.findAllList(sysAttachment);
		// TODO Auto-generated method stub
		return sysAttachmentDao.findAttachByRecordId(sysAttachment);
	}
	//2019-04-18查询团协议附件
	public List<SysAttachment> findTunAttatchments(String Id,String remarks) {
		SysAttachment sysAttachment=new SysAttachment();
		sysAttachment.setAttRecordid(Id);
		sysAttachment.setRemarks(remarks);
	//	System.out.println("===================="+sysAttachment.setAttRecordid(vilProtocolId));
//		sysAttachmentDao.findAllList(sysAttachment);
		// TODO Auto-generated method stub
		return sysAttachmentDao.findTunAttachByRecordId(sysAttachment);
	}
	@Transactional(readOnly = false)
	public void deleteSingelePic(String filename) {
		 File file = new File(this.uploadePath+filename);
		 if(file.exists()){
			 file.delete();
			  //附件表存储信息
			 sysAttachmentDao.deleteByFileName(filename);
		 }	
	}

	public void downLoad(HttpServletResponse response, HttpServletRequest request, String filename) throws Exception {
		   OutputStream os = response.getOutputStream();
	        try {
	            //编码，否则下载文件中文名乱码，并解决空格变+问题  其中%20是空格在UTF-8下的编码
	        	response.reset();
	        	response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(filename, "UTF-8").replace("+", "%20"));
	        	response.setContentType("application/octet-stream; charset=utf-8");
	            os.write(FileUtils.readFileToByteArray(getDictionaryFile(request,filename)));
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
		  File file=new File(uploadePath+name);
			return file;
	 }
	 
	public boolean isPerson(String id) {
		User user=userDao.get(id);
		if(user.getPersonFlag().equals("0")){
			return true;
		}else{
			return false;
		}
		
	}
	@Transactional(readOnly = false)
	public void updatexqdwsqtpyFwxyForAttch(Sqtpy sqtpy) {
		sqtpyDao.updatexqdwsqtpyFwxyForAttch(sqtpy);
	}
	
}