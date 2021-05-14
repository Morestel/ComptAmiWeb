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
	
	<%= existe_user %>
	<!-- Affichage de la liste des évènements concernant l'utilisateur -->
	<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"  
     url="jdbc:mysql://localhost:4456/mt05697s"  
     user="mt05697s"  password="3RSHLEL7"/> 
     <sql:query var="listEvent" dataSource="${db }">
     SELECT Id_event 
     FROM Participe
     WHERE Id_user = "<c:out value="${Id_user}"/>"
     </sql:query>
     
     <table>
     <c:forEach var="row" items="${listEvent.rows }">
     	<tr>
     		<td><a href="Evenement.jsp?event=${row.Id_event }"><c:out value ="${row.Id_event}"/></a></td>
     		<td>${param.id_pseudo}</td>
     </tr>
     </c:forEach>
     </table>
     

	 <c:import url="footer.html" />
      <script src="js/script.js"></script>
</body>
</html>