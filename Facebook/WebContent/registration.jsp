<%@page import="code.Servlet.RegistrationServlet"%>
<%@page import="code.User"%>
<%
	if(session.getAttribute("uporabnik") == null){
		session.setAttribute("uporabnik", new code.User());
	}
	User u=(code.User)session.getAttribute("uporabnik");
	//Uporaba za avtomatsko izbran radio button
	String moski="", zenski="", smer=u.getSmer(), stopnja=u.getStopnja(), vrsta=u.getVrsta(); 
	if(u.getSpol().equals('m'))
		moski="checked";
	else if(u.getSpol().equals('z'))
		zenski="checked";

%>
<html>
<head>
<%@include file="head.jsp"%>
</head>
<body style="text-align: center; width:100%">
	<h1>Registracija uporabnika</h1>
	<div style="margin-left:auto; margin-right:auto;">
	<form method="POST" action="registration" style="margin-left:auto; margin-right:auto; text-align: center; width:600px; margin-top:20px">
		<table style="positon:relative; margin-left:auto; margin-right:auto;">
			<tr>
				<td width="150px">Ime:</td>
				<td><input type="text" size="30" name="name" value="<%=u.getIme()%>" />*
				</td>
			</tr>
			<tr>
				<td>Priimek:</td>
				<td><input type="text" size="30" name="lastname" value="<%=u.getPriimek()%>"/>*
				</td>
			</tr>
			<tr>
				<td>Uporabnisko ime:</td>
				<td><input type="text" size="30" name="username" value="<%=u.getUpIme()%>"/>*
				</td>
			</tr>
			<tr>
				<td>E-mail:</td>
				<td><input type="text" size="30" name="email" value="<%=u.getEmail()%>"/>*
				</td>
			</tr>
			<tr>
				<td>Spol:</td>
				<td>
				<label for="m">Moski:</label>
				<input id="m" type="radio" name="spol" value="m" <%=moski%>/> 
				<label for="z">Zenski:</label>
				<input id="z" type="radio" name="spol" value="z" <%=zenski%>/>
				</td>
			</tr>
			<tr>
				<td>Naslov:</td>
				<td><input type="text" size="30" name="naslov" value="<%=u.getNaslov()%>" />*
				</td>
			</tr>
			<tr>
				<td>Leto rojstva:</td>
				<td><input type="text" size="30" name="letnik" value="<%=((u.getLetnik())==-1)?"":u.getLetnik() %>"/>
				</td>
			</tr>
			<tr>
				<td>Leto diplomiranja:</td>
				<td><input type="text" size="30" name="letnikDip" value="<%=(u.getLetnikDiplomiranja()==-1)?"":u.getLetnikDiplomiranja()%>" />*
				</td>
			</tr>
			<tr>
				<td>Studijska smer:</td>
				<td><select size="1" name="smer">
						<option value="""> </option>
						<option value="ri"  <%if(smer.equals("ri")){out.println("selected");}%>>Racunalnistvo in informatika</option>
						<option value="rm" <%if(smer.equals("rm")){out.println("selected");}%>>Racunalnistvo in matematika</option>
						<option value="ui" <%if(smer.equals("ui")){out.println("selected");}%>>Upravna informatika</option>
				</select>*</td>
			</tr>
			<tr>
				<td>Stopnja studija:</td>
				<td><select size="1" name="stopnja">
						<option value="""> </option>
						<option value="1" <%if(stopnja.equals("1")){out.println("selected");}%>>1</option>
						<option value="2" <%if(stopnja.equals("2")){out.println("selected");}%>>2</option>
						<option value="3" <%if(stopnja.equals("3")){out.println("selected");}%>>3</option>
				</select>*</td>
			</tr>
			<tr>
				<td>Vrsta studija:</td>
				<td><select size="1" name="vrsta">
						<option value=""> </option>
						<option value="1" <%if(vrsta.equals("1")){out.println("selected");}%>>Redni</option>
						<option value="2" <%if(vrsta.equals("2")){out.println("selected");}%>>Izredni</option>
				</select>*</td>
			</tr>
		</table>
		<hr />
		<table style="positon:relative; margin-left:auto; margin-right:auto; margin-bottom:40px;">
			<tr>
				<td width="150px">Password:</td>
				<td><input type="password" size="30" name="geslo"
					value="">*
				</td>
			</tr>
			<tr>
				<td>Repeat password:</td>
				<td><input type="password" size="30" name="gesloPonovljeno"
					value="">*
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Registriraj" />
				</td>
			</tr>
			<tr>
				<td colspan="2"">* Polja so obvezna</td>
			</tr>
		</table>
	</form>
	<ul>
	<%
	if(RegistrationServlet.napake!=null && RegistrationServlet.napake.size()!=0){
		for(String s : RegistrationServlet.napake){%>
			<li style="color:red;"><%=s%></li><% 
		}
		session.invalidate();
	}
	 %>
	 
</ul>
	
	</div>
	<div> <%@include file="footer.jsp"%>
	</div>
</body>
</html>