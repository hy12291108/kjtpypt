/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.sun.tools.example.debug.expr.ParseException;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.VideoUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.attachment.entity.FileMsg;
import com.thinkgem.jeesite.modules.attachment.entity.JjbgAttachment;
import com.thinkgem.jeesite.modules.attachment.service.JjbgAttachmentService;
import com.thinkgem.jeesite.modules.attachment.web.FileDownload;
import com.thinkgem.jeesite.modules.cms.entity.Article;
import com.thinkgem.jeesite.modules.cms.entity.Category;
import com.thinkgem.jeesite.modules.cms.entity.Site;
import com.thinkgem.jeesite.modules.cms.service.ArticleDataService;
import com.thinkgem.jeesite.modules.cms.service.ArticleService;
import com.thinkgem.jeesite.modules.cms.service.CategoryService;
import com.thinkgem.jeesite.modules.cms.service.FileTplService;
import com.thinkgem.jeesite.modules.cms.service.SiteService;
import com.thinkgem.jeesite.modules.cms.utils.CmsUtils;
import com.thinkgem.jeesite.modules.cms.utils.TplUtils;
import com.thinkgem.jeesite.modules.eightMile.entity.EightLogin;
import com.thinkgem.jeesite.modules.eightMile.entity.KjtpyVideoInfo;
import com.thinkgem.jeesite.modules.eightMile.service.KjtpyVideoInfoService;
import com.thinkgem.jeesite.modules.sqtpy.entity.Sqtpy;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;


/**
 * 文章Controller
 * @author ThinkGem
 * @version 2013-3-23
 */
