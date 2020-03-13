<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>三区人才</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/threeSq/findResult">申请三区人才历史记录</a></li>
	</ul>
	<c:if test="${!empty message}">
		<div id ="div" class="alert alert-success alert-dismissible" role="alert">
			 <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			 ${message}
		</div>
	</c:if>
	 <div>
		<table class="table table-bordered">
						<thead>
							<tr>
								<th>年度</th>
								<th>管辖部门</th>
								<th>姓名</th>
								<th>性别</th>
								<th>Email</th>
								<th>手机号码</th>
								<th>学历</th>
								<th>专业</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="tbody-job-list">
						<c:forEach items="${threeArea}" var="threeArea">
							<tr class="tr-length">
								<td>${threeArea.year}</td>
								<td>${threeArea.zoneName}</td>
								<td>${threeArea.name}</td>
								<td>${threeArea.sex}</td>
								<td>${threeArea.email}</td>
								<td>${threeArea.mobile}</td>
								<td>${threeArea.tpyQulification}</td>
								<td>${threeArea.tpyMajor}</td>
								<c:choose>
										<c:when test="${threeArea.status=='1'}">
											<td><a>审核中</a></td>
										</c:when>
										<c:when test="${threeArea.status=='3'}">
											<td><a>市级未通过</a></td>
										</c:when>
										<c:when test="${threeArea.status=='5'}">
											<td><a>省级未通过</a></td>
										</c:when>
										<c:when test="${threeArea.status=='4'}">
											<td><a>审核通过</a></td>
										</c:when>
										<c:otherwise> 
										    <td><a>审核中</a></td>                       
										</c:otherwise>
								</c:choose>
								<td>
								<a href="${ctx}/threeSq/threeAreaResultInfo?id=${threeArea.id}">查看</a>
								<c:choose>
										
										<c:when test="${threeArea.status=='3'}">
											<a href="${ctx}/threeSq/threeAreaResultInfoupdate?id=${threeArea.id}">修改</a>
										</c:when>
										<c:when test="${threeArea.status=='5'}">
											<a href="${ctx}/threeSq/threeAreaResultInfoupdate?id=${threeArea.id}">修改</a>
										</c:when>
										<c:otherwise> 
										    <a></a>                      
										</c:otherwise>
								</c:choose>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
	</div>
</body>
</html>