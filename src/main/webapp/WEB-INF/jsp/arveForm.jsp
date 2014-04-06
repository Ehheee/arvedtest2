<%@ include file="include.jsp"%>
<c:choose>
	<c:when test="${not empty arve.objekt }">
		<c:set var = "objekt" value = "${arve.objekt }" />
	</c:when>
	<c:when test="${not empty filter.objekt }">
		<c:set var = "objekt" value = "${filter.objekt }" />
	</c:when>
</c:choose>
	<input type = "hidden" name = "arvedType" value = "${type.identifier }" />
	<input type = "hidden" name = "id" value = "${arve.id }" />
	<td>
		<input type="text" name="arveNumber" class="arveNumber" value = "${arve.arveNumber }"/>
	</td>
	<td>
		<input type="text" id="${type}kuuPaev${id}" name="kuuPaev" class="kuuPaev" value="<fmt:formatDate
						value='${arve.kuuPaev}'
						pattern='dd/MM/yyyy'
					/>" />
	</td>
	<td>
		<input type="text" name="summaIlmaKM" class="summaIlmaKM" value ="${arve.summaIlmaKM }"/>
	</td>
	<td>
		<input type="text" name="summaKM" class="summaKM" value = "${arve.summaKM }" />
	</td>
	<td>
		<input type="text" name="objekt"  class = "objekt" value = "${objekt }" />
	</td>
	
	<c:if test = "${type == 'MUUGI' }">
		<td>
			<input type = "text" name = "muugiMees" class = "muugiMees" value = "${arve.muugiMees }" />
		</td>
		<td>
			<input type="text" name="klient" class = "klient" value = "${arve.klient }" />
		</td>
	</c:if>
	
	<c:if test="${type == 'OSTU' }">
		<td>
			<input type="text" name="tarnija" class = "tarnija" value = "${arve.tarnija }"/>
		</td>
	</c:if>
	
	<td class = "tasutud">
		<c:choose>
			<c:when test="${arve.tasutud }">
				<input type="checkbox" name="tasutud" checked="checked" />
			</c:when>
			<c:otherwise>
				<input type="checkbox" name="tasutud" />
			</c:otherwise>
		</c:choose>
	</td>
	<td>
		<input type="file" name="pdf" />
	</td>
	<td>
		<input type="submit" value="salvesta" />
	</td>
	
