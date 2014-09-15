<%@ include file="include.jsp"%>



<sec:authorize access="hasAnyRole()">
	hellloo
</sec:authorize>
<c:forEach items="${testMap }" var="entry">
${entry.key } ---- ${entry.value }
</c:forEach>
