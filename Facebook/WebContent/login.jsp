<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="head.jsp"  />
</head>
<body style="text-align:center">
	<h1>Prijava uporabnika</h1>
	<form method="post" action="login">
		<label for="upIme">Uporabnisko ime: <input type="text" id ="upIme" name="upIme"></input></label>
		<label for="geslo">Geslo: <input type="password" id ="geslo" name="geslo"></input></label>
		<br /><br />
		<input type="submit" id ="geslo" value="Prijava"></input><br />
		<a href="registration.jsp" style="text-color:#fff;">Se nimate uporabniskega imena?</a>
	</form>
	<%
		if(code.Servlet.LoginHandlerServlet.napake!=null && code.Servlet.LoginHandlerServlet.napake.size()!=0)
		for(String s : code.Servlet.LoginHandlerServlet.napake){
	%>
				<li style="color:red;"><%=s%></li><% 
		}
	 %>
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<footer>
		<%@include file="footer.jsp"  %> 
	</footer>
</body>
</html>