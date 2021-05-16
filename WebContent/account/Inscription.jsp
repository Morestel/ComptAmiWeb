<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page errorPage="../erreur.jsp" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription</title>
<link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="../css/account.css">
</head>
<body>

<c:import url="../header.html" />

<div class="login-box">
    <h2>Inscription</h2>
	<form action ="http://localhost:8080/ComptAmiWeb/index.jsp" name ="inscription" method="get" id="form" >
      <div class="user-box">
      
      <input type="text" name="pseudo" id="pseudo" pattern="\w*" title="Les caractères spéciaux sauf '_' ne sont pas acceptés" required>
      <label for="pseudo">PSEUDO</label>
      </div>
      
      <div class="user-box">
 	  <input type="email" name="email" id="email" required>
 	  <label for="email">EMAIL</label>
 	  </div>
 	  
 	  <div class="user-box">
      <input type="password" name="password" id="password" required>
      <label for="password">MOT DE PASSE</label>
      </div>
      
      <div class="user-box">
      <input type="password" name="password_confirm" id="password_confirm" required>
      <label for="password_confirm">CONFIRMER LE MOT DE PASSE</label>
      </div>
      
      <input type="hidden" name="id_pseudo" id="id_pseudo" value="-1"></input>
  	  <div id="Vérifier" class="verif" onclick="valider()">Vérification</div>
      <input type="submit" class="bouton_valid" title="Valider" value="Valider">
     
      </form>
       <a class="alt_co" href="http://localhost:8080/ComptAmiWeb/account/Connexion.jsp">Vous avez déja un compte ?</a>
       </div>
       
  <c:import url="../footer.html" />
</body>
<script type="text/javascript" src="../js/script.js"></script>

</html>