<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="meta" fragment="true"%>
<%@ attribute name="css" fragment="true"%>
<%@ attribute name="content" fragment="true"%>
<%@ attribute name="script" fragment="true"%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<jsp:invoke fragment="meta"></jsp:invoke>

<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap-icons/font/bootstrap-icons.css">
<jsp:invoke fragment="css"></jsp:invoke>

<title>MyApp</title>
</head>
<body>
	<nav class="navbar navbar-expand navbar-dark bg-primary mb-5">
		<div class="container">
			<a class="navbar-brand" href="${pageContext.request.contextPath}">
				<i class="bi bi-house-fill"></i>
			</a>
			<div class="navbar-nav mr-auto">
				<a class="nav-link${requestScope['javax.servlet.forward.servlet_path'] == '/post/list' ? ' active' : null}" href="${pageContext.request.contextPath}/post/list">게시물</a>
				<a class="nav-link${requestScope['javax.servlet.forward.servlet_path'] == '/post/create' ? ' active' : null}" href="${pageContext.request.contextPath}/post/create">게시물 등록</a>
			</div>
		</div>
	</nav>
	<section class="mb-5">
		<div class="container">
			<jsp:invoke fragment="content"></jsp:invoke>
		</div>
	</section>

	<script src="${pageContext.request.contextPath}/webjars/jquery/dist/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/webjars/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
	<jsp:invoke fragment="script"></jsp:invoke>
</body>
</html>