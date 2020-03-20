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
    <!-- 上传图片 20171026-->
    <link href="${ctxStatic}/fileUpload/css/iconfont.css" rel="stylesheet" type="text/css"/>
    <link href="${ctxStatic}/fileUpload/css/fileUpload.css" rel="stylesheet" type="text/css">
    <script src="${ctxStatic}/fileUpload/js/fileUpload.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#fileUploadContent").initUpload({
                "uploadUrl": "/kjtpypt/a/UserRegister/uploadImage",//上传文件信息地址
                autoCommit: false,//文件是否自动上传
                "maxFileNumber": 1,//文件个数限制，为整数
                "fileType": ['jpg']//文件类型限制，默认不限制，注意写的是文件后缀
            });
            $("#inputForm").validate({
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

        function tpyUploadSave() {
            uploadEvent.uploadFileEvent(option, 5);
        }

    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/UserSh/uploadTjTableForm">特派员推荐表上传</a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="user" method="post" class="form-horizontal">
    <sys:message content="${message}"/>
    <table class="table  table-bordered  ">
        <c:if test="${empty user.tjTableImage}">
            <tr>
                <td class="tit">
                    <button type="button" class="btn btn-primary" id="tit">选择图片</button>
                </td>
                <td colspan="2">
                    <div id="fileUploadContent" class="fileUploadContent" class="required"></div>
                </td>
                <td><span class="help-inline"><font color="red">*注：推荐表图片需重新上传</font></span></td>
            </tr>
        </c:if>
        <c:if test="${user.tjTableImage!=null&&user.tjTableImage!=''}">
        <tr>
            <td>
                <img id="fileUploadContent" src="${ctx}/UserSh/getImage?id=${user.id}&type=1"/>
            </td>
        </tr>
        </c:if>
    </table>
    <form:hidden path="id"/>
    <form:input path="tjTableImage" id="tjTableImage" htmlEscape="false" type="hidden"/>
    <c:if test="${empty user.tjTableImage}">
        <div class="btgroup form-actions table-bordered-bt">
            <input id="btnSubmit" type="submit" class="btn btn-primary" value="保存" onclick="tpyUploadSave()">
            <input id="btnCancel" type="button" class="btn btn-default" value="返回" onclick="history.go(-1)"/>
        </div>
    </c:if>
</form:form>
</body>
</html>