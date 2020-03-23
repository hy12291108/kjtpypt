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
    <%--<link href="${ctxStatic}/fileUpload/css/iconfont.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/fileUpload/css/fileUpload.css" rel="stylesheet" type="text/css">
    <script src="${ctxStatic}/fileUpload/js/fileUpload.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${ctxStatic}/centermenu-master/css/animate.css">
    <link rel="stylesheet" href="${ctxStatic}/centermenu-master/css/toast.css">
    <link rel="stylesheet" href="${ctxStatic}/centermenu-master/css/centermenu.css">
    <script src="${ctxStatic}/centermenu-master/js/toast.js" type="text/javascript"></script>
    <script src="${ctxStatic}/centermenu-master/js/centermenu.js" type="text/javascript"></script>--%>

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


        function initZy() {
            $("#tpyMajor").empty();
            $("<option value=''>--选择专业名称--</option>").appendTo("#tpyMajor");//添加下拉框的option
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
                        for (var i = 0; i < json.majorList.length; i++) {
                            $("<option value='" + json.majorList[i].name + "'>" + json.majorList[i].name + "</option>").appendTo("#tpyMajor");//添加下拉框的option
                        }
                    }
                });
            }
        }

        /**
         * 身份证校验并且获取生日
         * @param obj 身份证
         */
        function autoBackFill(obj) {

             //正则表达式验证身份证号码
            var ID = /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
            //验证身份证号码是否正确，返回值为true false
            var isCorrect = ID.test(obj.value);

            if (isCorrect) {
                //验证通过自动计算年龄和出生日期
                //截取身份证中的年份
                var year = obj.value.substring(6, 10);
                //获取月份
                var month = obj.value.substring(10, 12);
                //获取出生日
                var day = obj.value.substring(12, 14);
                //去0处理。当月份和日期中有0时。自动省略。不显示
                $("#birthData").val(year+"-"+ month + "-" + day);
                //document.getElementById("birthData").value =year+"-"+ month + "-" + day;
            } else {
                alert("你的身份证有误，请重新输入");
            }
        }

        //保存
        function updateTpy() {
            $("#inputForm").attr("action", "/kjtpypt/a/UserSh/perfectInfoSave");
            $("#inputForm").submit();
        }

        //提交
        function saveTpy() {
            var loginName=$("#loginName").val();
            var photo=$("#photo").val();
            var name=$("#name").val();
            if(loginName==""||photo==""||name==""){
                alert("数据没填完，请填写数据");
            }else{
                $("#inputForm").attr("action", "/kjtpypt/a/UserSh/submitInfo");
                $("#inputForm").submit();
            }

        }
    </script>


