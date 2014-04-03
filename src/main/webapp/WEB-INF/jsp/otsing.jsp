<%@ include file='include.jsp'%>
<c:url value="/otsing" var="otsingUrl"></c:url>
<form method="GET" action="${otsingUrl }">
Alguskuupäev
	<input type="text" 
			name="startDate"
			class="kuuPaev"
			id="${param.startDateId}"
			value="<fmt:formatDate
						value='${sessionScope.startDate}'
						pattern='dd/MM/yyyy'
					/>"
	/> 
Lõppkuupäev			
	<input type="text" 
			name="endDate"
			class="kuuPaev"
			id="${param.endDateId}"
			value="<fmt:formatDate
						value='${sessionScope.endDate}'
						pattern='dd/MM/yyyy'
					/>"
	/>
	
	<input type="text"
			name="searchPhrase"
			class="searchPhrase ${arveType }"
	/>
	
	<input type="hidden"
			name="searchWhat"
			value="${param.searchWhat}"
	/>
	
	<input type="submit" value="Otsi" />

</form>