<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ attribute name="update"%>
<tags:layout>
	<jsp:attribute name="content">
		<form method="post">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header justify-content-center">
						<h5 class="modal-title">${update ? '회원정보 수정' : '회원가입'}</h5>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label>아이디</label>
							<input class="form-control${errors == null ? null : errors.hasFieldErrors('username') ? ' is-invalid' : ' is-valid'}" type="text" name="username" value="${dto.username}">
							<c:if test="${errors != null}">
								<c:forEach items="${errors.getFieldErrors('username')}" var="error">
									<div class="invalid-feedback">${error.defaultMessage}</div>
								</c:forEach>
							</c:if>
						</div>
						<div class="form-group">
							<label>비밀번호</label>
							<input class="form-control${errors == null ? null : errors.hasFieldErrors('password') ? ' is-invalid' : ' is-valid'}" type="password" name="password" value="${dto.password}">
							<c:if test="${errors != null}">
								<c:forEach items="${errors.getFieldErrors('password')}" var="error">
									<div class="invalid-feedback">${error.defaultMessage}</div>
								</c:forEach>
							</c:if>
						</div>
						<div class="form-group">
							<label>이름</label>
							<input class="form-control${errors == null ? null : errors.hasFieldErrors('name') ? ' is-invalid' : ' is-valid'}" type="text" name="name" value="${dto.name}">
							<c:if test="${errors != null}">
								<c:forEach items="${errors.getFieldErrors('name')}" var="error">
									<div class="invalid-feedback">${error.defaultMessage}</div>
								</c:forEach>
							</c:if>
						</div>
						<div class="form-group">
							<label>연락처</label>
							<input class="form-control${errors == null ? null : errors.hasFieldErrors('phone') ? ' is-invalid' : ' is-valid'}" type="tel" name="phone" value="${dto.phone}">
							<c:if test="${errors != null}">
								<c:forEach items="${errors.getFieldErrors('phone')}" var="error">
									<div class="invalid-feedback">${error.defaultMessage}</div>
								</c:forEach>
							</c:if>
						</div>
						<div class="form-group">
							<label>소속 회사</label>
							<input class="form-control${errors == null ? null : errors.hasFieldErrors('company') ? ' is-invalid' : ' is-valid'}" type="text" name="company" value="${dto.company}">
							<c:if test="${errors != null}">
								<c:forEach items="${errors.getFieldErrors('company')}" var="error">
									<div class="invalid-feedback">${error.defaultMessage}</div>
								</c:forEach>
							</c:if>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-primary btn-lg btn-block" type="submit">${update ? '회원정보 수정' : '회원가입'}</button>
					</div>
				</div>
			</div>
		</form>
	</jsp:attribute>
</tags:layout>