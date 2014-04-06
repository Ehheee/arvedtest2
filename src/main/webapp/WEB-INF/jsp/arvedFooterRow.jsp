<%@ include file="include.jsp"%>

<tr id="arveRow${arve.id}"  data-type = ${type.identifier } class = "tableRow">
	<td >
	
	</td>
	<td >

			
	</td>
	<td >
	
		<span  data-name = "totalsummaIlmaKM" data-type = ${type.identifier } class = "summaIlmaKM" >
		${totalSummaIlmaKM}
		<c:set var = "totalSummaIlmaKM" value = "${null }"></c:set>
		</span>
	</td>
	<td >
	
		<span data-name = "totalsummaKM" data-type = ${type.identifier } class = "summaKM"  >
		${totalSummaKM }
		<c:set var = "totalSummaKM" value = "${null }"></c:set>
		</span>
	</td>
	
</tr>