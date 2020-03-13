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
		function shPass(){
			$("#inputForm").attr("action","/kjtpypt/a/UserSh/tpyShResult?checkFlag=2");
		}
		function shFail(){
			$("#inputForm").attr("action","/kjtpypt/a/UserSh/tpyShResult?checkFlag=1");
		}
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
		<form:input path="company.id" value="${user.company.name}" htmlEscape="false" type="hidden" />
		<table id="contentTable" class="table  table-bordered  ">
		<tbody>
			<tr>
				<td>姓名:</td>
				<td>
			        <!--<form:input path="name" htmlEscape="false" maxlength="50" class="required" />-->
			        ${user.name}
				</td>
				<td>所属区域:</td>
				<td>
					<!--<form:input path="company.id" value="${user.office.name}" htmlEscape="false" />-->
					${user.office.name}
				</td>
				<td>籍贯:</td>
				<td>
					<!--<form:input path="tpyLocation" htmlEscape="false" maxlength="100" />-->
					${user.tpyLocation}
				</td>
			</tr>
			<tr>
				<td>工作单位名称:</td>
				<td>
			       <!-- <form:input path="tpyCompany" htmlEscape="false" maxlength="100" />-->
			        ${user.tpyCompany}
				</td>
				<td>部门:</td>
				<td>
					<!--<form:input path="tpyDept" htmlEscape="false" maxlength="50" />-->
					${user.tpyDept}
				</td>
				<td>职位:</td>
				<td>
					<!--<form:input path="tpyPosition" htmlEscape="false" maxlength="50" />-->
					${user.tpyPosition}
				</td>
			</tr>
			
			<tr>
				<td>学历:</td>
				<td>
			       <!-- <form:input path="tpyQulification" htmlEscape="false" maxlength="20" />-->
			      
			         ${fns:getDictLabels(user.tpyQulification,'tpy_qulification',user.tpyQulification)}
				</td>
				<td>身份证号:</td>
				<td>
					<!--<form:input path="tpyIdcard" htmlEscape="false" maxlength="20"/>-->
					${user.tpyIdcard}
				</td>
				<td>Email:</td>
				<td>
					<!--<form:input path="email" htmlEscape="false" maxlength="100" class="email" />-->
					${user.email}
				</td>
			</tr>
			<tr>
				<td>座机:</td>
				<td>
			       <!-- <form:input path="phone" htmlEscape="false" maxlength="100"  />-->
			        ${user.phone}
				</td>
				<td>手机:</td>
				<td>
					<!--<form:input path="mobile" htmlEscape="false" maxlength="100" />-->
					${user.mobile}
				</td>
				<td>参加工作时间:</td>
				<td>
					<!--<form:input path="tpyBeginWorkDate" htmlEscape="false" maxlength="40" />-->
					${user.tpyBeginWorkDate}
				</td>
			</tr>
			<tr>
				<td>专业:</td>
				<td>
			       <!-- <form:input path="tpyMajor" htmlEscape="false" maxlength="100" />-->
			        ${user.tpyMajor}
			        <%-- ${fns:getDictLabels(user.tpyMajor,'tpy_major',user.tpyMajor)} --%>
				</td>
				
				<td>职称:</td>
				<td>
					<!--<form:input path="tpyTitle" htmlEscape="false" maxlength="40" />-->
				<%-- 	${user.tpyTitle} --%>
					${fns:getDictLabels(user.tpyTitle,'tpy_title',user.tpyTitle)}
				</td>
				<td></td>
				<td>
					<!--<form:input path="tpySpecial" htmlEscape="false" maxlength="100" />-->
					
				</td>
			</tr>
			<tr>
				<td>特长:</td>
				<td colspan="5">
					<!--<form:textarea path="tpyAddress" htmlEscape="false" rows="1" maxlength="200" style="width:700px"/>-->
					${user.tpySpecial}
				</td>
			</tr>
			<tr>
				<td>通信地址:</td>
				<td colspan="5">
					<!--<form:textarea path="tpyAddress" htmlEscape="false" rows="1" maxlength="200" style="width:700px"/>-->
					${user.tpyAddress}
				</td>
			</tr>
			<tr>
				<td>银行账号:</td>
				<td>
			       <!-- <form:input path="bankAccount" htmlEscape="false" maxlength="100" />-->
			        ${user.bankAccount}
				</td>
				<td>开户名:</td>
				<td>
					<!--<form:input path="bankName" htmlEscape="false" maxlength="100" />-->
					${user.bankName}
				</td>
				<td>邮编:</td>
				<td>
					<!--<form:input path="tpyPostCode" htmlEscape="false" maxlength="20" />-->
					${user.tpyPostCode}
				</td>
			</tr>
			<tr>
				<td>开户行:</td>
				<td colspan="5">
					<!--<form:textarea path="bankOpen" htmlEscape="false" rows="1" maxlength="200" style="width:700px"/>-->
					${user.bankOpen}
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
						${user.checkPerson}
						</td>
						<td><lable>审核时间:</lable></td>
						<td>
						${user.checkTime}
						</td>
						
			</tr>
			<tr>
						<td>
						<lable>审核意见:</lable></td>
						<td  colspan="5">
							<textarea name="checkAdvice" rows="3"  placeholder="审核意见" style="width:800px" required></textarea>
							<span class="help-inline"><font color="red">*</font></span>
						</td>
			</tr>
			
		</tbody>
		</table>	
		<!--<div>
			<table  class="table  table-bordered">
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
		</div>	
		  <div class="control-group">
			<label class="control-label">姓名:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
		<label class="control-label">省份:</label>
		<div class="controls">
		<form:input path="company.id" value="${user.company.name}" htmlEscape="false" />
		</div>
		</div>
		<div class="control-group">
			<label class="control-label">部门:</label>
			<div class="controls">
				<form:input path="company.id" value="${user.office.name}" htmlEscape="false" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱:</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="100" class="email" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">座机:</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="100"  />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机:</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="100" />
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">身份证号:</label>
			<div class="controls">
				<form:input path="tpyIdcard" htmlEscape="false" maxlength="20"/>
			</div>
		</div>
		
			<div class="control-group">
			<label class="control-label">职称:</label>
			<div class="controls">
				<form:input path="tpyTitle" htmlEscape="false" maxlength="40" />
			</div>
		</div>
		
			<div class="control-group">
			<label class="control-label">学历:</label>
			<div class="controls">
				<form:input path="tpyQulification" htmlEscape="false" maxlength="20" />
			</div>
		</div>
		
			<div class="control-group">
			<label class="control-label">工作单位名称:</label>
			<div class="controls">
				<form:input path="tpyCompany" htmlEscape="false" maxlength="100" />
			</div>
		</div>
		
			<div class="control-group">
			<label class="control-label">工作部门:</label>
			<div class="controls">
				<form:input path="tpyDept" htmlEscape="false" maxlength="50" />
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">工作职位:</label>
			<div class="controls">
				<form:input path="tpyPosition" htmlEscape="false" maxlength="50" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参加工作时间:</label>
			<div class="controls">
				<form:input path="tpyBeginWorkDate" htmlEscape="false" maxlength="40" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">专业:</label>
			<div class="controls">
				<form:input path="tpyMajor" htmlEscape="false" maxlength="100" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">专业特长:</label>
			<div class="controls">
				<form:input path="tpySpecial" htmlEscape="false" maxlength="100" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮政编码:</label>
			<div class="controls">
				<form:input path="tpyPostCode" htmlEscape="false" maxlength="20" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地址:</label>
			<div class="controls">
				<form:input path="tpyAddress" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">户籍所在地:</label>
			<div class="controls">
				<form:input path="tpyLocation" htmlEscape="false" maxlength="100" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">银行账户:</label>
			<div class="controls">
				<form:input path="bankAccount" htmlEscape="false" maxlength="100" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">银行账户名:</label>
			<div class="controls">
				<form:input path="bankName" htmlEscape="false" maxlength="100" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开户行:</label>
			<div class="controls">
				<form:input path="bankOpen" htmlEscape="false" maxlength="100"/>
			</div>
		</div>-->
		<div class="form-actions">
			<!-- <input id="btnSubmit" class="btn btn-primary" type="submit" value="通过" onclick="return shPass();"/>&nbsp;
			<input id="btnCancel" class="btn"  type="submit" value="驳回"  onclick="return shFail();"/> -->
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保存" />&nbsp;
			<input id="btnCancel"  type="button" class="btn btn-default" value="返回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>