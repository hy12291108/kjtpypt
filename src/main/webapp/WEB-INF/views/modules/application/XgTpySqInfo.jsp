<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
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
		<li><a href="${ctx}/sqtpy/tpy/Xqlist">待审核列表</a></li>
		<li><a href="${ctx}/sqtpy/tpy/YshXqlist">已审核列表</a></li>
		<li class="active"><a>自然人特派员信息修改</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="sqtpy" action="${ctx}/sqtpy/tpy/changeTpy" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="personFlag"/>
		<form:hidden path="state"/>
		<sys:message content="${message}"/>	
		<table id="contentTable" class="table-form">
		<tbody>
			<tr>
				<td colspan="4"><h4>自然人特派员信息</h4></td>
			</tr>
			<tr>
				<td class="tit"><lable>特派员姓名:</lable></td>
				<td>
					<form:input path="tpyname" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
				<td class="tit"><lable>专业:</lable></td>
				<td>
					<form:input path="zy" class="required" readonly="true" htmlEscape="false" maxlength="50" />
				</td>		
			</tr>
			<tr>
				<td class="tit"><lable>专业特长:</lable></td>
				<td>
					<form:input path="techspecial" class="required" readonly="true" htmlEscape="false" maxlength="50" />
				</td>	
				<td class="tit"><lable>所在单位:</lable></td>
				<td>
					<form:input path="company" class="required" readonly="true" htmlEscape="false" maxlength="100" />
				</td>		
			</tr>
			<tr>			
				<td class="tit"><lable>职称:</lable></td>
				<td>
					<form:input path="zc"  class="required" readonly="true" htmlEscape="false" maxlength="100"/>
				</td>
				<td class="tit"><lable>电话:</lable></td>
				<td>
					<form:input path="mobile" class="required" readonly="true" htmlEscape="false" maxlength="100"/>
				</td>
			</tr>
			<tr>		
				<td class="tit"><lable>服务开始时间:</lable></td>
				<td>
					<input id="starTime" name="starTime"  class="required" type="text" readonly="readonly" maxlength="100" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td class="tit"><lable>服务结束时间:</lable></td>
				<td>
					<input id="endTime" name="endTime" type="text"  class="required" readonly="readonly" maxlength="100"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
				</td>				
			</tr>			
			<tr>
				<td class="tit"><lable>需求信息:</lable></td>
				<td colspan="5">		
					<form:textarea path="xqdwsqReason" htmlEscape="false" rows="4" style="width:1060px" maxlength="1000" class="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</td>					
			</tr>
			<c:set var="sqtpy" scope="session" value="${sqtpy}"/>
			<c:if test="${sqtpy.state=='3' }">
			<tr>
				<td class="tit"><lable>审核建议:</lable></td>
				<td colspan="5">
					<form:textarea path="zpryj" htmlEscape="false" readonly="true" rows="4" maxlength="1000" style="width:1060px"/>
				</td>		
			</tr>
			</c:if>
		<tbody>
		</table>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="修改"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>