/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.web.front;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.servlet.ValidateCodeServlet;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.VideoUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.attachment.entity.JjbgAttachment;
import com.thinkgem.jeesite.modules.attachment.service.JjbgAttachmentService;
import com.thinkgem.jeesite.modules.cms.entity.Article;
import com.thinkgem.jeesite.modules.cms.entity.ArticleData;
import com.thinkgem.jeesite.modules.cms.entity.Category;
import com.thinkgem.jeesite.modules.cms.entity.Comment;
import com.thinkgem.jeesite.modules.cms.entity.Link;
import com.thinkgem.jeesite.modules.cms.entity.Site;
import com.thinkgem.jeesite.modules.cms.service.ArticleDataService;
import com.thinkgem.jeesite.modules.cms.service.ArticleService;
import com.thinkgem.jeesite.modules.cms.service.CategoryService;
import com.thinkgem.jeesite.modules.cms.service.CommentService;
import com.thinkgem.jeesite.modules.cms.service.LinkService;
import com.thinkgem.jeesite.modules.cms.service.SiteService;
import com.thinkgem.jeesite.modules.cms.utils.CmsUtils;
import com.thinkgem.jeesite.modules.eightMile.entity.KjtpyVideoInfo;
import com.thinkgem.jeesite.modules.eightMile.service.KjtpyVideoInfoService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 网站Controller
 * @author ThinkGem
 * @version 2013-5-29
 */
@Controller
@RequestMapping(value = "${frontPath}")
public class FrontController extends BaseController{
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleDataService articleDataService;
	@Autowired
	private LinkService linkService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private KjtpyVideoInfoService kjtpyVideoInfoService;
	
	@Autowired
	private JjbgAttachmentService jjbgAttachmentService;
	
	/**
	 * 网站首页
	 */
	@RequestMapping
	public String index(Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("site", site);
		model.addAttribute("isIndex", true);
		return "modules/cms/front/themes/"+site.getTheme()+"/frontIndex";
	}
	
	/**
	 * 网站首页
	 */
	@RequestMapping(value = "index-{siteId}${urlSuffix}")
	public String index(@PathVariable String siteId, Model model) {
		if (siteId.equals("1")){
			return "redirect:"+Global.getFrontPath();
		}
		Site site = CmsUtils.getSite(siteId);
		// 子站有独立页面，则显示独立页面
		if (StringUtils.isNotBlank(site.getCustomIndexView())){
			model.addAttribute("site", site);
			model.addAttribute("isIndex", true);
			return "modules/cms/front/themes/"+site.getTheme()+"/frontIndex"+site.getCustomIndexView();
		}
		// 否则显示子站第一个栏目
		List<Category> mainNavList = CmsUtils.getMainNavList(siteId);
		if (mainNavList.size() > 0){
			String firstCategoryId = CmsUtils.getMainNavList(siteId).get(0).getId();
			return "redirect:"+Global.getFrontPath()+"/list-"+firstCategoryId+Global.getUrlSuffix();
		}else{
			model.addAttribute("site", site);
			return "modules/cms/front/themes/"+site.getTheme()+"/frontListCategory";
		}
	}
	
