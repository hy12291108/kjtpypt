<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html style="overflow-x:auto;overflow-y:auto;">
<head>
    <title>用户管理</title>
    <!--  <meta name="decorator" content="default"/>-->
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
    <link href="/kjtpypt/static/skin/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="/kjtpypt/static/skin/css/css.css" type="text/css" rel="stylesheet"/>
    <script src="/kjtpypt/static/common/jeesite.js" type="text/javascript"></script>
    <!-- 上传图片 20171012-->
    <link href="${ctxStatic}/fileUpload/css/iconfont.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/fileUpload/css/fileUpload.css" rel="stylesheet" type="text/css">
    <script src="${ctxStatic}/fileUpload/js/fileUpload.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${ctxStatic}/centermenu-master/css/animate.css">
    <link rel="stylesheet" href="${ctxStatic}/centermenu-master/css/toast.css">
    <link rel="stylesheet" href="${ctxStatic}/centermenu-master/css/centermenu.css">
    <script src="${ctxStatic}/centermenu-master/js/toast.js" type="text/javascript"></script>
    <script src="${ctxStatic}/centermenu-master/js/centermenu.js" type="text/javascript"></script>

    <script type="text/javascript">var ctx = '/kjtpypt/a', ctxStatic = '/kjtpypt/static';</script>
    <script>var _hmt = _hmt || [];
    (function () {
        var hm = document.createElement("script");
        hm.src = "//hm.baidu.com/hm.js?82116c626a8d504a5c0675073362ef6f";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();</script>
    <script type="text/javascript">

        function mobileBind() {
            var mobile=$("#loginName").val();
            $("#mobile").val(mobile);
        }


        $(document).ready(function () {
            var option = null;
            $("#fileUploadContent").initUpload({
                //"uploadUrl":"http://***/",//上传文件信息地址
                "uploadUrl": "/kjtpypt/a/UserRegister/uploadImage",//上传文件信息地址
                //"size":350,//文件大小限制，单位kb,默认不限制
                //"maxFileNumber":3,//文件个数限制，为整数
                //"filelSavePath":"",//文件上传地址，后台设置的根目录
                //"beforeUpload":beforeUploadFun,//在上传前执行的函数
                //"onUpload":onUploadFun，//在上传后执行的函数
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

        /* 联系人号码校验*/
        function phoneCheck() {
            var corpCorPhone = $.trim($('#corpCorPhone').val());
            var num = /^\d*$/; //全数字
            if (!num.exec(corpCorPhone)) {
                alert("号码必须全为数字");
                $("#corpCorPhone").focus();
                return false;
            }
            if (corpCorPhone.length != 11) {
                alert("号码长度不符,长度为11位");
                $("#corpCorPhone").focus();
                return false;
            }
        }

        function yingyeinfo() {
            var corpScale = $.trim($('#corpScale').val());
            if (corpScale.length > 200) {
                alert("营业范围长度不能大于200");
                $("#corpScale").focus();
                return false;
            }
        }

        function jsinfo() {
            var corpMajor = $.trim($('#corpMajor').val());
            if (corpMajor.length > 200) {
                alert("科技优势与服务内容长度不能大于200");
                $("#corpMajor").focus();
                return false;
            }

        }

        /* 法定代表人号码校验*/
        function phoneCheck1() {
            var mobile = $.trim($('#mobile').val());
            var num = /^\d*$/; //全数字
            if (!num.exec(mobile)) {
                alert("号码必须全为数字");
                $("#mobile").focus();
                return false;
            }
            if (mobile.length != 11) {
                alert("号码长度不符,长度为11位");
                $("#mobile").focus();
                return false;
            }
        }

        /*银行卡号校验*/
        function bankAccountCheck() {
            var bankno = $.trim($('#bankAccount').val());
            if (bankno == "") {
                alert("请填写银行卡号");
                $("#bankAccount").focus();
                return false;
            }
            var num = /^\d*$/; //全数字
            if (!num.exec(bankno)) {
                alert("银行卡号必须全为数字");
                $("#bankAccount").focus();
                return false;
            }
            if (bankno.length < 16 || bankno.length > 21) {
                alert("银行卡号长度不符,长度为16-21");
                $("#bankAccount").focus();
                return false;
            }

        }

        function tpyCorpSave() {
            uploadEvent.uploadFileEvent(option, 1);
        }
    </script>
</head>
<body>
<div class="header">
    <h1><img src="/kjtpypt/static/skin/images/logo.png" width="560" height="42" alt=""/><span>陕西省科技特派员服务与管理系统</span>
    </h1>
</div>
<div class="container">
    <div class="signin001">
        <h2><span>特派员（法人）注册</span></h2>
        <form:form id="inputForm" modelAttribute="user" method="post" class="form-horizontal">
            <form:hidden path="id"/>
            <sys:message content="${message}"/>

            <table cellpadding="0" cellspacing="0" width="100%">

                <tr>
                    <td>
                        <label>登录名：</label>
                    </td>
                    <td colspan="3">
                        <input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}">
                        <form:input path="loginName" id="loginName" placeholder="请输入您的手机号码" htmlEscape="false" maxlength="50"
                                    class="required mobile" onkeyup=" mobileBind()"/>
                        <em>*</em>
                    </td>

                </tr>
                <tr>
                    <td>
                        <label>密码：</label>
                    </td>
                    <td colspan="3">
                        <input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3"
                               class="${empty user.id?'required':''}"/>
                        <c:if test="${empty user.id}"><span class="help-inline"><font
                                color="red">*</font> </span></c:if>
                        <c:if test="${not empty user.id}"><span class="help-inline">若不修改密码，请留空。</span></c:if>
                    </td>

                </tr>
                <tr>
                    <td>
                        <label>确认密码：</label>
                    </td>
                    <td colspan="3">
                        <input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50"
                               minlength="3" equalTo="#newPassword"/>
                        <c:if test="${empty user.id}"><span class="help-inline"><font
                                color="red">*</font> </span></c:if>
                    </td>

                </tr>
                <tr>
                    <td>
                        <label>单位名称：</label>
                    </td>
                    <td>
                            <%-- <form:textarea path="name" htmlEscape="false" rows="1" maxlength="200" style="width:769px"/> --%>
                        <form:input path="name" htmlEscape="false" maxlength="100"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>

                    <td>
                        <label>法人性质：</label>
                    </td>
                    <td>
                        <form:select path="tpyCorporateNature">
                            <form:options items="${fns:getDictList('corporate_nature')}" itemLabel="label" itemValue="value"
                                          htmlEscape="false" class="required"/>
                        </form:select>
                        <span class="help-inline"><font color="red">*</font></span>
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
                        <label>单位类型：</label>
                    </td>
                    <td>
                        <form:select path="corpType">
                            <form:options items="${fns:getDictList('corp_type')}" itemLabel="label" itemValue="value"
                                          htmlEscape="false" class="required"/>
                        </form:select>
                        <span class="help-inline"><font color="red">*</font></span>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>邮箱地址：</label>
                    </td>
                    <td>
                        <form:input path="email" htmlEscape="false" maxlength="100" class="required email"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>

                    <td>
                        <label>单位成立日期：</label>
                    </td>
                    <td>
                        <form:input path="corpEstDate" htmlEscape="false" maxlength="100" class="Wdate required"
                                    onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',isShowClear:false,maxDate:'%y-%M-%d'})"/>
                        <em>*</em>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>法定代表人：</label>
                    </td>
                    <td>
                        <form:input path="corpLegRepName" htmlEscape="false" maxlength="100" class="required"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                    <td>
                        <label>代表人职务：</label>
                    </td>
                    <td>
                        <form:input path="tpyPosition" htmlEscape="false" maxlength="50" class="required"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                </tr>


                <tr>
                    <td>
                        <label>代表人手机：</label>
                    </td>
                    <td>
                        <form:input path="mobile" id="mobile" readonly="true" htmlEscape="false" maxlength="50"
                                    class="required"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                    <td>
                        <label>代表人职称：</label>
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
                        <label>联系人姓名：</label>
                    </td>
                    <td>
                        <form:input path="corpCorName" htmlEscape="false" maxlength="100" class="required"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                    <td>
                        <label>联系人手机：</label>
                    </td>
                    <td>
                        <form:input path="corpCorPhone" htmlEscape="false" maxlength="100" class="required"
                                    onchange="phoneCheck()"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                </tr>


                <tr>
                    <td>
                        <label>统一社会信用代码：</label>
                    </td>
                    <td>
                        <form:input path="corpOrgCode" htmlEscape="false" maxlength="100"/><em>*</em>
                    </td>
                    <td>
                        <label>企业属性：</label>
                    </td>
                    <td>
                        <form:select path="tpyEnterpriseAttribute">
                            <form:options items="${fns:getDictList('enterprise_attribute')}" itemLabel="label" itemValue="value"
                                          htmlEscape="false" class="required"/>
                        </form:select>
                        <span class="help-inline"><font color="red">*</font></span>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>单位地址：</label>
                    </td>
                    <td colspan="3">

                        <form:textarea path="tpyAddress" htmlEscape="false" rows="2" maxlength="200"
                                       style="width:769px"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>营业范围：</label>
                    </td>
                    <td colspan="3">
                        <form:textarea path="corpScale" id="corpScale" htmlEscape="false" rows="2" maxlength="20"
                                       style="width:769px" onchange="yingyeinfo()"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>拟服务地点：</label>
                    </td>
                    <td colspan="3">
                        <form:textarea path="tpyNfwAddress" htmlEscape="false" rows="2" maxlength="10"
                                       style="width:753px"
                                       class="required"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>科技优势与服务内容：</label>
                    </td>
                    <td colspan="3">
                        <form:textarea path="corpMajor" id="corpMajor" htmlEscape="false" rows="2" maxlength="200"
                                       style="width:769px" onchange="jsinfo()"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>

                </tr>


                <tr>
                    <td>
                        <label>拟开展的服务内容：</label>
                    </td>
                    <td colspan="3">

                        <form:textarea path="tpyNfwContent" htmlEscape="false" rows="2" maxlength="100" style="width:753px"
                                       class="required"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>科技服务与创业情况：</label>
                    </td>
                    <td colspan="3">

                        <form:textarea path="tpyExperience" htmlEscape="false" rows="2" maxlength="200" style="width:753px"
                                       class="required"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                </tr>

                <tr>
                    <td class="tit">
                        <button type="button" class="btn btn-primary" id="tit">选择图片</button>
                    </td>
                    <td colspan="2">
                        <div id="fileUploadContent" class="fileUploadContent" class="required"></div>
                    </td>
                    <td><em>*注：营业执照上传</em></td>
                </tr>
            </table>
            <form:input path="photo" id="tjTableImage" htmlEscape="false" type="hidden" value=""/>  <%--营业执照--%>
            <form:input path="loginFlag" value="1" htmlEscape="false" type="hidden"/>
            <form:input path="personFlag" value="2" htmlEscape="false" type="hidden"/>
            <form:input path="checkFlag" value="0" htmlEscape="false" type="hidden"/>
            <form:input path="roleIdList" value="3bb6453c699d49508b15529670ad9e9b" htmlEscape="false" type="hidden"/>
            <div class="btgroup form-actions">
                <input id="btnSubmit" type="submit" class="btn btn-primary" value="申报" onclick="tpyCorpSave()">
                <input id="btnCancel" type="button" class="btn btn-default" value="返回" onclick="history.go(-1)"/>
            </div>
        </form:form>
    </div>
</div>
<div class="footer">陕西省科技特派员服务与管理系统 版权所有 Copyright © 2017 备案信息：陕ICP备***********</div>
</body>
</html>