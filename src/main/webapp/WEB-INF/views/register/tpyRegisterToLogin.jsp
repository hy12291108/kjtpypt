<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>${fns:getConfig('productName')} 登录</title>
    <meta name="decorator" content="blank"/>
    <style type="text/css">
        html, body, table {
            background-color: #f5f5f5;
            width: 100%;
            text-align: center;
            height: 100%;
        }

        .form-signin-heading {
            font-family: Helvetica, Georgia, Arial, sans-serif, 黑体;
            font-size: 36px;
            margin-bottom: 20px;
            color: #0663a2;
        }

        .form-signin {
            position: relative;
            text-align: left;
            width: 300px;
            padding: 25px 29px 24px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
        }

        .form-signin .checkbox {
            margin-bottom: 10px;
            color: #0663a2;
        }

        .form-signin .input-label {
            font-size: 16px;
            line-height: 23px;
            color: #999;
        }

        .form-signin .input-block-level {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px;
            *width: 283px;
            *padding-bottom: 0;
            _padding: 7px 7px 9px 7px;
        }

        .form-signin .btn.btn-large {
            font-size: 16px;
        }

        .form-signin #themeSwitch {
            position: absolute;
            right: 15px;
            bottom: 10px;
        }

        .form-signin div.validateCode {
            padding-bottom: 15px;
        }

        .mid {
            vertical-align: middle;
        }

        .header {
            height: 140px;
            padding-top: 20px;
        }

        .alert {
            position: relative;
            width: 300px;
            margin: 0 auto;
            *padding-bottom: 0px;
        }

        label.error {
            background: none;
            width: 270px;
            font-weight: normal;
            color: inherit;
            margin: 0;
        }

        /* pop */
        #pop {
            background: #fff;
            width: 220px;
            border: 1px solid #e0e0e0;
            font-size: 12px;
            position: fixed;
            right: 0px;
            bottom: 0px;
        }

        #popHead {
            line-height: 32px;
            background: #f6f0f3;
            border-bottom: 1px solid #e0e0e0;
            position: relative;
            font-size: 12px;
            padding: 0 0 0 10px;
        }

        #popHead h2 {
            font-size: 14px;
            color: #438cce;
            line-height: 32px;
            height: 32px;
            margin: 0;
        }

        #popHead #popClose {
            position: absolute;
            right: 10px;
            top: 1px;
        }

        #popHead a#popClose:hover {
            color: #f00;
            cursor: pointer;
        }

        #popContent {
            padding: 5px 10px;
        }

        #popTitle a {
            line-height: 24px;
            font-size: 14px;
            font-family: '微软雅黑';
            color: #333;
            font-weight: bold;
            text-decoration: none;
        }

        #popTitle a:hover {
            color: #f60;
        }

        #popIntro {
            text-indent: 24px;
            line-height: 160%;
            margin: 5px 0;
            color: #666;
        }

        #popMore {
            border-top: 1px dotted #ccc;
            line-height: 24px;
            margin: 8px 0 0 0;
        }

        #popMore a {
            color: #438cce;
        }

        #popMore a:hover {
            color: #f00;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#loginForm").validate({
                rules: {
                    validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
                },
                messages: {
                    username: {required: "请填写用户名."}, password: {required: "请填写密码."},
                    validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
                },
                errorLabelContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    error.appendTo($("#loginError").parent());
                }
            });
        });

        function downTpyExcel() {
            $("#loginForm").attr("action", "${ctx}/UserRegister/downTpyExcel");
            $("#loginForm").submit();
        }

        function login() {
            $("#loginForm").attr("action", "${ctx}/login");
            $("#loginForm").submit();
        }

    </script>
</head>
<body>
<div class="login">
    <br/>
    <div class="header">
        <c:if test="${!empty message}">
            <div id="div" class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                    ${message}
            </div>
        </c:if>
    </div>
    <h1 class="form-signin-heading">${fns:getConfig('productName')}</h1>
    <form id="loginForm" class="form-signin" method="post">
        <label class="input-label" for="username">登录名</label>
        <input type="text" id="username" name="username" placeholder="请输入您注册的手机号码" class="input-block-level required" value="${username}">
        <label class="input-label" for="password">密码</label>
        <input type="password" id="password" name="password" placeholder="请输入您注册的登陆密码" class="input-block-level required">
        <c:if test="${isValidateCodeLogin}">
            <div class="validateCode">
                <label class="input-label mid" for="validateCode">验证码</label>
                <sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
            </div>
        </c:if>
        <input class="btn btn-large btn-primary" type="submit" value="登 录" onclick="login()"/>&nbsp;&nbsp;
        <label for="rememberMe" title="下次不需要再登录"><input type="checkbox" id="rememberMe"
                                                        name="rememberMe" ${rememberMe ? 'checked' : ''}/>
            记住我（公共场所慎用）</label>
        <br>
        <table class="signin">
            <tr style="border-bottom:1px solid #e5e5e5;">
            <tr>
                <td align="left"><input id="btnSubmit" type="submit" class="btn btn-primary" value="下载特派员推荐表"
                                        onclick="downTpyExcel()"></td>
            </tr>
        </table>
        <input id="tpyExcelUrl" name="tpyExcelUrl" type="hidden" value="${tpyExcelUrl}">
    </form>

    <div class="footer">
        <%

        %>
        Copyright &copy; ${fns:getConfig('copyrightYear')} <a
            href="${pageContext.request.contextPath}${fns:getFrontPath()}">${fns:getConfig('productName')}</a> - Powered
        By <a href="#" target="_blank">协同数码股份有限公司</a> ${fns:getConfig('version')}
    </div>
</div>
<!-- APP下载框 -->
<script type="text/javascript" src="/kjtpypt/static/skines/js/jquery.min.js"></script>
<script type="text/javascript" src="/kjtpypt/static/skines/js/yanue.pop.js"></script>
<script type="text/javascript">
    //记得加载jquery
    //使用参数：1.标题，2.链接地址，3.内容简介
    window.onload = function () {
        var pop = new Pop("订阅号关注", "", "");
    }
</script>
<div id="pop" style="display:none;">

    <div id="popHead"><a id="popClose" title="关闭"><font color="#438cce">关闭</font></a>
        <h2>订阅号及QQ群</h2>
    </div>
    <div id="popContent">
        <p id="popMore"><font style='color:#438cce'>扫描关注微信订阅号<img src='/kjtpypt/static/skines/images/dyh.png'/></font>
        </p>
        <p id="popMore"><font style='color:#438cce'>陕西科特派系统管理群<img
                src='/kjtpypt/static/skines/images/qqqun.png'/></font></p>
    </div>

</div>
</body>
</html>