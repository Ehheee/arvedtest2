<%@ include file="include.jsp"%>
<tr>
	<th>
		ArveNumber
	</th>
	<th>
		Kuup�ev
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
			M��gimees
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