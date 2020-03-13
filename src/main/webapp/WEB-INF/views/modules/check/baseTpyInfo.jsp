<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>基本信息完善</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body>
	<ul id="ulWork" class="nav nav-tabs">
		<li class="active"><a href="${ctx}/UserSh/baseInfo">基本信息完善</a></li>
	</ul>
	 <form action="${ctx}/UserSh/InfoSave" method="post" class="form-inline">
		<table id="contentTable" class="table  table-bordered  ">
			<tbody>
				<tr>
					<td>单位名称:</td>
					<td><input type="text" value="${user.name}"
						class="form-control" disabled> <span
						class="help-inline"><font color="red">*</font> </span></td>
					<td>单位类型:</td>
					<td><input type="text" value="${fns:getDictLabels(user.corpType,'corp_type',user.corpType)}"
						class="form-control" disabled> <span class="help-inline"><font
							color="red">*</font> </span></td>
					<td>Email:</td>
					<td><input type="text" value="${user.email}"
						class="form-control" disabled> <span class="help-inline"><font
							color="red">*</font> </span></td>
				</tr>
				<input type="hidden" name="id" value="${tpyInfo.id}" id="tpyInfoId"/>
				<tr>
					<td>拟分配的服务地点:</td>
					<th colspan="5"><textarea name="tpyServiceSite" 
							style="width:700px;" class="form-control" rows="2" required>${tpyInfo.tpyServiceSite}</textarea><span
						class="help-inline"><font color="red">*</font></span></th>
				</tr>
				<tr>
					<td>拟开展的服务内容:</td>
					<th colspan="5"><textarea name="tpyServiceContent"
							style="width:700px;" class="form-control" rows="3" required>${tpyInfo.tpyServiceContent}</textarea> <span
						class="help-inline"><font color="red">*</font> </span></th>
				</tr>
				<tr>
					<td>法人科技特派员技术力量与优势:</td>
					<th colspan="5"><textarea name="tpySD"
							style="width:700px;" class="form-control" rows="5" required>${tpyInfo.tpySD}</textarea> <span
						class="help-inline"><font color="red">*</font></span></th>
				</tr>
				<tr>
					<td>科技服务与创业情况:</td>
					<th colspan="5"><textarea name="tpySW"
							style="width:700px;" class="form-control" rows="5" required>${tpyInfo.tpySW}</textarea> <span
						class="help-inline"><font color="red">*</font></span></th>
				</tr>
			</tbody>
		</table>
		<!-- <h4 id="h5">工作经历</h4>
		<table class="table table-bordered margin-top-0 emi-table" id="experienceTable">
			<tr>
				<td><button type="button"
						class="btn btn-success btn-xs btn-add-job">新增</button></td>
				<td>
			<table class="table table-bordered">
						<thead>
							<tr>
								<th>单位名称</th>
								<th>入职时间</th>
								<th>离职时间</th>
								<th>工作內容</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="tbody-job-list">
							<tr class="tr-length">
								<td><input type="text" name="tpyCorpName"
									placeholder="单位名称" required /><span class="help-inline"><font
										color="red">*</font></span></td>
								<td><input type="text" name="tpyWorkDate"
									placeholder="入职时间" id="d4311" class="Wdate" type="text"
									required
									onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',isShowClear:false,maxDate:'#F{$dp.$D(\'d4312\')||\'%y-%M-%d\'}'})" /><span
									class="help-inline"><font color="red">*</font></span></td>
								<td><input type="text" name="tpyLeaveDate"
									placeholder="离职时间" id="d4312" class="Wdate" type="text"
									required
									onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',isShowClear:false,minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'%y-%M-%d'})" /><span
									class="help-inline"><font color="red">*</font></span></td>
								<td><input type="text" name="tpyWork" placeholder="岗位"
									required /><span class="help-inline"><font color="red">*</font></span></td>
								<td><button type="button" class="btn btn-default btn-xs"
										onclick="removeJob(this)">删除</button></td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</table> -->
	<div class="form-actions">
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" />
		<input id="btnCancel" class="btn btn-default" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
</form>
</body>
</html>