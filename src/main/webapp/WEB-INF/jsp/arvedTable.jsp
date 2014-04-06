<%@ include file="include.jsp"%>

<table id = "${type.identifier}table" class = "${isHiddenTable }">
			
		<sec:authorize access="hasRole('ROLE_USER')">
			<h3>${type.description }arved:</h3>
			<jsp:include page="arvedHeader.jsp"></jsp:include>
		</sec:authorize>
		<c:forEach var = "arve" items = "${arved[type]}">
				
			<c:set var = "arve" value = "${arve }" scope = "request"></c:set>
			<c:set scope = "request" var = "totalSummaKM" value = "${arve.summaKM + totalSummaKM }" />
			<c:set scope = "request" var = "totalSummaIlmaKM" value = "${arve.summaIlmaKM + totalSummaIlmaKM }" />
			<jsp:include page="arveRow.jsp"></jsp:include>
		</c:forEach>
				
		
	<jsp:include page = "arvedFooterRow.jsp"></jsp:include>
		
		
</table>