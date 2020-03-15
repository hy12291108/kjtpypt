package com.thinkgem.jeesite.modules.sys.web;


import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "${adminPath}/userRegistration")
public class UserRegistrationController  extends BaseController {
    @Autowired
    private SystemService systemService;
    @ModelAttribute
    public User get(@RequestParam(required=false) String id) {
        if (StringUtils.isNotBlank(id)){
            return systemService.getUser(id);
        }else{
            return new User();
        }
    }

    /**
     * 跳转注册分类页面
     * @return
     */
    @RequestMapping(value = {"registerSort", ""})
    public String temporaryRegister(){
        return "register/registerSort";
    }

}
