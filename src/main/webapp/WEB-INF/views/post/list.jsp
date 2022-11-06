<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<tags:layout>
	<jsp:attribute name="content">
		<form name="list">
			<div class="navbar navbar-expand navbar-light">
				<div class="navbar-nav">
					<label class="nav-link${dto.filterType == 'CATEGORY' ? ' active' : null}" role="button">
						<input class="d-none" type="radio" name="filterType" value="CATEGORY"${dto.filterType == 'CATEGORY' ? ' checked' : null}>
						분야
					</label>
					<label class="nav-link${dto.filterType == 'COMPANYTYPE' ? ' active' : null}" role="button">
						<input class="d-none" type="radio" name="filterType" value="COMPANYTYPE"${dto.filterType == 'COMPANYTYPE' ? ' checked' : null}>
						주최 구분
					</label>
					<label class="nav-link${dto.filterType == 'PRIZETOTAL' ? ' active' : null}" role="button">
						<input class="d-none" type="radio" name="filterType" value="PRIZETOTAL"${dto.filterType == 'PRIZETOTAL' ? ' checked' : null}>
						총 시상금 규모
					</label>
					<label class="nav-link${dto.filterType == 'PRIZEBENEFIT' ? ' active' : null}" role="button">
						<input class="d-none" type="radio" name="filterType" value="PRIZEBENEFIT"${dto.filterType == 'PRIZEBENEFIT' ? ' checked' : null}>
						특전
					</label>
				</div>
			</div>
			<div class="mb-4">
				<div class="${dto.filterType == 'CATEGORY' ? 'd-block' : 'd-none'}">
					<c:forEach items="${postFilterList.getCategoryItems()}" var="item">
						<label class="btn btn-outline-primary btn-sm rounded-pill${dto.filterBy_categoryIDs.contains(item.dataID) ? ' active' : null}">
							<input id="${item.hashCode()}" class="d-none" type="checkbox" name="filterBy_categoryIDs" value="${item.dataID}"${dto.filterBy_categoryIDs.contains(item.dataID) ? ' checked' : null}>
							${item.name}
						</label>
					</c:forEach>
				</div>
				<div class="${dto.filterType == 'COMPANYTYPE' ? 'd-block' : 'd-none'}">
					<c:forEach items="${postFilterList.getCompanyTypeItems()}" var="item">
						<label class="btn btn-outline-primary btn-sm rounded-pill${dto.filterBy_companyTypeIDs.contains(item.dataID) ? ' active' : null}">
							<input id="${item.hashCode()}" class="d-none" type="checkbox" name="filterBy_companyTypeIDs" value="${item.dataID}"${dto.filterBy_companyTypeIDs.contains(item.dataID) ? ' checked' : null}>
							${item.name}
						</label>
					</c:forEach>
				</div>
				<div class="${dto.filterType == 'PRIZETOTAL' ? 'd-block' : 'd-none'}">
					<c:forEach items="${postFilterList.getPrizeTotalItems()}" var="item">
						<label class="btn btn-outline-primary btn-sm rounded-pill${dto.filterBy_prizeTotalIDs.contains(item.dataID) ? ' active' : null}">
							<input id="${item.hashCode()}" class="d-none" type="checkbox" name="filterBy_prizeTotalIDs" value="${item.dataID}"${dto.filterBy_prizeTotalIDs.contains(item.dataID) ? ' checked' : null}>
							${item.name}
						</label>
					</c:forEach>
				</div>
				<div class="${dto.filterType == 'PRIZEBENEFIT' ? 'd-block' : 'd-none'}">
					<c:forEach items="${postFilterList.getPrizeBenefitItems()}" var="item">
						<label class="btn btn-outline-primary btn-sm rounded-pill${dto.filterBy_prizeBenefitIDs.contains(item.dataID) ? ' active' : null}">
							<input id="${item.hashCode()}" class="d-none" type="checkbox" name="filterBy_prizeBenefitIDs" value="${item.dataID}"${dto.filterBy_prizeBenefitIDs.contains(item.dataID) ? ' checked' : null}>
							${item.name}
						</label>
					</c:forEach>
				</div>
			</div>
			<c:if test="${!dto.isFilterByEmpty()}">
				<div class="row mb-4">
					<div class="col">
						<c:forEach items="${postFilterList.getCategoryItems()}" var="item">
							<c:if test="${dto.filterBy_categoryIDs.contains(item.dataID)}">
								<label for="${item.hashCode()}" class="btn btn-primary btn-sm rounded-pill">
									${item.name} &times;
								</label>
							</c:if>
						</c:forEach>
						<c:forEach items="${postFilterList.getCompanyTypeItems()}" var="item">
							<c:if test="${dto.filterBy_companyTypeIDs.contains(item.dataID)}">
								<label for="${item.hashCode()}" class="btn btn-primary btn-sm rounded-pill">
									${item.name} &times;
								</label>
							</c:if>
						</c:forEach>
						<c:forEach items="${postFilterList.getPrizeTotalItems()}" var="item">
							<c:if test="${dto.filterBy_prizeTotalIDs.contains(item.dataID)}">
								<label for="${item.hashCode()}" class="btn btn-primary btn-sm rounded-pill">
									${item.name} &times;
								</label>
							</c:if>
						</c:forEach>
						<c:forEach items="${postFilterList.getPrizeBenefitItems()}" var="item">
							<c:if test="${dto.filterBy_prizeBenefitIDs.contains(item.dataID)}">
								<label for="${item.hashCode()}" class="btn btn-primary btn-sm rounded-pill">
									${item.name} &times;
								</label>
							</c:if>
						</c:forEach>
					</div>
					<a class="col-auto col-form-label" href="${pageContext.request.contextPath}/post/list">
						<i class="bi bi-arrow-counterclockwise"></i>
					</a>
				</div>
			</c:if>
			<div class="row justify-content-between mb-4">
				<label class="col-auto col-form-label">검색결과 ${dto.totalCount}건</label>
				<div class="col-auto">
					<select class="custom-select" name="orderBy_field">
						<option value="ID"${dto.orderBy_field == 'ID' ? ' selected' : null}>최신순</option>
						<option value="ENDDATE"${dto.orderBy_field == 'ENDDATE' ? ' selected' : null}>마감순</option>
					</select>
					<input class="d-none" name="orderBy_direction" value="${dto.orderBy_direction}">
				</div>
			</div>
			<c:choose>
				<c:when test="${dto.totalCount == 0}">
					<div class="row mb-4">
						<div class="col-12">
							검색 결과가 없습니다.
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="row mb-4">
						<c:forEach items="${dto.posts}" var="item">
							<a class="col-3 mb-3 text-decoration-none" href="${item.link}">
								<div class="card h-100">
									<img class="card-img-top" src="${item.image}">
									<div class="card-body">
										<h5 class="card-title text-dark font-weight-bold">${item.title}</h5>
										<p class="card-text text-dark">${item.company}</p>
									</div>
									<div class="card-footer">
										<small class="text-muted">${item.dday}</small>
									</div>
								</div>
							</a>
						</c:forEach>
					</div>
					<ul class="pagination justify-content-center mb-4">
						<c:forEach begin="${dto.page % dto.numberOfPages == 0 ? Math.floor(dto.page / dto.numberOfPages - 1) * numberOfPages : Math.floor(dto.page / dto.numberOfPages) * dto.numberOfPages}" end="${Math.ceil(dto.page / dto.numberOfPages) * dto.numberOfPages + 1}" var="var" varStatus="status">
							<c:if test="${status.first || status.last || var <= Math.ceil(dto.totalCount / dto.pageSize)}">
								<li class="page-item${dto.page == var ? ' active' : null}">
									<label class="page-link${var == 0 || var > Math.ceil(dto.totalCount / dto.pageSize) ? ' disabled' : null}">
										<input class="d-none" type="radio" name="page" value="${var}"${dto.page == var ? ' checked' : null}${var == 0 || var > Math.ceil(dto.totalCount / dto.pageSize) ? ' disabled' : null}>
										<c:choose>
											<c:when test="${status.first}">&lt;</c:when>
											<c:when test="${status.last}">&gt;</c:when>
											<c:otherwise>${var}</c:otherwise>
										</c:choose>
									</label>
								</li>
							</c:if>
						</c:forEach>
					</ul>
				</c:otherwise>
			</c:choose>
		</form>
	</jsp:attribute>
	<jsp:attribute name="script">
		<script src="${pageContext.request.contextPath}/resources/js/post/list.js"></script>
	</jsp:attribute>
</tags:layout>