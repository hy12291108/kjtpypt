<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>审批管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/fileUpload/css/iconfont.css" rel="stylesheet" type="text/css"/>
	<link href="${ctxStatic}/fileUpload/css/fileUpload.css" rel="stylesheet" type="text/css">
	<script src="${ctxStatic}/fileUpload/js/fileUpload.js" type="text/javascript"></script>
	<link rel="stylesheet" href="${ctxStatic}/centermenu-master/css/animate.css">
	<link rel="stylesheet" href="${ctxStatic}/centermenu-master/css/toast.css">	
	<link rel="stylesheet" href="${ctxStatic}/centermenu-master/css/centermenu.css">
	<script type="text/javascript">
	//alert("2222");
		$(document).ready(function() {
			var option = null;
			$("#fileUploadContent").initUpload({
        		"uploadUrl":"/a/dailywork/tpy/tpyDailyrecord/uploadFwdxImage",//上传文件信息地址
        		//"size":350,//文件大小限制，单位kb,默认不限制
        		"maxFileNumber":2,//文件个数限制，为整数
        		//"filelSavePath":"",//文件上传地址，后台设置的根目录
        		//"beforeUpload":beforeUploadFun,//在上传前执行的函数
        		//"onUpload":onUploadFun，//在上传后执行的函数
        		//autoCommit:true,//文件是否自动上传
       		    "fileType":['png','jpg','JPG','PNG']//文件类型限制，默认不限制，注意写的是文件后缀
    		});
  			//$(".uploadFileBt").hide();
  			//$(".cleanFileBt").hide();
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
    function dailyRecordByFwdxSave(){
    	var fileNumber1 = uploadTools.getFileNumber(option);
    	if(fileNumber1>=1){
    		uploadEvent.uploadFileEvent(option,2);
    	}else{
    	alert();
    		$("#inputForm").attr("action","/kjtpypt/a/dailywork/tpy/tpyDailyrecord/save");//测试
    	//	$("#inputForm").attr("action","/a/dailywork/tpy/tpyDailyrecord/save");//正式
        	$("#inputForm").submit();	
    	}
        
    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/dailywork/tpy/tpyDailyrecord/">工作日志待审核列表</a></li>
		<li><a href="${ctx}/dailywork/tpy/tpyDailyrecord/yshlist">工作日志已审核列表</a></li>	
		<li class="active"><a href="${ctx}/dailywork/tpy/tpyDailyrecord/form">特派员日志添加(服务对象)</a></li>
	</ul>
		<form:form id="inputForm" modelAttribute="tpyDailyrecord" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<fieldset>
		<table id="contentTable" class="table-form">
		<tbody>	
			<tr>
				<td colspan="4"><h4>特派员日志(服务对象)填报信息</h4></td>
			</tr>						
			<tr>
				<td class="tit">填报人</td>
				<td><form:input path="recWriter" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>
				<td class="tit">特派员类型</td>
				<td><form:input path="tpytype" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>					
			</tr>
			<tr>
				<td class="tit">年度</td>					
				<td>
				<form:input path="Year" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px" onclick="WdatePicker({dateFmt:'yyyy'+'年',isShowClear:false});"/>
				</td>
				<td class="tit">本年度是否三区人才</td>
				<td><form:input path="IsThreeArea" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>					
			</tr>
			<tr>
				<td class="tit">填报服务对象</td>
				<td><form:select path="recHelpobjid" id="recHelpobjid" class="required" cssStyle="width:210px">
				<c:forEach items="${tpyDailyrecord.recHelpObjList}" var="a1">	
				<form:option value="${a1.xqdwid}" label="${a1.xqdwname}"/>
				</c:forEach>
				</form:select>
				</td>
				<td class="tit">服务对象及日志类型</td>
				<td>
				<form:input path="recHelpobjtype" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px"/>
				<form:input path="dailyRecordType" readonly="true" htmlEscape="false" maxlength="50" cssStyle="width:210px"/>
				</td>										
			</tr>				
			<tr>
				<td class="tit">服务开始时间</td>
				<td>
				<input id="recStartTime" name="recStartTime" type="text" readonly="readonly" maxlength="100" class="required Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
				</td>
				<td class="tit">服务结束时间</td>
				<td>
				<input id="recEndTime" name="recEndTime" type="text" readonly="readonly" maxlength="100" class="required Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<span class="help-inline"><font color="red">*</font> </span>
				</td>
			</tr>
			<tr>
				<td class="tit">科技服务天数(天)</td>
				<td><form:input path="kjfwts" htmlEscape="false" maxlength="50" class="digits required"/></td>
				<td class="tit">集体经济增收(万元)</td>
				<td><form:input path="jtjjzc" htmlEscape="false" maxlength="50" class="number  required"/></td>
			</tr>
			<tr>
				<td class="tit">引进推广新品种新产品(个)</td>
				<td><form:input path="yjtgxpzxcp" htmlEscape="false" maxlength="50" class="digits required"/></td>
				<td class="tit">推广新技术(项)</td>
				<td><form:input path="tgxjs" htmlEscape="false" maxlength="50" class="digits required"/></td>
			</tr>
			<tr>
				<td class="tit">解决技术难题(个)</td>
				<td><form:input path="jjjsnt" htmlEscape="false" maxlength="50" class="digits required"/></td>
				<td class="tit">培训农民(人次)</td>
				<td><form:input path="pxnm" htmlEscape="false" maxlength="50" class="digits required"/></td>
			</tr>
			<tr>
				<td class="tit">创办领办培育企业或合作社(个)</td>
				<td><form:input path="cbqyhzs" htmlEscape="false" maxlength="50" class="digits required"/></td>
				<td class="tit">带动就业人数(人)</td>
				<td><form:input path="ddjyrc" htmlEscape="false" maxlength="50" class="digits required"/></td>
			</tr>
			<tr>
				<td class="tit">服务农村合作组织(个)</td>
				<td><form:input path="fwnchzzz" htmlEscape="false" maxlength="50" class="digits required"/></td>
				<td class="tit">建立科技示范基地(个)</td>
				<td><form:input path="jlkjsfjd" htmlEscape="false" maxlength="50" class="digits required"/></td>
			</tr>
			<tr>
				<td class="tit">培育科技示范户(户)</td>
				<td><form:input path="pykjsfh" htmlEscape="false" maxlength="50" class="digits required"/></td>
				<td class="tit">举办培训班(场次)</td>
				<td><form:input path="jbpxb" htmlEscape="false" maxlength="50" class="digits required"/></td>
			</tr>
			<tr>
				<td class="tit">培训人员(人次)</td>
				<td><form:input path="pxry" htmlEscape="false" maxlength="50" class="digits required"/></td>
				<td class="tit">培训贫困群众(人次)</td>
				<td><form:input path="pxpkqz" htmlEscape="false" maxlength="50" class="digits required"/></td>
			</tr>
			<tr>
				<td class="tit">实现产值(万元)</td>
				<td><form:input path="sxcz" htmlEscape="false" maxlength="50" class="number required"/></td>
				<td class="tit">带动增收(万元)</td>
				<td><form:input path="ddzs" htmlEscape="false" maxlength="50" class="number required"/></td>
			</tr>
			<tr>
				<td class="tit">创利税(万元)</td>
				<td><form:input path="cls" htmlEscape="false" maxlength="50" class="number required"/></td>
				<td class="tit">帮扶贫困村(个)</td>
				<td><form:input path="bfpkc" htmlEscape="false" maxlength="50" class="digits required"/></td>
			</tr>
			<tr>
				<td class="tit">贫困户(户)</td>
				<td><form:input path="pkh" htmlEscape="false" maxlength="50" class="digits required"/></td>
				<td class="tit">带动脱贫(户)</td>
				<td><form:input path="ddtp" htmlEscape="false" maxlength="50" class="digits required"/></td>
			</tr>
			<tr>
				<td class="tit">贫困人口(个)</td>
				<td><form:input path="pkrk" htmlEscape="false" maxlength="50" class="digits"/></td>
				<td class="tit">填报时间</td>
				<td><form:input path="recWrittenTime" readonly="true"  htmlEscape="false" maxlength="50" cssStyle="width:210px"/></td>
			</tr>
			<tr>
				<td class="tit">工作总结</td>
				<td colspan="3">
				<form:textarea path="recHelpContent"  rows="4"  cssStyle="width:960px"/>
				</td>
			</tr>			
			<tr>
				<td class="tit"><button type="button" class="btn btn-primary" id="tit">选择图片</button></td>
				<td colspan="3">
				<div id="fileUploadContent" class="fileUploadContent"></div>   		
				</td>	
			</tr>						
			<tbody>
		</table>
		<form:input path="drTableImage" id="drTableImage" htmlEscape="false" type="hidden"/>
		</fieldset>	
		<div class="form-actions">
			
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="提交" onclick="dailyRecordByFwdxSave()"/>		
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<c:if test="${not empty testAudit.id}">
			<act:histoicFlow procInsId="${tpyDailyrecord.id}" />
		</c:if>
	</form:form>
</body>
</html>
