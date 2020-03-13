<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<c:if test="${article.category.id != 999&&article.category.id !=1000}">
<head>
	<title>${category.name}</title>
<!-- 201709021加服务平台页面样式 -->
<link href="/kjtpypt/static/skines/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="/kjtpypt/static/skines/css/css.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.min.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			if ("${category.allowComment}"=="1" && "${article.articleData.allowComment}"=="1"){
				$("#comment").show();
				page(1);
			}
		});
	/*	function page(n,s){
			$.get("${ctx}/comment",{theme: '${site.theme}', 'category.id': '${category.id}',
				contentId: '${article.id}', title: '${article.title}', pageNo: n, pageSize: s, date: new Date().getTime()
			},function(data){
				$("#comment").html(data);
			});
		}*/
	</script>
</head>
<body>
<div class="header">
	<div class="top">
		<h1><img src="/kjtpypt/static/skines/images/logo.png" width="560" height="42" alt=""/><span>陕西省科技特派员服务与管理系统</span></h1>
		<ul class="login">
			<!-- <li><a href="#" class="bt">登  录</a></li>
			<li>没有账户？<a href="#">立即注册</a></li> -->
			<c:if test="${user.name!=''&&user.name!=null}">
			<li><a href="#" class="bt">${user.name}已登录</a></li>
			</c:if>
			<c:if test="${user.name==''||user.name==null}">
			<li><a href="/kjtpypt/a/UserRegister/temporaryLoginIndex" class="bt"> 登  录</a></li>
			<li>没有账户？<a href="/kjtpypt/a/UserRegister/temporary">立即注册</a></li>
			</c:if>
		</ul>
	</div>	
	<div class="navbg">
		<div class="nav">
			<ul>
				<c:if test="${category.name=='通知通告'}">
					<li><a href="/kjtpypt/f/index-1.html">首  页</a></li>
					<li><a href="/kjtpypt/f/list-6.html" class="current">通知通告</a></li>
					<li><a href="/kjtpypt/f/list-2.html" >动态新闻</a></li>
					<li><a href="/kjtpypt/f/list-10.html" >特派员风采</a></li>
					<li><a href="/kjtpypt/f/list-1000.html">学习园地</a></li>
					<li><a href="/kjtpypt/a/communication/listAll">用户交流</a></li>
					<li><a href="/kjtpypt/f/list-999.html">专家咨询</a></li>
					<li><a href="/kjtpypt/a/communication/contactMe">联系我们</a></li>
	    		</c:if>
				<c:if test="${category.name=='动态新闻'}">
					<li><a href="/kjtpypt/f/index-1.html">首  页</a></li>
					<li><a href="/kjtpypt/f/list-6.html">通知通告</a></li>
					<li><a href="/kjtpypt/f/list-2.html" class="current">动态新闻</a></li>
					<li><a href="/kjtpypt/f/list-10.html">特派员风采</a></li>
					<li><a href="/kjtpypt/f/list-1000.html">学习园地</a></li>
					<li><a href="/kjtpypt/a/communication/listAll">用户交流</a></li>
					<li><a href="/kjtpypt/f/list-999.html">专家咨询</a></li>
					<li><a href="/kjtpypt/a/communication/contactMe">联系我们</a></li>
				</c:if>
				<c:if test="${category.name=='特派员风采'}">
					<li><a href="/kjtpypt/f/index-1.html">首  页</a></li>
					<li><a href="/kjtpypt/f/list-6.html">通知通告</a></li>
					<li><a href="/kjtpypt/f/list-2.html">动态新闻</a></li>
					<li><a href="/kjtpypt/f/list-10.html" class="current">特派员风采</a></li>
					<li><a href="/kjtpypt/f/list-1000.html">学习园地</a></li>
					<li><a href="/kjtpypt/a/communication/listAll">用户交流</a></li>
					<li><a href="/kjtpypt/f/list-999.html">专家咨询</a></li>
					<li><a href="/kjtpypt/a/communication/contactMe">联系我们</a></li>
				</c:if>
				<c:if test="${category.name=='学习园地'}">
					<li><a href="/kjtpypt/f/index-1.html">首  页</a></li>
					<li><a href="/kjtpypt/f/list-6.html">通知通告</a></li>
					<li><a href="/kjtpypt/f/list-2.html">动态新闻</a></li>
					<li><a href="/kjtpypt/f/list-10.html" class="current">特派员风采</a></li>
					<li><a href="/kjtpypt/f/list-1000.html">学习园地</a></li>
					<li><a href="/kjtpypt/a/communication/listAll">用户交流</a></li>
					<li><a href="/kjtpypt/f/list-999.html">专家咨询</a></li>
					<li><a href="/kjtpypt/a/communication/contactMe">联系我们</a></li>
				</c:if>
				<c:if test="${category.name=='专家咨询'}">
					<li><a href="/kjtpypt/f/index-1.html">首  页</a></li>
					<li><a href="/kjtpypt/f/list-6.html">通知通告</a></li>
					<li><a href="/kjtpypt/f/list-2.html">动态新闻</a></li>
					<li><a href="/kjtpypt/f/list-10.html">特派员风采</a></li>
					<li><a href="/kjtpypt/f/list-1000.html">学习园地</a></li>
					<li><a href="/kjtpypt/a/communication/listAll">用户交流</a></li>
					<li><a href="/kjtpypt/f/list-999.html class="current">专家咨询</a></li>
					<li><a href="/kjtpypt/a/communication/contactMe">联系我们</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>

