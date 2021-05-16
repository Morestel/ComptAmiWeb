<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page errorPage="/erreur.jsp" %>
   <%@page import="compteami.Connexion"%> 
   <%@page import="compteami.Serveur" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
   <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>  
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste évènements</title>
	<link rel="stylesheet" type="text/css" href="css/main.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@900&display=swap" rel="stylesheet">
</head>
<body>

	<c:import url="header.html" />
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
	
	
	<!-- Affichage de la liste des évènements concernant l'utilisateur -->
	<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"  
     url="jdbc:mysql://localhost:4456/mt05697s"  
     user="mt05697s"  password="3RSHLEL7"/> 
     <sql:query var="listEvent" dataSource="${db}">
     SELECT Intitule, Id_event
     FROM Participe, Evenement
     WHERE Id_user = <%= existe_user %>
     AND Participe.Id_event = Evenement.Id
     </sql:query>
    
     <sql:query var="nb_event" dataSource="${db}">
     SELECT Count(DISTINCT Id_user) AS nb, Id_event
     FROM Participe
     GROUP BY Id_event
     
     </sql:query>
     
    <div class="main">
      <div class="main_content">
     	<h1>Liste des évènements</h1>
     		<ul>
	     <c:forEach var="row" items="${listEvent.rows }">
	     	<c:forEach var="col" items="${nb_event.rows}">
	     		 <c:if test = "${row.Id_event == col.Id_event}">
	     			<li><a href="Evenement.jsp?event=${row.Id_event}"><c:out value ="${row.Intitule}"/> (<c:out value="${col.nb}"/> participant(s))</a></li>
	     		</c:if>
	     	</c:forEach>
	     </c:forEach>
	    
     		</ul>
     	</div>
     </div>

	 <c:import url="footer.html" />
      <script src="js/script.js"></script>
</body>
</html>