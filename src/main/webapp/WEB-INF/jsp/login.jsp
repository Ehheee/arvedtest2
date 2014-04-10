<%@ include file="include.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<h1>Please Log In</h1>
<br  />
<form action="login_check" method="post">
	<label for="username">Username</label>:
	<input id="username" name="username" size="20" maxlength="50" type="text" />
	<br />
	<label for="password">Password</label>:
	<input id="password" name="password" size="20" maxlength="50" type="password" />
	<br />
	<input type="submit" value="Login" />

</form>
</body>
</html>