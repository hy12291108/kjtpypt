package com.thinkgem.jeesite.modules.sys.web;


import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.config.TpyInfoConfig;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.TpyRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 特派员注册、申报（控制层）
 * @author 白子涵
 * @version 2020-03-21
 */
@Controller
@RequestMapping(value = "${adminPath}/register")
public class TpyRegisterController extends BaseController {

    @Autowired  //项目启动时SPRING自动对该对象注入配置（该对象不需要调用）
    private TpyInfoConfig tpyInfoConfig;
    @Autowired
    private TpyRegisterService tpyRegisterService;

    @ModelAttribute
    public User get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)) {
            return tpyRegisterService.getUser(id);
        } else {
            return new User();
        }
    }

    /**
     * 用户返回注册页面
     * @return
     */
    @RequestMapping(value = "returnForm")
    private String returnForm(User user, Model model) {
        String personFlag=user.getPersonFlag();
        if(personFlag.equals(TpyInfoConfig.PERSON_FLAG_NATURE)){
            //返回自然人特派员注册页面
            return natureForm(user,model);
        }else if(personFlag.equals(TpyInfoConfig.PERSON_FLAG_CORPORATION)){
            //返回法人特派员注册页面
            return corpForm(user,model);
        }else if(personFlag.equals(TpyInfoConfig.PERSON_FLAG_REVERSE)){
            //返回反向特派员注册页面
            return reverseForm(user,model);
        }else if(personFlag.equals(TpyInfoConfig.PERSON_FLAG_TEMPORARY)){
            //返回个人用户注册页面
            return tempForm(user,model);
        }else if(personFlag.equals(TpyInfoConfig.PERSON_FLAG_ENTERPRISE)){
            //返回企业用户注册页面
            return enterForm(user,model);
        }
        return "404";
    }

    /**
     * 注册（个人）
     * @return
     */
    @RequestMapping(value = "tempForm")
    public String tempForm(User user,Model model) {
        model.addAttribute("user", user);
        return "register/temporary/userRegister";
    }
    /**
     * 注册（企业）
     * @return
     */
    @RequestMapping(value = "enterForm")
    public String enterForm(User user,Model model) {
        model.addAttribute("user", user);
        return "register/enterprise/userRegister";
    }
    /**
     * 注册页面跳转（自然人特派员）
     * @return
     */
    @RequestMapping(value = "natureForm")
    public String natureForm(User user,Model model) {
        model.addAttribute("user", user);
        return "register/nature/tpyRegister";
    }
    /**
     * 注册（法人特派员）
     * @return
     */
    @RequestMapping(value = "corpForm")
    public String corpForm(User user,Model model) {
        model.addAttribute("user", user);
        return "register/corporation/tpyRegister";
    }
    /**
     * 注册（反向特派员）
     * @return
     */
    @RequestMapping(value = "reverseForm")
    public String reverseForm(User user,Model model) {
        model.addAttribute("user", user);
        return "register/reverse/tpyRegister";
    }

    /**
     * 注册（自然人、法人、反向特派员第一次注册时调用此接口）
     * @return
     */
    @RequestMapping(value = "tpyRegister")
    public String tpyRegister(User user,Model model) {
        if (!beanValidator(model, user)){
            return returnForm(user, model);
        }
        boolean flag=tpyRegisterService.tpyRegister(user);
        if(flag){//注册成功,跳转到登陆页面
            return "modules/sys/sysLogin";  //TODO 登录页面
        }
        //注册失败，返回注册页面
        model.addAttribute("message",  "注册失败");
        return returnForm(user,model);
    }



}
