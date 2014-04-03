<%@ include file="include.jsp"%>
<tr>
	<th>
		ArveNumber
	</th>
	<th>
		Kuupäev
	</th>
	<th>
		SummaIlmaKM
	</th>
	<th>
		SummaKM
	</th>
	<th>
		Objekt
	</th>
	
	<c:if test = "${type == 'MUUGI' }">
		<th>
			Müügimees
		</th>
		<th>
			Klient
		</th>	
	</c:if>
			
	<c:if test="${type == 'OSTU' }">
		<th>
			Tarnija
		</th>
	</c:if>
	
	<th>
		Tasutud
	</th>
	<th>
		Pdf
	</th>

</tr>