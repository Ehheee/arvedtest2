<%@ include file="include.jsp"%>
<form action="<c:url value='/muugiarved/single' /> " method="post">
	<table>
		<tr>
			<th>Klient</th>
			<th>Arvenumber</th>
			<th>Kuupäev</th>
			<th>SummaIlmaKM</th>
			<th>SummaKM</th>
			<th>Objekt</th>
			<th>Müügimees</th>
			<th>Tasutud</th>
			<th>Pdf</th>
		</tr>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<tr>
				<td><input type="text" name="klient" value="${muugiArve.klient}" /> <input type="hidden" name="id" value="${muugiArve.id }" /></td>
				<td><input type="text" name="arveNumber" class="arveNumber" value="${muugiArve.arveNumber }" /></td>
				<td><input type="text" name="kuuPaev" class="kuuPaev" value="<fmt:formatDate
						value='${muugiArve.kuuPaev}'
						pattern='dd/MM/yyyy'
					/>" /></td>
				<td><input type="text" name="summaIlmaKM" class="summa" value="${muugiArve.summaIlmaKM}" /></td>
				<td><input type="text" name="summaKM" class="summa" value="${muugiArve.summaKM}" /></td>
				<td><input type="text" name="objektid" value="<c:forEach items="${muugiArve.objektid}" var="objekt" ><c:out value="${objekt.nimi }, "></c:out></c:forEach>" /></td>
				<td><input type="text" name="muugiMees" value="${muugiArve.muugiMees }" /></td>
				<td><c:choose>
						<c:when test="${muugiArve.tasutud }">
							<input type="checkbox" name="tasutud" checked="checked" />
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="tasutud" />
						</c:otherwise>
					</c:choose>
				</td>
				<td><input type="text" name="pdfLocation" value="${muugiArve.pdfLocation }" readonly="readonly" /></td>
				<td><input type="submit" value="salvesta" /></td>

			</tr>
		</sec:authorize>
	</table>
</form>
<br />
<c:url value="/muugiarved/delete/${muugiArve.id}" var="deleteUrl" />
<a href="${deleteUrl}" >Kustuta</a>