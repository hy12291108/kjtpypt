<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>联系我们</title>
<!-- 201709021加服务平台页面样式 -->
<link href="/kjtpypt/static/skines/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="/kjtpypt/static/skines/css/css.css" rel="stylesheet" type="text/css">	
<script src="/kjtpypt/static/skines/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<script type="text/javascript">
</script>
</head>
<body>
<div class="header">
	<div class="top">
		<h1><img src="/kjtpypt/static/skines/images/logo.png" width="560" height="42" alt=""/><span>陕西省科技特派员服务与管理系统</span></h1>
		<ul class="login">
			<c:if test="${user.name!=''&&user.name!=null}">
			<li><a href="#" class="bt">${user.name}已登录</a></li>
			<li><a href="/kjtpypt/a/UserRegister/logOff">退出</a></li>
		</c:if>
		<c:if test="${user.name==''||user.name==null}">
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
					<li><a href="/kjtpypt/a/communication/listAll">用户交流</a></li>
					<li><a href="/kjtpypt/f/list-999.html">专家咨询</a></li>
					<li><a href="/kjtpypt/a/communication/contactMe"  class="current">联系我们</a></li>
			</ul>
		</div>
	</div>
</div>
<div class="path">当前位置：<a href="">联系我们</a></div>
<div class="container">
		<div class="main_sub">
		<table class="table table-bordered">
		<tr>
		<td rowspan="5"><img alt="" width="820px" src="/kjtpypt/static/skines/images/zyzxArea.png"></td>
		<td>QQ：349148975</td>
		</tr>
		<tr>
		<td>邮编：710061 </td>
		</tr>
		<tr>
		<td>联系地址：西安市雁塔区丈八五路10号 </td>
		</tr>
		<tr>
		<td>技术支持QQ：465198066</td>
		</tr>
		<tr>
		<td>业务联系电话：029-88895278</td>
		</tr>
		</table>
    	</div>  
    </div>
<div class="footer">陕西省科技特派员服务与管理系统 版权所有     Copyright © 2017 备案信息：陕ICP备***********</div>
</body>
</html>
