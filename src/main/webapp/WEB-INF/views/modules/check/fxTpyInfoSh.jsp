<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>用户管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#no").focus();
            $("#inputForm").validate({
                rules: {
                    loginName: {remote: "${ctx}/sys/user/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')}
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

        function shPass() {
            $("#inputForm").attr("action", "/kjtpypt/a/UserSh/tpyShResult?checkFlag=2");
        }

        function shFail() {
            $("#inputForm").attr("action", "/kjtpypt/a/UserSh/tpyShResult?checkFlag=1");
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/UserSh/tpyShForm">反向特派员列表</a></li>
    <li class="active"><a href="${ctx}/UserSh/tpyInfoSh?id=${user.id}">反向特派员<shiro:hasPermission
            name="sys:user:edit">信息审核</shiro:hasPermission></a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="user" action="/kjtpypt/a/UserSh/tpyShResult" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <form:input path="company.id" value="${user.company.name}" htmlEscape="false" type="hidden"/>
    <table id="contentTable" class="table  table-bordered  ">
        <tbody>
        <tr>
            <td>姓名:</td>
            <td>${user.name}</td>
            <td>性别：</td>
            <td>${fns:getDictLabel(user.sex,'sex',user.sex)}</td>
            <td rowspan="6" >头像：</td>
            <%--<td rowspan="6"><img src="${ctx}/UserSh/getImage?id=${user.id}&type=0" style="width: 140px;height: 200px"></td>--%>
            <td rowspan="6"><img src="${user.photo}" style="width: 140px;height: 200px"></td>
        </tr>
        <tr>
            <td>民族：</td>
            <td>${user.tpyNation}</td>
            <td>籍贯：</td>
            <td>${user.tpyLocation}</td>
        </tr>
        <tr>
            <td>所属区域:</td>
            <td>${user.office.name}</td>
            <td>工作单位名称：</td>
            <td>${user.tpyCompany}</td>
        </tr>
        <tr>
            <td>职务：</td>
            <td>${user.tpyPosition}</td>
            <td>职称：</td>
            <td>${fns:getDictLabel(user.tpyTitle,'tpy_title',user.tpyTitle)}</td>
        </tr>
        <tr>
            <td>学历:</td>
            <td> ${fns:getDictLabel(user.tpyQulification,'tpy_qulification',user.tpyQulification)}</td>
            <td>政治面貌：</td>
            <td> ${fns:getDictLabel(user.tpyPolitical,'political',user.tpyPolitical)}</td>
        </tr>
        <tr>
            <td>邮箱：</td>
            <td>${user.email}</td>
            <td>手机：</td>
            <td>${user.mobile}</td>
        </tr>
        <tr>
            <td>出生日期：</td>
            <td>${user.tpyBirthDate}</td>
            <td>身份证号/护照：</td>
            <td>${user.tpyIdcard}</td>
            <td>人才类型：</td>
            <td>${fns:getDictLabel(user.tpyTalentType,'talent_type',user.tpyTalentType)}</td>
        </tr>
        <tr>
            <td>服务优势：</td>
            <td colspan="5">
                    ${fns:getDictLabel(user.tpyServiceAdvantages,'service_advantages',user.tpyServiceAdvantages)}
            </td>
        </tr>
        <tr>
            <td>
                <lable>拟服务地点:</lable>
            </td>
            <td colspan="5">
                    ${user.tpyNfwAddress}
            </td>
        </tr>
        <tr>
            <td>
                <lable>拟服务内容:</lable>
            </td>
            <td colspan="5">
                    ${user.tpyNfwContent}
            </td>
        </tr>
        <tr>
            <td>
                <lable>从事科技服务与创业经历:</lable>
            </td>
            <td colspan="5">
                    ${user.tpyExperience}
            </td>
        </tr>
        <tr>
            <td>
                <lable>奖惩情况:</lable>
            </td>
            <td colspan="5">
                    ${user.tpyJcSituation}
            </td>
        </tr>
        <tr>
            <td>推荐表图片:</td>
            <td colspan="5">
                <%--<img src="${ctx}/UserSh/getImage?id=${user.id}&type=1" style="width:500px;height:600px"/>--%>
                    <img src="${user.tjTableImage}" style="width:500px;height:600px"/>
            </td>
        </tr>
        <%--<tr>
            <td>推荐表图片:</td>
            <td colspan="5">
                <c:if test="${empty imagePathList}">
                    <img alt="暂无图片" src=""/> <br/>
                </c:if>
                <c:if test="${imagePathList==null}">
                    <img alt="暂无图片" src=""/> <br/>
                </c:if>
                <c:if test="${!empty imagePathList}">
                    <c:forEach items="${imagePathList}" var="imagename">
                        <img alt="暂无图片" src="/tjImage${imagename}" style="width:500px;height:600px"/>
                    </c:forEach>
                </c:if>
            </td>
        </tr>--%>
        <tr>
            <td>
                <lable>审核结果:</lable>
            </td>
            <td>
                <form:select path="checkFlag" class="input-xlarge">
                    <form:options items="${fns:getDictList('check_flag')}" itemLabel="label" itemValue="value"
                                  htmlEscape="false" class="required"/>
                </form:select>
                <span class="help-inline"><font color="red">*</font></span>
            </td>
            <td>
                <lable>审核人:</lable>
            </td>
            <td>
                    ${user.checkPerson}
            </td>
            <td>
                <lable>审核时间:</lable>
            </td>
            <td>
                    ${user.checkTime}
            </td>
        </tr>
        <tr>
            <td>
                <lable>审核意见:</lable>
            </td>
            <td colspan="5">
                <textarea name="checkAdvice" rows="3" placeholder="审核意见" style="width:800px" required></textarea>
                <span class="help-inline"><font color="red">*</font></span>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="form-actions">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保存"/>&nbsp;
        <input id="btnCancel" type="button" class="btn btn-default" value="返回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>