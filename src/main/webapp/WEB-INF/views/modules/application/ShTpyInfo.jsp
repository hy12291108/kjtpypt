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
		function selectTpy(){
		var selectName=$("#selectName").val();
		var major=$("#zy").val();		
						$.ajax({  
					        type : "post",  
					        /* contentType : "application/json",   */
					        url : "${ctx}/sqtpy/tpy/choosetpy", 
					        data : {"tpyid":selectName},
					        dataType : "json",  
					        contentType: "application/x-www-form-urlencoded; charset=utf-8", 
					        success : function(data) {
					          $("#tpyid").val(data.tpyid);
					          $("#tpyxm").val(data.tpyname);
					          $("#zc").val(data.zc);
					          $("#zy").val(data.zy);
					          $("#zytc").val(data.techspecial);
					          $("#gzdw").val(data.company);
					          $("#shouji").val(data.mobile);
					          alert("选择成功！");
					        },  
					        error : function() {  
					          alert("未查到该特派员,请重新选择！");  
					        }  
					      });  
				}		

		
	</script>
</head>
<body>	
<ul class="nav nav-tabs">
		<li><a href="${ctx}/sqtpy/tpy/Shlist">未审核列表</a></li>	
		<li><a href="${ctx}/sqtpy/tpy/yshlist">已审核列表</a></li>
		<li class="active"><a>审核自然人特派员</a></li>
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
				<td class="tit"><lable>选择特派员:</lable></td>
				<td>
					<form:select path="selectName" maxlength="50" items="${tpylistbymajor}" itemLabel="name" itemValue="id" style="width:175px" id ="selectName"  class="input-xlarge">			
				</form:select>				
				<input type="button" onclick="selectTpy()" value="确定"/>
				</td>
				<td class="tit"><lable>姓名:</lable></td>
				<td>
					<form:input path="tpyname" readonly="true" htmlEscape="false" class="required" id="tpyxm" maxlength="100" />
				</td>
				
			</tr>
			<tr>
				<td class="tit"><lable>工作单位:</lable></td>
				<td>
					<form:input path="company" readonly="true" htmlEscape="false" class="required" id="gzdw" maxlength="100"/>
				</td>
				<td class="tit"><lable>手机:</lable></td>
				<td>
					<form:input path="mobile" readonly="true" htmlEscape="false" class="required" id="shouji" maxlength="100"/>
				</td>
				
			</tr>
			<tr>
				<td class="tit"><lable>专业:</lable></td>
				<td>
					<form:input path="zy" readonly="true" id="zy" htmlEscape="false" class="required" maxlength="100"/>
				</td>
				<td class="tit"><lable>职称:</lable></td>
				<td>
					<form:input path="zc" readonly="true" id="zc" htmlEscape="false" class="required" maxlength="100"/>
				</td>
				
				
			</tr>
			
			<tr>
				<td class="tit"><lable>特长:</lable></td>
				<td colspan="3">
					<form:textarea path="techspecial" htmlEscape="false" rows="4" readonly="true" maxlength="100" style="width:900px" id="zytc"  class="required"/>
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
					<form:textarea path="zpryj" htmlEscape="false" rows="4" style="width:900px"  class="required" maxlength="1000"/>
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
		<form:input path="state" id="shzt" htmlEscape="false" readonly="true" maxlength="50" type="hidden" class="required"/>
		<div class="form-actions table-bordered-bt">
			<input id="btnSubmit" class="btn btn-primary" type="submit" name="action" value="审核通过"/>&nbsp;
			<input id="btnSubmit" class="btn btn-primary" type="submit" name="action" value="审核不通过"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>