<div class="path">当前位置：<a href="list.html">${category.name}</a></div>
	<div class="container">
			<div class="main_sub">
				<div class="main_sub_article">
	             	<h3><font style="color:#438cce;font-size:24px; font-weight:bold;">${article.title}</font></h3>
	             	<c:if test="${not empty article.description}"><div>摘要：${article.description}</div></c:if>
					<!--  <em><fmt:formatDate value="${article.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></em>-->
					<em></em>
					
	                <div class="article">			
						${article.articleData.content}
	                </div>
	                
	                <hr color="#1240ab" size="1px">
					<c:if test="${not empty fjlist}">
						<div class="content_par_main">
					    	<label>附件列表：</label>
					    	<table id="test" >
			    		  		<c:forEach items="${fjlist}" var="jjbgAttachment">
									<tr id="${jjbgAttachment.id}">
										<td style="border:0px">
											<a value="${jjbgAttachment.ahtpath }" href="${ctx}/cms/article/download?id=${jjbgAttachment.id}" >${fns:abbr(jjbgAttachment.ahtFilename,45)}</a>
										</td>
									</tr>
								</c:forEach>
			    			</table>
						</div>
					</c:if>
	            </div>	            
	            
				<div class="other">发布者：${article.user.name} &nbsp; 点击数：${article.hits} &nbsp; 发布时间：<fmt:formatDate value="${article.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/> &nbsp; 更新时间：<fmt:formatDate value="${article.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
				<!--  <div class="other">
					<p>上一篇：<a href="#">“加速”中国科技：走近共和国首台大科学装置</a></p>
					<p>上一篇：<a href="#">科技日报：“重大科学仪器设备开发”重点专项指南解读</a></p>
				</div>-->
		</div>
	</div>
<div class="footer">陕西省科技特派员服务与管理系统 版权所有     Copyright © 2017 备案信息：陕ICP备***********</div>
</body>
</c:if>
<c:if test="${article.category.id == 1000}">
<head>
	<title>${category.name}</title>
<!-- 201709021加服务平台页面样式 -->
<link href="/kjtpypt/static/skines/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="/kjtpypt/static/skines/css/css.css" rel="stylesheet" type="text/css">	

	<script type="text/javascript">
		$(document).ready(function() {
			if ("${category.allowComment}"=="1" && "${article.articleData.allowComment}"=="1"){
				$("#comment").show();
				page(1);
			}
		});
		function page(n,s){
			$.get("${ctx}/comment",{theme: '${site.theme}', 'category.id': '${category.id}',
				contentId: '${article.id}', title: '${article.title}', pageNo: n, pageSize: s, date: new Date().getTime()
			},function(data){
				$("#comment").html(data);
			});
		}
	</script>
</head>
<body>
<div class="header">
	<div class="top">
		<h1><img src="/kjtpypt/static/skines/images/logo.png" width="560" height="42" alt=""/><span>陕西省科技特派员服务与管理系统</span></h1>
		<ul class="login">
			<c:if test="${user.name!=''&&user.name!=null}">
			<li><a href="#" class="bt">${user.name}已登录</a></li>
			</c:if>
			<c:if test="${user.name==''||user.name==null}">
			<li><a href="/kjtpypt/a/UserRegister/temporaryLoginIndex" class="bt"> 登  录</a></li>
			<li>没有账户？<a href="/kjtpypt/a/UserRegister/temporary">立即注册</a></li>
			</c:if>
		</ul>
	</div>	
	<div class="navbg">
		<div class="nav">
			<ul>
				<c:if test="${category.name=='通知通告'}">
					<li><a href="/kjtpypt/f/index-1.html">首  页</a></li>
					<li><a href="/kjtpypt/f/list-6.html" class="current">通知通告</a></li>
					<li><a href="/kjtpypt/f/list-2.html" >动态新闻</a></li>
					<li><a href="/kjtpypt/f/list-10.html" >特派员风采</a></li>
					<li><a href="/kjtpypt/f/list-1000.html">学习园地</a></li>
					<li><a href="/kjtpypt/a/communication/listAll">用户交流</a></li>
					<li><a href="/kjtpypt/f/list-999.html">专家咨询</a></li>
					<li><a href="/kjtpypt/a/communication/contactMe">联系我们</a></li>
	    		</c:if>
				<c:if test="${category.name=='动态新闻'}">
					<li><a href="/kjtpypt/f/index-1.html">首  页</a></li>
					<li><a href="/kjtpypt/f/list-6.html">通知通告</a></li>
					<li><a href="/kjtpypt/f/list-2.html" class="current">动态新闻</a></li>
					<li><a href="/kjtpypt/f/list-10.html">特派员风采</a></li>
					<li><a href="/kjtpypt/f/list-1000.html">学习园地</a></li>
					<li><a href="/kjtpypt/a/communication/listAll">用户交流</a></li>
					<li><a href="/kjtpypt/f/list-999.html">专家咨询</a></li>
					<li><a href="/kjtpypt/a/communication/contactMe">联系我们</a></li>
				</c:if>
				<c:if test="${category.name=='特派员风采'}">
					<li><a href="/kjtpypt/f/index-1.html">首  页</a></li>
					<li><a href="/kjtpypt/f/list-6.html">通知通告</a></li>
					<li><a href="/kjtpypt/f/list-2.html">动态新闻</a></li>
					<li><a href="/kjtpypt/f/list-10.html" class="current">特派员风采</a></li>
					<li><a href="/kjtpypt/f/list-1000.html">学习园地</a></li>
					<li><a href="/kjtpypt/a/communication/listAll">用户交流</a></li>
					<li><a href="/kjtpypt/f/list-999.html">专家咨询</a></li>
					<li><a href="/kjtpypt/a/communication/contactMe">联系我们</a></li>
				</c:if>
				<c:if test="${category.name=='学习园地'}">
					<li><a href="/kjtpypt/f/index-1.html">首  页</a></li>
					<li><a href="/kjtpypt/f/list-6.html">通知通告</a></li>
					<li><a href="/kjtpypt/f/list-2.html">动态新闻</a></li>
					<li><a href="/kjtpypt/f/list-10.html">特派员风采</a></li>
					<li><a href="/kjtpypt/f/list-1000.html" class="current">学习园地</a></li>
					<li><a href="/kjtpypt/a/communication/listAll">用户交流</a></li>
					<li><a href="/kjtpypt/f/list-999.html">专家咨询</a></li>
					<li><a href="/kjtpypt/a/communication/contactMe">联系我们</a></li>
				</c:if>
				<c:if test="${category.name=='专家咨询'}">
					<li><a href="/kjtpypt/f/index-1.html">首  页</a></li>
					<li><a href="/kjtpypt/f/list-6.html">通知通告</a></li>
					<li><a href="/kjtpypt/f/list-2.html">动态新闻</a></li>
					<li><a href="/kjtpypt/f/list-10.html">特派员风采</a></li>
					<li><a href="/kjtpypt/f/list-1000.html">学习园地</a></li>
					<li><a href="/kjtpypt/a/communication/listAll">用户交流</a></li>
					<li><a href="/kjtpypt/f/list-999.html class="current">专家咨询</a></li>
					<li><a href="/kjtpypt/a/communication/contactMe">联系我们</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>

<div class="path">当前位置：<a href="#">${category.name}</a></div>
	<div class="container">
			<div class="main_sub">
				<div class="main_sub_article">
	             	<h3><font style="color:#438cce;font-size:24px">${article.title}</font></h3>
	             	<c:if test="${not empty article.description}"><div>摘要：${article.description}</div></c:if>
					<!--  <em><fmt:formatDate value="${article.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></em>-->
					<em></em>
					<c:forEach items="${kjtpyVideoInfoList}" var="a1">
		                <div class="article" align="center">
			                <c:set var="time" value="<%=System.currentTimeMillis()%>"></c:set>
			                <script type="text/javascript" src="http://10.0.248.53:8083/assets/player.js"></script>
							<script type="text/javascript">p2ps_embed("flash", "vod_p2p", "http://10.0.248.53:8083/${a1.virtualPath}", "p2ps_video_${time}", "640", "480", "10.1.0", "${a1.p2psSwfUrl}",{}, {allowFullScreen:true,allowScriptAccess: "always"});</script>
							<div id="p2ps_video_${time}">
								<h1>我们需要Flash player 10.1或以上版本来播放。</h1>
								<p>
									<a href="http://www.adobe.com/go/getflashplayer">
										<img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="安装最新的Flash player" />
									</a>
								</p>
							</div>
		                </div>
	                </c:forEach>
	                ${article.articleData.content}
	            </div>	            
	            
				<div class="other">发布者：${article.user.name} &nbsp; 点击数：${article.hits} &nbsp; 发布时间：<fmt:formatDate value="${article.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/> &nbsp; 更新时间：<fmt:formatDate value="${article.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
				<!--  <div class="other">
					<p>上一篇：<a href="#">“加速”中国科技：走近共和国首台大科学装置</a></p>
					<p>上一篇：<a href="#">科技日报：“重大科学仪器设备开发”重点专项指南解读</a></p>
				</div>-->
		</div>
	
	</div>
<div class="footer">陕西省科技特派员服务与管理系统 版权所有     Copyright © 2017 备案信息：陕ICP备***********</div>
</body>
</c:if>





<c:if test="${article.category.id == 999}">
	<head>
		<title>${article.title} - ${category.name}</title>
			<title>${category.name}</title>
		<!-- 201709021加服务平台页面样式 -->
		<link href="/kjtpypt/static/skines/css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="/kjtpypt/static/skines/css/css.css" rel="stylesheet" type="text/css">
		<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				if ("${category.allowComment}"=="1" && "${article.articleData.allowComment}"=="1"){
					$("#comment").show();
					page(1);
				}
			});
			function page(n,s){
				$.get("${ctx}/comment",{theme: '${site.theme}', 'category.id': '${category.id}',flag:'0'
					contentId: '${article.id}', title: '${article.title}', pageNo: n, pageSize: s, date: new Date().getTime()
				},function(data){
					$("#comment").html(data);
				});
			}
		</script>
	</head>
	<body>
	<div class="header">
	<div class="top">
		<h1><img src="/kjtpypt/static/skines/images/logo.png" width="560" height="42" alt=""/><span>陕西省科技特派员服务与管理系统</span></h1>
		<ul class="login">
			<c:if test="${user.name!=''&&user.name!=null}">
			<li><a href="#" class="bt">${user.name}已登录</a></li>
			</c:if>
			<c:if test="${user.name==''||user.name==null}">
			<li><a href="/kjtpypt/a/UserRegister/temporaryLoginIndex" class="bt"> 登  录</a></li>
			<li>没有账户？<a href="/kjtpypt/a/UserRegister/temporary">立即注册</a></li>
			</c:if>
		</ul>
	</div>	
	<div class="navbg">
		<div class="nav">
			<ul>
				<c:if test="${category.name=='通知通告'}">
					<li><a href="/kjtpypt/f/index-1.html">首  页</a></li>
					<li><a href="/kjtpypt/f/list-6.html" class="current">通知通告</a></li>
					<li><a href="/kjtpypt/f/list-2.html" >动态新闻</a></li>
					<li><a href="/kjtpypt/f/list-10.html" >特派员风采</a></li>
					<li><a href="/kjtpypt/f/list-1000.html">学习园地</a></li>
					<li><a href="/kjtpypt/a/communication/listAll">用户交流</a></li>
					<li><a href="/kjtpypt/f/list-999.html">专家咨询</a></li>
					<li><a href="/kjtpypt/a/communication/contactMe">联系我们</a></li>
	    		</c:if>
				<c:if test="${category.name=='动态新闻'}">
					<li><a href="/kjtpypt/f/index-1.html">首  页</a></li>
					<li><a href="/kjtpypt/f/list-6.html">通知通告</a></li>
					<li><a href="/kjtpypt/f/list-2.html" class="current">动态新闻</a></li>
					<li><a href="/kjtpypt/f/list-10.html">特派员风采</a></li>
					<li><a href="/kjtpypt/f/list-1000.html">学习园地</a></li>
					<li><a href="/kjtpypt/a/communication/listAll">用户交流</a></li>
					<li><a href="/kjtpypt/f/list-999.html">专家咨询</a></li>
					<li><a href="/kjtpypt/a/communication/contactMe">联系我们</a></li>
				</c:if>
				<c:if test="${category.name=='特派员风采'}">
					<li><a href="/kjtpypt/f/index-1.html">首  页</a></li>
					<li><a href="/kjtpypt/f/list-6.html">通知通告</a></li>
					<li><a href="/kjtpypt/f/list-2.html">动态新闻</a></li>
					<li><a href="/kjtpypt/f/list-10.html" class="current">特派员风采</a></li>
					<li><a href="/kjtpypt/f/list-1000.html">学习园地</a></li>
					<li><a href="/kjtpypt/a/communication/listAll">用户交流</a></li>
					<li><a href="/kjtpypt/f/list-999.html">专家咨询</a></li>
					<li><a href="/kjtpypt/a/communication/contactMe">联系我们</a></li>
				</c:if>
				<c:if test="${category.name=='专家咨询'}">
					<li><a href="/kjtpypt/f/index-1.html">首  页</a></li>
					<li><a href="/kjtpypt/f/list-6.html">通知通告</a></li>
					<li><a href="/kjtpypt/f/list-2.html">动态新闻</a></li>
					<li><a href="/kjtpypt/f/list-10.html">特派员风采</a></li>
					<li><a href="/kjtpypt/f/list-1000.html">学习园地</a></li>
					<li><a href="/kjtpypt/a/communication/listAll">用户交流</a></li>
					<li><a href="/kjtpypt/f/list-999.html" class="current">专家咨询</a></li>
					<li><a href="/kjtpypt/a/communication/contactMe">联系我们</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>

