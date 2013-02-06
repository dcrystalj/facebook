	
	<%
	if(session.getAttribute("uporabnik") == null){
		session.setAttribute("uporabnik", new code.User());
	}
	code.User u=(code.User)session.getAttribute("uporabnik");
	
	if(u.getTipUporabnika()>=2){
	%>	
	<div style="text-algin: center; margin-left: 150px">
		<form method="post" action="editnews">
			<textarea cols="50" rows="10" name="content"><%=request.getParameter("content")%></textarea>
			<br>
			<input type="hidden" name="id" value="<%=request.getParameter("id")%>" />
			<input type="submit" value="Uredi novico" style="width: 190px" />
		</form>
	</div>
	<%
	}
	else{
	%>
		<span>Nimas pravic</span>
	<%
	}
	 %>