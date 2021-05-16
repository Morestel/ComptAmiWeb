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

	<c:import url="header.html" />
	
	<div class="login-box">
    <h2>Créer Event</h2>
      
		<form name ="creer_event" method="post" id="form" >
		
		<div class="user-box">
	     <input type="text" name="intitule" id="intitule" required ><label for="intitule">INTITULE</label>
	     </div>
	    
	     <div class="user-box">
	 	 <input type="text" name="budget" id="budget" required><label for="budget">BUDGET</label>
	 	 </div>
	 	
	 	 <div class="user-box">
	    	<textarea id="description" placeholder="Insérez votre description"></textarea>
	    </div>
	    
	    <div class="user-box">
	     <input type="date" id="start" name="start"
	       value="2021-01-01"
	       min="2021-01-01" max="2030-01-01">
	       </div>
	       
	       <div class="user-box">
	    <input type="date" id="end" name="end"
	       value="2021-01-01"
	       min="2021-01-01" max="2030-01-01">
	       </div>
	       
	      <div class= "verif" onclick = "valider_creerEvent('<%= context.getAttribute("id_pseudo")%>')">Créer</div>
	     
      </form>
      
      </div>
     



<c:import url="footer.html" />

<script type="text/javascript" src="js/script.js"></script>
</body>
</html>