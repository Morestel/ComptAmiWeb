
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page errorPage="/erreur.jsp" %>
   <%@page import="compteami.Connexion"%> 
   <%@page import="compteami.Serveur" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
   <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>  

<jsp:useBean id="Connexion" class="compteami.Connexion" scope="application"></jsp:useBean>
<!-- <jsp:useBean id="listeSession" class="compteami.Serveur" scope="application">></jsp:useBean> -->

<!DOCTYPE html>
<html lang="fr">
  <head>
  
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
	
	<% ServletContext context = request.getSession().getServletContext(); %>
	<%=
	context.getAttribute("id_pseudo") 
	%>
	<c:if test="${not empty param.id_pseudo or param.id_pseudo > -1 }">
		<c:catch var="donner_id">
			<c:set var="id_pseudo" value="${param.id_pseudo }" scope = "application"></c:set>
		</c:catch>
	     
	     ${donner_id.message }
	  	<p>TEST</p>
	  	<c:out value="${donner_id.message }" default = ""/>
  	</c:if>
  	
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
                  <p>${param.id_pseudo}</p></div>
                  
                </article>
              </a>
            </li>
          </ul>
        </section>
        </div>
       
      </div>
      <input type="button" name="submit" value="Créer un évènement" onclick="self.location='CreerEvent.jsp'" target="_blank">
      
      
      <c:import url="footer.html" />
      <script src="js/scriptv2.js"></script>
      
  </body>
  
</html>
	
</body>
</html>