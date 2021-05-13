
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@page import="compteami.Connexion"%> 
   <%@page import="compteami.Serveur" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
   <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>  

<jsp:useBean id="Connexion" class="compteami.Connexion" scope="application"></jsp:useBean>
<!-- <jsp:useBean id="listeSession" class="compteami.Serveur" scope="application">></jsp:useBean> -->

<!DOCTYPE html>
<html lang="fr">
  <head>
  <script>
      
      function connectToWebSocket(){
  		let ws = new WebSocket("ws://localhost:8080/ComptAmiWeb/CompteAmi");
  	}
  	
  connectToWebSocket();
      </script>
    <meta charset="utf-8">
    <title>MAD LIONS</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@900&display=swap" rel="stylesheet">
  </head>
<body>
<%
	
	String Id_userr = "1";
	session.setAttribute("Id_user", Id_userr);
	
	%>
	<%	String Id_user = (String) session.getAttribute("Id_user"); %>
	<% String Id = "1"; %>
	
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
     		<td><c:out value ="${row.Id_event}"/></td>
     		<td><%= Id_user %></td>
     </tr>
     </c:forEach>
     </table>
     
     
	<%@include file="../header.html" %>
	<div class="main">
      <div class="main_content">
        <section class="news">
          <h2>DERNIÈRES NEWS</h2>
          <ul class="list_news">
            <li>
              <a href="#">
                <article class="full-news-box">
                  <div class="news-box" style="background : url(''):">
                    <h3>Yo</h3>
                    <div class="filtre"></div>
                  </div>
                  <div class="news-box-text"><p>Il y a <%= Serveur.getListeSession().size() %> utilisateur(s)</p>
                  <p><%= Id %></p></div>
                  
                </article>
              </a>
            </li>
          </ul>
        </section>
        </div>
       
      </div>
      <input type="button" name="submit" value="Créer un évènement" onclick="self.location='www.google.fr'" target="_blank">
      <script src="js/script.js"></script>
      
  </body>
  
</html>
	
</body>
</html>