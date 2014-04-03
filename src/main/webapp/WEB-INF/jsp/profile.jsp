<%@ include file="include.jsp" %>
<form action="<c:url value='/profiil/ridadearv' />" method="post">
	<input type="hidden" value="${arvedUser.id}" />
	<input type="text" value="${arvedUser.ridadeArv}" name="ridadeArv"/>
	<input type="submit" value="salvesta" />
</form>

Parool peab olema vähemalt 5 tähemärki pikk
<form action="<c:url value='/profiil/parool' />" method="post">
	<input type="hidden" value="${arvedUser.id}" />
	<input type="text" name="vanaParool" />
	<input type="text" name="uusParool1" />
	<input type="text" name="uusParool2" />
	<input type="submit" value="muuda" />
</form >

<c:if test="${changePassword == false}" >
	Viga parooli vahetamisel
</c:if>

<c:if test ="${changePassword == true}" >
	Parool vahetatud
</c:if>