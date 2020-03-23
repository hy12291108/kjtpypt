<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>新用户注册第一步</title>
<link href="${ctxStatic}/login/skin/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div id="wrap">
  	<div id="header">
		<div class="top">
			<h1><a href="/kjtrzpt/a/login"><span>陕西科技投融资信息化管理服务</span></a></h1>
			<ul>
            	<li>已有账号，点击<a href="/kjtrzpt/a/login">登录</a> | <a href="/kjtrzpt/f">首页</a></li>
            </ul>
		</div>
		<div class="subbanner" style="background:url(/kjtrzpt/static/login/skin/images/subbanner006.jpg) top center no-repeat;background-size:100%;"></div>
		
  	</div>
	<div id="container">
		<div class="sub forgetpd">
			<div class="submain">
				<ul class="buzhou">
					<li class="one">请选择注册类型</li>
					<li class="two">填写注册资料</li>
					<li class="three">完成注册</li>
				</ul>
                <div class="fenlei">
                    <ul>
                        <li class="geren current"><a href="${pageContext.request.contextPath}${fns:getFrontPath()}/registerTwo?userType=0">个人</a></li>
                        <li class="fwjg"><a href="${pageContext.request.contextPath}${fns:getFrontPath()}/registerTwo?userType=2">服务机构</a></li>
                        <li class="qy"><a href="${pageContext.request.contextPath}${fns:getFrontPath()}/registerTwo?userType=1">企业</a></li>
                        <li class="ky"><a href="${pageContext.request.contextPath}${fns:getFrontPath()}/registerTwo?userType=3">科研院所</a></li>
                    </ul>
                 </div>
			</div>
		</div>
	</div>
</div>
</body>
</html>
