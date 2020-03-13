<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>${category.name}</title>

<!-- 201709021加服务平台页面样式 -->
<link href="/kjtpypt/static/skines/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="/kjtpypt/static/skines/css/css.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	function searchArticle(){
		var a = $("#search").val();
		var category = "${category.id}";
		if(a.length > 0){
			categoryAndName = category+"-"+a; 
		}else{
			categoryAndName = category;
		}
		var url = "${ctx}/list-"  + categoryAndName +".html";
		location.replace(url);
	}
	
	function zjzxSearch(){
		var a = $("#zjzxSearch").val();
		var category = "${category.id}";
		var categoryAndName = "";
		if(a.length > 0){
			categoryAndName = category+"-"+a; 
		}else{
			categoryAndName = category;
		}
		
		var url = "${ctx}/list-"  + categoryAndName +".html";
		location.replace(url);
	}
	
</script>
<style>
	.myCss{
		margin-top:9px
	}
</style>
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
			<li><a href="/kjtpypt/a/UserRegister/logOff">退出</a></li>
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
				<li><a href="/kjtpypt/f/list-6.html" >通知通告</a></li>
				<li><a href="/kjtpypt/f/list-2.html" class="current">动态新闻</a></li>
				<li><a href="/kjtpypt/f/list-10.html">特派员风采</a></li>
				<li><a href="/kjtpypt/f/list-1000.html">学习园地</a></li>
				<li><a href="/kjtpypt/a/communication/listAll">用户交流</a></li>
				<li><a href="/kjtpypt/f/list-999.html">专家咨询</a></li>
				<li><a href="/kjtpypt/a/communication/contactMe">联系我们</a></li>
			</c:if>
			<c:if test="${category.name=='专家咨询'}">
				<li><a href="/kjtpypt/f/index-1.html">首  页</a></li>
				<li><a href="/kjtpypt/f/list-6.html" >通知通告</a></li>
				<li><a href="/kjtpypt/f/list-2.html" >动态新闻</a></li>
				<li><a href="/kjtpypt/f/list-10.html">特派员风采</a></li>
				<li><a href="/kjtpypt/f/list-1000.html">学习园地</a></li>
				<li><a href="/kjtpypt/a/communication/listAll">用户交流</a></li>
				<li><a href="/kjtpypt/f/list-999.html" class="current">专家咨询</a></li>
				<li><a href="/kjtpypt/a/communication/contactMe">联系我们</a></li>
			</c:if>
			<c:if test="${category.name=='特派员风采'}">
				<li><a href="/kjtpypt/f/index-1.html">首  页</a></li>
				<li><a href="/kjtpypt/f/list-6.html" >通知通告</a></li>
				<li><a href="/kjtpypt/f/list-2.html" >动态新闻</a></li>
				<li><a href="/kjtpypt/f/list-10.html" class="current">特派员风采</a></li>
				<li><a href="/kjtpypt/f/list-1000.html">学习园地</a></li>
				<li><a href="/kjtpypt/a/communication/listAll">用户交流</a></li>
				<li><a href="/kjtpypt/f/list-999.html">专家咨询</a></li>
				<li><a href="/kjtpypt/a/communication/contactMe">联系我们</a></li>
			</c:if>
			<c:if test="${category.name=='学习园地'}">
				<li><a href="/kjtpypt/f/index-1.html">首  页</a></li>
				<li><a href="/kjtpypt/f/list-6.html" >通知通告</a></li>
				<li><a href="/kjtpypt/f/list-2.html" >动态新闻</a></li>
				<li><a href="/kjtpypt/f/list-10.html">特派员风采</a></li>
				<li><a href="/kjtpypt/f/list-1000.html"  class="current">学习园地</a></li>
				<li><a href="/kjtpypt/a/communication/listAll">用户交流</a></li>
				<li><a href="/kjtpypt/f/list-999.html">专家咨询</a></li>
				<li><a href="/kjtpypt/a/communication/contactMe">联系我们</a></li>
			</c:if>
			
			</ul>
		</div>
	</div>
</div>

<c:choose>
       <c:when test="${category.name=='专家咨询'}">
              <div class="path">当前位置：${category.name} <a href="/kjtpypt/a/cms/article/zjzxform?category.name=专家咨询&id=&category.id=999" >&nbsp;&nbsp;&nbsp;&nbsp;<font style="color:#0072bb;">我要咨询>></font></a>
              	<input id="zjzxSearch" class="input-xlarge myCss" type="text" placeholder="根据标题查询文章"/>
              	<input type="button" class="btn" value="搜索" onclick="zjzxSearch()">
              
              
              </div>
       </c:when>
       <c:otherwise>
              <div class="path">当前位置：${category.name}
              	<input id="search" type="text" class="input-xlarge myCss" placeholder="根据标题查询文章"/>
              	<input type="button" class="btn" value="搜索" onclick="searchArticle()">
              </div>
       </c:otherwise>
