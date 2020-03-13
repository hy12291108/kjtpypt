/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.dailywork.web.tpy;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.dailywork.entity.tpy.TpyProtocol;
import com.thinkgem.jeesite.modules.dailywork.service.tpy.TpyProtocolService;
import com.thinkgem.jeesite.modules.dailywork.service.village.VilProtocolService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 日常工作-特派员-服务协议书Controller
 * @author GraceQu
 * @version 2017-07-28
 */
@Controller
@RequestMapping(value = "${adminPath}/dailywork/tpy")
public class TpyProtocolController extends BaseController {

	@Autowired
	private TpyProtocolService tpyService;
	@Autowired
	private VilProtocolService vilProtocolService;
	
	@ModelAttribute
	public TpyProtocol get(@RequestParam(required=false) String id) {
		TpyProtocol entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tpyService.get(id);
		}
		if (entity == null){
			entity = new TpyProtocol();
		}
		return entity;
	}
	
	@RequiresPermissions("dailywork:tpy:view")
	@RequestMapping(value = "myProtocol")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
//		Page<TpyProtocol> page = tpyService.findPage(new Page<TpyProtocol>(request, response), tpyProtocol); 
//		model.addAttribute("page", page);
		System.out.println("*********************");
		return "modules/dailywork/agent/protocol";
	}
	/**
	 * 特派员协议界面控制（已经上传则返回查看修改界面）
	 * @param tpyProtocol
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("dailywork:tpy:view")
	@RequestMapping(value = {"tpyProtocol"})
	public String tpyProtocol(TpyProtocol tpyProtocol,HttpServletRequest request, HttpServletResponse response, Model model) {
//		Page<Data> page = dataService.findPage(new Page<Data>(request, response), data); 
//		model.addAttribute("page", page);
//		return "modules/dailywork/agent/protocol";
		tpyProtocol.setProTpyid(UserUtils.getUser().toString());	
		List<TpyProtocol> tpyProtocolAns=tpyService.findList(tpyProtocol);
		if(tpyProtocolAns.size()==0){
			/*User user=new User();
			user.setId();
			User user=UserUtils.getUser();
			User user2=userDao.findAllList(UserUtils.getUser()).get(0);*/
			//新建页面没有返回按钮
			tpyProtocol.setUser(UserUtils.getUser());
			tpyProtocol.setHtml("type='hidden'");
			//提示上传附件
//			tpyProtocol.setHtml2("return readyToSubmit()");
			model.addAttribute("tpyProtocol", tpyProtocol);
			return "modules/dailywork/tpy/tpyProtocolAdd";
		}else{
			tpyProtocolAns.get(0).setUser(UserUtils.getUser());
			model.addAttribute("tpyProtocol", tpyProtocolAns);
			return "modules/dailywork/tpy/tpyProtocolList";
		}
		
	}
	/**
	 * 新建特派员服务协议/修改特派员服务协议
	 * @param tpyProtocol
	 * @param files
	 * @return
	 */
	@RequestMapping(value = "UploadTpyProtocol")
	public ModelAndView UploadTpyProtocol( Model model,TpyProtocol tpyProtocol,@RequestParam("file") CommonsMultipartFile[] files) {
//		Map<String, Boolean> map = new HashMap<String, Boolean>();
//		UserUtils.getUser();
//		System.out.println(UserUtils.getUser());
//		tpyService.upload
		//保存/更新主表
		tpyProtocol.setProTpyid(UserUtils.getUser().toString());
		tpyService.save(tpyProtocol);
		//选择 则删除附件
		if(tpyProtocol.getCheckbox()!=null){
//			System.out.println(tpyProtocol.getCheckbox());
			for(String checkBox:tpyProtocol.getCheckbox()){
				vilProtocolService.deleteSingelePic(checkBox);
			}
		}
		//选择则上传附件
		if(files!=null&&files.length>0){  
			for(CommonsMultipartFile file:files){
				 if(file.getSize()!=0){
					 this.tpyService.upload(file,tpyProtocol.getId());
				 }
			}
		 }
		ModelAndView  mv=new ModelAndView ();
		List<TpyProtocol> list=new ArrayList<TpyProtocol>();
		list.add(tpyProtocol);
		mv.addObject("tpyProtocol", list);
		mv.setViewName("modules/dailywork/tpy/tpyProtocolList");
		return mv;
	}
	
	/**
	 * 编辑
	 * @param tpyProtocol
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	//@RequiresPermissions("dailywork:tpy:view")
	@RequestMapping(value = {"tpyProtocolEdit"})
	public String tpyProtocolEdit(TpyProtocol tpyProtocol,HttpServletRequest request, HttpServletResponse response, Model model) {
//		Page<Data> page = dataService.findPage(new Page<Data>(request, response), data); 
//		model.addAttribute("page", page);
//		return "modules/dailywork/agent/protocol";
		String tpyProtocolId=tpyProtocol.getId();
		tpyProtocol.setUser(UserUtils.getUser());
		tpyProtocol.setSysAttachmentList(tpyService.findAttatchments(tpyProtocolId));
		model.addAttribute("tpyProtocol", tpyProtocol);
//		List<TpyProtocol> tpyProtocolAns=tpyService.findList(tpyProtocol);
			return "modules/dailywork/tpy/tpyProtocolAdd";
		
	}
	/**
	 * 查看
	 * @param tpyProtocol
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"tpyProtocolView"})
	public String tpyProtocolView(TpyProtocol tpyProtocol,HttpServletRequest request, HttpServletResponse response, Model model) {
//		Page<Data> page = dataService.findPage(new Page<Data>(request, response), data); 
//		model.addAttribute("page", page);
//		return "modules/dailywork/agent/protocol";
		String tpyProtocolId=tpyProtocol.getId();
		tpyProtocol.setSysAttachmentList(tpyService.findAttatchments(tpyProtocolId));
//		model.addAttribute("tpyProtocol", tpyProtocol);
//		List<TpyProtocol> tpyProtocolAns=tpyService.findList(tpyProtocol);
		
//			tpyProtocol.setProTpyid(UserUtils.getUser().toString());	
			tpyProtocol.setUser(UserUtils.getUser());
			model.addAttribute("tpyProtocol", tpyProtocol);
			return "modules/dailywork/tpy/tpyProtocolView";
		
	}
	/**
	 * 删除
	 * @param tpyProtocol
	 * @param redirectAttributes
	 * @return
	 */
