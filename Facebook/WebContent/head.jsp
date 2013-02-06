<title>Facebook</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href='http://fonts.googleapis.com/css?family=Oswald:400,300' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Abel' rel='stylesheet' type='text/css'>
<link href="style.css" rel="stylesheet" type="text/css" />
<% //nastavi izbrani stil iz piskotka
Cookie[] piskoti = request.getCookies();
String style= "-1";
if(piskoti != null){
	for(Cookie piskot: piskoti){
		if(piskot.getName().equals("style")){
			style = piskot.getValue();
			if(style.equals("2")){ %>
				<link href="style1.css" rel="stylesheet" type="text/css" /><%
			}
			else{
				%><link href="style.css" rel="stylesheet" type="text/css" /><%
			}
			break;
		}
	}
}
else {%><link href="style.css" rel="stylesheet" type="text/css" /><%} %>




