<%@ include file="include.jsp"%>
<tr>
	<th>
		<c:url value = "" var = "orderUrl">
			<c:param name="orderBy" value = "arveNumber" ></c:param>
		</c:url>
		<a href = "${orderUrl }">
		ArveNumber
		</a>
	</th>
	<th>
		<c:url value = "" var = "orderUrl">
			<c:param name="orderBy" value = "kuuPaev" ></c:param>
		</c:url>
		<a href = "${orderUrl }">
		Kuupäev
		</a>
	</th>
	<th>
		<c:url value = "" var = "orderUrl">
			<c:param name="orderBy" value = "summaIlmaKM" ></c:param>
		</c:url>
		<a href = "${orderUrl }">
		SummaIlmaKM
		</a>
	</th>
	<th>
		<c:url value = "" var = "orderUrl">
			<c:param name="orderBy" value = "summaKM" ></c:param>
		</c:url>
		<a href = "${orderUrl }">
		SummaKM
		</a>
	</th>
	<th>
		<c:url value = "" var = "orderUrl">
			<c:param name="orderBy" value = "objekt" ></c:param>
		</c:url>
		<a href = "${orderUrl }">
		Objekt
		</a>
	</th>
	
	<c:if test = "${type == 'MUUGI' }">
		<th>
			<c:url value = "" var = "orderUrl">
				<c:param name="orderBy" value = "muugiMees" ></c:param>
			</c:url>
			<a href = "${orderUrl }">
			Müügimees
			</a>
		</th>
		<th>
			<c:url value = "" var = "orderUrl">
				<c:param name="orderBy" value = "klient" ></c:param>
			</c:url>
			<a href = "${orderUrl }">
			Klient
			</a>
		</th>	
	</c:if>
			
	<c:if test="${type == 'OSTU' }">
		<th>
			<c:url value = "" var = "orderUrl">
				<c:param name="orderBy" value = "tarnija" ></c:param>
			</c:url>
			<a href = "${orderUrl }">
			Tarnija
			</a>
		</th>
	</c:if>
	
	<th>
		<c:url value = "" var = "orderUrl">
			<c:param name="orderBy" value = "tasutud" ></c:param>
		</c:url>
		<a href = "${orderUrl }">
		Tasutud
		</a>
	</th>
	<th>
		Pdf
	</th>

</tr>