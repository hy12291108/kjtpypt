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
    <!-- 20200319加注册页面样式 -->
    <link href="/kjtpypt/static/classify/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/kjtpypt/static/classify/css/perfect.css" rel="stylesheet" type="text/css">

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

        //保存
        function updateTpy() {
            $("#inputForm").attr("action", "/kjtpypt/a/UserSh/perfectInfoSave");
            $("#inputForm").submit();
        }
    </script>


    <script type="text/javascript" src="${ctxsys}/WEB-INF/views/register/js/perfectInfo.js"></script>
</head>
<body>
<div class="container">
    <div class="signin001">
        <form:form id="inputForm" modelAttribute="user" method="post" class="form-horizontal">
            <form:hidden path="id"/>
            <form:hidden path="loginFlag"/>
            <form:hidden path="personFlag"/>
            <form:hidden path="checkFlag"/>
            <form:hidden path="photo" id="tjTableImage"/>
            <sys:message content="${message}"/>
            <form:errors path="*"></form:errors>
            <table cellpadding="0" cellspacing="0" width="100%">
                <tr>
                    <td>
                        <label>登录名：</label>
                    </td>
                    <td>
                        <input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}">
                        <form:input path="loginName" id="loginName" placeholder="请输入您的手机号码" readonly="true"
                                    htmlEscape="false"
                                    maxlength="50" class=" mobile" onkeyup=" mobileBind()"/>
                    </td>
                <td rowspan="5"><label>头像：</label></td>
                <td rowspan="5">
                    <table class="uploadheadimg">
                        <tr>
                            <td rowspan="2">
                                <div id="fileUploadContent" class=" fileUploadContent"></div>
                            </td>
                            <td valign="bottom"><input type="button" class="btn btn-primary" value="上传头像" id="tit">
                            </td>
                        </tr>
                    </table>
                </td>
                </tr>
                <tr>
                    <td>
                        <label>姓名：</label>
                    </td>
                    <td>
                        <form:input path="name" htmlEscape="false" maxlength="50" class=""/>
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
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>民族：</label>
                    </td>
                    <td>
                        <form:input path="tpyNation" htmlEscape="false" maxlength="50" class=""/>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>籍贯：</label>
                    </td>
                    <td>
                        <form:input path="tpyLocation" htmlEscape="false" maxlength="50" class=""/>
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
                                        title="部门" url="/UserRegister/treeData?type=2" cssClass=""
                                        notAllowSelectParent="true"/>
                    </td>
                    <td>
                        <label>工作单位名称：</label>
                    </td>
                    <td>
                        <form:input path="tpyCompany" htmlEscape="false" maxlength="50"/>
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
                                          htmlEscape="false" class=""/>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>学历：</label>
                    </td>
                    <td>
                        <form:select path="tpyQulification">
                            <form:options items="${fns:getDictList('tpy_qulification')}" itemLabel="label"
                                          itemValue="value" htmlEscape="false" class=""/>
                        </form:select>
                    </td>
                    <td>
                        <label>政治面貌：</label>
                    </td>
                    <td>
                        <form:select path="tpyPolitical">
                            <form:options items="${fns:getDictList('political')}" itemLabel="label" itemValue="value"
                                          htmlEscape="false"/>
                        </form:select>
                    </td>
                </tr>

                <tr>

                    <td>
                        <label>邮箱：</label>
                    </td>
                    <td>
                        <form:input path="email" htmlEscape="false" maxlength="100" class=" email"/>
                    </td>
                    <td>
                        <label>手机：</label>
                    </td>
                    <td>
                        <form:input path="mobile" id="mobile" readonly="true" htmlEscape="false" maxlength="50"
                                    class=""/>
                    </td>
                </tr>


                <tr>
                    <td>
                        <label>出生日期：</label>
                    </td>
                    <td>
                        <form:input path="tpyBirthDate" class="Wdate " htmlEscape="false" maxlength="40"
                                    onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',isShowClear:false,maxDate:'%y-%M-%d'})"/>
                    </td>
                    <td>
                        <label>身份证号/护照：</label>
                    </td>
                    <td>
                        <form:input path="tpyIdcard" id="tpyIdcard" htmlEscape="false" maxlength="20" class=""/>
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
                    </td>
                    <td>
                        <label>人才类型：</label>
                    </td>
                    <td>
                        <form:select path="tpyTalentType">
                            <form:options items="${fns:getDictList('talent_type')}" itemLabel="label" itemValue="value"
                                          htmlEscape="false"/>
                        </form:select>
                    </td>
                </tr>


                <tr>
                    <td>
                        <label>专业类别：</label></td>
                    <td>
                        <select id="yjxkdm" name="tpyMajorType" class="ch-select " onchange="initZy();">
                            <option value="" selected>--选择专业类别--</option>
                        </select>
                    </td>
                    <td><label>专业名称：</label></td>
                    <td>
                        <select name="tpyMajor" id="tpyMajor" class="ch-select ">
                            <option value="" selected>--选择专业名称--</option>
                        </select>
                    </td>
                </tr>


                <tr>
                    <td>
                        <label>专业擅长：</label>
                    </td>
                    <td colspan="3">

                        <form:textarea path="tpySpecial" htmlEscape="false" rows="2" maxlength="100" style="width:753px"
                                       class=""/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>拟服务地点：</label>
                    </td>
                    <td colspan="3">

                        <form:textarea path="tpyNfwAddress" htmlEscape="false" rows="2" maxlength="15"
                                       style="width:753px"
                                       class=""/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>拟服务内容：</label>
                    </td>
                    <td colspan="3">

                        <form:textarea path="tpyNfwContent" htmlEscape="false" rows="2" maxlength="100"
                                       style="width:753px"
                                       class=""/>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>从事科技服务与创业经历：</label>
                    </td>
                    <td colspan="3">

                        <form:textarea path="tpyExperience" htmlEscape="false" rows="2" maxlength="200"
                                       style="width:753px"
                                       class=""/>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>奖惩情况：</label>
                    </td>
                    <td colspan="3">

                        <form:textarea path="tpyJcSituation" htmlEscape="false" rows="2" maxlength="100"
                                       style="width:753px"
                                       class=""/>
                    </td>
                </tr>
            </table>
            <div class="btgroup form-actions">
                <input id="btnSubmit" type="button" class="btn btn-primary" value="提交" onclick="saveTpy()"/>
                <input id="btnCancel" type="button" class="btn btn-default" value="保存" onclick="updateTpy()"/>
            </div>
        </form:form>
    </div>
</div>
</body>


</html>