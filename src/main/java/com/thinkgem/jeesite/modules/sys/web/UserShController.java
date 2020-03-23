/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web;


import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.ImageUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.config.TpyInfoConfig;
import com.thinkgem.jeesite.modules.sys.entity.MajorMenu;
import com.thinkgem.jeesite.modules.sys.entity.TpyInfo;
import com.thinkgem.jeesite.modules.sys.entity.TpyWorkExperience;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.MajorMenuService;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.service.TpyRegisterService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 用户Controller
 *
 * @author ThinkGem
 * @version 2013-8-29
 */
@Controller
@RequestMapping(value = "/a/UserSh")
public class UserShController extends BaseController {

    @Autowired
    private SystemService systemService;
    @Autowired
    private OfficeService officeService;
    @Autowired
    private MajorMenuService majorMenuService;
    @Autowired
    private TpyRegisterService tpyRegisterService;

    @ModelAttribute
    public User get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)) {
            return systemService.getUser(id);
        } else {
            return new User();
        }
    }

    @ModelAttribute
    public TpyInfo getTpyInfo(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)) {
            System.out.println(systemService.getTpyInfo(id));
            return systemService.getTpyInfo(id);
        } else {
            return new TpyInfo();
        }
    }

    @ModelAttribute
    public TpyWorkExperience getTpyExperience(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)) {
            System.out.println(systemService.getWorkExperience(id));
            return systemService.getWorkExperience(id);
        } else {
            return new TpyWorkExperience();
        }
    }

    /*
     * 特派员审核列表
     * @author 刘钢
     * 20170802
     */
    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = {"tpyShForm", ""})
    public String listTpy(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
        //user.setPersonFlag("0");
        user.setCheckFlag("0");
        Page<User> page = systemService.findUser(new Page<User>(request, response), user);
        model.addAttribute("page", page);
        return "modules/check/tpySh";
    }

    /*
     * 特派员审核具体信息
     * @author 刘钢
     * 20170802
     */
    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = {"tpyInfoSh", ""})
    public String tpySh(User user, Model model) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        List list = new ArrayList();
        List photoList = new ArrayList();
        String imagePath[] = null;
        String photoPath[] = null;
        user.setCheckPerson(UserUtils.getUser().getName());
        user.setCheckTime(sf.format(new Date()));
        if (user.getTjTableImage() != null && !(user.getTjTableImage().equals(""))) {
            imagePath = user.getTjTableImage().split("\\|");
            for (int i = 0; i < imagePath.length; i++) {
                String path = imagePath[i].substring(12);
                System.out.println(path);
                list.add(imagePath[i].substring(12));
            }
        }
        if (user.getPhoto() != null && !(user.getPhoto().equals(""))) {
            photoPath = user.getPhoto().split("\\|");
            for (int i = 0; i < photoPath.length; i++) {
                String path = photoPath[i].substring(12);
                System.out.println(path);
                photoList.add(photoPath[i].substring(12));
            }
        }
        model.addAttribute("user", user);
        model.addAttribute("imagePathList", list);
        model.addAttribute("photoList", photoList);
        if (user.getPersonFlag().equals("2")) {
            return "modules/check/tpyCorpInfoSh";
        } else if (user.getPersonFlag().equals("5")) {
            return "modules/check/fxTpyInfoSh";
        }
        model.addAttribute("majorType", majorMenuService.getMajorMenu(user.getTpyMajorType()).getName());
        return "modules/check/tpyInfoSh";
    }

    @RequestMapping(value = {"getImage", ""})
    public void getImage(User user, int type, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设定输出的类型
        final String JPG = "image/jpeg;charset=GB2312";
        String imagePath = null;
        if (type == 0) { //头像
            imagePath = user.getPhoto().substring(0, user.getPhoto().length() - 1);//获取真实路径
        } else if (type == 1) {  //推荐表
            imagePath = user.getTjTableImage().substring(0, user.getTjTableImage().length() - 1);//获取真实路径
        }
        response.reset();
        OutputStream output = response.getOutputStream();// 得到输出流
        if (imagePath.toLowerCase().endsWith(".jpg")) { // 使用编码处理文件流的情况：
            response.setContentType(JPG);// 设定输出的类型
            // 得到图片的真实路径
            // 得到图片的文件流
            InputStream imageIn = new FileInputStream(new File(imagePath));
            // 得到输入的编码器，将文件流进行jpg格式编码
            JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(imageIn);
            // 得到编码后的图片对象
            BufferedImage image = decoder.decodeAsBufferedImage();
            // 得到输出的编码器
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(output);
            encoder.encode(image);// 对图片进行输出编码
            imageIn.close();// 关闭文件流
        }
        output.close();
    }


    /*
     * 特派员审核结果更新
     * @author 刘钢
     * 20170802
     */
    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = {"tpyShResult", ""})
    public String tpyShResult(User user) {
        User currentUser = UserUtils.getUser();
        systemService.updateTpyShResultById(currentUser.getId(), user);

        return "redirect:" + adminPath + "/UserSh/tpyShForm/";

    }

    /*
     * 查询审核通过或不通过特派员列表
     * 刘钢
     * 20170904
     */
    @RequestMapping("passTpy")
    public String passOrNoTpy(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
        String flag = request.getParameter("flag");
        if (flag.equals("2")) {
            //user.setPersonFlag("0");
            user.setCheckFlag(flag);
            Page<User> page = systemService.findPassOrNo(new Page<User>(request, response), user);
            model.addAttribute("page", page);
            return "modules/check/passTpy";
        } else if (flag.equals("1")) {
            //user.setPersonFlag("0");
            user.setCheckFlag(flag);
            Page<User> page = systemService.findPassOrNo(new Page<User>(request, response), user);
            model.addAttribute("page", page);
            return "modules/check/failureTpy";
        } else {
            //user.setPersonFlag("0");
            user.setCheckFlag("0");
            Page<User> page = systemService.findUser(new Page<User>(request, response), user);
            model.addAttribute("page", page);
            return "modules/check/tpySh";
        }
    }

    /*
     * 需求单位审核列表
     * @author 刘钢
     * 20170804
     */
    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = {"xdShForm", ""})
    public String listXd(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
        user.setPersonFlag("1");
        user.setCheckFlag("0");
        Page<User> page = systemService.findXdList(new Page<User>(request, response), user);
        model.addAttribute("page", page);
        return "modules/check/xdSh";
    }

    /*
     * 需求单位具体信息审核
     * @author 刘钢
     * 20170804
     */
    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = {"xdInfoSh", ""})
    public String xdSh(User user, Model model) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        user.setCheckPerson(UserUtils.getUser().getName());
        user.setCheckTime(sf.format(new Date()));
        model.addAttribute("user", user);
        return "modules/check/xdInfoSh";
    }

    /*
     * 需求单位审核结果更新
     * @author 刘钢
     * 20170804
     */
    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = {"xdShResult", ""})
    public String xdShResult(User user) {
        User currentUser = UserUtils.getUser();
        systemService.updateXdShResultById(currentUser.getId(), user);

        return "redirect:" + adminPath + "/UserSh/xdShForm";

    }

    @Override
    protected void addMessage(Model model, String... messages) {
        StringBuilder sb = new StringBuilder();
        for (String message : messages) {
            sb.append(message).append(messages.length > 1 ? "<br/>" : "");
            System.out.println(sb.toString());
        }
        model.addAttribute("message", sb.toString());
    }

    /*
     * 已通过/不通过需求单位
     * @author 刘钢
     * 20170804
     */
    @RequiresPermissions("sys:user:view")
    @RequestMapping(value = {"passXd", ""})
    public String passXd(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
        String flag = request.getParameter("flag");
        if (flag.equals("2")) {
            user.setPersonFlag("1");
            user.setCheckFlag(flag);
            Page<User> page = systemService.findXdPass(new Page<User>(request, response), user);
            model.addAttribute("page", page);
            return "modules/check/passXd";
        } else if (flag.equals("1")) {
            user.setPersonFlag("1");
            user.setCheckFlag(flag);
            Page<User> page = systemService.findXdPass(new Page<User>(request, response), user);
            model.addAttribute("page", page);
            return "modules/check/failureXd";
        } else {
            user.setPersonFlag("1");
            user.setCheckFlag("0");
            Page<User> page = systemService.findXdList(new Page<User>(request, response), user);
            model.addAttribute("page", page);
            return "modules/check/xdSh";
        }
    }

    /*
     * 导出word
     */
