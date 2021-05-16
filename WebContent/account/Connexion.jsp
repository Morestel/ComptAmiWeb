<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page errorPage="../erreur.jsp" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion</title>
<link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="../css/account.css">
</head>

<body>
<c:import url="../header.html" />

<div class="login-box">
    <h2>Connexion</h2>
    
    <form action ="http://localhost:8080/ComptAmiWeb/index.jsp" id ="form" method="post" name="connexion">
   	<div class="user-box">
    <input type="text" name="pseudo" id="pseudo" pattern="\w*" title="Les caractères spéciaux sauf '_' ne sont pas acceptés" value="" required><label for="pseudo">PSEUDO</label></div>
  
    <div class="user-box"><input type="password" name="password" id="password"  minlength="6" title="6 caractère minimum" required><label for="password">MOT DE PASSE</label></div>
     <input type="hidden" name="id_pseudo" id="id_pseudo" value="-1"></input>
   	<div id="Vérifier" class="verif" onclick="valider_connexion()">Vérification</div>
    <input id ="valid" type="submit" class="bouton_valid" title="Connexion" value="Se connecter">
	
    </form>
    
    <a class="alt_co" href="http://localhost:8080/ComptAmiWeb/account/Inscription.jsp">Créer un compte</a>
    </div>
    
    <% System.out.println(request.getParameter("id_pseudo")); %>
    
    <c:import url="../footer.html" />
</body>

<script type="text/javascript" src="../js/script.js"></script>
</html>