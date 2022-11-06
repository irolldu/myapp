<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<tags:layout>
	<jsp:attribute name="content">
		<div class="card mb-4">
			<div class="row no-gutters">
				<div class="col-2">
					<img class="img-fluid" src="${dto.image}">
				</div>
				<div class="col-10">
					<div class="card-body">
						<h5 class="card-title">${dto.title}</h5>
						<small class="text-muted">${dto.dday}</small>
					</div>
				</div>
			</div>
		</div>
		<div class="card mb-4">
			<div class="card-body">
				<div class="mb-3">
					<h5>활동 개요</h5>
					<div class="row">
						<div class="col-2">분야</div>
						<div class="col-10">
							<c:forEach items="${dto.category}" var="item">
								<span class="badge badge-primary">${item}</span>
							</c:forEach>
						</div>
					</div>
					<div class="row">
						<div class="col-2">접수기간</div>
						<div class="col-10">${dto.startDate} ~ ${dto.endDate}</div>
					</div>
				</div>
				<div class="mb-3">
					<h5>기관 정보</h5>
					<div class="row">
						<div class="col-2">주최 기관명</div>
						<div class="col-10">${dto.company}</div>
					</div>
					<div class="row">
						<div class="col-2">주최 구분</div>
						<div class="col-10">
							<span class="badge badge-primary">${dto.companyType}</span>
						</div>
					</div>
					<div class="row">
						<div class="col-2">주관 기관명</div>
						<div class="col-10">${dto.company2}</div>
					</div>
					<div class="row">
						<div class="col-2">후원/협찬 기관명</div>
						<div class="col-10">${dto.company3}</div>
					</div>
				</div>
				<div>
					<h5>시상 정보</h5>
					<div class="row">
						<div class="col-2">1등 시상금(원)</div>
						<div class="col-10">${dto.prizeTop}</div>
					</div>
					<div class="row">
						<div class="col-2">총 시상금 규모</div>
						<div class="col-10">
							<span class="badge badge-primary">${dto.prizeTotal}</span>
						</div>
					</div>
					<div class="row">
						<div class="col-2">특전</div>
						<div class="col-10">
							<c:forEach items="${dto.prizeBenefit}" var="item">
								<span class="badge badge-primary">${item}</span>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="card mb-4">
			<div class="card-body">
				${dto.description}
			</div>
		</div>
		<div>
			<a class="btn btn-outline-primary" href="${dto.website}">
				<i class="bi bi-globe"></i>
				웹사이트 바로가기
			</a>
		</div>
	</jsp:attribute>
</tags:layout>