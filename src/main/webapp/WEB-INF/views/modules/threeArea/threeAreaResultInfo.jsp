<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>三区人才申请</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/threeSq/findResult">申请三区人才历史记录</a></li>
		<li class="active"><a href="${ctx}/threeSq/threeAreaResultInfo?id=${threeArea.id}">审核结果信息</a></li>
	</ul>
	<c:if test="${!empty message}">
		<div id ="div" class="alert alert-success alert-dismissible" role="alert">
			 <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			 ${message}
		</div>
	</c:if>
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
			<%-- <tr>
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
			</tr> --%>
			<%-- <tr>
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
			</tr> --%>
			<tr>
				<td>专业特长:</td>
				<td colspan="3">
				<textarea name="tpySpecial" style="width:700px;" class="form-control"  rows="1" readonly>${threeArea.tpySpecial}</textarea>
				</td>
				<td>年度:</td>
				<td >
				<input type="text" name="year" class="form-control" value="${threeArea.year}" readonly/>
				</td>
			</tr>
			<tr>
				<td>个人介绍:</td>
				<th colspan="5">
				<textarea name="personalInfo" style="width:700px;" class="form-control"  rows="5" readonly>${threeArea.personalInfo}</textarea>
				</th>
			</tr>
		</tbody>
	 </table>
	</div>
	<c:if test="${threeArea.status!='1'}">
	<c:if test="${!empty threeArea.checkPersonC}">
	<div>
	<table  class="table  table-bordered  ">
				<tr>
						<td><label>市审核人:</label></td>
						<td>
							${threeArea.checkPersonC}<%-- <input name="checkPersonC" type="text" value="${threeArea.checkPersonC}" readonly> --%>
						</td>
						<td>市审核时间:</td>
						<td>
							${threeArea.checkTimeC} <%-- <input name="checkTimeC" type="text" value="${threeArea.checkTimeC}" readonly> --%>
						</td>
						<td>市审核结果:</td>
						<td>
						<c:if test="${threeArea.status==2}">
						通过
						</c:if>
						<c:if test="${threeArea.status==3}">
						不通过
						</c:if>
						<c:if test="${threeArea.status==4}">
						通过
						</c:if>
						<c:if test="${threeArea.status==5}">
						通过
						</c:if>
						</td>
				</tr>
				<tr>
						<td>
						市审核意见:</td>
						<td  colspan="5">
						${threeArea.checkAdviceC}
							<%-- <textarea name="checkAdviceC" rows="3"  placeholder="审核意见" style="width:1024px" readonly>${threeArea.checkAdviceC}</textarea> --%>
						</td>
				</tr>
	</table>
	</div>
	</c:if>
	<c:if test="${!empty threeArea.checkPersonP}">
	<div>
	<table  class="table  table-bordered  ">
				<tr>
						<td>省审核人:</td>
						<td>
						${threeArea.checkPersonP}	<%-- <input name="checkPersonP" type="text" value="${threeArea.checkPersonP}" readonly> --%>
						</td>
						<td>省审核时间:</td>
						<td>
						${threeArea.checkTimeP}	<%-- <input name="checkTimeP" type="text" value="${threeArea.checkTimeP}" readonly> --%>
						</td>
						<td>省审核结果:</td>
						<td>
						<c:if test="${threeArea.status==4}">
						通过
						</c:if>
						<c:if test="${threeArea.status==5}">
						不通过
						</c:if></td>
				</tr>
				<tr>
						<td>省审核意见:</td>
						<td  colspan="5">
						${threeArea.checkAdviceP}
							<%-- <textarea name="checkAdviceP" rows="3"  style="width:1024px" readonly>${threeArea.checkAdviceP}</textarea> --%>
						</td>
				</tr>
			</table>
	</div>
	</c:if>
	</c:if>
	<div class="form-actions">
		<!--<input id="btnSubmit" class="btn btn-primary" type="submit" value="保存" />&nbsp;-->
		<input id="btnCancel"  type="button" class="btn btn-default" value="返回" onclick="history.go(-1)"/>
	</div>
</body>
</html>