<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>注册分类</title>
<!-- <meta name="decorator" content="default"/>-->
<meta name="decorator" content="blank"/>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" /><meta name="author" content="http://jeesite.com/"/>
<meta name="renderer" content="webkit"><meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
<meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache"><meta http-equiv="Cache-Control" content="no-store">
<script src="/kjtpypt/static/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="/kjtpypt/static/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/kjtpypt/static/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>
<script src="/kjtpypt/static/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
<script src="/kjtpypt/static/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<script src="/kjtpypt/static/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="/kjtpypt/static/common/mustache.min.js" type="text/javascript"></script>
<!-- 20170906加注册页面样式 -->
<link href="/kjtpypt/static/skin/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="/kjtpypt/static/skin/css/css.css" type="text/css" rel="stylesheet" />
<script src="/kjtpypt/static/common/jeesite.js" type="text/javascript"></script>
<script type="text/javascript">var ctx = '/kjtpypt/a', ctxStatic='/kjtpypt/static';</script>		
<script>var _hmt=_hmt||[];(function(){var hm=document.createElement("script");hm.src="//hm.baidu.com/hm.js?82116c626a8d504a5c0675073362ef6f";var s=document.getElementsByTagName("script")[0];s.parentNode.insertBefore(hm,s);})();</script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				rules: {
					loginName: {remote: "/kjtpypt/a/UserRegister/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')}
				},
				messages: {
					loginName:{remote: "用户名已存在"},
					confirmNewPassword:{equalTo: "输入相同的密码"}
				},
				submitHandler: function(form){
					var loginName = $.trim($('#loginName').val());
					var newPassword = $.trim($('#newPassword').val());
					var name = $.trim($('#name').val());
					var email = $.trim($('#email').val());
					var mobile = $.trim($('#mobile').val());
					var num = /^\d*$/; //全数字
					if(loginName==""){
						top.$.jBox.tip('登录名不能为空','warning');
						$('#loginName').focus();
						return ;
					}else
					if(newPassword==""){
						top.$.jBox.tip('密码不能为空','warning');
						$('#newPassword').focus();
						return ;
					}else
					if(name==""){
						top.$.jBox.tip('姓名不能为空','warning');
						$('#name').focus();
						return ;
					}else
					if(email==""){
						top.$.jBox.tip('email不能为空','warning');
						$('#email').focus();
						return ;
					}else
					   if(!num.exec(mobile)) {
						 top.$.jBox.tip('號碼必须全为数字','warning');
					     $("#mobile").focus();
					     return ;
					   }else
					 if(mobile.length!= 11) {
					     top.$.jBox.tip('號碼长度不符,长度为11位','warning');
					     $("#mobile").focus();
					     return ;
					   }else{
					loading('正在提交，请稍等...');
					form.submit();}
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
		function temporaryRegister() {
		$("#inputForm").attr("action","/kjtpypt/a/UserRegister/tempSave");
		$("#inputForm").submit();	
		}	
	</script>
</head>
<body>
<div class="header">
	<h1><img src="/kjtpypt/static/skin/images/logo.png" width="560" height="42" alt=""/><span>陕西省科技特派员服务与管理系统</span></h1>	
</div>
	<div class="container">
		<div class="signin001">
		<h2><span>普通游客注册 </span><span>普通游客注册 </span></h2>
		<form:form id="inputForm" modelAttribute="user" method="post" class="form-horizontal">
			<table class="table  table-bordered">
					<tr>
						<td>
							<label>登录名：</label>
						</td>
						<td colspan="1">
							<input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}">
							<form:input id="loginName" path="loginName" htmlEscape="false" maxlength="50" class="userName"/>
							<span class="help-inline"><font color="red">*</font></span>
						</td>
					</tr>
					<tr>
						<td>
							<label>密码：</label>
						</td>
						<td colspan="1">
						<input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3" /><em>*</em>
						<c:if test="${empty user.id}"><span class="help-inline"> </span></c:if>
						<c:if test="${not empty user.id}"><span class="help-inline">若不修改密码，请留空。</span></c:if>
						</td>
					</tr>
					<tr>
						<td>
							<label>确认密码：</label>
						</td>
						<td colspan="3">
							<input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3" equalTo="#newPassword"/>
						   <c:if test="${empty user.id}"><span class="help-inline"></span></c:if><em>*</em>
						</td>
					</tr>
					<tr>
						<td>
							<label>姓名：</label>
						</td>
						<td colspan="3">
							<input name="name" id="name" type="text" >
							<span class="help-inline"><font color="red">*</font></span>
						</td>
					</tr>
					<tr>
						<td>
							<label>Email：</label>
						</td>
						<td colspan="3">
							<form:input path="email" id="email" htmlEscape="false" maxlength="100" class="email" />
							<span class="help-inline"><font color="red">*</font></span>
						</td>
					</tr>
					<tr>
						<td>
							<label>手机号码：</label>
						</td>
						<td colspan="3">
							<form:input path="mobile" id="mobile" htmlEscape="false" maxlength="100"  />
							<span class="help-inline"><font color="red">*</font></span>
						</td>
					</tr>
			</table>
			<input name="loginFlag" value="1"  type="hidden"/>
			<input name="personFlag" value="4"  type="hidden"/>
			<input name="checkFlag" value="2"  type="hidden"/>
			<div class="btgroup form-actions">
				<input id="btnSubmit"  type="submit" class="btn btn-primary" onclick="temporaryRegister()" value="注册">
				<input id="btnCancel"  type="button" class="btn btn-default" value="返回" onclick="history.go(-1)"/>
			</div>
		</form:form>
		</div>
	</div>
<div class="footer">陕西省科技特派员服务与管理系统 版权所有     Copyright © 2017 备案信息：陕ICP备***********</div>
</body>
</html>