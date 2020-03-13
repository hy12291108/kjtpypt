package com.thinkgem.jeesite.modules.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.modules.app.bean.AppResult;
import com.thinkgem.jeesite.modules.app.service.AppCmsService;

/**
 * APP 服务平台
 *
 * 赵凯浩
 * 2017年11月1日 下午12:15:14
 */
@Controller
@RequestMapping(value = "/app/cms")
public class AppCmsController {
	
	@Autowired
	private AppCmsService appCmsService;
	

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
	@RequestMapping(value = {"getArticleList"})
	@ResponseBody
	public AppResult getArticleList(String siteId, String categoryId, int number, String param) {
		return appCmsService.getArticleList(siteId, categoryId, number, param);
	}
	
	/**
	 * 内容列表
	 * @param categoryId
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value = {"list"})
	@ResponseBody
	public AppResult list(String categoryId, Integer pageNo) {
		return appCmsService.list(categoryId, pageNo);
	}
	
	/**
	 * 文章内容
	 * @param id
	 * @return
	 */
	@RequestMapping(value = {"getContent"})
	@ResponseBody
	public AppResult getContent(String id) {
		return appCmsService.getContent(id);
	}
	
	/**
	 * 用户交流列表
	 * 用户发布的交流信息记录列表
	 * @param pageNo
	 * @return
	 */
	@RequestMapping(value = {"findAllExchange"})
	@ResponseBody
	public AppResult findAllExchange(Integer pageNo) {
		return appCmsService.findAllExchange(pageNo);
	}
	
	/**
	 * 保存用户发布的交流信息
	 * @param title
	 * @param content
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = {"saveExchange"})
	@ResponseBody
	public AppResult saveExchange(String title, String content, String userId) {
		return appCmsService.saveExchange(title, content, userId);
	}
	
	/**
	 * 通过id获取用户交流详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = {"getExchange"})
	@ResponseBody
	public AppResult getExchange(String id) {
		return appCmsService.getExchange(id);
	}
	
	/**
	 * 保存评论信息
	 * @param id
	 * @param title
	 * @param content
	 * @param userId
	 * @param name
	 * @return
	 */
	@RequestMapping(value = {"saveCommet"})
	@ResponseBody
	public AppResult saveCommet(String id, String title, String content, String userId, String name){
		return appCmsService.saveCommet(id, title, content, userId, name);
	}
	
	/**
	 * 保存发布的专家咨询信息
	 * @param title
	 * @param content
	 * @param image
	 * @param newName
	 * @param oldName
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = {"saveConsult"})
	@ResponseBody
	public AppResult saveConsult(String title, String content, String image, String newName, String oldName, String userId){
		return appCmsService.saveConsult(title, content, image, newName, oldName, userId);
	}
	
	/**
	 * 专家咨询：获取评论
	 * @param pageNo
	 * @param contentId
	 * @return
	 */
	@RequestMapping(value = {"getComment"})
	@ResponseBody
	public AppResult getComment(Integer pageNo, String contentId){
		return appCmsService.getComment(pageNo, contentId);
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
	@RequestMapping(value = {"saveConsultCommet"})
	@ResponseBody
	public AppResult saveConsultCommet(String id, String title, String content, String userId, String name){
		return appCmsService.saveConsultCommet(id, title, content, userId, name);
	}
	
}
