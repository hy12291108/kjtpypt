package com.thinkgem.jeesite.modules.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.kjtpypt.dao.SysAttachmentDao;
import com.thinkgem.jeesite.kjtpypt.entity.SysAttachment;
import com.thinkgem.jeesite.modules.app.bean.AppConfigure;
import com.thinkgem.jeesite.modules.app.bean.AppResult;
import com.thinkgem.jeesite.modules.cms.dao.ArticleDao;
import com.thinkgem.jeesite.modules.cms.dao.ArticleDataDao;
import com.thinkgem.jeesite.modules.cms.entity.Article;
import com.thinkgem.jeesite.modules.cms.entity.ArticleData;
import com.thinkgem.jeesite.modules.cms.entity.Category;
import com.thinkgem.jeesite.modules.cms.entity.Comment;
import com.thinkgem.jeesite.modules.cms.entity.Communication;
import com.thinkgem.jeesite.modules.cms.entity.CommunicationComment;
import com.thinkgem.jeesite.modules.cms.entity.Link;
import com.thinkgem.jeesite.modules.cms.service.ArticleDataService;
import com.thinkgem.jeesite.modules.cms.service.ArticleService;
import com.thinkgem.jeesite.modules.cms.service.CategoryService;
import com.thinkgem.jeesite.modules.cms.service.CommentService;
import com.thinkgem.jeesite.modules.cms.service.CommunicationCommentService;
import com.thinkgem.jeesite.modules.cms.service.CommunicationService;
import com.thinkgem.jeesite.modules.cms.service.LinkService;
import com.thinkgem.jeesite.modules.cms.utils.CmsUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

/**
 * APP 服务平台Service
 *
 * 赵凯浩
 * 2017年11月1日 上午11:34:22
 */
