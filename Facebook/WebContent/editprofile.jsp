<%@page import="code.Servlet.EditProfileServlet"%>
<%
	code.User u=(code.User)session.getAttribute("uporabnik");
	//Uporaba za avtomatsko izbran radio button
	String moski="", zenski="", smer=(u.getSmer()==null)?"":u.getSmer(), stopnja=(u.getStopnja()==null)?"":u.getStopnja(), vrsta=(u.getVrsta()==null)?"":u.getVrsta(); 
	if(u.getSpol().equals("m"))
		moski="checked";
	else if(u.getSpol().equals("z"))
		zenski="checked";
		
	Cookie[] c = request.getCookies();
	String stil1="", stil2="";
	for(Cookie k : c){
		if(k.getName().equals("style")){
			if(k.getValue().equals("2")){
				stil2="checked";
			}
			else{
				stil1="checked";
			}
		}
	}
%>
<h1>Uredite svoj profil</h1>
	<a href="ShowConfirmedFriends"><h3>Prikazi moje prijatelje</h3></a>
	<p><b>Spodaj lahko spremenite vase podatke:</b></p>
	<form method="POST" action="editprofile" style="positon:relative; margin-left:auto; margin-right:auto;">
	<table>
			<tr>
				<td width="150px">Uporabnisko ime:</td>
				<td><input type="text" size="30" name="username" value="<%=u.getUpIme()%>" />*
				</td>
			</tr>
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
				<td><input type="text" size="30" name="letnik" value="<%=u.getLetnik()%>"/>
				</td>
			</tr>
			<tr>
				<td>Leto diplomiranja:</td>
				<td><input type="text" size="30" name="letnikDip" value="<%=u.getLetnikDiplomiranja()%>" />*
				</td>
			</tr>
			<tr>
				<td>Studijska smer:</td>
				<td><select size="1" name="smer">
						<option value="" <%if(smer==""){out.println("selected");}%>> </option>
						<option value="ri"  <%if(smer!=null&&smer.equals("ri")){out.println("selected");}%>>Racunalnistvo in informatika</option>
						<option value="rm" <%if(smer!=null&&smer.equals("rm")){out.println("selected");}%>>Racunalnistvo in matematika</option>
						<option value="ui" <%if(smer!=null&&smer.equals("ui")){out.println("selected");}%>>Upravna informatika</option>
				</select>*</td>
			</tr>
			<tr>
				<td>Stopnja studija:</td>
				<td><select size="1" name="stopnja">
						<option value="" <%if(stopnja==""){out.println("selected");}%>> </option>
						<option value="1" <%if(stopnja!=null&&stopnja.equals("1")){out.println("selected");}%>>1</option>
						<option value="2" <%if(stopnja!=null&&stopnja.equals("2")){out.println("selected");}%>>2</option>
						<option value="3" <%if(stopnja!=null&&stopnja.equals("3")){out.println("selected");}%>>3</option>
				</select>*</td>
			</tr>
			<tr>
				<td>Vrsta studija:</td>
				<td><select size="1" name="vrsta">
						<option value="" <%if(vrsta==""){out.println("selected");}%>> </option>
						<option value="1" <%if(vrsta!=null&&vrsta.equals("1")){out.println("selected");}%>>Redni</option>
						<option value="2" <%if(vrsta!=null&&vrsta.equals("2")){out.println("selected");}%>>Izredni</option>
				</select>*</td>
			</tr>
		</table>
	<hr />
	<p><b>Spremenite geslo:</b></p>
	<table>
		<tr>
			<td width="150px">Vase geslo:</td>
			<td><input type="password" size="30" name="geslo"
				value=""></td>
		</tr>
		<tr>
			<td>Novo geslo:</td>
			<td><input type="password" size="30" name="gesloNovo" value=""></td>
		</tr>
	</table>
	<hr />
	<p><b>Izberite stilno predlogo:</b></p>
	<table>
		<tr>
			<td colspan="2"><label for="rb1">Crna (privzeta)</label><input id="rb1" type="radio" value="1" name="radio"  <%=stil1 %>/>
							<label for="rb2">Svetla</label><input id="rb2" type="radio" value="2" name="radio" <%=stil2 %>/>
			</td>
		</tr>
	
		<tr>
			<td colspan="2" align="center"><input type="submit" value="Spremeni podatke" /></td>
		</tr>
		<tr style="margin-top:10px;">
			<td align="center"><a href="editprofile?izvoz=0">Izvoz v HTML</a></td>
			<td align="center"><a href="editprofile?izvoz=1">Izvoz v Excel</a></td>
		</tr>
	</table>
</form>
<ul>
	<%
	if(EditProfileServlet.napake!=null && EditProfileServlet.napake.size()!=0){
		for(String s : EditProfileServlet.napake){ %>
			<li style="color:red;"><%=s%></li>
		<% }
	}
	 %>
</ul>
