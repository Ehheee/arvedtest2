<%@ include file="include.jsp"%>

<form action="<c:url value='/ostuarved/single' /> " method="post">
	<table class="tabel">
		<tr>
			<th>Tarnija</th>
			<th>Arvenumber</th>
			<th>Kuupäev</th>
			<th>SummaIlmaKM</th>
			<th>SummaKM</th>
			<th>Objekt</th>
			<th>Tasutud</th>
			<th>Pdf</th>
		</tr>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<tr>

				<td><input type="text" name="tarnija" value="${ostuArve.tarnija }" /> <input type="hidden" name="id" value="${ostuArve.id }" /></td>
				<td><input type="text" name="arveNumber" class="arveNumber" value="${ostuArve.arveNumber }" /></td>
				<td><input type="text" name="kuuPaev" class="kuuPaev" value="<fmt:formatDate
						value="${ostuArve.kuuPaev}"
						pattern="dd/MM/yyyy"
					/>" /></td>
				<td><input type="text" name="summaIlmaKM" class="summa" value="${ostuArve.summaIlmaKM }" /></td>
				<td><input type="text" name="summaKM" class="summa" value="${ostuArve.summaKM }" /></td>
				<td><input type="text" name="objektid" value="<c:forEach items="${ostuArve.objektid}" var="objekt" ><c:out value="${objekt.nimi }, "></c:out></c:forEach>" /></td>
				<td><c:choose>
						<c:when test="${ostuArve.tasutud }">
							<input type="checkbox" name="tasutud" checked="checked" />
						</c:when>
						<c:otherwise>
							<input type="checkbox" name="tasutud" />
						</c:otherwise>
					</c:choose>
				</td>
				<td><input type="text" name="pdfLocation" value="${ostuArve.pdfLocation }" readonly="readonly" />
				</td>
				<td><input type="submit" value="salvesta" />
				</td>


			</tr>
		</sec:authorize>
	</table>
</form>