<div class="path">当前位置：<a href="/kjtpypt/f/list-999.html">${category.name}</a></div>
	<div class="container">
		<div class="main_sub">
			<div class="main_sub_article">
             	<h3><font style="color:#438cce;font-size:24px">${article.title}</font></h3>
             	<em>发布者：${article.user.name} &nbsp; 点击数：${article.hits} &nbsp; 发布时间：<fmt:formatDate value="${article.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/> &nbsp; 更新时间：<fmt:formatDate value="${article.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></em>
                <div class="article" style="text-align:center;">
                <c:if test="${!empty article.sysAttachmentList}">
                <c:forEach items="${article.sysAttachmentList}" var="a1">			
                	<div >
                <%-- 	<video  preload="auto" width="500px" height="300px" controls="controls" src="/kjtpypt/a/dailywork/village/vilProtocol/showImage?filename=${a1.attName}&fileType=${a1.attFolder}">${a1.attOriginname}</video>
                	 --%>
                	<video  preload="auto" width="500px" height="300px" controls="controls" >
                	<source src="http://127.0.0.1:8080/kjtpypt/a/dailywork/village/vilProtocol/showImage?filename=${a1.attName}&fileType=${a1.attFolder}" type="video/mp4">
                	</video>
                	
                	
                	</div>
				</c:forEach>
				</c:if>
				<c:if test="${!empty imagePathList}">
				<div>
					<c:forEach items="${imagePathList}" var="imagename">  
       		 		<img src="/zjzxImage/${imagename}" style="width:300px;height:300px"/> 
        			</c:forEach>  
				</div>
				</c:if>
                </div>
                <div  class="article">
					<p  style="text-align:center;">${article.articleData.content}</p>
				</div>
           </div>
			<div class="evaluation">
			<ul>
				<c:forEach items="${commentList}" var="comment">
					<li>
						<h5>回复人：${comment.name}<span><fmt:formatDate value="${comment.createDate}" pattern="yyyy-MM-dd HH:mm"/></span></h5>
						<p>${comment.content}</p>
					</li>
				</c:forEach>
				</ul>
			</div>
		    </div>	   
		    <%-- <div class="pagination">${page}</div>  --%>
		           
		</div>
	<div class="footer">陕西省科技特派员服务与管理系统 版权所有     Copyright © 2017 备案信息：陕ICP备***********</div>
	</body>
</c:if>
</html>