	/**
	 * 内容列表
	 */
	@RequestMapping(value = "list-{categoryId}${urlSuffix}")
	public String list(@PathVariable String categoryId, @RequestParam(required=false, defaultValue="1") Integer pageNo,
			@RequestParam(required=false, defaultValue="15") Integer pageSize, Model model) {
		String[] categoryAndName = categoryId.split("-");
		String cateId = "";
		String name = "";
		Category category = null;
		if(categoryAndName.length>1){
			cateId =  categoryAndName[0];
			name = categoryAndName[1];
			category = categoryService.get(cateId);
		}else{
			category = categoryService.get(categoryId);
		}
		
		if (category==null){
			Site site = CmsUtils.getSite(Site.defaultSiteId());
			model.addAttribute("site", site);
			return "error/404";
		}
		Site site = siteService.get(category.getSite().getId());
		model.addAttribute("site", site);
		// 2：简介类栏目，栏目第一条内容
		if("2".equals(category.getShowModes()) && "article".equals(category.getModule())){
			// 如果没有子栏目，并父节点为跟节点的，栏目列表为当前栏目。
			List<Category> categoryList = Lists.newArrayList();
			if (category.getParent().getId().equals("1")){
				categoryList.add(category);
			}else{
				categoryList = categoryService.findByParentId(category.getParent().getId(), category.getSite().getId());
			}
			model.addAttribute("category", category);
			model.addAttribute("categoryList", categoryList);
			// 获取文章内容
			Page<Article> page = new Page<Article>(1, 1, -1);
			Article article = new Article(category);
			if("".equals(name)){
			}else{
				article.setTitle(name);
			}
			article.setTitle(name);
			page = articleService.findPage(page, article, false);
			if (page.getList().size()>0){
				article = page.getList().get(0);
				article.setArticleData(articleDataService.get(article.getId()));
				articleService.updateHitsAddOne(article.getId());
			}
			model.addAttribute("article", article);
            CmsUtils.addViewConfigAttribute(model, category);
            CmsUtils.addViewConfigAttribute(model, article.getViewConfig());
			return "modules/cms/front/themes/"+site.getTheme()+"/"+getTpl(article);
		}else{
			List<Category> categoryList = categoryService.findByParentId(category.getId(), category.getSite().getId());
			// 展现方式为1 、无子栏目或公共模型，显示栏目内容列表
			if("1".equals(category.getShowModes())||categoryList.size()==0){
				// 有子栏目并展现方式为1，则获取第一个子栏目；无子栏目，则获取同级分类列表。
				if(categoryList.size()>0){
					category = categoryList.get(0);
				}else{
					// 如果没有子栏目，并父节点为跟节点的，栏目列表为当前栏目。
					if (category.getParent().getId().equals("1")){
						categoryList.add(category);
					}else{
						categoryList = categoryService.findByParentId(category.getParent().getId(), category.getSite().getId());
					}
				}
				model.addAttribute("category", category);
				model.addAttribute("categoryList", categoryList);
				// 获取内容列表
				if ("article".equals(category.getModule())){
					Page<Article> page = new Page<Article>(pageNo, pageSize);
					//System.out.println(page.getPageNo());
					Article article = new Article(category);
					if("".equals(name)){
					}else{
						article.setTitle(name);
					}
					article.setTitle(name);
					page = articleService.findPage(page, article, false);
					model.addAttribute("page", page);
					// 如果第一个子栏目为简介类栏目，则获取该栏目第一篇文章
					if ("2".equals(category.getShowModes())){
						article = new Article(category);
						if (page.getList().size()>0){
							article = page.getList().get(0);
							article.setArticleData(articleDataService.get(article.getId()));
							articleService.updateHitsAddOne(article.getId());
						}
						model.addAttribute("article", article);
			            CmsUtils.addViewConfigAttribute(model, category);
			            CmsUtils.addViewConfigAttribute(model, article.getViewConfig());
						return "modules/cms/front/themes/"+site.getTheme()+"/"+getTpl(article);
					}
				}else if ("link".equals(category.getModule())){
					Page<Link> page = new Page<Link>(1, -1);
					page = linkService.findPage(page, new Link(category), false);
					model.addAttribute("page", page);
				}
				String view = "/frontList";
				if (StringUtils.isNotBlank(category.getCustomListView())){
					view = "/"+category.getCustomListView();
				}
	            CmsUtils.addViewConfigAttribute(model, category);
                site =siteService.get(category.getSite().getId());
                //System.out.println("else 栏目第一条内容 _2 :"+category.getSite().getTheme()+","+site.getTheme());
				return "modules/cms/front/themes/"+siteService.get(category.getSite().getId()).getTheme()+view;
				//return "modules/cms/front/themes/"+category.getSite().getTheme()+view;
			}
			// 有子栏目：显示子栏目列表
			else{
				model.addAttribute("category", category);
				model.addAttribute("categoryList", categoryList);
				String view = "/frontListCategory";
				if (StringUtils.isNotBlank(category.getCustomListView())){
					view = "/"+category.getCustomListView();
				}
	            CmsUtils.addViewConfigAttribute(model, category);
				return "modules/cms/front/themes/"+site.getTheme()+view;
			}
		}
	}
	
	
	
	
	
	
	

	/**
	 * 内容列表（通过url自定义视图）
	 */
	@RequestMapping(value = "listc-{categoryId}-{customView}${urlSuffix}")
	public String listCustom(@PathVariable String categoryId, @PathVariable String customView, @RequestParam(required=false, defaultValue="1") Integer pageNo,
			@RequestParam(required=false, defaultValue="15") Integer pageSize, Model model) {
		Category category = categoryService.get(categoryId);
		if (category==null){
			Site site = CmsUtils.getSite(Site.defaultSiteId());
			model.addAttribute("site", site);
			return "error/404";
		}
		Site site = siteService.get(category.getSite().getId());
		model.addAttribute("site", site);
		List<Category> categoryList = categoryService.findByParentId(category.getId(), category.getSite().getId());
		model.addAttribute("category", category);
		model.addAttribute("categoryList", categoryList);
        CmsUtils.addViewConfigAttribute(model, category);
		return "modules/cms/front/themes/"+site.getTheme()+"/frontListCategory"+customView;
	}

