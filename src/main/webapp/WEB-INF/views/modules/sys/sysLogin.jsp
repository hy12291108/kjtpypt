<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')} 登录</title>
	<meta name="decorator" content="blank"/>
	<style type="text/css">
      html,body,table{background-color:#f5f5f5;width:100%;text-align:center;height:100%;}.form-signin-heading{font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:36px;margin-bottom:20px;color:#0663a2;}
      .form-signin{position:relative;text-align:left;width:300px;padding:25px 29px 24px;margin:0 auto 20px;background-color:#fff;border:1px solid #e5e5e5;
        	-webkit-border-radius:5px;-moz-border-radius:5px;border-radius:5px;-webkit-box-shadow:0 1px 2px rgba(0,0,0,.05);-moz-box-shadow:0 1px 2px rgba(0,0,0,.05);box-shadow:0 1px 2px rgba(0,0,0,.05);}
      .form-signin .checkbox{margin-bottom:10px;color:#0663a2;} .form-signin .input-label{font-size:16px;line-height:23px;color:#999;}
      .form-signin .input-block-level{font-size:16px;height:auto;margin-bottom:15px;padding:7px;*width:283px;*padding-bottom:0;_padding:7px 7px 9px 7px;}
      .form-signin .btn.btn-large{font-size:16px;} .form-signin #themeSwitch{position:absolute;right:15px;bottom:10px;}
      .form-signin div.validateCode {padding-bottom:15px;} .mid{vertical-align:middle;}
      .header{height:140px;padding-top:20px;} .alert{position:relative;width:300px;margin:0 auto;*padding-bottom:0px;}
      label.error{background:none;width:270px;font-weight:normal;color:inherit;margin:0;}
      
 /* pop */
#pop{background:#fff;width:220px;border:1px solid #e0e0e0;font-size:12px;position:fixed;right:0px;bottom:0px;}
#popHead{line-height:32px;background:#f6f0f3;border-bottom:1px solid #e0e0e0;position:relative;font-size:12px;padding:0 0 0 10px;}
#popHead h2{font-size:14px;color:#438cce;line-height:32px;height:32px;margin: 0;}
#popHead #popClose{position:absolute;right:10px;top:1px;}
#popHead a#popClose:hover{color:#f00;cursor:pointer;}
#popContent{padding:5px 10px;}
#popTitle a{line-height:24px;font-size:14px;font-family:'微软雅黑';color:#333;font-weight:bold;text-decoration:none;}
#popTitle a:hover{color:#f60;}
#popIntro{text-indent:24px;line-height:160%;margin:5px 0;color:#666;}
#popMore{border-top:1px dotted #ccc;line-height:24px;margin:8px 0 0 0;}
#popMore a{color:#438cce;}
#popMore a:hover{color:#f00;}
    </style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#loginForm").validate({
				rules: {
					validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
				},
				messages: {
					username: {required: "请填写用户名."},password: {required: "请填写密码."},
					validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
				},
				errorLabelContainer: "#messageBox",
				errorPlacement: function(error, element) {
					error.appendTo($("#loginError").parent());
				} 
			});
		});
		// 如果在框架或在对话框中，则弹出提示并跳转到首页
		/* if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
			alert('未登录或登录超时。请重新登录，谢谢！');
			top.location = "${ctx}";
		} */
	</script>
