	<%
		code.User u=(code.User)session.getAttribute("uporabnik");
	 %>
	<table>
		<tr><td>Uporabnisko Ime:</td><td><%=((u.getUpIme()!=null)?u.getUpIme():"/") %></td></tr>	
		<tr><td>Ime:</td><td><%=((u.getIme()!=null)?u.getIme():"/") %></td></tr>
		<tr><td>Priimek:</td><td><%=((u.getPriimek()!=null)?u.getPriimek():"/")%></td></tr>
		<tr><td>E-mail:</td><td><%=((u.getEmail()!=null)?u.getEmail():"/")%></td></tr>
		<tr><td>Spol:</td><td><%=((u.getSpol().equals(""))?u.getSpol():"/")%></td></tr>
		<tr><td>Naslov:</td><td><%=((u.getNaslov()!=null)?u.getNaslov():"/")%></td></tr>
		<tr><td>Leto rojstva:</td><td><%=((u.getLetnik()!=0)?u.getLetnik():"/")%></td></tr>
		<tr><td>Letnik diplomiranja:</td><td><%=((u.getLetnikDiplomiranja()!=0)?u.getLetnikDiplomiranja():"/")%></td></tr>
		<tr><td>Studijska smer:</td><td><%=((u.getSmer()!=null)?u.getSmer():"/")%></td></tr>
		<tr><td>Stopnja studija:</td><td><%=((u.getStopnja()!=null)?u.getStopnja():"/")%></td></tr>
		<tr><td>Vrsta studija:</td><td><%=((u.getVrsta()!=null)?u.getVrsta():"/")%></td></tr>
	</table>
	