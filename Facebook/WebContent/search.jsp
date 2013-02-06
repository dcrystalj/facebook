<%@page import="java.util.List"%>
<%@page import="code.User"%>

<div class="post">
	<h2>Iskalnik</h2>
	<h2 class="title">
		<a href="#">Vnesite iskalne podatke </a>
	</h2>
	<form method="GET" action="searchUser">
		<table>
			<tr>
				<td><label for="name">Ime: </label></td>
				<td><input type="text" id="name" size="30" name="name"/></td>
			</tr>
			<tr>
				<td><label for="sname">Priimek: </label></td>
				<td><input type="text" id="sname" size="30" name="sname"/></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center"><input type="submit" value="Isci med uporabniki" /></td>
			</tr>
			
		</table>
	</form>
	<hr />
	
	<%
		List<User> usrs=(List<User>)request.getAttribute("result");
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
					<td><a href=SendFriendRequest?id=<%= u.getId() %>>Dodaj med prijatelje</a>
				</tr>
				<%
			}
		}
	 %>
	</table>
</div>
