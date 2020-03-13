<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
  <head>
	<title>特派员工作日志审批管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
  </head>

<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/dailywork/tpy/tpyDailyrecord/dpjlist">待评价列表</a></li>
		<li><a href="${ctx}/dailywork/tpy/tpyDailyrecord/ypjlist">已评价列表</a></li>
		<li class="active"><a href="${ctx}/dailywork/tpy/tpyDailyrecord/savepj">添加评价信息</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="tpyDailyrecord" action="${ctx}/dailywork/tpy/tpyDailyrecord/savepj" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<table class="table-form">
				<tr>
				<td colspan="5"><h4>日志信息</h4></td>
				</tr>							
				<tr>
					<td class="tit">填报人</td>
					<td colspan="2"><form:input path="recWriter" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>
					<td class="tit">特派员类型</td>
					<td colspan="2"><form:input path="tpytype" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>					
				</tr>
				<tr>
					<td class="tit">填报服务对象</td>
					<td colspan="2"><form:input path="recHelpObj" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>
					<td class="tit">服务对象类型</td>
					<td colspan="2"><form:input path="recHelpobjtype" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>
				</tr>
				<tr>
					<td class="tit">服务开始时间</td>
					<td colspan="2"><form:input path="recStartTime" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>
					</td>
					<td class="tit">服务结束时间</td>
					<td colspan="2"><form:input path="recEndTime" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>
					</td>
				</tr>
				<tr>
					<td class="tit">科技服务天数(天)</td>
					<td colspan="2"><form:input path="kjfwts" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">集体经济增收(万元)</td>
					<td colspan="2"><form:input path="jtjjzc" readonly="true" htmlEscape="false" maxlength="50" class="number  required"/></td>
				</tr>
			
				<tr>
					<td class="tit">引进推广新品种新产品(个)</td>
					<td colspan="2"><form:input path="yjtgxpzxcp" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">推广新技术(项)</td>
					<td colspan="2"><form:input path="tgxjs" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">解决技术难题(个)</td>
					<td colspan="2"><form:input path="jjjsnt" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">培训农民(人次)</td>
					<td colspan="2"><form:input path="pxnm" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">创办领办培育企业或合作社(个)</td>
					<td colspan="2"><form:input path="cbqyhzs" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">带动就业人数(人)</td>
					<td colspan="2"><form:input path="ddjyrc" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">服务农村合作组织(个)</td>
					<td colspan="2"><form:input path="fwnchzzz" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">建立科技示范基地(个)</td>
					<td colspan="2"><form:input path="jlkjsfjd" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">培育科技示范户(户)</td>
					<td colspan="2"><form:input path="pykjsfh" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">举办培训班(场次)</td>
					<td colspan="2"><form:input path="jbpxb" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">培训人员(人次)</td>
					<td colspan="2"><form:input path="pxry" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">培训贫困群众(人次)</td>
					<td colspan="2"><form:input path="pxpkqz" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">实现产值(万元)</td>
					<td colspan="2"><form:input path="sxcz" readonly="true" htmlEscape="false" maxlength="50" class="number required"/></td>
					<td class="tit">带动增收(万元)</td>
					<td colspan="2"><form:input path="ddzs" readonly="true" htmlEscape="false" maxlength="50" class="number required"/></td>
				</tr>
				<tr>
					<td class="tit">创利税(万元)</td>
					<td colspan="2"><form:input path="cls" readonly="true" htmlEscape="false" maxlength="50" class="number required"/></td>
					<td class="tit">帮扶贫困村(个)</td>
					<td colspan="2"><form:input path="bfpkc" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">贫困户(户)</td>
					<td colspan="2"><form:input path="pkh"  readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">带动脱贫(户)</td>
					<td colspan="2"><form:input path="ddtp" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">贫困人口(个)</td>
					<td colspan="2"><form:input path="pkrk" readonly="true" htmlEscape="false" maxlength="50" class="digits"/></td>
					<td class="tit">填报时间</td>
					<td colspan="2"><form:input path="recWrittenTime" readonly="true"  htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>
				</tr>
				<tr>
					<td class="tit">工作总结</td>
					<td colspan="5">
						<form:textarea path="recHelpContent"  readonly="true" class="required" rows="4" maxlength="1000" cssStyle="width:900px"/>
					</td>
				</tr>		
				<tr>
					<td colspan="5"><h4>审核信息</h4></td>
				</tr>	
				<tr>
					<td class="tit">审核人</td>
					<td colspan="2"><form:input path="recApproperson" readonly="true" htmlEscape="false" maxlength="50" class="required"/></td>
					<td class="tit">审核时间</td>
					<td colspan="2"><form:input path="recApprotime" readonly="true"  htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>
				</tr>
				<tr>
					<td class="tit">审核</td>
					<td colspan="2">
					<form:input path="recStatus" readonly="true"  htmlEscape="false" maxlength="50" cssStyle="width:210px"/>
					</td>
					<td class="tit"></td>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td class="tit">审核意见</td>
					<td colspan="5">
						<form:textarea path="recApproopinion" readonly="true" class="required" rows="4" maxlength="1000" cssStyle="width:900px"/>
					</td>
				</tr>	
				<tr>
					<td colspan="5"><h4>评价信息</h4></td>
				</tr>	
				<tr>
					<td class="tit">评价人</td>
					<td colspan="2"><form:input path="fwdxpjr" readonly="true" htmlEscape="false" maxlength="50" class="required"/></td>
					<td class="tit">评价时间</td>
					<td colspan="2"><form:input path="fwdxpjTime" readonly="true"  htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>
				</tr>
				<tr>
					<td class="tit">评价意见</td>
					<td colspan="5">
						<form:textarea path="fwdxpjYj"  rows="4" maxlength="1000" cssStyle="width:900px"/>
					</td>
				</tr>					
			</table>
		<div class="form-actions table-bordered-bt">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn btn-default" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>
