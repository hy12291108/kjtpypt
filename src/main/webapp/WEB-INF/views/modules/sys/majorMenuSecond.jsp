\<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>菜单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").val("");
			$("#remarks").val("");
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
		<li><a href="${ctx}/sys/majorMenu/list">专业列表</a></li>
		<li class="active"><a href="${ctx}/sys/majorMenu/add2?id=${majorMenuId}">二级级专业添加</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="majorMenu" action="${ctx}/sys/majorMenu/save" method="post" class="form-horizontal">
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">上级菜单:</label>
			<div class="controls">
				<input type="text" name="menuParentName" value="${majorMenuName}" readonly>
			</div>
		</div>
		<input type="hidden" name="menuParentId" value="${majorMenuId}" readonly>
		<div class="control-group">
			<label class="control-label">名称:</label>
			<div class="controls">
				<form:input path="name" id="name" htmlEscape="false" maxlength="50" class="required input-xlarge"/>
				<!-- <input type="text" name="menuParentId" value=""> -->
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<input type="hidden" name="menuFlag" value="1"/>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" id="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>