//	@RequiresPermissions("dailywork:village:vilProtocol:edit")
	@RequestMapping(value = "tpyProtocolDelete")
	public String tpyProtocolDelete(TpyProtocol tpyProtocol, RedirectAttributes redirectAttributes,Model model) {
		tpyService.delete(tpyProtocol);
//		addMessage(redirectAttributes, "删除贫困村服务协议成功");
		TpyProtocol newTypProtocol=new TpyProtocol();
		newTypProtocol.setHtml("type='hidden'");
		newTypProtocol.setUser(UserUtils.getUser());
		model.addAttribute("tpyProtocol", newTypProtocol);
		return "modules/dailywork/tpy/tpyProtocolAdd";
	}

	
	/*@RequestMapping(value = "UploadWebuploader ")
	public Map<String, Boolean> fileUpload(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		System.out.println("request");
		System.out.println("response");
		
//        map.put("success", this.tpyService.upload(file));
//        map.put("fileUrl", request.getContextPath()+"/upload/"+fileName);  
        return map;
	}*/
	
	/**
	 * 下载特派员服务/贫困村协议协议书模板
	 * @param res
	 * @param req
	 * @param name
	 */
	@RequestMapping(value = "downloadFile")
	public void downloadFile(HttpServletResponse res , HttpServletRequest req,@RequestParam(value="name") String name){
//		Map<String,Boolean> map=new HashMap<String,Boolean>();
		try {
		this.tpyService.downLoad(res,req,name);
		} catch (IOException e) {
			System.out.println("fail");
			e.printStackTrace();
		}
	}
	  private File getDictionaryFile(HttpServletRequest req,String name) {
//	        File file =new File(req.getSession().getServletContext().getRealPath("/WEB-INF/document/down.xlsx"));
		  File file=new File("D:/grace/down.xlsx");
			return file;
	 }
	  @RequestMapping("/testHttpMessageDown")
	  public ResponseEntity<byte[]> download(HttpServletRequest request) throws IOException {
	      File file = new File("D:/grace/123.txt");
	      String fileName="123.txt";
	      byte[] body = FileUtils.readFileToByteArray(file);
//	      byte[] body = null;
//	      InputStream is = new FileInputStream(file);
//	      body = new byte[is.available()];
//	      is.read(body);
	      HttpHeaders headers = new HttpHeaders();
//	      headers.add("Content-Disposition", "attchement;filename=" + file.getName());
	      headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//	      MediaType mediaType = new MediaType("application","octet-stream",Charset.forName("utf-8"));
//	      headers.setContentType(mediaType);
//	      headers.setContentType(MediaType.valueOf("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
	      headers.setContentDispositionFormData("attachment", fileName);
	      HttpStatus statusCode = HttpStatus.OK;
	      ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
	      return entity;
	  } 
	  
	  @RequestMapping("testResponseEntity")
	    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException{
	        
	        byte[] body=null;
	        ServletContext servletContext=session.getServletContext();
	        ///files/abc.txt：所要下载文件的地址
	        InputStream in=servletContext.getResourceAsStream("/WEB-INF/document/down.xlsx");
	        body=new byte[in.available()];
	        in.read(body);
	        
	        HttpHeaders headers=new HttpHeaders();
	        //响应头的名字和响应头的值
	        headers.add("Content-Disposition", "attachment;filename=down.xlsx");
	        
	        HttpStatus statusCode=HttpStatus.OK;
	        
	        ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
	        return response;
	    }
	    
	    
	    @RequestMapping("/something")
	    public ResponseEntity<String> handle(HttpEntity<byte[]> requestEntity) throws Exception {
	        String requestHeader = requestEntity.getHeaders().getFirst("MyRequestHeader");
	        byte[] requestBody = requestEntity.getBody();

	        // do something with request header and body

	        HttpHeaders responseHeaders = new HttpHeaders();
	        responseHeaders.set("MyResponseHeader", "MyValue");
	        return new ResponseEntity<String>("Hello World", responseHeaders, HttpStatus.CREATED);
	    }   
	/*@RequiresPermissions("dailywork:agent:data:view")
	@RequestMapping(value = "form")
	public String form(Data data, Model model) {
		model.addAttribute("data", data);
		return "modules/dailywork/agent/dataForm";
	}

	@RequiresPermissions("dailywork:agent:data:edit")
	@RequestMapping(value = "save"uplode)
	public String save(Data data, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, data)){
			return form(data, model);
		}
		tpyService.save(data);
		addMessage(redirectAttributes, "保存服务协议书成功");
		return "redirect:"+Global.getAdminPath()+"/dailywork/agent/data/?repage";
	}
	
	@RequiresPermissions("dailywork:agent:data:edit")
	@RequestMapping(value = "delete")
	public String delete(Data data, RedirectAttributes redirectAttributes) {
		tpyService.delete(data);
		addMessage(redirectAttributes, "删除服务协议书成功");
		return "redirect:"+Global.getAdminPath()+"/dailywork/agent/data/?repage";
	}*/

}