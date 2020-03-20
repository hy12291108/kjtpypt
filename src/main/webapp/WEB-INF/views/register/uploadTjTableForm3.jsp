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
    <link href="/kjtpypt/static/classify/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/kjtpypt/static/classify/css/css.css" rel="stylesheet" type="text/css">

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


        $(document).ready(function () {
            $("#fileUploadContent").initUpload({
                "uploadUrl": "/kjtpypt/a/UserRegister/uploadImage",//上传文件信息地址
                autoCommit: false,//文件是否自动上传
                "maxFileNumber":1,//文件个数限制，为整数
                "fileType": ['png', 'jpg']//文件类型限制，默认不限制，注意写的是文件后缀
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
            alert("上传！！")
            uploadEvent.uploadFileEvent(option, 5);
        }
    </script>
</head>
<body>
<br/>
<div class="container">
    <div class="signin001">
        <form:form id="inputForm" modelAttribute="user" method="post" class="form-horizontal">
            <sys:message content="${message}"/>
            <table cellpadding="0" cellspacing="0" width="100%">
                <tr>
                    <td class="tit">
                        <button type="button" class="btn btn-primary" id="tit">选择图片</button>
                    </td>
                    <td colspan="2">
                        <div id="fileUploadContent" class="fileUploadContent" class="required"></div>
                    </td>
                    <td><em>*注：特派员推荐表上传</em></td>
                </tr>
            </table>
            <form:hidden path="id"/>
            <form:input path="tjTableImage" id="tjTableImage" htmlEscape="false" type="hidden" value=""/>  <%--推荐表--%>
            <div class="form-actions">
                <input id="btnSubmit" class="btn btn-primary" type="submit" value="保存" onclick="tpyUploadSave()"/>&nbsp;
            </div>
        </form:form>
    </div>
</div>
</body>
</html>