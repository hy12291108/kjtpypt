<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
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




        //保存
        function updateTpy() {
            var name=$("#name").val();
            if(name==""){
                alert("请输入单位名称");
            }else{
                $("#inputForm").attr("action", "/kjtpypt/a/UserSh/perfectInfoSave");
                $("#inputForm").submit();
            }
        }

        //提交
        function saveTpy() {
            var loginName = $("#loginName").val();
            var name = $("#name").val();
            if (loginName == "" ||  name == "") {
                alert("数据没填完，请填写数据");
            } else {
                $("#inputForm").attr("action", "/kjtpypt/a/UserSh/submitInfo");
                $("#inputForm").submit();
            }

        }
    </script>


</head>
<body>
<div class="container">
    <div class="signin001">
        <form:form id="inputForm" enctype="multipart/form-data" modelAttribute="user" method="post"
                   class="form-horizontal">
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
                        <form:input path="loginName" id="loginName" placeholder="请输入您的手机号码" readonly="true"
                                    htmlEscape="false"
                                    maxlength="50" class=" mobile" onkeyup=" mobileBind()"/>
                    </td>
                    <td>
                        <label>企业属性：</label>
                    </td>
                    <td>
                        <form:select path="tpyEnterpriseAttribute">
                            <form:option value="" label="--选择企业属性--"/>
                            <form:options items="${fns:getDictList('enterprise_attribute')}" itemLabel="label"
                                          itemValue="value"
                                          htmlEscape="false" class=""/>
                        </form:select>
                    </td>

                </tr>
                <tr>
                    <td>
                        <label>单位名称：</label>
                    </td>
                    <td colspan="3">
                        <form:input path="name" id="name" htmlEscape="false" maxlength="20" placeholder="请输入您的单位名称，最多可填20字" style="width:769px"  class="required"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>法人性质：</label>
                    </td>
                    <td>
                        <form:select path="tpyCorporateNature">
                            <form:option value="" label="--选择法人性质--"/>
                            <form:options items="${fns:getDictList('corporate_nature')}" itemLabel="label"
                                          itemValue="value"
                                          htmlEscape="false" class=""/>
                        </form:select>
                    </td>
                    <td>
                        <label>单位类型：</label>
                    </td>
                    <td>
                        <form:select path="corpType">
                            <form:option value="" label="--选择单位类型--"/>
                            <form:options items="${fns:getDictList('corp_type')}" itemLabel="label" itemValue="value"
                                          htmlEscape="false" class=""/>
                        </form:select>
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
                        <label>邮箱地址：</label>
                    </td>
                    <td>
                        <form:input path="email" htmlEscape="false" maxlength="100" placeholder="请输入您的邮箱号码" class=" email"/>
                    </td>

                </tr>
                <tr>
                    <td>
                        <label>单位成立日期：</label>
                    </td>
                    <td>
                        <form:input path="corpEstDate" htmlEscape="false" maxlength="100" class="Wdate "
                                    onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',isShowClear:false,maxDate:'%y-%M-%d'})"/>
                    </td>
                    <td>
                        <label>法定代表人：</label>
                    </td>
                    <td>
                        <form:input path="corpLegRepName" htmlEscape="false" placeholder="请输入您的法定代表人姓名" maxlength="100" class=""/>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>代表人职务：</label>
                    </td>
                    <td>
                        <form:input path="tpyPosition" htmlEscape="false" placeholder="请输入您的职务" maxlength="50"/>
                    </td>
                    <td>
                        <label>代表人职称：</label>
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
                        <label>代表人手机：</label>
                    </td>
                    <td>
                        <form:input path="mobile" id="mobile" readonly="true" placeholder="请输入您的手机号码" htmlEscape="false" maxlength="50"
                                    class=""/>
                    </td>
                    <td>
                        <label>联系人姓名：</label>
                    </td>
                    <td>
                        <form:input path="corpCorName" htmlEscape="false" maxlength="100" placeholder="请输入联系人姓名" class=""/>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>联系人手机：</label>
                    </td>
                    <td>
                        <form:input path="corpCorPhone" htmlEscape="false" maxlength="100" placeholder="请输入联系人手机号码" class=" mobile"/>
                    </td>
                    <td>
                        <label>统一社会信用代码：</label>
                    </td>
                    <td>
                        <form:input path="corpOrgCode" htmlEscape="false" placeholder="请输入统一社会信用代码" maxlength="100"/>
                    </td>
                </tr>


                <tr>
                    <td>
                        <label>单位地址：</label>
                    </td>
                    <td colspan="3">
                        <form:textarea path="tpyAddress" htmlEscape="false" placeholder="请输入您的单位地址，最多可填20字" rows="2" maxlength="20"
                                       style="width:769px"/>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>营业范围：</label>
                    </td>
                    <td colspan="3">
                        <form:textarea path="corpScale" id="corpScale" htmlEscape="false" placeholder="请输入您的营业范围，最多可填100字" rows="2" maxlength="20"
                                       style="width:769px"/>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>拟服务地点：</label>
                    </td>
                    <td colspan="3">
                        <form:textarea path="tpyNfwAddress" htmlEscape="false" rows="2" maxlength="15" placeholder="请输入您的拟服务地点，最多可填100字"
                                       style="width:769px"
                                       class=""/>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>科技优势与服务内容：</label>
                    </td>
                    <td colspan="3">
                        <form:textarea path="corpMajor" id="corpMajor" htmlEscape="false" rows="2" maxlength="200" placeholder="请输入您的科技优势与服务内容，最多可填200字"
                                       style="width:769px"/>
                    </td>

                </tr>


                <tr>
                    <td>
                        <label>拟开展的服务内容：</label>
                    </td>
                    <td colspan="3">

                        <form:textarea path="tpyNfwContent" htmlEscape="false" rows="2" maxlength="100" placeholder="请输入您的拟开展的服务内容，最多可填100字"
                                       style="width:769px"
                                       class=""/>
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>科技服务与创业情况：</label>
                    </td>
                    <td colspan="3">
                        <form:textarea path="tpyExperience" htmlEscape="false" rows="2" maxlength="200" placeholder="请输入您的科技服务与创业情况，最多可填200字"
                                       style="width:769px"
                                       class=""/>
                    </td>
                </tr>
                <tr>
                    <td><label>营业执照上传：</label></td>
                    <td colspan="2">
                        <form:hidden id="photo" path="photo" htmlEscape="false" maxlength="255" class="input-xlarge"/>
                        <sys:ckfinder input="photo" type="thumb" uploadPath="/tpyRegister/license"
                                      selectMultiple="false" maxHeight="300" maxWidth="300"/>
                    </td>
                    <td><em>*注：营业执照上传</em></td>
                </tr>

                <tr>
                    <td class="tit">
                        <label>推荐表上传：</label>
                    </td>
                    <td colspan="2">
                        <form:hidden id="tjTableImage" path="tjTableImage" htmlEscape="false" maxlength="255"
                                     class="input-xlarge"/>
                        <sys:ckfinder input="tjTableImage" type="thumb" uploadPath="/tpyRegister/tjTableImage"
                                      selectMultiple="false" maxHeight="300" maxWidth="300"/>
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