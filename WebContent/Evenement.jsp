
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="compteami.GestionMessages" %>
    <%@page import="compteami.Message" %>
    
    <!-- UseBean -->
   	<jsp:useBean id="listeMessage" class="compteami.GestionMessages" scope="page"></jsp:useBean>
   	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Evenement</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/account.css">
</head>
<body>

<c:import url="header.html" />
  
	
	<c:if test="${ not empty param.event}">
	<c:set target="${listeMessage}" property="id_event" value="${ param.event }" />
	<% 
	listeMessage.loadMessage(listeMessage.getId_event());
	
	%>
		
	
	</c:if>
	<c:if test="${empty param.event }">
	<%
	
	String raison = "Page d'évènement non trouvée";
	session.setAttribute("raison", raison);
	response.sendRedirect("erreur.jsp");
	
	%>
	</c:if>
  
  <% for (Message m : listeMessage.getAll()) { %>
	   [<%= m.getDate() %>] <%= m.getPseudo() %> : <%= m.getMessage() %> <br/>
	<% } %>



<c:import url="footer.html" />

</body>
</html>