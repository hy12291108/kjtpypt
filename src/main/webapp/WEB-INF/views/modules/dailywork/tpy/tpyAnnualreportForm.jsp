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
		
		function DyAnnualreport(){
		var repTime=$("#repTime").val();
		var repTpyuserid=$("#repTpyuserid").val();		
						$.ajax({  
					        type : "post",  
					        /* contentType : "application/json",   */
					        url : "${ctx}/dailywork/tpy/tpyAnnualreport/selectAnnualreport", 
					        data : {"repTime":repTime,"repTpyuserid":repTpyuserid},
					        dataType : "json",  
					        contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					        success : function(data) {
					          $("#kjfwts").val(data.kjfwts);
					          $("#jtjjzc").val(data.jtjjzc);
					          $("#yjtgxpzxcp").val(data.yjtgxpzxcp);
					          $("#tgxjs").val(data.tgxjs);
					          $("#jjjsnt").val(data.jjjsnt);
					          $("#pxnm").val(data.pxnm);
					          $("#cbqyhzs").val(data.cbqyhzs);
					          $("#ddjyrc").val(data.ddjyrc);
					          $("#fwnchzzz").val(data.fwnchzzz);
					          $("#jlkjsfjd").val(data.jlkjsfjd);
					          $("#pykjsfh").val(data.pykjsfh);
					          
					          $("#jbpxb").val(data.jbpxb);
					          $("#pxry").val(data.pxry);
					          $("#pxpkqz").val(data.pxpkqz);
					          $("#sxcz").val(data.sxcz);
					          $("#ddzs").val(data.ddzs);
					          
					          $("#cls").val(data.cls);
					          $("#bfpkc").val(data.bfpkc);
					          $("#pkh").val(data.pkh);
					          $("#ddtp").val(data.ddtp);
					          $("#pkrk").val(data.pkrk);
					          alert("选择成功！");
					        },  
					        error : function() {  
					          alert("请选择年份！");  
					        }  
					      });  
				}		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/dailywork/tpy/tpyAnnualreport/">年度考核(待审核)</a></li>
		<li><a href="${ctx}/dailywork/tpy/tpyAnnualreport/YshtpyAnnualreportList">年度考核(已审核)</a></li>
		<li class="active"><a href="${ctx}/dailywork/tpy/tpyAnnualreport/form">年度考核添加</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tpyAnnualreport" action="${ctx}/dailywork/tpy/tpyAnnualreport/save" method="post" class="form-horizontal">
		<sys:message content="${message}"/>
		<form:hidden path="repTpyuser.id" id = "repTpyuserid"/>
		<fieldset>
			<legend>特派员年终总结信息填报</legend>
			<table class="table-form">											
				<tr>
					<td class="tit">填报人</td>
					<td colspan="2"><form:input path="repTpyuser.name" readonly="true" htmlEscape="false" maxlength="50" class="required"/></td>
					<td class="tit">填报年份</td>
					<td colspan="2">
					<input id="repTime" name="repTime" type="text" readonly="readonly" maxlength="100" class="required" onmouseover="WdatePicker({dateFmt:'yyyy'})"/>
					<input type="button" onclick="DyAnnualreport()" value="确定"/>
					<span class="help-inline"><font color="red">*</font> </span>
					</td>
				</tr>
				<tr>
					<td class="tit">科技服务天数(天)</td>
					<td colspan="2"><form:input path="kjfwts" id ="kjfwts" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">集体经济增收(万元)</td>
					<td colspan="2"><form:input path="jtjjzc" id ="jtjjzc" htmlEscape="false" maxlength="50" class="number  required"/></td>
				</tr>			
				<tr>
					<td class="tit">引进推广新品种新产品(个)</td>
					<td colspan="2"><form:input path="yjtgxpzxcp" id ="yjtgxpzxcp" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">推广新技术(项)</td>
					<td colspan="2"><form:input path="tgxjs" id ="tgxjs" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">解决技术难题(个)</td>
					<td colspan="2"><form:input path="jjjsnt" id ="jjjsnt" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">培训农民(人次)</td>
					<td colspan="2"><form:input path="pxnm" id ="pxnm" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">创办领办培育企业或合作社(个)</td>
					<td colspan="2"><form:input path="cbqyhzs" id ="cbqyhzs" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">带动就业人数(人)</td>
					<td colspan="2"><form:input path="ddjyrc" id ="ddjyrc" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">服务农村合作组织(个)</td>
					<td colspan="2"><form:input path="fwnchzzz" id ="fwnchzzz" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">建立科技示范基地(个)</td>
					<td colspan="2"><form:input path="jlkjsfjd" id ="jlkjsfjd" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">培育科技示范户(户)</td>
					<td colspan="2"><form:input path="pykjsfh" id ="pykjsfh" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">举办培训班(场次)</td>
					<td colspan="2"><form:input path="jbpxb" id ="jbpxb" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">培训人员(人次)</td>
					<td colspan="2"><form:input path="pxry" id ="pxry" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">培训贫困群众(人次)</td>
					<td colspan="2"><form:input path="pxpkqz" id ="pxpkqz" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">实现产值(万元)</td>
					<td colspan="2"><form:input path="sxcz" id ="sxcz" htmlEscape="false" maxlength="50" class="number required"/></td>
					<td class="tit">带动增收(万元)</td>
					<td colspan="2"><form:input path="ddzs" id ="ddzs" htmlEscape="false" maxlength="50" class="number required"/></td>
				</tr>
				<tr>
					<td class="tit">创利税(万元)</td>
					<td colspan="2"><form:input path="cls" id ="cls" htmlEscape="false" maxlength="50" class="number required"/></td>
					<td class="tit">帮扶贫困村(个)</td>
					<td colspan="2"><form:input path="bfpkc" id ="bfpkc" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">贫困户(户)</td>
					<td colspan="2"><form:input path="pkh" id ="pkh" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">带动脱贫(户)</td>
					<td colspan="2"><form:input path="ddtp" id ="ddtp" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">贫困人口(个)</td>
					<td colspan="2"><form:input path="pkrk" id ="pkrk" htmlEscape="false" maxlength="50" class="digits"/></td>
					<td class="tit">填写时间</td>
					<td colspan="2"><form:input path="repWritenTime" htmlEscape="false" readonly="true" maxlength="50"/></td>
				</tr>
				<tr>
					<td class="tit">年度总结</td>
					<td colspan="5">
						<form:textarea path="repYearsummary" class="required" rows="5" cssStyle="width:800px"/>
					</td>
				</tr>				
			</table>
		</fieldset>
		<div class="form-actions">
			
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="提交" onclick="$('#flag').val('yes')"/>		
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<c:if test="${not empty testAudit.id}">
			<act:histoicFlow procInsId="${tpyDailyrecord.id}" />
		</c:if>
	</form:form>
</body>
</html>