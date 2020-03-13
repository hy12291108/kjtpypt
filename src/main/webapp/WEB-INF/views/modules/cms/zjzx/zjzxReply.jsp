<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文章管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/cms/article/zjzxListPass">专家咨询列表</a></li>
		<li class="active"><a href="<c:url value='${fns:getAdminPath()}/cms/article/zjzxReply?id=${article.id}&category.id=${article.category.id}'><c:param name='category.name' value='${article.category.name}'/></c:url>">专家回复</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="article" action="/kjtpypt/f/commentSave" method="post" class="form-horizontal"  enctype="multipart/form-data">
		<form:hidden path="id"/>
		<form:hidden path="category.id"/>
		<input type="hidden" name="id" value="${article.id}">
		<input type="hidden" name="title" value="${article.title}">
		<input type="hidden" name="category.id" value="${article.category.id}">
		<input type="hidden" name="name" value="${fns:getUser().name}">
		<sys:message content="${message}"/>
		<table class="table  table-bordered  ">
		<tr>
		<td style="width:20%"><label class="control-label">栏目:</label></td>
		<td style="width:80%">
		${article.category.name}		
		</td>
		</tr>
		<tr>		
		<td><label class="control-label">标题:</label></td>
		<td >
		<textarea rows="3" cols="3" maxlength="200"  style="width:80%" readonly="readonly">${article.title}</textarea>		
		</td>
		</tr>
		<tr>
		<td><label class="control-label">内容:</label></td>
		<td>
		<textarea rows="3" cols="3" maxlength="200" style="width:80%" readonly="readonly">${article.articleData.content}</textarea>		 
		</td>
		</tr>
		<tr>
		<td><label class="control-label">图片:</label></td>
		<td >
		<c:if test="${imagePathList==null}">
		<img alt="暂无图片" src="" /> <br/>  
		</c:if>
		<c:if test="${!empty imagePathList}">
		<c:forEach items="${imagePathList}" var="imagename">  
        <img src="/zjzxImage/${imagename}" style="width:300px;height:300px"/> 
        </c:forEach>  
		</c:if>
		</td>
		</tr>
		<tr>
		<td><label class="control-label">已上传附件:</label></td>
		<td >
		<c:forEach items="${article.sysAttachmentList}" var="a1">
		<a href="${ctx}/dailywork/village/vilProtocol/showImage?filename=${a1.attName}&fileType=${a1.attFolder}"  target="_blank" id="delAction">${a1.attOriginname}</a>
		</c:forEach>
		</td>
		</tr>
		<tr>
		<td>
		<label class="control-label">回复:</label>
		</td>
		<td colspan="3"><textarea rows="3" cols="3" maxlength="200" name="content" style="width:80%"></textarea></td>
		</tr>
		</table>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>