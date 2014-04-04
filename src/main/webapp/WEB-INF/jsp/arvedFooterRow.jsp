<%@ include file="include.jsp"%>

<tr id="arveRow${arve.id}"  data-type = ${arve.type.identifier } class = "tableRow">
	<td >
	
	</td>
	<td >

			
	</td>
	<td >
	
		<span  data-name = "totalsummaIlmaKM" data-type = ${arve.type.identifier } class = "summaIlmaKM" >
		${totalSummaIlmaKM}
		</span>
	</td>
	<td >
	
		<span data-name = "totalsummaKM" data-type = ${arve.type.identifier } class = "summaKM"  >
		${totalSummaKM }
		</span>
	</td>
	
</tr>