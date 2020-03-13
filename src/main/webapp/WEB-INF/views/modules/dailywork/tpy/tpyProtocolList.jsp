<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<meta name="decorator" content="default"/>
</head>
<body>

<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dailywork/tpy/tpyProtocol/">我的特派员服务协议</a></li>
		<%-- <li class="active"><a href="${ctx}/dailywork/tpy/tpyProtocolEdit?id=${a1.id}" ${hidden}>修改</a></li> --%>
	</ul>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>特派员姓名</th>
				<th>备注信息</th>
				<th>操作</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${tpyProtocol}" var="a1">
				<tr>
					<td>${a1.user.name}</td>
					<td>${a1.remarks}</td>
					<td><a href="${ctx}/dailywork/tpy/tpyProtocolView?id=${a1.id}">查看</a>
					<a href="${ctx}/dailywork/tpy/tpyProtocolEdit?id=${a1.id}" ${hidden}>修改</a>
					<a href="${ctx}/dailywork/tpy/tpyProtocolDelete?id=${a1.id}" onclick="return confirmx('确认要删除该特派员服务协议吗？', this.href)">删除</a></td>
				</tr>
			
			</c:forEach>
		</tbody>
	</table>
	</body>
</html>