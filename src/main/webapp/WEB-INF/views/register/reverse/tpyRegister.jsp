<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
    <title>用户管理</title>
    <meta name="decorator" content="blank"/>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta name="author" content="http://jeesite.com/"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10"/>
    <meta http-equiv="Expires" content="0">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-store">
    <script src="/kjtpypt/static/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="/kjtpypt/static/jquery/Select.js" type="text/javascript"></script>
    <link href="/kjtpypt/static/bootstrap/2.3.1/css_cerulean/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <script src="/kjtpypt/static/bootstrap/2.3.1/js/bootstrap.min.js" type="text/javascript"></script>
    <link href="/kjtpypt/static/bootstrap/2.3.1/awesome/font-awesome.min.css" type="text/css" rel="stylesheet"/>
    <link href="/kjtpypt/static/jquery-select2/3.4/select2.min.css" rel="stylesheet"/>
    <script src="/kjtpypt/static/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>
    <link href="/kjtpypt/static/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet"/>
    <script src="/kjtpypt/static/jquery-validation/1.11.0/jquery.validate.js" type="text/javascript"></script>
    <link href="/kjtpypt/static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet"/>
    <script src="/kjtpypt/static/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
    <script src="/kjtpypt/static/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <script src="/kjtpypt/static/common/mustache.min.js" type="text/javascript"></script>
    <link href="/kjtpypt/static/common/jeesite.css" type="text/css" rel="stylesheet"/>
    <link href="/kjtpypt/static/common/Select.css" type="text/css" rel="stylesheet"/>
    <!-- 20170906加注册页面样式 -->
    <%--<link href="/kjtpypt/static/skin/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="/kjtpypt/static/skin/css/css.css" type="text/css" rel="stylesheet"/>--%>
    <!-- 20200319加注册页面样式 -->
    <link href="/kjtpypt/static/classify/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/kjtpypt/static/classify/css/css.css" rel="stylesheet" type="text/css">

    <script src="/kjtpypt/static/common/jeesite.js" type="text/javascript"></script>
    <!-- 上传图片 20171011-->
    <link href="${ctxStatic}/fileUpload/css/iconfont.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/fileUpload/css/fileUpload.css" rel="stylesheet" type="text/css">
    <script src="${ctxStatic}/fileUpload/js/fileUpload.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${ctxStatic}/centermenu-master/css/animate.css">
    <link rel="stylesheet" href="${ctxStatic}/centermenu-master/css/toast.css">
    <link rel="stylesheet" href="${ctxStatic}/centermenu-master/css/centermenu.css">
    <script src="${ctxStatic}/centermenu-master/js/toast.js" type="text/javascript"></script>
    <script src="${ctxStatic}/centermenu-master/js/centermenu.js" type="text/javascript"></script>

    <script type="text/javascript">var ctx = '/kjtpypt/a', ctxStatic = '/kjtpypt/static';</script>
    <script>
        var _hmt = _hmt || [];
        (function () {
            var hm = document.createElement("script");
            hm.src = "//hm.baidu.com/hm.js?82116c626a8d504a5c0675073362ef6f";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>
    <script type="text/javascript">
        function mobileBind() {
            var mobile = $("#loginName").val();
            $("#mobile").val(mobile);
        }

        $(document).ready(function () {
            $("#fileUploadContent").initUpload({
                "uploadUrl": "/kjtpypt/a/UserRegister/uploadImage",//上传文件信息地址
                autoCommit: false,//文件是否自动上传
                "fileType": ['png', 'jpg']//文件类型限制，默认不限制，注意写的是文件后缀
            });
            $("#inputForm").validate({
                rules: {
                    loginName: {remote: "/kjtpypt/a/UserRegister/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')}
                },
                messages: {
                    loginName: {remote: "用户登录名已存在"},
                    confirmNewPassword: {equalTo: "输入与上面相同的密码"}
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

        function registerSave() {
            uploadEvent.uploadFileEvent(option, 1);
        }

    </script>


</head>
<body>
<div class="header">
    <div class="top">
        <h1><img src="/kjtpypt/static/classify/images/logo02.png" width="650" height="42" alt=""/><span>陕西省科技特派员服务与管理系统</span></h1>
    </div>
    <div class="navbg">
        <div class="nav">
            <ul>
                <li><a href="/kjtpypt/a/UserRegister/form">自然人特派员</a></li>
                <li><a href="/kjtpypt/a/UserRegister/tpyCorp">法人特派员</a></li>
                <li><a href="/kjtpypt/a/UserRegister/fxForm"  class="current">反向特派员</a></li>
                <li><a href="/kjtpypt/a/UserRegister/enterpriseForm">企业</a></li>
                <li><a href="/kjtpypt/a/UserRegister/temporary">普通用户</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container">
    <div class="signin001">
        <form:form id="inputForm" modelAttribute="user" method="post" class="form-horizontal">
            <form:hidden path="id"/>
            <sys:message content="${message}"/>
            <form:errors path="*"></form:errors>
            <table cellpadding="0" cellspacing="0" width="100%">
                <tr>
                    <td>
                        <label>登录名：</label>
                    </td>
                    <td>
                        <input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}">
                        <form:input path="loginName" id="loginName" placeholder="请输入您的手机号码" htmlEscape="false"
                                    maxlength="50" class="required mobile" onkeyup=" mobileBind()"/>
                        <em>*</em>
                    </td>
                    <td rowspan="5"><label>头像：</label></td>
                    <td rowspan="5">
                        <table class="uploadheadimg">
                            <tr><td rowspan="2"><div id="fileUploadContent" class="required fileUploadContent"></div></td><td valign="bottom"><input type="button" class="btn btn-primary" value="上传头像" id="tit"></td></tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>密码：</label>
                    </td>
                    <td>
                        <input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3"
                               class="${empty user.id?'required':''}"/><span class="help-inline"><font
                            color="red">*</font> </span>
                    </td>

                </tr>
                <tr>
                    <td>
                        <label>确认密码：</label>
                    </td>
                    <td>
                        <input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50"
                               minlength="3" equalTo="#newPassword"/>
                        <c:if test="${empty user.id}"><span class="help-inline"><font
                                color="red">*</font> </span></c:if>
                    </td>

                </tr>

                <tr>
                    <td>
                        <label>姓名：</label>
                    </td>
                    <td>
                        <form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>性别：</label>
                    </td>
                    <td>
                        <form:select path="sex">
                            <form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value"
                                          htmlEscape="false"/>
                        </form:select>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>民族：</label>
                    </td>
                    <td>
                        <form:input path="tpyNation" htmlEscape="false" maxlength="50" class="required"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                    <td>
                        <label>籍贯：</label>
                    </td>
                    <td>
                        <form:input path="tpyLocation" htmlEscape="false" maxlength="50" class="required"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                </tr>

                <form:input path="company.id" htmlEscape="false" type="hidden"/>
                <tr>
                    <td>
                        <label>所属区域：</label>
                    </td>
                    <td>
                        <sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name"
                                        labelValue="${user.office.name}"
                                        title="部门" url="/UserRegister/treeData?type=2" cssClass="required"
                                        notAllowSelectParent="true"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                    <td>
                        <label>工作单位名称：</label>
                    </td>
                    <td>
                        <form:input path="tpyCompany" htmlEscape="false" maxlength="15"/>
                        <span class="help-inline"><font color="red">*</font></span>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>职务：</label>
                    </td>
                    <td>
                        <form:input path="tpyPosition" htmlEscape="false" maxlength="50"/>
                    </td>
                    <td>
                        <label>职称：</label>
                    </td>
                    <td>
                        <form:select path="tpyTitle">
                            <form:options items="${fns:getDictList('tpy_title')}" itemLabel="label" itemValue="value"
                                          htmlEscape="false" class="required"/>
                        </form:select>
                        <span class="help-inline"><font color="red">*</font></span>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>学历：</label>
                    </td>
                    <td>
                        <form:select path="tpyQulification">
                            <form:options items="${fns:getDictList('tpy_qulification')}" itemLabel="label"
                                          itemValue="value" htmlEscape="false" class="required"/>
                        </form:select>
                        <span class="help-inline"><font color="red">*</font></span>
                    </td>
                    <td>
                        <label>政治面貌：</label>
                    </td>
                    <td>
                        <form:select path="tpyPolitical">
                            <form:options items="${fns:getDictList('political')}" itemLabel="label" itemValue="value"
                                          htmlEscape="false"/>
                        </form:select>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                </tr>

                <tr>

                    <td>
                        <label>邮箱：</label>
                    </td>
                    <td>
                        <form:input path="email" htmlEscape="false" maxlength="100" class="required email"/>
                        <span class="help-inline"><font color="red">*</font></span>
                    </td>
                    <td>
                        <label>手机：</label>
                    </td>
                    <td>
                        <form:input path="mobile" id="mobile" readonly="true" htmlEscape="false" maxlength="50"
                                    class="required"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                </tr>


                <tr>
                    <td>
                        <label>出生日期：</label>
                    </td>
                    <td>
                        <form:input path="tpyBirthDate" class="Wdate required" htmlEscape="false" maxlength="40"
                                    onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',isShowClear:false,maxDate:'%y-%M-%d'})"/>
                        <em>*</em>
                    </td>
                    <td>
                        <label>身份证号/护照：</label>
                    </td>
                    <td>
                        <form:input path="tpyIdcard" id="tpyIdcard" htmlEscape="false" maxlength="20" class="required "/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>人才类型：</label>
                    </td>
                    <td>
                        <form:select path="tpyTalentType">
                            <form:options items="${fns:getDictList('talent_type')}" itemLabel="label" itemValue="value"
                                          htmlEscape="false"/>
                        </form:select>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                    <td>
                        <label>服务优势：</label>
                    </td>
                    <td>
                        <form:select path="tpyServiceAdvantages">
                            <form:options items="${fns:getDictList('service_advantages')}" itemLabel="label"
                                          itemValue="value"
                                          htmlEscape="false"/>
                        </form:select>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>拟服务地点：</label>
                    </td>
                    <td colspan="3">

                        <form:textarea path="tpyNfwAddress" htmlEscape="false" rows="2" maxlength="15"
                                       style="width:753px"
                                       class="required"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>拟服务内容：</label>
                    </td>
                    <td colspan="3">

                        <form:textarea path="tpyNfwContent" htmlEscape="false" rows="2" maxlength="100"
                                       style="width:753px"
                                       class="required"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>从事科技服务与创业经历：</label>
                    </td>
                    <td colspan="3">

                        <form:textarea path="tpyExperience" htmlEscape="false" rows="2" maxlength="200"
                                       style="width:753px"
                                       class="required"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>奖惩情况：</label>
                    </td>
                    <td colspan="3">

                        <form:textarea path="tpyJcSituation" htmlEscape="false" rows="2" maxlength="100"
                                       style="width:753px"
                                       class="required"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                </tr>
            </table>


            <form:input path="photo" id="tjTableImage" htmlEscape="false" type="hidden" value=""/>
            <form:input path="loginFlag" value="1" htmlEscape="false" type="hidden"/>
            <form:input path="personFlag" value="5" htmlEscape="false" type="hidden"/>
            <form:input path="checkFlag" value="0" htmlEscape="false" type="hidden"/>
            <form:input path="roleIdList" value="4d54294fbd694873b19f752cda308f2d" htmlEscape="false" type="hidden"/> <%--TODO 待修改--%>
            <div class="btgroup form-actions">
                <input id="btnSubmit" type="submit" class="btn btn-primary" value="申报" onclick="registerSave()">
                <input id="btnCancel" type="button" class="btn btn-default" value="返回" onclick="history.go(-1)"/>
            </div>
        </form:form>
    </div>
</div>
<div class="footer">陕西省科技特派员服务与管理系统 版权所有 Copyright © 2017 备案信息：陕ICP备***********</div>
</body>
</html>