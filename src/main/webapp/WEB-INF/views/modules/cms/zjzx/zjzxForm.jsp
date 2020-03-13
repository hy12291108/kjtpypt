<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>专家咨询</title>
<!-- 上传图片 20171107-->
<script src="/kjtpypt/static/skines/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<link href="/kjtpypt/static/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<script src="/kjtpypt/static/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<link href="/kjtpypt/static/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
<script src="/kjtpypt/static/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/fileUpload/css/iconfont.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/fileUpload/css/fileUpload.css" rel="stylesheet" type="text/css">
<script src="${ctxStatic}/fileUpload/js/fileUpload.js" type="text/javascript"></script>
<link rel="stylesheet" href="${ctxStatic}/centermenu-master/css/animate.css">
<link rel="stylesheet" href="${ctxStatic}/centermenu-master/css/toast.css">	
<link rel="stylesheet" href="${ctxStatic}/centermenu-master/css/centermenu.css">
<script src="${ctxStatic}/centermenu-master/js/toast.js" type="text/javascript"></script>
<script src="${ctxStatic}/centermenu-master/js/centermenu.js" type="text/javascript"></script>
<link href="/kjtpypt/static/skines/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="/kjtpypt/static/skines/css/css.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
$(document).ready(function() {
	var option = null;
	$("#fileUploadContent").initUpload({
        //"uploadUrl":"http://***/",//上传文件信息地址
       // "uploadUrl":"/a/UserRegister/uploadZjzxImage",//上传文件信息地址//正式服务器URL路径
         "uploadUrl":"/kjtpypt/a/UserRegister/uploadZjzxImage",//上传文件信息地址
        "size":3500,//文件大小限制，单位kb,默认不限制
        "maxFileNumber":3,//文件个数限制，为整数
        //"filelSavePath":"",//文件上传地址，后台设置的根目录
        //"beforeUpload":beforeUploadFun,//在上传前执行的函数
        //"onUpload":onUploadFun，//在上传后执行的函数
        autoCommit:false,//文件是否自动上传
        "fileType":['png','jpg']//文件类型限制，默认不限制，注意写的是文件后缀
    	});
	/* $("#inputForm").validate({
		submitHandler: function(form){
			  loading('正在提交，请稍等...');
              form.submit();
		}
	}); */
});
function zjzxsubmit(){
	if($("#title").val().trim()==""){
        $("#title").focus();
        top.$.jBox.tip('请输入标题','warning');
        return;
    }else if ($("#content").val().trim()==""){
    	$("#content").focus();
        top.$.jBox.tip('请填写问题描述','warning');
        return;
        }else if($("#userName").val()!=''&&$("#userName").val()!=null){
		uploadEvent.uploadFileEvent(option,4);
	}else{
		top.$.jBox.tip('登陆后才能咨询专家','warning');
	}
}
</script>
</head>
<body>
<div class="header">
	<div class="top">
		<h1><img src="/kjtpypt/static/skines/images/logo.png" width="560" height="42" alt=""/><span>陕西省科技特派员服务与管理系统</span></h1>
		<ul class="login">
		<c:if test="${user.name!=''&&user.name!=null}">
			<li><a href="#" class="bt">${user.name}已登录</a></li>
			<li><a href="/kjtpypt/a/UserRegister/logOff">退出</a></li>
		</c:if>
		<c:if test="${user.name==''||user.name==null}">
			<li><a href="/kjtpypt/a/UserRegister/temporaryLoginIndex" class="bt"> 登  录</a></li>
			<li>没有账户？<a href="/kjtpypt/a/UserRegister/temporary">立即注册</a></li>
		</c:if>
		</ul>
	</div>	
	<div class="navbg">
		<div class="nav">
			<ul>
				<li><a href="/kjtpypt/f/index-1.html">首  页</a></li>
				<li><a href="/kjtpypt/f/list-6.html" >通知通告</a></li>
				<li><a href="/kjtpypt/f/list-2.html" >动态新闻</a></li>
				<li><a href="/kjtpypt/f/list-10.html" >特派员风采</a></li>
				<li><a href="/kjtpypt/f/list-1000.html">学习园地</a></li>
				<li><a href="/kjtpypt/a/communication/listAll">用户交流</a></li>
				<li><a href="/kjtpypt/f/list-999.html" class="current">专家咨询</a></li>
				<li><a href="/kjtpypt/a/communication/contactMe">联系我们</a></li>
			</ul>
		</div>
	</div>
</div>

<div class="path">当前位置：专家咨询&gt;向专家提问</div>
<div class="container">
	<div class="signin002">
		<h2><span>向专家提问</span></h2>
		<form id="inputForm" method="post" class="form-horizontal" enctype="multipart/form-data">
		<input name="category.name" type="hidden"/>
		<input type="hidden" name="category.id"/>
		<input type="hidden" id="zjzxImage" name="image" required/>
		<input  type="hidden" id="userName" value="${user.name}">
		<table cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td><label><em>*&nbsp</em>标题：</label></td>
				<td><input type="text" name="title" id="title" class="input-xxlarge" style="width:80%"></td>
			</tr>
			<tr>
				<td><label><em>*&nbsp</em>问题描述：</label></td>
				<td>
					<textarea id="content"  name="articleData.content" rows="3" maxlength="200" class="input-xxlarge" style="width:80%"></textarea>
				</td>
			</tr>
			<tr>
				<td><label>视频附件：</label></td>
				<td>
					<input type="file" name="file" accept="audio/mpeg,audio/mp4,video/mp4" multiple="multiple">
				</td>
			</tr>
			<tr>
				<td>
				<label><em>*&nbsp</em><button type="button" class="btn btn-primary" id="tit">选择图片</button>：</label>
				</td>
				<td>
				<div id="fileUploadContent" class="fileUploadContent" class="required" style="width:800px;"></div> 
				</td>
			</tr>
		</table>
		<div class="btgroup form-actions">
			<input id="btnSubmit" type="button" class="btn btn-primary" value="提交" onclick="zjzxsubmit()">
			<input id="btnCancel" type="button" class="btn btn-default" value="返回">
		</div>
	</form>
	</div>
</div>
<div class="footer">陕西省科技特派员服务与管理系统 版权所有     Copyright © 2017 备案信息：陕ICP备***********</div>
</body>
</html>



