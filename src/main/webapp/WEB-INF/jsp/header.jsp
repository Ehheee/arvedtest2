<%@ include file="include.jsp"%>
<%@ page language="java" contentType="text/html; charset=utf-8" 
    pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="<c:url value="/resources/style.css" />" rel="stylesheet" type="text/css" />
<link href="<c:url value="/resources/javascript/jquery/anytime.5.0.1-1403131246.css" />" rel="stylesheet" type="text/css" />
<sec:authorize access="hasRole('ROLE_ADMIN')">
<script type="text/javascript">
	var admin = true;
	var formSubmitUrl = "<c:url value = '/' />";
</script>
</sec:authorize>
<!--  
 <link rel="stylesheet" href="//www.ama3.com/anytime/anytime.5.0.1-1403131246.css" />
        <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
        <script src="//www.ama3.com/anytime/anytime.5.0.1-1403131246.js"></script>
-->
<script type="text/javascript" src="<c:url value='/resources/javascript/jquery/jquery-1.11.0.js' />"></script>
<script type="text/javascript" src="<c:url value='/resources/javascript/jquery/anytime.5.0.1-1403131246.js' />"></script>



<script type="text/javascript" src="<c:url value='/resources/javascript/utility.js' />" ></script>
<script type="text/javascript" src="<c:url value='/resources/javascript/binders.js' />" /></script>
<script type="text/javascript" src="<c:url value='/resources/javascript/init.js' />"></script>


</head>
<body>

	<div id="headerContainer">
		<div id="headerLinks">
			<c:url value="/ob" var="ob" />
			<c:url value="/m" var="ma" />
			<c:url value="/o" var="oa" />
			<a href="${ob }">Objektid</a> <a href="${ma }">Müügiarved</a> <a href="${oa }">Ostuarved</a>

		</div>
		<div id="userInfo">
			<sec:authorize access="isAuthenticated()">
				Tere, <sec:authentication property="principal.username" /> - <a href="<c:url value='/logout' />">Logi välja</a>
			</sec:authorize>
			<sec:authorize access="isAnonymous()">
				<a href="<c:url value='/login' />">Logi sisse</a>, et lehte kasutada
			</sec:authorize>
		</div>
		<div style="clear: both;"></div>
	</div>