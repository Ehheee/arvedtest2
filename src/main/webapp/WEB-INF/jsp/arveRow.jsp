<%@ include file="include.jsp"%>

<tr id="arveRow${arve.id}"  data-type = "${arve.type.identifier }" class = "tableRow">
	<td >
		<div data-id = "${arve.id }" data-name = "arveNumber" class = "editable arveNumber"  >
		${arve.arveNumber}
		</div>
	</td>
	<td >
	
		<input data-id = "${arve.id }" data-name = "kuuPaev" type="text" id="${type.identifier}kuuPaev${arve.id}" name="kuuPaev" class="kuuPaev" value="<fmt:formatDate
						value='${arve.kuuPaev}'
						pattern='dd/MM/yyyy'
					/>" />
			
	</td>
	<td >
	
		<div data-id = "${arve.id }" data-type = ${arve.type.identifier } data-name = "summaIlmaKM" class = "editable summaIlmaKM" >
		${arve.summaIlmaKM}
		</div>
	</td>
	<td >
		<div data-id = "${arve.id }" data-type = ${arve.type.identifier } data-name = "summaKM" class = "editable summaKM"  >
		${arve.summaKM }
		</div>
	</td>
	
	
	<td >
		<div data-id = "${arve.id }" data-name = "objekt" class = "editable objekt"  >
		${arve.objekt }
		</div>
	</td>
	
	<c:if test = "${arve.type == 'MUUGI' }">
		<td >
			<div  data-id = "${arve.id }" data-name = "muugiMees" class = "editable muugiMees"  >
			${arve.muugiMees }
			</div>
		</td>
		<td >
			<div data-id = "${arve.id }" data-name = "klient" class = "editable klient" >
			${arve.klient }
			</div>
		</td>		
	</c:if>
			
	<c:if test="${arve.type == 'OSTU' }">
		<td >
			<div data-id = "${arve.id }" data-name = "tarnija" class = "editable tarnija"  >
			${arve.tarnija }
			</div>
		</td>
	</c:if>
	<td class = "tasutud">
		<c:choose>
			<c:when test="${arve.tasutud }">
				<input data-id = "${arve.id }" data-name = "tasutud" class = "editable" type="checkbox" name="tasutud" checked="checked" />
			</c:when>
			<c:otherwise>
				<input data-id = "${arve.id }" data-name = "tasutud" class = "editable" type="checkbox" name="tasutud" />
			</c:otherwise>
		</c:choose>
	</td>
	<td>
		<a href="<c:url value='${arve.pdfLocation }' />">Vaata pdf-i</a>
	</td>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<td>
			<a href = "<c:url value = '/arve/${arve.id }' />" > Muuda</a>
		</td>
		<td>
			<a class = "deleteArve" href = "<c:url value = '/delete/${arve.id }' />" >Kustuta</a>
			
		</td>
	</sec:authorize>
	
</tr>