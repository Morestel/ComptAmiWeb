
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page errorPage="/erreur.jsp" %>
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


<body onload="demander_budget(${param.event})">

<c:import url="header.html" />
  
	<!--  Chargement liste des messages -->
	<c:if test="${ not empty param.event}">
	<c:set target="${listeMessage}" property="id_event" value="${ param.event }" />
	<% 
	listeMessage.loadMessage(listeMessage.getId_event());
	
	%>
		
	
	</c:if>
	
	<!-- Redirection si on tente d'accéder à cette page sans paramètre -->
	<c:if test="${empty param.event }">
	<%
	
	String raison = "Page d'évènement non trouvée";
	session.setAttribute("raison", raison);
	response.sendRedirect("erreur.jsp");
	
	%>
	</c:if>
  
  <!-- Affichage du budget -->
   <div id="aff_budget">Budget : </div>
  <!-- Affichage de la liste de messages -->
  <% for (Message m : listeMessage.getAll()) { %>
	   [<%= m.getDate() %>] <%= m.getPseudo() %> : <%= m.getMessage() %> <br/>
	<% } %>
	
	<!--  Ajout d'un participant à l'évènement -->
	<form onsubmit="ajouter_participant(${param.event})" name ="participant" method="get" class="box_form" >
		<input type="text" id="ajout" name="ajout" required/>
		<input type="submit" class="bouton_valid" title="Ajouter" value="Ajouter">
	</form>
	
<c:import url="footer.html" />


<script type="text/javascript" src="js/scriptv2.js"></script>
</body>
</html>