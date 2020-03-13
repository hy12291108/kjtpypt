<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'protocolAdd.jsp' starting page</title>
    <meta name="decorator" content="default"/>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	.delStyle{ text-decoration: line-through; font-weight:100;}
	</style>
	<script type="text/javascript">
		function readyToSubmit() {
		if($("#file").val()==""){
			alert("请上传附件！");
			return false;			
			}
			alert("上传附件成功！");
			$("#inputForm").attr("action","${ctx}/dailywork/tpy/UploadTpyProtocol");
			$("#inputForm").submit();
		}
	</script>
</head>
  
  <body>
  	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dailywork/tpy/tpyProtocol">我的特派员服务协议</a></li>
		<%-- <li ><a href="${ctx}/dailywork/tpy/tpyProtocolEdit?id=${a1.id}" ${hidden}>修改</a></li> --%>
		<%-- <shiro:hasPermission name="dailywork:village:vilProtocol:edit">${not empty vilProtocol.id?'修改':'添加'}</shiro:hasPermission>
		<shiro:lacksPermission name="dailywork:village:vilProtocol:edit">查看</shiro:lacksPermission></a> --%>
	</ul><br/>
    	<form:form id="inputForm" modelAttribute="tpyProtocol" action="${ctx}/dailywork/tpy/UploadTpyProtocol" method="post" class="form-horizontal" enctype="multipart/form-data" >
		<form:hidden path="id"/>
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
			<form:input readonly="true" path="user.name" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电话：</label>
			<div class="controls">
			<form:input readonly="true"  path="user.phone" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">专业：</label>
			<div class="controls">
			<form:input readonly="true"  path="user.tpyMajor" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作单位：</label>
			<div class="controls">
			<form:input readonly="true"  path="user.tpyCompany" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		点击下载特派员协议书模板：
		<a href="${ctx}/dailywork/tpy/downloadFile?name=tpyTemplate.docx" style="color: red"> 特派员协议书模板</a><br>
		<hr/>
		<c:forEach items="${tpyProtocol.sysAttachmentList}" var="a1">	
		勾选删除：
		<input type="checkbox" name="checkbox" value="${a1.attName}">
		<a  href ="${ctx}/dailywork/village/vilProtocol/showImage?filename=${a1.attName}&fileType=${a1.attFolder}"  target="_blank" >${a1.attOriginname}</a></br>
		</c:forEach>
		</br>
		<span style="color:red">*</span>上传附件：
		<!-- <input type="file" name="file" accept="application/msword" > <br/> -->
	    <input type="file" name="file" multiple="multiple" id="file" > <br/> 
		<hr/>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
		</div>
		</div>	
		<div class="form-actions">
		<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="readyToSubmit()"/>
		
			<%-- <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="${tpyProtocol.html2}"/> --%>&nbsp;
			 <input ${tpyProtocol.html} id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" /> 
		</div>
	</form:form>
  </body>
</html>
