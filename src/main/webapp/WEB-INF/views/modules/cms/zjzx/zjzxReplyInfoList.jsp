<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文章管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/cms/article/zjzxListPass">专家咨询列表</a></li>
		<li class="active"><a href="/kjtpypt/f/commentList?id=${article.id}">专家咨询查看</a></li>
	</ul><br/>
	<sys:message content="${message}"/>
		<table class="table  table-bordered  ">
		<tr>
		<td style="width:20%;text-align: center;"><label class="control-label" >栏目:</label></td>
		<td style="width:80%">${article.category.name}</td>
		
		</tr>
		<tr>
		
		<td style="text-align: center;"><label class="control-label" >标题:</label></td>
		<td><textarea rows="2" cols="3" maxlength="200" style="width:80%" readonly="readonly">${article.title}</textarea></td>
		</tr>
		<tr>
		<td style="text-align: center;">内容:</td>
		<td><textarea rows="3" cols="3" maxlength="200" style="width:80%" readonly="readonly">${article.articleData.content}</textarea></td>
		</tr>
		<tr>
		<td style="text-align: center;">上传图片:</td>
		<td> 
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
		<td style="text-align: center;">已上传附件:</td>
		<td >
			<c:forEach items="${article.sysAttachmentList}" var="a1">
				<a href="${ctx}/dailywork/village/vilProtocol/showImage?filename=${a1.attName}&fileType=${a1.attFolder}"  target="_blank" id="delAction">${a1.attOriginname}</a>
			</c:forEach>
		</td>
		</tr>
		</table>
		
		<div class="evaluation">
		<table class="table  table-bordered  ">
				<tr>
						<td colspan="4" >
						<h2 align="center">回复信息</h2>
						</td>
				</tr>
				<c:forEach items="${commentList}" var="comment">
						<tr>
						<td>
						<h5>回复人：${comment.name}</h5>
						</td>
						<td>
						<h5>回复时间：<fmt:formatDate value="${comment.createDate}" pattern="yyyy-MM-dd HH:mm"/></h5>
						</td>
						</tr>
						<tr>
						<td colspan="2">
						<p>${comment.content}</p>
						</td>
						</tr>
				</c:forEach>
		</table>
			</div>
</body>
</html>