/**
 */
package com.thinkgem.jeesite.modules.attachment.web;

import java.io.IOException;

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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.attachment.service.JjbgAttachmentService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

import com.thinkgem.jeesite.modules.attachment.entity.JjbgAttachment;


/**
 * 附件Controller
 * @author 
 * @version 2017-09-16
 */
@Controller
@RequestMapping(value = "${adminPath}/jjbgAttachment/jjbgAttachment")
public class JjbgAttachmentController extends BaseController {

	@Autowired
	private JjbgAttachmentService jjbgAttachmentService;
	
	@ModelAttribute
	public JjbgAttachment get(@RequestParam(required=false) String id) {
		JjbgAttachment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = jjbgAttachmentService.get(id);
		}
		if (entity == null){
			entity = new JjbgAttachment();
		}
		return entity;
	}
	
	@RequiresPermissions("jjbgAttachment:jjbgAttachment:view")
	@RequestMapping(value = {"list", ""})
	public String list(JjbgAttachment jjbgAttachment, HttpServletRequest request, HttpServletResponse response, Model model) {
		jjbgAttachment.setCreateBy(UserUtils.getUser());
		Page<JjbgAttachment> page = jjbgAttachmentService.findPageBy(new Page<JjbgAttachment>(request, response), jjbgAttachment); 
		model.addAttribute("page", page);
		return "modules/jjbgAttachment/jjbgAttachmentList";
	}


	@RequestMapping(value = "delete")
	public String delete(JjbgAttachment jjbgAttachment, RedirectAttributes redirectAttributes){
		jjbgAttachmentService.delete(jjbgAttachment);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/dbfw/jjbgWddb";
	}
	
	@RequestMapping(value = "deleteFile")
	public void deleteFile(HttpServletResponse response,String id,JjbgAttachment jjbgAttachment, RedirectAttributes redirectAttributes) throws IOException {
		jjbgAttachment.setId(id);
		try{
			jjbgAttachmentService.delete(jjbgAttachment);
			response.getWriter().print("1");
		}catch(Exception e){
			response.getWriter().print("0");
		}
	}

}