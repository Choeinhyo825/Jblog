<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("newLine", "\n");
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${blogVo.title }</h1>
			<ul>
			<!-- 미 로그인 시	로그인 -->
				<c:if test="${empty authUser}">
					<li><a href="${pageContext.request.contextPath }/user/login">로그인</a></li>
				</c:if>
					
			<!-- 타인 블로그면 	로그아웃 -->
				<c:if test="${blogVo.id ne authUser.id && !empty authUser}">
					<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
				</c:if>
				
			<!-- 본인 블로그면 	로그아웃 / 관리 -->
				<c:if test="${blogVo.id eq authUser.id && !empty authUser}">
					<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
					<li><a href="${pageContext.request.contextPath }/${authUser.id }/admin/basic">블로그 관리</a></li>
				</c:if>
			</ul> 
		</div>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<c:forEach items="${postList }" begin="0" end="0" var="post">
						<h4>${post.title }</h4>
						<p>${fn:replace(post.contents, newLine, "<br>")}<p>
					</c:forEach>
				</div>
				<ul class="blog-list">
					<c:forEach items="${postList }" var="post">
						<li><a href="${pageContext.request.contextPath }/${blogVo.id }/${post.categoryNo}/${post.no}">${post.title }</a> <span>${post.regDate }</span>	</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogVo.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${categoryList }" var="category">
					<li><a href="${pageContext.request.contextPath }/${blogVo.id }/${category.no}">${category.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>