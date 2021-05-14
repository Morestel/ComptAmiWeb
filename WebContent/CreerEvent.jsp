<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@page import="compteami.Connexion"%> 
   <%@page import="compteami.Serveur" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
   <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Créer évènement</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="css/account.css">
</head>
<body>
<%@include file="../header.html" %>

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

	<form onsubmit="valider_creerEvent(${existe_user})" name ="creer_event" method="get" class="box_form" >
     <input type="text" name="intitule" id="intitule" placeholder="Intitule" required >
 	 <input type="text" name="budget" id="budget" placeholder="Budget initial" required>
    <textarea id="description" placeholder="Insérez votre description"></textarea>
     <input type="date" id="start" name="start"
       value="2018-07-22"
       min="2018-01-01" max="2018-12-31">
    <input type="date" id="end" name="end"
       value="2018-07-22"
       min="2018-01-01" max="2018-12-31">
      <input type="submit" class="bouton_valid" title="Valider" value="Valider">
     
      </form>




<c:import url="footer.html" />

<script type="text/javascript" src="js/scriptv2.js"></script>
</body>
</html>