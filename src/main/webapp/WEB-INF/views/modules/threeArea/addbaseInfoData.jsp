<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>基本信息完善</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		}
	</script>
</head>
<body>
	<ul id="ulWork" class="nav nav-tabs">
		<li ><a href="${ctx}/threeSq/baseInfoData">三区申请参数维护</a></li>
		<li class="active"><a href="${ctx}/threeSq/addBaseInfoData">三区申请参数添加</a></li>
	</ul>
	 <form action="${ctx}/threeSq/insertBaseData" method="post" class="form-inline">
	 <input type="text" name="id" value="${threeAreaBaseData.id}"/>
		<table id="contentTable" class="table  table-bordered  ">
			<tbody>
				<tr>
				<td>年度:</td>
				<td>
				<input type="text" name="year" id="d243" value="${threeAreaBaseData.year}" onclick="WdatePicker({skin:'whyGreen',readOnly:true,dateFmt:'yyyy年'})" class="Wdate" required/>
				<span class="help-inline"><font color="red">*</font> </span>
				</td>
				</tr>
				<tr>
					<td>年度开始时间:</td>
					<td><input name="yearStartTime" placeholder="年度开始时间" id="d4311" class="Wdate" type="text" value="${threeAreaBaseData.yearStartTime}"  onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',isShowClear:false})" required/>
					<font color="red">*</font></span></td>
				</tr>
				<tr>
					<td>年度结束时间:</td>
					<td><input name="yearEndTime" placeholder="年度结束时间"  id="d4312" class="Wdate" type="text" value="${threeAreaBaseData.yearEndTime}" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',isShowClear:false,minDate:'#F{$dp.$D(\'d4311\')}'})" required/>
					<span class="help-inline"><font color="red">*</font>
					</span></td>
				</tr>
				<tr>
					<td>申请开始时间:</td>
					<td><input name="startTime" placeholder="开始时间" id="d4311" class="Wdate" type="text" value="${threeAreaBaseData.startTime}"  onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',isShowClear:false})" required/>
					<font color="red">*</font></span></td>
				</tr>
				<tr>
					<td>申请结束时间:</td>
					<td><input name="endTime" placeholder="结束时间"  id="d4312" class="Wdate" type="text" value="${threeAreaBaseData.endTime}" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',isShowClear:false,minDate:'#F{$dp.$D(\'d4311\')}'})" required/>
					<span class="help-inline"><font color="red">*</font>
					</span></td>
				</tr>
			</tbody>
		</table>
		<div class="form-actions">
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
		<input id="btnCancel" class="btn btn-default" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
</form>
</body>
</html>