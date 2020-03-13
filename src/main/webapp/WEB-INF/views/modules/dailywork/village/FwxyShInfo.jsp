<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>贫困村服务协议管理</title>
	<meta name="decorator" content="default"/>
	
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
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
	<style type="text/css">
	.delStyle{ text-decoration: line-through; font-weight:100;}
	</style>
</head>
<body>
<ul class="nav nav-tabs">
		<li><a href="${ctx}/dailywork/village/vilProtocol/fwxylist">待审核服务协议列表</a></li>
		<li><a href="${ctx}/dailywork/village/vilProtocol/fwxyyshlist">已审核服务协议列表</a></li>
		<li class="active"><a>服务协议审核</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="vilProtocol" action="${ctx}/dailywork/village/vilProtocol/fwxysave" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table id="contentTable" class="table-form">
		<tbody>
			<tr>
				<td colspan="4"><h4>服务对象信息</h4></td>
			</tr>
			<tr>
				<td class="tit"><lable>服务对象:</lable></td>
				<td>
					<form:input  readonly="true"  path="vilName" htmlEscape="false" maxlength="64" />
				</td>
				<td class="tit"><lable>类型:</lable></td>
				<td>
					需求单位
				</td>
			</tr>
			<tr>
				<td class="tit"><lable>联系人:</lable></td>
				<td>
					<form:input  readonly="true"  path="vilContact" htmlEscape="false" maxlength="64" />
				</td>
				<td class="tit"><lable>联系电话:</lable></td>
				<td>
					<form:input  readonly="true"  path="vilContactphone" htmlEscape="false" maxlength="64" />
				</td>
				
			</tr>
			<tr>
				<td colspan="4"><h4>特派员信息</h4></td>
			</tr>
			<tr>
				<td class="tit"><lable>特派员:</lable></td>
				<td>
					<form:input  readonly="true"  path="vilTpyname" htmlEscape="false" maxlength="64" />
				</td>
				<td class="tit"><lable>联系电话:</lable></td>
				<td>
					<form:input  readonly="true"  path="vilTpyphone" htmlEscape="false" maxlength="64" />
				</td>
				
			</tr>
			<tr>
				<td class="tit"><lable>需求信息:</lable></td>
				<td colspan="5">
					<form:textarea readonly="true" path="vilNeeds" htmlEscape="false" rows="4" style="width:1070px" maxlength="1000" class="required"/>
				</td>		
			</tr>
			<tr>
				<td class="tit"><lable>服务协议:</lable></td>
				<td colspan="5">
					<br>
					<c:forEach items="${vilProtocol.sysAttachmentList}" var="a1">
					<a href="${ctx}/dailywork/village/vilProtocol/showImage?filename=${a1.attName}&fileType=${a1.attFolder}"  target="_blank" id="delAction">${a1.attOriginname}</a></br>
					</c:forEach>
					<br>
				</td>			
			</tr>
			<tr>
				<td colspan="4"><h4>审核信息</h4></td>
			</tr>
			<tr>
				<td class="tit"><lable>审核人:</lable></td>
				<td>
					<form:input  readonly="true"  path="fwxyzpr" htmlEscape="false" maxlength="64" />
				</td>
				<td class="tit"><lable>审核时间:</lable></td>
				<td>
					<form:input  readonly="true"  path="fwxyzpTime" htmlEscape="false" maxlength="64" />
				</td>
			</tr>
			<tr>
				<td class="tit"><lable>是否通过:</lable></td>
				<td colspan="5">
					<br>
					<form:radiobutton path="fwxystateflag" checked="true" value="1"/>通过  
               		<form:radiobutton path="fwxystateflag" value="2"/>不通过  
               		<br>
               		<br>
				</td>	
			</tr>
			<tr>
				<td class="tit"><lable>审核意见:</lable></td>
				<td colspan="5">
					<form:textarea path="fwxyopinion" htmlEscape="false" rows="4" style="width:1070px" maxlength="1000" class="required"/>
				</td>
				
				
			</tr>
		<tbody>
		</table>
		<div hidden class="control-group">
			<label class="control-label">创建者角色：</label>
			<div class="controls">
				<form:input readonly="true" path="vilCreateRole" htmlEscape="false" maxlength="64" />
			</div>
		</div> 	
		<!-- <div class="control-group">
			<label class="control-label">服务对象名称：</label>
			<div class="controls">
				<form:input  readonly="true"  path="vilName" htmlEscape="false" maxlength="64" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">服务对象联系人：</label>
			<div class="controls">
				<form:input  readonly="true"  path="vilContact" htmlEscape="false" maxlength="64" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">服务对象联系电话：</label>
			<div class="controls">
				<form:input  readonly="true"  path="vilContactphone" htmlEscape="false" maxlength="64" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">特派员名称：</label>
			<div class="controls">
				<form:input  readonly="true"  path="vilTpyname" htmlEscape="false" maxlength="64" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">特派员联系电话：</label>
			<div class="controls">
				<form:input  readonly="true"  path="vilTpyphone" htmlEscape="false" maxlength="64" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">服务对象需求：</label>
			<div class="controls">
				<form:textarea readonly="true" path="vilNeeds" htmlEscape="false" rows="4" maxlength="255" class="required"/>
			</div>
		</div> -->
		<div hidden class="control-group">
			<label class="control-label">特派员id：</label>
			<div class="controls">
				<form:input path="vilTpyid" htmlEscape="false" maxlength="64" />
		</div>
		<div hidden class="control-group">
			<label class="control-label">村id：</label>
			<div class="controls">
				<form:input path="vilId" htmlEscape="false" maxlength="64" />
		</div>
		</div>
		</div>
	 	<div hidden class="control-group">
			<label class="control-label">帮扶关系id：</label>
			<div class="controls">
				<form:input path="helpRelationid" htmlEscape="false" maxlength="64" />
			</div>
		</div> 
		<div hidden class="control-group">
			<label class="control-label">订单id：</label>
			<div class="controls">
				<form:input path="ddId" htmlEscape="false" maxlength="64" />
			</div>
		</div>
		<!-- <div class="control-group">
			<label class="control-label">特派员附件：</label>
			<div class="controls">
				<c:forEach items="${vilProtocol.sysAttachmentList}" var="a1">
				<a href="${ctx}/dailywork/village/vilProtocol/showImage?filename=${a1.attName}&fileType=${a1.attFolder}"  target="_blank" id="delAction">${a1.attOriginname}</a></br>
				</c:forEach>
				  <td>  
               <form:radiobutton path="fwxystateflag" checked="true" value="1"/>通过  
               <form:radiobutton path="fwxystateflag" value="2"/>不通过  
            </td>  
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">特派员服务协议审核意见：</label>
			<div class="controls">
				<form:textarea path="fwxyopinion" htmlEscape="false" rows="4" maxlength="255" class="required"/>
			</div>
		</div> -->
		<!--
		<div class="control-group">
			<label class="control-label">贫困村附件：</label>
			<div class="controls">
				<c:forEach items="${vilProtocol.sysAttachmentList2}" var="a2">
				<a href="${ctx}/dailywork/village/vilProtocol/showImage?filename=${a2.attName}&fileType=${a2.attFolder}"  target="_blank" id="delAction">${a2.attOriginname}</a></br>
				</c:forEach>
				<form:radiobutton path="fwxystateflag1" checked="true" value="1"/>通过  
                <form:radiobutton path="fwxystateflag1" value="2"/>不通过 	
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">贫困村服务协议审核意见：</label>
			<div class="controls">
				<form:textarea path="fwxyopinion1" htmlEscape="false" rows="4" maxlength="255" class="required"/>
			</div>
		</div>
		-->	
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" name="action" value="提交"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>