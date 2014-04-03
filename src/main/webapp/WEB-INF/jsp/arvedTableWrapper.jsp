<%@ include file="include.jsp"%>
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<jsp:include page="${requestType + 'Input'}" />
</sec:authorize>
<jsp:include page="tableHeaders.jsp" />
<jsp:include page="${requestType + 'Table'}" />