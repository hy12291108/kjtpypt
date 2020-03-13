<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>用户交流</title>
<!-- 201709021加服务平台页面样式 -->
<link href="/kjtpypt/static/skines/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="/kjtpypt/static/skines/css/css.css" rel="stylesheet" type="text/css">	
<script src="/kjtpypt/static/skines/js/jquery-1.8.3.min.js" type="text/javascript"></script>

<script type="text/javascript">
function commentSave(){
	if($("#userId").val()==""||$("#userId").val()==null){
		alert("请登录后再回复");
		return false;
	}
	$("#inputForm").attr("action","/kjtpypt/a/communicationComment/save");
	$("#inputForm").submit();
} 
function reply(name){
	$("#content").text("@"+name+":");
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
				<li><a href="/kjtpypt/f/index-1.html">首  页</a></li>
					<li><a href="/kjtpypt/f/list-6.html">通知通告</a></li>
					<li><a href="/kjtpypt/f/list-2.html">动态新闻</a></li>
					<li><a href="/kjtpypt/f/list-10.html" >特派员风采</a></li>
					<li><a href="/kjtpypt/f/list-1000.html">学习园地</a></li>
					<li><a href="/kjtpypt/a/communication/listAll" class="current">用户交流</a></li>
					<li><a href="/kjtpypt/f/list-999.html">专家咨询</a></li>
					<li><a href="/kjtpypt/a/communication/contactMe">联系我们</a></li>
			</ul>
		</div>
	</div>
</div>
<div class="path">当前位置：<a href="list.html">用户交流</a>&gt;${fns:abbr(communication.title,40)}</div>
<div class="container">
		<div class="main_sub">
			<div class="main_sub_article">
			    <h3><font style="color:#438cce;font-size:24px; font-weight:bold;">${fns:abbr(communication.title,40)}</font></h3>
				<em><fmt:formatDate value="${communication.createDate}" pattern="yyyy年MM月dd日 HH:mm"/></em>
                <div class="article">			
					<p>${communication.content}</p>
                </div>
			<div class="evaluation">
				<ul>
					<!-- <li>
						<h5>Diana:<span>2017-9-19</span></h5>
						<div class="ev_info">
							<p>遇到高温天气，苹果脱袋前10天浇水肯定比脱袋后浇水好。脱袋前适时灌溉，既可以改善土壤水分供应和果园温湿状况，又可缓解干旱和高温热害对果树的为害，促进果实膨大与着色。有些旱原果区，如果实在没有浇水条件，也可于每天下午6时以后，向树冠喷水降温增湿，改善果园小气候，缓解高温和太阳直接辐射对树体和果实的伤害。</p>
						</div>
						<div class="ev_reply">
							<div class="ev_reply_info">
								<div class="infocon">
									<h6>Mars：</h6>
									<p>评价回复评价回复评价回复评价回复评价回复评价回复评价回复评价回复评价回复评价回复评价回复评价回复评价回复评价回复评价回复评价回复评价回复评价回复评价回复</p>
									<span>2017-8-29<a href="#">回复</a></span>
								</div>
								<div class="infocon">
									<h6>Diana <span>回复</span> Mars：</h6>
									<p>评价回复评价回复评价回复评价回复评价回复评价回复评价回复评价回复评价回复评价回复评价回复评价回复评价回复评价回复评价回复评价回复评价回复评价回复评价回复</p>
									<span>2017-8-29<a href="#">回复</a></span>
								</div>
							</div>
						</div>
					</li> -->
					
				<c:forEach items="${communicationComment}" var="communicationComment">
					<li>
						<h5>${communicationComment.name}<span><fmt:formatDate value="${communicationComment.createDate}" pattern="yyyy-MM-dd HH:mm"/></span></h5>
						<p>${communicationComment.content}</p>
						<span><a href="#content" onclick="reply('${communicationComment.name}')">回复</a></span>
					</li>
				</c:forEach>
					<!-- <li>
						<h5>Diana:<span>2017-9-19</span></h5>
						<p>评价内容</p>
						<span><a href="#">回复</a></span>
					</li>
					<li>
						<h5>Diana:<span>2017-9-19</span></h5>
						<p>评价内容</p>
						<span><a href="#">回复</a></span>
					</li>
					<li>
						<h5>Diana:<span>2017-9-19</span></h5>
						<p>评价内容</p>
						<span><a href="#">回复</a></span>
					</li>
					<li>
						<h5>Diana:<span>2017-9-19</span></h5>
						<p>评价内容</p>
						<span><a href="#">回复</a></span>
					</li> -->
				</ul>
				<div class="form3">
				<form method="post" id="inputForm">
					<input type="hidden" name="communicationId" value="${communication.id}">
					<input type="hidden" name="userId" id="userId" value="${user.id}">
					<input type="hidden" name="name" value="${user.name}">
					<input type="hidden" name="title" value="${communication.title}">
					<textarea name="content" id="content" cols="" rows=""  style="width:964px;"></textarea>
					<div class="btgroup form-actions">
						<input type="button" class="btn btn-primary" onclick="commentSave()" value="发表回复">
					</div>
				</form>
				</div>
			</div>	            
    </div>  
    </div>
    </div>
<div class="footer">陕西省科技特派员服务与管理系统 版权所有     Copyright © 2017 备案信息：陕ICP备***********</div>
</body>
</html>
