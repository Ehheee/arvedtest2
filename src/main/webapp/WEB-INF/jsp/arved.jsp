<%@ include file="include.jsp"%>
<c:set var = "editable" value = "false"></c:set>


<c:if test="${not empty type }">
	<c:set var = "arvedSize" value = "${fn:length(arved[type]) }"></c:set>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<c:set var = "arve" value = "${null }"></c:set>
			<h5>Sisesta uus ${type}arve:</h5>
			<jsp:include page="arve.jsp"></jsp:include>
		<c:set var = "editable" value = "true" scope = "request"></c:set>
	</sec:authorize>
	<c:if test="${arvedSize gt 0 }">
		<h3>${type.description }:</h3>
		
		
		<table id = "${type.identifier}table">
			
				<sec:authorize access="hasRole('ROLE_USER')">	
					<jsp:include page="arvedHeader.jsp"></jsp:include>
				</sec:authorize>
				<c:forEach var = "arve" items = "${arved[type]}">
					
					<c:set var = "arve" value = "${arve }" scope = "request"></c:set>
					<jsp:include page="arveRow.jsp"></jsp:include>
				</c:forEach>
				
			
		</table>
		<c:if test = "${filter.page gt 0}">
			<c:url value = "" var="previousPage">
				<c:param name="page" value = "${filter.page -1}"></c:param>
			</c:url>
			<a href = "${previousPage }" >Eelmised</a>
		</c:if>
		<c:if test = "${arvedSize gt filter.pageSize }" >
			
			
			<c:url value = "" var="nextPage">
				<c:param name="page" value = "${filter.page +1}"></c:param>
			</c:url>
			<a href = "${nextPage }" >Järgmised</a>
		</c:if>
	</c:if>
</c:if>
