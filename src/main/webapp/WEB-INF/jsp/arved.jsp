<%@ include file="include.jsp"%>
<c:set var = "editable" value = "false"></c:set>


<c:if test="${not empty type }">
	
	<c:set var = "arvedSize" value = "${fn:length(arved[type]) }"></c:set>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<c:set var = "arve" value = "${null }"></c:set>						
			<h4>${type.description }arved:</h4>
			<h5>Sisesta uus ${type.description}arve:</h5>
			<jsp:include page="arve.jsp"></jsp:include>
			<c:if test="${arvedSize lt 1 }">
				<c:set scope = "request" var = "isHiddenTable" value = "hiddenTable" />
				<jsp:include page="arvedTable.jsp" ></jsp:include>
				<c:set scope = "request" var = "isHiddenTable" value = "${null }" />
			</c:if>
		<c:set var = "editable" value = "true" scope = "request"></c:set>
	</sec:authorize>
	<c:if test="${arvedSize gt 0 }">
		<c:set scope = "request" var = "totalSummaKM" value = "0" />
		<c:set scope = "request" var = "totalSummaIlmaKM" value = "0" />
		
		
		
		<jsp:include page="arvedTable.jsp"></jsp:include>
		
		<c:if test = "${filter.page gt 0}">
			<c:url value = "" var="previousPage">
				<c:param name="page" value = "${filter.page -1}"></c:param>
			</c:url>
			<a href = "${previousPage }" >Eelmised</a>
		</c:if>
		<c:if test = "${arvedSize gt filter.pageSize }" >
			
			
			<c:url value = "${thisUrl + queryString }" var="nextPage">
				<c:param name="page" value = "${filter.page +1}"></c:param>
			</c:url>
			<a href = "${nextPage }" >Järgmised</a>
		</c:if>
	</c:if>
</c:if>
