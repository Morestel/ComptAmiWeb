
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@page import="compteami.Connexion"%> 
   <%@page import="compteami.Serveur" %>

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
                  <div class="news-box-text"><p>Il y a <%= Serveur.getListeSession().size() %> utilisateur(s)</p></div>
                  
                  </div>
                </article>
              </a>
            </li>
          </ul>
        </section>
       
      </div>
      <script src="js/script.js"></script>
      
  </body>
  
</html>
	
</body>
</html>