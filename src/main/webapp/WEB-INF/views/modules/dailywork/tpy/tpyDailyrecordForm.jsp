<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>特派员工作日志管理</title>
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
		<li><a href="${ctx}/dailywork/tpy/tpyDailyrecord/">特派员工作日志列表</a></li>
		<li class="active"><a href="${ctx}/dailywork/tpy/tpyDailyrecord/form?id=${tpyDailyrecord.id}">特派员工作日志<shiro:hasPermission name="dailywork:tpy:tpyDailyrecord:edit">${not empty tpyDailyrecord.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dailywork:tpy:tpyDailyrecord:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="tpyDailyrecord" action="${ctx}/dailywork/tpy/tpyDailyrecord/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<%-- <div class="control-group">
			<label class="control-label">填写人姓名：</label>
			<div class="controls">
				<form:input path="recWriter" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">帮扶开始时间：</label>
			<div class="controls">
				<input name="recStartTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${tpyDailyrecord.recStartTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">帮扶结束时间：</label>
			<div class="controls">
				<input name="recEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${tpyDailyrecord.recEndTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div hidden class="control-group">
			<label class="control-label">填写时间：</label>
			<div class="controls">
				<input name="recWrittenTime" type="text" readonly="true" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
			<div class="control-group">
			<label class="control-label">帮扶对象：</label>
			<div class="controls">
				<form:select path="recHelpObj" class="input-xlarge ">
					<c:forEach items="${tpyDailyrecord.recHelpObjList}" var="a1">	
					<form:option value="${a1}" label="${a1}"/>
					</c:forEach>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">帮扶内容：</label>
			<div class="controls">
				<form:textarea path="recHelpContent" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="dailywork:tpy:tpyDailyrecord:edit">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>