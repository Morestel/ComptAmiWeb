<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>

<html lang="fr">
  <head>
    <meta charset="UTF-8">
    <title>Authentification</title>
    
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="css/account.css">
  
  </head>
  <body>
 	 <c:import url="header.html" />
  
    <div class="main">
      <div class="main_content">
	<div class="box_accounti">
	  <p>Pas de compte ?</p>
	  <a href="account/Inscription.jsp">
	    <button class="bouton" type="button">
	      <span>S'inscrire</span>
	    </button>
	  </a>
	</div>
	<div class="box_accountc">
	  <p>J'ai un compte</p>
	  <a href="account/Connexion.jsp">
	    <button class="bouton" type="button">
	      <span>Se connecter</span>
	    </button>
	  </a>
	</div>
      </div>
    </div>
   <!--   <?php include '../includes/footer.html'; ?>-->
  </body>
</html>
