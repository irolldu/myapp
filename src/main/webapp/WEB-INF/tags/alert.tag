<%@ tag language="java" pageEncoding="EUC-KR"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<%@ attribute name="returnUrl"%>
<%@ attribute name="message"%>
<tags:layout>
	<jsp:attribute name="meta">
		<meta http-equiv="refresh" content="0;url=${returnUrl}">
	</jsp:attribute>
	<jsp:attribute name="script">
		<script>
			alert("${message}");
		</script>
	</jsp:attribute>
</tags:layout>