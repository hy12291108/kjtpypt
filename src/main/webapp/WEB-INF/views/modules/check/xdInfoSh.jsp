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
			$("#inputForm").attr("action","/kjtpypt/a/UserSh/xdShResult?checkFlag=2");
		}
		function shFail(){
			$("#inputForm").attr("action","/kjtpypt/a/UserSh/xdShResult?checkFlag=1");
		} */
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/UserSh/xdShForm">服务对象列表</a></li>
		<li class="active"><a href="${ctx}/UserSh/xdInfoSh?id=${user.id}">服务对象<shiro:hasPermission name="sys:user:edit">信息审核</shiro:hasPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="user" action="/kjtpypt/a/UserSh/xdShResult" method="post" class="form-horizontal">
		<input name="id" value="${user.id}" type="hidden"/>
		<input name="loginName" value="${user.loginName}" type="hidden"/>
		<!-- <table id="contentTable" class="table  table-bordered  "> -->
		<table id="contentTable" class="table  table-bordered ">
		<tbody>
			<tr>
				<td><lable>单位名称:</lable></td>
				<td>
					${user.name}
			       
				</td>
				<td><lable>单位类型:</lable></td>
				<td>
					<%-- ${user.corpType}
					 --%>
					 ${fns:getDictLabels(user.corpType,'corp_type',user.corpType)}
				</td>
				<td><lable>所属区域:</lable></td>
				<td>
					${user.office.name}
				</td>
			</tr>
			<tr>
				<td><lable>法定代表人:</lable></td>
				<td>
					${user.corpLegRepName}
				</td>
				<td><lable>电话:</lable></td>
				<td>
					${user.mobile}
				</td>
				<td><lable>统一社会信用代码:</lable></td>
				<td>
					${user.corpOrgCode}
				</td>
			</tr>
			<tr>
				<td><lable>联系人:</lable></td>
				<td>
			        ${user.corpCorName}
			        
				</td>
				<td><lable>电话:</lable></td>
				<td>
					${user.corpCorPhone}
				</td>
				<td><lable>成立日期:</lable></td>
				<td>
					${user.corpEstDate}
				</td>
			</tr>
			<tr>
				<td><lable>注册资本（万元）:</lable></td>
				<td>
			        ${user.corpZczb}
				</td>
				<%-- <td><lable>已投入:</lable></td>
				<td>
					${user.corpInvest}
				</td> --%>
				<td><lable>Email:</lable></td>
				<td>
					${user.email}
				</td>
				<td><lable>去年收入（万元）:</lable></td>
				<td>
			        ${user.corpExIncome}
				</td>
			</tr>
			<tr>
				<td><lable>用工人数（人）:</lable></td>
				<td>
					${user.corpNumWorker}
				</td>
				<td><lable>资产总额（万元）:</lable></td>
				<td>
					${user.corpScale}
				</td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><lable>技术需求:</lable></td>
				<td colspan="5">
					${user.corpNeeds}
				</td>
			</tr>
			<tr>
				<td><lable>备注:</lable></td>
				<td colspan="5">
					${user.remarks}
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
					<textarea name="checkAdvice" rows="3"  placeholder="审核意见" style="width:900px" required></textarea>
					<span class="help-inline"><font color="red">*</font></span>
				</td>
		</tr>
		</tbody>
		</table>
		<%-- <div>
		<table  class="mytable">
		<tr>
				<td>
				<lable>审核人:</lable></td>
				<td>
					<input name="checkPerson" type="text" value="${user.checkPerson}" readonly>
				</td>
				<td><lable>审核时间:</lable></td>
				<td>
					<input name="checkTime" type="text" value="${user.checkTime}" readonly>
				</td>
				<td><lable>审核结果:</lable></td>
				<td>
							<form:select path="checkFlag" class="input-xlarge">
								<form:options items="${fns:getDictList('check_flag')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
							</form:select>
							<span class="help-inline"><font color="red">*</font></span>
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
		</div> --%>
		
		<div class="form-actions table-bordered-bt">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保存" />&nbsp;
			<input id="btnCancel"  type="button" class="btn btn-default" value="返回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>