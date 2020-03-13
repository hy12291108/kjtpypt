/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cms.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.entity.Communication;
import com.thinkgem.jeesite.modules.cms.entity.CommunicationComment;
import com.thinkgem.jeesite.modules.cms.service.CommunicationCommentService;
import com.thinkgem.jeesite.modules.cms.service.CommunicationService;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 评论Controller
 * @author LG
 * @version 20171102
 */
@Controller
@RequestMapping(value = "${adminPath}/communicationComment")
public class CommunctionCommentController extends BaseController {

	@Autowired
	private CommunicationCommentService communicationCommentService;
	@Autowired
	private CommunicationService communicationService;
	
	@ModelAttribute
	public CommunicationComment get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return communicationCommentService.get(id);
		}else{
			return new CommunicationComment();
		}
	}
	
	@RequestMapping(value = {"save", ""})
	public String commentSave(HttpServletRequest request){
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String userId = request.getParameter("userId");
		String name = request.getParameter("name");
		String communicationId = request.getParameter("communicationId");
		CommunicationComment communicationComment = new CommunicationComment();
		communicationComment.setTitle(title);
		communicationComment.setContent(content);
		communicationComment.setCreateDate(new Date());
		communicationComment.setName(name);
		communicationComment.setUser(new User(userId,null));
		communicationComment.setCommunication(new Communication(communicationId));
		communicationComment.setDelFlag("0");
		communicationComment.setCheckFlag("0");
		communicationCommentService.insert(communicationComment);
		communicationService.get(new Communication(communicationId));
		return "redirect:" +adminPath+"/communication/viewPage?id="+communicationId;
	}
	
	/*@RequestMapping(value = {"saveIndex", ""})
	public String communicationSaveIndex(Communication communication){
		return "modules/cms/ComunicationView";
	}
	@RequestMapping("viewPage")
	public String commentPage(Communication communication,Model model){
		model.addAttribute("communication",communication);
		return "modules/cms/CommentView";
	}*/
	
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
