/**
 */
package com.thinkgem.jeesite.modules.attachment.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import com.sun.tools.example.debug.expr.ParseException;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.modules.attachment.dao.JjbgAttachmentDao;
import com.thinkgem.jeesite.modules.attachment.entity.FileMeta;
import com.thinkgem.jeesite.modules.attachment.entity.FileMsg;
import com.thinkgem.jeesite.modules.attachment.entity.JjbgAttachment;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 附件Service
 * @author zl
 * @version 2017-08-27
 */
@Service
@Transactional(readOnly = true)
public class JjbgAttachmentService extends CrudService<JjbgAttachmentDao, JjbgAttachment> {

	@Value("${files_jjbgPath}")
	private String files_jjbgPath;
	
	@Value("${projectName}")
	private String files_projectName;
	
	@Autowired
	JjbgAttachmentDao jjbgAttachmentDao;

	public JjbgAttachment get(String id) {
		return super.get(id);
	}
	
	public Page<JjbgAttachment> findPageBy(Page<JjbgAttachment> page, JjbgAttachment jjbgAttachment) {
		/*jjbgAttachment.setPage(page);*/
		if(jjbgAttachment.getAhtOperId()!=null){
			page.setList(jjbgAttachmentDao.findListBy(jjbgAttachment));
		}
		return page;
	}
	
	public List<JjbgAttachment> findList(JjbgAttachment JjbgAttachment) {
		return super.findList(JjbgAttachment);
	}
	
	public JjbgAttachment findObjectByAhtOperId(String ahtOperId){
		return jjbgAttachmentDao.findObjectByAhtOperId(ahtOperId);
	}

	
	/**
	 * 公文传输接口使用，用于获得公文相对应的附件
	 * @author 程天白
	 * @date 2017-11-27 
	 * 
	 */
	public List<JjbgAttachment> findListByGW(String AhtOperId) {
		List<JjbgAttachment> fjlist = jjbgAttachmentDao.findListByGW(AhtOperId);
		return fjlist;
	}
	

	
	public List<JjbgAttachment> findListAtt(JjbgAttachment jjbgAttachment) {
		List<JjbgAttachment> list = jjbgAttachmentDao.findListBy(jjbgAttachment);
		 return list;
	}


