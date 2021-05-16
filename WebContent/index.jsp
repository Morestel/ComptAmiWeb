
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page errorPage="/erreur.jsp" %>
   <%@page import="compteami.Connexion"%> 
   <%@page import="compteami.Serveur" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
   <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>  

<jsp:useBean id="Connexion" class="compteami.Connexion" scope="application"></jsp:useBean>

<!DOCTYPE html>
<html lang="fr">
  <head>
  
    <meta charset="utf-8">
    <title>ComptAmi</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@900&display=swap" rel="stylesheet">
  </head>
<body>

	<%	String Id_user = (String) session.getAttribute("Id_user"); %>
	<% ServletContext context = request.getSession().getServletContext(); %>
	
	<c:if test="${not empty param.id_pseudo or param.id_pseudo > -1 }">
		<c:catch var="donner_id">
			<c:set var="id_pseudo" value="${param.id_pseudo }" scope = "application"></c:set>
		</c:catch>
	  	
  	</c:if>
  	
  	<c:import url="header.html" />
	<div class="main">
      <div class="main_content">
        <section class="news">
          <h2>BIENVENUE</h2>
          <p>Bienvenu sur le site ComptAmi : Le site vous permet de créer des évènements avec vos amis avec un budget défini à l'avance !</p>
          <p>Chaque participant met la somme qu'il souhaite investir dans cet évènement</p>
          <p>Une messagerie propre à chaque évènement est également intégrée</p>
          <p>Bonne visite !</p>
          <p>/!\ Un email vous sera envoyer si jamais la date limite de l'évènement est dépassé et que le solde de votre évènement n'est toujours pas à 0 !</p>
          <input type="button" name="submit" value="Créer un évènement" onclick="self.location='CreerEvent.jsp'">
 
          
          <p>Il y a <%= Serveur.getListeSession().size() %> utilisateur(s) en ligne</p>
		</section>
		
		<c:import url="Sponsor.html" />
        </div>
       
        
      </div>
      
      
      
      <c:import url="footer.html" />
      <script src="js/script.js"></script>
      
  </body>
  
</html>