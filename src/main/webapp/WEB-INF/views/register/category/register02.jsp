<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>新用户注册第二步</title>
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
		$(document).ready(function() {
			$("#registerForm").validate({
				rules: {
					loginName: {required:true,remote: "${pageContext.request.contextPath}${fns:getFrontPath()}/checkLoginNameDws?oldLoginName=" + encodeURIComponent('${user.loginName}')},
					email: {required:true,remote: "${pageContext.request.contextPath}${fns:getFrontPath()}/checkRegEmail?oldEmail=" + encodeURIComponent('${user.email}')}
				},
				messages: {
					loginName: {required:"非空",remote: "该用户已存在"},
					email: {required:"非空",remote: "该邮箱已存在"},
					confirmNewPassword: {equalTo: "输入与上面相同的密码"}
				},
				submitHandler: function(form){
					if(chkinput()!=true){
					
					}else{
						loading('正在提交，请稍等...');
						form.submit();
					}				
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
		function chkinput() { 
			var $check=$("#tiaokuan");//获取checkbook的元素对象			
			if($check.is(":checked")){
				return true;	
			}else{
				alert("请选择同意条款!");
				return false;	
			}
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
					<div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}">
					<label id="loginError" class="error">${message}</label>
					</div>                
					<div class="form2">
					<form:form id="registerForm" modelAttribute="user" action="${pageContext.request.contextPath}${fns:getFrontPath()}/registerIndex" method="post" class="form-horizontal">
					<form:input path="company.id" type="hidden" value="1"/>
					<form:input path="office.id" type="hidden" value="1"/>
					<form:input path="userType" type="hidden" value="${user.userType }"/>
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td width="31%" align="right">用户名：</td>					
							<td width="69%">
							<input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}">
							<form:input path="loginName" htmlEscape="false" minlength="4" maxlength="50" class="required username"/>
							</td>
						</tr>
						<tr>
							<td width="31%" align="right">邮箱：</td>					
							<td width="69%">
							<input id="oldEmail" name="oldEmail" type="hidden" value="${user.email}">
							<form:input path="email" htmlEscape="false" minlength="4" maxlength="50" class="required email"/>
							</td>
						</tr>
						<tr>
							<td width="31%" align="right">密码：</td>
							<td width="69%"><form:input path="newPassword" type="password" name="password" class="required password" minlength="6" maxlength="15" oncontextmenu="return false" onpaste="return false" /></td>
						</tr>
						<tr>
							<td width="31%" align="right">确认密码：</td>
							<td width="69%"><input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3" equalTo="#newPassword"/></td>
						</tr>
						<tr>
							<td align="right">验证码：</td>
							<td>
								<div class="validateCode">								
								<sys:validateCode  name="validateCode" inputCssStyle="margin-bottom:0;"/>
								</div>
							</td>
						</tr>
                        <tr>
                            <td align="right">&nbsp;</td>
							<td><input name="" type="checkbox" id="tiaokuan" value="" style=" vertical-align:middle;" />我已阅读并同意<a href="${pageContext.request.contextPath}${fns:getFrontPath()}/serviceTerm" target="_blank">《服务条款》</a></td>
						</tr>
						<tr>
							<td colspan="3" align="center"><button id="submit" class="bt" type="submit">注 册</button></td>		
						</tr>
					</table>
					</form:form>
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
