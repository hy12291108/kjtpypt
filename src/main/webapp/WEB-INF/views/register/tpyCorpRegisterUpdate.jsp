<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html  style="overflow-x:auto;overflow-y:auto;">
<head>
<title>用户管理</title>
<!--  <meta name="decorator" content="default"/>-->
<meta name="decorator" content="blank"/>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" /><meta name="author" content="http://jeesite.com/"/>
<meta name="renderer" content="webkit"><meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
<meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache"><meta http-equiv="Cache-Control" content="no-store">
<!-- 上传图片 20171012-->
<link href="${ctxStatic}/fileUpload/css/iconfont.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/fileUpload/css/fileUpload.css" rel="stylesheet" type="text/css">
<script src="${ctxStatic}/fileUpload/js/fileUpload.js" type="text/javascript"></script>
<script type="text/javascript">
		$(document).ready(function() {
			var option = null;
			$("#fileUploadContent").initUpload({
		        //"uploadUrl":"http://***/",//上传文件信息地址
		        "uploadUrl":"/kjtpypt/a/UserRegister/uploadImage",//上传文件信息地址
		        //"size":350,//文件大小限制，单位kb,默认不限制
		        //"maxFileNumber":3,//文件个数限制，为整数
		        //"filelSavePath":"",//文件上传地址，后台设置的根目录
		        //"beforeUpload":beforeUploadFun,//在上传前执行的函数
		        //"onUpload":onUploadFun，//在上传后执行的函数
		        autoCommit:false,//文件是否自动上传
		        "fileType":['png','jpg']//文件类型限制，默认不限制，注意写的是文件后缀
		    	});
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
		/*银行卡号校验*/
		function bankAccountCheck(){
			  var bankno = $.trim($('#bankAccount').val());
			  if(bankno == "") {
			   alert("请填写银行卡号");
			   $("#bankAccount").focus();
			     return false;
			   }
			   var num = /^\d*$/; //全数字
			   if(!num.exec(bankno)) {
			     alert("银行卡号必须全为数字");
			     $("#bankAccount").focus();
			     return false;
			   }
			   if(bankno.length < 16 || bankno.length > 21) {
			     alert("银行卡号长度不符,长度为16-21");
			     $("#bankAccount").focus();
			     return false;
			   }
		}
		function tpyCorpSave() {
		  uploadEvent.uploadFileEvent(option,1);  
		}	
		
	</script>
</head>
<body>
<ul class="nav nav-tabs">
		<li ><a href="${ctx}/UserRegister/registerResult">审核结果查看</a></li>
		<li class="active"><a href="${ctx}/UserRegister/registerUpdate">特派员（法人）信息</a></li>
		</ul><br/>
			<form:form id="inputForm" modelAttribute="user" method="post" class="form-horizontal">
				<form:hidden path="id"/>
				<table class="table  table-bordered">
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
					<tr>
						<td>
							<label>单位名称：</label>
						</td>
						<td colspan="3">
							<form:input path="name" htmlEscape="false" maxlength="100"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</td>
						
					</tr>
					<form:input path="company.id"  htmlEscape="false" type="hidden"/>
					<tr>
						<td>
							<label>管辖部门：</label>
						</td>
						<td>
							<sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}"
							title="部门" url="/UserRegister/treeData?type=2" cssClass="required" notAllowSelectParent="true"/>
						   <span class="help-inline"><font color="red">*</font> </span>
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
					<tr>
						<td>
							<label>营业范围：</label>
						</td>
						<td colspan="3">
							<form:textarea path="corpScale" htmlEscape="false" rows="2" maxlength="200" style="width:769px"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</td>
						
					</tr>
					<tr>
						<td>
							<label>科技优势与服务内容：</label>
						</td>
						<td colspan="3">
							<form:textarea path="corpMajor" htmlEscape="false" rows="2" maxlength="200" style="width:769px"/>
							<span class="help-inline"><font color="red">*</font> </span>
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
							<label>单位成立日期：</label>
						</td>
						<td>
							<form:input path="corpEstDate" htmlEscape="false" maxlength="100" class="Wdate required" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',isShowClear:false,maxDate:'%y-%M-%d'})"/>
							<span class="help-inline"><font color="red">*</font> </span>
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
							<label>电话：</label>
						</td>
						<td>
						<form:input path="mobile" htmlEscape="false" maxlength="50" class="required" onchange="phoneCheck1()"/>
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
							<form:input path="corpCorPhone" htmlEscape="false" maxlength="100" class="required" onchange="phoneCheck()"/>
						    <span class="help-inline"><font color="red">*</font> </span>
						</td>
					</tr>
					
					<tr>
						<td>
							<label>Email：</label>
						</td>
						<td>
							<form:input path="email" htmlEscape="false" maxlength="100" class="required email"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</td>
						<td>
							<label>邮政编码：</label>
						</td>
						<td>
							<form:input path="tpyPostCode" htmlEscape="false" maxlength="20"/>
						</td>
					</tr>
					<tr>
						<td>
							<label>通信地址：</label>
						</td>
						<td colspan="3">
							
							<form:textarea path="tpyAddress" htmlEscape="false" rows="2" maxlength="200" style="width:769px"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</td>
						
					</tr>
					<tr>
						<td>
							<label>银行账号：</label>
						</td>
						<td>
							<form:input path="bankAccount" id="bankAccount" htmlEscape="false" maxlength="100" class="required" onchange="bankAccountCheck()"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</td>
						<td>
							<label>开户名：</label>
						</td>
						<td>
							<form:input path="bankName" htmlEscape="false" maxlength="100" class="required"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</td>
					</tr>
					<tr>
						<td>
							<label>开户行：</label>
						</td>
						<td colspan="3">
							<form:textarea path="bankOpen" htmlEscape="false" rows="2" maxlength="200" style="width:769px"/>
							<span class="help-inline"><font color="red">*</font> </span>
						</td>
					</tr>
				<tr>
					<td><button type="button" class="btn btn-primary" id="tit">选择图片</button></td>
					<td colspan="2">
					 <div id="fileUploadContent" class="fileUploadContent" class="required"></div>   		
					</td>
					<td><span class="help-inline"><font color="red">*注：推荐表图片需重新上传</font></span></td>	
				</tr>		
				</table>
				<form:input path="tjTableImage" id="tjTableImage" htmlEscape="false" type="hidden"/>
				<form:input path="loginFlag" value="1" htmlEscape="false" type="hidden"/>
				<form:input path="personFlag" value="2" htmlEscape="false" type="hidden"/>
				<form:input path="checkFlag" value="0" htmlEscape="false" type="hidden"/>
				<form:input path="roleIdList" value="3bb6453c699d49508b15529670ad9e9b" htmlEscape="false" type="hidden"/>
				<div class="btgroup form-actions table-bordered-bt">
					<input id="btnSubmit"  type="submit" class="btn btn-primary" value="保存" onclick="tpyCorpSave()">
					<input id="btnCancel"  type="button" class="btn btn-default" value="返回" onclick="history.go(-1)"/>
				</div>
			</form:form>
</body>
</html>