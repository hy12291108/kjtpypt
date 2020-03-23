<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>新用户注册完成</title>
	<!-- <meta name="decorator" content="default"/> -->	
	<script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
	<link href="${ctxStatic}/login/skin/css/style.css" rel="stylesheet" type="text/css" />
	<link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
	<style type="text/css">
      .form-signin div.validateCode {padding-bottom:15px;} .mid{vertical-align:middle;}
      .header{height:80px;padding-top:20px;} .alert{position:relative;width:300px;margin:0 auto;*padding-bottom:0px;}
      label.error{background:none;width:270px;font-weight:normal;color:inherit;margin:0;}
    </style>
    <script type="text/javascript">
		function login()
  		{
  			window.location.replace("${pageContext.request.contextPath}${fns:getAdminPath()}/login")
  		}
	</script>
</head>
<body>
<div id="wrap">
	<div id="header">
		<div class="top">
			<h1><a href="${ctx}/login"><span>陕西科技投融资信息化管理服务平台</span></a></h1>
			<ul>
            	<li>已有账号，点击<a href="/kjtrzpt/a/login">登录</a> | <a href="/kjtrzpt/f">首页</a></li>
            </ul>
		</div>
		<div class="subbanner" style="background:url(${ctxStatic}/login/skin/images/subbanner006.jpg) top center no-repeat;">
		</div>		
  	</div>
		<div id="container">
			<div class="sub forgetpd">
				<div class="submain">
					<ul class="buzhou">
						<li class="one">请选择注册类型</li>
						<li class="twook">填写注册资料</li>
						<li class="three">完成注册</li>
					</ul>                   
					<div class="form2">
					<form>
                            <table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td align="center" style="font-size:30px;">注册成功！请登录注册邮箱激活账号。</td>
								</tr>
                                <tr>
									<td>&nbsp;</td>
								</tr>
                                <tr>
								  <td align="center"><input name="" type="button" class="bt" value="返回" onclick="login()" /></td>							
								</tr>
                            </table>
							</form>
					</div>
				</div>
			</div>
		
	</div>	
	<jsp:include page="/WEB-INF/views/modules/index/register/footer.jsp" flush="true" ></jsp:include>	
<!-- 	<div id="footer"> -->
<!-- 		<p>主办：陕西科技控股集团有限责任公司&nbsp;&nbsp;&nbsp;咨询热线：029-88862922、029-88862923&nbsp;&nbsp;&nbsp;Copyright © 2018 - 2020&nbsp;&nbsp;&nbsp;技术支持：西安协同数码股份有限公司&nbsp;&nbsp;&nbsp;陕ICP备：05317645号</p> -->
<!-- 	</div> -->
</div>
</body>
</html>