	/**
	 * 显示内容
	 */
	@RequestMapping(value = "view-{categoryId}-{contentId}${urlSuffix}")
	public String view(@PathVariable String categoryId, @PathVariable String contentId, Model model,HttpServletRequest request,HttpServletResponse response) {
		//专家回复
		List<String> list = new ArrayList<String>();
		List<Comment> commentList = new ArrayList<Comment>();
		Category category = categoryService.get(categoryId);
		if (category==null){
			Site site = CmsUtils.getSite(Site.defaultSiteId());
			model.addAttribute("site", site);
			return "error/404";
		}
		model.addAttribute("site", category.getSite());
		if ("article".equals(category.getModule())){
			// 如果没有子栏目，并父节点为跟节点的，栏目列表为当前栏目。
			List<Category> categoryList = Lists.newArrayList();
			if (category.getParent().getId().equals("1")){
				categoryList.add(category);
			}else{
				categoryList = categoryService.findByParentId(category.getParent().getId(), category.getSite().getId());
			}
			// 获取文章内容
			Article article = articleService.get(contentId);
			if (article==null || !Article.DEL_FLAG_NORMAL.equals(article.getDelFlag())){
				return "error/404";
			}
			
			
			
			
			// 文章阅读次数+1
			articleService.updateHitsAddOne(contentId);
			// 获取推荐文章列表
			List<Object[]> relationList = articleService.findByIds(articleDataService.get(article.getId()).getRelation());
			// 将数据传递到视图
			if(article.getAttachmentName()!=""){
				article.setSysAttachmentList(articleService.findAttatchments(article.getAttachmentName()));
			}
			//判断是否为专家咨询栏目
			if(categoryId.equals("999")){
				Comment c = new Comment();
				c.setCategory(new Category("999"));
				c.setContentId(contentId);
				c.setDelFlag(Comment.DEL_FLAG_NORMAL);
				 commentList = commentService.findLists(c);
				if(article.getImage()!=null&&article.getImage()!=""){
		   			String images[] = article.getImage().split("\\|");
		   			for(int i=0;i<images.length;i++){
		   				String path = images[i].substring(18);
		   				System.out.println(path);
		   				list.add(images[i].substring(18));
		   			}
		   		}
			//判断是否为学习园地栏目
			}else if(categoryId.equals("1000")){
				List<KjtpyVideoInfo> kjtpyVideoInfoList = kjtpyVideoInfoService.getListByArticleId(article.getId()); 
				if(!kjtpyVideoInfoList.isEmpty()){
					for(KjtpyVideoInfo s:kjtpyVideoInfoList){
					}
				}
				model.addAttribute("kjtpyVideoInfoList",kjtpyVideoInfoList);
				Integer as = Math.round(465456456);
			}
			//评论及图片
			model.addAttribute("commentList",commentList);
			model.addAttribute("imagePathList",list);
			model.addAttribute("category", categoryService.get(article.getCategory().getId()));
			model.addAttribute("categoryList", categoryList);
			article.setArticleData(articleDataService.get(article.getId()));
			model.addAttribute("article", article);
			String attachment = article.getAttachment();
            JjbgAttachment jjbgAttachment = new JjbgAttachment();
    		jjbgAttachment.setAhtOperId(article.getAttachment());
    		List<JjbgAttachment> fjlist = jjbgAttachmentService.findList(jjbgAttachment); 
    		model.addAttribute("fjlist", fjlist);
			model.addAttribute("relationList", relationList); 
            CmsUtils.addViewConfigAttribute(model, article.getCategory());
            CmsUtils.addViewConfigAttribute(model, article.getViewConfig());
            Site site = siteService.get(category.getSite().getId());
            model.addAttribute("site", site);
            System.out.println("modules/cms/front/themes/"+site.getTheme()+"/"+getTpl(article));
            return "modules/cms/front/themes/"+site.getTheme()+"/"+getTpl(article);
		}
		return "error/404";
	}
	
	/**
	 * 内容评论
	 */
	@RequestMapping(value = "comment", method=RequestMethod.GET)
	public String comment(String theme, Comment comment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Comment> page = new Page<Comment>(request, response);
		Comment c = new Comment();
		c.setCategory(comment.getCategory());
		c.setContentId(comment.getContentId());
		c.setDelFlag(Comment.DEL_FLAG_NORMAL);
		page = commentService.findPage(page, c);
		model.addAttribute("page", page);
		//model.addAttribute("comment", comment);
		return "modules/cms/front/themes/"+theme+"/frontComment";
	}
	