</head>
<body>
<div class="container">
    <div class="signin001">
        <form:form id="inputForm" enctype="multipart/form-data" modelAttribute="user" method="post" class="form-horizontal">
            <form:hidden path="id"/>
            <form:hidden path="loginFlag"/>
            <form:hidden path="personFlag"/>
            <form:hidden path="checkFlag"/>
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
                    <td rowspan="4"><label>头像：</label></td>
                    <td rowspan="4">
                        <%--<img src="${pageContext.request.contextPath}/${user.photo }" style="height: 150px;width:120px"/>
                        <input type="file" name="pictureFile" id="pictureFile" value="请选择图片" />--%>
                        <form:hidden id="photo" path="photo" htmlEscape="false" maxlength="255" class="input-xlarge"/>
                        <sys:ckfinder input="photo" type="thumb" uploadPath="/tpyRegister/photo" selectMultiple="false"  maxHeight="130" maxWidth="100"/>
                    </td>

                </tr>
                <tr>

                    <td>
                        <label>姓名：</label>
                    </td>
                    <td>
                        <form:input id="name" path="name" htmlEscape="false" placeholder="请输入您的姓名" maxlength="50" class=""/>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>性别：</label>
                    </td>
                    <td>
                        <form:select path="sex">
                            <form:option value="" label="--选择性别--"/>
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
                        <form:input path="tpyNation" htmlEscape="false" placeholder="请输入您的民族" maxlength="50" class=""/>
                    </td>
                </tr>

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
                        <label>籍贯：</label>
                    </td>
                    <td>
                        <form:input path="tpyLocation" htmlEscape="false" placeholder="请输入您的籍贯" maxlength="50"
                                    class=""/>
                    </td>

                </tr>

                <form:input path="company.id" htmlEscape="false" type="hidden"/>
                <tr>

                    <td>
                        <label>工作单位名称：</label>
                    </td>
                    <td colspan="3">
                        <form:textarea path="tpyCompany" htmlEscape="false" rows="1" maxlength="15"
                                       placeholder="请输入您的工作单位名称，最多可填15字"
                                       style="width:753px" class=""/>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>职务：</label>
                    </td>
                    <td>
                        <form:input path="tpyPosition" htmlEscape="false" placeholder="请输入您的职务" maxlength="50"/>
                    </td>
                    <td>
                        <label>职称：</label>
                    </td>
                    <td>
                        <form:select path="tpyTitle">
                            <form:option value="" label="--选择职称--"/>
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
                            <form:option value="" label="--选择学历--"/>
                            <form:options items="${fns:getDictList('tpy_qulification')}" itemLabel="label"
                                          itemValue="value" htmlEscape="false" class=""/>
                        </form:select>
                    </td>
                    <td>
                        <label>政治面貌：</label>
                    </td>
                    <td>
                        <form:select path="tpyPolitical">
                            <form:option value="" label="--选择政治面貌--"/>
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
                        <form:input path="email" htmlEscape="false" maxlength="100" placeholder="请输入您的邮箱号码"
                                    class="email"/>
                    </td>
                    <td>
                        <label>手机：</label>
                    </td>
                    <td>
                        <form:input path="mobile" id="mobile" readonly="true" htmlEscape="false" placeholder="请输入您的手机号码"
                                    maxlength="50"
                                    class=""/>
                    </td>
                </tr>


                <tr>
                    <td>
                        <label>身份证号/护照：</label>
                    </td>
                    <td>
                        <form:input path="tpyIdcard" id="tpyIdcard" htmlEscape="false" maxlength="20"
                                    placeholder="请输入您的身份证号码" class="" onblur="autoBackFill(this)"/>
                    </td>
                    <td>
                        <label>出生日期：</label>
                    </td>
                    <td>
                        <form:input id="birthData" path="tpyBirthDate" class="Wdate " htmlEscape="false" maxlength="40"
                                    placeholder="请输入您的出生日期"
                                    onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',isShowClear:false,maxDate:'%y-%M-%d'})"/>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>服务形式：</label>
                    </td>
                    <td>
                        <form:select path="tpyServiceMode">
                            <form:option value="" label="--选择服务形式--"/>
                            <form:options items="${fns:getDictList('service_mode')}" itemLabel="label" itemValue="value"
                                          htmlEscape="false"/>
                        </form:select>
                    </td>
                    <td>
                        <label>人才类型：</label>
                    </td>
                    <td>
                        <form:select path="tpyTalentType">
                            <form:option value="" label="--选择人才分类--"/>
                            <form:options items="${fns:getDictList('talent_type')}" itemLabel="label" itemValue="value"
                                          htmlEscape="false"/>
                        </form:select>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>专业类别：</label></td>
                    <td>
                        <form:select id="yjxkdm" path="tpyMajorType" class="ch-select " onchange="initZy()">
                            <form:option value="" label="--选择专业类别--"/>
                            <form:options items="${tpyMajorTypeList }" itemLabel="name" itemValue="id"
                                          htmlEscape="false"/>
                        </form:select>
                    </td>
                    <td><label>专业名称：</label></td>
                    <td>
                        <form:select id="tpyMajor" path="tpyMajor" class="ch-select ">
                            <form:option value="" label="--选择专业名称--"/>
                            <form:options items="${tpyMajorList }" itemLabel="name" itemValue="name"
                                          htmlEscape="false"/>
                        </form:select>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>专业擅长：</label>
                    </td>
                    <td colspan="3">
                        <form:textarea path="tpySpecial" htmlEscape="false" rows="3" placeholder="请输入您的专业擅长，最多可填100字"
                                       maxlength="100" style="width:753px"
                                       class=""/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>拟服务地点：</label>
                    </td>
                    <td colspan="3">
                        <form:textarea path="tpyNfwAddress" htmlEscape="false" rows="3" maxlength="15"
                                       placeholder="请输入您的拟服务地点，最多可填15字"
                                       style="width:753px"
                                       class=""/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>拟服务内容：</label>
                    </td>
                    <td colspan="3">
                        <form:textarea path="tpyNfwContent" htmlEscape="false" rows="3" maxlength="100"
                                       placeholder="请输入您的拟服务地点，最多可填100字"
                                       style="width:753px"
                                       class=""/>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>从事科技服务与创业经历：</label>
                    </td>
                    <td colspan="3">
                        <form:textarea path="tpyExperience" htmlEscape="false" rows="3" maxlength="200"
                                       placeholder="请输入您的从事科技服务与创业经历，最多可填200字"
                                       style="width:753px"
                                       class=""/>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>奖惩情况：</label>
                    </td>
                    <td colspan="3">
                        <form:textarea path="tpyJcSituation" htmlEscape="false" rows="3" maxlength="100"
                                       placeholder="请输入您的拟服务地点，最多可填100字"
                                       style="width:753px" class=""/>
                    </td>
                </tr>

                <tr>
                    <td class="tit">
                        <label>推荐表上传：</label>
                    </td>
                    <td colspan="2">
                        <form:hidden id="tjTableImage" path="tjTableImage" htmlEscape="false" maxlength="255" class="input-xlarge"/>
                        <sys:ckfinder input="tjTableImage" type="thumb" uploadPath="/tpyRegister/tjTableImage" selectMultiple="false"  maxHeight="300" maxWidth="300"/>
                    </td>
                    <td><em>*注：推荐表上传</em></td>
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