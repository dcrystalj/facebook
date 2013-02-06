<%@page import="code.Servlet.AddNewsHandlerServlet"%>
<%
	if(session.getAttribute("uporabnik") == null){
		session.setAttribute("uporabnik", new code.User());
	}
	code.User u=(code.User)session.getAttribute("uporabnik");
%>
	<form method="POST" action="insertnews" style="margin-left:auto; margin-right:auto; text-align: center; width:600px; margin-top:20px">
		<table style="positon:relative; margin-left:auto; margin-right:auto;">
			<tr>
				<td width="150px">Naziv:</td>
				<td><input type="text" size="30" name="name" value="" />*
				</td>
			</tr>
			<tr>
				<td>Vsebina:</td>
				<td><textarea cols="50" rows="10" name="content"></textarea>
				</td>
			</tr>
			<tr align="center">
				<td colspan="2"><input type="submit" value="Vstavi novico"
				style="width: 190px" />
			</td>
			</tr>
		</table>
	</form>
	<ul>
	<%
	if(AddNewsHandlerServlet.napake!=null && AddNewsHandlerServlet.napake.size()!=0){
		for(String s : AddNewsHandlerServlet.napake){ %>
			<li style="color:red;"><%=s%></li>
		<% }
	}
	 %>
	</ul>