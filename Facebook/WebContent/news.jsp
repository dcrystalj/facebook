<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="code.DAO.NoviceDAOImpl"%>
<%@page import="code.Novice"%>
<%@page import="code.Entiteta"%>
<%@page import="code.Servlet.ShowNewsInitServlet"%>
<%
	if(session.getAttribute("uporabnik") == null){
		session.setAttribute("uporabnik", new code.User());
	}
	code.User u=(code.User)session.getAttribute("uporabnik");
%>
<div class="post">	
	<h2>Novice</h2>
	<h3><a href="index.jsp?page=insertNews">Vpisi novo novico</a></h3>
	<%
		List<Entiteta> list = new ArrayList<Entiteta>();
		if(request.getAttribute("news")!= null){
			list= (List<Entiteta>)request.getAttribute("news");
			for(Entiteta en : list){
				Novice n = (Novice)en;		
				%>
				<hr />
				<p class="meta">
					<span class="date"><%= n.getDatum_objave()%></span><span class="posted">Objavil: 
					<a href="#"><%=n.getId_avtorja()%></a>
					</span>
				</p>
				<div style="clear: both; color: green;"><%=n.getNaziv()%> 
				</div>
				<div class="entry">
						 <%=n.getVsebina()%>
				</div>
			
		<%	
				if(u.getTipUporabnika()>=2){
					%>
					<br />
					<a href="index.jsp?page=editnews&id=<%=n.getId()%>&content=<%=n.getVsebina()%>">Uredi</a>
					<form method="post" action="deletenews">
						<input type="hidden" name="id" value="<%=n.getId()%>" />
						<input type="submit" value="Izbrisi Novico" />
					</form>
					<%
				}
			}
		
		} %>
</div>
