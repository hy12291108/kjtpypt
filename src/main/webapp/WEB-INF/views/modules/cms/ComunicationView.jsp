<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>用户交流</title>
<!-- 201709021加服务平台页面样式 -->
<link href="/kjtpypt/static/skines/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="/kjtpypt/static/skines/css/css.css" rel="stylesheet" type="text/css">	
<script src="/kjtpypt/static/skines/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<link href="/kjtpypt/static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<script src="/kjtpypt/static/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<link href="/kjtpypt/static/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
<script src="/kjtpypt/static/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function() {
});
function communicationSave(){
	if ($("#title").val()==""){
        $("#title").focus();
        top.$.jBox.tip('请输入标题','warning');
        return;
    }else if ($("#content").val().trim()==""){
  	  $("#content").focus();
        top.$.jBox.tip('请填写内容描述','warning');
        return;
    }else if($("#userId").val()==""){
          top.$.jBox.tip('登陆后才能发布','warning');
          return;
      }
	$("#inputForm").attr("action","/kjtpypt/a/communication/save");
	$("#inputForm").submit();
}
</script>
</head>
<body>
<div class="header">
	<div class="top">
		<h1><img src="/kjtpypt/static/skines/images/logo.png" width="560" height="42" alt=""/><span>陕西省科技特派员服务与管理系统</span></h1>
		<ul class="login">
		<c:if test="${user.id!=''&& user.id!=null}">
			<li><a href="#" class="bt">${user.name}已登录</a></li>
			<li><a href="/kjtpypt/a/UserRegister/logOff">退出</a></li>
		</c:if>
		<c:if test="${user.id==''||user.id==null}">
			<li><a href="/kjtpypt/a/UserRegister/temporaryLoginIndex" class="bt"> 登  录</a></li>
			<li>没有账户？<a href="/kjtpypt/a/UserRegister/temporary">立即注册</a></li>
		</c:if>
		</ul>
	</div>	
	<div class="navbg">
		<div class="nav">
			<ul>
				<li><a href="/kjtpypt/f/index-1.html">首  页</a></li>
					<li><a href="/kjtpypt/f/list-6.html">通知通告</a></li>
					<li><a href="/kjtpypt/f/list-2.html">动态新闻</a></li>
					<li><a href="/kjtpypt/f/list-10.html" >特派员风采</a></li>
					<li><a href="/kjtpypt/f/list-1000.html">学习园地</a></li>
					<li><a href="/kjtpypt/a/communication/listAll" class="current">用户交流</a></li>
					<li><a href="/kjtpypt/f/list-999.html">专家咨询</a></li>
					<li><a href="/kjtpypt/a/communication/contactMe">联系我们</a></li>
			</ul>
		</div>
	</div>
</div>

<div class="path">当前位置：<a href="#">用户交流</a>&gt;发布信息</div>
<div class="container">
	<div class="signin002">
		<h2><span>发布</span></h2>
		<form id="inputForm" class="form-horizontal" method="post">
		<table cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td width="20%"><label>标题：</label></td>
				<td width="80%"><input type="text" name="title" id="title" class="input-xlarge"><em>*</em></td>
			</tr>
			<tr>
				<td><label>内容描述：</label></td>
				<td>
					<textarea id="content" name="content" maxlength="200" class="input-xlarge" rows="3" style="width:700px"></textarea>
					<em>*</em>
				</td>
			</tr>
		</table>
		<input type="hidden" name="userId"  id="userId" value="${user.id}">
		<div class="btgroup form-actions">
			<input type="button" class="btn btn-primary" value="提交" onclick="communicationSave()">
			<input type="button" class="btn btn-default" value="返回">
		</div>
		</form>
	</div>
</div>
<div class="footer">陕西省科技特派员服务与管理系统 版权所有     Copyright © 2017 备案信息：陕ICP备***********</div>
</body>
</html>
