
<%@page import="code.Servlet.SendNotificationHandlerServlet"%>
<%
if(session.getAttribute("uporabnik") == null){
	session.setAttribute("uporabnik", new code.User());
}
code.User u=(code.User)session.getAttribute("uporabnik");

if(u.getTipUporabnika()>=2){
	if(SendNotificationHandlerServlet.status!=null  && !SendNotificationHandlerServlet.status.equals("")){
	%>
	<h2><%= SendNotificationHandlerServlet.status %></h2>
	<%
	}
%>	
	
	<h1>Poslji obvestilo</h1>
	<form method="POST" action="sendnotification" style="margin-left:auto; margin-right:auto; text-align: center; width:600px; margin-top:20px">
		<table style="positon:relative; margin-left:auto; margin-right:auto;">
			<tr>
				<td width="150px">Naslov:</td>
				<td><input type="text" size="30" name="name" value="" />*
				</td>
			</tr>
			<tr>
				<td>Vsebina:</td>
				<td><textarea cols="50" rows="10" name="content"></textarea>
				</td>
			</tr>
			<tr align="center">
				<td colspan="2"><input type="submit" value="Poslji obvestilo"
				style="width: 190px" />
			</td>
			</tr>
		</table>
	</form>
	
	<%
	}
	else{
	%>
		<span>Nimas pravic</span>
	<%
	}
	 %>	
	
	