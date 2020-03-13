<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#no").focus();
			$("#inputForm").validate({
				rules: {
					loginName: {remote: "${ctx}/sys/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')}
				},
				messages: {
					loginName: {remote: "用户登录名已存在"},
					confirmNewPassword: {equalTo: "输入与上面相同的密码"}
				},
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
	/* 	function shPass(){
			$("#inputForm").attr("action","/kjtpypt/a/UserSh/tpyShResult?checkFlag=2");
		}
		function shFail(){
			$("#inputForm").attr("action","/kjtpypt/a/UserSh/tpyShResult?checkFlag=1");
		} */
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/UserSh/tpyShForm">特派员列表</a></li>
		<li class="active"><a href="${ctx}/UserSh/tpyInfoSh?id=${user.id}">特派员<shiro:hasPermission name="sys:user:edit">信息审核</shiro:hasPermission></a></li>
		</ul><br/>
	<form:form id="inputForm" modelAttribute="user" action="/kjtpypt/a/UserSh/tpyShResult" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}" disabled="disabled">
						<form:input path="loginName" htmlEscape="false" maxlength="50" class="required userName" type="hidden"/>
		<table id="contentTable" class="table  table-bordered  ">
			<tbody>
				<tr>
					
					<td>单位名称:</td>
					<td>
						<!--<form:input path="name" htmlEscape="false" maxlength="50" class="required" disabled="disabled"/>-->
						${user.name}
					</td>
					<td>单位类型:</td>
					<td>
						<!--<form:input path="corpType" htmlEscape="false" maxlength="100"/>-->
						<%-- ${user.corpType} --%>
						${fns:getDictLabels(user.corpType,'corp_type',user.corpType)}
					</td>
					<td>所属区域:</td>
					<td>
			            ${user.office.name}
					</td>
				</tr>
				<tr>
					<td>法定代表人:</td>
					<td>
			          <!-- <form:input path="corpLegRepName" htmlEscape="false" maxlength="100"/>-->
			           ${user.corpLegRepName}
					</td>
					<td>电话:</td>
					<td>
						<!--<form:input path="mobile" htmlEscape="false" maxlength="50" class="required"/>-->
						${user.mobile}
					</td>
					<td>Email:</td>
					<td>
						<!--<form:input path="email" htmlEscape="false" maxlength="100" class="email"/>-->
						${user.email}
					</td>
				</tr>
				<tr>
					<td>联系人姓名:</td>
					<td>
			           <!--<form:input path="corpCorName" htmlEscape="false" maxlength="100" class="required"/>-->
			           ${user.corpCorName}
					</td>
					<td>电话:</td>
					<td>
						<!--<form:input path="corpCorPhone" htmlEscape="false" maxlength="100" class="required"/>-->
						${user.corpCorPhone}
					</td>
					<td>邮政编码:</td>
					<td>
						<!--<form:input path="tpyPostCode" htmlEscape="false" maxlength="20"/>-->
						${user.tpyPostCode}
					</td>
				</tr>
				<tr>
					<td>统一社会信用代码:</td>
					<td>
			          <!-- <form:input path="corpOrgCode" htmlEscape="false" maxlength="100"/>-->
			           ${user.corpOrgCode}
					</td>
					<td>单位成立日期:</td>
					<td>
						<!--<form:input path="corpEstDate" htmlEscape="false" maxlength="100" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',isShowClear:false,maxDate:'%y-%M-%d'})"/>-->
						${user.corpEstDate}
					</td>
					<td>通信地址:</td>
					<td>
						<!--<form:input path="tpyAddress" htmlEscape="false" maxlength="100"/>-->
						${user.tpyAddress}
					</td>
				</tr>
				<tr>
					<td>银行账号:</td>
					<td>
			           <!--<form:input path="bankAccount" id="bankAccount" htmlEscape="false" maxlength="100" class="required" onchange="validateAccount()"/>-->
			           ${user.bankAccount}
					</td>
					<td>开户名:</td>
					<td>
						<!--<form:input path="bankName" htmlEscape="false" maxlength="100" class="required"/>-->
						${user.bankName}
					</td>
					<td>开户行:</td>
					<td>
						<!--<form:input path="bankOpen" htmlEscape="false" maxlength="100" class="required"/>-->
						${user.bankOpen}
					</td>
				</tr>
				<tr>
					<td>备注</td>
					<td colspan="5">
						<!--<textarea name="" style="width:700px;" class="form-control" rows="2"></textarea>
						<span class="help-inline"><font color="red">*</font> </span>-->
						${user.remarks}
					</td>
				</tr>
				<tr>
					<td>推荐表图片:</td>
					<td colspan="5">
					<c:if test="${empty imagePathList}">
					<img alt="暂无图片" src="" /> <br/>  
					</c:if>
					<c:if test="${imagePathList==null}">
					<img alt="暂无图片" src="" /> <br/>  
					</c:if>
					<c:if test="${!empty imagePathList}">
					<c:forEach items="${imagePathList}" var="imagename">  
	                <img alt="暂无图片" src="/tjImage${imagename}" style="width:500px;height:600px"/> 
	        		</c:forEach>  
					</c:if>
					<%-- <c:forEach items="${imagePathList}" var="imagename">  
	                <img alt="暂无图片" src="/tjImage${imagename}" style="width:400px;height:200px"/> <br/>  
	        		</c:forEach>  --%> 
					</td>
				</tr>
			<tr>
						<td><lable>审核结果:</lable></td>
						<td>
									<form:select path="checkFlag" class="input-xlarge">
										<form:options items="${fns:getDictList('check_flag')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
									</form:select>
									<span class="help-inline"><font color="red">*</font></span>
						</td>
						<td>
						<lable>审核人:</lable></td>
						<td>
							<input name="checkPerson" type="text" value="${user.checkPerson}" readonly>
						</td>
						<td><lable>审核时间:</lable></td>
						<td>
							<input name="checkTime" type="text" value="${user.checkTime}" readonly>
						</td>
						
				</tr>
				<tr>
				<td>
				<lable>审核意见:</lable></td>
				<td  colspan="5">
					<textarea name="checkAdvice" rows="3"  placeholder="审核意见" style="width:900px" required></textarea>
					<span class="help-inline"><font color="red">*</font></span>
				</td>
		</tr>
			
			</tbody>
		 </table>
		<%-- <div>
				<table  class="mytable">
				<tr>
						
						<td><lable>审核结果:</lable></td>
						<td>
									<form:select path="checkFlag" class="input-xlarge">
										<form:options items="${fns:getDictList('check_flag')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
									</form:select>
									<span class="help-inline"><font color="red">*</font></span>
						</td>
						<td>
						<lable>审核人:</lable></td>
						<td>
							<input name="checkPerson" type="text" value="${user.checkPerson}" readonly>
						</td>
						<td><lable>审核时间:</lable></td>
						<td>
							<input name="checkTime" type="text" value="${user.checkTime}" readonly>
						</td>
				</tr>
				<tr>
				<td>
				<lable>审核意见:</lable></td>
				<td  colspan="5">
					<textarea name="checkAdvice" rows="3"  placeholder="审核意见" style="width:1024px" required></textarea>
					<span class="help-inline"><font color="red">*</font></span>
				</td>
		</tr>
		</table>
		</div>	 --%>
	<!-- 	<div class="control-group">
			<label class="control-label">登录名:</label>
			<div class="controls">
				<input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}" disabled="disabled">
				<form:input path="loginName" htmlEscape="false" maxlength="50" class="required userName" disabled="disabled"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单位名称:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required" disabled="disabled"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单位类型:</label>
			<div class="controls">
				<form:input path="corpType" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">法定代表人:</label>
			<div class="controls">
				<form:input path="corpLegRepName" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电话:</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人姓名:</label>
			<div class="controls">
				<form:input path="corpCorName" htmlEscape="false" maxlength="100" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
				</div>
		</div>
		<div class="control-group">
			<label class="control-label">电话:</label>
			<div class="controls">
				<form:input path="corpCorPhone" htmlEscape="false" maxlength="100" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">Email:</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="100" class="email"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮政编码:</label>
			<div class="controls">
				<form:input path="tpyPostCode" htmlEscape="false" maxlength="20"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">统一社会信用代码:</label>
			<div class="controls">
				<form:input path="corpOrgCode" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单位成立日期:</label>
			<div class="controls">
				<form:input path="corpEstDate" htmlEscape="false" maxlength="100" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',isShowClear:false,maxDate:'%y-%M-%d'})"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">通信地址:</label>
			<div class="controls">
				<form:input path="tpyAddress" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">银行账户:</label>
			<div class="controls">
				<form:input path="bankAccount" id="bankAccount" htmlEscape="false" maxlength="100" class="required" onchange="validateAccount()"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">银行账户名:</label>
			<div class="controls">
				<form:input path="bankName" htmlEscape="false" maxlength="100" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开户行:</label>
			<div class="controls">
				<form:input path="bankOpen" htmlEscape="false" maxlength="100" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>	-->	
		<div class="form-actions">
			<!-- <input id="btnSubmit" class="btn btn-primary" type="submit" value="通过" onclick="return shPass();"/>&nbsp;
			<input id="btnCancel" class="btn"  type="submit" value="驳回"  onclick="return shFail();"/> -->
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保存" />&nbsp;
			<input id="btnCancel"  type="button" class="btn btn-default" value="返回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>