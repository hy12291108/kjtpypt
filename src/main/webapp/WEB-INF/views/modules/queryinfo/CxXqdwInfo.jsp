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
		<li class="active"><a>需求单位信息</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="user" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<form:input path="company.name" htmlEscape="false" readonly="true" maxlength="50" class="required" type="hidden"/>
		<table id="contentTable" class="mytable">
		<tbody>
			<tr>
				<td><lable>服务对象名称:</lable></td>
				<td>
					<form:input path="name" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
				<td><lable>归属区域:</lable></td>
				<td>
					<form:input path="office.name" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
				<td><lable>单位类型:</lable></td>
				<td>
					<form:input path="corpType" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
			</tr>
			<tr>
				<td><lable>统一社会信用代码:</lable></td>
				<td>
					<form:input path="corpOrgCode" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
				<td><lable>单位成立日期:</lable></td>
				<td>
					<form:input path="corpEstDate" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
				<td><lable>Email:</lable></td>
				<td>
					<form:input path="email" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
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
				<td><lable>注册资本:</lable></td>
				<td>
					<form:input path="corpZczb" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
			</tr>
			<tr>
				<td><lable>联系人:</lable></td>
				<td>
					<form:input path="corpCorName" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
				<td><lable>电话:</lable></td>
				<td>
					<form:input path="corpCorPhone" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
				<td><lable>已投资:</lable></td>
				<td>
					<form:input path="corpInvest" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
			</tr>
			<tr>
				<td><lable>用工人数:</lable></td>
				<td>
					<form:input path="corpNumWorker" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
				<td><lable>现有规模:</lable></td>
				<td>
					<form:input path="corpScale" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
				<td><lable>去年收入:</lable></td>
				<td>
					<form:input path="corpExIncome" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
			</tr>
			<tr>
				<td><lable>技术需求:</lable></td>
				<td colspan="5">
					<form:textarea path="corpNeeds" htmlEscape="false" readonly="true" rows="2" maxlength="200" style="width:750px"/>
				</td>
				
			</tr>
			<tr>
				<td><lable>备注:</lable></td>
				<td colspan="5">
					<form:textarea path="remarks" htmlEscape="false" readonly="true" rows="2" maxlength="200" style="width:750px"/>
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
			<label class="control-label">需求单位名称:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">组织机构代码:</label>
			<div class="controls">
				<form:input path="corpOrgCode" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">注册资本:</label>
			<div class="controls">
				<form:input path="corpZczb" readonly="true" htmlEscape="false" maxlength="50" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单位成立日期:</label>
			<div class="controls">
				<form:input path="corpEstDate" readonly="true" htmlEscape="false" maxlength="50" />
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">法人代表姓名:</label>
			<div class="controls">
				<form:input path="corpLegRepName" readonly="true" htmlEscape="false" maxlength="100" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">法人电话:</label>
			<div class="controls">
				<form:input path="mobile" readonly="true" htmlEscape="false" maxlength="100"/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">联系人姓名:</label>
			<div class="controls">
				<form:input path="corpCorName" readonly="true" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人手机:</label>
			<div class="controls">
				<form:input path="phone" readonly="true" htmlEscape="false" maxlength="100"/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">联系人电话:</label>
			<div class="controls">
				<form:input path="mobile" readonly="true" htmlEscape="false" maxlength="100"/>
			</div>
		</div>					
		<div class="control-group">
			<label class="control-label">公司人数:</label>
			<div class="controls">
				<form:input path="corpNumWorker" readonly="true" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">主营业务:</label>
			<div class="controls">
				<form:input path="corpMajor" readonly="true" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">技术需求:</label>
			<div class="controls">
				<form:input path="corpNeeds" readonly="true" htmlEscape="false" maxlength="100"/>
			</div>
		</div>	-->	
		
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>