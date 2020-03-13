<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>文章管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            if($("#link").val()){
                $('#linkBody').show();
                $('#url').attr("checked", true);
            }
			$("#title").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
                    if ($("#categoryId").val()==""){
                        $("#categoryName").focus();
                        top.$.jBox.tip('请选择归属栏目','warning');
                    }else if (CKEDITOR.instances.content.getData()=="" || $("#link").val()==""){
                        top.$.jBox.tip('请填写正文','warning');
                    }else{
                        loading('正在提交，请稍等...');
                        form.submit();
                    }
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
		
			var stream_name = "";
			$("#videoButton").on("click",function(){
				$("#setVideoTitle").modal();
			});
			
			
			$("#videoUpload").on("click",function(){
				var videoTitle = $("#videoTitle").val();
				$.ajax({
					type:"POST",
					url:"${ctx}/cms/article/xxydGetUrl",
					async: false,
					dataType:'json',
					data:{"videoTitle":videoTitle},
					success:function(data){
						var postUrl = data.url;
						if(postUrl.length>0){
							stream_name = data.stream_name;
							window.location.href = postUrl;
							$("#videoOverButton").show();
						}else{
							alert(data.msg);
						}
						
					}
				});
			});
// 			function videoUpload(){
// 			}
			
			
			$("#videoOverButton").on("click",function(){
				$.ajax({
					type:"POST",
					url:"${ctx}/cms/article/xxydVideoOver",
					async: false,
					dataType:'json',
					data:{"stream_name":stream_name},
					success:function(data){
						var vodId = data.vodId;
						var vodTitle = data.vodTitle;
						var vodioId = $("#videoInput").val();
						var vodioTitle = $("#videoTitleInput").val();
					
						if(vodId.length>0){
							if(vodioId.length>0){
								vodioId = vodioId + ',' + vodId;
								vodioTitle = vodioTitle + ',' + vodTitle;
								$("#videoInput").attr("value",vodioId);
								$("#videoTitleInput").attr("value",vodioTitle);
								$("#videoOverButton").hide();
							}else{
								vodioId = vodId;
								vodioTitle = vodTitle;
								$("#videoInput").attr("value",vodioId);
								$("#videoTitleInput").attr("value",vodioTitle);
								$("#videoOverButton").hide();
							}
						}else{
							$.jBox.alert("获取视频信息失败，请联系管理员");
						}
					},
					error:function(){
						$.jBox.alert("系统异常，请联系管理员");					
					}
				});
			});
		});
		
		function save(){
			var arr = new Array();
			var files = document.getElementById("uploadFrame").contentWindow.document.getElementsByTagName("a");
			for(var i=0;i<files.length;i++){
				var h = files[i].getAttribute('value');
				arr.push(h);
			} 
			var pathStr = arr.join(",");
			var arr1 = new Array();
 			var files1 = $("#test a");
 			for(var i=0;i<files1.length;i++){
				var aa = files1[i].getAttribute('value');
				arr1.push(aa);
 			} 
			var pathStr1 = arr1.join(",");
			var finallyPath;
			if(pathStr == ""){
				finallyPath = pathStr1;
			}else if(pathStr1 == "")
				finallyPath = pathStr;
			else{
 				finallyPath= pathStr+","+pathStr1;
			}
			$("#files1").attr("value", finallyPath); 
			$("#inputForm").attr("action", "${ctx}/cms/article/savexxyd");
			$("#inputForm").submit();
		}
	
		
		function deleteFile(id){
			var submit = function (v, h, f) {
		    if (v == true){
		    	 $.post("${ctx}/jjbgAttachment/jjbgAttachment/deleteFile",{"id":id})
		    	 var index=$("#"+id).index();	    	
		    	 $("#test tr:eq("+index+")").remove();  	    	
		        jBox.tip("删除成功", 'info');
		        }
		    else
		    return true;
			};
			$.jBox.confirm("确认要删除该附件吗？", "系统提示", submit, { buttons: { '确定': true, '取消': false} }); 
		}
		
		function delVideoById(id){
			alert(id);
			$.ajax({
				type:"POST",
				url:"${ctx}/cms/article/xxydDelVideo",
				async: false,
				dataType:'json',
				data:{"videoId":id},
				success:function(data){
					if(data.artId.length>0){
						var url = "${ctx}/cms/article/xxydUpdate?id=" + data.artId;
						gotoForm(url);
					}else{
						$.jBox.alert("删除视频附件失败，请刷新页面并重试");
					}
				},
				error:function(data){
					$.jBox.alert("系统异常，请联系管理员");
				}
			});
		}
		
		function gotoForm(url){
// 			location.replace(url);
			window.location.reload(url);
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/cms/article/xxydlist">学习园地文章列表</a></li>
		<li class="active"><a href="<c:url value='${fns:getAdminPath()}/cms/article/xxydUpdate?id=${article.id}&category.id=${article.category.id}'><c:param name='category.name' value='${article.category.name}'/></c:url>">学习园地文章修改</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="article" action="${ctx}/cms/article/savexxyd" method="post" class="form-horizontal"  enctype="multipart/form-data">
		<form:hidden path="id"/>
		<input type="hidden" id="files1" name="files1" value=""/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">归属栏目:</label>
			<div class="controls">
				<form:input path="category.name" readonly="true" htmlEscape="false" maxlength="200" class="input-xlarge"/>
				<form:hidden path="category.id"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标题:</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-xxlarge measure-input required"/>
				&nbsp;<label>颜色:</label>
				<form:select path="color" class="input-mini">
					<form:option value="" label="默认"/>
					<form:options items="${fns:getDictList('color')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
        <!--  <div id="linkBody" class="control-group" style="display:none">
            <label class="control-label">外部链接:</label>
            <div class="controls">
                <form:input path="link" htmlEscape="false" maxlength="200" class="input-xlarge"/>
                <span class="help-inline">绝对或相对地址。</span>
            </div>
        </div>-->
		<div class="control-group">
			<label class="control-label">关键字:</label>
			<div class="controls">
				<form:input path="keywords" htmlEscape="false" maxlength="200" class="input-xlarge"/>
				<span class="help-inline">多个关键字，用空格分隔。</span>
			</div>
		</div>
		<!-- <div class="control-group">
			<label class="control-label">权重:</label>
			<div class="controls">
				<form:input path="weight" htmlEscape="false" maxlength="200" class="input-mini required digits"/>&nbsp;
				<span>
					<input id="weightTop" type="checkbox" onclick="$('#weight').val(this.checked?'999':'0')"><label for="weightTop">置顶</label>
				</span>
				&nbsp;过期时间：
				<input id="weightDate" name="weightDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
					value="<fmt:formatDate value="${article.weightDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
				<span class="help-inline">数值越大排序越靠前，过期时间可为空，过期后取消置顶。</span>
			</div>
		</div> -->
		<div class="control-group">
			<label class="control-label">摘要:</label>
			<div class="controls">
				<form:textarea path="description" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		 <div class="control-group">
			<label class="control-label">缩略图:</label>
			<div class="controls">
                <input type="hidden" id="image" name="image" value="${article.imageSrc}" />
				<sys:ckfinder input="image" type="thumb" uploadPath="/cms/article" selectMultiple="false"/>
			</div>
		</div> 
		<div class="control-group">
			<label class="control-label">正文:</label>
			<div class="controls">
				<form:textarea id="content" htmlEscape="true" path="articleData.content" rows="4" maxlength="200" class="input-xxlarge"/>
				<sys:ckeditor replace="content" uploadPath="/cms/article" />
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">已上传附件:</label>
			<div class="controls">
				<c:forEach items="${article.sysAttachmentList}" var="a1">
					<a href="${ctx}/dailywork/village/vilProtocol/showImage?filename=${a1.attName}&fileType=${a1.attFolder}"  target="_blank" id="delAction">${a1.attOriginname}</a></br>
				</c:forEach>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">更新附件:</label>
			<div class="controls">
				<input type="file" name="file" accept="audio/mpeg,audio/mp4,video/mp4" multiple="multiple" >
			</div>
		</div> --%>
		
		<div class="control-group">
			<label class="control-label">已上传视频附件:</label>
			<div class="controls">
				 <c:forEach items="${kjtpyVideoInfoList}" var="a1">
					<a href="javascript:void(0);">${a1.title}</a>
					<input class="btn" type="button" value="删除文件" onclick="delVideoById('${a1.id}')"/>
					</br>
				</c:forEach>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">更新视频附件:</label>
			<div class="controls">
				 <input name="videoTitleInput" id="videoTitleInput" class="input-xxlarge" readonly="readonly">
				 <input  name="video" id="videoInput" class="input-xxlarge hide" >
				 <input id="videoButton" class="btn" type="button" value="上传文件"/>
				 <input id="videoOverButton" class="btn" style="display:none" type="button" value="上传完成"/>
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label">来源:</label>
			<div class="controls">
				<form:input path="articleData.copyfrom" htmlEscape="false" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">推荐位:</label>
			<div class="controls">
				<form:checkboxes path="posidList" items="${fns:getDictList('cms_posid')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发布时间:</label>
			<div class="controls">
				<input id="createDate" name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${article.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<shiro:hasPermission name="cms:article:audit">
			<div class="control-group">
				<label class="control-label">发布状态:</label>
				<div class="controls">
					<form:radiobuttons path="delFlag" items="${fns:getDictList('cms_del_flag')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
					<span class="help-inline"></span>
				</div>
			</div>
		</shiro:hasPermission>
		<shiro:hasPermission name="cms:category:edit">
          <!--  <div class="control-group">
                <label class="control-label">自定义内容视图:</label>
                <div class="controls">
                      <form:select path="customContentView" class="input-medium">
                          <form:option value="" label="默认视图"/>
                          <form:options items="${contentViewList}" htmlEscape="false"/>
                      </form:select>
                      <span class="help-inline">自定义内容视图名称必须以"${article_DEFAULT_TEMPLATE}"开始</span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">自定义视图参数:</label>
                <div class="controls">
                      <form:input path="viewConfig" htmlEscape="true"/>
                      <span class="help-inline">视图参数例如: {count:2, title_show:"yes"}</span>
                </div>
            </div>-->
		</shiro:hasPermission>
		
		<div class="control-group">
			<label class="control-label">文件附件:</label>
			<div class="controls">
				<iframe id="uploadFrame" name="uploadFrame" src="${ctx}/cms/article/upload" width="340px" onload="this.height=240"  scrolling="yes"  frameborder="yes"></iframe>
			</div>
			<div class="controls">
			<table id="test" >
	    		<c:forEach items="${fjlist.list}" var="jjbgAttachment">
					<tr id="${jjbgAttachment.id}">
						<td style="border:0px">  
							<a value="${jjbgAttachment.ahtpath }" href="${ctx}/cms/article/download?id=${jjbgAttachment.id}" >${fns:abbr(jjbgAttachment.ahtFilename,45)}</a>
						</td>
						<c:if test="${article.attachment !=null || article.attachment != ''}">
							<td id="del"  onclick="deleteFile(${jjbgAttachment.id})">删除</td>
						</c:if>
					</tr>
				</c:forEach>
	    	</table>
	    	</div>
		</div>
		<c:if test="${not empty article.id}">
			<div class="control-group">
				<label class="control-label">查看评论:</label>
				<div class="controls">
					<input id="btnComment" class="btn" type="button" value="查看评论" onclick="viewComment('${ctx}/cms/comment/?module=article&contentId=${article.id}&status=0')"/>
					<script type="text/javascript">
						function viewComment(href){
							top.$.jBox.open('iframe:'+href,'查看评论',$(top.document).width()-220,$(top.document).height()-180,{
								buttons:{"关闭":true},
								loaded:function(h){
									$(".jbox-content", top.document).css("overflow-y","hidden");
									$(".nav,.form-actions,[class=btn]", h.find("iframe").contents()).hide();
									$("body", h.find("iframe").contents()).css("margin","10px");
								}
							});
							return false;
						}
					</script>
				</div>
			</div>
		</c:if>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="save()"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<form id="videoTitleForm" action="" method="post" class="form-horizontal">
		<div class="modal fade bs-example-modal-lg" id="setVideoTitle"
			tabindex="-1" role="dialog" style="width: 450px;height: 100px; >
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content" style="margin: 30px;">
					<label>视频标题:</label><input type="text" class="form-control" id="videoTitle" name="videoTitle" style="width:150px">
					<button type="button" class="btn btn-primary" data-dismiss="modal" id="videoUpload">上传</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</form>
	
	
</body>
</html>