@Controller
@RequestMapping(value = "${adminPath}/cms/article")
public class ArticleController extends BaseController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleDataService articleDataService;
	@Autowired
	private CategoryService categoryService;
    @Autowired
   	private FileTplService fileTplService;
    @Autowired
   	private SiteService siteService;
    @Autowired
    private JjbgAttachmentService jjbgAttachmentService;
    
    @Autowired
   	private KjtpyVideoInfoService kjtpyVideoInfoService;
	
	@ModelAttribute
	public Article get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return articleService.get(id);
		}else{
			return new Article();
		}
	}
	
	@RequiresPermissions("cms:article:view")
	@RequestMapping(value = {"list", ""})
	public String list(Article article, HttpServletRequest request, HttpServletResponse response, Model model) {
//		for (int i=0; i<10000000; i++){
//			Article a = new Article();
//			a.setCategory(new Category(article.getCategory().getId()));
//			a.setTitle("测试测试测试测试测试测试测试测试"+a.getCategory().getId());
//			a.setArticleData(new ArticleData());
//			a.getArticleData().setContent(a.getTitle());
//			articleService.save(a);
//		}
        Page<Article> page = articleService.findPage(new Page<Article>(request, response), article, true); 
        model.addAttribute("page", page);
		return "modules/cms/articleList";
	}
	
	@RequestMapping(value = "upload")
	public String upload(Article article, RedirectAttributes redirectAttributes) {
		return "modules/cms/upload";
	}

	@RequiresPermissions("cms:article:view")
	@RequestMapping(value = "form")
	public String form(Article article,HttpServletRequest request, HttpServletResponse response, Model model) {
		// 如果当前传参有子节点，则选择取消传参选择
		if (article.getCategory()!=null && StringUtils.isNotBlank(article.getCategory().getId())){
			List<Category> list = categoryService.findByParentId(article.getCategory().getId(), Site.getCurrentSiteId());
			if (list.size() > 0){
				article.setCategory(null);
			}else{
				article.setCategory(categoryService.get(article.getCategory().getId()));
			}
		}
		article.setArticleData(articleDataService.get(article.getId()));
//		if (article.getCategory()=null && StringUtils.isNotBlank(article.getCategory().getId())){
//			Category category = categoryService.get(article.getCategory().getId());
//		}
        model.addAttribute("contentViewList",getTplContent());
        model.addAttribute("article_DEFAULT_TEMPLATE",Article.DEFAULT_TEMPLATE);
		model.addAttribute("article", article);
		CmsUtils.addViewConfigAttribute(model, article.getCategory());
		JjbgAttachment jjbgAttachment = new JjbgAttachment();
		jjbgAttachment.setCreateBy(article.getCreateBy());
		jjbgAttachment.setAhtOperId(article.getAttachment());
		Page<JjbgAttachment> fjlist = jjbgAttachmentService.findPageBy(new Page<JjbgAttachment>(request, response), jjbgAttachment); 
		model.addAttribute("fjlist", fjlist);
		return "modules/cms/articleForm";
	}

	@RequiresPermissions("cms:article:edit")
	@RequestMapping(value = "save")
	public String save(Article article, Model model, RedirectAttributes redirectAttributes,@RequestParam("files") String files) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
		JjbgAttachment jjbgAttachment = new JjbgAttachment();
		String s = "09" + sdf.format(new Date());//
		article.setAttachment(s);
		if(article.getAttachment() == null){
			
			if (null != files) {
				jjbgAttachment = jjbgAttachmentService.editupload(files, "wzfj", s, "文章附件");
			}
		}else{
			jjbgAttachment.setCreateBy(article.getCreateBy());
			jjbgAttachment.setAhtOperId(article.getAttachment());
			List<JjbgAttachment> fjlist = jjbgAttachmentService.findList(jjbgAttachment);
			for(JjbgAttachment j : fjlist){
				jjbgAttachmentService.delete(j);
			}
			if (null != files) {
				jjbgAttachment = jjbgAttachmentService.editupload(files, "wzfj", article.getAttachment(), "文章附件");
			}
		}
		articleService.save(article);
		addMessage(redirectAttributes, "保存文章'" + StringUtils.abbr(article.getTitle(),50) + "'成功");
		String categoryId = article.getCategory()!=null?article.getCategory().getId():null;
		return "redirect:" + adminPath + "/cms/article/?repage&category.id="+(categoryId!=null?categoryId:"");
	}
	
	@RequiresPermissions("cms:article:edit")
	@RequestMapping(value = "delete")
	public String delete(Article article, String categoryId, @RequestParam(required=false) Boolean isRe, RedirectAttributes redirectAttributes) {
		// 如果没有审核权限，则不允许删除或发布。
		if (!UserUtils.getSubject().isPermitted("cms:article:audit")){
			addMessage(redirectAttributes, "你没有删除或发布权限");
		}
		articleService.delete(article, isRe);
		addMessage(redirectAttributes, (isRe!=null&&isRe?"发布":"删除")+"文章成功");
		return "redirect:" + adminPath + "/cms/article/?repage&category.id="+(categoryId!=null?categoryId:"");
	}

	/**
	 * 文章选择列表
	 */
	@RequiresPermissions("cms:article:view")
	@RequestMapping(value = "selectList")
	public String selectList(Article article, HttpServletRequest request, HttpServletResponse response, Model model) {
        list(article, request, response, model);
		return "modules/cms/articleSelectList";
	}
	
	/**
	 * 通过编号获取文章标题
	 */
	@RequiresPermissions("cms:article:view")
	@ResponseBody
	@RequestMapping(value = "findByIds")
	public String findByIds(String ids) {
		List<Object[]> list = articleService.findByIds(ids);
		return JsonMapper.nonDefaultMapper().toJson(list);
	}

    private List<String> getTplContent() {
   		List<String> tplList = fileTplService.getNameListByPrefix(siteService.get(Site.getCurrentSiteId()).getSolutionPath());
   		tplList = TplUtils.tplTrim(tplList, Article.DEFAULT_TEMPLATE, "");
   		return tplList;
   	}
    
    @RequestMapping(value = {"xxydlist"})
	public String xxydlist(Article article, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Article> page = articleService.findPagexxydAll(new Page<Article>(request, response), article, true); 
        model.addAttribute("page", page);
		return "modules/cms/xxyd/xxydList";
	}
    
    @RequestMapping(value = "xxydform")
	public String xxydform(String id, Article article, Model model,HttpServletRequest request, HttpServletResponse response) {
    	
		// 如果当前传参有子节点，则选择取消传参选择		
		Category category = new Category("xxyd");
		category.setId("1000");
		category.setName("学习园地");
		if(id.equals("")){
			article.setArticleData(articleDataService.get(article.getId()));
	    }
		else
	    {
			Article article1 = articleService.getXxyd(id);	
			article.setArticleData(articleDataService.get(article1.getId()));
	    }
		article.setCategory(category);
		
		JjbgAttachment jjbgAttachment = new JjbgAttachment();
		jjbgAttachment.setCreateBy(article.getCreateBy());
		jjbgAttachment.setAhtOperId(article.getAttachment());
		Page<JjbgAttachment> fjlist = jjbgAttachmentService.findPageBy(new Page<JjbgAttachment>(request, response), jjbgAttachment); 
		model.addAttribute("fjlist", fjlist);
        model.addAttribute("contentViewList",getTplContent());
        model.addAttribute("article_DEFAULT_TEMPLATE",Article.DEFAULT_TEMPLATE);
		model.addAttribute("article", article);
		CmsUtils.addViewConfigAttribute(model, article.getCategory());
		
		
		
		return "modules/cms/xxyd/xxydForm";
	}
    
    @RequestMapping(value = "xxydUpdate")
   	public String xxydUpdate(String id, Article article, Model model,HttpServletRequest request, HttpServletResponse response) {
    	article.setSysAttachmentList(articleService.findAttatchments(article.getAttachmentName()));
   		// 如果当前传参有子节点，则选择取消传参选择		
   		Category category = new Category("xxyd");
   		category.setId("1000");
   		category.setName("学习园地");
   		if(id.equals("")){
   			article.setArticleData(articleDataService.get(article.getId()));
   	    }
   		else
   	    {
   			Article article1 = articleService.getXxyd(id);	
   			article.setArticleData(articleDataService.get(article1.getId()));
   	    }
   		article.setCategory(category);
   		JjbgAttachment jjbgAttachment = new JjbgAttachment();
		jjbgAttachment.setCreateBy(article.getCreateBy());
		jjbgAttachment.setAhtOperId(article.getAttachment());
		Page<JjbgAttachment> fjlist = jjbgAttachmentService.findPageBy(new Page<JjbgAttachment>(request, response), jjbgAttachment); 
		model.addAttribute("fjlist", fjlist);
        model.addAttribute("contentViewList",getTplContent());
        model.addAttribute("article_DEFAULT_TEMPLATE",Article.DEFAULT_TEMPLATE);
   		model.addAttribute("article", article);
   		CmsUtils.addViewConfigAttribute(model, article.getCategory());
   		
   		List<KjtpyVideoInfo> kjtpyVideoInfoList = kjtpyVideoInfoService.getListByArticleId(article.getId()); 
		model.addAttribute("kjtpyVideoInfoList", kjtpyVideoInfoList);
   		return "modules/cms/xxyd/xxydUpdate";
   	}
    
    //,@RequestParam("file") CommonsMultipartFile[] videoFiles
	@RequestMapping(value = "savexxyd")
	public String savexxyd(Article article, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes,@RequestParam(value="files1",required=false) String files1) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
		JjbgAttachment jjbgAttachment = new JjbgAttachment();
		String s = "09" + sdf.format(new Date());//
		article.setAttachment(s);
		if(article.getAttachment() == null){
			
			if (null != files1) {
				jjbgAttachment = jjbgAttachmentService.editupload(files1, "wzfj", s, "文章附件");
			}
		}else{
			jjbgAttachment.setCreateBy(article.getCreateBy());
			jjbgAttachment.setAhtOperId(article.getAttachment());
			List<JjbgAttachment> fjlist = jjbgAttachmentService.findList(jjbgAttachment);
			for(JjbgAttachment j : fjlist){
				jjbgAttachmentService.delete(j);
			}
			if (null != files1) {
				jjbgAttachment = jjbgAttachmentService.editupload(files1, "wzfj", article.getAttachment(), "文章附件");
			}
		}
		
		
		
		
		
		articleService.savexxyd(article);
		//保存视频信息
		String videoIds = request.getParameter("video");
		if(StringUtils.isNotBlank(videoIds)){
			article.setAttachment(Article.HAVEVIDEO);
			String[] videoArr = videoIds.split(",");
			if(videoArr.length>0){
				for(String s1:videoArr){
					KjtpyVideoInfo kjtpyVideoInfo = kjtpyVideoInfoService.get(s1);
					kjtpyVideoInfo.setArictleId(article.getId());
					kjtpyVideoInfoService.save(kjtpyVideoInfo);
				}
				
			}
		}
		
