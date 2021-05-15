
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>  
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

<% ServletContext context = request.getSession().getServletContext();
	   String existe_user = (String) context.getAttribute("id_pseudo"); 
	%>
	
	<% 
		if (existe_user == null || existe_user.equals("-1")){
			String raison = "Veuillez vous connecter";
			session.setAttribute("raison", raison);
			response.sendRedirect("erreur.jsp");
			
		}
	%>



  
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
  
  
  <%--<c:import url="header.html" />--%>
  
  <!-- Affichage du budget -->
   <div id="aff_budget">Budget:<div id ="bud"></div></div>
   
   <!-- Changement dans le budget -->
   <form name="payer" method="post">
   <input type="text" id="payer" placeholder="Payer"/>
   <div onclick="retirer('${param.event}', '<%= context.getAttribute("id_pseudo")%>')">Payer</div>
   </form>
   
  <!-- Affichage de la liste de messages -->
  <% for (Message m : listeMessage.getAll()) { %>
	   [<%= m.getDate() %>] <%= m.getPseudo() %> : <%= m.getMessage() %> <br/>
	<% } %>
	
	<!-- Ajouter un message -->
	<form name="envoieMess" method="post" onsubmit="ajouter_message('<%= context.getAttribute("id_pseudo")%>', '${param.event}')">
		 <textarea id="messageArea" placeholder="Envoyer un message"></textarea>
		 <button type="submit" name="valider" value="Envoyer">Envoyer</button>
	</form>
	
	<!-- Affichage de la liste des participants -->
	
	<div class="participe">
	<h1>Liste des participants à cet évènement</h1>
	<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"  
     url="jdbc:mysql://localhost:4456/mt05697s"  
     user="mt05697s"  password="3RSHLEL7"/> 
     <sql:query var="listeParticipant" dataSource="${db}">
     SELECT Pseudo 
     FROM Utilisateur, Evenement, Participe
     WHERE Participe.Id_event = ${param.event}
     AND Participe.Id_user = Utilisateur.Id
     AND Participe.Id_event = Evenement.Id
     </sql:query>
	
	<ul>
     <c:forEach var="row" items="${listeParticipant.rows }">
     		<li><c:out value ="${row.Pseudo}"/></li>
     </c:forEach>
     </ul>
     
	</div>
	
	<!--  Ajout d'un participant à l'évènement -->
	<form onsubmit="ajouter_participant(${param.event})" name ="participant" method="post" class="box_form" >
		<input type="text" id="ajout" name="ajout" required/>
		<input type="submit" class="bouton_valid" title="Ajouter" value="Ajouter"/>
	</form>
	
<c:import url="footer.html" />


<script type="text/javascript" src="js/scriptv2.js"></script>
</body>
</html>