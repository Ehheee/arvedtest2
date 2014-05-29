<%@ include file="include.jsp"%>

<form action="<c:url value='/' /> " class="filterForm">
	<select name= "period">
		<c:forEach var = "period" items = "${sessionScope.periods }">
			<c:choose>
				<c:when test="${filter.period == period}">
					<option selected = "selected" value = "${period.identifier }">${period.description }</option>
				</c:when>
				<c:otherwise>
					<option value = "${period.identifier }">${period.description }</option>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</select>
	
	<input type="submit" value="saada" />
	
</form>