@Service
@Transactional(readOnly = true)
public class AppCmsService extends BaseService{

	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private LinkService linkService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SysAttachmentDao sysAttachmentDao;
	@Autowired
	private ArticleDataDao articleDataDao;
	@Autowired
	private ArticleDataService articleDataService;
	@Autowired
	private CommunicationService communicationService;
	@Autowired
	private CommunicationCommentService communicationCommentService;
	
	
	/**
	 * 获取文章列表
	 * 目前只在服务平台主页面拉取幻灯图片新闻时使用到
	 * @param siteId 站点编号
	 * @param categoryId 分类编号
	 * @param number 获取数目
	 * @param param  预留参数，例： key1:'value1', key2:'value2' ...
	 * 			posid	推荐位（1：首页焦点图；2：栏目页文章推荐；）
	 * 			image	文章图片（1：有图片的文章）
	 *          orderBy 排序字符串
	 * @return
	 */
	@Transactional(readOnly = false)
	public AppResult getArticleList(String siteId, String categoryId, int number, String param){
		AppResult appResult = new AppResult();
		
		try {			
			List<Article> list = CmsUtils.getArticleList(siteId, categoryId, number, param);
			appResult.setObj(list);
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	
	/**
	 * 内容列表
	 * 原始方法改写
	 * @param categoryId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Transactional(readOnly = false)
	public AppResult list(String categoryId, @RequestParam(required=false, defaultValue="1") Integer pageNo){
		AppResult appResult = new AppResult();
		
		try {
			Category category = categoryService.get(categoryId);
			if (category==null){
				appResult.setSuccess(false);
				appResult.setMsg("栏目不存在");
				return appResult;
			}
			// 2：简介类栏目，栏目第一条内容
			if("2".equals(category.getShowModes()) && "article".equals(category.getModule())){
				// 如果没有子栏目，并父节点为跟节点的，栏目列表为当前栏目。
				List<Category> categoryList = Lists.newArrayList();
				if (category.getParent().getId().equals("1")){
					categoryList.add(category);
				}else{
					categoryList = categoryService.findByParentId(category.getParent().getId(), category.getSite().getId());
				}
				// 获取文章内容
				Page<Article> page = new Page<Article>(1, 1, -1);
				Article article = new Article(category);
				page = articleService.findPage(page, article, false);
				if (page.getList().size()>0){
					article = page.getList().get(0);
					article.setArticleData(articleDataService.get(article.getId()));
					articleService.updateHitsAddOne(article.getId());
				}
				
				// 返回数据
				appResult.setSubObj(article);
				
			}else{
				// 获取内容列表
				if ("article".equals(category.getModule())){
					Page<Article> page = new Page<Article>(pageNo, AppConfigure.pageSize);
					page = articleService.findPage(page, new Article(category), false);
					
					// 返回数据
					appResult.setObj(page);
					
					// 如果第一个子栏目为简介类栏目，则获取该栏目第一篇文章
					if ("2".equals(category.getShowModes())){
						Article article = new Article(category);
						if (page.getList().size()>0){
							article = page.getList().get(0);
							article.setArticleData(articleDataService.get(article.getId()));
							articleService.updateHitsAddOne(article.getId());
						}
						
						// 返回数据
						appResult.setSubObj(article);
						
					}
				}else if ("link".equals(category.getModule())){
					Page<Link> page = new Page<Link>(1, -1);
					page = linkService.findPage(page, new Link(category), false);
					
					// 返回数据
					appResult.setObj(page);
					
				}
			}
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	
	/**
	 * 文章内容详情
	 * @param contentId
	 * @return
	 */
	@Transactional(readOnly = false)
	public AppResult getContent(String id){
		AppResult appResult = new AppResult();
		
		try {
			// 1、获取文章对象
			Article article = articleService.get(id);
			article.setArticleData(articleDataService.get(article.getId()));
			// 2、文章阅读次数+1
			articleService.updateHitsAddOne(id);
			// 3、学习园地：获取视频
			if(article.getAttachmentName()!=""){
				article.setSysAttachmentList(articleService.findAttatchments(article.getAttachmentName()));
			}
			// 4、图片
			String img = "";
			if(article.getImage() != null){
				if(article.getImage().length()>12 && "999".equals(article.getCategory().getId())){
					// 专家咨询图片
					img += AppConfigure.zjzxImage + article.getImage().substring(17);
				}else{
					// 学习园地等的缩略图
					img = article.getImage();
				}
			}
			if (img!=null && img.length()>0) {
				if(img.substring(img.length()-1).equals("|")){					
					article.setImage(img.substring(0, img.length()-1));
				}else{
					article.setImage(img);
				}
			}
			// 组装返回数据
			appResult.setObj(article);
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	
	/**
	 * 用户交流列表
	 * 用户发布的交流信息记录列表
	 * @param pageNo
	 * @return
	 */
	public AppResult findAllExchange(Integer pageNo){
		AppResult appResult = new AppResult();
		
		try {
			// 1、配置分页参数
			Page<Communication> page = new Page<Communication>(pageNo, AppConfigure.pageSize);
			Communication communication = new Communication();
			communication.setDelFlag("0");
			// 2、获取数据
			page = communicationService.findPage(page, communication);
			// 组装返回数据
			appResult.setObj(page);
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	
	/**
	 * 保存用户发布的交流信息
	 * 原始方法改写
	 * @param jsonObj
	 * @return
	 */
	@Transactional(readOnly = false)
	public AppResult saveExchange(String title, String content, String userId){
		AppResult appResult = new AppResult();
		
		try {
			Communication communication = new Communication();
			communication.setTitle(title);
			communication.setContent(content);
			communication.setCreateDate(new Date());
			communication.setUser(new User(userId,null));
			communication.setDelFlag("0");
			communication.setCheckFlag("0");
			communicationService.insert(communication);
			// 组装返回数据
			appResult.setMsg("发布成功，请等待管理员审核通过");
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	
	/**
	 * 通过id获取用户交流详情
	 * @param id
	 * @return
	 */
	public AppResult getExchange(String id){
		AppResult appResult = new AppResult();
		
		try {
			Communication communication = communicationService.get(id);
			// 作者
			if(communication.getUser()!=null){
				communication.setUser(systemService.getUser(communication.getUser().getId()));
			}
			// 组装返回数据
			// 交流信息对象
			appResult.setObj(communicationService.get(id));
			// 评论列表
			appResult.setSubObj(communicationCommentService.findAllList(id));
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	
	/**
	 * 保存用户交流的评论信息
	 * 原始方法改写
	 * @param id
	 * @param title
	 * @param content
	 * @param userId
	 * @param name
	 * @return
	 */
	@Transactional(readOnly = false)
	public AppResult saveCommet(String id, String title, String content, String userId, String name){
		AppResult appResult = new AppResult();
		
		try {
			CommunicationComment communicationComment = new CommunicationComment();
			communicationComment.setTitle(title);
			communicationComment.setContent(content);
			communicationComment.setCreateDate(new Date());
			communicationComment.setName(name);
			communicationComment.setUser(new User(userId,null));
			communicationComment.setCommunication(new Communication(id));
			communicationComment.setDelFlag("0");
			communicationComment.setCheckFlag("0");
			communicationCommentService.insert(communicationComment);
			// 组装返回数据
			appResult.setMsg("评论成功，请等待管理员审核通过");
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	
	/**
	 * 保存发布的专家咨询信息
	 * @param title
	 * @param content
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = false)
	public AppResult saveConsult(String title, String content, String image, String newName, String oldName, String userId){
		AppResult appResult = new AppResult();
		
		try {
			ArticleData articleData = new ArticleData();
			Article article = new Article();
			article.preInsert();
			// 标题
			article.setTitle(title);
			// 内容
			articleData.setContent(content);
			article.setArticleData(articleData);
			// 创建人、创建时间
			article.setCreateBy(new User(userId));
			article.setCreateDate(new Date());
			// 状态
			article.setDelFlag("2");
			// 专家咨询栏目
			article.setCategory(new Category("999"));
			// 图片
			article.setImage(image);
			// 保存视频
			saveVideo(newName, oldName,"xxydvideo",article);
			// 保存文章内容
			saveArticleData(articleData, article.getId());
			// 保存文章
			articleDao.insert(article);
			// 返回结果
			appResult.setMsg("发布成功，请等待管理员审核通过");
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	// 保存视频
	private void saveVideo(String newName, String oldName, String attTable, Article article) throws Exception{
		if(newName!=null){        	
			SysAttachment sysAttachment = new SysAttachment();
	        sysAttachment.preInsert();
	        article.setAttachmentName(sysAttachment.getId());
	        sysAttachment.setAttTable(attTable);
	        sysAttachment.setAttRecordid(sysAttachment.getId());
	        sysAttachment.setAttOriginname(oldName);
	        sysAttachment.setAttName(newName);
	    	sysAttachment.setAttFolder("doc");
	    	sysAttachmentDao.insert(sysAttachment);
	    	articleDao.updateAttachmentName(article);
        }
	}
	// 保存文章内容
	private void saveArticleData(ArticleData articleData, String articleId) throws Exception{
		articleData.setId(articleId);
		articleDataDao.insert(articleData);
	}
	
	/**
	 * 专家咨询：获取评论列表
	 * @param pageNo
	 * @param categoryId
	 * @param contentId
	 * @return
	 */
	@Transactional(readOnly = false)
	public AppResult getComment(Integer pageNo, String contentId){
		AppResult appResult = new AppResult();

		try {
			Page<Comment> page = new Page<Comment>(pageNo, AppConfigure.pageSize);
			Comment comment = new Comment();
			comment.setCategory(new Category("999"));
			comment.setContentId(contentId);
			comment.setDelFlag(Comment.DEL_FLAG_NORMAL);
			comment.setPage(page);
			List<Comment> list = commentService.findLists(comment);
			page.setList(list);
			appResult.setObj(page);
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	
	/**
	 * 保存专家咨询的评论信息
	 * @param id
	 * @param title
	 * @param content
	 * @param userId
	 * @param name
	 * @return
	 */
	@Transactional(readOnly = false)
	public AppResult saveConsultCommet(String id, String title, String content, String userId, String name){
		AppResult appResult = new AppResult();
		
		try {
			Comment comment = new Comment();
			comment.preInsert();
			comment.setContentId(id);
			comment.setTitle(title);
			comment.setCategory(new Category("999"));
			comment.setUserId(userId);
			comment.setName(name);
			comment.setContent(content);
			comment.setCreateBy(new User(userId));
			comment.setCreateDate(new Date());
			comment.setDelFlag("0");
			comment.setIsNewRecord(true);
			commentService.save(comment);
			// 组装返回数据
			appResult.setMsg("评论成功，请等待管理员审核通过");
		} catch (Exception e) {
			appResult.setSuccess(false);
			appResult.setMsg(AppConfigure.MSG_Exception);
			logger.error(AppConfigure.APP_ERR + e.toString());
		}
		
		return appResult;
	}
	
}
