<%@page import="java.util.List"%>
<%@page import="code.User"%>

<h1>Moji prijatelji</h1>
<table>
<%
		List<User> usrs=(List<User>)request.getAttribute("friends");
		if(usrs!=null){
			%>
			
			<table style="color:black; border-bottom:1px solid black;">
			<h3>Rezultati:</h3>
			<%
			for(User u : usrs){
				%>
				
				<tr>
					<td><%=u.getIme() %></td>
					<td><%=u.getPriimek() %></td>
				</tr>
				
				<%
			}
		}
	 %>
	</table>
</table>