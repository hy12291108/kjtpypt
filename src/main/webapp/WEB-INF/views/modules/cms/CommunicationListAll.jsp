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
<script type="text/javascript">
	function searchArticle(){
		var a = $("#search").val();
		var url = "/kjtpypt/a/communication/listAll?title=" + a;
		location.replace(url);
	}
</script>
<style>
	.myCss{
		margin-top:9px
	}
</style>



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
				<li><a href="/kjtpypt/f/list-6.html" >通知通告</a></li>
				<li><a href="/kjtpypt/f/list-2.html" >动态新闻</a></li>
				<li><a href="/kjtpypt/f/list-10.html" >特派员风采</a></li>
				<li><a href="/kjtpypt/f/list-1000.html">学习园地</a></li>
				<li><a href="/kjtpypt/a/communication/listAll" class="current">用户交流</a></li>
				<li><a href="/kjtpypt/f/list-999.html">专家咨询</a></li>
				<li><a href="/kjtpypt/a/communication/contactMe">联系我们</a></li>
			</ul>
		</div>
	</div>
</div>

<div class="path">当前位置：用户交流
	<input id="search" type="text" class="input-xlarge myCss" placeholder="根据标题查询文章"/>
    <input type="button" class="btn" value="搜索" onclick="searchArticle()">
</div>
	<div class="container">
				<div class="main_sub">
					<div class="main_sub_list" >
							<ol>
							<c:forEach items="${page.list}" var="communication">
							<li><a href="/kjtpypt/a/communication/viewPage?id=${communication.id}">${fns:abbr(communication.title,96)}</a><span><fmt:formatDate value="${communication.createDate}" pattern="yyyy-MM-dd"/></span></li>
							</c:forEach>
							</ol>
			       </div>
				</div>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<div class="pagination">${page}</div>
		<script type="text/javascript">
				function page(n,s){
					if(n) $("#pageNo").val(n);
					if(s) $("#pageSize").val(s);
					location="/kjtpypt/a/communication/listAll?pageNo="+n+"&pageSize="+s;
			    	return false;
				}
			</script>
	</div>
<div class="footer">陕西省科技特派员服务与管理系统 版权所有     Copyright © 2017 备案信息：陕ICP备***********</div>
</body>
</html>
