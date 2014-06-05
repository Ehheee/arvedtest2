<%@ include file="include.jsp"%>
<jsp:include page="header.jsp"></jsp:include>

<c:choose>
	<c:when test = "${not empty arved }">
		<jsp:include page="filter.jsp"></jsp:include>
		
		<c:if test="${not empty filter.objekt }">
			
			<h3>${filter.objekt }:</h3>
			<c:if test = "${not empty objektKasum }">
				Objekti kasum: ${objektKasum}
			</c:if>
			<br/>
			<span id = "objektKasum">
				
			</span>
		</c:if>
		
		<c:forEach var = "type" items = "${includedTypes }">
			<c:set var= "type" value = "${type}" scope = "request"></c:set>
			<jsp:include page = "arved.jsp"></jsp:include>
		</c:forEach>
		
	</c:when>
	<c:when test = "${not empty user }">
		<jsp:include page="user.jsp"></jsp:include>	
	</c:when>
	<c:when test = "${not empty arve }">
		<jsp:include page="arve.jsp"></jsp:include>
	</c:when>
	<c:when test = "${not empty objektid }" >
		<jsp:include page = "objektid.jsp" ></jsp:include>
	</c:when>
	<c:when test="${not empty jspContent }">
		<c:forEach var = "type" items = "${includedTypes }">
			
			<jsp:include page="${jspContent }"></jsp:include>
		</c:forEach>
	</c:when>

</c:choose>
<jsp:include page="footer.jsp"></jsp:include>