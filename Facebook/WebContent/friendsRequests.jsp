<%@page import="java.util.List"%>
<%@page import="code.User"%>

<div class="post">
	<h3>Nepotrjene prosnje za prijateljstvo</h3>
	
		<table style="color:black">
			<%
			List<User> usrs=(List<User>)request.getAttribute("prosnjePrijatelji");
			for(User u : usrs){
				%>
				
				<tr>
					<td><%=u.getIme() %></td>
					<td><%=u.getPriimek() %></td>
					<td style="margin-left:20px;"><a href="FriendsRequestAction?action=1&id=<%=u.getId()%>"><img src="images/ok_button.jpg" alt="Ok" style="width:30px;"></img></a></td>
					<td><a href="FriendsRequestAction?action=-1&id=<%=u.getId()%>"><img src="images/cancel_button.jpg" alt="Cancel" style="width:30px;"></img></a></td>
					<hr />
				</tr>
				<%
			}
	 %>
	</table>
</div>
