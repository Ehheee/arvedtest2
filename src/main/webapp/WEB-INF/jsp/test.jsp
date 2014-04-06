<%@ include file="include.jsp"%>

<c:if test="${empty test }">
<c:set scope = "request" var = "test" value = "5"></c:set>
</c:if>
<c:if test="${test gt 1 }">
	${test }
	<c:set scope = "request" var = "test" value = "${test - 1 }"></c:set>
	<jsp:include page="test.jsp"></jsp:include>
	
	
	 
</c:if>