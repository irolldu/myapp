<%@ tag language="java" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ attribute name="update"%>
<tags:layout>
	<jsp:attribute name="css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/summernote/dist/summernote-bs4.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/post/createUpdate.css">
	</jsp:attribute>
	<jsp:attribute name="content">
		<form name="post" method="post" data-image="${pageContext.request.contextPath}/images" novalidate>
			<fieldset class="mb-4">
				<legend>활동 개요</legend>
				<div class="form-group">
					<input class="form-control${errors == null ? null : errors.hasFieldErrors('title') ? ' is-invalid' : ' is-valid'}" type="text" name="title" placeholder="제목" value="${dto.title}">
					<c:if test="${errors != null}">
						<c:forEach items="${errors.getFieldErrors('title')}" var="error">
							<div class="invalid-feedback">${error.defaultMessage}</div>
						</c:forEach>
					</c:if>
				</div>
				<div class="form-group">
					<input class="form-control${errors == null ? null : errors.hasFieldErrors('website') ? ' is-invalid' : ' is-valid'}" type="url" name="website" placeholder="웹사이트" value="${dto.website}">
					<c:if test="${errors != null}">
						<c:forEach items="${errors.getFieldErrors('website')}" var="error">
							<div class="invalid-feedback">${error.defaultMessage}</div>
						</c:forEach>
					</c:if>
				</div>
				<div class="form-group">
					<label>분야</label>
					<div class="card${errors == null ? null : errors.hasFieldErrors('category') ? ' is-invalid' : ' is-valid'}">
						<div class="card-body">
							<div class="row">
								<c:forEach items="${postFilterList.getCategoryItems()}" var="item">
									<div class="col-3">
										<label class="form-check m-0">
											<input class="form-check-input" type="checkbox" name="category" value="${item.dataID}"${dto.category.contains(item.dataID) ? ' checked' : null}>
											<span>${item.name}</span>
										</label>
									</div>								
								</c:forEach>
							</div>
						</div>
					</div>
					<c:if test="${errors != null}">
						<c:forEach items="${errors.getFieldErrors('category')}" var="error">
							<div class="invalid-feedback">${error.defaultMessage}</div>
						</c:forEach>
					</c:if>
				</div>
				<div class="form-group">
					<label class="${errors == null ? null : errors.hasFieldErrors('startDate') || errors.hasFieldErrors('endDate') ? 'is-invalid' : 'is-valid'}">접수 기간</label>
					<div class="row mb-3">
						<div class="col">
							<input class="form-control${errors == null ? null : errors.hasFieldErrors('startDate') ? ' is-invalid' : ' is-valid'}" type="date" name="startDate" value="${dto.startDate}">
						</div>
						<label class="col-auto col-form-label">부터</label>
					</div>
					<div class="row">
						<div class="col">
							<input class="form-control${errors == null ? null : errors.hasFieldErrors('endDate') ? ' is-invalid' : ' is-valid'}" type="date" name="endDate" value="${dto.endDate}">
						</div>
						<label class="col-auto col-form-label">까지</label>
					</div>
					<c:if test="${errors != null}">
						<c:forEach items="${errors.getFieldErrors('startDate')}" var="error">
							<div class="invalid-feedback">${error.defaultMessage}</div>
						</c:forEach>
					</c:if>
					<c:if test="${errors != null}">
						<c:forEach items="${errors.getFieldErrors('endDate')}" var="error">
							<div class="invalid-feedback">${error.defaultMessage}</div>
						</c:forEach>
					</c:if>
				</div>
			</fieldset>
			<fieldset class="mb-4">
				<legend>기관 정보</legend>
				<div class="form-group">
					<input class="form-control${errors == null ? null : errors.hasFieldErrors('company') ? ' is-invalid' : ' is-valid'}" type="text" name="company" placeholder="주최 기관명" value="${dto.company}">
					<c:if test="${errors != null}">
						<c:forEach items="${errors.getFieldErrors('company')}" var="error">
							<div class="invalid-feedback">${error.defaultMessage}</div>
						</c:forEach>
					</c:if>
				</div>
				<div class="form-group">
					<label>주최 구분</label>
					<div class="card${errors == null ? null : errors.hasFieldErrors('companyType') ? ' is-invalid' : ' is-valid'}">
						<div class="card-body">
							<div class="row">
								<c:forEach items="${postFilterList.getCompanyTypeItems()}" var="item">
									<div class="col-3">
										<label class="form-check m-0">
											<input class="form-check-input" type="radio" name="companyType" value="${item.dataID}"${dto.companyType == item.dataID ? ' checked' : null}>
											${item.name}
										</label>
									</div>								
								</c:forEach>
							</div>
						</div>
					</div>
					<c:if test="${errors != null}">
						<c:forEach items="${errors.getFieldErrors('companyType')}" var="error">
							<div class="invalid-feedback">${error.defaultMessage}</div>
						</c:forEach>
					</c:if>
				</div>
				<div class="form-group">
					<input class="form-control${errors == null ? null : errors.hasFieldErrors('company2') ? ' is-invalid' : ' is-valid'}" type="text" name="company2" placeholder="주관 기관명" value="${dto.company2}">
					<c:if test="${errors != null}">
						<c:forEach items="${errors.getFieldErrors('company2')}" var="error">
							<div class="invalid-feedback">${error.defaultMessage}</div>
						</c:forEach>
					</c:if>
				</div>
				<div class="form-group">
					<input class="form-control${errors == null ? null : errors.hasFieldErrors('company3') ? ' is-invalid' : ' is-valid'}" type="text" name="company3" placeholder="후원/협찬 기관명" value="${dto.company3}">
					<c:if test="${errors != null}">
						<c:forEach items="${errors.getFieldErrors('company3')}" var="error">
							<div class="invalid-feedback">${error.defaultMessage}</div>
						</c:forEach>
					</c:if>
				</div>
			</fieldset>
			<fieldset class="mb-4">
				<legend>시상 정보</legend>
				<div class="form-group">
					<input class="form-control${errors == null ? null : errors.hasFieldErrors('prizeTop') ? ' is-invalid' : ' is-valid'}" type="number" name="prizeTop" placeholder="1등 시상금 (원)" value="${dto.prizeTop}">
					<c:if test="${errors != null}">
						<c:forEach items="${errors.getFieldErrors('prizeTop')}" var="error">
							<div class="invalid-feedback">${error.defaultMessage}</div>
						</c:forEach>
					</c:if>
				</div>
				<div class="form-group">
					<label>총 시상금 규모</label>
					<div class="card${errors == null ? null : errors.hasFieldErrors('prizeTotal') ? ' is-invalid' : ' is-valid'}">
						<div class="card-body">
							<div class="row">
								<c:forEach items="${postFilterList.getPrizeTotalItems()}" var="item">
									<div class="col-3">
										<label class="form-check m-0">
											<input class="form-check-input" type="radio" name="prizeTotal" value="${item.dataID}"${dto.prizeTotal == item.dataID ? ' checked' : null}>
											${item.name}
										</label>
									</div>								
								</c:forEach>
							</div>
						</div>
					</div>
					<c:if test="${errors != null}">
						<c:forEach items="${errors.getFieldErrors('prizeTotal')}" var="error">
							<div class="invalid-feedback">${error.defaultMessage}</div>
						</c:forEach>
					</c:if>
				</div>
				<div class="form-group">
					<label>특전</label>
					<div class="card${errors == null ? null : errors.hasFieldErrors('prizeBenefit') ? ' is-invalid' : ' is-valid'}">
						<div class="card-body">
							<div class="row">
								<c:forEach items="${postFilterList.getPrizeBenefitItems()}" var="item">
									<div class="col-3">
										<label class="form-check m-0">
											<input class="form-check-input" type="checkbox" name="prizeBenefit" value="${item.dataID}"${dto.prizeBenefit.contains(item.dataID) ? ' checked' : null}>
											${item.name}
										</label>
									</div>								
								</c:forEach>
							</div>
						</div>
					</div>
					<c:if test="${errors != null}">
						<c:forEach items="${errors.getFieldErrors('prizeBenefit')}" var="error">
							<div class="invalid-feedback">${error.defaultMessage}</div>
						</c:forEach>
					</c:if>
				</div>
			</fieldset>
			<fieldset class="mb-4">
				<legend>상세 내용</legend>
				<div class="form-group">
					<textarea class="${errors == null ? null : errors.hasFieldErrors('description') ? 'is-invalid' : 'is-valid'}" name="description">${dto.description}</textarea>
					<c:if test="${errors != null}">
						<c:forEach items="${errors.getFieldErrors('description')}" var="error">
							<div class="invalid-feedback">${error.defaultMessage}</div>
						</c:forEach>
					</c:if>
				</div>
			</fieldset>
			<fieldset class="mb-4">
				<legend>이미지</legend>
				<div class="form-group">
					<div class="row no-gutters">
						<div class="col mr-2">
							<input class="form-control${errors == null ? null : errors.hasFieldErrors('image') ? ' is-invalid' : ' is-valid'}" type="text" name="image" value="${dto.image}" readonly>
							<c:if test="${errors != null}">
								<c:forEach items="${errors.getFieldErrors('image')}" var="error">
									<div class="invalid-feedback">${error.defaultMessage}</div>
								</c:forEach>
							</c:if>
						</div>
						<div class="col-auto">
							<label class="btn btn-primary">
								<input class="d-none" type="file" accept="image/*" id="image">
								파일 선택
							</label>
						</div>
					</div>
				</div>
			</fieldset>
			<button class="btn btn-primary btn-lg btn-block" type="submit">${update ? '수정' : '등록'}</button>
		</form>
	</jsp:attribute>
	<jsp:attribute name="script">
		<script src="${pageContext.request.contextPath}/webjars/lodash/lodash.min.js"></script>
		<script src="${pageContext.request.contextPath}/webjars/summernote/dist/summernote-bs4.min.js"></script>
		<script src="${pageContext.request.contextPath}/webjars/summernote/dist/lang/summernote-ko-KR.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/post/createUpdate.js"></script>
	</jsp:attribute>
</tags:layout>