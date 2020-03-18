package com.thinkgem.jeesite.modules.sys.wrap;

import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.MajorMenuService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserToMap {

    @Autowired
    private MajorMenuService majorMenuService;

    private HashMap<String, String> data;

    /**
     * 自然人特派员用户 TO 对应MAP文档数据
     * @param user 自然人特派员用户数据
     * @return
     */
    public HashMap<String,String> getMapByNatureTpy(User user){
        try {
            data = new HashMap<String, String>();
            data.put("${name}", user.getName());
            data.put("${sex}", DictUtils.getDictLabel(user.getSex(), "sex", ""));
            data.put("${email}", user.getEmail());
            data.put("${tpyNation}", user.getTpyNation());
            data.put("${tpyLocation}", user.getTpyLocation());
            data.put("${office}", user.getOffice().getName());
            data.put("${tpyCompany}", user.getTpyCompany());
            data.put("${tpyPosition}", user.getTpyPosition());
            data.put("${tpyTitle}", DictUtils.getDictLabel(user.getTpyTitle(), "tpy_title", ""));
            data.put("${qulification}", DictUtils.getDictLabel(user.getTpyQulification(), "tpy_qulification", ""));
            data.put("${tpyPolitical}", DictUtils.getDictLabel(user.getTpyPolitical(), "political", ""));
            data.put("${mobile}", user.getMobile());
            data.put("${tpyBirthDate}", user.getTpyBirthDate());
            data.put("${tpyIdcard}", user.getTpyIdcard());
            data.put("${tpyServiceMode}", DictUtils.getDictLabel(user.getTpyServiceMode(), "service_mode", ""));
            data.put("${tpyTalentType}", DictUtils.getDictLabel(user.getTpyTalentType(), "talent_type", ""));
            data.put("${tpyMajorType}", majorMenuService.getMajorMenu(user.getTpyMajorType()).getName());
            data.put("${tpyMajor}", user.getTpyMajor());
            data.put("${tpySpecial}", user.getTpySpecial());
            data.put("${tpyNfwAddress}", user.getTpyNfwAddress());
            data.put("${tpyNfwContent}", user.getTpyNfwContent());
            data.put("${tpyExperience}", user.getTpyExperience());
            data.put("${tpyJcSituation}", user.getTpyJcSituation());
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 法人特派员用户 TO 对应MAP文档数据
     * @param user 法人特派员用户数据
     * @return
     */
    public Map<String,String> getMapByCorporationTpy(User user) {
        try {
            data = new HashMap<String, String>();
            data.put("${name}", user.getName());
            data.put("${tpyCorporateNature}", DictUtils.getDictLabel(user.getTpyCorporateNature(), "corporate_nature", ""));
            data.put("${office}", user.getOffice().getName());
            data.put("${corpType}", DictUtils.getDictLabel(user.getCorpType(), "corp_type", ""));
            data.put("${corpEstDate}", user.getCorpEstDate());
            data.put("${email}", user.getEmail());
            data.put("${corpLegRepName}", user.getCorpLegRepName());
            data.put("${tpyPosition}", user.getTpyPosition());
            data.put("${mobile}", user.getMobile());
            data.put("${tpyTitle}", DictUtils.getDictLabel(user.getTpyTitle(), "tpy_title", ""));
            data.put("${corpCorName}", user.getCorpCorName());
            data.put("${corpCorPhone}", user.getCorpCorPhone());
            data.put("${corpOrgCode}", user.getCorpOrgCode());
            data.put("${attribute}", DictUtils.getDictLabel(user.getTpyEnterpriseAttribute(), "enterprise_attribute", ""));
            data.put("${tpyAddress}", user.getTpyAddress());
            data.put("${corpScale}", user.getCorpScale());
            data.put("${tpyNfwAddress}", user.getTpyNfwAddress());
            data.put("${corpMajor}", user.getCorpMajor());
            data.put("${tpyNfwContent}", user.getTpyNfwContent());
            data.put("${tpyExperience}", user.getTpyExperience());
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 反向特派员用户 TO 对应MAP文档数据
     * @param user 反向特派员用户数据
     * @return
     */
    public Map<String,String> getMapByReverseTpy(User user) {
        try {
            data = new HashMap<String, String>();
            data.put("${name}", user.getName());
            data.put("${sex}", DictUtils.getDictLabel(user.getSex(), "sex", ""));
            data.put("${tpyLocation}", user.getTpyLocation());
            data.put("${tpyNation}", user.getTpyNation());
            data.put("${tpyPolitical}", DictUtils.getDictLabel(user.getTpyPolitical(), "political", ""));
            data.put("${birthDate}", user.getTpyBirthDate());
            data.put("${office}", user.getOffice().getName());
            data.put("${email}", user.getEmail());
            data.put("${mobile}", user.getMobile());
            data.put("${position}", user.getTpyPosition());
            data.put("${qulification}", DictUtils.getDictLabel(user.getTpyQulification(), "tpy_qulification", ""));
            data.put("${tpyTitle}", DictUtils.getDictLabel(user.getTpyTitle(), "tpy_title", ""));
            data.put("${tpyTalentType}", DictUtils.getDictLabel(user.getTpyTalentType(), "talent_type", ""));
            data.put("${tpyIdcard}", user.getTpyIdcard());
            data.put("${tpyServiceAdvantages}", DictUtils.getDictLabel(user.getTpyServiceAdvantages(), "service_advantages", ""));
            data.put("${tpyCompany}", user.getTpyCompany());
            data.put("${tpyNfwAddress}", user.getTpyNfwAddress());
            data.put("${tpyExperience}", user.getTpyExperience());
            data.put("${tpyNfwContent}", user.getTpyNfwContent());
            data.put("${tpyJcSituation}", user.getTpyJcSituation());
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
