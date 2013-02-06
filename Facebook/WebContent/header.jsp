<div id="user-loggedin">

<%
	if(session.getAttribute("uporabnik") == null){	
    	response.sendRedirect("login.jsp");
	}
    else{
    	code.User u =  (code.User)session.getAttribute("uporabnik");
    	String ime = u.getIme();
    	String priimek = u.getPriimek();
    	
    	out.write("Pozdravljeni <b>" + ime + " " + priimek + "</b>!");
    }%>
	
	<a style="margin-left: 20px; text-color:white;" href="logout">Odjava</a> <br />
	<a href="ShowFriendsRequest"><span style="text-align:right; text-color: red;">Caka vas <%=session.getAttribute("stProsenj") %> prosenj za prijateljstvo</span></a>
</div>
<div id="header-wrapper">
	<div id="header" class="container">
			<div id="logo" style="width:200px;">
				<h1><a href="#">Facebook</a></h1>
			</div>
			<jsp:include page="menu.jsp" />
	</div>
</div>