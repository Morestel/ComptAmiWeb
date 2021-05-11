<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<c:import url="../header.html" />
<div class="login-box">
    <h2>Connexion</h2>
    <form method="post">
   	<div class="user-box">
    <input type="text" name="pseudo" id="pseudo" pattern="\w*" title="Les caractères spéciaux sauf '_' ne sont pas acceptés" value="" required><label for="pseudo">PSEUDO</label></div>
  
    <div class="user-box"><input type="password" name="password" id="password"  minlength="6" title="6 caractère minimum" required><label for="password">MOT DE PASSE</label></div>
     
   
    <input type="submit" class="bouton_valid" title="Connexion" value="Se connecter">
    </form>
    <a class="alt_co" href="https://mira2.univ-st-etienne.fr/~rg06871s/projet/account?auth=signup">Créer un compte</a></div>
</body>
</html>