</head>
<body>
	<div class="login">
	<!--[if lte IE 6]><br/><div class='alert alert-block' style="text-align:left;padding-bottom:10px;"><a class="close" data-dismiss="alert">x</a><h4>温馨提示：</h4><p>你使用的浏览器版本过低。为了获得更好的浏览体验，我们强烈建议您 <a href="http://browsehappy.com" target="_blank">升级</a> 到最新版本的IE浏览器，或者使用较新版本的 Chrome、Firefox、Safari 等。</p></div><![endif]-->
	<div class="header">
		<%-- <div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}"><button data-dismiss="alert" class="close">×</button>
			<label id="loginError" class="error">${message}</label>
		</div> --%>
	<c:if test="${!empty message}">
		<div id ="div" class="alert alert-success alert-dismissible" role="alert">
			 <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			 ${message}
		</div>
	</c:if>
		
	</div> 
	<h1 class="form-signin-heading">${fns:getConfig('productName')}</h1>
	<form id="loginForm" class="form-signin" action="${ctx}/login" method="post">
		<label class="input-label" for="username">登录名</label>
		<input type="text" id="username" name="username" class="input-block-level required" value="${username}">
		<label class="input-label" for="password">密码</label>
		<input type="password" id="password" name="password" class="input-block-level required">
		<c:if test="${isValidateCodeLogin}"><div class="validateCode">
			<label class="input-label mid" for="validateCode">验证码</label>
			<sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
		</div></c:if><%--
		<label for="mobile" title="手机登录"><input type="checkbox" id="mobileLogin" name="mobileLogin" ${mobileLogin ? 'checked' : ''}/></label> --%>
		<input class="btn btn-large btn-primary" type="submit" value="登 录"/>&nbsp;&nbsp;
		<label for="rememberMe" title="下次不需要再登录"><input type="checkbox" id="rememberMe" name="rememberMe" ${rememberMe ? 'checked' : ''}/> 记住我（公共场所慎用）</label>
		<br>
		<table class="signin"><tr style="border-bottom:1px solid #e5e5e5;"><td align="left"><!-- <a href="/kjtpypt/a/UserRegister/form">特派员注册</a> -->
		<div class="dropdown">
		  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		    特派员注册
		  <span class="caret"></span>
		  </button>
		  <ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
		    <li><a href="/kjtpypt/a/UserRegister/form">自然人</a></li>
		    <li><a href="/kjtpypt/a/UserRegister/tpyCorp">法人</a></li>
		  </ul>
		</div>
		</td>
		
		<td align="right"><a href="/kjtpypt/a/UserRegister/xdregister">服务对象注册</a></td></tr>
		<tr>
			<td align="left"><a href="/kjtpypt/a/UserRegister/downLoad">下载自然人推荐表</a></td>
			<td align="right"><a href="/kjtpypt/a/UserRegister/downLoadCorp">下载法人推荐表</a></td>
		</tr>
		</table>
		
		<!--<div id="themeSwitch" class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">${fns:getDictLabel(cookie.theme.value,'theme','默认主题')}<b class="caret"></b></a>
			<ul class="dropdown-menu">
			  <c:forEach items="${fns:getDictList('theme')}" var="dict"><li><a href="#" onclick="location='${pageContext.request.contextPath}/theme/${dict.value}?url='+location.href">${dict.label}</a></li></c:forEach>
			</ul>
			[if lte IE 6]><script type="text/javascript">$('#themeSwitch').hide();</script><![endif]
		</div>-->
	</form>
	
	<div class="footer">
	<%
	
	 %>
		Copyright &copy; ${fns:getConfig('copyrightYear')} <a href="${pageContext.request.contextPath}${fns:getFrontPath()}">${fns:getConfig('productName')}</a> - Powered By <a href="#" target="_blank">协同数码股份有限公司</a> ${fns:getConfig('version')} 
	</div>
	<!--<script src="${ctxStatic}/flash/zoom.min.js" type="text/javascript"></script>-->
	</div>
	
	<!-- APP下载框 -->
<script type="text/javascript" src="/kjtpypt/static/skines/js/jquery.min.js"></script>
<script type="text/javascript" src="/kjtpypt/static/skines/js/yanue.pop.js"></script>  
<script type="text/javascript" >
//记得加载jquery
//使用参数：1.标题，2.链接地址，3.内容简介
window.onload=function(){
	var pop=new Pop("订阅号关注","","");
	//var pop=new Pop("","","");
}
</script>
<div id="pop" style="display:none;">

	<div id="popHead"> <a id="popClose" title="关闭"><font color="#438cce">关闭</font></a>
		<h2>订阅号及QQ群</h2>
	</div>
	<div id="popContent">
		<!--  <dl>
			<dd id="popIntro">这里是内容简介</dd>
		</dl>-->
		<p id="popMore"><font style='color:#438cce'>扫描关注微信订阅号<img src='/kjtpypt/static/skines/images/dyh.png' /></font></p>
		<p id="popMore"><font style='color:#438cce'>陕西科特派系统管理群<img src='/kjtpypt/static/skines/images/qqqun.png' /></font></p>
	</div>
	
</div>
</body>
</html>