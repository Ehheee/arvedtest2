<%@ include file="include.jsp"%>
<h4>Objektid</h4>
<c:forEach items="${objektid}" var="objekt">
<a href="<c:url value='/ob/${objekt }' />" >${objekt}</a><br />
</c:forEach>