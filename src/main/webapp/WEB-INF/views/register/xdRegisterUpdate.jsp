<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>服务对象注册</title>
<meta name="decorator" content="default"/>
<meta name="decorator" content="default"/>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" /><meta name="author" content="http://jeesite.com/"/>
<meta name="renderer" content="webkit"><meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
<meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache"><meta http-equiv="Cache-Control" content="no-store">
<script type="text/javascript">
		$(document).ready(function() {
		/* 	$("#no").focus(); */
			$("#inputForm").validate({
				rules: {
					loginName: {remote: "/kjtpypt/a/UserRegister/checkLoginName?oldLoginName=" + encodeURIComponent('${user.loginName}')}
				},
				messages: {
					loginName: {remote: "用户登录名已存在"},
					confirmNewPassword: {equalTo: "输入与上面相同的密码"}
				},
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		/* 联系人号码校验*/
		function phoneCheck(){
		 var corpCorPhone = $.trim($('#corpCorPhone').val());
		 var num = /^\d*$/; //全数字
		   if(!num.exec(corpCorPhone)) {
		     alert("號碼必须全为数字");
		     $("#corpCorPhone").focus();
		     return false;
		   }
		 if(corpCorPhone.length!= 11) {
		     alert("號碼长度不符,长度为11位");
		     $("#corpCorPhone").focus();
		     return false;
		   }
		}
		/* 法定代表人号码校验*/
		function phoneCheck1(){
		 var mobile = $.trim($('#mobile').val());
		 var num = /^\d*$/; //全数字
		   if(!num.exec(mobile)) {
		     alert("號碼必须全为数字");
		     $("#mobile").focus();
		     return false;
		   }
		 if(mobile.length!= 11) {
		     alert("號碼长度不符,长度为11位");
		     $("#mobile").focus();
		     return false;
		   }
		}
		function xdRegister() {
		$("#inputForm").attr("action","/kjtpypt/a/UserRegister/xdsave");
		$("#inputForm").submit();	
		}	
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li ><a href="${ctx}/UserRegister/registerResult">审核结果查看</a></li>
		<li class="active"><a href="${ctx}/UserRegister/registerUpdate">服务对象信息修改</a></li>
	</ul><br/>
		<form:form id="inputForm" modelAttribute="user" method="post" class="form-horizontal">
			<form:hidden path="id"/>
			<table  class="table  table-bordered">
					<tr>
						<td>
							<label>登录名：</label>
						</td>
						<td colspan="3">
							<input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}">
							<form:input path="loginName" htmlEscape="false" maxlength="50" class="required userName"/>
							<span class="help-inline"><font color="red">*</font></span>
						</td>
					</tr>
					<form:input path="roleIdList" value="abf520ca23614c0da7dfd4057485f6ed" htmlEscape="false" type="hidden"/>
					<tr>
						<td>
							<label>名称：</label>
						</td>
						<td >
							<form:input path="name" htmlEscape="false" maxlength="50" class="required"/>
							<!-- <input name="name" type="text" required> -->
							<span class="help-inline"><font color="red">*</font></span>
						</td>
						<td>
							<label>单位类型：</label>
						</td>
						<td>
							<form:select path="corpType" class="input-xlarge">
								<form:options items="${fns:getDictList('corp_type')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
							</form:select>
							<span class="help-inline"><font color="red">*</font></span>
						</td>
					</tr>
					<form:input path="company.id"  htmlEscape="false" type="hidden"/>
					<tr>
						<td>
							<label>管辖部门：</label>
						</td>
						<td>
							<sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}"
						title="部门" url="/UserRegister/treeData?type=2" cssClass="required" notAllowSelectParent="true"/><span class="help-inline"><font color="red">*</font></span>
						</td>
						<td>
							<label>用工人数：</label>
						</td>
						<td>
							<form:input path="corpNumWorker" htmlEscape="false" maxlength="100"/>
						</td>
					</tr>
					
					<tr>
						<td>
							<label>统一社会信用代码：</label>
						</td>
						<td>
							<form:input path="corpOrgCode" htmlEscape="false" maxlength="100"/>
						</td>
						<td>
							<label>注册资本(单位:万元)：</label>
						</td>
						<td>
							<form:input path="corpZczb" htmlEscape="false" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td>
							<label>单位成立日期：</label>
						</td>
						<td>
							<form:input path="corpEstDate" htmlEscape="false" maxlength="100" class="Wdate required" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',isShowClear:false,maxDate:'%y-%M-%d'})"/>
							<span class="help-inline"><font color="red">*</font></span>
						</td>
						<td>
							<label>Email：</label>
						</td>
						<td>
							<form:input path="email" htmlEscape="false" maxlength="100" class="required email" />
							<span class="help-inline"><font color="red">*</font></span>
						</td>
					</tr>
					<tr>
						<td>
							<label>法定代表人：</label>
						</td>
						<td>
							<form:input path="corpLegRepName" htmlEscape="false" maxlength="100" class="required"/>
							<span class="help-inline"><font color="red">*</font></span>
						</td>
						<td>
							<label>电话：</label>
						</td>
						<td>
						<form:input path="mobile"  id="mobile" htmlEscape="false" maxlength="50" class="required" onchange="phoneCheck1()"/>
						<span class="help-inline"><font color="red">*</font> </span>
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
							<label>电话：</label>
						</td>
						<td>
							<form:input path="corpCorPhone" id="corpCorPhone" htmlEscape="false" maxlength="100" class="required" onchange="phoneCheck()"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</td>
					</tr>
					<tr>
						<td>
							<label>资产总额(单位:万元)：</label>
						</td>
						<td>
							<form:input path="corpScale" htmlEscape="false" maxlength="100"/>
						</td>
						<td>
							<label>上年收入(单位:万元)：</label>
						</td>
						<td>
							<form:input path="corpExIncome" htmlEscape="false" maxlength="100"/>
						</td>
					</tr>
					<tr>
						<td>
							<label>技术需求：</label>
						</td>
						<td colspan="3">
							<form:textarea path="corpNeeds" htmlEscape="false" rows="2" maxlength="200" style="width:750px"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</td>
						
					</tr>
					<tr>
						<td>
							<label>备注：</label>
						</td>
						<td colspan="3">
							<form:textarea path="remarks" htmlEscape="false" rows="2" maxlength="200" style="width:750px"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</td>
					</tr>
			</table>
			<form:input path="loginFlag" value="1" htmlEscape="false" type="hidden"/>
			<form:input path="personFlag" value="1" htmlEscape="false" type="hidden"/>
			<form:input path="checkFlag" value="0" htmlEscape="false" type="hidden"/>
			<div class="btgroup form-actions table-bordered-bt">
				<input id="btnSubmit"  type="submit" class="btn btn-primary" onclick="xdRegister()" value="保存">
				<input id="btnCancel"  type="button" class="btn btn-default" value="返回" onclick="history.go(-1)"/>
			</div>
		</form:form>
</body>
</html>