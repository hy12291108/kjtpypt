<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>三区人才申请</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
		function  onSubmit(){
		//alert("111");
			 if($("#year").val()==""||$("#year").val==null){
				 return false;
			 }
			 if($("#personalInfo").val()==""||$("#personalInfo").val==null){
			     alert("个人介绍不能为空！");
			     return false;
			 }
			 if($("#tpySpecial").val()==""||$("#tpySpecial").val==null){
			     alert("专业特长不能为空！");
			     return false;
			 }
	// alert("222");
			$("#tpyThreeAreaupdate").attr("action","${ctx}/threeSq/tpyThreeAreaupdate");
			$("#tpyThreeAreaupdate").submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/threeSq/findResult">申请三区人才历史记录</a></li>
		<li class="active"><a href="${ctx}/threeSq/threeAreaResultInfoupdate?id=${threeArea.id}">信息修改</a></li>
	</ul>
	<c:if test="${!empty message}">
		<div id ="div" class="alert alert-success alert-dismissible" role="alert">
			 <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			 ${message}
		</div>
	</c:if>
	<form id="tpyThreeAreaupdate" method="post" class="form-inline">
	<input type="hidden" name="id" value="${threeArea.id}">
	 <div>
	 <table id="contentTable" class="table  table-bordered  ">
		<tbody>
			<tr>
				<td>姓名:</td>
				<td>
            	<input type="text" name="name" value="${threeArea.name}" class="form-control"  readonly>
				</td>
				<td>性别:</td>
				<td>
				<input type="text" name="sex" value="${threeArea.sex}" class="form-control"  readonly>
				</td>
				<td>出生年月:</td>
				<td>
				<input type="text" name="tpyBirthDate" value="${threeArea.tpyBirthDate}" class="form-control"  readonly>
				</td>
			</tr>
			<tr>
				<td>民族:</td>
				<td>
				  <input type="text" name="tpyNation" class="form-control" value="${threeArea.tpyNation}" readonly/>
				</td>
				<td>E-mail:</td>
				<td>
				<input type="text" name="email" value="${threeArea.email}" class="form-control" readonly>
				</td>
				<td>手机号码:</td>
				<td>
				<input type="text" name="mobile" value="${threeArea.mobile}" class="form-control" readonly>
				</td>
			</tr> 
			<tr>
				<td>专业:</td>
				<td>
				 <input type="text" name="tpyMajor"  class="form-control" value="${threeArea.tpyMajor}" readonly />
				</td>
				<td>学历:</td>
				<td>
				 <input type="text" name="tpyQulification" class="form-control" value="${threeArea.tpyQulification}" readonly />
				</td>
				<td>政治面貌:</td>
				<td>
				<input type="text" name="tpyPoliticalStatus" class="form-control" value="${threeArea.tpyPoliticalStatus}" readonly/>
				</td>
			</tr>
			<tr>
				<td>专业特长:</td>
				<td colspan="3">
				<textarea name="tpySpecial" style="width:700px;" class="form-control"  rows="5" >${threeArea.tpySpecial}</textarea>
				</td>
				<td>年度:</td>
				<td >
				<input type="text" name="year" class="form-control" value="${threeArea.year}" readonly/>
				</td>
			</tr>
			<tr>
				<td>个人介绍:</td>
				<th colspan="5">
				<textarea name="personalInfo" style="width:700px;" class="form-control"  rows="5" >${threeArea.personalInfo}</textarea>
				</th>
			</tr>
		</tbody>
	 </table>
	</div>
	
	
	<div class="form-actions">
	    <input id="btnSubmit" class="btn btn-primary" type="submit" value="提交" onclick="onSubmit()"/>	
		<input id="btnCancel"  type="button" class="btn btn-default" value="返回" onclick="history.go(-1)"/>
	</div>
	</form>
</body>
</html>