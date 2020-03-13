package com.thinkgem.jeesite.modules.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.modules.app.bean.AppResult;
import com.thinkgem.jeesite.modules.app.service.StatisticalService;

import net.sf.json.JSONObject;

/**
 * APP 统计查询
 * 
 * 赵凯浩
 * 2017年8月30日 下午12:42:34
 */
@Controller
@RequestMapping(value = "/app/statistical")
public class StatisticalController {
	
	@Autowired
	private StatisticalService statisticalService;
	
	
	/**1、科技特派员名单、服务对象名单***************************************************/
	/**
	 * 查询当前管理员下的所有特派员列表
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"findTpyAll"})
	@ResponseBody
	public AppResult findTpyAll(@RequestBody JSONObject jsonObj) {
		return statisticalService.findTpyAll(jsonObj);
	}
	
	/**
	 * 查询当前管理员下的所有服务对象列表
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"findXqdwAll"})
	@ResponseBody
	public AppResult findXqdwAll(@RequestBody JSONObject jsonObj) {
		return statisticalService.findXqdwAll(jsonObj);
	}
	
	/**
	 * 根据特派员ID查询指定特派员/服务对象
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = {"findUserById"})
	@ResponseBody
	public AppResult findUserById(String userId) {
		return statisticalService.findUserById(userId);
	}
	/**1、科技特派员名单、需求单位名单***************************************************/

	
	/**2、特派员日志查询********************************************************************/
	/**
	 * 特派员日志查询
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"findTpyDailyrecordAll"})
	@ResponseBody
	public AppResult findTpyDailyrecordAll(@RequestBody JSONObject jsonObj) {
		return statisticalService.findTpyDailyrecordAll(jsonObj);
	}
	/**2、特派员日志查询********************************************************************/
	
	
	/**3、年度总结查询********************************************************************/
	/**
	 * 年度总结查询
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"findTpyAnnualreportAll"})
	@ResponseBody
	public AppResult findTpyAnnualreportAll(@RequestBody JSONObject jsonObj) {
		return statisticalService.findTpyAnnualreportAll(jsonObj);
	}
	
	/**
	 * 查看年度总结详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = {"findTpyAnnualreportById"})
	@ResponseBody
	public AppResult findTpyAnnualreportById(String id) {
		return statisticalService.findTpyAnnualreportById(id);
	}
	/**3、年度总结查询********************************************************************/
	
	
	/**4、帮扶情况查询*************************************************************************/
	/**
	 * 帮扶情况查询
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"findSqtpyAll"})
	@ResponseBody
	public AppResult findSqtpyAll(@RequestBody JSONObject jsonObj) {
		return statisticalService.findSqtpyAll(jsonObj);
	}
	
	/**
	 * 查看帮扶情况详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = {"findSqtpyById"})
	@ResponseBody
	public AppResult findSqtpyById(String id) {
		return statisticalService.findSqtpyById(id);
	}
	/**4、帮扶情况查询*************************************************************************/
	
	
	/**5、特派员统计图表**********************************************************************/
	/**
	 * 特派员统计图表
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"findTpytjviewAll"})
	@ResponseBody
	public AppResult findTpytjviewAll(@RequestBody JSONObject jsonObj){
		return statisticalService.findTpytjviewAll(jsonObj);
	}
	/**5、特派员统计图表**********************************************************************/
	
	
	/**6、专家回复统计**********************************************************************/
	/**
	 * 专家回复统计
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"findZjhftjAll"})
	@ResponseBody
	public AppResult findZjhftjAll(@RequestBody JSONObject jsonObj){
		return statisticalService.findZjhftjAll(jsonObj);
	}
	/**6、专家回复统计**********************************************************************/

	
	/**7、贫困村及特派团信息查询**********************************************************/
	/**
	 * 贫困村列表查询
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"findVillageAll"})
	@ResponseBody
	public AppResult findVillageAll(@RequestBody JSONObject jsonObj){
		return statisticalService.findVillageAll(jsonObj);
	}
	
	/**
	 * 贫困村详情及特派团信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = {"findVillageById"})
	@ResponseBody
	public AppResult findVillageById(String id) {
		return statisticalService.findVillageById(id);
	}
	
	/**
	 * 特派团人员列表
	 * @param id
	 * @return
	 */
	@RequestMapping(value = {"findServiceTeam"})
	@ResponseBody
	public AppResult findServiceTeam(String id) {
		return statisticalService.findServiceTeam(id);
	}
	/**7、贫困村及特派团信息查询**********************************************************/
	
	
	/**8、贫困村帮扶关系名单************************************************************/
	/**
	 * 贫困村帮扶关系名单
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"findServiceTeamAll"})
	@ResponseBody
	public AppResult findServiceTeamAll(@RequestBody JSONObject jsonObj){
		return statisticalService.findServiceTeamAll(jsonObj);
	}
	/**8、贫困村帮扶关系名单************************************************************/
	
	
	/**9、服务协议信息查询*********************************************************************/
	/**
	 * 服务对象协议查询
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"findFuxyAll"})
	@ResponseBody
	public AppResult findFuxyAll(@RequestBody JSONObject jsonObj){
		return statisticalService.findFuxyAll(jsonObj);
	}
	
	/**
	 * （贫困村）团队协议查询
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"findAllByTeam"})
	@ResponseBody
	public AppResult findAllByTeam(@RequestBody JSONObject jsonObj){
		return statisticalService.findAllByTeam(jsonObj);
	}
	
	/**
	 * （贫困村）个人协议查询
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"findAllByMember"})
	@ResponseBody
	public AppResult findAllByMember(@RequestBody JSONObject jsonObj){
		return statisticalService.findAllByMember(jsonObj);
	}
	/**9、服务协议信息查询*********************************************************************/
	
	
	/**10、派出单位派出科技特派员人数统计****************************************************/
	/**
	 * 派出单位派出科技特派员人数统计
	 * @param jsonObj
	 * @return
	 */
	@RequestMapping(value = {"findSendTpyNum"})
	@ResponseBody
	public AppResult findSendTpyNum(@RequestBody JSONObject jsonObj){
		return statisticalService.findSendTpyNum(jsonObj);
	}
	/**10、派出单位派出科技特派员人数统计****************************************************/
	
}