/*	@RequestMapping("exportword")
	public void exportword(HttpServletResponse response) throws IOException{
		com.thinkgem.jeesite.modules.sys.utils.exportword.word(response);
	}*/
    /*
     * 20170907
     * @author 刘钢
     * 基本信息完善
     */
    @RequestMapping("baseInfo")
    public String baseInfo(User user, Model model) {
        user = UserUtils.getUser();
        model.addAttribute("user", user);
        //根据user的ID获取特派员的基本信息
        TpyInfo tpyInfo = systemService.getTpyInfos(user.getId());
        if (tpyInfo != null) {
            model.addAttribute("tpyInfo", tpyInfo);
        }
        if (user.getPersonFlag().equals("2")) {
            return "modules/check/baseTpyInfo";
        } else if (user.getPersonFlag().equals("1")) {
            return "modules/check/xdInfo";
        }
        return "modules/check/baseInfo";
    }

    /*
     * @author 刘钢
     * 保存基本信息
     * 20170908
     */
    @RequestMapping("InfoSave")
    public String tjInfoSaveOrUp(User user, TpyInfo tpyInfo1, TpyWorkExperience tpyWorkExperience, Model model) {
        //获取当前用户
        user = UserUtils.getUser();
        systemService.tjTableSaveOrUpDate(user, tpyInfo1, tpyWorkExperience);
        model.addAttribute("user", user);
        TpyInfo tpyInfo = systemService.getTpyInfo(user.getId());
        if (tpyInfo != null) {
            model.addAttribute("tpyInfo", tpyInfo);
        } else if (user.getPersonFlag().equals("2")) {
            return "modules/check/baseTpyInfo";
        }
        return "modules/check/baseInfo";
    }

    /*
     * 工作经历查询（所有）
     * @author 刘钢
     * 20170911
     */
    @RequestMapping("findWork")
    public String findWork(@RequestParam("tpyId") String id, Model model) {
        List<TpyWorkExperience> experience = systemService.findWork(id);
        if (experience != null) {
            model.addAttribute("experience", experience);
            return "modules/check/workExperience";
        } else {
            model.addAttribute("message", "查无数据");
            return "modules/check/workExperience";
        }
    }

    /*
     * 工作经历查询（一条）
     * @author 刘钢
     * 20170911
     */
    @RequestMapping("findOneWork")
    @ResponseBody
    public Map<String, Object> findOneWork(TpyWorkExperience experience) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (experience != null) {
            map.put("experience", experience);
            return map;
        } else {
            map.put("message", "查无数据");
            return map;
        }
    }


    /*
     * @author 刘钢
     * 删除工作经历
     * 20170911
     */
    @RequestMapping("deleteExperience")
    public String deleteExperience(@RequestParam("id") String id, Model model, TpyWorkExperience experience, RedirectAttributes redirectAttributes) {
        String tpyId = experience.getTpyInfoId();
        systemService.deleteExperience(experience);
        addMessage(redirectAttributes, "删除成功");
        return "redirect:" + adminPath + "/UserSh/findWork?tpyId=" + tpyId;
    }

    /*
     * @author 刘钢
     *  增加工作经历
     *  20170911
     */
    @RequestMapping("addExperience")
    public String addExperience(TpyWorkExperience experience, RedirectAttributes redirectAttributes) {
        String tpyId = experience.getTpyInfoId();
        systemService.addExperience(experience);
        addMessage(redirectAttributes, "添加成功");
        return "redirect:" + adminPath + "/UserSh/findWork?tpyId=" + tpyId;
    }

    /* @author 刘钢
     * 更新工作经历
     * 20170912
     */
    @RequestMapping("updateExperience")
    public String updateExperience(TpyWorkExperience experience) {
        String tpyId = experience.getTpyInfoId();
        systemService.updateExperience(experience);
        return "redirect:" + adminPath + "/UserSh/findWork?tpyId=" + tpyId;
    }

    /* @author 刘钢
     * 特派员下派
     * 20170918
     */
    @RequestMapping("tpyXp")
    public String tpyXp(String flag, User user, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (flag == null) {
            flag = "1";
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sfnd = new SimpleDateFormat("yyyy");
        String xpNd = sfnd.format(new Date());
        System.out.println("xpNd=" + xpNd);
        user.setXpNd(xpNd);
        //user.setCheckFlag("2");
        user.setXpTime(sf.format(new Date()));
        if (flag.equals("1")) {
            Page<User> page = systemService.findXpTpyUser(new Page<User>(request, response), user);
            model.addAttribute("page", page);
            model.addAttribute("time", sf.format(new Date()));
            return "modules/check/xpTpyList1";
        } else {
            Page<User> page = systemService.findXpUser(new Page<User>(request, response), user);
            model.addAttribute("page", page);
            model.addAttribute("time", sf.format(new Date()));
            return "modules/check/xpSuccessList";
        }
    }

    /*
     * @author 刘钢
     * 20170919
     * 特派员下派区域信息更新
     */
    @RequestMapping("updateXp")
    public String updateXp(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String id = request.getParameter("id");
        String areaId = request.getParameter("area.id");
        String time = request.getParameter("time");
        String startTime = request.getParameter("xpStartTime");
        String endTime = request.getParameter("xpEndTime");
        System.out.println(id);
        System.out.println(areaId);
        System.out.println(time);
        System.out.println(startTime);
        System.out.println(endTime);
        boolean flag = systemService.updateXp(id, areaId, time, startTime, endTime);
        if (flag) {
            addMessage(redirectAttributes, "下派成功");
        } else {
            addMessage(redirectAttributes, "下派失败");
        }
        return "redirect:" + adminPath + "/UserSh/tpyXp";
    }

    /**
     * 跳转上传推荐表页面
     */
    @RequestMapping(value = "uploadTjTableForm")
    public String uploadTjTableForm(Model model) {
        model.addAttribute("user", UserUtils.getUser());
        return "register/uploadTjTableForm";
    }

    /**
     * 上传推荐表,修改用户状态字段
     */
    @RequestMapping(value = "uploadTjTable")
    public String uploadTjTable(User user, Model model) {
        if (user.getCheckFlag().equals("3") && user.getTjTableImage() != "" && user.getTjTableImage() != null && systemService.uploadTjTable(user)) {
            model.addAttribute("message", "上传成功！");
        } else {
            model.addAttribute("message", "上传失败！");
        }
        model.addAttribute("user", UserUtils.getUser());
        return "register/uploadTjTableForm";
    }


    //===========================================================================//
    //=============================完善信息业务START==============================//
    //===========================================================================//

    /**
     * 特派员完善信息页面跳转
     * @return
     */
    @RequestMapping(value = "perfectInfo")
    public String perfectInfo( Model model) {
        User user=UserUtils.getUser();
        model.addAttribute("user", user);
        return returnPerfectInfo(user,model);
    }
    @RequestMapping(value = "returnPerfectInfo")
    public String returnPerfectInfo(User user,Model model) {
        //根绝角色跳转对应页面
        String personFlag=user.getPersonFlag();
        if(personFlag.equals(TpyInfoConfig.PERSON_FLAG_NATURE)){
            //专业类别菜单
            List<MajorMenu> tpyMajorTypeList = majorMenuService.findAllMajorMenu();
            model.addAttribute("tpyMajorTypeList", tpyMajorTypeList);
            //专业名称菜单
            MajorMenu majorMenu = majorMenuService.getMajorMenu(user.getTpyMajorType());
            List<MajorMenu> tpyMajorList = majorMenuService.findMajorMenuSecond(majorMenu);
            model.addAttribute("tpyMajorList", tpyMajorList);
            return "register/nature/tpyPerfectInfo";
        }else if(personFlag.equals(TpyInfoConfig.PERSON_FLAG_CORPORATION)){
            return "register/nature/tpyPerfectInfo";
        }else if(personFlag.equals(TpyInfoConfig.PERSON_FLAG_REVERSE)){
            return "register/nature/tpyPerfectInfo";
        }


        return "error";  //没有对应角色
    }


    /**
     * 完善信息保存
     */
    @RequestMapping(value = "perfectInfoSave",method = RequestMethod.POST)
    public String perfectInfoSave(User user, Model model) {
        //将数据提交到数据库（包含文件和普通表单数据）
        boolean flag=tpyRegisterService.perfectInfoSave(user);
        user=UserUtils.getUser();
        model.addAttribute("user",user);
        if(flag){
            model.addAttribute("message","保存成功");
        }else{
            model.addAttribute("message","保存失败");
        }
        return returnPerfectInfo(user,model);
    }


    /**
     * 特派员推荐表上传功能
     */
    @RequestMapping(value = "submitInfo",method = RequestMethod.POST)
    public String submitInfo(User user, Model model) {
        //将数据提交到数据库（包含文件和普通表单数据）
        boolean flag=tpyRegisterService.submitInfo(user);
        user=UserUtils.getUser();
        model.addAttribute("user",user);
        if(flag){
            model.addAttribute("message","提交成功,请等待你的审核结果！");
        }else{
            model.addAttribute("message","提交失败");
        }
        return returnPerfectInfo(user,model);
    }
    //===========================================================================//
    //=============================完善信息业务END================================//
    //===========================================================================//

}
