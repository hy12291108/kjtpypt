<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>贫困村服务协议管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
		<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dailywork/village/vilProtocol/xqdwlist">待上传服务协议列表</a></li>
		<li><a href="${ctx}/dailywork/village/vilProtocol/xqdwlist2">已上传服务协议列表</a></li>
		</ul>
	<c:choose>
		<c:when test="${tpye=='village'}">
				<h2>贫困村：</h2>
		</c:when>
		<c:otherwise>
				<h2>特派员：</h2>
		</c:otherwise>
	</c:choose>	
	<sys:message content="${message}"/>
    	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>特派员</th>		
				<th>特派员电话</th>
				<th>需求单位</th>		
				<th>联系人</th>
				<th>联系人电话</th>
				<th>服务时间</th>			
				<th>指派人</th>
				<th>审核时间</th>
				<th>协议是否上传</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sqtpy">
			<tr>
				<td>
					${sqtpy.tpyname}		
				</td>
				<td>
					${sqtpy.mobile}
				</td>
				<td>
					${sqtpy.xqdwname}
					
				</td>
				<td>
					${sqtpy.corpcorName}
				<%-- 	<a href="${ctx}/dailywork/village/vilProtocol/add?recid=${sqtpy.id}&type=tpy&orgid=${sqtpy.xqdwid}&tpyid=${sqtpy.tpyid}">添加</a> --%>
				</td>
				<td>
					${sqtpy.xqdwphone}
				</td>
				<td>
					${sqtpy.starTime}至${sqtpy.endTime}
				</td>
				<td>
					${sqtpy.zpr}
				</td>
				<td>
					${sqtpy.zpTime}
				</td>
				<td>
					<font color=red >未上传协议</font> 
				</td>
				<td>
				<a href="${ctx}/dailywork/village/vilProtocol/addxqdw?recid=${sqtpy.id}&type=${tpye}&vilid=${sqtpy.xqdwid}&tpyid=${sqtpy.tpyid}">添加</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
  </body>
</html>
