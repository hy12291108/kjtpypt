<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>注册</title>
    <meta name="decorator" content="blank"/>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta name="author" content="http://jeesite.com/"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10"/>
    <meta http-equiv="Expires" content="0">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-store">
    <script src="/kjtpypt/static/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="/kjtpypt/static/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/kjtpypt/static/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>
    <script src="/kjtpypt/static/jquery-validation/1.11.0/jquery.validate.js" type="text/javascript"></script>
    <script src="/kjtpypt/static/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
    <script src="/kjtpypt/static/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <script src="/kjtpypt/static/common/mustache.min.js" type="text/javascript"></script>
    <!-- 20170906加注册页面样式 -->
    <link href="/kjtpypt/static/skin/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="/kjtpypt/static/skin/css/css.css" type="text/css" rel="stylesheet"/>
    <script src="/kjtpypt/static/common/jeesite.js" type="text/javascript"></script>
    <script type="text/javascript">
        function mobileBind() {
            var mobile = $("#loginName").val();
            $("#mobile").val(mobile);
        }

        $(document).ready(function () {
            $("#inputForm").validate({
                rules: {
                    loginName: {remote: "/kjtpypt/a/UserRegister/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')}
                },
                messages: {
                    loginName: {remote: "用户名已存在"},
                    confirmNewPassword: {equalTo: "输入相同的密码"}
                },
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
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
<div class="header">
    <h1><img src="/kjtpypt/static/skin/images/logo.png" width="560" height="42" alt=""/><span>陕西省科技特派员服务与管理系统</span></h1>
</div>
<div class="container">
    <div class="signin001">
        <h2><span>个人用户注册 </span></h2>
        <form:form id="inputForm" modelAttribute="user" action="${ctx}/UserRegister/tempSave" method="post" class="form-horizontal">
            <table class="table  table-bordered">
                <tr>
                    <td>
                        <label>登录名：</label>
                    </td>
                    <td colspan="1">
                        <input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}">
                        <form:input path="loginName" id="loginName" placeholder="请输入手机号码" htmlEscape="false"
                                    maxlength="50" class="required mobile" onkeyup="mobileBind()"/>
                        <span class="help-inline"><font color="red">*</font></span>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>密码：</label>
                    </td>
                    <td colspan="1">
                        <input id="newPassword" name="newPassword" placeholder="请输入密码" type="password" value=""
                               maxlength="50" minlength="3"/><em>*</em>
                        <c:if test="${empty user.id}"><span class="help-inline"> </span></c:if>
                        <c:if test="${not empty user.id}"><span class="help-inline">若不修改密码，请留空。</span></c:if>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>确认密码：</label>
                    </td>
                    <td colspan="3">
                        <input id="confirmNewPassword" name="confirmNewPassword" placeholder="请输入密码" type="password"
                               value="" maxlength="50" minlength="3" equalTo="#newPassword"/>
                        <c:if test="${empty user.id}"><span class="help-inline"></span></c:if><em>*</em>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>姓名：</label>
                    </td>
                    <td colspan="3">
                        <input id="name" name="name" placeholder="请输入姓名" type="text" value="" maxlength="10"
                               minlength="2"/>
                        <span class="help-inline"><font color="red">*</font></span>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>手机号码：</label>
                    </td>
                    <td colspan="3">
                        <form:input path="mobile" id="mobile" readonly="true" placeholder="请输入手机号码" htmlEscape="false" maxlength="100"/>
                        <span class="help-inline"><font color="red">*</font></span>
                    </td>
                </tr>
            </table>
            <input name="loginFlag" value="1" type="hidden"/>
            <input name="personFlag" value="4" type="hidden"/>
            <input name="checkFlag" value="2" type="hidden"/>
            <div class="btgroup form-actions">
                <input id="btnSubmit" type="submit" class="btn btn-primary"  value="注册">
                <input id="btnCancel" type="button" class="btn btn-default" value="返回" onclick="history.go(-1)"/>
            </div>
        </form:form>
    </div>
</div>
<div class="footer">陕西省科技特派员服务与管理系统 版权所有 Copyright © 2017 备案信息：陕ICP备***********</div>
</body>
</html>