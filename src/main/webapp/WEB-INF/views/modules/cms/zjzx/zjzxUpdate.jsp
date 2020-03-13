<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文章管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            if($("#link").val()){
                $('#linkBody').show();
                $('#url').attr("checked", true);
            }
			$("#title").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
                    if ($("#categoryId").val()==""){
                        $("#categoryName").focus();
                        top.$.jBox.tip('请选择归属栏目','warning');
                    }else if (CKEDITOR.instances.content.getData()=="" && $("#link").val().trim()==""){
                        top.$.jBox.tip('请填写正文','warning');
                    }else{
                        loading('正在提交，请稍等...');
                        form.submit();
                    }
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/cms/article/zjzxList">专家咨询列表</a></li>
		<li class="active"><a href="<c:url value='${fns:getAdminPath()}/cms/article/zjzxUpdate?id=${article.id}&category.id=${article.category.id}'><c:param name='category.name' value='${article.category.name}'/></c:url>">专家咨询审核</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="article" action="${ctx}/cms/article/savezjzxSh" method="post" class="form-horizontal"  enctype="multipart/form-data">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">栏目:</label>
			<div class="controls">
				<form:input path="category.name" readonly="true" htmlEscape="false" maxlength="200" class="input-xlarge"/>
				<form:hidden path="category.id"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标题:</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-xlarge  " readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容:</label>
			<div class="controls">
				<form:textarea id="content" htmlEscape="true" path="articleData.content" rows="4" maxlength="200"  style="width:800px" class="input-xxlarge" readonly="true"/>
			</div>
		</div>
		 <div class="control-group">
			<label class="control-label">上传图片:</label>
			<div class="controls">
            <c:if test="${imagePathList==null}">
				<img alt="暂无图片" src="" /> <br/>  
				</c:if>
				<c:if test="${!empty imagePathList}">
				<c:forEach items="${imagePathList}" var="imagename">  
                <img src="/zjzxImage/${imagename}" style="width:300px;height:300px"/> 
        		</c:forEach>  
				</c:if>
			</div>
		</div> 
		<div class="control-group">
			<label class="control-label">已上传附件:</label>
			<div class="controls">
				<c:forEach items="${article.sysAttachmentList}" var="a1">
					<a href="${ctx}/dailywork/village/vilProtocol/showImage?filename=${a1.attName}&fileType=${a1.attFolder}"  target="_blank" id="delAction">${a1.attOriginname}</a>
				</c:forEach>
			</div>
		</div>
		<input type="hidden" name="delFlag" value="0">
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="通过"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>