<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		$("#zpr").focus();			
			$("#inputForm").validate({
			
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox"
			});
		});
		$('selectForm').submit(function() {
  			alert($(this).serialize());
 			 return false;
		});
	</script>
</head>
<body>	
<ul class="nav nav-tabs">
		<li><a href="${ctx}/sqtpy/tpy/Shlist">未审核列表</a></li>	
		<li><a href="${ctx}/sqtpy/tpy/yshlist">已审核列表</a></li>
		<li class="active"><a>审核法人特派员</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="sqtpy" action="${ctx}/sqtpy/tpy/saveshtpy" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="personFlag"/>
		<sys:message content="${message}"/>	
		<!--  <label><font color="red">需求单位信息:</font></label><br> -->
		<table id="contentTable" class="table-form">
		<tbody>
			<tr>
				<td colspan="4"><h4>服务对象信息</h4></td>
			</tr>
			<tr>
				<td class="tit"><lable>服务对象:</lable></td>
				<td>
					<form:input path="xqdwname" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
				<td class="tit"><lable>归属区域:</lable></td>
				<td>
					<form:input path="office.name" readonly="true" htmlEscape="false" maxlength="50" class="required"/>
				</td>
				
			</tr>
			<tr>
				<td class="tit"><lable>联系人:</lable></td>
				<td>
					<form:input path="corpcorName" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
				<td class="tit"><lable>电话:</lable></td>
				<td>
					<form:input path="xqdwphone" readonly="true" htmlEscape="false" maxlength="50" class="required"/>
				</td>		
			</tr>
			<tr>
				<td class="tit"><lable>需求信息:</lable></td>
				<td colspan="3">
					<form:textarea path="xqdwsqReason" htmlEscape="false" rows="4" readonly="true" maxlength="1000" style="width:900px"   class="required"/>
				</td>		
			</tr>
			<tr>
				<td colspan="4"><h4>特派员信息</h4></td>
			</tr>
			<tr>
				<td class="tit"><lable>特派员姓名:</lable></td>
				<td>
					<form:input path="tpyname" readonly="true" htmlEscape="false" class="required" id="tpyxm" maxlength="100" />
				</td>
				<td class="tit"><lable>所属区域</lable></td>
				<td>
					<form:input path="office.name" readonly="true" htmlEscape="false" class="required" id="tpyxm" maxlength="100" />
				</td>
			</tr>
			<tr>
				<td class="tit"><lable>法人姓名:</lable></td>
				<td>
					<form:input path="tpyinfo.corpLegRepName" readonly="true" htmlEscape="false" class="required" id="gzdw" maxlength="100"/>
				</td>
				<td class="tit"><lable>法人电话:</lable></td>
				<td>
					<form:input path="tpyinfo.mobile" readonly="true" htmlEscape="false" class="required" id="shouji" maxlength="100"/>
				</td>
				
			</tr>
			<tr>
				<td class="tit"><lable>联系人:</lable></td>
				<td>
					<form:input path="tpyinfo.corpCorName" readonly="true" id="zy" htmlEscape="false" class="required" maxlength="100"/>
				</td>
				<td class="tit"><lable>联系人电话:</lable></td>
				<td>
					<form:input path="tpyinfo.corpCorPhone" readonly="true" id="zc" htmlEscape="false" class="required" maxlength="100"/>
				</td>		
			</tr>
			<tr>
				<td class="tit"><lable>单位类型:</lable></td>
				<td>
					<form:input path="tpyinfo.corpType" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
				</td>
				<td class="tit"><lable></lable></td>
				<td>
					
				</td>		
			</tr>
			<tr>
				<td class="tit"><lable>营业范围:</lable></td>
				<td colspan="3">
					<form:textarea path="tpyinfo.corpScale" htmlEscape="false" rows="4" readonly="true" maxlength="1000" style="width:900px"   class="required"/>
				</td>		
			</tr>
			<tr>
				<td class="tit"><lable>科技优势与服务内容:</lable></td>
				<td colspan="3">
					<form:textarea path="tpyinfo.corpMajor" htmlEscape="false" rows="4" readonly="true" maxlength="1000" style="width:900px"   class="required"/>
				</td>		
			</tr>
			<tr>
				
				<td class="tit"><lable>服务开始时间:</lable></td>
				<td>
					<form:input path="starTime" readonly="true" htmlEscape="false" class="required" maxlength="100"/>
				</td>
				<td class="tit"><lable>服务结束时间:</lable></td>
				<td>
					<form:input path="endTime" readonly="true" htmlEscape="false" class="required" maxlength="100"/>
				</td>						
			</tr>
			<tr>
				<td colspan="4"><h4>审核信息</h4></td>
			</tr>
			
			<tr>
				<td class="tit"><lable>审核人:</lable></td>
				<td>
					<form:input path="zpr" readonly="true" htmlEscape="false" class="required" maxlength="100"/>
				</td>
				<td class="tit"><lable>审核时间:</lable></td>
				<td>
					<form:input path="zpTime" readonly="true" htmlEscape="false" class="required" maxlength="100"/>
				</td>			
			</tr>
			<tr>
				<td class="tit"><lable>审核意见:</lable></td>
				<td colspan="3">
					<form:textarea path="zpryj" htmlEscape="false" rows="4" maxlength="255" style="width:900px"  class="required"/>
				</td>		
			</tr>
		<tbody>
		</table>
		<div hidden class="control-group">
			<label class="control-label">需求单位联系人:</label>
			<div class="controls">
				<form:input path="tpyid" id="tpyid" readonly="true" htmlEscape="false" maxlength="100" />
			</div>
		</div>
		<!--  <div class="control-group">
			<label class="control-label">帮扶对象名称:</label>
			<div class="controls">
				<form:input path="xqdwname" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">帮扶对象所在区域:</label>
			<div class="controls">
				<form:input path="office.name" readonly="true" htmlEscape="false" maxlength="50" class="required"/>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">需求单位联系人:</label>
			<div class="controls">
				<form:input path="corpcorName" readonly="true" htmlEscape="false" maxlength="100" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">需求单位联系人电话:</label>
			<div class="controls">
				<form:input path="xqdwphone" readonly="true" htmlEscape="false" maxlength="100"/><br>
			</div>		
		</div>
		<div class="control-group">
			<label class="control-label">需求单位的申请原因:</label>
			<div class="controls">
				<form:textarea  path="xqdwsqReason" readonly="true" htmlEscape="false" rows="4" maxlength="255" class="required"/>
			</div>
		</div>
				
		<label><font color="red" >特派员信息:</font></label><br>
		<div class="control-group">
			<label class="control-label">选择特派员:</label>
			<div class="controls">
				<form:select path="selectName" maxlength="50" id ="selectName"  class="input-xlarge">
					<form:options items="${tpylistbymajor}" />				
				</form:select>				
				<input type="button" onclick="selectTpy()" value="选择"/>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">特派员姓名:</label>
			<div class="controls">			
				<form:input path="tpyname" readonly="true" htmlEscape="false" class="required" id="tpyxm" maxlength="100" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">职称:</label>
			<div class="controls">
				<form:input path="zc" readonly="true" htmlEscape="false" class="required" id="zhicheng" maxlength="100"/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">专业:</label>
			<div class="controls">
				<form:input path="zy" readonly="true" id="zy" htmlEscape="false" class="required" maxlength="100"/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">专业特长:</label>
			<div class="controls">
				<form:input path="techspecial" readonly="true" htmlEscape="false" class="required" id="zytc" maxlength="100"/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">工作单位:</label>
			<div class="controls">
				<form:input path="company" readonly="true" htmlEscape="false" class="required" id="gzdw" maxlength="100"/>
			</div>
		</div>		
		<div class="control-group">
			<label class="control-label">手机:</label>
			<div class="controls">
				<form:input path="mobile" readonly="true" htmlEscape="false" class="required" id="shouji" maxlength="100"/>
			</div>
		</div>					
		<div class="control-group">
			<label class="control-label">服务开始时间:</label>
			<div class="controls">
				<form:input path="starTime" readonly="true" htmlEscape="false" class="required" maxlength="100"/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">服务结束时间:</label>
			<div class="controls">
				<form:input path="endTime" readonly="true" htmlEscape="false" class="required" maxlength="100"/>
			</div>			
		</div>	
		
		<label><font color="red">审核人信息:</font></label><br>	
		<div class="control-group">
			<label class="control-label">审核状态:</label>
			<div class="controls">
				<form:input path="state" id="shzt" htmlEscape="false" readonly="true" maxlength="50" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审核人:</label>
			<div class="controls">
				<form:input path="zpr"  htmlEscape="false" readonly="true"  maxlength="100" class="required"/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">审核时间:</label>
			<div class="controls">
				<form:input path="zpTime" readonly="true" htmlEscape="false" class="required" maxlength="100" />
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">审核人意见:</label>
			<div class="controls">
				<form:textarea path="zpryj" htmlEscape="false" rows="4" maxlength="255" class="required"/>
			</div>
		</div>	-->
		<form:input path="state" id="shzt" htmlEscape="false" readonly="true" maxlength="50" type="hidden" class="required"/>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" name="action" value="审核通过"/>&nbsp;
			<input id="btnSubmit" class="btn btn-primary" type="submit" name="action" value="审核不通过"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>