//		//2.保存附件
//		 if(videoFiles!=null&&videoFiles.length>0){  
//	            //循环获取file数组中得文件  
//			 for(CommonsMultipartFile file:videoFiles){
//				 //文件，关联表，关联表记录主键
//				 if(file.getSize()!=0){
//				 this.articleService.upload(file,"xxydvideo",article);
//				 }
//			 }
//		 }
		
		
		
		//1.保存主表
		articleService.savexxyd(article);
		addMessage(redirectAttributes, "保存文章'" + StringUtils.abbr(article.getTitle(),50) + "'成功");
		String categoryId = article.getCategory()!=null?article.getCategory().getId():null;
		return "redirect:" + adminPath + "/cms/article/xxydlist?repage&category.id="+(categoryId!=null?categoryId:"");
	}
	
	@RequestMapping(value = "xxydGetUrl")
    @ResponseBody
    public Map<String,String> xxydGetUrl(HttpServletRequest request) throws IOException{
		Map<String,String> map = new HashMap<String,String>();
    	//获取视频标题
		String videoTitle = request.getParameter("videoTitle");
		
		//启用八百里流媒体
		 //获取八百里createUpload实体类
		 EightLogin e = VideoUtils.getEightLogin();
		 StringBuffer msg = new StringBuffer();
		 String msg1 = e.getMsg();
		 //登录成功
		 if(e != null && "ok".equals(e.getCode())){
			 //获取八百里调用客户端路径
			 String url = VideoUtils.getVideoUploadLaunchURLAction(e.getStreamName(),videoTitle); 
			 System.out.println(url);
			 map.put("url", url);
			 map.put("stream_name",e.getStreamName());
			 return map;
		 }else{
			 System.out.println(e.getCode());
//			 switch(e.getCode()){
//			 	case "auth":
//					msg = msg.append("密码验证失败或权限不足").append(":").append(msg1);
//					break;
//				case "rpc":
//					msg = msg.append("内部RPC错误").append(":").append(msg1);
//					break;
//				case "db":
//					msg = msg.append("数据库错误 ").append(":").append(msg1);
//					break;
//				case "param":
//					msg = msg.append("参数错误 ").append(":").append(msg1);
//					break;
//				case "notexists":
//					msg = msg.append("流不存在").append(":").append(msg1);
//					break;
//				case "internal":
//					msg = msg.append("内部错误").append(":").append(msg1);
//					break;
//				default:
//					msg = msg.append("未获取到返回值");
//			}
			map.put("msg", msg.toString());
		 }
    	
    	return null;
    }
    
    @RequestMapping(value = "xxydVideoOver")
    @ResponseBody
    public Map<String,String> xxydVideoOver(HttpServletRequest request) throws IOException{
    	Map<String,String> map = new HashMap<String,String>();
    	String stream_name = request.getParameter("stream_name");
    	//启用八百里流媒体,获取视频播放实例
    	KjtpyVideoInfo kjtpyVideoInfo = VideoUtils.vodInfo(stream_name);
    	kjtpyVideoInfoService.save(kjtpyVideoInfo);
    	map.put("vodId",kjtpyVideoInfo.getId());
    	map.put("vodTitle",kjtpyVideoInfo.getTitle());
    	return map;
    }
    
    @RequestMapping(value = "xxydDelVideo")
    @ResponseBody
    public Map<String,String> xxydDelVideo(HttpServletRequest request) throws IOException{
    	try{
    		String videoId = request.getParameter("videoId");
    		Map<String,String> sd = new HashMap<String,String>();
    		KjtpyVideoInfo kjtpyVideoInfo = kjtpyVideoInfoService.get(videoId);
    		kjtpyVideoInfoService.delete(kjtpyVideoInfo);
        	sd.put("artId", kjtpyVideoInfo.getArictleId());
    		return sd;
    	}catch(Exception e){
    		return null;
    	}
    	
    }
	
	
	@RequestMapping(value = "xxyddelete")
	public String xxyddelete(Article article, String categoryId, @RequestParam(required=false) Boolean isRe, RedirectAttributes redirectAttributes) {
		// 如果没有审核权限，则不允许删除或发布。
				if (!UserUtils.getSubject().isPermitted("cms:article:audit")){
					addMessage(redirectAttributes, "你没有删除或发布权限");
				}
		articleService.delete(article, isRe);
		addMessage(redirectAttributes, (isRe!=null&&isRe?"发布":"删除")+"文章成功");
		return "redirect:" + adminPath + "/cms/article/xxydlist?repage&category.id="+(categoryId!=null?categoryId:"");
	}
    /*
     *未审核专家咨询列表
     *jxy
     */
    @RequestMapping(value = {"zjzxList"})
	public String zjzxListCheck(Article article, HttpServletRequest request, HttpServletResponse response, Model model) {
    	article.setDelFlag("2");
    	Page<Article> page = articleService.findPagezjzx(new Page<Article>(request, response),article, true); 
        model.addAttribute("page", page);
		return "modules/cms/zjzx/zjzxList";
	}
    /*
     *审核通过专家咨询列表
     *jxy
     */
    @RequestMapping(value = {"zjzxListPass"})
	public String zjzxListPass(Article article, HttpServletRequest request, HttpServletResponse response, Model model) {
    	article.setDelFlag("0");
    	Page<Article> page = articleService.findPagezjzx(new Page<Article>(request, response),article, true); 
        model.addAttribute("page", page);
		return "modules/cms/zjzx/zjzxListPass";
	}
    
    /*
     * 获取专家咨询所需相关数据
     * 20171107
     * LG
     */
    @RequestMapping(value = "zjzxform")
	public String zjzxform(String id,Article article, Model model) {
		// 如果当前传参有子节点，则选择取消传参选择		
		Category category = new Category("zjzx");
		category.setId("999");
		category.setName("专家咨询");
		if(id.equals("")){
			article.setArticleData(articleDataService.get(article.getId()));
	    }
		else
	    {
			Article article1 = articleService.getZjzx(id);	
			article.setArticleData(articleDataService.get(article1.getId()));
	    }
		article.setCategory(category);
        model.addAttribute("contentViewList",getTplContent());
        model.addAttribute("article_DEFAULT_TEMPLATE",Article.DEFAULT_TEMPLATE);
		model.addAttribute("article",article);
		CmsUtils.addViewConfigAttribute(model, article.getCategory());
		return "modules/cms/zjzx/zjzxForm";
	}
    
    @RequestMapping(value = "zjzxUpdate")
   	public String zjzxUpdate(String id, Article article, Model model) {
    	article.setSysAttachmentList(articleService.findAttatchments(article.getAttachmentName()));
   		// 如果当前传参有子节点，则选择取消传参选择		
   		Category category = new Category("zjzx");
   		category.setId("999");
   		category.setName("专家咨询");
   		if(id.equals("")){
   			article.setArticleData(articleDataService.get(article.getId()));
   	    }
   		else
   	    {
   			Article article1 = articleService.getZjzx(id);	
   			article.setArticleData(articleDataService.get(article1.getId()));
   	    }
   		article.setCategory(category);
   		List<String> list = new ArrayList<String>();
   		if(article.getImage()!=null&&article.getImage()!=""){
   			String images[] = article.getImage().split("\\|");
   			for(int i=0;i<images.length;i++){
   				String path = images[i].substring(18);
   				System.out.println(path);
   				list.add(images[i].substring(18));
   			}
   		}
        model.addAttribute("contentViewList",getTplContent());
        model.addAttribute("article_DEFAULT_TEMPLATE",Article.DEFAULT_TEMPLATE);
   		model.addAttribute("article",article);
   		model.addAttribute("imagePathList",list);
   		CmsUtils.addViewConfigAttribute(model, article.getCategory());
   		return "modules/cms/zjzx/zjzxUpdate";
   	}
    @RequestMapping(value = "zjzxReply")
   	public String zjzxReply(String id,Article article, Model model,RedirectAttributes redirectAttributes) {
    	User user = UserUtils.getUser();
    //	System.out.println("user.getZjFlag()="+user.getZjFlag());
    	if(user.getZjFlag()==null){
    		System.out.println("========");
    		addMessage(redirectAttributes,"非专家不能回复！");
    		return "redirect:"+adminPath+"/cms/article/zjzxListPass";
    		
    	}
    	System.out.println("11111111111111");
    	if(user.getZjFlag().equals("0")){
    		addMessage(redirectAttributes,"非专家不能回复！");
    		return "redirect:"+adminPath+"/cms/article/zjzxListPass";
    	}else{
    	article.setSysAttachmentList(articleService.findAttatchments(article.getAttachmentName()));
   		// 如果当前传参有子节点，则选择取消传参选择		
   		Category category = new Category("zjzx");
   		category.setId("999");
   		category.setName("专家咨询");
   		if(id.equals("")){
   			article.setArticleData(articleDataService.get(article.getId()));
   	    }
   		else
   	    {
   			Article article1 = articleService.getZjzx(id);	
   			article.setArticleData(articleDataService.get(article1.getId()));
   	    }
   		article.setCategory(category);
   		List<String> list = new ArrayList<String>();
   		if(article.getImage()!=null&&article.getImage()!=""){
   			String images[] = article.getImage().split("\\|");
   			for(int i=0;i<images.length;i++){
   				String path = images[i].substring(18);
   				System.out.println(path);
   				list.add(images[i].substring(18));
   			}
   		}
        model.addAttribute("contentViewList",getTplContent());
        model.addAttribute("article_DEFAULT_TEMPLATE",Article.DEFAULT_TEMPLATE);
   		model.addAttribute("article",article);
   		model.addAttribute("imagePathList",list);
   		CmsUtils.addViewConfigAttribute(model, article.getCategory());
   		return "modules/cms/zjzx/zjzxReply";}
   	}
    /*
     * 保存专家咨询审核结果
     * @author LG
     * 20171107
     */
	@RequestMapping(value = "savezjzxSh")
	public String savezjzx(Article article, Model model, RedirectAttributes redirectAttributes) {
		article.setDelFlag("0");
		articleService.updateZjzxSh(article);
		return "redirect:" + adminPath + "/cms/article/zjzxList";
	}
	  /*
     * 保存专家咨询相关信息
     * @author LG
     * 20171107
     */
	@RequestMapping(value = "savezjzx1")
	public String savezjzx1(Article article,HttpSession session, Model model, RedirectAttributes redirectAttributes,@RequestParam("file") CommonsMultipartFile[] files) throws IOException {
		//1.保存附件
		 if(files!=null&&files.length>0){  
	            //循环获取file数组中得文件  
			 for(CommonsMultipartFile file:files){
				 //文件，关联表，关联表记录主键
				 if(file.getSize()!=0){
				 this.articleService.upload(file,"zjzxvideo",article);
				 }
			 }
		 }
		//2.保存主表
		articleService.savezjzx(article,session);
		addMessage(redirectAttributes, "保存文章'" + StringUtils.abbr(article.getTitle(),50) + "'成功！请等待审核通过...");
		return "redirect:/f" ;
	}
	
	
	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	public String uploadFileHandler(@RequestParam("file") MultipartFile file, HttpServletResponse response)
			throws IOException, ParseException {
		
	
		FileMsg fileMsg = jjbgAttachmentService.upload(file);
		response.setContentType("text/plain");
		Gson gson = new Gson();
		String json = gson.toJson(fileMsg);
		return json;
	}
	
	/**下载
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/download")   
	public void downExcel(HttpServletResponse response,HttpServletRequest request,String id)throws Exception{
		String tempBaseDir = Global.getFileuploadTempBaseDir();
		String filePath = jjbgAttachmentService.get(id).getAhtpath();
		String fileName = jjbgAttachmentService.get(id).getAhtFilename();
		if (filePath.startsWith(tempBaseDir)) {
			FileDownload.fileDownload(response,request,filePath, fileName);
		}else{
			String pathBase = Global.getFileuploadRootDir();
			filePath = pathBase +filePath;
			FileDownload.fileDownload(response,request,filePath, fileName);
		}
	}
	
	/**下载
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/fileDownload")   
	public void downExcel(HttpServletResponse response,HttpServletRequest request,String filePath,String fileName)throws Exception{
		String tempBaseDir = Global.getFileuploadTempBaseDir();
		
		if (filePath.startsWith(tempBaseDir)) {
			FileDownload.fileDownload(response,request,filePath, fileName);
		}else{
			String pathBase = Global.getFileuploadRootDir();
			filePath = pathBase +filePath;
			FileDownload.fileDownload(response,request,filePath, fileName);
		}
	}
	
	
}
