<%@ include file="include.jsp"%>

<form class="filterForm">
	<!--  
		Html select based on which period is selected. Choose is used to define which one is selected.
	-->
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
	
	<!-- 
		Start date and end date. JS is used to recalculate those when period is selected.
	 -->
	<input type="text" id="filterStartDate" name="startDate" class="kuuPaev" value="<fmt:formatDate
						value='${filter.startDate}'
						pattern='dd/MM/yyyy'
					/>" />
					
	<input type="text" id="filterEndDate" name="endDate" class="kuuPaev" value="<fmt:formatDate
						value='${filter.endDate}'
						pattern='dd/MM/yyyy'
					/>" />
		
		
	<select name = "tasutud">
		<option value=	"null" 	${empty filter.tasutud ? 'selected="selected"' : ''}>Mõlemad</option>
		<option value = "false"	${(not filter.tasutud && not empty filter.tasutud) ? 'selected="selected"' : ''}>Ei</option>
		<option value = "true" 	${filter.tasutud ? 'selected="selected"' : ''}>Jah</option>
	
	</select>
					
					<!-- 
	<c:choose>
		<c:when test="${filter.tasutud }">
			<input type="checkbox" name="tasutud" checked="checked" />
		</c:when>
		<c:otherwise>
			<input type="checkbox" name="tasutud" />
		</c:otherwise>
	</c:choose>
	 -->
	<input type="submit" value="saada" />
	
</form>