	/**
	 * 内容评论保存
	 */
	@ResponseBody
	@RequestMapping(value = "comment", method=RequestMethod.POST)
	public String comment(Comment comment, String validateCode,@RequestParam(required=false) String replyId, HttpServletRequest request) {
		if (StringUtils.isNotBlank(validateCode)){
			if (ValidateCodeServlet.validate(request, validateCode)){
				if (StringUtils.isNotBlank(replyId)){
					Comment replyComment = commentService.get(replyId);
					if (replyComment != null){
						comment.setContent("<div class=\"reply\">"+replyComment.getName()+":<br/>"
								+replyComment.getContent()+"</div>"+comment.getContent());
					}
				}
				comment.setIp(request.getRemoteAddr());
				comment.setCreateDate(new Date());
				//comment.setDelFlag("0");
				commentService.save(comment);
				return "{result:1, message:'提交成功。'}";
			}else{
				return "{result:2, message:'验证码不正确。'}";
			}
		}else{
			return "{result:2, message:'验证码不能为空。'}";
		}
	}
	
	/**
	 * 专家咨询回复保存
	 */
	@RequestMapping(value = "commentSave", method=RequestMethod.POST)
	public String commentSave(HttpServletRequest request) {
				Comment comment = new Comment();
				String contentId = request.getParameter("id");
				String title = request.getParameter("title");
				String categoryId = request.getParameter("category.id");
				String name = request.getParameter("name");
				String content = request.getParameter("content");
				comment.setContentId(contentId);
				comment.setTitle(title);
				comment.setCategory(new Category(categoryId));
				comment.setName(name);
				comment.setUserId(UserUtils.getUser().getId());
				comment.setContent(content);
				comment.setIp(request.getRemoteAddr());
				comment.setCreateDate(new Date());
				comment.setDelFlag("0");
				commentService.save(comment);
				return "redirect:"+"/a/cms/article/zjzxListPass/";
			
	}

	/**
	 * 专家咨询回复列表
	 * 20171108
	 * LG
	 */
	@RequestMapping(value = "commentList", method=RequestMethod.GET)
	public String commentList(String flag,String theme,Comment comment,HttpServletRequest request,HttpServletResponse response, Model model) {
		//Page<Comment> page = new Page<Comment>(request, response);
		List<Comment> commentList = new ArrayList<Comment>();
		Comment c = new Comment();
		c.setCategory(new Category("999"));
		c.setContentId(comment.getId());
		c.setDelFlag(Comment.DEL_FLAG_NORMAL);
		commentList = commentService.findLists(c);
		//page = commentService.findPage(page,c);
		Article article = articleService.get(comment.getId());
		if(article.getAttachmentName()!=""){
			article.setSysAttachmentList(articleService.findAttatchments(article.getAttachmentName()));
		}
		List<String> list = new ArrayList<String>();
   		if(article.getImage()!=null&&article.getImage()!=""){
   			String images[] = article.getImage().split("\\|");
   			for(int i=0;i<images.length;i++){
   				String path = images[i].substring(18);
   				System.out.println(path);
   				list.add(images[i].substring(18));
   			}
   		}
   		ArticleData articleData = articleDataService.get(comment.getId());
   		if(articleData!=null){
   		article.setArticleData(articleData);
   		}
   		model.addAttribute("commentList",commentList);
   		model.addAttribute("imagePathList",list);
		model.addAttribute("article",article);
		//model.addAttribute("page",page);
		return "/modules/cms/zjzx/zjzxReplyInfoList";
	}
	
	
	
	

	
	/**
	 * 站点地图
	 */
	@RequestMapping(value = "map-{siteId}${urlSuffix}")
	public String map(@PathVariable String siteId, Model model) {
		Site site = CmsUtils.getSite(siteId!=null?siteId:Site.defaultSiteId());
		model.addAttribute("site", site);
		return "modules/cms/front/themes/"+site.getTheme()+"/frontMap";
	}

    private String getTpl(Article article){
        if(StringUtils.isBlank(article.getCustomContentView())){
            String view = null;
            Category c = article.getCategory();
            boolean goon = true;
            do{
                if(StringUtils.isNotBlank(c.getCustomContentView())){
                    view = c.getCustomContentView();
                    goon = false;
                }else if(c.getParent() == null || c.getParent().isRoot()){
                    goon = false;
                }else{
                    c = c.getParent();
                }
            }while(goon);
            return StringUtils.isBlank(view) ? Article.DEFAULT_TEMPLATE : view;
        }else{
            return article.getCustomContentView();
        }
    }
    
    
    
 
	
}