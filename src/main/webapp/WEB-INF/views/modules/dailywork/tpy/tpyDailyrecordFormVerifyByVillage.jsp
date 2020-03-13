<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
  <head>
	<title>特派员工作日志审批管理</title>
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
  </head>

<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/dailywork/tpy/tpyDailyrecordVerify">工作日志未审批列表</a></li>
		<li><a href="${ctx}/dailywork/tpy/tpyDailyrecordVerify/YshList">工作日志已审批列表</a></li>
		<li class="active">
			<a href="${ctx}/dailywork/tpy/tpyDailyrecordVerify/form?id=${tpyDailyrecord.id}">贫困村日志信息审批</a>
		</li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="tpyDailyrecord"
		action="${ctx}/dailywork/tpy/tpyDailyrecordVerify/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<table class="table-form">	
				<tr>
				<td colspan="5"><h4>日志信息</h4></td>
				</tr>			
				<tr>
					<td class="tit">填报人</td>
					<td colspan="2"><form:input path="recWriter" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>
					<td class="tit">特派员类型</td>
					<td colspan="2"><form:input path="tpytype" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>					
				</tr>
				<tr>
					<td class="tit">年度</td>
					<td colspan="2"><form:input path="year" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>
					<td class="tit">服务对象类型</td>
					<td colspan="2"><form:input path="recHelpobjtype" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>					
				</tr>
				<tr>
					<td class="tit">填报贫困村</td>
					<td colspan="2">
					<form:input path="recHelpObj" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px"/>					
					</td>
					<td class="tit">填报特派团</td>
					<td colspan="2"><form:input path="TeamName" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>										
				</tr>
				<tr>
					<td class="tit">团长/团员</td>
					<td colspan="2">
					<form:input path="teamMemberType" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px"/>
					</td>					
					<td class="tit">日志类型</td>			
					<td colspan="2">
					<form:input path="dailyRecordType" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px"/>
					</td>
				</tr>
				<tr>		
					<td class="tit">服务开始时间</td>
					<td colspan="2"><form:input path="recStartTime" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>
					
					<td class="tit">服务结束时间</td>
					<td colspan="2"><form:input path="recEndTime" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>
					
				</tr>
				<tr>
					<td class="tit">科技服务天数(天)</td>
					<td colspan="2"><form:input path="kjfwts" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">集体经济增收(万元)</td>
					<td colspan="2"><form:input path="jtjjzc" readonly="true" htmlEscape="false" maxlength="50" class="number  required"/></td>
				</tr>
			
				<tr>
					<td class="tit">引进推广新品种新产品(个)</td>
					<td colspan="2"><form:input path="yjtgxpzxcp" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">推广新技术(项)</td>
					<td colspan="2"><form:input path="tgxjs" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">解决技术难题(个)</td>
					<td colspan="2"><form:input path="jjjsnt" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">培训农民(人次)</td>
					<td colspan="2"><form:input path="pxnm" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">创办领办培育企业或合作社(个)</td>
					<td colspan="2"><form:input path="cbqyhzs" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">带动就业人数(人)</td>
					<td colspan="2"><form:input path="ddjyrc" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">服务农村合作组织(个)</td>
					<td colspan="2"><form:input path="fwnchzzz" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">建立科技示范基地(个)</td>
					<td colspan="2"><form:input path="jlkjsfjd" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">培育科技示范户(户)</td>
					<td colspan="2"><form:input path="pykjsfh" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">举办培训班(场次)</td>
					<td colspan="2"><form:input path="jbpxb" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">培训人员(人次)</td>
					<td colspan="2"><form:input path="pxry" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">培训贫困群众(人次)</td>
					<td colspan="2"><form:input path="pxpkqz" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">实现产值(万元)</td>
					<td colspan="2"><form:input path="sxcz" readonly="true" htmlEscape="false" maxlength="50" class="number required"/></td>
					<td class="tit">带动增收(万元)</td>
					<td colspan="2"><form:input path="ddzs" readonly="true" htmlEscape="false" maxlength="50" class="number required"/></td>
				</tr>
				<tr>
					<td class="tit">创利税(万元)</td>
					<td colspan="2"><form:input path="cls" readonly="true" htmlEscape="false" maxlength="50" class="number required"/></td>
					<td class="tit">帮扶贫困村(个)</td>
					<td colspan="2"><form:input path="bfpkc" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">贫困户(户)</td>
					<td colspan="2"><form:input path="pkh"  readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
					<td class="tit">带动脱贫(户)</td>
					<td colspan="2"><form:input path="ddtp" readonly="true" htmlEscape="false" maxlength="50" class="digits required"/></td>
				</tr>
				<tr>
					<td class="tit">贫困人口(个)</td>
					<td colspan="2"><form:input path="pkrk" readonly="true" htmlEscape="false" maxlength="50" class="digits"/></td>
					<td class="tit">填报时间</td>
					<td colspan="2"><form:input path="recWrittenTime" readonly="true"  htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>
				</tr>
				<tr>
					<td class="tit">工作总结</td>
					<td colspan="5">
						<form:textarea path="recHelpContent"  readonly="true" class="required" rows="5" cssStyle="width:1020px"/>
					</td>
				</tr>	
				<tr>
				<td class="tit">日志图片:</td>
				<td colspan="5">
				<c:if test="${empty imagePathList}">
				<img alt="暂无图片" src="" /> <br/>  
				</c:if>
				<c:if test="${imagePathList==null}">
				<img alt="暂无图片" src="" /> <br/>  
				</c:if>
				<c:if test="${!empty imagePathList}">
				<c:forEach items="${imagePathList}" var="imagename">  
                <img alt="暂无图片" src="/tjImage${imagename}" style="width:400px;height:200px"/> <br/>  
        		</c:forEach>  
				</c:if>
				</td>
			</tr>			
			<tr>
				<td colspan="5"><h4>审核信息</h4></td>
				</tr>
				<tr>
					<td class="tit">审核人</td>
					<td colspan="2"><form:input path="recApproperson" readonly="true" htmlEscape="false" maxlength="50" class="required"/></td>
					<td class="tit">审核时间</td>
					<td colspan="2"><form:input path="recApprotime" readonly="true"  htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>
				</tr>
				<tr>
					<td class="tit">审核</td>
					<td colspan="2">
					<form:select path="recStatus" cssStyle="width:220px">
					<option value="pass">通过</option>
					<option value="return">退回</option>
					</form:select>
					</td>
					<td class="tit"></td>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td class="tit">审核意见</td>
					<td colspan="5">
						<form:textarea path="recApproopinion" class="required" rows="5" cssStyle="width:1020px"/>
					</td>
				</tr>					
			</table>
		<div hidden class="control-group">
			<label class="control-label">填写人id：</label>
			<div class="controls">
				<form:input path="recAppropersonid" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="提 交" />
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>
