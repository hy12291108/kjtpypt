/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.entity.Article;
import com.thinkgem.jeesite.modules.cms.entity.Comment;
import com.thinkgem.jeesite.modules.cms.entity.Communication;
import com.thinkgem.jeesite.modules.cms.entity.CommunicationComment;
import com.thinkgem.jeesite.modules.cms.service.CommentService;
import com.thinkgem.jeesite.modules.cms.service.CommunicationCommentService;
import com.thinkgem.jeesite.modules.cms.service.CommunicationService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 发布Controller
 * @author LG
 * @version 20171101
 */
@Controller
@RequestMapping(value = "${adminPath}/communication")
public class CommunctionController extends BaseController {

	@Autowired
	private CommunicationService communicationService;
	@Autowired
	private CommunicationCommentService communicationCommentService;
	
	@ModelAttribute
	public Communication get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return communicationService.get(id);
		}else{
			return new Communication();
		}
	}
	
	
	@RequestMapping(value = {"save", ""})
	public String communicationSave(HttpServletRequest request){
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String userId = request.getParameter("userId");
		Communication communication = new Communication();
		communication.setTitle(title);
		communication.setContent(content);
		communication.setCreateDate(new Date());
		communication.setUser(new User(userId,null));
		communication.setDelFlag("0");
		communication.setCheckFlag("0");
		communicationService.insert(communication);
		return "redirect:" +"/f";
	}
	
	@RequestMapping(value = {"saveIndex", ""})
	public String communicationSaveIndex(Communication communication){
		return "modules/cms/ComunicationView";
	}
	
	@RequestMapping("viewPage")
	public String commentPage(Communication communication,Model model){
		List<CommunicationComment> communicationComment = communicationCommentService.findAllList(communication.getId());
		model.addAttribute("communication",communication);
		model.addAttribute("communicationComment",communicationComment);
		return "modules/cms/CommentView";
	}
	
	@RequestMapping("listAll")
	public String findAllList(Communication communication,@RequestParam(required=false, defaultValue="1") Integer pageNo,@RequestParam(required=false)String title,
			@RequestParam(required=false, defaultValue="15") Integer pageSize,Model model,HttpServletRequest request, HttpServletResponse response){
		Page<Communication> page = new Page<Communication>(pageNo,pageSize);
		communication.setDelFlag("0");
		communication.setTitle(title);
		Page<Communication> pages = communicationService.findPage(page,communication); 
		model.addAttribute("page", pages);
	    return "modules/cms/CommunicationListAll";
	}
	
	
	
	
	@RequestMapping("InfoCheck")
	public String infoCheck(Model model){
		Communication communication = new Communication();
		CommunicationComment communicationComment  = new CommunicationComment();
		List <Communication> communicationList = communicationService.checkList(communication);
		List <CommunicationComment> communicationCommentList = communicationCommentService.findCheckList(communicationComment);
		model.addAttribute("communicationList",communicationList);
		model.addAttribute("communicationCommentList",communicationCommentList);
		return "modules/cms/CommentCheck";
	}
	@RequestMapping("Check")
	public String pageCheck(HttpServletRequest request){
		String []commentId = request.getParameterValues("commentId");
		communicationService.updateCheck(commentId);
		communicationCommentService.updateCheck(commentId);
		return "redirect:"+adminPath+"/communication/InfoCheck";
	}
	@RequestMapping("delete")
	public String pageDelete(HttpServletRequest request){
		String []commentId = request.getParameterValues("commentId");
		communicationService.updateStatus(commentId);
		communicationCommentService.updateStatus(commentId);
		return "redirect:"+adminPath+"/communication/InfoCheck";
	}
	
	
	@RequestMapping("contactMe")
	public String contactMe(Model model){
		
		return "modules/cms/ContactMe";
	}
	
	
	
/*	@ModelAttribute
	public Comment get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return commentService.get(id);
		}else{
			return new Comment();
		}
	}
	
	@RequiresPermissions("cms:comment:view")
	@RequestMapping(value = {"list", ""})
	public String list(Comment comment, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<Comment> page = commentService.findPage(new Page<Comment>(request, response), comment); 
        model.addAttribute("page", page);
		return "modules/cms/commentList";
	}

	@RequiresPermissions("cms:comment:edit")
	@RequestMapping(value = "save")
	public String save(Comment comment, RedirectAttributes redirectAttributes) {
		if (beanValidator(redirectAttributes, comment)){
			if (comment.getAuditUser() == null){
				comment.setAuditUser(UserUtils.getUser());
				comment.setAuditDate(new Date());
			}
			comment.setDelFlag(Comment.DEL_FLAG_NORMAL);
			commentService.save(comment);
			addMessage(redirectAttributes, DictUtils.getDictLabel(comment.getDelFlag(), "cms_del_flag", "保存")
					+"评论'" + StringUtils.abbr(StringUtils.replaceHtml(comment.getContent()),50) + "'成功");
		}
		return "redirect:" + adminPath + "/cms/comment/?repage&delFlag=2";
	}
	
	@RequiresPermissions("cms:comment:edit")
	@RequestMapping(value = "delete")
	public String delete(Comment comment, @RequestParam(required=false) Boolean isRe, RedirectAttributes redirectAttributes) {
		commentService.delete(comment, isRe);
		addMessage(redirectAttributes, (isRe!=null&&isRe?"恢复审核":"删除")+"评论成功");
		return "redirect:" + adminPath + "/cms/comment/?repage&delFlag=2";
	}*/

}
