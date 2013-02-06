<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<html>
<head>
	<jsp:include page="head.jsp" />	
</head>
<body>

	<header>
		<%@include file="header.jsp"%>
	</header>
	<div id="page">
		<div id="content">

	<%if (request.getParameter("page")!= null) {
		String s = request.getParameter("page");
		if(s.equals("news")){ %>
			<jsp:include page="news.jsp" />
			<%
      	}
      	
      	else if(s.equals("profile")){ %>
			<jsp:include page="profile.jsp" />
			<%
      	}
      	else if (s.equals("search")){ %>
			<jsp:include page="search.jsp" />
			<%
      	}
      	else if (s.equals("presentation")){ %>
			<jsp:include page="presentation.jsp" />
			<%
      	}
      	else if (s.equals("editprofile")){ %>
			<jsp:include page="editprofile.jsp" />
			<%
      	}
      	else if (s.equals("printUser")){ %>
			<jsp:include page="printUser.jsp" />
			<%
      	}
      	else if (s.equals("insertNews")){ %>
			<jsp:include page="insertNews.jsp" />
			<%
      	}
      	else if (s.equals("showFriends")){ %>
			<jsp:include page="showFriends.jsp" />
			<%
      	}
      	else if (s.equals("friendsRequests")){ %>
			<jsp:include page="friendsRequests.jsp" />
			<%
      	}
      	else if (s.equals("editnews")){ %>
			<jsp:include page="editnews.jsp" />
			<%
      	}
 	    else if (s.equals("insertNotification")){ %>
			<jsp:include page="insertNotification.jsp" />
			<%
      	}
      	else{ %>
			<jsp:include page="home.jsp" />
			<%
      	}
      }
      
      else{ %>
			<jsp:include page="home.jsp" />
			<% 
	  }%>
		</div>
		<div id="sidebar"></div>
		<!-- end #sidebar -->
		<div style="clear: both;">&nbsp;</div>
	</div>
	<div class="container">
		<img src="images/img03.png" width="1000" height="40" alt="" />
	</div>
	<footer>
		<%@include file="footer.jsp"%>
	</footer>
</body>
</html>