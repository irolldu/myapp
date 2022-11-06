<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<tags:layout>
	<jsp:attribute name="content">
		<div class="row">
			<div class="col-4">
				<div class="card">
					<div class="card-body">
						<security:authorize access="isAuthenticated()" var="isAuthenticated"></security:authorize>
						<c:choose>
							<c:when test="${isAuthenticated}">
								<div class="row">
									<div class="col-6">
										<a class="btn btn-primary btn-block" href="${pageContext.request.contextPath}/user/logout">로그아웃</a>
									</div>
									<div class="col-6">
										<a class="btn btn-outline-primary btn-block" href="${pageContext.request.contextPath}/user/admin">내게시물</a>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="row">
									<div class="col-6">
										<a class="btn btn-primary btn-block" href="${pageContext.request.contextPath}/user/login">로그인</a>
									</div>
									<div class="col-6">
										<a class="btn btn-outline-primary btn-block" href="${pageContext.request.contextPath}/user/create">회원가입</a>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<div class="col-8">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">게시물</h5>
						<div class="list-group">
							<c:choose>
								<c:when test="${postListDto.totalCount == 0}">
									<div class="list-group-item list-group-item-action">
										검색 결과가 없습니다.
									</div>									
								</c:when>
								<c:otherwise>
									<c:forEach items="${postListDto.posts}" var="item">
										<a class="list-group-item list-group-item-action" href="${item.link}">
											<div class="row">
												<div class="col-1 align-self-center">
													<img class="img-fluid" src="${item.image}">
												</div>
												<div class="col-11">
													<div class="d-flex w-100 justify-content-between">
														<h5 class="mb-1">${item.title}</h5>
														<small>${item.dday}</small>
													</div>
													<p class="mb-1">${item.company}</p>
												</div>
											</div>
										</a>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
	</jsp:attribute>
</tags:layout>