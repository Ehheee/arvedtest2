<%@ include file="include.jsp"%>
<c:set var="summaKokkuKM" value="0" />
<c:set var="summaKokkuIlmaKM" value="0" />
<h4>Ostuarved</h4>
<form action="<c:url value='/ostuarved' /> " method="post" enctype="multipart/form-data">
	<table class="tabel">
		<tr>
			<th><c:url value="${thisUrl}" var="sort">
					<c:param name="ostuArveBy" value="tarnija"></c:param>
					<c:param name="orderType" value="asc"></c:param>
					<c:param name="orderOA" value="true"></c:param>
				</c:url> 
				<a href="${sort}">↑ </a> 
				Tarnija 
				<c:url value="${thisUrl}" var="sort">
					<c:param name="ostuArveBy" value="tarnija"></c:param>
					<c:param name="orderType" value="desc"></c:param>
					<c:param name="orderOA" value="true"></c:param>
				</c:url> 
				<a href="${sort}">↓</a>
			</th>
			<th><c:url value="${thisUrl}" var="sort">
					<c:param name="ostuArveBy" value="arveNumber"></c:param>
					<c:param name="orderType" value="asc"></c:param>
					<c:param name="orderOA" value="true"></c:param>
				</c:url> 
				<a href="${sort}">↑ </a> 
				Arvenumber 
				<c:url value="${thisUrl}" var="sort">
					<c:param name="ostuArveBy" value="arveNumber"></c:param>
					<c:param name="orderType" value="desc"></c:param>
					<c:param name="orderOA" value="true"></c:param>
				</c:url>
				<a href="${sort}">↓</a>
			</th>
			<th><c:url value="${thisUrl}" var="sort">
					<c:param name="ostuArveBy" value="kuuPaev"></c:param>
					<c:param name="orderType" value="asc"></c:param>
					<c:param name="orderOA" value="true"></c:param>
				</c:url> <a href="${sort}">↑ </a> 
				Kuupäev 
				<c:url value="${thisUrl}" var="sort">
					<c:param name="ostuArveBy" value="kuuPaev"></c:param>
					<c:param name="orderType" value="desc"></c:param>
					<c:param name="orderOA" value="true"></c:param>
				</c:url>
				<a href="${sort}">↓</a>
			</th>
			<th>
				<c:url value="${thisUrl}" var="sort">
					<c:param name="ostuArveBy" value="summaIlmaKM"></c:param>
					<c:param name="orderType" value="asc"></c:param>
					<c:param name="orderOA" value="true"></c:param>
				</c:url> <a href="${sort}">↑ </a> 
			Summa<br />IlmaKM
			<c:url value="${thisUrl}" var="sort">
				<c:param name="ostuArveBy" value="summaIlmaKM"></c:param>
				<c:param name="orderType" value="desc"></c:param>
				<c:param name="orderOA" value="true"></c:param>
			</c:url>
			<a href="${sort}">↓</a>
			</th>
			<th>
				<c:url value="${thisUrl}" var="sort">
					<c:param name="ostuArveBy" value="summaKM"></c:param>
					<c:param name="orderType" value="asc"></c:param>
					<c:param name="orderOA" value="true"></c:param>
				</c:url> <a href="${sort}">↑ </a> 
			SummaKM
			<c:url value="${thisUrl}" var="sort">
				<c:param name="ostuArveBy" value="summaKM"></c:param>
				<c:param name="orderType" value="desc"></c:param>
				<c:param name="orderOA" value="true"></c:param>
			</c:url>
			<a href="${sort}">↓</a>
			</th>
			<th>
				<c:url value="${thisUrl}" var="sort">
					<c:param name="ostuArveBy" value="objektid"></c:param>
					<c:param name="orderType" value="asc"></c:param>
					<c:param name="orderOA" value="true"></c:param>
				</c:url> <a href="${sort}">↑ </a> 
			Objekt
			<c:url value="${thisUrl}" var="sort">
					<c:param name="ostuArveBy" value="objektid"></c:param>
					<c:param name="orderType" value="desc"></c:param>
					<c:param name="orderOA" value="true"></c:param>
			</c:url>
			<a href="${sort}">↓</a>
			
			</th>
			<th>
				<c:url value="${thisUrl}" var="sort">
					<c:param name="ostuArveBy" value="tasutud"></c:param>
					<c:param name="orderType" value="asc"></c:param>
					<c:param name="orderOA" value="true"></c:param>
				</c:url> <a href="${sort}">↑ </a> 
			Tasutud
			<c:url value="${thisUrl}" var="sort">
					<c:param name="ostuArveBy" value="tasutud"></c:param>
					<c:param name="orderType" value="desc"></c:param>
					<c:param name="orderOA" value="true"></c:param>
			</c:url>
			<a href="${sort}">↓</a>
			</th>
			<th>Pdf</th>
		</tr>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<tr>

				<td><input type="text" name="tarnija" /></td>
				<td><input type="text" name="arveNumber" class="arveNumber" /></td>
				<td><input type="text" id="oaKuuPaevInput" name="kuuPaev" class="kuuPaev" /></td>
				<td><input type="text" name="summaIlmaKM" class="summaIlmaKM" /></td>
				<td><input type="text" name="summaKM" class="summaKM" /></td>
				<td><input type="text" name="objektid" /></td>
				<td><input type="checkbox" name="tasutud" class="tasutud" /></td>
				<td><input type="file" name="pdf" /></td>
				<td><input type="submit" value="salvesta" /></td>


			</tr>
		</sec:authorize>


		<c:forEach items="${ostuArved}" var="ostuArve">
			<c:set var="summaKokkuKM" value="${summaKokkuKM + ostuArve.summaKM }" />
			<c:set var="summaKokkuIlmaKM" value="${summaKokkuIlmaKM + ostuArve.summaIlmaKM }" />
			<tr class="tableRow">
				<td class="tableColumn">${ostuArve.tarnija }</td>
				<td class="tableColumn">${ostuArve.arveNumber }</td>
				<td class="tableColumn"><fmt:formatDate value="${ostuArve.kuuPaev}" pattern="dd/MM/yyyy" /></td>
				<td class="tableColumn">${ostuArve.summaIlmaKM }</td>
				<td class="tableColumn">${ostuArve.summaKM }</td>
				<td class="tableColumn"><c:forEach items="${ostuArve.objektid }" var="objekt">
						<a href="<c:url value='/objektid/${objekt.id}'  />">${objekt.nimi}</a>
						<br />
					</c:forEach></td>
				<td class="tableColumn"><sec:authorize access="hasRole('ROLE_ADMIN')">
						<c:choose>
							<c:when test="${ostuArve.tasutud}">
								<c:url value="/ostuarved/${ostuArve.id }/tasutud" var="tas">
									<c:param name="tasutud" value="false" />
								</c:url>
								<a href="${tas}">Jah</a>
							</c:when>
							<c:otherwise>
								<c:url value="/ostuarved/${ostuArve.id}/tasutud" var="tas">
									<c:param name="tasutud" value="true" />
								</c:url>
								<a href="${tas }">Ei</a>
							</c:otherwise>
						</c:choose>
					</sec:authorize> <sec:authorize access="hasRole('ROLE_USER')">
						<c:choose>
							<c:when test="${ostuArve.tasutud}">
								<input type="checkbox" disabled="disabled" checked="checked" />
							</c:when>
							<c:otherwise>
								<input type="checkbox" disabled="disabled" />
							</c:otherwise>
						</c:choose>
					</sec:authorize></td>
				<td class="tableColumn"><a href="<c:url value='${ostuArve.pdfLocation }' />">Vaata pdf-i</a></td>
				<td><sec:authorize access="hasRole('ROLE_ADMIN')">
						<a href="<c:url value="/ostuarved/id/${ostuArve.id}" />">Muuda</a>
					</sec:authorize></td>
			</tr>
		</c:forEach>
		<tr class="tableRow"><td class="tableColumn">Summad kokku:</td><td></td><td></td><td class="tableColumn summaIlmaKM">${summaKokkuIlmaKM}</td>
			<td class="tableColumn summaKM">${summaKokkuKM }</td>
			</tr>
	</table>
</form>