</c:choose>


 <!--  <div class="span10">
		  <h4>${category.name}</h4>
		  <c:if test="${category.module eq 'article'}">
			<ul><c:forEach items="${page.list}" var="article">
				<li><span class="pull-right"><fmt:formatDate value="${article.updateDate}" pattern="yyyy.MM.dd"/></span><a href="${article.url}" style="color:${article.color}">${fns:abbr(article.title,96)}</a></li>
			</c:forEach></ul>
			<div class="pagination">${page}</div>
			<script type="text/javascript">
				function page(n,s){
					location="${ctx}/list-${category.id}${urlSuffix}?pageNo="+n+"&pageSize="+s;
				}
			</script>
		  </c:if>
		  <c:if test="${category.module eq 'link'}">
			<ul><c:forEach items="${page.list}" var="link">
				<li><a href="${link.href}" target="_blank" style="color:${link.color}"><c:out value="${link.title}" /></a></li>
			</c:forEach></ul>
		  </c:if>
  	  </div>-->
  	  
<div class="container">
		<div class="main_sub">
			<c:if test="${category.name=='通知通告'}">
				<div class="main_sub_list" >
					  <ol>
							<c:if test="${category.module eq 'article'}">
							
							<c:forEach items="${page.list}" var="article">
							<li><a href="${article.url}" style="color:${article.color}">${fns:abbr(article.title,96)}</a><span ><fmt:formatDate value="${article.updateDate}" pattern="yyyy-MM-dd"/></span></li>
							<!--<li><span class="pull-right"><fmt:formatDate value="${article.updateDate}" pattern="yyyy.MM.dd"/></span><a href="${article.url}" style="color:${article.color}">${fns:abbr(article.title,96)}</a></li>-->
							</c:forEach>
						   </c:if>
					</ol>
	        	</div>
            </c:if>
			<c:if test="${category.name=='动态新闻'}">
				<div class="main_sub_list" >
					  <ol>
						<c:if test="${category.module eq 'article'}">
						<c:forEach items="${page.list}" var="article">
						<li><a href="${article.url}" style="color:${article.color}">${fns:abbr(article.title,96)}</a><span ><fmt:formatDate value="${article.updateDate}" pattern="yyyy-MM-dd"/></span></li>
						<!--<li><span class="pull-right"><fmt:formatDate value="${article.updateDate}" pattern="yyyy.MM.dd"/></span><a href="${article.url}" style="color:${article.color}">${fns:abbr(article.title,96)}</a></li>-->
						</c:forEach>
					   </c:if>
					</ol>
	        	</div>
       		</c:if>
        	<c:if test="${category.name=='专家咨询'}">
				<div class="main_sub_list" >
					  <ol>
							<c:if test="${category.module eq 'article'}">
							<c:forEach items="${page.list}" var="article">
							<li><a href="${article.url}" style="color:${article.color}">${fns:abbr(article.title,96)}<img src="/kjtpypt/static/skines/images/video.gif" alt=""/></a><span ><fmt:formatDate value="${article.updateDate}" pattern="yyyy-MM-dd"/></span></li>
							<!--<li><span class="pull-right"><fmt:formatDate value="${article.updateDate}" pattern="yyyy.MM.dd"/></span><a href="${article.url}" style="color:${article.color}">${fns:abbr(article.title,96)}</a></li>-->
							</c:forEach>
						   </c:if>
				   
					</ol>
	        	</div>
        	</c:if>
        	<c:if test="${category.name=='特派员风采'}">
       			<div class="piclist" id="tpyfc">
				<ul>
					<c:if test="${category.module eq 'article'}">
							<c:forEach items="${page.list}" var="article">
							<li><a href="${article.url}" style="color:${article.color}" target="_blank"><img style="width:230px;height:165px" src="${article.image}"  /><span>${fns:abbr(article.title,32)}</span></a></li>
							</c:forEach>
					</c:if>
				</ul>
				</div>	
			</c:if>
			<c:if test="${category.name=='学习园地'}">
				<div class="main_sub_list" >
					  <ol>
							<c:if test="${category.module eq 'article'}">
							
							<c:forEach items="${page.list}" var="article">
							
						
							<c:choose>
	  							<c:when test="${fn:length(article.attachment)>0 }">
								<li><a href="${article.url}" style="color:${article.color}"><img src="/kjtpypt/static/skines/images/shipin.png"></img>${fns:abbr(article.title,96)}</a><span ><fmt:formatDate value="${article.updateDate}" pattern="yyyy-MM-dd"/></span></li>
								</c:when>
	
								<c:otherwise>
	                              <li><a href="${article.url}" style="color:${article.color}">${fns:abbr(article.title,96)}</a><span ><fmt:formatDate value="${article.updateDate}" pattern="yyyy-MM-dd"/></span></li>  
								</c:otherwise>
							</c:choose>
							
							
							
							</c:forEach>
							
						   </c:if>
				   
					</ol>
	     
	        </div>
        </c:if>	
 	
       <!-- <div class="page" style="color:red;">此处为分页</div> --> 
		</div>
			<div class="pagination">${page}</div>

			<script type="text/javascript">
				function page(n,s){
					location="${ctx}/list-${category.id}${urlSuffix}?pageNo="+n+"&pageSize="+s;
				}
			</script>         
        <c:if test="${category.module eq 'link'}">
			<ul><c:forEach items="${page.list}" var="link">
				<li><a href="${link.href}" target="_blank" style="color:${link.color}"><div class="footer">陕西省科技特派员服务与管理系统 版权所有     Copyright &copy; 2017 备案信息：陕ICP备***********</div></a></li>
			</c:forEach></ul>
		  </c:if>
</div>



</body>
</html>
