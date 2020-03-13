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
		<li><a href="${ctx}/sqtpy/tpy/list">申请自然人特派员</a></li>
		<li><a href="${ctx}/sqtpy/tpy/frlist">申请法人特派员</a></li>
		<li class="active"><a>法人特派员信息添加</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sqtpy/tpy/SaveSqtpy" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="personFlag"/>
		<sys:message content="${message}"/>	
		<table id="contentTable" class="table-form">
		<tbody>
			<tr>
				<td class="tit"><lable>特派员姓名:</lable></td>
				<td>
					<form:input path="name" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
				<td class="tit"><lable>公司成立时间:</lable></td>
				<td>
					<form:input path="corpEstDate" class="required" readonly="true" htmlEscape="false" maxlength="50" />
				</td>
				
			</tr>
			<tr>
				<td class="tit"><lable>法人代表姓名:</lable></td>
				<td>
					<form:input path="corpLegRepName" class="required" readonly="true" htmlEscape="false" maxlength="100" />
				</td>
				<td class="tit"><lable>法人联系方式:</lable></td>
				<td>
					<form:input path="mobile"  class="required" readonly="true" htmlEscape="false" maxlength="100"/>
				</td>							
			</tr>
			<tr>
				<td class="tit"><lable>联系人姓名:</lable></td>
				<td>
					<form:input path="corpCorName"  class="required" readonly="true" htmlEscape="false" maxlength="100"/>
				</td>
				<td class="tit"><lable>联系人联系方式:</lable></td>
				<td>
					<form:input path="corpCorPhone" class="required" readonly="true" htmlEscape="false" maxlength="100"/>
				</td>
			</tr>
			<tr>
				<td class="tit"><lable>单位类型:</lable></td>
				<td>
					<form:input path="corpType" class="required" readonly="true" htmlEscape="false" maxlength="50" />
				</td>
				<td class="tit"><lable></lable></td>
				<td>
					
				</td>
				
			</tr>
			<tr>
				<td class="tit"><lable>营业范围:</lable></td>
				<td colspan="5">
					<form:textarea path="corpScale" readonly="true" htmlEscape="false" rows="4" style="width:900px" maxlength="1000" class="required"/>
					
				</td>
			</tr>
			<tr>
				<td class="tit"><lable>科技优势与服务内容:</lable></td>
				<td colspan="5">
					<form:textarea path="corpMajor"  htmlEscape="false" rows="4" style="width:900px" maxlength="1000" class="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
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
					<form:textarea path="tpyReason" htmlEscape="false" rows="4" style="width:900px" maxlength="1000" class="required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</td>				
			</tr>
		<tbody>
		</table>
		<div hidden class="control-group">
			<label class="control-label">需求单位手机:</label>
			<div class="controls">
				<form:input path="corpCorPhone" readonly="true" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>