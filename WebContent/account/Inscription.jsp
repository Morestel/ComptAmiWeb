<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page errorPage="../erreur.jsp" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="../css/account.css">
</head>
<body>


	<form action ="http://localhost:8080/ComptAmiWeb/index.jsp" name ="inscription" method="get" class="box_form" >
      <label for="pseudo">PSEUDO</label><input type="text" name="pseudo" id="pseudo" pattern="\w*" title="Les caractères spéciaux sauf '_' ne sont pas acceptés">
 	  <label for="email">EMAIL</label><input type="text" name="email" id="email" required>
      <label for="password">MOT DE PASSE</label><input type="password" name="password" id="password" required>
      <label for="password_confirm">CONFIRMER LE MOT DE PASSE</label><input type="password" name="password_confirm" id="password_confirm" required>
      <input type="hidden" name="id_pseudo" id="id_pseudo" value="-1"></input>
      
  	  <div id="Vérifier" onclick="valider()">Vérification</div>
      <input type="submit" class="bouton_valid" title="Valider" value="Valider">
      <a href="http://localhost:8080/ComptAmiWeb/account/Connexion.jsp">Vous avez déja un compte ?</a>
      </form>
      
      
  
</body>
<script type="text/javascript" src="../js/scriptv2.js"></script>

</html>