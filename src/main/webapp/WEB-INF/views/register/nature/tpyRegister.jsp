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
            var option = null;

            $("#fileUploadContent").initUpload({
                "uploadUrl": "/kjtpypt/a/UserRegister/uploadImage",//上传文件信息地址
                autoCommit: false,//文件是否自动上传
                "fileType": ['png', 'jpg']//文件类型限制，默认不限制，注意写的是文件后缀
            });
            initZyLb();
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

        //加载即可输入又可实现下拉的select
        function corpList() {
            var officeId = $("#officeId").val();
            if (officeId == "") {
                alert("请先输入所属部门");
                $("#officeName").focus();
                return;
            } else if ($("#tpyCompany .es-list").length > 0) {
                $("#editable-select").remove();
                $(".es-list").remove();
                $("#tpyCompany").prepend("<select id='editable-select' name='tpyCompany'></select>");
            }
            $.ajax({
                type: "post",
                contentType: "application/json",
                url: "${ctx}/UserRegister/corpList?officeId=" + officeId,
                dataType: "json",
                success: function (data) {
                    var append1 = "<option value='";
                    var append2 = "'>";
                    var append3 = "</option>";
                    if (data.corpList[0] != "查无数据") {
                        for (var i = 0; i < data.corpList.length; i++) {
                            var append = append1 + data.corpList[i] + append2 + data.corpList[i] + append3;
                            $("#editable-select").empty();
                            $("#editable-select").append(append);
                        }
                    }
                    $("#editable-select").editableSelect({
                        effects: 'slide'
                    });
                },
                error: function () {
                    alert("error");
                }
            });
        }

        //专业类别
        function initZyLb() {
            $("#yjxkdm").empty();
            $.ajax({
                type: "POST",
                async: false,
                url: "${ctx}/UserRegister/registerList",
                dataType: "json",
                success: function (json) {
                    $("<option value=''>--选择专业类别--</option>").appendTo("#yjxkdm");//添加下拉框的option
                    for (var i = 0; i < json.majorList.length; i++) {
                        $("<option value='" + json.majorList[i].id + "'>" + json.majorList[i].name + "</option>").appendTo("#yjxkdm");//添加下拉框的option
                    }
                }
            });
        }

        function initZy() {
            $("#tpyMajor").empty();
            var yjxkdm = $("#yjxkdm").val();
            if (yjxkdm == "") {
                return;
            } else {
                $.ajax({
                    type: "POST",
                    async: false,
                    url: "${ctx}/UserRegister/registerSecondList",
                    data: "id=" + yjxkdm,
                    dataType: "json",
                    success: function (json) {
                        $("<option value=''>--选择专业--</option>").appendTo("#tpyMajor");//添加下拉框的option
                        for (var i = 0; i < json.majorList.length; i++) {
                            $("<option value='" + json.majorList[i].name + "'>" + json.majorList[i].name + "</option>").appendTo("#tpyMajor");//添加下拉框的option
                        }
                    }
                });
            }
        }


        /* 号码校验*/
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


        /*身份证校验*/
        function idCardCheck() {
            var idCard = $("#tpyIdcard").val();
            var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
            if (reg.test(idCard) === false) {
                alert("身份证输入不合法");
                $("#tpyIdcard").focus();
                return false;
            }
        }

        function registerSave() {
            var yjxkdm = $("#yjxkdm").val();
            if (yjxkdm == "" || yjxkdm == null) {
                alert("请选择专业类别");
                $("#yjxkdm").focus();
                return false;
            }
            if ($("#tpyMajor").val() == "" || $("#tpyMajor").val() == null) {
                alert("请选择专业");
                $("#tpyMajor").focus();
                return false;
            }
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
        <h2><span>特派员（自然人）注册</span></h2>
        <form:form id="inputForm" modelAttribute="user" method="post" class="form-horizontal">
            <form:hidden path="id"/>
            <sys:message content="${message}"/>
            <form:errors path="*"></form:errors>
            <table cellpadding="0" cellspacing="0" width="100%">
                <tr>
                    <td>
                        <label>登录名：</label>
                    </td>
                    <td colspan="3">
                        <input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}">
                        <form:input path="loginName" id="loginName" placeholder="请输入您的手机号码" htmlEscape="false"
                                    maxlength="50"
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
                               class="${empty user.id?'required':''}"/><span class="help-inline"><font
                            color="red">*</font> </span>
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
                        <label>姓名：</label>
                    </td>
                    <td>
                        <form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
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
                            <%--<div id="tpyCompany">
                                <select id="editable-select" name="tpyCompany">
                                </select>
                                <input type="button" value="选择" onClick="corpList()">
                                <span class="help-inline"><font color="red">*</font></span>
                            </div>--%>
                        <form:input path="tpyCompany" htmlEscape="false" maxlength="50"/>
                        <span class="help-inline"><font color="red">*</font></span>
                    </td>
                </tr>

                <tr>
                        <%--<td>
                            <label>工作部门：</label>
                        </td>
                        <td>
                            <form:input path="tpyDept" htmlEscape="false" maxlength="50"/>
                        </td>--%>
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
                        <!--校验身份证<form:input path="tpyIdcard" id="tpyIdcard" htmlEscape="false" maxlength="20" class="required" onChange="idCardCheck()"/>-->
                        <form:input path="tpyIdcard" id="tpyIdcard" htmlEscape="false" maxlength="20" class="required"/>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>服务形式：</label>
                    </td>
                    <td>
                        <form:select path="tpyServiceMode">
                            <form:options items="${fns:getDictList('service_mode')}" itemLabel="label" itemValue="value"
                                          htmlEscape="false"/>
                        </form:select>
                        <span class="help-inline"><font color="red">*</font> </span>
                    </td>
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
                </tr>


                <tr>
                    <td>
                        <label>专业类别：</label></td>
                    <td>
                        <select id="yjxkdm" name="tpyMajorType" class="ch-select required" onchange="initZy();">
                            <option value="" selected>--选择专业类别--</option>
                        </select>
                        <span class="help-inline"><font color="red">*</font></span>
                    </td>
                    <td><label>专业名称：</label></td>
                    <td>
                        <select name="tpyMajor" id="tpyMajor" class="ch-select required">
                            <option value="" selected>--选择专业名称--</option>
                        </select>
                        <span class="help-inline"><font color="red">*</font></span>
                    </td>
                </tr>


                <tr>
                    <td>
                        <label>专业擅长：</label>
                    </td>
                    <td colspan="3">

                        <form:textarea path="tpySpecial" htmlEscape="false" rows="2" maxlength="100" style="width:753px"
                                       class="required"/>
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

                <tr>
                    <td class="tit">
                        <button type="button" class="btn btn-primary" id="tit">选择图片</button>
                    </td>
                    <td colspan="2">
                        <div id="fileUploadContent" class="fileUploadContent" class="required"></div>
                    </td>
                    <td><em>*注：头像上传</em></td>
                </tr>
            </table>


            <form:input path="photo" id="tjTableImage" htmlEscape="false" type="hidden" value=""/>
            <form:input path="loginFlag" value="1" htmlEscape="false" type="hidden"/>
            <form:input path="personFlag" value="0" htmlEscape="false" type="hidden"/>
            <form:input path="checkFlag" value="0" htmlEscape="false" type="hidden"/>
            <form:input path="roleIdList" value="3bb6453c699d49508b15529670ad9e9b" htmlEscape="false" type="hidden"/>
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