<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<tags:layout>
	<jsp:attribute name="content">
		<form method="post">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header justify-content-center">
						<h5 class="modal-title">로그인</h5>
					</div>
					<div class="modal-body">
						<c:if test="${error != null}">
							<div class="form-group">
								<div class="alert alert-danger">
									${error}
								</div>
							</div>
						</c:if>
						<div class="form-group">
							<label>아이디</label>
							<input class="form-control" type="text" name="username">
						</div>
						<div class="form-group">
							<label>비밀번호</label>
							<input class="form-control" type="password" name="password">
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-primary btn-lg btn-block" type="submit">로그인</button>
					</div>
				</div>
			</div>
		</form>
	</jsp:attribute>
</tags:layout>