	@Transactional(readOnly = false)
	public void saveSysAttachment(String files,String ahtOperId,String ahtYwlx,String ahtYwlxName) {
		
//		String ahtYwlx= "jjbg_fwgl";		//业务类型
//		String ahtYwlxName = "发文流程";		//业务类型名称
//		String ahtOperId;		//业务编号
		String ahtName= null;		//存放名称
		String ahtFilename= null;		//显示名称
		String ahtSize= null;		//文件大小
		String ahtType= null;		//文件类型
		String ahtDate= null;		//上传时间
		String ahtPath= null;		//存放地址 最终绝对路径
		String tempPath = Global.getUserfilesBaseDir();//获取上传文件的根目录
		String tempAbsPath = null;//临时绝对路径
		try {
			files = java.net.URLDecoder.decode(files,"utf-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}		
		FileUtils fileUtils = new FileUtils();
		//附件表存储信息
		JjbgAttachment jjbgAttachment=new JjbgAttachment();
		
		if(files!=null&&(!"".equals(files))){
			String[] filePathString = files.split("\\|");
			String tempfilePath= null;
			for (int i = 1; i < filePathString.length; i++) {
				
				tempfilePath = filePathString[i].replace(files_projectName, "");////userfiles/c9e6d098a69644b4a4ab8fb4e821440e/files/2017/08/GBT8567-2006计算机软件文档编制规范.ppt
				
				ahtName = IdGen.uuid()+"."+fileUtils.getFileExtension(tempfilePath);
				
				tempAbsPath = tempPath+tempfilePath;
				tempAbsPath = tempAbsPath.replace("//", "/");//D:/webservice/apache-tomcat-7.0.78/webapps//userfiles/c9e6d098a69644b4a4ab8fb4e821440e/files/2017/08/GBT8567-2006计算机软件文档编制规范.ppt
				
//				System.out.println("*****************************"+tempAbsPath);
				
				ahtFilename = fileUtils.getFileShowName(tempfilePath);
				ahtSize = fileUtils.getFileNameSize(tempfilePath);
				ahtType = fileUtils.getFileExtension(filePathString[i]);
				ahtDate = DateUtils.getDateTime();
				ahtPath = files_jjbgPath+ahtName;
						
				
//				System.out.println("￥￥￥￥￥￥￥￥￥￥￥￥￥"+tempfilePath);
				
				fileUtils.copyFile(tempAbsPath,ahtPath);
//				System.out.println("==="+tempAbsPath+"￥￥￥￥￥￥￥￥￥￥￥￥￥"+ahtPath);
				jjbgAttachment.setAhtYwlx(ahtYwlx);
				jjbgAttachment.setAhtYwlxName(ahtYwlxName);
				jjbgAttachment.setAhtOperId(ahtOperId);
				jjbgAttachment.setAhtName(ahtName);
				jjbgAttachment.setAhtFilename(ahtFilename);
				jjbgAttachment.setAhtsize(ahtSize);
				jjbgAttachment.setAhttype(ahtType);
				jjbgAttachment.setAhtdate(ahtDate);
				jjbgAttachment.setAhtpath(ahtPath);

				jjbgAttachment.preInsert();
				jjbgAttachmentDao.insert(jjbgAttachment);
			}
		 }
	}
	
	/**
	 * fileupload文件上传到临时文件
	 * @param file
	 * @return
	 * @throws IOException
	 * 在此处设置文件上传的路径，名称等 
	 */
	public FileMsg upload(MultipartFile file){
		
		String ahtDate= null;		//上传时间
		
		ahtDate = DateUtils.getDateTime(); //2017-10-23 10:24:37
		ahtDate = ahtDate.substring(0,11); //2017-10-23 
		String[] date = ahtDate.split("-"); //[2017, 10, 23 ]
		
		//获取上传文件的根目录
		String tempFile = Global.getFileuploadTempBaseDir(); // /opt/resource/jjbgfiles/tempFile
		//设置文件地址
		String dirPath = tempFile+date[0];  // /opt/resource/jjbgfiles/tempFile/2017
		
		String fileName = file.getOriginalFilename(); // 获取文件名  0.jpg
		
		String type = FileUtils.getFileExtension(fileName); // 获取文件类型  jpg
		
		if (fileName.length() >= 950) {
			FileMeta fileMeta = new FileMeta();
            fileMeta.setName("文件名称不能大于950!");
            List<FileMeta> files = new ArrayList<FileMeta>();
            files.add(fileMeta);
            FileMsg fileMsg = new FileMsg();
            fileMsg.setFiles(files);
            return fileMsg;
		}
		
		/*else{
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("upload.properties");   
		Properties p = new Properties();  
		try {   
		   p.load(inputStream);   
		} catch (IOException e1) {   
		   e1.printStackTrace();   
		} */
		/*Properties p = new Properties();	
		String jpeg = p.getProperty("picture_jpeg");
		String gif = p.getProperty("picture_gif");
		String jpg = p.getProperty("picture_jpg");
		String png = p.getProperty("picture_png");
		String rar = p.getProperty("compression_rar");
		String zip = p.getProperty("compression_zip");
		String txt = p.getProperty("Document_txt");
		String doc = p.getProperty("Document_doc");
		String docx = p.getProperty("Document_docx");
		String pdf = p.getProperty("Document_pdf");
		
		String gd = p.getProperty("Document_gd");
		String sep = p.getProperty("Document_sep");
		
		String xls = p.getProperty("table_xls");
		String xlsx = p.getProperty("table_xlsx");*/
		
		if (type.equals("gd")||type.equals("sep")||type.equals("txt")||type.equals("doc") || type.equals("docx") || type.equals("xls") || type.equals("xlsx") 
				|| type.equals("pdf") ||type.equals("png") ||type.equals("jpg") || type.equals("jpeg") ||type.equals("gif") 
				||type.equals("zip") ||type.equals("rar")) {
		/*if(type.equals("pdf")){*/	
			File dir = new File(dirPath);
		
			if (!file.isEmpty()) {
				if(fileName.contains("#") ||fileName.contains("%") ||fileName.contains("……") ||fileName.contains("_") 
					||fileName.contains("$") || fileName.contains("&") || fileName.contains("”") || fileName.contains("“")
					|| fileName.contains("+") || fileName.contains("‘") || fileName.contains("’") || fileName.contains("，") || fileName.contains(",")){
					
					FileMeta fileMeta = new FileMeta();
		            fileMeta.setName("文件名包含特殊字符!");
		            List<FileMeta> files = new ArrayList<FileMeta>();
		            files.add(fileMeta);
		            FileMsg fileMsg = new FileMsg();
		            fileMsg.setFiles(files);
		            return fileMsg;
				}else{
					InputStream in = null;
					OutputStream out = null;

					try {
	         
						if (!dir.exists())
							dir.mkdirs();
							File serverFile = new File(dir.getAbsolutePath() + "/" + file.getOriginalFilename());
							in = file.getInputStream();
							out = new FileOutputStream(serverFile);
							byte[] b = new byte[1024];
							int len = 0;
							while ((len = in.read(b)) > 0) {
								out.write(b, 0, len);
							}
	                
						out.close();
						in.close();
	                
						FileMeta fileMeta = new FileMeta();
						fileMeta.setName(file.getOriginalFilename());
						/*File.separator*/
	                fileMeta.setUrl(dirPath+ "/" + file.getOriginalFilename());
	                List<FileMeta> files = new ArrayList<FileMeta>();
	                files.add(fileMeta);
	                FileMsg fileMsg = new FileMsg();
	                fileMsg.setFiles(files);
	                return fileMsg;

	            } catch (Exception e) {
	                FileMeta fileMeta = new FileMeta();
	                fileMeta.setName(file.getOriginalFilename());
	                fileMeta.setZsname(file.getOriginalFilename().substring(15));
	                fileMeta.setUrl(dirPath + "/" + file.getOriginalFilename());
	                
	                List<FileMeta> files = new ArrayList<FileMeta>();
	                files.add(fileMeta);
	                FileMsg fileMsg = new FileMsg();
	                fileMsg.setFiles(files);
	                return fileMsg;
	            } finally {
	                if (out != null) {
	                    try {
	                    	out.flush();
							out.close();
						} catch (IOException e) {
						}
	                    out = null;
	                }
	                if (in != null) {
	                    try {
							in.close();
						} catch (IOException e) {
						}
	                    in = null;
	                }
	            }
	        }} else {
	            FileMeta fileMeta = new FileMeta();
	            fileMeta.setName("文件内容为空!");
	            List<FileMeta> files = new ArrayList<FileMeta>();
	            files.add(fileMeta);
	            FileMsg fileMsg = new FileMsg();
	            fileMsg.setFiles(files);
	            return fileMsg;
	        }
		}
		else{
			FileMeta fileMeta = new FileMeta();
            fileMeta.setName("文件类型不匹配");
            List<FileMeta> files = new ArrayList<FileMeta>();
            files.add(fileMeta);
            FileMsg fileMsg = new FileMsg();
            fileMsg.setFiles(files);
            return fileMsg;
	}
		
}
	
	/**
	 * 保存数据
	 * @param files
	 * @param string
	 * 
	 */
	public void saveupload(String files, String lx,String ahtOperId,String ywlx) {
		
		String ahtYwlx= null;		//业务类型
		String ahtYwlxName = null;		//业务类型名称
		String ahtName= null;		//存放名称
		String ahtFilename= null;		//显示名称
		String ahtSize= null;		//文件大小
		String ahtType= null;		//文件类型
		String ahtDate= null;		//上传时间
		String ahtPath= null;		//存放地址 最终绝对路径
		
		ahtYwlx = lx;
		ahtYwlxName = ywlx;
		
		String uploadFile = Global.getFileuploadBaseDir(); // D:/fileuploadDir/
		String base = Global.getFileuploadRootDir();
		String ahtDateTime = DateUtils.getDateTime(); //2017-10-23 10:32:37
		ahtDate = ahtDateTime.substring(0,10); //2017-10-23
		String[] date = ahtDate.split("-"); //[2017, 10, 23]
			
		try {
			files = java.net.URLDecoder.decode(files,"utf-8");    // D:/tempFile/2017\1372326022142.jpg
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
		FileUtils fileUtils = new FileUtils();
		//附件表存储信息
		JjbgAttachment jjbgAttachment=new JjbgAttachment();
		if(files!=null&&(!"".equals(files))){
			
			String[] filePathString = files.split(","); // [D:/tempFile/2017\1372326022142.jpg]
			String tempfilePath= null;
	
			for (int i = 0; i < filePathString.length; i++) {
				
				tempfilePath =filePathString[i];//临时路径   D:/tempFile/2017\1372326022142.jpg
				ahtName = IdGen.uuid()+"."+fileUtils.getFileExtension(tempfilePath);     //eeac9b319c8447188fdfddb30b5df1e7.jpg
				
				ahtFilename = fileUtils.getFileShowName(tempfilePath);   // 
				ahtSize = fileUtils.getFileNameSize(tempfilePath);
				ahtType = fileUtils.getFileExtension(filePathString[i]); //jpg
				
				String path = uploadFile+lx+File.separator+date[0]+File.separator+date[1]+File.separator+date[2]+File.separator+ahtName;
				ahtPath = path.substring(base.length(),path.length());
				ahtPath.replace("\\", "/");
				fileUtils.copyFile(tempfilePath,path);
				
				jjbgAttachment.setAhtYwlx(ahtYwlx);
				jjbgAttachment.setAhtYwlxName(ahtYwlxName);
				jjbgAttachment.setAhtOperId(ahtOperId);
				jjbgAttachment.setAhtName(ahtName);
				jjbgAttachment.setAhtFilename(ahtFilename);
				jjbgAttachment.setAhtsize(ahtSize);
				jjbgAttachment.setAhttype(ahtType);
				jjbgAttachment.setAhtdate(ahtDateTime);
				jjbgAttachment.setAhtpath(ahtPath);
				jjbgAttachment.preInsert();
				jjbgAttachmentDao.insert(jjbgAttachment);
				
			}
		}
	}
	public JjbgAttachment findObjectByPath(String path) {
		return jjbgAttachmentDao.findObjectByPath(path);
	}
	
	/**
	 * 保存数据   用于个人计划、公司计划的附件修改
	 * @param files
	 * @param string
	 * 
	 */
	@Transactional(readOnly = false)
	public JjbgAttachment editupload(String files,String lx,String ahtOperId,String ywlx) {
		
		String ahtYwlx= null;		//业务类型
		String ahtYwlxName = null;		//业务类型名称	
		
		String ahtName= null;		//存放名称
		String ahtFilename= null;		//显示名称
		String ahtSize= null;		//文件大小
		String ahtType= null;		//文件类型
		String ahtDate= null;		//上传时间
		String ahtPath= null;		//存放地址 最终绝对路径
		
		ahtYwlx = lx;   //wj
		ahtYwlxName = ywlx;  //个人文件
	
		
		//需要存放的路径
		String uploadFile = Global.getFileuploadBaseDir(); // D:/fileuploadDir
		//文件的临时上传路径
		String tempFile = Global.getFileuploadTempBaseDir(); // D:/tempFile
		String base = Global.getFileuploadRootDir(); //D:/
		String ahtDateTime = DateUtils.getDateTime();   //NEW DATE
		ahtDate = ahtDateTime.substring(0,10); //2018-03-22 13:50:35
		String[] date = ahtDate.split("-"); //[2018, 03, 22]
		
		try {
			files = java.net.URLDecoder.decode(files,"utf-8");    //  E:\temp\2017\qqqqq.txt
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
		FileUtils fileUtils = new FileUtils();
		//附件表存储信息
		JjbgAttachment jjbgAttachment = new JjbgAttachment();
		if(files!=null&&(!"".equals(files))){
			//查看临时路径下的文件数量，以逗号隔开
			String[] filePathString = files.split(","); //[D:/tempFile/2018/2.pdf]
			String tempfilePath= null;
	
			for (int i = 0; i < filePathString.length; i++) {
				tempfilePath =filePathString[i];//临时路径下的文件（还是路径）	 D:/tempFile/2018/2.pdf	 E:\temp\2017\qqqqq.txt
				if (filePathString[i].startsWith(tempFile)) { //D:/tempFile
					//获取文件的名称和大小
					ahtFilename = fileUtils.getFileShowName(tempfilePath);   // 显示名称  2.pdf
					ahtSize = fileUtils.getFileNameSize(tempfilePath); //117kb
				}else{
					JjbgAttachment jjbgAttachment2 = jjbgAttachmentDao.findObjectByPath(filePathString[i]);
					ahtFilename = jjbgAttachment2.getAhtFilename();
					ahtSize = jjbgAttachment2.getAhtsize();
				
				}
				//生成保存时的文件名  6640b0ddc9924aadbd32735c266bcd4e.pdf 
				ahtName = IdGen.uuid()+"."+fileUtils.getFileExtension(tempfilePath);    
				//文件类型
				ahtType = fileUtils.getFileExtension(filePathString[i]); //pdf	
				//生成文件最终的保存路径
				String path = uploadFile+lx+File.separator+date[0]+File.separator+date[1]+File.separator+date[2]+File.separator+ahtName;
				//D:/fileuploadDir/wj\2018\03\22\6640b0ddc9924aadbd32735c266bcd4e.pdf
				//修改路径
				ahtPath = path.substring(base.length(),path.length()); //fileuploadDir/wj\2018\03\22\f77383c6fc47413db7d1733ebd21ee66.pdf
				//fileuploadDir/wj\2018\03\22\6640b0ddc9924aadbd32735c266bcd4e.pdf
				//修改路径
				ahtPath = ahtPath.replace("\\", "/"); //fileuploadDir/wj/2018/03/22/f77383c6fc47413db7d1733ebd21ee66.pdf
				//fileuploadDir/wj/2018/03/22/6640b0ddc9924aadbd32735c266bcd4e.pdf
				if(!tempfilePath.startsWith(tempFile)){
					//添加根目录
					tempfilePath = base+tempfilePath; 
				}
				//把文件从tempfilePath中copy到path路径下
				fileUtils.copyFile(tempfilePath,path);
				jjbgAttachment.setAhtYwlx(ahtYwlx);
				jjbgAttachment.setAhtYwlxName(ahtYwlxName);
				jjbgAttachment.setAhtName(ahtName);
				jjbgAttachment.setAhtFilename(ahtFilename);
				jjbgAttachment.setAhtsize(ahtSize);
				jjbgAttachment.setAhttype(ahtType);
				jjbgAttachment.setAhtdate(ahtDateTime);
				jjbgAttachment.setAhtpath(ahtPath);
				jjbgAttachment.setAhtOperId(ahtOperId);
				super.save(jjbgAttachment);
				
			}
		}
		return jjbgAttachment;
	}
	
	
	
	
	
	/**
	 * 保存数据   用于cms附件系统
	 * @param files
	 * @param string
	 * 
	 */
	public JjbgAttachment editupload1(String files,String lx,String ahtOperId,String ywlx) {
		
		String ahtYwlx= null;		//业务类型
		String ahtYwlxName = null;		//业务类型名称	
		
		String ahtName= null;		//存放名称
		String ahtFilename= null;		//显示名称
		String ahtSize= null;		//文件大小
		String ahtType= null;		//文件类型
		String ahtDate= null;		//上传时间
		String ahtPath= null;		//存放地址 最终绝对路径
		
		ahtYwlx = lx;   //wj
		ahtYwlxName = ywlx;  //个人文件
	
		
		//需要存放的路径
		String uploadFile = Global.getFileuploadBaseDir(); // D:/fileuploadDir
		//文件的临时上传路径
		String tempFile = Global.getFileuploadTempBaseDir(); // D:/tempFile
		String base = Global.getFileuploadRootDir(); //D:/
		String ahtDateTime = DateUtils.getDateTime();   //NEW DATE
		ahtDate = ahtDateTime.substring(0,10); //2018-03-22 13:50:35
		String[] date = ahtDate.split("-"); //[2018, 03, 22]
		
		try {
			files = java.net.URLDecoder.decode(files,"utf-8");    //  E:\temp\2017\qqqqq.txt
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
		FileUtils fileUtils = new FileUtils();
		//附件表存储信息
		JjbgAttachment jjbgAttachment=new JjbgAttachment();
		if(files!=null&&(!"".equals(files))){
			//查看临时路径下的文件数量，以逗号隔开
			String[] filePathString = files.split(","); //[D:/tempFile/2018/2.pdf]
			String tempfilePath= null;
	
			for (int i = 0; i < filePathString.length; i++) {
				tempfilePath =filePathString[i];//临时路径下的文件（还是路径）	 D:/tempFile/2018/2.pdf	 E:\temp\2017\qqqqq.txt
				if (filePathString[i].startsWith(tempFile)) { //D:/tempFile
					//获取文件的名称和大小
					ahtFilename = fileUtils.getFileShowName(tempfilePath);   // 显示名称  2.pdf
					ahtSize = fileUtils.getFileNameSize(tempfilePath); //117kb
				}else{
					JjbgAttachment jjbgAttachment2 = jjbgAttachmentDao.findObjectByPath(filePathString[i]);
					ahtFilename = jjbgAttachment2.getAhtFilename();
					ahtSize = jjbgAttachment2.getAhtsize();
				
				}
				//生成保存时的文件名  6640b0ddc9924aadbd32735c266bcd4e.pdf 
				ahtName = IdGen.uuid()+"."+fileUtils.getFileExtension(tempfilePath);    
				//文件类型
				ahtType = fileUtils.getFileExtension(filePathString[i]); //pdf	
				//生成文件最终的保存路径
				String path = uploadFile+lx+File.separator+date[0]+File.separator+date[1]+File.separator+date[2]+File.separator+ahtName;
				//D:/fileuploadDir/wj\2018\03\22\6640b0ddc9924aadbd32735c266bcd4e.pdf
				//修改路径
				ahtPath = path.substring(base.length(),path.length()); //fileuploadDir/wj\2018\03\22\f77383c6fc47413db7d1733ebd21ee66.pdf
				//fileuploadDir/wj\2018\03\22\6640b0ddc9924aadbd32735c266bcd4e.pdf
				//修改路径
				ahtPath = ahtPath.replace("\\", "/"); //fileuploadDir/wj/2018/03/22/f77383c6fc47413db7d1733ebd21ee66.pdf
				//fileuploadDir/wj/2018/03/22/6640b0ddc9924aadbd32735c266bcd4e.pdf
				if(!tempfilePath.startsWith(tempFile)){
					//添加根目录
					tempfilePath = base+tempfilePath; 
				}
				//把文件从tempfilePath中copy到path路径下
				fileUtils.copyFile(tempfilePath,path);
				jjbgAttachment.setAhtYwlx(ahtYwlx);
				jjbgAttachment.setAhtYwlxName(ahtYwlxName);
				jjbgAttachment.setAhtName(ahtName);
				jjbgAttachment.setAhtFilename(ahtFilename);
				jjbgAttachment.setAhtsize(ahtSize);
				jjbgAttachment.setAhttype(ahtType);
				jjbgAttachment.setAhtdate(ahtDateTime);
				jjbgAttachment.setAhtpath(ahtPath);
				jjbgAttachment.setAhtOperId(ahtOperId);
				jjbgAttachment.preInsert();
				jjbgAttachmentDao.insert(jjbgAttachment);
				
			}
		}
		return jjbgAttachment;
	}

	/**
	 * 文档中心调用
	 * @param files
	 * @param lx
	 * @param ywlx
	 * @return
	 */
	public JjbgAttachment editupload(String files,String lx,String ywlx){
		String ahtYwlx= null;		//业务类型
		String ahtYwlxName = null;		//业务类型名称
		String ahtName= null;		//存放名称
		String ahtFilename= null;		//显示名称
		String ahtSize= null;		//文件大小
		String ahtType= null;		//文件类型
		String ahtDate= null;		//上传时间
		String ahtPath= null;		//存放地址 最终绝对路径
		
		ahtYwlx = lx;
		ahtYwlxName = ywlx;
		
		String uploadFile = Global.getFileuploadBaseDir();  //       /opt/resource/jjbgfiles/fileuploadDir
		String tempFile = Global.getFileuploadTempBaseDir();  //     /opt/resource/jjbgfiles/tempFile
		String base = Global.getFileuploadRootDir(); // 文件存储根目录          /opt/resource/jjbgfiles/
		
		String ahtDateTime = DateUtils.getDateTime(); 
		ahtDate = ahtDateTime.substring(0,10); 
		String[] date = ahtDate.split("-"); 
		
		try {
			files = java.net.URLDecoder.decode(files,"utf-8");  // fileuploadDir/xzzx/2017/11/08/2fd0524bb767403d8a16d5e302d9a4ce.docx
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}		
		FileUtils fileUtils = new FileUtils();
		//附件表存储信息
		JjbgAttachment jjbgAttachment=new JjbgAttachment();
		if(files!=null&&(!"".equals(files))){
			
			String[] filePathString = files.split(","); 
			String tempfilePath=filePathString[0];   // fileuploadDir/xzzx/2017/11/08/2fd0524bb767403d8a16d5e302d9a4ce.docx
				if (filePathString[0].startsWith(tempFile)) {
					tempfilePath = tempfilePath.replace("\\", "/");
					ahtFilename = fileUtils.getFileShowName(tempfilePath); 
				
					ahtSize = fileUtils.getFileNameSize(tempfilePath);
				}else{
					JjbgAttachment jjbgAttachment2 = jjbgAttachmentDao.findObjectByPath(filePathString[0]);
					ahtFilename = jjbgAttachment2.getAhtFilename(); 
					ahtSize = jjbgAttachment2.getAhtsize();
				}
				
				ahtName = IdGen.uuid()+"."+fileUtils.getFileExtension(tempfilePath);  // 80a6a33708a94b5ca16fea23b621e6ef.docx    
				ahtType = fileUtils.getFileExtension(tempfilePath); 
				String path = uploadFile+lx+File.separator+date[0]+File.separator+date[1]+File.separator+date[2]+File.separator+ahtName;
				
				ahtPath = path.substring(base.length(),path.length()); // fileuploadDir/xzzx\2017\11\08\80a6a33708a94b5ca16fea23b621e6ef.docx
				
				if(!tempfilePath.startsWith(tempFile)){
					tempfilePath = base+tempfilePath;
				}
				
				fileUtils.copyFile(tempfilePath,path);
				ahtPath = ahtPath.replace("\\", "/"); 
				jjbgAttachment.setAhtYwlx(ahtYwlx);
				jjbgAttachment.setAhtYwlxName(ahtYwlxName);
				jjbgAttachment.setAhtName(ahtName);
				jjbgAttachment.setAhtFilename(ahtFilename);
				jjbgAttachment.setAhtsize(ahtSize);
				jjbgAttachment.setAhttype(ahtType);
				jjbgAttachment.setAhtdate(ahtDateTime);
				jjbgAttachment.setAhtpath(ahtPath);
				jjbgAttachment.preInsert();
				jjbgAttachmentDao.insert(jjbgAttachment);
				
			}
		
		return jjbgAttachment;
	}
	
	/**
	 * 办公用品    文件上传到临时文件
	 * 及支持上传表格
	 * @param file
	 * @return
	 * @throws IOException 
	 */
	public FileMsg uploadExcel(MultipartFile file) throws IOException, ParseException {
		
		String ahtDate= null;		//上传时间
		
		ahtDate = DateUtils.getDateTime(); //2017-10-23 10:24:37
		ahtDate = ahtDate.substring(0,11); //2017-10-23 
		String[] date = ahtDate.split("-"); //[2017, 10, 23 ]
		
		String tempFile = Global.getFileuploadTempBaseDir(); // D:/tempFile/
		String dirPath = tempFile+date[0];  // D:/tempFile/2017
		
		String fileName = file.getOriginalFilename(); // 文档.docx
		String type = FileUtils.getFileExtension(fileName); //docx
		
		if (type.equals("xls") || type.equals("xlsx")) {
			
		File dir = new File(dirPath);
		if (!file.isEmpty()) {
			if(fileName.contains("#") ||fileName.contains("%") ||fileName.contains("……") ||fileName.contains("_") 
					||fileName.contains("$") || fileName.contains("&") || fileName.contains("”") || fileName.contains("“")
					|| fileName.contains("+") || fileName.contains("‘") || fileName.contains("’") || fileName.contains("，") || fileName.contains(",")){
					
					FileMeta fileMeta = new FileMeta();
		            fileMeta.setName("文件名包含特殊字符!");
		            List<FileMeta> files = new ArrayList<FileMeta>();
		            files.add(fileMeta);
		            FileMsg fileMsg = new FileMsg();
		            fileMsg.setFiles(files);
		            return fileMsg;
			}else{
				
	         InputStream in = null;
	          OutputStream out = null;

	            try {
	         
	                if (!dir.exists())
	                    dir.mkdirs();
	                File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
	                in = file.getInputStream();
	                out = new FileOutputStream(serverFile);
	                byte[] b = new byte[1024];
	                int len = 0;
	                while ((len = in.read(b)) > 0) {
	                    out.write(b, 0, len);
	                }
	                
	                out.close();
	                in.close();
	                
	                FileMeta fileMeta = new FileMeta();
	                fileMeta.setName(file.getOriginalFilename());
	                
	                fileMeta.setUrl(dirPath+ File.separator + file.getOriginalFilename());
	                List<FileMeta> files = new ArrayList<FileMeta>();
	                files.add(fileMeta);
	                FileMsg fileMsg = new FileMsg();
	                fileMsg.setFiles(files);
	                return fileMsg;

	            } catch (Exception e) {
	                FileMeta fileMeta = new FileMeta();
	                fileMeta.setName(file.getOriginalFilename());
	                fileMeta.setZsname(file.getOriginalFilename().substring(15));
	                fileMeta.setUrl(dirPath + File.separator + file.getOriginalFilename());
	                List<FileMeta> files = new ArrayList<FileMeta>();
	                files.add(fileMeta);
	                FileMsg fileMsg = new FileMsg();
	                fileMsg.setFiles(files);
	                return fileMsg;
	            } finally {
	                if (out != null) {
	                    out.close();
	                    out = null;
	                }

	                if (in != null) {
	                    in.close();
	                    in = null;
	                }
	            }
	        }} else {
	            FileMeta fileMeta = new FileMeta();
	            fileMeta.setName("文件内容为空!");
	           /* fileMeta.setUrl(savePath+ File.separator + file.getOriginalFilename());*/
	            List<FileMeta> files = new ArrayList<FileMeta>();
	            files.add(fileMeta);
	            FileMsg fileMsg = new FileMsg();
	            fileMsg.setFiles(files);
	            return fileMsg;
	        }
		}
		else{
			FileMeta fileMeta = new FileMeta();
            fileMeta.setName("文件类型不匹配");
            List<FileMeta> files = new ArrayList<FileMeta>();
            files.add(fileMeta);
            FileMsg fileMsg = new FileMsg();
            fileMsg.setFiles(files);
            return fileMsg;
	}
}

	
/**
 * 公司相册限制文件上传的类型
 * 仅支持上传图片
 * 
 * @param file
 * @return
 * @throws IOException
 * @throws ParseException
 */
public FileMsg uploadImage(MultipartFile file) throws IOException, ParseException {
		
		String ahtDate= null;		//上传时间
		
		ahtDate = DateUtils.getDateTime(); //2017-10-23 10:24:37
		ahtDate = ahtDate.substring(0,11); //2017-10-23 
		String[] date = ahtDate.split("-"); //[2017, 10, 23 ]
		
		String tempFile = Global.getFileuploadTempBaseDir(); // D:/tempFile/
		String dirPath = tempFile+date[0];  // D:/tempFile/2017
		
		String fileName = file.getOriginalFilename(); // 文档.docx
		String type = FileUtils.getFileExtension(fileName); //docx
		
		if (type.equals("png") || type.equals("jpg") ||  type.equals("jpeg") || type.equals("gif")  ) {
			
		File dir = new File(dirPath);
		if (!file.isEmpty()) {
			if(fileName.contains("#") ||fileName.contains("%") ||fileName.contains("……") ||fileName.contains("_") 
					||fileName.contains("$") || fileName.contains("&") || fileName.contains("”") || fileName.contains("“")
					|| fileName.contains("+") || fileName.contains("‘") || fileName.contains("’") || fileName.contains("，") || fileName.contains(",")){
					
					FileMeta fileMeta = new FileMeta();
		            fileMeta.setName("文件名包含特殊字符!");
		            List<FileMeta> files = new ArrayList<FileMeta>();
		            files.add(fileMeta);
		            FileMsg fileMsg = new FileMsg();
		            fileMsg.setFiles(files);
		            return fileMsg;
			}else{
				
	         InputStream in = null;
	          OutputStream out = null;

	            try {
	         
	                if (!dir.exists())
	                    dir.mkdirs();
	                File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
	                in = file.getInputStream();
	                out = new FileOutputStream(serverFile);
	                byte[] b = new byte[1024];
	                int len = 0;
	                while ((len = in.read(b)) > 0) {
	                    out.write(b, 0, len);
	                }
	                
	                out.close();
	                in.close();
	                
	                FileMeta fileMeta = new FileMeta();
	                fileMeta.setName(file.getOriginalFilename());
	                
	                fileMeta.setUrl(dirPath+ File.separator + file.getOriginalFilename());
	                List<FileMeta> files = new ArrayList<FileMeta>();
	                files.add(fileMeta);
	                FileMsg fileMsg = new FileMsg();
	                fileMsg.setFiles(files);
	                return fileMsg;

	            } catch (Exception e) {
	                FileMeta fileMeta = new FileMeta();
	                fileMeta.setName(file.getOriginalFilename());
	                fileMeta.setZsname(file.getOriginalFilename().substring(15));
	                fileMeta.setUrl(dirPath + File.separator + file.getOriginalFilename());
	                List<FileMeta> files = new ArrayList<FileMeta>();
	                files.add(fileMeta);
	                FileMsg fileMsg = new FileMsg();
	                fileMsg.setFiles(files);
	                return fileMsg;
	            } finally {
	                if (out != null) {
	                    out.close();
	                    out = null;
	                }

	                if (in != null) {
	                    in.close();
	                    in = null;
	                }
	            }
	        }} else {
	            FileMeta fileMeta = new FileMeta();
	            fileMeta.setName("文件内容为空!");
	           /* fileMeta.setUrl(savePath+ File.separator + file.getOriginalFilename());*/
	            List<FileMeta> files = new ArrayList<FileMeta>();
	            files.add(fileMeta);
	            FileMsg fileMsg = new FileMsg();
	            fileMsg.setFiles(files);
	            return fileMsg;
	        }
		}
		else{
			FileMeta fileMeta = new FileMeta();
            fileMeta.setName("文件类型不匹配");
            List<FileMeta> files = new ArrayList<FileMeta>();
            files.add(fileMeta);
            FileMsg fileMsg = new FileMsg();
            fileMsg.setFiles(files);
            return fileMsg;
	}
}

	
	public FileMsg uploadFile(MultipartFile file) throws IOException, ParseException {
		
		String ahtDate= null;		//上传时间
		
		ahtDate = DateUtils.getDateTime(); //2017-10-23 10:24:37
		ahtDate = ahtDate.substring(0,11); //2017-10-23 
		String[] date = ahtDate.split("-"); //[2017, 10, 23 ]
		
		String tempFile = Global.getFileuploadTempBaseDir(); // D:/tempFile/
		String dirPath = tempFile+date[0];  // D:/tempFile/2017
		
		String fileName = file.getOriginalFilename(); // 文档.docx
		String type = FileUtils.getFileExtension(fileName); //docx
		
		if (type.equals("docx") || type.equals("doc") ||  type.equals("txt") || type.equals("xls") || type.equals("xlsx") || type.equals("zip") || type.equals("rar")) {
			
		File dir = new File(dirPath);
		if (!file.isEmpty()) {
			if(fileName.contains("#") ||fileName.contains("%") ||fileName.contains("……") ||fileName.contains("_") 
					||fileName.contains("$") || fileName.contains("&") || fileName.contains("”") || fileName.contains("“")
					|| fileName.contains("+") || fileName.contains("‘") || fileName.contains("’") || fileName.contains("，") || fileName.contains(",")){
					
					FileMeta fileMeta = new FileMeta();
		            fileMeta.setName("文件名包含特殊字符!");
		            List<FileMeta> files = new ArrayList<FileMeta>();
		            files.add(fileMeta);
		            FileMsg fileMsg = new FileMsg();
		            fileMsg.setFiles(files);
		            return fileMsg;
			}else{
				
	         InputStream in = null;
	          OutputStream out = null;

	            try {
	         
	                if (!dir.exists())
	                    dir.mkdirs();
	                File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
	                in = file.getInputStream();
	                out = new FileOutputStream(serverFile);
	                byte[] b = new byte[1024];
	                int len = 0;
	                while ((len = in.read(b)) > 0) {
	                    out.write(b, 0, len);
	                }
	                
	                out.close();
	                in.close();
	                
	                FileMeta fileMeta = new FileMeta();
	                fileMeta.setName(file.getOriginalFilename());
	                
	                fileMeta.setUrl(dirPath+ File.separator + file.getOriginalFilename());
	                List<FileMeta> files = new ArrayList<FileMeta>();
	                files.add(fileMeta);
	                FileMsg fileMsg = new FileMsg();
	                fileMsg.setFiles(files);
	                return fileMsg;

	            } catch (Exception e) {
	                FileMeta fileMeta = new FileMeta();
	                fileMeta.setName(file.getOriginalFilename());
	                fileMeta.setZsname(file.getOriginalFilename().substring(15));
	                fileMeta.setUrl(dirPath + File.separator + file.getOriginalFilename());
	                List<FileMeta> files = new ArrayList<FileMeta>();
	                files.add(fileMeta);
	                FileMsg fileMsg = new FileMsg();
	                fileMsg.setFiles(files);
	                return fileMsg;
	            } finally {
	                if (out != null) {
	                    out.close();
	                    out = null;
	                }

	                if (in != null) {
	                    in.close();
	                    in = null;
	                }
	            }
	        }} else {
	            FileMeta fileMeta = new FileMeta();
	            fileMeta.setName("文件内容为空!");
	           /* fileMeta.setUrl(savePath+ File.separator + file.getOriginalFilename());*/
	            List<FileMeta> files = new ArrayList<FileMeta>();
	            files.add(fileMeta);
	            FileMsg fileMsg = new FileMsg();
	            fileMsg.setFiles(files);
	            return fileMsg;
	        }
		}
		else{
			FileMeta fileMeta = new FileMeta();
            fileMeta.setName("文件类型不匹配");
            List<FileMeta> files = new ArrayList<FileMeta>();
            files.add(fileMeta);
            FileMsg fileMsg = new FileMsg();
            fileMsg.setFiles(files);
            return fileMsg;
	}
}

	
	@Transactional(readOnly = false)
	public void save(JjbgAttachment jjbgAttachment) {
		super.save(jjbgAttachment);
	}
	
	/**
	 * 修改附件列表中的附件的processId
	 * @param processId
	 * @return
	 *  
	 */
	public void saveFjlist(String oId,String processId) {
		if(oId!=null){
			  JjbgAttachment jjbgAttachment = new JjbgAttachment();
			  jjbgAttachment.setCreateBy(UserUtils.getUser());
			  jjbgAttachment.setAhtOperId(oId);
			  List<JjbgAttachment> fjlist = findListAtt(jjbgAttachment); 
			  for (JjbgAttachment attachment : fjlist) {
				   attachment.setAhtOperId(processId);
				   save(attachment);
				}
			 System.out.println("保存附件列表成功");
		}else{
			System.out.println("processId为空");
		}
	}

	public List<String> getFilePath(String attachmentId) {
		return jjbgAttachmentDao.getFilePath(attachmentId);
	}
	
	
}