<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {			
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox"
			});
		});
	</script>
</head>
<body>	
<ul class="nav nav-tabs">
		<li class="active"><a >法人特派员信息</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="user" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<form:input path="company.name" htmlEscape="false" readonly="true" maxlength="50" class="required" type="hidden"/>
		<table id="contentTable" class="table-form">
		<tbody>
			<tr>
				<td><lable>特派员姓名:</lable></td>
				<td>
					<form:input path="name" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
				<td><lable>归属区域:</lable></td>
				<td>
					<form:input path="office.name" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
			</tr>
			<tr>
				<td><lable>单位类型:</lable></td>
				<td>
					<form:input path="corpType" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
				<td><lable>单位成立日期:</lable></td>
				<td>
					<form:input path="corpEstDate" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
			</tr>
			<tr>
				<td><lable>法定代表人:</lable></td>
				<td>
					<form:input path="corpLegRepName" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
				<td><lable>电话:</lable></td>
				<td>
					<form:input path="mobile" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
			</tr>
			<tr>
				<td><lable>联系人姓名:</lable></td>
				<td>
					<form:input path="corpCorName" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
				<td><lable>电话:</lable></td>
				<td>
					<form:input path="corpCorPhone" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
			</tr>
			<tr>
				
				<td><lable>科技优势与服务内容:</lable></td>
				<td colspan="5">
					<form:textarea path="corpMajor" htmlEscape="false" rows="2" readonly="true" maxlength="50" style="width:960px"  class="required"/>
				</td>
				
			</tr>
			<tr>
				
				<td><lable>营业范围:</lable></td>
				<td colspan="5">
					<form:textarea path="corpScale" htmlEscape="false" rows="2" readonly="true" maxlength="50" style="width:960px"  class="required"/>
				</td>
				
			</tr>
			<tr>
				
				<td><lable>通信地址:</lable></td>
				<td colspan="5">
					<form:textarea path="tpyAddress" htmlEscape="false" rows="2" readonly="true" maxlength="50" style="width:960px"  class="required"/>
				</td>
				
			</tr>
			<tr>
				<td><lable>银行账号:</lable></td>
				<td>
					<form:input path="bankAccount" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
				<td><lable>开户名:</lable></td>
				<td >
					<form:input path="bankName" htmlEscape="false"  readonly="true" maxlength="50"  class="required"/>
				</td>
				<td><lable></lable></td>
				<td >
					
				</td>
				
			</tr>
			<tr>
				
				<td><lable>开户行:</lable></td>
				<td colspan="5">
					<form:textarea path="bankOpen" htmlEscape="false" rows="2" readonly="true" maxlength="50" style="width:960px"   class="required"/>
				</td>
				
			</tr>
		<tbody>
		</table>
		<!--  <div class="control-group">
			<label class="control-label">归属公司:</label>
			<div class="controls">
				<form:input path="company.name" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属部门:</label>
			<div class="controls">
				<form:input path="office.name" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">特派员姓名:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别:</label>
			<div class="controls">
				<form:input path="sex" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出生年月:</label>
			<div class="controls">
				<form:input path="tpyBirthDate" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">个人住址:</label>
			<div class="controls">
				<form:input path="tpyAddress" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">专业:</label>
			<div class="controls">
				<form:input path="tpyMajor" readonly="true" htmlEscape="false" maxlength="50" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">专业特长:</label>
			<div class="controls">
				<form:input path="tpySpecial" readonly="true" htmlEscape="false" maxlength="50" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开始工作日期:</label>
			<div class="controls">
				<form:input path="tpyBeginWorkDate" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作单位:</label>
			<div class="controls">
				<form:input path="tpyCompany" readonly="true" htmlEscape="false" maxlength="100" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学历:</label>
			<div class="controls">
				<form:input path="tpyQulification" readonly="true" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">职称:</label>
			<div class="controls">
				<form:input path="tpyTitle" readonly="true" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">手机:</label>
			<div class="controls">
				<form:input path="phone" readonly="true" htmlEscape="false" maxlength="100"/>
			</div>
		</div>-->	
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>