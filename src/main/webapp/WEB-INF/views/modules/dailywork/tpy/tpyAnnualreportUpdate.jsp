<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>特派员年度考核管理</title>
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
		<li><a href="${ctx}/dailywork/tpy/tpyAnnualreport/">年度考核(待审核)</a></li>
		<li><a href="${ctx}/dailywork/tpy/tpyAnnualreport/YshtpyAnnualreportList">年度考核(已审核)</a></li>
		<li class="active"><a href="">年度考核修改</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="tpyAnnualreport" action="${ctx}/dailywork/tpy/tpyAnnualreport/updateInfo" method="post" class="form-horizontal">
		<sys:message content="${message}"/>
		<form:hidden path="id"/>
		<form:hidden path="repStatus"/>
		<fieldset>
			<table class="table-form">
				<tr>
					<td colspan="5"><h4>年度考核信息</h4></td>
				</tr>											
				<tr>
					<td class="tit">填报人</td>
					<td colspan="2"><form:input path="repTpyuser.name" readonly="true" htmlEscape="false" maxlength="50" class="required"/></td>
					<td class="tit">填报年份</td>
					<td colspan="2"><form:input path="repTime" readonly="true" htmlEscape="false" maxlength="50" class="required"/></td>
				</tr>
				<tr>
					<td class="tit">科技服务天数(天)</td>
					<td colspan="2"><form:input path="kjfwts" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">集体经济增收(万元)</td>
					<td colspan="2"><form:input path="jtjjzc" htmlEscape="false" maxlength="50" class="number  required"/></td>
				</tr>			
				<tr>
					<td class="tit">引进推广新品种新产品(个)</td>
					<td colspan="2"><form:input path="yjtgxpzxcp" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">推广新技术(项)</td>
					<td colspan="2"><form:input path="tgxjs" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">解决技术难题(个)</td>
					<td colspan="2"><form:input path="jjjsnt" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">培训农民(人次)</td>
					<td colspan="2"><form:input path="pxnm" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">创办领办培育企业或合作社(个)</td>
					<td colspan="2"><form:input path="cbqyhzs" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">带动就业人数(人)</td>
					<td colspan="2"><form:input path="ddjyrc" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">服务农村合作组织(个)</td>
					<td colspan="2"><form:input path="fwnchzzz" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">建立科技示范基地(个)</td>
					<td colspan="2"><form:input path="jlkjsfjd" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">培育科技示范户(户)</td>
					<td colspan="2"><form:input path="pykjsfh" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">举办培训班(场次)</td>
					<td colspan="2"><form:input path="jbpxb" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">培训人员(人次)</td>
					<td colspan="2"><form:input path="pxry" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">培训贫困群众(人次)</td>
					<td colspan="2"><form:input path="pxpkqz" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">实现产值(万元)</td>
					<td colspan="2"><form:input path="sxcz" htmlEscape="false" maxlength="50" class="number required"/></td>
					<td class="tit">带动增收(万元)</td>
					<td colspan="2"><form:input path="ddzs" htmlEscape="false" maxlength="50" class="number required"/></td>
				</tr>
				<tr>
					<td class="tit">创利税(万元)</td>
					<td colspan="2"><form:input path="cls" htmlEscape="false" maxlength="50" class="number required"/></td>
					<td class="tit">帮扶贫困村(个)</td>
					<td colspan="2"><form:input path="bfpkc" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">贫困户(户)</td>
					<td colspan="2"><form:input path="pkh" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">带动脱贫(户)</td>
					<td colspan="2"><form:input path="ddtp" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">贫困人口(个)</td>
					<td colspan="2"><form:input path="pkrk" htmlEscape="false" maxlength="50" class="digits"/></td>
					<td class="tit">填写时间</td>
					<td colspan="2"><form:input path="repWritenTime" htmlEscape="false" readonly="true" maxlength="50"/></td>
				</tr>
				<tr>
					<td class="tit">年度总结</td>
					<td colspan="5">
						<form:textarea path="repYearsummary" class="required" rows="5" maxlength="999" cssStyle="width:1020px"/>
					</td>
				</tr>	
				<tr>
				<td colspan="5"><h4>审核信息</h4></td>
				</tr>					
				<tr>
					<td class="tit">审核人</td>
					<td colspan="2"><form:input path="repApproperson" readonly="true" htmlEscape="false" maxlength="50" class="required"/></td>
					<td class="tit">审核时间</td>
					<td colspan="2"><form:input path="repApprotime" readonly="true"  htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>
				</tr>
				<tr>
					<td class="tit">审核状态</td>
					<td colspan="2">
					<form:input path="repStatus" readonly="true"  htmlEscape="false" maxlength="50" cssStyle="width:210px"/>
					</td>
					<td class="tit"></td>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td class="tit">审核意见</td>
					<td colspan="4">
						<form:textarea path="repApproopinion" readonly="true" class="required" rows="5" maxlength="200" cssStyle="width:1020px"/>
					</td>
				</tr>						
			</table>
		</fieldset>
		<div class="form-actions">			
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="修改" onclick="$('#flag').val('yes')"/>		
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<c:if test="${not empty testAudit.id}">
			<act:histoicFlow procInsId="${tpyDailyrecord.id}" />
		</c:if>
	</form:form>
</body>
</html>